package util.databaseAPIs;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import componentsFood.employee;

public class employeesAPI extends abstractManagerDB {

    // GET from database.
    public static employee getEmployee(int ID) {
        try (Connection connection = DriverManager.getConnection(getURL(), getUser(), getPassword())) {
            String query = "SELECT * FROM employees WHERE active = true AND employee_id = ?;";
            ppdStatement = connection.prepareStatement(query);
            ppdStatement.setInt(1, ID);
            try {
                ResultSet rs = ppdStatement.executeQuery();
                if (rs.next()) {
                    int employeeID = rs.getInt("employee_id");
                    String name = rs.getString("name");
                    float salary = rs.getFloat("salary");
                    String hoursWeek = rs.getString("hours_a_week");
                    int roleID = rs.getInt("role_id");
                    return new employee(employeeID, name, salary, hoursWeek, roleID, true);
                }
                return null;
            } catch (Exception SQLTimeoutException) {
                return null;
            }
        } catch (SQLException e) {
            throw new IllegalStateException("Cannot connect the database!", e);
        }
    }

    public static ArrayList<employee> getAllCurrentEmployeesOrdered() {
        ArrayList<employee> tempList = new ArrayList<employee>();
        try (Connection connection = DriverManager.getConnection(getURL(), getUser(), getPassword())) {
            String query = "SELECT * FROM employees WHERE active = true ORDER BY role_id;";
            ppdStatement = connection.prepareStatement(query);
            try {
                ResultSet rs = ppdStatement.executeQuery();
                while (rs.next()) {
                    int ID = rs.getInt("employee_id");
                    String name = rs.getString("name");
                    float salary = rs.getFloat("salary");
                    String hoursWeek = rs.getString("hours_a_week");
                    int roleID = rs.getInt("role_id");
                    tempList.add(new employee(ID, name, salary, hoursWeek, roleID, true));
                }
                return tempList;
            } catch (Exception SQLTimeoutException) {
                return null;
            }
        } catch (SQLException e) {
            throw new IllegalStateException("Cannot connect the database!", e);
        }
    }

    public static String getNameOfEmployee(int employeeID) {
        try (Connection connection = DriverManager.getConnection(getURL(), getUser(), getPassword())) {
            String query = "SELECT name FROM employees WHERE employee_id = ?;";
            ppdStatement = connection.prepareStatement(query);
            ppdStatement.setInt(1, employeeID);
            try {
                ResultSet rs = ppdStatement.executeQuery();
                if (rs.next())
                    return rs.getString("name");
                return "";
            } catch (Exception SQLTimeoutException) {
                return "";
            }
        } catch (SQLException e) {
            throw new IllegalStateException("Cannot connect the database!", e);
        }
    }

    public static String getRoleOfEmployee(int employeeID) {
        try (Connection connection = DriverManager.getConnection(getURL(), getUser(), getPassword())) {
            String query = "SELECT role_name FROM roles NATURAL JOIN employees WHERE employee_id = ?;";
            ppdStatement = connection.prepareStatement(query);
            ppdStatement.setInt(1, employeeID);
            try {
                ResultSet rs = ppdStatement.executeQuery();
                if (rs.next())
                    return rs.getString("role_name");
                return "";
            } catch (Exception SQLTimeoutException) {
                return "";
            }
        } catch (SQLException e) {
            throw new IllegalStateException("Cannot connect the database!", e);
        }
    }

    private static int getLastEmployeeID() {
        try (Connection connection = DriverManager.getConnection(getURL(), getUser(), getPassword())) {
            String query = "SELECT employee_id FROM employees ORDER BY employee_id DESC LIMIT 1;";
            ppdStatement = connection.prepareStatement(query);
            try {
                ResultSet rs = ppdStatement.executeQuery();
                if (rs.next()) {
                    return rs.getInt("employee_id");
                }
                return -1;
            } catch (Exception SQLTimeoutException) {
                return -1;
            }
        } catch (SQLException e) {
            throw new IllegalStateException("Cannot connect the database!", e);
        }
    }

    // ADD to database.
    public static boolean addEmployee(String name, float salary, String hoursWeek, int roleID) {
        try (Connection connection = DriverManager.getConnection(getURL(), getUser(), getPassword())) {
            int employeeID = getLastEmployeeID() + 1;
            String query = "INSERT INTO employees VALUES (?, ?, ?, ?, ?, true, NULL);";
            ppdStatement = connection.prepareStatement(query);
            ppdStatement.setInt(1, employeeID);
            ppdStatement.setString(2, name);
            ppdStatement.setFloat(3, salary);
            ppdStatement.setString(4, hoursWeek);
            ppdStatement.setInt(5, roleID);
            try {
                ppdStatement.executeUpdate();
                return true;
            } catch (Exception SQLTimeoutException) {
                return false;
            }
        } catch (SQLException e) {
            throw new IllegalStateException("Cannot connect the database!", e);
        }
    }

    // UPDATE in database.
    public static boolean updateEmployeeName(employee theEmployee, String name) {
        try (Connection connection = DriverManager.getConnection(getURL(), getUser(), getPassword())) {
            String query = "UPDATE employees SET name = ? WHERE employee_id = ?;";
            ppdStatement = connection.prepareStatement(query);
            ppdStatement.setString(1, name);
            ppdStatement.setInt(2, theEmployee.getId());
            try {
                ppdStatement.executeUpdate();
                return true;
            } catch (Exception SQLTimeoutException) {
                return false;
            }
        } catch (SQLException e) {
            throw new IllegalStateException("Cannot connect the database!", e);
        }
    }

    public static boolean updateEmployeeSalary(employee theEmployee, float salary) {
        try (Connection connection = DriverManager.getConnection(getURL(), getUser(), getPassword())) {
            String query = "UPDATE employees SET salary = ? WHERE employee_id = ?;";
            ppdStatement = connection.prepareStatement(query);
            ppdStatement.setFloat(1, salary);
            ppdStatement.setInt(2, theEmployee.getId());
            try {
                ppdStatement.executeUpdate();
                return true;
            } catch (Exception SQLTimeoutException) {
                return false;
            }
        } catch (SQLException e) {
            throw new IllegalStateException("Cannot connect the database!", e);
        }
    }

    public static boolean updateEmployeeHoursWeek(employee theEmployee, String hoursWeek) {
        try (Connection connection = DriverManager.getConnection(getURL(), getUser(), getPassword())) {
            String query = "UPDATE employees SET hours_a_week = ? WHERE employee_id = ?;";
            ppdStatement = connection.prepareStatement(query);
            ppdStatement.setString(1, hoursWeek);
            ppdStatement.setInt(2, theEmployee.getId());
            try {
                ppdStatement.executeUpdate();
                return true;
            } catch (Exception SQLTimeoutException) {
                return false;
            }
        } catch (SQLException e) {
            throw new IllegalStateException("Cannot connect the database!", e);
        }
    }

    public static boolean updateEmployeeRole(employee theEmployee, int roleID) {
        try (Connection connection = DriverManager.getConnection(getURL(), getUser(), getPassword())) {
            String query = "UPDATE employees SET role_id = ? WHERE employee_id = ?;";
            ppdStatement = connection.prepareStatement(query);
            ppdStatement.setInt(1, roleID);
            ppdStatement.setInt(2, theEmployee.getId());
            try {
                ppdStatement.executeUpdate();
                return true;
            } catch (Exception SQLTimeoutException) {
                return false;
            }
        } catch (SQLException e) {
            throw new IllegalStateException("Cannot connect the database!", e);
        }
    }

    // DELETE from database.
    public static boolean setEmployeeUnactive(employee theEmployee) {
        try (Connection connection = DriverManager.getConnection(getURL(), getUser(), getPassword())) {
            String query = "UPDATE employees SET active = false, inactive_since = CURDATE() WHERE employee_id = ?;";
            ppdStatement = connection.prepareStatement(query);
            ppdStatement.setInt(1, theEmployee.getId());
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

    public static boolean deleteFutureShifts(employee theEmployee) {
        try (Connection connection = DriverManager.getConnection(getURL(), getUser(), getPassword())) {
            String query = "DELETE FROM employees_schedule WHERE employee_id = ? AND shift_date >= CURDATE();";
            ppdStatement = connection.prepareStatement(query);
            ppdStatement.setInt(1, theEmployee.getId());
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

    // CHECK in database.
    public static boolean hasEmployeeFutureShifts(employee theEmployee) {
        try (Connection connection = DriverManager.getConnection(getURL(), getUser(), getPassword())) {
            String query = "SELECT employee_id FROM employees_schedule WHERE employee_id = ? AND shift_date >= CURDATE();";
            ppdStatement = connection.prepareStatement(query);
            ppdStatement.setInt(1, theEmployee.getId());
            try {
                ResultSet rs = ppdStatement.executeQuery();
                if (rs.next())
                    return true;
                return false;
            } catch (Exception SQLTimeoutException) {
                return false;
            }
        } catch (SQLException e) {
            throw new IllegalStateException("Cannot connect the database!", e);
        }
    }

}