package util.buttonFormatters;

import java.awt.Color;
import java.awt.event.*;

import javax.swing.JButton;

public class auxButtonFormatter {

    public static void formatAuxButton(JButton auxButton, iAuxButton actionHolder, String text) {
        auxButton.setBackground(new Color(120, 168, 252));
        auxButton.setForeground(new Color(0, 0, 0));
        auxButton.setText(text);
        auxButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                actionHolder.action();
            }
        });
    }
}
