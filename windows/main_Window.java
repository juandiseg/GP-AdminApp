package windows;

import windows.categoryWindow.main_cWindow;
import windows.ingredientsWindow.main_iWindow;
import windows.menuWindow.main_mWindow;
import windows.productsWindow.main_productWindow;

import java.awt.event.ActionListener;
import iLayouts.GridLayoutApplyer;
import java.awt.event.ActionEvent;
import windows.providersWindow.*;
import util.abstractUpdater;
import javax.swing.*;

public class main_Window extends abstractUpdater {

    static JFrame theFrame = new JFrame("Main window");
    private JButton button1 = new JButton("Providers");
    private JButton button2 = new JButton("Ingredients");
    private JButton button3 = new JButton("Products");
    private JButton button4 = new JButton("Categories");
    private JButton button5 = new JButton("Menus");

    public main_Window() {
        super(null, new GridLayoutApplyer(theFrame, 4));
        setFrame(theFrame);
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
        theFrame.add(button5);
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
                main_productWindow ingredientsWdw = new main_productWindow(temp);
                ingredientsWdw.updateToThisMenu();
            }
        });
        button4.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                main_cWindow ingredientsWdw = new main_cWindow(temp);
                ingredientsWdw.updateToThisMenu();
            }
        });
        button5.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                main_mWindow ingredientsWdw = new main_mWindow(temp);
                ingredientsWdw.updateToThisMenu();
            }
        });
    }

}