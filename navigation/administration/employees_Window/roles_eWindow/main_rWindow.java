package navigation.administration.employees_Window.roles_eWindow;

import java.awt.event.ActionListener;
import iLayouts.GridLayoutApplyer;
import util.abstractUpdater;

import java.awt.event.ActionEvent;
import javax.swing.JButton;

public class main_rWindow extends abstractUpdater {

    private JButton button1 = new JButton("Add Role");
    private JButton button2 = new JButton("Edit Role");
    private JButton button3 = new JButton("Delete Role");
    private JButton button4 = new JButton("Check Roles");
    private JButton backButton = new JButton("Back");

    public main_rWindow(abstractUpdater previousWindow) {
        super(previousWindow, new GridLayoutApplyer(theFrame, 6));
    }

    @Override
    public void addComponents() {
        theFrame.setTitle("Roles menu");
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
                add_rWindow tempWind = new add_rWindow(temp);
                tempWind.updateToThisMenu();
            }
        });
        button2.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                edit_rWindow tempWind = new edit_rWindow(temp);
                tempWind.updateToThisMenu();
            }
        });
        button3.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                delete_rWindow tempWind = new delete_rWindow(temp);
                tempWind.updateToThisMenu();
            }
        });
        button4.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                check_rWindow tempWinw = new check_rWindow(temp);
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
