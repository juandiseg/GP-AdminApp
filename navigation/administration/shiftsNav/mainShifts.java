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
import util.buttonFormatters.iNavigatorButton;
import util.buttonFormatters.navigatorButtonFormatter;
import util.buttonFormatters.iAuxButton;
import util.buttonFormatters.auxButtonFormatter;
import util.databaseAPIs.employeesAPI;
import util.databaseAPIs.shiftsAPI;
import util.inputFormatting.iFormatter;
import util.inputFormatting.inputFormatterFactory;
import util.listenersFormatting.booleanWrapper;
import util.listenersFormatting.iTextFieldListener;
import util.listenersFormatting.edit.editDateTFFListener;

public class mainShifts {

    private JLabel clickShift = new JLabel("Double-Click on Shift to edit it");
    private JButton addShiftButton = new JButton();
    private JButton applyButton = new JButton();
    private JButton undertimeButton = new JButton();
    private JTable myTable = new JTable();
    private JScrollPane shiftsJScrollPanel = new JScrollPane(myTable, JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED,
            JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
    private DefaultTableModel model;
    private String from = "01-06-2022";
    private String to = "01-06-2023";
    private boolean shiftDate;
    private boolean undertimeHighlighted = false;

    private JLabel fromLabel = new JLabel();
    private JLabel toLabel = new JLabel();
    private JTextField fromTextField = new JTextField();
    private JTextField toTextField = new JTextField();
    private booleanWrapper fromPlaceholder = new booleanWrapper(true);
    private booleanWrapper toPlaceholder = new booleanWrapper(true);

    private JPanel jPanel1 = new JPanel();
    private JButton sortToggle = new JButton();

    public mainShifts(JPanel playground, String from, String to, boolean shiftDate) {
        this.from = from;
        this.to = to;
        this.shiftDate = shiftDate;
        initComponents(playground);
        populateTable();
        addListeners(playground);
    }

    private void initComponents(JPanel playground) {
        playground.setBackground(new Color(255, 255, 255));
        fromLabel.setFont(new Font("Segoe UI", 0, 18)); // NOI18N
        fromLabel.setHorizontalAlignment(SwingConstants.CENTER);
        fromLabel.setText("From");
        fromLabel.setVerticalAlignment(SwingConstants.BOTTOM);

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

    private void addListeners(JPanel playground) {
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
                                    shiftsAPI.getShift(ID, date, startShift, endShift));
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
        applyButton(playground);
        sortButton();
        undertimeButton();
        addButton(playground);
        applyGenericListeners();
    }

    private void sortButton() {
        class sortMethodHolder implements iAuxButton {
            public void action() {
                if (sortToggle.getText().equals("Sort by Employee")) {
                    sortToggle.setText("Sort by Shift Date");
                    renewTable();
                } else {
                    sortToggle.setText("Sort by Employee");
                    renewTable();
                }
            }
        }
        String title = "Sort by Shift Date";
        if (shiftDate)
            sortToggle.setText("Sort by Shift Date");
        else {
            sortToggle.setText("Sort by Employee");
            title = "Sort by Employee";
        }
        auxButtonFormatter.formatAuxButton(sortToggle, new sortMethodHolder(), title);
    }

    private void applyButton(JPanel playground) {
        class applyMethodHolder implements iAuxButton {
            public void action() {
                String newFrom = fromTextField.getText();
                try {
                    LocalDate.parse(newFrom, DateTimeFormatter.ofPattern("dd-MM-yyyy"));
                } catch (Exception DateTimeParseException) {
                    JOptionPane.showMessageDialog(playground,
                            "The given from date is unvalid.", "ERROR",
                            JOptionPane.ERROR_MESSAGE);
                    return;
                }

                String newTo = toTextField.getText();
                try {
                    LocalDate.parse(newTo, DateTimeFormatter.ofPattern("dd-MM-yyyy"));
                } catch (Exception DateTimeParseException) {
                    JOptionPane.showMessageDialog(playground,
                            "The given to date is unvalid.", "ERROR",
                            JOptionPane.ERROR_MESSAGE);
                    return;
                }

                if (newFrom.equals(from) && newTo.equals(to))
                    return;
                else {
                    renewTable();
                    from = newFrom;
                    to = newTo;
                }
            }
        }
        auxButtonFormatter.formatAuxButton(applyButton, new applyMethodHolder(), "Apply");
    }

    private void undertimeButton() {
        class undertimeMethodHolder implements iAuxButton {
            public void action() {
                if (!undertimeHighlighted) {
                    ArrayList<Integer> rowIndexes = new ArrayList<Integer>();
                    for (int row = 0; row < myTable.getRowCount(); row++) {
                        if (model.getValueAt(row, 8).equals("YES"))
                            rowIndexes.add(row);
                    }
                    if (rowIndexes.isEmpty())
                        return;
                    myTable.setDefaultRenderer(Object.class, new DefaultTableCellRenderer() {
                        public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected,
                                boolean hasFocus, int row, int column) {
                            Component cell = super.getTableCellRendererComponent(table, value, isSelected, hasFocus,
                                    row,
                                    column);
                            if (rowIndexes.contains(row) && column == 0)
                                cell.setBackground(Color.ORANGE);
                            else
                                cell.setBackground(Color.WHITE);
                            return cell;
                        }
                    });
                    undertimeHighlighted = true;
                } else {
                    myTable.setDefaultRenderer(Object.class, new DefaultTableCellRenderer() {
                        public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected,
                                boolean hasFocus, int row, int column) {
                            Component cell = super.getTableCellRendererComponent(table, value, isSelected, hasFocus,
                                    row, column);
                            cell.setBackground(Color.WHITE);
                            return cell;
                        }
                    });
                    undertimeHighlighted = false;
                }
                myTable.revalidate();
                myTable.repaint();
            }
        }
        auxButtonFormatter.formatAuxButton(undertimeButton, new undertimeMethodHolder(), "Check Undertime");
    }

    private void addButton(JPanel playground) {
        class addMethodHolder extends iNavigatorButton {
            public void createNewNavigator() {
                new addShifts(playground, from, to, shiftDate);
            }
        }
        navigatorButtonFormatter.formatNavigationButton(addShiftButton, new addMethodHolder(), playground, false,
                "Add Shifts");
    }

    private void applyGenericListeners() {
        iFormatter dateFormatter = new inputFormatterFactory().createInputFormatter("DATE");
        dateFormatter.applyFormat(fromTextField);
        dateFormatter.applyFormat(toTextField);

        iTextFieldListener textListener = new editDateTFFListener();
        textListener.applyListenerTextField(fromTextField, from, fromPlaceholder, true);
        textListener.applyListenerTextField(toTextField, to, toPlaceholder, true);
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
        ArrayList<shift> tempList = shiftsAPI.getShiftsWithinDateSorted(newFrom, newTo, newShiftDate);
        for (shift temp : tempList) {
            String id = Integer.toString(temp.getEmployeeId());
            String name = employeesAPI.getNameOfEmployee(temp.getEmployeeId());
            String role = employeesAPI.getRoleOfEmployee(temp.getEmployeeId());
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
        ArrayList<shift> tempList = shiftsAPI.getShiftsWithinDateSorted(from, to, shiftDate);
        model = new DefaultTableModel(
                new String[] { "id", "Employee", "Role", "Shift Date", "Start", "End", "Check-In", "Check-Out",
                        "undertime" },
                0);
        for (shift temp : tempList) {
            String id = Integer.toString(temp.getEmployeeId());
            String name = employeesAPI.getNameOfEmployee(temp.getEmployeeId());
            String role = employeesAPI.getRoleOfEmployee(temp.getEmployeeId());
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