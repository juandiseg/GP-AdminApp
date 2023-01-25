import java.util.ArrayList;
import javax.swing.JButton;
import javax.swing.JFrame;
import iLayouts.*;

public abstract class updateGUI implements iUpdateButtons {

    public static JFrame theFrame;
    private updateGUI previousWindow;
    private iLayout layoutApplyer;
    private ArrayList<JButton> buttonList;

    public updateGUI(updateGUI previousWindow, iLayout layoutApplyer) {
        this.previousWindow = previousWindow;
        this.layoutApplyer = layoutApplyer;
        buttonList = new ArrayList<JButton>();
    }

    final public void setFrame(JFrame _theFrame) {
        theFrame = _theFrame;
    }

    public void updateToThisMenu() {
        deleteContents();
        addButtons();
        addActionListeners();
        layoutApplyer.applyLayout();
        refreshFrame();
    }

    private void deleteContents() {
        theFrame.getContentPane().removeAll();
    }

    public void updateToPreviousMenu() {
        if (previousWindow == null)
            return;
        previousWindow.getButtonList().clear();
        previousWindow.updateToThisMenu();
    }

    private void refreshFrame() {
        theFrame.validate();
        theFrame.repaint();
    }

    public void addToButtonList(JButton theButton) {
        buttonList.add(theButton);
    }

    public ArrayList<JButton> getButtonList() {
        return buttonList;
    }

}