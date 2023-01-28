package windows;

import javax.swing.*;
import java.sql.*;
import iLayouts.GridLayoutApplyer;
import util.abstractUpdater;
import util.managerDB;
import windows.ingredientsWindow.main_iWindow;
import windows.providersWindow.*;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class main_Window extends abstractUpdater {

    static JFrame theFrame = new JFrame("Main window");

    public main_Window() {
        super(null, new GridLayoutApplyer(theFrame, 4));
        setFrame(theFrame);
        setManagerDB(new managerDB());
        theFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        theFrame.setSize(600, 600);
        theFrame.setVisible(true);
        theFrame.setLocationRelativeTo(null);
        updateToThisMenu();
    }

    public void addComponents() {
        theFrame.setTitle("new main menu uno.");
        addToButtonList(new JButton("Providers"));
        addToButtonList(new JButton("Ingredients"));
        addToButtonList(new JButton("Drinks"));
        addToButtonList(new JButton("Your mum"));
        for (JButton temp : getButtonList())
            theFrame.add(temp);
    }

    public void addActionListeners() {
        abstractUpdater temp = this;
        getButtonList().get(0).addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                main_pWindow providersWdw = new main_pWindow(temp);
                providersWdw.updateToThisMenu();
            }
        });
        getButtonList().get(1).addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                main_iWindow ingredientsWdw = new main_iWindow(temp);
                ingredientsWdw.updateToThisMenu();
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
    }

}