package windows.ingredientsWindow;

import javax.swing.table.DefaultTableModel;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JTextField;
import componentsFood.allergen;
import util.abstractAddWindow;
import util.abstractUpdater;
import javax.swing.JLabel;
import javax.swing.*;

public class assist_edit_iWindow extends abstractAddWindow {

    private allergen theCurrentAllergen;
    private JTextField textFieldName = new JTextField();
    private JTable myTable;
    private JLabel summaryTXT = new JLabel("Allergen to be changed:");
    private JLabel enterName = new JLabel("Enter the new allergen's NAME: ");

    public assist_edit_iWindow(abstractUpdater previousWindow, int ID) {
        super(previousWindow, "Ingredient", false);
        theCurrentAllergen = theManagerDB.getAllergen(ID);
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
                if (theManagerDB.editAllergen(theCurrentAllergen.getId(), name)) {
                    printSuccessGUI();
                    updateFrame(theCurrentAllergen.getId());
                } else {
                    printErrorGUI();
                }

            }
        });
    }

    private void updateFrame(int id) {
        theCurrentAllergen = theManagerDB.getAllergen(id);
        loadTable();
        theFrame.revalidate();
    }

    private void loadTable() {
        myTable = new JTable();
        DefaultTableModel model = new DefaultTableModel(new String[] { "Name" }, 0);
        myTable.setModel(model);
        model.addRow(new Object[] { theCurrentAllergen.getName() });
        myTable.setDefaultEditor(Object.class, null);
        myTable.setFocusable(true);
    }

    @Override
    public void setBounds() {
        getInputSuccesful().setBounds(250, 160, 250, 25);
        getBackButton().setBounds(400, 400, 120, 80);
        getInputError().setBounds(250, 160, 300, 25);
        getAddButton().setBounds(80, 160, 130, 20);
        textFieldName.setBounds(200, 90, 165, 25);
        summaryTXT.setBounds(200, 20, 250, 25);
        enterName.setBounds(10, 90, 220, 25);
        myTable.setBounds(45, 60, 500, 15);
    }

    @Override
    public void addToFrame() {
        theFrame.add(getBackButton());
        theFrame.add(getAddButton());
        theFrame.add(textFieldName);
        theFrame.add(summaryTXT);
        theFrame.add(enterName);
        theFrame.add(myTable);
    }

}
