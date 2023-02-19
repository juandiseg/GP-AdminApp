package navigation.food.ingredientsWindow;

import componentsFood.ingredient;
import javax.swing.table.*;
import java.awt.event.*;
import javax.swing.*;
import java.awt.*;

public class mainIngredients {

    private JLabel clickIngredient = new JLabel("Double-Click on ingredient to edit it");
    private JButton addCategoryButton = new JButton("Add Ingredient");
    private JTable myTable = new JTable();
    private JScrollPane rolesJScrollPanel = new JScrollPane(myTable, JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED,
            JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
    private DefaultTableModel model;

    public mainIngredients(JPanel playground) {
        initComponents(playground);
        populateTable();
        addActionListeners(playground);
    }

    private void initComponents(JPanel playground) {
        playground.setBackground(new java.awt.Color(255, 255, 255));
        clickIngredient.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        clickIngredient.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        clickIngredient.setVerticalAlignment(javax.swing.SwingConstants.BOTTOM);
        addCategoryButton.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        addCategoryButton.setBackground(new Color(23, 35, 51));
        addCategoryButton.setForeground(new Color(255, 255, 255));

        javax.swing.GroupLayout playgroundLayout = new javax.swing.GroupLayout(playground);
        playground.setLayout(playgroundLayout);
        playgroundLayout.setHorizontalGroup(
                playgroundLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, playgroundLayout.createSequentialGroup()
                                .addGap(0, 0, Short.MAX_VALUE)
                                .addComponent(rolesJScrollPanel, javax.swing.GroupLayout.PREFERRED_SIZE, 694,
                                        javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(88, 88, 88))
                        .addGroup(playgroundLayout.createSequentialGroup()
                                .addGroup(playgroundLayout
                                        .createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addGroup(playgroundLayout.createSequentialGroup()
                                                .addGap(88, 88, 88)
                                                .addComponent(clickIngredient, javax.swing.GroupLayout.PREFERRED_SIZE,
                                                        707,
                                                        javax.swing.GroupLayout.PREFERRED_SIZE))
                                        .addGroup(playgroundLayout.createSequentialGroup()
                                                .addGap(334, 334, 334)
                                                .addComponent(addCategoryButton, javax.swing.GroupLayout.PREFERRED_SIZE,
                                                        200, javax.swing.GroupLayout.PREFERRED_SIZE)))
                                .addContainerGap(75, Short.MAX_VALUE)));
        playgroundLayout.setVerticalGroup(
                playgroundLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, playgroundLayout.createSequentialGroup()
                                .addGap(58, 58, 58)
                                .addComponent(clickIngredient, javax.swing.GroupLayout.PREFERRED_SIZE, 35,
                                        javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(30, 30, 30)
                                .addComponent(rolesJScrollPanel, javax.swing.GroupLayout.PREFERRED_SIZE, 337,
                                        javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(addCategoryButton, javax.swing.GroupLayout.PREFERRED_SIZE, 55,
                                        javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addContainerGap(97, Short.MAX_VALUE)));
    }

    private void addActionListeners(JPanel playground) {
        addCategoryButton.addMouseListener(new MouseListener() {
            public void mouseClicked(MouseEvent e) {
                playground.removeAll();
                new addIngredient(playground);
                playground.revalidate();
                playground.repaint();
            }

            public void mousePressed(MouseEvent e) {
            }

            public void mouseReleased(MouseEvent e) {
            }

            public void mouseEntered(MouseEvent e) {
                addCategoryButton.setBackground(new Color(120, 168, 252));
                addCategoryButton.setForeground(new Color(0, 0, 0));
            }

            public void mouseExited(MouseEvent e) {
                addCategoryButton.setBackground(new Color(23, 35, 51));
                addCategoryButton.setForeground(new Color(255, 255, 255));
            }
        });
        myTable.addMouseListener(new MouseAdapter() {
            public void mouseClicked(MouseEvent me) {
                if (me.getClickCount() == 2) {
                    try {
                        if (myTable.getValueAt(myTable.getSelectedRow(), 0).toString().equals(""))
                            return;
                        int ID = Integer.parseInt((String) model.getValueAt(myTable.getSelectedRow(), 0));
                        String name = (String) model.getValueAt(myTable.getSelectedRow(), 1);
                        boolean type = ((String) model.getValueAt(myTable.getSelectedRow(), 2)).equals("Product");
                        playground.removeAll();
                        // new editIngredient(playground, new category(ID, name, type));
                        playground.revalidate();
                        playground.repaint();
                    } catch (IndexOutOfBoundsException e) {
                        return;
                    }
                }
            }
        });
    }

    private void populateTable() {
        model = new DefaultTableModel(
                new String[] { "ID", "Prov_ID", "date", "Name", "Price", "Amount", "Inventory", "active" },
                0);
        for (ingredient temp : new ingredientsAPI().getAllCurrentIngredients()) {
            String id = Integer.toString(temp.getId());
            String prov_id = Integer.toString(temp.getProviderID());
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
                    new String[] { id, prov_id, temp.getDate(), temp.getName(), price, amount, in_inventory, active });
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
        myTable.getColumnModel().getColumn(1).setCellRenderer(centerRenderer);
        myTable.getColumnModel().getColumn(2).setCellRenderer(centerRenderer);
        myTable.getColumnModel().getColumn(3).setCellRenderer(centerRenderer);

        Dimension tempdimen = new Dimension(20, 1);
        myTable.setIntercellSpacing(tempdimen);
        myTable.setRowHeight(myTable.getRowHeight() + 10);

    }
}