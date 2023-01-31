package windows.productsWindow;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.JToggleButton;
import javax.swing.table.DefaultTableModel;

import componentsFood.ingredient;
import componentsFood.product;
import componentsFood.provider;
import util.abstractAddWindow;
import util.abstractUpdater;

public class assist_assist_edit_productWindow extends abstractAddWindow {

    private product theCurrentProduct;

    private JTextField textFieldPrice = new JTextField();
    private JScrollPane scrollPaneTable;
    private JTable myTable;
    private DefaultTableModel model;
    private JLabel summaryTXT = new JLabel("Product to be changed:");
    private JLabel enterPrice = new JLabel("Enter the products's new PRICE: ");
    private JLabel changeLabel = new JLabel("Select the product's new INGREDIENTS: ");
    private JToggleButton changeIngredientsButton = new JToggleButton("Change Ingredients Too");

    private JButton swapLeft = new JButton("Left");
    private JButton swapRight = new JButton("Right");
    private JScrollPane scrollPaneIngredients;
    private JScrollPane scrollPaneSelected;
    private JTable tableIngredients;
    private JTable tableSelected;
    private DefaultTableModel modelIngredients;
    private DefaultTableModel modelSelected;

    public assist_assist_edit_productWindow(abstractUpdater previousWindow, product theCurrentProduct) {
        super(previousWindow, "Product", false);
        this.theCurrentProduct = theCurrentProduct;
        getAddButton().setText("Apply Changes");
    }

    @Override
    public void addComponents() {
        loadTable();
        setTable();
        setBounds();
        addToFrame();
        setBackButton();
    }

    private void showList() {
        theFrame.add(changeLabel);
        theFrame.add(scrollPaneIngredients);
        theFrame.add(scrollPaneSelected);
        theFrame.add(swapRight);
        theFrame.add(swapLeft);
        theFrame.revalidate();
        theFrame.repaint();
    }

    private void hideList() {
        theFrame.remove(changeLabel);
        theFrame.remove(scrollPaneIngredients);
        theFrame.remove(scrollPaneSelected);
        theFrame.remove(swapRight);
        theFrame.remove(swapLeft);
        theFrame.revalidate();
        theFrame.repaint();
    }

    @Override
    public void addActionListeners() {
        getAddButton().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (changeIngredientsButton.getText().equals("Change Ingredients Too")) {
                    String tempPrice = textFieldPrice.getText();
                    if (tempPrice.isEmpty())
                        return;
                    Float price = Float.parseFloat(tempPrice);
                    if (theManagerDB.mediumUpdateProduct(theCurrentProduct, price)) {
                        updateTable();
                        printSuccessGUI();
                    }
                } else {

                }
            }
        });
        changeIngredientsButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (changeIngredientsButton.getText().equals("Change Ingredients Too")) {
                    changeIngredientsButton.setText("Don't Change Ingredients Too");
                    showList();
                } else {
                    changeIngredientsButton.setText("Change Ingredients Too");
                    hideList();
                }
            }
        });
        swapLeft.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                int row = tableIngredients.getSelectedRow();
                if (row == -1)
                    return;
                String ingID = (String) modelIngredients.getValueAt(row, 0);
                String provID = (String) modelIngredients.getValueAt(row, 1);
                String date = (String) modelIngredients.getValueAt(row, 2);
                String name = (String) modelIngredients.getValueAt(row, 3);
                String price = (String) modelIngredients.getValueAt(row, 4);
                String amount = (String) modelIngredients.getValueAt(row, 5);
                String in_inventory = (String) modelIngredients.getValueAt(row, 6);
                String active = (String) modelIngredients.getValueAt(row, 7);
                modelIngredients.removeRow(row);
                modelSelected.addRow(new String[] { ingID, provID, date, name, price, amount, in_inventory, active });
            }
        });
        swapRight.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                int row = tableSelected.getSelectedRow();
                if (row == -1)
                    return;
                String ingID = (String) modelSelected.getValueAt(row, 0);
                String provID = (String) modelSelected.getValueAt(row, 1);
                String date = (String) modelSelected.getValueAt(row, 2);
                String name = (String) modelSelected.getValueAt(row, 3);
                String price = (String) modelSelected.getValueAt(row, 4);
                String amount = (String) modelSelected.getValueAt(row, 5);
                String in_inventory = (String) modelIngredients.getValueAt(row, 6);
                String active = (String) modelIngredients.getValueAt(row, 7);
                modelSelected.removeRow(row);
                modelIngredients
                        .addRow(new String[] { ingID, provID, date, name, price, amount, in_inventory, active });
            }
        });

    }

    private void updateTable() {
        theCurrentProduct = theManagerDB.getProduct(theCurrentProduct.getId());
        model.removeRow(0);
        String id = Integer.toString(theCurrentProduct.getId());
        String date = theCurrentProduct.getDate();
        String name = theCurrentProduct.getName();
        String price = Float.toString(theCurrentProduct.getPrice());
        String active = "No";
        if (theCurrentProduct.getActive())
            active = "Yes";
        model.addRow(new String[] { id, date, name, price, active });
        myTable.revalidate();
        myTable.repaint();
    }

    private void loadTable() {
        myTable = new JTable();
        model = new DefaultTableModel(
                new String[] { "product_id", "Active Since", "Name", "Price", "Active" }, 0);
        myTable.setModel(model);
        String id = Integer.toString(theCurrentProduct.getId());
        String date = theCurrentProduct.getDate();
        String name = theCurrentProduct.getName();
        String price = Float.toString(theCurrentProduct.getPrice());
        String active = "No";
        if (theCurrentProduct.getActive())
            active = "Yes";
        model.addRow(new String[] { id, date, name, price, active });
        myTable.setDefaultEditor(Object.class, null);
        myTable.setFocusable(true);
        myTable.removeColumn(myTable.getColumn("product_id"));
        scrollPaneTable = new JScrollPane(myTable, JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED,
                JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
    }

    @Override
    public void setBounds() {
        getInputSuccesful().setBounds(230, 300, 300, 25);
        getInputError().setBounds(230, 300, 300, 25);
        getBackButton().setBounds(400, 460, 120, 80);
        getAddButton().setBounds(80, 500, 180, 20);
        summaryTXT.setBounds(200, 20, 250, 25);
        textFieldPrice.setBounds(270, 170, 165, 25);
        enterPrice.setBounds(10, 170, 220, 25);
        scrollPaneTable.setBounds(45, 60, 500, 55);
        changeIngredientsButton.setBounds(45, 130, 500, 25);
        changeLabel.setBounds(10, 200, 270, 25);
        scrollPaneIngredients.setBounds(320, 240, 170, 200);
        scrollPaneSelected.setBounds(50, 240, 170, 200);
        swapLeft.setBounds(230, 300, 80, 25);
        swapRight.setBounds(230, 340, 80, 25);
    }

    private void setTable() {
        tableIngredients = new JTable();
        tableIngredients.setDefaultEditor(Object.class, null);
        tableSelected = new JTable();
        tableSelected.setDefaultEditor(Object.class, null);
        modelIngredients = new DefaultTableModel(
                new String[] { "ingredient_id", "provider_id", "date", "Name", "Price", "Amount", "in_inventory",
                        "active" },
                0);
        modelSelected = new DefaultTableModel(
                new String[] { "ingredient_id", "provider_id", "date", "Name", "Price", "Amount", "in_inventory",
                        "active" },
                0);

        for (ingredient tempIngredient : theManagerDB.getAllCurrentIngredients()) {
            String ingID = Integer.toString(tempIngredient.getId());
            String provID = Integer.toString(tempIngredient.getProviderID());
            String date = tempIngredient.getDate();
            String name = tempIngredient.getName();
            String price = Float.toString(tempIngredient.getPrice());
            String amount = Integer.toString(tempIngredient.getAmount());
            String in_inventory = "No";
            if (tempIngredient.getInInventory())
                in_inventory = "Yes";
            String active = "No";
            if (tempIngredient.getActive())
                active = "Yes";

            modelIngredients.addRow(new String[] { ingID, provID, date, name, price, amount, in_inventory, active });
        }

        tableIngredients.setModel(modelIngredients);
        tableSelected.setModel(modelSelected);
        tableIngredients.removeColumn(tableIngredients.getColumn("ingredient_id"));
        tableIngredients.removeColumn(tableIngredients.getColumn("provider_id"));
        tableIngredients.removeColumn(tableIngredients.getColumn("date"));
        tableIngredients.removeColumn(tableIngredients.getColumn("in_inventory"));
        tableIngredients.removeColumn(tableIngredients.getColumn("active"));

        tableSelected.removeColumn(tableSelected.getColumn("ingredient_id"));
        tableSelected.removeColumn(tableSelected.getColumn("provider_id"));
        tableSelected.removeColumn(tableSelected.getColumn("date"));
        tableSelected.removeColumn(tableSelected.getColumn("in_inventory"));
        tableSelected.removeColumn(tableSelected.getColumn("active"));

        scrollPaneIngredients = new JScrollPane(tableIngredients, JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED,
                JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
        scrollPaneSelected = new JScrollPane(tableSelected, JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED,
                JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
    }

    @Override
    public void addToFrame() {
        theFrame.add(getBackButton());
        theFrame.add(getAddButton());
        theFrame.add(summaryTXT);
        theFrame.add(scrollPaneTable);
        theFrame.add(textFieldPrice);
        theFrame.add(enterPrice);
        theFrame.add(changeIngredientsButton);
    }

}
