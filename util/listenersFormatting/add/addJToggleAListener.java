package util.listenersFormatting.add;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JToggleButton;

import util.listenersFormatting.iToggleListener;

public class addJToggleAListener implements iToggleListener {

    public void applyActionListenerToggle(JToggleButton theToggleButton, String ifTrue, String ifFalse,
            boolean theBoolean) {

        // Change the text of the JToggleButton every click.
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
