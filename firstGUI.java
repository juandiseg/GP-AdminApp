import javax.swing.*;
import java.awt.*;

public class firstGUI {

    JFrame aFrame = new JFrame("FrameDemo");

    firstGUI() {
        aFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        aFrame.setSize(600, 600);
        aFrame.setVisible(true);
        aFrame.setLocationRelativeTo(null);
        addButtons(aFrame);
        aFrame.setLayout(new GridLayout(2, 2));
    }

    private void addButtons(JFrame theFrame) {
        JButton theButton1 = new JButton("I am a button 1");
        JButton theButton2 = new JButton("I am a button 2");
        JButton theButton3 = new JButton("I am a button 3");
        JButton theButton4 = new JButton("I am a button 4");
        theFrame.add(theButton1);
        theFrame.add(theButton2);
        theFrame.add(theButton3);
        theFrame.add(theButton4);
    }

    public static void main(String[] args) {
        firstGUI hi = new firstGUI();
    }

}