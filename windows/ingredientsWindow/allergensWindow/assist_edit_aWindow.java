package windows.ingredientsWindow.allergensWindow;

import iLayouts.placeholderLayoutApplyer;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;

import componentsFood.allergen;
import componentsFood.provider;
import util.abstractUpdater;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.*;

public class assist_edit_aWindow extends abstractUpdater {

    private allergen theCurrentAllergen;
    private JTextField textFieldName = new JTextField();
    private JTable myTable;
    JLabel succesful = new JLabel("The allergen has been successfully edited.");
    JLabel inputError = new JLabel("There is something wrong with the given input.");

    private JButton backButton = new JButton("Back");
    private JButton addButton = new JButton("Edit Allergen");

    public assist_edit_aWindow(abstractUpdater previousWindow, int ID) {
        super(previousWindow, new placeholderLayoutApplyer(theFrame));
        theCurrentAllergen = theManagerDB.getAllergen(ID);
    }

    @Override
    public void addComponents() {
        theFrame.setTitle("Specify Changes");
        succesful.setBounds(250, 160, 250, 25);
        inputError.setBounds(250, 160, 300, 25);
        JLabel summaryTXT = new JLabel("Allergen to be changed:");
        summaryTXT.setBounds(200, 20, 250, 25);
        theFrame.add(summaryTXT);
        JLabel enterName = new JLabel("Enter the new allergen's NAME: ");
        enterName.setBounds(10, 90, 220, 25);
        textFieldName.setBounds(200, 90, 165, 25);
        theFrame.add(enterName);
        theFrame.add(textFieldName);
        loadTable();
        addButton.setBounds(80, 160, 130, 20);
        theFrame.add(addButton);
        backButton.setBounds(400, 400, 120, 80);
        theFrame.add(backButton);
    }

    @Override
    public void addActionListeners() {
        addButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                String name = textFieldName.getText();
                if (name.isEmpty()) {
                    printErrorGUI();
                    return;
                }
                if (theManagerDB.editAllergen(theCurrentAllergen.getId(), name)) {
                    printSuccessGUI();
                    updateFrame(theCurrentAllergen.getId());
                } else {
                    printErrorGUI();
                }

            }
        });
        backButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                updateToPreviousMenu();
            }
        });
    }

    private void updateFrame(int id) {
        theCurrentAllergen = theManagerDB.getAllergen(id);
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
        model.addRow(new Object[] { theCurrentAllergen.getId(), theCurrentAllergen.getName() });
        myTable.setBounds(45, 60, 500, 15);
        myTable.setDefaultEditor(Object.class, null);
        myTable.setFocusable(true);
        theFrame.add(myTable);
    }

}
