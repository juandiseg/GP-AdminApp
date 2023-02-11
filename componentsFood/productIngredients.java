package componentsFood;
import java.util.ArrayList;

public class productIngredients {

    private final int productID;
    private final String date;
    private ArrayList<ingredient> ingredientIDs = new ArrayList<ingredient>();
    private ArrayList<Float> quantities = new ArrayList<Float>();
    private float numberProductsSold = 0;

    public productIngredients(int productID, String date) {
        this.productID = productID;
        this.date = date;
    }

    public void setNumberProductsSold(int sum){
        numberProductsSold = sum;
    }

    public float getNumberProductsSold(){
        return numberProductsSold;
    }

    public int getProductID() {
        return productID;
    }

    public ArrayList<ingredient> getIngredients() {
        return ingredientIDs;
    }

    public void addIngredient(ingredient newIngredient){
        ingredientIDs.add(newIngredient);
    }

    public ArrayList<Float> getQuantity() {
        return quantities;
    }

    public void addQuantity(Float quantity){
        quantities.add(quantity);
    }
    
    public String getDate() {
        return date;
    }

    public void print(){
        System.out.println("This product has ID " + productID + " and date: " + date + ".");
        System.out.println("It also has the following products with the following amounts: ");
        for(int i = 0; i < ingredientIDs.size(); i++){
            System.out.println(ingredientIDs.get(i).getName() + " and it uses " + quantities.get(i));
        }
    }
}
