import java.util.ArrayList;
import javax.swing.JButton;
import javax.swing.JFrame;

public abstract class updateGUI {

    private updateGUI previousWindow;
    static JFrame theFrame;
    private ArrayList<JButton> buttonList = new ArrayList<JButton>();
    private int numberOfButtons = 0;

    updateGUI(JFrame aFrame, int numberOfButtons) {
        theFrame = aFrame;
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

    public boolean addToButtonList(JButton theButton) {
        try {
            buttonList.add(theButton);
            return true;
        } catch (Exception e) {
            return false;
        }
    }

}