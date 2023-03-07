package navigation.home.items;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
import java.awt.image.BufferedImage;

import javax.imageio.ImageIO;
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
import util.buttonFormatters.iSelectionButton;
import util.buttonFormatters.selectionButtonFormatter;

public class editItems {

    private JLabel imageJLabel = new JLabel();
    private JTextArea descriptionJTextArea = new JTextArea();
    private JScrollPane jScrollPane1 = new JScrollPane(descriptionJTextArea, JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED,
            JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
    private JButton editImageButton = new JButton();
    private JButton editDescriptionButton = new JButton();
    private menu currentMenu = null;
    private product currentProduct = null;

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

        descriptionJTextArea.setColumns(20);
        descriptionJTextArea.setRows(5);
        jScrollPane1.setViewportView(descriptionJTextArea);

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
        editImageButton.addMouseListener(new MouseListener() {

            public void mouseClicked(MouseEvent e) {
                JnaFileChooser chooser = new JnaFileChooser();
                chooser.setMode(JnaFileChooser.Mode.Files);
                chooser.addFilter("jpg");
                if (chooser.showOpenDialog(theFrame)) {
                    File theImage = chooser.getSelectedFile();
                    byte[] byteImage = jpegToBytes(theImage);
                    if (byteImage == null) {
                        // print error somehow
                        return;
                    }

                }
            }

            public void mousePressed(MouseEvent e) {
            }

            public void mouseReleased(MouseEvent e) {
            }

            public void mouseEntered(MouseEvent e) {
            }

            public void mouseExited(MouseEvent e) {
            }
        });
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

    private void getImage() {
        imageJLabel.setText("image");
    }

}