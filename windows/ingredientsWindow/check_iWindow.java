package windows.ingredientsWindow;

import javax.swing.table.DefaultTableModel;
import util.abstractEdit_CheckWindow;
import componentsFood.ingredient;
import util.abstractUpdater;
import java.util.ArrayList;

import javax.swing.JScrollPane;
import javax.swing.JTable;

public class check_iWindow extends abstractEdit_CheckWindow {

    public check_iWindow(abstractUpdater previousWindow) {
        super(previousWindow, "Check Ingredients", "Ingredients");
    }

    @Override
    public void addActionListeners() {
    }

    @Override
    public void addRowsToModel() {
        ArrayList<ingredient> tempList = theManagerDB.getAllIngredients();
        myTable = new JTable();
        model = new DefaultTableModel(
                new String[] { "ID", "Prov_ID", "Date", "Name", "Price", "Amount", "in_inventory", "active" }, 0);
        for (ingredient temp : tempList) {
            String id = Integer.toString(temp.getId());
            String prov_id = Integer.toString(temp.getProviderID());
            String price = Float.toString(temp.getPrice());
            String amount = Integer.toString(temp.getAmount());
            String in_inventory;
            String active;
            if (temp.getInInventory())
                in_inventory = "In inventory";
            else
                in_inventory = "Not in inventory";
            if (temp.getActive())
                active = "Active";
            else
                active = "Not active";
            model.addRow(
                    new String[] { id, prov_id, temp.getDate(), temp.getName(), price, amount, in_inventory, active });
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
