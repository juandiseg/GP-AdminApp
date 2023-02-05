package navigation.food.categoryWindow;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;

import componentsFood.category;
import util.abstractAddWindow;
import util.abstractUpdater;
import javax.swing.JLabel;
import javax.swing.*;

public class assist_edit_cWindow extends abstractAddWindow {

    private categoryAPI theManagerDB = new categoryAPI();
    private category theCurrentCategory;
    private JTextField textFieldName = new JTextField();
    private JTable myTable;
    private DefaultTableModel model;
    private JScrollPane scrollPane;

    private JLabel summaryTXT = new JLabel("Provider to be changed:");
    private JLabel enterName = new JLabel("Enter the new provider's NAME: ");

    public assist_edit_cWindow(abstractUpdater previousWindow, int ID) {
        super(previousWindow, "Provider", false);
        theCurrentCategory = theManagerDB.getCategory(ID);
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
                if (name.isEmpty()) {
                    printErrorGUI();
                    return;
                }
                if (theManagerDB.editCategory(theCurrentCategory.getId(), name)) {
                    printSuccessGUI();
                    theCurrentCategory = theManagerDB.getCategory(theCurrentCategory.getId());
                    model.removeRow(0);
                    String isProduct = "Menu";
                    if (theCurrentCategory.getIsProduct())
                        isProduct = "Product";
                    model.addRow(new String[] { theCurrentCategory.getName(), isProduct });
                } else {
                    printErrorGUI();
                }

            }
        });
    }

    private void loadTable() {
        myTable = new JTable();
        model = new DefaultTableModel(new String[] { "Name", "Email" }, 0);
        myTable.setModel(model);
        String isProduct = "Menu";
        if (theCurrentCategory.getIsProduct())
            isProduct = "Product";
        model.addRow(new Object[] { theCurrentCategory.getName(), isProduct });
        myTable.setDefaultEditor(Object.class, null);
        myTable.setFocusable(true);
        scrollPane = new JScrollPane(myTable);
        scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);
    }

    @Override
    public void setBounds() {
        getInputSuccesful().setBounds(250, 175, 250, 25);
        getInputError().setBounds(250, 175, 300, 25);
        getBackButton().setBounds(400, 400, 120, 80);
        getAddButton().setBounds(80, 175, 130, 20);
        textFieldName.setBounds(200, 105, 165, 25);
        summaryTXT.setBounds(200, 20, 250, 25);
        enterName.setBounds(10, 105, 220, 25);
        scrollPane.setBounds(45, 60, 500, 40);
    }

    @Override
    public void addToFrame() {
        theFrame.add(getAddButton());
        theFrame.add(getBackButton());
        theFrame.add(textFieldName);
        theFrame.add(summaryTXT);
        theFrame.add(enterName);
        theFrame.add(scrollPane);
    }

}
