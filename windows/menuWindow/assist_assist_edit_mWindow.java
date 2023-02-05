package windows.menuWindow;

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
import componentsFood.product;
import componentsFood.menu;
import util.abstractAddWindow;
import util.abstractUpdater;
import windows.categoryWindow.categoryAPI;
import windows.productsWindow.productAPI;

public class assist_assist_edit_mWindow extends abstractAddWindow {

    private menuAPI theManagerDB = new menuAPI();
    private menu theCurrentMenu;

    private JTextField textFieldPrice = new JTextField();
    private JScrollPane scrollPaneTable;
    private JTable myTable;
    private DefaultTableModel model;
    private JLabel summaryTXT = new JLabel("Product to be changed:");
    private JLabel enterPrice = new JLabel("Enter the Menu's new PRICE: ");
    private JLabel changeLabel = new JLabel("Select the Menu's new PRODUCTS: ");
    private JToggleButton changeProductsButton = new JToggleButton("Change Products Too");

    private JButton swapLeft = new JButton("Left");
    private JButton swapRight = new JButton("Right");
    private JScrollPane scrollPaneProducts;
    private JScrollPane scrollPaneSelected;
    private JTable tableProducts;
    private JTable tableSelected;
    private DefaultTableModel modelProducts;
    private DefaultTableModel modelSelected;

    public assist_assist_edit_mWindow(abstractUpdater previousWindow, menu theCurrentMenu) {
        super(previousWindow, "Menu", false);
        this.theCurrentMenu = theCurrentMenu;
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
        theFrame.add(scrollPaneProducts);
        theFrame.add(scrollPaneSelected);
        theFrame.add(swapRight);
        theFrame.add(swapLeft);
        theFrame.revalidate();
        theFrame.repaint();
    }

    private void hideList() {
        theFrame.remove(changeLabel);
        theFrame.remove(scrollPaneProducts);
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
                    theManagerDB.updatePrice(theCurrentMenu.getId(), price);
                    theCurrentMenu = theManagerDB.getMenu(theCurrentMenu.getId());
                }

                if (changeProductsButton.getText().equals("Don't Change Products Too")) {
                    Stack<Integer> stackIDs = new Stack<Integer>();
                    Stack<Float> stackQtys = new Stack<Float>();
                    for (int i = 0; i < modelSelected.getRowCount(); i++) {
                        stackIDs.push(Integer.parseInt((String) modelSelected.getValueAt(i, 0)));
                        stackQtys.push(Float.parseFloat((String) modelSelected.getValueAt(i, 4)));
                    }
                    theManagerDB.updateProducts(theCurrentMenu.getId(), stackIDs, stackQtys);
                }
                updateTable();
            }
        });
        changeProductsButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (changeProductsButton.getText().equals("Change Products Too")) {
                    changeProductsButton.setText("Don't Change Products Too");
                    showList();
                } else {
                    changeProductsButton.setText("Change Products Too");
                    hideList();
                }
            }
        });
        swapLeft.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                int row = tableProducts.getSelectedRow();
                if (row == -1)
                    return;
                String productID = (String) modelProducts.getValueAt(row, 0);
                String date = (String) modelProducts.getValueAt(row, 1);
                String name = (String) modelProducts.getValueAt(row, 2);
                String price = (String) modelProducts.getValueAt(row, 3);
                String entAmount = (String) modelProducts.getValueAt(row, 4);
                modelProducts.removeRow(row);
                modelSelected.addRow(new String[] { productID, date, name, price, entAmount, "true" });
            }
        });
        swapRight.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                int row = tableSelected.getSelectedRow();
                if (row == -1)
                    return;
                String productID = (String) modelSelected.getValueAt(row, 0);
                String date = (String) modelSelected.getValueAt(row, 1);
                String name = (String) modelSelected.getValueAt(row, 2);
                String price = (String) modelSelected.getValueAt(row, 3);
                String entAmount = (String) modelSelected.getValueAt(row, 4);
                modelSelected.removeRow(row);
                modelProducts.addRow(new String[] { productID, date, name, price, entAmount, "true" });
            }
        });

    }

    private void updateTable() {
        theCurrentMenu = theManagerDB.getMenu(theCurrentMenu.getId());
        model.removeRow(0);
        String id = Integer.toString(theCurrentMenu.getId());
        String date = theCurrentMenu.getDate();
        String name = theCurrentMenu.getName();
        String price = Float.toString(theCurrentMenu.getPrice());
        category tempCategory = new categoryAPI().getCategoryOfMenu(theCurrentMenu.getId());
        model.addRow(new String[] { id, date, name, price, tempCategory.getName() });
    }

    private void loadTable() {
        myTable = new JTable();
        model = new DefaultTableModel(
                new String[] { "menu_id", "Active Since", "Name", "Price" }, 0);
        myTable.setModel(model);
        String id = Integer.toString(theCurrentMenu.getId());
        String date = theCurrentMenu.getDate();
        String name = theCurrentMenu.getName();
        String price = Float.toString(theCurrentMenu.getPrice());
        model.addRow(new String[] { id, date, name, price });
        myTable.setDefaultEditor(Object.class, null);
        myTable.setFocusable(true);
        myTable.removeColumn(myTable.getColumn("menu_id"));
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
        changeProductsButton.setBounds(45, 130, 500, 25);
        changeLabel.setBounds(10, 200, 270, 25);
        scrollPaneProducts.setBounds(320, 240, 170, 200);
        scrollPaneSelected.setBounds(50, 240, 170, 200);
        swapLeft.setBounds(230, 300, 80, 25);
        swapRight.setBounds(230, 340, 80, 25);
    }

    private void loadProductTable(boolean alreadySelected, JTable tempTable, DefaultTableModel tempModel) {
        if (!alreadySelected)
            tempTable.setDefaultEditor(Object.class, null);
        ArrayList<product> tempProducts = new ArrayList<product>();
        if (alreadySelected)
            tempProducts = new productAPI().getSelectedProductsInMenu(theCurrentMenu.getId());
        else
            tempProducts = new productAPI().getNonSelectedProductsInMenu(theCurrentMenu);
        for (product temp : tempProducts) {
            String productID = Integer.toString(temp.getId());
            String date = temp.getDate();
            String name = temp.getName();
            String price = Float.toString(temp.getPrice());
            String amountUsed = "Type here";
            if (alreadySelected) {
                int tempAmount = new productAPI().getAmountOfProductInMenu(theCurrentMenu.getId(), temp.getId());
                amountUsed = Float.toString(tempAmount);
            }
            tempModel.addRow(new String[] { productID, date, name, price, amountUsed, "Yes" });
        }

        tempTable.setModel(tempModel);
        tempTable.removeColumn(tempTable.getColumn("product_id"));
        tempTable.removeColumn(tempTable.getColumn("date"));
        tempTable.removeColumn(tempTable.getColumn("active"));
        if (!alreadySelected)
            tempTable.removeColumn(tempTable.getColumn("Enter Amount"));
    }

    private void setTable() {

        tableProducts = new JTable();
        modelProducts = new DefaultTableModel(
                new String[] { "product_id", "date", "Name", "Price", "Enter Amount", "active" }, 0);
        loadProductTable(false, tableProducts, modelProducts);
        tableSelected = new JTable() {
            public boolean isCellEditable(int row, int column) {
                return column == 2;
            }
        };

        modelSelected = new DefaultTableModel(
                new String[] { "product_id", "date", "Name", "Price", "Enter Amount", "active" }, 0);

        loadProductTable(true, tableSelected, modelSelected);

        scrollPaneProducts = new JScrollPane(tableProducts, JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED,
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
        theFrame.add(changeProductsButton);
    }

}
