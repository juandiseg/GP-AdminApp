package navigation.administration.shifts_Window;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;

import componentsFood.employee;
import componentsFood.role;
import navigation.administration.employees_Window.employeesAPI;
import util.abstractAddWindow;
import util.abstractUpdater;

import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JScrollPane;
import javax.swing.JTable;

public class add_sWindow extends abstractAddWindow {

    private shiftsAPI theManagerDB = new shiftsAPI();

    private JLabel enterDate = new JLabel("Enter the shift's DATE: ");
    private JLabel enterStart = new JLabel("Enter the shift's START TIME: ");
    private JLabel enterEnd = new JLabel("Enter the shift's END TIME: ");
    private JLabel chooseEmployees = new JLabel("Select the employee(s): ");

    private JTextField textFieldDate = new JTextField();
    private JTextField textFieldStart = new JTextField();
    private JTextField textFieldEnd = new JTextField();

    private JButton swapLeft = new JButton("Left");
    private JButton swapRight = new JButton("Right");

    private JTable tableEmployees;
    private JTable tableSelected;

    private DefaultTableModel modelEmployees;
    private DefaultTableModel modelSelected;

    private JScrollPane scrollPaneEmployee;
    private JScrollPane scrollPaneSelected;

    public add_sWindow(abstractUpdater previousWindow) {
        super(previousWindow, "Shifts", true);
    }

    @Override
    public void addComponents() {
        setTable();
        setBounds();
        addToFrame();
        setBackButton();
    }

    @Override
    public void addActionListeners() {
        getAddButton().addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                // check inputs
                String shifDate = textFieldDate.getText();
                String startShift = textFieldStart.getText();
                String endShift = textFieldEnd.getText();
                for (int i = 0; i < tableSelected.getRowCount(); i++) {
                    String employeeID = (String) modelSelected.getValueAt(i, 0);
                    theManagerDB.addShift(employeeID, shifDate, startShift, endShift);
                }
                updateToPreviousMenu();
            }
        });
        swapLeft.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                int row = tableEmployees.getSelectedRow();
                if (row == -1)
                    return;
                String ID = (String) modelEmployees.getValueAt(row, 0);
                String name = (String) modelEmployees.getValueAt(row, 1);
                String salary = (String) modelEmployees.getValueAt(row, 2);
                String hoursWeek = (String) modelEmployees.getValueAt(row, 3);
                String role = (String) modelEmployees.getValueAt(row, 4);
                String roleID = (String) modelEmployees.getValueAt(row, 5);
                modelEmployees.removeRow(row);
                modelSelected.addRow(new String[] { ID, name, salary, hoursWeek, role, roleID });
            }
        });
        swapRight.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                int row = tableSelected.getSelectedRow();
                if (row == -1)
                    return;
                String ID = (String) modelSelected.getValueAt(row, 0);
                String name = (String) modelSelected.getValueAt(row, 1);
                String salary = (String) modelSelected.getValueAt(row, 2);
                String hoursWeek = (String) modelSelected.getValueAt(row, 3);
                String role = (String) modelSelected.getValueAt(row, 4);
                String roleID = (String) modelSelected.getValueAt(row, 5);
                modelSelected.removeRow(row);
                modelEmployees.addRow(new String[] { ID, name, salary, hoursWeek, role, roleID });
            }
        });
    }

    @Override
    public void setBounds() {
        getInputSuccesful().setBounds(250, 120, 250, 25);
        getInputError().setBounds(250, 120, 300, 25);
        getAddButton().setBounds(80, 400, 130, 20);
        getBackButton().setBounds(400, 400, 120, 80);
        enterDate.setBounds(10, 20, 200, 25);
        enterStart.setBounds(10, 50, 200, 25);
        enterEnd.setBounds(10, 80, 200, 25);
        chooseEmployees.setBounds(10, 110, 200, 25);
        textFieldDate.setBounds(210, 20, 165, 25);
        textFieldStart.setBounds(210, 50, 165, 25);
        textFieldEnd.setBounds(210, 80, 165, 25);
        scrollPaneEmployee.setBounds(320, 140, 170, 200);
        scrollPaneSelected.setBounds(50, 140, 170, 200);
        swapLeft.setBounds(230, 200, 80, 25);
        swapRight.setBounds(230, 240, 80, 25);
    }

    @Override
    public void addToFrame() {
        theFrame.add(getAddButton());
        theFrame.add(getBackButton());
        theFrame.add(enterDate);
        theFrame.add(textFieldDate);
        theFrame.add(enterStart);
        theFrame.add(textFieldStart);
        theFrame.add(enterEnd);
        theFrame.add(textFieldEnd);
        theFrame.add(chooseEmployees);
        theFrame.add(scrollPaneEmployee);
        theFrame.add(scrollPaneSelected);
        theFrame.add(swapLeft);
        theFrame.add(swapRight);
    }

    private void setTable() {
        tableEmployees = new JTable();
        tableSelected = new JTable();
        modelEmployees = new DefaultTableModel(
                new String[] { "id", "Name", "Salary", "Hours per Week", "Role", "role_id" }, 0);
        modelSelected = new DefaultTableModel(
                new String[] { "id", "Name", "Salary", "Hours per Week", "Role", "role_id" }, 0);
        for (employee tempEmp : new employeesAPI().getAllCurrentEmployeesOrdered()) {
            String id = Integer.toString(tempEmp.getId());
            String name = tempEmp.getName();
            String salary = Float.toString(tempEmp.getSalary());
            String hoursWeek = tempEmp.getHoursWeek();
            String role = new employeesAPI().getNameOfRoleID(tempEmp.getRoleID());
            String roleID = Integer.toString(tempEmp.getRoleID());
            modelEmployees.addRow(new String[] { id, name, salary, hoursWeek, role, roleID });
        }
        tableEmployees.setModel(modelEmployees);
        tableSelected.setModel(modelSelected);
        tableEmployees.removeColumn(tableEmployees.getColumn("id"));
        tableEmployees.removeColumn(tableEmployees.getColumn("role_id"));
        tableSelected.removeColumn(tableSelected.getColumn("id"));
        tableSelected.removeColumn(tableSelected.getColumn("role_id"));
        scrollPaneEmployee = new JScrollPane(tableEmployees, JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED,
                JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
        scrollPaneSelected = new JScrollPane(tableSelected, JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED,
                JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
    }

}
