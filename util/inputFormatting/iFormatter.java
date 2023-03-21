package util.inputFormatting;

import javax.swing.text.JTextComponent;

public interface iFormatter {

    // Apply the format to the given JTextField
    public void applyFormat(JTextComponent theTextField);

    // Check whether the given JTextField's input is filled according to its format.
    public boolean isFilled(JTextComponent theTextField);
}
