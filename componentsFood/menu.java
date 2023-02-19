package componentsFood;

public class menu {

    private final int id;
    private int categoryID;
    private String date;
    private String name;
    private float price;
    private boolean active;

    public menu(int id, int categoryID, String date, String name, float price, boolean active) {
        this.id = id;
        this.categoryID = categoryID;
        this.date = date;
        this.name = name;
        this.price = price;
        this.active = active;
    }

    public int getId() {
        return id;
    }

    public int getCategoryID() {
        return categoryID;
    }

    public String getDate() {
        return date;
    }

    public String getName() {
        return name;
    }

    public float getPrice() {
        return price;
    }

    public boolean getActive() {
        return active;
    }

    public String toString() {
        return name;
    }
}
