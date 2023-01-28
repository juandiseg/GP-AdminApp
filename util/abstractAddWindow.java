package util;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JLabel;

import iLayouts.placeholderLayoutApplyer;

public abstract class abstractAddWindow extends abstractUpdater {

    private JButton backButton = new JButton("Back");
    private JButton addButton = new JButton("Add Provider");

    private JLabel inputSuccesful = new JLabel("The provider has been successfully added.");
    private JLabel inputError = new JLabel("There is something wrong with the given input.");

    public abstractAddWindow(abstractUpdater previousWindow, String title) {
        super(previousWindow, new placeholderLayoutApplyer(theFrame));
        theFrame.setTitle("Add " + title);
    }

    @Override
    public void addComponents() {
        setBounds();
        addToFrame();
        setBackButton();
    }

    public abstract void setBounds();

    public abstract void addToFrame();

    public JButton getBackButton() {
        return backButton;
    }

    public JButton getAddButton() {
        return addButton;
    }

    public JLabel getInputSuccesful() {
        return inputSuccesful;
    }

    public JLabel getInputError() {
        return inputError;
    }

    public void printErrorGUI() {
        theFrame.remove(inputSuccesful);
        theFrame.add(inputError);
        theFrame.repaint();
    }

    public void printSuccessGUI() {
        theFrame.remove(inputError);
        theFrame.add(inputSuccesful);
        theFrame.repaint();
    }

    private void setBackButton() {
        backButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                updateToPreviousMenu();
            }
        });
    }

}
