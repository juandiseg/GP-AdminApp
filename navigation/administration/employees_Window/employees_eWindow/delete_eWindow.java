package navigation.administration.employees_Window.employees_eWindow;

import javax.swing.table.DefaultTableModel;
import util.abstractEdit_CheckWindow;
import componentsFood.employee;
import navigation.administration.employees_Window.employeesAPI;
import util.abstractUpdater;
import java.util.ArrayList;

import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import java.awt.event.*;

public class delete_eWindow extends abstractEdit_CheckWindow {

    private employeesAPI theManagerDB = new employeesAPI();

    public delete_eWindow(abstractUpdater previousWindow) {
        super(previousWindow, "Delete Employees", "Employees");
    }

    @Override
    public void addActionListeners() {
        myTable.addMouseListener(new MouseAdapter() {
            public void mouseClicked(MouseEvent me) {
                if (me.getClickCount() == 2) {
                    String stringID = (String) model.getValueAt(myTable.getSelectedRow(), 0);
                    if (stringID.equals(""))
                        return;
                    int reply = JOptionPane.showConfirmDialog(null, "Are you sure you want to delete this Employee?",
                            "Confirmation", JOptionPane.YES_NO_OPTION);
                    if (reply == JOptionPane.YES_OPTION) {
                        theManagerDB.setEmployeeUnactive(Integer.parseInt(stringID));
                        model.removeRow(myTable.getSelectedRow());
                    }
                }
            }
        });
    }

    @Override
    public void addRowsToModel() {
        ArrayList<employee> tempList = theManagerDB.getAllCurrentEmployees();
        myTable = new JTable();
        model = new DefaultTableModel(new String[] { "employee_id", "Name", "Salary", "Hours / Week", "Role" }, 0);
        for (employee temp : tempList) {
            String id = Integer.toString(temp.getId());
            String name = temp.getName();
            String salary = Float.toString(temp.getSalary());
            String hoursWeek = temp.getHoursWeek().substring(0, 5);
            String role = theManagerDB.getNameOfRoleID(temp.getRoleID());
            model.addRow(new String[] { id, name, salary, hoursWeek, role });
        }
    }

    @Override
    public void adjustTable() {
        setScrollPane(new JScrollPane(myTable));
        getScrollPane().setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);
        myTable.setDefaultEditor(Object.class, null);
        myTable.setFocusable(true);
        myTable.setModel(model);
        myTable.removeColumn(myTable.getColumn("employee_id"));
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
