package navigation;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

import navigation.administration.*;
import navigation.food.*;

public class mainMenu extends JFrame {

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

        public mainMenu() {
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
                playground.setBackground(new java.awt.Color(255, 255, 255));

                javax.swing.GroupLayout playgroundLayout = new javax.swing.GroupLayout(playground);
                playground.setLayout(playgroundLayout);
                playgroundLayout.setHorizontalGroup(
                                playgroundLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                .addGap(0, 870, Short.MAX_VALUE));
                playgroundLayout.setVerticalGroup(
                                playgroundLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                .addGap(0, 630, Short.MAX_VALUE));

                getContentPane().add(playground, new org.netbeans.lib.awtextra.AbsoluteConstraints(400, 90, 870, 630));
                pack();
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
                administrationLabel.addMouseListener(new MouseListener() {
                        boolean clicked = false;

                        public void mouseClicked(MouseEvent e) {
                                setColor(administrationPanel);
                                resetColor(homePanel);
                                resetColor(foodPanel);
                                clicked = true;
                                playground.removeAll();
                                leftAuxPanel.removeAll();
                                new mainAdmin(leftAuxPanel, playground, jPanel4, date);
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
        }

        private void setColor(JPanel panel) {
                panel.setBackground(new Color(41, 57, 80));
        }

        private void resetColor(JPanel panel) {
                panel.setBackground(new Color(23, 35, 51));
        }
}
