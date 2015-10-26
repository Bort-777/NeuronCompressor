package view;


import controller.Controller;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;


/**
 * Created by Asus on 26.10.2015.
 */
public class Menu extends JFrame {

    private Box imagePanels = new Box(BoxLayout.X_AXIS);
    private JPanel sourceImagePanel = new JPanel();
    private JPanel outputImagePanel = new JPanel();

    private Controller controller;

    public Menu(Controller controller) {

        super("Neoron Compressor");
        Toolkit defTool = Toolkit.getDefaultToolkit();
        this.setSize(new Dimension(defTool.getScreenSize().width / 5 * 3, defTool.getScreenSize().height / 5 * 3));
        this.setLocation(defTool.getScreenSize().width / 5, defTool.getScreenSize().height / 5);

        this.controller = controller;

        this.setLayout(new BorderLayout());

        sourceImagePanel.setBorder(BorderFactory.createEtchedBorder());
        imagePanels.add(sourceImagePanel);

        imagePanels.add(Box.createRigidArea(new Dimension(defTool.getScreenSize().width / 5 * 3 / 4, 0)));

        outputImagePanel.setBorder(BorderFactory.createEtchedBorder());
        imagePanels.add(outputImagePanel);

        this.add(imagePanels, BorderLayout.CENTER);

        this.setJMenuBar(createJMenuBar());

        this.add(createJButtonsPanel(), BorderLayout.SOUTH);

        this.setVisible(true);
    }

    private JMenuBar createJMenuBar() {
        JMenuBar jMenuBar = new JMenuBar();

        JMenu fileItem = new JMenu("File");

        // open file item
        JMenuItem openFile = new JMenuItem("Open file");
        openFile.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                JFileChooser jFileChooser = new JFileChooser("..\\image\\");
                jFileChooser.addActionListener(new ActionListener() {
                    public void actionPerformed(ActionEvent e) {
                        File file = jFileChooser.getSelectedFile();
                        try {
                            BufferedImage image = ImageIO.read(file);
                            controller.setSource(image);
                            sourceImagePanel.getGraphics().drawImage(image, 0, 0, null);
                        } catch (IOException ex) {

                        }
                    }
                });
                jFileChooser.showOpenDialog(new JFrame());
            }
        });
        fileItem.add(openFile);
        // open file item

        jMenuBar.add(fileItem);

        return jMenuBar;
    }

    private Box createJButtonsPanel() {
        Box buttons = Box.createVerticalBox();
        buttons.setAlignmentX(CENTER_ALIGNMENT);

        JButton comressButton = new JButton("Comress >>>");
        comressButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                controller.comress();
                outputImagePanel.getGraphics().drawImage(controller.getOutput(), 0, 0, null);
            }
        });
        buttons.add(comressButton);

        return buttons;
    }

}
