package windows.ingredientsWindow;

import javax.swing.table.DefaultTableModel;
import util.abstractEdit_CheckWindow;
import componentsFood.ingredient;
import util.abstractUpdater;
import java.util.ArrayList;

import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import java.awt.event.*;

public class delete_iWindow extends abstractEdit_CheckWindow {

    private ingredientsAPI theManagerDB = new ingredientsAPI();

    public delete_iWindow(abstractUpdater previousWindow) {
        super(previousWindow, "Choose Ingredient to be deleted", "Ingredient");
    }

    @Override
    public void addActionListeners() {
        abstractUpdater temp = this;
        myTable.addMouseListener(new MouseAdapter() {
            public void mouseClicked(MouseEvent me) {
                if (me.getClickCount() == 1) { // to detect doble click events
                }
            }
        });
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
            String amount = Integer.toString(temp.getAmount());
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
