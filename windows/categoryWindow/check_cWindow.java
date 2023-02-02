package windows.categoryWindow;

import javax.swing.table.DefaultTableModel;
import util.abstractEdit_CheckWindow;
import componentsFood.category;
import util.abstractUpdater;
import java.util.ArrayList;

import javax.swing.JScrollPane;
import javax.swing.JTable;

public class check_cWindow extends abstractEdit_CheckWindow {

    private categoryAPI theManagerDB = new categoryAPI();

    public check_cWindow(abstractUpdater previousWindow) {
        super(previousWindow, "Check Categories", "Category");
    }

    @Override
    public void addActionListeners() {
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
    public void setBounds() {
        getSummaryTXT().setBounds(200, 20, 250, 25);
        getBackButton().setBounds(400, 400, 120, 80);
        getScrollPane().setBounds(45, 60, 500, 300);
    }

    @Override
    public void addToFrame() {
        theFrame.add(getSummaryTXT());
        theFrame.add(getBackButton());
        theFrame.add(getScrollPane());
    }

}
