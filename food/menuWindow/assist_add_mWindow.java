package food.menuWindow;

import java.awt.event.ActionListener;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Stack;
import java.awt.event.ActionEvent;
import javax.swing.table.DefaultTableModel;

import componentsFood.menu;
import componentsFood.product;
import util.abstractAddWindow;
import util.abstractUpdater;
import food.categoryWindow.categoryAPI;
import food.productsWindow.productAPI;

import javax.swing.JLabel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JButton;

public class assist_add_mWindow extends abstractAddWindow {

    private JLabel selectIngredients = new JLabel("Select ingredients used: ");

    private menuAPI theManagerDB = new menuAPI();
    private JButton swapLeft = new JButton("Left");
    private JButton swapRight = new JButton("Right");
    private String menuName;
    private float menuPrice;
    private int menuCategoryID;

    private JScrollPane scrollPaneIngredients;
    private JScrollPane scrollPaneSelected;
    private JTable tableIngredients;
    private JTable tableSelected;
    private DefaultTableModel modelIngredients;
    private DefaultTableModel modelSelected;

    public assist_add_mWindow(abstractUpdater previousWindow, String menuName, float menuPrice, int menuCategoryID) {
        super(previousWindow, "Product", true);
        this.menuName = menuName;
        this.menuPrice = menuPrice;
        this.menuCategoryID = menuCategoryID;
    }

    @Override
    public void addComponents() {
        setTable();
        setBounds();
        addToFrame();
        setBackButton();
    }

    @Override
    public void addActionListeners() {
        getAddButton().addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                Stack<Integer> productIDs = new Stack<Integer>();
                Stack<Float> productAmounts = new Stack<Float>();
                for (int i = 0; i < modelSelected.getRowCount(); i++) {
                    productIDs.push(Integer.parseInt((String) modelSelected.getValueAt(i, 0)));
                    productAmounts.push(Float.parseFloat((String) modelSelected.getValueAt(i, 3)));
                }

                if (productIDs.isEmpty() || productAmounts.isEmpty())
                    return;
                String date = LocalDate.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd"));
                menu addedMenu = theManagerDB.addMenu(date, menuName, menuPrice, true);
                if (addedMenu == null)
                    return;
                new categoryAPI().addMenuToCategory(addedMenu.getId(), menuCategoryID);
                while (!productIDs.isEmpty()) {
                    theManagerDB.addProducts(addedMenu.getId(), productIDs.pop(), date, productAmounts.pop());
                }
                getPreviousWindow().getPreviousWindow().updateToPreviousMenu();

            }
        });
        swapLeft.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                int row = tableIngredients.getSelectedRow();
                if (row == -1)
                    return;
                String productID = (String) modelIngredients.getValueAt(row, 0);
                String name = (String) modelIngredients.getValueAt(row, 1);
                String price = (String) modelIngredients.getValueAt(row, 2);
                String amount = (String) modelIngredients.getValueAt(row, 3);
                modelIngredients.removeRow(row);
                modelSelected.addRow(new String[] { productID, name, price, amount });
            }
        });
        swapRight.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                int row = tableSelected.getSelectedRow();
                if (row == -1)
                    return;
                String productID = (String) modelSelected.getValueAt(row, 0);
                String name = (String) modelSelected.getValueAt(row, 1);
                String price = (String) modelSelected.getValueAt(row, 2);
                String amount = (String) modelSelected.getValueAt(row, 3);
                modelSelected.removeRow(row);
                modelIngredients
                        .addRow(new String[] { productID, name, price, amount });
            }
        });
    }

    @Override
    public void setBounds() {
        getInputSuccesful().setBounds(250, 120, 250, 25);
        getInputError().setBounds(250, 120, 300, 25);
        getAddButton().setBounds(80, 400, 130, 20);
        getBackButton().setBounds(400, 400, 120, 80);
        selectIngredients.setBounds(10, 50, 200, 25);
        scrollPaneIngredients.setBounds(320, 90, 170, 200);
        scrollPaneSelected.setBounds(50, 90, 170, 200);
        swapLeft.setBounds(230, 150, 80, 25);
        swapRight.setBounds(230, 190, 80, 25);
    }

    @Override
    public void addToFrame() {
        theFrame.add(getAddButton());
        theFrame.add(getBackButton());
        theFrame.add(selectIngredients);
        theFrame.add(scrollPaneIngredients);
        theFrame.add(scrollPaneSelected);
        theFrame.add(swapRight);
        theFrame.add(swapLeft);
    }

    private void setTable() {
        tableIngredients = new JTable();
        tableIngredients.setDefaultEditor(Object.class, null);
        tableSelected = new JTable() {
            public boolean isCellEditable(int row, int column) {
                return column == 2;
            };
        };
        modelIngredients = new DefaultTableModel(
                new String[] { "product_id", "Name", "Price", "Amount" }, 0);
        modelSelected = new DefaultTableModel(
                new String[] { "product_id", "Name", "Price", "Amount" }, 0);

        for (product tempProduct : new productAPI().getAllCurrentProducts()) {
            String ingID = Integer.toString(tempProduct.getId());
            String name = tempProduct.getName();
            String price = Float.toString(tempProduct.getPrice());
            modelIngredients.addRow(new String[] { ingID, name, price, "" });
        }

        tableIngredients.setModel(modelIngredients);
        tableSelected.setModel(modelSelected);
        tableIngredients.removeColumn(tableIngredients.getColumn("product_id"));
        tableIngredients.removeColumn(tableIngredients.getColumn("Amount"));
        tableSelected.removeColumn(tableSelected.getColumn("product_id"));

        scrollPaneIngredients = new JScrollPane(tableIngredients, JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED,
                JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
        scrollPaneSelected = new JScrollPane(tableSelected, JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED,
                JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
    }

}
