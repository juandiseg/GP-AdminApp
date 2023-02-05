package navigation.administration.employees_Window.employees_eWindow;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JTextField;

import componentsFood.role;
import navigation.administration.employees_Window.employeesAPI;
import util.abstractAddWindow;
import util.abstractUpdater;

import javax.swing.DefaultListModel;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JScrollPane;

public class add_eWindow extends abstractAddWindow {

    private JLabel enterName = new JLabel("Enter the employee's NAME: ");
    private JLabel enterSalary = new JLabel("Enter the employee's SALARY: ");
    private JLabel enterHoursWeek = new JLabel("Enter the employee's Hours/Week: ");
    private JLabel selectRole = new JLabel("Select the employee's ROLE: ");

    private JTextField textFieldName = new JTextField();
    private JTextField textFieldSalary = new JTextField();
    private JTextField textFieldHours = new JTextField();

    private JList<role> listRoles = new JList<role>();
    private JScrollPane scrollPane;

    public add_eWindow(abstractUpdater previousWindow) {
        super(previousWindow, "Ingredient", true);
    }

    @Override
    public void addComponents() {
        setList();
        setBounds();
        addToFrame();
        setBackButton();
    }

    @Override
    public void addActionListeners() {
        getAddButton().addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                String name = textFieldName.getText();
                float salary = Float.parseFloat(textFieldSalary.getText());
                String hours = textFieldHours.getText();
                int roleID = listRoles.getSelectedValue().getId();
                if (new employeesAPI().addEmployee(name, salary, hours, roleID)) {
                    updateToPreviousMenu();
                }
            }
        });
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
        enterName.setBounds(10, 20, 200, 25);
        enterSalary.setBounds(10, 50, 200, 25);
        enterHoursWeek.setBounds(10, 80, 200, 25);
        selectRole.setBounds(10, 110, 200, 25);

        textFieldName.setBounds(210, 20, 165, 25);
        textFieldSalary.setBounds(210, 50, 165, 25);
        textFieldHours.setBounds(210, 80, 165, 25);
        scrollPane.setBounds(210, 110, 170, 200);

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
    }

}
