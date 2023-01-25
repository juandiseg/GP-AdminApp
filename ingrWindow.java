import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ingrWindow extends updateGUI {

    ingrWindow(updateGUI prevWindow, int nmbrButtons) {
        super(prevWindow, nmbrButtons);
    }

    public void addButtons() {
        addToButtonList(new JButton("Ingasdasdredients"));
        addToButtonList(new JButton("Measdasdals"));
        addToButtonList(new JButton("Drasdadasnks"));
        addToButtonList(new JButton("Your sdasdasmum"));
        for (JButton temp : getButtonList())
            theFrame.add(temp);
    }

    public void addActionListeners() {
        getButtonList().get(0).addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {

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