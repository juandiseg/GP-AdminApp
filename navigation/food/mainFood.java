package navigation.food;

import java.awt.Dimension;
import java.awt.Color;

import java.awt.event.MouseListener;

import java.awt.event.MouseEvent;

import javax.swing.GroupLayout;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.LayoutStyle.ComponentPlacement;

import navigation.food.allergensWindow.mainAllergen;
import navigation.food.categoryWindow.mainCategory;
import navigation.food.ingredientsWindow.mainIngredients;
import navigation.food.menuWindow.mainMenus;
import navigation.food.productsWindow.mainProducts;
import navigation.food.providersWindow.mainProvider;

public class mainFood {

        private JPanel menusPanel = new JPanel();
        private JPanel productsPanel = new JPanel();
        private JPanel ingredientsPanel = new JPanel();
        private JPanel providersPanel = new JPanel();
        private JPanel categoriesPanel = new JPanel();
        private JPanel allergensPanel = new JPanel();

        private JLabel menusLabel = new JLabel();
        private JLabel productsLabel = new JLabel();
        private JLabel ingredientsLabel = new JLabel();
        private JLabel providersLabel = new JLabel();
        private JLabel categoriesLabel = new JLabel();
        private JLabel allergensLabel = new JLabel();

        private JPanel menusAux = new JPanel();
        private JPanel productsAux = new JPanel();
        private JPanel ingredientsAux = new JPanel();
        private JPanel providersAux = new JPanel();
        private JPanel categoriesAux = new JPanel();
        private JPanel allergensAux = new JPanel();

        public mainFood(JPanel leftAuxPanel, JPanel playground, JPanel jPanel4,
                        JLabel date) {
                initComp2(leftAuxPanel, jPanel4, date);
                addActionListeners(playground);
        }

        public void initComp2(JPanel leftAuxPanel, JPanel jPanel4, JLabel date) {
                leftAuxPanel.setBackground(new Color(71, 120, 197));

                jPanel4.setBackground(new Color(120, 168, 252));

                GroupLayout jPanel4Layout = new GroupLayout(jPanel4);
                jPanel4.setLayout(jPanel4Layout);
                jPanel4Layout.setHorizontalGroup(
                                jPanel4Layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                                                .addGap(0, 0, Short.MAX_VALUE));
                jPanel4Layout.setVerticalGroup(
                                jPanel4Layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                                                .addGap(0, 67, Short.MAX_VALUE));

                menusPanel.setBackground(new Color(71, 120, 197));

                menusAux.setPreferredSize(new Dimension(5, 43));

                GroupLayout menusAuxLayout = new GroupLayout(menusAux);
                menusAux.setLayout(menusAuxLayout);
                menusAuxLayout.setHorizontalGroup(
                                menusAuxLayout.createParallelGroup(GroupLayout.Alignment.LEADING)
                                                .addGap(0, 5, Short.MAX_VALUE));
                menusAuxLayout.setVerticalGroup(
                                menusAuxLayout.createParallelGroup(GroupLayout.Alignment.LEADING)
                                                .addGap(0, 0, Short.MAX_VALUE));

                menusLabel.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
                menusLabel.setText("Menus");

                GroupLayout menusPanelLayout = new GroupLayout(menusPanel);
                menusPanel.setLayout(menusPanelLayout);
                menusPanelLayout.setHorizontalGroup(
                                menusPanelLayout.createParallelGroup(GroupLayout.Alignment.LEADING)
                                                .addGroup(menusPanelLayout.createSequentialGroup()
                                                                .addComponent(menusAux,
                                                                                GroupLayout.PREFERRED_SIZE,
                                                                                GroupLayout.DEFAULT_SIZE,
                                                                                GroupLayout.PREFERRED_SIZE)
                                                                .addPreferredGap(
                                                                                ComponentPlacement.UNRELATED)
                                                                .addComponent(menusLabel,
                                                                                GroupLayout.PREFERRED_SIZE,
                                                                                79,
                                                                                GroupLayout.PREFERRED_SIZE)
                                                                .addContainerGap(134, Short.MAX_VALUE)));
                menusPanelLayout.setVerticalGroup(
                                menusPanelLayout.createParallelGroup(GroupLayout.Alignment.LEADING)
                                                .addGroup(menusPanelLayout.createSequentialGroup()
                                                                .addGroup(menusPanelLayout
                                                                                .createParallelGroup(
                                                                                                GroupLayout.Alignment.LEADING,
                                                                                                false)
                                                                                .addComponent(menusAux,
                                                                                                GroupLayout.DEFAULT_SIZE,
                                                                                                GroupLayout.DEFAULT_SIZE,
                                                                                                Short.MAX_VALUE)
                                                                                .addComponent(menusLabel,
                                                                                                GroupLayout.DEFAULT_SIZE,
                                                                                                43,
                                                                                                Short.MAX_VALUE))
                                                                .addGap(0, 0, Short.MAX_VALUE)));

                productsPanel.setBackground(new Color(71, 120, 197));

                productsAux.setPreferredSize(new Dimension(5, 43));

                GroupLayout productsAuxLayout = new GroupLayout(productsAux);
                productsAux.setLayout(productsAuxLayout);
                productsAuxLayout.setHorizontalGroup(
                                productsAuxLayout.createParallelGroup(GroupLayout.Alignment.LEADING)
                                                .addGap(0, 5, Short.MAX_VALUE));
                productsAuxLayout.setVerticalGroup(
                                productsAuxLayout.createParallelGroup(GroupLayout.Alignment.LEADING)
                                                .addGap(0, 0, Short.MAX_VALUE));

                productsLabel.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
                productsLabel.setText("Products");

                GroupLayout productsPanelLayout = new GroupLayout(productsPanel);
                productsPanel.setLayout(productsPanelLayout);
                productsPanelLayout.setHorizontalGroup(
                                productsPanelLayout.createParallelGroup(GroupLayout.Alignment.LEADING)
                                                .addGroup(productsPanelLayout.createSequentialGroup()
                                                                .addComponent(productsAux,
                                                                                GroupLayout.PREFERRED_SIZE,
                                                                                GroupLayout.DEFAULT_SIZE,
                                                                                GroupLayout.PREFERRED_SIZE)
                                                                .addPreferredGap(
                                                                                ComponentPlacement.UNRELATED)
                                                                .addComponent(productsLabel,
                                                                                GroupLayout.PREFERRED_SIZE,
                                                                                79,
                                                                                GroupLayout.PREFERRED_SIZE)
                                                                .addContainerGap(134, Short.MAX_VALUE)));
                productsPanelLayout.setVerticalGroup(
                                productsPanelLayout.createParallelGroup(GroupLayout.Alignment.LEADING)
                                                .addGroup(productsPanelLayout.createSequentialGroup()
                                                                .addGroup(productsPanelLayout
                                                                                .createParallelGroup(
                                                                                                GroupLayout.Alignment.LEADING,
                                                                                                false)
                                                                                .addComponent(productsAux,
                                                                                                GroupLayout.DEFAULT_SIZE,
                                                                                                GroupLayout.DEFAULT_SIZE,
                                                                                                Short.MAX_VALUE)
                                                                                .addComponent(productsLabel,
                                                                                                GroupLayout.DEFAULT_SIZE,
                                                                                                43,
                                                                                                Short.MAX_VALUE))
                                                                .addGap(0, 0, Short.MAX_VALUE)));

                ingredientsPanel.setBackground(new Color(71, 120, 197));

                ingredientsAux.setPreferredSize(new Dimension(5, 43));

                GroupLayout ingredientsAuxLayout = new GroupLayout(ingredientsAux);
                ingredientsAux.setLayout(ingredientsAuxLayout);
                ingredientsAuxLayout.setHorizontalGroup(
                                ingredientsAuxLayout.createParallelGroup(GroupLayout.Alignment.LEADING)
                                                .addGap(0, 5, Short.MAX_VALUE));
                ingredientsAuxLayout.setVerticalGroup(
                                ingredientsAuxLayout.createParallelGroup(GroupLayout.Alignment.LEADING)
                                                .addGap(0, 0, Short.MAX_VALUE));

                ingredientsLabel.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
                ingredientsLabel.setText("Ingredients");

                GroupLayout ingredientsPanelLayout = new GroupLayout(ingredientsPanel);
                ingredientsPanel.setLayout(ingredientsPanelLayout);
                ingredientsPanelLayout.setHorizontalGroup(
                                ingredientsPanelLayout.createParallelGroup(GroupLayout.Alignment.LEADING)
                                                .addGroup(ingredientsPanelLayout.createSequentialGroup()
                                                                .addComponent(ingredientsAux,
                                                                                GroupLayout.PREFERRED_SIZE,
                                                                                GroupLayout.DEFAULT_SIZE,
                                                                                GroupLayout.PREFERRED_SIZE)
                                                                .addPreferredGap(
                                                                                ComponentPlacement.UNRELATED)
                                                                .addComponent(ingredientsLabel,
                                                                                GroupLayout.PREFERRED_SIZE,
                                                                                79,
                                                                                GroupLayout.PREFERRED_SIZE)
                                                                .addContainerGap(134, Short.MAX_VALUE)));
                ingredientsPanelLayout.setVerticalGroup(
                                ingredientsPanelLayout.createParallelGroup(GroupLayout.Alignment.LEADING)
                                                .addComponent(ingredientsAux, GroupLayout.DEFAULT_SIZE,
                                                                GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                                .addComponent(ingredientsLabel, GroupLayout.DEFAULT_SIZE,
                                                                43, Short.MAX_VALUE));

                providersPanel.setBackground(new Color(71, 120, 197));

                providersAux.setPreferredSize(new Dimension(5, 43));

                GroupLayout providersAuxLayout = new GroupLayout(providersAux);
                providersAux.setLayout(providersAuxLayout);
                providersAuxLayout.setHorizontalGroup(
                                providersAuxLayout.createParallelGroup(GroupLayout.Alignment.LEADING)
                                                .addGap(0, 5, Short.MAX_VALUE));
                providersAuxLayout.setVerticalGroup(
                                providersAuxLayout.createParallelGroup(GroupLayout.Alignment.LEADING)
                                                .addGap(0, 0, Short.MAX_VALUE));

                providersLabel.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
                providersLabel.setText("Providers");

                GroupLayout providersPanelLayout = new GroupLayout(providersPanel);
                providersPanel.setLayout(providersPanelLayout);
                providersPanelLayout.setHorizontalGroup(
                                providersPanelLayout.createParallelGroup(GroupLayout.Alignment.LEADING)
                                                .addGroup(providersPanelLayout.createSequentialGroup()
                                                                .addComponent(providersAux,
                                                                                GroupLayout.PREFERRED_SIZE,
                                                                                GroupLayout.DEFAULT_SIZE,
                                                                                GroupLayout.PREFERRED_SIZE)
                                                                .addPreferredGap(
                                                                                ComponentPlacement.UNRELATED)
                                                                .addComponent(providersLabel,
                                                                                GroupLayout.PREFERRED_SIZE,
                                                                                79,
                                                                                GroupLayout.PREFERRED_SIZE)
                                                                .addContainerGap(GroupLayout.DEFAULT_SIZE,
                                                                                Short.MAX_VALUE)));
                providersPanelLayout.setVerticalGroup(
                                providersPanelLayout.createParallelGroup(GroupLayout.Alignment.LEADING)
                                                .addGroup(providersPanelLayout.createSequentialGroup()
                                                                .addGroup(providersPanelLayout
                                                                                .createParallelGroup(
                                                                                                GroupLayout.Alignment.LEADING,
                                                                                                false)
                                                                                .addComponent(providersAux,
                                                                                                GroupLayout.DEFAULT_SIZE,
                                                                                                GroupLayout.DEFAULT_SIZE,
                                                                                                Short.MAX_VALUE)
                                                                                .addComponent(providersLabel,
                                                                                                GroupLayout.DEFAULT_SIZE,
                                                                                                43,
                                                                                                Short.MAX_VALUE))
                                                                .addGap(0, 0, Short.MAX_VALUE)));

                categoriesPanel.setBackground(new Color(71, 120, 197));

                categoriesAux.setPreferredSize(new Dimension(5, 43));

                GroupLayout categoriesAuxLayout = new GroupLayout(categoriesAux);
                categoriesAux.setLayout(categoriesAuxLayout);
                categoriesAuxLayout.setHorizontalGroup(
                                categoriesAuxLayout.createParallelGroup(GroupLayout.Alignment.LEADING)
                                                .addGap(0, 5, Short.MAX_VALUE));
                categoriesAuxLayout.setVerticalGroup(
                                categoriesAuxLayout.createParallelGroup(GroupLayout.Alignment.LEADING)
                                                .addGap(0, 0, Short.MAX_VALUE));

                categoriesLabel.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
                categoriesLabel.setText("Categories");

                GroupLayout categoriesPanelLayout = new GroupLayout(categoriesPanel);
                categoriesPanel.setLayout(categoriesPanelLayout);
                categoriesPanelLayout.setHorizontalGroup(
                                categoriesPanelLayout.createParallelGroup(GroupLayout.Alignment.LEADING)
                                                .addGroup(categoriesPanelLayout.createSequentialGroup()
                                                                .addComponent(categoriesAux,
                                                                                GroupLayout.PREFERRED_SIZE,
                                                                                GroupLayout.DEFAULT_SIZE,
                                                                                GroupLayout.PREFERRED_SIZE)
                                                                .addPreferredGap(
                                                                                ComponentPlacement.UNRELATED)
                                                                .addComponent(categoriesLabel,
                                                                                GroupLayout.PREFERRED_SIZE,
                                                                                79,
                                                                                GroupLayout.PREFERRED_SIZE)
                                                                .addContainerGap(GroupLayout.DEFAULT_SIZE,
                                                                                Short.MAX_VALUE)));
                categoriesPanelLayout.setVerticalGroup(
                                categoriesPanelLayout.createParallelGroup(GroupLayout.Alignment.LEADING)
                                                .addGroup(categoriesPanelLayout.createSequentialGroup()
                                                                .addGroup(categoriesPanelLayout
                                                                                .createParallelGroup(
                                                                                                GroupLayout.Alignment.LEADING,
                                                                                                false)
                                                                                .addComponent(categoriesAux,
                                                                                                GroupLayout.DEFAULT_SIZE,
                                                                                                GroupLayout.DEFAULT_SIZE,
                                                                                                Short.MAX_VALUE)
                                                                                .addComponent(categoriesLabel,
                                                                                                GroupLayout.DEFAULT_SIZE,
                                                                                                43,
                                                                                                Short.MAX_VALUE))
                                                                .addGap(0, 0, Short.MAX_VALUE)));

                allergensPanel.setBackground(new Color(71, 120, 197));

                allergensAux.setPreferredSize(new Dimension(5, 43));

                GroupLayout allergensAuxLayout = new GroupLayout(allergensAux);
                allergensAux.setLayout(allergensAuxLayout);
                allergensAuxLayout.setHorizontalGroup(
                                allergensAuxLayout.createParallelGroup(GroupLayout.Alignment.LEADING)
                                                .addGap(0, 5, Short.MAX_VALUE));
                allergensAuxLayout.setVerticalGroup(
                                allergensAuxLayout.createParallelGroup(GroupLayout.Alignment.LEADING)
                                                .addGap(0, 0, Short.MAX_VALUE));

                allergensLabel.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
                allergensLabel.setText("Allergens");

                GroupLayout allergensPanelLayout = new GroupLayout(allergensPanel);
                allergensPanel.setLayout(allergensPanelLayout);
                allergensPanelLayout.setHorizontalGroup(
                                allergensPanelLayout.createParallelGroup(GroupLayout.Alignment.LEADING)
                                                .addGroup(allergensPanelLayout.createSequentialGroup()
                                                                .addComponent(allergensAux,
                                                                                GroupLayout.PREFERRED_SIZE,
                                                                                GroupLayout.DEFAULT_SIZE,
                                                                                GroupLayout.PREFERRED_SIZE)
                                                                .addPreferredGap(
                                                                                ComponentPlacement.UNRELATED)
                                                                .addComponent(allergensLabel,
                                                                                GroupLayout.PREFERRED_SIZE,
                                                                                79,
                                                                                GroupLayout.PREFERRED_SIZE)
                                                                .addContainerGap(GroupLayout.DEFAULT_SIZE,
                                                                                Short.MAX_VALUE)));
                allergensPanelLayout.setVerticalGroup(
                                allergensPanelLayout.createParallelGroup(GroupLayout.Alignment.LEADING)
                                                .addComponent(allergensAux, GroupLayout.DEFAULT_SIZE,
                                                                GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                                .addComponent(allergensLabel, GroupLayout.DEFAULT_SIZE, 43,
                                                                Short.MAX_VALUE));

                date.setForeground(new Color(255, 255, 255));
                date.setText("13 Feb 2023");

                GroupLayout leftAuxPanelLayout = new GroupLayout(leftAuxPanel);
                leftAuxPanel.setLayout(leftAuxPanelLayout);
                leftAuxPanelLayout.setHorizontalGroup(
                                leftAuxPanelLayout.createParallelGroup(GroupLayout.Alignment.LEADING)
                                                .addComponent(jPanel4, GroupLayout.DEFAULT_SIZE,
                                                                GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                                .addComponent(menusPanel, GroupLayout.DEFAULT_SIZE,
                                                                GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                                .addComponent(ingredientsPanel, GroupLayout.DEFAULT_SIZE,
                                                                GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                                .addGroup(leftAuxPanelLayout.createSequentialGroup()
                                                                .addGap(80, 80, 80)
                                                                .addComponent(date)
                                                                .addContainerGap(GroupLayout.DEFAULT_SIZE,
                                                                                Short.MAX_VALUE))
                                                .addComponent(productsPanel, GroupLayout.DEFAULT_SIZE,
                                                                GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                                .addComponent(providersPanel, GroupLayout.DEFAULT_SIZE,
                                                                GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                                .addComponent(categoriesPanel, GroupLayout.DEFAULT_SIZE,
                                                                GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                                .addComponent(allergensPanel, GroupLayout.DEFAULT_SIZE,
                                                                GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE));
                leftAuxPanelLayout.setVerticalGroup(
                                leftAuxPanelLayout.createParallelGroup(GroupLayout.Alignment.LEADING)
                                                .addGroup(leftAuxPanelLayout.createSequentialGroup()
                                                                .addComponent(jPanel4,
                                                                                GroupLayout.PREFERRED_SIZE,
                                                                                GroupLayout.DEFAULT_SIZE,
                                                                                GroupLayout.PREFERRED_SIZE)
                                                                .addGap(58, 58, 58)
                                                                .addComponent(menusPanel,
                                                                                GroupLayout.PREFERRED_SIZE,
                                                                                GroupLayout.DEFAULT_SIZE,
                                                                                GroupLayout.PREFERRED_SIZE)
                                                                .addPreferredGap(
                                                                                ComponentPlacement.RELATED)
                                                                .addComponent(productsPanel,
                                                                                GroupLayout.PREFERRED_SIZE,
                                                                                GroupLayout.DEFAULT_SIZE,
                                                                                GroupLayout.PREFERRED_SIZE)
                                                                .addPreferredGap(
                                                                                ComponentPlacement.RELATED)
                                                                .addComponent(ingredientsPanel,
                                                                                GroupLayout.PREFERRED_SIZE,
                                                                                GroupLayout.DEFAULT_SIZE,
                                                                                GroupLayout.PREFERRED_SIZE)
                                                                .addPreferredGap(
                                                                                ComponentPlacement.RELATED)
                                                                .addComponent(providersPanel,
                                                                                GroupLayout.PREFERRED_SIZE,
                                                                                GroupLayout.DEFAULT_SIZE,
                                                                                GroupLayout.PREFERRED_SIZE)
                                                                .addPreferredGap(
                                                                                ComponentPlacement.RELATED)
                                                                .addComponent(categoriesPanel,
                                                                                GroupLayout.PREFERRED_SIZE,
                                                                                GroupLayout.DEFAULT_SIZE,
                                                                                GroupLayout.PREFERRED_SIZE)
                                                                .addPreferredGap(
                                                                                ComponentPlacement.RELATED)
                                                                .addComponent(allergensPanel,
                                                                                GroupLayout.PREFERRED_SIZE,
                                                                                GroupLayout.DEFAULT_SIZE,
                                                                                GroupLayout.PREFERRED_SIZE)
                                                                .addPreferredGap(
                                                                                ComponentPlacement.RELATED,
                                                                                195,
                                                                                Short.MAX_VALUE)
                                                                .addComponent(date)
                                                                .addContainerGap()));
        }

        private void addActionListeners(JPanel playground) {
                menusPanel.addMouseListener(new MouseListener() {
                        boolean clicked = false;

                        public void mouseClicked(MouseEvent e) {
                                setColor(menusPanel);
                                resetColor(productsPanel);
                                resetColor(ingredientsPanel);
                                resetColor(providersPanel);
                                resetColor(categoriesPanel);
                                resetColor(allergensPanel);
                                clicked = true;
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
                                if (menusPanel.getBackground().equals(new Color(120, 168, 252)))
                                        clicked = true;
                                else
                                        clicked = false;
                                setColor(menusPanel);
                        }

                        public void mouseExited(MouseEvent e) {
                                if (!clicked)
                                        resetColor(menusPanel);
                        }
                });
                productsPanel.addMouseListener(new MouseListener() {
                        boolean clicked = false;

                        public void mouseClicked(MouseEvent e) {
                                setColor(productsPanel);
                                resetColor(menusPanel);
                                resetColor(ingredientsPanel);
                                resetColor(providersPanel);
                                resetColor(categoriesPanel);
                                resetColor(allergensPanel);
                                clicked = true;
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
                                if (productsPanel.getBackground().equals(new Color(120, 168, 252)))
                                        clicked = true;
                                else
                                        clicked = false;
                                setColor(productsPanel);
                        }

                        public void mouseExited(MouseEvent e) {
                                if (!clicked)
                                        resetColor(productsPanel);
                        }
                });
                ingredientsPanel.addMouseListener(new MouseListener() {
                        boolean clicked = false;

                        public void mouseClicked(MouseEvent e) {
                                setColor(ingredientsPanel);
                                resetColor(menusPanel);
                                resetColor(productsPanel);
                                resetColor(providersPanel);
                                resetColor(categoriesPanel);
                                resetColor(allergensPanel);
                                clicked = true;
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
                                if (ingredientsPanel.getBackground().equals(new Color(120, 168, 252)))
                                        clicked = true;
                                else
                                        clicked = false;
                                setColor(ingredientsPanel);
                        }

                        public void mouseExited(MouseEvent e) {
                                if (!clicked)
                                        resetColor(ingredientsPanel);
                        }
                });
                providersPanel.addMouseListener(new MouseListener() {
                        boolean clicked = false;

                        public void mouseClicked(MouseEvent e) {
                                setColor(productsPanel);
                                resetColor(menusPanel);
                                resetColor(productsPanel);
                                resetColor(ingredientsPanel);
                                resetColor(categoriesPanel);
                                resetColor(allergensPanel);
                                clicked = true;
                                playground.removeAll();
                                new mainProvider(playground);
                                playground.revalidate();
                                playground.repaint();
                        }

                        public void mousePressed(MouseEvent e) {
                        }

                        public void mouseReleased(MouseEvent e) {
                        }

                        public void mouseEntered(MouseEvent e) {
                                if (providersPanel.getBackground().equals(new Color(120, 168, 252)))
                                        clicked = true;
                                else
                                        clicked = false;
                                setColor(providersPanel);
                        }

                        public void mouseExited(MouseEvent e) {
                                if (!clicked)
                                        resetColor(providersPanel);
                        }
                });
                categoriesPanel.addMouseListener(new MouseListener() {
                        boolean clicked = false;

                        public void mouseClicked(MouseEvent e) {
                                setColor(categoriesPanel);
                                resetColor(menusPanel);
                                resetColor(productsPanel);
                                resetColor(ingredientsPanel);
                                resetColor(providersPanel);
                                resetColor(allergensPanel);
                                clicked = true;
                                playground.removeAll();
                                new mainCategory(playground);
                                playground.revalidate();
                                playground.repaint();
                        }

                        public void mousePressed(MouseEvent e) {
                        }

                        public void mouseReleased(MouseEvent e) {
                        }

                        public void mouseEntered(MouseEvent e) {
                                if (categoriesPanel.getBackground().equals(new Color(120, 168, 252)))
                                        clicked = true;
                                else
                                        clicked = false;
                                setColor(categoriesPanel);
                        }

                        public void mouseExited(MouseEvent e) {
                                if (!clicked)
                                        resetColor(categoriesPanel);
                        }
                });
                allergensPanel.addMouseListener(new MouseListener() {
                        boolean clicked = false;

                        public void mouseClicked(MouseEvent e) {
                                setColor(allergensPanel);
                                resetColor(menusPanel);
                                resetColor(productsPanel);
                                resetColor(ingredientsPanel);
                                resetColor(providersPanel);
                                resetColor(categoriesPanel);
                                clicked = true;
                                playground.removeAll();
                                new mainAllergen(playground);
                                playground.revalidate();
                                playground.repaint();
                        }

                        public void mousePressed(MouseEvent e) {
                        }

                        public void mouseReleased(MouseEvent e) {
                        }

                        public void mouseEntered(MouseEvent e) {
                                if (allergensPanel.getBackground().equals(new Color(120, 168, 252)))
                                        clicked = true;
                                else
                                        clicked = false;
                                setColor(allergensPanel);
                        }

                        public void mouseExited(MouseEvent e) {
                                if (!clicked)
                                        resetColor(allergensPanel);
                        }
                });
        }

        private void setColor(JPanel panel) {
                panel.setBackground(new Color(120, 168, 252));
        }

        private void resetColor(JPanel panel) {
                panel.setBackground(new Color(71, 120, 197));
        }
}