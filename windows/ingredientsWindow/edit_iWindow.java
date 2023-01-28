package windows.ingredientsWindow;

import iLayouts.placeholderLayoutApplyer;
import java.awt.event.ActionListener;
import java.awt.event.MouseListener;
import java.util.ArrayList;
import java.awt.event.ActionEvent;
import javax.swing.JTextField;
import java.awt.*;

import java.awt.event.*;
import javax.swing.*;
import javax.swing.table.*;
import javax.swing.table.DefaultTableModel;

import com.mysql.cj.protocol.a.authentication.MysqlNativePasswordPlugin;

import componentsFood.provider;
import util.abstractUpdater;
import javax.swing.JButton;
import javax.swing.JTable;
import javax.swing.JLabel;

public class edit_iWindow extends abstractUpdater {

    private JTextField textFieldName = new JTextField();
    private JTextField textFieldEmail = new JTextField();
    private JTable myTable;
    private DefaultTableModel model;
    JLabel summaryTXT = new JLabel("Summary of current providers:");

    public edit_iWindow(abstractUpdater previousWindow) {
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
        myTable.addMouseListener(new MouseAdapter() {
            public void mouseClicked(MouseEvent me) {
                if (me.getClickCount() == 1) { // to detect doble click events
                    try {
                        JTable target = (JTable) me.getSource();
                        if (target.getValueAt(target.getSelectedRow(), 0).toString().equals(""))
                            return;
                        String ID = (String) target.getValueAt(target.getSelectedRow(), 0);
                        new assist_edit_iWindow(temp, Integer.valueOf(ID)).updateToThisMenu();
                    } catch (IndexOutOfBoundsException e) {
                        return;
                    }
                }
            }
        });
    }

}
