package util.databaseAPIs;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Stack;

import componentsFood.ingredient;
import componentsFood.product;

public class ingredientsAPI extends abstractManagerDB {

    // GET from database.
    public ingredient getIngredient(int ID) {
        try (Connection connection = DriverManager.getConnection(getURL(), getUser(), getPassword())) {
            String query = "SELECT * FROM ingredients WHERE ingredient_id = ? AND active = true;";
            ppdStatement = connection.prepareStatement(query);
            ppdStatement.setInt(1, ID);
            try {
                ResultSet rs = ppdStatement.executeQuery();
                if (rs.next()) {
                    int prov_id = rs.getInt("provider_id");
                    String date = rs.getString("ingredients_date");
                    String name = rs.getString("name");
                    float price = rs.getFloat("price");
                    float amount = rs.getFloat("amount");
                    boolean in_inventory = rs.getBoolean("in_inventory");
                    boolean active = rs.getBoolean("active");
                    return new ingredient(ID, prov_id, date, name, price, amount, in_inventory, active);
                }
                return null;
            } catch (Exception SQLTimeoutException) {
                return null;
            }
        } catch (SQLException e) {
            throw new IllegalStateException("Cannot connect the database!", e);
        }
    }

    public float getAmountOfIngredientInProduct(int productID, int ingredient_id) {
        try (Connection connection = DriverManager.getConnection(getURL(), getUser(), getPassword())) {
            String query = "SELECT ingredientQuantity FROM products_ingredients WHERE product_ingredients_date IN (SELECT product_date FROM products WHERE active = true AND product_id = ?) AND ingredient_id = ?";
            ppdStatement = connection.prepareStatement(query);
            ppdStatement.setInt(1, productID);
            ppdStatement.setInt(2, ingredient_id);
            try {
                ResultSet rs = ppdStatement.executeQuery();
                if (rs.next())
                    return rs.getFloat("ingredientQuantity");
                return 0;
            } catch (Exception SQLTimeoutException) {
                return 0;
            }
        } catch (SQLException e) {
            throw new IllegalStateException("Cannot connect the database!", e);
        }
    }

    public Stack<Integer> getAllActiveProductIDs() {
        Stack<Integer> tempStack = new Stack<Integer>();
        try (Connection connection = DriverManager.getConnection(getURL(), getUser(), getPassword())) {
            String query = "SELECT DISTINCT product_id FROM products WHERE active = true;";
            ppdStatement = connection.prepareStatement(query);
            try {
                ResultSet rs = ppdStatement.executeQuery();
                while (rs.next())
                    tempStack.add(rs.getInt("product_id"));
                return tempStack;
            } catch (Exception SQLTimeoutException) {
                return null;
            }
        } catch (SQLException e) {
            throw new IllegalStateException("Cannot connect the database!", e);
        }
    }

    public ArrayList<ingredient> getAllCurrentIngredients() {
        ArrayList<ingredient> tempList = new ArrayList<ingredient>();
        try (Connection connection = DriverManager.getConnection(getURL(), getUser(), getPassword())) {
            String query = "SELECT * FROM ingredients WHERE active = true;";
            ppdStatement = connection.prepareStatement(query);
            try {
                ResultSet rs = ppdStatement.executeQuery();
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
                return tempList;
            } catch (Exception SQLTimeoutException) {
                return null;
            }
        } catch (SQLException e) {
            throw new IllegalStateException("Cannot connect the database!", e);
        }
    }

    public ArrayList<ingredient> getSelectedIngredientsInProduct(product theProduct) {
        ArrayList<ingredient> tempList = new ArrayList<ingredient>();
        try (Connection connection = DriverManager.getConnection(getURL(), getUser(), getPassword())) {
            String query = "SELECT * FROM (SELECT ingredient_id, a.product_ingredients_date, ingredientQuantity FROM products_ingredients AS a, (SELECT MAX(product_ingredients_date) AS product_ingredients_date FROM products_ingredients WHERE product_id = ?) AS b WHERE product_id = ? AND a.product_ingredients_date = b.product_ingredients_date) AS temp NATURAL JOIN ingredients WHERE active = true";
            ppdStatement = connection.prepareStatement(query);
            ppdStatement.setInt(1, theProduct.getId());
            ppdStatement.setInt(2, theProduct.getId());
            try {
                ResultSet rs = ppdStatement.executeQuery();
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
                return tempList;
            } catch (Exception SQLTimeoutException) {
                return null;
            }
        } catch (SQLException e) {
            throw new IllegalStateException("Cannot connect the database!", e);
        }
    }

    public ArrayList<ingredient> getNonSelectedIngredientsInProduct(product theProduct) {
        ArrayList<ingredient> tempList = new ArrayList<ingredient>();
        try (Connection connection = DriverManager.getConnection(getURL(), getUser(), getPassword())) {
            String query = "SELECT * FROM ingredients WHERE ingredient_id NOT IN (SELECT DISTINCT ingredient_id FROM products_ingredients AS a, (SELECT MAX(product_ingredients_date) AS temp FROM products_ingredients WHERE product_id = ?) AS b WHERE a.product_id = ? AND a.product_ingredients_date = b.temp) AND active = true";
            ppdStatement = connection.prepareStatement(query);
            ppdStatement.setInt(1, theProduct.getId());
            ppdStatement.setInt(2, theProduct.getId());
            try {
                ResultSet rs = ppdStatement.executeQuery();
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
                return tempList;
            } catch (Exception SQLTimeoutException) {
                return null;
            }
        } catch (SQLException e) {
            throw new IllegalStateException("Cannot connect the database!", e);
        }
    }

    public ArrayList<Integer> getProductsIDUsingIngredient(int ingredientID) {
        Stack<Integer> stackProductID = getAllActiveProductIDs();
        ArrayList<Integer> productIDs = new ArrayList<Integer>();
        while (!stackProductID.isEmpty()) {
            int temp = stackProductID.pop();
            if (isIngredientContainedInProduct(temp, ingredientID))
                productIDs.add(temp);
        }
        return productIDs;
    }

    private int getLastIngredientID() {
        try (Connection connection = DriverManager.getConnection(getURL(), getUser(), getPassword())) {
            String query = "SELECT ingredient_id FROM ingredients ORDER BY ingredient_id DESC LIMIT 1;";
            ppdStatement = connection.prepareStatement(query);
            try {
                ResultSet rs = ppdStatement.executeQuery();
                if (rs.next()) {
                    return rs.getInt("employee_id");
                }
                return -1;
            } catch (Exception SQLTimeoutException) {
                return -1;
            }
        } catch (SQLException e) {
            throw new IllegalStateException("Cannot connect the database!", e);
        }
    }

    // ADD to database.
    public int addIngredient(int provID, String name, float price, float amount, boolean in_inventory) {
        int ingrID = getLastIngredientID() + 1;
        return addIngredient(ingrID, provID, name, price, amount, in_inventory);
    }

    private int addIngredient(int ingrID, int provID, String name, float price, float amount,
            boolean in_inventory) {
        try (Connection connection = DriverManager.getConnection(getURL(), getUser(), getPassword())) {
            String query = "INSERT INTO ingredients VALUES (?, ?, CURDATE(), ?, ?, ?, ?, TRUE);";
            ppdStatement = connection.prepareStatement(query);
            ppdStatement.setInt(1, ingrID);
            ppdStatement.setInt(2, provID);
            ppdStatement.setString(3, name);
            ppdStatement.setFloat(4, price);
            ppdStatement.setFloat(5, amount);
            ppdStatement.setBoolean(6, in_inventory);
            try {
                ppdStatement.executeUpdate();
                return ingrID;
            } catch (Exception SQLTimeoutException) {
                return -1;
            }
        } catch (SQLException e) {
            throw new IllegalStateException("Cannot connect the database!", e);
        }
    }

    // UPDATE in database.
    public boolean updateProvider(ingredient theIngredient, int newProviderID) {
        fixIngredientDate(theIngredient);
        try (Connection connection = DriverManager.getConnection(getURL(), getUser(), getPassword())) {
            String query = "UPDATE ingredients SET provider_id = ? WHERE active = true AND ingredient_id = ?;";
            ppdStatement = connection.prepareStatement(query);
            ppdStatement.setInt(1, newProviderID);
            ppdStatement.setInt(2, theIngredient.getId());
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

    public boolean updateName(ingredient theIngredient, String newName) {
        try (Connection connection = DriverManager.getConnection(getURL(), getUser(), getPassword())) {
            String query = "UPDATE ingredients SET name = ? WHERE active = true AND ingredient_id = ?;";
            ppdStatement = connection.prepareStatement(query);
            ppdStatement.setString(1, newName);
            ppdStatement.setInt(2, theIngredient.getId());
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

    public boolean updatePrice(ingredient theIngredient, float newPrice) {
        fixIngredientDate(theIngredient);
        try (Connection connection = DriverManager.getConnection(getURL(), getUser(), getPassword())) {
            String query = "UPDATE ingredients SET price = ? WHERE active = true AND ingredient_id = ?;";
            ppdStatement = connection.prepareStatement(query);
            ppdStatement.setFloat(1, newPrice);
            ppdStatement.setInt(2, theIngredient.getId());
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

    public boolean updateAmount(ingredient theIngredient, float newAmount) {
        fixIngredientDate(theIngredient);
        try (Connection connection = DriverManager.getConnection(getURL(), getUser(), getPassword())) {
            String query = "UPDATE ingredients SET amount = ? WHERE active = true AND ingredient_id = ?;";
            ppdStatement = connection.prepareStatement(query);
            ppdStatement.setFloat(1, newAmount);
            ppdStatement.setInt(2, theIngredient.getId());
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

    public boolean updateInInventory(ingredient theIngredient, boolean in_inventory) {
        try (Connection connection = DriverManager.getConnection(getURL(), getUser(), getPassword())) {
            String query = "UPDATE ingredients SET in_inventory = ? WHERE active = true AND ingredient_id = ?;";
            ppdStatement = connection.prepareStatement(query);
            ppdStatement.setBoolean(1, in_inventory);
            ppdStatement.setInt(2, theIngredient.getId());
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

    public boolean setToUnactive(int ingredientID) {
        try (Connection connection = DriverManager.getConnection(getURL(), getUser(), getPassword())) {
            String query = "UPDATE ingredients SET active = false WHERE active = true AND ingredient_id = ?;";
            ppdStatement = connection.prepareStatement(query);
            ppdStatement.setInt(1, ingredientID);
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

    private void fixIngredientDate(ingredient theIngredient) {
        if (isLastIngredientEntryToday(theIngredient)) {
            return;
        }
        ingredient temp = getIngredient(theIngredient.getId());
        setEntriesIngredientInactive(temp);
        addIngredient(temp.getId(), temp.getProviderID(), temp.getName(), temp.getPrice(), temp.getAmount(),
                temp.getInInventory());
    }

    private void setEntriesIngredientInactive(ingredient theIngredient) {
        try (Connection connection = DriverManager.getConnection(getURL(), getUser(), getPassword())) {
            String query = "UPDATE ingredients SET active = false WHERE ingredient_id = ?;";
            ppdStatement = connection.prepareStatement(query);
            ppdStatement.setInt(1, theIngredient.getId());
            try {
                ppdStatement.executeUpdate();
            } catch (Exception SQLTimeoutException) {
            }
        } catch (SQLException e) {
            throw new IllegalStateException("Cannot connect the database!", e);
        }
    }

    // DELETE from database.
    public void deleteIngredientsInProduct(int productID, int ingredientID) {
        if (!isIngredientContainedInProduct(productID, ingredientID))
            try (Connection connection = DriverManager.getConnection(getURL(), getUser(), getPassword())) {
                String query = "DELETE FROM products_ingredients WHERE product_id = ? AND ingredient_id = ? AND product_ingredients_date IN (SELECT * FROM (SELECT MAX(product_ingredients_date) FROM products_ingredients WHERE product_id = ?) AS x)";
                ppdStatement = connection.prepareStatement(query);
                ppdStatement.setInt(1, productID);
                ppdStatement.setInt(2, ingredientID);
                ppdStatement.setInt(3, productID);
                try {
                    ppdStatement.executeUpdate();
                } catch (Exception SQLTimeoutException) {
                }
            } catch (SQLException e) {
            }
    }

    public void deleteProductWithIngredient(int productID, int ingredientID) {
        if (!isIngredientContainedInProduct(productID, ingredientID))
            try (Connection connection = DriverManager.getConnection(getURL(), getUser(), getPassword())) {
                String query = "UPDATE products SET active = false WHERE product_id = ?";
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
    private boolean isLastIngredientEntryToday(ingredient theIngredient) {
        try (Connection connection = DriverManager.getConnection(getURL(), getUser(), getPassword())) {
            String query = "SELECT 1 FROM (SELECT MAX(ingredients_date) AS max_date FROM ingredients WHERE ingredient_id = ?) as sub WHERE sub.max_date = CURDATE();";
            ppdStatement = connection.prepareStatement(query);
            ppdStatement.setInt(1, theIngredient.getId());
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

    private boolean isIngredientContainedInProduct(int productID, int ingredientID) {
        try (Connection connection = DriverManager.getConnection(getURL(), getUser(), getPassword())) {
            String query = "SELECT product_id FROM products_ingredients WHERE product_id = ? AND ingredient_id = ? AND product_ingredients_date IN (SELECT MAX(product_ingredients_date) FROM products_ingredients WHERE product_id = ?);";
            ppdStatement = connection.prepareStatement(query);
            ppdStatement.setInt(1, productID);
            ppdStatement.setInt(2, ingredientID);
            ppdStatement.setInt(3, productID);
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

    public boolean isNameTaken(String name) {
        try (Connection connection = DriverManager.getConnection(getURL(), getUser(), getPassword())) {
            String query = "SELECT * FROM ingredients WHERE name = ? AND active = TRUE";
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