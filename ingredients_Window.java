import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JButton;
import iLayouts.GridLayoutApplyer;
import iLayouts.iLayout;

public class ingredients_Window extends abstractUpdater {

    ingredients_Window(abstractUpdater prevWindow, iLayout layoutApplyer) {
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

// CASCADE DELETE:
/*
 * Constraint "nameOfConstraint"
 * FOREIGN KEY "column thats a foreign key"
 * REFERENCES "column thats referenced previous foreign key" (prob primary key
 * of other table)
 * ON DELETE CASCADE
 * ON UPDATE CASCADE (if there's need to change IDs)
 * 
 */