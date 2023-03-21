package navigation.food;

import java.awt.*;

import java.awt.event.MouseListener;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Locale;
import java.awt.event.MouseEvent;

import javax.swing.GroupLayout;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JToggleButton;
import javax.swing.border.LineBorder;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.data.category.DefaultCategoryDataset;
import org.jfree.data.general.DefaultPieDataset;

import navigation.food.allergensNav.mainAllergens;
import navigation.food.categoriesNav.mainCategories;
import navigation.food.ingredientsNav.mainIngredients;
import navigation.food.menusNav.mainMenus;
import navigation.food.productsNav.mainProducts;
import navigation.food.providersNav.mainProviders;
import util.buttonFormatters.dashToggleFormatter;
import util.buttonFormatters.iDashToggleFormatter;
import util.databaseAPIs.dashboardsAPI;
import util.databaseAPIs.dashboardsAPI.catSalesTuple;

public class foodDash {

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

        private JPanel productDistPanel = new JPanel();
        private JPanel headProductDistPanel = new JPanel();
        private JPanel productDistContentPanel = new JPanel();
        private JPanel menuDistPanel = new JPanel();
        private JPanel headMenuDistPanel = new JPanel();
        private JPanel menuDistContentPanel = new JPanel();
        private JPanel categorySalesPanel = new JPanel();
        private JPanel headCategorySales = new JPanel();
        private JPanel categorySalesContentPanel = new JPanel();
        private JLabel productDistLabel = new JLabel();
        private JLabel menuDistLabel = new JLabel();
        private JLabel categorySalesLabel = new JLabel();
        private JToggleButton productDistTuggleButton = new JToggleButton();
        private JToggleButton menuDistTuggleButton = new JToggleButton();
        private JToggleButton categorySalesTuggleButton = new JToggleButton();

        public foodDash(JPanel leftAuxPanel, JPanel playground, JPanel jPanel4,
                        JLabel date) {
                initComp2(leftAuxPanel, jPanel4, date);
                initComp3(playground);
                setProductDistribution(false);
                setMenuDistribution(false);
                setGraphSales(false);
                addListeners(playground);
        }

        public void initComp2(JPanel leftAuxPanel, JPanel jPanel4, JLabel date) {
                leftAuxPanel.setBackground(new Color(71, 120, 197));

                menusPanel.setBackground(new Color(71, 120, 197));

                menusAux.setPreferredSize(new Dimension(5, 43));

                javax.swing.GroupLayout menusAuxLayout = new GroupLayout(menusAux);
                menusAux.setLayout(menusAuxLayout);
                menusAuxLayout.setHorizontalGroup(
                                menusAuxLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                .addGap(0, 5, Short.MAX_VALUE));
                menusAuxLayout.setVerticalGroup(
                                menusAuxLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                .addGap(0, 0, Short.MAX_VALUE));

                menusLabel.setFont(new Font("Segoe UI", 0, 14)); // NOI18N
                menusLabel.setText("Menus");

                javax.swing.GroupLayout menusPanelLayout = new GroupLayout(menusPanel);
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
                                                                                javax.swing.GroupLayout.DEFAULT_SIZE,
                                                                                javax.swing.GroupLayout.DEFAULT_SIZE,
                                                                                Short.MAX_VALUE)
                                                                .addContainerGap()));
                menusPanelLayout.setVerticalGroup(
                                menusPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                .addComponent(menusAux, javax.swing.GroupLayout.DEFAULT_SIZE,
                                                                javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                                .addComponent(menusLabel, javax.swing.GroupLayout.DEFAULT_SIZE, 43,
                                                                Short.MAX_VALUE));

                jPanel4.setBackground(new Color(120, 168, 252));

                javax.swing.GroupLayout jPanel4Layout = new GroupLayout(jPanel4);
                jPanel4.setLayout(jPanel4Layout);
                jPanel4Layout.setHorizontalGroup(
                                jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                .addGap(0, 0, Short.MAX_VALUE));
                jPanel4Layout.setVerticalGroup(
                                jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                .addGap(0, 67, Short.MAX_VALUE));

                Locale engLocale = new Locale("en", "UK");
                String dateToday = DateTimeFormatter.ofPattern("EEEE dd MMMM yyyy", engLocale)
                                .format(LocalDateTime.now());
                date.setForeground(new Color(255, 255, 255));
                date.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
                date.setText(dateToday);

                providersPanel.setBackground(new Color(71, 120, 197));

                providersAux.setPreferredSize(new Dimension(5, 43));

                javax.swing.GroupLayout providersAuxLayout = new GroupLayout(providersAux);
                providersAux.setLayout(providersAuxLayout);
                providersAuxLayout.setHorizontalGroup(
                                providersAuxLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                .addGap(0, 5, Short.MAX_VALUE));
                providersAuxLayout.setVerticalGroup(
                                providersAuxLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                .addGap(0, 0, Short.MAX_VALUE));

                providersLabel.setFont(new Font("Segoe UI", 0, 14)); // NOI18N
                providersLabel.setText("Providers");

                javax.swing.GroupLayout providersPanelLayout = new GroupLayout(providersPanel);
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
                                                                                javax.swing.GroupLayout.DEFAULT_SIZE,
                                                                                javax.swing.GroupLayout.DEFAULT_SIZE,
                                                                                Short.MAX_VALUE)
                                                                .addContainerGap()));
                providersPanelLayout.setVerticalGroup(
                                providersPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                .addComponent(providersAux, javax.swing.GroupLayout.DEFAULT_SIZE,
                                                                javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                                .addComponent(providersLabel, javax.swing.GroupLayout.DEFAULT_SIZE, 43,
                                                                Short.MAX_VALUE));

                ingredientsPanel.setBackground(new Color(71, 120, 197));

                ingredientsAux.setPreferredSize(new Dimension(5, 43));

                javax.swing.GroupLayout ingredientsAuxLayout = new GroupLayout(ingredientsAux);
                ingredientsAux.setLayout(ingredientsAuxLayout);
                ingredientsAuxLayout.setHorizontalGroup(
                                ingredientsAuxLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                .addGap(0, 5, Short.MAX_VALUE));
                ingredientsAuxLayout.setVerticalGroup(
                                ingredientsAuxLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                .addGap(0, 0, Short.MAX_VALUE));

                ingredientsLabel.setFont(new Font("Segoe UI", 0, 14)); // NOI18N
                ingredientsLabel.setText("Ingredients");

                javax.swing.GroupLayout ingredientsPanelLayout = new GroupLayout(ingredientsPanel);
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
                                                                                javax.swing.GroupLayout.DEFAULT_SIZE,
                                                                                javax.swing.GroupLayout.DEFAULT_SIZE,
                                                                                Short.MAX_VALUE)
                                                                .addContainerGap()));
                ingredientsPanelLayout.setVerticalGroup(
                                ingredientsPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                .addGroup(ingredientsPanelLayout.createSequentialGroup()
                                                                .addGroup(ingredientsPanelLayout.createParallelGroup(
                                                                                javax.swing.GroupLayout.Alignment.LEADING,
                                                                                false)
                                                                                .addComponent(ingredientsAux,
                                                                                                javax.swing.GroupLayout.DEFAULT_SIZE,
                                                                                                javax.swing.GroupLayout.DEFAULT_SIZE,
                                                                                                Short.MAX_VALUE)
                                                                                .addComponent(ingredientsLabel,
                                                                                                javax.swing.GroupLayout.DEFAULT_SIZE,
                                                                                                43, Short.MAX_VALUE))
                                                                .addGap(0, 0, Short.MAX_VALUE)));

                productsPanel.setBackground(new Color(71, 120, 197));

                productsAux.setPreferredSize(new Dimension(5, 43));

                javax.swing.GroupLayout productsAuxLayout = new GroupLayout(productsAux);
                productsAux.setLayout(productsAuxLayout);
                productsAuxLayout.setHorizontalGroup(
                                productsAuxLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                .addGap(0, 5, Short.MAX_VALUE));
                productsAuxLayout.setVerticalGroup(
                                productsAuxLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                .addGap(0, 0, Short.MAX_VALUE));

                productsLabel.setFont(new Font("Segoe UI", 0, 14)); // NOI18N
                productsLabel.setText("Products");

                javax.swing.GroupLayout productsPanelLayout = new GroupLayout(productsPanel);
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
                                                                                javax.swing.GroupLayout.DEFAULT_SIZE,
                                                                                javax.swing.GroupLayout.DEFAULT_SIZE,
                                                                                Short.MAX_VALUE)
                                                                .addContainerGap()));
                productsPanelLayout.setVerticalGroup(
                                productsPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                .addGroup(productsPanelLayout.createSequentialGroup()
                                                                .addGroup(productsPanelLayout.createParallelGroup(
                                                                                javax.swing.GroupLayout.Alignment.LEADING,
                                                                                false)
                                                                                .addComponent(productsAux,
                                                                                                javax.swing.GroupLayout.DEFAULT_SIZE,
                                                                                                javax.swing.GroupLayout.DEFAULT_SIZE,
                                                                                                Short.MAX_VALUE)
                                                                                .addComponent(productsLabel,
                                                                                                javax.swing.GroupLayout.DEFAULT_SIZE,
                                                                                                43, Short.MAX_VALUE))
                                                                .addGap(0, 0, Short.MAX_VALUE)));

                categoriesPanel.setBackground(new Color(71, 120, 197));

                categoriesAux.setPreferredSize(new Dimension(5, 43));

                javax.swing.GroupLayout categoriesAuxLayout = new GroupLayout(categoriesAux);
                categoriesAux.setLayout(categoriesAuxLayout);
                categoriesAuxLayout.setHorizontalGroup(
                                categoriesAuxLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                .addGap(0, 5, Short.MAX_VALUE));
                categoriesAuxLayout.setVerticalGroup(
                                categoriesAuxLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                .addGap(0, 0, Short.MAX_VALUE));

                categoriesLabel.setFont(new Font("Segoe UI", 0, 14)); // NOI18N
                categoriesLabel.setText("Categories");

                javax.swing.GroupLayout categoriesPanelLayout = new GroupLayout(categoriesPanel);
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
                                                                                javax.swing.GroupLayout.DEFAULT_SIZE,
                                                                                javax.swing.GroupLayout.DEFAULT_SIZE,
                                                                                Short.MAX_VALUE)
                                                                .addContainerGap()));
                categoriesPanelLayout.setVerticalGroup(
                                categoriesPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                .addComponent(categoriesAux, javax.swing.GroupLayout.DEFAULT_SIZE,
                                                                javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                                .addComponent(categoriesLabel, javax.swing.GroupLayout.DEFAULT_SIZE, 43,
                                                                Short.MAX_VALUE));

                allergensPanel.setBackground(new Color(71, 120, 197));

                allergensAux.setPreferredSize(new Dimension(5, 43));

                javax.swing.GroupLayout allergensAuxLayout = new GroupLayout(allergensAux);
                allergensAux.setLayout(allergensAuxLayout);
                allergensAuxLayout.setHorizontalGroup(
                                allergensAuxLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                .addGap(0, 5, Short.MAX_VALUE));
                allergensAuxLayout.setVerticalGroup(
                                allergensAuxLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                .addGap(0, 0, Short.MAX_VALUE));

                allergensLabel.setFont(new Font("Segoe UI", 0, 14)); // NOI18N
                allergensLabel.setText("Allergens");

                javax.swing.GroupLayout allergensPanelLayout = new GroupLayout(allergensPanel);
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
                                                                                javax.swing.GroupLayout.DEFAULT_SIZE,
                                                                                javax.swing.GroupLayout.DEFAULT_SIZE,
                                                                                Short.MAX_VALUE)
                                                                .addContainerGap()));
                allergensPanelLayout.setVerticalGroup(
                                allergensPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                .addGroup(allergensPanelLayout.createSequentialGroup()
                                                                .addGroup(allergensPanelLayout.createParallelGroup(
                                                                                javax.swing.GroupLayout.Alignment.LEADING,
                                                                                false)
                                                                                .addComponent(allergensAux,
                                                                                                javax.swing.GroupLayout.DEFAULT_SIZE,
                                                                                                javax.swing.GroupLayout.DEFAULT_SIZE,
                                                                                                Short.MAX_VALUE)
                                                                                .addComponent(allergensLabel,
                                                                                                javax.swing.GroupLayout.DEFAULT_SIZE,
                                                                                                43, Short.MAX_VALUE))
                                                                .addGap(0, 0, Short.MAX_VALUE)));

                javax.swing.GroupLayout leftAuxPanelLayout = new GroupLayout(leftAuxPanel);
                leftAuxPanel.setLayout(leftAuxPanelLayout);
                leftAuxPanelLayout.setHorizontalGroup(
                                leftAuxPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                .addComponent(jPanel4, javax.swing.GroupLayout.DEFAULT_SIZE,
                                                                javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                                .addComponent(menusPanel, javax.swing.GroupLayout.DEFAULT_SIZE,
                                                                javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                                .addComponent(ingredientsPanel, javax.swing.GroupLayout.DEFAULT_SIZE,
                                                                javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                                .addComponent(productsPanel, javax.swing.GroupLayout.DEFAULT_SIZE,
                                                                javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                                .addGroup(leftAuxPanelLayout.createSequentialGroup()
                                                                .addContainerGap()
                                                                .addComponent(date,
                                                                                javax.swing.GroupLayout.PREFERRED_SIZE,
                                                                                208,
                                                                                javax.swing.GroupLayout.PREFERRED_SIZE)
                                                                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE,
                                                                                Short.MAX_VALUE))
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
                                                                                183, Short.MAX_VALUE)
                                                                .addComponent(date,
                                                                                javax.swing.GroupLayout.PREFERRED_SIZE,
                                                                                16,
                                                                                javax.swing.GroupLayout.PREFERRED_SIZE)
                                                                .addContainerGap()));
        }

        public void initComp3(JPanel playground) {
                playground.setBackground(new Color(255, 255, 255));
                productDistPanel.setBackground(new Color(255, 255, 255));
                productDistPanel.setBorder(
                                new LineBorder(new Color(185, 208, 248), 3, true));

                headProductDistPanel.setBackground(new Color(185, 208, 248));

                productDistLabel.setFont(new Font("Segoe UI", 1, 18)); // NOI18N
                productDistLabel.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
                productDistLabel.setText("PRODUCTS DISTRIBUTION");

                productDistTuggleButton.setText("Today");

                javax.swing.GroupLayout headProductDistPanelLayout = new GroupLayout(headProductDistPanel);
                headProductDistPanel.setLayout(headProductDistPanelLayout);
                headProductDistPanelLayout.setHorizontalGroup(
                                headProductDistPanelLayout
                                                .createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                .addGroup(headProductDistPanelLayout.createSequentialGroup()
                                                                .addContainerGap()
                                                                .addComponent(productDistLabel,
                                                                                javax.swing.GroupLayout.PREFERRED_SIZE,
                                                                                254,
                                                                                javax.swing.GroupLayout.PREFERRED_SIZE)
                                                                .addGap(18, 18, 18)
                                                                .addComponent(productDistTuggleButton,
                                                                                javax.swing.GroupLayout.DEFAULT_SIZE,
                                                                                127, Short.MAX_VALUE)));
                headProductDistPanelLayout.setVerticalGroup(
                                headProductDistPanelLayout
                                                .createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                .addComponent(productDistLabel, javax.swing.GroupLayout.DEFAULT_SIZE,
                                                                31, Short.MAX_VALUE)
                                                .addGroup(headProductDistPanelLayout.createSequentialGroup()
                                                                .addComponent(productDistTuggleButton)
                                                                .addGap(0, 0, Short.MAX_VALUE)));

                javax.swing.GroupLayout productDistContentPanelLayout = new GroupLayout(
                                productDistContentPanel);
                productDistContentPanel.setLayout(productDistContentPanelLayout);
                productDistContentPanelLayout.setHorizontalGroup(
                                productDistContentPanelLayout
                                                .createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                .addGap(0, 0, Short.MAX_VALUE));
                productDistContentPanelLayout.setVerticalGroup(
                                productDistContentPanelLayout
                                                .createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                .addGap(0, 228, Short.MAX_VALUE));

                javax.swing.GroupLayout productDistPanelLayout = new GroupLayout(productDistPanel);
                productDistPanel.setLayout(productDistPanelLayout);
                productDistPanelLayout.setHorizontalGroup(
                                productDistPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                .addComponent(headProductDistPanel,
                                                                javax.swing.GroupLayout.DEFAULT_SIZE,
                                                                javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                                .addGroup(productDistPanelLayout.createSequentialGroup()
                                                                .addContainerGap()
                                                                .addComponent(productDistContentPanel,
                                                                                javax.swing.GroupLayout.DEFAULT_SIZE,
                                                                                javax.swing.GroupLayout.DEFAULT_SIZE,
                                                                                Short.MAX_VALUE)
                                                                .addContainerGap()));
                productDistPanelLayout.setVerticalGroup(
                                productDistPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                .addGroup(productDistPanelLayout.createSequentialGroup()
                                                                .addComponent(headProductDistPanel,
                                                                                javax.swing.GroupLayout.PREFERRED_SIZE,
                                                                                javax.swing.GroupLayout.DEFAULT_SIZE,
                                                                                javax.swing.GroupLayout.PREFERRED_SIZE)
                                                                .addPreferredGap(
                                                                                javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                                                .addComponent(productDistContentPanel,
                                                                                javax.swing.GroupLayout.PREFERRED_SIZE,
                                                                                javax.swing.GroupLayout.DEFAULT_SIZE,
                                                                                javax.swing.GroupLayout.PREFERRED_SIZE)
                                                                .addContainerGap()));

                menuDistPanel.setBackground(new Color(255, 255, 255));
                menuDistPanel.setBorder(new LineBorder(new Color(185, 208, 248), 3, true));

                headMenuDistPanel.setBackground(new Color(185, 208, 248));

                menuDistLabel.setFont(new Font("Segoe UI", 1, 18)); // NOI18N
                menuDistLabel.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
                menuDistLabel.setText("MENU DISTRIBUTION");

                menuDistTuggleButton.setText("Today");

                javax.swing.GroupLayout headMenuDistPanelLayout = new GroupLayout(headMenuDistPanel);
                headMenuDistPanel.setLayout(headMenuDistPanelLayout);
                headMenuDistPanelLayout.setHorizontalGroup(
                                headMenuDistPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                .addGroup(headMenuDistPanelLayout.createSequentialGroup()
                                                                .addContainerGap()
                                                                .addComponent(menuDistLabel,
                                                                                javax.swing.GroupLayout.PREFERRED_SIZE,
                                                                                254,
                                                                                javax.swing.GroupLayout.PREFERRED_SIZE)
                                                                .addGap(18, 18, 18)
                                                                .addComponent(menuDistTuggleButton,
                                                                                javax.swing.GroupLayout.DEFAULT_SIZE,
                                                                                127, Short.MAX_VALUE)));
                headMenuDistPanelLayout.setVerticalGroup(
                                headMenuDistPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                .addComponent(menuDistLabel, javax.swing.GroupLayout.DEFAULT_SIZE, 31,
                                                                Short.MAX_VALUE)
                                                .addGroup(headMenuDistPanelLayout.createSequentialGroup()
                                                                .addComponent(menuDistTuggleButton)
                                                                .addGap(0, 0, Short.MAX_VALUE)));

                javax.swing.GroupLayout menuDistContentPanelLayout = new GroupLayout(menuDistContentPanel);
                menuDistContentPanel.setLayout(menuDistContentPanelLayout);
                menuDistContentPanelLayout.setHorizontalGroup(
                                menuDistContentPanelLayout
                                                .createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                .addGap(0, 0, Short.MAX_VALUE));
                menuDistContentPanelLayout.setVerticalGroup(
                                menuDistContentPanelLayout
                                                .createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                .addGap(0, 228, Short.MAX_VALUE));

                javax.swing.GroupLayout menuDistPanelLayout = new GroupLayout(menuDistPanel);
                menuDistPanel.setLayout(menuDistPanelLayout);
                menuDistPanelLayout.setHorizontalGroup(
                                menuDistPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                .addGroup(menuDistPanelLayout.createSequentialGroup()
                                                                .addContainerGap()
                                                                .addComponent(menuDistContentPanel,
                                                                                javax.swing.GroupLayout.DEFAULT_SIZE,
                                                                                javax.swing.GroupLayout.DEFAULT_SIZE,
                                                                                Short.MAX_VALUE)
                                                                .addContainerGap())
                                                .addComponent(headMenuDistPanel, javax.swing.GroupLayout.DEFAULT_SIZE,
                                                                javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE));
                menuDistPanelLayout.setVerticalGroup(
                                menuDistPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                .addGroup(menuDistPanelLayout.createSequentialGroup()
                                                                .addComponent(headMenuDistPanel,
                                                                                javax.swing.GroupLayout.PREFERRED_SIZE,
                                                                                javax.swing.GroupLayout.DEFAULT_SIZE,
                                                                                javax.swing.GroupLayout.PREFERRED_SIZE)
                                                                .addPreferredGap(
                                                                                javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                                                .addComponent(menuDistContentPanel,
                                                                                javax.swing.GroupLayout.PREFERRED_SIZE,
                                                                                javax.swing.GroupLayout.DEFAULT_SIZE,
                                                                                javax.swing.GroupLayout.PREFERRED_SIZE)
                                                                .addContainerGap()));

                categorySalesPanel.setBackground(new Color(255, 255, 255));
                categorySalesPanel.setBorder(
                                new LineBorder(new Color(185, 208, 248), 3, true));

                headCategorySales.setBackground(new Color(185, 208, 248));

                categorySalesLabel.setFont(new Font("Segoe UI", 1, 18)); // NOI18N
                categorySalesLabel.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
                categorySalesLabel.setText("SALES PER PRODUCT CATEGORY");

                categorySalesTuggleButton.setText("Today");

                javax.swing.GroupLayout headCategorySalesLayout = new GroupLayout(headCategorySales);
                headCategorySales.setLayout(headCategorySalesLayout);
                headCategorySalesLayout.setHorizontalGroup(
                                headCategorySalesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                .addGroup(headCategorySalesLayout.createSequentialGroup()
                                                                .addContainerGap()
                                                                .addComponent(categorySalesLabel,
                                                                                javax.swing.GroupLayout.PREFERRED_SIZE,
                                                                                546,
                                                                                javax.swing.GroupLayout.PREFERRED_SIZE)
                                                                .addPreferredGap(
                                                                                javax.swing.LayoutStyle.ComponentPlacement.RELATED,
                                                                                javax.swing.GroupLayout.DEFAULT_SIZE,
                                                                                Short.MAX_VALUE)
                                                                .addComponent(categorySalesTuggleButton,
                                                                                javax.swing.GroupLayout.PREFERRED_SIZE,
                                                                                131,
                                                                                javax.swing.GroupLayout.PREFERRED_SIZE)));
                headCategorySalesLayout.setVerticalGroup(
                                headCategorySalesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                .addComponent(categorySalesLabel, javax.swing.GroupLayout.DEFAULT_SIZE,
                                                                31, Short.MAX_VALUE)
                                                .addGroup(headCategorySalesLayout.createSequentialGroup()
                                                                .addComponent(categorySalesTuggleButton)
                                                                .addGap(0, 0, Short.MAX_VALUE)));

                javax.swing.GroupLayout categorySalesContentPanelLayout = new GroupLayout(
                                categorySalesContentPanel);
                categorySalesContentPanel.setLayout(categorySalesContentPanelLayout);
                categorySalesContentPanelLayout.setHorizontalGroup(
                                categorySalesContentPanelLayout
                                                .createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                .addGap(0, 0, Short.MAX_VALUE));
                categorySalesContentPanelLayout.setVerticalGroup(
                                categorySalesContentPanelLayout
                                                .createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                .addGap(0, 235, Short.MAX_VALUE));

                javax.swing.GroupLayout categorySalesPanelLayout = new GroupLayout(categorySalesPanel);
                categorySalesPanel.setLayout(categorySalesPanelLayout);
                categorySalesPanelLayout.setHorizontalGroup(
                                categorySalesPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                .addGroup(categorySalesPanelLayout.createSequentialGroup()
                                                                .addContainerGap()
                                                                .addComponent(categorySalesContentPanel,
                                                                                javax.swing.GroupLayout.DEFAULT_SIZE,
                                                                                javax.swing.GroupLayout.DEFAULT_SIZE,
                                                                                Short.MAX_VALUE)
                                                                .addContainerGap())
                                                .addComponent(headCategorySales, javax.swing.GroupLayout.DEFAULT_SIZE,
                                                                javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE));
                categorySalesPanelLayout.setVerticalGroup(
                                categorySalesPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                .addGroup(categorySalesPanelLayout.createSequentialGroup()
                                                                .addComponent(headCategorySales,
                                                                                javax.swing.GroupLayout.PREFERRED_SIZE,
                                                                                javax.swing.GroupLayout.DEFAULT_SIZE,
                                                                                javax.swing.GroupLayout.PREFERRED_SIZE)
                                                                .addPreferredGap(
                                                                                javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                                                .addComponent(categorySalesContentPanel,
                                                                                javax.swing.GroupLayout.PREFERRED_SIZE,
                                                                                javax.swing.GroupLayout.DEFAULT_SIZE,
                                                                                javax.swing.GroupLayout.PREFERRED_SIZE)
                                                                .addContainerGap()));

                javax.swing.GroupLayout playgroundLayout = new GroupLayout(playground);
                playground.setLayout(playgroundLayout);
                playgroundLayout.setHorizontalGroup(
                                playgroundLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, playgroundLayout
                                                                .createSequentialGroup()
                                                                .addContainerGap()
                                                                .addGroup(playgroundLayout.createParallelGroup(
                                                                                javax.swing.GroupLayout.Alignment.TRAILING)
                                                                                .addComponent(categorySalesPanel,
                                                                                                javax.swing.GroupLayout.DEFAULT_SIZE,
                                                                                                javax.swing.GroupLayout.DEFAULT_SIZE,
                                                                                                Short.MAX_VALUE)
                                                                                .addGroup(playgroundLayout
                                                                                                .createSequentialGroup()
                                                                                                .addComponent(productDistPanel,
                                                                                                                javax.swing.GroupLayout.PREFERRED_SIZE,
                                                                                                                javax.swing.GroupLayout.DEFAULT_SIZE,
                                                                                                                javax.swing.GroupLayout.PREFERRED_SIZE)
                                                                                                .addPreferredGap(
                                                                                                                javax.swing.LayoutStyle.ComponentPlacement.RELATED,
                                                                                                                12,
                                                                                                                Short.MAX_VALUE)
                                                                                                .addComponent(menuDistPanel,
                                                                                                                javax.swing.GroupLayout.PREFERRED_SIZE,
                                                                                                                javax.swing.GroupLayout.DEFAULT_SIZE,
                                                                                                                javax.swing.GroupLayout.PREFERRED_SIZE)))
                                                                .addContainerGap()));
                playgroundLayout.setVerticalGroup(
                                playgroundLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                .addGroup(playgroundLayout.createSequentialGroup()
                                                                .addContainerGap()
                                                                .addGroup(playgroundLayout.createParallelGroup(
                                                                                javax.swing.GroupLayout.Alignment.LEADING)
                                                                                .addComponent(productDistPanel,
                                                                                                javax.swing.GroupLayout.PREFERRED_SIZE,
                                                                                                javax.swing.GroupLayout.DEFAULT_SIZE,
                                                                                                javax.swing.GroupLayout.PREFERRED_SIZE)
                                                                                .addComponent(menuDistPanel,
                                                                                                javax.swing.GroupLayout.PREFERRED_SIZE,
                                                                                                javax.swing.GroupLayout.DEFAULT_SIZE,
                                                                                                javax.swing.GroupLayout.PREFERRED_SIZE))
                                                                .addGap(18, 18, 18)
                                                                .addComponent(categorySalesPanel,
                                                                                javax.swing.GroupLayout.PREFERRED_SIZE,
                                                                                javax.swing.GroupLayout.DEFAULT_SIZE,
                                                                                javax.swing.GroupLayout.PREFERRED_SIZE)
                                                                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE,
                                                                                Short.MAX_VALUE)));
        }

        private void addListeners(JPanel playground) {

                // All these mouseListeners have the goal to transition to a new menu. They do
                // so by removing the contents of the panels needed by the respective new menu,
                // and then updating the renders of these panels.
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
                                new mainProviders(playground);
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
                                new mainCategories(playground);
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
                                new mainAllergens(playground);
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
                productTuggleButton();
                menuTuggleButton();
                salesTuggleButton();
        }

        private void setProductDistribution(boolean today) {
                productDistContentPanel.removeAll();
                DefaultPieDataset dataset = new DefaultPieDataset();
                for (catSalesTuple temp : dashboardsAPI.getProductCategoryDistribution(today))
                        dataset.setValue(temp.categoryName, temp.sold);
                String title = "Category Sales Last Week";
                if (today)
                        title = "Category Sales Today";
                JFreeChart pieChart = ChartFactory.createPieChart(title, dataset, false,
                                true, false);
                pieChart.getPlot().setBackgroundPaint(new Color(232, 237, 246));
                ChartPanel panel = new ChartPanel(pieChart);
                panel.setMouseWheelEnabled(false);
                panel.setMinimumSize(new Dimension(258, 240));
                panel.setMaximumSize(new Dimension(258, 240));
                panel.setPreferredSize(new Dimension(258, 240));
                productDistContentPanel.setLayout(new BorderLayout());
                productDistContentPanel.add(panel, BorderLayout.CENTER);
                productDistContentPanel.revalidate();
                productDistContentPanel.repaint();
        }

        private void setMenuDistribution(boolean today) {
                menuDistContentPanel.removeAll();
                DefaultPieDataset dataset = new DefaultPieDataset();
                for (catSalesTuple temp : dashboardsAPI.getMenuCategoryDistribution(today))
                        dataset.setValue(temp.categoryName, temp.sold);
                String title = "Category Sales Last Week";
                if (today)
                        title = "Category Sales Today";
                JFreeChart pieChart = ChartFactory.createPieChart(title, dataset, false,
                                true, false);
                pieChart.getPlot().setBackgroundPaint(new Color(232, 237, 246));
                ChartPanel panel = new ChartPanel(pieChart);
                panel.setMouseWheelEnabled(false);
                panel.setMinimumSize(new Dimension(258, 240));
                panel.setMaximumSize(new Dimension(258, 240));
                panel.setPreferredSize(new Dimension(258, 240));
                menuDistContentPanel.setLayout(new BorderLayout());
                menuDistContentPanel.add(panel, BorderLayout.CENTER);
                menuDistContentPanel.revalidate();
                menuDistContentPanel.repaint();
        }

        private void setGraphSales(boolean today) {
                categorySalesContentPanel.removeAll();
                DefaultCategoryDataset datos = new DefaultCategoryDataset();
                ArrayList<catSalesTuple> data = dashboardsAPI.getCategorySalesDistribution(today);
                for (catSalesTuple temp : data)
                        datos.setValue(temp.sold, "Sales", temp.categoryName);

                String title = "Sales Last 7 Days";
                if (today)
                        title = "Sales Today";
                JFreeChart barChart = ChartFactory.createBarChart(title, null, "Sales (€)",
                                datos, PlotOrientation.VERTICAL, false, true, false);
                barChart.getPlot().setBackgroundPaint(new Color(232, 237, 246));
                ChartPanel pan = new ChartPanel(barChart);
                pan.setMouseWheelEnabled(false);
                pan.setMinimumSize(new Dimension(387, 234));
                pan.setMaximumSize(new Dimension(387, 234));
                pan.setPreferredSize(new Dimension(387, 234));
                categorySalesContentPanel.setLayout(new BorderLayout());
                categorySalesContentPanel.add(pan, BorderLayout.CENTER);
                categorySalesContentPanel.revalidate();
                categorySalesContentPanel.repaint();
        }

        private void productTuggleButton() {
                class productTuggleHolder implements iDashToggleFormatter {
                        public void action1() {
                                setProductDistribution(true);
                        }

                        public void action2() {
                                setProductDistribution(false);
                        }
                }
                dashToggleFormatter.applyDashActionListenerToggle(productDistTuggleButton, "Today",
                                "Last 7 Days", new productTuggleHolder());
        }

        private void menuTuggleButton() {
                class menuTuggleHolder implements iDashToggleFormatter {
                        public void action1() {
                                setMenuDistribution(true);
                        }

                        public void action2() {
                                setMenuDistribution(false);
                        }
                }
                dashToggleFormatter.applyDashActionListenerToggle(menuDistTuggleButton, "Today",
                                "Last 7 Days", new menuTuggleHolder());
        }

        private void salesTuggleButton() {
                class salesTuggleHolder implements iDashToggleFormatter {
                        public void action1() {
                                setGraphSales(true);
                        }

                        public void action2() {
                                setGraphSales(false);
                        }
                }
                dashToggleFormatter.applyDashActionListenerToggle(categorySalesTuggleButton, "Today",
                                "Last 7 Days", new salesTuggleHolder());
        }

        private void setColor(JPanel panel) {
                panel.setBackground(new Color(120, 168, 252));
        }

        private void resetColor(JPanel panel) {
                panel.setBackground(new Color(71, 120, 197));
        }
}