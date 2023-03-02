package util.inputFormatting;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import javax.swing.JTextField;

public class priceInputFormatter implements iFormatter {

    public void applyFormat(JTextField theTextField) {
        theTextField.addKeyListener(new KeyListener() {
            public void keyTyped(KeyEvent arg0) {
                char k = arg0.getKeyChar();
                int dotIndex = -1;

                int indexTemp = 0;
                for (char temp : theTextField.getText().toCharArray()) {
                    if (temp == '.')
                        dotIndex = indexTemp;
                    indexTemp++;
                }

                if (k >= '0' && k <= '9') {
                    if (theTextField.getSelectionStart() >= dotIndex + 3 && dotIndex != -1)
                        arg0.consume();
                    return;
                }

                // CHECK HERE AUTOCOMPLETE ZERO BEFORE POINT!!
                if ((k == ',' || k == '.') && dotIndex == -1) {

                    arg0.setKeyChar('.');
                    String beforeDot = theTextField.getText().substring(0, theTextField.getSelectionStart());
                    String afterDot = theTextField.getText().substring(theTextField.getSelectionStart());
                    if (afterDot.length() >= 2)
                        afterDot = afterDot.substring(0, 2);
                    afterDot = Character.toString('.').concat(afterDot);
                    String finalText;
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

    public boolean isFilled(JTextField theTextField) {
        return !theTextField.getText().isEmpty();
    }

}
