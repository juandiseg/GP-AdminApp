package componentsFood;
import java.util.ArrayList;

public class ingredientsProviders {

    private final int providerID;
    private final String providerName;
    private ArrayList<ingredient> ingredients = new ArrayList<ingredient>();
    private ArrayList<Integer> quantitySoldTotal = new ArrayList<Integer>();

    public ingredientsProviders(int providerID, String providerName) {
        this.providerID = providerID;
        this.providerName = providerName;
    }

    public ArrayList<Integer> getQuantitySoldTotal(){
        return quantitySoldTotal;
    }

    public int getProviderID() {
        return providerID;
    }

    public String getProviderName() {
        return providerName;
    }

    public ArrayList<ingredient> getIngredients() {
        return ingredients;
    }

    public void addIngredient(ingredient newIngredient){
        ingredients.add(newIngredient);
    }
}
