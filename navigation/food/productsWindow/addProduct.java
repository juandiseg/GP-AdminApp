package navigation.food.productsWindow;

import javax.swing.table.*;
import java.awt.event.*;
import javax.swing.*;
import java.awt.*;

import navigation.food.ingredientsWindow.ingredientsAPI;
import navigation.food.categoryWindow.categoryAPI;
import componentsFood.ingredient;
import componentsFood.category;
import componentsFood.product;
import java.time.format.DateTimeFormatter;
import java.time.LocalDate;
import java.util.ArrayList;

public class addProduct {

        private JLabel theProductLabel = new JLabel();

        private JLabel nameLabel = new JLabel();
        private JLabel priceLabel = new JLabel();
        private JLabel categoryLabel = new JLabel();
        private JLabel ingredientsLabel = new JLabel();

        private JTextField nameTextField = new JTextField();
        private JTextField priceTextField = new JTextField();

        private boolean namePlaceholder = true;
        private boolean pricePlaceholder = true;

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

        private ArrayList<category> categories = new categoryAPI().getProductCategories();

        public addProduct(JPanel playground) {
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

                nameLabel.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
                nameLabel.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
                nameLabel.setText("Name");
                nameLabel.setVerticalAlignment(javax.swing.SwingConstants.BOTTOM);

                nameTextField.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
                nameTextField.setText("Ex. \"Burguer\"");
                nameTextField.setForeground(Color.GRAY);

                addProductButton.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
                addProductButton.setText("Add Product");
                addProductButton.setBackground(new Color(255, 255, 255));
                addProductButton.setForeground(new Color(23, 35, 51));

                jPanel2.setBackground(new java.awt.Color(0, 0, 0));

                javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
                jPanel2.setLayout(jPanel2Layout);
                jPanel2Layout.setHorizontalGroup(
                                jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                .addGap(0, 8, Short.MAX_VALUE));
                jPanel2Layout.setVerticalGroup(
                                jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                .addGap(0, 0, Short.MAX_VALUE));

                priceLabel.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
                priceLabel.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
                priceLabel.setText("Price");
                priceLabel.setVerticalAlignment(javax.swing.SwingConstants.BOTTOM);

                priceTextField.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
                priceTextField.setText("Ex. \"9.99\"");
                priceTextField.setForeground(Color.GRAY);

                categoryLabel.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
                categoryLabel.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
                categoryLabel.setText("Choose Category");
                categoryLabel.setVerticalAlignment(javax.swing.SwingConstants.BOTTOM);

                ingredientsLabel.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
                ingredientsLabel.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
                ingredientsLabel.setText("Select Ingredients");
                ingredientsLabel.setVerticalAlignment(javax.swing.SwingConstants.BOTTOM);

                setTables();

                selectButton.setText("Select");
                selectButton.setBackground(new Color(23, 35, 51));
                selectButton.setForeground(new Color(255, 255, 255));

                unselectButton.setText("Unselect");
                unselectButton.setBackground(new Color(23, 35, 51));
                unselectButton.setForeground(new Color(255, 255, 255));

                setComboBox();

                javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
                jPanel1.setLayout(jPanel1Layout);
                jPanel1Layout.setHorizontalGroup(
                                jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                .addGroup(jPanel1Layout.createSequentialGroup()
                                                                .addComponent(jPanel2,
                                                                                javax.swing.GroupLayout.PREFERRED_SIZE,
                                                                                javax.swing.GroupLayout.DEFAULT_SIZE,
                                                                                javax.swing.GroupLayout.PREFERRED_SIZE)
                                                                .addGroup(jPanel1Layout.createParallelGroup(
                                                                                javax.swing.GroupLayout.Alignment.LEADING)
                                                                                .addGroup(jPanel1Layout
                                                                                                .createSequentialGroup()
                                                                                                .addGap(56, 56, 56)
                                                                                                .addGroup(jPanel1Layout
                                                                                                                .createParallelGroup(
                                                                                                                                javax.swing.GroupLayout.Alignment.TRAILING)
                                                                                                                .addGroup(jPanel1Layout
                                                                                                                                .createParallelGroup(
                                                                                                                                                javax.swing.GroupLayout.Alignment.TRAILING,
                                                                                                                                                false)
                                                                                                                                .addComponent(priceLabel,
                                                                                                                                                javax.swing.GroupLayout.DEFAULT_SIZE,
                                                                                                                                                javax.swing.GroupLayout.DEFAULT_SIZE,
                                                                                                                                                Short.MAX_VALUE)
                                                                                                                                .addComponent(nameLabel,
                                                                                                                                                javax.swing.GroupLayout.Alignment.LEADING,
                                                                                                                                                javax.swing.GroupLayout.DEFAULT_SIZE,
                                                                                                                                                165,
                                                                                                                                                Short.MAX_VALUE)
                                                                                                                                .addComponent(categoryLabel,
                                                                                                                                                javax.swing.GroupLayout.Alignment.LEADING,
                                                                                                                                                javax.swing.GroupLayout.DEFAULT_SIZE,
                                                                                                                                                javax.swing.GroupLayout.DEFAULT_SIZE,
                                                                                                                                                Short.MAX_VALUE))
                                                                                                                .addComponent(ingredientsLabel,
                                                                                                                                javax.swing.GroupLayout.PREFERRED_SIZE,
                                                                                                                                165,
                                                                                                                                javax.swing.GroupLayout.PREFERRED_SIZE))
                                                                                                .addPreferredGap(
                                                                                                                javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                                                                                .addGroup(jPanel1Layout
                                                                                                                .createParallelGroup(
                                                                                                                                javax.swing.GroupLayout.Alignment.LEADING,
                                                                                                                                false)
                                                                                                                .addGroup(jPanel1Layout
                                                                                                                                .createSequentialGroup()
                                                                                                                                .addComponent(selectButton,
                                                                                                                                                javax.swing.GroupLayout.PREFERRED_SIZE,
                                                                                                                                                75,
                                                                                                                                                javax.swing.GroupLayout.PREFERRED_SIZE)
                                                                                                                                .addPreferredGap(
                                                                                                                                                javax.swing.LayoutStyle.ComponentPlacement.RELATED,
                                                                                                                                                javax.swing.GroupLayout.DEFAULT_SIZE,
                                                                                                                                                Short.MAX_VALUE)
                                                                                                                                .addComponent(unselectButton))
                                                                                                                .addComponent(priceTextField,
                                                                                                                                javax.swing.GroupLayout.PREFERRED_SIZE,
                                                                                                                                434,
                                                                                                                                javax.swing.GroupLayout.PREFERRED_SIZE)
                                                                                                                .addComponent(nameTextField,
                                                                                                                                javax.swing.GroupLayout.PREFERRED_SIZE,
                                                                                                                                434,
                                                                                                                                javax.swing.GroupLayout.PREFERRED_SIZE)
                                                                                                                .addGroup(javax.swing.GroupLayout.Alignment.TRAILING,
                                                                                                                                jPanel1Layout.createSequentialGroup()
                                                                                                                                                .addComponent(unselectedJScrollPane,
                                                                                                                                                                javax.swing.GroupLayout.PREFERRED_SIZE,
                                                                                                                                                                194,
                                                                                                                                                                javax.swing.GroupLayout.PREFERRED_SIZE)
                                                                                                                                                .addGap(18, 18, 18)
                                                                                                                                                .addComponent(selectedJScrollPane,
                                                                                                                                                                javax.swing.GroupLayout.PREFERRED_SIZE,
                                                                                                                                                                0,
                                                                                                                                                                Short.MAX_VALUE))
                                                                                                                .addComponent(categoriesComboBox,
                                                                                                                                javax.swing.GroupLayout.PREFERRED_SIZE,
                                                                                                                                434,
                                                                                                                                javax.swing.GroupLayout.PREFERRED_SIZE))
                                                                                                .addContainerGap(
                                                                                                                javax.swing.GroupLayout.DEFAULT_SIZE,
                                                                                                                Short.MAX_VALUE))
                                                                                .addGroup(javax.swing.GroupLayout.Alignment.TRAILING,
                                                                                                jPanel1Layout
                                                                                                                .createSequentialGroup()
                                                                                                                .addPreferredGap(
                                                                                                                                javax.swing.LayoutStyle.ComponentPlacement.RELATED,
                                                                                                                                javax.swing.GroupLayout.DEFAULT_SIZE,
                                                                                                                                Short.MAX_VALUE)
                                                                                                                .addComponent(addProductButton,
                                                                                                                                javax.swing.GroupLayout.PREFERRED_SIZE,
                                                                                                                                200,
                                                                                                                                javax.swing.GroupLayout.PREFERRED_SIZE)
                                                                                                                .addGap(323, 323,
                                                                                                                                323)))));
                jPanel1Layout.setVerticalGroup(
                                jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout
                                                                .createSequentialGroup()
                                                                .addGap(21, 21, 21)
                                                                .addGroup(jPanel1Layout.createParallelGroup(
                                                                                javax.swing.GroupLayout.Alignment.BASELINE)
                                                                                .addComponent(nameLabel)
                                                                                .addComponent(nameTextField,
                                                                                                javax.swing.GroupLayout.PREFERRED_SIZE,
                                                                                                35,
                                                                                                javax.swing.GroupLayout.PREFERRED_SIZE))
                                                                .addPreferredGap(
                                                                                javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                                                .addGroup(jPanel1Layout.createParallelGroup(
                                                                                javax.swing.GroupLayout.Alignment.BASELINE)
                                                                                .addComponent(priceTextField,
                                                                                                javax.swing.GroupLayout.PREFERRED_SIZE,
                                                                                                35,
                                                                                                javax.swing.GroupLayout.PREFERRED_SIZE)
                                                                                .addComponent(priceLabel))
                                                                .addPreferredGap(
                                                                                javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                                                .addGroup(jPanel1Layout.createParallelGroup(
                                                                                javax.swing.GroupLayout.Alignment.BASELINE)
                                                                                .addComponent(categoryLabel)
                                                                                .addComponent(categoriesComboBox,
                                                                                                javax.swing.GroupLayout.PREFERRED_SIZE,
                                                                                                34,
                                                                                                javax.swing.GroupLayout.PREFERRED_SIZE))
                                                                .addPreferredGap(
                                                                                javax.swing.LayoutStyle.ComponentPlacement.RELATED,
                                                                                9, Short.MAX_VALUE)
                                                                .addGroup(jPanel1Layout.createParallelGroup(
                                                                                javax.swing.GroupLayout.Alignment.LEADING)
                                                                                .addGroup(jPanel1Layout
                                                                                                .createParallelGroup(
                                                                                                                javax.swing.GroupLayout.Alignment.TRAILING,
                                                                                                                false)
                                                                                                .addComponent(selectedJScrollPane,
                                                                                                                javax.swing.GroupLayout.Alignment.LEADING,
                                                                                                                javax.swing.GroupLayout.PREFERRED_SIZE,
                                                                                                                0,
                                                                                                                Short.MAX_VALUE)
                                                                                                .addComponent(unselectedJScrollPane,
                                                                                                                javax.swing.GroupLayout.Alignment.LEADING,
                                                                                                                javax.swing.GroupLayout.PREFERRED_SIZE,
                                                                                                                193,
                                                                                                                javax.swing.GroupLayout.PREFERRED_SIZE))
                                                                                .addComponent(ingredientsLabel))
                                                                .addPreferredGap(
                                                                                javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                                                .addGroup(jPanel1Layout.createParallelGroup(
                                                                                javax.swing.GroupLayout.Alignment.LEADING)
                                                                                .addComponent(selectButton)
                                                                                .addComponent(unselectButton))
                                                                .addGap(2, 2, 2)
                                                                .addComponent(addProductButton,
                                                                                javax.swing.GroupLayout.PREFERRED_SIZE,
                                                                                55,
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

                theProductLabel.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
                theProductLabel.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
                theProductLabel.setText("Add Product");
                theProductLabel.setVerticalAlignment(javax.swing.SwingConstants.BOTTOM);

                backButton.setBackground(new java.awt.Color(71, 120, 197));
                backButton.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
                backButton.setForeground(new java.awt.Color(255, 255, 255));
                backButton.setText("Back");

                javax.swing.GroupLayout playgroundLayout = new javax.swing.GroupLayout(playground);
                playground.setLayout(playgroundLayout);
                playgroundLayout.setHorizontalGroup(
                                playgroundLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE,
                                                                javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                                .addGroup(playgroundLayout.createSequentialGroup()
                                                                .addGap(185, 185, 185)
                                                                .addComponent(jPanel3,
                                                                                javax.swing.GroupLayout.PREFERRED_SIZE,
                                                                                javax.swing.GroupLayout.DEFAULT_SIZE,
                                                                                javax.swing.GroupLayout.PREFERRED_SIZE)
                                                                .addGap(0, 185, Short.MAX_VALUE))
                                                .addComponent(theProductLabel, javax.swing.GroupLayout.DEFAULT_SIZE,
                                                                javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                                .addGroup(playgroundLayout.createSequentialGroup()
                                                                .addContainerGap()
                                                                .addComponent(backButton,
                                                                                javax.swing.GroupLayout.PREFERRED_SIZE,
                                                                                200,
                                                                                javax.swing.GroupLayout.PREFERRED_SIZE)
                                                                .addPreferredGap(
                                                                                javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                                                .addComponent(successLabel,
                                                                                javax.swing.GroupLayout.DEFAULT_SIZE,
                                                                                javax.swing.GroupLayout.DEFAULT_SIZE,
                                                                                Short.MAX_VALUE)
                                                                .addContainerGap()));
                playgroundLayout.setVerticalGroup(
                                playgroundLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, playgroundLayout
                                                                .createSequentialGroup()
                                                                .addGap(39, 39, 39)
                                                                .addComponent(theProductLabel)
                                                                .addPreferredGap(
                                                                                javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                                                .addComponent(jPanel3,
                                                                                javax.swing.GroupLayout.PREFERRED_SIZE,
                                                                                javax.swing.GroupLayout.DEFAULT_SIZE,
                                                                                javax.swing.GroupLayout.PREFERRED_SIZE)
                                                                .addGap(18, 18, 18)
                                                                .addComponent(jPanel1,
                                                                                javax.swing.GroupLayout.PREFERRED_SIZE,
                                                                                javax.swing.GroupLayout.DEFAULT_SIZE,
                                                                                javax.swing.GroupLayout.PREFERRED_SIZE)
                                                                .addGroup(playgroundLayout
                                                                                .createParallelGroup(
                                                                                                javax.swing.GroupLayout.Alignment.LEADING)
                                                                                .addGroup(playgroundLayout
                                                                                                .createSequentialGroup()
                                                                                                .addPreferredGap(
                                                                                                                javax.swing.LayoutStyle.ComponentPlacement.RELATED,
                                                                                                                javax.swing.GroupLayout.DEFAULT_SIZE,
                                                                                                                Short.MAX_VALUE)
                                                                                                .addComponent(successLabel)
                                                                                                .addGap(79, 79, 79))
                                                                                .addGroup(playgroundLayout
                                                                                                .createSequentialGroup()
                                                                                                .addGap(18, 18, 18)
                                                                                                .addComponent(backButton,
                                                                                                                javax.swing.GroupLayout.PREFERRED_SIZE,
                                                                                                                55,
                                                                                                                javax.swing.GroupLayout.PREFERRED_SIZE)
                                                                                                .addContainerGap(
                                                                                                                javax.swing.GroupLayout.DEFAULT_SIZE,
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

                for (ingredient tempIngredient : new ingredientsAPI().getAllCurrentIngredients()) {
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
                                        active, "Fill Here" });
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

        private void addActionListeners(JPanel playground) {
                backButton.addMouseListener(new MouseListener() {
                        public void mouseClicked(MouseEvent e) {
                                playground.removeAll();
                                new mainProducts(playground);
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
                addProductButton.addMouseListener(new MouseListener() {
                        public void mouseClicked(MouseEvent e) {
                                boolean ingredientEmpty = modelSelected.getRowCount() == 0;
                                if (namePlaceholder || pricePlaceholder || ingredientEmpty) {
                                        successLabel.setText("Error. You must fill all the given fields.");
                                        return;
                                }
                                if (ingredientsQuantityNotSpecified()) {
                                        successLabel.setText(
                                                        "Error. You must specify the amount used of each ingredient.");
                                        return;
                                }
                                String date = LocalDate.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd"));
                                int catID = categories.get(categoriesComboBox.getSelectedIndex()).getId();
                                String name = nameTextField.getText();
                                Float price = Float.parseFloat(priceTextField.getText());

                                product newProduct = new productAPI().addProduct(date, catID, name, price);

                                for (int i = 0; i < modelSelected.getRowCount(); i++) {
                                        int productID = newProduct.getId();
                                        int ingredientID = Integer.parseInt((String) modelSelected.getValueAt(i, 0));
                                        Float qty = Float.parseFloat((String) modelSelected.getValueAt(i, 8));
                                        new productAPI().addIngredients(productID, ingredientID, date, qty);
                                }
                                successLabel.setText("Product \"" + name + "\" was successfully added.");
                        }

                        public void mousePressed(MouseEvent e) {
                        }

                        public void mouseReleased(MouseEvent e) {
                        }

                        public void mouseEntered(MouseEvent e) {
                                addProductButton.setBackground(new Color(23, 35, 51));
                                addProductButton.setForeground(new Color(255, 255, 255));
                        }

                        public void mouseExited(MouseEvent e) {
                                addProductButton.setBackground(new Color(255, 255, 255));
                                addProductButton.setForeground(new Color(23, 35, 51));
                        }
                });
                nameTextField.addFocusListener(new FocusListener() {
                        public void focusGained(FocusEvent e) {
                                if (nameTextField.getText().equals("Ex. \"Burguer\"")) {
                                        nameTextField.setText("");
                                        nameTextField.setForeground(Color.BLACK);
                                        namePlaceholder = false;
                                }
                        }

                        public void focusLost(FocusEvent e) {
                                if (nameTextField.getText().isEmpty()) {
                                        nameTextField.setForeground(Color.GRAY);
                                        nameTextField.setText("Ex. \"Burguer\"");
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
                unselectButton.addMouseListener(new MouseListener() {
                        public void mouseClicked(MouseEvent e) {
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