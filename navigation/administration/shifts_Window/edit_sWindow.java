package navigation.administration.shifts_Window;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JTextField;
import javax.swing.JToggleButton;

import util.abstractAddWindow;
import util.abstractUpdater;
import javax.swing.JLabel;

public class edit_sWindow extends abstractAddWindow {

    private JLabel enterFrom = new JLabel("From: ");
    private JLabel enterTo = new JLabel("To: ");
    private JTextField textFieldFrom = new JTextField();
    private JTextField textFieldTo = new JTextField();
    private JLabel chooseSorting = new JLabel("Sort the results by: ");
    private JToggleButton chooseButton = new JToggleButton("Employees");

    public edit_sWindow(abstractUpdater previousWindow) {
        super(previousWindow, "Shifts", false);
    }

    @Override
    public void addActionListeners() {
        abstractUpdater temp = this;
        getAddButton().addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                String from = textFieldFrom.getText();
                String to = textFieldTo.getText();
                boolean shift_date = true;
                if (chooseButton.getText().equals("Employees"))
                    shift_date = false;
                if (true /* ¡CHECK INPUT! MUST NOT BE A PAST DATE! */ ) {
                    assist_edit_sWindow tempWdw = new assist_edit_sWindow(temp, from, to, shift_date);
                    tempWdw.updateToThisMenu();
                }
            }
        });
        chooseButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                if (chooseButton.getText().equals("Employees"))
                    chooseButton.setText("Shifts Date");
                else
                    chooseButton.setText("Employees");
            }
        });
    }

    @Override
    public void setBounds() {
        getInputSuccesful().setBounds(250, 120, 250, 25);
        getInputError().setBounds(250, 120, 300, 25);
        getAddButton().setBounds(80, 120, 130, 20);
        getBackButton().setBounds(400, 400, 120, 80);
        enterFrom.setBounds(10, 20, 200, 25);
        textFieldFrom.setBounds(210, 20, 165, 25);
        enterTo.setBounds(10, 50, 200, 25);
        textFieldTo.setBounds(210, 50, 165, 25);
        chooseSorting.setBounds(10, 80, 200, 25);
        chooseButton.setBounds(210, 80, 165, 25);
    }

    @Override
    public void addToFrame() {
        theFrame.add(getAddButton());
        theFrame.add(getBackButton());
        theFrame.add(enterFrom);
        theFrame.add(textFieldFrom);
        theFrame.add(enterTo);
        theFrame.add(textFieldTo);
        theFrame.add(chooseSorting);
        theFrame.add(chooseButton);
    }

}
