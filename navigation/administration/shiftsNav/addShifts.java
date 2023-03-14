package navigation.administration.shiftsNav;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Date;

import javax.swing.*;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;

import componentsFood.employee;
import util.buttonFormatters.*;
import util.databaseAPIs.employeesAPI;
import util.databaseAPIs.rolesAPI;
import util.databaseAPIs.shiftsAPI;
import util.inputFormatting.iFormatter;
import util.inputFormatting.inputFormatterFactory;
import util.listenersFormatting.booleanWrapper;
import util.listenersFormatting.iTextFieldListener;
import util.listenersFormatting.edit.editDateTFFListener;

import java.awt.*;
import java.text.ParseException;
import java.text.SimpleDateFormat;

public class addShifts {

        private JLabel dateLabel = new JLabel();
        private JLabel startShiftLabel = new JLabel();
        private JLabel endShiftLabel = new JLabel();
        private JLabel addShiftLabel = new JLabel();
        private JLabel selectEmployeesLabel = new JLabel();

        private JTextField dateTextField = new JTextField();
        private JTextField startShiftTextField = new JTextField();
        private JTextField endShiftTextField = new JTextField();

        private booleanWrapper datePlaceholder = new booleanWrapper(true);
        private booleanWrapper startPlaceholder = new booleanWrapper(true);
        private booleanWrapper endPlaceholder = new booleanWrapper(true);

        private JButton addShiftsButton = new JButton();
        private JButton backButton = new JButton();
        private JButton selectButton = new JButton();
        private JButton unselectButton = new JButton();

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

        private String from;
        private String to;
        private boolean shiftDate;

        public addShifts(JPanel playground, String from, String to, boolean shiftDate) {
                this.from = from;
                this.to = to;
                this.shiftDate = shiftDate;
                initComponents(playground);
                setTables();
                addListeners(playground);
        }

        private void initComponents(JPanel playground) {
                playground.setBackground(new Color(255, 255, 255));

                successLabel.setFont(new Font("Segoe UI", 0, 18)); // NOI18N
                successLabel.setHorizontalAlignment(SwingConstants.RIGHT);
                successLabel.setVisible(false);
                successLabel.setVerticalAlignment(SwingConstants.BOTTOM);

                jPanel1.setBackground(new Color(120, 168, 252));
                jPanel1.setCursor(new Cursor(java.awt.Cursor.DEFAULT_CURSOR));
                jPanel1.setDebugGraphicsOptions(javax.swing.DebugGraphics.NONE_OPTION);

                dateLabel.setFont(new Font("Segoe UI", 1, 18)); // NOI18N
                dateLabel.setHorizontalAlignment(SwingConstants.LEFT);
                dateLabel.setText("Date");
                dateLabel.setVerticalAlignment(SwingConstants.BOTTOM);

                addShiftsButton.setFont(new Font("Segoe UI", 1, 14)); // NOI18N
                addShiftsButton.setText("Add Shift(s)");
                addShiftsButton.setBackground(new Color(255, 255, 255));
                addShiftsButton.setForeground(new Color(23, 35, 51));

                jPanel2.setBackground(new Color(0, 0, 0));

                GroupLayout jPanel2Layout = new GroupLayout(jPanel2);
                jPanel2.setLayout(jPanel2Layout);
                jPanel2Layout.setHorizontalGroup(
                                jPanel2Layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                                                .addGap(0, 8, Short.MAX_VALUE));
                jPanel2Layout.setVerticalGroup(
                                jPanel2Layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                                                .addGap(0, 0, Short.MAX_VALUE));

                startShiftLabel.setFont(new Font("Segoe UI", 1, 18)); // NOI18N
                startShiftLabel.setHorizontalAlignment(SwingConstants.LEFT);
                startShiftLabel.setText("Start Shift");

                startShiftLabel.setVerticalAlignment(SwingConstants.BOTTOM);

                endShiftLabel.setFont(new Font("Segoe UI", 1, 18)); // NOI18N
                endShiftLabel.setHorizontalAlignment(SwingConstants.LEFT);
                endShiftLabel.setText("End Shift");
                endShiftLabel.setVerticalAlignment(SwingConstants.BOTTOM);

                selectEmployeesLabel.setFont(new Font("Segoe UI", 1, 18)); // NOI18N
                selectEmployeesLabel.setHorizontalAlignment(SwingConstants.LEFT);
                selectEmployeesLabel.setText("Select Employees");
                selectEmployeesLabel.setVerticalAlignment(SwingConstants.BOTTOM);

                GroupLayout jPanel1Layout = new GroupLayout(jPanel1);
                jPanel1.setLayout(jPanel1Layout);
                jPanel1Layout.setHorizontalGroup(
                                jPanel1Layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                                                .addGroup(jPanel1Layout.createSequentialGroup()
                                                                .addComponent(jPanel2,
                                                                                GroupLayout.PREFERRED_SIZE,
                                                                                GroupLayout.DEFAULT_SIZE,
                                                                                GroupLayout.PREFERRED_SIZE)
                                                                .addGap(57, 57, 57)
                                                                .addGroup(jPanel1Layout.createParallelGroup(
                                                                                GroupLayout.Alignment.TRAILING,
                                                                                false)
                                                                                .addGroup(jPanel1Layout
                                                                                                .createSequentialGroup()
                                                                                                .addGroup(jPanel1Layout
                                                                                                                .createParallelGroup(
                                                                                                                                GroupLayout.Alignment.TRAILING)
                                                                                                                .addGroup(GroupLayout.Alignment.LEADING,
                                                                                                                                jPanel1Layout.createSequentialGroup()
                                                                                                                                                .addComponent(endShiftLabel,
                                                                                                                                                                GroupLayout.PREFERRED_SIZE,
                                                                                                                                                                165,
                                                                                                                                                                GroupLayout.PREFERRED_SIZE)
                                                                                                                                                .addGap(0, 0, Short.MAX_VALUE))
                                                                                                                .addComponent(startShiftLabel,
                                                                                                                                GroupLayout.DEFAULT_SIZE,
                                                                                                                                GroupLayout.DEFAULT_SIZE,
                                                                                                                                Short.MAX_VALUE)
                                                                                                                .addComponent(dateLabel,
                                                                                                                                GroupLayout.DEFAULT_SIZE,
                                                                                                                                GroupLayout.DEFAULT_SIZE,
                                                                                                                                Short.MAX_VALUE))
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
                                                                                                                                                                GroupLayout.DEFAULT_SIZE,
                                                                                                                                                                434,
                                                                                                                                                                Short.MAX_VALUE))
                                                                                                                .addComponent(dateTextField,
                                                                                                                                GroupLayout.Alignment.TRAILING,
                                                                                                                                GroupLayout.PREFERRED_SIZE,
                                                                                                                                434,
                                                                                                                                GroupLayout.PREFERRED_SIZE)))
                                                                                .addGroup(jPanel1Layout
                                                                                                .createSequentialGroup()
                                                                                                .addComponent(selectEmployeesLabel,
                                                                                                                GroupLayout.PREFERRED_SIZE,
                                                                                                                157,
                                                                                                                GroupLayout.PREFERRED_SIZE)
                                                                                                .addPreferredGap(
                                                                                                                ComponentPlacement.UNRELATED)
                                                                                                .addGroup(jPanel1Layout
                                                                                                                .createParallelGroup(
                                                                                                                                GroupLayout.Alignment.LEADING,
                                                                                                                                false)
                                                                                                                .addGroup(jPanel1Layout
                                                                                                                                .createSequentialGroup()
                                                                                                                                .addComponent(unselectedJScrollPane,
                                                                                                                                                GroupLayout.PREFERRED_SIZE,
                                                                                                                                                205,
                                                                                                                                                GroupLayout.PREFERRED_SIZE)
                                                                                                                                .addGap(18, 18, 18)
                                                                                                                                .addComponent(selectedJScrollPane,
                                                                                                                                                GroupLayout.PREFERRED_SIZE,
                                                                                                                                                213,
                                                                                                                                                GroupLayout.PREFERRED_SIZE))
                                                                                                                .addGroup(jPanel1Layout
                                                                                                                                .createSequentialGroup()
                                                                                                                                .addComponent(selectButton,
                                                                                                                                                GroupLayout.PREFERRED_SIZE,
                                                                                                                                                75,
                                                                                                                                                GroupLayout.PREFERRED_SIZE)
                                                                                                                                .addPreferredGap(
                                                                                                                                                ComponentPlacement.RELATED,
                                                                                                                                                GroupLayout.DEFAULT_SIZE,
                                                                                                                                                Short.MAX_VALUE)
                                                                                                                                .addComponent(addShiftsButton,
                                                                                                                                                GroupLayout.PREFERRED_SIZE,
                                                                                                                                                200,
                                                                                                                                                GroupLayout.PREFERRED_SIZE)
                                                                                                                                .addGap(50, 50, 50)
                                                                                                                                .addComponent(unselectButton)))))
                                                                .addContainerGap(200, Short.MAX_VALUE)));
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
                                                                .addPreferredGap(
                                                                                ComponentPlacement.RELATED)
                                                                .addGroup(jPanel1Layout.createParallelGroup(
                                                                                GroupLayout.Alignment.LEADING,
                                                                                false)
                                                                                .addComponent(selectEmployeesLabel)
                                                                                .addComponent(unselectedJScrollPane,
                                                                                                GroupLayout.DEFAULT_SIZE,
                                                                                                193, Short.MAX_VALUE)
                                                                                .addComponent(selectedJScrollPane,
                                                                                                GroupLayout.PREFERRED_SIZE,
                                                                                                0, Short.MAX_VALUE))
                                                                .addGroup(jPanel1Layout.createParallelGroup(
                                                                                GroupLayout.Alignment.LEADING)
                                                                                .addGroup(jPanel1Layout
                                                                                                .createSequentialGroup()
                                                                                                .addGap(0, 28, Short.MAX_VALUE)
                                                                                                .addComponent(addShiftsButton,
                                                                                                                GroupLayout.PREFERRED_SIZE,
                                                                                                                55,
                                                                                                                GroupLayout.PREFERRED_SIZE)
                                                                                                .addGap(14, 14, 14))
                                                                                .addGroup(jPanel1Layout
                                                                                                .createSequentialGroup()
                                                                                                .addPreferredGap(
                                                                                                                ComponentPlacement.RELATED)
                                                                                                .addGroup(jPanel1Layout
                                                                                                                .createParallelGroup(
                                                                                                                                GroupLayout.Alignment.BASELINE)
                                                                                                                .addComponent(selectButton)
                                                                                                                .addComponent(unselectButton))
                                                                                                .addContainerGap(
                                                                                                                GroupLayout.DEFAULT_SIZE,
                                                                                                                Short.MAX_VALUE))))
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

                addShiftLabel.setFont(new Font("Segoe UI", 1, 18)); // NOI18N
                addShiftLabel.setHorizontalAlignment(SwingConstants.CENTER);
                addShiftLabel.setText("Add Shift(s)");
                addShiftLabel.setVerticalAlignment(SwingConstants.BOTTOM);

                GroupLayout playgroundLayout = new GroupLayout(playground);
                playground.setLayout(playgroundLayout);
                playgroundLayout.setHorizontalGroup(
                                playgroundLayout.createParallelGroup(GroupLayout.Alignment.LEADING)
                                                .addComponent(jPanel1, GroupLayout.DEFAULT_SIZE,
                                                                GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                                .addComponent(addShiftLabel, GroupLayout.Alignment.TRAILING,
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
                                                                                                .addGap(0, 0, Short.MAX_VALUE)))
                                                                .addContainerGap()));
                playgroundLayout.setVerticalGroup(
                                playgroundLayout.createParallelGroup(GroupLayout.Alignment.LEADING)
                                                .addGroup(GroupLayout.Alignment.TRAILING, playgroundLayout
                                                                .createSequentialGroup()
                                                                .addGap(39, 39, 39)
                                                                .addComponent(addShiftLabel)
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
                                                                                GroupLayout.DEFAULT_SIZE,
                                                                                Short.MAX_VALUE)
                                                                .addComponent(backButton,
                                                                                GroupLayout.PREFERRED_SIZE,
                                                                                55,
                                                                                GroupLayout.PREFERRED_SIZE)
                                                                .addContainerGap()));
                selectedJScrollPane.getViewport().setBackground(new Color(245, 245, 245));
                selectedJScrollPane.setBackground(new Color(245, 245, 245));
                unselectedJScrollPane.getViewport().setBackground(new Color(245, 245, 245));
                unselectedJScrollPane.setBackground(new Color(245, 245, 245));
        }

        private ArrayList<Integer> getSelectedEmployeeIDs() {
                ArrayList<Integer> selectedEmployees = new ArrayList<Integer>();
                for (int i = 0; i < modelSelected.getRowCount(); i++)
                        selectedEmployees.add(Integer.parseInt((String) modelSelected.getValueAt(i, 0)));
                return selectedEmployees;
        }

        private void addListeners(JPanel playground) {
                backButton(playground);
                addButton(playground);
                selectionButtons();
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
                class backMethodHolder extends iNavigatorButton {
                        public void createNewNavigator() {
                                new mainShifts(playground, from, to, shiftDate);
                        }
                }
                navigatorButtonFormatter.formatNavigationButton(backButton, new backMethodHolder(), playground, true,
                                "Back");
        }

        private void addButton(JPanel playground) {
                class addMethodsHolder extends iAddButton {
                        private String date;
                        private String startShift;
                        private String endShift;
                        private ArrayList<Integer> selectedEmployeesIDs;

                        public boolean valuesArePlaceholders() {
                                boolean arePlaceholders = (datePlaceholder.getValue() || startPlaceholder.getValue()
                                                || endPlaceholder.getValue());
                                if (arePlaceholders) {
                                        successLabel.setText("Error. You must fill all the given fields.");
                                        successLabel.setVisible(true);
                                        return arePlaceholders;
                                }
                                arePlaceholders = modelSelected.getRowCount() == 0;
                                if (arePlaceholders) {
                                        successLabel.setText("Error. You must select at least one employee.");
                                        successLabel.setVisible(true);
                                        return true;
                                }
                                return false;
                        }

                        public boolean areInputsInvalid() {
                                // Get values.
                                date = dateTextField.getText();
                                startShift = startShiftTextField.getText();
                                endShift = endShiftTextField.getText();

                                // Check for correct date input.
                                LocalDate localDateNew;
                                try {
                                        localDateNew = LocalDate.parse(date,
                                                        DateTimeFormatter.ofPattern("dd-MM-yyyy"));
                                } catch (Exception DateTimeParseException) {
                                        successLabel.setText("ERROR. The given date is unvalid.");
                                        successLabel.setVisible(true);
                                        return true;
                                }
                                if (localDateNew.isBefore(LocalDate.now())) {
                                        successLabel.setText("ERROR. You can't add a shift that occured in the past.");
                                        successLabel.setVisible(true);
                                        return true;
                                }

                                // Check for correct time input.
                                int startHour = Integer.parseInt(startShift.substring(0, 2));
                                int startMins = Integer.parseInt(startShift.substring(3, 5));
                                int endHour = Integer.parseInt(endShift.substring(0, 2));
                                int endMins = Integer.parseInt(endShift.substring(3, 5));
                                if (startHour >= 24 || startMins >= 60 || endHour >= 24 || endMins >= 60) {
                                        successLabel.setText("ERROR. The given time is incorrect.");
                                        successLabel.setVisible(true);
                                        return true;
                                }

                                // Checks that entry time comes before exit time.
                                SimpleDateFormat timeFormat = new SimpleDateFormat("HH:MM");
                                try {
                                        Date start = timeFormat.parse(startShift);
                                        Date end = timeFormat.parse(endShift);
                                        if (start.after(end)) {
                                                successLabel.setText("ERROR. The shift start must be before the end.");
                                                successLabel.setVisible(true);
                                                return true;
                                        }
                                } catch (ParseException e) {
                                        successLabel.setText("ERROR. The given times are unvalid.");
                                        successLabel.setVisible(true);
                                        return true;
                                }

                                // Checks for conflicts, employees already working on that time.
                                selectedEmployeesIDs = getSelectedEmployeeIDs();
                                ArrayList<String> employeeNames = shiftsAPI.getNamesOfEmbededShifts(
                                                selectedEmployeesIDs, date,
                                                startShift, endShift);
                                if (employeeNames.size() > 0) {
                                        successLabel.setText(
                                                        "Error. The following employees already have shifts within the given time: ");
                                        for (String temp : employeeNames)
                                                successLabel.setText(successLabel.getText().concat(temp + ", "));
                                        String text = successLabel.getText();
                                        successLabel.setText(text.substring(0, text.length() - 2).concat("."));
                                        successLabel.setVisible(true);
                                        return false;
                                }

                                // If all checks are passed, input should be valid.
                                return false;
                        }

                        public boolean addFoodComponent() {
                                if (shiftsAPI.addShifts(selectedEmployeesIDs, date, startShift, endShift))
                                        return true;
                                successLabel.setText("Error. Something went wrong while adding shifts.");
                                successLabel.setVisible(true);
                                return false;
                        }

                        public void extraSteps() {
                                playground.removeAll();
                                new mainShifts(playground, from, to, shiftDate);
                                playground.revalidate();
                                playground.repaint();
                        }

                }
                addButtonFormatter.formatAddButton(addShiftsButton, new addMethodsHolder());
        }

        private void applyGenericListeners() {
                new inputFormatterFactory().createInputFormatter("DATE").applyFormat(dateTextField);
                iFormatter timeFormatter = new inputFormatterFactory().createInputFormatter("TIME");
                timeFormatter.applyFormat(startShiftTextField);
                timeFormatter.applyFormat(endShiftTextField);

                iTextFieldListener textListener = new editDateTFFListener();
                textListener.applyListenerTextField(dateTextField, "DD-MM-YYYY", datePlaceholder, false);
                textListener.applyListenerTextField(startShiftTextField, "HH:MM", startPlaceholder, false);
                textListener.applyListenerTextField(endShiftTextField, "HH:MM", endPlaceholder, false);
        }

        private void setTables() {
                tableEmployees = new JTable();
                tableSelected = new JTable();
                modelEmployees = new DefaultTableModel(
                                new String[] { "id", "Name", "Salary", "Hours/Week", "Role", "role_id" }, 0);
                modelSelected = new DefaultTableModel(
                                new String[] { "id", "Name", "Salary", "Hours/Week", "Role", "role_id" }, 0);
                for (employee tempEmp : employeesAPI.getAllCurrentEmployeesOrdered()) {
                        String id = Integer.toString(tempEmp.getId());
                        String name = tempEmp.getName();
                        String salary = Float.toString(tempEmp.getSalary());
                        String hoursWeek = tempEmp.getHoursWeek().substring(0, 5);
                        String role = rolesAPI.getNameOfRole(tempEmp.getRoleID());
                        String roleID = Integer.toString(tempEmp.getRoleID());
                        modelEmployees.addRow(new String[] { id, name, salary, hoursWeek, role, roleID });
                }
                tableEmployees.setModel(modelEmployees);
                tableSelected.setModel(modelSelected);
                tableEmployees.removeColumn(tableEmployees.getColumn("id"));
                tableEmployees.removeColumn(tableEmployees.getColumn("role_id"));
                tableSelected.removeColumn(tableSelected.getColumn("id"));
                tableSelected.removeColumn(tableSelected.getColumn("role_id"));
                unselectedJScrollPane.setViewportView(tableEmployees);
                selectedJScrollPane.setViewportView(tableSelected);
                tableEmployees.setDefaultEditor(Object.class, null);
                tableSelected.setDefaultEditor(Object.class, null);
                tableLookPretty(tableEmployees);
                tableLookPretty(tableSelected);
        }

        private void tableLookPretty(JTable theTable) {
                theTable.setDefaultEditor(Object.class, null);
                theTable.setFocusable(true);
                theTable.getTableHeader().setFont(new Font("Segoe UI", 1, 9));
                theTable.getTableHeader().setBackground(new Color(120, 168, 252));
                theTable.setFillsViewportHeight(true);
                theTable.setFont(new Font("Segoe UI", 0, 9));
                DefaultTableCellRenderer centerRenderer = new DefaultTableCellRenderer();
                centerRenderer.setHorizontalAlignment(JLabel.CENTER);
                theTable.getColumnModel().getColumn(1).setCellRenderer(centerRenderer);
                theTable.getColumnModel().getColumn(2).setCellRenderer(centerRenderer);
                Dimension temp = new Dimension(20, 1);
                theTable.setIntercellSpacing(temp);
                theTable.setRowHeight(theTable.getRowHeight() + 10);
        }
}