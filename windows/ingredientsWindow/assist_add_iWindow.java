package windows.ingredientsWindow;

import java.time.format.DateTimeFormatter;
import iLayouts.placeholderLayoutApplyer;
import java.awt.event.ActionListener;
import javax.swing.DefaultListModel;
import java.awt.event.ActionEvent;
import javax.swing.JToggleButton;
import componentsFood.provider;
import util.abstractUpdater;
import java.time.LocalDate;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JTable;
import javax.swing.JList;

public class assist_add_iWindow extends abstractUpdater {

    private String name;
    private int amount;
    private String price;
    private JTable myTable;
    private JList<provider> theList = new JList<provider>();

    private JButton backButton = new JButton("Back");
    private JButton addButton = new JButton("Add Ingredient");

    private JLabel summaryTXT = new JLabel("Specify the following information:");
    private JLabel enterName = new JLabel("Is this ingredient ACTIVE or NOT: ");
    private JLabel enterEmail = new JLabel("Is this ingredient in inventory: ");
    private JLabel chooseProv = new JLabel("Which provider provides you this ingredient:");

    private JLabel succesful = new JLabel("The ingredient has been successfully added.");
    private JLabel inputError = new JLabel("There is something wrong with the given input.");
    private JToggleButton activeButton = new JToggleButton("Active");
    private JToggleButton inventoryButton = new JToggleButton("With Inventory");

    public assist_add_iWindow(abstractUpdater previousWindow, String name, String amount, String price) {
        super(previousWindow, new placeholderLayoutApplyer(theFrame));
        this.name = name;
        this.amount = Integer.parseInt(amount);
        this.price = price;
    }

    @Override
    public void addComponents() {
        theFrame.setTitle("Adding Ingredient");
        succesful.setBounds(220, 360, 300, 25);
        inputError.setBounds(220, 360, 300, 25);
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
        addButton.setBounds(80, 400, 130, 20);
        theFrame.add(addButton);
        backButton.setBounds(400, 400, 120, 80);
        theFrame.add(backButton);
    }

    @Override
    public void addActionListeners() {
        addButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                int providerID = theList.getSelectedValue().getId();
                boolean inventory = true;
                if (inventoryButton.getText().equals("Without Inventory"))
                    inventory = false;
                boolean active = true;
                if (activeButton.getText().equals("Non Active"))
                    inventory = false;
                LocalDate dateObj = LocalDate.now();
                String date = dateObj.format(DateTimeFormatter.ofPattern("yyyy-MM-dd"));
                if (theManagerDB.addIngredient(providerID, date, name, price, amount, inventory, active)) {
                    printSuccessGUI();
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

}
