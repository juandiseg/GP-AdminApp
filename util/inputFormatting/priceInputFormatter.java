package util.inputFormatting;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import javax.swing.text.JTextComponent;

public class priceInputFormatter implements iFormatter {

    // This formatter restricts the user to enter numeric values up to 6 decimals
    // places. This is "####.##", so every time a key is pressed it checks whether
    // that value can be added to the selected index.

    public void applyFormat(JTextComponent theTextField) {
        theTextField.addKeyListener(new KeyListener() {
            public void keyTyped(KeyEvent arg0) {
                char k = arg0.getKeyChar();
                int dotIndex = -1;
                int indexTemp = 0;

                // Looks for a dot, and if there is one, it stores its index.
                for (char temp : theTextField.getText().toCharArray()) {
                    if (temp == '.')
                        dotIndex = indexTemp;
                    indexTemp++;
                }

                // If there is not a dot and the JTextField's length is above 6, consume the
                // pressed character.
                if (dotIndex != -1 && theTextField.getText().length() >= 7) {
                    arg0.consume();
                    return;
                }

                // If there is not a dot and the JTextField's length is above 4.
                if (dotIndex == -1 && theTextField.getText().length() >= 4) {
                    // If the user is not trying to add a dot, then consume the pressed char.
                    if (k != ',' && k != '.') {
                        arg0.consume();
                        return;
                    }
                }

                // If the pressed character is a number
                if (k >= '0' && k <= '9') {
                    // If the user tries to add more than 2 decimal places consume the character.
                    if (theTextField.getSelectionStart() >= dotIndex + 3 && dotIndex != -1)
                        arg0.consume();
                    return;
                }

                if ((k == ',' || k == '.') && dotIndex == -1) {
                    // Set a possibility of a ',' to a '.'.
                    arg0.setKeyChar('.');

                    // Divide the string into two parts: before the dot and after the dot.
                    String beforeDot = theTextField.getText().substring(0, theTextField.getSelectionStart());
                    String afterDot = theTextField.getText().substring(theTextField.getSelectionStart());

                    // If there are more than 2 numbers after the selected index, take the first 2.
                    if (afterDot.length() >= 2)
                        afterDot = afterDot.substring(0, 2);

                    // Concat the two parts back together with a dot in between.
                    afterDot = Character.toString('.').concat(afterDot);
                    String finalText;

                    // If the dot has been placed at the first index, add a 0 in front of it.
                    if (beforeDot.length() == 0)
                        finalText = Integer.toString(0).concat(afterDot);
                    else
                        finalText = beforeDot.concat(afterDot);
                    arg0.consume();
                    theTextField.setText(finalText);
                    return;
                }

                arg0.consume();

            }

            public void keyReleased(KeyEvent arg0) {
            }

            public void keyPressed(KeyEvent arg0) {
            }

        });
    }

    public boolean isFilled(JTextComponent theTextField) {
        return !theTextField.getText().isEmpty();
    }

}
