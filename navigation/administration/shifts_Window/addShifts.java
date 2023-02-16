package navigation.administration.shifts_Window;

import java.awt.event.MouseListener;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.awt.event.MouseEvent;

import javax.swing.*;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;

import componentsFood.employee;
import componentsFood.shift;
import navigation.administration.employeeSection.employeesAPI;
import navigation.administration.roleSection.rolesAPI;

import java.awt.*;

public class addShifts {

        private JLabel dateLabel = new JLabel();
        private JLabel startShiftLabel = new JLabel();
        private JLabel endShiftLabel = new JLabel();
        private JLabel addShiftLabel = new JLabel();
        private JLabel selectEmployeesLabel = new JLabel();

        private JTextField dateTextField = new JTextField();
        private JTextField startShiftTextField = new JTextField();
        private JTextField endShiftTextField = new JTextField();

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

        private boolean datePlaceholder = true;
        private boolean startPlaceholder = true;
        private boolean endPlaceholder = true;

        private String from;
        private String to;
        private boolean shiftDate;

        public addShifts(JPanel playground, String from, String to, boolean shiftDate) {
                this.from = from;
                this.to = to;
                this.shiftDate = shiftDate;
                initComponents(playground);
                setTables();
                addActionListeners(playground);
        }

        private void initComponents(JPanel playground) {
                playground.setBackground(new java.awt.Color(255, 255, 255));

                successLabel.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
                successLabel.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
                successLabel.setVisible(false);
                successLabel.setVerticalAlignment(javax.swing.SwingConstants.BOTTOM);

                jPanel1.setBackground(new java.awt.Color(120, 168, 252));
                jPanel1.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
                jPanel1.setDebugGraphicsOptions(javax.swing.DebugGraphics.NONE_OPTION);

                dateLabel.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
                dateLabel.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
                dateLabel.setText("Date");
                dateLabel.setVerticalAlignment(javax.swing.SwingConstants.BOTTOM);

                dateTextField.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
                dateTextField.setText("Enter DATE here YYYY-MM-DD");
                dateTextField.setForeground(Color.gray);

                addShiftsButton.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
                addShiftsButton.setText("Add Shift(s)");
                addShiftsButton.setBackground(new Color(255, 255, 255));
                addShiftsButton.setForeground(new Color(23, 35, 51));

                jPanel2.setBackground(new java.awt.Color(0, 0, 0));

                javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
                jPanel2.setLayout(jPanel2Layout);
                jPanel2Layout.setHorizontalGroup(
                                jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                .addGap(0, 8, Short.MAX_VALUE));
                jPanel2Layout.setVerticalGroup(
                                jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                .addGap(0, 0, Short.MAX_VALUE));

                startShiftLabel.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
                startShiftLabel.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
                startShiftLabel.setText("Start Shift");
                startShiftLabel.setVerticalAlignment(javax.swing.SwingConstants.BOTTOM);

                startShiftTextField.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
                startShiftTextField.setText("Enter START time here HH:MM");
                startShiftTextField.setForeground(Color.gray);

                endShiftLabel.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
                endShiftLabel.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
                endShiftLabel.setText("End Shift");
                endShiftLabel.setVerticalAlignment(javax.swing.SwingConstants.BOTTOM);

                endShiftTextField.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
                endShiftTextField.setText("Enter END time here HH:MM");
                endShiftTextField.setForeground(Color.gray);

                selectEmployeesLabel.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
                selectEmployeesLabel.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
                selectEmployeesLabel.setText("Select Employees");
                selectEmployeesLabel.setVerticalAlignment(javax.swing.SwingConstants.BOTTOM);

                selectButton.setText("Select");
                selectButton.setBackground(new Color(23, 35, 51));
                selectButton.setForeground(new Color(255, 255, 255));

                unselectButton.setText("Unselect");
                unselectButton.setBackground(new Color(23, 35, 51));
                unselectButton.setForeground(new Color(255, 255, 255));

                javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
                jPanel1.setLayout(jPanel1Layout);
                jPanel1Layout.setHorizontalGroup(
                                jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                .addGroup(jPanel1Layout.createSequentialGroup()
                                                                .addComponent(jPanel2,
                                                                                javax.swing.GroupLayout.PREFERRED_SIZE,
                                                                                javax.swing.GroupLayout.DEFAULT_SIZE,
                                                                                javax.swing.GroupLayout.PREFERRED_SIZE)
                                                                .addGap(57, 57, 57)
                                                                .addGroup(jPanel1Layout.createParallelGroup(
                                                                                javax.swing.GroupLayout.Alignment.TRAILING,
                                                                                false)
                                                                                .addGroup(jPanel1Layout
                                                                                                .createSequentialGroup()
                                                                                                .addGroup(jPanel1Layout
                                                                                                                .createParallelGroup(
                                                                                                                                javax.swing.GroupLayout.Alignment.TRAILING)
                                                                                                                .addGroup(javax.swing.GroupLayout.Alignment.LEADING,
                                                                                                                                jPanel1Layout.createSequentialGroup()
                                                                                                                                                .addComponent(endShiftLabel,
                                                                                                                                                                javax.swing.GroupLayout.PREFERRED_SIZE,
                                                                                                                                                                165,
                                                                                                                                                                javax.swing.GroupLayout.PREFERRED_SIZE)
                                                                                                                                                .addGap(0, 0, Short.MAX_VALUE))
                                                                                                                .addComponent(startShiftLabel,
                                                                                                                                javax.swing.GroupLayout.DEFAULT_SIZE,
                                                                                                                                javax.swing.GroupLayout.DEFAULT_SIZE,
                                                                                                                                Short.MAX_VALUE)
                                                                                                                .addComponent(dateLabel,
                                                                                                                                javax.swing.GroupLayout.DEFAULT_SIZE,
                                                                                                                                javax.swing.GroupLayout.DEFAULT_SIZE,
                                                                                                                                Short.MAX_VALUE))
                                                                                                .addPreferredGap(
                                                                                                                javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                                                                                .addGroup(jPanel1Layout
                                                                                                                .createParallelGroup(
                                                                                                                                javax.swing.GroupLayout.Alignment.LEADING)
                                                                                                                .addGroup(javax.swing.GroupLayout.Alignment.TRAILING,
                                                                                                                                jPanel1Layout.createParallelGroup(
                                                                                                                                                javax.swing.GroupLayout.Alignment.LEADING,
                                                                                                                                                false)
                                                                                                                                                .addComponent(startShiftTextField,
                                                                                                                                                                javax.swing.GroupLayout.Alignment.TRAILING)
                                                                                                                                                .addComponent(endShiftTextField,
                                                                                                                                                                javax.swing.GroupLayout.Alignment.TRAILING,
                                                                                                                                                                javax.swing.GroupLayout.DEFAULT_SIZE,
                                                                                                                                                                434,
                                                                                                                                                                Short.MAX_VALUE))
                                                                                                                .addComponent(dateTextField,
                                                                                                                                javax.swing.GroupLayout.Alignment.TRAILING,
                                                                                                                                javax.swing.GroupLayout.PREFERRED_SIZE,
                                                                                                                                434,
                                                                                                                                javax.swing.GroupLayout.PREFERRED_SIZE)))
                                                                                .addGroup(jPanel1Layout
                                                                                                .createSequentialGroup()
                                                                                                .addComponent(selectEmployeesLabel,
                                                                                                                javax.swing.GroupLayout.PREFERRED_SIZE,
                                                                                                                157,
                                                                                                                javax.swing.GroupLayout.PREFERRED_SIZE)
                                                                                                .addPreferredGap(
                                                                                                                javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                                                                                .addGroup(jPanel1Layout
                                                                                                                .createParallelGroup(
                                                                                                                                javax.swing.GroupLayout.Alignment.LEADING,
                                                                                                                                false)
                                                                                                                .addGroup(jPanel1Layout
                                                                                                                                .createSequentialGroup()
                                                                                                                                .addComponent(unselectedJScrollPane,
                                                                                                                                                javax.swing.GroupLayout.PREFERRED_SIZE,
                                                                                                                                                205,
                                                                                                                                                javax.swing.GroupLayout.PREFERRED_SIZE)
                                                                                                                                .addGap(18, 18, 18)
                                                                                                                                .addComponent(selectedJScrollPane,
                                                                                                                                                javax.swing.GroupLayout.PREFERRED_SIZE,
                                                                                                                                                213,
                                                                                                                                                javax.swing.GroupLayout.PREFERRED_SIZE))
                                                                                                                .addGroup(jPanel1Layout
                                                                                                                                .createSequentialGroup()
                                                                                                                                .addComponent(selectButton,
                                                                                                                                                javax.swing.GroupLayout.PREFERRED_SIZE,
                                                                                                                                                75,
                                                                                                                                                javax.swing.GroupLayout.PREFERRED_SIZE)
                                                                                                                                .addPreferredGap(
                                                                                                                                                javax.swing.LayoutStyle.ComponentPlacement.RELATED,
                                                                                                                                                javax.swing.GroupLayout.DEFAULT_SIZE,
                                                                                                                                                Short.MAX_VALUE)
                                                                                                                                .addComponent(addShiftsButton,
                                                                                                                                                javax.swing.GroupLayout.PREFERRED_SIZE,
                                                                                                                                                200,
                                                                                                                                                javax.swing.GroupLayout.PREFERRED_SIZE)
                                                                                                                                .addGap(50, 50, 50)
                                                                                                                                .addComponent(unselectButton)))))
                                                                .addContainerGap(200, Short.MAX_VALUE)));
                jPanel1Layout.setVerticalGroup(
                                jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout
                                                                .createSequentialGroup()
                                                                .addContainerGap()
                                                                .addGroup(jPanel1Layout.createParallelGroup(
                                                                                javax.swing.GroupLayout.Alignment.BASELINE)
                                                                                .addComponent(dateLabel)
                                                                                .addComponent(dateTextField,
                                                                                                javax.swing.GroupLayout.PREFERRED_SIZE,
                                                                                                35,
                                                                                                javax.swing.GroupLayout.PREFERRED_SIZE))
                                                                .addPreferredGap(
                                                                                javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                                                .addGroup(jPanel1Layout.createParallelGroup(
                                                                                javax.swing.GroupLayout.Alignment.BASELINE)
                                                                                .addComponent(startShiftTextField,
                                                                                                javax.swing.GroupLayout.PREFERRED_SIZE,
                                                                                                35,
                                                                                                javax.swing.GroupLayout.PREFERRED_SIZE)
                                                                                .addComponent(startShiftLabel))
                                                                .addPreferredGap(
                                                                                javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                                                .addGroup(jPanel1Layout.createParallelGroup(
                                                                                javax.swing.GroupLayout.Alignment.BASELINE)
                                                                                .addComponent(endShiftLabel)
                                                                                .addComponent(endShiftTextField,
                                                                                                javax.swing.GroupLayout.PREFERRED_SIZE,
                                                                                                35,
                                                                                                javax.swing.GroupLayout.PREFERRED_SIZE))
                                                                .addPreferredGap(
                                                                                javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                                                .addGroup(jPanel1Layout.createParallelGroup(
                                                                                javax.swing.GroupLayout.Alignment.LEADING,
                                                                                false)
                                                                                .addComponent(selectEmployeesLabel)
                                                                                .addComponent(unselectedJScrollPane,
                                                                                                javax.swing.GroupLayout.DEFAULT_SIZE,
                                                                                                193, Short.MAX_VALUE)
                                                                                .addComponent(selectedJScrollPane,
                                                                                                javax.swing.GroupLayout.PREFERRED_SIZE,
                                                                                                0, Short.MAX_VALUE))
                                                                .addGroup(jPanel1Layout.createParallelGroup(
                                                                                javax.swing.GroupLayout.Alignment.LEADING)
                                                                                .addGroup(jPanel1Layout
                                                                                                .createSequentialGroup()
                                                                                                .addGap(0, 28, Short.MAX_VALUE)
                                                                                                .addComponent(addShiftsButton,
                                                                                                                javax.swing.GroupLayout.PREFERRED_SIZE,
                                                                                                                55,
                                                                                                                javax.swing.GroupLayout.PREFERRED_SIZE)
                                                                                                .addGap(14, 14, 14))
                                                                                .addGroup(jPanel1Layout
                                                                                                .createSequentialGroup()
                                                                                                .addPreferredGap(
                                                                                                                javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                                                                                .addGroup(jPanel1Layout
                                                                                                                .createParallelGroup(
                                                                                                                                javax.swing.GroupLayout.Alignment.BASELINE)
                                                                                                                .addComponent(selectButton)
                                                                                                                .addComponent(unselectButton))
                                                                                                .addContainerGap(
                                                                                                                javax.swing.GroupLayout.DEFAULT_SIZE,
                                                                                                                Short.MAX_VALUE))))
                                                .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE,
                                                                javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE));

                jPanel3.setBackground(new java.awt.Color(71, 120, 197));

                javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
                jPanel3.setLayout(jPanel3Layout);
                jPanel3Layout.setHorizontalGroup(
                                jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                .addGap(0, 500, Short.MAX_VALUE));
                jPanel3Layout.setVerticalGroup(
                                jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                .addGap(0, 5, Short.MAX_VALUE));

                addShiftLabel.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
                addShiftLabel.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
                addShiftLabel.setText("Add Shift(s)");
                addShiftLabel.setVerticalAlignment(javax.swing.SwingConstants.BOTTOM);

                backButton.setBackground(new java.awt.Color(71, 120, 197));
                backButton.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
                backButton.setForeground(new java.awt.Color(255, 255, 255));
                backButton.setText("Back");

                javax.swing.GroupLayout playgroundLayout = new javax.swing.GroupLayout(playground);
                playground.setLayout(playgroundLayout);
                playgroundLayout.setHorizontalGroup(
                                playgroundLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE,
                                                                javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                                .addComponent(addShiftLabel, javax.swing.GroupLayout.Alignment.TRAILING,
                                                                javax.swing.GroupLayout.DEFAULT_SIZE,
                                                                javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                                .addGroup(playgroundLayout.createSequentialGroup()
                                                                .addGap(185, 185, 185)
                                                                .addComponent(jPanel3,
                                                                                javax.swing.GroupLayout.PREFERRED_SIZE,
                                                                                javax.swing.GroupLayout.DEFAULT_SIZE,
                                                                                javax.swing.GroupLayout.PREFERRED_SIZE)
                                                                .addGap(0, 185, Short.MAX_VALUE))
                                                .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, playgroundLayout
                                                                .createSequentialGroup()
                                                                .addContainerGap()
                                                                .addGroup(playgroundLayout.createParallelGroup(
                                                                                javax.swing.GroupLayout.Alignment.TRAILING)
                                                                                .addComponent(successLabel,
                                                                                                javax.swing.GroupLayout.DEFAULT_SIZE,
                                                                                                javax.swing.GroupLayout.DEFAULT_SIZE,
                                                                                                Short.MAX_VALUE)
                                                                                .addGroup(playgroundLayout
                                                                                                .createSequentialGroup()
                                                                                                .addComponent(backButton,
                                                                                                                javax.swing.GroupLayout.PREFERRED_SIZE,
                                                                                                                200,
                                                                                                                javax.swing.GroupLayout.PREFERRED_SIZE)
                                                                                                .addGap(0, 0, Short.MAX_VALUE)))
                                                                .addContainerGap()));
                playgroundLayout.setVerticalGroup(
                                playgroundLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, playgroundLayout
                                                                .createSequentialGroup()
                                                                .addGap(39, 39, 39)
                                                                .addComponent(addShiftLabel)
                                                                .addPreferredGap(
                                                                                javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                                                .addComponent(jPanel3,
                                                                                javax.swing.GroupLayout.PREFERRED_SIZE,
                                                                                javax.swing.GroupLayout.DEFAULT_SIZE,
                                                                                javax.swing.GroupLayout.PREFERRED_SIZE)
                                                                .addGap(18, 18, 18)
                                                                .addComponent(jPanel1,
                                                                                javax.swing.GroupLayout.PREFERRED_SIZE,
                                                                                javax.swing.GroupLayout.DEFAULT_SIZE,
                                                                                javax.swing.GroupLayout.PREFERRED_SIZE)
                                                                .addPreferredGap(
                                                                                javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                                                .addComponent(successLabel)
                                                                .addPreferredGap(
                                                                                javax.swing.LayoutStyle.ComponentPlacement.RELATED,
                                                                                javax.swing.GroupLayout.DEFAULT_SIZE,
                                                                                Short.MAX_VALUE)
                                                                .addComponent(backButton,
                                                                                javax.swing.GroupLayout.PREFERRED_SIZE,
                                                                                55,
                                                                                javax.swing.GroupLayout.PREFERRED_SIZE)
                                                                .addContainerGap()));
                selectedJScrollPane.getViewport().setBackground(new Color(245, 245, 245));
                selectedJScrollPane.setBackground(new Color(245, 245, 245));
                unselectedJScrollPane.getViewport().setBackground(new Color(245, 245, 245));
                unselectedJScrollPane.setBackground(new Color(245, 245, 245));
        }

        private void addActionListeners(JPanel playground) {
                backButton.addMouseListener(new MouseListener() {
                        public void mouseClicked(MouseEvent e) {
                                playground.removeAll();
                                new mainShifts(playground, from, to, shiftDate);
                                playground.revalidate();
                                playground.repaint();
                        }

                        public void mousePressed(MouseEvent e) {
                        }

                        public void mouseReleased(MouseEvent e) {
                        }

                        public void mouseEntered(MouseEvent e) {
                                backButton.setBackground(new Color(23, 35, 51));
                        }

                        public void mouseExited(MouseEvent e) {
                                backButton.setBackground(new Color(71, 120, 197));
                        }
                });
                addShiftsButton.addMouseListener(new MouseListener() {
                        // check inputs and add check shifts not imbeded. This is having a 10:00 - 18:00
                        // and also a 13:00-15:00 on the same day
                        public void mouseClicked(MouseEvent e) {
                                if (datePlaceholder || startPlaceholder || endPlaceholder) {
                                        successLabel.setText("Please fill all requested fields.");
                                        successLabel.setVisible(true);
                                        return;
                                }
                                String date = dateTextField.getText();
                                LocalDate today = LocalDate.now();
                                LocalDate localDateNew = LocalDate.parse(date,
                                                DateTimeFormatter.ofPattern("yyyy-MM-dd"));
                                if (localDateNew.isBefore(today)) {
                                        JOptionPane.showMessageDialog(playground,
                                                        "You can't add a shift that occured in the past.", "ERROR",
                                                        JOptionPane.ERROR_MESSAGE);
                                        return;
                                }
                                String startShift = startShiftTextField.getText();
                                String endShift = endShiftTextField.getText();
                                shiftsAPI theManagerDB = new shiftsAPI();
                                for (int i = 0; i < tableSelected.getRowCount(); i++) {
                                        String employeeID = (String) modelSelected.getValueAt(i, 0);
                                        theManagerDB.addShift(employeeID, date, startShift, endShift);
                                }
                                playground.removeAll();
                                new mainShifts(playground, from, to, shiftDate);
                                playground.revalidate();
                                playground.repaint();
                        }

                        public void mousePressed(MouseEvent e) {
                        }

                        public void mouseReleased(MouseEvent e) {
                        }

                        public void mouseEntered(MouseEvent e) {
                                addShiftsButton.setBackground(new Color(23, 35, 51));
                                addShiftsButton.setForeground(new Color(255, 255, 255));
                        }

                        public void mouseExited(MouseEvent e) {
                                addShiftsButton.setBackground(new Color(255, 255, 255));
                                addShiftsButton.setForeground(new Color(23, 35, 51));
                        }
                });
                addRightLeftListeners();
                addPlaceholderListeners();
        }

        private void addRightLeftListeners() {
                selectButton.addMouseListener(new MouseListener() {
                        public void mouseClicked(MouseEvent e) {
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

                        public void mousePressed(MouseEvent e) {
                        }

                        public void mouseReleased(MouseEvent e) {
                        }

                        public void mouseEntered(MouseEvent e) {
                                selectButton.setBackground(new Color(255, 255, 255));
                                selectButton.setForeground(new Color(23, 35, 51));

                        }

                        public void mouseExited(MouseEvent e) {
                                selectButton.setBackground(new Color(23, 35, 51));
                                selectButton.setForeground(new Color(255, 255, 255));
                        }
                });
                unselectButton.addMouseListener(new MouseListener() {
                        public void mouseClicked(MouseEvent e) {
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

                        public void mousePressed(MouseEvent e) {
                        }

                        public void mouseReleased(MouseEvent e) {
                        }

                        public void mouseEntered(MouseEvent e) {
                                unselectButton.setBackground(new Color(255, 255, 255));
                                unselectButton.setForeground(new Color(23, 35, 51));

                        }

                        public void mouseExited(MouseEvent e) {
                                unselectButton.setBackground(new Color(23, 35, 51));
                                unselectButton.setForeground(new Color(255, 255, 255));
                        }
                });
        }

        private void addPlaceholderListeners() {
                dateTextField.addFocusListener(new FocusListener() {
                        public void focusGained(FocusEvent e) {
                                if (dateTextField.getText().equals("Enter DATE here YYYY-MM-DD")) {
                                        dateTextField.setText("");
                                        dateTextField.setForeground(Color.BLACK);
                                        datePlaceholder = false;
                                }
                        }

                        @Override
                        public void focusLost(FocusEvent e) {
                                if (dateTextField.getText().isEmpty()) {
                                        dateTextField.setForeground(Color.GRAY);
                                        dateTextField.setText("Enter DATE here YYYY-MM-DD");
                                        datePlaceholder = true;
                                }
                        }
                });
                startShiftTextField.addFocusListener(new FocusListener() {
                        public void focusGained(FocusEvent e) {
                                if (startShiftTextField.getText().equals("Enter START time here HH:MM")) {
                                        startShiftTextField.setText("");
                                        startShiftTextField.setForeground(Color.BLACK);
                                        startPlaceholder = false;
                                }
                        }

                        @Override
                        public void focusLost(FocusEvent e) {
                                if (startShiftTextField.getText().isEmpty()) {
                                        startShiftTextField.setForeground(Color.GRAY);
                                        startShiftTextField.setText("Enter START time here HH:MM");
                                        startPlaceholder = true;
                                }
                        }
                });
                endShiftTextField.addFocusListener(new FocusListener() {
                        public void focusGained(FocusEvent e) {
                                if (endShiftTextField.getText().equals("Enter END time here HH:MM")) {
                                        endShiftTextField.setText("");
                                        endShiftTextField.setForeground(Color.BLACK);
                                        endPlaceholder = false;
                                }
                        }

                        @Override
                        public void focusLost(FocusEvent e) {
                                if (endShiftTextField.getText().isEmpty()) {
                                        endShiftTextField.setForeground(Color.GRAY);
                                        endShiftTextField.setText("Enter END time here HH:MM");
                                        endPlaceholder = true;
                                }
                        }
                });
        }

        private void setTables() {
                tableEmployees = new JTable();
                tableSelected = new JTable();
                modelEmployees = new DefaultTableModel(
                                new String[] { "id", "Name", "Salary", "Hours/Week", "Role", "role_id" }, 0);
                modelSelected = new DefaultTableModel(
                                new String[] { "id", "Name", "Salary", "Hours/Week", "Role", "role_id" }, 0);
                rolesAPI theManagerDB = new rolesAPI();
                for (employee tempEmp : new employeesAPI().getAllCurrentEmployeesOrdered()) {
                        String id = Integer.toString(tempEmp.getId());
                        String name = tempEmp.getName();
                        String salary = Float.toString(tempEmp.getSalary());
                        String hoursWeek = tempEmp.getHoursWeek().substring(0, 5);
                        String role = theManagerDB.getNameOfRoleID(tempEmp.getRoleID());
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
                theTable.getTableHeader().setFont(new java.awt.Font("Segoe UI", 1, 9));
                theTable.getTableHeader().setBackground(new Color(120, 168, 252));
                theTable.setFillsViewportHeight(true);
                theTable.setFont(new java.awt.Font("Segoe UI", 0, 9));
                DefaultTableCellRenderer centerRenderer = new DefaultTableCellRenderer();
                centerRenderer.setHorizontalAlignment(JLabel.CENTER);
                theTable.getColumnModel().getColumn(1).setCellRenderer(centerRenderer);
                theTable.getColumnModel().getColumn(2).setCellRenderer(centerRenderer);
                Dimension temp = new Dimension(20, 1);
                theTable.setIntercellSpacing(temp);
                theTable.setRowHeight(theTable.getRowHeight() + 10);
        }
}