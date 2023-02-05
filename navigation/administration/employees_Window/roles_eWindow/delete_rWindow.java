package navigation.administration.employees_Window.roles_eWindow;

import javax.swing.table.DefaultTableModel;
import componentsFood.role;
import navigation.administration.employees_Window.employeesAPI;
import util.abstractEdit_CheckWindow;
import util.abstractUpdater;
import java.util.ArrayList;

import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import java.awt.event.*;

public class delete_rWindow extends abstractEdit_CheckWindow {

    private employeesAPI theManagerDB = new employeesAPI();

    public delete_rWindow(abstractUpdater previousWindow) {
        super(previousWindow, "Choose Role to be delete", "Role");
    }

    @Override
    public void addActionListeners() {
        abstractUpdater temp = this;
        myTable.addMouseListener(new MouseAdapter() {
            public void mouseClicked(MouseEvent me) {
                if (me.getClickCount() == 1) { // to detect doble click events
                    String stringID = (String) model.getValueAt(myTable.getSelectedRow(), 0);
                    if (stringID.equals(""))
                        return;
                    int reply = JOptionPane.showConfirmDialog(null, "Are you sure you want to delete this Role?",
                            "Confirmation", JOptionPane.YES_NO_OPTION);
                    if (reply == JOptionPane.YES_OPTION) {
                        int roleID = Integer.parseInt(stringID);
                        if (theManagerDB.isRoleAssigned(roleID)) {
                            JOptionPane.showMessageDialog(null,
                                    "You cannot delete a role if there is an employee assigned to it.",
                                    "Action Required", JOptionPane.ERROR_MESSAGE);
                        } else {
                            theManagerDB.setRolesUnactive(roleID);
                            model.removeRow(myTable.getSelectedRow());
                        }
                    }
                }
            }
        });
    }

    @Override
    public void setBounds() {
        getSummaryTXT().setBounds(200, 20, 250, 25);
        getBackButton().setBounds(400, 400, 120, 80);
        getScrollPane().setBounds(45, 60, 500, 300);
    }

    @Override
    public void addRowsToModel() {
        myTable = new JTable();
        model = new DefaultTableModel(new String[] { "ID", "Name" }, 0);
        ArrayList<role> providerList = theManagerDB.getAllRoles();
        for (role temp : providerList)
            model.addRow(new String[] { Integer.toString(temp.getId()), temp.getName() });
    }

    @Override
    public void adjustTable() {
        setScrollPane(new JScrollPane(myTable));
        getScrollPane().setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);
        myTable.setModel(model);
        myTable.removeColumn(myTable.getColumn("ID"));
        myTable.setDefaultEditor(Object.class, null);
        myTable.setFocusable(true);
    }

    @Override
    public void addToFrame() {
        theFrame.add(getSummaryTXT());
        theFrame.add(getBackButton());
        theFrame.add(getScrollPane());
    }
}
