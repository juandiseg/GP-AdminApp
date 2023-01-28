package windows.providersWindow;

import javax.swing.table.DefaultTableModel;
import componentsFood.provider;
import util.abstractEdit_CheckWindow;
import util.abstractUpdater;
import java.util.ArrayList;
import javax.swing.JTable;
import java.awt.event.*;

public class edit_pWindow extends abstractEdit_CheckWindow {

    public edit_pWindow(abstractUpdater previousWindow) {
        super(previousWindow, "Provider", true);
    }

    @Override
    public void addActionListeners() {
        abstractUpdater temp = this;
        myTable.addMouseListener(new MouseAdapter() {
            public void mouseClicked(MouseEvent me) {
                if (me.getClickCount() == 1) { // to detect doble click events
                    try {
                        JTable target = (JTable) me.getSource();
                        if (target.getValueAt(target.getSelectedRow(), 0).toString().equals(""))
                            return;
                        String ID = (String) model.getValueAt(target.getSelectedRow(), 0);
                        model = null;
                        myTable = null;
                        new assist_edit_pWindow(temp, Integer.valueOf(ID)).updateToThisMenu();
                    } catch (IndexOutOfBoundsException e) {
                        return;
                    }
                }
            }
        });
    }

    @Override
    public void setBounds() {
        getSummaryTXT().setBounds(200, 20, 250, 25);
        getBackButton().setBounds(400, 400, 120, 80);
        myTable.setBounds(45, 60, 500, 300);
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
    public void addToFrame() {
        theFrame.add(getSummaryTXT());
        theFrame.add(getBackButton());
        theFrame.add(myTable);
    }
}
