package navigation.home.items;

import java.awt.Image;
import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
import java.awt.image.BufferedImage;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.event.MouseInputListener;

import componentsFood.menu;
import componentsFood.product;
import jnafilechooser.api.JnaFileChooser;
import util.buttonFormatters.editButtonFormatter;
import util.buttonFormatters.iEditButton;
import util.databaseAPIs.descriptionsAPI;
import util.databaseAPIs.menuAPI;
import util.databaseAPIs.productAPI;
import util.listenersFormatting.booleanWrapper;
import util.listenersFormatting.iTextFieldListener;
import util.listenersFormatting.edit.editTextFieldFListener;

public class editItems {

    private JLabel imageJLabel = new JLabel();
    private JTextArea descriptionJTextArea = new JTextArea(5, 20);
    private JScrollPane jScrollPane1 = new JScrollPane(descriptionJTextArea, JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED,
            JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
    private JButton editImageButton = new JButton();
    private JButton editDescriptionButton = new JButton();
    private menu currentMenu = null;
    private product currentProduct = null;
    private String theDescription;
    private booleanWrapper descriptionPlaceholder = new booleanWrapper(true);

    public editItems(JFrame theFrame, JPanel playground, Object item) {
        if (item instanceof product)
            currentProduct = (product) item;
        else
            currentMenu = (menu) item;
        initComponents(playground);
        addListeners(theFrame, playground);
    }

    private void initComponents(JPanel playground) {
        playground.setBackground(new java.awt.Color(255, 255, 255));

        imageJLabel.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(109, 150, 213), 3, true));

        getImage();
        editImageButton.setText("Edit Image");

        jScrollPane1.setViewportView(descriptionJTextArea);
        getDescription();

        editDescriptionButton.setText("Edit Description");

        javax.swing.GroupLayout playgroundLayout = new javax.swing.GroupLayout(playground);
        playground.setLayout(playgroundLayout);
        playgroundLayout.setHorizontalGroup(
                playgroundLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(playgroundLayout.createSequentialGroup()
                                .addGap(228, 228, 228)
                                .addGroup(playgroundLayout
                                        .createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addGroup(playgroundLayout.createSequentialGroup()
                                                .addComponent(editImageButton)
                                                .addGap(30, 30, 30)
                                                .addComponent(editDescriptionButton,
                                                        javax.swing.GroupLayout.PREFERRED_SIZE, 200,
                                                        javax.swing.GroupLayout.PREFERRED_SIZE))
                                        .addGroup(playgroundLayout
                                                .createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                                .addComponent(jScrollPane1)
                                                .addComponent(imageJLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 413,
                                                        javax.swing.GroupLayout.PREFERRED_SIZE)))
                                .addContainerGap(229, Short.MAX_VALUE)));
        playgroundLayout.setVerticalGroup(
                playgroundLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(playgroundLayout.createSequentialGroup()
                                .addGap(67, 67, 67)
                                .addComponent(imageJLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 231,
                                        javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 153,
                                        javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(playgroundLayout
                                        .createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addComponent(editDescriptionButton, javax.swing.GroupLayout.PREFERRED_SIZE, 55,
                                                javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addComponent(editImageButton))
                                .addContainerGap(99, Short.MAX_VALUE)));
    }

    private void addListeners(JFrame theFrame, JPanel playground) {
        editImageButton(theFrame);
        editDescriptionButton();
        applyGenericListeners();
    }

    private void editImageButton(JFrame theFrame) {
        class editImageMethodsHolder implements iEditButton {
            public boolean valuesArePlaceholders() {
                return false;
            }

            public boolean areInputsInvalid() {
                return false;
            }

            public void editFoodComponent() {
                JnaFileChooser chooser = new JnaFileChooser();
                chooser.setMode(JnaFileChooser.Mode.Files);
                chooser.addFilter("images", "jpg");
                if (chooser.showOpenDialog(theFrame)) {
                    File theImage = chooser.getSelectedFile();
                    byte[] byteImage = jpegToBytes(theImage);
                    if (byteImage == null) {
                        // print error somehow
                        return;
                    }
                    if (currentMenu == null)
                        descriptionsAPI.updateImageProduct(currentProduct.getId(), byteImage);
                    else
                        descriptionsAPI.updateImageMenu(currentMenu.getId(), byteImage);
                    getImage();
                }
            }

            public void updatePlaceholders() {
                applyGenericListeners();
                descriptionJTextArea.setForeground(Color.GRAY);
            }
        }
        editButtonFormatter.formatEditButton(editImageButton, new editImageMethodsHolder());
    }

    private void editDescriptionButton() {
        class editMethodsHolder implements iEditButton {
            public boolean valuesArePlaceholders() {

                if (descriptionPlaceholder.getValue()) {
                    // successLabel.setText("Error. You must modify at least one field.");
                    // successLabel.setVisible(true);
                    return true;
                }
                return false;
            }

            public boolean areInputsInvalid() {
                return false;
            }

            public void editFoodComponent() {
                boolean successfulUpdate = true;
                String newDescription = descriptionJTextArea.getText();
                if (currentMenu != null)
                    successfulUpdate = descriptionsAPI.updateDescriptionMenu(currentMenu.getId(), newDescription);
                if (currentProduct != null)
                    successfulUpdate = descriptionsAPI.updateDescriptionProduct(currentProduct.getId(), newDescription);
                theDescription = newDescription;
                /*
                 * if (successfulUpdate)
                 * successLabel.setText("The provider \"" + nameTextField.getText()
                 * + "\" was successfully updated.");
                 * else
                 * successLabel.setText("Something went wrong while updating the provider.");
                 * successLabel.setVisible(true);
                 */
            }

            public void updatePlaceholders() {
                applyGenericListeners();
                descriptionJTextArea.setForeground(Color.GRAY);
            }
        }
        editButtonFormatter.formatEditButton(editDescriptionButton, new editMethodsHolder());
    }

    private void applyGenericListeners() {
        new editTextFieldFListener().applyListenerTextField(descriptionJTextArea, theDescription,
                descriptionPlaceholder,
                false);
    }

    private void getDescription() {
        descriptionJTextArea.setText("Placeholder");
        if (currentMenu != null) {
            theDescription = descriptionsAPI.getDescriptionMenu(currentMenu.getId());
            if (theDescription == null) {
                theDescription = "Placeholder";
                return;
            }
            descriptionJTextArea.setText(theDescription);
        } else {
            theDescription = descriptionsAPI.getDescriptionProduct(currentProduct.getId());
            if (theDescription == null) {
                theDescription = "Placeholder";
                return;
            }
            descriptionJTextArea.setText(theDescription);
        }
    }

    private void getImage() {
        if (currentMenu != null) {
            byte[] byteTemp = descriptionsAPI.getImageMenu(currentMenu.getId());
            if (byteTemp == null)
                return;
            ImageIcon temp = getSizedImage(new ImageIcon(byteTemp));
            imageJLabel.setIcon(temp);
        } else if (currentProduct != null) {
            byte[] byteTemp = descriptionsAPI.getImageProduct(currentProduct.getId());
            if (byteTemp == null)
                return;
            ImageIcon temp = getSizedImage(new ImageIcon(byteTemp));
            imageJLabel.setIcon(temp);
        } else {
            imageJLabel.setText("image placeholder");
            return;
        }
        imageJLabel.revalidate();
        imageJLabel.repaint();
    }

    private ImageIcon getSizedImage(ImageIcon theImage) {
        Image image = theImage.getImage();
        image = image.getScaledInstance(413, 231, Image.SCALE_SMOOTH);
        return new ImageIcon(image);
    }

    private byte[] jpegToBytes(File imageFile) {
        try {
            BufferedImage bufferedImage = ImageIO.read(imageFile);
            ByteArrayOutputStream bos = new ByteArrayOutputStream();
            ImageIO.write(bufferedImage, "jpg", bos);
            return bos.toByteArray();
        } catch (IOException e1) {
            return null;
        }
    }

}