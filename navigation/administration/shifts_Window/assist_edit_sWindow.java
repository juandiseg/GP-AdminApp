package navigation.administration.shifts_Window;

import java.awt.event.ActionListener;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Stack;
import java.awt.event.ActionEvent;
import javax.swing.table.DefaultTableModel;

import componentsFood.menu;
import componentsFood.product;
import componentsFood.shift;
import navigation.food.categoryWindow.categoryAPI;
import navigation.food.productsWindow.productAPI;
import util.abstractAddWindow;
import util.abstractUpdater;

import javax.swing.JLabel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.JButton;

public class assist_edit_sWindow extends abstractAddWindow {

    private JLabel selectIngredients = new JLabel("Select shifts to be editted: ");

    private shiftsAPI theManagerDB = new shiftsAPI();
    private JButton swapLeft = new JButton("Left");
    private JButton swapRight = new JButton("Right");
    private JButton deleteShifts = new JButton("Delete Shifts");
    private String from;
    private String to;
    private boolean shift_date;

    private JScrollPane scrollPaneShifts;
    private JScrollPane scrollPaneSelected;
    private JTable tableShifts;
    private JTable tableSelected;
    private DefaultTableModel modelShifts;
    private DefaultTableModel modelSelected;

    private JLabel enterStart = new JLabel("Enter the shift's NEW START TIME: ");
    private JLabel enterEnd = new JLabel("Enter the shift's NEW END TIME: ");
    private JTextField textFieldStart = new JTextField();
    private JTextField textFieldEnd = new JTextField();

    public assist_edit_sWindow(abstractUpdater previousWindow, String from, String to, boolean shift_date) {
        super(previousWindow, "Product", true);
        this.from = from;
        this.to = to;
        this.shift_date = shift_date;
    }

    @Override
    public void addComponents() {
        setTable();
        setBounds();
        addToFrame();
        setBackButton();
    }

    @Override
    public void addActionListeners() {
        getAddButton().addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                String newStart = textFieldStart.getText();
                String newEnd = textFieldEnd.getText();
                if (newStart.isEmpty() && newEnd.isEmpty())
                    return;
                ArrayList<shift> listShifts = new ArrayList<shift>();
                for (int i = 0; i < modelSelected.getRowCount(); i++) {
                    int id = Integer.parseInt((String) modelSelected.getValueAt(i, 0));
                    String date = (String) modelSelected.getValueAt(i, 3);
                    String startTime = (String) modelSelected.getValueAt(i, 4);
                    String endTime = (String) modelSelected.getValueAt(i, 5);
                    listShifts.add(new shift(id, date, startTime, endTime));
                }
                if (!newStart.isEmpty()) {
                    for (shift temp : listShifts)
                        theManagerDB.updateEntryTime(temp, newStart);
                }
                if (!newEnd.isEmpty()) {
                    for (shift temp : listShifts)
                        theManagerDB.updateEndTime(temp, newEnd);
                }
                // SOMEHOW UPDATE
            }
        });
        deleteShifts.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                if (modelSelected.getRowCount() == 0)
                    return;
                ArrayList<shift> listShifts = new ArrayList<shift>();
                for (int i = 0; i < modelSelected.getRowCount(); i++) {
                    int id = Integer.parseInt((String) modelSelected.getValueAt(i, 0));
                    String date = (String) modelSelected.getValueAt(i, 3);
                    String startTime = (String) modelSelected.getValueAt(i, 4);
                    String endTime = (String) modelSelected.getValueAt(i, 5);
                    listShifts.add(new shift(id, date, startTime, endTime));
                }
                for (int i = modelSelected.getRowCount() - 1; i > -1; i--)
                    modelSelected.removeRow(i);
                for (shift temp : listShifts)
                    theManagerDB.deleteShift(temp);
            }
        });
        swapLeft.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                int row = tableShifts.getSelectedRow();
                if (row == -1)
                    return;
                String id = (String) modelShifts.getValueAt(row, 0);
                String name = (String) modelShifts.getValueAt(row, 1);
                String role = (String) modelShifts.getValueAt(row, 2);
                String date = (String) modelShifts.getValueAt(row, 3);
                String startTime = (String) modelShifts.getValueAt(row, 4);
                String endTime = (String) modelShifts.getValueAt(row, 5);
                modelShifts.removeRow(row);
                modelSelected.addRow(new String[] { id, name, role, date, startTime, endTime });
            }
        });
        swapRight.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                int row = tableSelected.getSelectedRow();
                if (row == -1)
                    return;
                String id = (String) modelSelected.getValueAt(row, 0);
                String name = (String) modelSelected.getValueAt(row, 1);
                String role = (String) modelSelected.getValueAt(row, 2);
                String date = (String) modelSelected.getValueAt(row, 3);
                String startTime = (String) modelSelected.getValueAt(row, 4);
                String endTime = (String) modelSelected.getValueAt(row, 5);
                modelSelected.removeRow(row);
                modelShifts.addRow(new String[] { id, name, role, date, startTime, endTime });
            }
        });
    }

    @Override
    public void setBounds() {
        getInputSuccesful().setBounds(250, 120, 250, 25);
        getInputError().setBounds(250, 120, 300, 25);
        getAddButton().setBounds(80, 400, 130, 20);
        deleteShifts.setBounds(240, 400, 130, 20);
        getBackButton().setBounds(400, 400, 120, 80);
        selectIngredients.setBounds(10, 50, 200, 25);
        scrollPaneShifts.setBounds(320, 90, 170, 200);
        scrollPaneSelected.setBounds(50, 90, 170, 200);
        swapLeft.setBounds(230, 150, 80, 25);
        swapRight.setBounds(230, 190, 80, 25);
        enterStart.setBounds(10, 310, 230, 25);
        enterEnd.setBounds(10, 340, 230, 25);
        textFieldStart.setBounds(240, 310, 165, 25);
        textFieldEnd.setBounds(240, 340, 165, 25);
    }

    @Override
    public void addToFrame() {
        theFrame.add(getAddButton());
        theFrame.add(getBackButton());
        theFrame.add(deleteShifts);
        theFrame.add(selectIngredients);
        theFrame.add(scrollPaneShifts);
        theFrame.add(scrollPaneSelected);
        theFrame.add(swapRight);
        theFrame.add(swapLeft);
        theFrame.add(enterStart);
        theFrame.add(enterEnd);
        theFrame.add(textFieldStart);
        theFrame.add(textFieldEnd);
    }

    private void setTable() {
        tableShifts = new JTable();
        tableShifts.setDefaultEditor(Object.class, null);
        modelShifts = new DefaultTableModel(
                new String[] { "employee_id", "Employee", "Role", "Shift Date", "Start", "End" }, 0);

        tableSelected = new JTable();
        tableSelected.setDefaultEditor(Object.class, null);
        modelSelected = new DefaultTableModel(
                new String[] { "employee_id", "Employee", "Role", "Shift Date", "Start", "End" }, 0);

        for (shift tempShift : theManagerDB.getShiftsWithinDateSorted(from, to, shift_date)) {
            String employeeID = Integer.toString(tempShift.getEmployeeId());
            String name = theManagerDB.getNameOfEmployee(tempShift.getEmployeeId());
            String role = theManagerDB.getRoleOfEmployee(tempShift.getEmployeeId());
            String date = tempShift.getDate();
            String startTime = tempShift.getStartTime();
            String endTime = tempShift.getEndTime();
            modelShifts.addRow(new String[] { employeeID, name, role, date, startTime, endTime });
        }
        tableShifts.setModel(modelShifts);
        tableSelected.setModel(modelSelected);
        tableShifts.removeColumn(tableShifts.getColumn("employee_id"));
        tableSelected.removeColumn(tableSelected.getColumn("employee_id"));
        scrollPaneShifts = new JScrollPane(tableShifts, JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED,
                JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
        scrollPaneSelected = new JScrollPane(tableSelected, JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED,
                JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
    }

}
