package navigation.administration.reports_Window;

import java.awt.event.ActionListener;
import iLayouts.GridLayoutApplyer;
import util.abstractUpdater;

import java.awt.event.ActionEvent;
import javax.swing.JButton;

public class main_report_Window extends abstractUpdater {

    private JButton button1 = new JButton("Generate Sales Report");
    private JButton button2 = new JButton("Generate Expenses Report");
    private JButton button4 = new JButton("Generate General Report");
    private JButton backButton = new JButton("Back");

    public main_report_Window(abstractUpdater previousWindow) {
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
                // late_entries_sWindow tempWinw = new late_entries_sWindow(temp);
                // tempWinw.updateToThisMenu();
            }
        });
        button2.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                // late_entries_sWindow tempWinw = new late_entries_sWindow(temp);
                // tempWinw.updateToThisMenu();
            }
        });
        button4.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                // late_entries_sWindow tempWinw = new late_entries_sWindow(temp);
                // tempWinw.updateToThisMenu();
            }
        });
        button5.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                // late_entries_sWindow tempWinw = new late_entries_sWindow(temp);
                // tempWinw.updateToThisMenu();
            }
        });
        backButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                updateToPreviousMenu();
            }
        });
    }

}
