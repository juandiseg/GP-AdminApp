package navigation.administration.reportsNav;

import java.awt.event.*;
import javax.swing.*;
import javax.swing.LayoutStyle.ComponentPlacement;

import jnafilechooser.api.JnaFileChooser;
import navigation.administration.reportsNav.reportsGeneration.reportGeneratorFactory;
import util.inputFormatting.iFormatter;
import util.inputFormatting.inputFormatterFactory;
import util.listenersFormatting.booleanWrapper;
import util.listenersFormatting.iTextFieldListener;
import util.listenersFormatting.add.addTextFieldFListener;
import util.listenersFormatting.edit.editTextFieldFListener;

import java.awt.*;

public class mainReports {

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

        private booleanWrapper fromPlaceholder = new booleanWrapper(true);
        private booleanWrapper toPlaceholder = new booleanWrapper(true);

        private JLabel successLabel = new JLabel();

        public mainReports(JFrame theFrame, JPanel playground) {
                initComponents(playground);
                addActionListeners(theFrame);
        }

        private void initComponents(JPanel playground) {
                playground.setBackground(new Color(255, 255, 255));

                successLabel.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
                successLabel.setHorizontalAlignment(SwingConstants.RIGHT);
                successLabel.setText("Product successfully added !");
                successLabel.setVisible(false);
                successLabel.setVerticalAlignment(SwingConstants.BOTTOM);

                jPanel1.setBackground(new Color(120, 168, 252));
                jPanel1.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
                jPanel1.setDebugGraphicsOptions(javax.swing.DebugGraphics.NONE_OPTION);

                fromLabel.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
                fromLabel.setHorizontalAlignment(SwingConstants.LEFT);
                fromLabel.setText("From");
                fromLabel.setVerticalAlignment(SwingConstants.BOTTOM);

                fromTextField.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
                fromTextField.setText("DD-MM-YYYY");
                fromTextField.setForeground(Color.GRAY);

                generateButton.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
                generateButton.setText("Generate Report");
                generateButton.setActionCommand("Add Product");
                generateButton.setBackground(new Color(255, 255, 255));
                generateButton.setForeground(new Color(23, 35, 51));

                jPanel2.setBackground(new Color(0, 0, 0));

                GroupLayout jPanel2Layout = new GroupLayout(jPanel2);
                jPanel2.setLayout(jPanel2Layout);
                jPanel2Layout.setHorizontalGroup(
                                jPanel2Layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                                                .addGap(0, 8, Short.MAX_VALUE));
                jPanel2Layout.setVerticalGroup(
                                jPanel2Layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                                                .addGap(0, 0, Short.MAX_VALUE));

                toLabel.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
                toLabel.setHorizontalAlignment(SwingConstants.LEFT);
                toLabel.setText("To");
                toLabel.setVerticalAlignment(SwingConstants.BOTTOM);

                toTextField.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
                toTextField.setText("DD-MM-YYYY");
                toTextField.setForeground(Color.GRAY);

                comboLabel.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
                comboLabel.setHorizontalAlignment(SwingConstants.LEFT);
                comboLabel.setText("Choose Report");
                comboLabel.setVerticalAlignment(SwingConstants.BOTTOM);

                reportsComboBox.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
                reportsComboBox.setModel(
                                new javax.swing.DefaultComboBoxModel<>(
                                                new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));

                GroupLayout jPanel1Layout = new GroupLayout(jPanel1);
                jPanel1.setLayout(jPanel1Layout);
                jPanel1Layout.setHorizontalGroup(
                                jPanel1Layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                                                .addGroup(jPanel1Layout.createSequentialGroup()
                                                                .addComponent(jPanel2, GroupLayout.PREFERRED_SIZE,
                                                                                GroupLayout.DEFAULT_SIZE,
                                                                                GroupLayout.PREFERRED_SIZE)
                                                                .addGroup(jPanel1Layout.createParallelGroup(
                                                                                GroupLayout.Alignment.LEADING)
                                                                                .addGroup(jPanel1Layout
                                                                                                .createSequentialGroup()
                                                                                                .addGap(95, 95, 95)
                                                                                                .addGroup(jPanel1Layout
                                                                                                                .createParallelGroup(
                                                                                                                                GroupLayout.Alignment.LEADING,
                                                                                                                                false)
                                                                                                                .addComponent(toLabel,
                                                                                                                                GroupLayout.Alignment.TRAILING,
                                                                                                                                GroupLayout.DEFAULT_SIZE,
                                                                                                                                GroupLayout.DEFAULT_SIZE,
                                                                                                                                Short.MAX_VALUE)
                                                                                                                .addComponent(comboLabel,
                                                                                                                                GroupLayout.Alignment.TRAILING,
                                                                                                                                GroupLayout.DEFAULT_SIZE,
                                                                                                                                GroupLayout.DEFAULT_SIZE,
                                                                                                                                Short.MAX_VALUE)
                                                                                                                .addComponent(fromLabel,
                                                                                                                                GroupLayout.DEFAULT_SIZE,
                                                                                                                                GroupLayout.DEFAULT_SIZE,
                                                                                                                                Short.MAX_VALUE))
                                                                                                .addPreferredGap(
                                                                                                                ComponentPlacement.RELATED)
                                                                                                .addGroup(jPanel1Layout
                                                                                                                .createParallelGroup(
                                                                                                                                GroupLayout.Alignment.LEADING)
                                                                                                                .addComponent(toTextField,
                                                                                                                                GroupLayout.PREFERRED_SIZE,
                                                                                                                                434,
                                                                                                                                GroupLayout.PREFERRED_SIZE)
                                                                                                                .addComponent(fromTextField,
                                                                                                                                GroupLayout.PREFERRED_SIZE,
                                                                                                                                434,
                                                                                                                                GroupLayout.PREFERRED_SIZE)
                                                                                                                .addComponent(reportsComboBox,
                                                                                                                                GroupLayout.PREFERRED_SIZE,
                                                                                                                                434,
                                                                                                                                GroupLayout.PREFERRED_SIZE))
                                                                                                .addContainerGap(201,
                                                                                                                Short.MAX_VALUE))
                                                                                .addGroup(GroupLayout.Alignment.TRAILING,
                                                                                                jPanel1Layout
                                                                                                                .createSequentialGroup()
                                                                                                                .addPreferredGap(
                                                                                                                                ComponentPlacement.RELATED,
                                                                                                                                GroupLayout.DEFAULT_SIZE,
                                                                                                                                Short.MAX_VALUE)
                                                                                                                .addComponent(generateButton,
                                                                                                                                GroupLayout.PREFERRED_SIZE,
                                                                                                                                200,
                                                                                                                                GroupLayout.PREFERRED_SIZE)
                                                                                                                .addGap(323, 323,
                                                                                                                                323)))));
                jPanel1Layout.setVerticalGroup(
                                jPanel1Layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                                                .addGroup(GroupLayout.Alignment.TRAILING, jPanel1Layout
                                                                .createSequentialGroup()
                                                                .addGap(21, 21, 21)
                                                                .addGroup(jPanel1Layout.createParallelGroup(
                                                                                GroupLayout.Alignment.BASELINE)
                                                                                .addComponent(fromLabel)
                                                                                .addComponent(fromTextField,
                                                                                                GroupLayout.PREFERRED_SIZE,
                                                                                                35,
                                                                                                GroupLayout.PREFERRED_SIZE))
                                                                .addPreferredGap(
                                                                                ComponentPlacement.RELATED)
                                                                .addGroup(jPanel1Layout.createParallelGroup(
                                                                                GroupLayout.Alignment.BASELINE)
                                                                                .addComponent(toTextField,
                                                                                                GroupLayout.PREFERRED_SIZE,
                                                                                                35,
                                                                                                GroupLayout.PREFERRED_SIZE)
                                                                                .addComponent(toLabel))
                                                                .addPreferredGap(
                                                                                ComponentPlacement.RELATED)
                                                                .addGroup(jPanel1Layout.createParallelGroup(
                                                                                GroupLayout.Alignment.BASELINE)
                                                                                .addComponent(comboLabel)
                                                                                .addComponent(reportsComboBox,
                                                                                                GroupLayout.PREFERRED_SIZE,
                                                                                                34,
                                                                                                GroupLayout.PREFERRED_SIZE))
                                                                .addPreferredGap(
                                                                                ComponentPlacement.RELATED,
                                                                                35,
                                                                                Short.MAX_VALUE)
                                                                .addComponent(generateButton,
                                                                                GroupLayout.PREFERRED_SIZE, 55,
                                                                                GroupLayout.PREFERRED_SIZE)
                                                                .addGap(27, 27, 27))
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

                sectionTitle.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
                sectionTitle.setHorizontalAlignment(SwingConstants.CENTER);
                sectionTitle.setText("Generate Report");
                sectionTitle.setToolTipText("");
                sectionTitle.setVerticalAlignment(SwingConstants.BOTTOM);

                GroupLayout playgroundLayout = new GroupLayout(playground);
                playground.setLayout(playgroundLayout);
                playgroundLayout.setHorizontalGroup(
                                playgroundLayout.createParallelGroup(GroupLayout.Alignment.LEADING)
                                                .addComponent(jPanel1, GroupLayout.DEFAULT_SIZE,
                                                                GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                                .addGroup(playgroundLayout.createSequentialGroup()
                                                                .addGap(185, 185, 185)
                                                                .addComponent(jPanel3, GroupLayout.PREFERRED_SIZE,
                                                                                GroupLayout.DEFAULT_SIZE,
                                                                                GroupLayout.PREFERRED_SIZE)
                                                                .addGap(0, 185, Short.MAX_VALUE))
                                                .addComponent(sectionTitle, GroupLayout.DEFAULT_SIZE,
                                                                GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                                .addGroup(GroupLayout.Alignment.TRAILING, playgroundLayout
                                                                .createSequentialGroup()
                                                                .addContainerGap()
                                                                .addComponent(successLabel, GroupLayout.DEFAULT_SIZE,
                                                                                GroupLayout.DEFAULT_SIZE,
                                                                                Short.MAX_VALUE)
                                                                .addContainerGap()));
                playgroundLayout.setVerticalGroup(
                                playgroundLayout.createParallelGroup(GroupLayout.Alignment.LEADING)
                                                .addGroup(GroupLayout.Alignment.TRAILING, playgroundLayout
                                                                .createSequentialGroup()
                                                                .addGap(39, 39, 39)
                                                                .addComponent(sectionTitle)
                                                                .addPreferredGap(
                                                                                ComponentPlacement.RELATED)
                                                                .addComponent(jPanel3, GroupLayout.PREFERRED_SIZE,
                                                                                GroupLayout.DEFAULT_SIZE,
                                                                                GroupLayout.PREFERRED_SIZE)
                                                                .addGap(18, 18, 18)
                                                                .addComponent(jPanel1, GroupLayout.PREFERRED_SIZE,
                                                                                GroupLayout.DEFAULT_SIZE,
                                                                                GroupLayout.PREFERRED_SIZE)
                                                                .addPreferredGap(
                                                                                ComponentPlacement.RELATED)
                                                                .addComponent(successLabel)
                                                                .addContainerGap(252, Short.MAX_VALUE)));
                setComboBox();
        }

        private void addActionListeners(JFrame theFrame) {
                generateButton.addMouseListener(new MouseListener() {
                        public void mouseClicked(MouseEvent e) {
                                if (fromPlaceholder.getValue() || toPlaceholder.getValue()) {
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
                                                        new reportGeneratorFactory().createReportGenerator("SALES")
                                                                        .generateReport(
                                                                                        fromTextField.getText(),
                                                                                        toTextField.getText(),
                                                                                        chooser.getSelectedFile()
                                                                                                        .getAbsolutePath());
                                                        successLabel.setText("Report successfully generated");
                                                        successLabel.setVisible(true);
                                                } catch (Exception e1) {
                                                        successLabel.setText(
                                                                        "Something went wrong while generating the report");
                                                        successLabel.setVisible(true);
                                                }
                                        } else if (((String) (reportsComboBox.getSelectedItem()))
                                                        .equals("Expenses Report")) {
                                                try {
                                                        new reportGeneratorFactory().createReportGenerator("EXPENSES")
                                                                        .generateReport(
                                                                                        fromTextField.getText(),
                                                                                        toTextField.getText(),
                                                                                        chooser.getSelectedFile()
                                                                                                        .getAbsolutePath());
                                                        successLabel.setText("Report successfully generated");
                                                        successLabel.setVisible(true);
                                                } catch (Exception e1) {
                                                        successLabel.setText(
                                                                        "Something went wrong while generating the report");
                                                        successLabel.setVisible(true);
                                                }
                                        } else if (((String) (reportsComboBox.getSelectedItem()))
                                                        .equals("General Report")) {
                                                try {
                                                        new reportGeneratorFactory().createReportGenerator("GENERAL")
                                                                        .generateReport(
                                                                                        fromTextField.getText(),
                                                                                        toTextField.getText(),
                                                                                        chooser.getSelectedFile()
                                                                                                        .getAbsolutePath());
                                                        successLabel.setText("Report successfully generated");
                                                        successLabel.setVisible(true);
                                                } catch (Exception e1) {
                                                        successLabel.setText(
                                                                        "Something went wrong while generating the report");
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
                applyGenericListeners();
        }

        private void applyGenericListeners() {
                iTextFieldListener inputListener = new editTextFieldFListener();
                inputListener.applyListenerTextField(fromTextField, "DD-MM-YYYY", fromPlaceholder);
                inputListener.applyListenerTextField(toTextField, "DD-MM-YYYY", toPlaceholder);

                iFormatter dateFormatter = new inputFormatterFactory().createInputFormatter("DATE");
                dateFormatter.applyFormat(fromTextField);
                dateFormatter.applyFormat(toTextField);

        }

        private void setComboBox() {
                reportsComboBox.setModel(
                                new DefaultComboBoxModel<String>(
                                                new String[] { "Sales Report", "Expenses Report", "General Report" }));
                reportsComboBox.setFont(new Font("Segoe UI", 0, 18));
                reportsComboBox.setForeground(Color.BLACK);
                reportsComboBox.setBackground(Color.WHITE);
        }

}
