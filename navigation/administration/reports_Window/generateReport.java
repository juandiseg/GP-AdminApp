package navigation.administration.reports_Window;

import java.awt.event.*;
import javax.swing.*;

import jnafilechooser.api.JnaFileChooser;

import java.awt.*;

public class generateReport {

    private JPanel jPanel1 = new JPanel();
    private JPanel jPanel2 = new JPanel();
    private JPanel jPanel3 = new JPanel();

    private JLabel sectionTitle = new JLabel();
    private JLabel fromLabel = new JLabel();
    private JLabel toLabel = new JLabel();
    private JLabel comboLabel = new JLabel();

    private JTextField fromTextField = new JTextField();
    private JTextField toTextField = new JTextField();
    private JComboBox<String> reportsComboBox = new JComboBox<>();

    private JButton generateButton = new JButton();

    private boolean fromPlaceholder = true;
    private boolean toPlaceholder = true;

    private JLabel successLabel = new JLabel();

    public generateReport(JFrame theFrame, JPanel playground) {
        initComponents(playground);
        addActionListeners(theFrame);
    }

    private void initComponents(JPanel playground) {
        playground.setBackground(new java.awt.Color(255, 255, 255));

        successLabel.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        successLabel.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        successLabel.setText("Product successfully added !");
        successLabel.setVisible(false);
        successLabel.setVerticalAlignment(javax.swing.SwingConstants.BOTTOM);

        jPanel1.setBackground(new java.awt.Color(120, 168, 252));
        jPanel1.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        jPanel1.setDebugGraphicsOptions(javax.swing.DebugGraphics.NONE_OPTION);

        fromLabel.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        fromLabel.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        fromLabel.setText("From");
        fromLabel.setVerticalAlignment(javax.swing.SwingConstants.BOTTOM);

        fromTextField.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        fromTextField.setText("YYYY-MM-DD");
        fromTextField.setForeground(Color.GRAY);

        generateButton.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        generateButton.setText("Generate Report");
        generateButton.setActionCommand("Add Product");
        generateButton.setBackground(new Color(255, 255, 255));
        generateButton.setForeground(new Color(23, 35, 51));

        jPanel2.setBackground(new java.awt.Color(0, 0, 0));

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
                jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGap(0, 8, Short.MAX_VALUE));
        jPanel2Layout.setVerticalGroup(
                jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGap(0, 0, Short.MAX_VALUE));

        toLabel.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        toLabel.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        toLabel.setText("To");
        toLabel.setVerticalAlignment(javax.swing.SwingConstants.BOTTOM);

        toTextField.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        toTextField.setText("YYYY-MM-DD");
        toTextField.setForeground(Color.GRAY);

        comboLabel.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        comboLabel.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        comboLabel.setText("Choose Report");
        comboLabel.setVerticalAlignment(javax.swing.SwingConstants.BOTTOM);

        reportsComboBox.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        reportsComboBox.setModel(
                new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
                jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(jPanel1Layout.createSequentialGroup()
                                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE,
                                        javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addGroup(jPanel1Layout.createSequentialGroup()
                                                .addGap(95, 95, 95)
                                                .addGroup(jPanel1Layout
                                                        .createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING,
                                                                false)
                                                        .addComponent(toLabel,
                                                                javax.swing.GroupLayout.Alignment.TRAILING,
                                                                javax.swing.GroupLayout.DEFAULT_SIZE,
                                                                javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                                        .addComponent(comboLabel,
                                                                javax.swing.GroupLayout.Alignment.TRAILING,
                                                                javax.swing.GroupLayout.DEFAULT_SIZE,
                                                                javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                                        .addComponent(fromLabel, javax.swing.GroupLayout.DEFAULT_SIZE,
                                                                javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                                .addGroup(jPanel1Layout
                                                        .createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                        .addComponent(toTextField,
                                                                javax.swing.GroupLayout.PREFERRED_SIZE, 434,
                                                                javax.swing.GroupLayout.PREFERRED_SIZE)
                                                        .addComponent(fromTextField,
                                                                javax.swing.GroupLayout.PREFERRED_SIZE, 434,
                                                                javax.swing.GroupLayout.PREFERRED_SIZE)
                                                        .addComponent(reportsComboBox,
                                                                javax.swing.GroupLayout.PREFERRED_SIZE, 434,
                                                                javax.swing.GroupLayout.PREFERRED_SIZE))
                                                .addContainerGap(201, Short.MAX_VALUE))
                                        .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout
                                                .createSequentialGroup()
                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED,
                                                        javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                                .addComponent(generateButton, javax.swing.GroupLayout.PREFERRED_SIZE,
                                                        200, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                .addGap(323, 323, 323)))));
        jPanel1Layout.setVerticalGroup(
                jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                                .addGap(21, 21, 21)
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                        .addComponent(fromLabel)
                                        .addComponent(fromTextField, javax.swing.GroupLayout.PREFERRED_SIZE, 35,
                                                javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                        .addComponent(toTextField, javax.swing.GroupLayout.PREFERRED_SIZE, 35,
                                                javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addComponent(toLabel))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                        .addComponent(comboLabel)
                                        .addComponent(reportsComboBox, javax.swing.GroupLayout.PREFERRED_SIZE, 34,
                                                javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 35,
                                        Short.MAX_VALUE)
                                .addComponent(generateButton, javax.swing.GroupLayout.PREFERRED_SIZE, 55,
                                        javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(27, 27, 27))
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

        sectionTitle.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        sectionTitle.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        sectionTitle.setText("Generate Report");
        sectionTitle.setToolTipText("");
        sectionTitle.setVerticalAlignment(javax.swing.SwingConstants.BOTTOM);

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
                        .addComponent(sectionTitle, javax.swing.GroupLayout.DEFAULT_SIZE,
                                javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, playgroundLayout.createSequentialGroup()
                                .addContainerGap()
                                .addComponent(successLabel, javax.swing.GroupLayout.DEFAULT_SIZE,
                                        javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addContainerGap()));
        playgroundLayout.setVerticalGroup(
                playgroundLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, playgroundLayout.createSequentialGroup()
                                .addGap(39, 39, 39)
                                .addComponent(sectionTitle)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE,
                                        javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE,
                                        javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(successLabel)
                                .addContainerGap(252, Short.MAX_VALUE)));
        setComboBox();
    }

    private void addActionListeners(JFrame theFrame) {
        fromTextField.addFocusListener(new FocusListener() {
            public void focusGained(FocusEvent e) {
                if (fromPlaceholder) {
                    fromTextField.setText("");
                    fromTextField.setForeground(Color.BLACK);
                    fromPlaceholder = false;
                }
            }

            public void focusLost(FocusEvent e) {
                if (fromTextField.getText().isEmpty()) {
                    fromTextField.setForeground(Color.GRAY);
                    fromTextField.setText("YYYY-MM-DD");
                    fromPlaceholder = true;
                }
            }
        });
        toTextField.addFocusListener(new FocusListener() {
            public void focusGained(FocusEvent e) {
                if (toPlaceholder) {
                    toTextField.setText("");
                    toTextField.setForeground(Color.BLACK);
                    toPlaceholder = false;
                }
            }

            public void focusLost(FocusEvent e) {
                if (toTextField.getText().isEmpty()) {
                    toTextField.setForeground(Color.GRAY);
                    toTextField.setText("YYYY-MM-DD");
                    toPlaceholder = true;
                }
            }
        });
        generateButton.addMouseListener(new MouseListener() {
            public void mouseClicked(MouseEvent e) {
                if (fromPlaceholder || toPlaceholder) {
                    successLabel.setText("Error. You must fill all the given fields.");
                    successLabel.setVisible(true);
                    return;
                }
                // Check date input
                JnaFileChooser chooser = new JnaFileChooser();
                chooser.setMode(JnaFileChooser.Mode.Directories);
                boolean action = chooser.showOpenDialog(theFrame);
                if (action) {
                    if (((String) (reportsComboBox.getSelectedItem())).equals("Sales Report")) {
                        try {
                            new reportGeneratorFactory().createReportGenerator("SALES").generateReport(
                                    fromTextField.getText(),
                                    toTextField.getText(), chooser.getSelectedFile().getAbsolutePath());
                            successLabel.setText("Report successfully generated");
                            successLabel.setVisible(true);
                        } catch (Exception e1) {
                            successLabel.setText("Something went wrong while generating the report");
                            successLabel.setVisible(true);
                        }
                    } else if (((String) (reportsComboBox.getSelectedItem())).equals("Expenses Report")) {
                        try {
                            new reportGeneratorFactory().createReportGenerator("EXPENSES").generateReport(
                                    fromTextField.getText(),
                                    toTextField.getText(), chooser.getSelectedFile().getAbsolutePath());
                            successLabel.setText("Report successfully generated");
                            successLabel.setVisible(true);
                        } catch (Exception e1) {
                            successLabel.setText("Something went wrong while generating the report");
                            successLabel.setVisible(true);
                        }
                    } else if (((String) (reportsComboBox.getSelectedItem())).equals("General Report")) {
                        try {
                            new reportGeneratorFactory().createReportGenerator("GENERAL").generateReport(
                                    fromTextField.getText(),
                                    toTextField.getText(), chooser.getSelectedFile().getAbsolutePath());
                            successLabel.setText("Report successfully generated");
                            successLabel.setVisible(true);
                        } catch (Exception e1) {
                            successLabel.setText("Something went wrong while generating the report");
                            successLabel.setVisible(true);
                        }
                    }
                }
            }

            public void mousePressed(MouseEvent e) {
            }

            public void mouseReleased(MouseEvent e) {
            }

            public void mouseEntered(MouseEvent e) {
                generateButton.setBackground(new Color(23, 35, 51));
                generateButton.setForeground(new Color(255, 255, 255));
            }

            public void mouseExited(MouseEvent e) {
                generateButton.setBackground(new Color(255, 255, 255));
                generateButton.setForeground(new Color(23, 35, 51));
            }
        });
    }

    private void setComboBox() {
        reportsComboBox.setModel(
                new DefaultComboBoxModel<String>(new String[] { "Sales Report", "Expenses Report", "General Report" }));
        reportsComboBox.setFont(new Font("Segoe UI", 0, 18));
        reportsComboBox.setForeground(Color.BLACK);
        reportsComboBox.setBackground(Color.WHITE);
    }

}
