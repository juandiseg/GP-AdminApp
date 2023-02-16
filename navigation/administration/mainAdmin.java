package navigation.administration;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.event.MouseListener;

import java.awt.event.MouseEvent;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

import navigation.administration.employeeSection.main_employeesWindow;
import navigation.administration.roleSection.mainRole;
import navigation.administration.shifts_Window.mainShifts;

public class mainAdmin {

        private JPanel employeesPanel = new JPanel();
        private JPanel employeesAux = new JPanel();
        private JLabel employeesLabel = new JLabel();

        private JPanel rolesPanel = new JPanel();
        private JPanel rolesAux = new JPanel();
        private JLabel rolesLabel = new JLabel();

        private JPanel shiftsPanel = new JPanel();
        private JPanel shiftsAux = new JPanel();
        private JLabel shiftsLabel = new JLabel();

        private JPanel reportsPanel = new JPanel();
        private JPanel reportsAux = new JPanel();
        private JLabel reportsLabel = new JLabel();

        private JPanel topLeftAux = new JPanel();

        public mainAdmin(JPanel leftAuxPanel, JPanel playground, JPanel jPanel4,
                        JLabel date) {
                initComp2(leftAuxPanel, jPanel4, date);
                initComp3(playground);
                addActionListeners(playground);
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

        private void initComp3(JPanel playground) {
                playground.setBackground(new java.awt.Color(255, 255, 255));
                javax.swing.GroupLayout playgroundLayout = new javax.swing.GroupLayout(playground);
                playground.setLayout(playgroundLayout);
                playgroundLayout.setHorizontalGroup(
                                playgroundLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                .addGap(0, 870, Short.MAX_VALUE));
                playgroundLayout.setVerticalGroup(
                                playgroundLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                .addGap(0, 630, Short.MAX_VALUE));
        }

        private void addActionListeners(JPanel playground) {
                employeesPanel.addMouseListener(new MouseListener() {
                        boolean clicked = false;

                        public void mouseClicked(MouseEvent e) {
                                setColor(employeesPanel);
                                resetColor(shiftsPanel);
                                resetColor(reportsPanel);
                                resetColor(rolesPanel);
                                clicked = true;
                                playground.removeAll();
                                new main_employeesWindow(playground);
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
        }

        private void setColor(JPanel panel) {
                panel.setBackground(new Color(120, 168, 252));
        }

        private void resetColor(JPanel panel) {
                panel.setBackground(new Color(71, 120, 197));
        }
}