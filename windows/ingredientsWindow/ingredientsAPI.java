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

    public ingredient getIngredient(int ID, int prov_id, String date) {
        try (Connection connection = DriverManager.getConnection(getURL(), getUser(), getPassword())) {
            String query = "SELECT * FROM ingredients WHERE ingredient_id = " + ID + " AND provider_id  = " + prov_id
                    + " AND ingredients_date = '" + date + "';";
            try (Statement stmt = connection.createStatement()) {
                ResultSet rs = stmt.executeQuery(query);
                if (rs.next()) {
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

    private ArrayList<ingredient> checkLatestIngredients(ArrayList<ingredient> theList) {
        int goal = theList.size();
        for (int i = 0; i < goal - 1; i++) {
            if (theList.get(i) == theList.get(i + 1)) {
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
        return theList;
    }

    public ArrayList<ingredient> getSelectedIngredientsInProduct(product theProduct) {
        ArrayList<ingredient> tempList = new ArrayList<ingredient>();
        try (Connection connection = DriverManager.getConnection(getURL(), getUser(), getPassword())) {
            String query = "SELECT * FROM ingredients WHERE ingredient_id IN (SELECT DISTINCT ingredient_id FROM ingredients NATURAL JOIN product_ingredients WHERE product_id = "
                    + theProduct.getId()
                    + ") AND (ingredient_id, ingredients_date) IN (SELECT ingredient_id, MAX(ingredients_date) FROM ingredients GROUP BY ingredient_id)";
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

    public ArrayList<ingredient> getNonSelectedIngredientsInProduct(product theProduct) {
        ArrayList<ingredient> tempList = new ArrayList<ingredient>();
        try (Connection connection = DriverManager.getConnection(getURL(), getUser(), getPassword())) {
            String query = "SELECT * FROM ingredients WHERE ingredient_id NOT IN (SELECT DISTINCT ingredient_id FROM ingredients NATURAL JOIN product_ingredients WHERE product_id ="
                    + theProduct.getId()
                    + ") AND (ingredient_id, ingredients_date) IN (SELECT ingredient_id, MAX(ingredients_date) FROM ingredients GROUP BY ingredient_id)";
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

    public int getAmountOfIngredientInProduct(int productID, int ingredient_id) {
        try (Connection connection = DriverManager.getConnection(getURL(), getUser(), getPassword())) {
            String query = "SELECT ingredientQuantity FROM product_ingredients WHERE product_id = " + productID
                    + " AND ingredient_id = " + ingredient_id + " ORDER BY product_ingredients_date DESC LIMIT 1";
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

    public int addIngredient(int provID, String date, String name, String price, int amount, boolean in_inventory,
            boolean active) {
        try (Connection connection = DriverManager.getConnection(getURL(), getUser(), getPassword())) {
            int ingrID = getLastIngredientID() + 1;
            String query = "INSERT INTO ingredients VALUES (" + ingrID + ", " + provID + ", '" + date + "', '" + name
                    + "', " + price + ", " + amount + ", " + in_inventory + ", " + active + ");";
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

    public boolean addIngredientsToProduct(int productID, int ingredientID, String date, float quantity) {
        try (Connection connection = DriverManager.getConnection(getURL(), getUser(), getPassword())) {
            String query = "INSERT INTO product_ingredients VALUES (" + productID + ", " + ingredientID + ", '" + date
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

    public boolean editIngredient(int ingID, int provID, String date, String name, double price, int amount,
            boolean in_inventory, boolean active) {
        try (Connection connection = DriverManager.getConnection(getURL(), getUser(), getPassword())) {
            String query = "UPDATE ingredients SET provider_id = " + provID + ", ingredients_date = '" + date
                    + "', name = '" + name + "', price = " + price + ", amount = " + amount + ", in_inventory = "
                    + in_inventory + ", active = " + active + " WHERE provider_id = " + Integer.toString(ingID);
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

    public boolean ingredientSimpleEdit(ingredient theIngredient, String newName, boolean newInventory,
            boolean newActive) {
        try (Connection connection = DriverManager.getConnection(getURL(), getUser(), getPassword())) {
            String query = "UPDATE ingredients SET name = '" + newName + "', in_inventory = " + newInventory
                    + ", active = " + newActive + " WHERE ingredient_id = " + theIngredient.getId()
                    + " AND provider_id = "
                    + theIngredient.getProviderID() + " AND ingredients_date ='" + theIngredient.getDate() + "'";
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

    public boolean ingredientComplexIngredientEdit(ingredient theIngredient, int prov_id, int amount, float price) {
        if (theIngredient.getProviderID() == prov_id && theIngredient.getAmount() == amount
                && theIngredient.getPrice() == price)
            return false;
        LocalDate dateObj = LocalDate.now();
        String date = dateObj.format(DateTimeFormatter.ofPattern("yyyy-MM-dd"));
        if (theIngredient.getDate().equals(date)) {
            return updateComplexIngredient(theIngredient, prov_id, amount, price);
        }
        try (Connection connection = DriverManager.getConnection(getURL(), getUser(), getPassword())) {
            String query = "INSERT INTO ingredients VALUES (" + theIngredient.getId() + ", " + prov_id + ", '"
                    + date
                    + "', '" + theIngredient.getName()
                    + "', " + price + ", " + amount + ", " + theIngredient.getInInventory() + ", "
                    + theIngredient.getActive() + ");";
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

    public boolean updateComplexIngredient(ingredient theIngredient, int prov_id, int amount, float price) {
        try (Connection connection = DriverManager.getConnection(getURL(), getUser(), getPassword())) {
            String query = "UPDATE ingredients SET provider_id = " + prov_id
                    + ", amount = " + amount + ", price = " + price
                    + " WHERE ingredient_id = " + theIngredient.getId() + " AND provider_id = "
                    + theIngredient.getProviderID() + " AND ingredients_date = '" + theIngredient.getDate() + "';";
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

    // REMOVE "ingredient" from database.

}
