package util.inputFormatting;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import javax.swing.JTextField;

public class priceInputFormatter implements iFormatter {

    public void applyFormat(JTextField theTextField) {
        theTextField.addKeyListener(new KeyListener() {
            public void keyTyped(KeyEvent arg0) {
                char k = arg0.getKeyChar();
                boolean isNumber = false;
                int dotIndex = -1;

                int indexTemp = 0;
                for (char temp : theTextField.getText().toCharArray()) {
                    if (temp == '.')
                        dotIndex = indexTemp;
                    indexTemp++;
                }

                if (k >= '0' && k <= '9')
                    isNumber = true;

                if (dotIndex != -1 && theTextField.getSelectionStart() > dotIndex
                        && theTextField.getText().length() > (dotIndex + 2)) {
                    arg0.consume();
                    return;
                }

                // CHECK HERE AUTOCOMPLETE ZERO BEFORE POINT!!
                if ((k == ',' || k == '.') && dotIndex == -1) {
                    arg0.setKeyChar('.');
                    if (theTextField.getSelectionStart() == 0) {
                        theTextField.setText(Integer.toString(0).concat("." + theTextField.getText().substring(0, 2)));
                        arg0.consume();
                    }
                    return;
                }

                if (!isNumber)
                    arg0.consume();

            }

            public void keyReleased(KeyEvent arg0) {
            }

            public void keyPressed(KeyEvent arg0) {
            }
        });
    }

}
