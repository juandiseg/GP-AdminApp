package util.buttonFormatters;

import java.awt.event.MouseListener;
import java.awt.Color;
import java.awt.event.*;

import javax.swing.JButton;

public class deleteButtonFormatter {

    public static void formatDeleteButton(JButton deleteButton, iDeleteButton methodHolder) {
        deleteButton.setBackground(new Color(255, 102, 102));
        deleteButton.setForeground(new Color(255, 255, 255));
        deleteButton.setFont(new java.awt.Font("Segoe UI", 1, 14));
        deleteButton.setText("Delete");
        deleteButton.addMouseListener(new MouseListener() {
            public void mouseClicked(MouseEvent e) {
                if (methodHolder.thereIsError())
                    return;
                if (!methodHolder.askConfirmation())
                    return;
                methodHolder.chooseAmongOptions();
            }

            public void mousePressed(MouseEvent e) {
            }

            public void mouseReleased(MouseEvent e) {
            }

            public void mouseEntered(MouseEvent e) {
                deleteButton.setBackground(new Color(255, 255, 255));
                deleteButton.setForeground(new Color(255, 102, 102));
            }

            public void mouseExited(MouseEvent e) {
                deleteButton.setBackground(new Color(255, 102, 102));
                deleteButton.setForeground(new Color(255, 255, 255));
            }
        });
    }
}
