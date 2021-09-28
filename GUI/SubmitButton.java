package pl.com.jmotyka.GUI;

import pl.com.jmotyka.general.Submitter;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class SubmitButton extends JButton {

    private PanelCreatable panel;
    private MainFrame mainFrame;

    public SubmitButton(String text, PanelCreatable panel, MainFrame mainFrame){ //button moze przyjac wiecej parametrow, np nazwe tablicy w bazie danych? a odpowiedni panel/klasa da co ma tam wrzucic
        super(text);
        this.panel = panel;
        System.out.println("DZIALA TRZYARGUMENTOWY KONSTRUKTOR PRZYCISKU BEZ ACTION LISTENERA");
        /*        this.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
*//**//*                    Savable submittedBy = panel.createFromPanel(panel);
                    panel.submitButtonEvent(panel, mainFrame, submittedBy); //czy to zadziała?!*//*
                }
    });*/
    }

    public SubmitButton(String text, PanelCreatable panel, MainFrame mainFrame, Submitter submittedBy){
        super(text);
        System.out.println("DZIALA CZTEROARGUMENTOWY KONSTRUKTOR PRZYCISKU");
        this.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                panel.submitButtonEvent(panel, mainFrame, submittedBy);
            }
        });
    }

    public PanelCreatable getPanel() {
        return panel;
    }

    public void setPanel(PanelCreatable panel) {
        this.panel = panel;
    }

/*      NIEUDANA PROBA NESTED CLASS

        private class MyActionListener implements ActionListener{

        MyActionListener(Submittable panel, MainFrame mainFrame, Savable submittedBy){
            super();
        }

        @Override
        public void actionPerformed(ActionEvent e) {
            panel.submitButtonEvent(panel, mainFrame, panel.createFromPanel(panel));
        }
    }*/
}

