package windows.productsWindow;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Stack;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.JToggleButton;
import javax.swing.table.DefaultTableModel;

import componentsFood.category;
import componentsFood.ingredient;
import componentsFood.product;
import util.abstractAddWindow;
import util.abstractUpdater;
import windows.categoryWindow.categoryAPI;
import windows.ingredientsWindow.ingredientsAPI;

public class assist_assist_edit_productWindow extends abstractAddWindow {

    private productAPI theManagerDB = new productAPI();
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
                String tempPrice = textFieldPrice.getText();
                if (!tempPrice.isEmpty()) {
                    float price = Float.parseFloat(tempPrice);
                    theManagerDB.updatePrice(theCurrentProduct.getId(), price);
                    theCurrentProduct = theManagerDB.getProduct(theCurrentProduct.getId());
                }
                if (changeIngredientsButton.getText().equals("Don't Change Ingredients Too")) {
                    int productID = theCurrentProduct.getId();
                    Stack<Integer> stackIDs = new Stack<Integer>();
                    Stack<Integer> stackQtys = new Stack<Integer>();
                    for (int i = 0; i < modelSelected.getRowCount(); i++) {
                        stackIDs.push(Integer.parseInt((String) modelSelected.getValueAt(i, 0)));
                        stackQtys.push(Integer.parseInt((String) modelSelected.getValueAt(i, 6)));
                    }
                    theManagerDB.updateIngredients(productID, stackIDs, stackQtys);
                }
                updateTable();
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
                String entAmount = (String) modelIngredients.getValueAt(row, 6);
                String in_inventory = (String) modelIngredients.getValueAt(row, 7);
                String active = (String) modelIngredients.getValueAt(row, 8);
                modelIngredients.removeRow(row);
                modelSelected
                        .addRow(new String[] { ingID, provID, date, name, price, amount, entAmount, in_inventory,
                                active });
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
                String entAmount = (String) modelSelected.getValueAt(row, 6);
                String in_inventory = (String) modelSelected.getValueAt(row, 7);
                String active = (String) modelSelected.getValueAt(row, 8);
                modelSelected.removeRow(row);
                modelIngredients
                        .addRow(new String[] { ingID, provID, date, name, price, amount, entAmount, in_inventory,
                                active });
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
        category tempCategory = new categoryAPI().getCategoryOfProduct(theCurrentProduct.getId());
        model.addRow(new String[] { id, date, name, price, tempCategory.getName() });
    }

    private void loadTable() {
        myTable = new JTable();
        model = new DefaultTableModel(
                new String[] { "product_id", "Active Since", "Name", "Price" }, 0);
        myTable.setModel(model);
        String id = Integer.toString(theCurrentProduct.getId());
        String date = theCurrentProduct.getDate();
        String name = theCurrentProduct.getName();
        String price = Float.toString(theCurrentProduct.getPrice());
        model.addRow(new String[] { id, date, name, price });
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

    private void loadTable(boolean alreadySelected, JTable tempTable, DefaultTableModel tempModel) {
        if (!alreadySelected)
            tempTable.setDefaultEditor(Object.class, null);
        ArrayList<ingredient> tempIngredients = new ArrayList<ingredient>();
        if (alreadySelected)
            tempIngredients = new ingredientsAPI().getSelectedIngredientsInProduct(theCurrentProduct);
        else
            tempIngredients = new ingredientsAPI().getNonSelectedIngredientsInProduct(theCurrentProduct);

        for (ingredient tempIngredient : tempIngredients) {
            String ingID = Integer.toString(tempIngredient.getId());
            String provID = Integer.toString(tempIngredient.getProviderID());
            String date = tempIngredient.getDate();
            String name = tempIngredient.getName();
            String price = Float.toString(tempIngredient.getPrice());
            String amount = Integer.toString(tempIngredient.getAmount());
            String amountUsed = "Type here";
            int tempAmount = new ingredientsAPI().getAmountOfIngredientInProduct(theCurrentProduct.getId(),
                    tempIngredient.getId());
            if (tempAmount != -1)
                amountUsed = Integer.toString(tempAmount);
            String in_inventory = "No";
            if (tempIngredient.getInInventory())
                in_inventory = "Yes";
            String active = "No";
            if (tempIngredient.getActive())
                active = "Yes";
            if (alreadySelected)
                tempModel.addRow(
                        new String[] { ingID, provID, date, name, price, amount, amountUsed, in_inventory, active });
            else
                tempModel.addRow(
                        new String[] { ingID, provID, date, name, price, amount, amountUsed, in_inventory, active });
        }

        tempTable.setModel(tempModel);

        tempTable.removeColumn(tempTable.getColumn("ingredient_id"));
        tempTable.removeColumn(tempTable.getColumn("provider_id"));
        tempTable.removeColumn(tempTable.getColumn("date"));
        tempTable.removeColumn(tempTable.getColumn("in_inventory"));
        tempTable.removeColumn(tempTable.getColumn("active"));
        if (!alreadySelected)
            tempTable.removeColumn(tempTable.getColumn("Enter Amount"));
    }

    private void setTable() {

        tableIngredients = new JTable();
        modelIngredients = new DefaultTableModel(
                new String[] { "ingredient_id", "provider_id", "date", "Name", "Price", "Amount", "Enter Amount",
                        "in_inventory",
                        "active" },
                0);
        loadTable(false, tableIngredients, modelIngredients);

        tableSelected = new JTable() {
            public boolean isCellEditable(int row, int column) {
                return column == 3;
            }
        };
        modelSelected = new DefaultTableModel(
                new String[] { "ingredient_id", "provider_id", "date", "Name", "Price", "Amount", "Enter Amount",
                        "in_inventory",
                        "active" },
                0);

        loadTable(true, tableSelected, modelSelected);

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
