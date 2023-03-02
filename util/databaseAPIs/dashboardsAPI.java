package util.databaseAPIs;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import componentsFood.orderView;
import componentsFood.product;

public class dashboardsAPI extends abstractManagerDB {

    public ArrayList<orderView> getLast10OrderViewers() {
        ArrayList<orderView> tempList = new ArrayList<orderView>();
        try (Connection connection = DriverManager.getConnection(getURL(), getUser(), getPassword())) {
            String query = "SELECT TIME_TO_SEC(SUBTIME(time_out, time_in))/60 AS time, subtotal, cash FROM orders_summary WHERE total IS NOT NULL ORDER BY date DESC LIMIT 10;";
            try (Statement stmt = connection.createStatement()) {
                ResultSet rs = stmt.executeQuery(query);
                while (rs.next()) {
                    int minutes = rs.getInt("time");
                    float subtotal = rs.getFloat("subtotal");
                    String method = "Card";
                    if (rs.getBoolean("cash"))
                        method = "Cash";
                    tempList.add(new orderView(minutes, subtotal, method));
                }
                connection.close();
                return tempList;
            } catch (Exception e) {
                System.out.println(e);
                return tempList;
            }
        } catch (SQLException e) {
            throw new IllegalStateException("Cannot connect the database!", e);
        }
    }

    public int getAvgOrderTimeToday() {
        try (Connection connection = DriverManager.getConnection(getURL(), getUser(), getPassword())) {
            String query = "SELECT AVG(TIME_TO_SEC(SUBTIME(time_out, time_in))/60) AS time FROM orders_summary WHERE date = CURDATE()";
            try (Statement stmt = connection.createStatement()) {
                ResultSet rs = stmt.executeQuery(query);
                if (rs.next()) {
                    int minutes = rs.getInt("time");
                    connection.close();
                    return minutes;
                }
                connection.close();
                return 0;
            } catch (Exception e) {
                return 0;
            }
        } catch (SQLException e) {
            throw new IllegalStateException("Cannot connect the database!", e);
        }
    }

    public float getSalesOnDay(int minusDays) {
        try (Connection connection = DriverManager.getConnection(getURL(), getUser(), getPassword())) {
            String query = "SELECT SUM(total) as total FROM orders_summary WHERE date = DATE_SUB(CURDATE(), INTERVAL "
                    + minusDays + " DAY);";
            try (Statement stmt = connection.createStatement()) {
                ResultSet rs = stmt.executeQuery(query);
                if (rs.next()) {
                    float sales = rs.getFloat("total");
                    connection.close();
                    return sales;
                }
                connection.close();
                return 0;
            } catch (Exception e) {
                return 0;
            }
        } catch (SQLException e) {
            throw new IllegalStateException("Cannot connect the database!", e);
        }
    }

    public float getAvgSalePriceToday() {
        try (Connection connection = DriverManager.getConnection(getURL(), getUser(), getPassword())) {
            String query = "SELECT AVG(total) as total FROM orders_summary WHERE date = CURDATE()";
            try (Statement stmt = connection.createStatement()) {
                ResultSet rs = stmt.executeQuery(query);
                if (rs.next()) {
                    float sales = rs.getFloat("total");
                    connection.close();
                    return sales;
                }
                connection.close();
                return 0;
            } catch (Exception e) {
                return 0;
            }
        } catch (SQLException e) {
            throw new IllegalStateException("Cannot connect the database!", e);
        }
    }

    public product getMostSoldProductToday() {
        try (Connection connection = DriverManager.getConnection(getURL(), getUser(), getPassword())) {
            String query = "SELECT * FROM products WHERE product_id = (SELECT product_id FROM orders_summary NATURAL JOIN orders_items WHERE date = CURDATE() GROUP BY product_id ORDER BY SUM(quantity) DESC LIMIT 1)";
            try (Statement stmt = connection.createStatement()) {
                ResultSet rs = stmt.executeQuery(query);
                if (rs.next()) {
                    int id = rs.getInt("product_id");
                    int catID = rs.getInt("category_id");
                    String date = rs.getString("product_date");
                    String name = rs.getString("name");
                    float price = rs.getFloat("price");
                    Boolean active = rs.getBoolean("active");
                    connection.close();
                    return new product(id, catID, dateInverter.invert(date), name, price, active);
                }
                connection.close();
                return null;
            } catch (Exception e) {
                return null;
            }
        } catch (SQLException e) {
            throw new IllegalStateException("Cannot connect the database!", e);
        }
    }

    public product getMostSoldProductWeek() {
        try (Connection connection = DriverManager.getConnection(getURL(), getUser(), getPassword())) {
            String query = "SELECT * FROM products WHERE product_id = (SELECT product_id FROM orders_summary NATURAL JOIN orders_items WHERE date BETWEEN DATE_SUB(CURDATE(), INTERVAL 6 DAY) AND CURDATE() GROUP BY product_id ORDER BY SUM(quantity) DESC LIMIT 1)";
            try (Statement stmt = connection.createStatement()) {
                ResultSet rs = stmt.executeQuery(query);
                if (rs.next()) {
                    int id = rs.getInt("product_id");
                    int catID = rs.getInt("category_id");
                    String date = rs.getString("product_date");
                    String name = rs.getString("name");
                    float price = rs.getFloat("price");
                    Boolean active = rs.getBoolean("active");
                    connection.close();
                    return new product(id, catID, dateInverter.invert(date), name, price, active);
                }
                connection.close();
                return null;
            } catch (Exception e) {
                return null;
            }
        } catch (SQLException e) {
            throw new IllegalStateException("Cannot connect the database!", e);
        }
    }

    public product getMostSoldProductMonth() {
        try (Connection connection = DriverManager.getConnection(getURL(), getUser(), getPassword())) {
            String query = "SELECT * FROM products WHERE product_id = (SELECT product_id FROM orders_summary NATURAL JOIN orders_items WHERE date BETWEEN DATE_SUB(CURDATE(), INTERVAL 1 MONTH) AND CURDATE() GROUP BY product_id ORDER BY SUM(quantity) DESC LIMIT 1)";
            try (Statement stmt = connection.createStatement()) {
                ResultSet rs = stmt.executeQuery(query);
                if (rs.next()) {
                    int id = rs.getInt("product_id");
                    int catID = rs.getInt("category_id");
                    String date = rs.getString("product_date");
                    String name = rs.getString("name");
                    float price = rs.getFloat("price");
                    Boolean active = rs.getBoolean("active");
                    connection.close();
                    return new product(id, catID, dateInverter.invert(date), name, price, active);
                }
                connection.close();
                return null;
            } catch (Exception e) {
                return null;
            }
        } catch (SQLException e) {
            throw new IllegalStateException("Cannot connect the database!", e);
        }
    }

    public class tuple {
        public int percentage;
        public String roleName;

        public tuple(int percentage, String roleName) {
            this.percentage = percentage;
            this.roleName = roleName;
        }
    }

    public ArrayList<tuple> getEmployeeCategoryPercentagesToday() {
        ArrayList<tuple> tempList = new ArrayList<tuple>();
        try (Connection connection = DriverManager.getConnection(getURL(), getUser(), getPassword())) {
            String query = "SELECT SUM(SUBTIME(end_shift,start_shift)) AS totalTime, role_name FROM employees_schedule NATURAL JOIN employees NATURAL JOIN roles WHERE shift_date = CURDATE() GROUP BY role_name;";
            try (Statement stmt = connection.createStatement()) {
                ResultSet rs = stmt.executeQuery(query);
                while (rs.next()) {
                    int percentage = rs.getInt("totalTime");
                    String roleName = rs.getString("role_name");
                    tempList.add(new tuple(percentage, roleName));
                }
                connection.close();
                return tempList;
            } catch (Exception e) {
                System.out.println(e);
                return tempList;
            }
        } catch (SQLException e) {
            throw new IllegalStateException("Cannot connect the database!", e);
        }
    }

    public int getFrequencyOfShift(int hour, boolean weekly) {
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
                System.out.println(e);
                return -1;
            }
        } catch (SQLException e) {
            throw new IllegalStateException("Cannot connect the database!", e);
        }
    }

    public float expensesSalaryDaily(int daysBack) {
        try (Connection connection = DriverManager.getConnection(getURL(), getUser(), getPassword())) {
            String query = "SELECT SUM(TIME_TO_SEC(SUBTIME(end_shift, start_shift))/3600*salary) AS expenses FROM employees_schedule NATURAL JOIN employees WHERE shift_date = SUBDATE(CURDATE(), INTERVAL "
                    + daysBack + " DAY);";
            try (Statement stmt = connection.createStatement()) {
                ResultSet rs = stmt.executeQuery(query);
                int expenses = 0;
                if (rs.next())
                    expenses = rs.getInt("expenses");
                connection.close();
                return expenses;
            } catch (Exception e) {
                System.out.println(e);
                return 0;
            }
        } catch (SQLException e) {
            throw new IllegalStateException("Cannot connect the database!", e);
        }
    }
}
