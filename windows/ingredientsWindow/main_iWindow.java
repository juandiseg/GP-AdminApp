package windows.ingredientsWindow;

import java.awt.event.ActionListener;
import iLayouts.GridLayoutApplyer;
import util.abstractUpdater;

import java.awt.event.ActionEvent;
import javax.swing.JButton;

public class main_iWindow extends abstractUpdater {

    public main_iWindow(abstractUpdater previousWindow) {
        super(previousWindow, new GridLayoutApplyer(theFrame, 6));
    }

    @Override
    public void addComponents() {
        theFrame.setTitle("Ingredients menu");
        addToButtonList(new JButton("Add Ingredient"));
        addToButtonList(new JButton("Edit Ingredient"));
        addToButtonList(new JButton("Delete Ingredient"));
        addToButtonList(new JButton("Check Ingredients"));
        addToButtonList(new JButton("Back"));
        for (JButton temp : getButtonList())
            theFrame.add(temp);
    }

    @Override
    public void addActionListeners() {
        abstractUpdater temp = this;
        getButtonList().get(0).addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                add_iWindow tempWind = new add_iWindow(temp);
                tempWind.updateToThisMenu();
            }
        });
        getButtonList().get(1).addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                edit_iWindow tempWind = new edit_iWindow(temp);
                tempWind.updateToThisMenu();
            }
        });
        getButtonList().get(2).addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
            }
        });
        getButtonList().get(3).addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                check_iWindow tempWinw = new check_iWindow(temp);
                tempWinw.updateToThisMenu();
            }
        });
        getButtonList().get(4).addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                updateToPreviousMenu();
            }
        });
    }

}
