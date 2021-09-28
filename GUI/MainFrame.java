package pl.com.jmotyka.GUI;

import javax.swing.*;
import java.awt.*;

public class MainFrame extends JFrame {

    public MainFrame(String title) {
        super(title);
        this.setSize(700, 700);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setVisible(true);
        FirstPanel firstPanel = new FirstPanel(this);
        BorderLayout b = new BorderLayout();
        setLayout(b);

    }

}
