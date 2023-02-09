package navigation.administration.reports_Window;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import componentsFood.menu;
import componentsFood.product;
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
                    String date = rs.getString("product_date");
                    String name = rs.getString("name");
                    float price = rs.getFloat("price");
                    boolean active = rs.getBoolean("active");
                    if(lastID==ID){
                        temp.add(new product(ID, date, name, price, active));
                    } else {
                        if(!temp.isEmpty())
                            listOfLists.add(temp);
                        temp = new ArrayList<product>();
                        lastID = ID;
                        temp.add(new product(ID, date, name, price, active));
                    }
                }
                if(!temp.isEmpty())
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
                    String date = rs.getString("menu_date");
                    String name = rs.getString("name");
                    float price = rs.getFloat("price");
                    boolean active = rs.getBoolean("active");
                    if(lastID==ID){
                        temp.add(new menu(ID, date, name, price, active));
                    } else {
                        if(!temp.isEmpty())
                            listOfLists.add(temp);
                        temp = new ArrayList<menu>();
                        lastID = ID;
                        temp.add(new menu(ID, date, name, price, active));
                    }
                }
                if(!temp.isEmpty())
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

    public int getNumberSoldProduct(product current, product next, String from, String to){
        try (Connection connection = DriverManager.getConnection(getURL(), getUser(), getPassword())) {
            String query = "SELECT SUM(quantity) FROM orders_items NATURAL JOIN orders_summary NATURAL JOIN products WHERE date >= '"
            + current.getDate() + "'";
            if(next != null){
                query = query.concat(" AND date < '" + next.getDate() + "'");
            }
            query = query.concat(" AND date >= '"+from+"' AND date <= '"+to+"' AND product_id = " + current.getId() + " AND product_date = '" + current.getDate() + "'");
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

    public int getNumberSoldMenu(menu current, menu next, String from, String to){
        try (Connection connection = DriverManager.getConnection(getURL(), getUser(), getPassword())) {
            String query = "SELECT SUM(quantity) FROM orders_menus NATURAL JOIN orders_summary NATURAL JOIN menus WHERE date >= '"
            + current.getDate() + "'";
            if(next != null){
                query = query.concat(" AND date < '" + next.getDate() + "'");
            }
            query = query.concat(" AND date >= '"+from+"' AND date <= '"+to+"' AND menu_id = " + current.getId() + " AND menu_date = '" + current.getDate() + "'");
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

}
