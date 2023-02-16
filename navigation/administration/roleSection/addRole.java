package navigation.administration.roleSection;

import java.awt.event.*;
import javax.swing.*;
import java.awt.*;

public class addRole {

        private JLabel addRoleLabel = new JLabel();
        private JLabel nameLabel = new JLabel();
        private JLabel successLabel = new JLabel();

        private JButton addRoleButton = new JButton();
        private JButton backButton = new JButton();

        private JPanel jPanel1 = new JPanel();
        private JPanel jPanel2 = new JPanel();
        private JPanel jPanel3 = new JPanel();
        private boolean placeholder = true;

        private JTextField nameTextField = new JTextField();

        public addRole(JPanel playground) {
                initComponents(playground);
                addActionListeners(playground);
        }

        private void initComponents(JPanel playground) {

                nameTextField.setForeground(Color.GRAY);
                nameTextField.setText("Enter NAME here");
                playground.setBackground(new Color(255, 255, 255));
                successLabel.setFont(new Font("Segoe UI", 0, 18)); // NOI18N
                successLabel.setHorizontalAlignment(SwingConstants.RIGHT);
                successLabel.setText("returns the user status over their request");
                successLabel.setVerticalAlignment(SwingConstants.BOTTOM);
                successLabel.setVisible(false);
                addRoleButton.setBackground(new Color(255, 255, 255));
                addRoleButton.setForeground(new Color(23, 35, 51));
                jPanel1.setBackground(new Color(120, 168, 252));
                jPanel1.setCursor(new Cursor(Cursor.DEFAULT_CURSOR));
                jPanel1.setDebugGraphicsOptions(DebugGraphics.NONE_OPTION);

                nameLabel.setFont(new Font("Segoe UI", 1, 18)); // NOI18N
                nameLabel.setHorizontalAlignment(SwingConstants.LEFT);
                nameLabel.setText("Name");
                nameLabel.setVerticalAlignment(SwingConstants.BOTTOM);

                nameTextField.setFont(new Font("Segoe UI", 0, 18)); // NOI18N

                addRoleButton.setFont(new Font("Segoe UI", 1, 14)); // NOI18N
                addRoleButton.setText("Add Role");

                jPanel2.setBackground(new Color(0, 0, 0));

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
                                                                                                .addComponent(addRoleButton,
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
                                                                                                                LayoutStyle.ComponentPlacement.RELATED)
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
                                                                .addComponent(addRoleButton,
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

                backButton.setBackground(new Color(71, 120, 197));
                backButton.setFont(new Font("Segoe UI", 1, 14)); // NOI18N
                backButton.setForeground(new Color(255, 255, 255));
                backButton.setText("Back");
                backButton.setBackground(new Color(71, 120, 197));

                addRoleLabel.setFont(new Font("Segoe UI", 1, 18)); // NOI18N
                addRoleLabel.setHorizontalAlignment(SwingConstants.CENTER);
                addRoleLabel.setText("Add Role");
                addRoleButton.setBackground(new Color(255, 255, 255));
                addRoleButton.setForeground(new Color(23, 35, 51));
                addRoleLabel.setVerticalAlignment(SwingConstants.BOTTOM);

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
                                                                .addGroup(playgroundLayout.createParallelGroup(
                                                                                GroupLayout.Alignment.LEADING)
                                                                                .addComponent(successLabel,
                                                                                                GroupLayout.Alignment.TRAILING,
                                                                                                GroupLayout.DEFAULT_SIZE,
                                                                                                GroupLayout.DEFAULT_SIZE,
                                                                                                Short.MAX_VALUE)
                                                                                .addGroup(GroupLayout.Alignment.TRAILING,
                                                                                                playgroundLayout.createSequentialGroup()
                                                                                                                .addComponent(backButton,
                                                                                                                                GroupLayout.PREFERRED_SIZE,
                                                                                                                                200,
                                                                                                                                GroupLayout.PREFERRED_SIZE)
                                                                                                                .addGap(0, 0, Short.MAX_VALUE))
                                                                                .addComponent(addRoleLabel,
                                                                                                GroupLayout.DEFAULT_SIZE,
                                                                                                GroupLayout.DEFAULT_SIZE,
                                                                                                Short.MAX_VALUE))
                                                                .addContainerGap()));
                playgroundLayout.setVerticalGroup(
                                playgroundLayout.createParallelGroup(GroupLayout.Alignment.LEADING)
                                                .addGroup(GroupLayout.Alignment.TRAILING, playgroundLayout
                                                                .createSequentialGroup()
                                                                .addGap(39, 39, 39)
                                                                .addComponent(addRoleLabel)
                                                                .addPreferredGap(
                                                                                LayoutStyle.ComponentPlacement.RELATED)
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
                                                                                LayoutStyle.ComponentPlacement.RELATED)
                                                                .addComponent(successLabel)
                                                                .addPreferredGap(
                                                                                LayoutStyle.ComponentPlacement.RELATED,
                                                                                228, Short.MAX_VALUE)
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
                                new mainRole(playground);
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
                addRoleButton.addMouseListener(new MouseListener() {
                        public void mouseClicked(MouseEvent e) {
                                rolesAPI theManagerDB = new rolesAPI();
                                String name = nameTextField.getText();
                                if (name.isEmpty() || placeholder)
                                        return;
                                if (theManagerDB.addRole(name)) {
                                        successLabel.setText("'" + name + "' Successfully added !");
                                        successLabel.setVisible(true);
                                } else {
                                        successLabel.setText("Something went wrong when adding '" + name + "'.");
                                        successLabel.setVisible(true);
                                }
                        }

                        public void mousePressed(MouseEvent e) {
                        }

                        public void mouseReleased(MouseEvent e) {
                        }

                        public void mouseEntered(MouseEvent e) {
                                addRoleButton.setBackground(new Color(23, 35, 51));
                                addRoleButton.setForeground(new Color(255, 255, 255));
                        }

                        public void mouseExited(MouseEvent e) {
                                addRoleButton.setBackground(new Color(255, 255, 255));
                                addRoleButton.setForeground(new Color(23, 35, 51));
                        }
                });
                nameTextField.addFocusListener(new FocusListener() {
                        public void focusGained(FocusEvent e) {
                                if (nameTextField.getText().equals("Enter NAME here")) {
                                        nameTextField.setText("");
                                        nameTextField.setForeground(Color.BLACK);
                                        placeholder = false;
                                }
                        }

                        public void focusLost(FocusEvent e) {
                                if (nameTextField.getText().isEmpty()) {
                                        nameTextField.setForeground(Color.GRAY);
                                        nameTextField.setText("Enter NAME here");
                                        placeholder = true;
                                }
                        }
                });
        }
}