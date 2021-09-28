package pl.com.jmotyka.GUI;

import pl.com.jmotyka.dbConnectvity.MySQLCon;
import pl.com.jmotyka.general.Address;
import pl.com.jmotyka.general.Submitter;
import pl.com.jmotyka.general.Uploadable;
import pl.com.jmotyka.general.Shelter;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ShelterPanel extends JPanel implements PanelCreatable {

    private JLabel nameLabel, phoneLabel, emailLabel, streetLabel, streetNoLabel, flatNoLabel, postCodeLabel, cityLabel, countryLabel;
    private JTextField nameField, phoneField, emailField, streetField, streetNoField, flatNoField, postCodeField, cityField, countryField;
    private SubmitButton FoundDogButton, FoundCatButton;
    private GridBagConstraints gbc = new GridBagConstraints();

    public ShelterPanel(MainFrame mainFrame){
    super();
    setVisible(true);
    initComponents(mainFrame);
}

///////////////////////////////////////// PANELCREATABLE INTERFACE METHODS //////////////////////////////////////////////
    @Override
    public Uploadable createFromPanel(PanelCreatable panelCreatablePanel){
        ShelterPanel panel = (ShelterPanel) panelCreatablePanel;
        Shelter newShelter = new Shelter(panel.getNameField().getText(),
                new Address(panel.getStreetField().getText(), panel.getStreetNoField().getText(), panel.getFlatNoField().getText(), panel.getPostCodeField().getText(), panel.getCityField().getText(), panel.getCountryField().getText()),
                panel.getPhoneField().getText(), panel.getEmailField().getText());
        return newShelter;
    }

    @Override
    public void submitButtonEvent(PanelCreatable panel, MainFrame mainFrame, Submitter submittedBy) {
    }

    @Override
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

    ///////////////////////////////////////////  components //////////////////////////////////////////////////////////

    private void initComponents(MainFrame mainFrame){
        setLayout(new GridBagLayout());
        setBorder(BorderFactory.createTitledBorder("Provide info about the shelter:"));

        //////////////////////////// LABELS - 1st COLUMN ///////////////////////////////

        gbc.anchor = GridBagConstraints.FIRST_LINE_START;
        nameLabel = new JLabel("Name of the shelter: ");
        gbc.gridx = 0;
        gbc.gridy = 0;
        add(nameLabel, gbc);

        phoneLabel = new JLabel("Phone number: ");
        gbc.gridx = 0;
        gbc.gridy = 1;
        add(phoneLabel, gbc);

        emailLabel = new JLabel("E-mail address: ");
        gbc.gridx = 0;
        gbc.gridy = 2;
        add(emailLabel, gbc);

        streetLabel = new JLabel("Street name: ");
        gbc.gridx = 0;
        gbc.gridy = 3;
        add(streetLabel, gbc);

        streetNoLabel = new JLabel("Street number: ");
        gbc.gridx = 0;
        gbc.gridy = 4;
        add(streetNoLabel, gbc);

        flatNoLabel = new JLabel("Flat no:");
        gbc.gridx = 0;
        gbc.gridy = 5;
        add(flatNoLabel, gbc);

        postCodeLabel = new JLabel("Post code: ");
        gbc.gridx = 0;
        gbc.gridy = 6;
        add(postCodeLabel, gbc);

        cityLabel = new JLabel("City: ");
        gbc.gridx = 0;
        gbc.gridy = 7;
        add(cityLabel, gbc);

        countryLabel = new JLabel("Country: ");
        gbc.gridx = 0;
        gbc.gridy = 8;
        add(countryLabel, gbc);

        ////////////////////////// TEXTFIELDS - 2nd COLUMN /////////////////////////////

        nameField = new JTextField(10);
        gbc.gridx = 1;
        gbc.gridy = 0;
        add(nameField, gbc);

        phoneField = new JTextField(10);
        gbc.gridx = 1;
        gbc.gridy = 1;
        add(phoneField, gbc);

        emailField = new JTextField(10);
        gbc.gridx = 1;
        gbc.gridy = 2;
        add(emailField, gbc);

        streetField = new JTextField(10);
        gbc.gridx = 1;
        gbc.gridy = 3;
        add(streetField, gbc);

        streetNoField = new JTextField(10);
        gbc.gridx = 1;
        gbc.gridy = 4;
        add(streetNoField, gbc);

        flatNoField = new JTextField(10);
        gbc.gridx = 1;
        gbc.gridy = 5;
        add(flatNoField, gbc);

        postCodeField = new JTextField(10);
        gbc.gridx = 1;
        gbc.gridy = 6;
        add(postCodeField, gbc);

        cityField = new JTextField(10);
        gbc.gridx = 1;
        gbc.gridy = 7;
        add(cityField, gbc);

        countryField = new JTextField(10);
        gbc.gridx = 1;
        gbc.gridy = 8;
        add(countryField, gbc);

        //////////////////////////////////////  BUTTONS ///////////////////////////////////////////

        FoundDogButton = new SubmitButton("I've found a dog", this, mainFrame);
        FoundDogButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Shelter newShelter = (Shelter) FoundDogButton.getPanel().createFromPanel(FoundDogButton.getPanel());
                MySQLCon con = new MySQLCon();
                Boolean isDuplicate = con.checkDBforDuplicates(newShelter);
                if (isDuplicate == false){
                    con.sendToDB(newShelter);
                    int id = con.retrieveID(newShelter);
                    newShelter.setSubmitterID(id);
                    System.out.println("ID NOWO UTWORZONEGO SHELTER = " + id);
                    changePanelShown(mainFrame, new DogPanel(mainFrame, newShelter));
                    JOptionPane.showMessageDialog(getParent(),
                            "NEW SHELTER HAS BEEN CREATED AND ADDED TO BASE. A LOST DOG WILL BE CREATED");
                }
                else if (isDuplicate == true){
                    int id = con.retrieveID(newShelter);
                    newShelter.setSubmitterID(id);
                    System.out.println("ID istniejacego juz schroniska = " + id);
                    changePanelShown(mainFrame, new DogPanel(mainFrame, newShelter));
                    JOptionPane.showMessageDialog(getParent(),
                            "SHELTER ALREADY IN THE DATABASE. A LOST DOG WILL BE CREATED");
                }
            }
        });

        FoundCatButton = new SubmitButton("I've found a cat", this, mainFrame);
        FoundCatButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
            Shelter newShelter = (Shelter) FoundCatButton.getPanel().createFromPanel(FoundCatButton.getPanel());
            MySQLCon con = new MySQLCon();
            Boolean isDuplicate = con.checkDBforDuplicates(newShelter);
                if (isDuplicate == false){
                    con.sendToDB(newShelter);
                    int id = con.retrieveID(newShelter);
                    newShelter.setSubmitterID(id);
                    System.out.println("ID NOWO UTWORZONEGO SHELTER = " + id);
                    changePanelShown(mainFrame, new CatPanel(mainFrame, newShelter));
                    JOptionPane.showMessageDialog(getParent(),
                            "NEW SHELTER HAS BEEN CREATED AND ADDED TO BASE. A LOST CAT WILL BE CREATED");
                }
                else if (isDuplicate == true){
                    int id = con.retrieveID(newShelter);
                    newShelter.setSubmitterID(id);
                    System.out.println("ID istniejacego juz schroniska = " + id);
                    changePanelShown(mainFrame, new CatPanel(mainFrame, newShelter));
                    JOptionPane.showMessageDialog(getParent(),
                            "SHELTER ALREADY IN THE DATABASE. A LOST CAT WILL BE CREATED");
                }
            }
        });

        gbc.anchor = GridBagConstraints.SOUTH;
        gbc.gridx = 1;
        gbc.gridy = 100;
        add(FoundDogButton, gbc);

        gbc.gridx = 2;
        add(FoundCatButton, gbc);
        gbc.gridy = 100;
    }

///////////////////////////////////// getters and setters ////////////////////////////////////////////////////////

    public JTextField getNameField() {
        return nameField;
    }

    public void setNameField(JTextField nameField) {
        this.nameField = nameField;
    }

    public JTextField getPhoneField() {
        return phoneField;
    }

    public void setPhoneField(JTextField phoneField) {
        this.phoneField = phoneField;
    }

    public JTextField getEmailField() {
        return emailField;
    }

    public void setEmailField(JTextField emailField) {
        this.emailField = emailField;
    }

    public JTextField getStreetField() {
        return streetField;
    }

    public void setStreetField(JTextField streetField) {
        this.streetField = streetField;
    }

    public JTextField getStreetNoField() {
        return streetNoField;
    }

    public void setStreetNoField(JTextField streetNoField) {
        this.streetNoField = streetNoField;
    }

    public JTextField getFlatNoField() {
        return flatNoField;
    }

    public void setFlatNoField(JTextField flatNoField) {
        this.flatNoField = flatNoField;
    }

    public JTextField getPostCodeField() {
        return postCodeField;
    }

    public void setPostCodeField(JTextField postCodeField) {
        this.postCodeField = postCodeField;
    }

    public JTextField getCityField() {
        return cityField;
    }

    public void setCityField(JTextField cityField) {
        this.cityField = cityField;
    }

    public JTextField getCountryField() {
        return countryField;
    }

    public void setCountryField(JTextField countryField) {
        this.countryField = countryField;
    }

    public SubmitButton getFoundDogButton() {
        return FoundDogButton;
    }

    public void setFoundDogButton(SubmitButton foundDogButton) {
        FoundDogButton = foundDogButton;
    }

    public SubmitButton getFoundCatButton() {
        return FoundCatButton;
    }

    public void setFoundCatButton(SubmitButton foundCatButton) {
        FoundCatButton = foundCatButton;
    }

    public GridBagConstraints getGbc() {
        return gbc;
    }

    public void setGbc(GridBagConstraints gbc) {
        this.gbc = gbc;
    }
}
