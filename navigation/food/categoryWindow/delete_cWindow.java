package navigation.food.categoryWindow;

import javax.swing.table.DefaultTableModel;

import componentsFood.category;
import util.abstractEdit_CheckWindow;
import util.abstractUpdater;
import java.util.ArrayList;

import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import java.awt.event.*;

public class delete_cWindow extends abstractEdit_CheckWindow {

    private categoryAPI theManagerDB = new categoryAPI();

    public delete_cWindow(abstractUpdater previousWindow) {
        super(previousWindow, "Choose Category to be edited", "Category");
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
                                "Are you sure you want to delete this Category? It needs to be empty before doing so.",
                                "Confirmation", JOptionPane.YES_NO_OPTION);
                        if (reply == JOptionPane.YES_OPTION) {
                            System.out.println("okay have to simplement");
                        }
                    } catch (IndexOutOfBoundsException e) {
                        return;
                    }
                }
            }
        });
    }

    @Override
    public void setBounds() {
        getSummaryTXT().setBounds(200, 20, 250, 25);
        getBackButton().setBounds(400, 400, 120, 80);
        getScrollPane().setBounds(45, 60, 500, 300);
    }

    @Override
    public void addRowsToModel() {
        myTable = new JTable();
        model = new DefaultTableModel(new String[] { "ID", "Name", "Type" }, 0);
        ArrayList<category> categoriesList = theManagerDB.getAllCategories();
        for (category temp : categoriesList) {
            String ID = Integer.toString(temp.getId());
            String name = temp.getName();
            String isProduct = "Menu";
            if (temp.getIsProduct())
                isProduct = "Product";
            model.addRow(new String[] { ID, name, isProduct });
        }
    }

    @Override
    public void adjustTable() {
        setScrollPane(new JScrollPane(myTable));
        getScrollPane().setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);
        myTable.setModel(model);
        myTable.removeColumn(myTable.getColumn("ID"));
        myTable.setDefaultEditor(Object.class, null);
        myTable.setFocusable(true);
    }

    @Override
    public void addToFrame() {
        theFrame.add(getSummaryTXT());
        theFrame.add(getBackButton());
        theFrame.add(getScrollPane());
    }
}
