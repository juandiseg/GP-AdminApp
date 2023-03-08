package util.databaseAPIs;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import componentsFood.shift;

public class shiftsAPI extends abstractManagerDB {

    // GET "product" objects from database.
    public static shift getShift(int employeeID, String date, String startShift, String endShift) {
        date = dateInverter.invert(date);
        try (Connection connection = DriverManager.getConnection(getURL(), getUser(), getPassword())) {
            String query = "SELECT * FROM employees_schedule WHERE employee_id = ? AND shift_date = ? AND start_shift = ? AND end_shift = ?";
            ppdStatement = connection.prepareStatement(query);
            ppdStatement.setInt(1, employeeID);
            ppdStatement.setString(2, date);
            ppdStatement.setString(3, startShift);
            ppdStatement.setString(4, endShift);
            try {
                ResultSet rs = ppdStatement.executeQuery();
                if (rs.next()) {
                    int ID = rs.getInt("employee_id");
                    String shiftDate = rs.getString("shift_date");
                    String startTime = rs.getString("start_shift");
                    String endTime = rs.getString("end_shift");
                    String realStartTime = rs.getString("realtime_in");
                    String realEndTime = rs.getString("realtime_out");
                    boolean undertime = rs.getBoolean("undertime");
                    return new shift(ID, dateInverter.invert(shiftDate), startTime, endTime, realStartTime, realEndTime,
                            undertime);
                }
                return null;
            } catch (Exception SQLTimeoutException) {
                return null;
            }
        } catch (SQLException e) {
            throw new IllegalStateException("Cannot connect the database!", e);
        }

    }

    public static ArrayList<shift> getShiftsWithinDateSorted(String from, String to, boolean shift_date) {
        ArrayList<shift> tempList = new ArrayList<shift>();
        from = dateInverter.invert(from);
        to = dateInverter.invert(to);
        String sortingColumn = "employee_id";
        if (!shift_date)
            sortingColumn = "shift_date";
        try (Connection connection = DriverManager.getConnection(getURL(), getUser(), getPassword())) {
            String query = "SELECT * FROM employees_schedule NATURAL JOIN employees WHERE shift_date BETWEEN ? AND ? ORDER BY "
                    + sortingColumn + ", role_id;";
            ppdStatement = connection.prepareStatement(query);
            ppdStatement.setString(1, from);
            ppdStatement.setString(2, to);
            try {
                ResultSet rs = ppdStatement.executeQuery();
                while (rs.next()) {
                    int employeeID = rs.getInt("employee_id");
                    String date = rs.getString("shift_date");
                    String startTime = rs.getString("start_shift");
                    String endTime = rs.getString("end_shift");
                    String realStartTime = rs.getString("realtime_in");
                    String realEndTime = rs.getString("realtime_out");
                    boolean undertime = rs.getBoolean("undertime");
                    tempList.add(
                            new shift(employeeID, dateInverter.invert(date), startTime, endTime, realStartTime,
                                    realEndTime, undertime));
                }
                return tempList;
            } catch (Exception SQLTimeoutException) {
                return null;
            }
        } catch (SQLException e) {
            throw new IllegalStateException("Cannot connect the database!", e);
        }
    }

    public static ArrayList<shift> getAllFutureShiftsUntil(String to) {
        ArrayList<shift> tempList = new ArrayList<shift>();
        to = dateInverter.invert(to);
        try (Connection connection = DriverManager.getConnection(getURL(), getUser(), getPassword())) {
            String query = "SELECT * FROM employees_schedule NATURAL JOIN employees WHERE shift_date BETWEEN CURDATE() AND ? ORDER BY role_id;";
            ppdStatement = connection.prepareStatement(query);
            ppdStatement.setString(1, to);
            try {
                ResultSet rs = ppdStatement.executeQuery();
                while (rs.next()) {
                    int employeeID = rs.getInt("employee_id");
                    String date = rs.getString("shift_date");
                    String startTime = rs.getString("start_shift");
                    String endTime = rs.getString("end_shift");
                    String realStartTime = rs.getString("realtime_in");
                    String realEndTime = rs.getString("realtime_out");
                    boolean undertime = rs.getBoolean("undertime");
                    tempList.add(
                            new shift(employeeID, dateInverter.invert(date), startTime, endTime, realStartTime,
                                    realEndTime, undertime));
                }
                return tempList;
            } catch (Exception SQLTimeoutException) {
                return null;
            }
        } catch (SQLException e) {
            throw new IllegalStateException("Cannot connect the database!", e);
        }
    }

    public static ArrayList<String> getNamesOfEmbededShifts(ArrayList<Integer> employeeIDs, String shiftDate,
            String startShift, String endShift) {
        ArrayList<String> employeeNames = new ArrayList<String>();
        shiftDate = dateInverter.invert(shiftDate);
        String csEmployeeIDs = "(";
        for (Integer temp : employeeIDs) {
            csEmployeeIDs = csEmployeeIDs.concat(Integer.toString(temp)).concat(",");
        }
        csEmployeeIDs = csEmployeeIDs.substring(0, csEmployeeIDs.length() - 1);
        csEmployeeIDs = csEmployeeIDs.concat(")");
        try (Connection connection = DriverManager.getConnection(getURL(), getUser(), getPassword())) {
            String query = "SELECT name FROM employees_schedule NATURAL JOIN employees WHERE shift_date = ? AND ((? BETWEEN start_shift AND end_shift OR ? BETWEEN start_shift AND end_shift) OR (? < start_shift AND ? > end_shift)) AND employee_id IN "
                    + csEmployeeIDs;
            ppdStatement = connection.prepareStatement(query);
            ppdStatement.setString(1, shiftDate);
            ppdStatement.setString(2, startShift);
            ppdStatement.setString(3, endShift);
            ppdStatement.setString(4, startShift);
            ppdStatement.setString(5, endShift);
            try {
                ResultSet rs = ppdStatement.executeQuery();
                while (rs.next())
                    employeeNames.add(rs.getString("name"));
                return employeeNames;
            } catch (Exception SQLTimeoutException) {
                return employeeNames;
            }
        } catch (SQLException e) {
            throw new IllegalStateException("Cannot connect the database!", e);
        }
    }

    public static ArrayList<String> getNameOfEmbededShiftsEdit(ArrayList<shift> theShifts, String newShiftDate,
            String newStartShift, String newEndShift) {
        ArrayList<String> employeeNames = new ArrayList<String>();
        newShiftDate = dateInverter.invert(newShiftDate);
        try (Connection connection = DriverManager.getConnection(getURL(), getUser(), getPassword())) {
            for (shift tempShift : theShifts) {
                String query = "SELECT name FROM employees_schedule NATURAL JOIN employees WHERE (employee_id, shift_date, start_shift, end_shift) NOT IN (SELECT employee_id, shift_date, start_shift, end_shift from employees_schedule WHERE employee_id = ? AND shift_date = ? AND start_shift = ? AND end_shift = ?) AND shift_date = ? AND ((? BETWEEN start_shift AND end_shift OR ? BETWEEN start_shift AND end_shift) OR (? < start_shift AND ? > end_shift)) AND employee_id = ?;";
                ppdStatement = connection.prepareStatement(query);
                ppdStatement.setInt(1, tempShift.getEmployeeId());
                ppdStatement.setString(2, dateInverter.invert(tempShift.getDate()));
                ppdStatement.setString(3, tempShift.getStartTime());
                ppdStatement.setString(4, tempShift.getEndTime());
                ppdStatement.setString(5, newShiftDate);
                ppdStatement.setString(6, newStartShift);
                ppdStatement.setString(7, newEndShift);
                ppdStatement.setString(8, newStartShift);
                ppdStatement.setString(9, newEndShift);
                ppdStatement.setInt(10, tempShift.getEmployeeId());
                try {
                    ResultSet rs = ppdStatement.executeQuery();
                    if (rs.next())
                        employeeNames.add(rs.getString("name"));
                } catch (Exception SQLTimeoutException) {
                }
            }
            return employeeNames;
        } catch (SQLException e) {
            throw new IllegalStateException("Cannot connect the database!", e);
        }
    }

    public static ArrayList<shift> getLateEntries() {
        ArrayList<shift> tempList = new ArrayList<shift>();
        try (Connection connection = DriverManager.getConnection(getURL(), getUser(), getPassword())) {
            String query = "SELECT * FROM employees_schedule NATURAL JOIN employees WHERE undertime = true ORDER BY shift_date, role_id;";
            ppdStatement = connection.prepareStatement(query);
            try {
                ResultSet rs = ppdStatement.executeQuery();
                while (rs.next()) {
                    int employeeID = rs.getInt("employee_id");
                    String date = rs.getString("shift_date");
                    String startTime = rs.getString("start_shift");
                    String endTime = rs.getString("end_shift");
                    String realStartTime = rs.getString("realtime_in");
                    String realEndTime = rs.getString("realtime_out");
                    boolean undertime = rs.getBoolean("undertime");
                    tempList.add(new shift(employeeID, dateInverter.invert(date), startTime, endTime, realStartTime,
                            realEndTime, undertime));
                }
                return tempList;
            } catch (Exception SQLTimeoutException) {
                return tempList;
            }
        } catch (SQLException e) {
            throw new IllegalStateException("Cannot connect the database!", e);
        }
    }

    // ADD to database.
    public static boolean addShift(String employeeID, String shiftDate, String startShift, String endShift) {
        try (Connection connection = DriverManager.getConnection(getURL(), getUser(), getPassword())) {
            String query = "INSERT INTO employees_schedule VALUES (?, ?, ?, ?, NULL, NULL, NULL);";
            ppdStatement = connection.prepareStatement(query);
            ppdStatement.setString(1, employeeID);
            ppdStatement.setString(2, shiftDate);
            ppdStatement.setString(3, startShift);
            ppdStatement.setString(4, endShift);
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

    public static boolean addShifts(ArrayList<Integer> employeeID, String shiftDate, String startShift,
            String endShift) {
        shiftDate = dateInverter.invert(shiftDate);
        try (Connection connection = DriverManager.getConnection(getURL(), getUser(), getPassword())) {
            String query = "INSERT INTO employees_schedule VALUES (?, ?, ?, ?, NULL, NULL, NULL);";
            ppdStatement = connection.prepareStatement(query);
            for (Integer tempID : employeeID) {
                ppdStatement.setInt(1, tempID);
                ppdStatement.setString(2, shiftDate);
                ppdStatement.setString(3, startShift);
                ppdStatement.setString(4, endShift);
                try {
                    ppdStatement.executeUpdate();
                } catch (Exception SQLTimeoutException) {
                    return false;
                }
            }
            return true;
        } catch (SQLException e) {
            throw new IllegalStateException("Cannot connect the database!", e);
        }
    }

    // UPDATE in database.
    public static boolean updateShiftDate(shift theShift, String newDate) {
        try (Connection connection = DriverManager.getConnection(getURL(), getUser(), getPassword())) {
            String query = "UPDATE employees_schedule SET shift_date = ? WHERE employee_id = ? AND shift_date = ? AND start_shift = ? AND end_shift = ?;";
            ppdStatement = connection.prepareStatement(query);
            ppdStatement.setString(1, dateInverter.invert(newDate));
            ppdStatement.setInt(2, theShift.getEmployeeId());
            ppdStatement.setString(3, dateInverter.invert(theShift.getDate()));
            ppdStatement.setString(4, theShift.getStartTime());
            ppdStatement.setString(5, theShift.getEndTime());
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

    public static boolean updateEntryTime(shift theShift, String newEntryTime) {
        try (Connection connection = DriverManager.getConnection(getURL(), getUser(), getPassword())) {
            String query = "UPDATE employees_schedule SET start_shift = ? WHERE employee_id = ? AND shift_date = ? AND start_shift = ? AND end_shift = ?;";
            ppdStatement = connection.prepareStatement(query);
            ppdStatement.setString(1, newEntryTime);
            ppdStatement.setInt(2, theShift.getEmployeeId());
            ppdStatement.setString(3, dateInverter.invert(theShift.getDate()));
            ppdStatement.setString(4, theShift.getStartTime());
            ppdStatement.setString(5, theShift.getEndTime());
            try {
                ppdStatement.executeUpdate();
                theShift.setStartTime(dateInverter.invert(newEntryTime));
                return true;
            } catch (Exception SQLTimeoutException) {
                return false;
            }
        } catch (SQLException e) {
            throw new IllegalStateException("Cannot connect the database!", e);
        }
    }

    public static boolean updateEndTime(shift theShift, String newEndTime) {
        try (Connection connection = DriverManager.getConnection(getURL(), getUser(), getPassword())) {
            String query = "UPDATE employees_schedule SET end_shift = ? WHERE employee_id = ? AND shift_date = ? AND start_shift = ? AND end_shift = ?;";
            ppdStatement = connection.prepareStatement(query);
            ppdStatement.setString(1, newEndTime);
            ppdStatement.setInt(2, theShift.getEmployeeId());
            ppdStatement.setString(3, dateInverter.invert(theShift.getDate()));
            ppdStatement.setString(4, theShift.getStartTime());
            ppdStatement.setString(5, theShift.getEndTime());
            try {
                ppdStatement.executeUpdate();
                theShift.setEndTime(dateInverter.invert(newEndTime));
                return true;
            } catch (Exception SQLTimeoutException) {
                return false;
            }
        } catch (SQLException e) {
            throw new IllegalStateException("Cannot connect the database!", e);
        }
    }

    public static boolean deleteShift(shift theShift) {
        try (Connection connection = DriverManager.getConnection(getURL(), getUser(), getPassword())) {
            String query = "DELETE FROM employees_schedule WHERE employee_id = ? AND shift_date = ? AND start_shift = ? AND end_shift = ?;";
            ppdStatement = connection.prepareStatement(query);
            ppdStatement.setInt(1, theShift.getEmployeeId());
            ppdStatement.setString(2, dateInverter.invert(theShift.getDate()));
            ppdStatement.setString(3, theShift.getStartTime());
            ppdStatement.setString(4, theShift.getEndTime());
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
}