package pl.com.jmotyka.GUI;
import pl.com.jmotyka.animals.Animal;
import pl.com.jmotyka.dbConnectvity.MySQLCon;
import pl.com.jmotyka.general.Owner;
import pl.com.jmotyka.general.Submitter;
import pl.com.jmotyka.general.Uploadable;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class OwnerPanel extends JPanel implements PanelCreatable {

    private JLabel nameLabel,surnameLabel,sexLabel, ageLabel, phoneLabel, e_mailLabel;
    private JTextField nameField,surnameField, ageField, phoneField, e_mailField;
    private JComboBox sexField;
    private SubmitButton LostDogButton, LostCatButton;
    private GridBagConstraints gbc = new GridBagConstraints();

    public OwnerPanel(MainFrame mainFrame) {
        super();
        setVisible(true);
        initComponents(mainFrame);

    }

    ///////////////////////////////   UPLOADABLE INTERFACE METHODS //////////////////////////
    @Override
    public Uploadable createFromPanel(PanelCreatable panelCreatablePanel){
        OwnerPanel panel = (OwnerPanel) panelCreatablePanel;
        Owner newOwner = new Owner(panel.getNameField().getText(), panel.getSurnameField().getText(), (Animal.GenderEnum) panel.getSexField().getSelectedItem(), (Integer.parseInt(panel.getAgeField().getText())),
                panel.getPhoneField().getText(), panel.getE_mailField().getText());

        return newOwner;
    }

    @Override
    public void submitButtonEvent(PanelCreatable panel, MainFrame mainFrame, Submitter submittedBy) { }

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

////////////////////////////////////////////// COMPONENTS ///////////////////////////////////////////////////

    void initComponents(MainFrame mainFrame) {

        setLayout(new GridBagLayout());

        setBorder(BorderFactory.createTitledBorder("Provide owner info:"));

                        //////////////// LABELS - FIRST COLUMN /////////////////////////////////

        gbc.anchor = GridBagConstraints.FIRST_LINE_START;
        nameLabel = new JLabel("Name: ");
        gbc.gridx = 0;
        gbc.gridy = 0;
        add(nameLabel, gbc);

        surnameLabel = new JLabel("Surname: ");
        gbc.gridx = 0;
        gbc.gridy = 1;
        add(surnameLabel, gbc);

        sexLabel = new JLabel("Sex: ");
        gbc.gridx = 0;
        gbc.gridy = 2;
        add(sexLabel, gbc);

        ageLabel = new JLabel("Age: ");
        gbc.gridx = 0;
        gbc.gridy = 3;
        add(ageLabel, gbc);

        phoneLabel = new JLabel("Phone: ");
        gbc.gridx = 0;
        gbc.gridy = 4;
        add(phoneLabel, gbc);

        e_mailLabel = new JLabel("E-mail: ");
        gbc.gridx = 0;
        gbc.gridy = 5;
        add(e_mailLabel, gbc);

                        /////////////////// TEXT FIELDS - SECOND COLUMN ///////////////////////////

        nameField = new JTextField(10);
        gbc.gridx = 1;
        gbc.gridy = 0;
        add(nameField, gbc);

        surnameField = new JTextField(10);
        gbc.gridx = 1;
        gbc.gridy = 1;
        add(surnameField, gbc);

        sexField = new JComboBox(Animal.GenderEnum.values());
        gbc.gridx = 1;
        gbc.gridy = 2;
        add(sexField, gbc);

        ageField = new JTextField(10);
        gbc.gridx = 1;
        gbc.gridy = 3;
        add(ageField, gbc);

        phoneField = new JTextField(10);
        gbc.gridx = 1;
        gbc.gridy = 4;
        add(phoneField, gbc);

        e_mailField = new JTextField(10);
        gbc.gridx = 1;
        gbc.gridy = 5;
        add(e_mailField, gbc);

                                /////////////////// BUTTONS /////////////////////////////

        LostDogButton = new SubmitButton("I've lost a dog", this, mainFrame);
        LostDogButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Owner newOwner = (Owner) LostDogButton.getPanel().createFromPanel(LostDogButton.getPanel());
                MySQLCon con = new MySQLCon();
                Boolean isDuplicate = con.checkDBforDuplicates(newOwner);
                if (isDuplicate == false) {
                    con.sendToDB(newOwner);
                    int id = con.retrieveID(newOwner);
                    newOwner.setSubmitterID(id);
                    System.out.println("ID NOWO UTWORZONEGO OWNERA = " + id);
                    changePanelShown(mainFrame, new DogPanel(mainFrame, newOwner));
                    JOptionPane.showMessageDialog(getParent(),
                            "NEW OWNER HAS BEEN CREATED AND ADDED TO BASE. A LOST DOG WILL BE CREATED");
                }

                else if (isDuplicate == true){
                    int id = con.retrieveID(newOwner);
                    newOwner.setSubmitterID(id);
                    System.out.println("ID ISTNIEJACEGO JUZ OWNERA: " + id);
                    changePanelShown(mainFrame, new DogPanel(mainFrame, newOwner));
                    JOptionPane.showMessageDialog(getParent(),
                            "OWNER ALREADY IN DATABASE. A LOST DOG WILL BE CREATED");
                }
            }
        });

        LostCatButton = new SubmitButton("I've lost a cat", this, mainFrame);
        LostCatButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Owner newOwner = (Owner) LostCatButton.getPanel().createFromPanel(LostCatButton.getPanel());
                MySQLCon con = new MySQLCon();
                Boolean isDuplicate = con.checkDBforDuplicates(newOwner);
                if (isDuplicate == false) {
                    con.sendToDB(newOwner);
                    int id = con.retrieveID(newOwner);
                    newOwner.setSubmitterID(id);
                    System.out.println("ID NOWO UTWORZONEGO OWNERA = " + id);
                    changePanelShown(mainFrame, new CatPanel(mainFrame, newOwner));
                    JOptionPane.showMessageDialog(getParent(),
                            "NEW OWNER HAS BEEN CREATED AND ADDED TO BASE. A LOST CAT WILL BE CREATED");
                }

                else if (isDuplicate == true){
                    int id = con.retrieveID(newOwner);
                    newOwner.setSubmitterID(id);
                    System.out.println("ID ISTNIEJACEGO JUZ OWNERA: " + id);
                    changePanelShown(mainFrame, new CatPanel(mainFrame, newOwner));
                    JOptionPane.showMessageDialog(getParent(),
                            "OWNER ALREADY IN DATABASE. A LOST CAT WILL BE CREATED");
                }
            }
        });

        gbc.anchor = GridBagConstraints.SOUTH;
        gbc.gridx = 1;
        gbc.gridy = 100;
        add(LostDogButton, gbc);

        gbc.gridx = 2;
        gbc.gridy = 100;
        add(LostCatButton, gbc);

    }

///////////////////////////////////////// Getters and Setters ///////////////////////////////////////////////

    public JTextField getNameField() {
        return nameField;
    }

    public void setNameField(JTextField nameField) {
        this.nameField = nameField;
    }

    public JTextField getSurnameField() {
        return surnameField;
    }

    public void setSurnameField(JTextField surnameField) {
        this.surnameField = surnameField;
    }

    public JComboBox getSexField() {
        return sexField;
    }

    public void setSexField(JComboBox sexField) {
        this.sexField = sexField;
    }

    public JTextField getAgeField() {
        return ageField;
    }

    public void setAgeField(JTextField ageField) {
        this.ageField = ageField;
    }

    public JTextField getPhoneField() {
        return phoneField;
    }

    public void setPhoneField(JTextField phoneField) {
        this.phoneField = phoneField;
    }

    public JTextField getE_mailField() {
        return e_mailField;
    }

}
