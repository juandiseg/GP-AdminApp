package componentsFood;

public class menuProducts {

    private final int id;
    private String date;
    private int sold;

    public menuProducts(int id, String date) {
        this.id = id;
        this.date = date;
    }

    public int getId() {
        return id;
    }

    public String getDate() {
        return date;
    }

    public void setSold(int sold) {
        this.sold = sold;
    }

    public int getSold() {
        return sold;
    }

}
