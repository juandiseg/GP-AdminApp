package util.listenersFormatting.edit;

import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.awt.Color;
import java.awt.Font;

import javax.swing.text.JTextComponent;

import util.listenersFormatting.booleanWrapper;
import util.listenersFormatting.iTextFieldListener;

public class dateTextFieldFListener implements iTextFieldListener {

    public void applyListenerTextField(JTextComponent theTextField, String theString,
            booleanWrapper placeholder, boolean small) {

        // Remove the focus listeners previously added by this method.
        for (FocusListener temp : theTextField.getFocusListeners()) {
            if (temp.toString().startsWith("util.listeners"))
                theTextField.removeFocusListener(temp);
        }

        // Apply format
        theTextField.setFont(new Font("Segoe UI", 0, 18)); // NOI18N
        theTextField.setText(theString);
        theTextField.setForeground(Color.GRAY);
        theTextField.addFocusListener(new FocusListener() {

            // If focus is gained, allow the user to edit the placeholder and change font to
            // black.
            public void focusGained(FocusEvent e) {
                if (theTextField.getText().equals(theString)) {
                    theTextField.setForeground(Color.BLACK);
                    placeholder.setValue(false);
                }
            }

            // If focus is lost and the field is left empty or the value is the same
            // as the placeholder's change the font to gray and add the placeholder.
            public void focusLost(FocusEvent e) {
                if (theTextField.getText().isEmpty() || theTextField.getText().equals(theString)) {
                    theTextField.setForeground(Color.GRAY);
                    theTextField.setText(theString);
                    placeholder.setValue(true);
                }
            }
        });
    }
}
