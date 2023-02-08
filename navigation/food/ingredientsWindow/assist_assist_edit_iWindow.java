package navigation.food.ingredientsWindow;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import javax.swing.DefaultListModel;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;

import componentsFood.ingredient;
import componentsFood.provider;
import navigation.food.providersWindow.providerAPI;
import util.abstractAddWindow;
import util.abstractUpdater;

public class assist_assist_edit_iWindow extends abstractAddWindow {

    private ingredientsAPI theManagerDB = new ingredientsAPI();

    private ingredient theCurrentIngredient;
    private JTextField textFieldPrice = new JTextField();
    private JTextField textFieldAmount = new JTextField();
    private JScrollPane scrollPaneTable;
    private JTable myTable;
    private DefaultTableModel model;
    private JLabel summaryTXT = new JLabel("Ingredient to be changed:");
    private JLabel enterPrice = new JLabel("Enter the ingredient's new PRICE: ");
    private JLabel enterAmount = new JLabel("Enter the ingredient's new AMOUNT/price: ");
    private JLabel enterProvider = new JLabel("Select the ingredient's new PROVIDER: ");
    private JList<provider> theList = new JList<provider>();
    private JScrollPane scrollPaneList;

    public assist_assist_edit_iWindow(abstractUpdater previousWindow, ingredient theIngredient) {
        super(previousWindow, "Ingredient", false);
        theCurrentIngredient = theIngredient;
        getAddButton().setText("Apply Changes");
    }

    @Override
    public void addComponents() {
        loadTable();
        setList();
        setBounds();
        addToFrame();
        setBackButton();
    }

    public void setList() {
        DefaultListModel<provider> listModel = new DefaultListModel<provider>();
        for (provider tempProv : new providerAPI().getAllProviders())
            listModel.addElement(tempProv);
        theList.setModel(listModel);
        scrollPaneList = new JScrollPane(theList, JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED,
                JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
    }

    @Override
    public void addActionListeners() {
        getAddButton().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                provider temProvider = theList.getSelectedValue();
                if (temProvider != null) {
                    if (temProvider.getId() != theCurrentIngredient.getProviderID())
                        theManagerDB.updateProvider(theCurrentIngredient.getId(), temProvider.getId());
                }
                if (!textFieldAmount.getText().isEmpty()) {
                    float amount = Float.parseFloat(textFieldAmount.getText());
                    theManagerDB.updateAmount(theCurrentIngredient.getId(), amount);
                }
                if (!textFieldPrice.getText().isEmpty()) {
                    float price = Float.parseFloat(textFieldPrice.getText());
                    theManagerDB.updatePrice(theCurrentIngredient.getId(), price);
                }
                updateTable();
            }
        });
    }

    private void updateTable() {
        theCurrentIngredient = theManagerDB.getIngredient(theCurrentIngredient.getId());

        LocalDate dateObj = LocalDate.now();
        String date = dateObj.format(DateTimeFormatter.ofPattern("yyyy-MM-dd"));
        model.removeRow(0);
        String id = Integer.toString(theCurrentIngredient.getId());
        String prov_id = Integer.toString(theCurrentIngredient.getProviderID());
        String price = Float.toString(theCurrentIngredient.getPrice());
        String amount = Float.toString(theCurrentIngredient.getAmount());
        String in_inventory;
        if (theCurrentIngredient.getInInventory())
            in_inventory = "Yes";
        else
            in_inventory = "No";
        model.addRow(new String[] { id, theCurrentIngredient.getName(),
                new providerAPI().getProvider(Integer.parseInt(prov_id)).getName(),
                date, price, amount, in_inventory });
    }

    private void loadTable() {
        myTable = new JTable();
        model = new DefaultTableModel(
                new String[] { "ID", "Name", "Provider", "date", "Price", "Amount", "Inventory" },
                0);
        myTable.setModel(model);
        String id = Integer.toString(theCurrentIngredient.getId());
        String prov_id = Integer.toString(theCurrentIngredient.getProviderID());
        String price = Float.toString(theCurrentIngredient.getPrice());
        String amount = Float.toString(theCurrentIngredient.getAmount());
        String in_inventory;
        if (theCurrentIngredient.getInInventory())
            in_inventory = "Yes";
        else
            in_inventory = "No";
        model.addRow(new String[] { id, theCurrentIngredient.getName(),
                new providerAPI().getProvider(Integer.parseInt(prov_id)).getName(),
                theCurrentIngredient.getDate(), price,
                amount, in_inventory });
        myTable.setDefaultEditor(Object.class, null);
        myTable.setFocusable(true);
        myTable.removeColumn(myTable.getColumn("ID"));
        myTable.removeColumn(myTable.getColumn("date"));
        scrollPaneTable = new JScrollPane(myTable, JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED,
                JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
    }

    @Override
    public void setBounds() {
        getInputSuccesful().setBounds(230, 300, 300, 25);
        getInputError().setBounds(230, 300, 300, 25);
        getBackButton().setBounds(400, 400, 120, 80);
        getAddButton().setBounds(80, 400, 180, 20);
        summaryTXT.setBounds(200, 20, 250, 25);
        textFieldPrice.setBounds(270, 130, 165, 25);
        enterPrice.setBounds(10, 130, 220, 25);
        scrollPaneTable.setBounds(45, 60, 500, 55);
        textFieldAmount.setBounds(270, 160, 170, 25);
        enterAmount.setBounds(10, 160, 270, 25);

        enterProvider.setBounds(10, 190, 270, 25);
        scrollPaneList.setBounds(250, 200, 170, 200);
    }

    @Override
    public void addToFrame() {
        theFrame.add(getBackButton());
        theFrame.add(getAddButton());
        theFrame.add(textFieldAmount);
        theFrame.add(summaryTXT);
        theFrame.add(enterAmount);
        theFrame.add(scrollPaneTable);
        theFrame.add(textFieldPrice);
        theFrame.add(enterPrice);
        theFrame.add(enterProvider);
        theFrame.add(scrollPaneList);
    }

}