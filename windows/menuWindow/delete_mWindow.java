package windows.menuWindow;

import javax.swing.table.DefaultTableModel;
import windows.categoryWindow.categoryAPI;
import util.abstractEdit_CheckWindow;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import componentsFood.category;
import util.abstractUpdater;
import java.util.ArrayList;
import componentsFood.menu;
import javax.swing.JTable;
import java.awt.event.*;

public class delete_mWindow extends abstractEdit_CheckWindow {

    private menuAPI theManagerDB = new menuAPI();

    public delete_mWindow(abstractUpdater previousWindow) {
        super(previousWindow, "Choose Menu to be Deleted", "Menu");
    }

    @Override
    public void addActionListeners() {
        myTable.addMouseListener(new MouseAdapter() {
            public void mouseClicked(MouseEvent me) {
                if (me.getClickCount() == 1) { // to detect doble click events
                    try {
                        if (myTable.getValueAt(myTable.getSelectedRow(), 0).toString().equals(""))
                            return;
                        int reply = JOptionPane.showConfirmDialog(null,
                                "Are you sure you want to delete this Menu?",
                                "Confirmation", JOptionPane.YES_NO_OPTION);
                        if (reply == JOptionPane.YES_OPTION) {
                            theManagerDB.deleteMenu(
                                    Integer.parseInt(model.getValueAt(myTable.getSelectedRow(), 0).toString()));
                            model.removeRow(myTable.getSelectedRow());
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
        ArrayList<menu> tempList = theManagerDB.getAllCurrentMenus();
        myTable = new JTable();
        model = new DefaultTableModel(
                new String[] { "menu_id", "active", "Name", "Price", "Category" },
                0);
        categoryAPI tempAPI = new categoryAPI();
        for (menu temp : tempList) {
            String id = Integer.toString(temp.getId());
            String date = temp.getDate();
            String name = temp.getName();
            String price = Float.toString(temp.getPrice());
            category tempCategory = tempAPI.getCategoryOfMenu(temp.getId());
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
        myTable.removeColumn(myTable.getColumn("menu_id"));
        myTable.removeColumn(myTable.getColumn("active"));

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