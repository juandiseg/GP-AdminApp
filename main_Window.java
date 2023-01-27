import javax.swing.*;
import java.sql.*;
import iLayouts.GridLayoutApplyer;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class main_Window extends abstractUpdater {

    static JFrame theFrame = new JFrame("Main window");

    main_Window() {
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
        addToButtonList(new JButton("Ingredients"));
        addToButtonList(new JButton("Meals"));
        addToButtonList(new JButton("Drinks"));
        addToButtonList(new JButton("Your mum"));
        for (JButton temp : getButtonList())
            theFrame.add(temp);
    }

    public void addActionListeners() {
        abstractUpdater temp = this;
        getButtonList().get(0).addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                ingredients_Window ingredientesWdw = new ingredients_Window(temp, new GridLayoutApplyer(theFrame, 5));
                ingredientesWdw.updateToThisMenu();
            }
        });
        getButtonList().get(1).addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
            }
        });
        getButtonList().get(2).addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
            }
        });
        getButtonList().get(3).addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
            }
        });
    }

}