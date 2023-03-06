package navigation.food.allergensNav;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.util.ArrayList;

import javax.swing.GroupLayout;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.SwingConstants;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;

import componentsFood.allergen;
import util.buttonFormatters.iNavigatorButton;
import util.buttonFormatters.navigatorButtonFormatter;
import util.databaseAPIs.allergensAPI;

import java.awt.event.*;

public class mainAllergens {

    private JLabel clickAllergen = new JLabel("Double-Click on allergen to edit it");
    private JButton addAllergenButton = new JButton("Add Allergen");
    private JTable myTable = new JTable();
    private JScrollPane rolesJScrollPanel = new JScrollPane(myTable, JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED,
            JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
    private DefaultTableModel model;

    public mainAllergens(JPanel playground) {
        initComponents(playground);
        populateTable();
        addListeners(playground);
    }

    private void initComponents(JPanel playground) {
        playground.setBackground(new Color(255, 255, 255));
        clickAllergen.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        clickAllergen.setHorizontalAlignment(SwingConstants.CENTER);
        clickAllergen.setVerticalAlignment(SwingConstants.BOTTOM);
        addAllergenButton.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        addAllergenButton.setBackground(new Color(23, 35, 51));
        addAllergenButton.setForeground(new Color(255, 255, 255));

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
                                                .addComponent(clickAllergen, GroupLayout.PREFERRED_SIZE,
                                                        707,
                                                        GroupLayout.PREFERRED_SIZE))
                                        .addGroup(playgroundLayout.createSequentialGroup()
                                                .addGap(334, 334, 334)
                                                .addComponent(addAllergenButton, GroupLayout.PREFERRED_SIZE,
                                                        200, GroupLayout.PREFERRED_SIZE)))
                                .addContainerGap(75, Short.MAX_VALUE)));
        playgroundLayout.setVerticalGroup(
                playgroundLayout.createParallelGroup(GroupLayout.Alignment.LEADING)
                        .addGroup(GroupLayout.Alignment.TRAILING, playgroundLayout.createSequentialGroup()
                                .addGap(58, 58, 58)
                                .addComponent(clickAllergen, GroupLayout.PREFERRED_SIZE, 35,
                                        GroupLayout.PREFERRED_SIZE)
                                .addGap(30, 30, 30)
                                .addComponent(rolesJScrollPanel, GroupLayout.PREFERRED_SIZE, 337,
                                        GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(addAllergenButton, GroupLayout.PREFERRED_SIZE, 55,
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
                        String name = (String) model.getValueAt(myTable.getSelectedRow(), 1);
                        playground.removeAll();
                        new editAllergen(playground, new allergen(ID, name));
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
                new addAllergen(playground);
            }
        }
        navigatorButtonFormatter.formatNavigationButton(addAllergenButton, new addMethodHolder(), playground, false,
                "Add Allergen");
    }

    private void populateTable() {
        model = new DefaultTableModel(new String[] { "ID", "Name" }, 0);
        ArrayList<allergen> tempAllergens = allergensAPI.getAllAllergens();
        if (!tempAllergens.isEmpty()) {
            for (allergen temp : tempAllergens)
                model.addRow(new String[] { Integer.toString(temp.getId()), temp.getName() });
        }
        myTable.setDefaultEditor(Object.class, null);
        myTable.setFocusable(true);
        myTable.setModel(model);
        myTable.removeColumn(myTable.getColumn("ID"));
        myTable.getTableHeader().setFont(new Font("Segoe UI", 1, 14));
        myTable.getTableHeader().setBackground(new Color(120, 168, 252));
        myTable.setFillsViewportHeight(true);
        myTable.setFont(new Font("Segoe UI", 0, 14));
        DefaultTableCellRenderer centerRenderer = new DefaultTableCellRenderer();
        centerRenderer.setHorizontalAlignment(JLabel.CENTER);
        myTable.getColumnModel().getColumn(0).setCellRenderer(centerRenderer);

        Dimension tempdimen = new Dimension(20, 1);
        myTable.setIntercellSpacing(tempdimen);
        myTable.setRowHeight(myTable.getRowHeight() + 10);

    }
}