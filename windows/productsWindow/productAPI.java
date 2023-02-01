package windows.productsWindow;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;

import componentsFood.product;
import util.abstractManagerDB;

public class productAPI extends abstractManagerDB {

    // GET "product" objects from database.

    public ArrayList<product> getAllCurrentProducts() {
        ArrayList<product> tempList = new ArrayList<product>();
        try (Connection connection = DriverManager.getConnection(getURL(), getUser(), getPassword())) {
            String query = "SELECT * FROM product ORDER BY product_id";
            try (Statement stmt = connection.createStatement()) {
                ResultSet rs = stmt.executeQuery(query);
                while (rs.next()) {
                    int ID = rs.getInt("product_id");
                    String date = rs.getString("product_date");
                    String name = rs.getString("name");
                    float price = rs.getFloat("price");
                    boolean active = rs.getBoolean("active");
                    tempList.add(new product(ID, date, name, price, active));
                }
                tempList = checkRepeatedProducts(tempList);
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

    public product getProduct(int productID) {
        try (Connection connection = DriverManager.getConnection(getURL(), getUser(), getPassword())) {
            String query = "SELECT * FROM beatneat.product WHERE product_id = " + productID
                    + " ORDER BY product_date DESC LIMIT 1;";
            try (Statement stmt = connection.createStatement()) {
                ResultSet rs = stmt.executeQuery(query);
                if (rs.next()) {
                    int ID = rs.getInt("product_id");
                    String date = rs.getString("product_date");
                    String name = rs.getString("name");
                    float price = rs.getFloat("price");
                    boolean active = rs.getBoolean("active");
                    return new product(ID, date, name, price, active);
                }
            } catch (Exception e) {
                System.out.println(e);
                return null;
            }
        } catch (SQLException e) {
            throw new IllegalStateException("Cannot connect the database!", e);
        }
        return null;
    }

    private ArrayList<product> checkRepeatedProducts(ArrayList<product> theList) {
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
        return theList;
    }

    // ADD "product" to database.
    public product addProduct(String date, String name, float price, boolean active) {
        int productID = getLastProductID() + 1;
        try (Connection connection = DriverManager.getConnection(getURL(), getUser(), getPassword())) {
            String query = "INSERT INTO product VALUES (" + productID + ", '" + date + "', '" + name + "', " + price
                    + ", " + active + ");";
            try (Statement stmt = connection.createStatement()) {
                stmt.executeUpdate(query);
                connection.close();
                return new product(productID, date, name, price, active);
            } catch (Exception e) {
                System.out.println(e);
                return null;
            }
        } catch (SQLException e) {
            throw new IllegalStateException("Cannot connect the database!", e);
        }
    }

    private int getLastProductID() {
        try (Connection connection = DriverManager.getConnection(getURL(), getUser(), getPassword())) {
            String query = "SELECT product_id FROM product ORDER BY product_id DESC LIMIT 1;";
            try (Statement stmt = connection.createStatement()) {
                ResultSet rs = stmt.executeQuery(query);
                if (rs.next()) {
                    int providerID = rs.getInt("product_id");
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

    // UPDATE something "product" related in database.
    public boolean easyUpdateProduct(int productID, String name, boolean active) {
        try (Connection connection = DriverManager.getConnection(getURL(), getUser(), getPassword())) {
            String query = "UPDATE product SET name = '" + name + "', active = " + active
                    + " WHERE product_id = " + productID;
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

    public boolean mediumUpdateProduct(product theProduct, float price) {
        LocalDate dateObj = LocalDate.now();
        String date = dateObj.format(DateTimeFormatter.ofPattern("yyyy-MM-dd"));
        if (theProduct.getDate().equals(date)) {
            return updateProductPriceToday(theProduct.getId(), price);
        }
        try (Connection connection = DriverManager.getConnection(getURL(), getUser(), getPassword())) {
            String query = "INSERT INTO product VALUES (" + theProduct.getId() + ", '" + date + "', '"
                    + theProduct.getName() + "', " + price + ", " + theProduct.getActive() + ");";
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

    public boolean updateProductPriceToday(int productID, float productPrice) {
        try (Connection connection = DriverManager.getConnection(getURL(), getUser(), getPassword())) {
            String dateToday = LocalDate.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd"));
            String query = "UPDATE product SET price = " + productPrice + " WHERE product_date = '" + dateToday
                    + "' AND product_id = " + productID;
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

    public boolean isProductIngredientDateToday(int product_id) {
        try (Connection connection = DriverManager.getConnection(getURL(), getUser(), getPassword())) {
            String dateToday = LocalDate.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd"));
            String query = "SELECT MAX(product_ingredients_date) FROM product_ingredients WHERE product_id = "
                    + product_id + ";";
            try (Statement stmt = connection.createStatement()) {
                ResultSet rs = stmt.executeQuery(query);
                if (rs.next()) {
                    String dateDB = rs.getString("MAX(product_ingredients_date)");
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

    // REMOVE "product" from database.
    public boolean removeProductIngredientsToday(int productID) {
        try (Connection connection = DriverManager.getConnection(getURL(), getUser(), getPassword())) {
            String dateToday = LocalDate.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd"));
            String query = "DELETE FROM product_ingredients WHERE product_id = " + productID
                    + " AND product_ingredients_date = '" + dateToday + "';";
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

}
