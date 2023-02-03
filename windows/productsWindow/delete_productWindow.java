package windows.productsWindow;

import javax.swing.table.DefaultTableModel;
import util.abstractEdit_CheckWindow;
import componentsFood.category;
import componentsFood.product;
import util.abstractUpdater;
import windows.categoryWindow.categoryAPI;

import java.util.ArrayList;

import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import java.awt.event.*;

public class delete_productWindow extends abstractEdit_CheckWindow {

    private productAPI theManagerDB = new productAPI();
    private String[] options = new String[] { "Delete Menus", "Keep Menus without Products", "Cancel" };

    public delete_productWindow(abstractUpdater previousWindow) {
        super(previousWindow, "Choose Product to be deleted", "Product");
    }

    @Override
    public void addActionListeners() {
        abstractUpdater temp = this;
        myTable.addMouseListener(new MouseAdapter() {
            public void mouseClicked(MouseEvent me) {
                if (me.getClickCount() == 1) { // to detect doble click events
                    try {
                        if (myTable.getValueAt(myTable.getSelectedRow(), 0).toString().equals(""))
                            return;
                        int reply = JOptionPane.showConfirmDialog(null,
                                "Are you sure you want to delete this Product?",
                                "Confirmation", JOptionPane.YES_NO_OPTION);
                        if (reply == JOptionPane.YES_OPTION) {
                            int response = JOptionPane.showOptionDialog(null,
                                    "Do you want to delete the menus that use this product, or keep them without the product?",
                                    "Choice",
                                    JOptionPane.DEFAULT_OPTION, JOptionPane.PLAIN_MESSAGE, null, options, options[0]);
                        }
                    } catch (IndexOutOfBoundsException e) {
                        return;
                    }
                }
            }
        });
    }

    @Override
    public void addRowsToModel() {
        ArrayList<product> tempList = theManagerDB.getAllCurrentProducts();
        myTable = new JTable();
        model = new DefaultTableModel(
                new String[] { "product_id", "Active Since", "Name", "Price", "Active", "Category" },
                0);
        categoryAPI tempAPI = new categoryAPI();
        for (product temp : tempList) {
            String id = Integer.toString(temp.getId());
            String date = temp.getDate();
            String name = temp.getName();
            String price = Float.toString(temp.getPrice());
            String active = "No";
            category tempCategory = tempAPI.getCategoryOfProduct(temp.getId());
            if (temp.getActive())
                active = "Yes";
            model.addRow(new String[] { id, date, name, price, active, tempCategory.getName() });
        }
    }

    @Override
    public void adjustTable() {
        setScrollPane(new JScrollPane(myTable));
        getScrollPane().setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);
        myTable.setDefaultEditor(Object.class, null);
        myTable.setFocusable(true);
        myTable.setModel(model);
        myTable.removeColumn(myTable.getColumn("product_id"));
    }

    @Override
    public void setBounds() {
        getScrollPane().setBounds(45, 60, 500, 300);
        getSummaryTXT().setBounds(200, 20, 250, 25);
        getBackButton().setBounds(400, 400, 120, 80);

    }

    @Override
    public void addToFrame() {
        theFrame.add(getSummaryTXT());
        theFrame.add(getBackButton());
        theFrame.add(getScrollPane());
    }

}