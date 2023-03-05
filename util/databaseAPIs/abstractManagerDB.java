package util.databaseAPIs;

import java.sql.PreparedStatement;

public class abstractManagerDB {
    protected PreparedStatement ppdStatement;
    private final String url = "jdbc:mysql://localhost:3306/beatneat";
    private final String user = "juandi"; // Change to your local user
    private final String password = "Juandi"; // Change to your local password

    protected String getURL() {
        return url;
    }

    protected String getUser() {
        return user;
    }

    protected String getPassword() {
        return password;
    }
}