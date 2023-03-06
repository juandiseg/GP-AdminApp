package navigation.food.providersNav;

import componentsFood.provider;
import util.buttonFormatters.*;
import util.databaseAPIs.providerAPI;
import util.listenersFormatting.booleanWrapper;
import util.listenersFormatting.iTextFieldListener;
import util.listenersFormatting.edit.editTextFieldFListener;

import javax.swing.*;
import javax.swing.LayoutStyle.ComponentPlacement;

import java.awt.*;

public class editProvider {

        private JLabel auxProviderLabel = new JLabel();
        private JLabel theProviderLabel = new JLabel();

        private JPanel jPanel1 = new JPanel();
        private JPanel jPanel2 = new JPanel();
        private JPanel jPanel3 = new JPanel();

        private JLabel nameLabel = new JLabel();
        private JLabel emailLabel = new JLabel();
        private JLabel successLabel = new JLabel();

        private JButton editProviderButton = new JButton();
        private JButton backButton = new JButton();
        private JButton deleteButton = new JButton();

        private booleanWrapper namePlaceholder = new booleanWrapper(true);
        private booleanWrapper emailPlaceholder = new booleanWrapper(true);

        private JTextField nameTextField = new JTextField();
        private JTextField emailTextField = new JTextField();

        private provider theCurrentProvider;

        public editProvider(JPanel playground, provider theCurrentProvider) {
                this.theCurrentProvider = theCurrentProvider;
                initComponents(playground);
                addListeners(playground);
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
                nameTextField.setText(theCurrentProvider.getName());
                nameTextField.setForeground(Color.GRAY);

                editProviderButton.setText("Edit Provider");

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
                emailTextField.setText(theCurrentProvider.getEmail());
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
                                                                                                                .addComponent(editProviderButton,
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
                                                                .addComponent(editProviderButton,
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

                theProviderLabel.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
                theProviderLabel.setHorizontalAlignment(SwingConstants.CENTER);
                theProviderLabel.setText(theCurrentProvider.getName());
                theProviderLabel.setVerticalAlignment(SwingConstants.BOTTOM);

                auxProviderLabel.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
                auxProviderLabel.setHorizontalAlignment(SwingConstants.CENTER);
                auxProviderLabel.setText("Provider to edit:");
                auxProviderLabel.setVerticalAlignment(SwingConstants.BOTTOM);

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
                                                                                .addComponent(theProviderLabel,
                                                                                                GroupLayout.DEFAULT_SIZE,
                                                                                                GroupLayout.DEFAULT_SIZE,
                                                                                                Short.MAX_VALUE)
                                                                                .addComponent(auxProviderLabel,
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
                                                                                                                ComponentPlacement.RELATED,
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
                                                                .addComponent(auxProviderLabel)
                                                                .addPreferredGap(
                                                                                ComponentPlacement.RELATED)
                                                                .addComponent(theProviderLabel)
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
                                                                .addGroup(
                                                                                playgroundLayout.createParallelGroup(
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

        private void addListeners(JPanel playground) {
                deleteButton(playground);
                backButton(playground);
                editButton(null);
                applyGenericListeners();
        }

        private void deleteButton(JPanel playground) {
                class deleteMethodHolder implements iDeleteButton {

                        public boolean thereIsError() {
                                if (providerAPI.isProviderAssigned(theCurrentProvider.getId())) {
                                        JOptionPane.showMessageDialog(playground,
                                                        "You cannot delete a provider if there is an ingredient assigned to it.",
                                                        "ERROR", JOptionPane.ERROR_MESSAGE);
                                        return true;
                                }
                                return false;
                        }

                        public boolean askConfirmation() {
                                boolean neededToChoose = false;

                                int reply = JOptionPane.showConfirmDialog(null,
                                                "Are you sure you want to DELETE \"" + theCurrentProvider.getName()
                                                                + "\"?",
                                                "Confirmation", JOptionPane.YES_NO_OPTION);
                                if (reply == JOptionPane.YES_OPTION) {
                                        providerAPI.deleteProvider(theCurrentProvider);
                                        playground.removeAll();
                                        new mainProviders(playground);
                                        playground.revalidate();
                                        playground.repaint();
                                }
                                return neededToChoose;
                        }

                        public void chooseAmongOptions() {
                        }

                }
                deleteButtonFormatter.formatDeleteButton(deleteButton, new deleteMethodHolder());
        }

        private void backButton(JPanel playground) {
                class backMethodHolder extends iNavigatorButton {
                        public void createNewNavigator() {
                                new mainProviders(playground);
                        }
                }
                navigatorButtonFormatter.formatNavigationButton(backButton, new backMethodHolder(), playground, true,
                                "Back");
        }

        private void editButton(JPanel playground) {
                class editMethodsHolder implements iEditButton {
                        public boolean valuesArePlaceholders() {

                                if (namePlaceholder.getValue() && emailPlaceholder.getValue()) {
                                        successLabel.setText("Error. You must modify at least one field.");
                                        successLabel.setVisible(true);
                                        return true;
                                }
                                return false;
                        }

                        public boolean areInputsInvalid() {
                                if (!namePlaceholder.getValue() && providerAPI.isNameTaken(nameTextField.getText())) {
                                        successLabel.setText("Error. The given name is already taken.");
                                        successLabel.setVisible(true);
                                        return true;
                                }
                                if (!emailPlaceholder.getValue() && (!emailTextField.getText().contains("@")
                                                || emailTextField.getText().length() < 3)) {
                                        successLabel.setText("Error. The given email is invalid.");
                                        successLabel.setVisible(true);
                                        return true;
                                }
                                return false;
                        }

                        public void editFoodComponent() {
                                boolean successfulUpdate = true;
                                if (!namePlaceholder.getValue())
                                        successfulUpdate = providerAPI.updateName(theCurrentProvider,
                                                        nameTextField.getText());
                                if (!emailPlaceholder.getValue() && successfulUpdate)
                                        successfulUpdate = providerAPI.updateEmail(theCurrentProvider,
                                                        emailTextField.getText());
                                if (successfulUpdate)
                                        successLabel.setText("The provider \"" + nameTextField.getText()
                                                        + "\" was successfully updated.");
                                else
                                        successLabel.setText("Something went wrong while updating the provider.");
                                successLabel.setVisible(true);
                        }

                        public void updatePlaceholders() {
                                theCurrentProvider = providerAPI.getProvider(theCurrentProvider.getId());

                                namePlaceholder.setValue(true);
                                emailPlaceholder.setValue(true);

                                theProviderLabel.setText(theCurrentProvider.getName());
                                nameTextField.setForeground(Color.GRAY);
                                emailTextField.setForeground(Color.GRAY);

                                applyGenericListeners();
                        }
                }
                editButtonFormatter.formatEditButton(editProviderButton, new editMethodsHolder());
        }

        private void applyGenericListeners() {
                iTextFieldListener textListener = new editTextFieldFListener();
                textListener.applyListenerTextField(nameTextField, theCurrentProvider.getName(), namePlaceholder,
                                false);
                textListener.applyListenerTextField(emailTextField, theCurrentProvider.getEmail(), emailPlaceholder,
                                false);
        }
}