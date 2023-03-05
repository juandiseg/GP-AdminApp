package util.databaseAPIs;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Stack;

import componentsFood.allergen;
import componentsFood.ingredient;

public class allergensAPI extends abstractManagerDB {

    // GET from database.
    public allergen getAllergen(int ID) {
        try (Connection connection = DriverManager.getConnection(getURL(), getUser(), getPassword())) {
            String query = "SELECT * FROM allergens WHERE allergen_id = ?;";
            ppdStatement = connection.prepareStatement(query);
            ppdStatement.setInt(1, ID);
            try {
                ResultSet rs = ppdStatement.executeQuery();
                if (rs.next()) {
                    int allergenID = rs.getInt("allergen_id");
                    String name = rs.getString("name");
                    return new allergen(allergenID, name);
                }
                return null;
            } catch (Exception SQLTimeoutException) {
                return null;
            }
        } catch (SQLException e) {
            throw new IllegalStateException("Cannot connect the database!", e);
        }
    }

    public ArrayList<allergen> getAllAllergens() {
        ArrayList<allergen> tempList = new ArrayList<allergen>();
        try (Connection connection = DriverManager.getConnection(getURL(), getUser(), getPassword())) {
            String query = "SELECT * FROM allergens;";
            ppdStatement = connection.prepareStatement(query);
            try {
                ResultSet rs = ppdStatement.executeQuery();
                while (rs.next()) {
                    int allergenID = rs.getInt("allergen_id");
                    String name = rs.getString("name");
                    tempList.add(new allergen(allergenID, name));
                }
                return tempList;
            } catch (Exception SQLTimeoutException) {
                return null;
            }
        } catch (SQLException e) {
            throw new IllegalStateException("Cannot connect the database!", e);
        }
    }

    public ArrayList<allergen> getSelectedAllergens(ingredient theIngredient) {
        ArrayList<allergen> tempList = new ArrayList<allergen>();
        try (Connection connection = DriverManager.getConnection(getURL(), getUser(), getPassword())) {
            String query = "SELECT allergen_id, name FROM ingredients_allergens NATURAL JOIN allergens WHERE ingredient_id = ?;";
            ppdStatement = connection.prepareStatement(query);
            ppdStatement.setInt(1, theIngredient.getId());
            try {
                ResultSet rs = ppdStatement.executeQuery();
                while (rs.next()) {
                    int allergenID = rs.getInt("allergen_id");
                    String name = rs.getString("name");
                    tempList.add(new allergen(allergenID, name));
                }
                return tempList;
            } catch (Exception SQLTimeoutException) {
                return null;
            }
        } catch (SQLException e) {
            throw new IllegalStateException("Cannot connect the database!", e);
        }
    }

    public ArrayList<allergen> getNonSelectedAllergens(ingredient theIngredient) {
        ArrayList<allergen> tempList = new ArrayList<allergen>();
        try (Connection connection = DriverManager.getConnection(getURL(), getUser(), getPassword())) {
            String query = "SELECT allergen_id, name FROM allergens WHERE allergen_id NOT IN (SELECT allergen_id FROM ingredients_allergens WHERE ingredient_id = ?);";
            ppdStatement = connection.prepareStatement(query);
            ppdStatement.setInt(1, theIngredient.getId());
            try {
                ResultSet rs = ppdStatement.executeQuery();
                while (rs.next()) {
                    int allergenID = rs.getInt("allergen_id");
                    String name = rs.getString("name");
                    tempList.add(new allergen(allergenID, name));
                }
                return tempList;
            } catch (Exception SQLTimeoutException) {
                return null;
            }
        } catch (SQLException e) {
            throw new IllegalStateException("Cannot connect the database!", e);
        }
    }

    private int getLastAllergenID() {
        try (Connection connection = DriverManager.getConnection(getURL(), getUser(), getPassword())) {
            String query = "SELECT allergen_id FROM allergens ORDER BY allergen_id DESC LIMIT 1;";
            ppdStatement = connection.prepareStatement(query);
            try {
                ResultSet rs = ppdStatement.executeQuery();
                if (rs.next())
                    return rs.getInt("allergen_id");
                return -1;
            } catch (Exception SQLTimeoutException) {
                return -1;
            }
        } catch (SQLException e) {
            throw new IllegalStateException("Cannot connect the database!", e);
        }
    }

    // ADD to database.
    public boolean addAllergen(String name) {
        try (Connection connection = DriverManager.getConnection(getURL(), getUser(), getPassword())) {
            int allergenID = getLastAllergenID() + 1;
            String query = "INSERT INTO allergens VALUES (?, ?);";
            ppdStatement = connection.prepareStatement(query);
            ppdStatement.setInt(1, allergenID);
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

    public boolean addAlergensOfIngredient(Stack<Integer> allergenIDs, int ingredientID) {
        try (Connection connection = DriverManager.getConnection(getURL(), getUser(), getPassword())) {
            String query = "INSERT INTO ingredients_allergens VALUES (?, ?);";
            ppdStatement = connection.prepareStatement(query);
            while (!allergenIDs.isEmpty()) {
                ppdStatement.setInt(1, ingredientID);
                ppdStatement.setInt(2, allergenIDs.pop());
                try {
                    ppdStatement.executeUpdate();
                } catch (Exception SQLTimeoutException) {
                    return false;
                }
            }
            return true;
        } catch (SQLException e) {
            return false;
        }
    }

    // UPDATE in database.
    public boolean updateName(allergen theAllergen, String name) {
        try (Connection connection = DriverManager.getConnection(getURL(), getUser(), getPassword())) {
            String query = "UPDATE allergens SET name = ? WHERE allergen_id = ?;";
            ppdStatement = connection.prepareStatement(query);
            ppdStatement.setString(1, name);
            ppdStatement.setInt(2, theAllergen.getId());
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

    public boolean updateAlergensOfIngredient(Stack<Integer> allergenIDs, ingredient theIngredient) {
        try (Connection connection = DriverManager.getConnection(getURL(), getUser(), getPassword())) {
            String query = "DELETE FROM ingredients_allergens WHERE ingredient_id = ?;";
            ppdStatement = connection.prepareStatement(query);
            ppdStatement.setInt(1, theIngredient.getId());
            try {
                ppdStatement.executeUpdate();
            } catch (Exception SQLTimeoutException) {
                return false;
            }
            return addAlergensOfIngredient(allergenIDs, theIngredient.getId());
        } catch (SQLException e) {
            return false;
        }
    }

    // REMOVE from database.
    public boolean removeAllergen(allergen theAllergen) {
        try (Connection connection = DriverManager.getConnection(getURL(), getUser(), getPassword())) {
            String query = "DELETE FROM ingredients_allergens WHERE allergen_id = ?;";
            ppdStatement = connection.prepareStatement(query);
            ppdStatement.setInt(1, theAllergen.getId());
            try {
                ppdStatement.executeUpdate();
            } catch (Exception SQLTimeoutException) {
                return false;
            }
            query = "DELETE FROM allergens WHERE allergen_id = ?;";
            ppdStatement = connection.prepareStatement(query);
            ppdStatement.setInt(1, theAllergen.getId());
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
    public boolean isNameTaken(String name) {
        try (Connection connection = DriverManager.getConnection(getURL(), getUser(), getPassword())) {
            String query = "SELECT * FROM allergens WHERE category_name = ?";
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