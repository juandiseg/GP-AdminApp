package util.databaseAPIs;

import java.io.File;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import componentsFood.currentShiftEmployee;
import componentsFood.orderView;
import componentsFood.product;

public class descriptionsAPI extends abstractManagerDB {

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

    // INSERT data to database.
    public static boolean updateImageProduct(int productID, byte[] theImage) {
        try (Connection connection = DriverManager.getConnection(getURL(), getUser(), getPassword())) {
            String query = "UPDATE products_data SET image = ? WHERE product_id = ?;";
            ppdStatement = connection.prepareStatement(query);
            ppdStatement.setBytes(1, theImage);
            ppdStatement.setInt(2, productID);
            try {
                ppdStatement.executeUpdate();
                return true;
            } catch (Exception SQLTimeoutException) {
                return false;
            }
        } catch (SQLException e) {
            return false;
        }
    }

    public static boolean updateImageMenus(int menuID, byte[] theImage) {
        try (Connection connection = DriverManager.getConnection(getURL(), getUser(), getPassword())) {
            String query = "UPDATE menus_data SET image = ? WHERE menu_id = ?;";
            ppdStatement = connection.prepareStatement(query);
            ppdStatement.setBytes(1, theImage);
            ppdStatement.setInt(2, menuID);
            try {
                ppdStatement.executeUpdate();
                return true;
            } catch (Exception SQLTimeoutException) {
                return false;
            }
        } catch (SQLException e) {
            return false;
        }
    }

}
