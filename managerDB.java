import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.ResultSet;

public class managerDB {

    private final static String url = "jdbc:mysql://localhost:3306/beatneat";

    public Connection getConnection() {
        try (Connection connection = DriverManager.getConnection(url, "juandi", "Juandi")) {
            // this.connection = connection;
            System.out.println("Database connected!");
            return connection;
        } catch (SQLException e) {
            throw new IllegalStateException("Cannot connect the database!", e);
        }
    }

    public void query1() {
        try (Connection connection = DriverManager.getConnection(url, "juandi", "Juandi")) {
            String query = "SELECT * FROM providers";
            try (Statement stmt = getConnection().createStatement()) {
                ResultSet rs = stmt.executeQuery(query);
                while (rs.next()) {
                    int providerID = rs.getInt("provider_id");
                    String name = rs.getString("name");
                    String email = rs.getString("email");
                    System.out.println("Prov id: " + providerID + ", name : " + name +
                            ", and email: " + email + ".");
                }
            } catch (Exception e) {
                System.out.println(e);
            }
            return;
        } catch (SQLException e) {
            throw new IllegalStateException("Cannot connect the database!", e);
        }
    }

}
