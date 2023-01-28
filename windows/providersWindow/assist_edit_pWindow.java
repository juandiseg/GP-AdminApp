package windows.providersWindow;

import iLayouts.placeholderLayoutApplyer;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;

import componentsFood.provider;
import util.abstractAddWindow;
import util.abstractUpdater;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.*;

public class assist_edit_pWindow extends abstractAddWindow {

    private provider theCurrentProvider;
    private JTextField textFieldName = new JTextField();
    private JTextField textFieldEmail = new JTextField();
    private JTable myTable;

    JLabel summaryTXT = new JLabel("Provider to be changed:");
    JLabel enterEmail = new JLabel("Enter the new provider's EMAIL: ");
    JLabel enterName = new JLabel("Enter the new provider's NAME: ");

    public assist_edit_pWindow(abstractUpdater previousWindow, int ID) {
        super(previousWindow, "Provider", false);
        theCurrentProvider = theManagerDB.getProvider(ID);
    }

    @Override
    public void addComponents() {
        loadTable();
        setBounds();
        addToFrame();
        setBackButton();
    }

    @Override
    public void addActionListeners() {
        getAddButton().addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                String name = textFieldName.getText();
                String email = textFieldEmail.getText();
                if (name.isEmpty() && email.isEmpty()) {
                    printErrorGUI();
                    return;
                } else if (name.isEmpty() || email.isEmpty()) {
                    if (name.isEmpty())
                        theManagerDB.editProvider(theCurrentProvider.getId(), theCurrentProvider.getName(), email);
                    else
                        theManagerDB.editProvider(theCurrentProvider.getId(), name, theCurrentProvider.getEmail());
                    printSuccessGUI();
                    updateFrame(theCurrentProvider.getId());
                    return;
                }
                if (theManagerDB.editProvider(theCurrentProvider.getId(), name, email)) {
                    printSuccessGUI();
                    updateFrame(theCurrentProvider.getId());
                } else {
                    printErrorGUI();
                }

            }
        });
    }

    private void updateFrame(int id) {
        theCurrentProvider = theManagerDB.getProvider(id);
        loadTable();
        theFrame.revalidate();
    }

    private void loadTable() {
        myTable = new JTable();
        DefaultTableModel model = new DefaultTableModel(new String[] { "Name", "Email" }, 0);
        myTable.setModel(model);
        model.addRow(new Object[] { theCurrentProvider.getName(), theCurrentProvider.getEmail() });
        myTable.setDefaultEditor(Object.class, null);
        myTable.setFocusable(true);
    }

    @Override
    public void setBounds() {
        getInputSuccesful().setBounds(250, 160, 250, 25);
        getInputError().setBounds(250, 160, 300, 25);
        getBackButton().setBounds(400, 400, 120, 80);
        textFieldEmail.setBounds(200, 120, 165, 25);
        getAddButton().setBounds(80, 160, 130, 20);
        textFieldName.setBounds(200, 90, 165, 25);
        summaryTXT.setBounds(200, 20, 250, 25);
        enterEmail.setBounds(10, 120, 220, 25);
        enterName.setBounds(10, 90, 220, 25);
        myTable.setBounds(45, 60, 500, 15);
    }

    @Override
    public void addToFrame() {
        theFrame.add(getAddButton());
        theFrame.add(getBackButton());
        theFrame.add(textFieldEmail);
        theFrame.add(textFieldName);
        theFrame.add(summaryTXT);
        theFrame.add(enterEmail);
        theFrame.add(enterName);
        theFrame.add(myTable);
    }

}
