package navigation.administration.shifts_Window;

import javax.swing.table.*;
import java.awt.event.*;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Date;

import javax.swing.*;
import java.awt.*;

import componentsFood.shift;

public class editShifts {

        private JLabel dateLabel = new JLabel();
        private JLabel startShiftLabel = new JLabel();
        private JLabel endShiftLabel = new JLabel();

        private JTextField dateTextField = new JTextField();
        private JTextField startShiftTextField = new JTextField();
        private JTextField endShiftTextField = new JTextField();

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

        private boolean datePlaceholder = true;
        private boolean startPlaceholder = true;
        private boolean endPlaceholder = true;
        private boolean shiftDate;

        private shift theShift;
        private String from;
        private String to;

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
                dateTextField.setText(theShift.getDate());
                dateTextField.setForeground(Color.GRAY);

                editShiftsButton.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
                editShiftsButton.setText("Edit Shift(s)");
                editShiftsButton.setBackground(new Color(255, 255, 255));
                editShiftsButton.setForeground(new Color(23, 35, 51));

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
                startShiftTextField.setText(theShift.getStartTime().substring(0, 5));
                startShiftTextField.setForeground(Color.GRAY);

                endShiftLabel.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
                endShiftLabel.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
                endShiftLabel.setText("End Shift");
                endShiftLabel.setVerticalAlignment(javax.swing.SwingConstants.BOTTOM);

                endShiftTextField.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
                endShiftTextField.setText(theShift.getEndTime().substring(0, 5));
                endShiftTextField.setForeground(Color.GRAY);

                selectShiftsLabel.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
                selectShiftsLabel.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
                selectShiftsLabel.setText("Select Shifts");
                selectShiftsLabel.setVerticalAlignment(javax.swing.SwingConstants.BOTTOM);
                selectButton.setText("Select");
                selectButton.setBackground(new Color(23, 35, 51));
                selectButton.setForeground(new Color(255, 255, 255));
                unselectButton.setText("Unselect");
                unselectButton.setBackground(new Color(23, 35, 51));
                unselectButton.setForeground(new Color(255, 255, 255));

                javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
                jPanel1.setLayout(jPanel1Layout);
                jPanel1.setLayout(jPanel1Layout);
                jPanel1Layout.setHorizontalGroup(
                                jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                .addGroup(jPanel1Layout.createSequentialGroup()
                                                                .addComponent(jPanel2,
                                                                                javax.swing.GroupLayout.PREFERRED_SIZE,
                                                                                javax.swing.GroupLayout.DEFAULT_SIZE,
                                                                                javax.swing.GroupLayout.PREFERRED_SIZE)
                                                                .addGroup(jPanel1Layout.createParallelGroup(
                                                                                javax.swing.GroupLayout.Alignment.LEADING)
                                                                                .addGroup(jPanel1Layout
                                                                                                .createSequentialGroup()
                                                                                                .addGap(125, 125, 125)
                                                                                                .addGroup(jPanel1Layout
                                                                                                                .createParallelGroup(
                                                                                                                                javax.swing.GroupLayout.Alignment.LEADING,
                                                                                                                                false)
                                                                                                                .addComponent(endShiftLabel,
                                                                                                                                javax.swing.GroupLayout.DEFAULT_SIZE,
                                                                                                                                javax.swing.GroupLayout.DEFAULT_SIZE,
                                                                                                                                Short.MAX_VALUE)
                                                                                                                .addComponent(startShiftLabel,
                                                                                                                                javax.swing.GroupLayout.DEFAULT_SIZE,
                                                                                                                                javax.swing.GroupLayout.DEFAULT_SIZE,
                                                                                                                                Short.MAX_VALUE)
                                                                                                                .addComponent(dateLabel,
                                                                                                                                javax.swing.GroupLayout.PREFERRED_SIZE,
                                                                                                                                86,
                                                                                                                                javax.swing.GroupLayout.PREFERRED_SIZE))
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
                                                                                                                                                                javax.swing.GroupLayout.PREFERRED_SIZE,
                                                                                                                                                                434,
                                                                                                                                                                javax.swing.GroupLayout.PREFERRED_SIZE))
                                                                                                                .addComponent(dateTextField,
                                                                                                                                javax.swing.GroupLayout.Alignment.TRAILING,
                                                                                                                                javax.swing.GroupLayout.PREFERRED_SIZE,
                                                                                                                                434,
                                                                                                                                javax.swing.GroupLayout.PREFERRED_SIZE))
                                                                                                .addContainerGap(
                                                                                                                javax.swing.GroupLayout.DEFAULT_SIZE,
                                                                                                                Short.MAX_VALUE))
                                                                                .addGroup(javax.swing.GroupLayout.Alignment.TRAILING,
                                                                                                jPanel1Layout.createSequentialGroup()
                                                                                                                .addPreferredGap(
                                                                                                                                javax.swing.LayoutStyle.ComponentPlacement.RELATED,
                                                                                                                                javax.swing.GroupLayout.DEFAULT_SIZE,
                                                                                                                                Short.MAX_VALUE)
                                                                                                                .addGroup(jPanel1Layout
                                                                                                                                .createParallelGroup(
                                                                                                                                                javax.swing.GroupLayout.Alignment.LEADING,
                                                                                                                                                false)
                                                                                                                                .addGroup(javax.swing.GroupLayout.Alignment.TRAILING,
                                                                                                                                                jPanel1Layout.createSequentialGroup()
                                                                                                                                                                .addComponent(selectButton,
                                                                                                                                                                                javax.swing.GroupLayout.PREFERRED_SIZE,
                                                                                                                                                                                75,
                                                                                                                                                                                javax.swing.GroupLayout.PREFERRED_SIZE)
                                                                                                                                                                .addPreferredGap(
                                                                                                                                                                                javax.swing.LayoutStyle.ComponentPlacement.RELATED,
                                                                                                                                                                                javax.swing.GroupLayout.DEFAULT_SIZE,
                                                                                                                                                                                Short.MAX_VALUE)
                                                                                                                                                                .addComponent(editShiftsButton,
                                                                                                                                                                                javax.swing.GroupLayout.PREFERRED_SIZE,
                                                                                                                                                                                200,
                                                                                                                                                                                javax.swing.GroupLayout.PREFERRED_SIZE)
                                                                                                                                                                .addGap(163, 163,
                                                                                                                                                                                163)
                                                                                                                                                                .addComponent(unselectButton))
                                                                                                                                .addGroup(javax.swing.GroupLayout.Alignment.TRAILING,
                                                                                                                                                jPanel1Layout.createSequentialGroup()
                                                                                                                                                                .addComponent(unselectedJScrollPane,
                                                                                                                                                                                javax.swing.GroupLayout.PREFERRED_SIZE,
                                                                                                                                                                                333,
                                                                                                                                                                                javax.swing.GroupLayout.PREFERRED_SIZE)
                                                                                                                                                                .addPreferredGap(
                                                                                                                                                                                javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                                                                                                                                                .addComponent(selectedJScrollPane,
                                                                                                                                                                                javax.swing.GroupLayout.PREFERRED_SIZE,
                                                                                                                                                                                333,
                                                                                                                                                                                javax.swing.GroupLayout.PREFERRED_SIZE)))
                                                                                                                .addGap(99, 99, 99)))));
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
                                                                .addGap(18, 18, 18)
                                                                .addGroup(jPanel1Layout.createParallelGroup(
                                                                                javax.swing.GroupLayout.Alignment.LEADING)
                                                                                .addComponent(selectedJScrollPane,
                                                                                                javax.swing.GroupLayout.PREFERRED_SIZE,
                                                                                                190,
                                                                                                javax.swing.GroupLayout.PREFERRED_SIZE)
                                                                                .addComponent(unselectedJScrollPane,
                                                                                                javax.swing.GroupLayout.PREFERRED_SIZE,
                                                                                                190,
                                                                                                javax.swing.GroupLayout.PREFERRED_SIZE))
                                                                .addPreferredGap(
                                                                                javax.swing.LayoutStyle.ComponentPlacement.RELATED,
                                                                                javax.swing.GroupLayout.DEFAULT_SIZE,
                                                                                Short.MAX_VALUE)
                                                                .addGroup(jPanel1Layout.createParallelGroup(
                                                                                javax.swing.GroupLayout.Alignment.LEADING)
                                                                                .addGroup(javax.swing.GroupLayout.Alignment.TRAILING,
                                                                                                jPanel1Layout.createSequentialGroup()
                                                                                                                .addGroup(jPanel1Layout
                                                                                                                                .createParallelGroup(
                                                                                                                                                javax.swing.GroupLayout.Alignment.BASELINE)
                                                                                                                                .addComponent(unselectButton)
                                                                                                                                .addComponent(selectButton))
                                                                                                                .addGap(52, 52, 52))
                                                                                .addComponent(editShiftsButton,
                                                                                                javax.swing.GroupLayout.Alignment.TRAILING,
                                                                                                javax.swing.GroupLayout.PREFERRED_SIZE,
                                                                                                55,
                                                                                                javax.swing.GroupLayout.PREFERRED_SIZE))
                                                                .addGap(10, 10, 10))
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

                deleteButton.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
                deleteButton.setText("Delete");
                deleteButton.setBackground(new Color(255, 102, 102));
                deleteButton.setForeground(new Color(255, 255, 255));

                editShiftLabel.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
                editShiftLabel.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
                editShiftLabel.setText("Edit Shifts");
                editShiftLabel.setToolTipText("");
                editShiftLabel.setVerticalAlignment(javax.swing.SwingConstants.BOTTOM);

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
                                                .addComponent(editShiftLabel,
                                                                javax.swing.GroupLayout.Alignment.TRAILING,
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
                                                                                                .addPreferredGap(
                                                                                                                javax.swing.LayoutStyle.ComponentPlacement.RELATED,
                                                                                                                javax.swing.GroupLayout.DEFAULT_SIZE,
                                                                                                                Short.MAX_VALUE)
                                                                                                .addComponent(deleteButton,
                                                                                                                javax.swing.GroupLayout.PREFERRED_SIZE,
                                                                                                                200,
                                                                                                                javax.swing.GroupLayout.PREFERRED_SIZE)))
                                                                .addContainerGap()));
                playgroundLayout.setVerticalGroup(
                                playgroundLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, playgroundLayout
                                                                .createSequentialGroup()
                                                                .addGap(39, 39, 39)
                                                                .addComponent(editShiftLabel)
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
                                                                                23, Short.MAX_VALUE)
                                                                .addGroup(playgroundLayout.createParallelGroup(
                                                                                javax.swing.GroupLayout.Alignment.BASELINE)
                                                                                .addComponent(deleteButton,
                                                                                                javax.swing.GroupLayout.PREFERRED_SIZE,
                                                                                                55,
                                                                                                javax.swing.GroupLayout.PREFERRED_SIZE)
                                                                                .addComponent(backButton,
                                                                                                javax.swing.GroupLayout.PREFERRED_SIZE,
                                                                                                55,
                                                                                                javax.swing.GroupLayout.PREFERRED_SIZE))
                                                                .addContainerGap()));
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
                editShiftsButton.addMouseListener(new MouseListener() {
                        public void mouseClicked(MouseEvent e) {
                                if (datePlaceholder && startPlaceholder && endPlaceholder)
                                        return;
                                String newDate = dateTextField.getText();
                                String newStart = startShiftTextField.getText();
                                String newEnd = endShiftTextField.getText();
                                ArrayList<shift> listShifts = new ArrayList<shift>();
                                for (int i = 0; i < modelSelected.getRowCount(); i++) {
                                        int id = Integer.parseInt((String) modelSelected.getValueAt(i, 0));
                                        String date = (String) modelSelected.getValueAt(i, 3);
                                        String startTime = (String) modelSelected.getValueAt(i, 4);
                                        String endTime = (String) modelSelected.getValueAt(i, 5);
                                        listShifts.add(new shift(id, date, startTime, endTime));
                                }
                                shiftsAPI theManagerDB = new shiftsAPI();
                                if (!startPlaceholder) {
                                        for (shift temp : listShifts)
                                                theManagerDB.updateEntryTime(temp, newStart);
                                }
                                if (!endPlaceholder) {
                                        for (shift temp : listShifts)
                                                theManagerDB.updateEndTime(temp, newEnd);
                                }
                                if (!datePlaceholder) {
                                        LocalDate today = LocalDate.now();
                                        LocalDate newShiftDate = LocalDate.parse(newDate,
                                                        DateTimeFormatter.ofPattern("yyyy-MM-dd"));
                                        if (newShiftDate.isBefore(today)) {
                                                JOptionPane.showMessageDialog(playground,
                                                                "You can't change a shift's date to the past.", "ERROR",
                                                                JOptionPane.ERROR_MESSAGE);
                                        } else {
                                                for (shift temp : listShifts)
                                                        theManagerDB.updateShiftDate(temp, newDate);
                                        }
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
                                editShiftsButton.setBackground(new Color(23, 35, 51));
                                editShiftsButton.setForeground(new Color(255, 255, 255));
                        }

                        public void mouseExited(MouseEvent e) {
                                editShiftsButton.setBackground(new Color(255, 255, 255));
                                editShiftsButton.setForeground(new Color(23, 35, 51));
                        }
                });
                selectButton.addMouseListener(new MouseListener() {
                        public void mouseClicked(MouseEvent e) {
                                int row = tableEmployees.getSelectedRow();
                                if (row == -1)
                                        return;
                                String id = (String) modelEmployees.getValueAt(row, 0);
                                String name = (String) modelEmployees.getValueAt(row, 1);
                                String role = (String) modelEmployees.getValueAt(row, 2);
                                String date = (String) modelEmployees.getValueAt(row, 3);
                                String startTime = (String) modelEmployees.getValueAt(row, 4);
                                String endTime = (String) modelEmployees.getValueAt(row, 5);
                                modelEmployees.removeRow(row);
                                modelSelected.addRow(new String[] { id, name, role, date, startTime, endTime });
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
                                String id = (String) modelSelected.getValueAt(row, 0);
                                String name = (String) modelSelected.getValueAt(row, 1);
                                String role = (String) modelSelected.getValueAt(row, 2);
                                String date = (String) modelSelected.getValueAt(row, 3);
                                String startTime = (String) modelSelected.getValueAt(row, 4);
                                String endTime = (String) modelSelected.getValueAt(row, 5);
                                modelSelected.removeRow(row);
                                modelEmployees.addRow(new String[] { id, name, role, date, startTime, endTime });
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
                dateTextField.addFocusListener(new FocusListener() {
                        @Override
                        public void focusGained(FocusEvent e) {
                                if (dateTextField.getText().equals(theShift.getDate())) {
                                        dateTextField.setText("");
                                        dateTextField.setForeground(Color.BLACK);
                                        datePlaceholder = false;
                                }
                        }

                        @Override
                        public void focusLost(FocusEvent e) {
                                if (dateTextField.getText().isEmpty()) {
                                        dateTextField.setForeground(Color.GRAY);
                                        dateTextField.setText(theShift.getDate());
                                        datePlaceholder = true;
                                }
                        }
                });
                startShiftTextField.addFocusListener(new FocusListener() {
                        @Override
                        public void focusGained(FocusEvent e) {
                                if (startShiftTextField.getText().equals(theShift.getStartTime().substring(0, 5))) {
                                        startShiftTextField.setText("");
                                        startShiftTextField.setForeground(Color.BLACK);
                                        startPlaceholder = false;
                                }
                        }

                        @Override
                        public void focusLost(FocusEvent e) {
                                if (startShiftTextField.getText().isEmpty()) {
                                        startShiftTextField.setForeground(Color.GRAY);
                                        startShiftTextField.setText(theShift.getStartTime().substring(0, 5));
                                        startPlaceholder = true;
                                }
                        }
                });
                endShiftTextField.addFocusListener(new FocusListener() {
                        @Override
                        public void focusGained(FocusEvent e) {
                                if (endShiftTextField.getText().equals(theShift.getEndTime().substring(0, 5))) {
                                        endShiftTextField.setText("");
                                        endShiftTextField.setForeground(Color.BLACK);
                                        endPlaceholder = false;
                                }
                        }

                        @Override
                        public void focusLost(FocusEvent e) {
                                if (endShiftTextField.getText().isEmpty()) {
                                        endShiftTextField.setForeground(Color.GRAY);
                                        endShiftTextField.setText(theShift.getEndTime().substring(0, 5));
                                        endPlaceholder = true;
                                }
                        }
                });
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