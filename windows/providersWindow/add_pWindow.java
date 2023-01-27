package windows.providersWindow;

import iLayouts.placeholderLayoutApplyer;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JTextField;
import util.abstractUpdater;
import javax.swing.JButton;
import javax.swing.JLabel;

public class add_pWindow extends abstractUpdater {

    private JTextField textFieldName = new JTextField();
    private JTextField textFieldEmail = new JTextField();
    JLabel succesful = new JLabel("The provider has been successfully added.");
    JLabel inputError = new JLabel("There is something wrong with the given input.");

    public add_pWindow(abstractUpdater previousWindow) {
        super(previousWindow, new placeholderLayoutApplyer(theFrame));
    }

    @Override
    public void addComponents() {
        theFrame.setTitle("Add Provider");
        succesful.setBounds(250, 90, 250, 25);
        inputError.setBounds(250, 90, 300, 25);
        JLabel enterName = new JLabel("Enter the provider's NAME: ");
        enterName.setBounds(10, 20, 160, 25);
        textFieldName.setBounds(180, 20, 165, 25);
        JLabel enterEmail = new JLabel("Enter the provider's EMAIL: ");
        enterEmail.setBounds(10, 50, 160, 25);
        textFieldEmail.setBounds(180, 50, 165, 25);
        theFrame.add(enterName);
        theFrame.add(textFieldName);
        theFrame.add(enterEmail);
        theFrame.add(textFieldEmail);
        JButton addButton = new JButton("Add Provider");
        addButton.setBounds(80, 90, 130, 20);
        theFrame.add(addButton);
        addToButtonList(addButton);
        JButton backButton = new JButton("Back");
        backButton.setBounds(400, 400, 120, 80);
        theFrame.add(backButton);
        addToButtonList(backButton);
    }

    @Override
    public void addActionListeners() {
        abstractUpdater temp = this;
        getButtonList().get(0).addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                String name = textFieldName.getText();
                String email = textFieldEmail.getText();
                if (name.isEmpty() || email.isEmpty()) {
                    printErrorGUI();
                    return;
                }
                if (theManagerDB.addProvider(name, email)) {
                    theFrame.remove(inputError);
                    theFrame.add(succesful);
                    theFrame.repaint();
                } else {
                    printErrorGUI();
                }
            }
        });
        getButtonList().get(1).addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                updateToPreviousMenu();
            }
        });
    }

    private void printErrorGUI() {
        theFrame.remove(succesful);
        theFrame.add(inputError);
        theFrame.repaint();
    }

}
