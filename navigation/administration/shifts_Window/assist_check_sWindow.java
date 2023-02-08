package navigation.administration.shifts_Window;

import javax.swing.table.DefaultTableModel;
import util.abstractEdit_CheckWindow;
import componentsFood.employee;
import componentsFood.shift;
import navigation.administration.employees_Window.employeesAPI;
import util.abstractUpdater;
import java.util.ArrayList;
import javax.swing.JScrollPane;
import javax.swing.JTable;

public class assist_check_sWindow extends abstractEdit_CheckWindow {
    private String from;
    private String to;
    private boolean shift_date;
    private shiftsAPI theManagerDB = new shiftsAPI();

    public assist_check_sWindow(abstractUpdater previousWindow, String from, String to, boolean shift_date) {
        super(previousWindow, "Check Shifts", "Shifts");
        this.from = from;
        this.to = to;
        this.shift_date = shift_date;
    }

    @Override
    public void addActionListeners() {
    }

    @Override
    public void addRowsToModel() {
        ArrayList<shift> tempList = theManagerDB.getShiftsWithinDateSorted(from, to, shift_date);
        myTable = new JTable();
        // employee_id, shift_date, start_shift, end_shift, realtime_in, realtime_out,
        // undertime
        model = new DefaultTableModel(
                new String[] { "Employee", "Role", "Shift Date", "Start", "End", "Check-In", "Check-Out" },
                0);
        for (shift temp : tempList) {
            String id = theManagerDB.getNameOfEmployee(temp.getEmployeeId());
            String role = theManagerDB.getRoleOfEmployee(temp.getEmployeeId());
            String date = temp.getDate();
            String startTime = temp.getStartTime();
            String endTime = temp.getEndTime();
            String realStartTime = temp.getRealStartTime();
            String realEndTime = temp.getRealEndTime();
            model.addRow(new String[] { id, role, date, startTime, endTime, realStartTime, realEndTime });
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
