package navigation.food.productsNav;

import javax.swing.table.*;
import javax.swing.*;
import javax.swing.LayoutStyle.ComponentPlacement;

import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import util.buttonFormatters.*;
import util.databaseAPIs.categoryAPI;
import util.databaseAPIs.ingredientsAPI;
import util.databaseAPIs.productAPI;
import util.inputFormatting.iFormatter;
import util.inputFormatting.inputFormatterFactory;
import util.listenersFormatting.booleanWrapper;
import util.listenersFormatting.iTextFieldListener;
import util.listenersFormatting.add.addTextFieldFListener;
import componentsFood.ingredient;
import componentsFood.category;

import java.util.ArrayList;
import java.util.Stack;

public class addProduct {

        private JLabel theProductLabel = new JLabel();

        private JLabel nameLabel = new JLabel();
        private JLabel priceLabel = new JLabel();
        private JLabel categoryLabel = new JLabel();
        private JLabel ingredientsLabel = new JLabel();

        private JTextField nameTextField = new JTextField();
        private JTextField priceTextField = new JTextField();

        private booleanWrapper namePlaceholder = new booleanWrapper(true);
        private booleanWrapper pricePlaceholder = new booleanWrapper(true);

        private JComboBox<String> categoriesComboBox = new JComboBox<String>();

        private JTable tableIngredients = new JTable();
        private JTable tableSelected = new JTable();
        private DefaultTableModel modelIngredients;
        private DefaultTableModel modelSelected;

        private JButton selectButton = new JButton();
        private JButton unselectButton = new JButton();

        private JScrollPane selectedJScrollPane = new JScrollPane();
        private JScrollPane unselectedJScrollPane = new JScrollPane();

        private JLabel successLabel = new JLabel();

        private JButton addProductButton = new JButton();
        private JButton backButton = new JButton();

        private JPanel jPanel1 = new JPanel();
        private JPanel jPanel2 = new JPanel();
        private JPanel jPanel3 = new JPanel();

        private ArrayList<category> categories = categoryAPI.getProductCategories();

        public addProduct(JPanel playground) {
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

                nameLabel.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
                nameLabel.setHorizontalAlignment(SwingConstants.LEFT);
                nameLabel.setText("Name");
                nameLabel.setVerticalAlignment(SwingConstants.BOTTOM);

                addProductButton.setText("Add Product");

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

                categoryLabel.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
                categoryLabel.setHorizontalAlignment(SwingConstants.LEFT);
                categoryLabel.setText("Choose Category");
                categoryLabel.setVerticalAlignment(SwingConstants.BOTTOM);

                ingredientsLabel.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
                ingredientsLabel.setHorizontalAlignment(SwingConstants.LEFT);
                ingredientsLabel.setText("Select Ingredients");
                ingredientsLabel.setVerticalAlignment(SwingConstants.BOTTOM);

                setTables();

                setComboBox();

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
                                                                                                .addGap(56, 56, 56)
                                                                                                .addGroup(jPanel1Layout
                                                                                                                .createParallelGroup(
                                                                                                                                GroupLayout.Alignment.TRAILING)
                                                                                                                .addGroup(jPanel1Layout
                                                                                                                                .createParallelGroup(
                                                                                                                                                GroupLayout.Alignment.TRAILING,
                                                                                                                                                false)
                                                                                                                                .addComponent(priceLabel,
                                                                                                                                                GroupLayout.DEFAULT_SIZE,
                                                                                                                                                GroupLayout.DEFAULT_SIZE,
                                                                                                                                                Short.MAX_VALUE)
                                                                                                                                .addComponent(nameLabel,
                                                                                                                                                GroupLayout.Alignment.LEADING,
                                                                                                                                                GroupLayout.DEFAULT_SIZE,
                                                                                                                                                165,
                                                                                                                                                Short.MAX_VALUE)
                                                                                                                                .addComponent(categoryLabel,
                                                                                                                                                GroupLayout.Alignment.LEADING,
                                                                                                                                                GroupLayout.DEFAULT_SIZE,
                                                                                                                                                GroupLayout.DEFAULT_SIZE,
                                                                                                                                                Short.MAX_VALUE))
                                                                                                                .addComponent(ingredientsLabel,
                                                                                                                                GroupLayout.PREFERRED_SIZE,
                                                                                                                                165,
                                                                                                                                GroupLayout.PREFERRED_SIZE))
                                                                                                .addPreferredGap(
                                                                                                                ComponentPlacement.RELATED)
                                                                                                .addGroup(jPanel1Layout
                                                                                                                .createParallelGroup(
                                                                                                                                GroupLayout.Alignment.LEADING,
                                                                                                                                false)
                                                                                                                .addGroup(jPanel1Layout
                                                                                                                                .createSequentialGroup()
                                                                                                                                .addComponent(selectButton,
                                                                                                                                                GroupLayout.PREFERRED_SIZE,
                                                                                                                                                75,
                                                                                                                                                GroupLayout.PREFERRED_SIZE)
                                                                                                                                .addPreferredGap(
                                                                                                                                                ComponentPlacement.RELATED,
                                                                                                                                                GroupLayout.DEFAULT_SIZE,
                                                                                                                                                Short.MAX_VALUE)
                                                                                                                                .addComponent(unselectButton))
                                                                                                                .addComponent(priceTextField,
                                                                                                                                GroupLayout.PREFERRED_SIZE,
                                                                                                                                434,
                                                                                                                                GroupLayout.PREFERRED_SIZE)
                                                                                                                .addComponent(nameTextField,
                                                                                                                                GroupLayout.PREFERRED_SIZE,
                                                                                                                                434,
                                                                                                                                GroupLayout.PREFERRED_SIZE)
                                                                                                                .addGroup(GroupLayout.Alignment.TRAILING,
                                                                                                                                jPanel1Layout.createSequentialGroup()
                                                                                                                                                .addComponent(unselectedJScrollPane,
                                                                                                                                                                GroupLayout.PREFERRED_SIZE,
                                                                                                                                                                194,
                                                                                                                                                                GroupLayout.PREFERRED_SIZE)
                                                                                                                                                .addGap(18, 18, 18)
                                                                                                                                                .addComponent(selectedJScrollPane,
                                                                                                                                                                GroupLayout.PREFERRED_SIZE,
                                                                                                                                                                0,
                                                                                                                                                                Short.MAX_VALUE))
                                                                                                                .addComponent(categoriesComboBox,
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
                                                                                                                .addComponent(addProductButton,
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
                                                                                .addComponent(nameLabel)
                                                                                .addComponent(nameTextField,
                                                                                                GroupLayout.PREFERRED_SIZE,
                                                                                                35,
                                                                                                GroupLayout.PREFERRED_SIZE))
                                                                .addPreferredGap(
                                                                                ComponentPlacement.RELATED)
                                                                .addGroup(jPanel1Layout.createParallelGroup(
                                                                                GroupLayout.Alignment.BASELINE)
                                                                                .addComponent(priceTextField,
                                                                                                GroupLayout.PREFERRED_SIZE,
                                                                                                35,
                                                                                                GroupLayout.PREFERRED_SIZE)
                                                                                .addComponent(priceLabel))
                                                                .addPreferredGap(
                                                                                ComponentPlacement.RELATED)
                                                                .addGroup(jPanel1Layout.createParallelGroup(
                                                                                GroupLayout.Alignment.BASELINE)
                                                                                .addComponent(categoryLabel)
                                                                                .addComponent(categoriesComboBox,
                                                                                                GroupLayout.PREFERRED_SIZE,
                                                                                                34,
                                                                                                GroupLayout.PREFERRED_SIZE))
                                                                .addPreferredGap(
                                                                                ComponentPlacement.RELATED,
                                                                                9, Short.MAX_VALUE)
                                                                .addGroup(jPanel1Layout.createParallelGroup(
                                                                                GroupLayout.Alignment.LEADING)
                                                                                .addGroup(jPanel1Layout
                                                                                                .createParallelGroup(
                                                                                                                GroupLayout.Alignment.TRAILING,
                                                                                                                false)
                                                                                                .addComponent(selectedJScrollPane,
                                                                                                                GroupLayout.Alignment.LEADING,
                                                                                                                GroupLayout.PREFERRED_SIZE,
                                                                                                                0,
                                                                                                                Short.MAX_VALUE)
                                                                                                .addComponent(unselectedJScrollPane,
                                                                                                                GroupLayout.Alignment.LEADING,
                                                                                                                GroupLayout.PREFERRED_SIZE,
                                                                                                                193,
                                                                                                                GroupLayout.PREFERRED_SIZE))
                                                                                .addComponent(ingredientsLabel))
                                                                .addPreferredGap(
                                                                                ComponentPlacement.RELATED)
                                                                .addGroup(jPanel1Layout.createParallelGroup(
                                                                                GroupLayout.Alignment.LEADING)
                                                                                .addComponent(selectButton)
                                                                                .addComponent(unselectButton))
                                                                .addGap(2, 2, 2)
                                                                .addComponent(addProductButton,
                                                                                GroupLayout.PREFERRED_SIZE,
                                                                                55,
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

                theProductLabel.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
                theProductLabel.setHorizontalAlignment(SwingConstants.CENTER);
                theProductLabel.setText("Add Product");
                theProductLabel.setVerticalAlignment(SwingConstants.BOTTOM);

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
                                                .addComponent(theProductLabel, GroupLayout.DEFAULT_SIZE,
                                                                GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                                .addGroup(playgroundLayout.createSequentialGroup()
                                                                .addContainerGap()
                                                                .addComponent(backButton,
                                                                                GroupLayout.PREFERRED_SIZE,
                                                                                200,
                                                                                GroupLayout.PREFERRED_SIZE)
                                                                .addPreferredGap(
                                                                                ComponentPlacement.RELATED)
                                                                .addComponent(successLabel,
                                                                                GroupLayout.DEFAULT_SIZE,
                                                                                GroupLayout.DEFAULT_SIZE,
                                                                                Short.MAX_VALUE)
                                                                .addContainerGap()));
                playgroundLayout.setVerticalGroup(
                                playgroundLayout.createParallelGroup(GroupLayout.Alignment.LEADING)
                                                .addGroup(GroupLayout.Alignment.TRAILING, playgroundLayout
                                                                .createSequentialGroup()
                                                                .addGap(39, 39, 39)
                                                                .addComponent(theProductLabel)
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
                                                                .addGroup(playgroundLayout
                                                                                .createParallelGroup(
                                                                                                GroupLayout.Alignment.LEADING)
                                                                                .addGroup(playgroundLayout
                                                                                                .createSequentialGroup()
                                                                                                .addPreferredGap(
                                                                                                                ComponentPlacement.RELATED,
                                                                                                                GroupLayout.DEFAULT_SIZE,
                                                                                                                Short.MAX_VALUE)
                                                                                                .addComponent(successLabel)
                                                                                                .addGap(79, 79, 79))
                                                                                .addGroup(playgroundLayout
                                                                                                .createSequentialGroup()
                                                                                                .addGap(18, 18, 18)
                                                                                                .addComponent(backButton,
                                                                                                                GroupLayout.PREFERRED_SIZE,
                                                                                                                55,
                                                                                                                GroupLayout.PREFERRED_SIZE)
                                                                                                .addContainerGap(
                                                                                                                GroupLayout.DEFAULT_SIZE,
                                                                                                                Short.MAX_VALUE)))));
        }

        private void setTables() {
                tableIngredients = new JTable();
                tableSelected = new JTable() {
                        public boolean isCellEditable(int rowIndex, int colIndex) {
                                return colIndex == 3;
                        };
                };
                modelIngredients = new DefaultTableModel(
                                new String[] { "ingredient_id", "provider_id", "date", "Name", "Price", "Amount",
                                                "in_inventory", "active", "Quantity" },
                                0);
                modelSelected = new DefaultTableModel(
                                new String[] { "ingredient_id", "provider_id", "date", "Name", "Price", "Amount",
                                                "in_inventory",
                                                "active", "Quantity" },
                                0);
                for (ingredient tempIngredient : ingredientsAPI.getAllCurrentIngredients()) {
                        String ingID = Integer.toString(tempIngredient.getId());
                        String provID = Integer.toString(tempIngredient.getProviderID());
                        String date = tempIngredient.getDate();
                        String name = tempIngredient.getName();
                        String price = Float.toString(tempIngredient.getPrice());
                        String amount = Float.toString(tempIngredient.getAmount());
                        String in_inventory = "No";
                        if (tempIngredient.getInInventory())
                                in_inventory = "Yes";
                        String active = "No";
                        if (tempIngredient.getActive())
                                active = "Yes";
                        modelIngredients.addRow(new String[] { ingID, provID, date, name, price, amount, in_inventory,
                                        active, "0" });
                }

                tableIngredients.setModel(modelIngredients);
                tableSelected.setModel(modelSelected);
                tableIngredients.removeColumn(tableIngredients.getColumn("ingredient_id"));
                tableIngredients.removeColumn(tableIngredients.getColumn("provider_id"));
                tableIngredients.removeColumn(tableIngredients.getColumn("date"));
                tableIngredients.removeColumn(tableIngredients.getColumn("in_inventory"));
                tableIngredients.removeColumn(tableIngredients.getColumn("active"));
                tableIngredients.removeColumn(tableIngredients.getColumn("Quantity"));
                tableSelected.removeColumn(tableSelected.getColumn("ingredient_id"));
                tableSelected.removeColumn(tableSelected.getColumn("provider_id"));
                tableSelected.removeColumn(tableSelected.getColumn("date"));
                tableSelected.removeColumn(tableSelected.getColumn("in_inventory"));
                tableSelected.removeColumn(tableSelected.getColumn("active"));
                unselectedJScrollPane.setViewportView(tableIngredients);
                selectedJScrollPane.setViewportView(tableSelected);
                tableIngredients.setDefaultEditor(Object.class, null);
                tableLookPretty(tableIngredients);
                tableLookPretty(tableSelected);
        }

        private void tableLookPretty(JTable theTable) {
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
                for (category temp : categories)
                        tempNames.add(temp.getName());
                String[] namesArr = tempNames.toArray(new String[0]);
                categoriesComboBox.setModel(new DefaultComboBoxModel<String>(namesArr));
                categoriesComboBox.setFont(new java.awt.Font("Segoe UI", 0, 18));
                categoriesComboBox.setFont(new Font("Segoe UI", 0, 18));
                categoriesComboBox.setForeground(Color.BLACK);
                categoriesComboBox.setBackground(Color.WHITE);
        }

        private Stack<Integer> getSelectedIngredientIDs() {
                Stack<Integer> selectedIngredients = new Stack<Integer>();
                for (int i = 0; i < modelSelected.getRowCount(); i++)
                        selectedIngredients.push(Integer.parseInt((String) modelSelected.getValueAt(i, 0)));
                return selectedIngredients;
        }

        private Stack<Float> getSelectedIngredientQtys() {
                Stack<Float> selectedIngredients = new Stack<Float>();
                for (int i = 0; i < modelSelected.getRowCount(); i++)
                        selectedIngredients.push(Float.parseFloat((String) modelSelected.getValueAt(i, 8)));
                return selectedIngredients;
        }

        private void addListeners(JPanel playground) {
                selectionButtons();
                backButton(playground);
                addButton(null);
                applyGenericListeners();
        }

        private void backButton(JPanel playground) {
                class backMethodHolder extends iNavigatorButton {
                        public void createNewNavigator() {
                                new mainProducts(playground);
                        }
                }
                navigatorButtonFormatter.formatNavigationButton(backButton, new backMethodHolder(), playground, true,
                                "Back");
        }

        private void addButton(JPanel playground) {
                class addMethodsHolder extends iAddButton {
                        public boolean valuesArePlaceholders() {
                                boolean ingredientEmpty = modelSelected.getRowCount() == 0;
                                boolean arePlaceholders = (namePlaceholder.getValue() || pricePlaceholder.getValue()
                                                || ingredientEmpty);
                                if (arePlaceholders) {
                                        successLabel.setText("Error. You must fill all the given fields.");
                                        successLabel.setVisible(true);
                                }
                                return arePlaceholders;
                        }

                        public boolean areInputsInvalid() {
                                if (ingredientsQuantityNotSpecified()) {
                                        successLabel.setText(
                                                        "Error. You must specify the amount used of each ingredient.");
                                        successLabel.setVisible(true);
                                        return true;
                                }

                                for (int i = 0; i < modelSelected.getRowCount(); i++) {
                                        String temp = (String) modelSelected.getValueAt(i, 8);
                                        if (temp.length() > 7) {
                                                successLabel.setText("Error. You must specify an smaller amount.");
                                                successLabel.setVisible(true);
                                                return true;
                                        }
                                }

                                if (productAPI.isNameTaken(nameTextField.getText())) {
                                        successLabel.setText("Error. The given name is already taken.");
                                        successLabel.setVisible(true);
                                        return true;
                                }
                                return false;
                        }

                        public boolean addFoodComponent() {
                                String name = nameTextField.getText();
                                int catID = categories.get(categoriesComboBox.getSelectedIndex()).getId();
                                Float price = Float.parseFloat(priceTextField.getText());

                                int productID = productAPI.addProduct(catID, name, price);
                                if (productID == -1) {
                                        successLabel.setText("Error. Impossible to connect to database.");
                                        successLabel.setVisible(true);
                                        return false;
                                }

                                Stack<Integer> selectedIngredientIDs = getSelectedIngredientIDs();
                                Stack<Float> selectedIngredientQtys = getSelectedIngredientQtys();
                                if (productAPI.addIngredients(productID, selectedIngredientIDs,
                                                selectedIngredientQtys))
                                        successLabel.setText("The product \"" + name + "\" was added successfully.");
                                else
                                        successLabel.setText("Error. Something went wrong while adding ingredients.");
                                successLabel.setVisible(true);
                                return false;
                        }
                }
                addButtonFormatter.formatAddButton(addProductButton, new addMethodsHolder());
        }

        private void selectionButtons() {
                class selectMethodHolder implements iSelectionButton {
                        public void doSelection() {
                                int row = tableIngredients.getSelectedRow();
                                if (row == -1)
                                        return;
                                String ingID = (String) modelIngredients.getValueAt(row, 0);
                                String provID = (String) modelIngredients.getValueAt(row, 1);
                                String date = (String) modelIngredients.getValueAt(row, 2);
                                String name = (String) modelIngredients.getValueAt(row, 3);
                                String price = (String) modelIngredients.getValueAt(row, 4);
                                String amount = (String) modelIngredients.getValueAt(row, 5);
                                String in_inventory = (String) modelIngredients.getValueAt(row, 6);
                                String active = (String) modelIngredients.getValueAt(row, 7);
                                String quantity = (String) modelIngredients.getValueAt(row, 8);
                                modelIngredients.removeRow(row);
                                modelSelected.addRow(new String[] { ingID, provID, date, name, price, amount,
                                                in_inventory, active, quantity });
                        }
                }
                selectionButtonFormatter.formatSelectionButton(selectButton, new selectMethodHolder(), true);
                class unselectMethodHolder implements iSelectionButton {
                        public void doSelection() {
                                int row = tableSelected.getSelectedRow();
                                if (row == -1)
                                        return;
                                String ingID = (String) modelSelected.getValueAt(row, 0);
                                String provID = (String) modelSelected.getValueAt(row, 1);
                                String date = (String) modelSelected.getValueAt(row, 2);
                                String name = (String) modelSelected.getValueAt(row, 3);
                                String price = (String) modelSelected.getValueAt(row, 4);
                                String amount = (String) modelSelected.getValueAt(row, 5);
                                String in_inventory = (String) modelSelected.getValueAt(row, 6);
                                String active = (String) modelSelected.getValueAt(row, 7);
                                String quantity = (String) modelSelected.getValueAt(row, 8);
                                modelSelected.removeRow(row);
                                modelIngredients.addRow(new String[] { ingID, provID, date, name, price, amount,
                                                in_inventory, active, quantity });
                        }
                }
                selectionButtonFormatter.formatSelectionButton(unselectButton, new unselectMethodHolder(), false);
        }

        private void applyGenericListeners() {
                iTextFieldListener inputListener = new addTextFieldFListener();
                iFormatter numericFormatter = new inputFormatterFactory().createInputFormatter("PRICE");
                inputListener.applyListenerTextField(nameTextField, "Ex. \"Burguer\"", namePlaceholder, false);
                inputListener.applyListenerTextField(priceTextField, "Ex. \"9.99\"", pricePlaceholder, false);
                numericFormatter.applyFormat(priceTextField);
        }

        private boolean ingredientsQuantityNotSpecified() {
                for (int i = 0; i < modelSelected.getRowCount(); i++) {
                        String temp = (String) modelSelected.getValueAt(i, 8);
                        if (temp.isEmpty())
                                return true;
                        try {
                                if (Float.parseFloat(temp) <= 0)
                                        return true;
                        } catch (Exception NumberFormatException) {
                                return true;
                        }
                }
                return false;
        }
}