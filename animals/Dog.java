package pl.com.jmotyka.animals;

import pl.com.jmotyka.general.Owner;
import pl.com.jmotyka.general.Uploadable;
import pl.com.jmotyka.general.Shelter;
import pl.com.jmotyka.general.compareStrategies.CompareCats;
import pl.com.jmotyka.general.compareStrategies.CompareDogs;
import pl.com.jmotyka.general.uploadStrategies.UploadDog;

import java.util.Objects;

public class Dog extends Animal {

    private DogBreed breed;

    public Dog(String name, GenderEnum gender, AnimalHealthStatusEnum healthStatus, Color primaryColor, Color secondaryColor, Color tertiaryColor, Bodytype bodytype, Boolean sterilisation, Boolean hadCollar, Boolean hadNameTag, Boolean hasTail, Long heightInCm, Long lengthInCm, Long weightInGrams, DogBreed breed) {
        super(name, gender, healthStatus, primaryColor, secondaryColor, tertiaryColor, bodytype, sterilisation, hadCollar, hadNameTag, hasTail, heightInCm, lengthInCm, weightInGrams);
        this.breed = breed;
        uploadStrategy = new UploadDog();
        compareStrategy = new CompareDogs();
    }

    /////////////////////////////////////   Uploadable interface methods /////////////////////////////////////////////////////

    @Override
    public String getAllParams() {
        return null;
    }

    @Override
    public String createUploadStatement(Uploadable uploadableObject){
        Dog dog = (Dog) uploadableObject;
        String dogParams1 = "'" + dog.getName() + "', '" + dog.getGender().name() + "', '" + dog.getHealthStatus().name() + "', '" + dog.getPrimaryColor().name() + "', '" + dog.getSecondaryColor().name() + "', '"
                + dog.getTertiaryColor().name() + "', '" + dog.getBodytype().name() + "', ";
        boolean steril = dog.getSterilisation(); boolean collar = dog.getHadCollar(); boolean nameTag = dog.getHadNameTag(); boolean tail = dog.getHasTail();
        int id = dog.getSubmittedBy().getSubmitterID();

        String dogParams2 = ", '" + String.valueOf(dog.getHeightInCm()) + "', '" + String.valueOf(dog.getLengthInCm()) + "', '" + String.valueOf(dog.getWeightInGrams())
                + "', '" + dog.getBreed().name() + "'";
        String sqlStatement = null;
        if (dog.getSubmittedBy() instanceof Owner) { //USED FOR LOST ANIMALS BY OWNERS, I.E. LOSTANIMALS TABLE IN DATABASE
            sqlStatement = "INSERT INTO lostdogs (nameDog, genderDog, healthDog, primColorDog, secColorDog, terColorDog, " +
                    "bodytypeDog, sterilDog, collarDog, nametagDog, tailDog, heightDog, lengthDog, weightDog, breedDog, idOwner) VALUES (" + dogParams1 + steril +", "+ collar + ", "+ nameTag + ", "+ tail + dogParams2 + ", " + id + ")";
        }
        else if (dog.getSubmittedBy() instanceof Shelter){ //USED FOR FOUND ANIMALS, I.E. ENSHELTEREDCATS TABLE IN DATABASE
            sqlStatement = "INSERT INTO ensheltereddogs (nameDog, genderDog, healthDog, primColorDog, secColorDog, terColorDog, " +
                    "bodytypeDog, sterilDog, collarDog, nametagDog, tailDog, heightDog, lengthDog, weightDog, breedDog, idShelter) VALUES (" + dogParams1 + steril +", "+ collar + ", "+ nameTag + ", "+ tail + dogParams2 + ", " + id + ")";
        }
        return sqlStatement;
    }

    @Override //PODZIELIĆ INACZEJ INTERFEJSY??
    public String createGetIDStatement(Uploadable uploadableObject){
        System.out.println("TA FUNKCJA ZWRACA NULLA I JEST W DOG !!!!");
        return null;
    }


    ///////////////////////// findable interface methods ////////////////////////////////////////////////


    @Override
    public <T extends Animal> int compareAnimals(T animal, T animalSearchedFor) {
        int similarity = 0;
        Dog dog = (Dog)animal;
        Dog dogSearchedFor = (Dog)animalSearchedFor;
        if (dog.getBodytype() == dogSearchedFor.getBodytype()) { similarity += 10;}
        if (dog.getBreed() == dogSearchedFor.getBreed()) {similarity += 10;}
        if (dog.getHadNameTag() == dogSearchedFor.getHadNameTag() && dog.getName() == dogSearchedFor.getName())
        {
            similarity+=20;
        }

        return similarity;
    }

    @Override
    public String getAnimalDetailsForDisplay() {
        String text = super.getAnimalDetailsForDisplay();
        text += "dog, breed: " + this.getBreed();
        text += "</p>";
        System.out.println("TO JEST TEKST Z FUNKCJI GETANIMALDETAILSTODISPLAY: " + text);
        return text;
    }

    /////////////////////////////////// hashcode & equals overwrite /////////////////////////////////////////////////

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Dog)) return false;
        if (!super.equals(o)) return false;
        Dog dog = (Dog) o;
        return getBreed() == dog.getBreed();
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), getBreed());
    }

    //////////////////////// dog-specific enums /////////////////////////////////////

    public enum DogBreed{
        none, Labrador_Retriever, German_Shepherd_Dog, Poodle, Chihuahua, Golden_Retriever, Yorkshire_Terrier, Dachshund,
        Beagle, Boxer, Miniature_Schnauzer, Shih_Tzu, Bulldog, German_Spitz, English_Cocker_Spaniel, Cavalier_King_Charles_Spaniel, French_Bulldog, Pug,
        Rottweiler, English_Setter, Maltese, English_Springer_Spaniel, German_Shorthaired_Pointer, Staffordshire_Bull_Terrier, Border_Collie,
        Shetland_Sheepdog, Dobermann, West_Highland_White_Terrier, Bernese_Mountain_Dog, Great_Dane, Brittany_Spaniel;
        DogBreed(){}

    }

    ///////////////////////// getters and setters ///////////////////////////////////

    public DogBreed getBreed() {
        return breed;
    }

    public void setBreed(DogBreed breed) {
        this.breed = breed;
    }
}
