package util.databaseAPIs;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Stack;

import componentsFood.menu;

public class menuAPI extends abstractManagerDB {

    public ArrayList<menu> getAllCurrentMenus() {
        ArrayList<menu> tempList = new ArrayList<menu>();
        try (Connection connection = DriverManager.getConnection(getURL(), getUser(), getPassword())) {
            String query = "SELECT * FROM menus WHERE active = 1";
            try (Statement stmt = connection.createStatement()) {
                ResultSet rs = stmt.executeQuery(query);
                while (rs.next()) {
                    int menuID = rs.getInt("menu_id");
                    int catID = rs.getInt("category_id");
                    String date = rs.getString("menu_date");
                    String name = rs.getString("name");
                    float price = rs.getFloat("price");
                    tempList.add(new menu(menuID, catID, dateInverter.invert(date), name, price, true));
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
                    int catID = rs.getInt("category_id");
                    String date = rs.getString("menu_date");
                    String name = rs.getString("name");
                    float price = rs.getFloat("price");
                    boolean active = rs.getBoolean("active");
                    return new menu(ID, catID, dateInverter.invert(date), name, price, active);
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

    public menu addMenu(String date, int catID, String name, float price, boolean active) {
        int menuID = getLastMenuID() + 1;
        return addMenu(menuID, catID, date, name, price, active);
    }

    private menu addMenu(int ID, int catID, String date, String name, float price, boolean active) {
        date = dateInverter.invert(date);
        try (Connection connection = DriverManager.getConnection(getURL(), getUser(), getPassword())) {
            String query = "INSERT INTO menus VALUES (" + ID + ", " + catID + ", '" + date + "', '" + name + "', "
                    + price
                    + ", " + active + ");";
            try (Statement stmt = connection.createStatement()) {
                stmt.executeUpdate(query);
                connection.close();
                return new menu(ID, catID, dateInverter.invert(date), name, price, active);
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

    public boolean addProducts(int menuID, int productID, String date, Float quantity) {
        date = dateInverter.invert(date);
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

    public boolean updatePrice(menu theMenu, float menuPrice) {
        fixMenuDate(theMenu);
        try (Connection connection = DriverManager.getConnection(getURL(), getUser(), getPassword())) {
            String query = "UPDATE menus SET price = " + menuPrice + " WHERE menu_id = " + theMenu.getId()
                    + " AND active = true";
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

    public boolean updateCategory(menu theMenu, int categoryID) {
        try (Connection connection = DriverManager.getConnection(getURL(), getUser(), getPassword())) {
            String query = "UPDATE menus SET category_id = " + categoryID + " WHERE menu_id = " + theMenu.getId()
                    + " AND active = true;";
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

    private void fixMenuDate(menu theMenu) {
        if (isLastMenuEntryToday(theMenu))
            return;
        setMenuIDUnactive(theMenu.getId());
        String dateToday = LocalDate.now().format(DateTimeFormatter.ofPattern("dd-MM-yyyy"));
        addMenu(theMenu.getId(), theMenu.getCategoryID(), dateToday, theMenu.getName(), theMenu.getPrice(), true);
    }

    private boolean isLastMenuEntryToday(menu theMenu) {
        try (Connection connection = DriverManager.getConnection(getURL(), getUser(), getPassword())) {
            String query = "SELECT * FROM menus WHERE menu_id = " + theMenu.getId()
                    + " AND menu_date = CURDATE() AND active = true";
            try (Statement stmt = connection.createStatement()) {
                ResultSet rs = stmt.executeQuery(query);
                if (rs.next()) {
                    connection.close();
                    return true;
                } else {
                    connection.close();
                    return false;
                }
            } catch (Exception e) {
                System.out.println(e);
                return false;
            }
        } catch (SQLException e) {
            throw new IllegalStateException("Cannot connect the database!", e);
        }
    }

    public void setMenuIDUnactive(int menuID) {
        try (Connection connection = DriverManager.getConnection(getURL(), getUser(), getPassword())) {
            String query = "UPDATE menus SET active = false WHERE menu_id = " + menuID;
            try (Statement stmt = connection.createStatement()) {
                stmt.executeUpdate(query);
                connection.close();
            } catch (Exception e) {
                System.out.println(e);
            }
        } catch (SQLException e) {
            throw new IllegalStateException("Cannot connect the database!", e);
        }
    }

    public boolean updateProducts(menu theMenu, Stack<Integer> stackIDs, Stack<Float> stackAmounts) {
        if (areProductEntriesToday(theMenu))
            removeMenuProductsToday(theMenu);
        while (!stackIDs.empty() && !stackAmounts.empty()) {
            String dateToday = LocalDate.now().format(DateTimeFormatter.ofPattern("dd-MM-yyyy"));
            addProducts(theMenu.getId(), stackIDs.pop(), dateToday, stackAmounts.pop());
        }
        return true;
    }

    private boolean areProductEntriesToday(menu theMenu) {
        try (Connection connection = DriverManager.getConnection(getURL(), getUser(), getPassword())) {
            String query = "SELECT menu_products_date FROM menus_products WHERE menu_id = " + theMenu.getId()
                    + " AND menu_products_date = CURDATE()";
            try (Statement stmt = connection.createStatement()) {
                ResultSet rs = stmt.executeQuery(query);
                if (rs.next()) {
                    connection.close();
                    return true;
                } else {
                    connection.close();
                    return false;
                }
            } catch (Exception e) {
                System.out.println(e);
                return false;
            }
        } catch (SQLException e) {
            throw new IllegalStateException("Cannot connect the database!", e);
        }
    }

    // REMOVE "product" from database.
    private void removeMenuProductsToday(menu theMenu) {
        try (Connection connection = DriverManager.getConnection(getURL(), getUser(), getPassword())) {
            String query = "DELETE FROM menus_products WHERE menu_id = " + theMenu.getId() +
                    " AND menu_products_date = CURDATE()";
            try (Statement stmt = connection.createStatement()) {
                stmt.executeUpdate(query);
                connection.close();
            } catch (Exception e) {
                System.out.println(e);
            }
        } catch (SQLException e) {
            throw new IllegalStateException("Cannot connect the database!", e);
        }
    }

    public boolean deleteMenu(menu theMenu) {
        try (Connection connection = DriverManager.getConnection(getURL(), getUser(), getPassword())) {
            String query = "UPDATE menus SET active = false WHERE menu_id = " + theMenu.getId();
            try (Statement stmt = connection.createStatement()) {
                stmt.executeUpdate(query);
                connection.close();
                return true;
            } catch (Exception e) {
                return false;
            }
        } catch (SQLException e) {
            return false;
        }
    }

    public boolean isNameTaken(String name) {
        try (Connection connection = DriverManager.getConnection(getURL(), getUser(), getPassword())) {
            String query = "SELECT * FROM menus WHERE name = '" + name + "' AND active = TRUE";
            try (Statement stmt = connection.createStatement()) {
                ResultSet rs = stmt.executeQuery(query);
                if (rs.next()) {
                    connection.close();
                    return true;
                }
                connection.close();
                return false;
            } catch (Exception e) {
                return true;
            }
        } catch (SQLException e) {
            throw new IllegalStateException("Cannot connect the database!", e);
        }
    }
}
