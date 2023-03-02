package navigation.administration.shiftsNav;

import javax.swing.table.*;

import java.util.ArrayList;
import java.awt.event.*;
import java.text.ParseException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import javax.swing.*;
import javax.swing.LayoutStyle.ComponentPlacement;

import java.awt.*;

import componentsFood.shift;
import util.databaseAPIs.shiftsAPI;

public class mainShifts {

    private JLabel clickShift = new JLabel("Double-Click on Shift to edit it");
    private JButton addShiftButton = new JButton("Add Shift(s)");
    private JButton applyButton = new JButton();
    private JButton undertimeButton = new JButton();
    private JTable myTable = new JTable();
    private JScrollPane shiftsJScrollPanel = new JScrollPane(myTable, JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED,
            JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
    private DefaultTableModel model;
    private String from = "01-06-2022";
    private String to = "01-06-2023";
    private boolean shiftDate;

    private JLabel fromLabel = new JLabel();
    private JLabel toLabel = new JLabel();
    private JTextField fromTextField = new JTextField();
    private JTextField toTextField = new JTextField();
    private JPanel jPanel1 = new JPanel();
    private JToggleButton sortToggle = new JToggleButton();

    public mainShifts(JPanel playground, String from, String to, boolean shiftDate) {
        this.from = from;
        this.to = to;
        this.shiftDate = shiftDate;
        initComponents(playground);
        populateTable();
        addActionListeners(playground);
    }

    private void initComponents(JPanel playground) {

        playground.setBackground(new Color(255, 255, 255));
        if (shiftDate)
            sortToggle.setText("Sort by Shift Date");
        else
            sortToggle.setText("Sort by Employee");

        fromLabel.setFont(new Font("Segoe UI", 0, 18)); // NOI18N
        fromLabel.setHorizontalAlignment(SwingConstants.CENTER);
        fromLabel.setText("From");
        fromLabel.setVerticalAlignment(SwingConstants.BOTTOM);

        addShiftButton.setFont(new Font("Segoe UI", 1, 14)); // NOI18N
        addShiftButton.setText("Add Shift");

        clickShift.setFont(new Font("Segoe UI", 0, 18)); // NOI18N
        clickShift.setHorizontalAlignment(SwingConstants.CENTER);
        clickShift.setText("Double-Click on Shift to edit it");
        clickShift.setVerticalAlignment(SwingConstants.BOTTOM);

        fromTextField.setText(from);

        toLabel.setFont(new Font("Segoe UI", 0, 18)); // NOI18N
        toLabel.setHorizontalAlignment(SwingConstants.CENTER);
        toLabel.setText("To");
        toLabel.setVerticalAlignment(SwingConstants.BOTTOM);

        toTextField.setText(to);

        applyButton.setText("Apply");

        jPanel1.setBackground(new Color(0, 0, 0));
        jPanel1.setPreferredSize(new Dimension(2, 0));

        GroupLayout jPanel1Layout = new GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
                jPanel1Layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                        .addGap(0, 2, Short.MAX_VALUE));
        jPanel1Layout.setVerticalGroup(
                jPanel1Layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                        .addGap(0, 0, Short.MAX_VALUE));

        undertimeButton.setText("Check Undertime");

        GroupLayout playgroundLayout = new GroupLayout(playground);
        playground.setLayout(playgroundLayout);
        playgroundLayout.setHorizontalGroup(
                playgroundLayout.createParallelGroup(GroupLayout.Alignment.LEADING)
                        .addGroup(playgroundLayout.createSequentialGroup()
                                .addGap(94, 94, 94)
                                .addGroup(playgroundLayout
                                        .createParallelGroup(GroupLayout.Alignment.TRAILING, false)
                                        .addGroup(playgroundLayout.createSequentialGroup()
                                                .addComponent(clickShift)
                                                .addPreferredGap(ComponentPlacement.RELATED,
                                                        GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                                .addComponent(fromLabel)
                                                .addPreferredGap(ComponentPlacement.RELATED)
                                                .addComponent(fromTextField, GroupLayout.PREFERRED_SIZE, 88,
                                                        GroupLayout.PREFERRED_SIZE)
                                                .addPreferredGap(ComponentPlacement.UNRELATED)
                                                .addComponent(toLabel)
                                                .addPreferredGap(ComponentPlacement.RELATED)
                                                .addComponent(toTextField, GroupLayout.PREFERRED_SIZE, 88,
                                                        GroupLayout.PREFERRED_SIZE)
                                                .addGap(12, 12, 12)
                                                .addComponent(jPanel1, GroupLayout.PREFERRED_SIZE,
                                                        GroupLayout.DEFAULT_SIZE,
                                                        GroupLayout.PREFERRED_SIZE)
                                                .addPreferredGap(ComponentPlacement.UNRELATED)
                                                .addComponent(applyButton))
                                        .addComponent(shiftsJScrollPanel, GroupLayout.PREFERRED_SIZE, 682,
                                                GroupLayout.PREFERRED_SIZE)
                                        .addGroup(playgroundLayout.createSequentialGroup()
                                                .addComponent(undertimeButton, GroupLayout.PREFERRED_SIZE,
                                                        150, GroupLayout.PREFERRED_SIZE)
                                                .addPreferredGap(ComponentPlacement.RELATED,
                                                        GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                                .addComponent(sortToggle, GroupLayout.PREFERRED_SIZE, 150,
                                                        GroupLayout.PREFERRED_SIZE)))
                                .addContainerGap(94, Short.MAX_VALUE))
                        .addGroup(GroupLayout.Alignment.TRAILING, playgroundLayout.createSequentialGroup()
                                .addContainerGap(GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(addShiftButton, GroupLayout.PREFERRED_SIZE, 210,
                                        GroupLayout.PREFERRED_SIZE)
                                .addGap(330, 330, 330)));
        playgroundLayout.setVerticalGroup(
                playgroundLayout.createParallelGroup(GroupLayout.Alignment.LEADING)
                        .addGroup(GroupLayout.Alignment.TRAILING, playgroundLayout.createSequentialGroup()
                                .addGap(72, 72, 72)
                                .addGroup(playgroundLayout
                                        .createParallelGroup(GroupLayout.Alignment.LEADING, false)
                                        .addComponent(fromTextField)
                                        .addGroup(GroupLayout.Alignment.TRAILING,
                                                playgroundLayout
                                                        .createParallelGroup(GroupLayout.Alignment.BASELINE)
                                                        .addComponent(fromLabel)
                                                        .addComponent(clickShift))
                                        .addComponent(toLabel, GroupLayout.DEFAULT_SIZE,
                                                GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                        .addGroup(GroupLayout.Alignment.TRAILING, playgroundLayout
                                                .createParallelGroup(GroupLayout.Alignment.BASELINE)
                                                .addComponent(toTextField, GroupLayout.PREFERRED_SIZE, 25,
                                                        GroupLayout.PREFERRED_SIZE)
                                                .addComponent(applyButton))
                                        .addComponent(jPanel1, GroupLayout.DEFAULT_SIZE, 25,
                                                Short.MAX_VALUE))
                                .addPreferredGap(ComponentPlacement.RELATED)
                                .addComponent(shiftsJScrollPanel, GroupLayout.PREFERRED_SIZE, 371,
                                        GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(ComponentPlacement.RELATED)
                                .addGroup(
                                        playgroundLayout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                                                .addComponent(sortToggle)
                                                .addComponent(undertimeButton))
                                .addGap(15, 15, 15)
                                .addComponent(addShiftButton, GroupLayout.PREFERRED_SIZE, 55,
                                        GroupLayout.PREFERRED_SIZE)
                                .addContainerGap(57, Short.MAX_VALUE)));

        shiftsJScrollPanel.getViewport().setBackground(new Color(245, 245, 245));
        shiftsJScrollPanel.setBackground(new Color(245, 245, 245));
    }

    private void addActionListeners(JPanel playground) {
        addShiftButton.setBackground(new Color(23, 35, 51));
        addShiftButton.setForeground(new Color(255, 255, 255));
        addShiftButton.addMouseListener(new MouseListener() {
            public void mouseClicked(MouseEvent e) {
                playground.removeAll();
                new addShifts(playground, from, to, shiftDate);
                playground.revalidate();
                playground.repaint();
            }

            public void mousePressed(MouseEvent e) {
            }

            public void mouseReleased(MouseEvent e) {
            }

            public void mouseEntered(MouseEvent e) {
                addShiftButton.setBackground(new Color(255, 255, 255));
                addShiftButton.setForeground(new Color(23, 35, 51));
            }

            public void mouseExited(MouseEvent e) {
                addShiftButton.setBackground(new Color(23, 35, 51));
                addShiftButton.setForeground(new Color(255, 255, 255));
            }
        });
        myTable.addMouseListener(new MouseAdapter() {
            public void mouseClicked(MouseEvent me) {
                if (me.getClickCount() == 2) {
                    try {
                        if (myTable.getValueAt(myTable.getSelectedRow(), 0).toString().equals(""))
                            return;
                        LocalDate fromShift = LocalDate.parse((String) model.getValueAt(myTable.getSelectedRow(), 3),
                                DateTimeFormatter.ofPattern("dd-MM-yyyy"));
                        if (LocalDate.now().isAfter(fromShift)) {
                            JOptionPane.showMessageDialog(playground,
                                    "You can't change a shift which has occured past today.", "ERROR",
                                    JOptionPane.ERROR_MESSAGE);
                            return;
                        }
                        int ID = Integer.parseInt((String) model.getValueAt(myTable.getSelectedRow(), 0));
                        String date = (String) model.getValueAt(myTable.getSelectedRow(), 3);
                        String startShift = (String) model.getValueAt(myTable.getSelectedRow(), 4) + ":00";
                        String endShift = (String) model.getValueAt(myTable.getSelectedRow(), 5) + ":00";
                        try {
                            playground.removeAll();
                            new editShifts(playground, from, to, shiftDate,
                                    new shiftsAPI().getShift(ID, date, startShift, endShift));
                            playground.revalidate();
                            playground.repaint();
                        } catch (ParseException e) {
                            e.printStackTrace();
                        }

                    } catch (IndexOutOfBoundsException e) {
                        return;
                    }
                }
            }
        });
        applyButton.setBackground(new Color(120, 168, 252));
        applyButton.setForeground(new Color(0, 0, 0));
        applyButton.addMouseListener(new MouseListener() {
            public void mouseClicked(MouseEvent e) {
                String newFrom = fromTextField.getText();
                String newTo = toTextField.getText();
                if (newFrom.equals(from) && newTo.equals(to))
                    return;
                else {
                    renewTable();
                    from = newFrom;
                    to = newTo;
                }
            }

            public void mousePressed(MouseEvent e) {
            }

            public void mouseReleased(MouseEvent e) {
            }

            public void mouseEntered(MouseEvent e) {
            }

            public void mouseExited(MouseEvent e) {
            }
        });
        sortToggle.setBackground(new Color(120, 168, 252));
        sortToggle.setForeground(new Color(0, 0, 0));
        sortToggle.addMouseListener(new MouseListener() {
            public void mouseClicked(MouseEvent e) {
                if (sortToggle.getText().equals("Sort by Employee")) {
                    sortToggle.setText("Sort by Shift Date");
                    renewTable();
                } else {
                    sortToggle.setText("Sort by Employee");
                    renewTable();
                }
            }

            public void mousePressed(MouseEvent e) {
            }

            public void mouseReleased(MouseEvent e) {
            }

            public void mouseEntered(MouseEvent e) {
            }

            public void mouseExited(MouseEvent e) {
            }
        });
        undertimeButton.setBackground(new Color(120, 168, 252));
        undertimeButton.setForeground(new Color(0, 0, 0));
        undertimeButton.addMouseListener(new MouseListener() {
            public void mouseClicked(MouseEvent e) {
                for (int row = 0; row < myTable.getRowCount(); row++) {
                    if (model.getValueAt(row, 8).equals("YES")) {
                        for (int column = 0; column < myTable.getColumnCount(); column++) {
                            // FIGURE CHANGE OF COLOR OF ROW
                        }
                    }
                }
                // myTable.revalidate();
                // myTable.repaint();
            }

            public void mousePressed(MouseEvent e) {
            }

            public void mouseReleased(MouseEvent e) {
            }

            public void mouseEntered(MouseEvent e) {
            }

            public void mouseExited(MouseEvent e) {
            }
        });
    }

    private void renewTable() {
        String newFrom = fromTextField.getText();
        String newTo = toTextField.getText();
        boolean newShiftDate = sortToggle.getText().equals("Sort by Shift Date");
        if (newFrom.equals(from) && newTo.equals(to) && shiftDate == newShiftDate)
            return;
        for (int i = model.getRowCount() - 1; i >= 0; i--) {
            model.removeRow(i);
        }
        shiftsAPI theManagerDB = new shiftsAPI();
        ArrayList<shift> tempList = theManagerDB.getShiftsWithinDateSorted(newFrom, newTo, newShiftDate);
        for (shift temp : tempList) {
            String id = Integer.toString(temp.getEmployeeId());
            String name = theManagerDB.getNameOfEmployee(temp.getEmployeeId());
            String role = theManagerDB.getRoleOfEmployee(temp.getEmployeeId());
            String date = temp.getDate();
            String startTime = temp.getStartTime().substring(0, 5);
            String endTime = temp.getEndTime().substring(0, 5);
            String realStartTime = temp.getRealStartTime();
            if (realStartTime != null)
                realStartTime = realStartTime.substring(0, 5);
            String realEndTime = temp.getRealEndTime();
            if (realEndTime != null)
                realEndTime = realEndTime.substring(0, 5);
            String undertime = "NO";
            if (temp.getIsUndertime())
                undertime = "YES";
            model.addRow(
                    new String[] { id, name, role, date, startTime, endTime, realStartTime, realEndTime, undertime });
        }
        shiftDate = newShiftDate;
        shiftsJScrollPanel.revalidate();
        shiftsJScrollPanel.repaint();
    }

    private void populateTable() {
        shiftsAPI theManagerDB = new shiftsAPI();
        ArrayList<shift> tempList = theManagerDB.getShiftsWithinDateSorted(from, to, shiftDate);
        model = new DefaultTableModel(
                new String[] { "id", "Employee", "Role", "Shift Date", "Start", "End", "Check-In", "Check-Out",
                        "undertime" },
                0);
        for (shift temp : tempList) {
            String id = Integer.toString(temp.getEmployeeId());
            String name = theManagerDB.getNameOfEmployee(temp.getEmployeeId());
            String role = theManagerDB.getRoleOfEmployee(temp.getEmployeeId());
            String date = temp.getDate();
            String startTime = temp.getStartTime().substring(0, 5);
            String endTime = temp.getEndTime().substring(0, 5);
            String realStartTime = temp.getRealStartTime();
            if (realStartTime != null)
                realStartTime = realStartTime.substring(0, 5);
            String realEndTime = temp.getRealEndTime();
            if (realEndTime != null)
                realEndTime = realEndTime.substring(0, 5);
            String undertime = "NO";
            if (temp.getIsUndertime())
                undertime = "YES";
            model.addRow(
                    new String[] { id, name, role, date, startTime, endTime, realStartTime, realEndTime, undertime });
        }
        myTable.setDefaultEditor(Object.class, null);
        myTable.setFocusable(true);
        myTable.setModel(model);
        myTable.removeColumn(myTable.getColumn("id"));
        myTable.removeColumn(myTable.getColumn("undertime"));
        myTable.getTableHeader().setFont(new Font("Segoe UI", 1, 14));
        myTable.getTableHeader().setBackground(new Color(120, 168, 252));
        myTable.setFillsViewportHeight(true);
        myTable.setFont(new Font("Segoe UI", 0, 14));
        DefaultTableCellRenderer centerRenderer = new DefaultTableCellRenderer();
        centerRenderer.setHorizontalAlignment(JLabel.CENTER);
        myTable.getColumnModel().getColumn(3).setCellRenderer(centerRenderer);
        myTable.getColumnModel().getColumn(4).setCellRenderer(centerRenderer);
        myTable.getColumnModel().getColumn(5).setCellRenderer(centerRenderer);
        myTable.getColumnModel().getColumn(6).setCellRenderer(centerRenderer);

        Dimension temp = new Dimension(20, 1);
        myTable.setIntercellSpacing(temp);
        myTable.setRowHeight(myTable.getRowHeight() + 10);
    }
}