import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

import javax.swing.JButton;

import iLayouts.GridLayoutApplyer;
import iLayouts.iLayout;

public class meals_Window extends abstractUpdater {

    meals_Window(abstractUpdater prevWindow, iLayout layoutApplyer) {
        super(prevWindow, layoutApplyer);
    }

    @Override
    public void addComponents() {
        theFrame.setTitle("new main menu tres");
        addToButtonList(new JButton("GO BACK"));
        addToButtonList(new JButton("Meaasdals"));
        addToButtonList(new JButton("Drasdainks"));
        addToButtonList(new JButton("No sasdasdé"));
        addToButtonList(new JButton("No adasdsé2"));
        for (JButton temp : getButtonList())
            theFrame.add(temp);
    }

    @Override
    public void addActionListeners() {
        abstractUpdater temp = this;
        getButtonList().get(0).addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                updateToPreviousMenu();
            }
        });
        getButtonList().get(1).addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                meals_Window mealsWdw = new meals_Window(temp, new GridLayoutApplyer(theFrame, 5));
                mealsWdw.updateToThisMenu();
            }
        });
    }

}
