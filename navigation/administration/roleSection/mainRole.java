package navigation.administration.roleSection;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.event.MouseListener;
import java.util.ArrayList;
import java.awt.event.MouseEvent;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import componentsFood.role;

import java.awt.event.*;

public class mainRole {

    private JLabel clickRole = new JLabel("Double-Click on role to edit it");
    private JButton addRoleButton = new JButton("Add Role");
    private JTable myTable = new JTable();
    private JScrollPane rolesJScrollPanel = new JScrollPane(myTable, JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED,
            JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
    private DefaultTableModel model;

    public mainRole(JPanel playground) {
        initComponents(playground);
        populateTable();
        addActionListeners(playground);
    }

    private void initComponents(JPanel playground) {
        playground.setBackground(new java.awt.Color(255, 255, 255));
        clickRole.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        clickRole.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        clickRole.setVerticalAlignment(javax.swing.SwingConstants.BOTTOM);
        addRoleButton.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        addRoleButton.setBackground(new Color(23, 35, 51));
        addRoleButton.setForeground(new Color(255, 255, 255));

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
                                                .addComponent(clickRole, javax.swing.GroupLayout.PREFERRED_SIZE, 707,
                                                        javax.swing.GroupLayout.PREFERRED_SIZE))
                                        .addGroup(playgroundLayout.createSequentialGroup()
                                                .addGap(334, 334, 334)
                                                .addComponent(addRoleButton, javax.swing.GroupLayout.PREFERRED_SIZE,
                                                        200, javax.swing.GroupLayout.PREFERRED_SIZE)))
                                .addContainerGap(75, Short.MAX_VALUE)));
        playgroundLayout.setVerticalGroup(
                playgroundLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, playgroundLayout.createSequentialGroup()
                                .addGap(58, 58, 58)
                                .addComponent(clickRole, javax.swing.GroupLayout.PREFERRED_SIZE, 35,
                                        javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(30, 30, 30)
                                .addComponent(rolesJScrollPanel, javax.swing.GroupLayout.PREFERRED_SIZE, 337,
                                        javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(addRoleButton, javax.swing.GroupLayout.PREFERRED_SIZE, 55,
                                        javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addContainerGap(97, Short.MAX_VALUE)));
    }

    private void addActionListeners(JPanel playground) {
        addRoleButton.addMouseListener(new MouseListener() {
            public void mouseClicked(MouseEvent e) {
                playground.removeAll();
                new addRole(playground);
                playground.revalidate();
                playground.repaint();
            }

            public void mousePressed(MouseEvent e) {
            }

            public void mouseReleased(MouseEvent e) {
            }

            public void mouseEntered(MouseEvent e) {
                setColor(addRoleButton);
            }

            public void mouseExited(MouseEvent e) {
                resetColor(addRoleButton);
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
                        playground.removeAll();
                        new editRole(playground, new role(ID, name));
                        playground.revalidate();
                        playground.repaint();
                    } catch (IndexOutOfBoundsException e) {
                        return;
                    }
                }
            }
        });
    }

    private void setColor(JButton button) {
        button.setBackground(new Color(120, 168, 252));
        button.setForeground(new Color(0, 0, 0));
    }

    private void resetColor(JButton button) {
        button.setBackground(new Color(23, 35, 51));
        button.setForeground(new Color(255, 255, 255));
    }

    private void populateTable() {
        model = new DefaultTableModel(new String[] { "ID", "Name" }, 0);
        ArrayList<role> providerList = new rolesAPI().getAllRoles();
        for (role temp : providerList)
            model.addRow(new String[] { Integer.toString(temp.getId()), temp.getName() });
        myTable.setModel(model);
        myTable.removeColumn(myTable.getColumn("ID"));
        myTable.setDefaultEditor(Object.class, null);
        myTable.getTableHeader().setFont(new java.awt.Font("Segoe UI", 1, 14));
        myTable.getTableHeader().setBackground(new Color(120, 168, 252));
        myTable.setFillsViewportHeight(true);
        rolesJScrollPanel.getViewport().setBackground(new Color(245, 245, 245));
        rolesJScrollPanel.setBackground(new Color(245, 245, 245));
        myTable.setFont(new java.awt.Font("Segoe UI", 0, 14));
        DefaultTableCellRenderer centerRenderer = new DefaultTableCellRenderer();
        centerRenderer.setHorizontalAlignment(JLabel.CENTER);
        myTable.getColumnModel().getColumn(0).setCellRenderer(centerRenderer);
        Dimension temp = new Dimension(20, 1);
        myTable.setIntercellSpacing(temp);
        myTable.setRowHeight(myTable.getRowHeight() + 10);
    }
}