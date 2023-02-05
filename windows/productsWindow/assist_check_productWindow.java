package windows.productsWindow;

import java.util.ArrayList;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

import componentsFood.ingredient;
import util.abstractEdit_CheckWindow;
import util.abstractUpdater;
import windows.ingredientsWindow.ingredientsAPI;

public class assist_check_productWindow extends abstractEdit_CheckWindow {

    private int productID;

    public assist_check_productWindow(abstractUpdater previousWindow, int ID) {
        super(previousWindow, "Ingredients of selected product", "Product");
        this.productID = ID;
    }

    @Override
    public void addRowsToModel() {
        myTable = new JTable();
        model = new DefaultTableModel();
        setUpModel();
    }

    private void setUpModel() {
        ingredientsAPI temp = new ingredientsAPI();
        myTable = new JTable();
        model = new DefaultTableModel(new String[] { "Name", "Cost", "Amount", "Used in Ingredient" }, 0);
        ArrayList<ingredient> listIngredients = temp
                .getSelectedIngredientsInProduct(new productAPI().getProduct(productID));
        for (ingredient tempIngr : listIngredients) {
            String cost = Float.toString(tempIngr.getPrice());
            String amount = Float.toString(tempIngr.getAmount());
            String used = Float.toString(temp.getAmountOfIngredientInProduct(productID, tempIngr.getId()));
            model.addRow(new String[] { tempIngr.getName(), cost, amount, used });
        }

    }

    public ArrayList<String> getDisplayValues(ArrayList<String> containingOnes, int totalAllergens) {
        ArrayList<String> displayText = new ArrayList<>();
        for (int i = 1; i <= totalAllergens; i++) {
            if (containingOnes.contains(model.getColumnName(i)))
                displayText.add("Yes");
            else
                displayText.add("No");
        }
        return displayText;
    }

    @Override
    public void adjustTable() {
        setScrollPane(new JScrollPane(myTable, JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED,
                JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED));
        myTable.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
        myTable.setModel(model);
        myTable.setDefaultEditor(Object.class, null);
        myTable.setFocusable(false);
    }

    @Override
    public void setBounds() {
        getSummaryTXT().setBounds(200, 20, 250, 25);
        getBackButton().setBounds(400, 400, 120, 80);
        getScrollPane().setBounds(45, 60, 500, 400);
    }

    @Override
    public void addToFrame() {
        theFrame.add(getSummaryTXT());
        theFrame.add(getBackButton());
        theFrame.add(getScrollPane());
    }

    @Override
    public void addActionListeners() {
    }

}
