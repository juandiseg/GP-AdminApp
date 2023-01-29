package windows;

import windows.ingredientsWindow.main_iWindow;
import java.awt.event.ActionListener;
import iLayouts.GridLayoutApplyer;
import java.awt.event.ActionEvent;
import windows.providersWindow.*;
import util.abstractUpdater;
import util.managerDB;
import javax.swing.*;

public class main_Window extends abstractUpdater {

    static JFrame theFrame = new JFrame("Main window");
    private JButton button1 = new JButton("Providers");
    private JButton button2 = new JButton("Ingredients");
    private JButton button3 = new JButton("Products");
    private JButton button4 = new JButton("...");

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
        theFrame.add(button1);
        theFrame.add(button2);
        theFrame.add(button3);
        theFrame.add(button4);
    }

    public void addActionListeners() {
        abstractUpdater temp = this;
        button1.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                main_pWindow providersWdw = new main_pWindow(temp);
                providersWdw.updateToThisMenu();
            }
        });
        button2.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                main_iWindow ingredientsWdw = new main_iWindow(temp);
                ingredientsWdw.updateToThisMenu();
            }
        });
        button3.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
            }
        });
        button4.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
            }
        });
    }

}