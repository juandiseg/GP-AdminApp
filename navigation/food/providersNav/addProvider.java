package navigation.food.providersNav;

import java.awt.event.*;
import javax.swing.*;
import javax.swing.LayoutStyle.ComponentPlacement;

import util.addButton.addButtonFormatter;
import util.addButton.iAddButton;

import util.databaseAPIs.providerAPI;
import util.listenersFormatting.booleanWrapper;
import util.listenersFormatting.iTextFieldListener;
import util.listenersFormatting.add.addTextFieldFListener;

import java.awt.*;

public class addProvider {

        private JLabel providerLabel = new JLabel();

        private JLabel nameLabel = new JLabel();
        private JLabel emailLabel = new JLabel();
        private JLabel successLabel = new JLabel();

        private JTextField nameTextField = new JTextField();
        private JTextField emailTextField = new JTextField();
        private booleanWrapper namePlaceholder = new booleanWrapper(true);
        private booleanWrapper emailPlaceholder = new booleanWrapper(true);

        private JButton addProviderButton = new JButton();
        private JButton backButton = new JButton();

        private JPanel jPanel1 = new JPanel();
        private JPanel jPanel2 = new JPanel();
        private JPanel jPanel3 = new JPanel();

        private providerAPI theManagerDB = new providerAPI();

        public addProvider(JPanel playground) {
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

                nameLabel.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
                nameLabel.setHorizontalAlignment(SwingConstants.LEFT);
                nameLabel.setText("Name");
                nameLabel.setVerticalAlignment(SwingConstants.BOTTOM);

                nameTextField.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
                nameTextField.setText("Ex. 'Konsum'");
                nameTextField.setForeground(Color.GRAY);

                addProviderButton.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
                addProviderButton.setText("Add Provider");
                addProviderButton.setBackground(new Color(255, 255, 255));
                addProviderButton.setForeground(new Color(23, 35, 51));

                jPanel2.setBackground(new Color(0, 0, 0));

                GroupLayout jPanel2Layout = new GroupLayout(jPanel2);
                jPanel2.setLayout(jPanel2Layout);
                jPanel2Layout.setHorizontalGroup(
                                jPanel2Layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                                                .addGap(0, 8, Short.MAX_VALUE));
                jPanel2Layout.setVerticalGroup(
                                jPanel2Layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                                                .addGap(0, 0, Short.MAX_VALUE));

                emailLabel.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
                emailLabel.setHorizontalAlignment(SwingConstants.LEFT);
                emailLabel.setText("Email");
                emailLabel.setVerticalAlignment(SwingConstants.BOTTOM);

                emailTextField.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
                emailTextField.setText("Ex. 'provider@konsum.de'");
                emailTextField.setForeground(Color.GRAY);

                GroupLayout jPanel1Layout = new GroupLayout(jPanel1);
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
                                                                                                .addGap(113, 113, 113)
                                                                                                .addGroup(jPanel1Layout
                                                                                                                .createParallelGroup(
                                                                                                                                GroupLayout.Alignment.LEADING,
                                                                                                                                false)
                                                                                                                .addComponent(nameLabel,
                                                                                                                                GroupLayout.Alignment.TRAILING,
                                                                                                                                GroupLayout.DEFAULT_SIZE,
                                                                                                                                GroupLayout.DEFAULT_SIZE,
                                                                                                                                Short.MAX_VALUE)
                                                                                                                .addComponent(emailLabel,
                                                                                                                                GroupLayout.Alignment.TRAILING,
                                                                                                                                GroupLayout.PREFERRED_SIZE,
                                                                                                                                109,
                                                                                                                                GroupLayout.PREFERRED_SIZE))
                                                                                                .addPreferredGap(
                                                                                                                ComponentPlacement.RELATED)
                                                                                                .addGroup(jPanel1Layout
                                                                                                                .createParallelGroup(
                                                                                                                                GroupLayout.Alignment.LEADING)
                                                                                                                .addComponent(nameTextField,
                                                                                                                                GroupLayout.PREFERRED_SIZE,
                                                                                                                                434,
                                                                                                                                GroupLayout.PREFERRED_SIZE)
                                                                                                                .addComponent(emailTextField,
                                                                                                                                GroupLayout.PREFERRED_SIZE,
                                                                                                                                434,
                                                                                                                                GroupLayout.PREFERRED_SIZE))
                                                                                                .addContainerGap(
                                                                                                                GroupLayout.DEFAULT_SIZE,
                                                                                                                Short.MAX_VALUE))
                                                                                .addGroup(GroupLayout.Alignment.TRAILING,
                                                                                                jPanel1Layout
                                                                                                                .createSequentialGroup()
                                                                                                                .addPreferredGap(
                                                                                                                                ComponentPlacement.RELATED,
                                                                                                                                GroupLayout.DEFAULT_SIZE,
                                                                                                                                Short.MAX_VALUE)
                                                                                                                .addComponent(addProviderButton,
                                                                                                                                GroupLayout.PREFERRED_SIZE,
                                                                                                                                200,
                                                                                                                                GroupLayout.PREFERRED_SIZE)
                                                                                                                .addGap(330, 330,
                                                                                                                                330)))));
                jPanel1Layout.setVerticalGroup(
                                jPanel1Layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                                                .addGroup(GroupLayout.Alignment.TRAILING, jPanel1Layout
                                                                .createSequentialGroup()
                                                                .addGap(20, 20, 20)
                                                                .addGroup(jPanel1Layout.createParallelGroup(
                                                                                GroupLayout.Alignment.BASELINE)
                                                                                .addComponent(nameLabel)
                                                                                .addComponent(nameTextField,
                                                                                                GroupLayout.PREFERRED_SIZE,
                                                                                                35,
                                                                                                GroupLayout.PREFERRED_SIZE))
                                                                .addPreferredGap(
                                                                                ComponentPlacement.RELATED)
                                                                .addGroup(jPanel1Layout.createParallelGroup(
                                                                                GroupLayout.Alignment.BASELINE)
                                                                                .addComponent(emailTextField,
                                                                                                GroupLayout.PREFERRED_SIZE,
                                                                                                35,
                                                                                                GroupLayout.PREFERRED_SIZE)
                                                                                .addComponent(emailLabel))
                                                                .addGap(42, 42, 42)
                                                                .addComponent(addProviderButton,
                                                                                GroupLayout.PREFERRED_SIZE,
                                                                                55,
                                                                                GroupLayout.PREFERRED_SIZE)
                                                                .addContainerGap(44, Short.MAX_VALUE))
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

                backButton.setBackground(new Color(71, 120, 197));
                backButton.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
                backButton.setForeground(new Color(255, 255, 255));
                backButton.setText("Back");

                providerLabel.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
                providerLabel.setHorizontalAlignment(SwingConstants.CENTER);
                providerLabel.setText("Add Provider");
                providerLabel.setVerticalAlignment(SwingConstants.BOTTOM);

                GroupLayout playgroundLayout = new GroupLayout(playground);
                playground.setLayout(playgroundLayout);
                playgroundLayout.setHorizontalGroup(
                                playgroundLayout.createParallelGroup(GroupLayout.Alignment.LEADING)
                                                .addComponent(jPanel1, GroupLayout.DEFAULT_SIZE,
                                                                GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                                .addGroup(playgroundLayout.createSequentialGroup()
                                                                .addGap(185, 185, 185)
                                                                .addComponent(jPanel3,
                                                                                GroupLayout.PREFERRED_SIZE,
                                                                                GroupLayout.DEFAULT_SIZE,
                                                                                GroupLayout.PREFERRED_SIZE)
                                                                .addGap(0, 185, Short.MAX_VALUE))
                                                .addGroup(playgroundLayout.createSequentialGroup()
                                                                .addContainerGap()
                                                                .addGroup(playgroundLayout
                                                                                .createParallelGroup(
                                                                                                GroupLayout.Alignment.LEADING)
                                                                                .addComponent(providerLabel,
                                                                                                GroupLayout.DEFAULT_SIZE,
                                                                                                GroupLayout.DEFAULT_SIZE,
                                                                                                Short.MAX_VALUE)
                                                                                .addGroup(playgroundLayout
                                                                                                .createSequentialGroup()
                                                                                                .addComponent(backButton,
                                                                                                                GroupLayout.PREFERRED_SIZE,
                                                                                                                200,
                                                                                                                GroupLayout.PREFERRED_SIZE)
                                                                                                .addGap(0, 0, Short.MAX_VALUE))
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
                                                                .addGap(39, 39, 39)
                                                                .addComponent(providerLabel)
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
                                                                                208,
                                                                                Short.MAX_VALUE)
                                                                .addComponent(backButton,
                                                                                GroupLayout.PREFERRED_SIZE,
                                                                                55,
                                                                                GroupLayout.PREFERRED_SIZE)
                                                                .addContainerGap()));
        }

        private void addActionListeners(JPanel playground) {
                backButton.addMouseListener(new MouseListener() {
                        public void mouseClicked(MouseEvent e) {
                                playground.removeAll();
                                new mainProviders(playground);
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
                class addMethodsHolder extends iAddButton {
                        public boolean valuesArePlaceholders() {
                                boolean arePlaceholders = (namePlaceholder.getValue() || emailPlaceholder.getValue());
                                if (arePlaceholders) {
                                        successLabel.setText("Error. You must fill all the given fields.");
                                        successLabel.setVisible(true);
                                }
                                return arePlaceholders;
                        }

                        public boolean areInputsInvalid() {
                                boolean error = theManagerDB.isNameTaken(nameTextField.getText());
                                if (error) {
                                        successLabel.setText("Error. The given name is already in use.");
                                        successLabel.setVisible(true);
                                }
                                return error;
                        }

                        public boolean addFoodComponent() {
                                String name = nameTextField.getText();
                                String email = emailTextField.getText();
                                if (theManagerDB.addProvider(name, email))
                                        successLabel.setText("\"" + name + "\" was successfully added.");
                                else
                                        successLabel.setText("Error. Impossible to connect to database.");
                                successLabel.setVisible(true);
                                return false;
                        }
                }
                new addButtonFormatter().formatAddButton(addProviderButton, new addMethodsHolder());
                applyGenericListeners();
        }

        private void applyGenericListeners() {
                iTextFieldListener inputListener = new addTextFieldFListener();
                inputListener.applyListenerTextField(nameTextField, "Ex. 'Konsum'", namePlaceholder);
                inputListener.applyListenerTextField(emailTextField, "Ex. 'provider@konsum.de'", emailPlaceholder);
        }
}