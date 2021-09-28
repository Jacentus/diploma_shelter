package pl.com.jmotyka.general;

import pl.com.jmotyka.GUI.ContactButton;
import pl.com.jmotyka.animals.Animal;

import javax.swing.*;
import javax.swing.border.TitledBorder;
import java.awt.*;
import java.util.ArrayList;

public class AnimalComparer <T extends Animal>{

    public AnimalComparer(){}

    ///////////////////////////////// methods /////////////////////////////////////////////////

    public JPanel createResultPanel(ArrayList<T> list, Animal animalSearchedFor){
        System.out.println("robie resultpanel z nowej klasy AnimalComparer. Bede porownywal zwierzeta typu: " + animalSearchedFor.getClass());

        JPanel resultToBeDisplayed = new JPanel();
        resultToBeDisplayed.setLayout(new BoxLayout(resultToBeDisplayed, BoxLayout.Y_AXIS));
        resultToBeDisplayed.setVisible(true);
        TitledBorder bf = BorderFactory.createTitledBorder("Thank you for using KitQFinder!");
        resultToBeDisplayed.setBorder(bf);

       /* resultToBeDisplayed.setBorder(BorderFactory.createTitledBorder("Thank you for using KitQFinder!"));*/
        ArrayList<T> mostProbableMatch = new ArrayList<T>();
        ArrayList<T> probableMatch = new ArrayList<T>();

        ////// determine what is to be displayed based on probability of a match

        Animal bestmatchSoFar = null;
        int matchProbability ;
        int highestMatchProbabilitySoFar = 0;

        for (T i:list) {
            matchProbability = i.compareAnimals(i, animalSearchedFor);
            System.out.println("TO JEST WARTOSC MATCHPROBABILITY W PETLI" + matchProbability);

            if (matchProbability > highestMatchProbabilitySoFar) {
                highestMatchProbabilitySoFar = matchProbability;
                bestmatchSoFar = i;
            }

            else if (matchProbability >= 30) {
                mostProbableMatch.add(i);
            }
            else if (matchProbability >= 10){
                probableMatch.add(i);
            }
        }
        System.out.println("DLUGOSC LISTY MOSTPROBABLEMATCH:" + mostProbableMatch.size());
        System.out.println("DLUGOSC LISTY PROBABLE MATCH: " +probableMatch.size());

        /* MUSZE DODAC OPCJE NA PUSTE LISTY ZE NIE MA KOTA INFORMACJA */

        ////// first display most probable match together with contact button
        if (bestmatchSoFar != null) {
            String text = "<html> <h1> Hey, I think we may have found a match! </h1></html>";
            resultToBeDisplayed.add (new JLabel(text));
            resultToBeDisplayed.add(createTextField(bestmatchSoFar));
            resultToBeDisplayed.add(Box.createRigidArea(new Dimension(0, 5)));
        }
        ///// secondly display other probable matches
        if (mostProbableMatch.size()>0) {
            String text = "<h2> Here are some other similar animals, maybe it's worth to check them out?</h2>";
            resultToBeDisplayed.add(new JLabel("<html>" + text + "</html>"));
            for (T i:mostProbableMatch) {
                resultToBeDisplayed.add(createTextField(i));
                resultToBeDisplayed.add(Box.createRigidArea(new Dimension(0, 5)));
            }
 /*           resultToBeDisplayed.add(createTextField(mostProbableMatch));
            resultToBeDisplayed.add(Box.createRigidArea(new Dimension(0, 5)));*/
        }
        ///// then display a few less probable matches
        if (probableMatch.size()>0) {
            String text = "<html><h3> These animals are less similar, but maybe it's worth to try...</h3></html>";
            resultToBeDisplayed.add(new JLabel(text));
            for (T i:probableMatch) {
                resultToBeDisplayed.add(createTextField(i));
                resultToBeDisplayed.add(Box.createRigidArea(new Dimension(0, 5)));
            }
            /*resultToBeDisplayed.add(createTextField(probableMatch));*/
/*            resultToBeDisplayed.add(Box.createRigidArea(new Dimension(0, 5)));*/
        }
        bestmatchSoFar.getUploadStrategy().upload(bestmatchSoFar);
        return resultToBeDisplayed;
    }

    public JPanel createTextField(Animal bestMatch) {
        System.out.println("Dziala funkcja createTextField z pojedynczym animalem ze zmiennej bestMatch");
        JPanel result = new JPanel(new FlowLayout());
        JTextPane textField = new JTextPane();
        textField.setContentType("text/html");

        String text = "<html>" + /*<h1> Hey, I think we may have found a match! </h1>*/ "<br> <body> ";
        text+= bestMatch.getAnimalDetailsForDisplay();

        text += "</body></html>";

        textField.setText(text);
        textField.setEditable(false);
        textField.setForeground(Color.green);
        textField.setVisible(true);
        result.add(textField);
        result.add(new ContactButton(bestMatch));

        return result;
    }
}
