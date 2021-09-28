package pl.com.jmotyka.GUI;

import pl.com.jmotyka.general.Submitter;
import pl.com.jmotyka.general.Uploadable;

import javax.swing.*;

public interface PanelCreatable {

    Uploadable createFromPanel(PanelCreatable panel);

    void submitButtonEvent(PanelCreatable panel, MainFrame mainFrame, Submitter submittedBy);

    void changePanelShown(MainFrame mainFrame, JPanel panel);

}
