package windows.ingredientsWindow.allergensWindow;

import javax.swing.table.DefaultTableModel;
import componentsFood.allergen;
import util.abstractEdit_CheckWindow;
import util.abstractUpdater;
import java.util.ArrayList;
import javax.swing.JTable;
import java.awt.event.*;

public class check_aWindow extends abstractEdit_CheckWindow {

    public check_aWindow(abstractUpdater previousWindow, String title) {
        super(previousWindow, title, false);
    }

    @Override
    public void addActionListeners() {
    }

    @Override
    public void addRowsToModel() {
        myTable = new JTable();
        model = new DefaultTableModel(new String[] { "ID", "Name" }, 0);
        ArrayList<allergen> temp = theManagerDB.getAllAllergens();
        for (allergen temp2 : temp)
            model.addRow(new String[] { Integer.toString(temp2.getId()), temp2.getName() });
    }

    @Override
    public void adjustTable() {
        myTable.setModel(model);
        myTable.removeColumn(myTable.getColumn("ID"));
        myTable.setDefaultEditor(Object.class, null);
        myTable.setFocusable(true);
    }

    @Override
    public void setBounds() {
        getSummaryTXT().setBounds(200, 20, 250, 25);
        getBackButton().setBounds(400, 400, 120, 80);
        myTable.setBounds(45, 60, 500, 300);

    }

    @Override
    public void addToFrame() {
        theFrame.add(getBackButton());
        theFrame.add(getSummaryTXT());
        theFrame.add(myTable);
    }

}
