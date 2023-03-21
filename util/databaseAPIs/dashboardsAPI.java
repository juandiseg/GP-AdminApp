package util.databaseAPIs;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import componentsFood.currentShiftEmployee;
import componentsFood.orderView;
import componentsFood.product;
import util.inputFormatting.dateInverter;

public class dashboardsAPI extends abstractManagerDB {

    // GET data for panels.
    public static ArrayList<orderView> getLast10OrderViewers() {
        ArrayList<orderView> tempList = new ArrayList<orderView>();
        try (Connection connection = DriverManager.getConnection(getURL(), getUser(), getPassword())) {
            String query = "SELECT TIME_TO_SEC(SUBTIME(time_out, time_in))/60 AS time, subtotal, cash FROM orders_summary WHERE total IS NOT NULL ORDER BY date DESC LIMIT 10;";
            ppdStatement = connection.prepareStatement(query);
            try {
                ResultSet rs = ppdStatement.executeQuery();
                while (rs.next()) {
                    int minutes = rs.getInt("time");
                    float subtotal = rs.getFloat("subtotal");
                    String method = "Card";
                    if (rs.getBoolean("cash"))
                        method = "Cash";
                    tempList.add(new orderView(minutes, subtotal, method));
                }
                return tempList;
            } catch (Exception SQLTimeoutException) {
                return null;
            }
        } catch (SQLException e) {
            throw new IllegalStateException("Cannot connect the database!", e);
        }
    }

    public static ArrayList<currentShiftEmployee> getCurrentlyWorkingEmployees() {
        ArrayList<currentShiftEmployee> tempList = new ArrayList<>();
        try (Connection connection = DriverManager.getConnection(getURL(), getUser(), getPassword())) {
            String query = "SELECT name, role_name, salary, hours_a_week, start_shift, end_shift FROM employees_schedule NATURAL JOIN employees LEFT JOIN roles ON roles.role_id = employees.role_id WHERE shift_date = CURDATE() AND start_shift < CURTIME() AND end_shift > CURTIME();";
            ppdStatement = connection.prepareStatement(query);
            try {
                ResultSet rs = ppdStatement.executeQuery();
                while (rs.next()) {
                    String name = rs.getString("name");
                    String role = rs.getString("role_name");
                    Float salary = rs.getFloat("salary");
                    String hoursWeek = rs.getString("hours_a_week");
                    String startShift = rs.getString("start_shift");
                    String endShift = rs.getString("end_shift");
                    tempList.add(new currentShiftEmployee(name, role, salary, hoursWeek, startShift, endShift));
                }
                return tempList;
            } catch (Exception SQLTimeoutException) {
                return null;
            }
        } catch (SQLException e) {
            throw new IllegalStateException("Cannot connect the database!", e);
        }
    }

    public static product getMostSoldProductToday() {
        try (Connection connection = DriverManager.getConnection(getURL(), getUser(), getPassword())) {
            String query = "SELECT * FROM products WHERE product_id = (SELECT product_id FROM orders_summary NATURAL JOIN orders_items WHERE date = CURDATE() GROUP BY product_id ORDER BY SUM(quantity) DESC LIMIT 1);";
            ppdStatement = connection.prepareStatement(query);
            try {
                ResultSet rs = ppdStatement.executeQuery();
                if (rs.next()) {
                    int id = rs.getInt("product_id");
                    int catID = rs.getInt("category_id");
                    String date = rs.getString("product_date");
                    String name = rs.getString("name");
                    float price = rs.getFloat("price");
                    Boolean active = rs.getBoolean("active");
                    return new product(id, catID, dateInverter.invert(date), name, price, active);
                }
                return null;
            } catch (Exception SQLTimeoutException) {
                return null;
            }
        } catch (SQLException e) {
            return null;
        }
    }

    public static product getMostSoldProductWeek() {
        try (Connection connection = DriverManager.getConnection(getURL(), getUser(), getPassword())) {
            String query = "SELECT * FROM products WHERE product_id = (SELECT product_id FROM orders_summary NATURAL JOIN orders_items WHERE date BETWEEN DATE_SUB(CURDATE(), INTERVAL 6 DAY) AND CURDATE() GROUP BY product_id ORDER BY SUM(quantity) DESC LIMIT 1);";
            ppdStatement = connection.prepareStatement(query);
            try {
                ResultSet rs = ppdStatement.executeQuery();
                if (rs.next()) {
                    int id = rs.getInt("product_id");
                    int catID = rs.getInt("category_id");
                    String date = rs.getString("product_date");
                    String name = rs.getString("name");
                    float price = rs.getFloat("price");
                    Boolean active = rs.getBoolean("active");
                    return new product(id, catID, dateInverter.invert(date), name, price, active);
                }
                return null;
            } catch (Exception SQLTimeoutException) {
                return null;
            }
        } catch (SQLException e) {
            return null;
        }
    }

    public static product getMostSoldProductMonth() {
        try (Connection connection = DriverManager.getConnection(getURL(), getUser(), getPassword())) {
            String query = "SELECT * FROM products WHERE product_id = (SELECT product_id FROM orders_summary NATURAL JOIN orders_items WHERE date BETWEEN DATE_SUB(CURDATE(), INTERVAL 1 MONTH) AND CURDATE() GROUP BY product_id ORDER BY SUM(quantity) DESC LIMIT 1);";
            ppdStatement = connection.prepareStatement(query);
            try {
                ResultSet rs = ppdStatement.executeQuery();
                if (rs.next()) {
                    int id = rs.getInt("product_id");
                    int catID = rs.getInt("category_id");
                    String date = rs.getString("product_date");
                    String name = rs.getString("name");
                    float price = rs.getFloat("price");
                    Boolean active = rs.getBoolean("active");
                    return new product(id, catID, dateInverter.invert(date), name, price, active);
                }
                return null;
            } catch (Exception SQLTimeoutException) {
                return null;
            }
        } catch (SQLException e) {
            return null;
        }
    }

    public static float getAvgSalePriceToday() {
        try (Connection connection = DriverManager.getConnection(getURL(), getUser(), getPassword())) {
            String query = "SELECT AVG(total) as total FROM orders_summary WHERE date = CURDATE();";
            ppdStatement = connection.prepareStatement(query);
            try {
                ResultSet rs = ppdStatement.executeQuery();
                if (rs.next())
                    return rs.getFloat("total");
                return 0;
            } catch (Exception SQLTimeoutException) {
                return 0;
            }
        } catch (SQLException e) {
            return 0;
        }
    }

    public static int getAvgOrderTimeToday() {
        try (Connection connection = DriverManager.getConnection(getURL(), getUser(), getPassword())) {
            String query = "SELECT AVG(TIME_TO_SEC(SUBTIME(time_out, time_in))/60) AS time FROM orders_summary WHERE date = CURDATE();";
            ppdStatement = connection.prepareStatement(query);
            try {
                ResultSet rs = ppdStatement.executeQuery();
                if (rs.next())
                    return rs.getInt("time");
                return 0;
            } catch (Exception SQLTimeoutException) {
                return 0;
            }
        } catch (SQLException e) {
            return 0;
        }
    }

    // GET data for graphs.
    public static float getSalesOnDay(int minusDays) {
        try (Connection connection = DriverManager.getConnection(getURL(), getUser(), getPassword())) {
            String query = "SELECT SUM(total) as total FROM orders_summary WHERE date = DATE_SUB(CURDATE(), INTERVAL ? DAY);";
            ppdStatement = connection.prepareStatement(query);
            ppdStatement.setInt(1, minusDays);
            try {
                ResultSet rs = ppdStatement.executeQuery();
                if (rs.next())
                    return rs.getFloat("total");
                return 0;
            } catch (Exception SQLTimeoutException) {
                return 0;
            }
        } catch (SQLException e) {
            return 0;
        }
    }

    public static ArrayList<catSalesTuple> getProductCategoryDistribution(boolean today) {
        ArrayList<catSalesTuple> tempList = new ArrayList<>();
        try (Connection connection = DriverManager.getConnection(getURL(), getUser(), getPassword())) {
            String interval = "WHERE date BETWEEN DATE_SUB(CURDATE(), INTERVAL 6 DAY) AND CURDATE()";
            if (today)
                interval = "WHERE date = CURDATE()";
            String query = "SELECT categories.category_name, SUM(quantity) as quantitySold FROM orders_summary NATURAL JOIN orders_items JOIN products ON orders_items.product_id = products.product_id JOIN categories ON categories.category_id = products.category_id "
                    + interval + " GROUP BY categories.category_name;";
            ppdStatement = connection.prepareStatement(query);
            try {
                ResultSet rs = ppdStatement.executeQuery();
                while (rs.next()) {
                    float sales = rs.getFloat("quantitySold");
                    String categoryName = rs.getString("category_name");
                    tempList.add(new dashboardsAPI.catSalesTuple(sales, categoryName));
                }
                return tempList;
            } catch (Exception SQLTimeoutException) {
                return null;
            }
        } catch (SQLException e) {
            throw new IllegalStateException("Cannot connect the database!", e);
        }
    }

    public static ArrayList<catSalesTuple> getMenuCategoryDistribution(boolean today) {
        ArrayList<catSalesTuple> tempList = new ArrayList<>();
        try (Connection connection = DriverManager.getConnection(getURL(), getUser(), getPassword())) {
            String interval = "WHERE date BETWEEN DATE_SUB(CURDATE(), INTERVAL 6 DAY) AND CURDATE()";
            if (today)
                interval = "WHERE date = CURDATE()";
            String query = "SELECT categories.category_name, SUM(quantity) as quantitySold FROM orders_summary NATURAL JOIN orders_menus JOIN menus ON orders_menus.menu_id = menus.menu_id JOIN categories ON categories.category_id = menus.category_id "
                    + interval + " GROUP BY categories.category_name;";
            ppdStatement = connection.prepareStatement(query);
            try {
                ResultSet rs = ppdStatement.executeQuery();
                while (rs.next()) {
                    float sales = rs.getFloat("quantitySold");
                    String categoryName = rs.getString("category_name");
                    tempList.add(new dashboardsAPI.catSalesTuple(sales, categoryName));
                }
                return tempList;
            } catch (Exception SQLTimeoutException) {
                return null;
            }
        } catch (SQLException e) {
            throw new IllegalStateException("Cannot connect the database!", e);
        }
    }

    public static ArrayList<catSalesTuple> getCategorySalesDistribution(boolean today) {
        ArrayList<catSalesTuple> tempList = new ArrayList<>();
        try (Connection connection = DriverManager.getConnection(getURL(), getUser(), getPassword())) {
            String interval = "WHERE date BETWEEN DATE_SUB(CURDATE(), INTERVAL 6 DAY) AND CURDATE()";
            if (today)
                interval = "WHERE date = CURDATE()";
            String query = "SELECT result.category_name, SUM(result.sold) as sold FROM ((SELECT category_name, SUM(qty*price) AS sold FROM products NATURAL JOIN categories, (SELECT product_id, SUM(quantity) AS qty FROM orders_summary NATURAL JOIN orders_items "
                    + interval
                    + " GROUP BY product_id) AS subq WHERE products.product_id = subq.product_id AND active = 1 GROUP BY category_name) UNION (SELECT category_name, SUM(sold*price) AS sold FROM products NATURAL JOIN categories, (SELECT product_id, SUM(productQuantity * quantity) AS sold FROM menus_products, (SELECT menus.menu_id, SUM(quantity) AS quantity FROM orders_summary NATURAL JOIN orders_menus JOIN menus ON orders_menus.menu_id = menus.menu_id "
                    + interval
                    + " GROUP BY menus.menu_id) AS subq WHERE subq.menu_id = menus_products.menu_id AND (menus_products.menu_id, menu_products_date) IN (SELECT menu_id, MAX(menu_products_date) AS menu_products_date FROM menus_products GROUP BY menu_id) GROUP BY product_id ) AS subsubq WHERE subsubq.product_id = products.product_id AND active = 1 GROUP BY category_name)) AS result GROUP BY result.category_name;";
            ppdStatement = connection.prepareStatement(query);
            try {
                ResultSet rs = ppdStatement.executeQuery();
                while (rs.next()) {
                    float sales = rs.getFloat("sold");
                    String categoryName = rs.getString("category_name");
                    tempList.add(new dashboardsAPI.catSalesTuple(sales, categoryName));
                }
                return tempList;
            } catch (Exception SQLTimeoutException) {
                return null;
            }
        } catch (SQLException e) {
            throw new IllegalStateException("Cannot connect the database!", e);
        }
    }

    public static ArrayList<tuple> getEmployeeCategoryPercentagesToday() {
        ArrayList<tuple> tempList = new ArrayList<tuple>();
        try (Connection connection = DriverManager.getConnection(getURL(), getUser(), getPassword())) {
            String query = "SELECT SUM(SUBTIME(end_shift,start_shift)) AS totalTime, role_name FROM employees_schedule NATURAL JOIN employees NATURAL JOIN roles WHERE shift_date = CURDATE() GROUP BY role_name;";
            ppdStatement = connection.prepareStatement(query);
            try {
                ResultSet rs = ppdStatement.executeQuery();
                while (rs.next()) {
                    int percentage = rs.getInt("totalTime");
                    String roleName = rs.getString("role_name");
                    tempList.add(new dashboardsAPI.tuple(percentage, roleName));
                }
                return tempList;
            } catch (Exception SQLTimeoutException) {
                return null;
            }
        } catch (SQLException e) {
            throw new IllegalStateException("Cannot connect the database!", e);
        }
    }

    public static class tuple {
        public int frequency;
        public String roleName;

        public tuple(int frequency, String roleName) {
            this.frequency = frequency;
            this.roleName = roleName;
        }
    }

    public static class catSalesTuple {
        public float sold;
        public String categoryName;

        public catSalesTuple(float sold, String categoryName) {
            this.sold = sold;
            this.categoryName = categoryName;
        }
    }

    public static int getFrequencyOfShift(int hour, boolean weekly) {
        try (Connection connection = DriverManager.getConnection(getURL(), getUser(), getPassword())) {
            String shift_date = "shift_date = CURDATE()";
            if (weekly)
                shift_date = "shift_date BETWEEN DATE_SUB(CURDATE(), INTERVAL 7 DAY) AND CURDATE()";
            String query = "SELECT COUNT(shift_date) as frequency FROM employees_schedule WHERE " + shift_date
                    + " AND start_shift <= '"
                    + hour + ":59' AND end_shift >= '"
                    + (hour + 1) + ":00';";
            try (Statement stmt = connection.createStatement()) {
                ResultSet rs = stmt.executeQuery(query);
                int frequency = -1;
                if (rs.next())
                    frequency = rs.getInt("frequency");
                connection.close();
                return frequency;
            } catch (Exception e) {
                return -1;
            }
        } catch (SQLException e) {
            throw new IllegalStateException("Cannot connect the database!", e);
        }
    }

    public static float getExpensesSalaryDaily(int daysBack) {
        try (Connection connection = DriverManager.getConnection(getURL(), getUser(), getPassword())) {
            String query = "SELECT SUM(TIME_TO_SEC(SUBTIME(end_shift, start_shift))/3600*salary) AS expenses FROM employees_schedule NATURAL JOIN employees WHERE shift_date = SUBDATE(CURDATE(), INTERVAL ? DAY);";
            ppdStatement = connection.prepareStatement(query);
            ppdStatement.setInt(1, daysBack);
            try {
                ResultSet rs = ppdStatement.executeQuery();
                if (rs.next())
                    return rs.getInt("expenses");
                return 0;
            } catch (Exception SQLTimeoutException) {
                return 0;
            }
        } catch (SQLException e) {
            return 0;
        }
    }
}
