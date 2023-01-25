import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class mainWindow extends updateGUI {

    static JFrame theFrame = new JFrame("FrameDemo");

    mainWindow() {
        super(null, 4);
        setFrame(theFrame);
        theFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        theFrame.setSize(600, 600);
        theFrame.setVisible(true);
        theFrame.setLocationRelativeTo(null);
        updateToThisMenu();
    }

    public static void main(String[] args) {
        mainWindow hi = new mainWindow();
    }

    public void addButtons() {
        addToButtonList(new JButton("Ingredients"));
        addToButtonList(new JButton("Meals"));
        addToButtonList(new JButton("Drinks"));
        addToButtonList(new JButton("Your mum"));
        for (JButton temp : getButtonList())
            theFrame.add(temp);
    }

    public void addActionListeners() {
        updateGUI temp = this;
        getButtonList().get(0).addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                ingrWindow ingredientesWdw = new ingrWindow(temp, 8);
                ingredientesWdw.updateToThisMenu();
            }
        });
        getButtonList().get(1).addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {

            }
        });
        getButtonList().get(2).addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {

            }
        });
        getButtonList().get(3).addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {

            }
        });
    }

    public void applyLayout(int numberElements) {
        theFrame.setLayout(new GridLayout(numberElements / 2, numberElements / 2));
    }

}