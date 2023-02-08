package navigation.administration.shifts_Window;

import java.awt.event.ActionListener;
import iLayouts.GridLayoutApplyer;
import util.abstractUpdater;

import java.awt.event.ActionEvent;
import javax.swing.JButton;

public class main_sWindow extends abstractUpdater {

    private JButton button1 = new JButton("Add Shifts");
    private JButton button2 = new JButton("Edit Shift");
    private JButton button4 = new JButton("Check Shifts");
    private JButton button5 = new JButton("Check Late entries");
    private JButton backButton = new JButton("Back");

    public main_sWindow(abstractUpdater previousWindow) {
        super(previousWindow, new GridLayoutApplyer(theFrame, 6));
    }

    @Override
    public void addComponents() {
        theFrame.setTitle("Employees menu");
        theFrame.add(button1);
        theFrame.add(button2);
        theFrame.add(button4);
        theFrame.add(button5);
        theFrame.add(backButton);
    }

    @Override
    public void addActionListeners() {
        abstractUpdater temp = this;
        button1.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                add_sWindow tempWind = new add_sWindow(temp);
                tempWind.updateToThisMenu();
            }
        });
        button2.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                edit_sWindow tempWind = new edit_sWindow(temp);
                tempWind.updateToThisMenu();
            }
        });
        button4.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                check_sWindow tempWinw = new check_sWindow(temp);
                tempWinw.updateToThisMenu();
            }
        });
        button5.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                late_entries_sWindow tempWinw = new late_entries_sWindow(temp);
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
