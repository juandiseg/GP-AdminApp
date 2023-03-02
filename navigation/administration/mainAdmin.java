package navigation.administration;

import java.awt.*;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.awt.event.*;

import javax.swing.*;

import org.jfree.chart.*;
import org.jfree.chart.axis.NumberAxis;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.data.category.DefaultCategoryDataset;
import org.jfree.data.general.DefaultPieDataset;
import org.jfree.data.statistics.HistogramDataset;
import org.jfree.data.statistics.HistogramType;

import navigation.dashboardsAPI;
import navigation.administration.employeeSection.mainEmployees;
import navigation.administration.reports_Window.generateReport;
import navigation.administration.roleSection.mainRole;
import navigation.administration.shifts_Window.mainShifts;
import navigation.dashboardsAPI.tuple;

public class mainAdmin {

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

        private JPanel topLeftAux = new JPanel();

        public mainAdmin(JFrame theFrame, JPanel leftAuxPanel, JPanel playground, JPanel jPanel4,
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

                topLeftAux.setBackground(new java.awt.Color(120, 168, 252));

                javax.swing.GroupLayout topLeftAuxLayout = new javax.swing.GroupLayout(topLeftAux);
                topLeftAux.setLayout(topLeftAuxLayout);
                topLeftAuxLayout.setHorizontalGroup(
                                topLeftAuxLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                .addGap(0, 0, Short.MAX_VALUE));
                topLeftAuxLayout.setVerticalGroup(
                                topLeftAuxLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                .addGap(0, 67, Short.MAX_VALUE));

                date.setForeground(new java.awt.Color(255, 255, 255));
                date.setText("13 Feb 2023");

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

                javax.swing.GroupLayout leftAuxPanelLayout = new javax.swing.GroupLayout(leftAuxPanel);
                leftAuxPanel.setLayout(leftAuxPanelLayout);
                leftAuxPanelLayout.setHorizontalGroup(
                                leftAuxPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                .addComponent(topLeftAux, javax.swing.GroupLayout.DEFAULT_SIZE,
                                                                javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                                .addComponent(employeesPanel, javax.swing.GroupLayout.DEFAULT_SIZE,
                                                                javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                                .addComponent(shiftsPanel, javax.swing.GroupLayout.DEFAULT_SIZE,
                                                                javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                                .addComponent(reportsPanel, javax.swing.GroupLayout.DEFAULT_SIZE,
                                                                javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                                .addGroup(leftAuxPanelLayout.createSequentialGroup()
                                                                .addGap(80, 80, 80)
                                                                .addComponent(date)
                                                                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE,
                                                                                Short.MAX_VALUE))
                                                .addComponent(rolesPanel, javax.swing.GroupLayout.DEFAULT_SIZE,
                                                                javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE));
                leftAuxPanelLayout.setVerticalGroup(
                                leftAuxPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                .addGroup(leftAuxPanelLayout.createSequentialGroup()
                                                                .addComponent(topLeftAux,
                                                                                javax.swing.GroupLayout.PREFERRED_SIZE,
                                                                                javax.swing.GroupLayout.DEFAULT_SIZE,
                                                                                javax.swing.GroupLayout.PREFERRED_SIZE)
                                                                .addGap(58, 58, 58)
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
                                                                                javax.swing.LayoutStyle.ComponentPlacement.RELATED)
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
                                                                                javax.swing.LayoutStyle.ComponentPlacement.RELATED,
                                                                                293, Short.MAX_VALUE)
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
                                                                                215,
                                                                                javax.swing.GroupLayout.PREFERRED_SIZE)
                                                                .addGap(57, 57, 57)
                                                                .addComponent(salesTuggleButton,
                                                                                javax.swing.GroupLayout.DEFAULT_SIZE,
                                                                                127, Short.MAX_VALUE)));
                headSalesPanelLayout.setVerticalGroup(
                                headSalesPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                .addComponent(salesLabel, javax.swing.GroupLayout.DEFAULT_SIZE, 31,
                                                                Short.MAX_VALUE)
                                                .addGroup(headSalesPanelLayout.createSequentialGroup()
                                                                .addComponent(salesTuggleButton)
                                                                .addGap(0, 0, Short.MAX_VALUE)));

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
                                                .addGroup(salesPanelLayout.createSequentialGroup()
                                                                .addContainerGap()
                                                                .addComponent(salesContentPanel,
                                                                                javax.swing.GroupLayout.DEFAULT_SIZE,
                                                                                javax.swing.GroupLayout.DEFAULT_SIZE,
                                                                                Short.MAX_VALUE)
                                                                .addContainerGap())
                                                .addComponent(headSalesPanel, javax.swing.GroupLayout.DEFAULT_SIZE,
                                                                javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE));
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

                employeesPanelDash.setBackground(new java.awt.Color(255, 255, 255));
                employeesPanelDash.setBorder(
                                new javax.swing.border.LineBorder(new java.awt.Color(204, 204, 204), 3, true));
                employeesPanelDash.setMaximumSize(new java.awt.Dimension(270, 270));
                employeesPanelDash.setMinimumSize(new java.awt.Dimension(270, 270));

                headEmployeesPanel.setBackground(new java.awt.Color(204, 204, 204));
                headEmployeesPanel.setPreferredSize(new java.awt.Dimension(295, 37));

                employeesLabelDash.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
                employeesLabelDash.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
                employeesLabelDash.setText("EMPLOYEE ROLE DISTRIBUTION");

                javax.swing.GroupLayout headEmployeesPanelLayout = new javax.swing.GroupLayout(headEmployeesPanel);
                headEmployeesPanel.setLayout(headEmployeesPanelLayout);
                headEmployeesPanelLayout.setHorizontalGroup(
                                headEmployeesPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                .addGroup(headEmployeesPanelLayout.createSequentialGroup()
                                                                .addContainerGap()
                                                                .addComponent(employeesLabelDash,
                                                                                javax.swing.GroupLayout.DEFAULT_SIZE,
                                                                                258, Short.MAX_VALUE)
                                                                .addContainerGap()));
                headEmployeesPanelLayout.setVerticalGroup(
                                headEmployeesPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                .addGroup(headEmployeesPanelLayout.createSequentialGroup()
                                                                .addComponent(employeesLabelDash)
                                                                .addGap(0, 7, Short.MAX_VALUE)));

                javax.swing.GroupLayout emploeesContentPanelLayout = new javax.swing.GroupLayout(emploeesContentPanel);
                emploeesContentPanel.setLayout(emploeesContentPanelLayout);
                emploeesContentPanelLayout.setHorizontalGroup(
                                emploeesContentPanelLayout
                                                .createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                .addGap(0, 0, Short.MAX_VALUE));
                emploeesContentPanelLayout.setVerticalGroup(
                                emploeesContentPanelLayout
                                                .createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                .addGap(0, 240, Short.MAX_VALUE));

                javax.swing.GroupLayout employeesPanelLayout = new javax.swing.GroupLayout(employeesPanelDash);
                employeesPanelDash.setLayout(employeesPanelLayout);
                employeesPanelLayout.setHorizontalGroup(
                                employeesPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                .addComponent(headEmployeesPanel, javax.swing.GroupLayout.DEFAULT_SIZE,
                                                                282, Short.MAX_VALUE)
                                                .addGroup(employeesPanelLayout.createSequentialGroup()
                                                                .addContainerGap()
                                                                .addComponent(emploeesContentPanel,
                                                                                javax.swing.GroupLayout.DEFAULT_SIZE,
                                                                                javax.swing.GroupLayout.DEFAULT_SIZE,
                                                                                Short.MAX_VALUE)
                                                                .addContainerGap()));
                employeesPanelLayout.setVerticalGroup(
                                employeesPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                .addGroup(employeesPanelLayout.createSequentialGroup()
                                                                .addComponent(headEmployeesPanel,
                                                                                javax.swing.GroupLayout.PREFERRED_SIZE,
                                                                                32,
                                                                                javax.swing.GroupLayout.PREFERRED_SIZE)
                                                                .addPreferredGap(
                                                                                javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                                                .addComponent(emploeesContentPanel,
                                                                                javax.swing.GroupLayout.PREFERRED_SIZE,
                                                                                javax.swing.GroupLayout.DEFAULT_SIZE,
                                                                                javax.swing.GroupLayout.PREFERRED_SIZE)
                                                                .addContainerGap()));

                expensesPanel.setBackground(new java.awt.Color(255, 255, 255));
                expensesPanel.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(204, 204, 204), 3, true));

                headExpensesPanel.setBackground(new java.awt.Color(204, 204, 204));

                expensesLabel.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
                expensesLabel.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
                expensesLabel.setText("EMPLOYEE EXPENSES");

                expensesTuggleButton.setText("Last 14 days");

                javax.swing.GroupLayout headExpensesPanelLayout = new javax.swing.GroupLayout(headExpensesPanel);
                headExpensesPanel.setLayout(headExpensesPanelLayout);
                headExpensesPanelLayout.setHorizontalGroup(
                                headExpensesPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                .addGroup(headExpensesPanelLayout.createSequentialGroup()
                                                                .addContainerGap()
                                                                .addComponent(expensesLabel,
                                                                                javax.swing.GroupLayout.PREFERRED_SIZE,
                                                                                215,
                                                                                javax.swing.GroupLayout.PREFERRED_SIZE)
                                                                .addGap(57, 57, 57)
                                                                .addComponent(expensesTuggleButton,
                                                                                javax.swing.GroupLayout.DEFAULT_SIZE,
                                                                                127, Short.MAX_VALUE)));
                headExpensesPanelLayout.setVerticalGroup(
                                headExpensesPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                .addComponent(expensesLabel, javax.swing.GroupLayout.DEFAULT_SIZE, 31,
                                                                Short.MAX_VALUE)
                                                .addGroup(headExpensesPanelLayout.createSequentialGroup()
                                                                .addComponent(expensesTuggleButton)
                                                                .addGap(0, 0, Short.MAX_VALUE)));

                javax.swing.GroupLayout expensesContentPanelLayout = new javax.swing.GroupLayout(expensesContentPanel);
                expensesContentPanel.setLayout(expensesContentPanelLayout);
                expensesContentPanelLayout.setHorizontalGroup(
                                expensesContentPanelLayout
                                                .createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                .addGap(0, 0, Short.MAX_VALUE));
                expensesContentPanelLayout.setVerticalGroup(
                                expensesContentPanelLayout
                                                .createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                .addGap(0, 234, Short.MAX_VALUE));

                javax.swing.GroupLayout expensesPanelLayout = new javax.swing.GroupLayout(expensesPanel);
                expensesPanel.setLayout(expensesPanelLayout);
                expensesPanelLayout.setHorizontalGroup(
                                expensesPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                .addGroup(expensesPanelLayout.createSequentialGroup()
                                                                .addContainerGap()
                                                                .addComponent(expensesContentPanel,
                                                                                javax.swing.GroupLayout.DEFAULT_SIZE,
                                                                                javax.swing.GroupLayout.DEFAULT_SIZE,
                                                                                Short.MAX_VALUE)
                                                                .addContainerGap())
                                                .addComponent(headExpensesPanel, javax.swing.GroupLayout.DEFAULT_SIZE,
                                                                javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE));
                expensesPanelLayout.setVerticalGroup(
                                expensesPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                .addGroup(expensesPanelLayout.createSequentialGroup()
                                                                .addComponent(headExpensesPanel,
                                                                                javax.swing.GroupLayout.PREFERRED_SIZE,
                                                                                javax.swing.GroupLayout.DEFAULT_SIZE,
                                                                                javax.swing.GroupLayout.PREFERRED_SIZE)
                                                                .addPreferredGap(
                                                                                javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                                                .addComponent(expensesContentPanel,
                                                                                javax.swing.GroupLayout.DEFAULT_SIZE,
                                                                                javax.swing.GroupLayout.DEFAULT_SIZE,
                                                                                Short.MAX_VALUE)
                                                                .addContainerGap()));

                employeesHourPanel.setBackground(new java.awt.Color(255, 255, 255));
                employeesHourPanel.setBorder(
                                new javax.swing.border.LineBorder(new java.awt.Color(204, 204, 204), 3, true));

                headEmployeesHourPanel.setBackground(new java.awt.Color(204, 204, 204));

                employeesHourLabel.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
                employeesHourLabel.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
                employeesHourLabel.setText("EMPLOYEES HOUR");

                employeesHourTuggleButton.setText("Last Week");

                javax.swing.GroupLayout headEmployeesHourPanelLayout = new javax.swing.GroupLayout(
                                headEmployeesHourPanel);
                headEmployeesHourPanel.setLayout(headEmployeesHourPanelLayout);
                headEmployeesHourPanelLayout.setHorizontalGroup(
                                headEmployeesHourPanelLayout
                                                .createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                .addGroup(headEmployeesHourPanelLayout.createSequentialGroup()
                                                                .addContainerGap()
                                                                .addComponent(employeesHourLabel,
                                                                                javax.swing.GroupLayout.PREFERRED_SIZE,
                                                                                215,
                                                                                javax.swing.GroupLayout.PREFERRED_SIZE)
                                                                .addGap(57, 57, 57)
                                                                .addComponent(employeesHourTuggleButton,
                                                                                javax.swing.GroupLayout.DEFAULT_SIZE,
                                                                                javax.swing.GroupLayout.DEFAULT_SIZE,
                                                                                Short.MAX_VALUE)));
                headEmployeesHourPanelLayout.setVerticalGroup(
                                headEmployeesHourPanelLayout
                                                .createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                .addComponent(employeesHourLabel, javax.swing.GroupLayout.DEFAULT_SIZE,
                                                                31, Short.MAX_VALUE)
                                                .addGroup(headEmployeesHourPanelLayout.createSequentialGroup()
                                                                .addComponent(employeesHourTuggleButton)
                                                                .addGap(0, 0, Short.MAX_VALUE)));

                javax.swing.GroupLayout employeesHourContentPanelLayout = new javax.swing.GroupLayout(
                                employeesHourContentPanel);
                employeesHourContentPanel.setLayout(employeesHourContentPanelLayout);
                employeesHourContentPanelLayout.setHorizontalGroup(
                                employeesHourContentPanelLayout
                                                .createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                .addGap(0, 0, Short.MAX_VALUE));
                employeesHourContentPanelLayout.setVerticalGroup(
                                employeesHourContentPanelLayout
                                                .createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                .addGap(0, 0, Short.MAX_VALUE));

                javax.swing.GroupLayout employeesHourPanelLayout = new javax.swing.GroupLayout(employeesHourPanel);
                employeesHourPanel.setLayout(employeesHourPanelLayout);
                employeesHourPanelLayout.setHorizontalGroup(
                                employeesHourPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                .addGroup(employeesHourPanelLayout.createSequentialGroup()
                                                                .addContainerGap()
                                                                .addComponent(employeesHourContentPanel,
                                                                                javax.swing.GroupLayout.DEFAULT_SIZE,
                                                                                javax.swing.GroupLayout.DEFAULT_SIZE,
                                                                                Short.MAX_VALUE)
                                                                .addContainerGap())
                                                .addComponent(headEmployeesHourPanel,
                                                                javax.swing.GroupLayout.DEFAULT_SIZE,
                                                                javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE));
                employeesHourPanelLayout.setVerticalGroup(
                                employeesHourPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                .addGroup(employeesHourPanelLayout.createSequentialGroup()
                                                                .addComponent(headEmployeesHourPanel,
                                                                                javax.swing.GroupLayout.PREFERRED_SIZE,
                                                                                javax.swing.GroupLayout.DEFAULT_SIZE,
                                                                                javax.swing.GroupLayout.PREFERRED_SIZE)
                                                                .addPreferredGap(
                                                                                javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                                                .addComponent(employeesHourContentPanel,
                                                                                javax.swing.GroupLayout.DEFAULT_SIZE,
                                                                                javax.swing.GroupLayout.DEFAULT_SIZE,
                                                                                Short.MAX_VALUE)
                                                                .addContainerGap()));

                javax.swing.GroupLayout playgroundLayout = new javax.swing.GroupLayout(playground);
                playground.setLayout(playgroundLayout);
                playgroundLayout.setHorizontalGroup(
                                playgroundLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                .addGroup(playgroundLayout.createSequentialGroup()
                                                                .addContainerGap()
                                                                .addGroup(playgroundLayout.createParallelGroup(
                                                                                javax.swing.GroupLayout.Alignment.LEADING)
                                                                                .addGroup(playgroundLayout
                                                                                                .createSequentialGroup()
                                                                                                .addComponent(employeesPanelDash,
                                                                                                                javax.swing.GroupLayout.PREFERRED_SIZE,
                                                                                                                javax.swing.GroupLayout.DEFAULT_SIZE,
                                                                                                                javax.swing.GroupLayout.PREFERRED_SIZE)
                                                                                                .addPreferredGap(
                                                                                                                javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                                                                                .addComponent(employeesHourPanel,
                                                                                                                javax.swing.GroupLayout.DEFAULT_SIZE,
                                                                                                                javax.swing.GroupLayout.DEFAULT_SIZE,
                                                                                                                Short.MAX_VALUE))
                                                                                .addGroup(playgroundLayout
                                                                                                .createSequentialGroup()
                                                                                                .addComponent(salesPanel,
                                                                                                                javax.swing.GroupLayout.PREFERRED_SIZE,
                                                                                                                javax.swing.GroupLayout.DEFAULT_SIZE,
                                                                                                                javax.swing.GroupLayout.PREFERRED_SIZE)
                                                                                                .addPreferredGap(
                                                                                                                javax.swing.LayoutStyle.ComponentPlacement.RELATED,
                                                                                                                12,
                                                                                                                Short.MAX_VALUE)
                                                                                                .addComponent(expensesPanel,
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
                                                                                .addComponent(salesPanel,
                                                                                                javax.swing.GroupLayout.PREFERRED_SIZE,
                                                                                                javax.swing.GroupLayout.DEFAULT_SIZE,
                                                                                                javax.swing.GroupLayout.PREFERRED_SIZE)
                                                                                .addComponent(expensesPanel,
                                                                                                javax.swing.GroupLayout.PREFERRED_SIZE,
                                                                                                javax.swing.GroupLayout.DEFAULT_SIZE,
                                                                                                javax.swing.GroupLayout.PREFERRED_SIZE))
                                                                .addGap(18, 18, 18)
                                                                .addGroup(playgroundLayout.createParallelGroup(
                                                                                javax.swing.GroupLayout.Alignment.LEADING,
                                                                                false)
                                                                                .addComponent(employeesPanelDash,
                                                                                                javax.swing.GroupLayout.DEFAULT_SIZE,
                                                                                                javax.swing.GroupLayout.DEFAULT_SIZE,
                                                                                                Short.MAX_VALUE)
                                                                                .addComponent(employeesHourPanel,
                                                                                                javax.swing.GroupLayout.DEFAULT_SIZE,
                                                                                                javax.swing.GroupLayout.DEFAULT_SIZE,
                                                                                                Short.MAX_VALUE))
                                                                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE,
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
                                // initComp2();
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
                                new mainShifts(playground, "2022-06-01", "2023-06-01", false);
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
                                new generateReport(theFrame, playground);

                                // JnaFileChooser chooser = new JnaFileChooser();
                                // chooser.setMode(JnaFileChooser.Mode.Directories);
                                // boolean action = chooser.showOpenDialog(theFrame);
                                // if (action)
                                // System.out.println(chooser.getSelectedFile());

                                // JFileChooser chooser;
                                // chooser = new JFileChooser();
                                // chooser.setCurrentDirectory(new java.io.File("."));
                                // chooser.setTitle("ola");
                                // chooser.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
                                //
                                // disable the "All files" option.
                                //
                                // chooser.setAcceptAllFileFilterUsed(false);
                                //
                                // + chooser.getCurrentDirectory());
                                // System.out.println("getSelectedFile() : "
                                // + chooser.getSelectedFile());
                                //
                                // }
                                playground.revalidate();
                                playground.repaint();
                                // initComp2();
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
                                new mainRole(playground);
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
                employeesHourTuggleButton.addMouseListener(new MouseListener() {
                        public void mouseClicked(MouseEvent e) {
                                if (employeesHourTuggleButton.getText().equals("Last Week")) {
                                        employeesHourTuggleButton.setText("Today");
                                        setGraphScheduledHours(true);
                                } else {
                                        employeesHourTuggleButton.setText("Last Week");
                                        setGraphScheduledHours(false);
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
                salesTuggleButton.addMouseListener(new MouseListener() {
                        public void mouseClicked(MouseEvent e) {
                                if (salesTuggleButton.getText().equals("Last 14 days")) {
                                        salesTuggleButton.setText("Last Week");
                                        setGraphSales(14);
                                } else {
                                        salesTuggleButton.setText("Last 14 days");
                                        setGraphSales(7);
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
                expensesTuggleButton.addMouseListener(new MouseListener() {
                        public void mouseClicked(MouseEvent e) {
                                if (expensesTuggleButton.getText().equals("Last 14 days")) {
                                        expensesTuggleButton.setText("Last Week");
                                        setGraphExpenses(14);
                                } else {
                                        expensesTuggleButton.setText("Last 14 days");
                                        setGraphExpenses(7);
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
                panel.setBackground(new Color(120, 168, 252));
        }

        private void resetColor(JPanel panel) {
                panel.setBackground(new Color(71, 120, 197));
        }

        private void setGraphSales(int goal) {
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
                dashboardsAPI dbManager = new dashboardsAPI();
                String date = "";
                for (int i = goal - 1; i > -1; i--) {

                        if (goal == 7 || i == goal - 1 || i == 0)
                                date = LocalDate.now().minus(i, ChronoUnit.DAYS)
                                                .format(DateTimeFormatter.ofPattern("dd-MM"));
                        else
                                date = Integer.toString(-i);
                        datos.setValue(dbManager.expensesSalaryDaily(i), "Expenses", date);
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
                dashboardsAPI dbAPI = new dashboardsAPI();
                for (tuple temp : dbAPI.getEmployeeCategoryPercentagesToday())
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
                dashboardsAPI dbAPI = new dashboardsAPI();
                ArrayList<Double> tempList = new ArrayList<Double>();
                for (int i = 0; i < 23; i++) {
                        for (int j = 0; j < dbAPI.getFrequencyOfShift(i, weekly); j++)
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