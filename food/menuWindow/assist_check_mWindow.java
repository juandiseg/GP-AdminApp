package food.menuWindow;

import java.util.ArrayList;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

import componentsFood.category;
import componentsFood.product;
import util.abstractEdit_CheckWindow;
import util.abstractUpdater;
import food.categoryWindow.categoryAPI;
import food.productsWindow.productAPI;

public class assist_check_mWindow extends abstractEdit_CheckWindow {

    private int menuID;

    public assist_check_mWindow(abstractUpdater previousWindow, int menuID) {
        super(previousWindow, "Products of selected Menu", "Menu");
        this.menuID = menuID;
    }

    @Override
    public void addRowsToModel() {
        myTable = new JTable();
        model = new DefaultTableModel();
        setUpModel();
    }

    private void setUpModel() {
        ArrayList<product> tempList = new productAPI().getSelectedProductsInMenu(menuID);
        myTable = new JTable();
        model = new DefaultTableModel(
                new String[] { "Name", "Price", "Category", "Used in Menu" },
                0);
        productAPI tempProdAPI = new productAPI();
        categoryAPI tempCatAPI = new categoryAPI();
        for (product temp : tempList) {
            String name = temp.getName();
            String price = Float.toString(temp.getPrice());
            category tempCategory = tempCatAPI.getCategoryOfProduct(temp.getId());
            String amountUsed = Float.toString(tempProdAPI.getAmountOfProductInMenu(menuID, temp.getId()));
            model.addRow(new String[] { name, price, tempCategory.getName(), amountUsed });
        }
    }

    @Override
    public void adjustTable() {
        setScrollPane(new JScrollPane(myTable));
        getScrollPane().setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);
        myTable.setDefaultEditor(Object.class, null);
        myTable.setFocusable(true);
        myTable.setModel(model);
    }

    @Override
    public void setBounds() {
        getSummaryTXT().setBounds(200, 20, 250, 25);
        getBackButton().setBounds(400, 400, 120, 80);
        getScrollPane().setBounds(45, 60, 500, 300);
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
