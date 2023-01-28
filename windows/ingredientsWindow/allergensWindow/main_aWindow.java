package windows.ingredientsWindow.allergensWindow;

import javax.swing.*;
import java.sql.*;
import iLayouts.GridLayoutApplyer;
import iLayouts.iLayout;
import iLayouts.placeholderLayoutApplyer;
import util.abstractUpdater;
import util.managerDB;
import windows.ingredientsWindow.main_iWindow;
import windows.providersWindow.*;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class main_aWindow extends abstractUpdater {

    private JButton button1 = new JButton("Add Allergen");
    private JButton button2 = new JButton("Edit Allergen");
    private JButton button3 = new JButton("Delete Allergen");
    private JButton button4 = new JButton("Check Allergen");
    private JButton backButton = new JButton("Back");

    public main_aWindow(abstractUpdater previousWindow) {
        super(previousWindow, new GridLayoutApplyer(theFrame, 6));
    }

    @Override
    public void addComponents() {
        theFrame.setTitle("Ingredients menu");
        theFrame.add(button1);
        theFrame.add(button2);
        theFrame.add(button3);
        theFrame.add(button4);
        theFrame.add(backButton);
    }

    @Override
    public void addActionListeners() {
        abstractUpdater temp = this;
        button1.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                add_aWindow tempWinw = new add_aWindow(temp, "Allergen");
                tempWinw.updateToThisMenu();
            }
        });
        button2.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                edit_aWindow tempWinw = new edit_aWindow(temp, "Allergen");
                tempWinw.updateToThisMenu();
            }
        });
        button3.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
            }
        });
        button4.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                check_aWindow tempWinw = new check_aWindow(temp, "Allergen");
                tempWinw.updateToThisMenu();
            }
        });
        backButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                updateToPreviousMenu();
            }
        });
    }
}