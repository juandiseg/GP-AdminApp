package navigation.administration.employeesNav;

import componentsFood.role;
import util.databaseAPIs.employeesAPI;
import util.databaseAPIs.rolesAPI;
import util.inputFormatting.iFormatter;
import util.inputFormatting.inputFormatterFactory;
import util.listenersFormatting.booleanWrapper;
import util.listenersFormatting.iTextFieldListener;
import util.listenersFormatting.add.addTextFieldFListener;

import java.util.ArrayList;
import java.awt.event.*;
import javax.swing.*;

import org.apache.commons.math3.ml.neuralnet.twod.util.QuantizationError;

import java.awt.*;

public class addEmployee {

        private JPanel fillingPanel = new JPanel();
        private JPanel jPanel2 = new JPanel();
        private JPanel jPanel3 = new JPanel();

        private JLabel theEmployeeLabel = new JLabel();
        private JLabel nameLabel = new JLabel();
        private JLabel salaryLabel = new JLabel();
        private JLabel hoursWeekLabel = new JLabel();
        private JLabel roleLabel = new JLabel();

        private JTextField nameTextField = new JTextField();
        private JTextField salaryTextField = new JTextField();
        private JTextField hoursWeekTextField = new JTextField();
        private JComboBox<String> roleComboBox = new JComboBox<>();

        private JButton addEmployeeButton = new JButton();
        private JButton backButton = new JButton();

        private booleanWrapper namePlaceholder = new booleanWrapper(true);
        private booleanWrapper salaryPlaceholder = new booleanWrapper(true);
        private booleanWrapper hoursWeekPlaceholder = new booleanWrapper(true);

        private ArrayList<role> roles = new rolesAPI().getAllRoles();
        private JLabel successLabel = new JLabel();

        public addEmployee(JPanel playground) {
                initComponents(playground);
                addActionListeners(playground);
        }

        private void initComponents(JPanel playground) {

                playground.setBackground(new Color(255, 255, 255));

                successLabel.setFont(new Font("Segoe UI", 0, 18)); // NOI18N
                successLabel.setHorizontalAlignment(SwingConstants.RIGHT);
                successLabel.setText("");
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
                nameTextField.setText("Ex: 'John Schdmit'");
                nameTextField.setForeground(Color.GRAY);

                addEmployeeButton.setFont(new Font("Segoe UI", 1, 14)); // NOI18N
                addEmployeeButton.setText("Add Employee");
                addEmployeeButton.setBackground(new Color(255, 255, 255));
                addEmployeeButton.setForeground(new Color(23, 35, 51));

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
                salaryTextField.setText("Ex: '14'");
                salaryTextField.setForeground(Color.GRAY);

                hoursWeekLabel.setFont(new Font("Segoe UI", 1, 18)); // NOI18N
                hoursWeekLabel.setHorizontalAlignment(SwingConstants.LEFT);
                hoursWeekLabel.setText("Hours a Week");
                hoursWeekLabel.setVerticalAlignment(SwingConstants.BOTTOM);

                roleLabel.setFont(new Font("Segoe UI", 1, 18)); // NOI18N
                roleLabel.setHorizontalAlignment(SwingConstants.LEFT);
                roleLabel.setText("Role");
                roleLabel.setVerticalAlignment(SwingConstants.BOTTOM);

                setComboBox();

                hoursWeekTextField.setFont(new Font("Segoe UI", 0, 18)); // NOI18N
                hoursWeekTextField.setText("Ex: '22:00'");
                hoursWeekTextField.setForeground(Color.GRAY);

                GroupLayout fillingPanelLayout = new GroupLayout(fillingPanel);
                fillingPanel.setLayout(fillingPanelLayout);
                fillingPanelLayout.setHorizontalGroup(
                                fillingPanelLayout.createParallelGroup(GroupLayout.Alignment.LEADING)
                                                .addGroup(fillingPanelLayout.createSequentialGroup()
                                                                .addComponent(jPanel2,
                                                                                GroupLayout.PREFERRED_SIZE,
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
                                                                                                .addComponent(addEmployeeButton,
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
                                                                                fillingPanelLayout.createParallelGroup(
                                                                                                GroupLayout.Alignment.BASELINE)
                                                                                                .addComponent(nameLabel)
                                                                                                .addComponent(nameTextField,
                                                                                                                GroupLayout.PREFERRED_SIZE,
                                                                                                                35,
                                                                                                                GroupLayout.PREFERRED_SIZE))
                                                                .addPreferredGap(
                                                                                LayoutStyle.ComponentPlacement.RELATED)
                                                                .addGroup(
                                                                                fillingPanelLayout.createParallelGroup(
                                                                                                GroupLayout.Alignment.BASELINE)
                                                                                                .addComponent(salaryTextField,
                                                                                                                GroupLayout.PREFERRED_SIZE,
                                                                                                                35,
                                                                                                                GroupLayout.PREFERRED_SIZE)
                                                                                                .addComponent(salaryLabel))
                                                                .addPreferredGap(
                                                                                LayoutStyle.ComponentPlacement.RELATED)
                                                                .addGroup(fillingPanelLayout
                                                                                .createParallelGroup(
                                                                                                GroupLayout.Alignment.LEADING)
                                                                                .addComponent(hoursWeekLabel)
                                                                                .addComponent(hoursWeekTextField,
                                                                                                GroupLayout.PREFERRED_SIZE,
                                                                                                GroupLayout.DEFAULT_SIZE,
                                                                                                GroupLayout.PREFERRED_SIZE))
                                                                .addPreferredGap(
                                                                                LayoutStyle.ComponentPlacement.RELATED)
                                                                .addGroup(
                                                                                fillingPanelLayout.createParallelGroup(
                                                                                                GroupLayout.Alignment.BASELINE)
                                                                                                .addComponent(roleComboBox,
                                                                                                                GroupLayout.PREFERRED_SIZE,
                                                                                                                31,
                                                                                                                GroupLayout.PREFERRED_SIZE)
                                                                                                .addComponent(roleLabel))
                                                                .addGap(37, 37, 37)
                                                                .addComponent(addEmployeeButton,
                                                                                GroupLayout.PREFERRED_SIZE,
                                                                                55,
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
                theEmployeeLabel.setText("Add Employee");
                theEmployeeLabel.setVerticalAlignment(SwingConstants.BOTTOM);

                backButton.setFont(new Font("Segoe UI", 1, 14)); // NOI18N
                backButton.setBackground(new Color(71, 120, 197));
                backButton.setForeground(new Color(255, 255, 255));
                backButton.setText("Back");

                GroupLayout playgroundLayout = new GroupLayout(playground);
                playground.setLayout(playgroundLayout);
                playgroundLayout.setHorizontalGroup(
                                playgroundLayout.createParallelGroup(GroupLayout.Alignment.LEADING)
                                                .addComponent(fillingPanel, GroupLayout.DEFAULT_SIZE,
                                                                GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                                .addGroup(playgroundLayout.createSequentialGroup()
                                                                .addGap(185, 185, 185)
                                                                .addComponent(jPanel3,
                                                                                GroupLayout.PREFERRED_SIZE,
                                                                                GroupLayout.DEFAULT_SIZE,
                                                                                GroupLayout.PREFERRED_SIZE)
                                                                .addGap(0, 0, Short.MAX_VALUE))
                                                .addComponent(theEmployeeLabel, GroupLayout.DEFAULT_SIZE,
                                                                GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                                .addGroup(playgroundLayout.createSequentialGroup()
                                                                .addContainerGap()
                                                                .addComponent(backButton,
                                                                                GroupLayout.PREFERRED_SIZE,
                                                                                200,
                                                                                GroupLayout.PREFERRED_SIZE)
                                                                .addContainerGap(GroupLayout.DEFAULT_SIZE,
                                                                                Short.MAX_VALUE))
                                                .addGroup(playgroundLayout.createSequentialGroup()
                                                                .addContainerGap()
                                                                .addComponent(successLabel,
                                                                                GroupLayout.DEFAULT_SIZE,
                                                                                GroupLayout.DEFAULT_SIZE,
                                                                                Short.MAX_VALUE)
                                                                .addContainerGap()));
                playgroundLayout.setVerticalGroup(
                                playgroundLayout.createParallelGroup(GroupLayout.Alignment.LEADING)
                                                .addGroup(GroupLayout.Alignment.TRAILING, playgroundLayout
                                                                .createSequentialGroup()
                                                                .addGap(39, 39, 39)
                                                                .addComponent(theEmployeeLabel)
                                                                .addPreferredGap(
                                                                                LayoutStyle.ComponentPlacement.RELATED)
                                                                .addComponent(jPanel3,
                                                                                GroupLayout.PREFERRED_SIZE,
                                                                                GroupLayout.DEFAULT_SIZE,
                                                                                GroupLayout.PREFERRED_SIZE)
                                                                .addGap(18, 18, 18)
                                                                .addComponent(fillingPanel,
                                                                                GroupLayout.PREFERRED_SIZE,
                                                                                GroupLayout.DEFAULT_SIZE,
                                                                                GroupLayout.PREFERRED_SIZE)
                                                                .addPreferredGap(
                                                                                LayoutStyle.ComponentPlacement.RELATED)
                                                                .addComponent(successLabel)
                                                                .addPreferredGap(
                                                                                LayoutStyle.ComponentPlacement.RELATED,
                                                                                138,
                                                                                Short.MAX_VALUE)
                                                                .addComponent(backButton,
                                                                                GroupLayout.PREFERRED_SIZE,
                                                                                55,
                                                                                GroupLayout.PREFERRED_SIZE)
                                                                .addContainerGap()));
        }

        private void setComboBox() {
                roleComboBox.setFont(new Font("Segoe UI", 0, 14));
                ArrayList<String> tempNames = new ArrayList<>();
                for (role temp : roles)
                        tempNames.add(temp.getName());
                String[] namesArr = tempNames.toArray(new String[0]);
                roleComboBox.setModel(new DefaultComboBoxModel<String>(namesArr));
                roleComboBox.setFont(new Font("Segoe UI", 0, 18));
                roleComboBox.setForeground(Color.BLACK);
                roleComboBox.setBackground(Color.WHITE);
        }

        private void addActionListeners(JPanel playground) {
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
                addEmployeeButton.addMouseListener(new MouseListener() {

                        public void mouseClicked(MouseEvent e) {
                                if (namePlaceholder.getValue() || salaryPlaceholder.getValue()
                                                || hoursWeekPlaceholder.getValue()) {
                                        successLabel.setText("Error. All the required fields must be filled.");
                                        successLabel.setVisible(true);
                                        return;
                                }
                                String name = nameTextField.getText();
                                Float salary = Float.parseFloat(salaryTextField.getText());
                                String hoursWeek = hoursWeekTextField.getText();
                                int roleID = roles.get(roleComboBox.getSelectedIndex()).getId();
                                if (new employeesAPI().addEmployee(name, salary, hoursWeek, roleID)) {
                                        successLabel.setText(
                                                        "The employee '" + name + "' has been successfully added.");
                                        successLabel.setVisible(true);
                                }
                        }

                        public void mousePressed(MouseEvent e) {

                        }

                        public void mouseReleased(MouseEvent e) {

                        }

                        public void mouseEntered(MouseEvent e) {
                                addEmployeeButton.setBackground(new Color(23, 35, 51));
                                addEmployeeButton.setForeground(new Color(255, 255, 255));
                        }

                        public void mouseExited(MouseEvent e) {
                                addEmployeeButton.setBackground(new Color(255, 255, 255));
                                addEmployeeButton.setForeground(new Color(23, 35, 51));
                        }

                });
                applyGenericListeners();
        }

        private void applyGenericListeners() {
                iTextFieldListener inputListener = new addTextFieldFListener();
                inputListener.applyListenerTextField(nameTextField, "Ex: 'John Schdmit'", namePlaceholder);
                inputListener.applyListenerTextField(salaryTextField, "Ex: '14'", salaryPlaceholder);
                inputListener.applyListenerTextField(hoursWeekTextField, "Ex: '22:00'", hoursWeekPlaceholder);

                new inputFormatterFactory().createInputFormatter("PRICE").applyFormat(salaryTextField);
                new inputFormatterFactory().createInputFormatter("TIME").applyFormat(hoursWeekTextField);
        }
}