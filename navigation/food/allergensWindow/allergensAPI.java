package navigation.food.allergensWindow;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Stack;

import javax.swing.JOptionPane;

import componentsFood.allergen;
import componentsFood.ingredient;
import util.abstractManagerDB;

public class allergensAPI extends abstractManagerDB {

    // GET "allergen" objects from database.
    public ArrayList<allergen> getAllAllergens() {
        ArrayList<allergen> tempList = new ArrayList<allergen>();
        try (Connection connection = DriverManager.getConnection(getURL(), getUser(), getPassword())) {
            String query = "SELECT * FROM allergens";
            try (Statement stmt = connection.createStatement()) {
                ResultSet rs = stmt.executeQuery(query);
                while (rs.next()) {
                    int allergenID = rs.getInt("allergen_id");
                    String name = rs.getString("name");
                    tempList.add(new allergen(allergenID, name));
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

    public allergen getAllergen(int ID) {
        try (Connection connection = DriverManager.getConnection(getURL(), getUser(), getPassword())) {
            String query = "SELECT * FROM allergens WHERE allergen_id = " + ID;
            try (Statement stmt = connection.createStatement()) {
                ResultSet rs = stmt.executeQuery(query);
                if (rs.next()) {
                    int allergenID = rs.getInt("allergen_id");
                    String name = rs.getString("name");
                    allergen temp = new allergen(allergenID, name);
                    connection.close();
                    return temp;
                }
                connection.close();
                return null;
            } catch (Exception e) {
                System.out.println(e);
                return null;
            }
        } catch (SQLException e) {
            throw new IllegalStateException("Cannot connect the database!", e);
        }
    }

    public ArrayList<allergen> getSelectedAllergens(ingredient theIngredient) {
        ArrayList<allergen> tempList = new ArrayList<allergen>();
        try (Connection connection = DriverManager.getConnection(getURL(), getUser(), getPassword())) {
            String query = "SELECT allergen_id, name FROM ingredients_allergens NATURAL JOIN allergens WHERE ingredient_id = "
                    + theIngredient.getId();
            try (Statement stmt = connection.createStatement()) {
                ResultSet rs = stmt.executeQuery(query);
                while (rs.next()) {
                    int allergenID = rs.getInt("allergen_id");
                    String name = rs.getString("name");
                    tempList.add(new allergen(allergenID, name));
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

    public ArrayList<allergen> getNonSelectedAllergens(ingredient theIngredient) {
        ArrayList<allergen> tempList = new ArrayList<allergen>();
        try (Connection connection = DriverManager.getConnection(getURL(), getUser(), getPassword())) {
            String query = "SELECT allergen_id, name FROM allergens WHERE allergen_id NOT IN (SELECT allergen_id FROM ingredients_allergens WHERE ingredient_id = "
                    + theIngredient.getId() + ")";
            try (Statement stmt = connection.createStatement()) {
                ResultSet rs = stmt.executeQuery(query);
                while (rs.next()) {
                    int allergenID = rs.getInt("allergen_id");
                    String name = rs.getString("name");
                    tempList.add(new allergen(allergenID, name));
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

    public ArrayList<String> getAllergensOfIngredient(String ID) {
        Stack<Integer> allergenIDs = new Stack<Integer>();
        try (Connection connection = DriverManager.getConnection(getURL(), getUser(), getPassword())) {
            String query = "SELECT allergen_id FROM ingredients_allergens WHERE ingredient_id = " + ID;
            try (Statement stmt = connection.createStatement()) {
                ResultSet rs = stmt.executeQuery(query);
                while (rs.next())
                    allergenIDs.add(rs.getInt("allergen_id"));
            } catch (Exception e) {
                System.out.println(e);
                return null;
            }
            ArrayList<String> containingAllergenNames = new ArrayList<String>();
            while (!allergenIDs.isEmpty()) {
                query = "SELECT name FROM allergens WHERE allergen_id = " + allergenIDs.pop();
                try (Statement stmt = connection.createStatement()) {
                    ResultSet rs = stmt.executeQuery(query);
                    if (rs.next())
                        containingAllergenNames.add(rs.getString("name"));
                } catch (Exception e) {
                    System.out.println(e);
                    return null;
                }
            }
            return containingAllergenNames;
        } catch (SQLException e) {
            throw new IllegalStateException("Cannot connect the database!", e);
        }
    }

    // ADD "allergen" to database.
    public boolean addAllergen(String name) {
        try (Connection connection = DriverManager.getConnection(getURL(), getUser(), getPassword())) {
            String queryTest = "SELECT * FROM allergens WHERE name LIKE '%" + name + "%' LIMIT 1";
            try (Statement stmt1 = connection.createStatement()) {
                ResultSet rs = stmt1.executeQuery(queryTest);
                if (rs.next()) {
                    JOptionPane.showMessageDialog(null, "There is a similar entry with that name.", "Error",
                            JOptionPane.WARNING_MESSAGE);
                    return false;
                }
            }
            int allergenID = getLastAllergenID() + 1;
            String query = "INSERT INTO allergens VALUES (" + allergenID + ", '" + name + "');";
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

    public boolean addAlergensOfIngredient(Stack<allergen> stackSelected, int ingredientID) {
        try (Connection connection = DriverManager.getConnection(getURL(), getUser(), getPassword())) {
            while (!stackSelected.isEmpty()) {
                allergen temp = stackSelected.pop();
                String query = "INSERT INTO ingredients_allergens VALUES (" + ingredientID + ", '" + temp.getId()
                        + "');";
                try (Statement stmt = connection.createStatement()) {
                    stmt.executeUpdate(query);
                } catch (Exception a) {
                    System.out.println(a);
                    return false;
                }
            }
            return true;
        } catch (SQLException e) {
            System.out.println(e);
            return false;
        }
    }

    private int getLastAllergenID() {
        try (Connection connection = DriverManager.getConnection(getURL(), getUser(), getPassword())) {
            String query = "SELECT allergen_id FROM beatneat.allergens ORDER BY allergen_id DESC LIMIT 1;";
            try (Statement stmt = connection.createStatement()) {
                ResultSet rs = stmt.executeQuery(query);
                if (rs.next()) {
                    int allergenID = rs.getInt("allergen_id");
                    connection.close();
                    return allergenID;
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

    // UPDATE something "allergen" related in database.
    public boolean editAllergen(allergen theAllergen, String name) {
        try (Connection connection = DriverManager.getConnection(getURL(), getUser(), getPassword())) {
            String query = "UPDATE allergens SET name = '" + name + "' WHERE allergen_id = " + theAllergen.getId();
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

    public boolean editAlergensOfIngredient(Stack<allergen> stackSelected, int ingredientID) {
        try (Connection connection = DriverManager.getConnection(getURL(), getUser(), getPassword())) {
            String query = "DELETE FROM ingredients_allergens WHERE ingredient_id = " + ingredientID;
            try (Statement stmt = connection.createStatement()) {
                stmt.executeUpdate(query);
            } catch (Exception a) {
                System.out.println(a);
                return false;
            }
            return addAlergensOfIngredient(stackSelected, ingredientID);
        } catch (SQLException e) {
            System.out.println(e);
            return false;
        }
    }

    // REMOVE "allergen" from database.
    public boolean removeAllergen(allergen theAllergen) {
        try (Connection connection = DriverManager.getConnection(getURL(), getUser(), getPassword())) {
            String query = "DELETE FROM ingredients_allergens WHERE allergen_id = " + theAllergen.getId() + ";";
            try (Statement stmt = connection.createStatement()) {
                stmt.executeUpdate(query);
            } catch (Exception e) {
                return false;
            }
            query = "DELETE FROM allergens WHERE allergen_id = " + theAllergen.getId() + ";";
            try (Statement stmt = connection.createStatement()) {
                stmt.executeUpdate(query);
                connection.close();
                return true;
            } catch (Exception e) {
                return false;
            }
        } catch (Exception e) {
            return false;
        }
    }

}
