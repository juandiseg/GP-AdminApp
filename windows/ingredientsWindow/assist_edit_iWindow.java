package windows.ingredientsWindow;

import iLayouts.placeholderLayoutApplyer;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;

import componentsFood.provider;
import util.abstractUpdater;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.*;

public class assist_edit_iWindow extends abstractUpdater {

    private provider theCurrentProvider;
    private JTextField textFieldName = new JTextField();
    private JTextField textFieldEmail = new JTextField();
    private JTable myTable;
    JLabel succesful = new JLabel("The provider has been successfully edited.");
    JLabel inputError = new JLabel("There is something wrong with the given input.");

    public assist_edit_iWindow(abstractUpdater previousWindow, int ID) {
        super(previousWindow, new placeholderLayoutApplyer(theFrame));
        theCurrentProvider = theManagerDB.getProvider(ID);
    }

    @Override
    public void addComponents() {
        theFrame.setTitle("Specify Changes");
        succesful.setBounds(250, 160, 250, 25);
        inputError.setBounds(250, 160, 300, 25);
        JLabel summaryTXT = new JLabel("Provider to be changed:");
        summaryTXT.setBounds(200, 20, 250, 25);
        theFrame.add(summaryTXT);
        JLabel enterName = new JLabel("Enter the new provider's NAME: ");
        enterName.setBounds(10, 90, 220, 25);
        textFieldName.setBounds(200, 90, 165, 25);
        JLabel enterEmail = new JLabel("Enter the new provider's EMAIL: ");
        enterEmail.setBounds(10, 120, 220, 25);
        textFieldEmail.setBounds(200, 120, 165, 25);
        theFrame.add(enterName);
        theFrame.add(textFieldName);
        theFrame.add(enterEmail);
        theFrame.add(textFieldEmail);
        loadTable();
        JButton addButton = new JButton("Edit Provider");
        addButton.setBounds(80, 160, 130, 20);
        theFrame.add(addButton);
        addToButtonList(addButton);
        JButton backButton = new JButton("Back");
        backButton.setBounds(400, 400, 120, 80);
        theFrame.add(backButton);
        addToButtonList(backButton);
    }

    @Override
    public void addActionListeners() {
        getButtonList().get(0).addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                String name = textFieldName.getText();
                String email = textFieldEmail.getText();
                if (name.isEmpty() && email.isEmpty()) {
                    printErrorGUI();
                    return;
                } else if (name.isEmpty() || email.isEmpty()) {
                    if (name.isEmpty())
                        theManagerDB.editProvider(theCurrentProvider.getId(), theCurrentProvider.getName(), email);
                    else
                        theManagerDB.editProvider(theCurrentProvider.getId(), name, theCurrentProvider.getEmail());
                    printSuccessGUI();
                    updateFrame(theCurrentProvider.getId());
                    return;
                }
                if (theManagerDB.editProvider(theCurrentProvider.getId(), name, email)) {
                    printSuccessGUI();
                    updateFrame(theCurrentProvider.getId());
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

    private void updateFrame(int id) {
        theCurrentProvider = theManagerDB.getProvider(id);
        loadTable();
        theFrame.revalidate();
    }

    private void printErrorGUI() {
        theFrame.remove(succesful);
        theFrame.add(inputError);
        theFrame.repaint();
    }

    private void printSuccessGUI() {
        theFrame.remove(inputError);
        theFrame.add(succesful);
        theFrame.remove(myTable);
    }

    private void loadTable() {
        myTable = new JTable();
        DefaultTableModel model = new DefaultTableModel(new String[] { "ID", "Name", "Email" }, 0);
        myTable.setModel(model);
        model.addRow(new Object[] { theCurrentProvider.getId(), theCurrentProvider.getName(),
                theCurrentProvider.getEmail() });
        myTable.setBounds(45, 60, 500, 15);
        myTable.setDefaultEditor(Object.class, null);
        myTable.setFocusable(true);
        theFrame.add(myTable);
    }

}
