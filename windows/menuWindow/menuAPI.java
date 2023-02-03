package windows.menuWindow;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import componentsFood.category;
import componentsFood.menu;
import util.abstractManagerDB;

public class menuAPI extends abstractManagerDB {

    public ArrayList<menu> getAllCurrentMenus() {
        ArrayList<menu> tempList = new ArrayList<menu>();
        try (Connection connection = DriverManager.getConnection(getURL(), getUser(), getPassword())) {
            String query = "SELECT * FROM menus WHERE active = 1";
            try (Statement stmt = connection.createStatement()) {
                ResultSet rs = stmt.executeQuery(query);
                while (rs.next()) {
                    int menuID = rs.getInt("menu_id");
                    String date = rs.getString("menu_date");
                    String name = rs.getString("name");
                    float price = rs.getFloat("price");
                    boolean active = rs.getBoolean("active");
                    tempList.add(new menu(menuID, date, name, price, active));
                }
                connection.close();
                return tempList;
            } catch (Exception e) {
                System.out.println(e);
                return tempList;
            }
        } catch (SQLException e) {
            throw new IllegalStateException("Cannot connect the database!", e);
        }
    }

    public menu getMenu(int menuID) {
        try (Connection connection = DriverManager.getConnection(getURL(), getUser(), getPassword())) {
            String query = "SELECT * FROM menus WHERE menu_id = " + menuID
                    + " ORDER BY menu_date DESC LIMIT 1;";
            try (Statement stmt = connection.createStatement()) {
                ResultSet rs = stmt.executeQuery(query);
                if (rs.next()) {
                    int ID = rs.getInt("menu_id");
                    String date = rs.getString("menu_date");
                    String name = rs.getString("name");
                    float price = rs.getFloat("price");
                    boolean active = rs.getBoolean("active");
                    return new menu(ID, date, name, price, active);
                }
            } catch (Exception e) {
                System.out.println(e);
                return null;
            }
        } catch (SQLException e) {
            throw new IllegalStateException("Cannot connect the database!", e);
        }
        return null;
    }

    public menu addMenu(String date, String name, float price, boolean active) {
        int menuID = getLastMenuID() + 1;
        return addMenu(menuID, date, name, price, active);
    }

    private menu addMenu(int ID, String date, String name, float price, boolean active) {
        try (Connection connection = DriverManager.getConnection(getURL(), getUser(), getPassword())) {
            String query = "INSERT INTO menus VALUES (" + ID + ", '" + date + "', '" + name + "', " + price
                    + ", " + active + ");";
            try (Statement stmt = connection.createStatement()) {
                stmt.executeUpdate(query);
                connection.close();
                return new menu(ID, date, name, price, active);
            } catch (Exception e) {
                System.out.println(e);
                return null;
            }
        } catch (SQLException e) {
            throw new IllegalStateException("Cannot connect the database!", e);
        }
    }

    private int getLastMenuID() {
        try (Connection connection = DriverManager.getConnection(getURL(), getUser(), getPassword())) {
            String query = "SELECT menu_id FROM menus ORDER BY menu_id DESC LIMIT 1;";
            try (Statement stmt = connection.createStatement()) {
                ResultSet rs = stmt.executeQuery(query);
                if (rs.next()) {
                    int providerID = rs.getInt("menu_id");
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

    public boolean addProducts(int menuID, int productID, String date, float quantity) {
        try (Connection connection = DriverManager.getConnection(getURL(), getUser(), getPassword())) {
            String query = "INSERT INTO menus_products VALUES (" + menuID + ", " + productID + ", '" + date
                    + "', " + quantity + ")";
            try (Statement stmt = connection.createStatement()) {
                stmt.executeUpdate(query);
            } catch (Exception a) {
                System.out.println(a);
                return false;
            }
            return true;
        } catch (SQLException e) {
            System.out.println(e);
            return false;
        }
    }

    public boolean updateMenuName(int menuID, String name) {
        try (Connection connection = DriverManager.getConnection(getURL(), getUser(), getPassword())) {
            String query = "UPDATE menus AS m, (SELECT MAX(menu_date) AS menu_date FROM menus WHERE menu_id = "
                    + menuID + ") AS temp SET m.name = '" + name
                    + "' WHERE m.menu_date = temp.menu_date AND m.menu_id = " + menuID + ";";
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
