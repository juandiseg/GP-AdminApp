package windows.productsWindow;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JTextField;
import javax.swing.JToggleButton;
import javax.swing.table.DefaultTableModel;

import componentsFood.ingredient;
import componentsFood.product;
import util.abstractAddWindow;
import util.abstractUpdater;
import javax.swing.JLabel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JButton;

public class assist_add_productWindow extends abstractAddWindow {

    private JLabel selectIngredients = new JLabel("Select ingredients used: ");

    private JButton swapLeft = new JButton("Left");
    private JButton swapRight = new JButton("Right");
    private product theProduct;

    private JScrollPane scrollPaneIngredients;
    private JScrollPane scrollPaneSelected;
    private JTable tableIngredients;
    private JTable tableSelected;
    private DefaultTableModel modelIngredients;
    private DefaultTableModel modelSelected;

    public assist_add_productWindow(abstractUpdater previousWindow, product theProduct) {
        super(previousWindow, "Product", true);
        this.theProduct = theProduct;
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
        abstractUpdater temp = this;
        getAddButton().addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {

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
                modelIngredients.removeRow(row);
                modelSelected.addRow(new String[] { ingID, provID, date, name, price, amount });
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
                modelSelected.removeRow(row);
                modelIngredients.addRow(new String[] { ingID, provID, date, name, price, amount });
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
        scrollPaneIngredients.setBounds(320, 900, 170, 200);
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
        tableSelected = new JTable();
        modelIngredients = new DefaultTableModel(
                new String[] { "ingredient_id", "provider_id", "date", "Name", "Price", "Amount" }, 0);
        modelSelected = new DefaultTableModel(
                new String[] { "ingredient_id", "provider_id", "date", "Name", "Price", "Amount" }, 0);

        for (ingredient tempIngredient : theManagerDB.getAllCurrentIngredients()) {
            String ingID = Integer.toString(tempIngredient.getId());
            String provID = Integer.toString(tempIngredient.getProviderID());
            String date = tempIngredient.getDate();
            String name = tempIngredient.getName();
            String price = Float.toString(tempIngredient.getPrice());
            String amount = Integer.toString(tempIngredient.getAmount());
            modelIngredients.addRow(new String[] { ingID, provID, date, name, price, amount });
        }

        tableIngredients.setModel(modelIngredients);
        tableSelected.setModel(modelSelected);
        tableIngredients.removeColumn(tableIngredients.getColumn("ingredient_id"));
        tableIngredients.removeColumn(tableIngredients.getColumn("provider_id"));
        tableIngredients.removeColumn(tableIngredients.getColumn("date"));
        tableSelected.removeColumn(tableSelected.getColumn("ingredient_id"));
        tableSelected.removeColumn(tableSelected.getColumn("provider_id"));
        tableSelected.removeColumn(tableSelected.getColumn("date"));

        scrollPaneIngredients = new JScrollPane(tableIngredients, JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED,
                JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
        scrollPaneSelected = new JScrollPane(tableSelected, JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED,
                JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
    }

}
