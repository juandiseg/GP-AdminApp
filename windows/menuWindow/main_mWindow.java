package windows.menuWindow;

import java.awt.event.ActionListener;
import iLayouts.GridLayoutApplyer;
import util.abstractUpdater;

import java.awt.event.ActionEvent;
import javax.swing.JButton;

public class main_mWindow extends abstractUpdater {

    private JButton button1 = new JButton("Add Menu");
    private JButton button2 = new JButton("Edit Menu");
    private JButton button3 = new JButton("Delete Menu");
    private JButton button4 = new JButton("Check Menus");
    private JButton backButton = new JButton("Back");

    public main_mWindow(abstractUpdater previousWindow) {
        super(previousWindow, new GridLayoutApplyer(theFrame, 6));
    }

    @Override
    public void addComponents() {
        theFrame.setTitle("Menus menu");
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
                add_mWindow tempWind = new add_mWindow(temp);
                tempWind.updateToThisMenu();
            }
        });
        button2.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                edit_mWindow tempWind = new edit_mWindow(temp);
                tempWind.updateToThisMenu();
            }
        });
        button3.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                delete_mWindow tempWinw = new delete_mWindow(temp);
                tempWinw.updateToThisMenu();
            }
        });
        button4.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                check_mWindow tempWinw = new check_mWindow(temp);
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
