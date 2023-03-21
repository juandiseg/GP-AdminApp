package util.databaseAPIs;

import java.sql.PreparedStatement;

/*  
    Every API inherits from this class in order to create a connection.
    It is expected to change the values of url, user and password according
    the instance of your local DB in order for the app to work.
*/
public class abstractManagerDB {
    private final static String url = "jdbc:mysql://localhost:3306/beatneat";
    private final static String user = "root"; // Change to your local user
    private final static String password = ""; // Change to your local password

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