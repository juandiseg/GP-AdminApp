package navigation.administration.employees_Window.roles_eWindow;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;

import componentsFood.provider;
import componentsFood.role;
import navigation.administration.employees_Window.employeesAPI;
import util.abstractAddWindow;
import util.abstractUpdater;
import javax.swing.JLabel;
import javax.swing.*;

public class assist_edit_rWindow extends abstractAddWindow {

    private employeesAPI theManagerDB = new employeesAPI();
    private JLabel enterName = new JLabel("Enter the new Role's NAME: ");
    private JTextField textFieldName = new JTextField();
    private JTable myTable;
    private DefaultTableModel model;
    private JScrollPane scrollPane;

    private role theCurrentRole;

    private JLabel summaryTXT = new JLabel("Role to be changed:");

    public assist_edit_rWindow(abstractUpdater previousWindow, role theCurrentRole) {
        super(previousWindow, "Role", false);
        this.theCurrentRole = theCurrentRole;
    }

    @Override
    public void addComponents() {
        loadTable();
        setBounds();
        addToFrame();
        setBackButton();
    }

    @Override
    public void addActionListeners() {
        getAddButton().addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                String name = textFieldName.getText();
                if (name.isEmpty())
                    return;
                theManagerDB.updateRoleName(theCurrentRole.getId(), name);
                theCurrentRole = theManagerDB.getRole(theCurrentRole.getId());
                model.removeRow(0);
                model.addRow(new String[] { Integer.toString(theCurrentRole.getId()), theCurrentRole.getName() });
            }
        });
    }

    private void loadTable() {
        myTable = new JTable();
        model = new DefaultTableModel(new String[] { "id", "Name" }, 0);
        myTable.setModel(model);
        model.addRow(new Object[] { theCurrentRole.getId(), theCurrentRole.getName() });
        myTable.removeColumn(myTable.getColumn("id"));
        myTable.setDefaultEditor(Object.class, null);
        myTable.setFocusable(true);
        scrollPane = new JScrollPane(myTable);
        scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);
    }

    @Override
    public void setBounds() {
        getInputSuccesful().setBounds(250, 175, 250, 25);
        getInputError().setBounds(250, 175, 300, 25);
        getBackButton().setBounds(400, 400, 120, 80);
        getAddButton().setBounds(80, 175, 130, 20);
        textFieldName.setBounds(200, 105, 165, 25);
        summaryTXT.setBounds(200, 20, 250, 25);
        enterName.setBounds(10, 105, 220, 25);
        scrollPane.setBounds(45, 60, 500, 40);
    }

    @Override
    public void addToFrame() {
        theFrame.add(getAddButton());
        theFrame.add(getBackButton());
        theFrame.add(textFieldName);
        theFrame.add(summaryTXT);
        theFrame.add(enterName);
        theFrame.add(scrollPane);
    }

}
