package navigation.home.items;

import java.awt.Font;
import java.awt.Color;
import java.awt.Dimension;

import java.util.ArrayList;

import javax.lang.model.util.ElementScanner14;
import javax.swing.GroupLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JToggleButton;
import javax.swing.SwingConstants;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;

import componentsFood.category;
import componentsFood.menu;
import componentsFood.product;
import util.buttonFormatters.auxButtonFormatter;
import util.buttonFormatters.dashToggleFormatter;
import util.buttonFormatters.iAuxButton;
import util.buttonFormatters.iDashToggleFormatter;
import util.buttonFormatters.navigatorButtonFormatter;
import util.databaseAPIs.categoryAPI;
import util.databaseAPIs.menuAPI;
import util.databaseAPIs.productAPI;

import java.awt.event.*;

public class mainItems {

    private JLabel infoLabel = new JLabel();
    private JTable itemsTable = new JTable();
    private JScrollPane itemJScrollPane = new JScrollPane(itemsTable, JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED,
            JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
    private DefaultTableModel model;
    private JToggleButton sortButton = new JToggleButton();
    private JToggleButton swapButton = new JToggleButton();

    public mainItems(JFrame theFrame, JPanel playground) {
        initComponents(playground);
        addListeners(theFrame, playground);
    }

    private void initComponents(JPanel playground) {
        playground.setBackground(new java.awt.Color(255, 255, 255));

        setTableMenus(true);
        itemsTable.setRowHeight(itemsTable.getRowHeight() + 10);
        itemJScrollPane.revalidate();
        itemJScrollPane.repaint();

        infoLabel.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        infoLabel.setForeground(new java.awt.Color(23, 35, 51));
        infoLabel.setText("Double-Click on an Item to edit its preview");

        javax.swing.GroupLayout playgroundLayout = new javax.swing.GroupLayout(playground);
        playground.setLayout(playgroundLayout);
        playgroundLayout.setHorizontalGroup(
                playgroundLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(playgroundLayout.createSequentialGroup()
                                .addGroup(playgroundLayout
                                        .createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addGroup(playgroundLayout.createSequentialGroup()
                                                .addGap(331, 331, 331)
                                                .addComponent(swapButton, javax.swing.GroupLayout.PREFERRED_SIZE, 200,
                                                        javax.swing.GroupLayout.PREFERRED_SIZE))
                                        .addGroup(playgroundLayout.createSequentialGroup()
                                                .addGap(85, 85, 85)
                                                .addGroup(playgroundLayout
                                                        .createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                        .addGroup(playgroundLayout.createSequentialGroup()
                                                                .addComponent(infoLabel,
                                                                        javax.swing.GroupLayout.PREFERRED_SIZE, 387,
                                                                        javax.swing.GroupLayout.PREFERRED_SIZE)
                                                                .addGap(176, 176, 176)
                                                                .addComponent(sortButton,
                                                                        javax.swing.GroupLayout.PREFERRED_SIZE, 137,
                                                                        javax.swing.GroupLayout.PREFERRED_SIZE))
                                                        .addComponent(itemJScrollPane,
                                                                javax.swing.GroupLayout.PREFERRED_SIZE, 700,
                                                                javax.swing.GroupLayout.PREFERRED_SIZE))))
                                .addContainerGap(85, Short.MAX_VALUE)));
        playgroundLayout.setVerticalGroup(
                playgroundLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(playgroundLayout.createSequentialGroup()
                                .addContainerGap(44, Short.MAX_VALUE)
                                .addGroup(
                                        playgroundLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                                .addComponent(sortButton)
                                                .addComponent(infoLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 28,
                                                        javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(itemJScrollPane, javax.swing.GroupLayout.PREFERRED_SIZE, 400,
                                        javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(swapButton, javax.swing.GroupLayout.PREFERRED_SIZE, 55,
                                        javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(81, 81, 81)));
    }

    private void addListeners(JFrame theFrame, JPanel playground) {
        itemsTable.addMouseListener(new MouseAdapter() {
            public void mouseClicked(MouseEvent me) {
                if (me.getClickCount() == 2) {
                    try {
                        itemsTable.getValueAt(itemsTable.getSelectedRow(), 0).toString().equals("");
                    } catch (IndexOutOfBoundsException e) {
                        return;
                    }
                    boolean isProduct = true;
                    if (swapButton.getText().equals("Swap to Products"))
                        isProduct = false;
                    int ID = Integer.parseInt((String) model.getValueAt(itemsTable.getSelectedRow(), 0));
                    String name = (String) model.getValueAt(itemsTable.getSelectedRow(), 1);
                    String category = (String) model.getValueAt(itemsTable.getSelectedRow(), 2);
                    int categoryID = categoryAPI.getCategoryID(category);
                    Float price = Float.parseFloat((String) model.getValueAt(itemsTable.getSelectedRow(), 3));
                    String date = (String) model.getValueAt(itemsTable.getSelectedRow(), 4);
                    playground.removeAll();
                    if (isProduct) {
                        new editItems(theFrame, playground, new product(ID, categoryID, date, name, price, true));
                    } else {
                        new editItems(theFrame, playground, new menu(ID, categoryID, date, name, price, true));
                    }
                    playground.revalidate();
                    playground.repaint();
                }
            }
        });
        swapButton(playground);
        sortButton(playground);
    }

    private void sortButton(JPanel playground) {
        class swapMethodHolder implements iDashToggleFormatter {
            public void action1() {
                if (swapButton.getText().equals("Swap to Products"))
                    setTableMenus(false);
                else
                    setTableProducts(false);
            }

            public void action2() {
                if (swapButton.getText().equals("Swap to Products"))
                    setTableMenus(true);
                else
                    setTableProducts(true);
            }
        }
        dashToggleFormatter.applyDashActionListenerToggle(sortButton, "Sort by Category", "Sort Alphabetically",
                new swapMethodHolder());
    }

    private void swapButton(JPanel playground) {
        class swapMethodHolder implements iDashToggleFormatter {
            public void action1() {
                boolean alphabetical = false;
                if (sortButton.getText().equals("Sort by Category"))
                    alphabetical = true;
                setTableProducts(alphabetical);
            }

            public void action2() {
                boolean alphabetical = false;
                if (sortButton.getText().equals("Sort by Category"))
                    alphabetical = true;
                setTableMenus(alphabetical);
            }
        }
        dashToggleFormatter.applyDashActionListenerToggle(swapButton, "Swap to Products", "Swap to Menus",
                new swapMethodHolder());
    }

    private void setTableMenus(boolean isAlphabetical) {
        model = new DefaultTableModel(new String[] { "ID", "Name", "Category", "Price", "Date" }, 0);
        itemsTable.setModel(model);
        ArrayList<menu> menuList;
        if (isAlphabetical)
            menuList = menuAPI.getAllCurrentMenusAlphabetical();
        else
            menuList = menuAPI.getAllCurrentMenusByCategory();
        for (menu temp : menuList) {
            String ID = Integer.toString(temp.getId());
            String name = temp.getName();
            String category = categoryAPI.getNameOfCategory(temp.getCategoryID());
            String price = Float.toString(temp.getPrice());
            String date = temp.getDate();
            model.addRow(new String[] { ID, name, category, price, date });
        }
        setUpTable();
        itemJScrollPane.revalidate();
        itemJScrollPane.repaint();
    }

    private void setTableProducts(boolean isAlphabetical) {
        model = new DefaultTableModel(new String[] { "ID", "Name", "Category", "Price", "Date" }, 0);
        itemsTable.setModel(model);
        ArrayList<product> productList;
        if (isAlphabetical)
            productList = productAPI.getAllCurrentProductsAlphabetical();
        else
            productList = productAPI.getAllCurrentProductsByCategory();
        for (product temp : productList) {
            String ID = Integer.toString(temp.getId());
            String name = temp.getName();
            String category = categoryAPI.getNameOfCategory(temp.getCategoryID());
            String price = Float.toString(temp.getPrice());
            String date = temp.getDate();
            model.addRow(new String[] { ID, name, category, price, date });
        }
        setUpTable();
        itemJScrollPane.revalidate();
        itemJScrollPane.repaint();
    }

    private void setUpTable() {
        itemsTable.setDefaultEditor(Object.class, null);
        itemsTable.setFocusable(true);
        itemsTable.removeColumn(itemsTable.getColumn("ID"));
        itemsTable.removeColumn(itemsTable.getColumn("Date"));
        itemsTable.getTableHeader().setFont(new Font("Segoe UI", 1, 14));
        itemsTable.getTableHeader().setBackground(new Color(120, 168, 252));
        itemsTable.setFillsViewportHeight(true);
        itemsTable.setFont(new Font("Segoe UI", 0, 14));
        DefaultTableCellRenderer centerRenderer = new DefaultTableCellRenderer();
        centerRenderer.setHorizontalAlignment(JLabel.CENTER);
        itemsTable.getColumnModel().getColumn(0).setCellRenderer(centerRenderer);
        itemsTable.getColumnModel().getColumn(1).setCellRenderer(centerRenderer);
        itemsTable.getColumnModel().getColumn(2).setCellRenderer(centerRenderer);
        Dimension tempdimen = new Dimension(20, 1);
        itemsTable.setIntercellSpacing(tempdimen);
    }

}