package util.listenersFormatting.edit;

import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.awt.Color;
import java.awt.Font;

import javax.swing.JTextField;

import util.inputFormatting.dateInputFormatter;
import util.listenersFormatting.booleanWrapper;
import util.listenersFormatting.iTextFieldListener;

public class editDateTFFListener implements iTextFieldListener {

    public void applyListenerTextField(JTextField theTextField, String theString,
            booleanWrapper placeholder, boolean small) {
        for (FocusListener temp : theTextField.getFocusListeners()) {
            if (temp.toString().startsWith("util.listeners"))
                theTextField.removeFocusListener(temp);
        }
        if (small)
            theTextField.setFont(new Font("Segoe UI", 0, 14)); // NOI18N
        else
            theTextField.setFont(new Font("Segoe UI", 0, 18)); // NOI18N
        theTextField.setText(theString);
        theTextField.setForeground(Color.GRAY);
        theTextField.addFocusListener(new FocusListener() {
            public void focusGained(FocusEvent e) {
                theTextField.setForeground(Color.BLACK);
                placeholder.setValue(false);
            }

            public void focusLost(FocusEvent e) {
                if (theTextField.getText().isEmpty() || theTextField.getText().equals(theString)
                        || formatIncomplete(theTextField)) {
                    theTextField.setForeground(Color.GRAY);
                    placeholder.setValue(true);
                }
            }
        });
    }

    private boolean formatIncomplete(JTextField theTextField) {
        return !new dateInputFormatter().isFilled(theTextField);
    }
}
