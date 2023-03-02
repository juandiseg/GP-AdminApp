package util.inputFormatting;

import javax.swing.JTextField;

public interface iFormatter {

    public void applyFormat(JTextField theTextField);

    public boolean isFilled(JTextField theTextField);
}
