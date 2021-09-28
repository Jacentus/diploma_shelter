package pl.com.jmotyka.general.compareStrategies;

import pl.com.jmotyka.animals.Animal;

public interface CompareStrategy {

    <T extends Animal> int compareAnimals(T animal1, T animal2);

    Animal findBestMatch();

}
