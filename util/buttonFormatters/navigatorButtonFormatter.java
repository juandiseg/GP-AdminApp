package util.buttonFormatters;

import java.awt.event.MouseListener;
import java.awt.Color;
import java.awt.Font;

import java.awt.event.*;

import javax.swing.JButton;
import javax.swing.JPanel;

public class navigatorButtonFormatter {

    public static void formatNavigationButton(JButton navButton, iNavigatorButton methodHolder, JPanel panelToUpdate,
            boolean isBackButton, String title) {
        if (isBackButton) {
            navButton.setFont(new Font("Segoe UI", 1, 14));
            navButton.setBackground(new Color(71, 120, 197));
            navButton.setForeground(new Color(255, 255, 255));
        } else {
            navButton.setFont(new Font("Segoe UI", 1, 14)); // NOI18N
            navButton.setBackground(new Color(23, 35, 51));
            navButton.setForeground(new Color(255, 255, 255));
        }
        navButton.setText(title);
        navButton.addMouseListener(new MouseListener() {
            public void mouseClicked(MouseEvent e) {
                panelToUpdate.removeAll();
                methodHolder.createNewNavigator();
                panelToUpdate.revalidate();
                panelToUpdate.repaint();
            }

            public void mousePressed(MouseEvent e) {
            }

            public void mouseReleased(MouseEvent e) {
            }

            public void mouseEntered(MouseEvent e) {
                if (isBackButton)
                    navButton.setBackground(new Color(23, 35, 51));
                else {
                    navButton.setBackground(new Color(120, 168, 252));
                    navButton.setForeground(new Color(0, 0, 0));
                }
            }

            public void mouseExited(MouseEvent e) {
                if (isBackButton)
                    navButton.setBackground(new Color(71, 120, 197));
                else {
                    navButton.setBackground(new Color(23, 35, 51));
                    navButton.setForeground(new Color(255, 255, 255));
                }
            }
        });
    }
}
