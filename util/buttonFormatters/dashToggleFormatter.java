package util.buttonFormatters;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JToggleButton;

public class dashToggleFormatter {

    public static void applyDashActionListenerToggle(JToggleButton theToggleButton, String text1, String text2,
            iDashToggleFormatter methodsHolder) {
        theToggleButton.setText(text1);
        theToggleButton.setBackground(Color.WHITE);
        theToggleButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                if (theToggleButton.getText().equals(text1)) {
                    theToggleButton.setText(text2);
                    methodsHolder.action1();
                } else {
                    theToggleButton.setText(text1);
                    methodsHolder.action2();
                }
            }
        });

    }
}
