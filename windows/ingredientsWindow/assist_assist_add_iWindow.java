package windows.ingredientsWindow;

import java.time.format.DateTimeFormatter;
import java.util.Stack;
import java.awt.event.ActionListener;
import javax.swing.DefaultListModel;
import java.awt.event.ActionEvent;
import javax.swing.JToggleButton;
import javax.swing.ListSelectionModel;
import javax.swing.event.*;
import javax.swing.event.ListSelectionListener;

import componentsFood.allergen;
import componentsFood.provider;
import util.abstractAddWindow;
import util.abstractUpdater;
import java.time.LocalDate;
import javax.swing.*;
import javax.swing.JList;

public class assist_assist_add_iWindow extends abstractAddWindow {

    private int ID;

    private JList<allergen> listAllergens = new JList<allergen>();
    private DefaultListModel<allergen> modelAllergenList = new DefaultListModel<allergen>();

    private JList<allergen> listSelected = new JList<allergen>();
    private DefaultListModel<allergen> modelSelectedList = new DefaultListModel<allergen>();

    private JButton swapLeft = new JButton("Left");
    private JButton swapRight = new JButton("Right");

    private JLabel summaryTXT;

    public assist_assist_add_iWindow(abstractUpdater previousWindow, int ID, String name) {
        super(previousWindow, "Provider", true);
        summaryTXT = new JLabel("Select the allergens contained in " + name + ":");
        this.ID = ID;
    }

    @Override
    public void addComponents() {
        setList();
        setBounds();
        addToFrame();
        setBackButton();
    }

    @Override
    public void addActionListeners() {
        abstractUpdater temp = this;
        getAddButton().addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                Stack<allergen> selectedAllergens = new Stack<allergen>();
                for (int i = 0; i < modelSelectedList.getSize(); i++)
                    selectedAllergens.push(modelSelectedList.getElementAt(i));
                if (theManagerDB.addAlergensOfIngredient(selectedAllergens, ID)) {
                    add_iWindow tempWind = new add_iWindow(
                            temp.getPreviousWindow().getPreviousWindow().getPreviousWindow());
                    tempWind.updateToThisMenu();
                    return;
                } else
                    System.out.println("there was an error");
            }
        });
        swapLeft.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                if (listAllergens.isSelectionEmpty())
                    return;
                allergen temp = listAllergens.getSelectedValue();
                modelAllergenList.remove(listAllergens.getSelectedIndex());
                modelSelectedList.addElement(temp);
            }
        });
        swapRight.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                if (listSelected.isSelectionEmpty())
                    return;
                allergen temp = listSelected.getSelectedValue();
                modelSelectedList.remove(listSelected.getSelectedIndex());
                modelAllergenList.addElement(temp);
            }
        });
    }

    @Override
    public void setBounds() {
        getInputSuccesful().setBounds(220, 360, 300, 25);
        getInputError().setBounds(220, 360, 300, 25);
        getBackButton().setBounds(400, 400, 120, 80);
        getAddButton().setBounds(80, 400, 130, 20);
        summaryTXT.setBounds(200, 20, 250, 25);
        listAllergens.setBounds(320, 150, 170, 200);
        listSelected.setBounds(50, 150, 170, 200);
        swapLeft.setBounds(230, 210, 80, 25);
        swapRight.setBounds(230, 250, 80, 25);
    }

    @Override
    public void addToFrame() {
        theFrame.add(getBackButton());
        theFrame.add(getAddButton());
        theFrame.add(listAllergens);
        theFrame.add(listSelected);
        theFrame.add(summaryTXT);
        theFrame.add(swapRight);
        theFrame.add(swapLeft);
    }

    private void setList() {
        for (allergen tempProv : theManagerDB.getAllAllergens())
            modelAllergenList.addElement(tempProv);
        listAllergens.setModel(modelAllergenList);
        listSelected.setModel(modelSelectedList);
    }
}
