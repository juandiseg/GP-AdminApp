package navigation.administration.employees_Window.employees_eWindow;

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
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JScrollPane;
import javax.swing.JTable;

public class assist_edit_eWindow extends abstractAddWindow {

    private employee theCurrentEmployee;
    private employeesAPI theManagerDB = new employeesAPI();

    private JLabel enterName = new JLabel("Enter the employee's new NAME: ");
    private JLabel enterSalary = new JLabel("Enter the employee's new SALARY: ");
    private JLabel enterHoursWeek = new JLabel("Enter the employee's new Hours/Week: ");
    private JLabel selectRole = new JLabel("Select the employee's new ROLE: ");

    private JTextField textFieldName = new JTextField();
    private JTextField textFieldSalary = new JTextField();
    private JTextField textFieldHours = new JTextField();

    private JList<role> listRoles = new JList<role>();
    private JScrollPane scrollPane;

    private JScrollPane summaryPane;
    private JTable summaryTable;
    private DefaultTableModel summaryModel;

    public assist_edit_eWindow(abstractUpdater previousWindow, employee theCurrentEmployee) {
        super(previousWindow, "Employee", false);
        this.theCurrentEmployee = theCurrentEmployee;
    }

    @Override
    public void addComponents() {
        setTableSummary();
        setList();
        setBounds();
        addToFrame();
        setBackButton();
    }

    private void setTableSummary() {
        summaryTable = new JTable();
        summaryModel = new DefaultTableModel(new String[] { "Name", "Salary", "Hours / Week", "Role" }, 0);
        String name = theCurrentEmployee.getName();
        String salary = Float.toString(theCurrentEmployee.getSalary());
        String hoursWeek = theCurrentEmployee.getHoursWeek().substring(0, 5);
        String role = theManagerDB.getNameOfRoleID(theCurrentEmployee.getRoleID());
        summaryModel.addRow(new String[] { name, salary, hoursWeek, role });
        summaryPane = new JScrollPane(summaryTable, JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED,
                JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
        summaryTable.setDefaultEditor(Object.class, null);
        summaryTable.setFocusable(true);
        summaryTable.setModel(summaryModel);
    }

    @Override
    public void addActionListeners() {
        getAddButton().addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                String name = textFieldName.getText();
                if (!name.isEmpty())
                    theManagerDB.updateEmployeeName(theCurrentEmployee.getId(), name);
                String salaryString = textFieldSalary.getText();
                if (!salaryString.isEmpty())
                    theManagerDB.updateEmployeeSalary(theCurrentEmployee.getId(), Float.parseFloat(salaryString));
                String hoursWeek = textFieldHours.getText();
                if (!hoursWeek.isEmpty())
                    theManagerDB.updateEmployeeHoursWeek(theCurrentEmployee.getId(), hoursWeek);
                role tempRole = listRoles.getSelectedValue();
                if (tempRole != null)
                    theManagerDB.updateEmployeeRole(theCurrentEmployee.getId(), tempRole.getId());
                updateTable();
            }
        });
    }

    private void updateTable() {
        theCurrentEmployee = theManagerDB.getEmployee(theCurrentEmployee.getId());
        summaryModel.removeRow(0);
        String name = theCurrentEmployee.getName();
        String salary = Float.toString(theCurrentEmployee.getSalary());
        String hoursWeek = theCurrentEmployee.getHoursWeek().substring(0, 5);
        String role = theManagerDB.getNameOfRoleID(theCurrentEmployee.getRoleID());
        summaryModel.addRow(new String[] { name, salary, hoursWeek, role });
    }

    private void setList() {
        DefaultListModel<role> listModel = new DefaultListModel<role>();
        for (role tempProv : new employeesAPI().getAllRoles())
            listModel.addElement(tempProv);
        listRoles.setModel(listModel);
        scrollPane = new JScrollPane(listRoles, JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED,
                JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
    }

    @Override
    public void setBounds() {
        getInputSuccesful().setBounds(250, 120, 250, 25);
        getInputError().setBounds(250, 120, 300, 25);
        getAddButton().setBounds(80, 400, 130, 20);
        getBackButton().setBounds(400, 400, 120, 80);
        enterName.setBounds(10, 90, 250, 25);
        enterSalary.setBounds(10, 120, 250, 25);
        enterHoursWeek.setBounds(10, 150, 250, 25);
        selectRole.setBounds(10, 180, 250, 25);
        textFieldName.setBounds(260, 90, 165, 25);
        textFieldSalary.setBounds(260, 120, 165, 25);
        textFieldHours.setBounds(260, 150, 165, 25);
        scrollPane.setBounds(260, 180, 170, 200);
        summaryPane.setBounds(45, 20, 500, 55);
    }

    @Override
    public void addToFrame() {
        theFrame.add(getAddButton());
        theFrame.add(getBackButton());
        theFrame.add(enterName);
        theFrame.add(textFieldName);
        theFrame.add(enterSalary);
        theFrame.add(textFieldSalary);
        theFrame.add(enterHoursWeek);
        theFrame.add(textFieldHours);
        theFrame.add(selectRole);
        theFrame.add(scrollPane);
        theFrame.add(summaryPane);
    }

}
