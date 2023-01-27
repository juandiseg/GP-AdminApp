package iLayouts;

import java.awt.GridLayout;
import javax.swing.JFrame;

public class GridLayoutApplyer implements iLayout {

    private JFrame theFrame;
    private int numberOfButtons;

    public GridLayoutApplyer(JFrame theFrame, int numberOfButtons) {
        this.theFrame = theFrame;
        this.numberOfButtons = numberOfButtons;
    }

    public void applyLayout() {
        theFrame.setLayout(new GridLayout(numberOfButtons / 2, numberOfButtons / 2));
    }
}