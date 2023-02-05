package windows.ingredientsWindow;

import javax.swing.table.DefaultTableModel;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

import componentsFood.ingredient;
import util.abstractAddWindow;
import util.abstractUpdater;
import windows.providersWindow.providerAPI;

import javax.swing.*;

public class assist_edit_iWindow extends abstractAddWindow {

    private ingredientsAPI theManagerDB = new ingredientsAPI();

    protected ingredient theCurrentIngredient;
    private JTextField textFieldName = new JTextField();
    private JScrollPane scrollPane;
    private JTable myTable;
    private DefaultTableModel model;
    private JLabel summaryTXT = new JLabel("Ingredient to be changed:");
    private JLabel enterName = new JLabel("Enter the ingredient's new NAME: ");
    private JLabel enterInventory = new JLabel("Enter the ingredient's new INVETORY state: ");
    private JButton editOtherAttributes = new JButton("See more changes");
    private JToggleButton inventoryButton = new JToggleButton("With Inventory");

    public assist_edit_iWindow(abstractUpdater previousWindow, ingredient theCurrentIngredient) {
        super(previousWindow, "Ingredient", false);
        this.theCurrentIngredient = theCurrentIngredient;
        getAddButton().setText("Apply Changes");
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
                String newName = textFieldName.getText();
                if (!newName.isEmpty())
                    theManagerDB.updateName(theCurrentIngredient.getId(), newName);
                boolean inventory = true;
                if (inventoryButton.getText().equals("Without Inventory"))
                    inventory = false;
                if (inventory != theCurrentIngredient.getInInventory())
                    theManagerDB.updateInInventory(theCurrentIngredient.getId(), inventory);
                updateTable();
            }
        });
        abstractUpdater temp = this;
        editOtherAttributes.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                assist_assist_edit_iWindow tempWdw = new assist_assist_edit_iWindow(temp, theCurrentIngredient);
                tempWdw.updateToThisMenu();
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

    private void updateTable() {
        theCurrentIngredient = theManagerDB.getIngredient(theCurrentIngredient.getId());
        model.removeRow(0);
        String id = Integer.toString(theCurrentIngredient.getId());
        String prov_id = Integer.toString(theCurrentIngredient.getProviderID());
        String price = Float.toString(theCurrentIngredient.getPrice());
        String amount = Float.toString(theCurrentIngredient.getAmount());
        String in_inventory = "Yes";
        String active = "Yes";
        if (!theCurrentIngredient.getInInventory())
            in_inventory = "No";
        if (!theCurrentIngredient.getActive())
            active = "No";
        model.addRow(new String[] { id, theCurrentIngredient.getName(),
                new providerAPI().getProvider(Integer.parseInt(prov_id)).getName(),
                theCurrentIngredient.getDate(), price,
                amount, in_inventory, active });
    }

    private void loadTable() {
        myTable = new JTable();
        model = new DefaultTableModel(
                new String[] { "ID", "Name", "Provider", "date", "Price", "Amount", "Inventory" },
                0);
        myTable.setModel(model);
        String id = Integer.toString(theCurrentIngredient.getId());
        String prov_id = Integer.toString(theCurrentIngredient.getProviderID());
        String price = Float.toString(theCurrentIngredient.getPrice());
        String amount = Float.toString(theCurrentIngredient.getAmount());
        String in_inventory;
        if (theCurrentIngredient.getInInventory())
            in_inventory = "Yes";
        else
            in_inventory = "No";
        model.addRow(new String[] { id, theCurrentIngredient.getName(),
                new providerAPI().getProvider(Integer.parseInt(prov_id)).getName(),
                theCurrentIngredient.getDate(), price,
                amount, in_inventory });
        myTable.setDefaultEditor(Object.class, null);
        myTable.setFocusable(true);
        myTable.removeColumn(myTable.getColumn("ID"));
        myTable.removeColumn(myTable.getColumn("date"));

        scrollPane = new JScrollPane(myTable, JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED,
                JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
    }

    @Override
    public void setBounds() {
        getInputSuccesful().setBounds(230, 300, 300, 25);
        getInputError().setBounds(230, 300, 300, 25);
        getBackButton().setBounds(400, 400, 120, 80);
        getAddButton().setBounds(80, 400, 180, 20);
        editOtherAttributes.setBounds(80, 430, 180, 20);
        summaryTXT.setBounds(200, 20, 250, 25);
        textFieldName.setBounds(270, 130, 165, 25);
        enterName.setBounds(10, 130, 220, 25);
        scrollPane.setBounds(45, 60, 500, 55);
        inventoryButton.setBounds(270, 160, 170, 25);
        enterInventory.setBounds(10, 160, 270, 25);
    }

    @Override
    public void addToFrame() {
        theFrame.add(getBackButton());
        theFrame.add(getAddButton());
        theFrame.add(textFieldName);
        theFrame.add(summaryTXT);
        theFrame.add(enterName);
        theFrame.add(scrollPane);
        theFrame.add(inventoryButton);
        theFrame.add(enterInventory);
        theFrame.add(editOtherAttributes);
    }

}
