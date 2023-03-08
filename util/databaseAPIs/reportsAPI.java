package util.databaseAPIs;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import componentsFood.employee;
import componentsFood.ingredient;
import componentsFood.ingredientsProviders;
import componentsFood.menu;
import componentsFood.product;
import componentsFood.productIngredients;
import componentsFood.shift;

public class reportsAPI extends abstractManagerDB {

    // GET from database.
    public static ArrayList<ArrayList<product>> getAllProducts() {
        ArrayList<ArrayList<product>> listOfLists = new ArrayList<ArrayList<product>>();
        try (Connection connection = DriverManager.getConnection(getURL(), getUser(), getPassword())) {
            String query = "SELECT * FROM products ORDER BY product_id;";
            ppdStatement = connection.prepareStatement(query);
            try {
                ResultSet rs = ppdStatement.executeQuery();
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
                        temp.add(new product(ID, catID, dateInverter.invert(date), name, price, active));
                    } else {
                        if (!temp.isEmpty())
                            listOfLists.add(temp);
                        temp = new ArrayList<product>();
                        lastID = ID;
                        temp.add(new product(ID, catID, dateInverter.invert(date), name, price, active));
                    }
                }
                if (!temp.isEmpty())
                    listOfLists.add(temp);
                return listOfLists;
            } catch (Exception SQLTimeoutException) {
                return null;
            }
        } catch (SQLException e) {
            throw new IllegalStateException("Cannot connect the database!", e);
        }
    }

    public static String getNameOfProduct(int prodID, String date) {
        date = dateInverter.invert(date);
        try (Connection connection = DriverManager.getConnection(getURL(), getUser(), getPassword())) {
            String query = "SELECT name FROM products WHERE product_id = ? AND product_date = ?";
            ppdStatement = connection.prepareStatement(query);
            ppdStatement.setInt(1, prodID);
            ppdStatement.setString(2, date);
            try {
                ResultSet rs = ppdStatement.executeQuery();
                if (rs.next())
                    return rs.getString("name");
                return "";
            } catch (Exception SQLTimeoutException) {
                return "";
            }
        } catch (SQLException e) {
            throw new IllegalStateException("Cannot connect the database!", e);
        }
    }

    public static ArrayList<ArrayList<menu>> getAllMenus() {
        ArrayList<ArrayList<menu>> listOfLists = new ArrayList<ArrayList<menu>>();
        try (Connection connection = DriverManager.getConnection(getURL(), getUser(), getPassword())) {
            String query = "SELECT * FROM menus ORDER BY menu_id;";
            ppdStatement = connection.prepareStatement(query);
            try {
                ResultSet rs = ppdStatement.executeQuery();
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
                        temp.add(new menu(ID, catID, dateInverter.invert(date), name, price, active));
                    } else {
                        if (!temp.isEmpty())
                            listOfLists.add(temp);
                        temp = new ArrayList<menu>();
                        lastID = ID;
                        temp.add(new menu(ID, catID, dateInverter.invert(date), name, price, active));
                    }
                }
                if (!temp.isEmpty())
                    listOfLists.add(temp);
                return listOfLists;
            } catch (Exception SQLTimeoutException) {
                return null;
            }
        } catch (SQLException e) {
            throw new IllegalStateException("Cannot connect the database!", e);
        }
    }

    public static int getNumberSoldProducts(product current, product next, String from, String to) {
        from = dateInverter.invert(from);
        to = dateInverter.invert(to);
        try (Connection connection = DriverManager.getConnection(getURL(), getUser(), getPassword())) {
            String query = "SELECT SUM(quantity) FROM orders_items NATURAL JOIN orders_summary NATURAL JOIN products WHERE date >= ?";
            if (next != null)
                query = query.concat(" AND date < ?");
            query = query.concat(" AND date >= ? AND date <= ? AND product_id = ? AND product_date = ?");

            ppdStatement = connection.prepareStatement(query);
            ppdStatement.setString(1, dateInverter.invert(current.getDate()));
            if (next != null) {
                ppdStatement.setString(2, dateInverter.invert(next.getDate()));
                ppdStatement.setString(3, from);
                ppdStatement.setString(4, to);
                ppdStatement.setInt(5, current.getId());
                ppdStatement.setString(6, dateInverter.invert(current.getDate()));
            } else {
                ppdStatement.setString(2, from);
                ppdStatement.setString(3, to);
                ppdStatement.setInt(4, current.getId());
                ppdStatement.setString(5, dateInverter.invert(current.getDate()));
            }
            try {
                ResultSet rs = ppdStatement.executeQuery();
                if (rs.next()) {
                    return rs.getInt("SUM(quantity)");
                }
                return -1;
            } catch (Exception SQLTimeoutException) {
                return -1;
            }
        } catch (SQLException e) {
            throw new IllegalStateException("Cannot connect the database!", e);
        }
    }

    public static int getNumberSoldMenus(menu current, menu next, String from, String to) {
        from = dateInverter.invert(from);
        to = dateInverter.invert(to);
        try (Connection connection = DriverManager.getConnection(getURL(), getUser(), getPassword())) {
            String query = "SELECT SUM(quantity) FROM orders_menus NATURAL JOIN orders_summary NATURAL JOIN menus WHERE date >= ?";
            if (next != null)
                query = query.concat(" AND date < ?");
            query = query.concat(" AND date >= ? AND date <= ? AND menu_id = ? AND menu_date = ?;");

            ppdStatement = connection.prepareStatement(query);
            ppdStatement.setString(1, dateInverter.invert(current.getDate()));
            if (next != null) {
                ppdStatement.setString(2, dateInverter.invert(next.getDate()));
                ppdStatement.setString(3, from);
                ppdStatement.setString(4, to);
                ppdStatement.setInt(5, current.getId());
                ppdStatement.setString(6, dateInverter.invert(current.getDate()));
            } else {
                ppdStatement.setString(2, from);
                ppdStatement.setString(3, to);
                ppdStatement.setInt(4, current.getId());
                ppdStatement.setString(5, dateInverter.invert(current.getDate()));
            }
            try {
                ResultSet rs = ppdStatement.executeQuery();
                if (rs.next())
                    return rs.getInt("SUM(quantity)");
                return -1;
            } catch (Exception SQLTimeoutException) {
                return -1;
            }
        } catch (SQLException e) {
            throw new IllegalStateException("Cannot connect the database!", e);
        }
    }

    public static ArrayList<ArrayList<productIngredients>> getAllProductIngredientsFromProducts() {
        ArrayList<ArrayList<productIngredients>> listOfLists = new ArrayList<ArrayList<productIngredients>>();
        try (Connection connection = DriverManager.getConnection(getURL(), getUser(), getPassword())) {
            String query = "SELECT DISTINCT product_id, product_date, product_ingredients_date FROM products_ingredients NATURAL JOIN products ORDER BY product_id, product_ingredients_date;";
            ppdStatement = connection.prepareStatement(query);
            try {
                ResultSet rs = ppdStatement.executeQuery();
                ArrayList<productIngredients> tempList = new ArrayList<>();
                int lastID = -1;
                while (rs.next()) {
                    int ID = rs.getInt("product_id");
                    String ingredientsDate = rs.getString("product_ingredients_date");
                    String productDate = rs.getString("product_date");
                    if (lastID == ID) {
                        tempList.add(new productIngredients(ID, dateInverter.invert(productDate), ingredientsDate));
                    } else {
                        if (!tempList.isEmpty())
                            listOfLists.add(tempList);
                        tempList = new ArrayList<productIngredients>();
                        lastID = ID;
                        tempList.add(new productIngredients(ID, dateInverter.invert(productDate), ingredientsDate));
                    }
                }
                if (!tempList.isEmpty())
                    listOfLists.add(tempList);
                return listOfLists;
            } catch (Exception SQLTimeoutException) {
                return null;
            }
        } catch (SQLException e) {
            throw new IllegalStateException("Cannot connect the database!", e);
        }
    }

    public static ArrayList<ArrayList<productIngredients>> getAllProductIngredientsFromMenus(String from,
            String to) {
        ArrayList<ArrayList<productIngredients>> listOfLists = new ArrayList<ArrayList<productIngredients>>();
        from = dateInverter.invert(from);
        to = dateInverter.invert(to);
        try (Connection connection = DriverManager.getConnection(getURL(), getUser(), getPassword())) {
            String query = "SELECT MAX(product_ingredients_date) AS dateIngredients, ssquery.* FROM products_ingredients AS query, (SELECT product_id, menu_products_date AS dateProducts, SUM(productQuantity*quantity) AS totalOrdered FROM menus_products AS mp, (SELECT date, menu_id, SUM(quantity) AS quantity FROM orders_summary NATURAL JOIN orders_menus WHERE date >= ? AND date <= ? GROUP BY date, menu_id) AS subq WHERE mp.menu_products_date <= subq.date AND subq.menu_id = mp.menu_id GROUP BY product_id, dateProducts ORDER BY product_id) AS ssquery WHERE product_ingredients_date <= ssquery.dateProducts AND query.product_id = ssquery.product_id GROUP BY ssquery.product_id, ssquery.dateProducts, ssquery.totalOrdered;";
            ppdStatement = connection.prepareStatement(query);
            ppdStatement.setString(1, from);
            ppdStatement.setString(2, to);
            try {
                ResultSet rs = ppdStatement.executeQuery();
                ArrayList<productIngredients> tempList = new ArrayList<>();
                int lastID = -1;
                while (rs.next()) {
                    int ID = rs.getInt("product_id");
                    String ingredientsDate = rs.getString("dateIngredients");
                    String productDate = rs.getString("dateProducts");
                    if (lastID == ID) {
                        productIngredients temp = new productIngredients(ID, dateInverter.invert(productDate),
                                dateInverter.invert(ingredientsDate));
                        temp.setNumberSoldMenus(rs.getInt("totalOrdered"));
                        tempList.add(temp);
                    } else {
                        if (!tempList.isEmpty())
                            listOfLists.add(tempList);
                        tempList = new ArrayList<productIngredients>();
                        lastID = ID;
                        productIngredients temp = new productIngredients(ID, dateInverter.invert(productDate),
                                dateInverter.invert(ingredientsDate));
                        temp.setNumberSoldMenus(rs.getInt("totalOrdered"));
                        tempList.add(temp);
                    }
                }
                if (!tempList.isEmpty())
                    listOfLists.add(tempList);
                return listOfLists;
            } catch (Exception SQLTimeoutException) {
                return null;
            }
        } catch (SQLException e) {
            throw new IllegalStateException("Cannot connect the database!", e);
        }
    }

    public static int getNumberSoldOfProductIngr(productIngredients product, String nextDate, String from, String to) {
        from = dateInverter.invert(from);
        to = dateInverter.invert(to);
        try (Connection connection = DriverManager.getConnection(getURL(), getUser(), getPassword())) {
            String query = "SELECT SUM(quantity) FROM orders_items NATURAL JOIN orders_summary WHERE";
            if (!nextDate.equals(""))
                query = query.concat(" date BETWEEN ? AND ?");
            else
                query = query.concat(" date >= ?");
            query = query.concat(
                    " AND date BETWEEN ? AND ? AND product_id = ?;");
            ppdStatement = connection.prepareStatement(query);
            if (!nextDate.equals("")) {
                // possible dateInverter.invert( for product.getproductdate
                ppdStatement.setString(1, product.getProductDate());
                ppdStatement.setString(2, nextDate);
                ppdStatement.setString(3, from);
                ppdStatement.setString(4, to);
                ppdStatement.setInt(5, product.getProductID());
            } else {
                ppdStatement.setString(1, product.getProductDate());
                ppdStatement.setString(2, from);
                ppdStatement.setString(3, to);
                ppdStatement.setInt(4, product.getProductID());
            }
            try {
                ResultSet rs = ppdStatement.executeQuery();
                if (rs.next())
                    return rs.getInt("SUM(quantity)");
                return -1;
            } catch (Exception SQLTimeoutException) {
                return -1;
            }
        } catch (SQLException e) {
            throw new IllegalStateException("Cannot connect the database!", e);
        }
    }

    public static void addIngredientsToProductIngredients(productIngredients productIngr) {
        try (Connection connection = DriverManager.getConnection(getURL(), getUser(), getPassword())) {
            String query = "SELECT ingredients.*, subsubq.ingredientQuantity FROM ingredients, (SELECT subq.ingredient_id, MAX(ingredients_date) AS dates, subq.ingredientQuantity FROM (SELECT ingredient_id, ingredientQuantity FROM products_ingredients WHERE product_id = ? AND product_ingredients_date = ?) AS subq LEFT JOIN ingredients ON subq.ingredient_id = ingredients.ingredient_id WHERE ingredients_date <= ? GROUP BY ingredients.ingredient_id) AS subsubq WHERE subsubq.ingredient_id = ingredients.ingredient_id AND subsubq.dates = ingredients_date;";
            ppdStatement = connection.prepareStatement(query);
            ppdStatement.setInt(1, productIngr.getProductID());
            ppdStatement.setString(2, productIngr.getIngredientsDate());
            ppdStatement.setString(3, productIngr.getIngredientsDate());
            try {
                ResultSet rs = ppdStatement.executeQuery();
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
            } catch (Exception SQLTimeoutException) {
            }
        } catch (SQLException e) {
            throw new IllegalStateException("Cannot connect the database!", e);
        }
    }

    public static ArrayList<ingredientsProviders> getAllProviders() {
        ArrayList<ingredientsProviders> tempList = new ArrayList<ingredientsProviders>();
        try (Connection connection = DriverManager.getConnection(getURL(), getUser(), getPassword())) {
            String query = "SELECT provider_id, name FROM providers;";
            ppdStatement = connection.prepareStatement(query);
            try {
                ResultSet rs = ppdStatement.executeQuery();
                while (rs.next()) {
                    int providerID = rs.getInt("provider_id");
                    String name = rs.getString("name");
                    tempList.add(new ingredientsProviders(providerID, name));
                }
                return tempList;
            } catch (Exception SQLTimeoutException) {
                return null;
            }
        } catch (SQLException e) {
            throw new IllegalStateException("Cannot connect the database!", e);
        }
    }

    public static ArrayList<employee> getAllEmployeesAndShifts(String from, String to) {
        ArrayList<employee> tempList = new ArrayList<employee>();
        from = dateInverter.invert(from);
        to = dateInverter.invert(to);
        try (Connection connection = DriverManager.getConnection(getURL(), getUser(), getPassword())) {
            String query = "SELECT * FROM employees_schedule NATURAL JOIN employees WHERE shift_date >= ? AND shift_date <= ? ORDER BY employee_id, shift_date, start_shift, role_id;";
            ppdStatement = connection.prepareStatement(query);
            ppdStatement.setString(1, from);
            ppdStatement.setString(2, to);
            try {
                ResultSet rs = ppdStatement.executeQuery();
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
                    temp.addShift(new shift(temp.getId(), dateInverter.invert(date), startTime, endTime));
                }
                if (temp.getId() != -1)
                    tempList.add(temp);
                return tempList;
            } catch (Exception SQLTimeoutException) {
                return null;
            }
        } catch (SQLException e) {
            throw new IllegalStateException("Cannot connect the database!", e);
        }
    }

}