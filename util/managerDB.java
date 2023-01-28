package util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.sql.ResultSet;
import componentsFood.*;

public class managerDB {

    private final static String url = "jdbc:mysql://localhost:3306/beatneat";
    private final String user = "juandi";
    private final String password = "Juandi";

    public Connection getConnection() {
        try (Connection connection = DriverManager.getConnection(url, user, password)) {
            System.out.println("Database connected!");
            return connection;
        } catch (SQLException e) {
            throw new IllegalStateException("Cannot connect the database!", e);
        }
    }

    public boolean addProvider(String name, String email) {
        try (Connection connection = DriverManager.getConnection(url, user, password)) {
            int provID = getLastProvID() + 1;
            String query = "INSERT INTO providers VALUES (" + provID + ", '" + name + "', '" + email + "');";
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

    private int getLastProvID() {
        try (Connection connection = DriverManager.getConnection(url, "juandi", "Juandi")) {
            String query = "SELECT provider_id FROM beatneat.providers ORDER BY provider_id DESC LIMIT 1;";
            try (Statement stmt = connection.createStatement()) {
                ResultSet rs = stmt.executeQuery(query);
                if (rs.next()) {
                    int providerID = rs.getInt("provider_id");
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

    private int getLastIngredientID() {
        try (Connection connection = DriverManager.getConnection(url, "juandi", "Juandi")) {
            String query = "SELECT ingredient_id FROM beatneat.ingredients ORDER BY ingredient_id DESC LIMIT 1;";
            try (Statement stmt = connection.createStatement()) {
                ResultSet rs = stmt.executeQuery(query);
                if (rs.next()) {
                    int providerID = rs.getInt("ingredient_id");
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

    public boolean addIngredient(int provID, String date, String name, float price, int amount, boolean in_inventory,
            boolean active) {
        try (Connection connection = DriverManager.getConnection(url, user, password)) {
            int ingrID = getLastIngredientID() + 1;
            String query = "INSERT INTO ingredients VALUES (" + ingrID + ", " + provID + ", '" + date + "', '" + name
                    + "', " + price + ", " + amount + ", " + in_inventory + ", " + active + ");";
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

    public boolean editIngredient(int ingID, int provID, String date, String name, double price, int amount,
            boolean in_inventory, boolean active) {
        try (Connection connection = DriverManager.getConnection(url, user, password)) {
            String query = "UPDATE ingredients SET provider_id = " + provID + ", date = '" + date
                    + "', name = '" + name + "', price = " + price + ", amount = " + amount + ", in_inventory = "
                    + in_inventory + ", active = " + active + " WHERE provider_id = " + Integer.toString(ingID);
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

    public ArrayList<provider> getAllProviders() {
        ArrayList<provider> tempList = new ArrayList<provider>();
        try (Connection connection = DriverManager.getConnection(url, user, password)) {
            String query = "SELECT * FROM providers";
            try (Statement stmt = connection.createStatement()) {
                ResultSet rs = stmt.executeQuery(query);
                while (rs.next()) {
                    int providerID = rs.getInt("provider_id");
                    String name = rs.getString("name");
                    String email = rs.getString("email");
                    tempList.add(new provider(providerID, name, email));
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

    public provider getProvider(int ID) {
        try (Connection connection = DriverManager.getConnection(url, user, password)) {
            String query = "SELECT * FROM providers WHERE provider_id = " + Integer.toString(ID);
            try (Statement stmt = connection.createStatement()) {
                ResultSet rs = stmt.executeQuery(query);
                if (rs.next()) {
                    int providerID = rs.getInt("provider_id");
                    String name = rs.getString("name");
                    String email = rs.getString("email");
                    provider temp = new provider(providerID, name, email);
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

    public boolean editProvider(int ID, String name, String email) {
        try (Connection connection = DriverManager.getConnection(url, user, password)) {
            String query = "UPDATE providers SET name = '" + name + "', email = '" + email
                    + "' WHERE provider_id = " + Integer.toString(ID);
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
