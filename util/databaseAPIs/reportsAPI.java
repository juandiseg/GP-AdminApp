package util.databaseAPIs;

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
import componentsFood.menuProducts;
import componentsFood.product;
import componentsFood.productIngredients;
import componentsFood.shift;

public class reportsAPI extends abstractManagerDB {

    public static ArrayList<provSales> getSoldPerProvider(String from, String to) {
        ArrayList<ArrayList<productIngredients>> generateProductExpenses;

        return null;

    }

    public class provSales {

        private String providerName;
        private int sales;

        provSales(String name, int sales) {

        }
    }

    public static ArrayList<ArrayList<productIngredients>> generateProductExpenses(String from, String to) {
        ArrayList<ArrayList<productIngredients>> productIngredientsLists = getAllProductIngredientsFromProducts();
        for (ArrayList<productIngredients> bigTemp : productIngredientsLists) {
            for (int i = 0; i < bigTemp.size(); i++) {
                productIngredients temp = bigTemp.get(i);
                addIngredientsToProductIngredients(temp);
                String nextDate = "";
                if (i + 1 < bigTemp.size())
                    nextDate = bigTemp.get(i + 1).getProductDate();
                temp.setNumberSoldProducts(getNumberSoldOfProductIngr(temp, nextDate, from, to));
            }
        }
        reportsAPI.getAllProductIngredientsFromMenus(productIngredientsLists, from, to);
        return productIngredientsLists;
    }

    public static ArrayList<ArrayList<product>> getAllProducts() {
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

    public static ArrayList<ArrayList<menu>> getAllMenus() {
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

    public static int getNumberSoldProducts(product current, product next, String from, String to) {
        try (Connection connection = DriverManager.getConnection(getURL(), getUser(), getPassword())) {
            String query = "SELECT SUM(quantity) FROM orders_items NATURAL JOIN orders_summary NATURAL JOIN products WHERE date >= '"
                    + dateInverter.invert(current.getDate()) + "'";
            if (next != null) {
                query = query.concat(" AND date < '" + dateInverter.invert(next.getDate()) + "'");
            }
            query = query.concat(" AND date >= '" + from + "' AND date <= '" + to + "' AND product_id = "
                    + current.getId() + " AND product_date = '" + dateInverter.invert(current.getDate()) + "'");
            try (Statement stmt = connection.createStatement()) {
                ResultSet rs = stmt.executeQuery(query);
                if (rs.next())
                    return rs.getInt("SUM(quantity)");
                return 0;
            } catch (Exception e) {
                System.out.println(e);
                return 0;
            }
        } catch (SQLException e) {
            throw new IllegalStateException("Cannot connect the database!", e);
        }
    }

    public static String getNameOfProduct(int prodID, String date) {
        String name = "";
        date = dateInverter.invert(date);
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

    public static int getNumberSoldOfProductIngr(productIngredients product, String nextDate, String from, String to) {
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

    public static int getNumberSoldMenus(menu current, menu next, String from, String to) {
        try (Connection connection = DriverManager.getConnection(getURL(), getUser(), getPassword())) {
            String query = "SELECT SUM(quantity) FROM orders_menus NATURAL JOIN orders_summary NATURAL JOIN menus WHERE date >= '"
                    + dateInverter.invert(current.getDate()) + "'";
            if (next != null) {
                query = query.concat(" AND date < '" + dateInverter.invert(next.getDate()) + "'");
            }
            query = query.concat(" AND date >= '" + from + "' AND date <= '" + to + "' AND menu_id = " + current.getId()
                    + " AND menu_date = '" + dateInverter.invert(current.getDate()) + "'");
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

    public static ArrayList<ArrayList<productIngredients>> getAllProductIngredientsFromProducts() {
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

    public static void getAllProductIngredientsFromMenus(ArrayList<ArrayList<productIngredients>> prodIngr, String from,
            String to) {
        ArrayList<menuProducts> menuProd = getMenusSold(from, to);
        addSoldsToMenus(menuProd, from, to);
        try {
            turnMenuProductsIntoProductIngr(prodIngr, menuProd);
        } catch (SQLException e) {
        }
    }

    private static void turnMenuProductsIntoProductIngr(ArrayList<ArrayList<productIngredients>> prodIngr,
            ArrayList<menuProducts> menus) throws SQLException {
        for (menuProducts temp : menus) {
            try (Connection connection = DriverManager.getConnection(getURL(), getUser(), getPassword())) {
                String query = "SELECT product_id, MAX(product_date) FROM products WHERE (product_id, product_date) IN (SELECT product_id, MAX(product_date) as product_date FROM products WHERE product_date <= '"
                        + temp.getDate() + "' AND product_id IN (SELECT product_id FROM menus_products WHERE menu_id = "
                        + temp.getId()
                        + " AND menu_products_date IN (SELECT MAX(menus_products.menu_products_date) FROM menus_products WHERE menu_products_date <= '"
                        + temp.getDate() + "' AND menu_id = " + temp.getId()
                        + ")) GROUP BY product_id) GROUP BY product_id;";
                try (Statement stmt = connection.createStatement()) {
                    ResultSet rs = stmt.executeQuery(query);
                    while (rs.next()) {
                        int ID = rs.getInt("product_id");
                        String date = rs.getString("MAX(product_date)");
                        for (ArrayList<productIngredients> tempBigProd : prodIngr) {
                            if (tempBigProd.get(0).getProductID() == ID) {
                                for (productIngredients tempSmall : tempBigProd) {
                                    if (tempSmall.getProductDate().equals(date)) {
                                        tempSmall.setNumberSoldMenus(getNumberSoldProductOfMenu(temp, ID));
                                    }
                                }
                            }
                        }
                    }
                } catch (Exception e) {
                }
            }
        }
    }

    public static String getIngrDates(int ID, String date) {
        try (Connection connection = DriverManager.getConnection(getURL(), getUser(), getPassword())) {
            String query = "SELECT MAX(product_ingredients_date) FROM products_ingredients WHERE product_ingredients_date < '"
                    + date + "' AND product_id = " + ID + ";";
            try (Statement stmt = connection.createStatement()) {
                ResultSet rs = stmt.executeQuery(query);
                if (rs.next())
                    return rs.getString("MAX(product_ingredients_date)");
                return null;
            } catch (Exception e) {
                System.out.println(e);
                return null;
            }
        } catch (SQLException e) {
            throw new IllegalStateException("Cannot connect the database!", e);
        }
    }

    private static int getNumberSoldProductOfMenu(menuProducts theMenu, int productID) {
        try (Connection connection = DriverManager.getConnection(getURL(), getUser(), getPassword())) {
            String query = "SELECT productQuantity FROM menus_products WHERE menu_products_date = (SELECT MAX(menu_products_date) FROM menus_products WHERE menu_id = "
                    + theMenu.getId() + " AND menu_products_date <= '"
                    + theMenu.getDate() + "') AND product_id = " + productID + ";";
            try (Statement stmt = connection.createStatement()) {
                ResultSet rs = stmt.executeQuery(query);
                if (rs.next())
                    return rs.getInt("productQuantity") * theMenu.getSold();
                connection.close();
                return 0;
            } catch (Exception e) {
                System.out.println(e);
                return 0;
            }
        } catch (SQLException e) {
            throw new IllegalStateException("Cannot connect the database!", e);
        }
    }

    private static ArrayList<menuProducts> getMenusSold(String from, String to) {
        ArrayList<menuProducts> tempList = new ArrayList<>();
        try (Connection connection = DriverManager.getConnection(getURL(), getUser(), getPassword())) {
            String query = "SELECT orders_menus.menu_id, MAX(subq.menu_date) FROM orders_menus, (SELECT menu_id, menu_date FROM menus) AS subq  NATURAL JOIN orders_summary WHERE orders_summary.date BETWEEN '"
                    + from + "' AND '" + to
                    + "' AND orders_menus.menu_id = subq.menu_id AND date > subq.menu_date GROUP BY orders_menus.menu_id, subq.menu_date;";
            try (Statement stmt = connection.createStatement()) {
                ResultSet rs = stmt.executeQuery(query);
                while (rs.next()) {
                    int ID = rs.getInt("menu_id");
                    String date = rs.getString("MAX(subq.menu_date)");
                    tempList.add(new menuProducts(ID, date));
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

    private static void addSoldsToMenus(ArrayList<menuProducts> listMenus, String from, String to) {
        try (Connection connection = DriverManager.getConnection(getURL(), getUser(), getPassword())) {
            for (int i = 0; i < listMenus.size(); i++) {
                menuProducts temp = listMenus.get(i);
                String query = "SELECT SUM(quantity) FROM orders_summary NATURAL JOIN orders_menus WHERE menu_id = "
                        + temp.getId() + " AND date > '"
                        + temp.getDate() + "'";
                if (i + 1 < listMenus.size()) {
                    if (listMenus.get(i + 1).getId() == temp.getId())
                        query = query.concat("AND date < '" + listMenus.get(i + 1).getDate() + "'");
                }
                query = query.concat(" AND date BETWEEN '" + from + "' AND '" + to + "';");
                try (Statement stmt = connection.createStatement()) {
                    ResultSet rs = stmt.executeQuery(query);
                    if (rs.next())
                        temp.setSold(rs.getInt("SUM(quantity)"));
                } catch (Exception e) {

                }
            }
        } catch (SQLException e) {
            throw new IllegalStateException("Cannot connect the database!", e);
        }
    }

    public static void addIngredientsToProductIngredients(productIngredients productIngr) {
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

    public static ArrayList<ingredientsProviders> getAllProviders() {
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

    public static ArrayList<employee> getAllEmployeesAndShifts(String from, String to) {
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
                    temp.addShift(new shift(temp.getId(), dateInverter.invert(date), startTime, endTime));
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

}
