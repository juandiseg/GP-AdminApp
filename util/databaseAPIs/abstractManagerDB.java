package util.databaseAPIs;

import java.sql.PreparedStatement;

public class abstractManagerDB {
    private final static String url = "jdbc:mysql://localhost:3306/beatneat";
    private final static String user = "juandi"; // Change to your local user
    private final static String password = "Juandi"; // Change to your local password

    protected static PreparedStatement ppdStatement;

    protected static String getURL() {
        return url;
    }

    protected static String getUser() {
        return user;
    }

    protected static String getPassword() {
        return password;
    }
}