package pl.com.jmotyka.general;

import pl.com.jmotyka.GUI.FirstPanel;
import pl.com.jmotyka.GUI.MainFrame;

import javax.swing.*;

public class App {

    public static void main(String[] args) {

        SwingUtilities.invokeLater(new Runnable() {

            @Override
            public void run() {

                MainFrame mainFrame = new MainFrame("KitQFinder");
                FirstPanel firstPanel = new FirstPanel(mainFrame);
                mainFrame.add(firstPanel);

            }
        });
    }
}
