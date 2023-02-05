package windows.productsWindow;

import javax.swing.table.DefaultTableModel;
import util.abstractEdit_CheckWindow;
import componentsFood.category;
import componentsFood.product;
import util.abstractUpdater;
import windows.categoryWindow.categoryAPI;

import java.util.ArrayList;

import javax.swing.JLabel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import java.awt.event.*;

public class check_productWindow extends abstractEdit_CheckWindow {

    private productAPI theManagerDB = new productAPI();

    private JLabel instruction = new JLabel("Double-click on Product to check its Ingredients");

    public check_productWindow(abstractUpdater previousWindow) {
        super(previousWindow, "Check Products", "Products");
    }

    @Override
    public void addActionListeners() {
        abstractUpdater temp = this;
        myTable.addMouseListener(new MouseAdapter() {
            public void mouseClicked(MouseEvent me) {
                if (me.getClickCount() == 2) {
                    try {
                        if (myTable.getValueAt(myTable.getSelectedRow(), 0).toString().equals(""))
                            return;
                        int ID = Integer.parseInt((String) model.getValueAt(myTable.getSelectedRow(), 0));
                        new assist_check_productWindow(temp, ID).updateToThisMenu();
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
                new String[] { "product_id", "date", "Name", "Price", "Category" },
                0);
        categoryAPI tempAPI = new categoryAPI();
        for (product temp : tempList) {
            String id = Integer.toString(temp.getId());
            String date = temp.getDate();
            String name = temp.getName();
            String price = Float.toString(temp.getPrice());
            category tempCategory = tempAPI.getCategoryOfProduct(temp.getId());
            model.addRow(new String[] { id, date, name, price, tempCategory.getName() });
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
        myTable.removeColumn(myTable.getColumn("date"));

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
        theFrame.add(instruction);
    }

}
