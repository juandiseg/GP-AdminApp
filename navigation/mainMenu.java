package navigation;

import java.awt.event.ActionListener;
import iLayouts.GridLayoutApplyer;
import navigation.administration.main_adminstration_Window;
import navigation.food.main_food_Window;

import java.awt.event.ActionEvent;

import util.abstractUpdater;
import javax.swing.*;

public class mainMenu extends abstractUpdater {

    static JFrame theFrame = new JFrame("Main window");
    private JButton button1 = new JButton("Food");
    private JButton button2 = new JButton("Admin");

    public mainMenu() {
        super(null, new GridLayoutApplyer(theFrame, 4));
        setFrame(theFrame);
        theFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        theFrame.setSize(600, 600);
        theFrame.setVisible(true);
        theFrame.setLocationRelativeTo(null);
        updateToThisMenu();
    }

    public void addComponents() {
        theFrame.setTitle("Food Menu");
        theFrame.add(button1);
        theFrame.add(button2);
    }

    public void addActionListeners() {
        abstractUpdater temp = this;
        button1.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                main_food_Window providersWdw = new main_food_Window(temp);
                providersWdw.updateToThisMenu();
            }
        });
        button2.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                main_adminstration_Window ingredientsWdw = new main_adminstration_Window(temp);
                ingredientsWdw.updateToThisMenu();
            }
        });
    }

}