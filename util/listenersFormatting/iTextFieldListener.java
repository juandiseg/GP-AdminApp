package util.listenersFormatting;

import javax.swing.text.JTextComponent;

public interface iTextFieldListener {

    public void applyListenerTextField(JTextComponent theTextField, String theString, booleanWrapper placeholder,
            boolean small);

}
