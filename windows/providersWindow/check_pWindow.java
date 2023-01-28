package windows.providersWindow;

import iLayouts.placeholderLayoutApplyer;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.awt.event.ActionEvent;
import javax.swing.table.DefaultTableModel;
import componentsFood.provider;
import util.abstractUpdater;
import javax.swing.JButton;
import javax.swing.JTable;
import javax.swing.JLabel;

public class check_pWindow extends abstractUpdater {

    private JTable myTable;
    private DefaultTableModel model;
    JLabel summaryTXT = new JLabel("Summary of current providers:");

    public check_pWindow(abstractUpdater previousWindow) {
        super(previousWindow, new placeholderLayoutApplyer(theFrame));
    }

    @Override
    public void addComponents() {
        theFrame.setTitle("Choose Provider to Change");
        summaryTXT.setBounds(200, 20, 250, 25);
        theFrame.add(summaryTXT);
        ArrayList<provider> temp = theManagerDB.getAllProviders();
        myTable = new JTable();
        model = new DefaultTableModel(new String[] { "Name", "Email" }, 0);
        myTable.setModel(model);
        for (provider temp2 : temp) {
            // JButton tempButton = new JButton("Edit");
            model.addRow(
                    new String[] { temp2.getName(), temp2.getEmail() });
        }
        myTable.setBounds(45, 60, 500, 300);
        myTable.setDefaultEditor(Object.class, null);
        myTable.setFocusable(true);
        theFrame.add(myTable);
        JButton backButton = new JButton("Back");
        backButton.setBounds(400, 400, 120, 80);
        theFrame.add(backButton);
        addToButtonList(backButton);
    }

    @Override
    public void addActionListeners() {
        getButtonList().get(0).addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                updateToPreviousMenu();
            }
        });
    }

}