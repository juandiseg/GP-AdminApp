package navigation;

import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;

import javax.swing.*;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.border.LineBorder;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.data.category.DefaultCategoryDataset;

import componentsFood.currentShiftEmployee;
import componentsFood.orderView;
import componentsFood.product;
import navigation.administration.*;
import navigation.food.*;
import util.databaseAPIs.dashboardsAPI;

public class homeDash extends JFrame {

        private JPanel homePanel = new JPanel();
        private JLabel homeLabel = new JLabel("Home");
        private JPanel homeAux = new JPanel();
        private JPanel administrationPanel = new JPanel();
        private JLabel administrationLabel = new JLabel("Administration");
        private JPanel administrationAux = new JPanel();
        private JPanel foodPanel = new JPanel();
        private JLabel foodLabel = new JLabel("Food");
        private JPanel foodAux = new JPanel();

        private JLabel date = new JLabel();
        private JPanel jPanel4 = new JPanel();
        private JPanel leftAuxPanel = new JPanel();
        private JPanel overheadLogoPanel = new JPanel();
        private JPanel overheadPanel = new JPanel();
        private JPanel playground = new JPanel();
        private JPanel staticButtonsPanel = new JPanel();
        private JLabel welcomeLabel = new JLabel();
        private JLabel clickRole1 = new JLabel();
        private JScrollPane ordersPane = new JScrollPane();
        private JScrollPane employeesPane = new JScrollPane();

        private JLabel employeesLabel = new JLabel();
        private JLabel ordersLabel = new JLabel();

        private JLabel priceIconLabel = new JLabel();
        private JLabel priceTitleLabel = new JLabel();
        private JLabel timeTimeLabel = new JLabel();
        private JLabel timeIconLabel = new JLabel();
        private JLabel timeTitleLabel = new JLabel();

        private JPanel timePanel = new JPanel();
        private JPanel timePanelBackground = new JPanel();

        private JPanel headOrdersPanel = new JPanel();
        private JPanel headEmployeesPanel = new JPanel();

        private JPanel employeesPanel = new JPanel();
        private JPanel ordersPanel = new JPanel();

        private JPanel salesPanel = new JPanel();

        private JPanel bsMonthPanel = new JPanel();
        private JPanel bsMonthPanelBackground = new JPanel();
        private JLabel bsMonthProductLabel = new JLabel();
        private JLabel bsMonthTitlePanel = new JLabel();
        private JPanel bsTodayPanel = new JPanel();
        private JPanel bsTodayPanelBackground = new JPanel();
        private JLabel bsTodayProductLabel = new JLabel();
        private JLabel bsTodayTitlePanel = new JLabel();
        private JPanel bsWeekPanel = new JPanel();
        private JPanel bsWeekPanelBackground = new JPanel();
        private JLabel bsWeekProductLabel = new JLabel();
        private JLabel bsWeekTitlePanel = new JLabel();
        private JPanel headSalesPanel = new JPanel();
        private JPanel pricePanel = new JPanel();
        private JPanel pricePanelBackground = new JPanel();
        private JLabel pricePriceLabel = new JLabel();
        private JPanel salesContentPanel = new JPanel();
        private JLabel salesLabel = new JLabel();
        private JToggleButton salesTuggleButton = new JToggleButton();

        public homeDash() {
                initComponents();
        }

        private void initComp2() {
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

                date.setForeground(new Color(255, 255, 255));
                date.setText("13 Feb 2023");

                GroupLayout leftAuxPanelLayout = new GroupLayout(leftAuxPanel);
                leftAuxPanel.setLayout(leftAuxPanelLayout);
                leftAuxPanelLayout.setHorizontalGroup(
                                leftAuxPanelLayout.createParallelGroup(GroupLayout.Alignment.LEADING)
                                                .addComponent(jPanel4, GroupLayout.DEFAULT_SIZE,
                                                                GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                                .addGroup(leftAuxPanelLayout.createSequentialGroup()
                                                                .addGap(80, 80, 80)
                                                                .addComponent(date)
                                                                .addContainerGap(86, Short.MAX_VALUE)));
                leftAuxPanelLayout.setVerticalGroup(
                                leftAuxPanelLayout.createParallelGroup(GroupLayout.Alignment.LEADING)
                                                .addGroup(leftAuxPanelLayout.createSequentialGroup()
                                                                .addComponent(jPanel4,
                                                                                GroupLayout.PREFERRED_SIZE,
                                                                                GroupLayout.DEFAULT_SIZE,
                                                                                GroupLayout.PREFERRED_SIZE)
                                                                .addPreferredGap(
                                                                                ComponentPlacement.RELATED,
                                                                                541, Short.MAX_VALUE)
                                                                .addComponent(date)
                                                                .addContainerGap()));

                getContentPane().add(leftAuxPanel,
                                new org.netbeans.lib.awtextra.AbsoluteConstraints(170, 90, 230, 630));
        }

        private void initComp1() {
                clickRole1.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
                clickRole1.setHorizontalAlignment(SwingConstants.CENTER);
                clickRole1.setText("New name :");
                clickRole1.setVerticalAlignment(SwingConstants.BOTTOM);

                setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
                setMaximumSize(new Dimension(1284, 756));
                setPreferredSize(new Dimension(1284, 756));
                setResizable(false);
                setSize(new Dimension(1284, 756));
                getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

                staticButtonsPanel.setBackground(new Color(23, 35, 51));

                homePanel.setBackground(new Color(41, 57, 80));

                homeAux.setPreferredSize(new Dimension(5, 43));

                GroupLayout homeAuxLayout = new GroupLayout(homeAux);
                homeAux.setLayout(homeAuxLayout);
                homeAuxLayout.setHorizontalGroup(
                                homeAuxLayout.createParallelGroup(GroupLayout.Alignment.LEADING)
                                                .addGap(0, 5, Short.MAX_VALUE));
                homeAuxLayout.setVerticalGroup(
                                homeAuxLayout.createParallelGroup(GroupLayout.Alignment.LEADING)
                                                .addGap(0, 0, Short.MAX_VALUE));

                homeLabel.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
                homeLabel.setForeground(new Color(255, 255, 255));
                homeLabel.setText("Home");

                GroupLayout homePanelLayout = new GroupLayout(homePanel);
                homePanel.setLayout(homePanelLayout);
                homePanelLayout.setHorizontalGroup(
                                homePanelLayout.createParallelGroup(GroupLayout.Alignment.LEADING)
                                                .addGroup(homePanelLayout.createSequentialGroup()
                                                                .addComponent(homeAux,
                                                                                GroupLayout.PREFERRED_SIZE,
                                                                                GroupLayout.DEFAULT_SIZE,
                                                                                GroupLayout.PREFERRED_SIZE)
                                                                .addPreferredGap(
                                                                                ComponentPlacement.UNRELATED)
                                                                .addComponent(homeLabel,
                                                                                GroupLayout.PREFERRED_SIZE,
                                                                                79,
                                                                                GroupLayout.PREFERRED_SIZE)
                                                                .addContainerGap(GroupLayout.DEFAULT_SIZE,
                                                                                Short.MAX_VALUE)));
                homePanelLayout.setVerticalGroup(
                                homePanelLayout.createParallelGroup(GroupLayout.Alignment.LEADING)
                                                .addGroup(homePanelLayout.createSequentialGroup()
                                                                .addGroup(homePanelLayout.createParallelGroup(
                                                                                GroupLayout.Alignment.LEADING,
                                                                                false)
                                                                                .addComponent(homeAux,
                                                                                                GroupLayout.DEFAULT_SIZE,
                                                                                                GroupLayout.DEFAULT_SIZE,
                                                                                                Short.MAX_VALUE)
                                                                                .addComponent(homeLabel,
                                                                                                GroupLayout.DEFAULT_SIZE,
                                                                                                43, Short.MAX_VALUE))
                                                                .addGap(0, 0, Short.MAX_VALUE)));

                administrationPanel.setBackground(new Color(23, 35, 51));

                administrationAux.setPreferredSize(new Dimension(5, 43));

                GroupLayout administrationAuxLayout = new GroupLayout(administrationAux);
                administrationAux.setLayout(administrationAuxLayout);
                administrationAuxLayout.setHorizontalGroup(
                                administrationAuxLayout.createParallelGroup(GroupLayout.Alignment.LEADING)
                                                .addGap(0, 5, Short.MAX_VALUE));
                administrationAuxLayout.setVerticalGroup(
                                administrationAuxLayout.createParallelGroup(GroupLayout.Alignment.LEADING)
                                                .addGap(0, 0, Short.MAX_VALUE));

                administrationLabel.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
                administrationLabel.setForeground(new Color(255, 255, 255));
                administrationLabel.setText("Administration");

                GroupLayout administrationPanelLayout = new GroupLayout(administrationPanel);
                administrationPanel.setLayout(administrationPanelLayout);
                administrationPanelLayout.setHorizontalGroup(
                                administrationPanelLayout.createParallelGroup(GroupLayout.Alignment.LEADING)
                                                .addGroup(administrationPanelLayout.createSequentialGroup()
                                                                .addComponent(administrationAux,
                                                                                GroupLayout.PREFERRED_SIZE,
                                                                                GroupLayout.DEFAULT_SIZE,
                                                                                GroupLayout.PREFERRED_SIZE)
                                                                .addPreferredGap(
                                                                                ComponentPlacement.UNRELATED)
                                                                .addComponent(administrationLabel)
                                                                .addContainerGap(63, Short.MAX_VALUE)));
                administrationPanelLayout.setVerticalGroup(
                                administrationPanelLayout.createParallelGroup(GroupLayout.Alignment.LEADING)
                                                .addGroup(administrationPanelLayout.createSequentialGroup()
                                                                .addGroup(administrationPanelLayout.createParallelGroup(
                                                                                GroupLayout.Alignment.LEADING,
                                                                                false)
                                                                                .addComponent(administrationAux,
                                                                                                GroupLayout.DEFAULT_SIZE,
                                                                                                GroupLayout.DEFAULT_SIZE,
                                                                                                Short.MAX_VALUE)
                                                                                .addComponent(administrationLabel,
                                                                                                GroupLayout.DEFAULT_SIZE,
                                                                                                43, Short.MAX_VALUE))
                                                                .addGap(0, 0, Short.MAX_VALUE)));

                foodPanel.setBackground(new Color(23, 35, 51));

                foodAux.setPreferredSize(new Dimension(5, 43));

                GroupLayout foodAuxLayout = new GroupLayout(foodAux);
                foodAux.setLayout(foodAuxLayout);
                foodAuxLayout.setHorizontalGroup(
                                foodAuxLayout.createParallelGroup(GroupLayout.Alignment.LEADING)
                                                .addGap(0, 5, Short.MAX_VALUE));
                foodAuxLayout.setVerticalGroup(
                                foodAuxLayout.createParallelGroup(GroupLayout.Alignment.LEADING)
                                                .addGap(0, 0, Short.MAX_VALUE));

                foodLabel.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
                foodLabel.setForeground(new Color(255, 255, 255));
                foodLabel.setText("Food");

                GroupLayout foodPanelLayout = new GroupLayout(foodPanel);
                foodPanel.setLayout(foodPanelLayout);
                foodPanelLayout.setHorizontalGroup(
                                foodPanelLayout
                                                .createParallelGroup(GroupLayout.Alignment.LEADING)
                                                .addGroup(foodPanelLayout.createSequentialGroup()
                                                                .addComponent(foodAux,
                                                                                GroupLayout.PREFERRED_SIZE,
                                                                                GroupLayout.DEFAULT_SIZE,
                                                                                GroupLayout.PREFERRED_SIZE)
                                                                .addPreferredGap(
                                                                                ComponentPlacement.UNRELATED)
                                                                .addComponent(foodLabel)
                                                                .addContainerGap(GroupLayout.DEFAULT_SIZE,
                                                                                Short.MAX_VALUE)));
                foodPanelLayout.setVerticalGroup(
                                foodPanelLayout
                                                .createParallelGroup(GroupLayout.Alignment.LEADING)
                                                .addGroup(foodPanelLayout.createSequentialGroup()
                                                                .addGroup(foodPanelLayout
                                                                                .createParallelGroup(
                                                                                                GroupLayout.Alignment.LEADING,
                                                                                                false)
                                                                                .addComponent(foodAux,
                                                                                                GroupLayout.DEFAULT_SIZE,
                                                                                                GroupLayout.DEFAULT_SIZE,
                                                                                                Short.MAX_VALUE)
                                                                                .addComponent(foodLabel,
                                                                                                GroupLayout.DEFAULT_SIZE,
                                                                                                43, Short.MAX_VALUE))
                                                                .addGap(0, 0, Short.MAX_VALUE)));

                GroupLayout staticButtonsPanelLayout = new GroupLayout(staticButtonsPanel);
                staticButtonsPanel.setLayout(staticButtonsPanelLayout);
                staticButtonsPanelLayout.setHorizontalGroup(
                                staticButtonsPanelLayout.createParallelGroup(GroupLayout.Alignment.LEADING)
                                                .addComponent(homePanel, GroupLayout.DEFAULT_SIZE,
                                                                GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                                .addComponent(administrationPanel, GroupLayout.DEFAULT_SIZE,
                                                                GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                                .addComponent(foodPanel,
                                                                GroupLayout.DEFAULT_SIZE,
                                                                GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE));
                staticButtonsPanelLayout.setVerticalGroup(
                                staticButtonsPanelLayout.createParallelGroup(GroupLayout.Alignment.LEADING)
                                                .addGroup(staticButtonsPanelLayout.createSequentialGroup()
                                                                .addGap(155, 155, 155)
                                                                .addComponent(homePanel,
                                                                                GroupLayout.PREFERRED_SIZE,
                                                                                GroupLayout.DEFAULT_SIZE,
                                                                                GroupLayout.PREFERRED_SIZE)
                                                                .addPreferredGap(
                                                                                ComponentPlacement.RELATED)
                                                                .addComponent(administrationPanel,
                                                                                GroupLayout.PREFERRED_SIZE,
                                                                                GroupLayout.DEFAULT_SIZE,
                                                                                GroupLayout.PREFERRED_SIZE)
                                                                .addPreferredGap(
                                                                                ComponentPlacement.RELATED)
                                                                .addComponent(foodPanel,
                                                                                GroupLayout.PREFERRED_SIZE,
                                                                                GroupLayout.DEFAULT_SIZE,
                                                                                GroupLayout.PREFERRED_SIZE)
                                                                .addContainerGap(GroupLayout.DEFAULT_SIZE,
                                                                                Short.MAX_VALUE)));

                getContentPane().add(staticButtonsPanel,
                                new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 170, 720));
        }

        private void initCompTop() {
                overheadPanel.setBackground(new Color(71, 120, 197));

                overheadLogoPanel.setBackground(new Color(0, 0, 0));
                overheadLogoPanel.setOpaque(false);

                GroupLayout overheadLogoPanelLayout = new GroupLayout(overheadLogoPanel);
                overheadLogoPanel.setLayout(overheadLogoPanelLayout);
                overheadLogoPanelLayout.setHorizontalGroup(
                                overheadLogoPanelLayout.createParallelGroup(GroupLayout.Alignment.LEADING)
                                                .addGap(0, 90, Short.MAX_VALUE));
                overheadLogoPanelLayout.setVerticalGroup(
                                overheadLogoPanelLayout.createParallelGroup(GroupLayout.Alignment.LEADING)
                                                .addGap(0, 0, Short.MAX_VALUE));

                welcomeLabel.setFont(new java.awt.Font("Segoe UI", 1, 24)); // NOI18N
                welcomeLabel.setForeground(new Color(255, 255, 255));
                welcomeLabel.setText("Welcome, [name]");

                GroupLayout overheadPanelLayout = new GroupLayout(overheadPanel);
                overheadPanel.setLayout(overheadPanelLayout);
                overheadPanelLayout.setHorizontalGroup(
                                overheadPanelLayout.createParallelGroup(GroupLayout.Alignment.LEADING)
                                                .addGroup(GroupLayout.Alignment.TRAILING,
                                                                overheadPanelLayout.createSequentialGroup()
                                                                                .addGap(14, 14, 14)
                                                                                .addComponent(welcomeLabel,
                                                                                                GroupLayout.PREFERRED_SIZE,
                                                                                                343,
                                                                                                GroupLayout.PREFERRED_SIZE)
                                                                                .addPreferredGap(
                                                                                                ComponentPlacement.RELATED,
                                                                                                653, Short.MAX_VALUE)
                                                                                .addComponent(overheadLogoPanel,
                                                                                                GroupLayout.PREFERRED_SIZE,
                                                                                                GroupLayout.DEFAULT_SIZE,
                                                                                                GroupLayout.PREFERRED_SIZE)));
                overheadPanelLayout.setVerticalGroup(
                                overheadPanelLayout.createParallelGroup(GroupLayout.Alignment.LEADING)
                                                .addGroup(overheadPanelLayout.createSequentialGroup()
                                                                .addContainerGap()
                                                                .addComponent(welcomeLabel,
                                                                                GroupLayout.DEFAULT_SIZE,
                                                                                78, Short.MAX_VALUE)
                                                                .addContainerGap())
                                                .addComponent(overheadLogoPanel, GroupLayout.DEFAULT_SIZE,
                                                                GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE));

                getContentPane().add(overheadPanel,
                                new org.netbeans.lib.awtextra.AbsoluteConstraints(170, 0, 1100, 90));
        }

        private void initComp3() {
                setTables();
                playground.setBackground(new Color(255, 255, 255));

                salesPanel.setBackground(new Color(255, 255, 255));
                salesPanel.setBorder(new LineBorder(new Color(204, 204, 204), 3, true));

                headSalesPanel.setBackground(new Color(204, 204, 204));

                salesLabel.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
                salesLabel.setHorizontalAlignment(SwingConstants.LEFT);
                salesLabel.setText("SALES OVERVIEW");

                salesTuggleButton.setText("Last 14 days");

                GroupLayout headSalesPanelLayout = new GroupLayout(headSalesPanel);
                headSalesPanel.setLayout(headSalesPanelLayout);
                headSalesPanelLayout.setHorizontalGroup(
                                headSalesPanelLayout.createParallelGroup(GroupLayout.Alignment.LEADING)
                                                .addGroup(headSalesPanelLayout.createSequentialGroup()
                                                                .addContainerGap()
                                                                .addComponent(salesLabel,
                                                                                GroupLayout.PREFERRED_SIZE,
                                                                                434,
                                                                                GroupLayout.PREFERRED_SIZE)
                                                                .addGap(18, 18, 18)
                                                                .addComponent(salesTuggleButton,
                                                                                GroupLayout.DEFAULT_SIZE,
                                                                                124, Short.MAX_VALUE)
                                                                .addGap(10, 10, 10)));
                headSalesPanelLayout.setVerticalGroup(
                                headSalesPanelLayout.createParallelGroup(GroupLayout.Alignment.LEADING)
                                                .addComponent(salesLabel, GroupLayout.DEFAULT_SIZE, 31,
                                                                Short.MAX_VALUE)
                                                .addComponent(salesTuggleButton, GroupLayout.DEFAULT_SIZE,
                                                                GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE));

                GroupLayout salesContentPanelLayout = new GroupLayout(salesContentPanel);
                salesContentPanel.setLayout(salesContentPanelLayout);
                salesContentPanelLayout.setHorizontalGroup(
                                salesContentPanelLayout.createParallelGroup(GroupLayout.Alignment.LEADING)
                                                .addGap(0, 0, Short.MAX_VALUE));
                salesContentPanelLayout.setVerticalGroup(
                                salesContentPanelLayout.createParallelGroup(GroupLayout.Alignment.LEADING)
                                                .addGap(0, 234, Short.MAX_VALUE));

                GroupLayout salesPanelLayout = new GroupLayout(salesPanel);
                salesPanel.setLayout(salesPanelLayout);
                salesPanelLayout.setHorizontalGroup(
                                salesPanelLayout.createParallelGroup(GroupLayout.Alignment.LEADING)
                                                .addComponent(headSalesPanel, GroupLayout.DEFAULT_SIZE,
                                                                GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                                .addGroup(salesPanelLayout.createSequentialGroup()
                                                                .addContainerGap()
                                                                .addComponent(salesContentPanel,
                                                                                GroupLayout.DEFAULT_SIZE,
                                                                                GroupLayout.DEFAULT_SIZE,
                                                                                Short.MAX_VALUE)
                                                                .addContainerGap()));
                salesPanelLayout.setVerticalGroup(
                                salesPanelLayout.createParallelGroup(GroupLayout.Alignment.LEADING)
                                                .addGroup(salesPanelLayout.createSequentialGroup()
                                                                .addComponent(headSalesPanel,
                                                                                GroupLayout.PREFERRED_SIZE,
                                                                                GroupLayout.DEFAULT_SIZE,
                                                                                GroupLayout.PREFERRED_SIZE)
                                                                .addPreferredGap(
                                                                                ComponentPlacement.RELATED)
                                                                .addComponent(salesContentPanel,
                                                                                GroupLayout.DEFAULT_SIZE,
                                                                                GroupLayout.DEFAULT_SIZE,
                                                                                Short.MAX_VALUE)
                                                                .addContainerGap()));

                ordersPanel.setBackground(new Color(255, 255, 255));
                ordersPanel.setBorder(new LineBorder(new Color(204, 204, 204), 3, true));
                ordersPanel.setMaximumSize(new Dimension(270, 270));
                ordersPanel.setMinimumSize(new Dimension(270, 270));
                ordersPanel.setPreferredSize(new Dimension(270, 270));

                headOrdersPanel.setBackground(new Color(204, 204, 204));
                headOrdersPanel.setPreferredSize(new Dimension(295, 37));

                ordersLabel.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
                ordersLabel.setHorizontalAlignment(SwingConstants.CENTER);
                ordersLabel.setText("LAST 10 ORDERS");

                GroupLayout headOrdersPanelLayout = new GroupLayout(headOrdersPanel);
                headOrdersPanel.setLayout(headOrdersPanelLayout);
                headOrdersPanelLayout.setHorizontalGroup(
                                headOrdersPanelLayout.createParallelGroup(GroupLayout.Alignment.LEADING)
                                                .addGroup(headOrdersPanelLayout.createSequentialGroup()
                                                                .addContainerGap()
                                                                .addComponent(ordersLabel,
                                                                                GroupLayout.DEFAULT_SIZE,
                                                                                GroupLayout.DEFAULT_SIZE,
                                                                                Short.MAX_VALUE)
                                                                .addContainerGap()));
                headOrdersPanelLayout.setVerticalGroup(
                                headOrdersPanelLayout.createParallelGroup(GroupLayout.Alignment.LEADING)
                                                .addComponent(ordersLabel, GroupLayout.DEFAULT_SIZE, 37,
                                                                Short.MAX_VALUE));

                ordersPane.setMaximumSize(new Dimension(252, 215));
                ordersPane.setMinimumSize(new Dimension(252, 215));
                ordersPane.setPreferredSize(new Dimension(252, 215));

                GroupLayout ordersPanelLayout = new GroupLayout(ordersPanel);
                ordersPanel.setLayout(ordersPanelLayout);
                ordersPanelLayout.setHorizontalGroup(
                                ordersPanelLayout.createParallelGroup(GroupLayout.Alignment.LEADING)
                                                .addComponent(headOrdersPanel, GroupLayout.DEFAULT_SIZE,
                                                                276, Short.MAX_VALUE)
                                                .addGroup(GroupLayout.Alignment.TRAILING, ordersPanelLayout
                                                                .createSequentialGroup()
                                                                .addComponent(ordersPane,
                                                                                GroupLayout.DEFAULT_SIZE,
                                                                                276, Short.MAX_VALUE)
                                                                .addGap(0, 0, 0)));
                ordersPanelLayout.setVerticalGroup(
                                ordersPanelLayout.createParallelGroup(GroupLayout.Alignment.LEADING)
                                                .addGroup(ordersPanelLayout.createSequentialGroup()
                                                                .addComponent(headOrdersPanel,
                                                                                GroupLayout.PREFERRED_SIZE,
                                                                                GroupLayout.DEFAULT_SIZE,
                                                                                GroupLayout.PREFERRED_SIZE)
                                                                .addGap(0, 0, 0)
                                                                .addComponent(ordersPane,
                                                                                GroupLayout.DEFAULT_SIZE,
                                                                                GroupLayout.DEFAULT_SIZE,
                                                                                Short.MAX_VALUE)));

                bsTodayPanel.setBackground(new Color(255, 255, 255));
                bsTodayPanel.setBorder(new LineBorder(new Color(204, 204, 204), 3, true));
                bsTodayPanel.setPreferredSize(new Dimension(295, 282));

                bsTodayPanelBackground.setBackground(new Color(204, 204, 204));

                bsTodayProductLabel.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
                bsTodayProductLabel.setHorizontalAlignment(SwingConstants.CENTER);
                product todayProd = new dashboardsAPI().getMostSoldProductToday();
                if (todayProd == null)
                        bsTodayProductLabel.setText("");
                else
                        bsTodayProductLabel.setText(todayProd.getName());

                bsTodayTitlePanel.setFont(new java.awt.Font("Segoe UI", 1, 17)); // NOI18N
                bsTodayTitlePanel.setHorizontalAlignment(SwingConstants.CENTER);
                bsTodayTitlePanel.setText("BEST SELLER TODAY");

                GroupLayout bsTodayPanelBackgroundLayout = new GroupLayout(
                                bsTodayPanelBackground);
                bsTodayPanelBackground.setLayout(bsTodayPanelBackgroundLayout);
                bsTodayPanelBackgroundLayout.setHorizontalGroup(
                                bsTodayPanelBackgroundLayout
                                                .createParallelGroup(GroupLayout.Alignment.LEADING)
                                                .addGroup(bsTodayPanelBackgroundLayout.createSequentialGroup()
                                                                .addGroup(bsTodayPanelBackgroundLayout
                                                                                .createParallelGroup(
                                                                                                GroupLayout.Alignment.TRAILING,
                                                                                                false)
                                                                                .addGroup(bsTodayPanelBackgroundLayout
                                                                                                .createSequentialGroup()
                                                                                                .addContainerGap()
                                                                                                .addComponent(bsTodayProductLabel,
                                                                                                                GroupLayout.DEFAULT_SIZE,
                                                                                                                GroupLayout.DEFAULT_SIZE,
                                                                                                                Short.MAX_VALUE))
                                                                                .addComponent(bsTodayTitlePanel,
                                                                                                GroupLayout.PREFERRED_SIZE,
                                                                                                201,
                                                                                                GroupLayout.PREFERRED_SIZE))
                                                                .addContainerGap(GroupLayout.DEFAULT_SIZE,
                                                                                Short.MAX_VALUE)));
                bsTodayPanelBackgroundLayout.setVerticalGroup(
                                bsTodayPanelBackgroundLayout
                                                .createParallelGroup(GroupLayout.Alignment.LEADING)
                                                .addGroup(bsTodayPanelBackgroundLayout.createSequentialGroup()
                                                                .addComponent(bsTodayTitlePanel)
                                                                .addPreferredGap(
                                                                                ComponentPlacement.RELATED)
                                                                .addComponent(bsTodayProductLabel,
                                                                                GroupLayout.PREFERRED_SIZE,
                                                                                64,
                                                                                GroupLayout.PREFERRED_SIZE)
                                                                .addContainerGap(GroupLayout.DEFAULT_SIZE,
                                                                                Short.MAX_VALUE)));

                GroupLayout bsTodayPanelLayout = new GroupLayout(bsTodayPanel);
                bsTodayPanel.setLayout(bsTodayPanelLayout);
                bsTodayPanelLayout.setHorizontalGroup(
                                bsTodayPanelLayout.createParallelGroup(GroupLayout.Alignment.LEADING)
                                                .addComponent(bsTodayPanelBackground,
                                                                GroupLayout.PREFERRED_SIZE, 206,
                                                                Short.MAX_VALUE));
                bsTodayPanelLayout.setVerticalGroup(
                                bsTodayPanelLayout.createParallelGroup(GroupLayout.Alignment.LEADING)
                                                .addComponent(bsTodayPanelBackground,
                                                                GroupLayout.DEFAULT_SIZE,
                                                                GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE));

                employeesPanel.setBackground(new Color(255, 255, 255));
                employeesPanel.setBorder(new LineBorder(new Color(204, 204, 204), 3, true));
                employeesPanel.setMaximumSize(new Dimension(270, 270));
                employeesPanel.setMinimumSize(new Dimension(270, 270));

                headEmployeesPanel.setBackground(new Color(204, 204, 204));
                headEmployeesPanel.setPreferredSize(new Dimension(295, 37));

                employeesLabel.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
                employeesLabel.setHorizontalAlignment(SwingConstants.CENTER);
                employeesLabel.setText("WORKING EMPLOYEES");

                GroupLayout headEmployeesPanelLayout = new GroupLayout(headEmployeesPanel);
                headEmployeesPanel.setLayout(headEmployeesPanelLayout);
                headEmployeesPanelLayout.setHorizontalGroup(
                                headEmployeesPanelLayout.createParallelGroup(GroupLayout.Alignment.LEADING)
                                                .addGroup(headEmployeesPanelLayout.createSequentialGroup()
                                                                .addContainerGap()
                                                                .addComponent(employeesLabel,
                                                                                GroupLayout.DEFAULT_SIZE,
                                                                                GroupLayout.DEFAULT_SIZE,
                                                                                Short.MAX_VALUE)
                                                                .addContainerGap()));
                headEmployeesPanelLayout.setVerticalGroup(
                                headEmployeesPanelLayout.createParallelGroup(GroupLayout.Alignment.LEADING)
                                                .addComponent(employeesLabel, GroupLayout.DEFAULT_SIZE, 37,
                                                                Short.MAX_VALUE));

                employeesPane.setMaximumSize(new Dimension(252, 215));
                employeesPane.setMinimumSize(new Dimension(252, 215));
                employeesPane.setPreferredSize(new Dimension(252, 215));

                GroupLayout employeesPanelLayout = new GroupLayout(employeesPanel);
                employeesPanel.setLayout(employeesPanelLayout);
                employeesPanelLayout.setHorizontalGroup(
                                employeesPanelLayout.createParallelGroup(GroupLayout.Alignment.LEADING)
                                                .addComponent(headEmployeesPanel, GroupLayout.DEFAULT_SIZE,
                                                                282, Short.MAX_VALUE)
                                                .addComponent(employeesPane, GroupLayout.DEFAULT_SIZE,
                                                                GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE));
                employeesPanelLayout.setVerticalGroup(
                                employeesPanelLayout.createParallelGroup(GroupLayout.Alignment.LEADING)
                                                .addGroup(employeesPanelLayout.createSequentialGroup()
                                                                .addComponent(headEmployeesPanel,
                                                                                GroupLayout.PREFERRED_SIZE,
                                                                                GroupLayout.DEFAULT_SIZE,
                                                                                GroupLayout.PREFERRED_SIZE)
                                                                .addGap(0, 0, 0)
                                                                .addComponent(employeesPane,
                                                                                GroupLayout.DEFAULT_SIZE,
                                                                                GroupLayout.DEFAULT_SIZE,
                                                                                Short.MAX_VALUE)
                                                                .addGap(0, 0, 0)));

                bsWeekPanel.setBackground(new Color(255, 255, 255));
                bsWeekPanel.setBorder(new LineBorder(new Color(204, 204, 204), 3, true));
                bsWeekPanel.setPreferredSize(new Dimension(295, 282));

                bsWeekPanelBackground.setBackground(new Color(204, 204, 204));

                bsWeekProductLabel.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
                bsWeekProductLabel.setHorizontalAlignment(SwingConstants.CENTER);
                product weekProd = new dashboardsAPI().getMostSoldProductWeek();
                if (weekProd == null)
                        bsWeekProductLabel.setText("");
                else
                        bsWeekProductLabel.setText(weekProd.getName());
                bsWeekTitlePanel.setFont(new java.awt.Font("Segoe UI", 1, 17)); // NOI18N
                bsWeekTitlePanel.setHorizontalAlignment(SwingConstants.CENTER);
                bsWeekTitlePanel.setText("BEST SELLER WEEK");

                GroupLayout bsWeekPanelBackgroundLayout = new GroupLayout(
                                bsWeekPanelBackground);
                bsWeekPanelBackground.setLayout(bsWeekPanelBackgroundLayout);
                bsWeekPanelBackgroundLayout.setHorizontalGroup(
                                bsWeekPanelBackgroundLayout
                                                .createParallelGroup(GroupLayout.Alignment.LEADING)
                                                .addGroup(bsWeekPanelBackgroundLayout.createSequentialGroup()
                                                                .addGroup(bsWeekPanelBackgroundLayout
                                                                                .createParallelGroup(
                                                                                                GroupLayout.Alignment.TRAILING,
                                                                                                false)
                                                                                .addGroup(bsWeekPanelBackgroundLayout
                                                                                                .createSequentialGroup()
                                                                                                .addContainerGap()
                                                                                                .addComponent(bsWeekProductLabel,
                                                                                                                GroupLayout.DEFAULT_SIZE,
                                                                                                                GroupLayout.DEFAULT_SIZE,
                                                                                                                Short.MAX_VALUE))
                                                                                .addComponent(bsWeekTitlePanel,
                                                                                                GroupLayout.PREFERRED_SIZE,
                                                                                                201,
                                                                                                GroupLayout.PREFERRED_SIZE))
                                                                .addContainerGap(GroupLayout.DEFAULT_SIZE,
                                                                                Short.MAX_VALUE)));
                bsWeekPanelBackgroundLayout.setVerticalGroup(
                                bsWeekPanelBackgroundLayout
                                                .createParallelGroup(GroupLayout.Alignment.LEADING)
                                                .addGroup(bsWeekPanelBackgroundLayout.createSequentialGroup()
                                                                .addComponent(bsWeekTitlePanel)
                                                                .addPreferredGap(
                                                                                ComponentPlacement.RELATED)
                                                                .addComponent(bsWeekProductLabel,
                                                                                GroupLayout.PREFERRED_SIZE,
                                                                                64,
                                                                                GroupLayout.PREFERRED_SIZE)
                                                                .addContainerGap(GroupLayout.DEFAULT_SIZE,
                                                                                Short.MAX_VALUE)));

                GroupLayout bsWeekPanelLayout = new GroupLayout(bsWeekPanel);
                bsWeekPanel.setLayout(bsWeekPanelLayout);
                bsWeekPanelLayout.setHorizontalGroup(
                                bsWeekPanelLayout.createParallelGroup(GroupLayout.Alignment.LEADING)
                                                .addComponent(bsWeekPanelBackground,
                                                                GroupLayout.PREFERRED_SIZE, 206,
                                                                Short.MAX_VALUE));
                bsWeekPanelLayout.setVerticalGroup(
                                bsWeekPanelLayout.createParallelGroup(GroupLayout.Alignment.LEADING)
                                                .addComponent(bsWeekPanelBackground,
                                                                GroupLayout.DEFAULT_SIZE,
                                                                GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE));

                bsMonthPanel.setBackground(new Color(255, 255, 255));
                bsMonthPanel.setBorder(new LineBorder(new Color(204, 204, 204), 3, true));
                bsMonthPanel.setPreferredSize(new Dimension(295, 282));

                bsMonthPanelBackground.setBackground(new Color(204, 204, 204));

                bsMonthProductLabel.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
                bsMonthProductLabel.setHorizontalAlignment(SwingConstants.CENTER);
                product monthProd = new dashboardsAPI().getMostSoldProductMonth();
                if (monthProd == null)
                        bsMonthProductLabel.setText("");
                else
                        bsMonthProductLabel.setText(weekProd.getName());
                bsMonthTitlePanel.setFont(new java.awt.Font("Segoe UI", 1, 17)); // NOI18N
                bsMonthTitlePanel.setHorizontalAlignment(SwingConstants.CENTER);
                bsMonthTitlePanel.setText("BEST SELLER MONTH");

                GroupLayout bsMonthPanelBackgroundLayout = new GroupLayout(
                                bsMonthPanelBackground);
                bsMonthPanelBackground.setLayout(bsMonthPanelBackgroundLayout);
                bsMonthPanelBackgroundLayout.setHorizontalGroup(
                                bsMonthPanelBackgroundLayout
                                                .createParallelGroup(GroupLayout.Alignment.LEADING)
                                                .addGroup(bsMonthPanelBackgroundLayout.createSequentialGroup()
                                                                .addGroup(bsMonthPanelBackgroundLayout
                                                                                .createParallelGroup(
                                                                                                GroupLayout.Alignment.TRAILING,
                                                                                                false)
                                                                                .addGroup(bsMonthPanelBackgroundLayout
                                                                                                .createSequentialGroup()
                                                                                                .addContainerGap()
                                                                                                .addComponent(bsMonthProductLabel,
                                                                                                                GroupLayout.DEFAULT_SIZE,
                                                                                                                GroupLayout.DEFAULT_SIZE,
                                                                                                                Short.MAX_VALUE))
                                                                                .addComponent(bsMonthTitlePanel,
                                                                                                GroupLayout.PREFERRED_SIZE,
                                                                                                201,
                                                                                                GroupLayout.PREFERRED_SIZE))
                                                                .addContainerGap(GroupLayout.DEFAULT_SIZE,
                                                                                Short.MAX_VALUE)));
                bsMonthPanelBackgroundLayout.setVerticalGroup(
                                bsMonthPanelBackgroundLayout
                                                .createParallelGroup(GroupLayout.Alignment.LEADING)
                                                .addGroup(bsMonthPanelBackgroundLayout.createSequentialGroup()
                                                                .addComponent(bsMonthTitlePanel)
                                                                .addPreferredGap(
                                                                                ComponentPlacement.RELATED)
                                                                .addComponent(bsMonthProductLabel,
                                                                                GroupLayout.PREFERRED_SIZE,
                                                                                64,
                                                                                GroupLayout.PREFERRED_SIZE)
                                                                .addContainerGap(GroupLayout.DEFAULT_SIZE,
                                                                                Short.MAX_VALUE)));

                GroupLayout bsMonthPanelLayout = new GroupLayout(bsMonthPanel);
                bsMonthPanel.setLayout(bsMonthPanelLayout);
                bsMonthPanelLayout.setHorizontalGroup(
                                bsMonthPanelLayout.createParallelGroup(GroupLayout.Alignment.LEADING)
                                                .addComponent(bsMonthPanelBackground,
                                                                GroupLayout.PREFERRED_SIZE, 206,
                                                                Short.MAX_VALUE));
                bsMonthPanelLayout.setVerticalGroup(
                                bsMonthPanelLayout.createParallelGroup(GroupLayout.Alignment.LEADING)
                                                .addComponent(bsMonthPanelBackground,
                                                                GroupLayout.DEFAULT_SIZE,
                                                                GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE));

                pricePanel.setBackground(new Color(255, 255, 255));
                pricePanel.setBorder(new LineBorder(new Color(204, 204, 204), 3, true));
                pricePanel.setPreferredSize(new Dimension(295, 282));

                pricePanelBackground.setBackground(new Color(204, 204, 204));

                pricePriceLabel.setFont(new java.awt.Font("Segoe UI", 0, 30)); // NOI18N
                pricePriceLabel.setHorizontalAlignment(SwingConstants.RIGHT);
                pricePriceLabel.setText(String.format("%.2f", new dashboardsAPI().getAvgSalePriceToday()).concat(" €"));

                priceIconLabel.setIcon(new javax.swing.ImageIcon(
                                "C:\\Users\\Juan Diego\\Desktop\\netbeansss\\guiProjectTEST\\src\\money-bag.png")); // NOI18N

                priceTitleLabel.setFont(new java.awt.Font("Segoe UI", 1, 17)); // NOI18N
                priceTitleLabel.setHorizontalAlignment(SwingConstants.CENTER);
                priceTitleLabel.setText("AVG. ORDER TODAY");

                GroupLayout pricePanelBackgroundLayout = new GroupLayout(pricePanelBackground);
                pricePanelBackground.setLayout(pricePanelBackgroundLayout);
                pricePanelBackgroundLayout.setHorizontalGroup(
                                pricePanelBackgroundLayout
                                                .createParallelGroup(GroupLayout.Alignment.LEADING)
                                                .addGroup(pricePanelBackgroundLayout.createSequentialGroup()
                                                                .addContainerGap()
                                                                .addGroup(pricePanelBackgroundLayout
                                                                                .createParallelGroup(
                                                                                                GroupLayout.Alignment.LEADING)
                                                                                .addGroup(pricePanelBackgroundLayout
                                                                                                .createSequentialGroup()
                                                                                                .addGap(6, 6, 6)
                                                                                                .addComponent(pricePriceLabel,
                                                                                                                GroupLayout.DEFAULT_SIZE,
                                                                                                                105,
                                                                                                                Short.MAX_VALUE)
                                                                                                .addPreferredGap(
                                                                                                                ComponentPlacement.RELATED)
                                                                                                .addComponent(priceIconLabel))
                                                                                .addComponent(priceTitleLabel,
                                                                                                GroupLayout.DEFAULT_SIZE,
                                                                                                GroupLayout.DEFAULT_SIZE,
                                                                                                Short.MAX_VALUE))
                                                                .addContainerGap()));
                pricePanelBackgroundLayout.setVerticalGroup(
                                pricePanelBackgroundLayout
                                                .createParallelGroup(GroupLayout.Alignment.LEADING)
                                                .addGroup(pricePanelBackgroundLayout.createSequentialGroup()
                                                                .addComponent(priceTitleLabel)
                                                                .addPreferredGap(
                                                                                ComponentPlacement.RELATED)
                                                                .addGroup(pricePanelBackgroundLayout
                                                                                .createParallelGroup(
                                                                                                GroupLayout.Alignment.LEADING,
                                                                                                false)
                                                                                .addComponent(priceIconLabel,
                                                                                                GroupLayout.DEFAULT_SIZE,
                                                                                                GroupLayout.DEFAULT_SIZE,
                                                                                                Short.MAX_VALUE)
                                                                                .addComponent(pricePriceLabel,
                                                                                                GroupLayout.DEFAULT_SIZE,
                                                                                                GroupLayout.DEFAULT_SIZE,
                                                                                                Short.MAX_VALUE))
                                                                .addContainerGap(GroupLayout.DEFAULT_SIZE,
                                                                                Short.MAX_VALUE)));

                GroupLayout pricePanelLayout = new GroupLayout(pricePanel);
                pricePanel.setLayout(pricePanelLayout);
                pricePanelLayout.setHorizontalGroup(
                                pricePanelLayout.createParallelGroup(GroupLayout.Alignment.LEADING)
                                                .addComponent(pricePanelBackground,
                                                                GroupLayout.DEFAULT_SIZE,
                                                                GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE));
                pricePanelLayout.setVerticalGroup(
                                pricePanelLayout.createParallelGroup(GroupLayout.Alignment.LEADING)
                                                .addComponent(pricePanelBackground,
                                                                GroupLayout.DEFAULT_SIZE,
                                                                GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE));

                timePanel.setBackground(new Color(255, 255, 255));
                timePanel.setBorder(new LineBorder(new Color(204, 204, 204), 3, true));
                timePanel.setPreferredSize(new Dimension(295, 282));

                timePanelBackground.setBackground(new Color(204, 204, 204));

                timeTimeLabel.setFont(new java.awt.Font("Segoe UI", 0, 30)); // NOI18N
                timeTimeLabel.setHorizontalAlignment(SwingConstants.RIGHT);
                timeTimeLabel.setText(Integer.toString(new dashboardsAPI().getAvgOrderTimeToday()).concat(" min."));
                timeIconLabel.setIcon(new javax.swing.ImageIcon(
                                "C:\\Users\\Juan Diego\\Desktop\\netbeansss\\guiProjectTEST\\src\\stopwatch.png")); // NOI18N

                timeTitleLabel.setFont(new java.awt.Font("Segoe UI", 1, 17)); // NOI18N
                timeTitleLabel.setHorizontalAlignment(SwingConstants.CENTER);
                timeTitleLabel.setText("AVG. ORDER TIME");

                GroupLayout timePanelBackgroundLayout = new GroupLayout(timePanelBackground);
                timePanelBackground.setLayout(timePanelBackgroundLayout);
                timePanelBackgroundLayout.setHorizontalGroup(
                                timePanelBackgroundLayout.createParallelGroup(GroupLayout.Alignment.LEADING)
                                                .addGroup(timePanelBackgroundLayout.createSequentialGroup()
                                                                .addContainerGap()
                                                                .addGroup(timePanelBackgroundLayout.createParallelGroup(
                                                                                GroupLayout.Alignment.LEADING)
                                                                                .addGroup(timePanelBackgroundLayout
                                                                                                .createSequentialGroup()
                                                                                                .addGap(6, 6, 6)
                                                                                                .addComponent(timeTimeLabel,
                                                                                                                GroupLayout.DEFAULT_SIZE,
                                                                                                                105,
                                                                                                                Short.MAX_VALUE)
                                                                                                .addPreferredGap(
                                                                                                                ComponentPlacement.RELATED)
                                                                                                .addComponent(timeIconLabel))
                                                                                .addComponent(timeTitleLabel,
                                                                                                GroupLayout.DEFAULT_SIZE,
                                                                                                GroupLayout.DEFAULT_SIZE,
                                                                                                Short.MAX_VALUE))
                                                                .addContainerGap()));
                timePanelBackgroundLayout.setVerticalGroup(
                                timePanelBackgroundLayout.createParallelGroup(GroupLayout.Alignment.LEADING)
                                                .addGroup(timePanelBackgroundLayout.createSequentialGroup()
                                                                .addComponent(timeTitleLabel)
                                                                .addPreferredGap(
                                                                                ComponentPlacement.RELATED)
                                                                .addGroup(timePanelBackgroundLayout.createParallelGroup(
                                                                                GroupLayout.Alignment.LEADING,
                                                                                false)
                                                                                .addComponent(timeIconLabel,
                                                                                                GroupLayout.DEFAULT_SIZE,
                                                                                                GroupLayout.DEFAULT_SIZE,
                                                                                                Short.MAX_VALUE)
                                                                                .addComponent(timeTimeLabel,
                                                                                                GroupLayout.DEFAULT_SIZE,
                                                                                                GroupLayout.DEFAULT_SIZE,
                                                                                                Short.MAX_VALUE))
                                                                .addContainerGap(GroupLayout.DEFAULT_SIZE,
                                                                                Short.MAX_VALUE)));

                GroupLayout timePanelLayout = new GroupLayout(timePanel);
                timePanel.setLayout(timePanelLayout);
                timePanelLayout.setHorizontalGroup(
                                timePanelLayout.createParallelGroup(GroupLayout.Alignment.LEADING)
                                                .addComponent(timePanelBackground, GroupLayout.DEFAULT_SIZE,
                                                                GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE));
                timePanelLayout.setVerticalGroup(
                                timePanelLayout.createParallelGroup(GroupLayout.Alignment.LEADING)
                                                .addComponent(timePanelBackground, GroupLayout.DEFAULT_SIZE,
                                                                GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE));

                GroupLayout playgroundLayout = new GroupLayout(playground);
                playground.setLayout(playgroundLayout);
                playgroundLayout.setHorizontalGroup(
                                playgroundLayout.createParallelGroup(GroupLayout.Alignment.LEADING)
                                                .addGroup(playgroundLayout.createSequentialGroup()
                                                                .addContainerGap()
                                                                .addGroup(playgroundLayout.createParallelGroup(
                                                                                GroupLayout.Alignment.LEADING,
                                                                                false)
                                                                                .addGroup(playgroundLayout
                                                                                                .createSequentialGroup()
                                                                                                .addComponent(employeesPanel,
                                                                                                                GroupLayout.DEFAULT_SIZE,
                                                                                                                GroupLayout.DEFAULT_SIZE,
                                                                                                                Short.MAX_VALUE)
                                                                                                .addGap(34, 34, 34)
                                                                                                .addComponent(ordersPanel,
                                                                                                                GroupLayout.PREFERRED_SIZE,
                                                                                                                282,
                                                                                                                GroupLayout.PREFERRED_SIZE))
                                                                                .addComponent(salesPanel,
                                                                                                GroupLayout.PREFERRED_SIZE,
                                                                                                GroupLayout.DEFAULT_SIZE,
                                                                                                GroupLayout.PREFERRED_SIZE))
                                                                .addGap(18, 18, Short.MAX_VALUE)
                                                                .addGroup(playgroundLayout.createParallelGroup(
                                                                                GroupLayout.Alignment.LEADING)
                                                                                .addGroup(playgroundLayout
                                                                                                .createSequentialGroup()
                                                                                                .addPreferredGap(
                                                                                                                ComponentPlacement.RELATED,
                                                                                                                3,
                                                                                                                Short.MAX_VALUE)
                                                                                                .addComponent(bsTodayPanel,
                                                                                                                GroupLayout.PREFERRED_SIZE,
                                                                                                                212,
                                                                                                                GroupLayout.PREFERRED_SIZE)
                                                                                                .addGap(21, 21, 21))
                                                                                .addGroup(playgroundLayout
                                                                                                .createSequentialGroup()
                                                                                                .addGroup(playgroundLayout
                                                                                                                .createParallelGroup(
                                                                                                                                GroupLayout.Alignment.LEADING)
                                                                                                                .addComponent(bsMonthPanel,
                                                                                                                                GroupLayout.PREFERRED_SIZE,
                                                                                                                                212,
                                                                                                                                GroupLayout.PREFERRED_SIZE)
                                                                                                                .addComponent(bsWeekPanel,
                                                                                                                                GroupLayout.PREFERRED_SIZE,
                                                                                                                                212,
                                                                                                                                GroupLayout.PREFERRED_SIZE))
                                                                                                .addContainerGap(
                                                                                                                GroupLayout.DEFAULT_SIZE,
                                                                                                                Short.MAX_VALUE))
                                                                                .addGroup(playgroundLayout
                                                                                                .createSequentialGroup()
                                                                                                .addGroup(playgroundLayout
                                                                                                                .createParallelGroup(
                                                                                                                                GroupLayout.Alignment.LEADING)
                                                                                                                .addComponent(pricePanel,
                                                                                                                                GroupLayout.PREFERRED_SIZE,
                                                                                                                                212,
                                                                                                                                GroupLayout.PREFERRED_SIZE)
                                                                                                                .addComponent(timePanel,
                                                                                                                                GroupLayout.PREFERRED_SIZE,
                                                                                                                                212,
                                                                                                                                GroupLayout.PREFERRED_SIZE))
                                                                                                .addGap(0, 0, Short.MAX_VALUE)))));
                playgroundLayout.setVerticalGroup(
                                playgroundLayout.createParallelGroup(GroupLayout.Alignment.LEADING)
                                                .addGroup(playgroundLayout.createSequentialGroup()
                                                                .addContainerGap()
                                                                .addGroup(playgroundLayout.createParallelGroup(
                                                                                GroupLayout.Alignment.LEADING,
                                                                                false)
                                                                                .addGroup(playgroundLayout
                                                                                                .createSequentialGroup()
                                                                                                .addComponent(bsTodayPanel,
                                                                                                                GroupLayout.PREFERRED_SIZE,
                                                                                                                111,
                                                                                                                GroupLayout.PREFERRED_SIZE)
                                                                                                .addPreferredGap(
                                                                                                                ComponentPlacement.UNRELATED)
                                                                                                .addComponent(bsWeekPanel,
                                                                                                                GroupLayout.PREFERRED_SIZE,
                                                                                                                111,
                                                                                                                GroupLayout.PREFERRED_SIZE)
                                                                                                .addPreferredGap(
                                                                                                                ComponentPlacement.UNRELATED)
                                                                                                .addComponent(bsMonthPanel,
                                                                                                                GroupLayout.PREFERRED_SIZE,
                                                                                                                111,
                                                                                                                GroupLayout.PREFERRED_SIZE)
                                                                                                .addPreferredGap(
                                                                                                                ComponentPlacement.UNRELATED)
                                                                                                .addComponent(pricePanel,
                                                                                                                GroupLayout.PREFERRED_SIZE,
                                                                                                                111,
                                                                                                                GroupLayout.PREFERRED_SIZE)
                                                                                                .addPreferredGap(
                                                                                                                ComponentPlacement.UNRELATED)
                                                                                                .addComponent(timePanel,
                                                                                                                GroupLayout.PREFERRED_SIZE,
                                                                                                                111,
                                                                                                                GroupLayout.PREFERRED_SIZE))
                                                                                .addGroup(playgroundLayout
                                                                                                .createSequentialGroup()
                                                                                                .addComponent(salesPanel,
                                                                                                                GroupLayout.PREFERRED_SIZE,
                                                                                                                GroupLayout.DEFAULT_SIZE,
                                                                                                                GroupLayout.PREFERRED_SIZE)
                                                                                                .addGap(18, 18, 18)
                                                                                                .addGroup(playgroundLayout
                                                                                                                .createParallelGroup(
                                                                                                                                GroupLayout.Alignment.LEADING)
                                                                                                                .addComponent(ordersPanel,
                                                                                                                                GroupLayout.DEFAULT_SIZE,
                                                                                                                                298,
                                                                                                                                Short.MAX_VALUE)
                                                                                                                .addComponent(employeesPanel,
                                                                                                                                GroupLayout.DEFAULT_SIZE,
                                                                                                                                GroupLayout.DEFAULT_SIZE,
                                                                                                                                Short.MAX_VALUE))))
                                                                .addContainerGap(GroupLayout.DEFAULT_SIZE,
                                                                                Short.MAX_VALUE)));

                getContentPane().add(playground, new org.netbeans.lib.awtextra.AbsoluteConstraints(400, 90, 870, 630));
                pack();
                setGraph(7);
        }

        private void setGraph(int goal) {
                salesContentPanel.removeAll();
                DefaultCategoryDataset datos = new DefaultCategoryDataset();
                dashboardsAPI dbAPI = new dashboardsAPI();
                String date = "";
                for (int i = goal - 1; i > -1; i--) {
                        if (goal == 7)
                                date = LocalDate.now().minus(i, ChronoUnit.DAYS)
                                                .format(DateTimeFormatter.ofPattern("dd-MM"));
                        else
                                date = Integer.toString(-i);
                        datos.setValue(dbAPI.getSalesOnDay(i), "Sales", date);
                }

                JFreeChart barChart = ChartFactory.createBarChart("Sales in Last " + goal + " Days", null, "Sales (€)",
                                datos,
                                PlotOrientation.VERTICAL, true, true, false);
                ChartPanel pan = new ChartPanel(barChart);
                pan.setMouseWheelEnabled(false);
                pan.setPreferredSize(new Dimension(400, 200));

                salesContentPanel.setLayout(new BorderLayout());
                salesContentPanel.add(pan, BorderLayout.CENTER);
                pack();
                salesContentPanel.revalidate();
                salesPanel.repaint();
        }

        private void initComponents() {
                initComp1();
                initCompTop();
                initComp2();
                initComp3();
                pack();
                addActionListeners();
                setVisible(true);
        }

        private void addActionListeners() {
                JFrame tempFrame = this;
                administrationLabel.addMouseListener(new MouseListener() {
                        boolean clicked = false;

                        public void mouseClicked(MouseEvent e) {
                                setColor(administrationPanel);
                                resetColor(homePanel);
                                resetColor(foodPanel);
                                clicked = true;
                                playground.removeAll();
                                leftAuxPanel.removeAll();
                                new adminDash(tempFrame, leftAuxPanel, playground, jPanel4, date);
                                playground.revalidate();
                                leftAuxPanel.revalidate();
                                playground.repaint();
                                leftAuxPanel.repaint();

                        }

                        public void mousePressed(MouseEvent e) {
                        }

                        public void mouseReleased(MouseEvent e) {
                        }

                        public void mouseEntered(MouseEvent e) {
                                if (administrationPanel.getBackground().equals(new Color(41, 57, 80)))
                                        clicked = true;
                                else
                                        clicked = false;
                                setColor(administrationPanel);
                        }

                        public void mouseExited(MouseEvent e) {
                                if (!clicked)
                                        resetColor(administrationPanel);
                                if (foodPanel.getBackground().equals(new Color(41, 57, 80))
                                                && homePanel.getBackground().equals(new Color(41, 57, 80)))
                                        clicked = false;
                        }
                });
                foodPanel.addMouseListener(new MouseListener() {
                        boolean clicked = false;

                        public void mouseClicked(MouseEvent e) {
                                setColor(foodPanel);
                                resetColor(homePanel);
                                resetColor(administrationPanel);
                                clicked = true;
                                playground.removeAll();
                                leftAuxPanel.removeAll();
                                new foodDash(leftAuxPanel, playground, jPanel4, date);
                                playground.revalidate();
                                leftAuxPanel.revalidate();
                                playground.repaint();
                                leftAuxPanel.repaint();
                        }

                        public void mousePressed(MouseEvent e) {
                        }

                        public void mouseReleased(MouseEvent e) {
                        }

                        public void mouseEntered(MouseEvent e) {
                                if (foodPanel.getBackground().equals(new Color(41, 57, 80)))
                                        clicked = true;
                                else
                                        clicked = false;
                                setColor(foodPanel);
                        }

                        public void mouseExited(MouseEvent e) {
                                if (!clicked)
                                        resetColor(foodPanel);
                                if (administrationPanel.getBackground().equals(new Color(41, 57, 80))
                                                && homePanel.getBackground().equals(new Color(41, 57, 80)))
                                        clicked = false;
                        }
                });
                homePanel.addMouseListener(new MouseListener() {
                        boolean clicked = false;

                        public void mouseClicked(MouseEvent e) {
                                setColor(homePanel);
                                resetColor(foodPanel);
                                resetColor(administrationPanel);
                                clicked = true;
                                leftAuxPanel.removeAll();
                                leftAuxPanel.revalidate();
                                leftAuxPanel.repaint();
                                playground.removeAll();
                                initComp2();
                                initComp3();
                                playground.revalidate();
                                playground.repaint();
                        }

                        public void mousePressed(MouseEvent e) {
                        }

                        public void mouseReleased(MouseEvent e) {
                        }

                        public void mouseEntered(MouseEvent e) {
                                if (homePanel.getBackground().equals(new Color(41, 57, 80)))
                                        clicked = true;
                                else
                                        clicked = false;
                                setColor(homePanel);
                        }

                        public void mouseExited(MouseEvent e) {
                                if (!clicked)
                                        resetColor(homePanel);
                                if (administrationPanel.getBackground().equals(new Color(41, 57, 80))
                                                && foodPanel.getBackground().equals(new Color(41, 57, 80)))
                                        clicked = false;
                        }
                });
                salesTuggleButton.addMouseListener(new MouseListener() {
                        public void mouseClicked(MouseEvent e) {
                                if (salesTuggleButton.getText().equals("Last 14 days")) {
                                        salesTuggleButton.setText("Last 7 days");
                                        setGraph(14);
                                } else {
                                        salesTuggleButton.setText("Last 14 days");
                                        setGraph(7);
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
        }

        private void setColor(JPanel panel) {
                panel.setBackground(new Color(41, 57, 80));
        }

        private void resetColor(JPanel panel) {
                panel.setBackground(new Color(23, 35, 51));
        }

        private void setTables() {
                JTable tableEmployees = new JTable();
                JTable tableOrders = new JTable();
                DefaultTableModel modelEmployees = new DefaultTableModel(
                                new String[] { "Name", "Role", "Time" },
                                0);

                DefaultTableModel modelOrders = new DefaultTableModel(
                                new String[] { "Time", "Subtotal", "Payment Method" }, 0);

                for (currentShiftEmployee temp : new dashboardsAPI().getCurrentlyWorkingEmployees()) {
                        String name = temp.getName();
                        String role = temp.getRole();
                        String timeIn = temp.getTimeIn().substring(0, 5);
                        String timeOut = temp.getTimeout().substring(0, 5);
                        modelEmployees.addRow(new String[] { name, role, timeIn.concat(" - ").concat(timeOut) });
                }
                for (orderView temp : new dashboardsAPI().getLast10OrderViewers()) {
                        String time = Integer.toString(temp.getTotalTimeMinutes()).concat(" min(s).");
                        String subtotal = String.format("%.2f", temp.getSubtotal()).concat(" €");
                        String method = temp.getPaymentMethod();
                        modelOrders.addRow(new String[] { time, subtotal, method });
                }

                tableEmployees.setModel(modelEmployees);
                tableOrders.setModel(modelOrders);
                employeesPane.setViewportView(tableEmployees);
                ordersPane.setViewportView(tableOrders);
                tableEmployees.setDefaultEditor(Object.class, null);
                tableOrders.setDefaultEditor(Object.class, null);
                tableLookPretty(tableEmployees);
                tableLookPretty(tableOrders);
        }

        private void tableLookPretty(JTable theTable) {
                theTable.setFocusable(true);
                theTable.setFillsViewportHeight(true);
                theTable.setFont(new java.awt.Font("Segoe UI", 0, 9));
                DefaultTableCellRenderer centerRenderer = new DefaultTableCellRenderer();
                centerRenderer.setHorizontalAlignment(JLabel.CENTER);
                for (int i = 0; i < theTable.getColumnModel().getColumnCount(); i++)
                        theTable.getColumnModel().getColumn(i).setCellRenderer(centerRenderer);
                Dimension temp = new Dimension(20, 1);
                theTable.setIntercellSpacing(temp);
                theTable.setRowHeight(theTable.getRowHeight() + 10);
                theTable.setTableHeader(null);
                theTable.setShowGrid(false);
                theTable.setShowHorizontalLines(true);
        }

}
