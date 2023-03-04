package util.buttonFormatters;

import java.awt.event.MouseListener;
import java.awt.Color;
import java.awt.Font;
import java.awt.event.*;

import javax.swing.JButton;

public class editButtonFormatter {

    public static void formatEditButton(JButton editButton, iEditButton methodHolder) {
        editButton.setBackground(new Color(255, 255, 255));
        editButton.setForeground(new Color(23, 35, 51));
        editButton.setFont(new Font("Segoe UI", 1, 14));

        for (MouseListener temp : editButton.getMouseListeners()) {
            if (temp.toString().startsWith("util.buttonFormatters"))
                editButton.removeMouseListener(temp);
        }
        editButton.addMouseListener(new MouseListener() {
            public void mouseClicked(MouseEvent e) {
                if (methodHolder.valuesArePlaceholders())
                    return;
                if (methodHolder.areInputsInvalid())
                    return;
                methodHolder.editFoodComponent();
                methodHolder.updatePlaceholders();
            }

            public void mousePressed(MouseEvent e) {
            }

            public void mouseReleased(MouseEvent e) {
            }

            public void mouseEntered(MouseEvent e) {
                editButton.setBackground(new Color(23, 35, 51));
                editButton.setForeground(new Color(255, 255, 255));
            }

            public void mouseExited(MouseEvent e) {
                editButton.setBackground(new Color(255, 255, 255));
                editButton.setForeground(new Color(23, 35, 51));
            }
        });
    }
}
