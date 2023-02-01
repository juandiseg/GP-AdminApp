package componentsFood;

public class category {

    private final int id;
    private final boolean isProduct;
    private String name;

    public category(int id, String name, boolean isProduct) {
        this.id = id;
        this.name = name;
        this.isProduct = isProduct;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public boolean getIsProduct() {
        return isProduct;
    }

    public String toString() {
        return name;
    }
}
