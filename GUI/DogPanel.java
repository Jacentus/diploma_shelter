package pl.com.jmotyka.GUI;

import pl.com.jmotyka.animals.Animal;
import pl.com.jmotyka.animals.Dog;
import pl.com.jmotyka.animals.Findable;
import pl.com.jmotyka.dbConnectvity.MySQLCon;
import pl.com.jmotyka.general.AnimalComparer;
import pl.com.jmotyka.general.Submitter;
import pl.com.jmotyka.general.Uploadable;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

public class DogPanel extends AnimalPanel implements PanelCreatable {

    private JLabel breedLabel;
    private JComboBox breedBox;
    private SubmitButton submitButton;

    private Submitter submittedBy;

    public DogPanel(MainFrame mainFrame, Submitter submittedBy) {
        super(mainFrame);
        this.submittedBy = submittedBy;
        setBorder(BorderFactory.createTitledBorder("Provide info about your dog: "));
        initComponents(mainFrame);
    }

    //////////////////////////////// UPLOADABLE INTERFACE METHODS /////////////////////////////////////

    @Override
    public Uploadable createFromPanel(PanelCreatable panelCreatablePanel) {
        DogPanel panel = (DogPanel) panelCreatablePanel;
        boolean tail, steril, collar, nametag;
        if(panel.getTailBox().getSelectedItem() == YN.YES) tail = true; else tail = false;
        if(panel.getSterilBox().getSelectedItem()==YN.YES) steril = true;else steril=false;
        if(panel.getCollarBox().getSelectedItem()==YN.YES) collar=true;else collar=false;
        if(panel.getNameTagBox().getSelectedItem()==YN.YES) nametag=true;else nametag=false;

        Dog dog = new Dog(panel.getNameField().getText(), (Animal.GenderEnum) panel.getGenderBox().getSelectedItem(), (Animal.AnimalHealthStatusEnum) panel.getHealthBox().getSelectedItem(), (Animal.Color) panel.getColor1Box().getSelectedItem(), (Animal.Color) panel.getColor2Box().getSelectedItem(), (Animal.Color) panel.getColor3Box().getSelectedItem(), (Animal.Bodytype) panel.getBodytypeBox().getSelectedItem(),
                steril, collar, nametag, tail, (long)panel.getHeightJS().getValue(), (long)panel.getLength().getValue(), (long)panel.getWeight().getValue(), (Dog.DogBreed)panel.getBreedBox().getSelectedItem());

        dog.setSubmittedBy(panel.getSubmittedBy());

        System.out.println("ID OWNERA WRZUCONE DO DOGA = " + dog.getSubmittedBy().getSubmitterID());

        return dog;
    }

    @Override
    public void submitButtonEvent(PanelCreatable panel, MainFrame mainFrame, Submitter submittedBy) {
        DogPanel dogPanel = (DogPanel)panel;
        Dog newDog = (Dog)createFromPanel(dogPanel);
        newDog.setSubmittedBy(submittedBy); //pies zaczyna miec wlasciciela, wlasciciel ma już ID. DLACZEGO NIESPOJNOSC DANYCH?!?!?!
        MySQLCon con = new MySQLCon();
        ArrayList<Animal> list = con.searchInDB(newDog);
        con.sendToDB(newDog);
        AnimalComparer dogComparer = new AnimalComparer();
        JPanel resultPanel = dogComparer.createResultPanel(list, newDog);
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

    ///////////////////////////////////////////  COMPONENTS //////////////////////////////////////////////////////////

    private void initComponents(MainFrame mainFrame){
        //////////////////// 1st COLUMN     //////////////////////////////////////

        breedLabel = new JLabel("Breed: ");
        gbc.gridx = 0;
        gbc.gridy = 18;
        add(breedLabel, gbc);

        ////////////////////// 2nd COLUMN ///////////////////////////////////////

        breedBox = new JComboBox(Dog.DogBreed.values());
        gbc.gridx = 1;
        gbc.gridy = 18;
        add(breedBox, gbc);

        ////////////////////////// BUTTON ////////////////////////////////////////

        submitButton = new SubmitButton("Submit from DOG", this, mainFrame, submittedBy);
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

    public Submitter getSubmittedBy() {
        return submittedBy;
    }

    public void setSubmittedBy(Submitter submittedBy) {
        this.submittedBy = submittedBy;
    }
}

