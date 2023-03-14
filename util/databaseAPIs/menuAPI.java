package util.databaseAPIs;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Stack;

import componentsFood.menu;

public class menuAPI extends abstractManagerDB {

    // GET from database.
    public static menu getMenu(int ID) {
        try (Connection connection = DriverManager.getConnection(getURL(), getUser(), getPassword())) {
            String query = "SELECT * FROM menus WHERE menu_id = ? ORDER BY menu_date DESC LIMIT 1;";
            ppdStatement = connection.prepareStatement(query);
            ppdStatement.setInt(1, ID);
            try {
                ResultSet rs = ppdStatement.executeQuery();
                if (rs.next()) {
                    int catID = rs.getInt("category_id");
                    String date = rs.getString("menu_date");
                    String name = rs.getString("name");
                    float price = rs.getFloat("price");
                    boolean active = rs.getBoolean("active");
                    return new menu(ID, catID, dateInverter.invert(date), name, price, active);
                }
                return null;
            } catch (Exception SQLTimeoutException) {
                return null;
            }
        } catch (SQLException e) {
            throw new IllegalStateException("Cannot connect the database!", e);
        }
    }

    public static ArrayList<menu> getAllCurrentMenus() {
        ArrayList<menu> tempList = new ArrayList<menu>();
        try (Connection connection = DriverManager.getConnection(getURL(), getUser(), getPassword())) {
            String query = "SELECT * FROM menus WHERE active = 1;";
            ppdStatement = connection.prepareStatement(query);
            try {
                ResultSet rs = ppdStatement.executeQuery();
                while (rs.next()) {
                    int menuID = rs.getInt("menu_id");
                    int catID = rs.getInt("category_id");
                    String date = rs.getString("menu_date");
                    String name = rs.getString("name");
                    float price = rs.getFloat("price");
                    tempList.add(new menu(menuID, catID, dateInverter.invert(date), name, price, true));
                }
                return tempList;
            } catch (Exception SQLTimeoutException) {
                return null;
            }
        } catch (SQLException e) {
            throw new IllegalStateException("Cannot connect the database!", e);
        }
    }

    public static ArrayList<menu> getAllCurrentMenusAlphabetical() {
        ArrayList<menu> tempList = new ArrayList<menu>();
        try (Connection connection = DriverManager.getConnection(getURL(), getUser(), getPassword())) {
            String query = "SELECT * FROM menus WHERE active = 1 ORDER BY name ASC;";
            ppdStatement = connection.prepareStatement(query);
            try {
                ResultSet rs = ppdStatement.executeQuery();
                while (rs.next()) {
                    int menuID = rs.getInt("menu_id");
                    int catID = rs.getInt("category_id");
                    String date = rs.getString("menu_date");
                    String name = rs.getString("name");
                    float price = rs.getFloat("price");
                    tempList.add(new menu(menuID, catID, dateInverter.invert(date), name, price, true));
                }
                return tempList;
            } catch (Exception SQLTimeoutException) {
                return null;
            }
        } catch (SQLException e) {
            throw new IllegalStateException("Cannot connect the database!", e);
        }
    }

    public static ArrayList<menu> getAllCurrentMenusByCategory() {
        ArrayList<menu> tempList = new ArrayList<menu>();
        try (Connection connection = DriverManager.getConnection(getURL(), getUser(), getPassword())) {
            String query = "SELECT * FROM menus WHERE active = 1 ORDER BY category_id;";
            ppdStatement = connection.prepareStatement(query);
            try {
                ResultSet rs = ppdStatement.executeQuery();
                while (rs.next()) {
                    int menuID = rs.getInt("menu_id");
                    int catID = rs.getInt("category_id");
                    String date = rs.getString("menu_date");
                    String name = rs.getString("name");
                    float price = rs.getFloat("price");
                    tempList.add(new menu(menuID, catID, dateInverter.invert(date), name, price, true));
                }
                return tempList;
            } catch (Exception SQLTimeoutException) {
                return null;
            }
        } catch (SQLException e) {
            throw new IllegalStateException("Cannot connect the database!", e);
        }
    }

    public static Stack<Integer> getAllActiveMenuIDs() {
        Stack<Integer> tempStack = new Stack<Integer>();
        try (Connection connection = DriverManager.getConnection(getURL(), getUser(), getPassword())) {
            String query = "SELECT DISTINCT menu_id FROM menus WHERE active = true;";
            ppdStatement = connection.prepareStatement(query);
            try {
                ResultSet rs = ppdStatement.executeQuery();
                while (rs.next())
                    tempStack.add(rs.getInt("menu_id"));
                return tempStack;
            } catch (Exception SQLTimeoutException) {
                return null;
            }
        } catch (SQLException e) {
            throw new IllegalStateException("Cannot connect the database!", e);
        }
    }

    private static int getLastMenuID() {
        try (Connection connection = DriverManager.getConnection(getURL(), getUser(), getPassword())) {
            String query = "SELECT menu_id FROM menus ORDER BY menu_id DESC LIMIT 1;";
            try (Statement stmt = connection.createStatement()) {
                ResultSet rs = stmt.executeQuery(query);
                if (rs.next()) {
                    int menuID = rs.getInt("menu_id");
                    connection.close();
                    return menuID;
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
    public static int addMenu(int catID, String name, float price, boolean isFirst) {
        int menuID = getLastMenuID() + 1;
        return addMenu(menuID, catID, name, price, isFirst);
    }

    private static int addMenu(int ID, int catID, String name, float price, boolean isFirst) {
        try (Connection connection = DriverManager.getConnection(getURL(), getUser(), getPassword())) {
            String query = "INSERT INTO menus VALUES (?, ?, CURDATE(), ?, ?, TRUE);";
            ppdStatement = connection.prepareStatement(query);
            ppdStatement.setInt(1, ID);
            ppdStatement.setInt(2, catID);
            ppdStatement.setString(3, name);
            ppdStatement.setFloat(4, price);
            try {
                ppdStatement.executeUpdate();
                ppdStatement.close();
                connection.close();
                if (isFirst)
                    addToMenuData(ID);
                return ID;
            } catch (Exception SQLTimeoutException) {
                return -1;
            }
        } catch (SQLException e) {
            throw new IllegalStateException("Cannot connect the database!", e);
        }
    }

    private static void addToMenuData(int ID) {
        try (Connection connection = DriverManager.getConnection(getURL(), getUser(), getPassword())) {
            String query = "INSERT INTO menus_data VALUES (" + ID + ", NULL, NULL);";
            try (Statement stmt = connection.createStatement()) {
                stmt.executeUpdate(query);
                return;
            } catch (Exception e) {
                System.out.println(e);
                return;
            }
        } catch (SQLException e) {
            throw new IllegalStateException("Cannot connect the database!", e);
        }
    }

    public static boolean addProducts(int menuID, Stack<Integer> productIDs, Stack<Float> quantities) {
        try (Connection connection = DriverManager.getConnection(getURL(), getUser(), getPassword())) {
            String query = "INSERT INTO menus_products VALUES (?, ?, CURDATE(), ?);";
            ppdStatement = connection.prepareStatement(query);
            while (!productIDs.isEmpty()) {
                ppdStatement.setInt(1, menuID);
                ppdStatement.setInt(2, productIDs.pop());
                ppdStatement.setFloat(3, quantities.pop());
                try {
                    ppdStatement.executeUpdate();
                } catch (Exception SQLTimeoutException) {
                    return false;
                }
            }
            return true;
        } catch (SQLException e) {
            throw new IllegalStateException("Cannot connect the database!", e);
        }
    }

    // UPDATE in database.
    public static boolean updateName(menu theMenu, String name) {
        try (Connection connection = DriverManager.getConnection(getURL(), getUser(), getPassword())) {
            String query = "UPDATE menus AS m, (SELECT MAX(menu_date) AS menu_date FROM menus WHERE menu_id = ?) AS temp SET m.name = ? WHERE m.menu_date = temp.menu_date AND m.menu_id = ?;";
            ppdStatement = connection.prepareStatement(query);
            ppdStatement.setInt(1, theMenu.getId());
            ppdStatement.setString(2, name);
            ppdStatement.setInt(3, theMenu.getId());
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

    public static boolean updatePrice(menu theMenu, float menuPrice) {
        fixMenuDate(theMenu);
        try (Connection connection = DriverManager.getConnection(getURL(), getUser(), getPassword())) {
            String query = "UPDATE menus SET price = ? WHERE menu_id = ? AND active = true";
            ppdStatement = connection.prepareStatement(query);
            ppdStatement.setFloat(1, menuPrice);
            ppdStatement.setInt(2, theMenu.getId());
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

    public static boolean updateCategory(menu theMenu, int categoryID) {
        try (Connection connection = DriverManager.getConnection(getURL(), getUser(), getPassword())) {
            String query = "UPDATE menus SET category_id = ? WHERE menu_id = ? AND active = true;";
            ppdStatement = connection.prepareStatement(query);
            ppdStatement.setInt(1, categoryID);
            ppdStatement.setInt(2, theMenu.getId());
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

    public static boolean updateProducts(menu theMenu, Stack<Integer> stackIDs, Stack<Float> stackAmounts) {
        if (areProductEntriesToday(theMenu))
            removeMenuProductsToday(theMenu);
        while (!stackIDs.empty() && !stackAmounts.empty())
            addProducts(theMenu.getId(), stackIDs, stackAmounts);
        return true;
    }

    private static void fixMenuDate(menu theMenu) {
        if (isLastMenuEntryToday(theMenu))
            return;
        deleteMenu(theMenu.getId());
        addMenu(theMenu.getId(), theMenu.getCategoryID(), theMenu.getName(), theMenu.getPrice(), false);
    }

    // REMOVE from database.
    private static void removeMenuProductsToday(menu theMenu) {
        try (Connection connection = DriverManager.getConnection(getURL(), getUser(), getPassword())) {
            String query = "DELETE FROM menus_products WHERE menu_id = ? AND menu_products_date = CURDATE()";
            ppdStatement = connection.prepareStatement(query);
            ppdStatement.setInt(1, theMenu.getId());
            try {
                ppdStatement.executeUpdate();
            } catch (Exception SQLTimeoutException) {
            }
        } catch (SQLException e) {
        }
    }

    public static boolean deleteMenu(int menuID) {
        try (Connection connection = DriverManager.getConnection(getURL(), getUser(), getPassword())) {
            String query = "UPDATE menus SET active = false WHERE menu_id = ?";
            ppdStatement = connection.prepareStatement(query);
            ppdStatement.setInt(1, menuID);
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
    private static boolean isLastMenuEntryToday(menu theMenu) {
        try (Connection connection = DriverManager.getConnection(getURL(), getUser(), getPassword())) {
            String query = "SELECT * FROM menus WHERE menu_id = ? AND menu_date = CURDATE() AND active = true";
            ppdStatement = connection.prepareStatement(query);
            ppdStatement.setInt(1, theMenu.getId());
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

    private static boolean areProductEntriesToday(menu theMenu) {
        try (Connection connection = DriverManager.getConnection(getURL(), getUser(), getPassword())) {
            String query = "SELECT menu_products_date FROM menus_products WHERE menu_id = ? AND menu_products_date = CURDATE()";
            ppdStatement = connection.prepareStatement(query);
            ppdStatement.setInt(1, theMenu.getId());
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
            String query = "SELECT * FROM menus WHERE name = ? AND active = TRUE";
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