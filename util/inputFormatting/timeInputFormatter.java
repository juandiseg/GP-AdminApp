package util.inputFormatting;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import javax.swing.JTextField;
import javax.swing.text.BadLocationException;
import javax.swing.text.DocumentFilter;
import javax.swing.text.AbstractDocument;

public class timeInputFormatter implements iFormatter {

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
        if (input.charAt(0) == 'H')
            return 0;
        else if (input.charAt(1) == 'H')
            return 1;
        else if (input.charAt(3) == 'M')
            return 3;
        else if (input.charAt(4) == 'M')
            return 4;
        else
            return -1;
    }

    private String moveValuesForward(String input, char newDigit) {
        char[] tempInput = input.toCharArray();
        int lastIndex = getFirstFreeIndex(input);
        if (lastIndex != -1)
            tempInput[lastIndex] = newDigit;
        return new String(tempInput);
    }

    private String moveValuesBackwards(String input) {
        char[] tempInput = input.toCharArray();
        int lastIndex = getFirstFreeIndex(input);

        switch (lastIndex) {
            case -1:
                tempInput[4] = 'M';
                return new String(tempInput);
            case 4:
                tempInput[3] = 'M';
                break;
            case 3:
                tempInput[1] = 'H';
                break;
            case 1:
                tempInput[0] = 'H';
                break;
            case 0:
                return input;
        }

        return new String(tempInput);
    }
}
