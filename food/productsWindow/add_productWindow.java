package food.productsWindow;

import java.awt.event.ActionListener;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.awt.event.ActionEvent;
import javax.swing.JTextField;
import javax.swing.JToggleButton;

import componentsFood.category;
import componentsFood.product;
import util.abstractAddWindow;
import util.abstractUpdater;
import food.categoryWindow.categoryAPI;

import javax.swing.DefaultListModel;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JScrollPane;

public class add_productWindow extends abstractAddWindow {

    private productAPI theManagerDB = new productAPI();

    private JLabel enterName = new JLabel("Enter the product's NAME: ");
    private JLabel enterPrice = new JLabel("Enter the product's PRICE: ");
    private JLabel chooseCategory = new JLabel("Which category is this product in :");

    private JTextField textFieldName = new JTextField();
    private JTextField textFieldPrice = new JTextField();
    private JList<category> theList = new JList<category>();
    private JScrollPane scrollPane;

    public add_productWindow(abstractUpdater previousWindow) {
        super(previousWindow, "Product", true);
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
                String name = textFieldName.getText();
                String price = textFieldPrice.getText();
                if (theList.getSelectedValue() == null)
                    return;
                if (checkInput(name, price)) {
                    LocalDate dateObj = LocalDate.now();
                    String date = dateObj.format(DateTimeFormatter.ofPattern("yyyy-MM-dd"));
                    product theResultProduct = theManagerDB.addProduct(date, name, Float.parseFloat(price));
                    if (theResultProduct != null) {
                        int category_id = theList.getSelectedValue().getId();
                        new categoryAPI().addProductToCategory(theResultProduct.getId(), category_id);

                        new assist_add_productWindow(temp, theResultProduct).updateToThisMenu();
                    } else {
                        System.out.println("error");
                    }
                }
            }
        });
    }

    private boolean checkInput(String name, String price) {
        if (name.isEmpty() || price.isEmpty()) {
            printErrorGUI();
            return false;
        }
        return true;
    }

    @Override
    public void setBounds() {
        getInputSuccesful().setBounds(250, 120, 250, 25);
        getInputError().setBounds(250, 120, 300, 25);
        getAddButton().setBounds(80, 400, 130, 20);
        getBackButton().setBounds(400, 400, 120, 80);

        enterName.setBounds(10, 20, 200, 25);
        textFieldName.setBounds(210, 20, 165, 25);
        enterPrice.setBounds(10, 50, 200, 25);
        textFieldPrice.setBounds(210, 50, 165, 25);
        chooseCategory.setBounds(10, 80, 200, 25);
        scrollPane.setBounds(240, 80, 170, 200);
    }

    @Override
    public void addToFrame() {
        theFrame.add(getAddButton());
        theFrame.add(getBackButton());
        theFrame.add(enterName);
        theFrame.add(textFieldName);
        theFrame.add(enterPrice);
        theFrame.add(textFieldPrice);
        theFrame.add(chooseCategory);
        theFrame.add(scrollPane);
    }

    private void setList() {
        DefaultListModel<category> listModel = new DefaultListModel<category>();
        for (category tempCategory : new categoryAPI().getProductCategories())
            listModel.addElement(tempCategory);
        theList.setModel(listModel);
        scrollPane = new JScrollPane(theList, JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED,
                JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
    }
}
