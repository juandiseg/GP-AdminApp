package navigation.food.productsNav;

import java.util.ArrayList;
import java.util.Stack;

import javax.swing.*;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;

import componentsFood.category;
import componentsFood.ingredient;
import componentsFood.product;
import util.buttonFormatters.*;
import util.databaseAPIs.categoryAPI;
import util.databaseAPIs.ingredientsAPI;
import util.databaseAPIs.menuAPI;
import util.databaseAPIs.productAPI;
import util.inputFormatting.iFormatter;
import util.inputFormatting.inputFormatterFactory;
import util.listenersFormatting.booleanWrapper;
import util.listenersFormatting.iTextFieldListener;
import util.listenersFormatting.edit.editTextFieldFListener;

import java.awt.*;

public class editProduct {

        private JLabel auxProductLabel = new JLabel();
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

        private JButton editProductButton = new JButton();
        private JButton backButton = new JButton();
        private JButton deleteButton = new JButton();

        private JPanel jPanel1 = new JPanel();
        private JPanel jPanel2 = new JPanel();
        private JPanel jPanel3 = new JPanel();

        private ArrayList<category> categories = new categoryAPI().getProductCategories();

        private product theCurrentProduct;
        private productAPI theManagerDB = new productAPI();

        public editProduct(JPanel playground, product theCurrentProduct) {
                this.theCurrentProduct = theCurrentProduct;
                initComponents(playground);
                addActionListeners(playground);
        }

        private void initComponents(JPanel playground) {
                playground.setBackground(new Color(255, 255, 255));

                successLabel.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
                successLabel.setHorizontalAlignment(SwingConstants.CENTER);
                successLabel.setVisible(false);
                successLabel.setVerticalAlignment(SwingConstants.BOTTOM);

                jPanel1.setBackground(new Color(120, 168, 252));
                jPanel1.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
                jPanel1.setDebugGraphicsOptions(javax.swing.DebugGraphics.NONE_OPTION);

                nameLabel.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
                nameLabel.setHorizontalAlignment(SwingConstants.LEFT);
                nameLabel.setText("Name");
                nameLabel.setVerticalAlignment(SwingConstants.BOTTOM);

                editProductButton.setText("Edit Product");

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
                                                                                                jPanel1Layout.createSequentialGroup()
                                                                                                                .addPreferredGap(
                                                                                                                                ComponentPlacement.RELATED,
                                                                                                                                GroupLayout.DEFAULT_SIZE,
                                                                                                                                Short.MAX_VALUE)
                                                                                                                .addComponent(editProductButton,
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
                                                                .addComponent(editProductButton,
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

                auxProductLabel.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
                auxProductLabel.setHorizontalAlignment(SwingConstants.CENTER);
                auxProductLabel.setText("Product to edit:");
                auxProductLabel.setToolTipText("");
                auxProductLabel.setVerticalAlignment(SwingConstants.BOTTOM);

                theProductLabel.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
                theProductLabel.setHorizontalAlignment(SwingConstants.CENTER);
                theProductLabel.setText(theCurrentProduct.getName());
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
                                                .addGroup(GroupLayout.Alignment.TRAILING, playgroundLayout
                                                                .createSequentialGroup()
                                                                .addContainerGap()
                                                                .addComponent(backButton,
                                                                                GroupLayout.PREFERRED_SIZE,
                                                                                200,
                                                                                GroupLayout.PREFERRED_SIZE)
                                                                .addPreferredGap(
                                                                                ComponentPlacement.RELATED)
                                                                .addComponent(successLabel,
                                                                                GroupLayout.PREFERRED_SIZE,
                                                                                337,
                                                                                GroupLayout.PREFERRED_SIZE)
                                                                .addPreferredGap(
                                                                                ComponentPlacement.RELATED,
                                                                                GroupLayout.DEFAULT_SIZE,
                                                                                Short.MAX_VALUE)
                                                                .addComponent(deleteButton,
                                                                                GroupLayout.PREFERRED_SIZE,
                                                                                200,
                                                                                GroupLayout.PREFERRED_SIZE)
                                                                .addContainerGap())
                                                .addComponent(theProductLabel, GroupLayout.DEFAULT_SIZE,
                                                                GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                                .addComponent(auxProductLabel, GroupLayout.DEFAULT_SIZE,
                                                                GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE));
                playgroundLayout.setVerticalGroup(
                                playgroundLayout.createParallelGroup(GroupLayout.Alignment.LEADING)
                                                .addGroup(GroupLayout.Alignment.TRAILING, playgroundLayout
                                                                .createSequentialGroup()
                                                                .addGap(8, 8, 8)
                                                                .addComponent(auxProductLabel)
                                                                .addPreferredGap(
                                                                                ComponentPlacement.RELATED)
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
                                                                .addGroup(playgroundLayout.createParallelGroup(
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
                                                                                                .addGroup(playgroundLayout
                                                                                                                .createParallelGroup(
                                                                                                                                GroupLayout.Alignment.BASELINE)
                                                                                                                .addComponent(backButton,
                                                                                                                                GroupLayout.PREFERRED_SIZE,
                                                                                                                                55,
                                                                                                                                GroupLayout.PREFERRED_SIZE)
                                                                                                                .addComponent(deleteButton,
                                                                                                                                GroupLayout.PREFERRED_SIZE,
                                                                                                                                55,
                                                                                                                                GroupLayout.PREFERRED_SIZE))
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

                ingredientsAPI theManagerDB = new ingredientsAPI();
                for (ingredient tempIngredient : theManagerDB.getNonSelectedIngredientsInProduct(theCurrentProduct)) {
                        String ingID = Integer.toString(tempIngredient.getId());
                        String provID = Integer.toString(tempIngredient.getProviderID());
                        String date = tempIngredient.getDate();
                        String name = tempIngredient.getName();
                        String price = Float.toString(tempIngredient.getPrice());
                        String amount = Float.toString(tempIngredient.getAmount());
                        String in_inventory = "No";
                        if (tempIngredient.getInInventory())
                                in_inventory = "Yes";
                        modelIngredients.addRow(new String[] { ingID, provID, date, name, price, amount, in_inventory,
                                        "Yes", "Fill Here" });
                }

                for (ingredient tempIngredient : theManagerDB.getSelectedIngredientsInProduct(theCurrentProduct)) {
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
                        float tempAmount = theManagerDB.getAmountOfIngredientInProduct(theCurrentProduct.getId(),
                                        tempIngredient.getId());
                        modelSelected.addRow(new String[] { ingID, provID, date, name, price, amount, in_inventory,
                                        active, Float.toString(tempAmount) });
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
                for (int i = 0; i < categories.size(); i++) {
                        if (categories.get(i).getId() == theCurrentProduct.getCategoryID())
                                categories.add(0, categories.remove(i));
                }
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

        private boolean isIngredientPlaceholder() {
                ingredientsAPI theManagerDB = new ingredientsAPI();
                ArrayList<ingredient> tempList = theManagerDB.getSelectedIngredientsInProduct(theCurrentProduct);
                ArrayList<ingredient> listSelected = new ArrayList<ingredient>();
                for (int i = 0; i < modelSelected.getRowCount(); i++) {
                        int tempID = Integer.parseInt((String) modelSelected.getValueAt(i, 0));
                        int provID = Integer.parseInt((String) modelSelected.getValueAt(i, 1));
                        String date = (String) modelSelected.getValueAt(i, 2);
                        String name = (String) modelSelected.getValueAt(i, 3);
                        Float price = Float.parseFloat((String) modelSelected.getValueAt(i, 4));
                        Float amount = Float.parseFloat((String) modelSelected.getValueAt(i, 5));
                        Boolean inventory = ((String) modelSelected.getValueAt(i, 5)).equals("Yes");
                        listSelected.add(new ingredient(tempID, provID, date, name, price, amount, inventory, true));
                }
                for (ingredient temp : tempList) {
                        if (!listSelected.contains(temp))
                                return false;
                }
                for (ingredient temp : listSelected) {
                        if (!tempList.contains(temp))
                                return false;
                }
                for (ingredient temp : listSelected) {
                        for (int i = 0; i < modelSelected.getRowCount(); i++) {
                                if (Integer.parseInt((String) modelSelected.getValueAt(i, 0)) == temp.getId()) {
                                        try {
                                                float tempAmount = theManagerDB.getAmountOfIngredientInProduct(
                                                                theCurrentProduct.getId(), temp.getId());
                                                if (Float.parseFloat((String) modelSelected.getValueAt(i,
                                                                8)) != tempAmount) {
                                                        return false;
                                                }
                                        } catch (Exception numberFormatException) {
                                                return true;
                                        }
                                }
                        }
                }

                return true;
        }

        private void addActionListeners(JPanel playground) {
                selectionButtons();
                backButton(playground);
                editButton(null);
                deleteButton(playground);
                applyGenericListeners();
        }

        private void deleteButton(JPanel playground) {
                class deleteMethodHolder implements iDeleteButton {

                        public boolean thereIsError() {
                                return false;
                        }

                        public boolean askConfirmation() {
                                boolean neededToChoose = true;
                                int reply = JOptionPane.showConfirmDialog(null,
                                                "Are you sure you want to delete this Product?",
                                                "Confirmation", JOptionPane.YES_NO_OPTION);
                                if (reply == JOptionPane.YES_OPTION) {
                                        return neededToChoose;
                                }
                                return !neededToChoose;
                        }

                        public void chooseAmongOptions() {
                                String[] options = new String[] { "Delete Menus", "Keep Menus without Products",
                                                "Cancel" };
                                int response = JOptionPane.showOptionDialog(null,
                                                "Do you want to delete the menus that use this product, or keep them without the product?",
                                                "Choice",
                                                JOptionPane.DEFAULT_OPTION, JOptionPane.PLAIN_MESSAGE, null,
                                                options, options[0]);
                                if (response == 0) {
                                        deleteMenusAssociatedToProductID();
                                } else if (response == 1) {
                                        deleteProductsFromMenus();
                                } else
                                        return;

                                playground.removeAll();
                                new mainProducts(playground);
                                playground.revalidate();
                                playground.repaint();
                        }

                }
                deleteButtonFormatter.formatDeleteButton(deleteButton, new deleteMethodHolder());
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

        private void backButton(JPanel playground) {
                class backMethodHolder extends iNavigatorButton {
                        public void createNewNavigator() {
                                new mainProducts(playground);
                        }
                }
                navigatorButtonFormatter.formatNavigationButton(backButton, new backMethodHolder(), playground, true,
                                "Back");
        }

        private void editButton(JPanel playground) {
                class editMethodsHolder implements iEditButton {
                        private boolean categoryPlaceholder;
                        private boolean ingredientPlaceholder;

                        public boolean valuesArePlaceholders() {
                                categoryPlaceholder = categories.get(categoriesComboBox.getSelectedIndex())
                                                .getId() == theCurrentProduct.getCategoryID();
                                ingredientPlaceholder = isIngredientPlaceholder();

                                if (namePlaceholder.getValue() && pricePlaceholder.getValue() && categoryPlaceholder
                                                && ingredientPlaceholder) {
                                        successLabel.setText("Error. You must modify at least one field.");
                                        successLabel.setVisible(true);
                                        return true;
                                }
                                return false;
                        }

                        public boolean areInputsInvalid() {
                                boolean ingredientsEmpty = modelSelected.getRowCount() == 0;

                                if (!namePlaceholder.getValue()
                                                && theManagerDB.isNameTaken(nameTextField.getText())) {
                                        successLabel.setText("Error. The given name is already taken.");
                                        successLabel.setVisible(true);
                                        return true;
                                }

                                if (ingredientsEmpty) {
                                        successLabel.setText("Error. A product needs to be made up of ingredients.");
                                        successLabel.setVisible(true);
                                        return true;
                                }

                                if (ingredientsQuantityNotSpecified() && !ingredientPlaceholder) {
                                        successLabel.setText(
                                                        "Error. You must specify the amount used of each ingredient.");
                                        successLabel.setVisible(true);
                                        return true;
                                }

                                return false;
                        }

                        public void editFoodComponent() {
                                boolean successfulUpdate = true;
                                if (!namePlaceholder.getValue()) {
                                        successfulUpdate = theManagerDB.updateName(theCurrentProduct,
                                                        nameTextField.getText());
                                        theCurrentProduct = theManagerDB.getProduct(theCurrentProduct.getId());
                                }

                                if (!pricePlaceholder.getValue()) {
                                        Float price = Float.parseFloat(priceTextField.getText());
                                        successfulUpdate = theManagerDB.updatePrice(theCurrentProduct, price);
                                }

                                if (!categoryPlaceholder) {
                                        int catID = categories.get(categoriesComboBox.getSelectedIndex()).getId();
                                        successfulUpdate = theManagerDB.updateCategory(theCurrentProduct, catID);
                                }

                                if (!ingredientPlaceholder) {
                                        Stack<Integer> stackIDs = new Stack<Integer>();
                                        Stack<Float> stackQtys = new Stack<Float>();
                                        for (int i = 0; i < modelSelected.getRowCount(); i++) {
                                                stackIDs.push(Integer
                                                                .parseInt((String) modelSelected.getValueAt(i, 0)));
                                                stackQtys.push(Float
                                                                .parseFloat((String) modelSelected.getValueAt(i, 8)));
                                        }
                                        successfulUpdate = theManagerDB.updateIngredients(theCurrentProduct.getId(),
                                                        stackIDs, stackQtys);
                                }

                                if (successfulUpdate)
                                        successLabel.setText("The product \"" + nameTextField.getText()
                                                        + "\" was successfully updated.");
                                else
                                        successLabel.setText("Something went wrong while updating the product.");
                                successLabel.setVisible(true);
                        }

                        public void updatePlaceholders() {
                                theCurrentProduct = theManagerDB.getProduct(theCurrentProduct.getId());

                                namePlaceholder.setValue(true);
                                pricePlaceholder.setValue(true);

                                nameTextField.setForeground(Color.GRAY);
                                priceTextField.setForeground(Color.GRAY);
                                setComboBox();
                                applyGenericListeners();
                        }
                }
                editButtonFormatter.formatEditButton(editProductButton, new editMethodsHolder());
        }

        private void applyGenericListeners() {
                iTextFieldListener textListener = new editTextFieldFListener();
                iFormatter numericFormatter = new inputFormatterFactory().createInputFormatter("PRICE");
                textListener.applyListenerTextField(nameTextField, theCurrentProduct.getName(), namePlaceholder, false);
                textListener.applyListenerTextField(priceTextField, Float.toString(theCurrentProduct.getPrice()),
                                pricePlaceholder, false);
                numericFormatter.applyFormat(priceTextField);
        }

        private void deleteMenusAssociatedToProductID() {
                productAPI theManagerDB = new productAPI();
                Stack<Integer> stackMenuIDs = new menuAPI().getAllActiveMenuIDs();
                while (!stackMenuIDs.empty())
                        theManagerDB.deleteMenuWithProduct(stackMenuIDs.pop(), theCurrentProduct.getId());
                theManagerDB.updateActive(theCurrentProduct.getId(), false);
        }

        private void deleteProductsFromMenus() {
                productAPI theManagerDB = new productAPI();
                Stack<Integer> stackMenuIDs = new menuAPI().getAllActiveMenuIDs();
                while (!stackMenuIDs.empty())
                        theManagerDB.deleteProductsInMenu(stackMenuIDs.pop(), theCurrentProduct);
                theManagerDB.updateActive(theCurrentProduct.getId(), false);
        }

        private boolean ingredientsQuantityNotSpecified() {
                for (int i = 0; i < modelSelected.getRowCount(); i++) {
                        String temp = (String) modelSelected.getValueAt(i, 8);
                        if (temp.isEmpty())
                                return true;
                        try {
                                Float.parseFloat(temp);
                        } catch (Exception NumberFormatException) {
                                return true;
                        }
                }
                return false;
        }
}