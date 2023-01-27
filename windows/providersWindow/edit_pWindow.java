package windows.providersWindow;

import iLayouts.placeholderLayoutApplyer;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.awt.event.ActionEvent;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;

import com.mysql.cj.protocol.a.authentication.MysqlNativePasswordPlugin;

import componentsFood.provider;
import util.abstractUpdater;
import javax.swing.JButton;
import javax.swing.JTable;
import javax.swing.JLabel;

public class edit_pWindow extends abstractUpdater {

    private JTextField textFieldName = new JTextField();
    private JTextField textFieldEmail = new JTextField();
    private JTable myTable;
    private DefaultTableModel model;
    JLabel summaryTXT = new JLabel("Summary of current providers:");

    public edit_pWindow(abstractUpdater previousWindow) {
        super(previousWindow, new placeholderLayoutApplyer(theFrame));
    }

    @Override
    public void addComponents() {
        theFrame.setTitle("Edit Providers");
        summaryTXT.setBounds(200, 20, 250, 25);
        theFrame.add(summaryTXT);
        ArrayList<provider> temp = theManagerDB.getAllProviders();

        myTable = new JTable();
        model = new DefaultTableModel(new String[] { "ID", "Name", "Email", "" }, 0);
        myTable.setModel(model);
        for (provider temp2 : temp) {
            JButton tempButton = new JButton("Edit");
            model.addRow(
                    new Object[] { Integer.toString(temp2.getId()), temp2.getName(), temp2.getEmail(), tempButton });
        }
        myTable.setBounds(45, 60, 500, 300);
        myTable.setDefaultEditor(Object.class, null);
        theFrame.add(myTable);

        JButton backButton = new JButton("Back");
        backButton.setBounds(400, 400, 120, 80);
        theFrame.add(backButton);
        addToButtonList(backButton);
    }

    @Override
    public void addActionListeners() {
        abstractUpdater temp = this;
        getButtonList().get(0).addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                updateToPreviousMenu();
            }
        });

    }

}
