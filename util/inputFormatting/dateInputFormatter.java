package util.inputFormatting;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import javax.swing.JTextField;

import org.apache.poi.ss.formula.atp.Switch;

public class dateInputFormatter implements iFormatter {

    public void applyFormat(JTextField theTextField) {
        theTextField.addKeyListener(new KeyListener() {
            boolean backSpace = false;
            boolean space = false;

            public void keyTyped(KeyEvent arg0) {
                if (!space && !backSpace) {
                    char k = arg0.getKeyChar();
                    if (!(k >= '0' && k <= '9'))
                        arg0.consume();
                    int indx = getLastTypedIndex(theTextField.getText());
                    String newInput = moveValues(theTextField.getText(), k, indx);
                    arg0.consume();
                    theTextField.setText(newInput);
                    return;
                } else if (backSpace) {
                    System.out.println("here");
                    arg0 = null;
                    backSpace = false;
                } else {
                    arg0.consume();
                    space = false;
                }
            }

            public void keyReleased(KeyEvent arg0) {
            }

            public void keyPressed(KeyEvent arg0) {
                switch (arg0.getKeyCode()) {
                    case KeyEvent.VK_BACK_SPACE:
                        backSpace = true;
                        return;
                    case KeyEvent.VK_SPACE:
                        space = true;
                        return;
                }

            }
        });
    }

    private int getLastTypedIndex(String input) {
        if (input.substring(9, 10).equals("Y"))
            return 9;
        else if (input.substring(8, 9).equals("Y"))
            return 8;
        else if (input.substring(7, 8).equals("Y"))
            return 7;
        else if (input.substring(6, 7).equals("Y"))
            return 6;
        else if (input.substring(4, 5).equals("M"))
            return 4;
        else if (input.substring(3, 4).equals("M"))
            return 3;
        else if (input.substring(1, 2).equals("D"))
            return 1;
        else if (input.substring(0, 1).equals("D"))
            return 0;
        else
            return -1;
    }

    private String moveValues(String input, char newDigit, int lastIndex) {
        char[] tempInput = input.toCharArray();
        if (lastIndex == -1) {
            return input;
        } else {

            if (lastIndex <= 0)
                tempInput[0] = tempInput[1];
            if (lastIndex <= 1)
                tempInput[1] = tempInput[3];

            if (lastIndex <= 3)
                tempInput[3] = tempInput[4];
            if (lastIndex <= 4)
                tempInput[4] = tempInput[6];

            if (lastIndex <= 6)
                tempInput[6] = tempInput[7];
            if (lastIndex <= 7)
                tempInput[7] = tempInput[8];
            if (lastIndex <= 8)
                tempInput[8] = tempInput[9];
        }
        tempInput[9] = newDigit;
        System.out.println(new String(tempInput));
        return new String(tempInput);
    }
}
