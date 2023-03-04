package util.buttonFormatters;

import java.awt.event.MouseListener;
import java.awt.Color;
import java.awt.Font;
import java.awt.event.*;

import javax.swing.JButton;

public class selectionButtonFormatter {

    public static void formatSelectionButton(JButton selectionButton, iSelectionButton methodHolder, boolean select) {
        String title = "Unselect";
        if (select)
            title = "Select";
        selectionButton.setText(title);
        selectionButton.setBackground(new Color(23, 35, 51));
        selectionButton.setForeground(new Color(255, 255, 255));
        selectionButton.addMouseListener(new MouseListener() {
            public void mouseClicked(MouseEvent e) {
                methodHolder.doSelection();
            }

            public void mousePressed(MouseEvent e) {
            }

            public void mouseReleased(MouseEvent e) {
            }

            public void mouseEntered(MouseEvent e) {
                selectionButton.setBackground(new Color(255, 255, 255));
                selectionButton.setForeground(new Color(23, 35, 51));
            }

            public void mouseExited(MouseEvent e) {
                selectionButton.setBackground(new Color(23, 35, 51));
                selectionButton.setForeground(new Color(255, 255, 255));
            }
        });
    }
}
