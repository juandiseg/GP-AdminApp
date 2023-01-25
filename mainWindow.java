import javax.swing.*;
import java.awt.*;

public class mainWindow extends updateGUI {

    JFrame theFrame = new JFrame("FrameDemo");

    mainWindow() {
        super(null, 0);
        theFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        theFrame.setSize(600, 600);
        theFrame.setVisible(true);
        theFrame.setLocationRelativeTo(null);
        addButtons();
        applyLayout(4);
    }

    public static void main(String[] args) {
        mainWindow hi = new mainWindow();
    }

    public void addButtons() {
        addToButtonList(new JButton("Main window button 1"));
        addToButtonList(new JButton("Main window button 2"));
        addToButtonList(new JButton("Main window button 3"));
        addToButtonList(new JButton("Main window button 4"));
        for (JButton temp : getButtonList())
            theFrame.add(temp);

    }

    public void addActionListeners() {
        System.out.println("abcd");

    }

    public void applyLayout(int numberElements) {
        theFrame.setLayout(new GridLayout(numberElements / 2, numberElements / 2));
    }

}