package pl.com.jmotyka.GUI;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.File;

public class FirstPanel extends JPanel {

    private JButton lostAnimal;
    private JButton foundAnimal;
    private JLabel background; //nie działa

    public FirstPanel(MainFrame mainFrame) {
            super();
            setVisible(true);
            GridBagLayout gbl = new GridBagLayout();
            setLayout(gbl);
            setBorder(BorderFactory.createTitledBorder("Welcome in KitQFinder! Please choose: "));
  /*          displayBackground();*/
            this.lostAnimal = new JButton("I've lost my pupil!");
            add(lostAnimal);
            this.foundAnimal = new JButton("Add ensheltered animal to base");
            add(foundAnimal);


        lostAnimal.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                changePanelShown(mainFrame, new OwnerPanel(mainFrame));
            }
        });

        foundAnimal.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
               changePanelShown(mainFrame, new ShelterPanel(mainFrame));
            }
        });
    }

    //czy może to byc metoda statyczna z cialem w interfejsie?
    public void changePanelShown(MainFrame mainFrame, JPanel showPanel) {
            mainFrame.getContentPane().removeAll();
            mainFrame.getContentPane().add(showPanel, BorderLayout.CENTER);
            mainFrame.getContentPane().repaint();
            mainFrame.getContentPane().doLayout();
            mainFrame.update(mainFrame.getGraphics());
            showPanel.invalidate();
            showPanel.validate();
            showPanel.repaint();
        }

    public void displayBackground(){ //ta metoda nie działa. Musze znaleźć sposób na zrobienie backgroundu bądź wrzucenie zdjęcia.
        ImageIcon icon = new ImageIcon("C:\\Users\\jmoty\\Desktop\\Coronavirus app\\kitQfinder_titlepage.jpg");
        this.background.setIcon(icon);
        add(background);
        setVisible(true);
    }

}
