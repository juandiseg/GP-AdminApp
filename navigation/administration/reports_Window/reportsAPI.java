package navigation.administration.reports_Window;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import componentsFood.employee;
import componentsFood.ingredient;
import componentsFood.ingredientsProviders;
import componentsFood.menu;
import componentsFood.product;
import componentsFood.productIngredients;
import componentsFood.shift;
import util.abstractManagerDB;

public class reportsAPI extends abstractManagerDB {

    public ArrayList<ArrayList<product>> getAllProducts() {
        ArrayList<ArrayList<product>> listOfLists = new ArrayList<ArrayList<product>>();
        try (Connection connection = DriverManager.getConnection(getURL(), getUser(), getPassword())) {
            String query = "SELECT * FROM products ORDER BY product_id";
            try (Statement stmt = connection.createStatement()) {
                ResultSet rs = stmt.executeQuery(query);
                ArrayList<product> temp = new ArrayList<>();
                int lastID = -1;
                while (rs.next()) {
                    int ID = rs.getInt("product_id");
                    int catID = rs.getInt("category_id");
                    String date = rs.getString("product_date");
                    String name = rs.getString("name");
                    float price = rs.getFloat("price");
                    boolean active = rs.getBoolean("active");
                    if (lastID == ID) {
                        temp.add(new product(ID, catID, date, name, price, active));
                    } else {
                        if (!temp.isEmpty())
                            listOfLists.add(temp);
                        temp = new ArrayList<product>();
                        lastID = ID;
                        temp.add(new product(ID, catID, date, name, price, active));
                    }
                }
                if (!temp.isEmpty())
                    listOfLists.add(temp);
                connection.close();
                return listOfLists;
            } catch (Exception e) {
                System.out.println(e);
                return listOfLists;
            }
        } catch (SQLException e) {
            throw new IllegalStateException("Cannot connect the database!", e);
        }
    }

    public ArrayList<ArrayList<menu>> getAllMenus() {
        ArrayList<ArrayList<menu>> listOfLists = new ArrayList<ArrayList<menu>>();
        try (Connection connection = DriverManager.getConnection(getURL(), getUser(), getPassword())) {
            String query = "SELECT * FROM menus ORDER BY menu_id";
            try (Statement stmt = connection.createStatement()) {
                ResultSet rs = stmt.executeQuery(query);
                ArrayList<menu> temp = new ArrayList<>();
                int lastID = -1;
                while (rs.next()) {
                    int ID = rs.getInt("menu_id");
                    int catID = rs.getInt("category_id");
                    String date = rs.getString("menu_date");
                    String name = rs.getString("name");
                    float price = rs.getFloat("price");
                    boolean active = rs.getBoolean("active");
                    if (lastID == ID) {
                        temp.add(new menu(ID, catID, date, name, price, active));
                    } else {
                        if (!temp.isEmpty())
                            listOfLists.add(temp);
                        temp = new ArrayList<menu>();
                        lastID = ID;
                        temp.add(new menu(ID, catID, date, name, price, active));
                    }
                }
                if (!temp.isEmpty())
                    listOfLists.add(temp);
                connection.close();
                return listOfLists;
            } catch (Exception e) {
                System.out.println(e);
                return listOfLists;
            }
        } catch (SQLException e) {
            throw new IllegalStateException("Cannot connect the database!", e);
        }
    }

    public int getNumberSoldProduct(product current, product next, String from, String to) {
        try (Connection connection = DriverManager.getConnection(getURL(), getUser(), getPassword())) {
            String query = "SELECT SUM(quantity) FROM orders_items NATURAL JOIN orders_summary NATURAL JOIN products WHERE date >= '"
                    + current.getDate() + "'";
            if (next != null) {
                query = query.concat(" AND date < '" + next.getDate() + "'");
            }
            query = query.concat(" AND date >= '" + from + "' AND date <= '" + to + "' AND product_id = "
                    + current.getId() + " AND product_date = '" + current.getDate() + "'");
            try (Statement stmt = connection.createStatement()) {
                ResultSet rs = stmt.executeQuery(query);
                int amount = 0;
                if (rs.next()) {
                    amount = rs.getInt("SUM(quantity)");
                }
                connection.close();
                return amount;
            } catch (Exception e) {
                System.out.println(e);
                return -1;
            }
        } catch (SQLException e) {
            throw new IllegalStateException("Cannot connect the database!", e);
        }
    }

    public String getNameOfProduct(int prodID, String date) {
        String name = "";
        try (Connection connection = DriverManager.getConnection(getURL(), getUser(), getPassword())) {
            String query = "SELECT name FROM products WHERE product_id = " + prodID + " AND product_date = '" + date
                    + "'";
            try (Statement stmt = connection.createStatement()) {
                ResultSet rs = stmt.executeQuery(query);
                if (rs.next()) {
                    name = rs.getString("name");
                    connection.close();
                }
            } catch (Exception e) {
                System.out.println(e);
                return "";
            }
        } catch (SQLException e) {
            throw new IllegalStateException("Cannot connect the database!", e);
        }
        return name;
    }

    public int getNumberSoldProduct(productIngredients product, String nextDate, String from, String to) {
        try (Connection connection = DriverManager.getConnection(getURL(), getUser(), getPassword())) {
            String query = "SELECT SUM(quantity) FROM orders_items NATURAL JOIN orders_summary WHERE";
            if (!nextDate.equals(""))
                query = query.concat(" date BETWEEN '" + product.getProductDate() + "' AND '" + nextDate + "'");
            else
                query = query.concat(" date >= '" + product.getProductDate() + "'");
            query = query.concat(
                    " AND date BETWEEN '" + from + "' AND '" + to + "' AND product_id = " + product.getProductID());
            try (Statement stmt = connection.createStatement()) {
                ResultSet rs = stmt.executeQuery(query);
                int amount = 0;
                if (rs.next())
                    amount = rs.getInt("SUM(quantity)");
                connection.close();
                return amount;
            } catch (Exception e) {
                System.out.println(e);
                return -1;
            }
        } catch (SQLException e) {
            throw new IllegalStateException("Cannot connect the database!", e);
        }
    }

    public int getNumberSoldMenu(menu current, menu next, String from, String to) {
        try (Connection connection = DriverManager.getConnection(getURL(), getUser(), getPassword())) {
            String query = "SELECT SUM(quantity) FROM orders_menus NATURAL JOIN orders_summary NATURAL JOIN menus WHERE date >= '"
                    + current.getDate() + "'";
            if (next != null) {
                query = query.concat(" AND date < '" + next.getDate() + "'");
            }
            query = query.concat(" AND date >= '" + from + "' AND date <= '" + to + "' AND menu_id = " + current.getId()
                    + " AND menu_date = '" + current.getDate() + "'");
            try (Statement stmt = connection.createStatement()) {
                ResultSet rs = stmt.executeQuery(query);
                int amount = 0;
                if (rs.next()) {
                    amount = rs.getInt("SUM(quantity)");
                }
                connection.close();
                return amount;
            } catch (Exception e) {
                System.out.println(e);
                return -1;
            }
        } catch (SQLException e) {
            throw new IllegalStateException("Cannot connect the database!", e);
        }
    }

    public ArrayList<ArrayList<productIngredients>> getAllProductIngredientsFromProducts() {
        ArrayList<ArrayList<productIngredients>> listOfLists = new ArrayList<ArrayList<productIngredients>>();
        try (Connection connection = DriverManager.getConnection(getURL(), getUser(), getPassword())) {
            String query = "SELECT DISTINCT product_id, product_date, product_ingredients_date FROM products_ingredients NATURAL JOIN products ORDER BY product_id, product_ingredients_date;";
            try (Statement stmt = connection.createStatement()) {
                ResultSet rs = stmt.executeQuery(query);
                ArrayList<productIngredients> tempList = new ArrayList<>();
                int lastID = -1;
                while (rs.next()) {
                    int ID = rs.getInt("product_id");
                    String ingredientsDate = rs.getString("product_ingredients_date");
                    String productDate = rs.getString("product_date");
                    if (lastID == ID) {
                        tempList.add(new productIngredients(ID, productDate, ingredientsDate));
                    } else {
                        if (!tempList.isEmpty())
                            listOfLists.add(tempList);
                        tempList = new ArrayList<productIngredients>();
                        lastID = ID;
                        tempList.add(new productIngredients(ID, productDate, ingredientsDate));
                    }
                }
                if (!tempList.isEmpty())
                    listOfLists.add(tempList);
                connection.close();
                return listOfLists;
            } catch (Exception e) {
                System.out.println(e);
                return listOfLists;
            }
        } catch (SQLException e) {
            throw new IllegalStateException("Cannot connect the database!", e);
        }
    }

    public ArrayList<ArrayList<productIngredients>> getAllProductIngredientsFromMenus(String from,
            String to) {
        ArrayList<ArrayList<productIngredients>> listOfLists = new ArrayList<ArrayList<productIngredients>>();
        try (Connection connection = DriverManager.getConnection(getURL(), getUser(), getPassword())) {
            String query = "SELECT MAX(product_ingredients_date) AS dateIngredients, ssquery.* FROM products_ingredients AS query, (SELECT product_id, menu_products_date AS dateProducts, SUM(productQuantity*quantity) AS totalOrdered FROM menus_products AS mp, (SELECT date, menu_id, SUM(quantity) AS quantity FROM orders_summary NATURAL JOIN orders_menus WHERE date >= '"
                    + from + "' AND date <= '" + to
                    + "' GROUP BY date, menu_id) AS subq WHERE mp.menu_products_date <= subq.date AND subq.menu_id = mp.menu_id GROUP BY product_id, dateProducts ORDER BY product_id) AS ssquery WHERE product_ingredients_date <= ssquery.dateProducts AND query.product_id = ssquery.product_id GROUP BY ssquery.product_id, ssquery.dateProducts, ssquery.totalOrdered";
            try (Statement stmt = connection.createStatement()) {
                ResultSet rs = stmt.executeQuery(query);
                ArrayList<productIngredients> tempList = new ArrayList<>();
                int lastID = -1;
                while (rs.next()) {
                    int ID = rs.getInt("product_id");
                    String ingredientsDate = rs.getString("dateIngredients");
                    String productDate = rs.getString("dateProducts");
                    if (lastID == ID) {
                        productIngredients temp = new productIngredients(ID, productDate, ingredientsDate);
                        temp.setNumberSoldMenus(rs.getInt("totalOrdered"));
                        tempList.add(temp);
                    } else {
                        if (!tempList.isEmpty())
                            listOfLists.add(tempList);
                        tempList = new ArrayList<productIngredients>();
                        lastID = ID;
                        productIngredients temp = new productIngredients(ID, productDate, ingredientsDate);
                        temp.setNumberSoldMenus(rs.getInt("totalOrdered"));
                        tempList.add(temp);
                    }
                }
                if (!tempList.isEmpty())
                    listOfLists.add(tempList);
                connection.close();
                return listOfLists;
            } catch (Exception e) {
                System.out.println(e);
                return listOfLists;
            }
        } catch (SQLException e) {
            throw new IllegalStateException("Cannot connect the database!", e);
        }
    }

    public void addIngredientsToProductIngredients(productIngredients productIngr) {
        try (Connection connection = DriverManager.getConnection(getURL(), getUser(), getPassword())) {
            String query = "SELECT ingredients.*, subsubq.ingredientQuantity FROM ingredients, (SELECT subq.ingredient_id, MAX(ingredients_date) AS dates, subq.ingredientQuantity FROM (SELECT ingredient_id, ingredientQuantity FROM products_ingredients WHERE product_id = "
                    + productIngr.getProductID()
                    + " AND product_ingredients_date = '" + productIngr.getIngredientsDate()
                    + "') AS subq LEFT JOIN ingredients ON subq.ingredient_id = ingredients.ingredient_id WHERE ingredients_date <= '"
                    + productIngr.getIngredientsDate()
                    + "' GROUP BY ingredients.ingredient_id) AS subsubq WHERE subsubq.ingredient_id = ingredients.ingredient_id AND subsubq.dates = ingredients_date;";
            try (Statement stmt = connection.createStatement()) {
                ResultSet rs = stmt.executeQuery(query);
                while (rs.next()) {
                    int ID = rs.getInt("ingredient_id");
                    int provID = rs.getInt("provider_id");
                    String date = rs.getString("ingredients_date");
                    String name = rs.getString("name");
                    Float price = rs.getFloat("price");
                    Float amount = rs.getFloat("amount");
                    Boolean inventory = rs.getBoolean("in_inventory");
                    Boolean active = rs.getBoolean("active");
                    ingredient temp = new ingredient(ID, provID, date, name, price, amount, inventory, active);
                    productIngr.addIngredient(temp);
                    productIngr.addQuantity(rs.getFloat("ingredientQuantity"));
                }
                connection.close();
            } catch (Exception e) {
                System.out.println(e);
            }
        } catch (SQLException e) {
            throw new IllegalStateException("Cannot connect the database!", e);
        }
    }

    public ArrayList<ingredientsProviders> getAllProviders() {
        ArrayList<ingredientsProviders> tempList = new ArrayList<ingredientsProviders>();
        try (Connection connection = DriverManager.getConnection(getURL(), getUser(), getPassword())) {
            String query = "SELECT provider_id, name FROM providers";
            try (Statement stmt = connection.createStatement()) {
                ResultSet rs = stmt.executeQuery(query);
                while (rs.next()) {
                    int providerID = rs.getInt("provider_id");
                    String name = rs.getString("name");
                    tempList.add(new ingredientsProviders(providerID, name));
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

    public ArrayList<employee> getAllEmployeesAndShifts(String from, String to) {
        ArrayList<employee> tempList = new ArrayList<employee>();
        try (Connection connection = DriverManager.getConnection(getURL(), getUser(), getPassword())) {
            String query = "SELECT * FROM employees_schedule NATURAL JOIN employees WHERE shift_date >= '" + from
                    + "' AND shift_date <= '" + to + "' ORDER BY employee_id, shift_date, start_shift, role_id;";
            try (Statement stmt = connection.createStatement()) {
                ResultSet rs = stmt.executeQuery(query);
                employee temp = new employee(-1, "", -99, "", -99);
                while (rs.next()) {
                    if (rs.getInt("employee_id") != temp.getId()) {
                        if (temp.getId() != -1) {
                            tempList.add(temp);
                        }
                        int employeeID = rs.getInt("employee_id");
                        String name = rs.getString("name");
                        Float salary = rs.getFloat("salary");
                        String hoursWeek = rs.getString("hours_a_week");
                        int roleID = rs.getInt("role_id");
                        temp = new employee(employeeID, name, salary, hoursWeek, roleID);
                    }
                    String date = rs.getString("shift_date");
                    String startTime = rs.getString("start_shift");
                    String endTime = rs.getString("end_shift");
                    temp.addShift(new shift(temp.getId(), date, startTime, endTime));
                }
                if (temp.getId() != -1)
                    tempList.add(temp);
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

    public String getRoleName(int roleID) {
        String name = "";
        try (Connection connection = DriverManager.getConnection(getURL(), getUser(), getPassword())) {
            String query = "SELECT role_name FROM roles WHERE role_id = " + roleID;
            try (Statement stmt = connection.createStatement()) {
                ResultSet rs = stmt.executeQuery(query);
                if (rs.next()) {
                    name = rs.getString("role_name");
                    connection.close();
                }
            } catch (Exception e) {
                System.out.println(e);
                return name;
            }
        } catch (SQLException e) {
            throw new IllegalStateException("Cannot connect the database!", e);
        }
        return name;
    }
}
