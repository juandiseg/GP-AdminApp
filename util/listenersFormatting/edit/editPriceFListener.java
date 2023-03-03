package util.listenersFormatting.edit;

import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.awt.Color;

import javax.swing.JTextField;

import util.listenersFormatting.booleanWrapper;
import util.listenersFormatting.iTextFieldListener;

public class editPriceFListener implements iTextFieldListener {

    public void applyListenerTextField(JTextField theTextField, String theString, booleanWrapper placeholder) {
        for (FocusListener temp : theTextField.getFocusListeners()) {
            if (temp.toString().startsWith("util.listeners"))
                theTextField.removeFocusListener(temp);
        }
        theTextField.addFocusListener(new FocusListener() {
            public void focusGained(FocusEvent e) {
                theTextField.setForeground(Color.BLACK);
                placeholder.setValue(false);
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
