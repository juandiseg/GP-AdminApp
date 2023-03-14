package navigation.food.ingredientsNav;

import componentsFood.ingredient;
import util.buttonFormatters.iNavigatorButton;
import util.buttonFormatters.navigatorButtonFormatter;
import util.databaseAPIs.ingredientsAPI;
import util.databaseAPIs.providerAPI;

import javax.swing.table.*;
import java.awt.event.*;
import javax.swing.*;
import java.awt.*;

public class mainIngredients {

    private JLabel clickIngredient = new JLabel("Double-Click on ingredient to edit it");
    private JButton addIngredientButton = new JButton();
    private JTable myTable = new JTable();
    private JScrollPane rolesJScrollPanel = new JScrollPane(myTable, JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED,
            JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
    private DefaultTableModel model;

    public mainIngredients(JPanel playground) {
        initComponents(playground);
        populateTable();
        addListeners(playground);
    }

    private void initComponents(JPanel playground) {
        playground.setBackground(new Color(255, 255, 255));
        clickIngredient.setFont(new Font("Segoe UI", 0, 18)); // NOI18N
        clickIngredient.setHorizontalAlignment(SwingConstants.CENTER);
        clickIngredient.setVerticalAlignment(SwingConstants.BOTTOM);

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
                                                .addComponent(clickIngredient, GroupLayout.PREFERRED_SIZE,
                                                        707,
                                                        GroupLayout.PREFERRED_SIZE))
                                        .addGroup(playgroundLayout.createSequentialGroup()
                                                .addGap(334, 334, 334)
                                                .addComponent(addIngredientButton,
                                                        GroupLayout.PREFERRED_SIZE,
                                                        200, GroupLayout.PREFERRED_SIZE)))
                                .addContainerGap(75, Short.MAX_VALUE)));
        playgroundLayout.setVerticalGroup(
                playgroundLayout.createParallelGroup(GroupLayout.Alignment.LEADING)
                        .addGroup(GroupLayout.Alignment.TRAILING, playgroundLayout.createSequentialGroup()
                                .addGap(58, 58, 58)
                                .addComponent(clickIngredient, GroupLayout.PREFERRED_SIZE, 35,
                                        GroupLayout.PREFERRED_SIZE)
                                .addGap(30, 30, 30)
                                .addComponent(rolesJScrollPanel, GroupLayout.PREFERRED_SIZE, 337,
                                        GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(addIngredientButton, GroupLayout.PREFERRED_SIZE, 55,
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
                        int provID = Integer.parseInt((String) model.getValueAt(myTable.getSelectedRow(), 1));
                        String date = (String) model.getValueAt(myTable.getSelectedRow(), 2);
                        String name = (String) model.getValueAt(myTable.getSelectedRow(), 3);
                        float price = Float.parseFloat((String) model.getValueAt(myTable.getSelectedRow(), 5));
                        float amount = Float.parseFloat((String) model.getValueAt(myTable.getSelectedRow(), 6));
                        Boolean inventory = ((String) model.getValueAt(myTable.getSelectedRow(), 7)).equals("Yes");
                        playground.removeAll();
                        new editIngredient(playground,
                                new ingredient(ID, provID, date, name, price, amount, inventory, true));
                        playground.revalidate();
                        playground.repaint();
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
                new addIngredient(playground);
            }
        }
        navigatorButtonFormatter.formatNavigationButton(addIngredientButton, new addMethodHolder(), playground, false,
                "Add Ingredient");
    }

    private void populateTable() {
        model = new DefaultTableModel(
                new String[] { "ID", "Prov_ID", "date", "Name", "Provider", "Price", "Amount", "Inventory", "active" },
                0);
        for (ingredient temp : ingredientsAPI.getAllCurrentIngredients()) {
            String id = Integer.toString(temp.getId());
            String prov_id = Integer.toString(temp.getProviderID());
            String provName = providerAPI.getProvider(temp.getProviderID()).getName();
            String price = Float.toString(temp.getPrice());
            String amount = Float.toString(temp.getAmount());
            String in_inventory;
            String active;
            if (temp.getInInventory())
                in_inventory = "Yes";
            else
                in_inventory = "No";
            if (temp.getActive())
                active = "Active";
            else
                active = "Not active";
            model.addRow(
                    new String[] { id, prov_id, temp.getDate(), temp.getName(), provName, price, amount, in_inventory,
                            active });
        }

        myTable.setDefaultEditor(Object.class, null);
        myTable.setFocusable(true);
        myTable.setModel(model);
        myTable.removeColumn(myTable.getColumn("ID"));
        myTable.removeColumn(myTable.getColumn("Prov_ID"));
        myTable.removeColumn(myTable.getColumn("date"));
        myTable.removeColumn(myTable.getColumn("active"));
        myTable.getTableHeader().setFont(new Font("Segoe UI", 1, 14));
        myTable.getTableHeader().setBackground(new Color(120, 168, 252));
        myTable.setFillsViewportHeight(true);
        myTable.setFont(new Font("Segoe UI", 0, 14));
        DefaultTableCellRenderer centerRenderer = new DefaultTableCellRenderer();
        centerRenderer.setHorizontalAlignment(JLabel.CENTER);
        myTable.getColumnModel().getColumn(2).setCellRenderer(centerRenderer);
        myTable.getColumnModel().getColumn(3).setCellRenderer(centerRenderer);
        myTable.getColumnModel().getColumn(4).setCellRenderer(centerRenderer);

        Dimension tempdimen = new Dimension(20, 1);
        myTable.setIntercellSpacing(tempdimen);
        myTable.setRowHeight(myTable.getRowHeight() + 10);

    }
}