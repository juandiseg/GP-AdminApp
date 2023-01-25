import java.util.ArrayList;
import javax.swing.JButton;
import javax.swing.JFrame;

public abstract class updateGUI {

    private updateGUI previousWindow;
    static JFrame theFrame;
    public ArrayList<JButton> buttonList;
    private int numberOfButtons = 0;

    public updateGUI(JFrame aFrame, int numberOfButtons) {
        theFrame = aFrame;
        buttonList = new ArrayList<JButton>();
        if (numberOfButtons >= 0)
            this.numberOfButtons = numberOfButtons;
    }

    public abstract void addButtons();

    public abstract void addActionListeners();

    public abstract void applyLayout(int numberElements);

    public void returnPreviousWindow() {
        if (previousWindow == null)
            return;
        previousWindow.applyLayout(0);
    }

    private void refreshFrame() {
        // theFrame.getContentPane().removeAll();
        theFrame.validate();
        theFrame.repaint();
    }

    public ArrayList<JButton> getButtonList() {
        return buttonList;
    }

    public void addToButtonList(JButton theButton) {
        buttonList.add(theButton);
    }

}