package navigation.administration;

import java.awt.event.ActionListener;
import iLayouts.GridLayoutApplyer;
import navigation.administration.employees_Window.main_employees_Window;
import navigation.administration.shifts_Window.main_sWindow;

import java.awt.event.ActionEvent;

import util.abstractUpdater;
import javax.swing.*;

public class main_adminstration_Window extends abstractUpdater {

    private JButton button1 = new JButton("Employees");
    private JButton button2 = new JButton("Shifts");
    private JButton button3 = new JButton("Reports");
    private JButton backButton = new JButton("Back");

    public main_adminstration_Window(abstractUpdater previousWindow) {
        super(previousWindow, new GridLayoutApplyer(theFrame, 4));
    }

    public void addComponents() {
        theFrame.setTitle("Administration Menu");
        theFrame.add(button1);
        theFrame.add(button2);
        theFrame.add(button3);
        theFrame.add(backButton);
    }

    public void addActionListeners() {
        abstractUpdater temp = this;
        button1.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                main_employees_Window providersWdw = new main_employees_Window(temp);
                providersWdw.updateToThisMenu();
            }
        });
        button2.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                main_sWindow ingredientsWdw = new main_sWindow(temp);
                ingredientsWdw.updateToThisMenu();
            }
        });
        button3.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                // main_productWindow ingredientsWdw = new main_productWindow(temp);
                // ingredientsWdw.updateToThisMenu();
            }
        });
        backButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                updateToPreviousMenu();
            }
        });
    }

}