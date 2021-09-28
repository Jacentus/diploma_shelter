package pl.com.jmotyka.GUI;

import pl.com.jmotyka.animals.Animal;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class  ContactButton <T extends Animal> extends JButton {

    public ContactButton(T animal){
        super("Contact details");
        this.addActionListener(new ActionListener() {
            @Override // wiem ze to pokazuje zwykly text. gdzie jest blad?!
            public void actionPerformed(ActionEvent e) {
                String text = animal.getSubmittedBy().showContactData();
                System.out.println(text);
                JOptionPane.showMessageDialog(getParent(), text);
            }
        });
    }
}
