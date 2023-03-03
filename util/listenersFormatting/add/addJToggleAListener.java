package util.listenersFormatting.add;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.Color;

import javax.swing.JToggleButton;

import util.listenersFormatting.iToggleListener;

public class addJToggleAListener implements iToggleListener {

    public void applyActionListenerToggle(JToggleButton theToggleButton, String ifTrue, String ifFalse,
            boolean theBoolean) {
        theToggleButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                if (theToggleButton.getText().equals(ifTrue))
                    theToggleButton.setText(ifFalse);
                else
                    theToggleButton.setText(ifTrue);
            }
        });
    }
}
