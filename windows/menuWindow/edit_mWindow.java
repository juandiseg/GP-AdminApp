package windows.menuWindow;

import javax.swing.table.DefaultTableModel;
import util.abstractEdit_CheckWindow;
import componentsFood.category;
import componentsFood.menu;
import util.abstractUpdater;
import windows.categoryWindow.categoryAPI;

import java.util.ArrayList;

import javax.swing.JScrollPane;
import javax.swing.JTable;
import java.awt.event.*;

public class edit_mWindow extends abstractEdit_CheckWindow {

    private menuAPI theManagerDB = new menuAPI();

    public edit_mWindow(abstractUpdater previousWindow) {
        super(previousWindow, "Choose Menu to be edited", "Menu");
    }

    @Override
    public void addActionListeners() {
        abstractUpdater temp = this;
        myTable.addMouseListener(new MouseAdapter() {
            public void mouseClicked(MouseEvent me) {
                if (me.getClickCount() == 2) { // to detect doble click events
                    try {
                        if (myTable.getValueAt(myTable.getSelectedRow(), 0).toString().equals(""))
                            return;
                        int menuID = Integer.parseInt((String) model.getValueAt(myTable.getSelectedRow(), 0));
                        String date = (String) model.getValueAt(myTable.getSelectedRow(), 1);
                        String name = (String) model.getValueAt(myTable.getSelectedRow(), 2);
                        Float price = Float.parseFloat((String) model.getValueAt(myTable.getSelectedRow(), 3));
                        boolean active = ((String) model.getValueAt(myTable.getSelectedRow(), 4)) == "Yes";
                        new assist_edit_mWindow(temp, new menu(menuID, date, name, price, active)).updateToThisMenu();
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
                new String[] { "menu_id", "Active Since", "Name", "Price", "Category" },
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
