package navigation.administration.shiftsNav;

import javax.swing.table.*;
import java.awt.event.*;
import java.text.ParseException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;

import javax.swing.*;
import javax.swing.LayoutStyle.ComponentPlacement;

import java.awt.*;

import componentsFood.shift;
import util.buttonFormatters.*;
import util.databaseAPIs.shiftsAPI;
import util.inputFormatting.iFormatter;
import util.inputFormatting.inputFormatterFactory;
import util.listenersFormatting.booleanWrapper;
import util.listenersFormatting.iTextFieldListener;
import util.listenersFormatting.edit.editDateTFFListener;

public class editShifts {

        private JLabel dateLabel = new JLabel();
        private JLabel startShiftLabel = new JLabel();
        private JLabel endShiftLabel = new JLabel();

        private JTextField dateTextField = new JTextField();
        private JTextField startShiftTextField = new JTextField();
        private JTextField endShiftTextField = new JTextField();

        private booleanWrapper datePlaceholder = new booleanWrapper(true);
        private booleanWrapper startPlaceholder = new booleanWrapper(true);
        private booleanWrapper endPlaceholder = new booleanWrapper(true);

        private JButton backButton = new JButton();
        private JButton deleteButton = new JButton();

        private JButton selectButton = new JButton();
        private JButton unselectButton = new JButton();
        private JButton editShiftsButton = new JButton();

        private JLabel selectShiftsLabel = new JLabel();
        private JLabel editShiftLabel = new JLabel();

        private JScrollPane selectedJScrollPane = new JScrollPane();
        private JScrollPane unselectedJScrollPane = new JScrollPane();

        private JLabel successLabel = new JLabel("Shift(s) successfully added !");

        private JPanel jPanel1 = new JPanel();
        private JPanel jPanel2 = new JPanel();
        private JPanel jPanel3 = new JPanel();

        private JTable tableEmployees;
        private JTable tableSelected;

        private DefaultTableModel modelEmployees;
        private DefaultTableModel modelSelected;

        private boolean shiftDate;

        private shift theShift;
        private String from;
        private String to;

        private shiftsAPI theManagerDB = new shiftsAPI();

        public editShifts(JPanel playground, String from, String to, boolean shiftDate, shift theShift)
                        throws ParseException {
                this.theShift = theShift;
                this.shiftDate = shiftDate;
                this.from = from;
                this.to = to;
                setTables(from, to, shiftDate);
                initComponents(playground);
                addActionListeners(playground);
        }

        private void initComponents(JPanel playground) {

                playground.setBackground(new Color(255, 255, 255));

                successLabel.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
                successLabel.setHorizontalAlignment(SwingConstants.RIGHT);
                successLabel.setVisible(false);
                successLabel.setVerticalAlignment(SwingConstants.BOTTOM);

                jPanel1.setBackground(new Color(120, 168, 252));
                jPanel1.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
                jPanel1.setDebugGraphicsOptions(javax.swing.DebugGraphics.NONE_OPTION);

                dateLabel.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
                dateLabel.setHorizontalAlignment(SwingConstants.LEFT);
                dateLabel.setText("Date");
                dateLabel.setVerticalAlignment(SwingConstants.BOTTOM);

                dateTextField.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
                dateTextField.setText(theShift.getDate());
                dateTextField.setForeground(Color.GRAY);

                editShiftsButton.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
                editShiftsButton.setText("Edit Shift(s)");
                editShiftsButton.setBackground(new Color(255, 255, 255));
                editShiftsButton.setForeground(new Color(23, 35, 51));

                jPanel2.setBackground(new Color(0, 0, 0));

                GroupLayout jPanel2Layout = new GroupLayout(jPanel2);
                jPanel2.setLayout(jPanel2Layout);
                jPanel2Layout.setHorizontalGroup(
                                jPanel2Layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                                                .addGap(0, 8, Short.MAX_VALUE));
                jPanel2Layout.setVerticalGroup(
                                jPanel2Layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                                                .addGap(0, 0, Short.MAX_VALUE));

                startShiftLabel.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
                startShiftLabel.setHorizontalAlignment(SwingConstants.LEFT);
                startShiftLabel.setText("Start Shift");
                startShiftLabel.setVerticalAlignment(SwingConstants.BOTTOM);

                startShiftTextField.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
                startShiftTextField.setText(theShift.getStartTime().substring(0, 5));
                startShiftTextField.setForeground(Color.GRAY);

                endShiftLabel.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
                endShiftLabel.setHorizontalAlignment(SwingConstants.LEFT);
                endShiftLabel.setText("End Shift");
                endShiftLabel.setVerticalAlignment(SwingConstants.BOTTOM);

                endShiftTextField.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
                endShiftTextField.setText(theShift.getEndTime().substring(0, 5));
                endShiftTextField.setForeground(Color.GRAY);

                selectShiftsLabel.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
                selectShiftsLabel.setHorizontalAlignment(SwingConstants.LEFT);
                selectShiftsLabel.setText("Select Shifts");
                selectShiftsLabel.setVerticalAlignment(SwingConstants.BOTTOM);

                GroupLayout jPanel1Layout = new GroupLayout(jPanel1);
                jPanel1.setLayout(jPanel1Layout);
                jPanel1.setLayout(jPanel1Layout);
                jPanel1Layout.setHorizontalGroup(
                                jPanel1Layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                                                .addGroup(jPanel1Layout.createSequentialGroup()
                                                                .addComponent(jPanel2,
                                                                                GroupLayout.PREFERRED_SIZE,
                                                                                GroupLayout.DEFAULT_SIZE,
                                                                                GroupLayout.PREFERRED_SIZE)
                                                                .addGroup(jPanel1Layout.createParallelGroup(
                                                                                GroupLayout.Alignment.LEADING)
                                                                                .addGroup(jPanel1Layout
                                                                                                .createSequentialGroup()
                                                                                                .addGap(125, 125, 125)
                                                                                                .addGroup(jPanel1Layout
                                                                                                                .createParallelGroup(
                                                                                                                                GroupLayout.Alignment.LEADING,
                                                                                                                                false)
                                                                                                                .addComponent(endShiftLabel,
                                                                                                                                GroupLayout.DEFAULT_SIZE,
                                                                                                                                GroupLayout.DEFAULT_SIZE,
                                                                                                                                Short.MAX_VALUE)
                                                                                                                .addComponent(startShiftLabel,
                                                                                                                                GroupLayout.DEFAULT_SIZE,
                                                                                                                                GroupLayout.DEFAULT_SIZE,
                                                                                                                                Short.MAX_VALUE)
                                                                                                                .addComponent(dateLabel,
                                                                                                                                GroupLayout.PREFERRED_SIZE,
                                                                                                                                86,
                                                                                                                                GroupLayout.PREFERRED_SIZE))
                                                                                                .addPreferredGap(
                                                                                                                ComponentPlacement.RELATED)
                                                                                                .addGroup(jPanel1Layout
                                                                                                                .createParallelGroup(
                                                                                                                                GroupLayout.Alignment.LEADING)
                                                                                                                .addGroup(GroupLayout.Alignment.TRAILING,
                                                                                                                                jPanel1Layout.createParallelGroup(
                                                                                                                                                GroupLayout.Alignment.LEADING,
                                                                                                                                                false)
                                                                                                                                                .addComponent(startShiftTextField,
                                                                                                                                                                GroupLayout.Alignment.TRAILING)
                                                                                                                                                .addComponent(endShiftTextField,
                                                                                                                                                                GroupLayout.Alignment.TRAILING,
                                                                                                                                                                GroupLayout.PREFERRED_SIZE,
                                                                                                                                                                434,
                                                                                                                                                                GroupLayout.PREFERRED_SIZE))
                                                                                                                .addComponent(dateTextField,
                                                                                                                                GroupLayout.Alignment.TRAILING,
                                                                                                                                GroupLayout.PREFERRED_SIZE,
                                                                                                                                434,
                                                                                                                                GroupLayout.PREFERRED_SIZE))
                                                                                                .addContainerGap(
                                                                                                                GroupLayout.DEFAULT_SIZE,
                                                                                                                Short.MAX_VALUE))
                                                                                .addGroup(GroupLayout.Alignment.TRAILING,
                                                                                                jPanel1Layout.createSequentialGroup()
                                                                                                                .addPreferredGap(
                                                                                                                                ComponentPlacement.RELATED,
                                                                                                                                GroupLayout.DEFAULT_SIZE,
                                                                                                                                Short.MAX_VALUE)
                                                                                                                .addGroup(jPanel1Layout
                                                                                                                                .createParallelGroup(
                                                                                                                                                GroupLayout.Alignment.LEADING,
                                                                                                                                                false)
                                                                                                                                .addGroup(GroupLayout.Alignment.TRAILING,
                                                                                                                                                jPanel1Layout.createSequentialGroup()
                                                                                                                                                                .addComponent(selectButton,
                                                                                                                                                                                GroupLayout.PREFERRED_SIZE,
                                                                                                                                                                                75,
                                                                                                                                                                                GroupLayout.PREFERRED_SIZE)
                                                                                                                                                                .addPreferredGap(
                                                                                                                                                                                ComponentPlacement.RELATED,
                                                                                                                                                                                GroupLayout.DEFAULT_SIZE,
                                                                                                                                                                                Short.MAX_VALUE)
                                                                                                                                                                .addComponent(editShiftsButton,
                                                                                                                                                                                GroupLayout.PREFERRED_SIZE,
                                                                                                                                                                                200,
                                                                                                                                                                                GroupLayout.PREFERRED_SIZE)
                                                                                                                                                                .addGap(163, 163,
                                                                                                                                                                                163)
                                                                                                                                                                .addComponent(unselectButton))
                                                                                                                                .addGroup(GroupLayout.Alignment.TRAILING,
                                                                                                                                                jPanel1Layout.createSequentialGroup()
                                                                                                                                                                .addComponent(unselectedJScrollPane,
                                                                                                                                                                                GroupLayout.PREFERRED_SIZE,
                                                                                                                                                                                333,
                                                                                                                                                                                GroupLayout.PREFERRED_SIZE)
                                                                                                                                                                .addPreferredGap(
                                                                                                                                                                                ComponentPlacement.UNRELATED)
                                                                                                                                                                .addComponent(selectedJScrollPane,
                                                                                                                                                                                GroupLayout.PREFERRED_SIZE,
                                                                                                                                                                                333,
                                                                                                                                                                                GroupLayout.PREFERRED_SIZE)))
                                                                                                                .addGap(99, 99, 99)))));
                jPanel1Layout.setVerticalGroup(
                                jPanel1Layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                                                .addGroup(GroupLayout.Alignment.TRAILING, jPanel1Layout
                                                                .createSequentialGroup()
                                                                .addContainerGap()
                                                                .addGroup(jPanel1Layout.createParallelGroup(
                                                                                GroupLayout.Alignment.BASELINE)
                                                                                .addComponent(dateLabel)
                                                                                .addComponent(dateTextField,
                                                                                                GroupLayout.PREFERRED_SIZE,
                                                                                                35,
                                                                                                GroupLayout.PREFERRED_SIZE))
                                                                .addPreferredGap(
                                                                                ComponentPlacement.RELATED)
                                                                .addGroup(jPanel1Layout.createParallelGroup(
                                                                                GroupLayout.Alignment.BASELINE)
                                                                                .addComponent(startShiftTextField,
                                                                                                GroupLayout.PREFERRED_SIZE,
                                                                                                35,
                                                                                                GroupLayout.PREFERRED_SIZE)
                                                                                .addComponent(startShiftLabel))
                                                                .addPreferredGap(
                                                                                ComponentPlacement.RELATED)
                                                                .addGroup(jPanel1Layout.createParallelGroup(
                                                                                GroupLayout.Alignment.BASELINE)
                                                                                .addComponent(endShiftLabel)
                                                                                .addComponent(endShiftTextField,
                                                                                                GroupLayout.PREFERRED_SIZE,
                                                                                                35,
                                                                                                GroupLayout.PREFERRED_SIZE))
                                                                .addGap(18, 18, 18)
                                                                .addGroup(jPanel1Layout.createParallelGroup(
                                                                                GroupLayout.Alignment.LEADING)
                                                                                .addComponent(selectedJScrollPane,
                                                                                                GroupLayout.PREFERRED_SIZE,
                                                                                                190,
                                                                                                GroupLayout.PREFERRED_SIZE)
                                                                                .addComponent(unselectedJScrollPane,
                                                                                                GroupLayout.PREFERRED_SIZE,
                                                                                                190,
                                                                                                GroupLayout.PREFERRED_SIZE))
                                                                .addPreferredGap(
                                                                                ComponentPlacement.RELATED,
                                                                                GroupLayout.DEFAULT_SIZE,
                                                                                Short.MAX_VALUE)
                                                                .addGroup(jPanel1Layout.createParallelGroup(
                                                                                GroupLayout.Alignment.LEADING)
                                                                                .addGroup(GroupLayout.Alignment.TRAILING,
                                                                                                jPanel1Layout.createSequentialGroup()
                                                                                                                .addGroup(jPanel1Layout
                                                                                                                                .createParallelGroup(
                                                                                                                                                GroupLayout.Alignment.BASELINE)
                                                                                                                                .addComponent(unselectButton)
                                                                                                                                .addComponent(selectButton))
                                                                                                                .addGap(52, 52, 52))
                                                                                .addComponent(editShiftsButton,
                                                                                                GroupLayout.Alignment.TRAILING,
                                                                                                GroupLayout.PREFERRED_SIZE,
                                                                                                55,
                                                                                                GroupLayout.PREFERRED_SIZE))
                                                                .addGap(10, 10, 10))
                                                .addComponent(jPanel2, GroupLayout.DEFAULT_SIZE,
                                                                GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE));

                jPanel3.setBackground(new Color(71, 120, 197));

                GroupLayout jPanel3Layout = new GroupLayout(jPanel3);
                jPanel3.setLayout(jPanel3Layout);
                jPanel3Layout.setHorizontalGroup(
                                jPanel3Layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                                                .addGap(0, 500, Short.MAX_VALUE));
                jPanel3Layout.setVerticalGroup(
                                jPanel3Layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                                                .addGap(0, 5, Short.MAX_VALUE));

                deleteButton.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
                deleteButton.setText("Delete");
                deleteButton.setBackground(new Color(255, 102, 102));
                deleteButton.setForeground(new Color(255, 255, 255));

                editShiftLabel.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
                editShiftLabel.setHorizontalAlignment(SwingConstants.CENTER);
                editShiftLabel.setText("Edit Shifts");
                editShiftLabel.setToolTipText("");
                editShiftLabel.setVerticalAlignment(SwingConstants.BOTTOM);

                GroupLayout playgroundLayout = new GroupLayout(playground);
                playground.setLayout(playgroundLayout);
                playgroundLayout.setHorizontalGroup(
                                playgroundLayout.createParallelGroup(GroupLayout.Alignment.LEADING)
                                                .addComponent(jPanel1, GroupLayout.DEFAULT_SIZE,
                                                                GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                                .addComponent(editShiftLabel,
                                                                GroupLayout.Alignment.TRAILING,
                                                                GroupLayout.DEFAULT_SIZE,
                                                                GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                                .addGroup(playgroundLayout.createSequentialGroup()
                                                                .addGap(185, 185, 185)
                                                                .addComponent(jPanel3,
                                                                                GroupLayout.PREFERRED_SIZE,
                                                                                GroupLayout.DEFAULT_SIZE,
                                                                                GroupLayout.PREFERRED_SIZE)
                                                                .addGap(0, 185, Short.MAX_VALUE))
                                                .addGroup(GroupLayout.Alignment.TRAILING, playgroundLayout
                                                                .createSequentialGroup()
                                                                .addContainerGap()
                                                                .addGroup(playgroundLayout.createParallelGroup(
                                                                                GroupLayout.Alignment.TRAILING)
                                                                                .addComponent(successLabel,
                                                                                                GroupLayout.DEFAULT_SIZE,
                                                                                                GroupLayout.DEFAULT_SIZE,
                                                                                                Short.MAX_VALUE)
                                                                                .addGroup(playgroundLayout
                                                                                                .createSequentialGroup()
                                                                                                .addComponent(backButton,
                                                                                                                GroupLayout.PREFERRED_SIZE,
                                                                                                                200,
                                                                                                                GroupLayout.PREFERRED_SIZE)
                                                                                                .addPreferredGap(
                                                                                                                ComponentPlacement.RELATED,
                                                                                                                GroupLayout.DEFAULT_SIZE,
                                                                                                                Short.MAX_VALUE)
                                                                                                .addComponent(deleteButton,
                                                                                                                GroupLayout.PREFERRED_SIZE,
                                                                                                                200,
                                                                                                                GroupLayout.PREFERRED_SIZE)))
                                                                .addContainerGap()));
                playgroundLayout.setVerticalGroup(
                                playgroundLayout.createParallelGroup(GroupLayout.Alignment.LEADING)
                                                .addGroup(GroupLayout.Alignment.TRAILING, playgroundLayout
                                                                .createSequentialGroup()
                                                                .addGap(39, 39, 39)
                                                                .addComponent(editShiftLabel)
                                                                .addPreferredGap(
                                                                                ComponentPlacement.RELATED)
                                                                .addComponent(jPanel3,
                                                                                GroupLayout.PREFERRED_SIZE,
                                                                                GroupLayout.DEFAULT_SIZE,
                                                                                GroupLayout.PREFERRED_SIZE)
                                                                .addGap(18, 18, 18)
                                                                .addComponent(jPanel1,
                                                                                GroupLayout.PREFERRED_SIZE,
                                                                                GroupLayout.DEFAULT_SIZE,
                                                                                GroupLayout.PREFERRED_SIZE)
                                                                .addPreferredGap(
                                                                                ComponentPlacement.RELATED)
                                                                .addComponent(successLabel)
                                                                .addPreferredGap(
                                                                                ComponentPlacement.RELATED,
                                                                                23, Short.MAX_VALUE)
                                                                .addGroup(playgroundLayout.createParallelGroup(
                                                                                GroupLayout.Alignment.BASELINE)
                                                                                .addComponent(deleteButton,
                                                                                                GroupLayout.PREFERRED_SIZE,
                                                                                                55,
                                                                                                GroupLayout.PREFERRED_SIZE)
                                                                                .addComponent(backButton,
                                                                                                GroupLayout.PREFERRED_SIZE,
                                                                                                55,
                                                                                                GroupLayout.PREFERRED_SIZE))
                                                                .addContainerGap()));
        }

        private void addActionListeners(JPanel playground) {
                deleteButton.addMouseListener(new MouseListener() {
                        public void mouseClicked(MouseEvent e) {
                                if (modelSelected.getRowCount() == 0)
                                        return;
                                int reply = JOptionPane.showConfirmDialog(playground,
                                                "Are you sure you want to delete the selected shifts?", "Confirmation",
                                                JOptionPane.YES_NO_OPTION);
                                if (reply != JOptionPane.YES_OPTION)
                                        return;
                                ArrayList<shift> listShifts = new ArrayList<shift>();
                                for (int i = 0; i < modelSelected.getRowCount(); i++) {
                                        int id = Integer.parseInt((String) modelSelected.getValueAt(i, 0));
                                        String date = (String) modelSelected.getValueAt(i, 3);
                                        String startTime = (String) modelSelected.getValueAt(i, 4);
                                        String endTime = (String) modelSelected.getValueAt(i, 5);
                                        listShifts.add(new shift(id, date, startTime, endTime));
                                }
                                for (int i = modelSelected.getRowCount() - 1; i > -1; i--)
                                        modelSelected.removeRow(i);
                                shiftsAPI theManagerDB = new shiftsAPI();
                                for (shift temp : listShifts)
                                        theManagerDB.deleteShift(temp);
                        }

                        public void mousePressed(MouseEvent e) {
                        }

                        public void mouseReleased(MouseEvent e) {
                        }

                        public void mouseEntered(MouseEvent e) {
                                deleteButton.setBackground(new Color(255, 255, 255));
                                deleteButton.setForeground(new Color(255, 102, 102));
                        }

                        public void mouseExited(MouseEvent e) {
                                deleteButton.setBackground(new Color(255, 102, 102));
                                deleteButton.setForeground(new Color(255, 255, 255));
                        }
                });
                selectionButtons();
                backButton(playground);
                editButton(playground);
                applyGenericListeners();
        }

        private void selectionButtons() {
                class selectMethodHolder implements iSelectionButton {
                        public void doSelection() {
                                int row = tableEmployees.getSelectedRow();
                                if (row == -1)
                                        return;
                                String ID = (String) modelEmployees.getValueAt(row, 0);
                                String name = (String) modelEmployees.getValueAt(row, 1);
                                String salary = (String) modelEmployees.getValueAt(row, 2);
                                String hoursWeek = (String) modelEmployees.getValueAt(row, 3);
                                String role = (String) modelEmployees.getValueAt(row, 4);
                                String roleID = (String) modelEmployees.getValueAt(row, 5);
                                modelEmployees.removeRow(row);
                                modelSelected.addRow(new String[] { ID, name, salary, hoursWeek, role, roleID });
                        }
                }
                selectionButtonFormatter.formatSelectionButton(selectButton, new selectMethodHolder(), true);
                class unselectMethodHolder implements iSelectionButton {
                        public void doSelection() {
                                int row = tableSelected.getSelectedRow();
                                if (row == -1)
                                        return;
                                String ID = (String) modelSelected.getValueAt(row, 0);
                                String name = (String) modelSelected.getValueAt(row, 1);
                                String salary = (String) modelSelected.getValueAt(row, 2);
                                String hoursWeek = (String) modelSelected.getValueAt(row, 3);
                                String role = (String) modelSelected.getValueAt(row, 4);
                                String roleID = (String) modelSelected.getValueAt(row, 5);
                                modelSelected.removeRow(row);
                                modelEmployees.addRow(new String[] { ID, name, salary, hoursWeek, role, roleID });
                        }
                }
                selectionButtonFormatter.formatSelectionButton(unselectButton, new unselectMethodHolder(), false);
        }

        private void backButton(JPanel playground) {
                class backMethodHolder extends iBackButton {
                        public void createNewNavigator() {
                                new mainShifts(playground, from, to, shiftDate);
                        }
                }
                backButtonFormatter.formatBackButton(backButton, new backMethodHolder(), playground);
        }

        private void editButton(JPanel playground) {
                class editMethodsHolder implements iEditButton {
                        public boolean valuesArePlaceholders() {
                                if (datePlaceholder.getValue() && startPlaceholder.getValue()
                                                && endPlaceholder.getValue()) {
                                        successLabel.setText("Error. You must modify at least one field.");
                                        successLabel.setVisible(true);
                                        return true;
                                }
                                return false;
                        }

                        public boolean areInputsInvalid() {
                                return false;
                        }

                        public void editFoodComponent() {
                                boolean successfulUpdate = true;

                                ArrayList<shift> listShifts = getListOfShifts();

                                if (!startPlaceholder.getValue() || !endPlaceholder.getValue()) {
                                        successfulUpdate = false;
                                        String newStart = startShiftTextField.getText();
                                        String newEnd = endShiftTextField.getText();
                                        for (shift temp : listShifts)
                                                theManagerDB.updateEntryTime(temp, newStart);
                                        for (shift temp : listShifts)
                                                theManagerDB.updateEndTime(temp, newEnd);
                                        successfulUpdate = true;
                                }

                                if (!datePlaceholder.getValue() && successfulUpdate) {
                                        String newDate = dateTextField.getText();
                                        LocalDate newShiftDate = LocalDate.parse(newDate,
                                                        DateTimeFormatter.ofPattern("dd-MM-yyyy"));
                                        if (newShiftDate.isBefore(LocalDate.now())) {
                                                JOptionPane.showMessageDialog(playground,
                                                                "You can't change a shift's date to the past.", "ERROR",
                                                                JOptionPane.ERROR_MESSAGE);
                                                successfulUpdate = false;
                                        } else {
                                                for (shift temp : listShifts)
                                                        theManagerDB.updateShiftDate(temp, newDate);
                                                successfulUpdate = true;
                                        }
                                }

                                if (successfulUpdate)
                                        successLabel.setText("The shift(s) was/were successfully updated.");
                                else
                                        successLabel.setText("Something went wrong while updating the shift(s).");
                                successLabel.setVisible(true);

                        }

                        public void updatePlaceholders() {
                                playground.removeAll();
                                new mainShifts(playground, from, to, shiftDate);
                                playground.revalidate();
                                playground.repaint();
                        }
                }
                editButtonFormatter.formatEditButton(editShiftsButton, new editMethodsHolder());
        }

        private ArrayList<shift> getListOfShifts() {
                ArrayList<shift> listShifts = new ArrayList<shift>();
                for (int i = 0; i < modelSelected.getRowCount(); i++) {
                        int id = Integer.parseInt((String) modelSelected.getValueAt(i, 0));
                        String date = (String) modelSelected.getValueAt(i, 3);
                        String startTime = (String) modelSelected.getValueAt(i, 4);
                        String endTime = (String) modelSelected.getValueAt(i, 5);
                        listShifts.add(new shift(id, date, startTime, endTime));
                }
                return listShifts;
        }

        private void applyGenericListeners() {
                new inputFormatterFactory().createInputFormatter("DATE").applyFormat(dateTextField);
                iFormatter timeFormatter = new inputFormatterFactory().createInputFormatter("TIME");
                timeFormatter.applyFormat(startShiftTextField);
                timeFormatter.applyFormat(endShiftTextField);

                iTextFieldListener textListener = new editDateTFFListener();
                textListener.applyListenerTextField(dateTextField, "DD-MM-YYYY", datePlaceholder);
                textListener.applyListenerTextField(startShiftTextField, "HH:MM", startPlaceholder);
                textListener.applyListenerTextField(endShiftTextField, "HH:MM", endPlaceholder);
        }

        private void setTables(String from, String to, boolean shiftDate) {
                Color white = new Color(255, 255, 255);
                tableEmployees = new JTable();
                tableSelected = new JTable();
                modelEmployees = new DefaultTableModel(
                                new String[] { "id", "Employee", "Role", "Shift Date", "Start", "End" }, 0);
                modelSelected = new DefaultTableModel(
                                new String[] { "id", "Employee", "Role", "Shift Date", "Start", "End" }, 0);
                shiftsAPI theManagerDB = new shiftsAPI();
                for (shift tempShift : theManagerDB.getAllFutureShiftsUntil(to)) {
                        if (!tempShift.equals(theShift)) {
                                String id = Integer.toString(tempShift.getEmployeeId());
                                String name = theManagerDB.getNameOfEmployee(tempShift.getEmployeeId());
                                String role = theManagerDB.getRoleOfEmployee(tempShift.getEmployeeId());
                                String date = tempShift.getDate();
                                String start = tempShift.getStartTime();
                                String end = tempShift.getEndTime();
                                modelEmployees.addRow(new String[] { id, name, role, date, start, end });
                        }
                }
                String id = Integer.toString(theShift.getEmployeeId());
                String name = theManagerDB.getNameOfEmployee(theShift.getEmployeeId());
                String role = theManagerDB.getRoleOfEmployee(theShift.getEmployeeId());
                String date = theShift.getDate();
                String start = theShift.getStartTime();
                String end = theShift.getEndTime();
                modelSelected.addRow(new String[] { id, name, role, date, start, end });

                tableEmployees.setModel(modelEmployees);
                tableEmployees.removeColumn(tableEmployees.getColumn("id"));
                tableEmployees.setDefaultEditor(Object.class, null);
                tableEmployees.setBackground(white);
                unselectedJScrollPane.setBackground(white);
                unselectedJScrollPane.setViewportView(tableEmployees);
                tableLookPretty(tableEmployees);

                tableSelected.setModel(modelSelected);
                tableSelected.removeColumn(tableSelected.getColumn("id"));
                tableSelected.setDefaultEditor(Object.class, null);
                selectedJScrollPane.setViewportView(tableSelected);
                selectedJScrollPane.setBackground(white);
                selectedJScrollPane.getViewport().setBackground(white);
                tableLookPretty(tableSelected);
        }

        private void tableLookPretty(JTable theTable) {
                theTable.setDefaultEditor(Object.class, null);
                theTable.setFocusable(false);

                theTable.getTableHeader().setFont(new java.awt.Font("Segoe UI", 1, 9));
                theTable.getTableHeader().setBackground(new Color(120, 168, 252));
                theTable.setBackground(new Color(245, 245, 245));

                theTable.setFillsViewportHeight(true);
                theTable.setFont(new java.awt.Font("Segoe UI", 0, 9));
                DefaultTableCellRenderer centerRenderer = new DefaultTableCellRenderer();
                centerRenderer.setHorizontalAlignment(JLabel.CENTER);
                theTable.getColumnModel().getColumn(1).setCellRenderer(centerRenderer);
                theTable.getColumnModel().getColumn(2).setCellRenderer(centerRenderer);
                Dimension temp = new Dimension(20, 1);
                theTable.setIntercellSpacing(temp);
                theTable.setBackground(new Color(255, 255, 255));
                theTable.setRowHeight(theTable.getRowHeight() + 10);
        }
}