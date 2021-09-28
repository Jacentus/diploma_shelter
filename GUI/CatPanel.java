package pl.com.jmotyka.GUI;

import pl.com.jmotyka.animals.Animal;
import pl.com.jmotyka.animals.Cat;
import pl.com.jmotyka.animals.Findable;
import pl.com.jmotyka.dbConnectvity.MySQLCon;
import pl.com.jmotyka.general.AnimalComparer;
import pl.com.jmotyka.general.Submitter;
import pl.com.jmotyka.general.Uploadable;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

public class CatPanel extends AnimalPanel implements PanelCreatable {

    private JLabel breedLabel, patternLabel;
    private JComboBox breedBox, patternBox;
    private SubmitButton submitButton;

    private Submitter submittedBy;

    public CatPanel(MainFrame mainFrame, Submitter submittedBy) {
        super(mainFrame);
        this.submittedBy = submittedBy;
        setBorder(BorderFactory.createTitledBorder("Provide info about your cat: "));
        initComponents(mainFrame);
    }

    //////////////////////////////// UPLOADABLE INTERFACE METHODS /////////////////////////////////////

    @Override
    public Uploadable createFromPanel(PanelCreatable panelCreatablePanel) {

        CatPanel panel = (CatPanel) panelCreatablePanel;
        boolean tail, steril, collar, nametag;
        if(panel.getTailBox().getSelectedItem() == YN.YES) tail = true; else tail = false;
        if(panel.getSterilBox().getSelectedItem()==YN.YES) steril = true;else steril=false;
        if(panel.getCollarBox().getSelectedItem()==YN.YES) collar=true;else collar=false;
        if(panel.getNameTagBox().getSelectedItem()==YN.YES) nametag=true;else nametag=false;

        Cat cat = new Cat(panel.getNameField().getText(), (Animal.GenderEnum) panel.getGenderBox().getSelectedItem(), (Animal.AnimalHealthStatusEnum) panel.getHealthBox().getSelectedItem(), (Animal.Color) panel.getColor1Box().getSelectedItem(), (Animal.Color) panel.getColor2Box().getSelectedItem(), (Animal.Color) panel.getColor3Box().getSelectedItem(), (Animal.Bodytype) panel.getBodytypeBox().getSelectedItem(),
                steril, collar, nametag, tail, (long)panel.getHeightJS().getValue(), (long)panel.getLength().getValue(), (long)panel.getWeight().getValue(), (Cat.Pattern)panel.getPatternBox().getSelectedItem(), (Cat.CatBreed)panel.getBreedBox().getSelectedItem());

        cat.setSubmittedBy(panel.getSubmittedBy());

        return cat;
    }

    @Override
    public void submitButtonEvent(PanelCreatable panel, MainFrame mainFrame, Submitter submittedBy) {
        CatPanel catPanel = (CatPanel) panel;
        Cat newCat = (Cat)createFromPanel(catPanel);
        newCat.setSubmittedBy(submittedBy); //stworzylem kota z panelu, dodalem mu ownera/shelter
        MySQLCon con = new MySQLCon();
        ArrayList<Cat> list = con.searchInDB(newCat);
        con.sendToDB(newCat);
        AnimalComparer catComparer = new AnimalComparer();
        JPanel resultPanel = catComparer.createResultPanel(list, newCat);
        changePanelShown(mainFrame, resultPanel);
    }

    @Override
    public void changePanelShown(MainFrame mainFrame, JPanel showPanel){
        mainFrame.getContentPane().removeAll();
        mainFrame.getContentPane().add(showPanel, BorderLayout.CENTER);
        mainFrame.getContentPane().repaint();
        mainFrame.getContentPane().doLayout();
        mainFrame.update(mainFrame.getGraphics());
        showPanel.invalidate();
        showPanel.validate();
        showPanel.repaint();
    }


///////////////////////////////////////////  components //////////////////////////////////////////////////////////

    private void initComponents(MainFrame mainFrame){
        //////////////////// 1st COLUMN     //////////////////////////////////////

        breedLabel = new JLabel("Breed: ");
        gbc.gridx = 0;
        gbc.gridy = 18;
        add(breedLabel, gbc);

        patternLabel = new JLabel("Pattern: ");
        gbc.gridx = 0;
        gbc.gridy = 19;
        add(patternLabel, gbc);

        ////////////////////// 2nd COLUMN ///////////////////////////////////////

        breedBox = new JComboBox(Cat.CatBreed.values());
        gbc.gridx = 1;
        gbc.gridy = 18;
        add(breedBox, gbc);

        patternBox = new JComboBox(Cat.Pattern.values());
        gbc.gridx = 1;
        gbc.gridy = 19;
        add(patternBox, gbc);

        ////////////////////////// BUTTON ////////////////////////////////////////

        submitButton = new SubmitButton("Submit from CAT", this, mainFrame, submittedBy);
        gbc.gridx = 4;
        gbc.gridy = 20;
        add(submitButton, gbc);
    }

    ////////////////////////////////////// Getters & Setters ////////////////////////////////////////////////////

    public JComboBox getBreedBox() {
        return breedBox;
    }

    public void setBreedBox(JComboBox breedBox) {
        this.breedBox = breedBox;
    }

    public JComboBox getPatternBox() {
        return patternBox;
    }

    public void setPatternBox(JComboBox patternBox) {
        this.patternBox = patternBox;
    }

    public Submitter getSubmittedBy() {
        return submittedBy;
    }

    public void setSubmittedBy(Submitter submittedBy) {
        this.submittedBy = submittedBy;
    }

}
