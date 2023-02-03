package windows.ingredientsWindow;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import componentsFood.ingredient;
import componentsFood.product;
import util.abstractManagerDB;

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
                    int amount = rs.getInt("amount");
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
                    int amount = rs.getInt("amount");
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
        ArrayList<ingredient> tempList = getNonRepeatedIngredients();
        tempList = getCorrectRepeatedIngredient(tempList);
        return tempList;
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

    private ArrayList<ingredient> getNonRepeatedIngredients() {
        ArrayList<ingredient> tempList = new ArrayList<ingredient>();
        try (Connection connection = DriverManager.getConnection(getURL(), getUser(), getPassword())) {
            String query = "SELECT * FROM ingredients WHERE ingredient_id NOT IN (SELECT ingredient_id FROM ingredients GROUP BY ingredient_id HAVING count(*) > 1);";
            try (Statement stmt = connection.createStatement()) {
                ResultSet rs = stmt.executeQuery(query);
                while (rs.next()) {
                    int ID = rs.getInt("ingredient_id");
                    int providerID = rs.getInt("provider_id");
                    String date = rs.getString("ingredients_date");
                    String name = rs.getString("name");
                    float price = rs.getFloat("price");
                    int amount = rs.getInt("amount");
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

    private ArrayList<ingredient> getCorrectRepeatedIngredient(ArrayList<ingredient> theList) {
        ArrayList<ingredient> tempList = new ArrayList<ingredient>();
        try (Connection connection = DriverManager.getConnection(getURL(), getUser(), getPassword())) {
            String query = "SELECT * FROM ingredients WHERE ingredient_id IN (SELECT ingredient_id FROM ingredients GROUP BY ingredient_id HAVING count(*) > 1);";
            try (Statement stmt = connection.createStatement()) {
                ResultSet rs = stmt.executeQuery(query);
                while (rs.next()) {
                    int ID = rs.getInt("ingredient_id");
                    int providerID = rs.getInt("provider_id");
                    String date = rs.getString("ingredients_date");
                    String name = rs.getString("name");
                    float price = rs.getFloat("price");
                    int amount = rs.getInt("amount");
                    boolean in_inventory = rs.getBoolean("in_inventory");
                    boolean active = rs.getBoolean("active");
                    tempList.add(new ingredient(ID, providerID, date, name, price, amount, in_inventory, active));
                }
                checkLatestIngredients(tempList);
                theList.addAll(tempList);
                connection.close();
                return theList;
            } catch (Exception e) {
                System.out.println(e);
                return theList;
            }
        } catch (SQLException e) {
            throw new IllegalStateException("Cannot connect the database!", e);
        }
    }

    private void checkLatestIngredients(ArrayList<ingredient> theList) {
        int goal = theList.size();
        for (int i = 0; i < goal - 1; i++) {
            if (theList.get(i).getId() == theList.get(i + 1).getId()) {
                LocalDate date1 = LocalDate.parse(theList.get(i).getDate(), DateTimeFormatter.ofPattern("yyyy-MM-dd"));
                LocalDate date2 = LocalDate.parse(theList.get(i + 1).getDate(),
                        DateTimeFormatter.ofPattern("yyyy-MM-dd"));
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
    }

    public ArrayList<ingredient> getSelectedIngredientsInProduct(product theProduct) {
        ArrayList<ingredient> tempList = new ArrayList<ingredient>();
        try (Connection connection = DriverManager.getConnection(getURL(), getUser(), getPassword())) {
            String query = "SELECT * FROM (SELECT ingredient_id, a.product_ingredients_date , ingredientQuantity FROM products_ingredients AS a, (SELECT MAX(product_ingredients_date) AS product_ingredients_date FROM products_ingredients WHERE product_id = "
                    + theProduct.getId() + ") AS b WHERE product_id = " + theProduct.getId()
                    + " AND a.product_ingredients_date = b.product_ingredients_date) AS temp NATURAL JOIN ingredients WHERE active = true AND ingredient_id = "
                    + theProduct.getId();
            try (Statement stmt = connection.createStatement()) {
                ResultSet rs = stmt.executeQuery(query);
                while (rs.next()) {
                    int ID = rs.getInt("ingredient_id");
                    int providerID = rs.getInt("provider_id");
                    String date = rs.getString("product_ingredients_date");
                    String name = rs.getString("name");
                    float price = rs.getFloat("price");
                    int amount = rs.getInt("amount");
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

    public ArrayList<ingredient> getNonSelectedIngredientsInProduct(product theProduct) {
        ArrayList<ingredient> tempList = new ArrayList<ingredient>();
        // NOT WORKING FOR INGREDIENTS WITH NO INGREDIENTS
        try (Connection connection = DriverManager.getConnection(getURL(), getUser(), getPassword())) {
            String query = "SELECT * FROM ingredients WHERE ingredient_id NOT IN (SELECT DISTINCT ingredient_id FROM products_ingredients AS a, (SELECT MAX(product_ingredients_date) AS temp FROM products_ingredients WHERE product_id = 1) AS b WHERE a.product_id = "
                    + theProduct.getId() + " AND a.product_ingredients_date = b.temp) AND active = true";
            try (Statement stmt = connection.createStatement()) {
                ResultSet rs = stmt.executeQuery(query);
                while (rs.next()) {
                    int ID = rs.getInt("ingredient_id");
                    int providerID = rs.getInt("provider_id");
                    String name = rs.getString("name");
                    float price = rs.getFloat("price");
                    int amount = rs.getInt("amount");
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

    public int getAmountOfIngredientInProduct(int productID, int ingredient_id) {
        try (Connection connection = DriverManager.getConnection(getURL(), getUser(), getPassword())) {
            String query = "SELECT ingredientQuantity FROM products_ingredients WHERE product_ingredients_date IN (SELECT product_date FROM beatneat.products WHERE active = true AND product_id = "
                    + productID + ") AND ingredient_id = " + ingredient_id;
            try (Statement stmt = connection.createStatement()) {
                ResultSet rs = stmt.executeQuery(query);
                if (rs.next()) {
                    int name = rs.getInt("ingredientQuantity");
                    connection.close();
                    return name;
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

    public int addIngredient(int provID, String date, String name, float price, int amount, boolean in_inventory) {
        int ingrID = getLastIngredientID() + 1;
        return addIngredient(ingrID, provID, date, name, price, amount, in_inventory);
    }

    private int addIngredient(int ingrID, int provID, String date, String name, float price, int amount,
            boolean in_inventory) {
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
            String query = "SELECT ingredient_id FROM beatneat.ingredients ORDER BY ingredient_id DESC LIMIT 1;";
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

    public boolean updateAmount(int ingredientID, int newAmount) {
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

    //

    private void fixIngredientDate(int ingredientID) {
        if (isLastIngredientEntryToday(ingredientID)) {
            return;
        }
        ingredient temp = getIngredient(ingredientID);
        setEntriesIngredientInactive(ingredientID);
        String dateToday = LocalDate.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd"));
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

    // REMOVE "ingredient" from database.

}
