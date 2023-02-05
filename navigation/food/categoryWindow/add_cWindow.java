package navigation.food.categoryWindow;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JTextField;
import javax.swing.JToggleButton;
import util.abstractAddWindow;
import util.abstractUpdater;
import javax.swing.JLabel;

public class add_cWindow extends abstractAddWindow {

    private categoryAPI theManagerDB = new categoryAPI();
    private JLabel enterName = new JLabel("Enter the category's NAME: ");
    private JTextField textFieldName = new JTextField();
    private JLabel chooseType = new JLabel("Select the categories TYPE: ");
    private JToggleButton chooseButton = new JToggleButton("Product");

    public add_cWindow(abstractUpdater previousWindow) {
        super(previousWindow, "Provider", true);
        getAddButton().setText("Add Category");
    }

    @Override
    public void addToFrame() {
        theFrame.add(enterName);
        theFrame.add(textFieldName);
        theFrame.add(chooseType);
        theFrame.add(getAddButton());
        theFrame.add(getBackButton());
        theFrame.add(chooseButton);
    }

    @Override
    public void setBounds() {
        getAddButton().setBounds(80, 90, 130, 20);
        getBackButton().setBounds(400, 400, 120, 80);
        getInputSuccesful().setBounds(250, 90, 250, 25);
        getInputError().setBounds(250, 90, 300, 25);
        enterName.setBounds(10, 20, 160, 25);
        textFieldName.setBounds(190, 20, 170, 25);
        chooseType.setBounds(10, 50, 180, 25);
        chooseButton.setBounds(190, 50, 170, 25);
    }

    @Override
    public void addActionListeners() {
        getAddButton().addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                String name = textFieldName.getText();
                if (name.isEmpty())
                    return;
                Boolean type = chooseButton.getText().equals("Product");
                if (theManagerDB.addCategory(name, type)) {
                    printSuccessGUI();
                } else {
                    printErrorGUI();
                }
            }
        });
        chooseButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                if (chooseButton.getText().equals("Product"))
                    chooseButton.setText("Menu");
                else
                    chooseButton.setText("Product");
            }
        });
    }

}
