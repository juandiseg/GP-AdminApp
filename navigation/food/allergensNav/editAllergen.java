package navigation.food.allergensWindow;

import componentsFood.allergen;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.LayoutStyle.ComponentPlacement;

import java.awt.*;

public class editAllergen {

        private JLabel auxEditAllergenLabel = new JLabel();
        private JLabel editAllergenLabel = new JLabel();

        private JPanel jPanel1 = new JPanel();
        private JPanel jPanel2 = new JPanel();
        private JPanel jPanel3 = new JPanel();

        private JLabel nameLabel = new JLabel();
        private JLabel successLabel = new JLabel();

        private JButton editAllergenButton = new JButton();
        private JButton backButton = new JButton();
        private JButton deleteButton = new JButton();

        private boolean namePlaceholder = true;

        private JTextField nameTextField = new JTextField();
        private allergen theCurrentAllergen;

        public editAllergen(JPanel playground, allergen theCurrentAllergen) {
                this.theCurrentAllergen = theCurrentAllergen;
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
                nameTextField.setText("Enter new NAME here");
                nameTextField.setForeground(Color.gray);

                editAllergenButton.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
                editAllergenButton.setText("Edit Allergen");
                editAllergenButton.setBackground(new Color(255, 255, 255));
                editAllergenButton.setForeground(new Color(23, 35, 51));

                jPanel2.setBackground(Color.black);

                GroupLayout jPanel2Layout = new GroupLayout(jPanel2);
                jPanel2.setLayout(jPanel2Layout);
                jPanel2Layout.setHorizontalGroup(
                                jPanel2Layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                                                .addGap(0, 8, Short.MAX_VALUE));
                jPanel2Layout.setVerticalGroup(
                                jPanel2Layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                                                .addGap(0, 0, Short.MAX_VALUE));

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
                                                                                                .addGap(331, 331, 331)
                                                                                                .addComponent(editAllergenButton,
                                                                                                                GroupLayout.PREFERRED_SIZE,
                                                                                                                200,
                                                                                                                GroupLayout.PREFERRED_SIZE))
                                                                                .addGroup(jPanel1Layout
                                                                                                .createSequentialGroup()
                                                                                                .addGap(117, 117, 117)
                                                                                                .addComponent(nameLabel,
                                                                                                                GroupLayout.PREFERRED_SIZE,
                                                                                                                106,
                                                                                                                GroupLayout.PREFERRED_SIZE)
                                                                                                .addPreferredGap(
                                                                                                                ComponentPlacement.RELATED)
                                                                                                .addComponent(nameTextField,
                                                                                                                GroupLayout.PREFERRED_SIZE,
                                                                                                                434,
                                                                                                                GroupLayout.PREFERRED_SIZE)))
                                                                .addContainerGap(GroupLayout.DEFAULT_SIZE,
                                                                                Short.MAX_VALUE)));
                jPanel1Layout.setVerticalGroup(
                                jPanel1Layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                                                .addGroup(GroupLayout.Alignment.TRAILING, jPanel1Layout
                                                                .createSequentialGroup()
                                                                .addGap(41, 41, 41)
                                                                .addGroup(jPanel1Layout.createParallelGroup(
                                                                                GroupLayout.Alignment.BASELINE)
                                                                                .addComponent(nameLabel)
                                                                                .addComponent(nameTextField,
                                                                                                GroupLayout.PREFERRED_SIZE,
                                                                                                35,
                                                                                                GroupLayout.PREFERRED_SIZE))
                                                                .addGap(49, 49, 49)
                                                                .addComponent(editAllergenButton,
                                                                                GroupLayout.PREFERRED_SIZE,
                                                                                55,
                                                                                GroupLayout.PREFERRED_SIZE)
                                                                .addContainerGap(37, Short.MAX_VALUE))
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

                deleteButton.setBackground(new Color(255, 102, 102));
                deleteButton.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
                deleteButton.setText("Delete");

                backButton.setBackground(new Color(71, 120, 197));
                backButton.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
                backButton.setForeground(new Color(255, 255, 255));
                backButton.setText("Back");

                editAllergenLabel.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
                editAllergenLabel.setHorizontalAlignment(SwingConstants.CENTER);
                editAllergenLabel.setText(theCurrentAllergen.getName());
                editAllergenLabel.setVerticalAlignment(SwingConstants.BOTTOM);

                auxEditAllergenLabel.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
                auxEditAllergenLabel.setHorizontalAlignment(SwingConstants.CENTER);
                auxEditAllergenLabel.setText("Allergen to edit:");
                auxEditAllergenLabel.setVerticalAlignment(SwingConstants.BOTTOM);

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
                                                                                .addComponent(successLabel,
                                                                                                GroupLayout.Alignment.TRAILING,
                                                                                                GroupLayout.DEFAULT_SIZE,
                                                                                                GroupLayout.DEFAULT_SIZE,
                                                                                                Short.MAX_VALUE)
                                                                                .addGroup(GroupLayout.Alignment.TRAILING,
                                                                                                playgroundLayout
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
                                                                                .addComponent(editAllergenLabel,
                                                                                                GroupLayout.DEFAULT_SIZE,
                                                                                                GroupLayout.DEFAULT_SIZE,
                                                                                                Short.MAX_VALUE)
                                                                                .addComponent(auxEditAllergenLabel,
                                                                                                GroupLayout.DEFAULT_SIZE,
                                                                                                GroupLayout.DEFAULT_SIZE,
                                                                                                Short.MAX_VALUE))
                                                                .addContainerGap()));
                playgroundLayout.setVerticalGroup(
                                playgroundLayout.createParallelGroup(GroupLayout.Alignment.LEADING)
                                                .addGroup(GroupLayout.Alignment.TRAILING, playgroundLayout
                                                                .createSequentialGroup()
                                                                .addGap(8, 8, 8)
                                                                .addComponent(auxEditAllergenLabel)
                                                                .addPreferredGap(
                                                                                ComponentPlacement.RELATED)
                                                                .addComponent(editAllergenLabel)
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
                                                                                228,
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

        private void addActionListeners(JPanel playground) {
                backButton.addMouseListener(new MouseListener() {
                        public void mouseClicked(MouseEvent e) {
                                playground.removeAll();
                                new mainAllergen(playground);
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
                editAllergenButton.addMouseListener(new MouseListener() {
                        public void mouseClicked(MouseEvent e) {
                                String name = nameTextField.getText();
                                if (namePlaceholder) {
                                        successLabel.setText(
                                                        "You must assign a new name in order to edit an allergen.");
                                        successLabel.setVisible(true);
                                        return;
                                }
                                allergensAPI theManagerDB = new allergensAPI();
                                if (theManagerDB.isNameTaken(name)) {
                                        successLabel.setText("Error. The given name is already taken.");
                                        successLabel.setVisible(true);
                                        return;
                                }
                                if (theManagerDB.editAllergen(theCurrentAllergen, name)) {
                                        successLabel.setText("'" + name + "' has been successfully updated.");
                                        successLabel.setVisible(true);
                                        theCurrentAllergen = new allergensAPI().getAllergen(theCurrentAllergen.getId());
                                        editAllergenLabel.setText(theCurrentAllergen.getName());
                                } else {
                                        successLabel.setText("Something went wrong while updating "
                                                        + theCurrentAllergen.getName());
                                        successLabel.setVisible(true);
                                }
                        }

                        public void mousePressed(MouseEvent e) {
                        }

                        public void mouseReleased(MouseEvent e) {
                        }

                        public void mouseEntered(MouseEvent e) {
                                editAllergenButton.setBackground(new Color(23, 35, 51));
                                editAllergenButton.setForeground(new Color(255, 255, 255));
                        }

                        public void mouseExited(MouseEvent e) {
                                editAllergenButton.setBackground(new Color(255, 255, 255));
                                editAllergenButton.setForeground(new Color(23, 35, 51));
                        }
                });
                deleteButton.addMouseListener(new MouseListener() {
                        public void mouseClicked(MouseEvent e) {
                                int reply = JOptionPane.showConfirmDialog(null,
                                                "Are you sure you want to delete " + theCurrentAllergen.getName()
                                                                + " from allergens?",
                                                "Confirmation", JOptionPane.YES_NO_OPTION);
                                if (reply == JOptionPane.YES_OPTION) {
                                        if (!new allergensAPI().removeAllergen(theCurrentAllergen)) {
                                                JOptionPane.showMessageDialog(playground, "Something went wrong.",
                                                                "Error",
                                                                JOptionPane.ERROR_MESSAGE);
                                        }
                                        playground.removeAll();
                                        new mainAllergen(playground);
                                        playground.revalidate();
                                        playground.repaint();
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
                nameTextField.addFocusListener(new FocusListener() {

                        public void focusGained(FocusEvent e) {
                                if (nameTextField.getText().equals("Enter new NAME here")) {
                                        nameTextField.setText("");
                                        nameTextField.setForeground(Color.BLACK);
                                        namePlaceholder = false;
                                }
                        }

                        public void focusLost(FocusEvent e) {
                                if (nameTextField.getText().isEmpty()) {
                                        nameTextField.setForeground(Color.GRAY);
                                        nameTextField.setText("Enter new NAME here");
                                        namePlaceholder = true;
                                }
                        }
                });
        }
}