package util;

public class abstractManagerDB {
    private final String url = "jdbc:mysql://localhost:3306/beatneat";
    private final String user = "juandi"; // Change to your local user
    private final String password = "Juandi"; // Change to your local password

    public String getURL() {
        return url;
    }

    public String getUser() {
        return user;
    }

    public String getPassword() {
        return password;
    }
}