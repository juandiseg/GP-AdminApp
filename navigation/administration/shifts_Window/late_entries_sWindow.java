package navigation.administration.shifts_Window;

import javax.swing.table.DefaultTableModel;
import util.abstractEdit_CheckWindow;
import componentsFood.shift;
import util.abstractUpdater;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

import javax.swing.JScrollPane;
import javax.swing.JTable;

public class late_entries_sWindow extends abstractEdit_CheckWindow {

    private shiftsAPI theManagerDB = new shiftsAPI();

    public late_entries_sWindow(abstractUpdater previousWindow) {
        super(previousWindow, "Check Late Entries", "Late Entries");
    }

    @Override
    public void addActionListeners() {
    }

    @Override
    public void addRowsToModel() {
        ArrayList<shift> tempList = theManagerDB.getLateEntries();
        myTable = new JTable();
        model = new DefaultTableModel(
                new String[] { "Employee", "Role", "Shift Date", "Start", "Check-In", "End", "Check-Out",
                        "Underworked" },
                0);
        for (shift temp : tempList) {
            String id = theManagerDB.getNameOfEmployee(temp.getEmployeeId());
            String role = theManagerDB.getRoleOfEmployee(temp.getEmployeeId());
            String date = temp.getDate();
            String startTime = temp.getStartTime();
            String endTime = temp.getEndTime();
            String realStartTime = temp.getRealStartTime();
            String realEndTime = temp.getRealEndTime();
            String difference = "";
            SimpleDateFormat sdf = new SimpleDateFormat("HH:mm");
            try {
                Date start = sdf.parse(startTime);
                Date end = sdf.parse(endTime);
                Date realStart = sdf.parse(realStartTime);
                Date realEnd = sdf.parse(realEndTime);
                long diff = ((end.getTime() - start.getTime()) - (realEnd.getTime() - realStart.getTime())) / 1000;
                long diffMinutes = diff / 60 % 60;
                long diffHours = diff / (60 * 60) % 24;
                difference = diffHours + ":" + diffMinutes;
            } catch (Exception e) {

            }
            model.addRow(new String[] { id, role, date, startTime, realStartTime, endTime, realEndTime, difference });
        }
    }

    @Override
    public void adjustTable() {
        setScrollPane(new JScrollPane(myTable));
        getScrollPane().setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);
        myTable.setDefaultEditor(Object.class, null);
        myTable.setFocusable(true);
        myTable.setModel(model);
    }

    @Override
    public void setBounds() {
        getScrollPane().setBounds(45, 60, 500, 300);
        getSummaryTXT().setBounds(200, 20, 250, 25);
        getBackButton().setBounds(400, 400, 120, 80);
    }

    @Override
    public void addToFrame() {
        theFrame.add(getSummaryTXT());
        theFrame.add(getBackButton());
        theFrame.add(getScrollPane());
    }

}
