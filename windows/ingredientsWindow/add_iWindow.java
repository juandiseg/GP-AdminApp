package windows.ingredientsWindow;

import iLayouts.placeholderLayoutApplyer;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JTextField;
import util.abstractUpdater;
import javax.swing.JButton;
import javax.swing.JLabel;

public class add_iWindow extends abstractUpdater {

    private JLabel enterName = new JLabel("Enter the ingredient's NAME: ");
    private JLabel enterPrice = new JLabel("Enter the ingredients's PRICE: ");
    private JLabel enterAmount = new JLabel("Enter how many KGs per 'PRICE': ");
    private JTextField textFieldName = new JTextField();
    private JTextField textFieldPrice = new JTextField();
    private JTextField textFieldAmount = new JTextField();

    private JButton backButton = new JButton("Back");
    private JButton addButton = new JButton("Add Ingredient");

    private JLabel succesful = new JLabel("The provider has been successfully added.");
    private JLabel inputError = new JLabel("There is something wrong with the given input.");

    public add_iWindow(abstractUpdater previousWindow) {
        super(previousWindow, new placeholderLayoutApplyer(theFrame));
    }

    @Override
    public void addComponents() {
        theFrame.setTitle("Add Provider");
        succesful.setBounds(250, 120, 250, 25);
        inputError.setBounds(250, 120, 300, 25);

        enterName.setBounds(10, 20, 200, 25);
        textFieldName.setBounds(210, 20, 165, 25);
        enterPrice.setBounds(10, 50, 200, 25);
        textFieldPrice.setBounds(210, 50, 165, 25);
        enterAmount.setBounds(10, 80, 200, 25);
        textFieldAmount.setBounds(210, 80, 165, 25);

        theFrame.add(enterName);
        theFrame.add(textFieldName);
        theFrame.add(enterPrice);
        theFrame.add(textFieldPrice);
        theFrame.add(enterAmount);
        theFrame.add(textFieldAmount);

        addButton.setBounds(80, 120, 130, 20);
        theFrame.add(addButton);
        backButton.setBounds(400, 400, 120, 80);
        theFrame.add(backButton);
    }

    @Override
    public void addActionListeners() {
        abstractUpdater temp = this;
        addButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                String name = textFieldName.getText();
                String price = textFieldPrice.getText();
                String amount = textFieldAmount.getText();
                if (checkInput(name, price, amount)) {
                    assist_add_iWindow tempWdw = new assist_add_iWindow(temp, name, amount, price);
                    tempWdw.updateToThisMenu();
                }
            }
        });
        backButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                updateToPreviousMenu();
            }
        });
    }

    private boolean checkInput(String name, String price, String amount) {
        if (name.isEmpty() || price.isEmpty() || amount.isEmpty()) {
            printErrorGUI();
            return false;
        }
        return true;
    }

    private void printErrorGUI() {
        theFrame.remove(succesful);
        theFrame.add(inputError);
        theFrame.repaint();
    }

}
