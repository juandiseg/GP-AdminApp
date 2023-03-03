package navigation.food.menusNav;

import java.awt.event.*;
import java.util.ArrayList;
import java.util.Stack;

import javax.swing.*;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;

import componentsFood.category;
import componentsFood.menu;
import componentsFood.product;
import util.databaseAPIs.categoryAPI;
import util.databaseAPIs.menuAPI;
import util.databaseAPIs.productAPI;
import util.inputFormatting.iFormatter;
import util.inputFormatting.inputFormatterFactory;
import util.listenersFormatting.booleanWrapper;
import util.listenersFormatting.iTextFieldListener;
import util.listenersFormatting.edit.editPriceFListener;
import util.listenersFormatting.edit.editTextFListener;

import java.awt.*;

public class editMenu {

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

        private JTable tableProducts = new JTable();
        private JTable tableSelected = new JTable();
        private DefaultTableModel modelProducts;
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

        private ArrayList<category> categories = new categoryAPI().getMenuCategories();

        private menu theCurrentMenu;

        public editMenu(JPanel playground, menu theCurrentMenu) {
                this.theCurrentMenu = theCurrentMenu;
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

                nameTextField.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
                nameTextField.setText(theCurrentMenu.getName());
                nameTextField.setForeground(Color.GRAY);

                editProductButton.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
                editProductButton.setText("Edit Product");
                editProductButton.setBackground(new Color(255, 255, 255));
                editProductButton.setForeground(new Color(23, 35, 51));

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

                priceTextField.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
                priceTextField.setText(Float.toString(theCurrentMenu.getPrice()));
                priceTextField.setForeground(Color.GRAY);

                categoryLabel.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
                categoryLabel.setHorizontalAlignment(SwingConstants.LEFT);
                categoryLabel.setText("Choose Category");
                categoryLabel.setVerticalAlignment(SwingConstants.BOTTOM);

                ingredientsLabel.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
                ingredientsLabel.setHorizontalAlignment(SwingConstants.LEFT);
                ingredientsLabel.setText("Select Products");
                ingredientsLabel.setVerticalAlignment(SwingConstants.BOTTOM);

                setTables();

                setComboBox();

                selectButton.setText("Select");
                selectButton.setBackground(new Color(23, 35, 51));
                selectButton.setForeground(new Color(255, 255, 255));

                unselectButton.setText("Unselect");
                unselectButton.setBackground(new Color(23, 35, 51));
                unselectButton.setForeground(new Color(255, 255, 255));

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

                backButton.setBackground(new Color(71, 120, 197));
                backButton.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
                backButton.setForeground(new Color(255, 255, 255));
                backButton.setText("Back");
                backButton.setToolTipText("");

                theProductLabel.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
                theProductLabel.setHorizontalAlignment(SwingConstants.CENTER);
                theProductLabel.setText(theCurrentMenu.getName());
                theProductLabel.setVerticalAlignment(SwingConstants.BOTTOM);

                deleteButton.setBackground(new Color(255, 102, 102));
                deleteButton.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
                deleteButton.setForeground(new Color(255, 255, 255));
                deleteButton.setText("Delete");

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
                tableProducts = new JTable();
                tableSelected = new JTable() {
                        public boolean isCellEditable(int rowIndex, int colIndex) {
                                return colIndex == 2;
                        };
                };
                modelProducts = new DefaultTableModel(
                                new String[] { "product_id", "category_id", "date", "Name", "Price", "Quantity" },
                                0);
                modelSelected = new DefaultTableModel(
                                new String[] { "product_id", "category_id", "date", "Name", "Price", "Quantity" },
                                0);

                productAPI managerDB = new productAPI();
                for (product tempIngredient : managerDB.getNonSelectedProductsInMenu(theCurrentMenu)) {
                        String prodID = Integer.toString(tempIngredient.getId());
                        String catID = Integer.toString(tempIngredient.getCategoryID());
                        String date = tempIngredient.getDate();
                        String name = tempIngredient.getName();
                        String price = Float.toString(tempIngredient.getPrice());
                        modelProducts.addRow(new String[] { prodID, catID, date, name, price, "Fill" });
                }

                for (product tempIngredient : managerDB.getSelectedProductsInMenu(theCurrentMenu)) {
                        String prodID = Integer.toString(tempIngredient.getId());
                        String catID = Integer.toString(tempIngredient.getCategoryID());
                        String date = tempIngredient.getDate();
                        String name = tempIngredient.getName();
                        String price = Float.toString(tempIngredient.getPrice());
                        String quantity = Float
                                        .toString(managerDB.getAmountOfProductInMenu(theCurrentMenu, tempIngredient));
                        modelSelected.addRow(new String[] { prodID, catID, date, name, price, quantity });
                }
                tableProducts.setModel(modelProducts);
                tableSelected.setModel(modelSelected);
                tableProducts.removeColumn(tableProducts.getColumn("product_id"));
                tableProducts.removeColumn(tableProducts.getColumn("category_id"));
                tableProducts.removeColumn(tableProducts.getColumn("date"));
                tableProducts.removeColumn(tableProducts.getColumn("Quantity"));
                tableSelected.removeColumn(tableSelected.getColumn("product_id"));
                tableSelected.removeColumn(tableSelected.getColumn("category_id"));
                tableSelected.removeColumn(tableSelected.getColumn("date"));
                unselectedJScrollPane.setViewportView(tableProducts);
                selectedJScrollPane.setViewportView(tableSelected);
                tableProducts.setDefaultEditor(Object.class, null);
                tableLookPretty(tableProducts);
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
                        if (categories.get(i).getId() == theCurrentMenu.getCategoryID())
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

        private boolean isProductPlaceholder() {
                productAPI managerDB = new productAPI();
                ArrayList<product> tempList = managerDB.getSelectedProductsInMenu(theCurrentMenu);
                ArrayList<product> listSelected = new ArrayList<product>();
                for (int i = 0; i < modelSelected.getRowCount(); i++) {
                        int prodID = Integer.parseInt((String) modelSelected.getValueAt(i, 0));
                        int catID = Integer.parseInt((String) modelSelected.getValueAt(i, 1));
                        String date = (String) modelSelected.getValueAt(i, 2);
                        String name = (String) modelSelected.getValueAt(i, 3);
                        Float price = Float.parseFloat((String) modelSelected.getValueAt(i, 4));
                        listSelected.add(new product(prodID, catID, date, name, price, true));
                }
                for (product temp : tempList) {
                        if (!listSelected.contains(temp))
                                return false;
                }
                for (product temp : listSelected) {
                        if (!tempList.contains(temp))
                                return false;
                }
                for (product temp : listSelected) {
                        for (int i = 0; i < modelSelected.getRowCount(); i++) {
                                if (Integer.parseInt((String) modelSelected.getValueAt(i, 0)) == temp.getId()) {
                                        try {
                                                float tempAmount = managerDB.getAmountOfProductInMenu(theCurrentMenu,
                                                                temp);
                                                if (Float.parseFloat(
                                                                (String) modelSelected.getValueAt(i, 5)) != tempAmount)
                                                        return false;
                                        } catch (Exception numberFormatException) {
                                                return true;
                                        }
                                }
                        }
                }

                return true;
        }

        private void renewPlaceholders() {
                theCurrentMenu = new menuAPI().getMenu(theCurrentMenu.getId());
                theProductLabel.setText(theCurrentMenu.getName());
                namePlaceholder.setValue(true);
                pricePlaceholder.setValue(true);
                nameTextField.setText(theCurrentMenu.getName());
                nameTextField.setForeground(Color.GRAY);
                priceTextField.setText(Float.toString(theCurrentMenu.getPrice()));
                priceTextField.setForeground(Color.GRAY);
                setComboBox();
                applyGenericListeners();
        }

        private void addActionListeners(JPanel playground) {
                backButton.addMouseListener(new MouseListener() {
                        public void mouseClicked(MouseEvent e) {
                                playground.removeAll();
                                new mainMenus(playground);
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
                editProductButton.addMouseListener(new MouseListener() {
                        public void mouseClicked(MouseEvent e) {

                                boolean categoryPlaceholder = categories.get(categoriesComboBox.getSelectedIndex())
                                                .getId() == theCurrentMenu.getCategoryID();
                                boolean ingredientEmpty = modelSelected.getRowCount() == 0;
                                boolean productPlaceholder = isProductPlaceholder();

                                if (ingredientEmpty || (namePlaceholder.getValue() && pricePlaceholder.getValue()
                                                && categoryPlaceholder
                                                && productPlaceholder)) {
                                        if (ingredientEmpty) {
                                                successLabel.setText(
                                                                "Error. A menu must have products.");
                                                successLabel.setVisible(true);
                                                return;
                                        }

                                        successLabel.setText(
                                                        "Error. You must alter at least one of the given fields.");
                                        successLabel.setVisible(true);

                                        return;
                                }
                                if (ingredientsQuantityNotSpecified()) {
                                        successLabel.setText(
                                                        "Error. You must specify the amount used of each product.");
                                        successLabel.setVisible(true);

                                        return;
                                }
                                menuAPI managerDB = new menuAPI();
                                boolean error = false;
                                String name = theCurrentMenu.getName();
                                if (!namePlaceholder.getValue()) {
                                        name = nameTextField.getText();
                                        if (managerDB.isNameTaken(name)) {
                                                successLabel.setText("Error. The given name is already taken.");
                                                successLabel.setVisible(true);
                                                return;
                                        }
                                        if (!managerDB.updateMenuName(theCurrentMenu.getId(), name))
                                                error = true;
                                        else
                                                theCurrentMenu = managerDB.getMenu(theCurrentMenu.getId());
                                }
                                if (!pricePlaceholder.getValue()) {
                                        Float price = Float.parseFloat(priceTextField.getText());
                                        if (!managerDB.updatePrice(theCurrentMenu, price))
                                                error = true;
                                }
                                if (!categoryPlaceholder) {
                                        int catID = categories.get(categoriesComboBox.getSelectedIndex()).getId();
                                        if (!managerDB.updateCategory(theCurrentMenu, catID))
                                                error = true;
                                }
                                if (!productPlaceholder) {
                                        Stack<Integer> stackIDs = new Stack<Integer>();
                                        Stack<Float> stackQtys = new Stack<Float>();
                                        for (int i = 0; i < modelSelected.getRowCount(); i++) {
                                                stackIDs.push(Integer
                                                                .parseInt((String) modelSelected.getValueAt(i, 0)));
                                                stackQtys.push(Float
                                                                .parseFloat((String) modelSelected.getValueAt(i, 5)));
                                        }
                                        if (!managerDB.updateProducts(theCurrentMenu, stackIDs, stackQtys))
                                                error = true;
                                }
                                if (error) {
                                        successLabel.setText("Something went wrong while updating "
                                                        + theCurrentMenu.getName());
                                } else {
                                        successLabel.setText("\"" + name + "\" has been successfully updated.");
                                }
                                successLabel.setVisible(true);
                                renewPlaceholders();
                        }

                        public void mousePressed(MouseEvent e) {
                        }

                        public void mouseReleased(MouseEvent e) {
                        }

                        public void mouseEntered(MouseEvent e) {
                                editProductButton.setBackground(new Color(23, 35, 51));
                                editProductButton.setForeground(new Color(255, 255, 255));
                        }

                        public void mouseExited(MouseEvent e) {
                                editProductButton.setBackground(new Color(255, 255, 255));
                                editProductButton.setForeground(new Color(23, 35, 51));
                        }
                });
                unselectButton.addMouseListener(new MouseListener() {
                        public void mouseClicked(MouseEvent e) {
                                int row = tableSelected.getSelectedRow();
                                if (row == -1)
                                        return;
                                String productID = (String) modelSelected.getValueAt(row, 0);
                                String catID = (String) modelSelected.getValueAt(row, 1);
                                String date = (String) modelSelected.getValueAt(row, 2);
                                String name = (String) modelSelected.getValueAt(row, 3);
                                String price = (String) modelSelected.getValueAt(row, 4);
                                String quantity = (String) modelSelected.getValueAt(row, 5);
                                modelSelected.removeRow(row);
                                modelProducts.addRow(new String[] { productID, catID, date, name, price, quantity });
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
                                int row = tableProducts.getSelectedRow();
                                if (row == -1)
                                        return;
                                String productID = (String) modelProducts.getValueAt(row, 0);
                                String catID = (String) modelProducts.getValueAt(row, 1);
                                String date = (String) modelProducts.getValueAt(row, 2);
                                String name = (String) modelProducts.getValueAt(row, 3);
                                String price = (String) modelProducts.getValueAt(row, 4);
                                String quantity = (String) modelProducts.getValueAt(row, 5);
                                modelProducts.removeRow(row);
                                modelSelected.addRow(new String[] { productID, catID, date, name, price, quantity });
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
                deleteButton.addMouseListener(new MouseListener() {
                        public void mouseClicked(MouseEvent e) {
                                int reply = JOptionPane.showConfirmDialog(null,
                                                "Are you sure you want to delete this Menu?",
                                                "Confirmation", JOptionPane.YES_NO_OPTION);
                                if (reply == JOptionPane.YES_OPTION) {
                                        if (new menuAPI().deleteMenu(theCurrentMenu)) {
                                                playground.removeAll();
                                                new mainMenus(playground);
                                                playground.revalidate();
                                                playground.repaint();
                                        } else {
                                                successLabel
                                                                .setText("Something went wrong while deleting \""
                                                                                + theCurrentMenu.getName() + "\".");
                                                successLabel.setVisible(true);
                                        }
                                }
                        }

                        public void mousePressed(MouseEvent e) {
                        }

                        public void mouseReleased(MouseEvent e) {
                        }

                        public void mouseEntered(MouseEvent e) {
                        }

                        public void mouseExited(MouseEvent e) {
                        }

                });
                applyGenericListeners();
        }

        private void applyGenericListeners() {
                iTextFieldListener numericListener = new editPriceFListener();
                iTextFieldListener textListener = new editTextFListener();
                iFormatter numericFormatter = new inputFormatterFactory().createInputFormatter("PRICE");
                textListener.applyListenerTextField(nameTextField, theCurrentMenu.getName(), namePlaceholder);
                numericListener.applyListenerTextField(priceTextField, Float.toString(theCurrentMenu.getPrice()),
                                pricePlaceholder);
                numericFormatter.applyFormat(priceTextField);
        }

        private boolean ingredientsQuantityNotSpecified() {
                for (int i = 0; i < modelSelected.getRowCount(); i++) {
                        String temp = (String) modelSelected.getValueAt(i, 5);
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