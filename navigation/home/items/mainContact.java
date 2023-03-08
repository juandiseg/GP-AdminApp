package navigation.home.items;

import java.awt.Color;
import java.awt.Font;
import java.awt.datatransfer.StringSelection;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.awt.Toolkit;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import componentsFood.provider;
import util.buttonFormatters.addButtonFormatter;
import util.buttonFormatters.auxButtonFormatter;
import util.buttonFormatters.iAddButton;
import util.buttonFormatters.iAuxButton;
import util.buttonFormatters.iNavigatorButton;
import util.buttonFormatters.navigatorButtonFormatter;
import util.databaseAPIs.providerAPI;
import util.inputFormatting.iFormatter;
import util.inputFormatting.inputFormatterFactory;
import util.listenersFormatting.booleanWrapper;
import util.listenersFormatting.iTextFieldListener;
import util.listenersFormatting.edit.editDateTFFListener;
import java.awt.event.FocusListener;

public class mainContact {

    private JTextArea descriptionJTextArea = new JTextArea(5, 20);
    private JScrollPane jScrollPane1 = new JScrollPane(descriptionJTextArea, JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED,
            JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
    private JLabel successLabel = new JLabel();

    JPanel jPanel1 = new JPanel();
    JPanel jPanel5 = new JPanel();
    JLabel providerLabel = new JLabel();
    JLabel fromLabel = new JLabel();
    JLabel untilLabel = new JLabel();
    JTextField fromTextField = new JTextField();
    JTextField untilTextField = new JTextField();
    booleanWrapper fromPlaceholder = new booleanWrapper(true);
    booleanWrapper untilPlaceholder = new booleanWrapper(true);
    JTextField addressTextField = new JTextField();
    JButton generateButton = new JButton();
    JButton copyEmailButton = new JButton();
    JButton copyAddressButton = new JButton();
    JTextArea emailJTextArea = new JTextArea();
    JComboBox<provider> providerComboBox = new JComboBox<provider>();
    JPanel jPanel3 = new JPanel();
    JLabel titleLabel = new JLabel();
    JButton backButton = new JButton();

    public mainContact(JPanel playground) {
        initComponents(playground);
        addListeners(playground);
    }

    private void initComponents(JPanel playground) {
        playground.setBackground(new java.awt.Color(255, 255, 255));

        successLabel.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        successLabel.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        successLabel.setVerticalAlignment(javax.swing.SwingConstants.BOTTOM);

        jPanel1.setBackground(new java.awt.Color(255, 255, 255));

        providerLabel.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        providerLabel.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        providerLabel.setText("Provider");
        providerLabel.setVerticalAlignment(javax.swing.SwingConstants.BOTTOM);

        fromLabel.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        fromLabel.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        fromLabel.setText("From");
        fromLabel.setVerticalAlignment(javax.swing.SwingConstants.BOTTOM);

        untilLabel.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        untilLabel.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        untilLabel.setText("Until");
        untilLabel.setVerticalAlignment(javax.swing.SwingConstants.BOTTOM);

        setComboBox();

        setEmailInfo();

        generateButton.setText("Generate Email");

        jPanel5.setBackground(new java.awt.Color(71, 120, 197));

        javax.swing.GroupLayout jPanel5Layout = new javax.swing.GroupLayout(jPanel5);
        jPanel5.setLayout(jPanel5Layout);
        jPanel5Layout.setHorizontalGroup(
                jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGap(0, 494, Short.MAX_VALUE));
        jPanel5Layout.setVerticalGroup(
                jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGap(0, 2, Short.MAX_VALUE));

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
                jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(generateButton, javax.swing.GroupLayout.PREFERRED_SIZE, 220,
                                        javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(320, 320, 320))
                        .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addGroup(jPanel1Layout.createSequentialGroup()
                                                .addGap(146, 146, 146)
                                                .addGroup(jPanel1Layout
                                                        .createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                                        .addGroup(jPanel1Layout.createSequentialGroup()
                                                                .addGroup(jPanel1Layout.createParallelGroup(
                                                                        javax.swing.GroupLayout.Alignment.LEADING)
                                                                        .addGroup(jPanel1Layout.createParallelGroup(
                                                                                javax.swing.GroupLayout.Alignment.TRAILING,
                                                                                false)
                                                                                .addComponent(providerLabel,
                                                                                        javax.swing.GroupLayout.DEFAULT_SIZE,
                                                                                        85, Short.MAX_VALUE)
                                                                                .addComponent(fromLabel,
                                                                                        javax.swing.GroupLayout.DEFAULT_SIZE,
                                                                                        javax.swing.GroupLayout.DEFAULT_SIZE,
                                                                                        Short.MAX_VALUE))
                                                                        .addComponent(untilLabel,
                                                                                javax.swing.GroupLayout.PREFERRED_SIZE,
                                                                                84,
                                                                                javax.swing.GroupLayout.PREFERRED_SIZE))
                                                                .addPreferredGap(
                                                                        javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                                                .addGroup(jPanel1Layout.createParallelGroup(
                                                                        javax.swing.GroupLayout.Alignment.LEADING,
                                                                        false)
                                                                        .addComponent(fromTextField,
                                                                                javax.swing.GroupLayout.Alignment.TRAILING)
                                                                        .addComponent(untilTextField,
                                                                                javax.swing.GroupLayout.Alignment.TRAILING,
                                                                                javax.swing.GroupLayout.DEFAULT_SIZE,
                                                                                434, Short.MAX_VALUE)
                                                                        .addComponent(providerComboBox, 0,
                                                                                javax.swing.GroupLayout.DEFAULT_SIZE,
                                                                                Short.MAX_VALUE)))
                                                        .addGroup(jPanel1Layout
                                                                .createParallelGroup(
                                                                        javax.swing.GroupLayout.Alignment.LEADING,
                                                                        false)
                                                                .addComponent(jScrollPane1)
                                                                .addGroup(javax.swing.GroupLayout.Alignment.TRAILING,
                                                                        jPanel1Layout.createSequentialGroup()
                                                                                .addComponent(addressTextField,
                                                                                        javax.swing.GroupLayout.PREFERRED_SIZE,
                                                                                        293,
                                                                                        javax.swing.GroupLayout.PREFERRED_SIZE)
                                                                                .addPreferredGap(
                                                                                        javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                                                                .addComponent(copyAddressButton,
                                                                                        javax.swing.GroupLayout.DEFAULT_SIZE,
                                                                                        javax.swing.GroupLayout.DEFAULT_SIZE,
                                                                                        Short.MAX_VALUE)))
                                                        .addComponent(copyEmailButton)))
                                        .addGroup(jPanel1Layout.createSequentialGroup()
                                                .addGap(186, 186, 186)
                                                .addComponent(jPanel5, javax.swing.GroupLayout.PREFERRED_SIZE,
                                                        javax.swing.GroupLayout.DEFAULT_SIZE,
                                                        javax.swing.GroupLayout.PREFERRED_SIZE)))
                                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)));
        jPanel1Layout.setVerticalGroup(
                jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                                .addContainerGap()
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                        .addComponent(providerLabel)
                                        .addComponent(providerComboBox, javax.swing.GroupLayout.PREFERRED_SIZE, 35,
                                                javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                        .addComponent(fromTextField, javax.swing.GroupLayout.PREFERRED_SIZE, 35,
                                                javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addComponent(fromLabel))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                        .addComponent(untilLabel)
                                        .addComponent(untilTextField, javax.swing.GroupLayout.PREFERRED_SIZE, 35,
                                                javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(generateButton, javax.swing.GroupLayout.PREFERRED_SIZE, 22,
                                        javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 9, Short.MAX_VALUE)
                                .addComponent(jPanel5, javax.swing.GroupLayout.PREFERRED_SIZE,
                                        javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                        .addComponent(copyAddressButton, javax.swing.GroupLayout.PREFERRED_SIZE, 22,
                                                javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addComponent(addressTextField, javax.swing.GroupLayout.PREFERRED_SIZE,
                                                javax.swing.GroupLayout.DEFAULT_SIZE,
                                                javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 238,
                                        javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(copyEmailButton, javax.swing.GroupLayout.PREFERRED_SIZE, 22,
                                        javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addContainerGap()));

        jPanel3.setBackground(new java.awt.Color(71, 120, 197));

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
                jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGap(0, 500, Short.MAX_VALUE));
        jPanel3Layout.setVerticalGroup(
                jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGap(0, 5, Short.MAX_VALUE));

        titleLabel.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        titleLabel.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        titleLabel.setText("Generate Provider Emails");
        titleLabel.setVerticalAlignment(javax.swing.SwingConstants.BOTTOM);

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
                                        .addComponent(titleLabel, javax.swing.GroupLayout.DEFAULT_SIZE,
                                                javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                        .addGroup(playgroundLayout.createSequentialGroup()
                                                .addGap(218, 218, 218)
                                                .addComponent(successLabel, javax.swing.GroupLayout.DEFAULT_SIZE,
                                                        javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                                .addContainerGap()));
        playgroundLayout.setVerticalGroup(
                playgroundLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, playgroundLayout.createSequentialGroup()
                                .addContainerGap()
                                .addComponent(titleLabel)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE,
                                        javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE,
                                        javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED,
                                        javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(successLabel)
                                .addGap(43, 43, 43)));
    }

    private void setEmailInfo() {
        addressTextField.setFont(new Font("Segoe UI", 0, 18));
        addressTextField.setText("");
        for (FocusListener temp : addressTextField.getFocusListeners()) {
            addressTextField.removeFocusListener(temp);
        }
        emailJTextArea.setText("");
        for (FocusListener temp : emailJTextArea.getFocusListeners()) {
            emailJTextArea.removeFocusListener(temp);
        }
        emailJTextArea.setColumns(20);
        emailJTextArea.setRows(5);
        jScrollPane1.setViewportView(emailJTextArea);
    }

    private void addListeners(JPanel playground) {
        backButton(playground);
        copyClipboards();
        generateButton();
        applyGenericListeners();
    }

    private void setComboBox() {
        ArrayList<provider> tempList = providerAPI.getAllCurrentProviders();
        provider[] providerArr = tempList.toArray(new provider[0]);

        providerComboBox.setModel(new DefaultComboBoxModel<provider>(providerArr));
        providerComboBox.setFont(new java.awt.Font("Segoe UI", 0, 18));
        providerComboBox.setFont(new Font("Segoe UI", 0, 18));
        providerComboBox.setForeground(Color.BLACK);
        providerComboBox.setBackground(Color.WHITE);
    }

    private void backButton(JPanel playground) {
        class backMethodHolder extends iNavigatorButton {
            public void createNewNavigator() {
                // new homeDash();
            }
        }
        navigatorButtonFormatter.formatNavigationButton(backButton, new backMethodHolder(), playground, true,
                "Back");
    }

    private void generateButton() {
        class addMethodsHolder extends iAddButton {

            public boolean valuesArePlaceholders() {
                if (fromPlaceholder.getValue() && untilPlaceholder.getValue()) {
                    successLabel.setText("Error. You must specify all fields.");
                    successLabel.setVisible(true);
                    return true;
                }
                return false;
            }

            public boolean areInputsInvalid() {
                String from = fromTextField.getText();
                String until = untilTextField.getText();

                // Check for correct date input.
                try {
                    LocalDate.parse(from, DateTimeFormatter.ofPattern("dd-MM-yyyy"));
                    LocalDate.parse(until, DateTimeFormatter.ofPattern("dd-MM-yyyy"));
                } catch (Exception DateTimeParseException) {
                    successLabel.setText("ERROR. The given date is unvalid.");
                    successLabel.setVisible(true);
                    return true;
                }

                // If all checks are passed, input should be valid.
                return false;
            }

            public boolean addFoodComponent() {
                boolean successfulUpdate = true;
                addressTextField.setText(((provider) providerComboBox.getSelectedItem()).getEmail());
                // GENERATE EMAIL
                if (!successfulUpdate)
                    successLabel.setText("Something went wrong while generating the email.");
                return false;
            }

            public void extraSteps() {
            }

        }
        addButtonFormatter.formatAddButton(generateButton, new addMethodsHolder());
    }

    private void applyGenericListeners() {
        iFormatter dateFormatter = new inputFormatterFactory().createInputFormatter("DATE");
        dateFormatter.applyFormat(fromTextField);
        dateFormatter.applyFormat(untilTextField);

        iTextFieldListener textListener = new editDateTFFListener();
        textListener.applyListenerTextField(fromTextField, "DD-MM-YYYY", fromPlaceholder, false);
        textListener.applyListenerTextField(untilTextField, "DD-MM-YYYY", untilPlaceholder, false);
    }

    private void copyClipboards() {
        class copyAddressMethodHolder implements iAuxButton {
            public void action() {
                Toolkit.getDefaultToolkit().getSystemClipboard()
                        .setContents(new StringSelection(addressTextField.getText()), null);
            }
        }
        auxButtonFormatter.formatAuxButton(copyAddressButton, new copyAddressMethodHolder(), "Copy to Clipboard");
        class copyEmailMethodHolder implements iAuxButton {
            public void action() {
                Toolkit.getDefaultToolkit().getSystemClipboard()
                        .setContents(new StringSelection(emailJTextArea.getText()), null);
            }
        }
        auxButtonFormatter.formatAuxButton(copyEmailButton, new copyEmailMethodHolder(), "Copy to Clipboard");
    }

}