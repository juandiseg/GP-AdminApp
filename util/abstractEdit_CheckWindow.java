package util;

import iLayouts.placeholderLayoutApplyer;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.table.*;

public abstract class abstractEdit_CheckWindow extends abstractUpdater {

    private boolean edit;
    private JButton backButton = new JButton("Back");
    private JLabel summaryTXT;
    private String title;
    protected DefaultTableModel model;
    protected JTable myTable;

    public abstractEdit_CheckWindow(abstractUpdater previousWindow, String title, boolean edit) {
        super(previousWindow, new placeholderLayoutApplyer(theFrame));
        this.title = title;
        this.edit = edit;
        summaryTXT = new JLabel("Summary of current " + title + ":");
    }

    public void addComponents() {
        setTitle(title);
        setTable();
        setBounds();
        addToFrame();
        setBackButton();
    }

    private void setTitle(String name) {
        if (edit)
            theFrame.setTitle("Choose " + name + " to be edited");
        else
            theFrame.setTitle("Check " + name + "s");
    }

    private void setTable() {
        addRowsToModel();
        adjustTable();
    }

    public abstract void addRowsToModel();

    public abstract void adjustTable();

    public abstract void setBounds();

    public abstract void addToFrame();

    private void setBackButton() {
        backButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                updateToPreviousMenu();
            }
        });
    }

    public JLabel getSummaryTXT() {
        return summaryTXT;
    }

    public JButton getBackButton() {
        return backButton;
    }

}
