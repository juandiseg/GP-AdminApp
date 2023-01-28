package windows.ingredientsWindow;

import iLayouts.placeholderLayoutApplyer;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.awt.event.ActionEvent;
import util.abstractUpdater;
import javax.swing.JButton;
import javax.swing.JLabel;

import componentsFood.provider;

import javax.swing.*;

public class assist_add_iWindow extends abstractUpdater {

    private String name;
    private String amount;
    private String price;
    private JTable myTable;
    private JList<provider> theList = new JList<provider>();

    private JLabel summaryTXT = new JLabel("Specify the following information:");
    private JLabel enterName = new JLabel("Is this ingredient ACTIVE or NOT: ");
    private JLabel enterEmail = new JLabel("Is this ingredient in inventory: ");
    private JLabel chooseProv = new JLabel("Which provider provides you this ingredient:");

    private JLabel succesful = new JLabel("The provider has been successfully edited.");
    private JLabel inputError = new JLabel("There is something wrong with the given input.");
    private JToggleButton activeButton = new JToggleButton("Active");
    private JToggleButton inventoryButton = new JToggleButton("With Inventory");

    public assist_add_iWindow(abstractUpdater previousWindow, String name, String amount, String price) {
        super(previousWindow, new placeholderLayoutApplyer(theFrame));
        this.name = name;
        this.amount = amount;
        this.price = price;
    }

    @Override
    public void addComponents() {
        theFrame.setTitle("Adding Ingredient");
        succesful.setBounds(250, 130, 250, 25);
        inputError.setBounds(250, 130, 300, 25);
        summaryTXT.setBounds(200, 20, 250, 25);
        enterName.setBounds(10, 60, 220, 25);
        activeButton.setBounds(240, 60, 170, 25);
        enterEmail.setBounds(10, 90, 220, 25);
        inventoryButton.setBounds(240, 90, 170, 25);
        chooseProv.setBounds(10, 120, 280, 25);
        theFrame.add(summaryTXT);
        theFrame.add(enterName);
        theFrame.add(enterEmail);
        theFrame.add(chooseProv);
        theFrame.add(activeButton);
        theFrame.add(inventoryButton);

        DefaultListModel<provider> listModel = new DefaultListModel<provider>();
        for (provider tempProv : theManagerDB.getAllProviders())
            listModel.addElement(tempProv);
        theList.setModel(listModel);
        theList.setBounds(240, 150, 170, 200);
        theFrame.add(theList);
        JButton addButton = new JButton("Add Ingredient");
        addButton.setBounds(80, 400, 130, 20);
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

                System.out.println(theList.getSelectedValue().getId());
            }
        });
        getButtonList().get(1).addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                updateToPreviousMenu();
            }
        });
        activeButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                if (activeButton.getText().equals("Active"))
                    activeButton.setText("Non Active");
                else
                    activeButton.setText("Active");
            }
        });
        inventoryButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                if (inventoryButton.getText().equals("With Inventory"))
                    inventoryButton.setText("Without Inventory");
                else
                    inventoryButton.setText("With Inventory");
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
        theFrame.remove(myTable);
    }

    private void loadTable() {
        /*
         * myTable = new JTable();
         * DefaultTableModel model = new DefaultTableModel(new String[] { "ID", "Name",
         * "Email" }, 0);
         * myTable.setModel(model);
         * model.addRow(new Object[] { theCurrentProvider.getId(),
         * theCurrentProvider.getName(),
         * theCurrentProvider.getEmail() });
         * myTable.setBounds(45, 60, 500, 15);
         * myTable.setDefaultEditor(Object.class, null);
         * myTable.setFocusable(true);
         * theFrame.add(myTable);
         */
    }

}
