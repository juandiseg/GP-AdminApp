package navigation.administration.employees_Window.roles_eWindow;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JTextField;

import navigation.administration.employees_Window.employeesAPI;
import util.abstractAddWindow;
import util.abstractUpdater;
import javax.swing.JLabel;

public class add_rWindow extends abstractAddWindow {

    private employeesAPI theManagerDB = new employeesAPI();
    private JLabel enterName = new JLabel("Enter the Role's NAME: ");
    private JTextField textFieldName = new JTextField();

    public add_rWindow(abstractUpdater previousWindow) {
        super(previousWindow, "Role", true);
    }

    @Override
    public void addToFrame() {
        theFrame.add(enterName);
        theFrame.add(textFieldName);
        theFrame.add(getAddButton());
        theFrame.add(getBackButton());
    }

    @Override
    public void setBounds() {
        getAddButton().setBounds(80, 90, 130, 20);
        getBackButton().setBounds(400, 400, 120, 80);
        getInputSuccesful().setBounds(250, 90, 250, 25);
        getInputError().setBounds(250, 90, 300, 25);
        enterName.setBounds(10, 20, 160, 25);
        textFieldName.setBounds(180, 20, 165, 25);
    }

    @Override
    public void addActionListeners() {
        getAddButton().addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                String name = textFieldName.getText();
                if (name.isEmpty()) {
                    printErrorGUI();
                    return;
                }
                if (theManagerDB.addRole(name)) {
                    printSuccessGUI();
                } else {
                    printErrorGUI();
                }
            }
        });
    }

}
