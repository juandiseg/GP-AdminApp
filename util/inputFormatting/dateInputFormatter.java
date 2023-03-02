package util.inputFormatting;

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

    private String moveValuesForward(String input, char newDigit) {
        char[] tempInput = input.toCharArray();
        int lastIndex = getLastTypedIndex(input);
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
        return new String(tempInput);
    }

    private String moveValuesBackwards(String input) {
        char[] tempInput = input.toCharArray();
        int lastIndex = getLastTypedIndex(input);

        if (lastIndex == -1) {
            tempInput[0] = 'D';
            return new String(tempInput);
        }

        if (lastIndex <= 9)
            tempInput[9] = tempInput[8];
        if (lastIndex <= 8)
            tempInput[8] = tempInput[7];
        if (lastIndex <= 7)
            tempInput[7] = tempInput[6];

        if (lastIndex <= 6) {
            if (tempInput[4] != 'M')
                tempInput[6] = tempInput[4];
            else
                tempInput[6] = 'Y';
        }

        if (lastIndex <= 4)
            tempInput[4] = tempInput[3];

        if (lastIndex <= 3) {
            if (tempInput[1] != 'D')
                tempInput[3] = tempInput[1];
            else
                tempInput[3] = 'M';
        }
        if (lastIndex <= 1)
            tempInput[1] = tempInput[0];
        tempInput[0] = 'D';
        return new String(tempInput);
    }
}
