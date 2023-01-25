import java.util.ArrayList;
import javax.swing.JButton;
import javax.swing.JFrame;

public abstract class updateGUI implements iUpdateButtons {

    private updateGUI previousWindow;
    public static JFrame theFrame;
    private ArrayList<JButton> buttonList;
    private int numberOfButtons = 0;

    public updateGUI(updateGUI prevWindow, int numberOfButtons) {
        previousWindow = prevWindow;
        buttonList = new ArrayList<JButton>();
        if (numberOfButtons >= 0)
            this.numberOfButtons = numberOfButtons;
    }

    final public void setFrame(JFrame _theFrame) {
        theFrame = _theFrame;
    }

    public void updateToThisMenu() {
        deleteContents();
        addButtons();
        addActionListeners();
        applyLayout(numberOfButtons);
        refreshFrame();
    }

    private void deleteContents() {
        theFrame.getContentPane().removeAll();
    }

    public void returnPreviousWindow() {
        if (previousWindow == null)
            return;
        previousWindow.applyLayout(0);
    }

    private void refreshFrame() {
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