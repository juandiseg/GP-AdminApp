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

import componentsFood.ingredient;
import componentsFood.product;

public class ingredientsAPI extends abstractManagerDB {

    // GET "ingredient" objects from database.

    public ArrayList<ingredient> getAllIngredients() {
        ArrayList<ingredient> tempList = new ArrayList<ingredient>();
        try (Connection connection = DriverManager.getConnection(getURL(), getUser(), getPassword())) {
            String query = "SELECT * FROM ingredients";
            try (Statement stmt = connection.createStatement()) {
                ResultSet rs = stmt.executeQuery(query);
                while (rs.next()) {
                    int ID = rs.getInt("ingredient_id");
                    int providerID = rs.getInt("provider_id");
                    String date = rs.getString("ingredients_date");
                    String name = rs.getString("name");
                    float price = rs.getFloat("price");
                    float amount = rs.getFloat("amount");
                    boolean in_inventory = rs.getBoolean("in_inventory");
                    boolean active = rs.getBoolean("active");
                    tempList.add(new ingredient(ID, providerID, date, name, price, amount, in_inventory, active));
                }
                connection.close();
                return tempList;
            } catch (Exception e) {
                System.out.println(e);
                return null;
            }
        } catch (SQLException e) {
            throw new IllegalStateException("Cannot connect the database!", e);
        }
    }

    public ingredient getIngredient(int ID) {
        try (Connection connection = DriverManager.getConnection(getURL(), getUser(), getPassword())) {
            String query = "SELECT * FROM ingredients WHERE ingredient_id = " + ID + " AND active = true;";
            try (Statement stmt = connection.createStatement()) {
                ResultSet rs = stmt.executeQuery(query);
                if (rs.next()) {
                    int prov_id = rs.getInt("provider_id");
                    String date = rs.getString("ingredients_date");
                    String name = rs.getString("name");
                    float price = rs.getFloat("price");
                    float amount = rs.getFloat("amount");
                    boolean in_inventory = rs.getBoolean("in_inventory");
                    boolean active = rs.getBoolean("active");
                    connection.close();
                    return new ingredient(ID, prov_id, date, name, price, amount, in_inventory, active);
                } else {
                    connection.close();
                    return null;
                }
            } catch (Exception e) {
                System.out.println(e);
                return null;
            }
        } catch (SQLException e) {
            throw new IllegalStateException("Cannot connect the database!", e);
        }
    }

    public ArrayList<ingredient> getAllCurrentIngredients() {
        ArrayList<ingredient> tempList = new ArrayList<ingredient>();
        try (Connection connection = DriverManager.getConnection(getURL(), getUser(), getPassword())) {
            String query = "SELECT * FROM ingredients WHERE active = true";
            try (Statement stmt = connection.createStatement()) {
                ResultSet rs = stmt.executeQuery(query);
                while (rs.next()) {
                    int ID = rs.getInt("ingredient_id");
                    int providerID = rs.getInt("provider_id");
                    String date = rs.getString("ingredients_date");
                    String name = rs.getString("name");
                    float price = rs.getFloat("price");
                    float amount = rs.getFloat("amount");
                    boolean in_inventory = rs.getBoolean("in_inventory");
                    boolean active = rs.getBoolean("active");
                    tempList.add(new ingredient(ID, providerID, dateInverter.invert(date), name, price, amount,
                            in_inventory, active));
                }
                connection.close();
                return tempList;
            } catch (Exception e) {
                System.out.println(e);
                return null;
            }
        } catch (SQLException e) {
            throw new IllegalStateException("Cannot connect the database!", e);
        }
    }

    public Stack<Integer> getAllActiveProductIDs() {
        Stack<Integer> tempStack = new Stack<Integer>();
        try (Connection connection = DriverManager.getConnection(getURL(), getUser(), getPassword())) {
            String query = "SELECT DISTINCT product_id FROM products WHERE active = true;";
            try (Statement stmt = connection.createStatement()) {
                ResultSet rs = stmt.executeQuery(query);
                while (rs.next()) {
                    int ID = rs.getInt("product_id");
                    tempStack.add(ID);
                }
                connection.close();
                return tempStack;
            } catch (Exception e) {
                System.out.println(e);
                return null;
            }
        } catch (SQLException e) {
            throw new IllegalStateException("Cannot connect the database!", e);
        }
    }

    public String getIngredientName(String ID) {
        try (Connection connection = DriverManager.getConnection(getURL(), getUser(), getPassword())) {
            String query = "SELECT * FROM ingredients WHERE ingredient_id = " + ID;
            try (Statement stmt = connection.createStatement()) {
                ResultSet rs = stmt.executeQuery(query);
                if (rs.next()) {
                    String name = rs.getString("name");
                    connection.close();
                    return name;
                } else {
                    connection.close();
                    return "";
                }
            } catch (Exception e) {
                System.out.println(e);
                return null;
            }
        } catch (SQLException e) {
            throw new IllegalStateException("Cannot connect the database!", e);
        }
    }

    public ArrayList<ingredient> getSelectedIngredientsInProduct(product theProduct) {
        ArrayList<ingredient> tempList = new ArrayList<ingredient>();
        try (Connection connection = DriverManager.getConnection(getURL(), getUser(), getPassword())) {
            String query = "SELECT * FROM (SELECT ingredient_id, a.product_ingredients_date, ingredientQuantity FROM products_ingredients AS a, (SELECT MAX(product_ingredients_date) AS product_ingredients_date FROM products_ingredients WHERE product_id = "
                    + theProduct.getId() + ") AS b WHERE product_id = " + theProduct.getId()
                    + " AND a.product_ingredients_date = b.product_ingredients_date) AS temp NATURAL JOIN ingredients WHERE active = true";
            try (Statement stmt = connection.createStatement()) {
                ResultSet rs = stmt.executeQuery(query);
                while (rs.next()) {
                    int ID = rs.getInt("ingredient_id");
                    int providerID = rs.getInt("provider_id");
                    String date = rs.getString("product_ingredients_date");
                    String name = rs.getString("name");
                    float price = rs.getFloat("price");
                    float amount = rs.getFloat("amount");
                    boolean in_inventory = rs.getBoolean("in_inventory");
                    boolean active = rs.getBoolean("active");
                    tempList.add(new ingredient(ID, providerID, dateInverter.invert(date), name, price, amount,
                            in_inventory, active));
                }
                connection.close();
                return tempList;
            } catch (Exception e) {
                System.out.println(e);
                return null;
            }
        } catch (SQLException e) {
            throw new IllegalStateException("Cannot connect the database!", e);
        }
    }

    public ArrayList<ingredient> getNonSelectedIngredientsInProduct(product theProduct) {
        ArrayList<ingredient> tempList = new ArrayList<ingredient>();
        // NOT WORKING FOR INGREDIENTS WITH NO INGREDIENTS
        try (Connection connection = DriverManager.getConnection(getURL(), getUser(), getPassword())) {
            String query = "SELECT * FROM ingredients WHERE ingredient_id NOT IN (SELECT DISTINCT ingredient_id FROM products_ingredients AS a, (SELECT MAX(product_ingredients_date) AS temp FROM products_ingredients WHERE product_id = "
                    + theProduct.getId() + ") AS b WHERE a.product_id = "
                    + theProduct.getId() + " AND a.product_ingredients_date = b.temp) AND active = true";
            try (Statement stmt = connection.createStatement()) {
                ResultSet rs = stmt.executeQuery(query);
                while (rs.next()) {
                    int ID = rs.getInt("ingredient_id");
                    int providerID = rs.getInt("provider_id");
                    String name = rs.getString("name");
                    float price = rs.getFloat("price");
                    float amount = rs.getFloat("amount");
                    boolean in_inventory = rs.getBoolean("in_inventory");
                    boolean active = rs.getBoolean("active");
                    tempList.add(new ingredient(ID, providerID, "", name, price, amount, in_inventory, active));
                }
                connection.close();
                return tempList;
            } catch (Exception e) {
                System.out.println(e);
                return null;
            }
        } catch (SQLException e) {
            throw new IllegalStateException("Cannot connect the database!", e);
        }
    }

    public float getAmountOfIngredientInProduct(int productID, int ingredient_id) {
        try (Connection connection = DriverManager.getConnection(getURL(), getUser(), getPassword())) {
            String query = "SELECT ingredientQuantity FROM products_ingredients WHERE product_ingredients_date IN (SELECT product_date FROM products WHERE active = true AND product_id = "
                    + productID + ") AND ingredient_id = " + ingredient_id;
            try (Statement stmt = connection.createStatement()) {
                ResultSet rs = stmt.executeQuery(query);
                if (rs.next()) {
                    float quantity = rs.getFloat("ingredientQuantity");
                    connection.close();
                    return quantity;
                } else {
                    connection.close();
                    return -1;
                }
            } catch (Exception e) {
                System.out.println(e);
                return -1;
            }
        } catch (SQLException e) {
            throw new IllegalStateException("Cannot connect the database!", e);
        }
    }

    // ADD "ingredient" to database.

    public int addIngredient(int provID, String date, String name, float price, float amount, boolean in_inventory) {
        int ingrID = getLastIngredientID() + 1;
        return addIngredient(ingrID, provID, date, name, price, amount, in_inventory);
    }

    private int addIngredient(int ingrID, int provID, String date, String name, float price, float amount,
            boolean in_inventory) {
        date = dateInverter.invert(date);
        try (Connection connection = DriverManager.getConnection(getURL(), getUser(), getPassword())) {
            String query = "INSERT INTO ingredients VALUES (" + ingrID + ", " + provID + ", '" + date + "', '" + name
                    + "', " + price + ", " + amount + ", " + in_inventory + ", " + true + ");";
            try (Statement stmt = connection.createStatement()) {
                stmt.executeUpdate(query);
                connection.close();
                return ingrID;
            } catch (Exception e) {
                System.out.println(e);
                return -1;
            }
        } catch (SQLException e) {
            throw new IllegalStateException("Cannot connect the database!", e);
        }
    }

    private int getLastIngredientID() {
        try (Connection connection = DriverManager.getConnection(getURL(), getUser(), getPassword())) {
            String query = "SELECT ingredient_id FROM ingredients ORDER BY ingredient_id DESC LIMIT 1;";
            try (Statement stmt = connection.createStatement()) {
                ResultSet rs = stmt.executeQuery(query);
                if (rs.next()) {
                    int providerID = rs.getInt("ingredient_id");
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

    // UPDATE something "ingredient" related in database.

    public boolean updateInInventory(int ingredientID, boolean in_inventory) {
        try (Connection connection = DriverManager.getConnection(getURL(), getUser(), getPassword())) {
            String query = "UPDATE ingredients SET in_inventory = " + in_inventory
                    + " WHERE active = true AND ingredient_id = " + ingredientID;
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

    public boolean updateName(int ingredientID, String newName) {
        try (Connection connection = DriverManager.getConnection(getURL(), getUser(), getPassword())) {
            String query = "UPDATE ingredients SET name = '" + newName
                    + "' WHERE active = true AND ingredient_id = " + ingredientID;
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

    public boolean updateProvider(int ingredientID, int newProviderID) {
        fixIngredientDate(ingredientID);
        try (Connection connection = DriverManager.getConnection(getURL(), getUser(), getPassword())) {
            String query = "UPDATE ingredients SET provider_id = " + newProviderID
                    + " WHERE active = true AND ingredient_id = " + ingredientID;
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

    public boolean updatePrice(int ingredientID, float newPrice) {
        fixIngredientDate(ingredientID);
        try (Connection connection = DriverManager.getConnection(getURL(), getUser(), getPassword())) {
            String query = "UPDATE ingredients SET price = " + newPrice
                    + " WHERE active = true AND ingredient_id = " + ingredientID;
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

    public boolean updateAmount(int ingredientID, float newAmount) {
        fixIngredientDate(ingredientID);
        try (Connection connection = DriverManager.getConnection(getURL(), getUser(), getPassword())) {
            String query = "UPDATE ingredients SET amount = " + newAmount
                    + " WHERE active = true AND ingredient_id = " + ingredientID;
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

    public boolean setToUnactive(int ingredientID) {
        try (Connection connection = DriverManager.getConnection(getURL(), getUser(), getPassword())) {
            String query = "UPDATE ingredients SET active = false WHERE active = true AND ingredient_id = "
                    + ingredientID;
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

    //

    private void fixIngredientDate(int ingredientID) {
        if (isLastIngredientEntryToday(ingredientID)) {
            return;
        }
        ingredient temp = getIngredient(ingredientID);
        setEntriesIngredientInactive(ingredientID);
        String dateToday = LocalDate.now().format(DateTimeFormatter.ofPattern("dd-MM-yyyy"));
        addIngredient(temp.getId(), temp.getProviderID(), dateToday, temp.getName(), temp.getPrice(), temp.getAmount(),
                temp.getInInventory());
    }

    private boolean isLastIngredientEntryToday(int ingredientID) {
        try (Connection connection = DriverManager.getConnection(getURL(), getUser(), getPassword())) {
            String dateToday = LocalDate.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd"));
            String query = "SELECT MAX(ingredients_date) FROM ingredients WHERE ingredient_id = " + ingredientID;
            try (Statement stmt = connection.createStatement()) {
                ResultSet rs = stmt.executeQuery(query);
                if (rs.next()) {
                    String dateDB = rs.getString("MAX(ingredients_date)");
                    connection.close();
                    if (dateToday.equals(dateDB))
                        return true;
                    else
                        return false;
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

    private void setEntriesIngredientInactive(int ingredientID) {
        try (Connection connection = DriverManager.getConnection(getURL(), getUser(), getPassword())) {
            String query = "UPDATE ingredients SET active = false WHERE ingredient_id = " + ingredientID;
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

    public ArrayList<Integer> getProductsIDWithIngredient(int ingredientID) {
        Stack<Integer> stackProductID = getAllActiveProductIDs();
        ArrayList<Integer> productIDs = new ArrayList<Integer>();
        while (!stackProductID.isEmpty()) {
            int temp = stackProductID.pop();
            if (isIngredientContainedInProduct(temp, ingredientID))
                productIDs.add(temp);
        }
        return productIDs;
    }

    // REMOVE "ingredient" from database.

    public void deleteIngredientsInProduct(int productID, int ingredientID) {
        if (!isIngredientContainedInProduct(productID, ingredientID))
            return;
        try (Connection connection = DriverManager.getConnection(getURL(), getUser(), getPassword())) {
            String query = "DELETE FROM products_ingredients WHERE product_id = " + productID + " AND ingredient_id = "
                    + ingredientID
                    + " AND product_ingredients_date IN (SELECT * FROM (SELECT MAX(product_ingredients_date) FROM products_ingredients WHERE product_id = "
                    + productID + ") AS x)";
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

    private boolean isIngredientContainedInProduct(int productID, int ingredientID) {
        try (Connection connection = DriverManager.getConnection(getURL(), getUser(), getPassword())) {
            String query = "SELECT product_id FROM products_ingredients WHERE product_id = " + productID
                    + " AND ingredient_id = " + ingredientID
                    + " AND product_ingredients_date IN (SELECT MAX(product_ingredients_date) FROM products_ingredients WHERE product_id = "
                    + productID + ")";
            try (Statement stmt = connection.createStatement()) {
                ResultSet rs = stmt.executeQuery(query);
                if (rs.next()) {
                    connection.close();
                    return true;
                }
                return false;
            } catch (Exception e) {
                System.out.println(e);
                return false;
            }
        } catch (SQLException e) {
            throw new IllegalStateException("Cannot connect the database!", e);
        }
    }

    public void deleteProductWithIngredient(int productID, int ingredientID) {
        if (!isIngredientContainedInProduct(productID, ingredientID))
            return;
        try (Connection connection = DriverManager.getConnection(getURL(), getUser(), getPassword())) {
            String query = "UPDATE products SET active = false WHERE product_id = " + productID;
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

    public boolean isNameTaken(String name) {
        try (Connection connection = DriverManager.getConnection(getURL(), getUser(), getPassword())) {
            String query = "SELECT * FROM ingredients WHERE name = '" + name + "' AND active = true";
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
