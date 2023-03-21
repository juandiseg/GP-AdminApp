package navigation.food.ingredientsNav;

import java.util.ArrayList;
import java.util.Stack;

import javax.swing.*;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;

import componentsFood.allergen;
import componentsFood.provider;
import util.buttonFormatters.*;
import util.databaseAPIs.allergensAPI;
import util.databaseAPIs.ingredientsAPI;
import util.databaseAPIs.providerAPI;
import util.inputFormatting.iFormatter;
import util.inputFormatting.inputFormatterFactory;
import util.listenersFormatting.booleanWrapper;
import util.listenersFormatting.iTextFieldListener;
import util.listenersFormatting.add.addJToggleAListener;
import util.listenersFormatting.add.addTextFieldFListener;

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
        private booleanWrapper namePlaceholder = new booleanWrapper(true);
        private booleanWrapper pricePlaceholder = new booleanWrapper(true);
        private booleanWrapper quantityPlaceholder = new booleanWrapper(true);
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

        private ArrayList<provider> providers = providerAPI.getAllCurrentProviders();

        public addIngredient(JPanel playground) {
                initComponents(playground);
                addListeners(playground);
        }

        private void initComponents(JPanel playground) {
                playground.setBackground(new Color(255, 255, 255));

                successLabel.setFont(new Font("Segoe UI", 0, 18)); // NOI18N
                successLabel.setHorizontalAlignment(SwingConstants.RIGHT);
                successLabel.setText("                                             ");

                successLabel.setVerticalAlignment(SwingConstants.BOTTOM);

                jPanel1.setBackground(new Color(120, 168, 252));
                jPanel1.setCursor(new Cursor(java.awt.Cursor.DEFAULT_CURSOR));
                jPanel1.setDebugGraphicsOptions(javax.swing.DebugGraphics.NONE_OPTION);

                nameLabel.setFont(new Font("Segoe UI", 1, 18)); // NOI18N
                nameLabel.setHorizontalAlignment(SwingConstants.LEFT);
                nameLabel.setText("Name");
                nameLabel.setVerticalAlignment(SwingConstants.BOTTOM);

                jPanel2.setBackground(new Color(0, 0, 0));

                GroupLayout jPanel2Layout = new GroupLayout(jPanel2);
                jPanel2.setLayout(jPanel2Layout);
                jPanel2Layout.setHorizontalGroup(
                                jPanel2Layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                                                .addGap(0, 8, Short.MAX_VALUE));
                jPanel2Layout.setVerticalGroup(
                                jPanel2Layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                                                .addGap(0, 0, Short.MAX_VALUE));

                priceLabel.setFont(new Font("Segoe UI", 1, 18)); // NOI18N
                priceLabel.setHorizontalAlignment(SwingConstants.LEFT);
                priceLabel.setText("Price");
                priceLabel.setVerticalAlignment(SwingConstants.BOTTOM);

                quantityLabel.setFont(new Font("Segoe UI", 1, 18)); // NOI18N
                quantityLabel.setHorizontalAlignment(SwingConstants.LEFT);
                quantityLabel.setText("Quantity");
                quantityLabel.setVerticalAlignment(SwingConstants.BOTTOM);

                inventoryLabel.setFont(new Font("Segoe UI", 1, 18)); // NOI18N
                inventoryLabel.setHorizontalAlignment(SwingConstants.LEFT);
                inventoryLabel.setText("Inventory");
                inventoryLabel.setVerticalAlignment(SwingConstants.BOTTOM);

                providerLabel.setFont(new Font("Segoe UI", 1, 18)); // NOI18N
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

                allergenLabel.setFont(new Font("Segoe UI", 1, 18)); // NOI18N
                allergenLabel.setHorizontalAlignment(SwingConstants.LEFT);
                allergenLabel.setText("Select Allergens");
                allergenLabel.setVerticalAlignment(SwingConstants.BOTTOM);

                addIngredientButton.setText("Add Ingredient");

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

                theIngredientLabel.setFont(new Font("Segoe UI", 1, 18)); // NOI18N
                theIngredientLabel.setHorizontalAlignment(SwingConstants.CENTER);
                theIngredientLabel.setText("Add Ingredient");
                theIngredientLabel.setToolTipText("");
                theIngredientLabel.setVerticalAlignment(SwingConstants.BOTTOM);

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
                for (allergen tempAller : allergensAPI.getAllAllergens()) {
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
                theTable.getTableHeader().setFont(new Font("Segoe UI", 1, 9));
                theTable.getTableHeader().setBackground(new Color(120, 168, 252));
                theTable.setFillsViewportHeight(true);
                theTable.setFont(new Font("Segoe UI", 0, 9));
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
                providerComboBox.setFont(new Font("Segoe UI", 0, 18));
                providerComboBox.setFont(new Font("Segoe UI", 0, 18));
                providerComboBox.setForeground(Color.BLACK);
                providerComboBox.setBackground(Color.WHITE);
        }

        private Stack<Integer> getSelectedAllergenIDs() {
                Stack<Integer> selectedAllergens = new Stack<Integer>();
                for (int i = 0; i < modelSelected.getRowCount(); i++)
                        selectedAllergens.push(Integer.parseInt((String) modelSelected.getValueAt(i, 0)));
                return selectedAllergens;
        }

        private void addListeners(JPanel playground) {
                selectionButtons();
                backButton(playground);
                addButton(null);
                applyGenericListeners();
        }

        private void selectionButtons() {
                class selectMethodHolder implements iSelectionButton {
                        public void doSelection() {
                                int row = tableAllergens.getSelectedRow();
                                if (row == -1)
                                        return;
                                String ID = (String) modelAllergens.getValueAt(row, 0);
                                String name = (String) modelAllergens.getValueAt(row, 1);
                                modelAllergens.removeRow(row);
                                modelSelected.addRow(new String[] { ID, name });
                        }
                }
                selectionButtonFormatter.formatSelectionButton(selectButton, new selectMethodHolder(), true);
                class unselectMethodHolder implements iSelectionButton {
                        public void doSelection() {
                                int row = tableSelected.getSelectedRow();
                                if (row == -1)
                                        return;
                                String ID = (String) modelSelected.getValueAt(row, 0);
                                String name = (String) modelSelected.getValueAt(row, 1);
                                modelSelected.removeRow(row);
                                modelAllergens.addRow(new String[] { ID, name });
                        }
                }
                selectionButtonFormatter.formatSelectionButton(unselectButton, new unselectMethodHolder(), false);
        }

        private void backButton(JPanel playground) {
                class backMethodHolder extends iNavigatorButton {
                        public void createNewNavigator() {
                                new mainIngredients(playground);
                        }
                }
                navigatorButtonFormatter.formatNavigationButton(backButton, new backMethodHolder(), playground, true,
                                "Back");
        }

        private void addButton(JPanel playground) {
                class addMethodsHolder extends iAddButton {
                        public boolean valuesArePlaceholders() {
                                boolean arePlaceholders = (namePlaceholder.getValue() || pricePlaceholder.getValue()
                                                || quantityPlaceholder.getValue());
                                if (arePlaceholders) {
                                        successLabel.setText("Error. You must fill all the given fields.");
                                        successLabel.setVisible(true);
                                }
                                return arePlaceholders;
                        }

                        public boolean areInputsInvalid() {
                                boolean error = ingredientsAPI.isNameTaken(nameTextField.getText());
                                if (error) {
                                        successLabel.setText("Error. The given name is already in use.");
                                        successLabel.setVisible(true);
                                }
                                return error;
                        }

                        public boolean addFoodComponent() {
                                String name = nameTextField.getText();
                                Float price = Float.parseFloat(priceTextField.getText());
                                Float quantity = Float.parseFloat(quantityTextField.getText());
                                int providerID = providers.get(providerComboBox.getSelectedIndex()).getId();
                                boolean inventory = inventoryToggle.getText().equals("Yes");

                                int ingredientID = ingredientsAPI.addIngredient(providerID, name, price, quantity,
                                                inventory);
                                if (ingredientID == -1) {
                                        successLabel.setText("Error. Impossible to connect to database.");
                                        successLabel.setVisible(true);
                                        return false;
                                }

                                Stack<Integer> selectedAllergens = getSelectedAllergenIDs();

                                if (allergensAPI.addAlergensToIngredient(selectedAllergens, ingredientID))
                                        successLabel.setText("The ingredient \"" + name + "\" was added successfully.");
                                else
                                        successLabel.setText("Error. Something went wrong while adding allergens.");
                                successLabel.setVisible(true);
                                return false;
                        }

                }
                addButtonFormatter.formatAddButton(addIngredientButton, new addMethodsHolder());
        }

        private void applyGenericListeners() {
                iTextFieldListener inputListener = new addTextFieldFListener();
                inputListener.applyListenerTextField(nameTextField, "Ex. \"Ground Beef\"", namePlaceholder, false);
                inputListener.applyListenerTextField(priceTextField, "Ex. \"9.99\"", pricePlaceholder, false);
                inputListener.applyListenerTextField(quantityTextField, "Ex. \"1\"", quantityPlaceholder, false);
                new addJToggleAListener().applyActionListenerToggle(inventoryToggle, "Yes", "No", false);

                iFormatter numericFormatter = new inputFormatterFactory().createInputFormatter("PRICE");
                numericFormatter.applyFormat(priceTextField);
                numericFormatter.applyFormat(quantityTextField);
        }

}