import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.GridLayout;
import javax.swing.JButton;

import iLayouts.GridLayoutApplyer;
import iLayouts.iLayout;

public class ingrWindow extends updateGUI {

    ingrWindow(updateGUI prevWindow, iLayout layoutApplyer) {
        super(prevWindow, layoutApplyer);
    }

    public void addComponents() {
        theFrame.setTitle("new main menu 2");
        addToButtonList(new JButton("GO BACK"));
        addToButtonList(new JButton("Meals"));
        addToButtonList(new JButton("Drinks"));
        addToButtonList(new JButton("No sé"));
        addToButtonList(new JButton("No sé2"));
        for (JButton temp : getButtonList())
            theFrame.add(temp);
    }

    public void addActionListeners() {
        updateGUI temp = this;
        getButtonList().get(0).addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                updateToPreviousMenu();
            }
        });
        getButtonList().get(1).addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                mealsWindow mealsWdw = new mealsWindow(temp, new GridLayoutApplyer(theFrame, 5));
                mealsWdw.updateToThisMenu();
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