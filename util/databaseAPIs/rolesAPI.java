package util.databaseAPIs;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import componentsFood.role;

public class rolesAPI extends abstractManagerDB {

    // GET from database.
    public static role getRole(int roleID) {
        try (Connection connection = DriverManager.getConnection(getURL(), getUser(), getPassword())) {
            String query = "SELECT * FROM roles WHERE role_id = ?;";
            ppdStatement = connection.prepareStatement(query);
            ppdStatement.setInt(1, roleID);
            try {
                ResultSet rs = ppdStatement.executeQuery();
                if (rs.next()) {
                    int ID = rs.getInt("role_id");
                    String name = rs.getString("role_name");
                    return new role(ID, name);
                }
                return null;
            } catch (Exception SQLTimeoutException) {
                return null;
            }
        } catch (SQLException e) {
            throw new IllegalStateException("Cannot connect the database!", e);
        }
    }

    public static ArrayList<role> getAllRoles() {
        ArrayList<role> tempList = new ArrayList<role>();
        try (Connection connection = DriverManager.getConnection(getURL(), getUser(), getPassword())) {
            String query = "SELECT * FROM roles WHERE unactive IS NULL;";
            ppdStatement = connection.prepareStatement(query);
            try {
                ResultSet rs = ppdStatement.executeQuery();
                while (rs.next()) {
                    int ID = rs.getInt("role_id");
                    String name = rs.getString("role_name");
                    tempList.add(new role(ID, name));
                }
                return tempList;
            } catch (Exception SQLTimeoutException) {
                return null;
            }
        } catch (SQLException e) {
            throw new IllegalStateException("Cannot connect the database!", e);
        }
    }

    public static String getNameOfRole(int roleID) {
        try (Connection connection = DriverManager.getConnection(getURL(), getUser(), getPassword())) {
            String query = "SELECT role_name FROM roles WHERE role_id =  ?;";
            ppdStatement = connection.prepareStatement(query);
            ppdStatement.setInt(1, roleID);
            try {
                ResultSet rs = ppdStatement.executeQuery();
                if (rs.next())
                    return rs.getString("role_name");
                return null;
            } catch (Exception SQLTimeoutException) {
                return null;
            }
        } catch (SQLException e) {
            throw new IllegalStateException("Cannot connect the database!", e);
        }
    }

    private static int getLastRoleID() {
        try (Connection connection = DriverManager.getConnection(getURL(), getUser(), getPassword())) {
            String query = "SELECT role_id FROM roles ORDER BY role_id DESC LIMIT 1;";
            ppdStatement = connection.prepareStatement(query);
            try {
                ResultSet rs = ppdStatement.executeQuery();
                if (rs.next())
                    return rs.getInt("role_id");
                return -1;
            } catch (Exception SQLTimeoutException) {
                return -1;
            }
        } catch (SQLException e) {
            throw new IllegalStateException("Cannot connect the database!", e);
        }
    }

    // ADD to database.
    public static boolean addRole(String name) {
        try (Connection connection = DriverManager.getConnection(getURL(), getUser(), getPassword())) {
            String query = "INSERT INTO roles VALUES (?, ?, NULL);";
            ppdStatement = connection.prepareStatement(query);
            ppdStatement.setInt(1, getLastRoleID() + 1);
            ppdStatement.setString(2, name);
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
    public static boolean updateName(role theRole, String newName) {
        try (Connection connection = DriverManager.getConnection(getURL(), getUser(), getPassword())) {
            String query = "UPDATE roles SET role_name = ? WHERE role_id = ?";
            ppdStatement = connection.prepareStatement(query);
            ppdStatement.setString(1, newName);
            ppdStatement.setInt(2, theRole.getId());
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

    public static void deleteRole(role theRole) {
        try (Connection connection = DriverManager.getConnection(getURL(), getUser(), getPassword())) {
            String query = "UPDATE roles SET unactive = true WHERE role_id = ?";
            ppdStatement = connection.prepareStatement(query);
            ppdStatement.setInt(1, theRole.getId());
            try {
                ppdStatement.executeUpdate();
            } catch (Exception SQLTimeoutException) {
            }
        } catch (SQLException e) {
            throw new IllegalStateException("Cannot connect the database!", e);
        }
    }

    // CHECK in database.
    public static boolean isRoleAssigned(int roleID) {
        try (Connection connection = DriverManager.getConnection(getURL(), getUser(), getPassword())) {
            String query = "SELECT * FROM employees WHERE active = true AND role_id = ?";
            ppdStatement = connection.prepareStatement(query);
            ppdStatement.setInt(1, roleID);
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

    public static boolean isNameTaken(String name) {
        try (Connection connection = DriverManager.getConnection(getURL(), getUser(), getPassword())) {
            String query = "SELECT * FROM roles WHERE role_name = ? AND unactive IS NULL";
            ppdStatement = connection.prepareStatement(query);
            ppdStatement.setString(1, name);
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