package navigation.food.providersWindow;

import componentsFood.provider;
import java.awt.event.*;
import javax.swing.*;
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

    private boolean namePlaceholder = true;
    private boolean emailPlaceholder = true;

    private JTextField nameTextField = new JTextField();
    private JTextField emailTextField = new JTextField();

    private provider theCurrentProvider;

    public editProvider(JPanel playground, provider theCurrentProvider) {
        this.theCurrentProvider = theCurrentProvider;
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
        nameTextField.setText(theCurrentProvider.getName());
        nameTextField.setForeground(Color.GRAY);

        editProviderButton.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        editProviderButton.setText("Edit Provider");
        editProviderButton.setBackground(new Color(255, 255, 255));
        editProviderButton.setForeground(new Color(23, 35, 51));

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
        emailTextField.setText(theCurrentProvider.getEmail());
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
                                                .addComponent(editProviderButton,
                                                        javax.swing.GroupLayout.PREFERRED_SIZE, 200,
                                                        javax.swing.GroupLayout.PREFERRED_SIZE)
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
                                .addComponent(editProviderButton, javax.swing.GroupLayout.PREFERRED_SIZE, 55,
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

        deleteButton.setBackground(new java.awt.Color(255, 102, 102));
        deleteButton.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        deleteButton.setText("Delete");

        backButton.setBackground(new java.awt.Color(71, 120, 197));
        backButton.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        backButton.setForeground(new java.awt.Color(255, 255, 255));
        backButton.setText("Back");

        theProviderLabel.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        theProviderLabel.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        theProviderLabel.setText(theCurrentProvider.getName());
        theProviderLabel.setVerticalAlignment(javax.swing.SwingConstants.BOTTOM);

        auxProviderLabel.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        auxProviderLabel.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        auxProviderLabel.setText("Provider to edit:");
        auxProviderLabel.setVerticalAlignment(javax.swing.SwingConstants.BOTTOM);

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
                                        .addComponent(theProviderLabel, javax.swing.GroupLayout.DEFAULT_SIZE,
                                                javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                        .addComponent(auxProviderLabel, javax.swing.GroupLayout.Alignment.TRAILING,
                                                javax.swing.GroupLayout.DEFAULT_SIZE,
                                                javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                        .addGroup(playgroundLayout.createSequentialGroup()
                                                .addComponent(backButton, javax.swing.GroupLayout.PREFERRED_SIZE, 200,
                                                        javax.swing.GroupLayout.PREFERRED_SIZE)
                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED,
                                                        javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                                .addComponent(deleteButton, javax.swing.GroupLayout.PREFERRED_SIZE, 200,
                                                        javax.swing.GroupLayout.PREFERRED_SIZE))
                                        .addComponent(successLabel, javax.swing.GroupLayout.Alignment.TRAILING,
                                                javax.swing.GroupLayout.DEFAULT_SIZE,
                                                javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                                .addContainerGap()));
        playgroundLayout.setVerticalGroup(
                playgroundLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, playgroundLayout.createSequentialGroup()
                                .addGap(8, 8, 8)
                                .addComponent(auxProviderLabel)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(theProviderLabel)
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
                                .addGroup(
                                        playgroundLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                                .addComponent(deleteButton, javax.swing.GroupLayout.PREFERRED_SIZE, 55,
                                                        javax.swing.GroupLayout.PREFERRED_SIZE)
                                                .addComponent(backButton, javax.swing.GroupLayout.PREFERRED_SIZE, 55,
                                                        javax.swing.GroupLayout.PREFERRED_SIZE))
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
        editProviderButton.addMouseListener(new MouseListener() {
            public void mouseClicked(MouseEvent e) {
                if (namePlaceholder && emailPlaceholder) {
                    successLabel.setText("At least one of the fields must be modified to edit a provider.");
                    successLabel.setVisible(true);
                    return;
                }
                providerAPI theManagerDB = new providerAPI();
                boolean successfulUpdate = true;

                if (!namePlaceholder)
                    successfulUpdate = theManagerDB.updateName(theCurrentProvider.getId(), nameTextField.getText());
                if (!emailPlaceholder) {
                    if (!successfulUpdate)
                        successfulUpdate = theManagerDB.updateEmail(theCurrentProvider.getId(),
                                emailTextField.getText());
                    else
                        theManagerDB.updateEmail(theCurrentProvider.getId(), emailTextField.getText());
                }
                if (successfulUpdate)
                    successLabel.setText(
                            "The provider '" + theCurrentProvider.getName() + "' has been successfully updated.");
                else
                    successLabel.setText("Something went wrong while updating the provider.");
                successLabel.setVisible(true);

                theCurrentProvider = theManagerDB.getProvider(theCurrentProvider.getId());
                theProviderLabel.setText(theCurrentProvider.getName());
                nameTextField.setText(theCurrentProvider.getName());
                nameTextField.setForeground(Color.GRAY);
                emailTextField.setText(theCurrentProvider.getEmail());
                emailTextField.setForeground(Color.GRAY);
            }

            public void mousePressed(MouseEvent e) {
            }

            public void mouseReleased(MouseEvent e) {
            }

            public void mouseEntered(MouseEvent e) {
                editProviderButton.setBackground(new Color(23, 35, 51));
                editProviderButton.setForeground(new Color(255, 255, 255));
            }

            public void mouseExited(MouseEvent e) {
                editProviderButton.setBackground(new Color(255, 255, 255));
                editProviderButton.setForeground(new Color(23, 35, 51));
            }
        });
        deleteButton.addMouseListener(new MouseListener() {
            public void mouseClicked(MouseEvent e) {
                int reply = JOptionPane.showConfirmDialog(null,
                        "Are you sure you want to DELETE \"" + theCurrentProvider.getName() + "\"?",
                        "Confirmation", JOptionPane.YES_NO_OPTION);
                if (reply == JOptionPane.YES_OPTION) {
                    if (new providerAPI().deleteProvider(theCurrentProvider.getId())) {
                        playground.removeAll();
                        new mainProvider(playground);
                        playground.revalidate();
                        playground.repaint();
                        return;
                    }
                    JOptionPane.showMessageDialog(playground,
                            "You cannot delete a provider if there is an ingredient assigned to it.",
                            "ERROR", JOptionPane.ERROR_MESSAGE);
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
            @Override
            public void focusGained(FocusEvent e) {
                if (nameTextField.getText().equals(theCurrentProvider.getName())) {
                    nameTextField.setText("");
                    nameTextField.setForeground(Color.BLACK);
                    namePlaceholder = false;
                }
            }

            @Override
            public void focusLost(FocusEvent e) {
                if (nameTextField.getText().isEmpty()) {
                    nameTextField.setForeground(Color.GRAY);
                    nameTextField.setText(theCurrentProvider.getName());
                    namePlaceholder = true;
                }
            }
        });
        emailTextField.addFocusListener(new FocusListener() {
            @Override
            public void focusGained(FocusEvent e) {
                if (emailTextField.getText().equals(theCurrentProvider.getEmail())) {
                    emailTextField.setText("");
                    emailTextField.setForeground(Color.BLACK);
                    emailPlaceholder = false;
                }
            }

            @Override
            public void focusLost(FocusEvent e) {
                if (emailTextField.getText().isEmpty()) {
                    emailTextField.setForeground(Color.GRAY);
                    emailTextField.setText(theCurrentProvider.getEmail());
                    emailPlaceholder = true;
                }
            }
        });
    }
}