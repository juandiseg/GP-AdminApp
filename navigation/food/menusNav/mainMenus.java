package navigation.food.menusNav;

import componentsFood.menu;
import util.buttonFormatters.iNavigatorButton;
import util.buttonFormatters.navigatorButtonFormatter;
import util.databaseAPIs.categoryAPI;
import util.databaseAPIs.menuAPI;

import javax.swing.table.*;
import java.awt.event.*;
import javax.swing.*;
import java.awt.*;

public class mainMenus {

    private JLabel clickProduct = new JLabel("Double-Click on menu to edit it");
    private JButton addMenuButton = new JButton();
    private JTable myTable = new JTable();
    private JScrollPane rolesJScrollPanel = new JScrollPane(myTable, JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED,
            JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
    private DefaultTableModel model;

    public mainMenus(JPanel playground) {
        initComponents(playground);
        populateTable();
        addListeners(playground);
    }

    private void initComponents(JPanel playground) {
        playground.setBackground(new Color(255, 255, 255));
        clickProduct.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        clickProduct.setHorizontalAlignment(SwingConstants.CENTER);
        clickProduct.setVerticalAlignment(SwingConstants.BOTTOM);

        GroupLayout playgroundLayout = new GroupLayout(playground);
        playground.setLayout(playgroundLayout);
        playgroundLayout.setHorizontalGroup(
                playgroundLayout.createParallelGroup(GroupLayout.Alignment.LEADING)
                        .addGroup(GroupLayout.Alignment.TRAILING, playgroundLayout.createSequentialGroup()
                                .addGap(0, 0, Short.MAX_VALUE)
                                .addComponent(rolesJScrollPanel, GroupLayout.PREFERRED_SIZE, 694,
                                        GroupLayout.PREFERRED_SIZE)
                                .addGap(88, 88, 88))
                        .addGroup(playgroundLayout.createSequentialGroup()
                                .addGroup(playgroundLayout
                                        .createParallelGroup(GroupLayout.Alignment.LEADING)
                                        .addGroup(playgroundLayout.createSequentialGroup()
                                                .addGap(88, 88, 88)
                                                .addComponent(clickProduct, GroupLayout.PREFERRED_SIZE,
                                                        707,
                                                        GroupLayout.PREFERRED_SIZE))
                                        .addGroup(playgroundLayout.createSequentialGroup()
                                                .addGap(334, 334, 334)
                                                .addComponent(addMenuButton, GroupLayout.PREFERRED_SIZE,
                                                        200, GroupLayout.PREFERRED_SIZE)))
                                .addContainerGap(75, Short.MAX_VALUE)));
        playgroundLayout.setVerticalGroup(
                playgroundLayout.createParallelGroup(GroupLayout.Alignment.LEADING)
                        .addGroup(GroupLayout.Alignment.TRAILING, playgroundLayout.createSequentialGroup()
                                .addGap(58, 58, 58)
                                .addComponent(clickProduct, GroupLayout.PREFERRED_SIZE, 35,
                                        GroupLayout.PREFERRED_SIZE)
                                .addGap(30, 30, 30)
                                .addComponent(rolesJScrollPanel, GroupLayout.PREFERRED_SIZE, 337,
                                        GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(addMenuButton, GroupLayout.PREFERRED_SIZE, 55,
                                        GroupLayout.PREFERRED_SIZE)
                                .addContainerGap(97, Short.MAX_VALUE)));
    }

    private void addListeners(JPanel playground) {
        myTable.addMouseListener(new MouseAdapter() {
            public void mouseClicked(MouseEvent me) {
                if (me.getClickCount() == 2) {
                    try {
                        if (myTable.getValueAt(myTable.getSelectedRow(), 0).toString().equals(""))
                            return;
                        int ID = Integer.parseInt((String) model.getValueAt(myTable.getSelectedRow(), 0));
                        int catID = Integer.parseInt((String) model.getValueAt(myTable.getSelectedRow(), 1));
                        String date = (String) model.getValueAt(myTable.getSelectedRow(), 2);
                        String name = (String) model.getValueAt(myTable.getSelectedRow(), 3);
                        Float price = Float.parseFloat((String) model.getValueAt(myTable.getSelectedRow(), 4));
                        playground.removeAll();
                        new editMenu(playground, new menu(ID, catID, date, name, price, true));
                        playground.repaint();
                        playground.revalidate();
                    } catch (IndexOutOfBoundsException e) {
                        return;
                    }
                }
            }
        });
        addButton(playground);
    }

    private void addButton(JPanel playground) {
        class addMethodHolder extends iNavigatorButton {
            public void createNewNavigator() {
                new addMenu(playground);
            }
        }
        navigatorButtonFormatter.formatNavigationButton(addMenuButton, new addMethodHolder(), playground, false,
                "Add Menu");
    }

    private void populateTable() {
        model = new DefaultTableModel(
                new String[] { "menu_id", "catID", "date", "Name", "Price", "Category" },
                0);
        for (menu temp : menuAPI.getAllCurrentMenus()) {
            String id = Integer.toString(temp.getId());
            String catID = Integer.toString(temp.getCategoryID());
            String date = temp.getDate();
            String name = temp.getName();
            String price = Float.toString(temp.getPrice());
            String catName = categoryAPI.getNameOfCategory(temp.getCategoryID());
            model.addRow(new String[] { id, catID, date, name, price, catName });
        }
        myTable.setDefaultEditor(Object.class, null);
        myTable.setFocusable(true);
        myTable.setModel(model);
        myTable.removeColumn(myTable.getColumn("menu_id"));
        myTable.removeColumn(myTable.getColumn("catID"));
        myTable.removeColumn(myTable.getColumn("date"));
        myTable.getTableHeader().setFont(new Font("Segoe UI", 1, 14));
        myTable.getTableHeader().setBackground(new Color(120, 168, 252));
        myTable.setFillsViewportHeight(true);
        myTable.setFont(new Font("Segoe UI", 0, 14));
        DefaultTableCellRenderer centerRenderer = new DefaultTableCellRenderer();
        centerRenderer.setHorizontalAlignment(JLabel.CENTER);
        myTable.getColumnModel().getColumn(1).setCellRenderer(centerRenderer);
        myTable.getColumnModel().getColumn(2).setCellRenderer(centerRenderer);
        Dimension tempdimen = new Dimension(20, 1);
        myTable.setIntercellSpacing(tempdimen);
        myTable.setRowHeight(myTable.getRowHeight() + 10);

    }
}