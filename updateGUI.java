import javax.swing.JFileChooser;
import javax.swing.JFrame;

public abstract class updateGUI {

    private updateGUI previousWindow;
    static JFrame theFrame;
    private int numberOfButtons;

    updateGUI(JFrame theFrame, int numberOfButtons) {
        this.theFrame = theFrame;
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
        theFrame.invalidate();
        theFrame.validate();
        theFrame.repaint();
    }

}