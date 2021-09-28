package pl.com.jmotyka.general.compareStrategies;

import pl.com.jmotyka.animals.Animal;
import pl.com.jmotyka.animals.Cat;

public class CompareCats implements CompareStrategy {

    @Override
    public <T extends Animal> int compareAnimals(T animal, T animalSearchedFor) {
        int similarity = 0;
        Cat cat = (Cat)animal;
        Cat catSearchedFor = (Cat)animalSearchedFor;
        if (cat.getBodytype() == catSearchedFor.getBodytype()) { similarity += 10;}
        if (cat.getPattern() == catSearchedFor.getPattern()) {similarity += 10;}
        if (cat.getBreed() == catSearchedFor.getBreed()) {similarity += 10;}
        if (cat.getHadNameTag() == catSearchedFor.getHadNameTag() && cat.getName() == catSearchedFor.getName())
        {
            similarity+=20;
        }

        return similarity;
    }

    @Override
    public Animal findBestMatch() {
        return null;
    }


/*
    ArrayList<T> (list, Animal animalSearchedFor){
            System.out.println("robie resultpanel z nowej klasy AnimalComparer. Bede porownywal zwierzeta typu: " + animalSearchedFor.getClass());

            /* resultToBeDisplayed.setBorder(BorderFactory.createTitledBorder("Thank you for using KitQFinder!"));
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

            /* MUSZE DODAC OPCJE NA PUSTE LISTY ZE NIE MA KOTA INFORMACJA

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
            resultToBeDisplayed.add(Box.createRigidArea(new Dimension(0, 5)));
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
                /*            resultToBeDisplayed.add(Box.createRigidArea(new Dimension(0, 5)));
            }


        }




    } */

}