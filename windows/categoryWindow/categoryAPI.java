package windows.categoryWindow;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import componentsFood.category;
import util.abstractManagerDB;

public class categoryAPI extends abstractManagerDB {

    // GET "category" objects from database.
    public ArrayList<category> getAllCategories() {
        ArrayList<category> tempList = new ArrayList<category>();
        try (Connection connection = DriverManager.getConnection(getURL(), getUser(), getPassword())) {
            String query = "SELECT * FROM categories";
            try (Statement stmt = connection.createStatement()) {
                ResultSet rs = stmt.executeQuery(query);
                while (rs.next()) {
                    int categoryID = rs.getInt("category_id");
                    String name = rs.getString("category_name");
                    Boolean isProduct = rs.getBoolean("iscategory_product");
                    tempList.add(new category(categoryID, name, isProduct));
                }
                connection.close();
                return tempList;
            } catch (Exception e) {
                System.out.println(e);
                return null;
            }
        } catch (SQLException e) {
            throw new IllegalStateException("Cannot connect the database!", e);
        }
    }

    public category getCategory(int ID) {
        try (Connection connection = DriverManager.getConnection(getURL(), getUser(), getPassword())) {
            String query = "SELECT * FROM categories WHERE category_id = " + ID;
            try (Statement stmt = connection.createStatement()) {
                ResultSet rs = stmt.executeQuery(query);
                if (rs.next()) {
                    int categoryID = rs.getInt("category_id");
                    String name = rs.getString("category_name");
                    Boolean isProduct = rs.getBoolean("iscategory_product");
                    category temp = new category(categoryID, name, isProduct);
                    connection.close();
                    return temp;
                }
                connection.close();
                return null;
            } catch (Exception e) {
                System.out.println(e);
                return null;
            }
        } catch (SQLException e) {
            throw new IllegalStateException("Cannot connect the database!", e);
        }
    }

    // ADD "provider" to database.
    public boolean addCategory(String name, boolean isProduct) {
        try (Connection connection = DriverManager.getConnection(getURL(), getUser(), getPassword())) {
            int categoryID = getLastCategoryID() + 1;
            String query = "INSERT INTO categories VALUES (" + categoryID + ", " + isProduct + ", '" + name + "');";
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

    private int getLastCategoryID() {
        try (Connection connection = DriverManager.getConnection(getURL(), getUser(), getPassword())) {
            String query = "SELECT category_id FROM categories ORDER BY category_id DESC LIMIT 1;";
            try (Statement stmt = connection.createStatement()) {
                ResultSet rs = stmt.executeQuery(query);
                if (rs.next()) {
                    int providerID = rs.getInt("category_id");
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

    // UPDATE something "product" related in database.
    public boolean editCategory(int ID, String newName) {
        try (Connection connection = DriverManager.getConnection(getURL(), getUser(), getPassword())) {
            String query = "UPDATE categories SET category_name = '" + newName + "' WHERE category_id = " + ID;
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

}
