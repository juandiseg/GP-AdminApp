package navigation.administration.shifts_Window;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeFormatterBuilder;
import java.util.ArrayList;

import componentsFood.currentShiftEmployee;
import componentsFood.employee;
import componentsFood.shift;
import util.abstractManagerDB;

public class shiftsAPI extends abstractManagerDB {

    // GET "product" objects from database.

    // "id", "Name", "Salary", "Hours per Week", "Role"
    public void addShift(String employeeID, String shiftDate, String startShift, String endShift) {
        try (Connection connection = DriverManager.getConnection(getURL(), getUser(), getPassword())) {
            String query = "INSERT INTO employees_schedule VALUES (" + employeeID + ", '" + shiftDate + "', '"
                    + startShift + "', '" + endShift + "', NULL, NULL, NULL)";
            try (Statement stmt = connection.createStatement()) {
                stmt.executeUpdate(query);
                connection.close();
            } catch (Exception e) {
                System.out.println(e);
            }
        } catch (SQLException e) {
            throw new IllegalStateException("Cannot connect the database!", e);
        }
    }

    public void updateRoleName(int roleID, String newName) {
        try (Connection connection = DriverManager.getConnection(getURL(), getUser(), getPassword())) {
            String query = "UPDATE roles SET role_name = '" + newName + "' WHERE role_id = " + roleID;
            try (Statement stmt = connection.createStatement()) {
                stmt.executeUpdate(query);
                connection.close();
            } catch (Exception e) {
                System.out.println(e);
            }
        } catch (SQLException e) {
            throw new IllegalStateException("Cannot connect the database!", e);
        }
    }

    public ArrayList<shift> getShiftsWithinDateSorted(String from, String to, boolean shift_date) {
        ArrayList<shift> tempList = new ArrayList<shift>();
        String sortingColumn = "employee_id";
        if (!shift_date)
            sortingColumn = "shift_date";
        try (Connection connection = DriverManager.getConnection(getURL(), getUser(), getPassword())) {
            String query = "SELECT * FROM employees_schedule NATURAL JOIN employees WHERE shift_date BETWEEN '" + from
                    + "' AND '" + to + "' ORDER BY " + sortingColumn + ", role_id;";
            try (Statement stmt = connection.createStatement()) {
                ResultSet rs = stmt.executeQuery(query);
                while (rs.next()) {
                    int employeeID = rs.getInt("employee_id");
                    String date = rs.getString("shift_date");
                    String startTime = rs.getString("start_shift");
                    String endTime = rs.getString("end_shift");
                    String realStartTime = rs.getString("realtime_in");
                    String realEndTime = rs.getString("realtime_out");
                    boolean undertime = rs.getBoolean("undertime");
                    tempList.add(
                            new shift(employeeID, date, startTime, endTime, realStartTime, realEndTime, undertime));
                }
                connection.close();
                return tempList;
            } catch (Exception e) {
                System.out.println(e);
                return tempList;
            }
        } catch (

        SQLException e) {
            throw new IllegalStateException("Cannot connect the database!", e);
        }
    }

    public ArrayList<shift> getAllFutureShiftsUntil(String to) {
        ArrayList<shift> tempList = new ArrayList<shift>();
        try (Connection connection = DriverManager.getConnection(getURL(), getUser(), getPassword())) {
            String query = "SELECT * FROM employees_schedule NATURAL JOIN employees WHERE shift_date BETWEEN '"
                    + LocalDate.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd")) + "' AND '" + to
                    + "'  ORDER BY role_id;";
            try (Statement stmt = connection.createStatement()) {
                ResultSet rs = stmt.executeQuery(query);
                while (rs.next()) {
                    int employeeID = rs.getInt("employee_id");
                    String date = rs.getString("shift_date");
                    String startTime = rs.getString("start_shift");
                    String endTime = rs.getString("end_shift");
                    String realStartTime = rs.getString("realtime_in");
                    String realEndTime = rs.getString("realtime_out");
                    boolean undertime = rs.getBoolean("undertime");
                    tempList.add(
                            new shift(employeeID, date, startTime, endTime, realStartTime, realEndTime, undertime));
                }
                connection.close();
                return tempList;
            } catch (Exception e) {
                System.out.println(e);
                return tempList;
            }
        } catch (

        SQLException e) {
            throw new IllegalStateException("Cannot connect the database!", e);
        }
    }

    public ArrayList<shift> getLateEntries() {
        ArrayList<shift> tempList = new ArrayList<shift>();
        try (Connection connection = DriverManager.getConnection(getURL(), getUser(), getPassword())) {
            String query = "SELECT * FROM employees_schedule NATURAL JOIN employees WHERE undertime = true ORDER BY shift_date, role_id";
            try (Statement stmt = connection.createStatement()) {
                ResultSet rs = stmt.executeQuery(query);
                while (rs.next()) {
                    int employeeID = rs.getInt("employee_id");
                    String date = rs.getString("shift_date");
                    String startTime = rs.getString("start_shift");
                    String endTime = rs.getString("end_shift");
                    String realStartTime = rs.getString("realtime_in");
                    String realEndTime = rs.getString("realtime_out");
                    boolean undertime = rs.getBoolean("undertime");
                    tempList.add(
                            new shift(employeeID, date, startTime, endTime, realStartTime, realEndTime, undertime));
                }
                connection.close();
                return tempList;
            } catch (Exception e) {
                System.out.println(e);
                return tempList;
            }
        } catch (

        SQLException e) {
            throw new IllegalStateException("Cannot connect the database!", e);
        }
    }

    public boolean addEmployee(String name, float salary, String hoursWeek, int roleID) {
        try (Connection connection = DriverManager.getConnection(getURL(), getUser(), getPassword())) {
            int employeeID = getLastEmployeeID() + 1;
            String query = "INSERT INTO employees VALUES (" + employeeID + ", '" + name + "', " + salary + ", '"
                    + hoursWeek + "', " + roleID + ", true);";
            try (Statement stmt = connection.createStatement()) {
                stmt.executeUpdate(query);
                connection.close();
                return true;
            } catch (Exception e) {
                System.out.println(e);
                return false;
            }
        } catch (SQLException e) {
            throw new IllegalStateException("Cannot connect the database!", e);
        }
    }

    private int getLastEmployeeID() {
        try (Connection connection = DriverManager.getConnection(getURL(), getUser(), getPassword())) {
            String query = "SELECT employee_id FROM employees ORDER BY employee_id DESC LIMIT 1;";
            try (Statement stmt = connection.createStatement()) {
                ResultSet rs = stmt.executeQuery(query);
                if (rs.next()) {
                    int providerID = rs.getInt("employee_id");
                    connection.close();
                    return providerID;
                }
                return -1;
            } catch (Exception e) {
                System.out.println(e);
                return -1;
            }
        } catch (SQLException e) {
            throw new IllegalStateException("Cannot connect the database!", e);
        }
    }

    public ArrayList<employee> getAllCurrentEmployees() {
        ArrayList<employee> tempList = new ArrayList<employee>();
        try (Connection connection = DriverManager.getConnection(getURL(), getUser(), getPassword())) {
            String query = "SELECT * FROM employees WHERE active = true";
            try (Statement stmt = connection.createStatement()) {
                ResultSet rs = stmt.executeQuery(query);
                while (rs.next()) {
                    int ID = rs.getInt("employee_id");
                    String name = rs.getString("name");
                    float salary = rs.getFloat("salary");
                    String hoursWeek = rs.getString("hours_a_week");
                    int roleID = rs.getInt("role_id");
                    tempList.add(new employee(ID, name, salary, hoursWeek, roleID, true));
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

    public String getNameOfRoleID(int roleID) {
        try (Connection connection = DriverManager.getConnection(getURL(), getUser(), getPassword())) {
            String query = "SELECT role_name FROM roles WHERE role_id = " + roleID;
            try (Statement stmt = connection.createStatement()) {
                ResultSet rs = stmt.executeQuery(query);
                if (rs.next()) {
                    String roleName = rs.getString("role_name");
                    connection.close();
                    return roleName;
                }
                return null;
            } catch (Exception e) {
                System.out.println(e);
                return null;
            }
        } catch (SQLException e) {
            throw new IllegalStateException("Cannot connect the database!", e);
        }
    }

    public ArrayList<currentShiftEmployee> getCurrentlyWorkingEmployees() {
        ArrayList<currentShiftEmployee> tempList = new ArrayList<>();
        try (Connection connection = DriverManager.getConnection(getURL(), getUser(), getPassword())) {
            String date = LocalDate.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd"));
            String time = LocalTime.now().format(DateTimeFormatter.ofPattern("HH:mm"));
            String query = "SELECT name, role_name, salary, hours_a_week, start_shift, end_shift FROM employees_schedule NATURAL JOIN employees LEFT JOIN roles ON roles.role_id = employees.role_id WHERE shift_date = '"
                    + date + "' AND start_shift < '" + time + "' AND end_shift > '" + time + "'";
            try (Statement stmt = connection.createStatement()) {
                ResultSet rs = stmt.executeQuery(query);
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
            } catch (Exception e) {
                return null;
            }
        } catch (SQLException e) {
            return null;
        }
    }

    public employee getEmployee(int employeeID) {
        try (Connection connection = DriverManager.getConnection(getURL(), getUser(), getPassword())) {
            String query = "SELECT * FROM employees WHERE active = true AND employee_id = " + employeeID;
            try (Statement stmt = connection.createStatement()) {
                ResultSet rs = stmt.executeQuery(query);
                if (rs.next()) {
                    int ID = rs.getInt("employee_id");
                    String name = rs.getString("name");
                    float salary = rs.getFloat("salary");
                    String hoursWeek = rs.getString("hours_a_week");
                    int roleID = rs.getInt("role_id");
                    connection.close();
                    return new employee(ID, name, salary, hoursWeek, roleID, true);
                }
                return null;
            } catch (Exception e) {
                System.out.println(e);
                return null;
            }
        } catch (SQLException e) {
            throw new IllegalStateException("Cannot connect the database!", e);
        }
    }

    public void updateEmployeeName(int employeeID, String name) {
        try (Connection connection = DriverManager.getConnection(getURL(), getUser(), getPassword())) {
            String query = "UPDATE employees SET name = '" + name + "' WHERE employee_id = " + employeeID;
            try (Statement stmt = connection.createStatement()) {
                stmt.executeUpdate(query);
                connection.close();
            } catch (Exception e) {
                System.out.println(e);
            }
        } catch (SQLException e) {
            throw new IllegalStateException("Cannot connect the database!", e);
        }
    }

    public void updateEmployeeSalary(int employeeID, float salary) {
        try (Connection connection = DriverManager.getConnection(getURL(), getUser(), getPassword())) {
            String query = "UPDATE employees SET salary = " + salary + " WHERE employee_id = " + employeeID;
            try (Statement stmt = connection.createStatement()) {
                stmt.executeUpdate(query);
                connection.close();
            } catch (Exception e) {
                System.out.println(e);
            }
        } catch (SQLException e) {
            throw new IllegalStateException("Cannot connect the database!", e);
        }
    }

    public void updateEmployeeHoursWeek(int employeeID, String hoursWeek) {
        try (Connection connection = DriverManager.getConnection(getURL(), getUser(), getPassword())) {
            String query = "UPDATE employees SET hours_a_week = '" + hoursWeek + "' WHERE employee_id = " + employeeID;
            try (Statement stmt = connection.createStatement()) {
                stmt.executeUpdate(query);
                connection.close();
            } catch (Exception e) {
                System.out.println(e);
            }
        } catch (SQLException e) {
            throw new IllegalStateException("Cannot connect the database!", e);
        }
    }

    public void updateEmployeeRole(int employeeID, int roleID) {
        try (Connection connection = DriverManager.getConnection(getURL(), getUser(), getPassword())) {
            String query = "UPDATE employees SET role_id = " + roleID + " WHERE employee_id = " + employeeID;
            try (Statement stmt = connection.createStatement()) {
                stmt.executeUpdate(query);
                connection.close();
            } catch (Exception e) {
                System.out.println(e);
            }
        } catch (SQLException e) {
            throw new IllegalStateException("Cannot connect the database!", e);
        }
    }

    public void updateShiftDate(shift theShift, String newDate) {
        try (Connection connection = DriverManager.getConnection(getURL(), getUser(), getPassword())) {
            String query = "UPDATE employees_schedule SET shift_date = '" + newDate + "' WHERE employee_id = "
                    + theShift.getEmployeeId() + " AND start_shift = '" + theShift.getStartTime()
                    + "' AND end_shift = '" + theShift.getEndTime() + "';";
            try (Statement stmt = connection.createStatement()) {
                stmt.executeUpdate(query);
                theShift.setDate(newDate);
                connection.close();
            } catch (Exception e) {
                System.out.println(e);
            }
        } catch (SQLException e) {
            throw new IllegalStateException("Cannot connect the database!", e);
        }
    }

    public void setEmployeeUnactive(int employeeID) {
        String dateToday = LocalDate.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd"));
        try (Connection connection = DriverManager.getConnection(getURL(), getUser(), getPassword())) {
            String query = "UPDATE employees SET active = false, inactive_since = '" + dateToday
                    + "' WHERE employee_id = " + employeeID;
            try (Statement stmt = connection.createStatement()) {
                stmt.executeUpdate(query);
                connection.close();
            } catch (Exception e) {
                System.out.println(e);
            }
        } catch (SQLException e) {
            throw new IllegalStateException("Cannot connect the database!", e);
        }
    }

    public boolean isRoleAssigned(int roleID) {
        try (Connection connection = DriverManager.getConnection(getURL(), getUser(), getPassword())) {
            String query = "SELECT * FROM employees WHERE active = true AND role_id = " + roleID;
            try (Statement stmt = connection.createStatement()) {
                ResultSet rs = stmt.executeQuery(query);
                if (rs.next()) {
                    connection.close();
                    return true;
                }
                return false;
            } catch (Exception e) {
                System.out.println(e);
                return false;
            }
        } catch (SQLException e) {
            throw new IllegalStateException("Cannot connect the database!", e);
        }

    }

    public void setRolesUnactive(int roleID) {
        try (Connection connection = DriverManager.getConnection(getURL(), getUser(), getPassword())) {
            String query = "UPDATE roles SET unactive = true WHERE role_id = " + roleID;
            try (Statement stmt = connection.createStatement()) {
                stmt.executeUpdate(query);
                connection.close();
            } catch (Exception e) {
                System.out.println(e);
            }
        } catch (SQLException e) {
            throw new IllegalStateException("Cannot connect the database!", e);
        }
    }

    public String getNameOfEmployee(int employeeID) {
        try (Connection connection = DriverManager.getConnection(getURL(), getUser(), getPassword())) {
            String query = "SELECT name FROM employees WHERE employee_id = " + employeeID;
            try (Statement stmt = connection.createStatement()) {
                ResultSet rs = stmt.executeQuery(query);
                if (rs.next()) {
                    String employeeName = rs.getString("name");
                    connection.close();
                    return employeeName;
                }
                return null;
            } catch (Exception e) {
                System.out.println(e);
                return null;
            }
        } catch (SQLException e) {
            throw new IllegalStateException("Cannot connect the database!", e);
        }
    }

    public shift getShift(int employeeID, String date, String startShift, String endShift) {
        try (Connection connection = DriverManager.getConnection(getURL(), getUser(), getPassword())) {
            String query = "SELECT * FROM employees_schedule WHERE employee_id = " + employeeID
                    + " AND shift_date = '" + date + "' AND start_shift = '" + startShift + "' AND end_shift = '"
                    + endShift + "'";
            try (Statement stmt = connection.createStatement()) {
                ResultSet rs = stmt.executeQuery(query);
                if (rs.next()) {
                    int ID = rs.getInt("employee_id");
                    String shiftDate = rs.getString("shift_date");
                    String startTime = rs.getString("start_shift");
                    String endTime = rs.getString("end_shift");
                    String realStartTime = rs.getString("realtime_in");
                    String realEndTime = rs.getString("realtime_out");
                    boolean undertime = rs.getBoolean("undertime");
                    return new shift(ID, shiftDate, startTime, endTime, realStartTime, realEndTime, undertime);
                }
                return null;
            } catch (Exception e) {
                System.out.println(e);
                return null;
            }
        } catch (SQLException e) {
            throw new IllegalStateException("Cannot connect the database!", e);
        }
    }

    public String getRoleOfEmployee(int employeeID) {
        try (Connection connection = DriverManager.getConnection(getURL(), getUser(), getPassword())) {
            String query = "SELECT role_name FROM roles NATURAL JOIN employees WHERE employee_id = " + employeeID;
            try (Statement stmt = connection.createStatement()) {
                ResultSet rs = stmt.executeQuery(query);
                if (rs.next()) {
                    String roleName = rs.getString("role_name");
                    connection.close();
                    return roleName;
                }
                return null;
            } catch (Exception e) {
                System.out.println(e);
                return null;
            }
        } catch (SQLException e) {
            throw new IllegalStateException("Cannot connect the database!", e);
        }
    }

    public void updateEntryTime(shift theShift, String newEntryTime) {
        try (Connection connection = DriverManager.getConnection(getURL(), getUser(), getPassword())) {
            String query = "UPDATE employees_schedule SET start_shift = '" + newEntryTime + "' WHERE employee_id = "
                    + theShift.getEmployeeId() + " AND shift_date = '" + theShift.getDate() + "';";
            try (Statement stmt = connection.createStatement()) {
                stmt.executeUpdate(query);
                theShift.setStartTime(newEntryTime);
                connection.close();
            } catch (Exception e) {
                System.out.println(e);
            }
        } catch (SQLException e) {
            throw new IllegalStateException("Cannot connect the database!", e);
        }
    }

    public void updateEndTime(shift theShift, String newEndTime) {
        try (Connection connection = DriverManager.getConnection(getURL(), getUser(), getPassword())) {
            String query = "UPDATE employees_schedule SET end_shift = '" + newEndTime + "' WHERE employee_id = "
                    + theShift.getEmployeeId() + " AND shift_date = '" + theShift.getDate() + "';";
            try (Statement stmt = connection.createStatement()) {
                stmt.executeUpdate(query);
                theShift.setEndTime(newEndTime);
                connection.close();
            } catch (Exception e) {
                System.out.println(e);
            }
        } catch (SQLException e) {
            throw new IllegalStateException("Cannot connect the database!", e);
        }
    }

    public void deleteShift(shift theShift) {
        try (Connection connection = DriverManager.getConnection(getURL(), getUser(), getPassword())) {
            String query = "DELETE FROM employees_schedule WHERE employee_id = " + theShift.getEmployeeId()
                    + " AND shift_date = '" + theShift.getDate() + "' AND start_shift = '" + theShift.getStartTime()
                    + "' AND end_shift = '" + theShift.getEndTime() + "';";
            try (Statement stmt = connection.createStatement()) {
                stmt.executeUpdate(query);
                connection.close();
            } catch (Exception e) {
                System.out.println(e);
            }
        } catch (SQLException e) {
            throw new IllegalStateException("Cannot connect the database!", e);
        }
    }
}
