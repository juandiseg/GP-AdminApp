package util.buttonFormatters;

import java.awt.event.MouseListener;
import java.awt.Color;
import java.awt.Font;

import java.awt.event.*;

import javax.swing.JButton;

public class addButtonFormatter {

    public static void formatAddButton(JButton addButton, iAddButton methodHolder) {
        addButton.setBackground(new Color(255, 255, 255));
        addButton.setForeground(new Color(23, 35, 51));
        addButton.setFont(new Font("Segoe UI", 1, 14));
        addButton.addMouseListener(new MouseListener() {
            public void mouseClicked(MouseEvent e) {
                if (methodHolder.valuesArePlaceholders())
                    return;
                if (methodHolder.areInputsInvalid())
                    return;
                if (!methodHolder.addFoodComponent())
                    return;
                methodHolder.extraSteps();
            }

            public void mousePressed(MouseEvent e) {
            }

            public void mouseReleased(MouseEvent e) {
            }

            public void mouseEntered(MouseEvent e) {
                addButton.setBackground(new Color(23, 35, 51));
                addButton.setForeground(new Color(255, 255, 255));
            }

            public void mouseExited(MouseEvent e) {
                addButton.setBackground(new Color(255, 255, 255));
                addButton.setForeground(new Color(23, 35, 51));
            }
        });
    }
}
