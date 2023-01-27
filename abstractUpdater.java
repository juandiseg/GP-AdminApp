import java.util.ArrayList;
import java.sql.Connection;
import javax.swing.JButton;
import javax.swing.JFrame;
import iLayouts.*;

public abstract class abstractUpdater {

    public static JFrame theFrame;
    public static managerDB theManagerDB;
    private abstractUpdater previousWindow;
    private iLayout layoutApplyer;
    private ArrayList<JButton> buttonList;

    public abstractUpdater(abstractUpdater previousWindow, iLayout layoutApplyer) {
        this.previousWindow = previousWindow;
        this.layoutApplyer = layoutApplyer;
        buttonList = new ArrayList<JButton>();
    }

    final public void setFrame(JFrame _theFrame) {
        theFrame = _theFrame;
    }

    final public void setManagerDB(managerDB _theManagerDB) {
        theManagerDB = _theManagerDB;
    }

    public void updateToThisMenu() {
        deleteContents();
        addComponents();
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

    public abstract void addComponents();

    public abstract void addActionListeners();

    public void addToButtonList(JButton theButton) {
        buttonList.add(theButton);
    }

    public ArrayList<JButton> getButtonList() {
        return buttonList;
    }

}