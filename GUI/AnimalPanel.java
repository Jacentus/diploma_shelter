package pl.com.jmotyka.GUI;

import pl.com.jmotyka.animals.Animal;

import javax.swing.*;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import java.awt.*;

public abstract class AnimalPanel extends JPanel implements PanelCreatable {

    private JLabel nameLabel, genderLabel, healthLabel, color1Label, color2Label,color3Label, bodytypeLabel, sterilLabel, collarLabel, nameTagLabel, tailLabel, weightLabel, heightLabel, lengthLabel;
    private JTextField nameField;
    private JComboBox genderBox, healthBox, color1Box, color2Box, color3Box, bodytypeBox, sterilBox, collarBox, nameTagBox, tailBox;
    private JSlider weight, heightJS, length;
/*    private SubmitButton submit;*/
    protected GridBagConstraints gbc = new GridBagConstraints();

    public AnimalPanel(MainFrame mainFrame) {
        super();
        setVisible(true);
        setLayout(new GridBagLayout());
        setBorder(BorderFactory.createTitledBorder("Provide info about the animal: "));
        initComponents(gbc, mainFrame);
    }

    ///////////////////////////////////////////  components //////////////////////////////////////////////////////////

        public void initComponents(GridBagConstraints gbc, MainFrame mainFrame){

        //////////////////////////// LABELS - 1st COLUMN ///////////////////////////////

            gbc.anchor = GridBagConstraints.FIRST_LINE_START;
            nameLabel = new JLabel("Name: ");
            gbc.gridx = 0;
            gbc.gridy = 0;
            add(nameLabel, gbc);

            genderLabel = new JLabel("Gender: ");
            gbc.gridx = 0;
            gbc.gridy = 1;
            add(genderLabel, gbc);

            healthLabel = new JLabel("Health status: ");
            gbc.gridx = 0;
            gbc.gridy = 2;
            add(healthLabel, gbc);

            color1Label = new JLabel("Primary color: ");
            gbc.gridx = 0;
            gbc.gridy = 3;
            add(color1Label, gbc);

            color2Label = new JLabel("Secondary color (optional): ");
            gbc.gridx = 0;
            gbc.gridy = 4;
            add(color2Label, gbc);

            color3Label = new JLabel("Tertiary color (optional): ");
            gbc.gridx = 0;
            gbc.gridy = 5;
            add(color3Label, gbc);

            bodytypeLabel = new JLabel("Bodytype: ");
            gbc.gridx = 0;
            gbc.gridy = 6;
            add(bodytypeLabel, gbc);

            sterilLabel = new JLabel("Is your pupil sterilized?");
            gbc.gridx = 0;
            gbc.gridy = 7;
            add(sterilLabel, gbc);

            collarLabel = new JLabel("Did your pupil have collar?");
            gbc.gridx = 0;
            gbc.gridy = 8;
            add(collarLabel, gbc);

            nameTagLabel = new JLabel("Did your pupil have a name tag?");
            gbc.gridx = 0;
            gbc.gridy = 9;
            add(nameTagLabel, gbc);

            tailLabel = new JLabel("Did your pupil have a tail?");
            gbc.gridx = 0;
            gbc.gridy = 10;
            add(tailLabel, gbc);

            ////////////////////////// TEXTFIELDS & COMBOBOXES - 2nd COLUMN /////////////////////////////

            nameField = new JTextField(10);
            gbc.gridx = 1;
            gbc.gridy = 0;
            add(nameField, gbc);

            genderBox = new JComboBox(Animal.GenderEnum.values());
            gbc.gridx = 1;
            gbc.gridy = 1;
            add(genderBox, gbc);

            healthBox = new JComboBox(Animal.AnimalHealthStatusEnum.values());
            gbc.gridx = 1;
            gbc.gridy = 2;
            add(healthBox, gbc);

            color1Box = new JComboBox(Animal.Color.values());
            gbc.gridx = 1;
            gbc.gridy = 3;
            add(color1Box, gbc);

            color2Box = new JComboBox(Animal.Color.values());
            gbc.gridx = 1;
            gbc.gridy = 4;
            add(color2Box, gbc);

            color3Box = new JComboBox(Animal.Color.values());
            gbc.gridx = 1;
            gbc.gridy = 5;
            add(color3Box, gbc);

            bodytypeBox = new JComboBox(Animal.Bodytype.values());
            gbc.gridx = 1;
            gbc.gridy = 6;
            add(bodytypeBox, gbc);

            sterilBox = new JComboBox(YN.values());
            gbc.gridx = 1;
            gbc.gridy = 7;
            add(sterilBox, gbc);

            collarBox = new JComboBox(YN.values());
            gbc.gridx = 1;
            gbc.gridy = 8;
            add(collarBox, gbc);

            nameTagBox = new JComboBox(YN.values());
            gbc.gridx = 1;
            gbc.gridy = 9;
            add(nameTagBox, gbc);

            tailBox = new JComboBox(YN.values());
            gbc.gridx = 1;
            gbc.gridy = 10;
            add(tailBox, gbc);

            //////////////////////////////// SLIDERS /////////////////////////////////////

            weight = new JSlider(JSlider.HORIZONTAL, 0, 50, 10);
            weight.setBounds(50, 50, 300, 50);
            weight.setMajorTickSpacing(5);
            weight.setMinorTickSpacing(1);
            weight.setPaintLabels(true);
            weight.setPaintTicks(true);
            weight.addChangeListener(new ChangeListener() {
                @Override
                public void stateChanged(ChangeEvent e) {
                    int weightValue = weight.getValue();
                    weightLabel.setText("Weight: " + weightValue);
                }
            });
            gbc.gridx = 1;
            gbc.gridy = 12;
            add(weight, gbc);

            weightLabel = new JLabel();
            gbc.gridx = 0;
            gbc.gridy = 12;
            add(weightLabel, gbc);

            heightJS = new JSlider(JSlider.HORIZONTAL, 0, 50, 10);
            heightJS.setBounds(50, 50, 300, 50);
            heightJS.setMajorTickSpacing(5);
            heightJS.setMinorTickSpacing(1);
            heightJS.setPaintLabels(true);
            heightJS.setPaintTicks(true);
            heightJS.addChangeListener(new ChangeListener() {
                @Override
                public void stateChanged(ChangeEvent e) {
                    int heightValue = heightJS.getValue();
                    heightLabel.setText("Height: " + heightValue);
                }
            });
            gbc.gridx = 1;
            gbc.gridy = 14;
            add(heightJS, gbc);

            heightLabel = new JLabel();
            gbc.gridx = 0;
            gbc.gridy = 14;
            add(heightLabel, gbc);

            length = new JSlider(JSlider.HORIZONTAL, 0, 200, 30);
            length.setBounds(50, 50, 300, 50);
            length.setMajorTickSpacing(50);
            length.setMinorTickSpacing(1);
            length.setPaintLabels(true);
            length.setPaintTicks(true);
            length.addChangeListener(new ChangeListener() {
                @Override
                public void stateChanged(ChangeEvent e) {
                    int lengthValue = length.getValue();
                    lengthLabel.setText("Length: "+lengthValue);
                }
            });
            gbc.gridx = 1;
            gbc.gridy = 16;
            add(length, gbc);

            lengthLabel = new JLabel();
            gbc.gridx = 0;
            gbc.gridy = 16;
            add(lengthLabel, gbc);

        }

    public enum YN {

        YES,
        NO;

        YN() {
        }
    }

    ////////////////////////////////////// Getters & Setters ////////////////////////////////////////////////////

    public JTextField getNameField() {
        return nameField;
    }

    public void setNameField(JTextField nameField) {
        this.nameField = nameField;
    }

    public JComboBox getGenderBox() {
        return genderBox;
    }

    public void setGenderBox(JComboBox genderBox) {
        this.genderBox = genderBox;
    }

    public JComboBox getHealthBox() {
        return healthBox;
    }

    public void setHealthBox(JComboBox healthBox) {
        this.healthBox = healthBox;
    }

    public JComboBox getColor1Box() {
        return color1Box;
    }

    public void setColor1Box(JComboBox color1Box) {
        this.color1Box = color1Box;
    }

    public JComboBox getColor2Box() {
        return color2Box;
    }

    public void setColor2Box(JComboBox color2Box) {
        this.color2Box = color2Box;
    }

    public JComboBox getColor3Box() {
        return color3Box;
    }

    public void setColor3Box(JComboBox color3Box) {
        this.color3Box = color3Box;
    }

    public JComboBox getBodytypeBox() {
        return bodytypeBox;
    }

    public void setBodytypeBox(JComboBox bodytypeBox) {
        this.bodytypeBox = bodytypeBox;
    }

    public JComboBox getSterilBox() {
        return sterilBox;
    }

    public void setSterilBox(JComboBox sterilBox) {
        this.sterilBox = sterilBox;
    }

    public JComboBox getCollarBox() {
        return collarBox;
    }

    public void setCollarBox(JComboBox collarBox) {
        this.collarBox = collarBox;
    }

    public JComboBox getNameTagBox() {
        return nameTagBox;
    }

    public void setNameTagBox(JComboBox nameTagBox) {
        this.nameTagBox = nameTagBox;
    }

    public JComboBox getTailBox() {
        return tailBox;
    }

    public void setTailBox(JComboBox tailBox) {
        this.tailBox = tailBox;
    }

    public JSlider getWeight() {
        return weight;
    }

    public void setWeight(JSlider weight) {
        this.weight = weight;
    }

    public JSlider getHeightJS() {
        return heightJS;
    }

    public void setHeightJS(JSlider heightJS) {
        this.heightJS = heightJS;
    }

    public JSlider getLength() {
        return length;
    }

    public void setLength(JSlider length) {
        this.length = length;
    }

/*    public SubmitButton getSubmit() {
        return submit;
    }

    public void setSubmit(SubmitButton submit) {
        this.submit = submit;
    }*/
}


