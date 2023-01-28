package windows.ingredientsWindow;

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

public class check_iWindow extends abstractUpdater {

    private JTable myTable;
    private DefaultTableModel model;
    private JButton backButton = new JButton("Back");
    JLabel summaryTXT = new JLabel("Summary of current providers:");

    public check_iWindow(abstractUpdater previousWindow) {
        super(previousWindow, new placeholderLayoutApplyer(theFrame));
    }

    @Override
    public void addComponents() {
        theFrame.setTitle("Choose Provider to Change");
        summaryTXT.setBounds(200, 20, 250, 25);
        theFrame.add(summaryTXT);
        ArrayList<provider> temp = theManagerDB.getAllProviders();
        myTable = new JTable();
        model = new DefaultTableModel(new String[] { "ID", "Name", "Email" }, 0);
        myTable.setModel(model);
        for (provider temp2 : temp) {
            // JButton tempButton = new JButton("Edit");
            model.addRow(
                    new String[] { Integer.toString(temp2.getId()), temp2.getName(), temp2.getEmail() });
        }
        myTable.setBounds(45, 60, 500, 300);
        myTable.setDefaultEditor(Object.class, null);
        myTable.setFocusable(true);
        theFrame.add(myTable);
        backButton.setBounds(400, 400, 120, 80);
        theFrame.add(backButton);
    }

    @Override
    public void addActionListeners() {
        backButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                updateToPreviousMenu();
            }
        });
    }

}
