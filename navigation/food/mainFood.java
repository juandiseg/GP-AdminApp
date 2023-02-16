package navigation.food;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.event.MouseListener;

import java.awt.event.MouseEvent;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

import navigation.administration.roleSection.mainRole;
import navigation.food.allergensWindow.mainAllergen;

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
                leftAuxPanel.setBackground(new java.awt.Color(71, 120, 197));

                jPanel4.setBackground(new java.awt.Color(120, 168, 252));

                javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
                jPanel4.setLayout(jPanel4Layout);
                jPanel4Layout.setHorizontalGroup(
                                jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                .addGap(0, 0, Short.MAX_VALUE));
                jPanel4Layout.setVerticalGroup(
                                jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                .addGap(0, 67, Short.MAX_VALUE));

                menusPanel.setBackground(new java.awt.Color(71, 120, 197));

                menusAux.setPreferredSize(new java.awt.Dimension(5, 43));

                javax.swing.GroupLayout menusAuxLayout = new javax.swing.GroupLayout(menusAux);
                menusAux.setLayout(menusAuxLayout);
                menusAuxLayout.setHorizontalGroup(
                                menusAuxLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                .addGap(0, 5, Short.MAX_VALUE));
                menusAuxLayout.setVerticalGroup(
                                menusAuxLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                .addGap(0, 0, Short.MAX_VALUE));

                menusLabel.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
                menusLabel.setText("Menus");

                javax.swing.GroupLayout menusPanelLayout = new javax.swing.GroupLayout(menusPanel);
                menusPanel.setLayout(menusPanelLayout);
                menusPanelLayout.setHorizontalGroup(
                                menusPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                .addGroup(menusPanelLayout.createSequentialGroup()
                                                                .addComponent(menusAux,
                                                                                javax.swing.GroupLayout.PREFERRED_SIZE,
                                                                                javax.swing.GroupLayout.DEFAULT_SIZE,
                                                                                javax.swing.GroupLayout.PREFERRED_SIZE)
                                                                .addPreferredGap(
                                                                                javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                                                .addComponent(menusLabel,
                                                                                javax.swing.GroupLayout.PREFERRED_SIZE,
                                                                                79,
                                                                                javax.swing.GroupLayout.PREFERRED_SIZE)
                                                                .addContainerGap(134, Short.MAX_VALUE)));
                menusPanelLayout.setVerticalGroup(
                                menusPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                .addGroup(menusPanelLayout.createSequentialGroup()
                                                                .addGroup(menusPanelLayout
                                                                                .createParallelGroup(
                                                                                                javax.swing.GroupLayout.Alignment.LEADING,
                                                                                                false)
                                                                                .addComponent(menusAux,
                                                                                                javax.swing.GroupLayout.DEFAULT_SIZE,
                                                                                                javax.swing.GroupLayout.DEFAULT_SIZE,
                                                                                                Short.MAX_VALUE)
                                                                                .addComponent(menusLabel,
                                                                                                javax.swing.GroupLayout.DEFAULT_SIZE,
                                                                                                43,
                                                                                                Short.MAX_VALUE))
                                                                .addGap(0, 0, Short.MAX_VALUE)));

                productsPanel.setBackground(new java.awt.Color(71, 120, 197));

                productsAux.setPreferredSize(new java.awt.Dimension(5, 43));

                javax.swing.GroupLayout productsAuxLayout = new javax.swing.GroupLayout(productsAux);
                productsAux.setLayout(productsAuxLayout);
                productsAuxLayout.setHorizontalGroup(
                                productsAuxLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                .addGap(0, 5, Short.MAX_VALUE));
                productsAuxLayout.setVerticalGroup(
                                productsAuxLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                .addGap(0, 0, Short.MAX_VALUE));

                productsLabel.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
                productsLabel.setText("Products");

                javax.swing.GroupLayout productsPanelLayout = new javax.swing.GroupLayout(productsPanel);
                productsPanel.setLayout(productsPanelLayout);
                productsPanelLayout.setHorizontalGroup(
                                productsPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                .addGroup(productsPanelLayout.createSequentialGroup()
                                                                .addComponent(productsAux,
                                                                                javax.swing.GroupLayout.PREFERRED_SIZE,
                                                                                javax.swing.GroupLayout.DEFAULT_SIZE,
                                                                                javax.swing.GroupLayout.PREFERRED_SIZE)
                                                                .addPreferredGap(
                                                                                javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                                                .addComponent(productsLabel,
                                                                                javax.swing.GroupLayout.PREFERRED_SIZE,
                                                                                79,
                                                                                javax.swing.GroupLayout.PREFERRED_SIZE)
                                                                .addContainerGap(134, Short.MAX_VALUE)));
                productsPanelLayout.setVerticalGroup(
                                productsPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                .addGroup(productsPanelLayout.createSequentialGroup()
                                                                .addGroup(productsPanelLayout
                                                                                .createParallelGroup(
                                                                                                javax.swing.GroupLayout.Alignment.LEADING,
                                                                                                false)
                                                                                .addComponent(productsAux,
                                                                                                javax.swing.GroupLayout.DEFAULT_SIZE,
                                                                                                javax.swing.GroupLayout.DEFAULT_SIZE,
                                                                                                Short.MAX_VALUE)
                                                                                .addComponent(productsLabel,
                                                                                                javax.swing.GroupLayout.DEFAULT_SIZE,
                                                                                                43,
                                                                                                Short.MAX_VALUE))
                                                                .addGap(0, 0, Short.MAX_VALUE)));

                ingredientsPanel.setBackground(new java.awt.Color(71, 120, 197));

                ingredientsAux.setPreferredSize(new java.awt.Dimension(5, 43));

                javax.swing.GroupLayout ingredientsAuxLayout = new javax.swing.GroupLayout(ingredientsAux);
                ingredientsAux.setLayout(ingredientsAuxLayout);
                ingredientsAuxLayout.setHorizontalGroup(
                                ingredientsAuxLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                .addGap(0, 5, Short.MAX_VALUE));
                ingredientsAuxLayout.setVerticalGroup(
                                ingredientsAuxLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                .addGap(0, 0, Short.MAX_VALUE));

                ingredientsLabel.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
                ingredientsLabel.setText("Ingredients");

                javax.swing.GroupLayout ingredientsPanelLayout = new javax.swing.GroupLayout(ingredientsPanel);
                ingredientsPanel.setLayout(ingredientsPanelLayout);
                ingredientsPanelLayout.setHorizontalGroup(
                                ingredientsPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                .addGroup(ingredientsPanelLayout.createSequentialGroup()
                                                                .addComponent(ingredientsAux,
                                                                                javax.swing.GroupLayout.PREFERRED_SIZE,
                                                                                javax.swing.GroupLayout.DEFAULT_SIZE,
                                                                                javax.swing.GroupLayout.PREFERRED_SIZE)
                                                                .addPreferredGap(
                                                                                javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                                                .addComponent(ingredientsLabel,
                                                                                javax.swing.GroupLayout.PREFERRED_SIZE,
                                                                                79,
                                                                                javax.swing.GroupLayout.PREFERRED_SIZE)
                                                                .addContainerGap(134, Short.MAX_VALUE)));
                ingredientsPanelLayout.setVerticalGroup(
                                ingredientsPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                .addComponent(ingredientsAux, javax.swing.GroupLayout.DEFAULT_SIZE,
                                                                javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                                .addComponent(ingredientsLabel, javax.swing.GroupLayout.DEFAULT_SIZE,
                                                                43, Short.MAX_VALUE));

                providersPanel.setBackground(new java.awt.Color(71, 120, 197));

                providersAux.setPreferredSize(new java.awt.Dimension(5, 43));

                javax.swing.GroupLayout providersAuxLayout = new javax.swing.GroupLayout(providersAux);
                providersAux.setLayout(providersAuxLayout);
                providersAuxLayout.setHorizontalGroup(
                                providersAuxLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                .addGap(0, 5, Short.MAX_VALUE));
                providersAuxLayout.setVerticalGroup(
                                providersAuxLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                .addGap(0, 0, Short.MAX_VALUE));

                providersLabel.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
                providersLabel.setText("Providers");

                javax.swing.GroupLayout providersPanelLayout = new javax.swing.GroupLayout(providersPanel);
                providersPanel.setLayout(providersPanelLayout);
                providersPanelLayout.setHorizontalGroup(
                                providersPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                .addGroup(providersPanelLayout.createSequentialGroup()
                                                                .addComponent(providersAux,
                                                                                javax.swing.GroupLayout.PREFERRED_SIZE,
                                                                                javax.swing.GroupLayout.DEFAULT_SIZE,
                                                                                javax.swing.GroupLayout.PREFERRED_SIZE)
                                                                .addPreferredGap(
                                                                                javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                                                .addComponent(providersLabel,
                                                                                javax.swing.GroupLayout.PREFERRED_SIZE,
                                                                                79,
                                                                                javax.swing.GroupLayout.PREFERRED_SIZE)
                                                                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE,
                                                                                Short.MAX_VALUE)));
                providersPanelLayout.setVerticalGroup(
                                providersPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                .addGroup(providersPanelLayout.createSequentialGroup()
                                                                .addGroup(providersPanelLayout
                                                                                .createParallelGroup(
                                                                                                javax.swing.GroupLayout.Alignment.LEADING,
                                                                                                false)
                                                                                .addComponent(providersAux,
                                                                                                javax.swing.GroupLayout.DEFAULT_SIZE,
                                                                                                javax.swing.GroupLayout.DEFAULT_SIZE,
                                                                                                Short.MAX_VALUE)
                                                                                .addComponent(providersLabel,
                                                                                                javax.swing.GroupLayout.DEFAULT_SIZE,
                                                                                                43,
                                                                                                Short.MAX_VALUE))
                                                                .addGap(0, 0, Short.MAX_VALUE)));

                categoriesPanel.setBackground(new java.awt.Color(71, 120, 197));

                categoriesAux.setPreferredSize(new java.awt.Dimension(5, 43));

                javax.swing.GroupLayout categoriesAuxLayout = new javax.swing.GroupLayout(categoriesAux);
                categoriesAux.setLayout(categoriesAuxLayout);
                categoriesAuxLayout.setHorizontalGroup(
                                categoriesAuxLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                .addGap(0, 5, Short.MAX_VALUE));
                categoriesAuxLayout.setVerticalGroup(
                                categoriesAuxLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                .addGap(0, 0, Short.MAX_VALUE));

                categoriesLabel.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
                categoriesLabel.setText("Categories");

                javax.swing.GroupLayout categoriesPanelLayout = new javax.swing.GroupLayout(categoriesPanel);
                categoriesPanel.setLayout(categoriesPanelLayout);
                categoriesPanelLayout.setHorizontalGroup(
                                categoriesPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                .addGroup(categoriesPanelLayout.createSequentialGroup()
                                                                .addComponent(categoriesAux,
                                                                                javax.swing.GroupLayout.PREFERRED_SIZE,
                                                                                javax.swing.GroupLayout.DEFAULT_SIZE,
                                                                                javax.swing.GroupLayout.PREFERRED_SIZE)
                                                                .addPreferredGap(
                                                                                javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                                                .addComponent(categoriesLabel,
                                                                                javax.swing.GroupLayout.PREFERRED_SIZE,
                                                                                79,
                                                                                javax.swing.GroupLayout.PREFERRED_SIZE)
                                                                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE,
                                                                                Short.MAX_VALUE)));
                categoriesPanelLayout.setVerticalGroup(
                                categoriesPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                .addGroup(categoriesPanelLayout.createSequentialGroup()
                                                                .addGroup(categoriesPanelLayout
                                                                                .createParallelGroup(
                                                                                                javax.swing.GroupLayout.Alignment.LEADING,
                                                                                                false)
                                                                                .addComponent(categoriesAux,
                                                                                                javax.swing.GroupLayout.DEFAULT_SIZE,
                                                                                                javax.swing.GroupLayout.DEFAULT_SIZE,
                                                                                                Short.MAX_VALUE)
                                                                                .addComponent(categoriesLabel,
                                                                                                javax.swing.GroupLayout.DEFAULT_SIZE,
                                                                                                43,
                                                                                                Short.MAX_VALUE))
                                                                .addGap(0, 0, Short.MAX_VALUE)));

                allergensPanel.setBackground(new java.awt.Color(71, 120, 197));

                allergensAux.setPreferredSize(new java.awt.Dimension(5, 43));

                javax.swing.GroupLayout allergensAuxLayout = new javax.swing.GroupLayout(allergensAux);
                allergensAux.setLayout(allergensAuxLayout);
                allergensAuxLayout.setHorizontalGroup(
                                allergensAuxLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                .addGap(0, 5, Short.MAX_VALUE));
                allergensAuxLayout.setVerticalGroup(
                                allergensAuxLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                .addGap(0, 0, Short.MAX_VALUE));

                allergensLabel.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
                allergensLabel.setText("Allergens");

                javax.swing.GroupLayout allergensPanelLayout = new javax.swing.GroupLayout(allergensPanel);
                allergensPanel.setLayout(allergensPanelLayout);
                allergensPanelLayout.setHorizontalGroup(
                                allergensPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                .addGroup(allergensPanelLayout.createSequentialGroup()
                                                                .addComponent(allergensAux,
                                                                                javax.swing.GroupLayout.PREFERRED_SIZE,
                                                                                javax.swing.GroupLayout.DEFAULT_SIZE,
                                                                                javax.swing.GroupLayout.PREFERRED_SIZE)
                                                                .addPreferredGap(
                                                                                javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                                                .addComponent(allergensLabel,
                                                                                javax.swing.GroupLayout.PREFERRED_SIZE,
                                                                                79,
                                                                                javax.swing.GroupLayout.PREFERRED_SIZE)
                                                                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE,
                                                                                Short.MAX_VALUE)));
                allergensPanelLayout.setVerticalGroup(
                                allergensPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                .addComponent(allergensAux, javax.swing.GroupLayout.DEFAULT_SIZE,
                                                                javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                                .addComponent(allergensLabel, javax.swing.GroupLayout.DEFAULT_SIZE, 43,
                                                                Short.MAX_VALUE));

                date.setForeground(new java.awt.Color(255, 255, 255));
                date.setText("13 Feb 2023");

                javax.swing.GroupLayout leftAuxPanelLayout = new javax.swing.GroupLayout(leftAuxPanel);
                leftAuxPanel.setLayout(leftAuxPanelLayout);
                leftAuxPanelLayout.setHorizontalGroup(
                                leftAuxPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                .addComponent(jPanel4, javax.swing.GroupLayout.DEFAULT_SIZE,
                                                                javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                                .addComponent(menusPanel, javax.swing.GroupLayout.DEFAULT_SIZE,
                                                                javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                                .addComponent(ingredientsPanel, javax.swing.GroupLayout.DEFAULT_SIZE,
                                                                javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                                .addGroup(leftAuxPanelLayout.createSequentialGroup()
                                                                .addGap(80, 80, 80)
                                                                .addComponent(date)
                                                                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE,
                                                                                Short.MAX_VALUE))
                                                .addComponent(productsPanel, javax.swing.GroupLayout.DEFAULT_SIZE,
                                                                javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                                .addComponent(providersPanel, javax.swing.GroupLayout.DEFAULT_SIZE,
                                                                javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                                .addComponent(categoriesPanel, javax.swing.GroupLayout.DEFAULT_SIZE,
                                                                javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                                .addComponent(allergensPanel, javax.swing.GroupLayout.DEFAULT_SIZE,
                                                                javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE));
                leftAuxPanelLayout.setVerticalGroup(
                                leftAuxPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                .addGroup(leftAuxPanelLayout.createSequentialGroup()
                                                                .addComponent(jPanel4,
                                                                                javax.swing.GroupLayout.PREFERRED_SIZE,
                                                                                javax.swing.GroupLayout.DEFAULT_SIZE,
                                                                                javax.swing.GroupLayout.PREFERRED_SIZE)
                                                                .addGap(58, 58, 58)
                                                                .addComponent(menusPanel,
                                                                                javax.swing.GroupLayout.PREFERRED_SIZE,
                                                                                javax.swing.GroupLayout.DEFAULT_SIZE,
                                                                                javax.swing.GroupLayout.PREFERRED_SIZE)
                                                                .addPreferredGap(
                                                                                javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                                                .addComponent(productsPanel,
                                                                                javax.swing.GroupLayout.PREFERRED_SIZE,
                                                                                javax.swing.GroupLayout.DEFAULT_SIZE,
                                                                                javax.swing.GroupLayout.PREFERRED_SIZE)
                                                                .addPreferredGap(
                                                                                javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                                                .addComponent(ingredientsPanel,
                                                                                javax.swing.GroupLayout.PREFERRED_SIZE,
                                                                                javax.swing.GroupLayout.DEFAULT_SIZE,
                                                                                javax.swing.GroupLayout.PREFERRED_SIZE)
                                                                .addPreferredGap(
                                                                                javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                                                .addComponent(providersPanel,
                                                                                javax.swing.GroupLayout.PREFERRED_SIZE,
                                                                                javax.swing.GroupLayout.DEFAULT_SIZE,
                                                                                javax.swing.GroupLayout.PREFERRED_SIZE)
                                                                .addPreferredGap(
                                                                                javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                                                .addComponent(categoriesPanel,
                                                                                javax.swing.GroupLayout.PREFERRED_SIZE,
                                                                                javax.swing.GroupLayout.DEFAULT_SIZE,
                                                                                javax.swing.GroupLayout.PREFERRED_SIZE)
                                                                .addPreferredGap(
                                                                                javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                                                .addComponent(allergensPanel,
                                                                                javax.swing.GroupLayout.PREFERRED_SIZE,
                                                                                javax.swing.GroupLayout.DEFAULT_SIZE,
                                                                                javax.swing.GroupLayout.PREFERRED_SIZE)
                                                                .addPreferredGap(
                                                                                javax.swing.LayoutStyle.ComponentPlacement.RELATED,
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
                                // playground.removeAll();
                                // new main_employeesWindow(playground);
                                // playground.revalidate();
                                // playground.repaint();
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
                                // playground.removeAll();
                                // new main_employeesWindow(playground);
                                // playground.revalidate();
                                // playground.repaint();
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
                                // playground.removeAll();
                                // new main_employeesWindow(playground);
                                // playground.revalidate();
                                // playground.repaint();
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
                                // playground.removeAll();
                                // new main_employeesWindow(playground);
                                // playground.revalidate();
                                // playground.repaint();
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
                                // playground.removeAll();
                                // new main_employeesWindow(playground);
                                // playground.revalidate();
                                // playground.repaint();
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

        private boolean checkPanelSelected(JPanel thePanel) {
                Color unselectedColor = new Color(71, 120, 197);
                if (!menusPanel.getBackground().equals(unselectedColor)) {
                        if (!menusPanel.equals(thePanel))
                                return false;
                }
                if (!productsPanel.getBackground().equals(unselectedColor)) {
                        if (!productsPanel.equals(thePanel))
                                return false;
                }
                if (!ingredientsPanel.getBackground().equals(unselectedColor)) {
                        if (!ingredientsPanel.equals(thePanel))
                                return false;
                }
                if (!providersPanel.getBackground().equals(unselectedColor)) {
                        if (!providersPanel.equals(thePanel))
                                return false;
                }
                if (!categoriesPanel.getBackground().equals(unselectedColor)) {
                        if (!categoriesPanel.equals(thePanel))
                                return false;
                }
                if (!allergensPanel.getBackground().equals(unselectedColor)) {
                        if (!allergensPanel.equals(thePanel))
                                return false;
                }
                return true;

        }

        private void setColor(JPanel panel) {
                panel.setBackground(new Color(120, 168, 252));
        }

        private void resetColor(JPanel panel) {
                panel.setBackground(new Color(71, 120, 197));
        }
}