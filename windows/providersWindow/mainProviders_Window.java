package windows.providersWindow;

import java.awt.event.ActionListener;
import iLayouts.GridLayoutApplyer;
import util.abstractUpdater;

import java.awt.event.ActionEvent;
import javax.swing.JButton;

public class mainProviders_Window extends abstractUpdater {

    public mainProviders_Window(abstractUpdater previousWindow) {
        super(previousWindow, new GridLayoutApplyer(theFrame, 6));
    }

    @Override
    public void addComponents() {
        theFrame.setTitle("Providers menu");
        addToButtonList(new JButton("Add Provider"));
        addToButtonList(new JButton("Edit Provider"));
        addToButtonList(new JButton("Delete Provider"));
        addToButtonList(new JButton("Check Providers"));
        addToButtonList(new JButton("Back"));
        for (JButton temp : getButtonList())
            theFrame.add(temp);
    }

    @Override
    public void addActionListeners() {
        abstractUpdater temp = this;
        getButtonList().get(0).addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                mainProviders_Window ingredientesWdw = new mainProviders_Window(temp);
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
        getButtonList().get(4).addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                updateToPreviousMenu();
            }
        });
    }

}
