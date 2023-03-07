package componentsFood;

import java.util.ArrayList;

public class productIngredients {

    private final int productID;
    private final String productDate;
    private final String ingredientsDate;
    private ArrayList<ingredient> ingredientIDs = new ArrayList<ingredient>();
    private ArrayList<Float> quantities = new ArrayList<Float>();
    private int numberSoldProducts = 0;
    private int numberSoldMenus = 0;

    public productIngredients(int productID, String productDate, String ingredientsDate) {
        this.productID = productID;
        this.productDate = productDate;
        this.ingredientsDate = ingredientsDate;
    }

    public void setNumberSoldProducts(int sum) {
        numberSoldProducts = sum;
    }

    public int getNumberSoldProducts() {
        return numberSoldProducts;
    }

    public void setNumberSoldMenus(int sum) {
        numberSoldMenus = sum;
    }

    public int getNumberSoldMenus() {
        return numberSoldMenus;
    }

    public int getProductID() {
        return productID;
    }

    public String getProductDate() {
        return productDate;
    }

    public ArrayList<ingredient> getIngredients() {
        return ingredientIDs;
    }

    public void addIngredient(ingredient newIngredient) {
        ingredientIDs.add(newIngredient);
    }

    public ArrayList<Float> getQuantity() {
        return quantities;
    }

    public void addQuantity(Float quantity) {
        quantities.add(quantity);
    }

    public String getIngredientsDate() {
        return ingredientsDate;
    }

}
