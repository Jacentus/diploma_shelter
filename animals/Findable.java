package pl.com.jmotyka.animals;

import pl.com.jmotyka.general.Submitter;

public interface Findable {

    <T extends Animal> int compareAnimals(T animal, T animalSearchedFor); //method returns an int which value is determined by probability of match with animalSearchedFor

    String getAnimalDetailsForDisplay();

    <S extends Submitter, T extends Animal> S retrieveSubmitterFromDB(T animal);

}
