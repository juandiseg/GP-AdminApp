package util.databaseAPIs;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;

public class descriptionsAPI extends abstractManagerDB {

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

    public static boolean updateImageMenu(int menuID, byte[] theImage) {
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

    public static byte[] getImageMenu(int menuID) {
        try (Connection connection = DriverManager.getConnection(getURL(), getUser(), getPassword())) {
            String query = "SELECT image FROM menus_data WHERE menu_id = ?;";
            ppdStatement = connection.prepareStatement(query);
            ppdStatement.setInt(1, menuID);
            try {
                ResultSet rs = ppdStatement.executeQuery();
                if (rs.next())
                    return rs.getBytes("image");
                return null;
            } catch (Exception SQLTimeoutException) {
                return null;
            }
        } catch (SQLException e) {
            return null;
        }
    }

    public static byte[] getImageProduct(int productID) {
        try (Connection connection = DriverManager.getConnection(getURL(), getUser(), getPassword())) {
            String query = "SELECT image FROM products_data WHERE product_id = ?;";
            ppdStatement = connection.prepareStatement(query);
            ppdStatement.setInt(1, productID);
            try {
                ResultSet rs = ppdStatement.executeQuery();
                if (rs.next())
                    return rs.getBytes("image");
                return null;
            } catch (Exception SQLTimeoutException) {
                return null;
            }
        } catch (SQLException e) {
            return null;
        }
    }

    public static boolean updateDescriptionProduct(int productID, String newDescription) {
        try (Connection connection = DriverManager.getConnection(getURL(), getUser(), getPassword())) {
            String query = "UPDATE products_data SET description = ? WHERE product_id = ?;";
            ppdStatement = connection.prepareStatement(query);
            ppdStatement.setString(1, newDescription);
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

    public static boolean updateDescriptionMenu(int menuID, String newDescription) {
        try (Connection connection = DriverManager.getConnection(getURL(), getUser(), getPassword())) {
            String query = "UPDATE menus_data SET description = ? WHERE menu_id = ?;";
            ppdStatement = connection.prepareStatement(query);
            ppdStatement.setString(1, newDescription);
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

    public static String getDescriptionProduct(int productID) {
        try (Connection connection = DriverManager.getConnection(getURL(), getUser(), getPassword())) {
            String query = "SELECT description FROM products_data WHERE product_id = ?;";
            ppdStatement = connection.prepareStatement(query);
            ppdStatement.setInt(1, productID);
            try {
                ResultSet rs = ppdStatement.executeQuery();
                if (rs.next())
                    return rs.getString("description");
                return null;
            } catch (Exception SQLTimeoutException) {
                return null;
            }
        } catch (SQLException e) {
            return null;
        }
    }

    public static String getDescriptionMenu(int menuID) {
        try (Connection connection = DriverManager.getConnection(getURL(), getUser(), getPassword())) {
            String query = "SELECT description FROM menus_data WHERE menu_id = ?;";
            ppdStatement = connection.prepareStatement(query);
            ppdStatement.setInt(1, menuID);
            try {
                ResultSet rs = ppdStatement.executeQuery();
                if (rs.next())
                    return rs.getString("description");
                return null;
            } catch (Exception SQLTimeoutException) {
                return null;
            }
        } catch (SQLException e) {
            return null;
        }
    }
}
