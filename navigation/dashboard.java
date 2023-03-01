package navigation;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.text.DecimalFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;

import javax.swing.*;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumnModel;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.data.category.DefaultCategoryDataset;

import componentsFood.currentShiftEmployee;
import componentsFood.orderView;
import navigation.administration.*;
import navigation.administration.reports_Window.reportsAPI;
import navigation.administration.shifts_Window.shiftsAPI;
import navigation.food.*;

public class dashboard extends JFrame {

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

        private JLabel overviewLabel = new JLabel();
        private JLabel employeesLabel = new JLabel();
        private JLabel ordersLabel = new JLabel();

        private JLabel priceMoneyLabel = new JLabel();
        private JLabel priceIconLabel = new JLabel();
        private JLabel priceTitleLabel = new JLabel();
        private JLabel timeTimeLabel = new JLabel();
        private JLabel timeIconLabel = new JLabel();
        private JLabel timeTitleLabel = new JLabel();

        private JPanel timePanel = new JPanel();
        private JPanel salesPanelBackground = new JPanel();
        private JPanel timePanelBackground = new JPanel();

        private JPanel overviewPanel = new JPanel();
        private JPanel headOverviewPanel = new JPanel();
        private JPanel headOrdersPanel = new JPanel();
        private JPanel headEmployeesPanel = new JPanel();
        private JPanel overviewContentsPanel = new JPanel();

        private JPanel employeesPanel = new JPanel();
        private JPanel ordersPanel = new JPanel();

        private JPanel salesPanel = new JPanel();

        private JTable tableEmployees = new JTable();
        private JTable tableOrders = new JTable();

        private JPanel administrationPanel1 = new JPanel();
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
        private JLabel employeeesLabel = new JLabel();
        private JPanel employeeesPanel = new JPanel();
        private JPanel employeesAux = new JPanel();
        private JPanel headMenuSalesPanel1 = new JPanel();
        private JPanel headSalesPanel = new JPanel();
        private JLabel menuSalesLabel1 = new JLabel();
        private JPanel menuSalesPanel1 = new JPanel();
        private JPanel pricePanel = new JPanel();
        private JPanel pricePanelBackground = new JPanel();
        private JLabel pricePriceLabel = new JLabel();
        private JPanel reportsAux = new JPanel();
        private JLabel reportsLabel = new JLabel();
        private JPanel reportsPanel = new JPanel();
        private JPanel rolesAux = new JPanel();
        private JLabel rolesLabel = new JLabel();
        private JPanel rolesPanel = new JPanel();
        private JPanel salesContentPanel = new JPanel();
        private JLabel salesLabel = new JLabel();
        private javax.swing.JToggleButton salesTuggleButton = new JToggleButton();
        private JPanel shiftsAux = new JPanel();
        private JLabel shiftsLabel = new JLabel();
        private JPanel shiftsPanel = new JPanel();

        public dashboard() {
                initComponents();
        }

        private void initComp2() {
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

                date.setForeground(new java.awt.Color(255, 255, 255));
                date.setText("13 Feb 2023");

                javax.swing.GroupLayout leftAuxPanelLayout = new javax.swing.GroupLayout(leftAuxPanel);
                leftAuxPanel.setLayout(leftAuxPanelLayout);
                leftAuxPanelLayout.setHorizontalGroup(
                                leftAuxPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                .addComponent(jPanel4, javax.swing.GroupLayout.DEFAULT_SIZE,
                                                                javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                                .addGroup(leftAuxPanelLayout.createSequentialGroup()
                                                                .addGap(80, 80, 80)
                                                                .addComponent(date)
                                                                .addContainerGap(86, Short.MAX_VALUE)));
                leftAuxPanelLayout.setVerticalGroup(
                                leftAuxPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                .addGroup(leftAuxPanelLayout.createSequentialGroup()
                                                                .addComponent(jPanel4,
                                                                                javax.swing.GroupLayout.PREFERRED_SIZE,
                                                                                javax.swing.GroupLayout.DEFAULT_SIZE,
                                                                                javax.swing.GroupLayout.PREFERRED_SIZE)
                                                                .addPreferredGap(
                                                                                javax.swing.LayoutStyle.ComponentPlacement.RELATED,
                                                                                541, Short.MAX_VALUE)
                                                                .addComponent(date)
                                                                .addContainerGap()));

                getContentPane().add(leftAuxPanel,
                                new org.netbeans.lib.awtextra.AbsoluteConstraints(170, 90, 230, 630));
        }

        private void initComp1() {
                clickRole1.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
                clickRole1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
                clickRole1.setText("New name :");
                clickRole1.setVerticalAlignment(javax.swing.SwingConstants.BOTTOM);

                setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
                setMaximumSize(new java.awt.Dimension(1284, 756));
                setPreferredSize(new java.awt.Dimension(1284, 756));
                setResizable(false);
                setSize(new java.awt.Dimension(1284, 756));
                getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

                staticButtonsPanel.setBackground(new java.awt.Color(23, 35, 51));

                homePanel.setBackground(new java.awt.Color(41, 57, 80));

                homeAux.setPreferredSize(new java.awt.Dimension(5, 43));

                javax.swing.GroupLayout homeAuxLayout = new javax.swing.GroupLayout(homeAux);
                homeAux.setLayout(homeAuxLayout);
                homeAuxLayout.setHorizontalGroup(
                                homeAuxLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                .addGap(0, 5, Short.MAX_VALUE));
                homeAuxLayout.setVerticalGroup(
                                homeAuxLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                .addGap(0, 0, Short.MAX_VALUE));

                homeLabel.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
                homeLabel.setForeground(new java.awt.Color(255, 255, 255));
                homeLabel.setText("Home");

                javax.swing.GroupLayout homePanelLayout = new javax.swing.GroupLayout(homePanel);
                homePanel.setLayout(homePanelLayout);
                homePanelLayout.setHorizontalGroup(
                                homePanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                .addGroup(homePanelLayout.createSequentialGroup()
                                                                .addComponent(homeAux,
                                                                                javax.swing.GroupLayout.PREFERRED_SIZE,
                                                                                javax.swing.GroupLayout.DEFAULT_SIZE,
                                                                                javax.swing.GroupLayout.PREFERRED_SIZE)
                                                                .addPreferredGap(
                                                                                javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                                                .addComponent(homeLabel,
                                                                                javax.swing.GroupLayout.PREFERRED_SIZE,
                                                                                79,
                                                                                javax.swing.GroupLayout.PREFERRED_SIZE)
                                                                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE,
                                                                                Short.MAX_VALUE)));
                homePanelLayout.setVerticalGroup(
                                homePanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                .addGroup(homePanelLayout.createSequentialGroup()
                                                                .addGroup(homePanelLayout.createParallelGroup(
                                                                                javax.swing.GroupLayout.Alignment.LEADING,
                                                                                false)
                                                                                .addComponent(homeAux,
                                                                                                javax.swing.GroupLayout.DEFAULT_SIZE,
                                                                                                javax.swing.GroupLayout.DEFAULT_SIZE,
                                                                                                Short.MAX_VALUE)
                                                                                .addComponent(homeLabel,
                                                                                                javax.swing.GroupLayout.DEFAULT_SIZE,
                                                                                                43, Short.MAX_VALUE))
                                                                .addGap(0, 0, Short.MAX_VALUE)));

                administrationPanel.setBackground(new java.awt.Color(23, 35, 51));

                administrationAux.setPreferredSize(new java.awt.Dimension(5, 43));

                javax.swing.GroupLayout administrationAuxLayout = new javax.swing.GroupLayout(administrationAux);
                administrationAux.setLayout(administrationAuxLayout);
                administrationAuxLayout.setHorizontalGroup(
                                administrationAuxLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                .addGap(0, 5, Short.MAX_VALUE));
                administrationAuxLayout.setVerticalGroup(
                                administrationAuxLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                .addGap(0, 0, Short.MAX_VALUE));

                administrationLabel.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
                administrationLabel.setForeground(new java.awt.Color(255, 255, 255));
                administrationLabel.setText("Administration");

                javax.swing.GroupLayout administrationPanelLayout = new javax.swing.GroupLayout(administrationPanel);
                administrationPanel.setLayout(administrationPanelLayout);
                administrationPanelLayout.setHorizontalGroup(
                                administrationPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                .addGroup(administrationPanelLayout.createSequentialGroup()
                                                                .addComponent(administrationAux,
                                                                                javax.swing.GroupLayout.PREFERRED_SIZE,
                                                                                javax.swing.GroupLayout.DEFAULT_SIZE,
                                                                                javax.swing.GroupLayout.PREFERRED_SIZE)
                                                                .addPreferredGap(
                                                                                javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                                                .addComponent(administrationLabel)
                                                                .addContainerGap(63, Short.MAX_VALUE)));
                administrationPanelLayout.setVerticalGroup(
                                administrationPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                .addGroup(administrationPanelLayout.createSequentialGroup()
                                                                .addGroup(administrationPanelLayout.createParallelGroup(
                                                                                javax.swing.GroupLayout.Alignment.LEADING,
                                                                                false)
                                                                                .addComponent(administrationAux,
                                                                                                javax.swing.GroupLayout.DEFAULT_SIZE,
                                                                                                javax.swing.GroupLayout.DEFAULT_SIZE,
                                                                                                Short.MAX_VALUE)
                                                                                .addComponent(administrationLabel,
                                                                                                javax.swing.GroupLayout.DEFAULT_SIZE,
                                                                                                43, Short.MAX_VALUE))
                                                                .addGap(0, 0, Short.MAX_VALUE)));

                foodPanel.setBackground(new java.awt.Color(23, 35, 51));

                foodAux.setPreferredSize(new java.awt.Dimension(5, 43));

                javax.swing.GroupLayout foodAuxLayout = new javax.swing.GroupLayout(foodAux);
                foodAux.setLayout(foodAuxLayout);
                foodAuxLayout.setHorizontalGroup(
                                foodAuxLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                .addGap(0, 5, Short.MAX_VALUE));
                foodAuxLayout.setVerticalGroup(
                                foodAuxLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                .addGap(0, 0, Short.MAX_VALUE));

                foodLabel.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
                foodLabel.setForeground(new java.awt.Color(255, 255, 255));
                foodLabel.setText("Food");

                javax.swing.GroupLayout foodPanelLayout = new javax.swing.GroupLayout(foodPanel);
                foodPanel.setLayout(foodPanelLayout);
                foodPanelLayout.setHorizontalGroup(
                                foodPanelLayout
                                                .createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                .addGroup(foodPanelLayout.createSequentialGroup()
                                                                .addComponent(foodAux,
                                                                                javax.swing.GroupLayout.PREFERRED_SIZE,
                                                                                javax.swing.GroupLayout.DEFAULT_SIZE,
                                                                                javax.swing.GroupLayout.PREFERRED_SIZE)
                                                                .addPreferredGap(
                                                                                javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                                                .addComponent(foodLabel)
                                                                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE,
                                                                                Short.MAX_VALUE)));
                foodPanelLayout.setVerticalGroup(
                                foodPanelLayout
                                                .createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                .addGroup(foodPanelLayout.createSequentialGroup()
                                                                .addGroup(foodPanelLayout
                                                                                .createParallelGroup(
                                                                                                javax.swing.GroupLayout.Alignment.LEADING,
                                                                                                false)
                                                                                .addComponent(foodAux,
                                                                                                javax.swing.GroupLayout.DEFAULT_SIZE,
                                                                                                javax.swing.GroupLayout.DEFAULT_SIZE,
                                                                                                Short.MAX_VALUE)
                                                                                .addComponent(foodLabel,
                                                                                                javax.swing.GroupLayout.DEFAULT_SIZE,
                                                                                                43, Short.MAX_VALUE))
                                                                .addGap(0, 0, Short.MAX_VALUE)));

                javax.swing.GroupLayout staticButtonsPanelLayout = new javax.swing.GroupLayout(staticButtonsPanel);
                staticButtonsPanel.setLayout(staticButtonsPanelLayout);
                staticButtonsPanelLayout.setHorizontalGroup(
                                staticButtonsPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                .addComponent(homePanel, javax.swing.GroupLayout.DEFAULT_SIZE,
                                                                javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                                .addComponent(administrationPanel, javax.swing.GroupLayout.DEFAULT_SIZE,
                                                                javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                                .addComponent(foodPanel,
                                                                javax.swing.GroupLayout.DEFAULT_SIZE,
                                                                javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE));
                staticButtonsPanelLayout.setVerticalGroup(
                                staticButtonsPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                .addGroup(staticButtonsPanelLayout.createSequentialGroup()
                                                                .addGap(155, 155, 155)
                                                                .addComponent(homePanel,
                                                                                javax.swing.GroupLayout.PREFERRED_SIZE,
                                                                                javax.swing.GroupLayout.DEFAULT_SIZE,
                                                                                javax.swing.GroupLayout.PREFERRED_SIZE)
                                                                .addPreferredGap(
                                                                                javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                                                .addComponent(administrationPanel,
                                                                                javax.swing.GroupLayout.PREFERRED_SIZE,
                                                                                javax.swing.GroupLayout.DEFAULT_SIZE,
                                                                                javax.swing.GroupLayout.PREFERRED_SIZE)
                                                                .addPreferredGap(
                                                                                javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                                                .addComponent(foodPanel,
                                                                                javax.swing.GroupLayout.PREFERRED_SIZE,
                                                                                javax.swing.GroupLayout.DEFAULT_SIZE,
                                                                                javax.swing.GroupLayout.PREFERRED_SIZE)
                                                                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE,
                                                                                Short.MAX_VALUE)));

                getContentPane().add(staticButtonsPanel,
                                new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 170, 720));
        }

        private void initCompTop() {
                overheadPanel.setBackground(new java.awt.Color(71, 120, 197));

                overheadLogoPanel.setBackground(new java.awt.Color(0, 0, 0));
                overheadLogoPanel.setOpaque(false);

                javax.swing.GroupLayout overheadLogoPanelLayout = new javax.swing.GroupLayout(overheadLogoPanel);
                overheadLogoPanel.setLayout(overheadLogoPanelLayout);
                overheadLogoPanelLayout.setHorizontalGroup(
                                overheadLogoPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                .addGap(0, 90, Short.MAX_VALUE));
                overheadLogoPanelLayout.setVerticalGroup(
                                overheadLogoPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                .addGap(0, 0, Short.MAX_VALUE));

                welcomeLabel.setFont(new java.awt.Font("Segoe UI", 1, 24)); // NOI18N
                welcomeLabel.setForeground(new java.awt.Color(255, 255, 255));
                welcomeLabel.setText("Welcome, [name]");

                javax.swing.GroupLayout overheadPanelLayout = new javax.swing.GroupLayout(overheadPanel);
                overheadPanel.setLayout(overheadPanelLayout);
                overheadPanelLayout.setHorizontalGroup(
                                overheadPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                .addGroup(javax.swing.GroupLayout.Alignment.TRAILING,
                                                                overheadPanelLayout.createSequentialGroup()
                                                                                .addGap(14, 14, 14)
                                                                                .addComponent(welcomeLabel,
                                                                                                javax.swing.GroupLayout.PREFERRED_SIZE,
                                                                                                343,
                                                                                                javax.swing.GroupLayout.PREFERRED_SIZE)
                                                                                .addPreferredGap(
                                                                                                javax.swing.LayoutStyle.ComponentPlacement.RELATED,
                                                                                                653, Short.MAX_VALUE)
                                                                                .addComponent(overheadLogoPanel,
                                                                                                javax.swing.GroupLayout.PREFERRED_SIZE,
                                                                                                javax.swing.GroupLayout.DEFAULT_SIZE,
                                                                                                javax.swing.GroupLayout.PREFERRED_SIZE)));
                overheadPanelLayout.setVerticalGroup(
                                overheadPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                .addGroup(overheadPanelLayout.createSequentialGroup()
                                                                .addContainerGap()
                                                                .addComponent(welcomeLabel,
                                                                                javax.swing.GroupLayout.DEFAULT_SIZE,
                                                                                78, Short.MAX_VALUE)
                                                                .addContainerGap())
                                                .addComponent(overheadLogoPanel, javax.swing.GroupLayout.DEFAULT_SIZE,
                                                                javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE));

                getContentPane().add(overheadPanel,
                                new org.netbeans.lib.awtextra.AbsoluteConstraints(170, 0, 1100, 90));
        }

        private void initComp3() {
                setTables();
                playground.setBackground(new java.awt.Color(255, 255, 255));

                salesPanel.setBackground(new java.awt.Color(255, 255, 255));
                salesPanel.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(204, 204, 204), 3, true));

                headSalesPanel.setBackground(new java.awt.Color(204, 204, 204));

                salesLabel.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
                salesLabel.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
                salesLabel.setText("SALES OVERVIEW");

                salesTuggleButton.setText("Last 14 days");

                javax.swing.GroupLayout headSalesPanelLayout = new javax.swing.GroupLayout(headSalesPanel);
                headSalesPanel.setLayout(headSalesPanelLayout);
                headSalesPanelLayout.setHorizontalGroup(
                                headSalesPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                .addGroup(headSalesPanelLayout.createSequentialGroup()
                                                                .addContainerGap()
                                                                .addComponent(salesLabel,
                                                                                javax.swing.GroupLayout.PREFERRED_SIZE,
                                                                                434,
                                                                                javax.swing.GroupLayout.PREFERRED_SIZE)
                                                                .addGap(18, 18, 18)
                                                                .addComponent(salesTuggleButton,
                                                                                javax.swing.GroupLayout.DEFAULT_SIZE,
                                                                                124, Short.MAX_VALUE)
                                                                .addGap(10, 10, 10)));
                headSalesPanelLayout.setVerticalGroup(
                                headSalesPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                .addComponent(salesLabel, javax.swing.GroupLayout.DEFAULT_SIZE, 31,
                                                                Short.MAX_VALUE)
                                                .addComponent(salesTuggleButton, javax.swing.GroupLayout.DEFAULT_SIZE,
                                                                javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE));

                javax.swing.GroupLayout salesContentPanelLayout = new javax.swing.GroupLayout(salesContentPanel);
                salesContentPanel.setLayout(salesContentPanelLayout);
                salesContentPanelLayout.setHorizontalGroup(
                                salesContentPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                .addGap(0, 0, Short.MAX_VALUE));
                salesContentPanelLayout.setVerticalGroup(
                                salesContentPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                .addGap(0, 234, Short.MAX_VALUE));

                javax.swing.GroupLayout salesPanelLayout = new javax.swing.GroupLayout(salesPanel);
                salesPanel.setLayout(salesPanelLayout);
                salesPanelLayout.setHorizontalGroup(
                                salesPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                .addComponent(headSalesPanel, javax.swing.GroupLayout.DEFAULT_SIZE,
                                                                javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                                .addGroup(salesPanelLayout.createSequentialGroup()
                                                                .addContainerGap()
                                                                .addComponent(salesContentPanel,
                                                                                javax.swing.GroupLayout.DEFAULT_SIZE,
                                                                                javax.swing.GroupLayout.DEFAULT_SIZE,
                                                                                Short.MAX_VALUE)
                                                                .addContainerGap()));
                salesPanelLayout.setVerticalGroup(
                                salesPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                .addGroup(salesPanelLayout.createSequentialGroup()
                                                                .addComponent(headSalesPanel,
                                                                                javax.swing.GroupLayout.PREFERRED_SIZE,
                                                                                javax.swing.GroupLayout.DEFAULT_SIZE,
                                                                                javax.swing.GroupLayout.PREFERRED_SIZE)
                                                                .addPreferredGap(
                                                                                javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                                                .addComponent(salesContentPanel,
                                                                                javax.swing.GroupLayout.DEFAULT_SIZE,
                                                                                javax.swing.GroupLayout.DEFAULT_SIZE,
                                                                                Short.MAX_VALUE)
                                                                .addContainerGap()));

                ordersPanel.setBackground(new java.awt.Color(255, 255, 255));
                ordersPanel.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(204, 204, 204), 3, true));
                ordersPanel.setMaximumSize(new java.awt.Dimension(270, 270));
                ordersPanel.setMinimumSize(new java.awt.Dimension(270, 270));
                ordersPanel.setPreferredSize(new java.awt.Dimension(270, 270));

                headOrdersPanel.setBackground(new java.awt.Color(204, 204, 204));
                headOrdersPanel.setPreferredSize(new java.awt.Dimension(295, 37));

                ordersLabel.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
                ordersLabel.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
                ordersLabel.setText("LAST 10 ORDERS");

                javax.swing.GroupLayout headOrdersPanelLayout = new javax.swing.GroupLayout(headOrdersPanel);
                headOrdersPanel.setLayout(headOrdersPanelLayout);
                headOrdersPanelLayout.setHorizontalGroup(
                                headOrdersPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                .addGroup(headOrdersPanelLayout.createSequentialGroup()
                                                                .addContainerGap()
                                                                .addComponent(ordersLabel,
                                                                                javax.swing.GroupLayout.DEFAULT_SIZE,
                                                                                javax.swing.GroupLayout.DEFAULT_SIZE,
                                                                                Short.MAX_VALUE)
                                                                .addContainerGap()));
                headOrdersPanelLayout.setVerticalGroup(
                                headOrdersPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                .addComponent(ordersLabel, javax.swing.GroupLayout.DEFAULT_SIZE, 37,
                                                                Short.MAX_VALUE));

                ordersPane.setMaximumSize(new java.awt.Dimension(252, 215));
                ordersPane.setMinimumSize(new java.awt.Dimension(252, 215));
                ordersPane.setPreferredSize(new java.awt.Dimension(252, 215));

                javax.swing.GroupLayout ordersPanelLayout = new javax.swing.GroupLayout(ordersPanel);
                ordersPanel.setLayout(ordersPanelLayout);
                ordersPanelLayout.setHorizontalGroup(
                                ordersPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                .addComponent(headOrdersPanel, javax.swing.GroupLayout.DEFAULT_SIZE,
                                                                276, Short.MAX_VALUE)
                                                .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, ordersPanelLayout
                                                                .createSequentialGroup()
                                                                .addComponent(ordersPane,
                                                                                javax.swing.GroupLayout.DEFAULT_SIZE,
                                                                                276, Short.MAX_VALUE)
                                                                .addGap(0, 0, 0)));
                ordersPanelLayout.setVerticalGroup(
                                ordersPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                .addGroup(ordersPanelLayout.createSequentialGroup()
                                                                .addComponent(headOrdersPanel,
                                                                                javax.swing.GroupLayout.PREFERRED_SIZE,
                                                                                javax.swing.GroupLayout.DEFAULT_SIZE,
                                                                                javax.swing.GroupLayout.PREFERRED_SIZE)
                                                                .addGap(0, 0, 0)
                                                                .addComponent(ordersPane,
                                                                                javax.swing.GroupLayout.DEFAULT_SIZE,
                                                                                javax.swing.GroupLayout.DEFAULT_SIZE,
                                                                                Short.MAX_VALUE)));

                bsTodayPanel.setBackground(new java.awt.Color(255, 255, 255));
                bsTodayPanel.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(204, 204, 204), 3, true));
                bsTodayPanel.setPreferredSize(new java.awt.Dimension(295, 282));

                bsTodayPanelBackground.setBackground(new java.awt.Color(204, 204, 204));

                bsTodayProductLabel.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
                bsTodayProductLabel.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
                bsTodayProductLabel.setText(new dashboardsAPI().getMostSoldProductToday().getName());

                bsTodayTitlePanel.setFont(new java.awt.Font("Segoe UI", 1, 17)); // NOI18N
                bsTodayTitlePanel.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
                bsTodayTitlePanel.setText("BEST SELLER TODAY");

                javax.swing.GroupLayout bsTodayPanelBackgroundLayout = new javax.swing.GroupLayout(
                                bsTodayPanelBackground);
                bsTodayPanelBackground.setLayout(bsTodayPanelBackgroundLayout);
                bsTodayPanelBackgroundLayout.setHorizontalGroup(
                                bsTodayPanelBackgroundLayout
                                                .createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                .addGroup(bsTodayPanelBackgroundLayout.createSequentialGroup()
                                                                .addGroup(bsTodayPanelBackgroundLayout
                                                                                .createParallelGroup(
                                                                                                javax.swing.GroupLayout.Alignment.TRAILING,
                                                                                                false)
                                                                                .addGroup(bsTodayPanelBackgroundLayout
                                                                                                .createSequentialGroup()
                                                                                                .addContainerGap()
                                                                                                .addComponent(bsTodayProductLabel,
                                                                                                                javax.swing.GroupLayout.DEFAULT_SIZE,
                                                                                                                javax.swing.GroupLayout.DEFAULT_SIZE,
                                                                                                                Short.MAX_VALUE))
                                                                                .addComponent(bsTodayTitlePanel,
                                                                                                javax.swing.GroupLayout.PREFERRED_SIZE,
                                                                                                201,
                                                                                                javax.swing.GroupLayout.PREFERRED_SIZE))
                                                                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE,
                                                                                Short.MAX_VALUE)));
                bsTodayPanelBackgroundLayout.setVerticalGroup(
                                bsTodayPanelBackgroundLayout
                                                .createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                .addGroup(bsTodayPanelBackgroundLayout.createSequentialGroup()
                                                                .addComponent(bsTodayTitlePanel)
                                                                .addPreferredGap(
                                                                                javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                                                .addComponent(bsTodayProductLabel,
                                                                                javax.swing.GroupLayout.PREFERRED_SIZE,
                                                                                64,
                                                                                javax.swing.GroupLayout.PREFERRED_SIZE)
                                                                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE,
                                                                                Short.MAX_VALUE)));

                javax.swing.GroupLayout bsTodayPanelLayout = new javax.swing.GroupLayout(bsTodayPanel);
                bsTodayPanel.setLayout(bsTodayPanelLayout);
                bsTodayPanelLayout.setHorizontalGroup(
                                bsTodayPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                .addComponent(bsTodayPanelBackground,
                                                                javax.swing.GroupLayout.PREFERRED_SIZE, 206,
                                                                Short.MAX_VALUE));
                bsTodayPanelLayout.setVerticalGroup(
                                bsTodayPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                .addComponent(bsTodayPanelBackground,
                                                                javax.swing.GroupLayout.DEFAULT_SIZE,
                                                                javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE));

                employeesPanel.setBackground(new java.awt.Color(255, 255, 255));
                employeesPanel.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(204, 204, 204), 3, true));
                employeesPanel.setMaximumSize(new java.awt.Dimension(270, 270));
                employeesPanel.setMinimumSize(new java.awt.Dimension(270, 270));

                headEmployeesPanel.setBackground(new java.awt.Color(204, 204, 204));
                headEmployeesPanel.setPreferredSize(new java.awt.Dimension(295, 37));

                employeesLabel.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
                employeesLabel.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
                employeesLabel.setText("WORKING EMPLOYEES");

                javax.swing.GroupLayout headEmployeesPanelLayout = new javax.swing.GroupLayout(headEmployeesPanel);
                headEmployeesPanel.setLayout(headEmployeesPanelLayout);
                headEmployeesPanelLayout.setHorizontalGroup(
                                headEmployeesPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                .addGroup(headEmployeesPanelLayout.createSequentialGroup()
                                                                .addContainerGap()
                                                                .addComponent(employeesLabel,
                                                                                javax.swing.GroupLayout.DEFAULT_SIZE,
                                                                                javax.swing.GroupLayout.DEFAULT_SIZE,
                                                                                Short.MAX_VALUE)
                                                                .addContainerGap()));
                headEmployeesPanelLayout.setVerticalGroup(
                                headEmployeesPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                .addComponent(employeesLabel, javax.swing.GroupLayout.DEFAULT_SIZE, 37,
                                                                Short.MAX_VALUE));

                employeesPane.setMaximumSize(new java.awt.Dimension(252, 215));
                employeesPane.setMinimumSize(new java.awt.Dimension(252, 215));
                employeesPane.setPreferredSize(new java.awt.Dimension(252, 215));

                javax.swing.GroupLayout employeesPanelLayout = new javax.swing.GroupLayout(employeesPanel);
                employeesPanel.setLayout(employeesPanelLayout);
                employeesPanelLayout.setHorizontalGroup(
                                employeesPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                .addComponent(headEmployeesPanel, javax.swing.GroupLayout.DEFAULT_SIZE,
                                                                282, Short.MAX_VALUE)
                                                .addComponent(employeesPane, javax.swing.GroupLayout.DEFAULT_SIZE,
                                                                javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE));
                employeesPanelLayout.setVerticalGroup(
                                employeesPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                .addGroup(employeesPanelLayout.createSequentialGroup()
                                                                .addComponent(headEmployeesPanel,
                                                                                javax.swing.GroupLayout.PREFERRED_SIZE,
                                                                                javax.swing.GroupLayout.DEFAULT_SIZE,
                                                                                javax.swing.GroupLayout.PREFERRED_SIZE)
                                                                .addGap(0, 0, 0)
                                                                .addComponent(employeesPane,
                                                                                javax.swing.GroupLayout.DEFAULT_SIZE,
                                                                                javax.swing.GroupLayout.DEFAULT_SIZE,
                                                                                Short.MAX_VALUE)
                                                                .addGap(0, 0, 0)));

                bsWeekPanel.setBackground(new java.awt.Color(255, 255, 255));
                bsWeekPanel.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(204, 204, 204), 3, true));
                bsWeekPanel.setPreferredSize(new java.awt.Dimension(295, 282));

                bsWeekPanelBackground.setBackground(new java.awt.Color(204, 204, 204));

                bsWeekProductLabel.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
                bsWeekProductLabel.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
                bsWeekProductLabel.setText(new dashboardsAPI().getMostSoldProductWeek().getName());

                bsWeekTitlePanel.setFont(new java.awt.Font("Segoe UI", 1, 17)); // NOI18N
                bsWeekTitlePanel.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
                bsWeekTitlePanel.setText("BEST SELLER WEEK");

                javax.swing.GroupLayout bsWeekPanelBackgroundLayout = new javax.swing.GroupLayout(
                                bsWeekPanelBackground);
                bsWeekPanelBackground.setLayout(bsWeekPanelBackgroundLayout);
                bsWeekPanelBackgroundLayout.setHorizontalGroup(
                                bsWeekPanelBackgroundLayout
                                                .createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                .addGroup(bsWeekPanelBackgroundLayout.createSequentialGroup()
                                                                .addGroup(bsWeekPanelBackgroundLayout
                                                                                .createParallelGroup(
                                                                                                javax.swing.GroupLayout.Alignment.TRAILING,
                                                                                                false)
                                                                                .addGroup(bsWeekPanelBackgroundLayout
                                                                                                .createSequentialGroup()
                                                                                                .addContainerGap()
                                                                                                .addComponent(bsWeekProductLabel,
                                                                                                                javax.swing.GroupLayout.DEFAULT_SIZE,
                                                                                                                javax.swing.GroupLayout.DEFAULT_SIZE,
                                                                                                                Short.MAX_VALUE))
                                                                                .addComponent(bsWeekTitlePanel,
                                                                                                javax.swing.GroupLayout.PREFERRED_SIZE,
                                                                                                201,
                                                                                                javax.swing.GroupLayout.PREFERRED_SIZE))
                                                                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE,
                                                                                Short.MAX_VALUE)));
                bsWeekPanelBackgroundLayout.setVerticalGroup(
                                bsWeekPanelBackgroundLayout
                                                .createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                .addGroup(bsWeekPanelBackgroundLayout.createSequentialGroup()
                                                                .addComponent(bsWeekTitlePanel)
                                                                .addPreferredGap(
                                                                                javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                                                .addComponent(bsWeekProductLabel,
                                                                                javax.swing.GroupLayout.PREFERRED_SIZE,
                                                                                64,
                                                                                javax.swing.GroupLayout.PREFERRED_SIZE)
                                                                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE,
                                                                                Short.MAX_VALUE)));

                javax.swing.GroupLayout bsWeekPanelLayout = new javax.swing.GroupLayout(bsWeekPanel);
                bsWeekPanel.setLayout(bsWeekPanelLayout);
                bsWeekPanelLayout.setHorizontalGroup(
                                bsWeekPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                .addComponent(bsWeekPanelBackground,
                                                                javax.swing.GroupLayout.PREFERRED_SIZE, 206,
                                                                Short.MAX_VALUE));
                bsWeekPanelLayout.setVerticalGroup(
                                bsWeekPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                .addComponent(bsWeekPanelBackground,
                                                                javax.swing.GroupLayout.DEFAULT_SIZE,
                                                                javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE));

                bsMonthPanel.setBackground(new java.awt.Color(255, 255, 255));
                bsMonthPanel.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(204, 204, 204), 3, true));
                bsMonthPanel.setPreferredSize(new java.awt.Dimension(295, 282));

                bsMonthPanelBackground.setBackground(new java.awt.Color(204, 204, 204));

                bsMonthProductLabel.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
                bsMonthProductLabel.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
                bsMonthProductLabel.setText(new dashboardsAPI().getMostSoldProductMonth().getName());

                bsMonthTitlePanel.setFont(new java.awt.Font("Segoe UI", 1, 17)); // NOI18N
                bsMonthTitlePanel.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
                bsMonthTitlePanel.setText("BEST SELLER MONTH");

                javax.swing.GroupLayout bsMonthPanelBackgroundLayout = new javax.swing.GroupLayout(
                                bsMonthPanelBackground);
                bsMonthPanelBackground.setLayout(bsMonthPanelBackgroundLayout);
                bsMonthPanelBackgroundLayout.setHorizontalGroup(
                                bsMonthPanelBackgroundLayout
                                                .createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                .addGroup(bsMonthPanelBackgroundLayout.createSequentialGroup()
                                                                .addGroup(bsMonthPanelBackgroundLayout
                                                                                .createParallelGroup(
                                                                                                javax.swing.GroupLayout.Alignment.TRAILING,
                                                                                                false)
                                                                                .addGroup(bsMonthPanelBackgroundLayout
                                                                                                .createSequentialGroup()
                                                                                                .addContainerGap()
                                                                                                .addComponent(bsMonthProductLabel,
                                                                                                                javax.swing.GroupLayout.DEFAULT_SIZE,
                                                                                                                javax.swing.GroupLayout.DEFAULT_SIZE,
                                                                                                                Short.MAX_VALUE))
                                                                                .addComponent(bsMonthTitlePanel,
                                                                                                javax.swing.GroupLayout.PREFERRED_SIZE,
                                                                                                201,
                                                                                                javax.swing.GroupLayout.PREFERRED_SIZE))
                                                                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE,
                                                                                Short.MAX_VALUE)));
                bsMonthPanelBackgroundLayout.setVerticalGroup(
                                bsMonthPanelBackgroundLayout
                                                .createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                .addGroup(bsMonthPanelBackgroundLayout.createSequentialGroup()
                                                                .addComponent(bsMonthTitlePanel)
                                                                .addPreferredGap(
                                                                                javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                                                .addComponent(bsMonthProductLabel,
                                                                                javax.swing.GroupLayout.PREFERRED_SIZE,
                                                                                64,
                                                                                javax.swing.GroupLayout.PREFERRED_SIZE)
                                                                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE,
                                                                                Short.MAX_VALUE)));

                javax.swing.GroupLayout bsMonthPanelLayout = new javax.swing.GroupLayout(bsMonthPanel);
                bsMonthPanel.setLayout(bsMonthPanelLayout);
                bsMonthPanelLayout.setHorizontalGroup(
                                bsMonthPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                .addComponent(bsMonthPanelBackground,
                                                                javax.swing.GroupLayout.PREFERRED_SIZE, 206,
                                                                Short.MAX_VALUE));
                bsMonthPanelLayout.setVerticalGroup(
                                bsMonthPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                .addComponent(bsMonthPanelBackground,
                                                                javax.swing.GroupLayout.DEFAULT_SIZE,
                                                                javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE));

                pricePanel.setBackground(new java.awt.Color(255, 255, 255));
                pricePanel.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(204, 204, 204), 3, true));
                pricePanel.setPreferredSize(new java.awt.Dimension(295, 282));

                pricePanelBackground.setBackground(new java.awt.Color(204, 204, 204));

                pricePriceLabel.setFont(new java.awt.Font("Segoe UI", 0, 30)); // NOI18N
                pricePriceLabel.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
                pricePriceLabel.setText(String.format("%.2f", new dashboardsAPI().getAvgSalePriceToday()).concat(" €"));

                priceIconLabel.setIcon(new javax.swing.ImageIcon(
                                "C:\\Users\\Juan Diego\\Desktop\\netbeansss\\guiProjectTEST\\src\\money-bag.png")); // NOI18N

                priceTitleLabel.setFont(new java.awt.Font("Segoe UI", 1, 17)); // NOI18N
                priceTitleLabel.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
                priceTitleLabel.setText("AVG. ORDER TODAY");

                javax.swing.GroupLayout pricePanelBackgroundLayout = new javax.swing.GroupLayout(pricePanelBackground);
                pricePanelBackground.setLayout(pricePanelBackgroundLayout);
                pricePanelBackgroundLayout.setHorizontalGroup(
                                pricePanelBackgroundLayout
                                                .createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                .addGroup(pricePanelBackgroundLayout.createSequentialGroup()
                                                                .addContainerGap()
                                                                .addGroup(pricePanelBackgroundLayout
                                                                                .createParallelGroup(
                                                                                                javax.swing.GroupLayout.Alignment.LEADING)
                                                                                .addGroup(pricePanelBackgroundLayout
                                                                                                .createSequentialGroup()
                                                                                                .addGap(6, 6, 6)
                                                                                                .addComponent(pricePriceLabel,
                                                                                                                javax.swing.GroupLayout.DEFAULT_SIZE,
                                                                                                                105,
                                                                                                                Short.MAX_VALUE)
                                                                                                .addPreferredGap(
                                                                                                                javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                                                                                .addComponent(priceIconLabel))
                                                                                .addComponent(priceTitleLabel,
                                                                                                javax.swing.GroupLayout.DEFAULT_SIZE,
                                                                                                javax.swing.GroupLayout.DEFAULT_SIZE,
                                                                                                Short.MAX_VALUE))
                                                                .addContainerGap()));
                pricePanelBackgroundLayout.setVerticalGroup(
                                pricePanelBackgroundLayout
                                                .createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                .addGroup(pricePanelBackgroundLayout.createSequentialGroup()
                                                                .addComponent(priceTitleLabel)
                                                                .addPreferredGap(
                                                                                javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                                                .addGroup(pricePanelBackgroundLayout
                                                                                .createParallelGroup(
                                                                                                javax.swing.GroupLayout.Alignment.LEADING,
                                                                                                false)
                                                                                .addComponent(priceIconLabel,
                                                                                                javax.swing.GroupLayout.DEFAULT_SIZE,
                                                                                                javax.swing.GroupLayout.DEFAULT_SIZE,
                                                                                                Short.MAX_VALUE)
                                                                                .addComponent(pricePriceLabel,
                                                                                                javax.swing.GroupLayout.DEFAULT_SIZE,
                                                                                                javax.swing.GroupLayout.DEFAULT_SIZE,
                                                                                                Short.MAX_VALUE))
                                                                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE,
                                                                                Short.MAX_VALUE)));

                javax.swing.GroupLayout pricePanelLayout = new javax.swing.GroupLayout(pricePanel);
                pricePanel.setLayout(pricePanelLayout);
                pricePanelLayout.setHorizontalGroup(
                                pricePanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                .addComponent(pricePanelBackground,
                                                                javax.swing.GroupLayout.DEFAULT_SIZE,
                                                                javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE));
                pricePanelLayout.setVerticalGroup(
                                pricePanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                .addComponent(pricePanelBackground,
                                                                javax.swing.GroupLayout.DEFAULT_SIZE,
                                                                javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE));

                timePanel.setBackground(new java.awt.Color(255, 255, 255));
                timePanel.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(204, 204, 204), 3, true));
                timePanel.setPreferredSize(new java.awt.Dimension(295, 282));

                timePanelBackground.setBackground(new java.awt.Color(204, 204, 204));

                timeTimeLabel.setFont(new java.awt.Font("Segoe UI", 0, 30)); // NOI18N
                timeTimeLabel.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
                timeTimeLabel.setText(Integer.toString(new dashboardsAPI().getAvgOrderTimeToday()).concat(" min."));

                timeIconLabel.setIcon(new javax.swing.ImageIcon(
                                "C:\\Users\\Juan Diego\\Desktop\\netbeansss\\guiProjectTEST\\src\\stopwatch.png")); // NOI18N

                timeTitleLabel.setFont(new java.awt.Font("Segoe UI", 1, 17)); // NOI18N
                timeTitleLabel.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
                timeTitleLabel.setText("AVG. ORDER TIME");

                javax.swing.GroupLayout timePanelBackgroundLayout = new javax.swing.GroupLayout(timePanelBackground);
                timePanelBackground.setLayout(timePanelBackgroundLayout);
                timePanelBackgroundLayout.setHorizontalGroup(
                                timePanelBackgroundLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                .addGroup(timePanelBackgroundLayout.createSequentialGroup()
                                                                .addContainerGap()
                                                                .addGroup(timePanelBackgroundLayout.createParallelGroup(
                                                                                javax.swing.GroupLayout.Alignment.LEADING)
                                                                                .addGroup(timePanelBackgroundLayout
                                                                                                .createSequentialGroup()
                                                                                                .addGap(6, 6, 6)
                                                                                                .addComponent(timeTimeLabel,
                                                                                                                javax.swing.GroupLayout.DEFAULT_SIZE,
                                                                                                                105,
                                                                                                                Short.MAX_VALUE)
                                                                                                .addPreferredGap(
                                                                                                                javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                                                                                .addComponent(timeIconLabel))
                                                                                .addComponent(timeTitleLabel,
                                                                                                javax.swing.GroupLayout.DEFAULT_SIZE,
                                                                                                javax.swing.GroupLayout.DEFAULT_SIZE,
                                                                                                Short.MAX_VALUE))
                                                                .addContainerGap()));
                timePanelBackgroundLayout.setVerticalGroup(
                                timePanelBackgroundLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                .addGroup(timePanelBackgroundLayout.createSequentialGroup()
                                                                .addComponent(timeTitleLabel)
                                                                .addPreferredGap(
                                                                                javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                                                .addGroup(timePanelBackgroundLayout.createParallelGroup(
                                                                                javax.swing.GroupLayout.Alignment.LEADING,
                                                                                false)
                                                                                .addComponent(timeIconLabel,
                                                                                                javax.swing.GroupLayout.DEFAULT_SIZE,
                                                                                                javax.swing.GroupLayout.DEFAULT_SIZE,
                                                                                                Short.MAX_VALUE)
                                                                                .addComponent(timeTimeLabel,
                                                                                                javax.swing.GroupLayout.DEFAULT_SIZE,
                                                                                                javax.swing.GroupLayout.DEFAULT_SIZE,
                                                                                                Short.MAX_VALUE))
                                                                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE,
                                                                                Short.MAX_VALUE)));

                javax.swing.GroupLayout timePanelLayout = new javax.swing.GroupLayout(timePanel);
                timePanel.setLayout(timePanelLayout);
                timePanelLayout.setHorizontalGroup(
                                timePanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                .addComponent(timePanelBackground, javax.swing.GroupLayout.DEFAULT_SIZE,
                                                                javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE));
                timePanelLayout.setVerticalGroup(
                                timePanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                .addComponent(timePanelBackground, javax.swing.GroupLayout.DEFAULT_SIZE,
                                                                javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE));

                javax.swing.GroupLayout playgroundLayout = new javax.swing.GroupLayout(playground);
                playground.setLayout(playgroundLayout);
                playgroundLayout.setHorizontalGroup(
                                playgroundLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                .addGroup(playgroundLayout.createSequentialGroup()
                                                                .addContainerGap()
                                                                .addGroup(playgroundLayout.createParallelGroup(
                                                                                javax.swing.GroupLayout.Alignment.LEADING,
                                                                                false)
                                                                                .addGroup(playgroundLayout
                                                                                                .createSequentialGroup()
                                                                                                .addComponent(employeesPanel,
                                                                                                                javax.swing.GroupLayout.DEFAULT_SIZE,
                                                                                                                javax.swing.GroupLayout.DEFAULT_SIZE,
                                                                                                                Short.MAX_VALUE)
                                                                                                .addGap(34, 34, 34)
                                                                                                .addComponent(ordersPanel,
                                                                                                                javax.swing.GroupLayout.PREFERRED_SIZE,
                                                                                                                282,
                                                                                                                javax.swing.GroupLayout.PREFERRED_SIZE))
                                                                                .addComponent(salesPanel,
                                                                                                javax.swing.GroupLayout.PREFERRED_SIZE,
                                                                                                javax.swing.GroupLayout.DEFAULT_SIZE,
                                                                                                javax.swing.GroupLayout.PREFERRED_SIZE))
                                                                .addGap(18, 18, Short.MAX_VALUE)
                                                                .addGroup(playgroundLayout.createParallelGroup(
                                                                                javax.swing.GroupLayout.Alignment.LEADING)
                                                                                .addGroup(playgroundLayout
                                                                                                .createSequentialGroup()
                                                                                                .addPreferredGap(
                                                                                                                javax.swing.LayoutStyle.ComponentPlacement.RELATED,
                                                                                                                3,
                                                                                                                Short.MAX_VALUE)
                                                                                                .addComponent(bsTodayPanel,
                                                                                                                javax.swing.GroupLayout.PREFERRED_SIZE,
                                                                                                                212,
                                                                                                                javax.swing.GroupLayout.PREFERRED_SIZE)
                                                                                                .addGap(21, 21, 21))
                                                                                .addGroup(playgroundLayout
                                                                                                .createSequentialGroup()
                                                                                                .addGroup(playgroundLayout
                                                                                                                .createParallelGroup(
                                                                                                                                javax.swing.GroupLayout.Alignment.LEADING)
                                                                                                                .addComponent(bsMonthPanel,
                                                                                                                                javax.swing.GroupLayout.PREFERRED_SIZE,
                                                                                                                                212,
                                                                                                                                javax.swing.GroupLayout.PREFERRED_SIZE)
                                                                                                                .addComponent(bsWeekPanel,
                                                                                                                                javax.swing.GroupLayout.PREFERRED_SIZE,
                                                                                                                                212,
                                                                                                                                javax.swing.GroupLayout.PREFERRED_SIZE))
                                                                                                .addContainerGap(
                                                                                                                javax.swing.GroupLayout.DEFAULT_SIZE,
                                                                                                                Short.MAX_VALUE))
                                                                                .addGroup(playgroundLayout
                                                                                                .createSequentialGroup()
                                                                                                .addGroup(playgroundLayout
                                                                                                                .createParallelGroup(
                                                                                                                                javax.swing.GroupLayout.Alignment.LEADING)
                                                                                                                .addComponent(pricePanel,
                                                                                                                                javax.swing.GroupLayout.PREFERRED_SIZE,
                                                                                                                                212,
                                                                                                                                javax.swing.GroupLayout.PREFERRED_SIZE)
                                                                                                                .addComponent(timePanel,
                                                                                                                                javax.swing.GroupLayout.PREFERRED_SIZE,
                                                                                                                                212,
                                                                                                                                javax.swing.GroupLayout.PREFERRED_SIZE))
                                                                                                .addGap(0, 0, Short.MAX_VALUE)))));
                playgroundLayout.setVerticalGroup(
                                playgroundLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                .addGroup(playgroundLayout.createSequentialGroup()
                                                                .addContainerGap()
                                                                .addGroup(playgroundLayout.createParallelGroup(
                                                                                javax.swing.GroupLayout.Alignment.LEADING,
                                                                                false)
                                                                                .addGroup(playgroundLayout
                                                                                                .createSequentialGroup()
                                                                                                .addComponent(bsTodayPanel,
                                                                                                                javax.swing.GroupLayout.PREFERRED_SIZE,
                                                                                                                111,
                                                                                                                javax.swing.GroupLayout.PREFERRED_SIZE)
                                                                                                .addPreferredGap(
                                                                                                                javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                                                                                .addComponent(bsWeekPanel,
                                                                                                                javax.swing.GroupLayout.PREFERRED_SIZE,
                                                                                                                111,
                                                                                                                javax.swing.GroupLayout.PREFERRED_SIZE)
                                                                                                .addPreferredGap(
                                                                                                                javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                                                                                .addComponent(bsMonthPanel,
                                                                                                                javax.swing.GroupLayout.PREFERRED_SIZE,
                                                                                                                111,
                                                                                                                javax.swing.GroupLayout.PREFERRED_SIZE)
                                                                                                .addPreferredGap(
                                                                                                                javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                                                                                .addComponent(pricePanel,
                                                                                                                javax.swing.GroupLayout.PREFERRED_SIZE,
                                                                                                                111,
                                                                                                                javax.swing.GroupLayout.PREFERRED_SIZE)
                                                                                                .addPreferredGap(
                                                                                                                javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                                                                                .addComponent(timePanel,
                                                                                                                javax.swing.GroupLayout.PREFERRED_SIZE,
                                                                                                                111,
                                                                                                                javax.swing.GroupLayout.PREFERRED_SIZE))
                                                                                .addGroup(playgroundLayout
                                                                                                .createSequentialGroup()
                                                                                                .addComponent(salesPanel,
                                                                                                                javax.swing.GroupLayout.PREFERRED_SIZE,
                                                                                                                javax.swing.GroupLayout.DEFAULT_SIZE,
                                                                                                                javax.swing.GroupLayout.PREFERRED_SIZE)
                                                                                                .addGap(18, 18, 18)
                                                                                                .addGroup(playgroundLayout
                                                                                                                .createParallelGroup(
                                                                                                                                javax.swing.GroupLayout.Alignment.LEADING)
                                                                                                                .addComponent(ordersPanel,
                                                                                                                                javax.swing.GroupLayout.DEFAULT_SIZE,
                                                                                                                                298,
                                                                                                                                Short.MAX_VALUE)
                                                                                                                .addComponent(employeesPanel,
                                                                                                                                javax.swing.GroupLayout.DEFAULT_SIZE,
                                                                                                                                javax.swing.GroupLayout.DEFAULT_SIZE,
                                                                                                                                Short.MAX_VALUE))))
                                                                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE,
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
                                new mainAdmin(tempFrame, leftAuxPanel, playground, jPanel4, date);
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
                                new mainFood(leftAuxPanel, playground, jPanel4, date);
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

                shiftsAPI managerDB = new shiftsAPI();
                for (currentShiftEmployee temp : managerDB.getCurrentlyWorkingEmployees()) {
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
