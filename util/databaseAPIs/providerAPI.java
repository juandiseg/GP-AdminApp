package util.databaseAPIs;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import componentsFood.provider;

public class providerAPI extends abstractManagerDB {

    // GET from database.
    public static provider getProvider(int ID) {
        try (Connection connection = DriverManager.getConnection(getURL(), getUser(), getPassword())) {
            String query = "SELECT * FROM providers WHERE provider_id = ?;";
            ppdStatement = connection.prepareStatement(query);
            ppdStatement.setInt(1, ID);
            try {
                ResultSet rs = ppdStatement.executeQuery();
                if (rs.next()) {
                    int providerID = rs.getInt("provider_id");
                    String name = rs.getString("name");
                    String email = rs.getString("email");
                    return new provider(providerID, name, email);
                }
                return null;
            } catch (Exception SQLTimeoutException) {
                return null;
            }
        } catch (SQLException e) {
            throw new IllegalStateException("Cannot connect the database!", e);
        }
    }

    public static ArrayList<provider> getAllCurrentProviders() {
        ArrayList<provider> tempList = new ArrayList<provider>();
        try (Connection connection = DriverManager.getConnection(getURL(), getUser(), getPassword())) {
            String query = "SELECT * FROM providers WHERE active = TRUE;";
            ppdStatement = connection.prepareStatement(query);
            try {
                ResultSet rs = ppdStatement.executeQuery();
                while (rs.next()) {
                    int providerID = rs.getInt("provider_id");
                    String name = rs.getString("name");
                    String email = rs.getString("email");
                    tempList.add(new provider(providerID, name, email));
                }
                return tempList;
            } catch (Exception SQLTimeoutException) {
                return null;
            }
        } catch (SQLException e) {
            throw new IllegalStateException("Cannot connect the database!", e);
        }

    }

    private static int getLastProvID() {
        try (Connection connection = DriverManager.getConnection(getURL(), getUser(), getPassword())) {
            String query = "SELECT provider_id FROM providers ORDER BY provider_id DESC LIMIT 1;";
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

    // ADD to database.
    public static boolean addProvider(String name, String email) {
        try (Connection connection = DriverManager.getConnection(getURL(), getUser(), getPassword())) {
            int provID = getLastProvID() + 1;
            String query = "INSERT INTO providers VALUES (" + provID + ", '" + name + "', '" + email + "', true);";
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

    // UPDATE in database.
    public static boolean updateName(provider theProvider, String name) {
        try (Connection connection = DriverManager.getConnection(getURL(), getUser(), getPassword())) {
            String query = "UPDATE providers SET name = ? WHERE provider_id = ?;";
            ppdStatement = connection.prepareStatement(query);
            ppdStatement.setString(1, name);
            ppdStatement.setInt(2, theProvider.getId());
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

    public static boolean updateEmail(provider theProvider, String email) {
        try (Connection connection = DriverManager.getConnection(getURL(), getUser(), getPassword())) {
            String query = "UPDATE providers SET email = ? WHERE provider_id = ?;";
            ppdStatement = connection.prepareStatement(query);
            ppdStatement.setString(1, email);
            ppdStatement.setInt(2, theProvider.getId());
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

    // REMOVE from database.
    public static boolean deleteProvider(provider theProvider) {
        try (Connection connection = DriverManager.getConnection(getURL(), getUser(), getPassword())) {
            String query = "UPDATE providers SET active = false WHERE provider_id =  ?";
            ppdStatement = connection.prepareStatement(query);
            ppdStatement.setInt(1, theProvider.getId());
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
    public static boolean isProviderAssigned(int ID) {
        try (Connection connection = DriverManager.getConnection(getURL(), getUser(), getPassword())) {
            String query = "SELECT * FROM ingredients JOIN providers ON ingredients.provider_id = providers.provider_id WHERE ingredients.provider_id = ? AND ingredients.active = TRUE";
            ppdStatement = connection.prepareStatement(query);
            ppdStatement.setInt(1, ID);
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
            String query = "SELECT * FROM providers WHERE name = ? AND active = TRUE";
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