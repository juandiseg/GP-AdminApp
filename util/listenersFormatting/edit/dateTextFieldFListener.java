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
        for (FocusListener temp : theTextField.getFocusListeners()) {
            if (temp.toString().startsWith("util.listeners"))
                theTextField.removeFocusListener(temp);
        }
        theTextField.setFont(new Font("Segoe UI", 0, 18)); // NOI18N
        theTextField.setText(theString);
        theTextField.setForeground(Color.GRAY);
        theTextField.addFocusListener(new FocusListener() {
            public void focusGained(FocusEvent e) {
                if (theTextField.getText().equals(theString)) {
                    theTextField.setForeground(Color.BLACK);
                    placeholder.setValue(false);
                }
            }

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
