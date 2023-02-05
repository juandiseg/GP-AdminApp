package navigation.food;

import java.awt.event.ActionListener;
import iLayouts.GridLayoutApplyer;
import navigation.food.categoryWindow.main_cWindow;
import navigation.food.ingredientsWindow.main_iWindow;
import navigation.food.menuWindow.main_mWindow;
import navigation.food.productsWindow.main_productWindow;
import navigation.food.providersWindow.*;

import java.awt.event.ActionEvent;

import util.abstractUpdater;
import javax.swing.*;

public class main_food_Window extends abstractUpdater {

    private JButton button1 = new JButton("Providers");
    private JButton button2 = new JButton("Ingredients");
    private JButton button3 = new JButton("Products");
    private JButton button4 = new JButton("Categories");
    private JButton button5 = new JButton("Menus");
    private JButton backButton = new JButton("Back");

    public main_food_Window(abstractUpdater previousWindow) {
        super(previousWindow, new GridLayoutApplyer(theFrame, 4));
    }

    public void addComponents() {
        theFrame.setTitle("Food Menu");
        theFrame.add(button1);
        theFrame.add(button2);
        theFrame.add(button3);
        theFrame.add(button4);
        theFrame.add(button5);
        theFrame.add(backButton);
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
        backButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                updateToPreviousMenu();
            }
        });
    }

}