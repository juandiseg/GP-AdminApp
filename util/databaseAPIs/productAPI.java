package util.databaseAPIs;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Stack;

import componentsFood.menu;
import componentsFood.product;

public class productAPI extends abstractManagerDB {

    // GET from database.
    public static product getProduct(int ID) {
        try (Connection connection = DriverManager.getConnection(getURL(), getUser(), getPassword())) {
            String query = "SELECT * FROM products WHERE product_id = ? ORDER BY product_date DESC LIMIT 1;";
            ppdStatement = connection.prepareStatement(query);
            ppdStatement.setInt(1, ID);
            try {
                ResultSet rs = ppdStatement.executeQuery();
                if (rs.next()) {
                    int catID = rs.getInt("category_id");
                    String date = rs.getString("product_date");
                    String name = rs.getString("name");
                    float price = rs.getFloat("price");
                    boolean active = rs.getBoolean("active");
                    return new product(ID, catID, date, name, price, active);
                }
                return null;
            } catch (Exception SQLTimeoutException) {
                return null;
            }
        } catch (SQLException e) {
            throw new IllegalStateException("Cannot connect the database!", e);
        }
    }

    public static ArrayList<product> getAllCurrentProducts() {
        ArrayList<product> tempList = new ArrayList<product>();
        try (Connection connection = DriverManager.getConnection(getURL(), getUser(), getPassword())) {
            String query = "SELECT * FROM products WHERE active = true ORDER BY product_id;";
            ppdStatement = connection.prepareStatement(query);
            try {
                ResultSet rs = ppdStatement.executeQuery();
                while (rs.next()) {
                    int ID = rs.getInt("product_id");
                    int catID = rs.getInt("category_id");
                    String date = rs.getString("product_date");
                    String name = rs.getString("name");
                    float price = rs.getFloat("price");
                    tempList.add(new product(ID, catID, dateInverter.invert(date), name, price, true));
                }
                return checkRepeatedProducts(tempList);
            } catch (Exception SQLTimeoutException) {
                return null;
            }
        } catch (SQLException e) {
            throw new IllegalStateException("Cannot connect the database!", e);
        }
    }

    public static ArrayList<product> getSelectedProductsInMenu(menu theMenu) {
        ArrayList<product> tempList = new ArrayList<product>();
        try (Connection connection = DriverManager.getConnection(getURL(), getUser(), getPassword())) {
            String query = "SELECT * FROM products NATURAL JOIN (SELECT product_id, a.menu_products_date, productQuantity FROM menus_products AS a, (SELECT MAX(menu_products_date) AS menu_products_date FROM menus_products WHERE menu_id = ?) AS b WHERE menu_id = ? AND a.menu_products_date = b.menu_products_date) as x WHERE active = true;";
            ppdStatement = connection.prepareStatement(query);
            ppdStatement.setInt(1, theMenu.getId());
            ppdStatement.setInt(2, theMenu.getId());
            try {
                ResultSet rs = ppdStatement.executeQuery();
                while (rs.next()) {
                    int ID = rs.getInt("product_id");
                    int catID = rs.getInt("product_id");
                    String name = rs.getString("name");
                    float price = rs.getFloat("price");
                    boolean active = rs.getBoolean("active");
                    tempList.add(new product(ID, catID, "", name, price, active));
                }
                return tempList;
            } catch (Exception SQLTimeoutException) {
                return null;
            }
        } catch (SQLException e) {
            throw new IllegalStateException("Cannot connect the database!", e);
        }
    }

    public static ArrayList<product> getNonSelectedProductsInMenu(menu theMenu) {
        ArrayList<product> tempList = new ArrayList<product>();
        // NOT WORKING FOR INGREDIENTS WITH NO INGREDIENTS
        try (Connection connection = DriverManager.getConnection(getURL(), getUser(), getPassword())) {
            String query = "SELECT * FROM products WHERE product_id NOT IN (SELECT DISTINCT product_id FROM menus_products AS a, (SELECT MAX(menu_products_date) AS temp FROM menus_products WHERE menu_id = ?) AS b WHERE a.menu_id = ? AND a.menu_products_date = b.temp) AND active = true;";
            ppdStatement = connection.prepareStatement(query);
            ppdStatement.setInt(1, theMenu.getId());
            ppdStatement.setInt(2, theMenu.getId());
            try {
                ResultSet rs = ppdStatement.executeQuery();
                while (rs.next()) {
                    int ID = rs.getInt("product_id");
                    int catID = rs.getInt("category_id");
                    String name = rs.getString("name");
                    float price = rs.getFloat("price");
                    boolean active = rs.getBoolean("active");
                    tempList.add(new product(ID, catID, "", name, price, active));
                }
                return tempList;
            } catch (Exception SQLTimeoutException) {
                return null;
            }
        } catch (SQLException e) {
            throw new IllegalStateException("Cannot connect the database!", e);
        }
    }

    private static boolean isProductContainedInMenu(int menuID, Integer productID) {
        try (Connection connection = DriverManager.getConnection(getURL(), getUser(), getPassword())) {
            String query = "SELECT menu_id FROM menus_products WHERE menu_id = ? AND product_id = ? AND menu_products_date IN (SELECT MAX(menu_products_date) FROM menus_products WHERE menu_id = ?);";
            ppdStatement = connection.prepareStatement(query);
            ppdStatement.setInt(1, menuID);
            ppdStatement.setInt(2, productID);
            ppdStatement.setInt(3, menuID);
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

    public static float getAmountOfProductInMenu(menu theMenu, product theProduct) {
        try (Connection connection = DriverManager.getConnection(getURL(), getUser(), getPassword())) {
            String query = "SELECT productQuantity FROM menus_products AS a, (SELECT menu_products_date FROM menus_products WHERE menu_id = ? ORDER BY menu_products_date DESC LIMIT 1) AS b WHERE a.menu_products_date = b.menu_products_date AND menu_id = ? AND product_id = ?;";
            ppdStatement = connection.prepareStatement(query);
            ppdStatement.setInt(1, theMenu.getId());
            ppdStatement.setInt(2, theMenu.getId());
            ppdStatement.setInt(3, theProduct.getId());
            try {
                ResultSet rs = ppdStatement.executeQuery();
                if (rs.next())
                    return rs.getFloat("productQuantity");
                return 0;
            } catch (Exception SQLTimeoutException) {
                return 0;
            }
        } catch (SQLException e) {
            throw new IllegalStateException("Cannot connect the database!", e);
        }
    }

    private static int getLastProductID() {
        try (Connection connection = DriverManager.getConnection(getURL(), getUser(), getPassword())) {
            String query = "SELECT product_id FROM products ORDER BY product_id DESC LIMIT 1;";
            ppdStatement = connection.prepareStatement(query);
            try {
                ResultSet rs = ppdStatement.executeQuery();
                if (rs.next())
                    return rs.getInt("product_id");
                return -1;
            } catch (Exception SQLTimeoutException) {
                return -1;
            }
        } catch (SQLException e) {
            throw new IllegalStateException("Cannot connect the database!", e);
        }
    }

    // ADD to database.
    public static int addProduct(int catID, String name, float price) {
        int productID = getLastProductID() + 1;
        return addProduct(productID, catID, name, price);
    }

    private static int addProduct(int ID, int catID, String name, float price) {
        try (Connection connection = DriverManager.getConnection(getURL(), getUser(), getPassword())) {
            String query = "INSERT INTO products VALUES (?, ?, CURDATE(), ?, ?, TRUE);";
            ppdStatement = connection.prepareStatement(query);
            ppdStatement.setInt(1, ID);
            ppdStatement.setInt(2, catID);
            ppdStatement.setString(3, name);
            ppdStatement.setFloat(4, price);
            try {
                ppdStatement.executeUpdate();
                return ID;
            } catch (Exception SQLTimeoutException) {
                return -1;
            }
        } catch (SQLException e) {
            throw new IllegalStateException("Cannot connect the database!", e);
        }
    }

    public static boolean addIngredients(int productID, Stack<Integer> ingredientIDs, Stack<Float> quantities) {
        try (Connection connection = DriverManager.getConnection(getURL(), getUser(), getPassword())) {
            String query = "INSERT INTO products_ingredients VALUES (?, ?, CURDATE(), ?);";
            ppdStatement = connection.prepareStatement(query);
            while (!ingredientIDs.isEmpty()) {
                ppdStatement.setInt(1, productID);
                ppdStatement.setInt(2, ingredientIDs.pop());
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
    public static boolean updateName(product theProduct, String name) {
        try (Connection connection = DriverManager.getConnection(getURL(), getUser(), getPassword())) {
            String query = "UPDATE products SET name = ? WHERE product_id = ? AND active = true;";
            ppdStatement = connection.prepareStatement(query);
            ppdStatement.setInt(1, theProduct.getId());
            ppdStatement.setString(2, name);
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

    public static boolean updatePrice(product theProduct, float productPrice) {
        fixProductDate(theProduct);
        try (Connection connection = DriverManager.getConnection(getURL(), getUser(), getPassword())) {
            String query = "UPDATE products SET price = ? WHERE product_id = ? AND active = true AND product_date = CURDATE();";
            ppdStatement = connection.prepareStatement(query);
            ppdStatement.setFloat(1, productPrice);
            ppdStatement.setInt(2, theProduct.getId());
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

    public static boolean updateCategory(product theProduct, int categoryID) {
        try (Connection connection = DriverManager.getConnection(getURL(), getUser(), getPassword())) {
            String query = "UPDATE products SET category_id = ? WHERE product_id = ? AND active = true AND product_date = CURDATE();";
            ppdStatement = connection.prepareStatement(query);
            ppdStatement.setInt(1, categoryID);
            ppdStatement.setInt(2, theProduct.getId());
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

    public static void updateActive(int productID, boolean active) {
        try (Connection connection = DriverManager.getConnection(getURL(), getUser(), getPassword())) {
            String query = "UPDATE products SET active = ? WHERE product_id = ?;";
            ppdStatement = connection.prepareStatement(query);
            ppdStatement.setBoolean(1, active);
            ppdStatement.setInt(2, productID);
            try {
                ppdStatement.executeUpdate();
            } catch (Exception SQLTimeoutException) {
            }
        } catch (SQLException e) {
            throw new IllegalStateException("Cannot connect the database!", e);
        }
    }

    public static boolean updateIngredients(int productID, Stack<Integer> stackIDs, Stack<Float> stackAmounts) {
        if (areIngredientEntriesToday(productID)) {
            removeProductIngredientsToday(productID);
        }
        return addIngredients(productID, stackIDs, stackAmounts);
    }

    private static void fixProductDate(product theProduct) {
        if (isLastProductEntryToday(theProduct))
            return;
        deleteProduct(theProduct);
        addProduct(theProduct.getId(), theProduct.getCategoryID(), theProduct.getName(),
                theProduct.getPrice());
    }

    // REMOVE from database.
    public static boolean deleteProduct(product theProduct) {
        try (Connection connection = DriverManager.getConnection(getURL(), getUser(), getPassword())) {
            String query = "UPDATE products SET active = false WHERE product_id =  ?";
            ppdStatement = connection.prepareStatement(query);
            ppdStatement.setInt(1, theProduct.getId());
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

    public static void deleteMenuWithProduct(int menuID, int productID) {
        if (!isProductContainedInMenu(menuID, productID))
            return;
        menuAPI.deleteMenu(menuID);
    }

    public static void deleteProductsInMenu(int menuID, product theProduct) {
        if (!isProductContainedInMenu(menuID, theProduct.getId()))
            return;
        try (Connection connection = DriverManager.getConnection(getURL(), getUser(), getPassword())) {
            String query = "DELETE FROM menus_products WHERE menu_id = ? AND product_id = ? AND menu_products_date IN (SELECT * FROM (SELECT MAX(menu_products_date) FROM menus_products WHERE menu_id = ?) AS x)";
            ppdStatement = connection.prepareStatement(query);
            ppdStatement.setInt(1, menuID);
            ppdStatement.setInt(2, theProduct.getId());
            ppdStatement.setInt(3, menuID);
            try {
                ppdStatement.executeUpdate();
            } catch (Exception SQLTimeoutException) {
            }
        } catch (SQLException e) {
        }
    }

    private static void removeProductIngredientsToday(int productID) {
        try (Connection connection = DriverManager.getConnection(getURL(), getUser(), getPassword())) {
            String query = "DELETE FROM products_ingredients WHERE product_id = ? AND product_ingredients_date = CURDATE();";
            ppdStatement = connection.prepareStatement(query);
            ppdStatement.setInt(1, productID);
            try {
                ppdStatement.executeUpdate();
            } catch (Exception SQLTimeoutException) {
            }
        } catch (SQLException e) {
        }
    }

    // CHECK in database.
    private static ArrayList<product> checkRepeatedProducts(ArrayList<product> theList) {
        int goal = theList.size();
        for (int i = 0; i < goal - 1; i++) {
            if (theList.get(i).getId() == theList.get(i + 1).getId()) {
                LocalDate date1 = LocalDate.parse(theList.get(i).getDate(), DateTimeFormatter.ofPattern("dd-MM-yyyy"));
                LocalDate date2 = LocalDate.parse(theList.get(i + 1).getDate(),
                        DateTimeFormatter.ofPattern("dd-MM-yyyy"));
                if (date1.isBefore(date2)) {
                    theList.remove(i);
                    goal--;
                    i--;
                } else {
                    theList.remove(i + 1);
                    goal--;
                    i--;
                }
            }
        }
        return theList;
    }

    private static boolean isLastProductEntryToday(product theProduct) {
        try (Connection connection = DriverManager.getConnection(getURL(), getUser(), getPassword())) {
            String query = "SELECT * FROM products WHERE product_id = ? AND product_date = CURDATE() AND active = true;";
            ppdStatement = connection.prepareStatement(query);
            ppdStatement.setInt(1, theProduct.getId());
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

    public static boolean isProductIngredientDateToday(int productID) {
        try (Connection connection = DriverManager.getConnection(getURL(), getUser(), getPassword())) {
            String query = "SELECT products_ingredients_date FROM products_ingredients WHERE product_id = ? AND products_ingredients = CURDATE()";
            ppdStatement = connection.prepareStatement(query);
            ppdStatement.setInt(1, productID);
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

    private static boolean areIngredientEntriesToday(int productID) {
        try (Connection connection = DriverManager.getConnection(getURL(), getUser(), getPassword())) {
            String query = "SELECT * FROM products_ingredients WHERE product_id = ? AND product_ingredients_date = CURDATE()";
            ppdStatement = connection.prepareStatement(query);
            ppdStatement.setInt(1, productID);
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
            String query = "SELECT * FROM products WHERE name = ? AND active = TRUE";
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