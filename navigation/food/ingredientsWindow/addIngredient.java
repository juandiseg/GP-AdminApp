package navigation.food.ingredientsWindow;

import java.awt.event.*;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Stack;

import javax.swing.*;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;

import componentsFood.allergen;
import componentsFood.provider;
import navigation.food.allergensWindow.allergensAPI;
import navigation.food.providersWindow.providerAPI;
import util.inputFormatting.inputFormatterFactory;

import java.awt.*;

public class addIngredient {

        private JLabel theIngredientLabel = new JLabel();

        private JLabel nameLabel = new JLabel();
        private JLabel priceLabel = new JLabel();
        private JLabel quantityLabel = new JLabel();
        private JLabel providerLabel = new JLabel();
        private JLabel inventoryLabel = new JLabel();
        private JLabel allergenLabel = new JLabel();

        private JTextField nameTextField = new JTextField();
        private JTextField priceTextField = new JTextField();
        private JTextField quantityTextField = new JTextField();
        private boolean namePlaceholder = true;
        private boolean pricePlaceholder = true;
        private boolean quantityPlaceholder = true;
        private JComboBox<String> providerComboBox = new JComboBox<String>();
        private JToggleButton inventoryToggle = new JToggleButton();

        private JTable tableAllergens = new JTable();
        private JTable tableSelected = new JTable();
        private DefaultTableModel modelAllergens;
        private DefaultTableModel modelSelected;

        private JButton selectButton = new JButton();
        private JButton unselectButton = new JButton();

        private JScrollPane selectedJScrollPane = new JScrollPane();
        private JScrollPane unselectedJScrollPane = new JScrollPane();

        private JLabel successLabel = new JLabel();

        private JButton addIngredientButton = new JButton();
        private JButton backButton = new JButton();

        private JPanel jPanel1 = new JPanel();
        private JPanel jPanel2 = new JPanel();
        private JPanel jPanel3 = new JPanel();

        private ArrayList<provider> providers = new providerAPI().getAllActiveProviders();

        public addIngredient(JPanel playground) {
                initComponents(playground);
                addActionListeners(playground);
        }

        private void initComponents(JPanel playground) {
                playground.setBackground(new Color(255, 255, 255));

                successLabel.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
                successLabel.setHorizontalAlignment(SwingConstants.RIGHT);
                successLabel.setText("                                             ");

                successLabel.setVerticalAlignment(SwingConstants.BOTTOM);

                jPanel1.setBackground(new Color(120, 168, 252));
                jPanel1.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
                jPanel1.setDebugGraphicsOptions(javax.swing.DebugGraphics.NONE_OPTION);

                nameLabel.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
                nameLabel.setHorizontalAlignment(SwingConstants.LEFT);
                nameLabel.setText("Name");
                nameLabel.setVerticalAlignment(SwingConstants.BOTTOM);

                nameTextField.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
                nameTextField.setText("Ex. \"Ground Beef\"");
                nameTextField.setForeground(Color.GRAY);

                jPanel2.setBackground(new Color(0, 0, 0));

                GroupLayout jPanel2Layout = new GroupLayout(jPanel2);
                jPanel2.setLayout(jPanel2Layout);
                jPanel2Layout.setHorizontalGroup(
                                jPanel2Layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                                                .addGap(0, 8, Short.MAX_VALUE));
                jPanel2Layout.setVerticalGroup(
                                jPanel2Layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                                                .addGap(0, 0, Short.MAX_VALUE));

                priceLabel.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
                priceLabel.setHorizontalAlignment(SwingConstants.LEFT);
                priceLabel.setText("Price");
                priceLabel.setVerticalAlignment(SwingConstants.BOTTOM);

                selectButton.setText("Select");
                selectButton.setBackground(new Color(23, 35, 51));
                selectButton.setForeground(new Color(255, 255, 255));

                unselectButton.setText("Unselect");
                unselectButton.setBackground(new Color(23, 35, 51));
                unselectButton.setForeground(new Color(255, 255, 255));

                priceTextField.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
                priceTextField.setText("Ex. \"9.99\"");
                priceTextField.setForeground(Color.GRAY);

                quantityLabel.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
                quantityLabel.setHorizontalAlignment(SwingConstants.LEFT);
                quantityLabel.setText("Quantity");
                quantityLabel.setVerticalAlignment(SwingConstants.BOTTOM);

                quantityTextField.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
                quantityTextField.setText("Ex. \"1\"");
                quantityTextField.setForeground(Color.GRAY);

                inventoryLabel.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
                inventoryLabel.setHorizontalAlignment(SwingConstants.LEFT);
                inventoryLabel.setText("Inventory");
                inventoryLabel.setVerticalAlignment(SwingConstants.BOTTOM);

                providerLabel.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
                providerLabel.setHorizontalAlignment(SwingConstants.LEFT);
                providerLabel.setText("Provider");
                providerLabel.setVerticalAlignment(SwingConstants.BOTTOM);

                setComboBox();
                setTables();
                selectedJScrollPane.getViewport().setBackground(new Color(245, 245, 245));
                selectedJScrollPane.setBackground(new Color(245, 245, 245));
                unselectedJScrollPane.getViewport().setBackground(new Color(245, 245, 245));
                unselectedJScrollPane.setBackground(new Color(245, 245, 245));

                inventoryToggle.setText("Yes");
                inventoryToggle.setBackground(new Color(255, 255, 255));
                inventoryToggle.setForeground(new Color(23, 35, 51));

                allergenLabel.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
                allergenLabel.setHorizontalAlignment(SwingConstants.LEFT);
                allergenLabel.setText("Select Allergens");
                allergenLabel.setVerticalAlignment(SwingConstants.BOTTOM);

                addIngredientButton.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
                addIngredientButton.setText("Add Ingredient");
                addIngredientButton.setBackground(new Color(255, 255, 255));
                addIngredientButton.setForeground(new Color(23, 35, 51));

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
                                                                                                .addGap(35, 35, 35)
                                                                                                .addGroup(jPanel1Layout
                                                                                                                .createParallelGroup(
                                                                                                                                GroupLayout.Alignment.TRAILING)
                                                                                                                .addGroup(jPanel1Layout
                                                                                                                                .createParallelGroup(
                                                                                                                                                GroupLayout.Alignment.LEADING,
                                                                                                                                                false)
                                                                                                                                .addComponent(nameLabel,
                                                                                                                                                GroupLayout.DEFAULT_SIZE,
                                                                                                                                                GroupLayout.DEFAULT_SIZE,
                                                                                                                                                Short.MAX_VALUE)
                                                                                                                                .addComponent(priceLabel,
                                                                                                                                                GroupLayout.DEFAULT_SIZE,
                                                                                                                                                GroupLayout.DEFAULT_SIZE,
                                                                                                                                                Short.MAX_VALUE)
                                                                                                                                .addComponent(providerLabel,
                                                                                                                                                GroupLayout.Alignment.TRAILING,
                                                                                                                                                GroupLayout.PREFERRED_SIZE,
                                                                                                                                                83,
                                                                                                                                                GroupLayout.PREFERRED_SIZE))
                                                                                                                .addComponent(allergenLabel))
                                                                                                .addPreferredGap(
                                                                                                                ComponentPlacement.RELATED)
                                                                                                .addGroup(jPanel1Layout
                                                                                                                .createParallelGroup(
                                                                                                                                GroupLayout.Alignment.LEADING,
                                                                                                                                false)
                                                                                                                .addComponent(nameTextField,
                                                                                                                                GroupLayout.PREFERRED_SIZE,
                                                                                                                                497,
                                                                                                                                GroupLayout.PREFERRED_SIZE)
                                                                                                                .addGroup(jPanel1Layout
                                                                                                                                .createSequentialGroup()
                                                                                                                                .addGroup(jPanel1Layout
                                                                                                                                                .createParallelGroup(
                                                                                                                                                                GroupLayout.Alignment.TRAILING,
                                                                                                                                                                false)
                                                                                                                                                .addComponent(providerComboBox,
                                                                                                                                                                0,
                                                                                                                                                                GroupLayout.DEFAULT_SIZE,
                                                                                                                                                                Short.MAX_VALUE)
                                                                                                                                                .addComponent(priceTextField,
                                                                                                                                                                GroupLayout.PREFERRED_SIZE,
                                                                                                                                                                195,
                                                                                                                                                                GroupLayout.PREFERRED_SIZE))
                                                                                                                                .addGroup(jPanel1Layout
                                                                                                                                                .createParallelGroup(
                                                                                                                                                                GroupLayout.Alignment.LEADING)
                                                                                                                                                .addGroup(jPanel1Layout
                                                                                                                                                                .createSequentialGroup()
                                                                                                                                                                .addGap(12, 12, 12)
                                                                                                                                                                .addComponent(inventoryLabel,
                                                                                                                                                                                GroupLayout.PREFERRED_SIZE,
                                                                                                                                                                                83,
                                                                                                                                                                                GroupLayout.PREFERRED_SIZE))
                                                                                                                                                .addGroup(jPanel1Layout
                                                                                                                                                                .createSequentialGroup()
                                                                                                                                                                .addPreferredGap(
                                                                                                                                                                                ComponentPlacement.UNRELATED)
                                                                                                                                                                .addComponent(quantityLabel,
                                                                                                                                                                                GroupLayout.PREFERRED_SIZE,
                                                                                                                                                                                83,
                                                                                                                                                                                GroupLayout.PREFERRED_SIZE)))
                                                                                                                                .addPreferredGap(
                                                                                                                                                ComponentPlacement.UNRELATED)
                                                                                                                                .addGroup(jPanel1Layout
                                                                                                                                                .createParallelGroup(
                                                                                                                                                                GroupLayout.Alignment.LEADING)
                                                                                                                                                .addComponent(quantityTextField)
                                                                                                                                                .addComponent(inventoryToggle,
                                                                                                                                                                GroupLayout.DEFAULT_SIZE,
                                                                                                                                                                GroupLayout.DEFAULT_SIZE,
                                                                                                                                                                Short.MAX_VALUE)))
                                                                                                                .addGroup(jPanel1Layout
                                                                                                                                .createSequentialGroup()
                                                                                                                                .addComponent(unselectedJScrollPane,
                                                                                                                                                GroupLayout.PREFERRED_SIZE,
                                                                                                                                                233,
                                                                                                                                                GroupLayout.PREFERRED_SIZE)
                                                                                                                                .addPreferredGap(
                                                                                                                                                ComponentPlacement.RELATED,
                                                                                                                                                GroupLayout.DEFAULT_SIZE,
                                                                                                                                                Short.MAX_VALUE)
                                                                                                                                .addComponent(selectedJScrollPane,
                                                                                                                                                GroupLayout.PREFERRED_SIZE,
                                                                                                                                                233,
                                                                                                                                                GroupLayout.PREFERRED_SIZE))
                                                                                                                .addGroup(GroupLayout.Alignment.TRAILING,
                                                                                                                                jPanel1Layout.createSequentialGroup()
                                                                                                                                                .addComponent(selectButton,
                                                                                                                                                                GroupLayout.PREFERRED_SIZE,
                                                                                                                                                                75,
                                                                                                                                                                GroupLayout.PREFERRED_SIZE)
                                                                                                                                                .addPreferredGap(
                                                                                                                                                                ComponentPlacement.RELATED,
                                                                                                                                                                GroupLayout.DEFAULT_SIZE,
                                                                                                                                                                Short.MAX_VALUE)
                                                                                                                                                .addComponent(unselectButton))))
                                                                                .addGroup(jPanel1Layout
                                                                                                .createSequentialGroup()
                                                                                                .addGap(318, 318, 318)
                                                                                                .addComponent(addIngredientButton,
                                                                                                                GroupLayout.PREFERRED_SIZE,
                                                                                                                200,
                                                                                                                GroupLayout.PREFERRED_SIZE)))
                                                                .addContainerGap(188, Short.MAX_VALUE)));
                jPanel1Layout.setVerticalGroup(
                                jPanel1Layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                                                .addComponent(jPanel2, GroupLayout.DEFAULT_SIZE,
                                                                GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                                .addGroup(jPanel1Layout.createSequentialGroup()
                                                                .addGap(16, 16, 16)
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
                                                                                GroupLayout.Alignment.LEADING)
                                                                                .addComponent(quantityTextField,
                                                                                                GroupLayout.PREFERRED_SIZE,
                                                                                                35,
                                                                                                GroupLayout.PREFERRED_SIZE)
                                                                                .addGroup(jPanel1Layout
                                                                                                .createParallelGroup(
                                                                                                                GroupLayout.Alignment.BASELINE)
                                                                                                .addComponent(priceTextField,
                                                                                                                GroupLayout.PREFERRED_SIZE,
                                                                                                                35,
                                                                                                                GroupLayout.PREFERRED_SIZE)
                                                                                                .addComponent(priceLabel)
                                                                                                .addComponent(quantityLabel)))
                                                                .addPreferredGap(
                                                                                ComponentPlacement.RELATED)
                                                                .addGroup(jPanel1Layout.createParallelGroup(
                                                                                GroupLayout.Alignment.LEADING)
                                                                                .addComponent(providerComboBox,
                                                                                                GroupLayout.PREFERRED_SIZE,
                                                                                                36,
                                                                                                GroupLayout.PREFERRED_SIZE)
                                                                                .addComponent(inventoryToggle,
                                                                                                GroupLayout.PREFERRED_SIZE,
                                                                                                33,
                                                                                                GroupLayout.PREFERRED_SIZE)
                                                                                .addGroup(jPanel1Layout
                                                                                                .createParallelGroup(
                                                                                                                GroupLayout.Alignment.BASELINE)
                                                                                                .addComponent(inventoryLabel)
                                                                                                .addComponent(providerLabel)))
                                                                .addPreferredGap(
                                                                                ComponentPlacement.RELATED)
                                                                .addGroup(jPanel1Layout.createParallelGroup(
                                                                                GroupLayout.Alignment.LEADING)
                                                                                .addComponent(allergenLabel)
                                                                                .addGroup(jPanel1Layout
                                                                                                .createSequentialGroup()
                                                                                                .addGroup(jPanel1Layout
                                                                                                                .createParallelGroup(
                                                                                                                                GroupLayout.Alignment.LEADING)
                                                                                                                .addComponent(unselectedJScrollPane,
                                                                                                                                GroupLayout.PREFERRED_SIZE,
                                                                                                                                210,
                                                                                                                                GroupLayout.PREFERRED_SIZE)
                                                                                                                .addComponent(selectedJScrollPane,
                                                                                                                                GroupLayout.PREFERRED_SIZE,
                                                                                                                                210,
                                                                                                                                GroupLayout.PREFERRED_SIZE))
                                                                                                .addPreferredGap(
                                                                                                                ComponentPlacement.RELATED,
                                                                                                                GroupLayout.DEFAULT_SIZE,
                                                                                                                Short.MAX_VALUE)
                                                                                                .addGroup(jPanel1Layout
                                                                                                                .createParallelGroup(
                                                                                                                                GroupLayout.Alignment.BASELINE)
                                                                                                                .addComponent(selectButton)
                                                                                                                .addComponent(unselectButton))
                                                                                                .addPreferredGap(
                                                                                                                ComponentPlacement.RELATED)
                                                                                                .addComponent(addIngredientButton,
                                                                                                                GroupLayout.PREFERRED_SIZE,
                                                                                                                55,
                                                                                                                GroupLayout.PREFERRED_SIZE)))
                                                                .addGap(17, 17, 17)));

                jPanel3.setBackground(new Color(71, 120, 197));

                GroupLayout jPanel3Layout = new GroupLayout(jPanel3);
                jPanel3.setLayout(jPanel3Layout);
                jPanel3Layout.setHorizontalGroup(
                                jPanel3Layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                                                .addGap(0, 500, Short.MAX_VALUE));
                jPanel3Layout.setVerticalGroup(
                                jPanel3Layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                                                .addGap(0, 5, Short.MAX_VALUE));

                theIngredientLabel.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
                theIngredientLabel.setHorizontalAlignment(SwingConstants.CENTER);
                theIngredientLabel.setText("Add Ingredient");
                theIngredientLabel.setToolTipText("");
                theIngredientLabel.setVerticalAlignment(SwingConstants.BOTTOM);

                backButton.setBackground(new Color(71, 120, 197));
                backButton.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
                backButton.setForeground(new Color(255, 255, 255));
                backButton.setText("Back");

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
                                                                .addGap(0, 0, Short.MAX_VALUE))
                                                .addComponent(theIngredientLabel,
                                                                GroupLayout.Alignment.TRAILING,
                                                                GroupLayout.DEFAULT_SIZE,
                                                                GroupLayout.DEFAULT_SIZE,
                                                                Short.MAX_VALUE)
                                                .addGroup(GroupLayout.Alignment.TRAILING, playgroundLayout
                                                                .createSequentialGroup()
                                                                .addContainerGap(GroupLayout.DEFAULT_SIZE,
                                                                                Short.MAX_VALUE)
                                                                .addComponent(backButton,
                                                                                GroupLayout.PREFERRED_SIZE,
                                                                                200,
                                                                                GroupLayout.PREFERRED_SIZE)
                                                                .addPreferredGap(
                                                                                ComponentPlacement.RELATED)
                                                                .addComponent(successLabel,
                                                                                GroupLayout.PREFERRED_SIZE,
                                                                                640,
                                                                                GroupLayout.PREFERRED_SIZE)
                                                                .addContainerGap()));
                playgroundLayout.setVerticalGroup(
                                playgroundLayout.createParallelGroup(GroupLayout.Alignment.LEADING)
                                                .addGroup(GroupLayout.Alignment.TRAILING, playgroundLayout
                                                                .createSequentialGroup()
                                                                .addGap(39, 39, 39)
                                                                .addComponent(theIngredientLabel)
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
                                                                .addGap(18, 18, 18)
                                                                .addGroup(
                                                                                playgroundLayout.createParallelGroup(
                                                                                                GroupLayout.Alignment.LEADING)
                                                                                                .addComponent(successLabel)
                                                                                                .addComponent(backButton,
                                                                                                                GroupLayout.PREFERRED_SIZE,
                                                                                                                55,
                                                                                                                GroupLayout.PREFERRED_SIZE))
                                                                .addContainerGap(7, Short.MAX_VALUE)));

        }

        private void setTables() {
                tableAllergens = new JTable();
                tableSelected = new JTable();
                modelAllergens = new DefaultTableModel(
                                new String[] { "id", "Name" }, 0);
                modelSelected = new DefaultTableModel(
                                new String[] { "id", "Name" }, 0);
                for (allergen tempAller : new allergensAPI().getAllAllergens()) {
                        String id = Integer.toString(tempAller.getId());
                        String name = tempAller.getName();
                        modelAllergens.addRow(new String[] { id, name });
                }
                tableAllergens.setModel(modelAllergens);
                tableSelected.setModel(modelSelected);
                tableAllergens.removeColumn(tableAllergens.getColumn("id"));
                tableSelected.removeColumn(tableSelected.getColumn("id"));
                unselectedJScrollPane.setViewportView(tableAllergens);
                selectedJScrollPane.setViewportView(tableSelected);
                tableAllergens.setDefaultEditor(Object.class, null);
                tableSelected.setDefaultEditor(Object.class, null);
                tableLookPretty(tableAllergens);
                tableLookPretty(tableSelected);
        }

        private void tableLookPretty(JTable theTable) {
                theTable.setDefaultEditor(Object.class, null);
                theTable.setFocusable(true);
                theTable.getTableHeader().setFont(new java.awt.Font("Segoe UI", 1, 9));
                theTable.getTableHeader().setBackground(new Color(120, 168, 252));
                theTable.setFillsViewportHeight(true);
                theTable.setFont(new java.awt.Font("Segoe UI", 0, 9));
                DefaultTableCellRenderer centerRenderer = new DefaultTableCellRenderer();
                centerRenderer.setHorizontalAlignment(JLabel.CENTER);
                theTable.getColumnModel().getColumn(0).setCellRenderer(centerRenderer);
                Dimension temp = new Dimension(20, 1);
                theTable.setIntercellSpacing(temp);
                theTable.setRowHeight(theTable.getRowHeight() + 10);
        }

        private void setComboBox() {
                ArrayList<String> tempNames = new ArrayList<String>();
                for (provider temp : providers)
                        tempNames.add(temp.getName());
                String[] namesArr = tempNames.toArray(new String[0]);
                providerComboBox.setModel(new DefaultComboBoxModel<String>(namesArr));
                providerComboBox.setFont(new java.awt.Font("Segoe UI", 0, 18));
                providerComboBox.setFont(new Font("Segoe UI", 0, 18));
                providerComboBox.setForeground(Color.BLACK);
                providerComboBox.setBackground(Color.WHITE);
        }

        private void addActionListeners(JPanel playground) {
                backButton.addMouseListener(new MouseListener() {
                        public void mouseClicked(MouseEvent e) {
                                playground.removeAll();
                                new mainIngredients(playground);
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
                addIngredientButton.addMouseListener(new MouseListener() {
                        public void mouseClicked(MouseEvent e) {
                                if (namePlaceholder || pricePlaceholder || quantityPlaceholder) {
                                        successLabel.setText("Error. You must fill all the given fields.");
                                        return;
                                }
                                String name = nameTextField.getText();
                                if (new ingredientsAPI().isNameTaken(name)) {
                                        successLabel.setText("Error. The given name is already taken.");
                                        successLabel.setVisible(true);
                                        return;
                                }
                                String price = priceTextField.getText();
                                String quantity = quantityTextField.getText();
                                int providerID = providers.get(providerComboBox.getSelectedIndex()).getId();
                                boolean inventory = inventoryToggle.getText().equals("Yes");
                                String date = LocalDate.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd"));

                                int ingredientID = new ingredientsAPI().addIngredient(providerID, date, name,
                                                Float.parseFloat(price),
                                                Float.parseFloat(quantity), inventory);
                                if (ingredientID == -1) {
                                        successLabel.setText(
                                                        "Error. Something went wrong while adding the ingredient.");
                                        return;
                                }
                                Stack<allergen> selectedAllergens = new Stack<allergen>();
                                for (int i = 0; i < modelSelected.getRowCount(); i++) {
                                        int ID = Integer.parseInt((String) modelSelected.getValueAt(i, 0));
                                        String allerName = (String) modelSelected.getValueAt(i, 1);
                                        selectedAllergens.push(new allergen(ID, allerName));
                                }
                                if (new allergensAPI().addAlergensOfIngredient(selectedAllergens, ingredientID)) {
                                        successLabel.setText("The ingredient \"" + name + "\" was added successfully.");
                                        return;
                                }
                                successLabel.setText("Error. Something went wrong when adding the \"" + name
                                                + "\"'s allergens.");
                        }

                        public void mousePressed(MouseEvent e) {
                        }

                        public void mouseReleased(MouseEvent e) {
                        }

                        public void mouseEntered(MouseEvent e) {
                                addIngredientButton.setBackground(new Color(23, 35, 51));
                                addIngredientButton.setForeground(new Color(255, 255, 255));
                        }

                        public void mouseExited(MouseEvent e) {
                                addIngredientButton.setBackground(new Color(255, 255, 255));
                                addIngredientButton.setForeground(new Color(23, 35, 51));
                        }
                });
                nameTextField.addFocusListener(new FocusListener() {
                        public void focusGained(FocusEvent e) {
                                if (nameTextField.getText().equals("Ex. \"Ground Beef\"")) {
                                        nameTextField.setText("");
                                        nameTextField.setForeground(Color.BLACK);
                                        namePlaceholder = false;
                                }
                        }

                        public void focusLost(FocusEvent e) {
                                if (nameTextField.getText().isEmpty()) {
                                        nameTextField.setForeground(Color.GRAY);
                                        nameTextField.setText("Ex. \"Ground Beef\"");
                                        namePlaceholder = true;
                                }
                        }
                });
                priceTextField.addFocusListener(new FocusListener() {
                        public void focusGained(FocusEvent e) {
                                if (priceTextField.getText().equals("Ex. \"9.99\"")) {
                                        priceTextField.setText("");
                                        priceTextField.setForeground(Color.BLACK);
                                        pricePlaceholder = false;
                                }
                        }

                        public void focusLost(FocusEvent e) {
                                if (priceTextField.getText().isEmpty()) {
                                        priceTextField.setForeground(Color.GRAY);
                                        priceTextField.setText("Ex. \"9.99\"");
                                        pricePlaceholder = true;
                                }
                        }
                });
                quantityTextField.addFocusListener(new FocusListener() {
                        public void focusGained(FocusEvent e) {
                                if (quantityTextField.getText().equals("Ex. \"1\"")) {
                                        quantityTextField.setText("");
                                        quantityTextField.setForeground(Color.BLACK);
                                        quantityPlaceholder = false;
                                }
                        }

                        public void focusLost(FocusEvent e) {
                                if (quantityTextField.getText().isEmpty()) {
                                        quantityTextField.setForeground(Color.GRAY);
                                        quantityTextField.setText("Ex. \"1\"");
                                        quantityPlaceholder = true;
                                }
                        }
                });
                inventoryToggle.addActionListener(new ActionListener() {
                        public void actionPerformed(ActionEvent e) {
                                if (inventoryToggle.getText().equals("Yes"))
                                        inventoryToggle.setText("No");
                                else
                                        inventoryToggle.setText("Yes");
                        }
                });
                unselectButton.addMouseListener(new MouseListener() {
                        public void mouseClicked(MouseEvent e) {
                                int row = tableSelected.getSelectedRow();
                                if (row == -1)
                                        return;
                                String ID = (String) modelSelected.getValueAt(row, 0);
                                String name = (String) modelSelected.getValueAt(row, 1);
                                modelSelected.removeRow(row);
                                modelAllergens.addRow(new String[] { ID, name });
                        }

                        public void mousePressed(MouseEvent e) {
                        }

                        public void mouseReleased(MouseEvent e) {
                        }

                        public void mouseEntered(MouseEvent e) {
                                unselectButton.setBackground(new Color(255, 255, 255));
                                unselectButton.setForeground(new Color(23, 35, 51));

                        }

                        public void mouseExited(MouseEvent e) {
                                unselectButton.setBackground(new Color(23, 35, 51));
                                unselectButton.setForeground(new Color(255, 255, 255));
                        }
                });
                selectButton.addMouseListener(new MouseListener() {
                        public void mouseClicked(MouseEvent e) {
                                int row = tableAllergens.getSelectedRow();
                                if (row == -1)
                                        return;
                                String ID = (String) modelAllergens.getValueAt(row, 0);
                                String name = (String) modelAllergens.getValueAt(row, 1);
                                modelAllergens.removeRow(row);
                                modelSelected.addRow(new String[] { ID, name });
                        }

                        public void mousePressed(MouseEvent e) {
                        }

                        public void mouseReleased(MouseEvent e) {
                        }

                        public void mouseEntered(MouseEvent e) {
                                selectButton.setBackground(new Color(255, 255, 255));
                                selectButton.setForeground(new Color(23, 35, 51));
                        }

                        public void mouseExited(MouseEvent e) {
                                selectButton.setBackground(new Color(23, 35, 51));
                                selectButton.setForeground(new Color(255, 255, 255));
                        }
                });
                new inputFormatterFactory().createInputFormatter("PRICE").applyFormat(priceTextField);
        }
}