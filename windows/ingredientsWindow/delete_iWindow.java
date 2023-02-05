package windows.ingredientsWindow;

import javax.swing.table.DefaultTableModel;
import util.abstractEdit_CheckWindow;
import componentsFood.ingredient;
import util.abstractUpdater;
import windows.productsWindow.productAPI;

import java.util.ArrayList;
import java.util.Stack;

import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import java.awt.event.*;

public class delete_iWindow extends abstractEdit_CheckWindow {

    private String[] options = new String[] { "Delete Menus/Products", "Keep Menus/Products", "Cancel" };
    private ingredientsAPI theManagerDB = new ingredientsAPI();

    public delete_iWindow(abstractUpdater previousWindow) {
        super(previousWindow, "Choose Ingredient to be deleted", "Ingredient");
    }

    @Override
    public void addActionListeners() {
        myTable.addMouseListener(new MouseAdapter() {
            public void mouseClicked(MouseEvent me) {
                if (me.getClickCount() == 1) {
                    try {
                        if (myTable.getValueAt(myTable.getSelectedRow(), 0).toString().equals(""))
                            return;
                        int reply = JOptionPane.showConfirmDialog(null,
                                "Are you sure you want to delete this Ingredient?",
                                "Confirmation", JOptionPane.YES_NO_OPTION);
                        if (reply == JOptionPane.YES_OPTION) {
                            int response = JOptionPane.showOptionDialog(null,
                                    "Do you want to delete every menu and product which uses this ingredient, or keep them without the ingredient?",
                                    "Choice",
                                    JOptionPane.DEFAULT_OPTION, JOptionPane.PLAIN_MESSAGE, null, options, options[0]);
                            int ingredientID = Integer
                                    .parseInt(model.getValueAt(myTable.getSelectedRow(), 0).toString());
                            if (response == 0) {
                                deleteAllAssociatedToIngredientID(ingredientID);
                                model.removeRow(myTable.getSelectedRow());
                            } else if (response == 1) {
                                deleteIngredientsFromProducts(ingredientID);
                                model.removeRow(myTable.getSelectedRow());
                            }
                        }
                    } catch (IndexOutOfBoundsException e) {
                        return;
                    }
                }
            }
        });
    }

    private void deleteAllAssociatedToIngredientID(int ingredientID) {
        productAPI tempAPI = new productAPI();
        ArrayList<Integer> productIDs = theManagerDB.getProductsIDWithIngredient(ingredientID);
        ArrayList<Integer> menuIDs = new ArrayList<Integer>();
        Stack<Integer> stackMenuIDs = tempAPI.getAllActiveMenuIDs();
        while (!stackMenuIDs.isEmpty())
            menuIDs.add(stackMenuIDs.pop());
        for (Integer tempProductID : productIDs) {
            for (Integer tempMenuID : menuIDs)
                tempAPI.deleteMenuWithProduct(tempMenuID, tempProductID);
            tempAPI.updateActive(tempProductID, false);
        }
        for (Integer tempProductID : productIDs)
            theManagerDB.deleteIngredientsInProduct(tempProductID, ingredientID);
        theManagerDB.setToUnactive(ingredientID);
    }

    private void deleteIngredientsFromProducts(int ingredientID) {
        Stack<Integer> stackProductIDs = theManagerDB.getAllActiveProductIDs();
        while (!stackProductIDs.empty())
            theManagerDB.deleteIngredientsInProduct(stackProductIDs.pop(), ingredientID);
        theManagerDB.setToUnactive(ingredientID);
    }

    @Override
    public void addRowsToModel() {
        ArrayList<ingredient> tempList = theManagerDB.getAllCurrentIngredients();
        myTable = new JTable();
        model = new DefaultTableModel(
                new String[] { "ID", "Prov_ID", "Active Since", "Name", "Price", "Amount", "in_inventory" },
                0);
        for (ingredient temp : tempList) {
            String id = Integer.toString(temp.getId());
            String prov_id = Integer.toString(temp.getProviderID());
            String price = Float.toString(temp.getPrice());
            String amount = Float.toString(temp.getAmount());
            String in_inventory = "No";
            if (temp.getInInventory())
                in_inventory = "Yes";
            model.addRow(
                    new String[] { id, prov_id, temp.getDate(), temp.getName(), price, amount, in_inventory });
        }
    }

    @Override
    public void adjustTable() {
        setScrollPane(new JScrollPane(myTable));
        getScrollPane().setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);
        myTable.setDefaultEditor(Object.class, null);
        myTable.setFocusable(true);
        myTable.setModel(model);
        myTable.removeColumn(myTable.getColumn("ID"));
        myTable.removeColumn(myTable.getColumn("Prov_ID"));
    }

    @Override
    public void setBounds() {
        getScrollPane().setBounds(45, 60, 500, 300);
        getSummaryTXT().setBounds(200, 20, 250, 25);
        getBackButton().setBounds(400, 400, 120, 80);

    }

    @Override
    public void addToFrame() {
        theFrame.add(getSummaryTXT());
        theFrame.add(getBackButton());
        theFrame.add(getScrollPane());
    }

}
