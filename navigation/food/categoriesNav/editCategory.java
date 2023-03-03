package navigation.food.categoriesNav;

import componentsFood.category;
import util.databaseAPIs.categoryAPI;
import util.listenersFormatting.booleanWrapper;
import util.listenersFormatting.iTextFieldListener;
import util.listenersFormatting.iToggleListener;
import util.listenersFormatting.edit.editTextFieldFListener;
import util.listenersFormatting.edit.editToggleAction;

import java.awt.event.*;
import javax.swing.*;
import javax.swing.LayoutStyle.ComponentPlacement;

import java.awt.*;

public class editCategory {

        private JLabel auxEditCategoryLabel = new JLabel();
        private JLabel theCategoryLabel = new JLabel();

        private JPanel jPanel1 = new JPanel();
        private JPanel jPanel2 = new JPanel();
        private JPanel jPanel3 = new JPanel();

        private JLabel nameLabel = new JLabel();
        private JLabel typeLabel = new JLabel();
        private JLabel successLabel = new JLabel();

        private JButton editCategoryButton = new JButton();
        private JButton backButton = new JButton();
        private JButton deleteButton = new JButton();

        private booleanWrapper namePlaceholder = new booleanWrapper(true);

        private JToggleButton typeToggle = new JToggleButton();

        private JTextField nameTextField = new JTextField();
        private category theCurrentCategory;

        public editCategory(JPanel playground, category theCurrentCategory) {
                this.theCurrentCategory = theCurrentCategory;
                initComponents(playground);
                addActionListeners(playground);

        }

        private void initComponents(JPanel playground) {
                playground.setBackground(new Color(255, 255, 255));

                successLabel.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
                successLabel.setHorizontalAlignment(SwingConstants.RIGHT);
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
                nameTextField.setText(theCurrentCategory.getName());
                nameTextField.setForeground(Color.GRAY);

                editCategoryButton.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
                editCategoryButton.setText("Edit Category");
                editCategoryButton.setBackground(new Color(255, 255, 255));
                editCategoryButton.setForeground(new Color(23, 35, 51));

                jPanel2.setBackground(new Color(0, 0, 0));

                GroupLayout jPanel2Layout = new GroupLayout(jPanel2);
                jPanel2.setLayout(jPanel2Layout);
                jPanel2Layout.setHorizontalGroup(
                                jPanel2Layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                                                .addGap(0, 8, Short.MAX_VALUE));
                jPanel2Layout.setVerticalGroup(
                                jPanel2Layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                                                .addGap(0, 0, Short.MAX_VALUE));

                typeLabel.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
                typeLabel.setHorizontalAlignment(SwingConstants.LEFT);
                typeLabel.setText("Type");
                typeLabel.setVerticalAlignment(SwingConstants.BOTTOM);

                typeToggle.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
                String category = "Menu Category";
                if (theCurrentCategory.getIsProduct())
                        category = "Product Category";
                typeToggle.setText(category);
                typeToggle.setBackground(new Color(255, 255, 255));
                typeToggle.setForeground(Color.GRAY);

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
                                                                                                .addGap(113, 113, 113)
                                                                                                .addGroup(jPanel1Layout
                                                                                                                .createParallelGroup(
                                                                                                                                GroupLayout.Alignment.LEADING,
                                                                                                                                false)
                                                                                                                .addComponent(nameLabel,
                                                                                                                                GroupLayout.Alignment.TRAILING,
                                                                                                                                GroupLayout.DEFAULT_SIZE,
                                                                                                                                GroupLayout.DEFAULT_SIZE,
                                                                                                                                Short.MAX_VALUE)
                                                                                                                .addComponent(typeLabel,
                                                                                                                                GroupLayout.Alignment.TRAILING,
                                                                                                                                GroupLayout.PREFERRED_SIZE,
                                                                                                                                109,
                                                                                                                                GroupLayout.PREFERRED_SIZE))
                                                                                                .addPreferredGap(
                                                                                                                ComponentPlacement.RELATED)
                                                                                                .addGroup(jPanel1Layout
                                                                                                                .createParallelGroup(
                                                                                                                                GroupLayout.Alignment.LEADING,
                                                                                                                                false)
                                                                                                                .addComponent(typeToggle,
                                                                                                                                GroupLayout.DEFAULT_SIZE,
                                                                                                                                434,
                                                                                                                                Short.MAX_VALUE)
                                                                                                                .addComponent(nameTextField))
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
                                                                                                                .addComponent(editCategoryButton,
                                                                                                                                GroupLayout.PREFERRED_SIZE,
                                                                                                                                200,
                                                                                                                                GroupLayout.PREFERRED_SIZE)
                                                                                                                .addGap(330, 330,
                                                                                                                                330)))));
                jPanel1Layout.setVerticalGroup(
                                jPanel1Layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                                                .addGroup(GroupLayout.Alignment.TRAILING, jPanel1Layout
                                                                .createSequentialGroup()
                                                                .addGap(20, 20, 20)
                                                                .addGroup(jPanel1Layout.createParallelGroup(
                                                                                GroupLayout.Alignment.BASELINE)
                                                                                .addComponent(nameLabel)
                                                                                .addComponent(nameTextField,
                                                                                                GroupLayout.PREFERRED_SIZE,
                                                                                                35,
                                                                                                GroupLayout.PREFERRED_SIZE))
                                                                .addPreferredGap(
                                                                                ComponentPlacement.RELATED)
                                                                .addGroup(jPanel1Layout
                                                                                .createParallelGroup(
                                                                                                GroupLayout.Alignment.LEADING,
                                                                                                false)
                                                                                .addComponent(typeLabel)
                                                                                .addComponent(typeToggle,
                                                                                                GroupLayout.DEFAULT_SIZE,
                                                                                                35,
                                                                                                Short.MAX_VALUE))
                                                                .addGap(42, 42, 42)
                                                                .addComponent(editCategoryButton,
                                                                                GroupLayout.PREFERRED_SIZE,
                                                                                55,
                                                                                GroupLayout.PREFERRED_SIZE)
                                                                .addContainerGap(44, Short.MAX_VALUE))
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

                deleteButton.setBackground(new Color(255, 102, 102));
                deleteButton.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
                deleteButton.setText("Delete");

                backButton.setBackground(new Color(71, 120, 197));
                backButton.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
                backButton.setForeground(new Color(255, 255, 255));
                backButton.setText("Back");

                theCategoryLabel.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
                theCategoryLabel.setHorizontalAlignment(SwingConstants.CENTER);
                theCategoryLabel.setText(theCurrentCategory.getName());
                theCategoryLabel.setVerticalAlignment(SwingConstants.BOTTOM);

                auxEditCategoryLabel.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
                auxEditCategoryLabel.setHorizontalAlignment(SwingConstants.CENTER);
                auxEditCategoryLabel.setText("Category to edit:");
                auxEditCategoryLabel.setVerticalAlignment(SwingConstants.BOTTOM);

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
                                                .addGroup(playgroundLayout.createSequentialGroup()
                                                                .addContainerGap()
                                                                .addGroup(playgroundLayout
                                                                                .createParallelGroup(
                                                                                                GroupLayout.Alignment.LEADING)
                                                                                .addComponent(theCategoryLabel,
                                                                                                GroupLayout.DEFAULT_SIZE,
                                                                                                GroupLayout.DEFAULT_SIZE,
                                                                                                Short.MAX_VALUE)
                                                                                .addComponent(auxEditCategoryLabel,
                                                                                                GroupLayout.Alignment.TRAILING,
                                                                                                GroupLayout.DEFAULT_SIZE,
                                                                                                GroupLayout.DEFAULT_SIZE,
                                                                                                Short.MAX_VALUE)
                                                                                .addGroup(playgroundLayout
                                                                                                .createSequentialGroup()
                                                                                                .addComponent(backButton,
                                                                                                                GroupLayout.PREFERRED_SIZE,
                                                                                                                200,
                                                                                                                GroupLayout.PREFERRED_SIZE)
                                                                                                .addPreferredGap(
                                                                                                                ComponentPlacement.RELATED,
                                                                                                                GroupLayout.DEFAULT_SIZE,
                                                                                                                Short.MAX_VALUE)
                                                                                                .addComponent(deleteButton,
                                                                                                                GroupLayout.PREFERRED_SIZE,
                                                                                                                200,
                                                                                                                GroupLayout.PREFERRED_SIZE))
                                                                                .addComponent(successLabel,
                                                                                                GroupLayout.Alignment.TRAILING,
                                                                                                GroupLayout.DEFAULT_SIZE,
                                                                                                GroupLayout.DEFAULT_SIZE,
                                                                                                Short.MAX_VALUE))
                                                                .addContainerGap()));
                playgroundLayout.setVerticalGroup(
                                playgroundLayout.createParallelGroup(GroupLayout.Alignment.LEADING)
                                                .addGroup(GroupLayout.Alignment.TRAILING, playgroundLayout
                                                                .createSequentialGroup()
                                                                .addGap(8, 8, 8)
                                                                .addComponent(auxEditCategoryLabel)
                                                                .addPreferredGap(
                                                                                ComponentPlacement.RELATED)
                                                                .addComponent(theCategoryLabel)
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
                                                                .addPreferredGap(
                                                                                ComponentPlacement.RELATED)
                                                                .addComponent(successLabel)
                                                                .addPreferredGap(
                                                                                ComponentPlacement.RELATED,
                                                                                208,
                                                                                Short.MAX_VALUE)
                                                                .addGroup(
                                                                                playgroundLayout.createParallelGroup(
                                                                                                GroupLayout.Alignment.BASELINE)
                                                                                                .addComponent(deleteButton,
                                                                                                                GroupLayout.PREFERRED_SIZE,
                                                                                                                55,
                                                                                                                GroupLayout.PREFERRED_SIZE)
                                                                                                .addComponent(backButton,
                                                                                                                GroupLayout.PREFERRED_SIZE,
                                                                                                                55,
                                                                                                                GroupLayout.PREFERRED_SIZE))
                                                                .addContainerGap()));
        }

        private void addActionListeners(JPanel playground) {
                backButton.addMouseListener(new MouseListener() {
                        public void mouseClicked(MouseEvent e) {
                                playground.removeAll();
                                new mainCategories(playground);
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
                editCategoryButton.addMouseListener(new MouseListener() {
                        public void mouseClicked(MouseEvent e) {
                                String originalType = "Menu Category";
                                if (theCurrentCategory.getIsProduct())
                                        originalType = "Product Category";
                                if (namePlaceholder.getValue() && typeToggle.getText().equals(originalType)) {
                                        successLabel.setText(
                                                        "At least one of the fields must be modified to edit the category.");
                                        successLabel.setVisible(true);
                                        return;
                                }
                                categoryAPI theManagerDB = new categoryAPI();
                                boolean successfulUpdate = true;
                                if (!namePlaceholder.getValue()) {
                                        String name = nameTextField.getText();
                                        if (theManagerDB.isNameTaken(name)) {
                                                successLabel.setText("Error. The given name is already taken.");
                                                successLabel.setVisible(true);
                                                return;
                                        }
                                        successfulUpdate = theManagerDB.updateName(theCurrentCategory.getId(), name);
                                }
                                if (!typeToggle.getText().equals(originalType)) {
                                        Boolean isProduct = typeToggle.getText().equals("Product Category");
                                        if (theManagerDB.updateType(theCurrentCategory.getId(), isProduct) == false) {
                                                JOptionPane.showMessageDialog(playground,
                                                                "To edit a category's type this one must not be assigned to any products/menus.\nPlease check that before editting.",
                                                                "ERROR", JOptionPane.ERROR_MESSAGE);
                                                successfulUpdate = false;
                                        } else
                                                successfulUpdate = true;
                                }
                                if (successfulUpdate) {

                                        successLabel.setText(
                                                        "The category '" + theCurrentCategory.getName()
                                                                        + "' has been successfully updated.");
                                        renewPlaceholders();
                                } else
                                        successLabel.setText("Something went wrong while updating the category.");
                                successLabel.setVisible(true);

                        }

                        public void mousePressed(MouseEvent e) {
                        }

                        public void mouseReleased(MouseEvent e) {
                        }

                        public void mouseEntered(MouseEvent e) {
                                editCategoryButton.setBackground(new Color(23, 35, 51));
                                editCategoryButton.setForeground(new Color(255, 255, 255));
                        }

                        public void mouseExited(MouseEvent e) {
                                editCategoryButton.setBackground(new Color(255, 255, 255));
                                editCategoryButton.setForeground(new Color(23, 35, 51));
                        }
                });
                deleteButton.addMouseListener(new MouseListener() {
                        public void mouseClicked(MouseEvent e) {
                                int reply = JOptionPane.showConfirmDialog(null,
                                                "Are you sure you want to delete " + theCurrentCategory.getName() +
                                                                "?",
                                                "Confirmation", JOptionPane.YES_NO_OPTION);
                                if (reply == JOptionPane.YES_OPTION) {
                                        if (!new categoryAPI().deleteCategory(theCurrentCategory.getId())) {
                                                JOptionPane.showMessageDialog(playground,
                                                                "A category cannot be deleted if there are products/menus still assigned to it.",
                                                                "Error",
                                                                JOptionPane.ERROR_MESSAGE);
                                                return;
                                        }
                                        playground.removeAll();
                                        new mainCategories(playground);
                                        playground.revalidate();
                                        playground.repaint();
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
                applyGenericListeners();
        }

        private void applyGenericListeners() {
                iTextFieldListener textListener = new editTextFieldFListener();
                textListener.applyListenerTextField(nameTextField, theCurrentCategory.getName(), namePlaceholder);

                iToggleListener toggleListener = new editToggleAction();
                String ifTrue = "Product Category";
                String ifFalse = "Menu Category";
                toggleListener.applyActionListenerToggle(typeToggle, ifTrue, ifFalse,
                                theCurrentCategory.getIsProduct());
        }

        private void renewPlaceholders() {
                namePlaceholder.setValue(true);
                theCurrentCategory = new categoryAPI().getCategory(theCurrentCategory.getId());
                theCategoryLabel.setText(theCurrentCategory.getName());
                nameTextField.setForeground(Color.GRAY);
                typeToggle.setForeground(Color.GRAY);
                applyGenericListeners();
        }
}