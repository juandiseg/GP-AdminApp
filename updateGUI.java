public abstract class updateGUI {

    private updateGUI previousWindow;
    private int numberOfButtons;

    public abstract void addButtons();

    public abstract void addActionListeners();

    public abstract void applyLayout(int numberElements);

    public void returnPreviousWindow() {
        if (previousWindow == null)
            return;

        previousWindow.applyLayout(0);
    }

    private void refreshFrame() {

    }

}