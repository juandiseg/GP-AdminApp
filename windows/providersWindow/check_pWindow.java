package windows.providersWindow;

import javax.swing.table.DefaultTableModel;
import util.abstractEdit_CheckWindow;
import componentsFood.provider;
import util.abstractUpdater;
import java.util.ArrayList;
import javax.swing.JTable;

public class check_pWindow extends abstractEdit_CheckWindow {

    public check_pWindow(abstractUpdater previousWindow, String title) {
        super(previousWindow, title, false);
    }

    @Override
    public void addActionListeners() {
    }

    @Override
    public void addRowsToModel() {
        myTable = new JTable();
        model = new DefaultTableModel(new String[] { "ID", "Name", "Email" }, 0);
        ArrayList<provider> providerList = theManagerDB.getAllProviders();
        for (provider temp : providerList)
            model.addRow(new String[] { Integer.toString(temp.getId()), temp.getName(), temp.getEmail() });
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
        theFrame.add(getSummaryTXT());
        theFrame.add(getBackButton());
        theFrame.add(myTable);
    }

}
