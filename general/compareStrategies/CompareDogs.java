package pl.com.jmotyka.general.compareStrategies;

import pl.com.jmotyka.animals.Animal;
import pl.com.jmotyka.animals.Dog;

import java.util.ArrayList;

public class CompareDogs <T extends Animal> implements CompareStrategy {

    private Dog dogSearched;
    private ArrayList<Dog> allDogs;

    public void compareAnimals() {

    }

    @Override
    public <T extends Animal> int compareAnimals(T animal1, T animal2) {
        return 0;
    }

    @Override
    public Animal findBestMatch() {
        return null;
    }


    ////////////////////////////////////////////  Getters & Setters  /////////////////////////////////////////////////

    public Dog getDogSearched() {
        return dogSearched;
    }

    public void setDogSearched(Dog dogSearched) {
        this.dogSearched = dogSearched;
    }

    public ArrayList<Dog> getAllDogs() {
        return allDogs;
    }

    public void setAllDogs(ArrayList<Dog> allDogs) {
        this.allDogs = allDogs;
    }
}
