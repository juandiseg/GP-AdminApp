import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

import javax.swing.JButton;

import iLayouts.GridLayoutApplyer;
import iLayouts.iLayout;

public class mealsWindow extends updateGUI {

    mealsWindow(updateGUI prevWindow, iLayout layoutApplyer) {
        super(prevWindow, layoutApplyer);
    }

    @Override
    public void addButtons() {
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
    }

}
