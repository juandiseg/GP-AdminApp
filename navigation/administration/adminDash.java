package navigation.administration;

import java.awt.*;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.awt.event.*;

import javax.swing.*;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.border.LineBorder;

import org.jfree.chart.*;
import org.jfree.chart.axis.NumberAxis;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.data.category.DefaultCategoryDataset;
import org.jfree.data.general.DefaultPieDataset;
import org.jfree.data.statistics.HistogramDataset;
import org.jfree.data.statistics.HistogramType;

import navigation.administration.employeesNav.mainEmployees;
import navigation.administration.reportsNav.mainReports;
import navigation.administration.rolesNav.mainRoles;
import navigation.administration.shiftsNav.mainShifts;
import util.buttonFormatters.dashToggleFormatter;
import util.buttonFormatters.iDashToggleFormatter;
import util.databaseAPIs.dashboardsAPI;
import util.databaseAPIs.dashboardsAPI.tuple;

public class adminDash {

        private JPanel employeesPanel = new JPanel();
        private JPanel employeesAux = new JPanel();
        private JLabel employeesLabel = new JLabel();

        private JPanel rolesPanel = new JPanel();
        private JPanel rolesAux = new JPanel();
        private JLabel rolesLabel = new JLabel();

        private JPanel shiftsPanel = new JPanel();
        private JPanel shiftsAux = new JPanel();
        private JPanel headExpensesPanel = new JPanel();
        private JLabel shiftsLabel = new JLabel();

        private JPanel reportsPanel = new JPanel();
        private JPanel reportsAux = new JPanel();
        private JLabel reportsLabel = new JLabel();

        public adminDash(JFrame theFrame, JPanel leftAuxPanel, JPanel playground, JPanel jPanel4,
                        JLabel date) {
                initComp2(leftAuxPanel, jPanel4, date);
                initComp3(playground);
                addActionListeners(playground, theFrame);
        }

        public void initComp2(JPanel leftAuxPanel, JPanel jPanel4, JLabel date) {
                leftAuxPanel.setBackground(new java.awt.Color(71, 120, 197));

                employeesPanel.setBackground(new java.awt.Color(71, 120, 197));

                employeesAux.setPreferredSize(new java.awt.Dimension(5, 43));

                javax.swing.GroupLayout employeesAuxLayout = new javax.swing.GroupLayout(employeesAux);
                employeesAux.setLayout(employeesAuxLayout);
                employeesAuxLayout.setHorizontalGroup(
                                employeesAuxLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                .addGap(0, 5, Short.MAX_VALUE));
                employeesAuxLayout.setVerticalGroup(
                                employeesAuxLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                .addGap(0, 0, Short.MAX_VALUE));

                employeesLabel.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
                employeesLabel.setText("Employees");

                javax.swing.GroupLayout employeesPanelLayout = new javax.swing.GroupLayout(employeesPanel);
                employeesPanel.setLayout(employeesPanelLayout);
                employeesPanelLayout.setHorizontalGroup(
                                employeesPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                .addGroup(employeesPanelLayout.createSequentialGroup()
                                                                .addComponent(employeesAux,
                                                                                javax.swing.GroupLayout.PREFERRED_SIZE,
                                                                                javax.swing.GroupLayout.DEFAULT_SIZE,
                                                                                javax.swing.GroupLayout.PREFERRED_SIZE)
                                                                .addPreferredGap(
                                                                                javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                                                .addComponent(employeesLabel,
                                                                                javax.swing.GroupLayout.PREFERRED_SIZE,
                                                                                79,
                                                                                javax.swing.GroupLayout.PREFERRED_SIZE)
                                                                .addContainerGap(134, Short.MAX_VALUE)));
                employeesPanelLayout.setVerticalGroup(
                                employeesPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                .addGroup(employeesPanelLayout.createSequentialGroup()
                                                                .addGroup(employeesPanelLayout.createParallelGroup(
                                                                                javax.swing.GroupLayout.Alignment.LEADING,
                                                                                false)
                                                                                .addComponent(employeesAux,
                                                                                                javax.swing.GroupLayout.DEFAULT_SIZE,
                                                                                                javax.swing.GroupLayout.DEFAULT_SIZE,
                                                                                                Short.MAX_VALUE)
                                                                                .addComponent(employeesLabel,
                                                                                                javax.swing.GroupLayout.DEFAULT_SIZE,
                                                                                                43, Short.MAX_VALUE))
                                                                .addGap(0, 0, Short.MAX_VALUE)));

                jPanel4.setBackground(new java.awt.Color(120, 168, 252));

                javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
                jPanel4.setLayout(jPanel4Layout);
                jPanel4Layout.setHorizontalGroup(
                                jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                .addGap(0, 0, Short.MAX_VALUE));
                jPanel4Layout.setVerticalGroup(
                                jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                .addGap(0, 67, Short.MAX_VALUE));

                shiftsPanel.setBackground(new java.awt.Color(71, 120, 197));

                shiftsAux.setPreferredSize(new java.awt.Dimension(5, 43));

                javax.swing.GroupLayout shiftsAuxLayout = new javax.swing.GroupLayout(shiftsAux);
                shiftsAux.setLayout(shiftsAuxLayout);
                shiftsAuxLayout.setHorizontalGroup(
                                shiftsAuxLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                .addGap(0, 5, Short.MAX_VALUE));
                shiftsAuxLayout.setVerticalGroup(
                                shiftsAuxLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                .addGap(0, 0, Short.MAX_VALUE));

                shiftsLabel.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
                shiftsLabel.setText("Shifts");

                javax.swing.GroupLayout shiftsPanelLayout = new javax.swing.GroupLayout(shiftsPanel);
                shiftsPanel.setLayout(shiftsPanelLayout);
                shiftsPanelLayout.setHorizontalGroup(
                                shiftsPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                .addGroup(shiftsPanelLayout.createSequentialGroup()
                                                                .addComponent(shiftsAux,
                                                                                javax.swing.GroupLayout.PREFERRED_SIZE,
                                                                                javax.swing.GroupLayout.DEFAULT_SIZE,
                                                                                javax.swing.GroupLayout.PREFERRED_SIZE)
                                                                .addPreferredGap(
                                                                                javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                                                .addComponent(shiftsLabel,
                                                                                javax.swing.GroupLayout.PREFERRED_SIZE,
                                                                                79,
                                                                                javax.swing.GroupLayout.PREFERRED_SIZE)
                                                                .addContainerGap(134, Short.MAX_VALUE)));
                shiftsPanelLayout.setVerticalGroup(
                                shiftsPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                .addGroup(shiftsPanelLayout.createSequentialGroup()
                                                                .addGroup(shiftsPanelLayout.createParallelGroup(
                                                                                javax.swing.GroupLayout.Alignment.LEADING,
                                                                                false)
                                                                                .addComponent(shiftsAux,
                                                                                                javax.swing.GroupLayout.DEFAULT_SIZE,
                                                                                                javax.swing.GroupLayout.DEFAULT_SIZE,
                                                                                                Short.MAX_VALUE)
                                                                                .addComponent(shiftsLabel,
                                                                                                javax.swing.GroupLayout.DEFAULT_SIZE,
                                                                                                43, Short.MAX_VALUE))
                                                                .addGap(0, 0, Short.MAX_VALUE)));

                reportsPanel.setBackground(new java.awt.Color(71, 120, 197));

                reportsAux.setPreferredSize(new java.awt.Dimension(5, 43));

                javax.swing.GroupLayout reportsAuxLayout = new javax.swing.GroupLayout(reportsAux);
                reportsAux.setLayout(reportsAuxLayout);
                reportsAuxLayout.setHorizontalGroup(
                                reportsAuxLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                .addGap(0, 5, Short.MAX_VALUE));
                reportsAuxLayout.setVerticalGroup(
                                reportsAuxLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                .addGap(0, 0, Short.MAX_VALUE));

                reportsLabel.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
                reportsLabel.setText("Reports");

                javax.swing.GroupLayout reportsPanelLayout = new javax.swing.GroupLayout(reportsPanel);
                reportsPanel.setLayout(reportsPanelLayout);
                reportsPanelLayout.setHorizontalGroup(
                                reportsPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                .addGroup(reportsPanelLayout.createSequentialGroup()
                                                                .addComponent(reportsAux,
                                                                                javax.swing.GroupLayout.PREFERRED_SIZE,
                                                                                javax.swing.GroupLayout.DEFAULT_SIZE,
                                                                                javax.swing.GroupLayout.PREFERRED_SIZE)
                                                                .addPreferredGap(
                                                                                javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                                                .addComponent(reportsLabel,
                                                                                javax.swing.GroupLayout.PREFERRED_SIZE,
                                                                                79,
                                                                                javax.swing.GroupLayout.PREFERRED_SIZE)
                                                                .addContainerGap(134, Short.MAX_VALUE)));
                reportsPanelLayout.setVerticalGroup(
                                reportsPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                .addGroup(reportsPanelLayout.createSequentialGroup()
                                                                .addGroup(reportsPanelLayout.createParallelGroup(
                                                                                javax.swing.GroupLayout.Alignment.LEADING,
                                                                                false)
                                                                                .addComponent(reportsAux,
                                                                                                javax.swing.GroupLayout.DEFAULT_SIZE,
                                                                                                javax.swing.GroupLayout.DEFAULT_SIZE,
                                                                                                Short.MAX_VALUE)
                                                                                .addComponent(reportsLabel,
                                                                                                javax.swing.GroupLayout.DEFAULT_SIZE,
                                                                                                43, Short.MAX_VALUE))
                                                                .addGap(0, 0, Short.MAX_VALUE)));

                rolesPanel.setBackground(new java.awt.Color(71, 120, 197));

                rolesAux.setPreferredSize(new java.awt.Dimension(5, 43));

                javax.swing.GroupLayout rolesAuxLayout = new javax.swing.GroupLayout(rolesAux);
                rolesAux.setLayout(rolesAuxLayout);
                rolesAuxLayout.setHorizontalGroup(
                                rolesAuxLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                .addGap(0, 5, Short.MAX_VALUE));
                rolesAuxLayout.setVerticalGroup(
                                rolesAuxLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                .addGap(0, 0, Short.MAX_VALUE));

                rolesLabel.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
                rolesLabel.setText("Roles");

                javax.swing.GroupLayout rolesPanelLayout = new javax.swing.GroupLayout(rolesPanel);
                rolesPanel.setLayout(rolesPanelLayout);
                rolesPanelLayout.setHorizontalGroup(
                                rolesPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                .addGroup(rolesPanelLayout.createSequentialGroup()
                                                                .addComponent(rolesAux,
                                                                                javax.swing.GroupLayout.PREFERRED_SIZE,
                                                                                javax.swing.GroupLayout.DEFAULT_SIZE,
                                                                                javax.swing.GroupLayout.PREFERRED_SIZE)
                                                                .addPreferredGap(
                                                                                javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                                                .addComponent(rolesLabel,
                                                                                javax.swing.GroupLayout.PREFERRED_SIZE,
                                                                                79,
                                                                                javax.swing.GroupLayout.PREFERRED_SIZE)
                                                                .addContainerGap(134, Short.MAX_VALUE)));
                rolesPanelLayout.setVerticalGroup(
                                rolesPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                .addGroup(rolesPanelLayout.createSequentialGroup()
                                                                .addGroup(rolesPanelLayout.createParallelGroup(
                                                                                javax.swing.GroupLayout.Alignment.LEADING,
                                                                                false)
                                                                                .addComponent(rolesAux,
                                                                                                javax.swing.GroupLayout.DEFAULT_SIZE,
                                                                                                javax.swing.GroupLayout.DEFAULT_SIZE,
                                                                                                Short.MAX_VALUE)
                                                                                .addComponent(rolesLabel,
                                                                                                javax.swing.GroupLayout.DEFAULT_SIZE,
                                                                                                43, Short.MAX_VALUE))
                                                                .addGap(0, 0, Short.MAX_VALUE)));

                date.setForeground(new java.awt.Color(255, 255, 255));
                date.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);

                javax.swing.GroupLayout leftAuxPanelLayout = new javax.swing.GroupLayout(leftAuxPanel);
                leftAuxPanel.setLayout(leftAuxPanelLayout);
                leftAuxPanelLayout.setHorizontalGroup(
                                leftAuxPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                .addComponent(jPanel4, javax.swing.GroupLayout.DEFAULT_SIZE,
                                                                javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                                .addComponent(employeesPanel, javax.swing.GroupLayout.DEFAULT_SIZE,
                                                                javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                                .addComponent(shiftsPanel, javax.swing.GroupLayout.DEFAULT_SIZE,
                                                                javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                                .addComponent(reportsPanel, javax.swing.GroupLayout.DEFAULT_SIZE,
                                                                javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                                .addComponent(rolesPanel, javax.swing.GroupLayout.DEFAULT_SIZE,
                                                                javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                                .addGroup(leftAuxPanelLayout.createSequentialGroup()
                                                                .addContainerGap()
                                                                .addComponent(date,
                                                                                javax.swing.GroupLayout.DEFAULT_SIZE,
                                                                                javax.swing.GroupLayout.DEFAULT_SIZE,
                                                                                Short.MAX_VALUE)
                                                                .addContainerGap()));
                leftAuxPanelLayout.setVerticalGroup(
                                leftAuxPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                .addGroup(leftAuxPanelLayout.createSequentialGroup()
                                                                .addComponent(jPanel4,
                                                                                javax.swing.GroupLayout.PREFERRED_SIZE,
                                                                                javax.swing.GroupLayout.DEFAULT_SIZE,
                                                                                javax.swing.GroupLayout.PREFERRED_SIZE)
                                                                .addGap(58, 58, 58)
                                                                .addComponent(reportsPanel,
                                                                                javax.swing.GroupLayout.PREFERRED_SIZE,
                                                                                javax.swing.GroupLayout.DEFAULT_SIZE,
                                                                                javax.swing.GroupLayout.PREFERRED_SIZE)
                                                                .addPreferredGap(
                                                                                javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                                                .addComponent(shiftsPanel,
                                                                                javax.swing.GroupLayout.PREFERRED_SIZE,
                                                                                javax.swing.GroupLayout.DEFAULT_SIZE,
                                                                                javax.swing.GroupLayout.PREFERRED_SIZE)
                                                                .addPreferredGap(
                                                                                javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                                                .addComponent(employeesPanel,
                                                                                javax.swing.GroupLayout.PREFERRED_SIZE,
                                                                                javax.swing.GroupLayout.DEFAULT_SIZE,
                                                                                javax.swing.GroupLayout.PREFERRED_SIZE)
                                                                .addPreferredGap(
                                                                                javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                                                .addComponent(rolesPanel,
                                                                                javax.swing.GroupLayout.PREFERRED_SIZE,
                                                                                javax.swing.GroupLayout.DEFAULT_SIZE,
                                                                                javax.swing.GroupLayout.PREFERRED_SIZE)
                                                                .addPreferredGap(
                                                                                javax.swing.LayoutStyle.ComponentPlacement.RELATED,
                                                                                283, Short.MAX_VALUE)
                                                                .addComponent(date)
                                                                .addContainerGap()));
        }

        private JPanel salesPanel = new JPanel();
        private JLabel employeesLabelDash = new JLabel();

        private JPanel headSalesPanel = new JPanel();
        private JPanel salesContentPanel = new JPanel();
        private JPanel headEmployeesPanel = new JPanel();
        private JPanel emploeesContentPanel = new JPanel();
        private JPanel employeesHourPanel = new JPanel();
        private JPanel headEmployeesHourPanel = new JPanel();

        private JPanel employeesPanelDash = new JPanel();

        private JPanel employeesHourContentPanel = new JPanel();

        private JPanel expensesContentPanel = new JPanel();

        private JLabel employeesHourLabel = new JLabel();
        private JLabel expensesLabel = new JLabel();
        private JPanel expensesPanel = new JPanel();

        private JLabel salesLabel = new JLabel();
        private JToggleButton salesTuggleButton = new JToggleButton();
        private JToggleButton expensesTuggleButton = new JToggleButton();
        private JToggleButton employeesHourTuggleButton = new JToggleButton();

        private void initComp3(JPanel playground) {
                playground.setBackground(new Color(255, 255, 255));

                salesPanel.setBackground(new Color(255, 255, 255));
                salesPanel.setBorder(new LineBorder(new Color(204, 204, 204), 3, true));

                headSalesPanel.setBackground(new Color(204, 204, 204));

                salesLabel.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
                salesLabel.setHorizontalAlignment(SwingConstants.LEFT);
                salesLabel.setText("SALES OVERVIEW");

                GroupLayout headSalesPanelLayout = new GroupLayout(headSalesPanel);
                headSalesPanel.setLayout(headSalesPanelLayout);
                headSalesPanelLayout.setHorizontalGroup(
                                headSalesPanelLayout.createParallelGroup(GroupLayout.Alignment.LEADING)
                                                .addGroup(headSalesPanelLayout.createSequentialGroup()
                                                                .addContainerGap()
                                                                .addComponent(salesLabel,
                                                                                GroupLayout.PREFERRED_SIZE,
                                                                                215,
                                                                                GroupLayout.PREFERRED_SIZE)
                                                                .addGap(57, 57, 57)
                                                                .addComponent(salesTuggleButton,
                                                                                GroupLayout.DEFAULT_SIZE,
                                                                                127, Short.MAX_VALUE)));
                headSalesPanelLayout.setVerticalGroup(
                                headSalesPanelLayout.createParallelGroup(GroupLayout.Alignment.LEADING)
                                                .addComponent(salesLabel, GroupLayout.DEFAULT_SIZE, 31,
                                                                Short.MAX_VALUE)
                                                .addGroup(headSalesPanelLayout.createSequentialGroup()
                                                                .addComponent(salesTuggleButton)
                                                                .addGap(0, 0, Short.MAX_VALUE)));

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
                                                .addGroup(salesPanelLayout.createSequentialGroup()
                                                                .addContainerGap()
                                                                .addComponent(salesContentPanel,
                                                                                GroupLayout.DEFAULT_SIZE,
                                                                                GroupLayout.DEFAULT_SIZE,
                                                                                Short.MAX_VALUE)
                                                                .addContainerGap())
                                                .addComponent(headSalesPanel, GroupLayout.DEFAULT_SIZE,
                                                                GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE));
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

                employeesPanelDash.setBackground(new Color(255, 255, 255));
                employeesPanelDash.setBorder(
                                new LineBorder(new Color(204, 204, 204), 3, true));
                employeesPanelDash.setMaximumSize(new Dimension(270, 270));
                employeesPanelDash.setMinimumSize(new Dimension(270, 270));

                headEmployeesPanel.setBackground(new Color(204, 204, 204));
                headEmployeesPanel.setPreferredSize(new Dimension(295, 37));

                employeesLabelDash.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
                employeesLabelDash.setHorizontalAlignment(SwingConstants.CENTER);
                employeesLabelDash.setText("EMPLOYEE ROLE DISTRIBUTION");

                GroupLayout headEmployeesPanelLayout = new GroupLayout(headEmployeesPanel);
                headEmployeesPanel.setLayout(headEmployeesPanelLayout);
                headEmployeesPanelLayout.setHorizontalGroup(
                                headEmployeesPanelLayout.createParallelGroup(GroupLayout.Alignment.LEADING)
                                                .addGroup(headEmployeesPanelLayout.createSequentialGroup()
                                                                .addContainerGap()
                                                                .addComponent(employeesLabelDash,
                                                                                GroupLayout.DEFAULT_SIZE,
                                                                                258, Short.MAX_VALUE)
                                                                .addContainerGap()));
                headEmployeesPanelLayout.setVerticalGroup(
                                headEmployeesPanelLayout.createParallelGroup(GroupLayout.Alignment.LEADING)
                                                .addGroup(headEmployeesPanelLayout.createSequentialGroup()
                                                                .addComponent(employeesLabelDash)
                                                                .addGap(0, 7, Short.MAX_VALUE)));

                GroupLayout emploeesContentPanelLayout = new GroupLayout(emploeesContentPanel);
                emploeesContentPanel.setLayout(emploeesContentPanelLayout);
                emploeesContentPanelLayout.setHorizontalGroup(
                                emploeesContentPanelLayout
                                                .createParallelGroup(GroupLayout.Alignment.LEADING)
                                                .addGap(0, 0, Short.MAX_VALUE));
                emploeesContentPanelLayout.setVerticalGroup(
                                emploeesContentPanelLayout
                                                .createParallelGroup(GroupLayout.Alignment.LEADING)
                                                .addGap(0, 240, Short.MAX_VALUE));

                GroupLayout employeesPanelLayout = new GroupLayout(employeesPanelDash);
                employeesPanelDash.setLayout(employeesPanelLayout);
                employeesPanelLayout.setHorizontalGroup(
                                employeesPanelLayout.createParallelGroup(GroupLayout.Alignment.LEADING)
                                                .addComponent(headEmployeesPanel, GroupLayout.DEFAULT_SIZE,
                                                                282, Short.MAX_VALUE)
                                                .addGroup(employeesPanelLayout.createSequentialGroup()
                                                                .addContainerGap()
                                                                .addComponent(emploeesContentPanel,
                                                                                GroupLayout.DEFAULT_SIZE,
                                                                                GroupLayout.DEFAULT_SIZE,
                                                                                Short.MAX_VALUE)
                                                                .addContainerGap()));
                employeesPanelLayout.setVerticalGroup(
                                employeesPanelLayout.createParallelGroup(GroupLayout.Alignment.LEADING)
                                                .addGroup(employeesPanelLayout.createSequentialGroup()
                                                                .addComponent(headEmployeesPanel,
                                                                                GroupLayout.PREFERRED_SIZE,
                                                                                32,
                                                                                GroupLayout.PREFERRED_SIZE)
                                                                .addPreferredGap(
                                                                                ComponentPlacement.RELATED)
                                                                .addComponent(emploeesContentPanel,
                                                                                GroupLayout.PREFERRED_SIZE,
                                                                                GroupLayout.DEFAULT_SIZE,
                                                                                GroupLayout.PREFERRED_SIZE)
                                                                .addContainerGap()));

                expensesPanel.setBackground(new Color(255, 255, 255));
                expensesPanel.setBorder(new LineBorder(new Color(204, 204, 204), 3, true));

                headExpensesPanel.setBackground(new Color(204, 204, 204));

                expensesLabel.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
                expensesLabel.setHorizontalAlignment(SwingConstants.LEFT);
                expensesLabel.setText("EMPLOYEE EXPENSES");

                GroupLayout headExpensesPanelLayout = new GroupLayout(headExpensesPanel);
                headExpensesPanel.setLayout(headExpensesPanelLayout);
                headExpensesPanelLayout.setHorizontalGroup(
                                headExpensesPanelLayout.createParallelGroup(GroupLayout.Alignment.LEADING)
                                                .addGroup(headExpensesPanelLayout.createSequentialGroup()
                                                                .addContainerGap()
                                                                .addComponent(expensesLabel,
                                                                                GroupLayout.PREFERRED_SIZE,
                                                                                215,
                                                                                GroupLayout.PREFERRED_SIZE)
                                                                .addGap(57, 57, 57)
                                                                .addComponent(expensesTuggleButton,
                                                                                GroupLayout.DEFAULT_SIZE,
                                                                                127, Short.MAX_VALUE)));
                headExpensesPanelLayout.setVerticalGroup(
                                headExpensesPanelLayout.createParallelGroup(GroupLayout.Alignment.LEADING)
                                                .addComponent(expensesLabel, GroupLayout.DEFAULT_SIZE, 31,
                                                                Short.MAX_VALUE)
                                                .addGroup(headExpensesPanelLayout.createSequentialGroup()
                                                                .addComponent(expensesTuggleButton)
                                                                .addGap(0, 0, Short.MAX_VALUE)));

                GroupLayout expensesContentPanelLayout = new GroupLayout(expensesContentPanel);
                expensesContentPanel.setLayout(expensesContentPanelLayout);
                expensesContentPanelLayout.setHorizontalGroup(
                                expensesContentPanelLayout
                                                .createParallelGroup(GroupLayout.Alignment.LEADING)
                                                .addGap(0, 0, Short.MAX_VALUE));
                expensesContentPanelLayout.setVerticalGroup(
                                expensesContentPanelLayout
                                                .createParallelGroup(GroupLayout.Alignment.LEADING)
                                                .addGap(0, 234, Short.MAX_VALUE));

                GroupLayout expensesPanelLayout = new GroupLayout(expensesPanel);
                expensesPanel.setLayout(expensesPanelLayout);
                expensesPanelLayout.setHorizontalGroup(
                                expensesPanelLayout.createParallelGroup(GroupLayout.Alignment.LEADING)
                                                .addGroup(expensesPanelLayout.createSequentialGroup()
                                                                .addContainerGap()
                                                                .addComponent(expensesContentPanel,
                                                                                GroupLayout.DEFAULT_SIZE,
                                                                                GroupLayout.DEFAULT_SIZE,
                                                                                Short.MAX_VALUE)
                                                                .addContainerGap())
                                                .addComponent(headExpensesPanel, GroupLayout.DEFAULT_SIZE,
                                                                GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE));
                expensesPanelLayout.setVerticalGroup(
                                expensesPanelLayout.createParallelGroup(GroupLayout.Alignment.LEADING)
                                                .addGroup(expensesPanelLayout.createSequentialGroup()
                                                                .addComponent(headExpensesPanel,
                                                                                GroupLayout.PREFERRED_SIZE,
                                                                                GroupLayout.DEFAULT_SIZE,
                                                                                GroupLayout.PREFERRED_SIZE)
                                                                .addPreferredGap(
                                                                                ComponentPlacement.RELATED)
                                                                .addComponent(expensesContentPanel,
                                                                                GroupLayout.DEFAULT_SIZE,
                                                                                GroupLayout.DEFAULT_SIZE,
                                                                                Short.MAX_VALUE)
                                                                .addContainerGap()));

                employeesHourPanel.setBackground(new Color(255, 255, 255));
                employeesHourPanel.setBorder(
                                new LineBorder(new Color(204, 204, 204), 3, true));

                headEmployeesHourPanel.setBackground(new Color(204, 204, 204));

                employeesHourLabel.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
                employeesHourLabel.setHorizontalAlignment(SwingConstants.LEFT);
                employeesHourLabel.setText("EMPLOYEES HOUR");

                GroupLayout headEmployeesHourPanelLayout = new GroupLayout(
                                headEmployeesHourPanel);
                headEmployeesHourPanel.setLayout(headEmployeesHourPanelLayout);
                headEmployeesHourPanelLayout.setHorizontalGroup(
                                headEmployeesHourPanelLayout
                                                .createParallelGroup(GroupLayout.Alignment.LEADING)
                                                .addGroup(headEmployeesHourPanelLayout.createSequentialGroup()
                                                                .addContainerGap()
                                                                .addComponent(employeesHourLabel,
                                                                                GroupLayout.PREFERRED_SIZE,
                                                                                215,
                                                                                GroupLayout.PREFERRED_SIZE)
                                                                .addGap(57, 57, 57)
                                                                .addComponent(employeesHourTuggleButton,
                                                                                GroupLayout.DEFAULT_SIZE,
                                                                                GroupLayout.DEFAULT_SIZE,
                                                                                Short.MAX_VALUE)));
                headEmployeesHourPanelLayout.setVerticalGroup(
                                headEmployeesHourPanelLayout
                                                .createParallelGroup(GroupLayout.Alignment.LEADING)
                                                .addComponent(employeesHourLabel, GroupLayout.DEFAULT_SIZE,
                                                                31, Short.MAX_VALUE)
                                                .addGroup(headEmployeesHourPanelLayout.createSequentialGroup()
                                                                .addComponent(employeesHourTuggleButton)
                                                                .addGap(0, 0, Short.MAX_VALUE)));

                GroupLayout employeesHourContentPanelLayout = new GroupLayout(
                                employeesHourContentPanel);
                employeesHourContentPanel.setLayout(employeesHourContentPanelLayout);
                employeesHourContentPanelLayout.setHorizontalGroup(
                                employeesHourContentPanelLayout
                                                .createParallelGroup(GroupLayout.Alignment.LEADING)
                                                .addGap(0, 0, Short.MAX_VALUE));
                employeesHourContentPanelLayout.setVerticalGroup(
                                employeesHourContentPanelLayout
                                                .createParallelGroup(GroupLayout.Alignment.LEADING)
                                                .addGap(0, 0, Short.MAX_VALUE));

                GroupLayout employeesHourPanelLayout = new GroupLayout(employeesHourPanel);
                employeesHourPanel.setLayout(employeesHourPanelLayout);
                employeesHourPanelLayout.setHorizontalGroup(
                                employeesHourPanelLayout.createParallelGroup(GroupLayout.Alignment.LEADING)
                                                .addGroup(employeesHourPanelLayout.createSequentialGroup()
                                                                .addContainerGap()
                                                                .addComponent(employeesHourContentPanel,
                                                                                GroupLayout.DEFAULT_SIZE,
                                                                                GroupLayout.DEFAULT_SIZE,
                                                                                Short.MAX_VALUE)
                                                                .addContainerGap())
                                                .addComponent(headEmployeesHourPanel,
                                                                GroupLayout.DEFAULT_SIZE,
                                                                GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE));
                employeesHourPanelLayout.setVerticalGroup(
                                employeesHourPanelLayout.createParallelGroup(GroupLayout.Alignment.LEADING)
                                                .addGroup(employeesHourPanelLayout.createSequentialGroup()
                                                                .addComponent(headEmployeesHourPanel,
                                                                                GroupLayout.PREFERRED_SIZE,
                                                                                GroupLayout.DEFAULT_SIZE,
                                                                                GroupLayout.PREFERRED_SIZE)
                                                                .addPreferredGap(
                                                                                ComponentPlacement.RELATED)
                                                                .addComponent(employeesHourContentPanel,
                                                                                GroupLayout.DEFAULT_SIZE,
                                                                                GroupLayout.DEFAULT_SIZE,
                                                                                Short.MAX_VALUE)
                                                                .addContainerGap()));

                GroupLayout playgroundLayout = new GroupLayout(playground);
                playground.setLayout(playgroundLayout);
                playgroundLayout.setHorizontalGroup(
                                playgroundLayout.createParallelGroup(GroupLayout.Alignment.LEADING)
                                                .addGroup(playgroundLayout.createSequentialGroup()
                                                                .addContainerGap()
                                                                .addGroup(playgroundLayout.createParallelGroup(
                                                                                GroupLayout.Alignment.LEADING)
                                                                                .addGroup(playgroundLayout
                                                                                                .createSequentialGroup()
                                                                                                .addComponent(employeesPanelDash,
                                                                                                                GroupLayout.PREFERRED_SIZE,
                                                                                                                GroupLayout.DEFAULT_SIZE,
                                                                                                                GroupLayout.PREFERRED_SIZE)
                                                                                                .addPreferredGap(
                                                                                                                ComponentPlacement.UNRELATED)
                                                                                                .addComponent(employeesHourPanel,
                                                                                                                GroupLayout.DEFAULT_SIZE,
                                                                                                                GroupLayout.DEFAULT_SIZE,
                                                                                                                Short.MAX_VALUE))
                                                                                .addGroup(playgroundLayout
                                                                                                .createSequentialGroup()
                                                                                                .addComponent(salesPanel,
                                                                                                                GroupLayout.PREFERRED_SIZE,
                                                                                                                GroupLayout.DEFAULT_SIZE,
                                                                                                                GroupLayout.PREFERRED_SIZE)
                                                                                                .addPreferredGap(
                                                                                                                ComponentPlacement.RELATED,
                                                                                                                12,
                                                                                                                Short.MAX_VALUE)
                                                                                                .addComponent(expensesPanel,
                                                                                                                GroupLayout.PREFERRED_SIZE,
                                                                                                                GroupLayout.DEFAULT_SIZE,
                                                                                                                GroupLayout.PREFERRED_SIZE)))
                                                                .addContainerGap()));
                playgroundLayout.setVerticalGroup(
                                playgroundLayout.createParallelGroup(GroupLayout.Alignment.LEADING)
                                                .addGroup(playgroundLayout.createSequentialGroup()
                                                                .addContainerGap()
                                                                .addGroup(playgroundLayout.createParallelGroup(
                                                                                GroupLayout.Alignment.LEADING)
                                                                                .addComponent(salesPanel,
                                                                                                GroupLayout.PREFERRED_SIZE,
                                                                                                GroupLayout.DEFAULT_SIZE,
                                                                                                GroupLayout.PREFERRED_SIZE)
                                                                                .addComponent(expensesPanel,
                                                                                                GroupLayout.PREFERRED_SIZE,
                                                                                                GroupLayout.DEFAULT_SIZE,
                                                                                                GroupLayout.PREFERRED_SIZE))
                                                                .addGap(18, 18, 18)
                                                                .addGroup(playgroundLayout.createParallelGroup(
                                                                                GroupLayout.Alignment.LEADING,
                                                                                false)
                                                                                .addComponent(employeesPanelDash,
                                                                                                GroupLayout.DEFAULT_SIZE,
                                                                                                GroupLayout.DEFAULT_SIZE,
                                                                                                Short.MAX_VALUE)
                                                                                .addComponent(employeesHourPanel,
                                                                                                GroupLayout.DEFAULT_SIZE,
                                                                                                GroupLayout.DEFAULT_SIZE,
                                                                                                Short.MAX_VALUE))
                                                                .addContainerGap(GroupLayout.DEFAULT_SIZE,
                                                                                Short.MAX_VALUE)));
                setGraphSales(7);
                setGraphExpenses(7);
                setGraphDistribution();
                setGraphScheduledHours(false);
        }

        private void addActionListeners(JPanel playground, JFrame theFrame) {
                employeesPanel.addMouseListener(new MouseListener() {
                        boolean clicked = false;

                        public void mouseClicked(MouseEvent e) {
                                setColor(employeesPanel);
                                resetColor(shiftsPanel);
                                resetColor(reportsPanel);
                                resetColor(rolesPanel);
                                clicked = true;
                                playground.removeAll();
                                new mainEmployees(playground);
                                playground.revalidate();
                                playground.repaint();
                        }

                        public void mousePressed(MouseEvent e) {
                        }

                        public void mouseReleased(MouseEvent e) {
                        }

                        public void mouseEntered(MouseEvent e) {
                                if (employeesPanel.getBackground().equals(new Color(120, 168, 252)))
                                        clicked = true;
                                else
                                        clicked = false;
                                setColor(employeesPanel);
                        }

                        public void mouseExited(MouseEvent e) {
                                if (!clicked)
                                        resetColor(employeesPanel);
                                if (shiftsPanel.getBackground().equals(new Color(41, 57, 80))
                                                && reportsPanel.getBackground().equals(new Color(41, 57, 80)))
                                        clicked = false;
                        }
                });
                shiftsPanel.addMouseListener(new MouseListener() {
                        boolean clicked = false;

                        public void mouseClicked(MouseEvent e) {
                                setColor(shiftsPanel);
                                resetColor(employeesPanel);
                                resetColor(reportsPanel);
                                resetColor(rolesPanel);
                                clicked = true;
                                playground.removeAll();
                                new mainShifts(playground, "01-06-2022", "01-06-2023", false);
                                playground.revalidate();
                                playground.repaint();
                        }

                        public void mousePressed(MouseEvent e) {
                        }

                        public void mouseReleased(MouseEvent e) {
                        }

                        public void mouseEntered(MouseEvent e) {
                                if (shiftsPanel.getBackground().equals(new Color(120, 168, 252)))
                                        clicked = true;
                                else
                                        clicked = false;
                                setColor(shiftsPanel);
                        }

                        public void mouseExited(MouseEvent e) {
                                if (!clicked)
                                        resetColor(shiftsPanel);
                                if (employeesPanel.getBackground().equals(new Color(41, 57, 80))
                                                && rolesPanel.getBackground().equals(new Color(41, 57, 80))
                                                && rolesPanel.getBackground().equals(new Color(41, 57, 80))
                                                && reportsPanel.getBackground().equals(new Color(41, 57, 80)))
                                        clicked = false;
                        }
                });
                reportsPanel.addMouseListener(new MouseListener() {
                        boolean clicked = false;

                        public void mouseClicked(MouseEvent e) {
                                setColor(reportsPanel);
                                resetColor(shiftsPanel);
                                resetColor(employeesPanel);
                                resetColor(rolesPanel);
                                clicked = true;
                                playground.removeAll();
                                new mainReports(theFrame, playground);
                                playground.revalidate();
                                playground.repaint();
                        }

                        public void mousePressed(MouseEvent e) {
                        }

                        public void mouseReleased(MouseEvent e) {
                        }

                        public void mouseEntered(MouseEvent e) {
                                if (reportsPanel.getBackground().equals(new Color(120, 168, 252)))
                                        clicked = true;
                                else
                                        clicked = false;
                                setColor(reportsPanel);
                        }

                        public void mouseExited(MouseEvent e) {
                                if (!clicked)
                                        resetColor(reportsPanel);
                                if (employeesPanel.getBackground().equals(new Color(41, 57, 80))
                                                && shiftsPanel.getBackground().equals(new Color(41, 57, 80))
                                                && rolesPanel.getBackground().equals(new Color(41, 57, 80)))
                                        clicked = false;
                        }
                });
                rolesPanel.addMouseListener(new MouseListener() {
                        boolean clicked = false;

                        public void mouseClicked(MouseEvent e) {
                                setColor(rolesPanel);
                                resetColor(shiftsPanel);
                                resetColor(employeesPanel);
                                resetColor(reportsPanel);
                                clicked = true;
                                playground.removeAll();
                                new mainRoles(playground);
                                playground.revalidate();
                                playground.repaint();
                                // initComp2();
                        }

                        public void mousePressed(MouseEvent e) {
                        }

                        public void mouseReleased(MouseEvent e) {
                        }

                        public void mouseEntered(MouseEvent e) {
                                if (rolesPanel.getBackground().equals(new Color(120, 168, 252)))
                                        clicked = true;
                                else
                                        clicked = false;
                                setColor(rolesPanel);
                        }

                        public void mouseExited(MouseEvent e) {
                                if (!clicked)
                                        resetColor(rolesPanel);
                                if (employeesPanel.getBackground().equals(new Color(41, 57, 80))
                                                && shiftsPanel.getBackground().equals(new Color(41, 57, 80))
                                                && reportsPanel.getBackground().equals(new Color(41, 57, 80)))
                                        clicked = false;
                        }
                });
                employeesTuggleButton();
                salesTuggleButton();
                expensesTuggleButton();
        }

        private void employeesTuggleButton() {
                class employeesTuggleHolder implements iDashToggleFormatter {
                        public void action1() {
                                setGraphScheduledHours(true);
                        }

                        public void action2() {
                                setGraphScheduledHours(false);
                        }
                }
                dashToggleFormatter.applyDashActionListenerToggle(employeesHourTuggleButton, "Last Week",
                                "Today",
                                new employeesTuggleHolder());
        }

        private void salesTuggleButton() {
                class salesTuggleHolder implements iDashToggleFormatter {
                        public void action1() {
                                setGraphSales(14);
                        }

                        public void action2() {
                                setGraphSales(7);
                        }
                }
                dashToggleFormatter.applyDashActionListenerToggle(salesTuggleButton, "Last 14 days", "Last Week",
                                new salesTuggleHolder());
        }

        private void expensesTuggleButton() {
                class expensesTuggleHolder implements iDashToggleFormatter {
                        public void action1() {
                                setGraphExpenses(14);
                        }

                        public void action2() {
                                setGraphExpenses(7);
                        }
                }
                dashToggleFormatter.applyDashActionListenerToggle(expensesTuggleButton, "Last 14 days", "Last Week",
                                new expensesTuggleHolder());
        }

        private void setColor(JPanel panel) {
                panel.setBackground(new Color(120, 168, 252));
        }

        private void resetColor(JPanel panel) {
                panel.setBackground(new Color(71, 120, 197));
        }

        private void setGraphSales(int goal) {
                salesContentPanel.removeAll();
                DefaultCategoryDataset datos = new DefaultCategoryDataset();
                String date = "";
                for (int i = goal - 1; i > -1; i--) {
                        if (goal == 7)
                                date = LocalDate.now().minus(i, ChronoUnit.DAYS)
                                                .format(DateTimeFormatter.ofPattern("dd-MM"));
                        else
                                date = Integer.toString(-i);
                        datos.setValue(dashboardsAPI.getSalesOnDay(i), "Sales", date);
                }

                JFreeChart barChart = ChartFactory.createBarChart("Sales in Last " + goal + " Days", null, "Sales (€)",
                                datos, PlotOrientation.VERTICAL, false, true, false);
                ChartPanel pan = new ChartPanel(barChart);
                pan.setMouseWheelEnabled(false);
                pan.setMinimumSize(new Dimension(387, 234));
                pan.setMaximumSize(new Dimension(387, 234));
                pan.setPreferredSize(new Dimension(387, 234));

                salesContentPanel.setLayout(new BorderLayout());
                salesContentPanel.add(pan, BorderLayout.CENTER);
                salesContentPanel.revalidate();
                salesContentPanel.repaint();
        }

        private void setGraphExpenses(int goal) {
                expensesContentPanel.removeAll();
                DefaultCategoryDataset datos = new DefaultCategoryDataset();
                String date = "";
                for (int i = goal - 1; i > -1; i--) {

                        if (goal == 7 || i == goal - 1 || i == 0)
                                date = LocalDate.now().minus(i, ChronoUnit.DAYS)
                                                .format(DateTimeFormatter.ofPattern("dd-MM"));
                        else
                                date = Integer.toString(-i);
                        datos.setValue(dashboardsAPI.expensesSalaryDaily(i), "Expenses", date);
                }

                JFreeChart barChart = ChartFactory.createBarChart("Employees Expenses in Last " + goal + " Days", null,
                                "Expenses (€)",
                                datos, PlotOrientation.VERTICAL, false, true, false);
                ChartPanel pan = new ChartPanel(barChart);
                pan.setMouseWheelEnabled(false);
                pan.setMinimumSize(new Dimension(387, 234));
                pan.setMaximumSize(new Dimension(387, 234));
                pan.setPreferredSize(new Dimension(387, 234));

                expensesContentPanel.setLayout(new BorderLayout());
                expensesContentPanel.add(pan, BorderLayout.CENTER);
                expensesContentPanel.revalidate();
                expensesContentPanel.repaint();
        }

        private void setGraphDistribution() {
                emploeesContentPanel.removeAll();
                DefaultPieDataset dataset = new DefaultPieDataset();
                for (tuple temp : dashboardsAPI.getEmployeeCategoryPercentagesToday())
                        dataset.setValue(temp.roleName, temp.percentage);
                JFreeChart pieChart = ChartFactory.createPieChart("Today", dataset, false,
                                true, false);
                ChartPanel panel = new ChartPanel(pieChart);
                panel.setMouseWheelEnabled(false);
                panel.setMinimumSize(new Dimension(258, 240));
                panel.setMaximumSize(new Dimension(258, 240));
                panel.setPreferredSize(new Dimension(258, 240));
                emploeesContentPanel.setLayout(new BorderLayout());
                emploeesContentPanel.add(panel, BorderLayout.CENTER);
                emploeesContentPanel.revalidate();
                emploeesContentPanel.repaint();
        }

        private void setGraphScheduledHours(boolean weekly) {
                employeesHourContentPanel.removeAll();
                ArrayList<Double> tempList = new ArrayList<Double>();
                for (int i = 0; i < 23; i++) {
                        for (int j = 0; j < dashboardsAPI.getFrequencyOfShift(i, weekly); j++)
                                tempList.add((double) i);
                }
                double[] target = new double[tempList.size()];
                for (int i = 0; i < target.length; i++)
                        target[i] = tempList.get(i).doubleValue();

                HistogramDataset dataset = new HistogramDataset();
                dataset.setType(HistogramType.FREQUENCY);
                if (target.length != 0)
                        dataset.addSeries("Hours", target, 24);
                String title = "Workers per Hour";
                String yAxis = "Number of Employees";
                if (weekly) {
                        title = "Sum of Worked Hours";
                        yAxis = "Total Worked Hours";
                }
                JFreeChart histogram = ChartFactory.createHistogram(title, "Hour of Day",
                                yAxis, dataset, PlotOrientation.VERTICAL, false, false, false);
                histogram.getXYPlot().getDomainAxis().setStandardTickUnits(NumberAxis.createIntegerTickUnits());
                histogram.getXYPlot().getRangeAxis().setStandardTickUnits(NumberAxis.createIntegerTickUnits());
                ChartPanel panel = new ChartPanel(histogram);
                panel.setMouseWheelEnabled(false);
                panel.setMinimumSize(new Dimension(258, 240));
                panel.setMaximumSize(new Dimension(258, 240));
                panel.setPreferredSize(new Dimension(258, 240));
                employeesHourContentPanel.setLayout(new BorderLayout());
                employeesHourContentPanel.add(panel, BorderLayout.CENTER);
                employeesHourContentPanel.revalidate();
                employeesHourContentPanel.repaint();
        }
}