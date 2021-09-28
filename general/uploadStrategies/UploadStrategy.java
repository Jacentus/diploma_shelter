package pl.com.jmotyka.general.uploadStrategies;

import pl.com.jmotyka.animals.Animal;
import pl.com.jmotyka.general.Uploadable;

public interface UploadStrategy {

    void upload(Animal animal);

    //String retrieveIDfromDB(Animal Animal); to bylo w interfejsie uploadable - wyrzucic to na osobna stgrategie ?

}
