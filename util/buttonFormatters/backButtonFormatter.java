package util.buttonFormatters;

import java.awt.event.MouseListener;
import java.awt.Color;
import java.awt.Font;

import java.awt.event.*;

import javax.swing.JButton;
import javax.swing.JPanel;

public class backButtonFormatter {

    public static void formatBackButton(JButton backButton, iBackButton methodHolder, JPanel panelToUpdate) {
        backButton.setFont(new Font("Segoe UI", 1, 14));
        backButton.setBackground(new Color(71, 120, 197));
        backButton.setForeground(new Color(255, 255, 255));
        backButton.setText("Back");
        backButton.addMouseListener(new MouseListener() {
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
                backButton.setBackground(new Color(23, 35, 51));
            }

            public void mouseExited(MouseEvent e) {
                backButton.setBackground(new Color(71, 120, 197));
            }
        });
    }
}
