package navigation.administration.employeesNav;

import componentsFood.employee;
import componentsFood.role;
import util.databaseAPIs.employeesAPI;
import util.databaseAPIs.rolesAPI;

import java.util.ArrayList;
import java.awt.event.*;
import javax.swing.*;
import java.awt.*;

public class editEmployee {

        private JPanel fillingPanel = new JPanel();
        private JPanel jPanel2 = new JPanel();
        private JPanel jPanel3 = new JPanel();

        private JLabel auxEmployeeLabel = new JLabel();
        private JLabel theEmployeeLabel = new JLabel();
        private JLabel nameLabel = new JLabel();
        private JLabel salaryLabel = new JLabel();
        private JLabel hoursWeekLabel = new JLabel();
        private JLabel roleLabel = new JLabel();

        private JTextField nameTextField = new JTextField();
        private JTextField salaryTextField = new JTextField();
        private JTextField hoursWeekTextField = new JTextField();
        private JComboBox<String> roleComboBox = new JComboBox<>();

        private JButton editEmployeeButton = new JButton();
        private JButton deleteButton = new JButton();
        private JButton backButton = new JButton();

        private boolean namePlaceholder = true;
        private boolean salaryPlaceholder = true;
        private boolean hoursWeekPlaceholder = true;

        private ArrayList<role> roles = new rolesAPI().getAllRoles();
        private JLabel successLabel = new JLabel();

        private employee theEmployee;

        public editEmployee(
                        JPanel playground, employee theEmployee) {
                this.theEmployee = theEmployee;
                initComponents(playground);
                addActionListeners(playground);
        }

        private void initComponents(JPanel playground) {
                playground.setBackground(new Color(255, 255, 255));

                successLabel.setFont(new Font("Segoe UI", 0, 18)); // NOI18N
                successLabel.setHorizontalAlignment(SwingConstants.RIGHT);
                successLabel.setVisible(false);
                successLabel.setVerticalAlignment(SwingConstants.BOTTOM);

                fillingPanel.setBackground(new Color(120, 168, 252));
                fillingPanel.setCursor(new Cursor(java.awt.Cursor.DEFAULT_CURSOR));
                fillingPanel.setDebugGraphicsOptions(DebugGraphics.NONE_OPTION);

                nameLabel.setFont(new Font("Segoe UI", 1, 18)); // NOI18N
                nameLabel.setHorizontalAlignment(SwingConstants.LEFT);
                nameLabel.setText("Name");
                nameLabel.setVerticalAlignment(SwingConstants.BOTTOM);

                nameTextField.setFont(new Font("Segoe UI", 0, 18)); // NOI18N
                nameTextField.setText(theEmployee.getName());
                nameTextField.setForeground(Color.GRAY);

                editEmployeeButton.setFont(new Font("Segoe UI", 1, 14)); // NOI18N
                editEmployeeButton.setText("Edit Employee");
                editEmployeeButton.setBackground(new Color(255, 255, 255));
                editEmployeeButton.setForeground(new Color(23, 35, 51));

                jPanel2.setBackground(new Color(0, 0, 0));

                GroupLayout jPanel2Layout = new GroupLayout(jPanel2);
                jPanel2.setLayout(jPanel2Layout);
                jPanel2Layout.setHorizontalGroup(
                                jPanel2Layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                                                .addGap(0, 8, Short.MAX_VALUE));
                jPanel2Layout.setVerticalGroup(
                                jPanel2Layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                                                .addGap(0, 0, Short.MAX_VALUE));

                salaryLabel.setFont(new Font("Segoe UI", 1, 18)); // NOI18N
                salaryLabel.setHorizontalAlignment(SwingConstants.LEFT);
                salaryLabel.setText("Salary");
                salaryLabel.setVerticalAlignment(SwingConstants.BOTTOM);

                salaryTextField.setFont(new Font("Segoe UI", 0, 18)); // NOI18N
                salaryTextField.setText(theEmployee.getSalary() + "");
                salaryTextField.setForeground(Color.GRAY);

                hoursWeekLabel.setFont(new Font("Segoe UI", 1, 18)); // NOI18N
                hoursWeekLabel.setText("Hours per Week");
                hoursWeekLabel.setHorizontalAlignment(SwingConstants.LEFT);
                hoursWeekLabel.setVerticalAlignment(SwingConstants.BOTTOM);

                roleLabel.setFont(new Font("Segoe UI", 1, 18)); // NOI18N
                roleLabel.setHorizontalAlignment(SwingConstants.LEFT);
                roleLabel.setText("Role");
                roleLabel.setVerticalAlignment(SwingConstants.BOTTOM);

                setComboBox();

                hoursWeekTextField.setFont(new Font("Segoe UI", 0, 18)); // NOI18N
                hoursWeekTextField.setText(theEmployee.getHoursWeek().substring(0, 5));
                hoursWeekTextField.setForeground(Color.GRAY);

                GroupLayout fillingPanelLayout = new GroupLayout(fillingPanel);
                fillingPanel.setLayout(fillingPanelLayout);
                fillingPanelLayout.setHorizontalGroup(
                                fillingPanelLayout.createParallelGroup(GroupLayout.Alignment.LEADING)
                                                .addGroup(fillingPanelLayout.createSequentialGroup()
                                                                .addComponent(jPanel2, GroupLayout.PREFERRED_SIZE,
                                                                                GroupLayout.DEFAULT_SIZE,
                                                                                GroupLayout.PREFERRED_SIZE)
                                                                .addGroup(fillingPanelLayout
                                                                                .createParallelGroup(
                                                                                                GroupLayout.Alignment.LEADING)
                                                                                .addGroup(fillingPanelLayout
                                                                                                .createSequentialGroup()
                                                                                                .addGap(56, 56, 56)
                                                                                                .addGroup(fillingPanelLayout
                                                                                                                .createParallelGroup(
                                                                                                                                GroupLayout.Alignment.TRAILING)
                                                                                                                .addComponent(salaryLabel,
                                                                                                                                GroupLayout.DEFAULT_SIZE,
                                                                                                                                GroupLayout.DEFAULT_SIZE,
                                                                                                                                Short.MAX_VALUE)
                                                                                                                .addGroup(fillingPanelLayout
                                                                                                                                .createSequentialGroup()
                                                                                                                                .addGap(0, 0, Short.MAX_VALUE)
                                                                                                                                .addComponent(nameLabel,
                                                                                                                                                GroupLayout.PREFERRED_SIZE,
                                                                                                                                                165,
                                                                                                                                                GroupLayout.PREFERRED_SIZE))
                                                                                                                .addComponent(roleLabel,
                                                                                                                                GroupLayout.Alignment.LEADING,
                                                                                                                                GroupLayout.DEFAULT_SIZE,
                                                                                                                                GroupLayout.DEFAULT_SIZE,
                                                                                                                                Short.MAX_VALUE)
                                                                                                                .addComponent(hoursWeekLabel,
                                                                                                                                GroupLayout.Alignment.LEADING,
                                                                                                                                GroupLayout.DEFAULT_SIZE,
                                                                                                                                GroupLayout.DEFAULT_SIZE,
                                                                                                                                Short.MAX_VALUE))
                                                                                                .addPreferredGap(
                                                                                                                LayoutStyle.ComponentPlacement.RELATED)
                                                                                                .addGroup(fillingPanelLayout
                                                                                                                .createParallelGroup(
                                                                                                                                GroupLayout.Alignment.LEADING,
                                                                                                                                false)
                                                                                                                .addComponent(salaryTextField)
                                                                                                                .addComponent(nameTextField)
                                                                                                                .addComponent(hoursWeekTextField,
                                                                                                                                GroupLayout.PREFERRED_SIZE,
                                                                                                                                434,
                                                                                                                                GroupLayout.PREFERRED_SIZE)
                                                                                                                .addComponent(roleComboBox,
                                                                                                                                0,
                                                                                                                                GroupLayout.DEFAULT_SIZE,
                                                                                                                                Short.MAX_VALUE))
                                                                                                .addGap(201, 201, 201))
                                                                                .addGroup(fillingPanelLayout
                                                                                                .createSequentialGroup()
                                                                                                .addGap(331, 331, 331)
                                                                                                .addComponent(editEmployeeButton,
                                                                                                                GroupLayout.PREFERRED_SIZE,
                                                                                                                200,
                                                                                                                GroupLayout.PREFERRED_SIZE)
                                                                                                .addContainerGap(
                                                                                                                GroupLayout.DEFAULT_SIZE,
                                                                                                                Short.MAX_VALUE)))));
                fillingPanelLayout.setVerticalGroup(
                                fillingPanelLayout.createParallelGroup(GroupLayout.Alignment.LEADING)
                                                .addGroup(GroupLayout.Alignment.TRAILING, fillingPanelLayout
                                                                .createSequentialGroup()
                                                                .addGap(23, 23, 23)
                                                                .addGroup(
                                                                                fillingPanelLayout
                                                                                                .createParallelGroup(
                                                                                                                GroupLayout.Alignment.BASELINE)
                                                                                                .addComponent(nameLabel)
                                                                                                .addComponent(nameTextField,
                                                                                                                GroupLayout.PREFERRED_SIZE,
                                                                                                                35,
                                                                                                                GroupLayout.PREFERRED_SIZE))
                                                                .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                                                                .addGroup(
                                                                                fillingPanelLayout
                                                                                                .createParallelGroup(
                                                                                                                GroupLayout.Alignment.BASELINE)
                                                                                                .addComponent(salaryTextField,
                                                                                                                GroupLayout.PREFERRED_SIZE,
                                                                                                                35,
                                                                                                                GroupLayout.PREFERRED_SIZE)
                                                                                                .addComponent(salaryLabel))
                                                                .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                                                                .addGroup(fillingPanelLayout
                                                                                .createParallelGroup(
                                                                                                GroupLayout.Alignment.LEADING)
                                                                                .addComponent(hoursWeekLabel)
                                                                                .addComponent(hoursWeekTextField,
                                                                                                GroupLayout.PREFERRED_SIZE,
                                                                                                GroupLayout.DEFAULT_SIZE,
                                                                                                GroupLayout.PREFERRED_SIZE))
                                                                .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                                                                .addGroup(
                                                                                fillingPanelLayout
                                                                                                .createParallelGroup(
                                                                                                                GroupLayout.Alignment.BASELINE)
                                                                                                .addComponent(roleComboBox,
                                                                                                                GroupLayout.PREFERRED_SIZE,
                                                                                                                31,
                                                                                                                GroupLayout.PREFERRED_SIZE)
                                                                                                .addComponent(roleLabel))
                                                                .addGap(37, 37, 37)
                                                                .addComponent(editEmployeeButton,
                                                                                GroupLayout.PREFERRED_SIZE, 55,
                                                                                GroupLayout.PREFERRED_SIZE)
                                                                .addContainerGap(42, Short.MAX_VALUE))
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

                theEmployeeLabel.setFont(new Font("Segoe UI", 1, 18)); // NOI18N
                theEmployeeLabel.setHorizontalAlignment(SwingConstants.CENTER);
                theEmployeeLabel.setText(theEmployee.getName());
                theEmployeeLabel.setVerticalAlignment(SwingConstants.BOTTOM);

                backButton.setBackground(new Color(71, 120, 197));
                backButton.setFont(new Font("Segoe UI", 1, 14)); // NOI18N
                backButton.setForeground(new Color(255, 255, 255));
                backButton.setText("Back");

                auxEmployeeLabel.setFont(new Font("Segoe UI", 0, 18)); // NOI18N
                auxEmployeeLabel.setHorizontalAlignment(SwingConstants.CENTER);
                auxEmployeeLabel.setText("Employe to edit:");
                auxEmployeeLabel.setVerticalAlignment(SwingConstants.BOTTOM);

                deleteButton.setBackground(new Color(71, 120, 197));
                deleteButton.setFont(new Font("Segoe UI", 1, 14)); // NOI18N
                deleteButton.setText("Delete");
                deleteButton.setBackground(new Color(255, 102, 102));
                deleteButton.setForeground(new Color(255, 255, 255));

                GroupLayout playgroundLayout = new GroupLayout(playground);
                playground.setLayout(playgroundLayout);
                playgroundLayout.setHorizontalGroup(
                                playgroundLayout.createParallelGroup(GroupLayout.Alignment.LEADING)
                                                .addComponent(fillingPanel, GroupLayout.DEFAULT_SIZE,
                                                                GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                                .addGroup(playgroundLayout.createSequentialGroup()
                                                                .addGap(185, 185, 185)
                                                                .addComponent(jPanel3, GroupLayout.PREFERRED_SIZE,
                                                                                GroupLayout.DEFAULT_SIZE,
                                                                                GroupLayout.PREFERRED_SIZE)
                                                                .addGap(0, 0, Short.MAX_VALUE))
                                                .addComponent(theEmployeeLabel, GroupLayout.DEFAULT_SIZE,
                                                                GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                                .addGroup(playgroundLayout.createSequentialGroup()
                                                                .addContainerGap()
                                                                .addGroup(playgroundLayout
                                                                                .createParallelGroup(
                                                                                                GroupLayout.Alignment.LEADING)
                                                                                .addComponent(auxEmployeeLabel,
                                                                                                GroupLayout.Alignment.TRAILING,
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
                                                                                                                LayoutStyle.ComponentPlacement.RELATED,
                                                                                                                GroupLayout.DEFAULT_SIZE,
                                                                                                                Short.MAX_VALUE)
                                                                                                .addComponent(deleteButton,
                                                                                                                GroupLayout.PREFERRED_SIZE,
                                                                                                                200,
                                                                                                                GroupLayout.PREFERRED_SIZE))
                                                                                .addComponent(successLabel,
                                                                                                GroupLayout.Alignment.TRAILING,
                                                                                                GroupLayout.DEFAULT_SIZE,
                                                                                                GroupLayout.DEFAULT_SIZE,
                                                                                                Short.MAX_VALUE))
                                                                .addContainerGap()));
                playgroundLayout.setVerticalGroup(
                                playgroundLayout.createParallelGroup(GroupLayout.Alignment.LEADING)
                                                .addGroup(GroupLayout.Alignment.TRAILING, playgroundLayout
                                                                .createSequentialGroup()
                                                                .addGap(8, 8, 8)
                                                                .addComponent(auxEmployeeLabel)
                                                                .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                                                                .addComponent(theEmployeeLabel)
                                                                .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                                                                .addComponent(jPanel3, GroupLayout.PREFERRED_SIZE,
                                                                                GroupLayout.DEFAULT_SIZE,
                                                                                GroupLayout.PREFERRED_SIZE)
                                                                .addGap(18, 18, 18)
                                                                .addComponent(fillingPanel, GroupLayout.PREFERRED_SIZE,
                                                                                GroupLayout.DEFAULT_SIZE,
                                                                                GroupLayout.PREFERRED_SIZE)
                                                                .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                                                                .addComponent(successLabel)
                                                                .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED,
                                                                                138,
                                                                                Short.MAX_VALUE)
                                                                .addGroup(
                                                                                playgroundLayout.createParallelGroup(
                                                                                                GroupLayout.Alignment.BASELINE)
                                                                                                .addComponent(backButton,
                                                                                                                GroupLayout.PREFERRED_SIZE,
                                                                                                                55,
                                                                                                                GroupLayout.PREFERRED_SIZE)
                                                                                                .addComponent(deleteButton,
                                                                                                                GroupLayout.PREFERRED_SIZE,
                                                                                                                55,
                                                                                                                GroupLayout.PREFERRED_SIZE))
                                                                .addContainerGap()));
        }

        private void addActionListeners(JPanel playground) {
                nameTextField.addFocusListener(new FocusListener() {
                        public void focusGained(FocusEvent e) {
                                if (namePlaceholder) {
                                        nameTextField.setText("");
                                        nameTextField.setForeground(Color.BLACK);
                                        namePlaceholder = false;
                                }
                        }

                        public void focusLost(FocusEvent e) {
                                if (nameTextField.getText().isEmpty()) {
                                        nameTextField.setForeground(Color.GRAY);
                                        nameTextField.setText(theEmployee.getName());
                                        namePlaceholder = true;
                                }
                        }
                });
                salaryTextField.addFocusListener(new FocusListener() {
                        public void focusGained(FocusEvent e) {
                                if (salaryPlaceholder) {
                                        salaryTextField.setText("");
                                        salaryTextField.setForeground(Color.BLACK);
                                        salaryPlaceholder = false;
                                }
                        }

                        public void focusLost(FocusEvent e) {
                                if (salaryTextField.getText().isEmpty()) {
                                        salaryTextField.setForeground(Color.GRAY);
                                        salaryTextField.setText(theEmployee.getSalary() + "");
                                        salaryPlaceholder = true;
                                }
                        }
                });
                hoursWeekTextField.addFocusListener(new FocusListener() {
                        public void focusGained(FocusEvent e) {
                                if (hoursWeekPlaceholder) {
                                        hoursWeekTextField.setText("");
                                        hoursWeekTextField.setForeground(Color.BLACK);
                                        hoursWeekPlaceholder = false;
                                }
                        }

                        public void focusLost(FocusEvent e) {
                                if (hoursWeekTextField.getText().isEmpty()) {
                                        hoursWeekTextField.setForeground(Color.GRAY);
                                        hoursWeekTextField.setText(theEmployee.getHoursWeek().substring(0, 5));
                                        hoursWeekPlaceholder = true;
                                }
                        }
                });
                backButton.addMouseListener(new MouseListener() {

                        public void mouseClicked(MouseEvent e) {
                                playground.removeAll();
                                new mainEmployees(playground);
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
                editEmployeeButton.addMouseListener(new MouseListener() {

                        public void mouseClicked(MouseEvent e) {
                                int index = roleComboBox.getSelectedIndex();
                                boolean rolePlaceholder = (roles.get(index).getId() == theEmployee.getRoleID());
                                if (namePlaceholder && salaryPlaceholder && hoursWeekPlaceholder && rolePlaceholder) {
                                        successLabel.setText("Error. At least one field must be diffeernt.");
                                        successLabel.setVisible(true);
                                        return;
                                }
                                employeesAPI theManagerDB = new employeesAPI();
                                int employeeID = theEmployee.getId();
                                String name = nameTextField.getText();
                                if (!namePlaceholder)
                                        theManagerDB.updateEmployeeName(employeeID, name);
                                Float salary = Float.parseFloat(salaryTextField.getText());
                                if (!salaryPlaceholder)
                                        theManagerDB.updateEmployeeSalary(employeeID, salary);
                                String hoursWeek = hoursWeekTextField.getText();
                                if (!hoursWeekPlaceholder)
                                        theManagerDB.updateEmployeeHoursWeek(employeeID, hoursWeek);
                                if (!rolePlaceholder)
                                        theManagerDB.updateEmployeeRole(employeeID,
                                                        roles.get(roleComboBox.getSelectedIndex()).getId());
                                updatePlaceholders();
                                successLabel.setText("The employee '" + name + "' has been successfully editted.");
                                successLabel.setVisible(true);
                        }

                        public void mousePressed(MouseEvent e) {
                        }

                        public void mouseReleased(MouseEvent e) {
                        }

                        public void mouseEntered(MouseEvent e) {
                                editEmployeeButton.setBackground(new Color(23, 35, 51));
                                editEmployeeButton.setForeground(new Color(255, 255, 255));
                        }

                        public void mouseExited(MouseEvent e) {
                                editEmployeeButton.setBackground(new Color(255, 255, 255));
                                editEmployeeButton.setForeground(new Color(23, 35, 51));
                        }

                });
                deleteButton.addMouseListener(new MouseListener() {
                        public void mouseClicked(MouseEvent e) {
                                int reply = JOptionPane.showConfirmDialog(null,
                                                "Are you sure you want to delete " + theEmployee.getName()
                                                                + " from employees?",
                                                "Confirmation", JOptionPane.YES_NO_OPTION);
                                if (reply == JOptionPane.YES_OPTION) {
                                        if (new employeesAPI().hasEmployeeFutureShifts(theEmployee)) {
                                                int response = JOptionPane.showOptionDialog(null,
                                                                "An employee with future or current shifts assigned cannot be deleted.\nYou can delete all their future shifts or keep the employee.",
                                                                "Choice",
                                                                JOptionPane.DEFAULT_OPTION, JOptionPane.PLAIN_MESSAGE,
                                                                null,
                                                                new String[] { "Delete Shifts and Employee",
                                                                                "Keep Employee" },
                                                                null);
                                                if (response == 0) {
                                                        new employeesAPI().setEmployeeUnactive(theEmployee);
                                                        playground.removeAll();
                                                        new mainEmployees(playground);
                                                        playground.revalidate();
                                                        playground.repaint();
                                                        return;
                                                }
                                        }
                                }
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
        }

        private void setComboBox() {
                for (int i = 0; i < roles.size(); i++) {
                        if (roles.get(i).getId() == theEmployee.getRoleID()) {
                                role temp = roles.remove(i);
                                roles.add(0, temp);
                        }
                }
                roleComboBox.setFont(new Font("Segoe UI", 0, 14)); // NOI18N
                ArrayList<String> tempNames = new ArrayList<>();
                for (role temp : roles)
                        tempNames.add(temp.getName());

                String[] namesArr = tempNames.toArray(new String[0]);
                roleComboBox.setModel(new DefaultComboBoxModel<String>(namesArr));
                roleComboBox.setFont(new Font("Segoe UI", 0, 18));
                roleComboBox.setForeground(Color.BLACK);
                roleComboBox.setBackground(Color.WHITE);
        }

        private void updatePlaceholders() {
                theEmployee = new employeesAPI().getEmployee(theEmployee.getId());
                namePlaceholder = true;
                salaryPlaceholder = true;
                hoursWeekPlaceholder = true;
                nameTextField.setText(theEmployee.getName());
                nameTextField.setForeground(Color.GRAY);
                salaryTextField.setText(theEmployee.getSalary() + "");
                salaryTextField.setForeground(Color.GRAY);
                hoursWeekTextField.setText(theEmployee.getHoursWeek().substring(0, 5));
                hoursWeekTextField.setForeground(Color.GRAY);
                for (int i = 0; i < roles.size(); i++) {
                        if (roles.get(i).getId() == theEmployee.getRoleID()) {
                                role temp = roles.remove(i);
                                roles.add(0, temp);
                        }
                }
                ArrayList<String> tempNames = new ArrayList<>();
                for (role temp : roles)
                        tempNames.add(temp.getName());

                String[] namesArr = tempNames.toArray(new String[0]);
                roleComboBox.setModel(new DefaultComboBoxModel<String>(namesArr));
                theEmployeeLabel.setText(theEmployee.getName());
        }
}
