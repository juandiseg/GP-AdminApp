package food.productsWindow;

import javax.swing.table.DefaultTableModel;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

import componentsFood.category;
import componentsFood.product;
import util.abstractAddWindow;
import util.abstractUpdater;
import food.categoryWindow.categoryAPI;

import javax.swing.*;

public class assist_edit_productWindow extends abstractAddWindow {

    private productAPI theManagerDB = new productAPI();

    protected product theCurrentProduct;
    private JTextField textFieldName = new JTextField();
    private JScrollPane scrollPane;
    private JTable myTable;
    private DefaultTableModel model;
    private JLabel summaryTXT = new JLabel("Product to be changed:");
    private JLabel enterName = new JLabel("Enter the product's new NAME: ");
    private JLabel chooseCategory = new JLabel("Select this product's new category:");
    private JButton editOtherAttributes = new JButton("See more changes");
    private JList<category> theList = new JList<category>();
    private JScrollPane scrollPaneList;

    public assist_edit_productWindow(abstractUpdater previousWindow, product theCurrentProduct) {
        super(previousWindow, "Product", false);
        this.theCurrentProduct = theCurrentProduct;
        getAddButton().setText("Apply Changes");
    }

    @Override
    public void addComponents() {
        setList();
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
                if (!name.isEmpty())
                    theManagerDB.updateProductName(theCurrentProduct.getId(), name);
                category tempSelectedCategory = theList.getSelectedValue();
                if (tempSelectedCategory != null)
                    new categoryAPI().editCategoryOfProduct(theCurrentProduct.getId(), tempSelectedCategory.getId());
                updateTable();
            }
        });
        abstractUpdater temp = this;
        editOtherAttributes.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                assist_assist_edit_productWindow tempWdw = new assist_assist_edit_productWindow(temp,
                        theCurrentProduct);
                tempWdw.updateToThisMenu();
            }
        });
    }

    private void updateTable() {
        theCurrentProduct = theManagerDB.getProduct(theCurrentProduct.getId());
        model.removeRow(0);
        String id = Integer.toString(theCurrentProduct.getId());
        String date = theCurrentProduct.getDate();
        String name = theCurrentProduct.getName();
        String price = Float.toString(theCurrentProduct.getPrice());
        category tempCategory = new categoryAPI().getCategoryOfProduct(theCurrentProduct.getId());
        model.addRow(new String[] { id, date, name, price, tempCategory.getName() });
    }

    private void loadTable() {
        myTable = new JTable();
        model = new DefaultTableModel(
                new String[] { "product_id", "date", "Name", "Price", "Category" }, 0);
        myTable.setModel(model);
        String id = Integer.toString(theCurrentProduct.getId());
        String date = theCurrentProduct.getDate();
        String name = theCurrentProduct.getName();
        String price = Float.toString(theCurrentProduct.getPrice());
        category tempCategory = new categoryAPI().getCategoryOfProduct(theCurrentProduct.getId());
        model.addRow(new String[] { id, date, name, price, tempCategory.getName() });
        myTable.setDefaultEditor(Object.class, null);
        myTable.setFocusable(true);
        myTable.removeColumn(myTable.getColumn("product_id"));
        myTable.removeColumn(myTable.getColumn("date"));
        scrollPane = new JScrollPane(myTable, JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED,
                JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
    }

    @Override
    public void setBounds() {
        getInputSuccesful().setBounds(230, 300, 300, 25);
        getInputError().setBounds(230, 300, 300, 25);
        getBackButton().setBounds(400, 400, 120, 80);
        getAddButton().setBounds(80, 400, 180, 20);
        editOtherAttributes.setBounds(80, 430, 180, 20);
        summaryTXT.setBounds(200, 20, 250, 25);
        textFieldName.setBounds(270, 130, 165, 25);
        enterName.setBounds(10, 130, 220, 25);
        scrollPane.setBounds(45, 60, 500, 55);
        chooseCategory.setBounds(10, 160, 270, 25);
        scrollPaneList.setBounds(270, 160, 170, 200);
    }

    @Override
    public void addToFrame() {
        theFrame.add(getBackButton());
        theFrame.add(getAddButton());
        theFrame.add(textFieldName);
        theFrame.add(summaryTXT);
        theFrame.add(enterName);
        theFrame.add(scrollPane);
        theFrame.add(editOtherAttributes);
        theFrame.add(chooseCategory);
        theFrame.add(scrollPaneList);
    }

    private void setList() {
        DefaultListModel<category> listModel = new DefaultListModel<category>();
        for (category tempCategory : new categoryAPI().getProductCategories())
            listModel.addElement(tempCategory);
        theList.setModel(listModel);
        scrollPaneList = new JScrollPane(theList, JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED,
                JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
    }
}
