import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class mainWindow extends updateGUI {

    JFrame theFrame = new JFrame("FrameDemo");

    mainWindow() {
        super(null, 0);
        theFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        theFrame.setSize(600, 600);
        theFrame.setVisible(true);
        theFrame.setLocationRelativeTo(null);
        addButtons();
        addActionListeners();
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
        getButtonList().get(0).addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                System.out.println("hi");
            }
        });
        getButtonList().get(1).addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                System.out.println("hi");
            }
        });
        getButtonList().get(2).addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                System.out.println("hi");
            }
        });
        getButtonList().get(3).addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                System.out.println("hi");
            }
        });
    }

    public void applyLayout(int numberElements) {
        theFrame.setLayout(new GridLayout(numberElements / 2, numberElements / 2));
    }

}