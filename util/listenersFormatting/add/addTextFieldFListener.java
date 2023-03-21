package util.listenersFormatting.add;

import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.awt.Color;
import java.awt.Font;

import javax.swing.text.JTextComponent;

import util.listenersFormatting.booleanWrapper;
import util.listenersFormatting.iTextFieldListener;

public class addTextFieldFListener implements iTextFieldListener {

    public void applyListenerTextField(JTextComponent theTextField, String theString, booleanWrapper placeholder,
            boolean small) {

        // Apply format
        theTextField.setFont(new Font("Segoe UI", 0, 18));
        theTextField.setForeground(Color.GRAY);
        theTextField.setText(theString);

        theTextField.addFocusListener(new FocusListener() {

            // If focus is gained, remove placeholders.
            public void focusGained(FocusEvent e) {
                if (theTextField.getText().equals(theString)) {
                    theTextField.setText("");
                    theTextField.setForeground(Color.BLACK);
                    placeholder.setValue(false);
                }
            }

            // If focus is lost, set placeholders.
            public void focusLost(FocusEvent e) {
                if (theTextField.getText().isEmpty()) {
                    theTextField.setForeground(Color.GRAY);
                    theTextField.setText(theString);
                    placeholder.setValue(true);
                }
            }
        });
    }

}
