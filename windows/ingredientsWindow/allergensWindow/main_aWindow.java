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

    public main_aWindow(abstractUpdater previousWindow) {
        super(previousWindow, new GridLayoutApplyer(theFrame, 6));
    }

    @Override
    public void addComponents() {
        theFrame.setTitle("Ingredients menu");
        addToButtonList(new JButton("Add Alergen"));
        addToButtonList(new JButton("Edit Alergens"));
        addToButtonList(new JButton("Delete Alergen"));
        addToButtonList(new JButton("Check Alergens"));
        addToButtonList(new JButton("Back"));
        for (JButton temp : getButtonList())
            theFrame.add(temp);
    }

    @Override
    public void addActionListeners() {
        abstractUpdater temp = this;
        getButtonList().get(0).addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                add_aWindow tempWinw = new add_aWindow(temp);
                tempWinw.updateToThisMenu();
            }
        });
        getButtonList().get(1).addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                edit_aWindow tempWinw = new edit_aWindow(temp);
                tempWinw.updateToThisMenu();
            }
        });
        getButtonList().get(2).addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
            }
        });
        getButtonList().get(3).addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
            }
        });
        getButtonList().get(4).addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                updateToPreviousMenu();
            }
        });
    }
}