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
import util.abstractEdit_CheckWindow;
import util.abstractUpdater;
import javax.swing.JButton;
import javax.swing.JTable;
import javax.swing.JLabel;

public class edit_iWindow extends abstractEdit_CheckWindow {

    public edit_iWindow(abstractUpdater previousWindow, String title) {
        super(previousWindow, title, true);
    }

    @Override
    public void addActionListeners() {
        abstractUpdater temp = this;
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

    @Override
    public void addRowsToModel() {
        ArrayList<provider> temp = theManagerDB.getAllProviders();
        myTable = new JTable();
        model = new DefaultTableModel(new String[] { "ID", "Name", "Email" }, 0);
        myTable.setModel(model);
        for (provider temp2 : temp) {
            // JButton tempButton = new JButton("Edit");
            model.addRow(
                    new String[] { Integer.toString(temp2.getId()), temp2.getName(), temp2.getEmail() });
        }
    }

    @Override
    public void adjustTable() {
        // TODO Auto-generated method stub
        myTable.setDefaultEditor(Object.class, null);
        myTable.setFocusable(true);

    }

    @Override
    public void setBounds() {
        myTable.setBounds(45, 60, 500, 300);
        getSummaryTXT().setBounds(200, 20, 250, 25);
        getBackButton().setBounds(400, 400, 120, 80);

    }

    @Override
    public void addToFrame() {
        // TODO Auto-generated method stub
        theFrame.add(getSummaryTXT());
        theFrame.add(getBackButton());
        theFrame.add(myTable);

    }

}
