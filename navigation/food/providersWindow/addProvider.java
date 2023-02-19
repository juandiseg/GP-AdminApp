package navigation.food.providersWindow;

import java.awt.event.*;
import javax.swing.*;
import java.awt.*;

public class addProvider {

    private JLabel providerLabel = new JLabel();

    private JLabel nameLabel = new JLabel();
    private JLabel emailLabel = new JLabel();
    private JLabel successLabel = new JLabel();

    private JTextField nameTextField = new JTextField();
    private JTextField emailTextField = new JTextField();
    private boolean namePlaceholder = true;
    private boolean emailPlaceholder = true;

    private JButton addProviderButton = new JButton();
    private JButton backButton = new JButton();

    private JPanel jPanel1 = new JPanel();
    private JPanel jPanel2 = new JPanel();
    private JPanel jPanel3 = new JPanel();

    public addProvider(JPanel playground) {
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

        nameLabel.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        nameLabel.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        nameLabel.setText("Name");
        nameLabel.setVerticalAlignment(javax.swing.SwingConstants.BOTTOM);

        nameTextField.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        nameTextField.setText("Ex. 'Konsum'");
        nameTextField.setForeground(Color.GRAY);

        addProviderButton.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        addProviderButton.setText("Add Provider");
        addProviderButton.setBackground(new Color(255, 255, 255));
        addProviderButton.setForeground(new Color(23, 35, 51));

        jPanel2.setBackground(new java.awt.Color(0, 0, 0));

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
                jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGap(0, 8, Short.MAX_VALUE));
        jPanel2Layout.setVerticalGroup(
                jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGap(0, 0, Short.MAX_VALUE));

        emailLabel.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        emailLabel.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        emailLabel.setText("Email");
        emailLabel.setVerticalAlignment(javax.swing.SwingConstants.BOTTOM);

        emailTextField.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        emailTextField.setText("Ex. 'provider@konsum.de'");
        emailTextField.setForeground(Color.GRAY);

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
                jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(jPanel1Layout.createSequentialGroup()
                                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE,
                                        javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addGroup(jPanel1Layout.createSequentialGroup()
                                                .addGap(113, 113, 113)
                                                .addGroup(jPanel1Layout
                                                        .createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING,
                                                                false)
                                                        .addComponent(nameLabel,
                                                                javax.swing.GroupLayout.Alignment.TRAILING,
                                                                javax.swing.GroupLayout.DEFAULT_SIZE,
                                                                javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                                        .addComponent(emailLabel,
                                                                javax.swing.GroupLayout.Alignment.TRAILING,
                                                                javax.swing.GroupLayout.PREFERRED_SIZE, 109,
                                                                javax.swing.GroupLayout.PREFERRED_SIZE))
                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                                .addGroup(jPanel1Layout
                                                        .createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                        .addComponent(nameTextField,
                                                                javax.swing.GroupLayout.PREFERRED_SIZE, 434,
                                                                javax.swing.GroupLayout.PREFERRED_SIZE)
                                                        .addComponent(emailTextField,
                                                                javax.swing.GroupLayout.PREFERRED_SIZE, 434,
                                                                javax.swing.GroupLayout.PREFERRED_SIZE))
                                                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                                        .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout
                                                .createSequentialGroup()
                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED,
                                                        javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                                .addComponent(addProviderButton, javax.swing.GroupLayout.PREFERRED_SIZE,
                                                        200, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                .addGap(330, 330, 330)))));
        jPanel1Layout.setVerticalGroup(
                jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                                .addGap(20, 20, 20)
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                        .addComponent(nameLabel)
                                        .addComponent(nameTextField, javax.swing.GroupLayout.PREFERRED_SIZE, 35,
                                                javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                        .addComponent(emailTextField, javax.swing.GroupLayout.PREFERRED_SIZE, 35,
                                                javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addComponent(emailLabel))
                                .addGap(42, 42, 42)
                                .addComponent(addProviderButton, javax.swing.GroupLayout.PREFERRED_SIZE, 55,
                                        javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addContainerGap(44, Short.MAX_VALUE))
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

        backButton.setBackground(new java.awt.Color(71, 120, 197));
        backButton.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        backButton.setForeground(new java.awt.Color(255, 255, 255));
        backButton.setText("Back");

        providerLabel.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        providerLabel.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        providerLabel.setText("Add Provider");
        providerLabel.setVerticalAlignment(javax.swing.SwingConstants.BOTTOM);

        javax.swing.GroupLayout playgroundLayout = new javax.swing.GroupLayout(playground);
        playground.setLayout(playgroundLayout);
        playgroundLayout.setHorizontalGroup(
                playgroundLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE,
                                javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGroup(playgroundLayout.createSequentialGroup()
                                .addGap(185, 185, 185)
                                .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE,
                                        javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(0, 185, Short.MAX_VALUE))
                        .addGroup(playgroundLayout.createSequentialGroup()
                                .addContainerGap()
                                .addGroup(playgroundLayout
                                        .createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addComponent(providerLabel, javax.swing.GroupLayout.DEFAULT_SIZE,
                                                javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                        .addGroup(playgroundLayout.createSequentialGroup()
                                                .addComponent(backButton, javax.swing.GroupLayout.PREFERRED_SIZE, 200,
                                                        javax.swing.GroupLayout.PREFERRED_SIZE)
                                                .addGap(0, 0, Short.MAX_VALUE))
                                        .addComponent(successLabel, javax.swing.GroupLayout.Alignment.TRAILING,
                                                javax.swing.GroupLayout.DEFAULT_SIZE,
                                                javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                                .addContainerGap()));
        playgroundLayout.setVerticalGroup(
                playgroundLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, playgroundLayout.createSequentialGroup()
                                .addGap(39, 39, 39)
                                .addComponent(providerLabel)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE,
                                        javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE,
                                        javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(successLabel)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 208,
                                        Short.MAX_VALUE)
                                .addComponent(backButton, javax.swing.GroupLayout.PREFERRED_SIZE, 55,
                                        javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addContainerGap()));
    }

    private void addActionListeners(JPanel playground) {
        backButton.addMouseListener(new MouseListener() {
            public void mouseClicked(MouseEvent e) {
                playground.removeAll();
                new mainProvider(playground);
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
        addProviderButton.addMouseListener(new MouseListener() {
            public void mouseClicked(MouseEvent e) {
                providerAPI theManagerDB = new providerAPI();
                if (namePlaceholder || emailPlaceholder) {
                    successLabel.setText("To add a category you must specify a name and email for it.");
                    successLabel.setVisible(true);
                    return;
                }
                String name = nameTextField.getText();
                String email = emailTextField.getText();
                if (theManagerDB.addProvider(name, email))
                    successLabel.setText("Provider: \"" + name + "\" was successfully added.");
                else
                    successLabel.setText(
                            "Something went wrong while adding \"" + name + "\".");
                successLabel.setVisible(true);
            }

            public void mousePressed(MouseEvent e) {
            }

            public void mouseReleased(MouseEvent e) {
            }

            public void mouseEntered(MouseEvent e) {
                addProviderButton.setBackground(new Color(23, 35, 51));
                addProviderButton.setForeground(new Color(255, 255, 255));
            }

            public void mouseExited(MouseEvent e) {
                addProviderButton.setBackground(new Color(255, 255, 255));
                addProviderButton.setForeground(new Color(23, 35, 51));
            }
        });
        nameTextField.addFocusListener(new FocusListener() {
            public void focusGained(FocusEvent e) {
                if (nameTextField.getText().equals("Ex. 'Konsum'")) {
                    nameTextField.setText("");
                    nameTextField.setForeground(Color.BLACK);
                    namePlaceholder = false;
                }
            }

            public void focusLost(FocusEvent e) {
                if (nameTextField.getText().isEmpty()) {
                    nameTextField.setForeground(Color.GRAY);
                    nameTextField.setText("Ex. 'Konsum'");
                    namePlaceholder = true;
                }
            }
        });
        emailTextField.addFocusListener(new FocusListener() {
            public void focusGained(FocusEvent e) {
                if (emailTextField.getText().equals("Ex. 'provider@konsum.de'")) {
                    emailTextField.setText("");
                    emailTextField.setForeground(Color.BLACK);
                    emailPlaceholder = false;
                }
            }

            public void focusLost(FocusEvent e) {
                if (emailTextField.getText().isEmpty()) {
                    emailTextField.setForeground(Color.GRAY);
                    emailTextField.setText("Ex. 'provider@konsum.de'");
                    emailPlaceholder = true;
                }
            }
        });
    }
}