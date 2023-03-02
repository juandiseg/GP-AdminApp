package navigation.administration.employeesNav;

import componentsFood.employee;
import util.databaseAPIs.employeesAPI;
import util.databaseAPIs.rolesAPI;

import java.util.ArrayList;
import javax.swing.table.*;
import javax.swing.*;
import java.awt.*;

import java.awt.event.*;

public class mainEmployees {

    private JLabel clickEmployee = new JLabel("Double-Click on employee to edit it");
    private JButton addEmployeeButton = new JButton("Add Employee");
    private JTable myTable = new JTable();
    private JScrollPane employeesJScrollPanel = new JScrollPane(myTable, JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED,
            JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
    private DefaultTableModel model;

    public mainEmployees(JPanel playground) {
        initComponents(playground);
        populateTable();
        addActionListeners(playground);
    }

    private void initComponents(JPanel playground) {
        playground.setBackground(new Color(255, 255, 255));
        clickEmployee.setFont(new Font("Segoe UI", 0, 18)); // NOI18N
        clickEmployee.setHorizontalAlignment(SwingConstants.CENTER);
        clickEmployee.setVerticalAlignment(SwingConstants.BOTTOM);
        addEmployeeButton.setFont(new Font("Segoe UI", 1, 14)); // NOI18N
        addEmployeeButton.setBackground(new Color(23, 35, 51));
        addEmployeeButton.setForeground(new Color(255, 255, 255));

        GroupLayout playgroundLayout = new GroupLayout(playground);
        playground.setLayout(playgroundLayout);
        playgroundLayout.setHorizontalGroup(
                playgroundLayout.createParallelGroup(GroupLayout.Alignment.LEADING)
                        .addGroup(GroupLayout.Alignment.TRAILING, playgroundLayout.createSequentialGroup()
                                .addGap(0, 0, Short.MAX_VALUE)
                                .addComponent(employeesJScrollPanel, GroupLayout.PREFERRED_SIZE, 694,
                                        GroupLayout.PREFERRED_SIZE)
                                .addGap(88, 88, 88))
                        .addGroup(playgroundLayout.createSequentialGroup()
                                .addGroup(playgroundLayout
                                        .createParallelGroup(GroupLayout.Alignment.LEADING)
                                        .addGroup(playgroundLayout.createSequentialGroup()
                                                .addGap(88, 88, 88)
                                                .addComponent(clickEmployee, GroupLayout.PREFERRED_SIZE,
                                                        707,
                                                        GroupLayout.PREFERRED_SIZE))
                                        .addGroup(playgroundLayout.createSequentialGroup()
                                                .addGap(334, 334, 334)
                                                .addComponent(addEmployeeButton, GroupLayout.PREFERRED_SIZE,
                                                        200, GroupLayout.PREFERRED_SIZE)))
                                .addContainerGap(75, Short.MAX_VALUE)));
        playgroundLayout.setVerticalGroup(
                playgroundLayout.createParallelGroup(GroupLayout.Alignment.LEADING)
                        .addGroup(GroupLayout.Alignment.TRAILING, playgroundLayout.createSequentialGroup()
                                .addGap(58, 58, 58)
                                .addComponent(clickEmployee, GroupLayout.PREFERRED_SIZE, 35,
                                        GroupLayout.PREFERRED_SIZE)
                                .addGap(30, 30, 30)
                                .addComponent(employeesJScrollPanel, GroupLayout.PREFERRED_SIZE, 337,
                                        GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(addEmployeeButton, GroupLayout.PREFERRED_SIZE, 55,
                                        GroupLayout.PREFERRED_SIZE)
                                .addContainerGap(97, Short.MAX_VALUE)));
    }

    private void addActionListeners(JPanel playground) {
        addEmployeeButton.addMouseListener(new MouseListener() {
            public void mouseClicked(MouseEvent e) {
                playground.removeAll();
                new addEmployee(playground);
                playground.revalidate();
                playground.repaint();
            }

            public void mousePressed(MouseEvent e) {
            }

            public void mouseReleased(MouseEvent e) {
            }

            public void mouseEntered(MouseEvent e) {
                setColor(addEmployeeButton);
            }

            public void mouseExited(MouseEvent e) {
                resetColor(addEmployeeButton);
            }
        });

        myTable.addMouseListener(new MouseAdapter() {
            public void mouseClicked(MouseEvent me) {
                if (me.getClickCount() == 2) {
                    try {
                        if (myTable.getValueAt(myTable.getSelectedRow(), 0).toString().equals(""))
                            return;

                        int ID = Integer.parseInt((String) model.getValueAt(myTable.getSelectedRow(), 0));
                        playground.removeAll();
                        new editEmployee(playground, new employeesAPI().getEmployee(ID));
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
        ArrayList<employee> tempList = new employeesAPI().getAllCurrentEmployees();
        model = new DefaultTableModel(new String[] { "employee_id", "Name", "Salary", "Hours / Week", "Role" }, 0);
        for (employee temp : tempList) {
            String id = Integer.toString(temp.getId());
            String name = temp.getName();
            String salary = Float.toString(temp.getSalary());
            String hoursWeek = temp.getHoursWeek().substring(0, 5);
            String role = new rolesAPI().getNameOfRoleID(temp.getRoleID());
            model.addRow(new String[] { id, name, salary, hoursWeek, role });
        }
        myTable.setDefaultEditor(Object.class, null);
        myTable.setFocusable(true);
        myTable.setModel(model);
        myTable.removeColumn(myTable.getColumn("employee_id"));
        myTable.getTableHeader().setFont(new Font("Segoe UI", 1, 14));
        myTable.getTableHeader().setBackground(new Color(120, 168, 252));
        myTable.setFillsViewportHeight(true);
        employeesJScrollPanel.getViewport().setBackground(new Color(245, 245, 245));
        employeesJScrollPanel.setBackground(new Color(245, 245, 245));
        myTable.setFont(new Font("Segoe UI", 0, 14));
        DefaultTableCellRenderer centerRenderer = new DefaultTableCellRenderer();
        centerRenderer.setHorizontalAlignment(JLabel.CENTER);
        myTable.getColumnModel().getColumn(1).setCellRenderer(centerRenderer);
        myTable.getColumnModel().getColumn(2).setCellRenderer(centerRenderer);
        Dimension temp = new Dimension(20, 1);
        myTable.setIntercellSpacing(temp);
        myTable.setRowHeight(myTable.getRowHeight() + 10);
    }
}