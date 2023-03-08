package util.inputFormatting;

import javax.swing.text.JTextComponent;

public interface iFormatter {

    public void applyFormat(JTextComponent theTextField);

    public boolean isFilled(JTextComponent theTextField);
}
