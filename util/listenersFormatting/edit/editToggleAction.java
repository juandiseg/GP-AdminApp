package util.listenersFormatting.edit;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.Color;

import javax.swing.JToggleButton;

import util.listenersFormatting.iToggleListener;

public class editToggleAction implements iToggleListener {

    public void applyActionListenerToggle(JToggleButton theToggleButton, String ifTrue, String ifFalse,
            boolean theBoolean) {

        // Remove the focus listeners previously added by this method.
        for (ActionListener temp : theToggleButton.getActionListeners()) {
            if (temp.toString().startsWith("util.listeners"))
                theToggleButton.removeActionListener(temp);
        }

        // When toggling the text of the JToggleButton if it is toggling to the
        // 'placeholder' value, set the font color to GRAY, if not, set it to BLACK.
        theToggleButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                if (theToggleButton.getText().equals(ifTrue)) {
                    if (theBoolean)
                        theToggleButton.setForeground(Color.BLACK);
                    else
                        theToggleButton.setForeground(Color.GRAY);
                    theToggleButton.setText(ifFalse);
                } else {
                    if (!theBoolean)
                        theToggleButton.setForeground(Color.BLACK);
                    else
                        theToggleButton.setForeground(Color.GRAY);
                    theToggleButton.setText(ifTrue);
                }
            }
        });
    }
}
