package windows.ingredientsWindow.allergensWindow;

import iLayouts.placeholderLayoutApplyer;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JTextField;
import util.abstractUpdater;
import javax.swing.JButton;
import javax.swing.JLabel;

public class add_aWindow extends abstractUpdater {

    private JTextField textFieldName = new JTextField();
    JLabel succesful = new JLabel("The allergen has been successfully added.");
    JLabel inputError = new JLabel("There is something wrong with the given input.");

    public add_aWindow(abstractUpdater previousWindow) {
        super(previousWindow, new placeholderLayoutApplyer(theFrame));
    }

    @Override
    public void addComponents() {
        theFrame.setTitle("Add Alergen");
        succesful.setBounds(250, 90, 250, 25);
        inputError.setBounds(250, 90, 300, 25);
        JLabel enterName = new JLabel("Enter the allergen's NAME: ");
        enterName.setBounds(10, 20, 160, 25);
        textFieldName.setBounds(180, 20, 165, 25);
        theFrame.add(enterName);
        theFrame.add(textFieldName);
        JButton addButton = new JButton("Add Alergen");
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
                if (name.isEmpty()) {
                    printErrorGUI();
                    return;
                }
                if (theManagerDB.addAllergen(name)) {
                    printSuccessGUI();
                    return;
                }
                printErrorGUI();
                return;
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

    private void printSuccessGUI() {
        theFrame.remove(inputError);
        theFrame.add(succesful);
        theFrame.repaint();
    }

}
