package util.inputFormatting;

import java.awt.Color;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import javax.swing.JTextField;
import javax.swing.text.BadLocationException;
import javax.swing.text.DocumentFilter;
import javax.swing.text.AbstractDocument;

public class dateInputFormatter implements iFormatter {

    public void applyFormat(JTextField theTextField) {
        ((AbstractDocument) theTextField.getDocument()).setDocumentFilter(
                new DocumentFilter() {
                    public void remove(DocumentFilter.FilterBypass fb, int i, int i1)
                            throws BadLocationException {
                    }
                });
        theTextField.addKeyListener(new KeyListener() {
            boolean delete = false;

            public void keyTyped(KeyEvent arg0) {
                if (arg0.getKeyCode() == KeyEvent.VK_SPACE) {
                    arg0.consume();
                    return;
                } else if (delete) {
                    String newInput = moveValuesBackwards(theTextField.getText());
                    theTextField.setText(newInput);
                    arg0.consume();
                    delete = false;
                    return;
                }

                char k = arg0.getKeyChar();
                if (!(k >= '0' && k <= '9')) {
                    arg0.consume();
                    return;
                }
                String newInput = moveValuesForward(theTextField.getText(), k);
                arg0.consume();
                theTextField.setText(newInput);
                return;
            }

            public void keyReleased(KeyEvent arg0) {
            }

            public void keyPressed(KeyEvent arg0) {
                if (arg0.getKeyCode() == KeyEvent.VK_BACK_SPACE)
                    delete = true;
            }
        });
    }

    private int getFirstFreeIndex(String input) {
        if (input.charAt(0) == 'D')
            return 0;
        else if (input.charAt(1) == 'D')
            return 1;
        else if (input.charAt(3) == 'M')
            return 3;
        else if (input.charAt(4) == 'M')
            return 4;
        else if (input.charAt(6) == 'Y')
            return 6;
        else if (input.charAt(7) == 'Y')
            return 7;
        else if (input.charAt(8) == 'Y')
            return 8;
        else if (input.charAt(9) == 'Y')
            return 9;
        else
            return -1;
    }

    private String moveValuesForward(String input, char newDigit) {
        char[] tempInput = input.toCharArray();
        int index = getFirstFreeIndex(input);
        if (index != -1)
            tempInput[index] = newDigit;
        return new String(tempInput);
    }

    private String moveValuesBackwards(String input) {
        char[] tempInput = input.toCharArray();
        int lastIndex = getFirstFreeIndex(input);

        switch (lastIndex) {
            case -1:
                tempInput[9] = 'Y';
                return new String(tempInput);
            case 9:
                tempInput[8] = 'Y';
                break;
            case 8:
                tempInput[7] = 'Y';
                break;
            case 7:
                tempInput[6] = 'Y';
                break;
            case 6:
                tempInput[4] = 'M';
                break;
            case 4:
                tempInput[3] = 'M';
                break;
            case 3:
                tempInput[1] = 'D';
                break;
            case 1:
                tempInput[0] = 'D';
                break;
            case 0:
                return input;
        }
        return new String(tempInput);
    }

    public boolean isFilled(JTextField theTextField) {
        char[] tempDate = theTextField.getText().toCharArray();
        for (char temp : tempDate) {
            if (temp == 'D' || temp == 'M' || temp == 'Y')
                return false;
        }
        return true;
    }
}
