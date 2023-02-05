package navigation.administration.employees_Window;

import java.awt.event.ActionListener;
import iLayouts.GridLayoutApplyer;
import navigation.administration.employees_Window.employees_eWindow.main_eWindow;
import navigation.administration.employees_Window.roles_eWindow.main_rWindow;
import util.abstractUpdater;

import java.awt.event.ActionEvent;
import javax.swing.JButton;

public class main_employees_Window extends abstractUpdater {

    private JButton button1 = new JButton("Roles");
    private JButton button2 = new JButton("Employees");
    private JButton backButton = new JButton("Back");

    public main_employees_Window(abstractUpdater previousWindow) {
        super(previousWindow, new GridLayoutApplyer(theFrame, 6));
    }

    @Override
    public void addComponents() {
        theFrame.setTitle("Employees Menu");
        theFrame.add(button1);
        theFrame.add(button2);
        theFrame.add(backButton);
    }

    @Override
    public void addActionListeners() {
        abstractUpdater temp = this;
        button1.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                main_rWindow tempWind = new main_rWindow(temp);
                tempWind.updateToThisMenu();
            }
        });
        button2.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                main_eWindow tempWind = new main_eWindow(temp);
                tempWind.updateToThisMenu();
            }
        });
        backButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                updateToPreviousMenu();
            }
        });
    }

}
