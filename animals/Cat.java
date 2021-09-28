package pl.com.jmotyka.animals;

import pl.com.jmotyka.general.Owner;
import pl.com.jmotyka.general.Uploadable;
import pl.com.jmotyka.general.Shelter;
import pl.com.jmotyka.general.compareStrategies.CompareCats;
import pl.com.jmotyka.general.uploadStrategies.UploadLostCat;

import java.util.Objects;

public class Cat extends Animal {

    private Pattern pattern;
    private CatBreed breed;

    public Cat(String name, GenderEnum gender, AnimalHealthStatusEnum healthStatus, Color primaryColor, Color secondaryColor, Color tertiaryColor, Bodytype bodytype, Boolean sterilisation, Boolean hadCollar, Boolean hadNameTag, Boolean hasTail, Long heightInCm, Long lengthInCm, Long weightInGrams, Pattern pattern, CatBreed breed) {
        super(name, gender, healthStatus, primaryColor, secondaryColor, tertiaryColor, bodytype, sterilisation, hadCollar, hadNameTag, hasTail, heightInCm, lengthInCm, weightInGrams);
        this.pattern = pattern;
        this.breed = breed;
        uploadStrategy = new UploadLostCat();
        compareStrategy = new CompareCats();
    }

    ///////////////////////////////////// ANIMAL METHODS ////////////////////////////////////////////////////////////////////


    /////////////////////////////////////   Uploadable interface methods /////////////////////////////////////////////////////


    @Override
    public String getAllParams() {

        String catParams1 = "'" + this.getName() + "', '" + this.getGender().name() + "', '" + this.getHealthStatus().name() + "', '" + this.getPrimaryColor().name() + "', '" + this.getSecondaryColor().name() + "', '"
                + this.getTertiaryColor().name() + "', '" + this.getBodytype().name() + "', ";
        boolean steril =  this.getSterilisation(); boolean collar = this.getHadCollar(); boolean nameTag = this.getHadNameTag(); boolean tail = this.getHasTail();
        int id = this.getSubmittedBy().getSubmitterID();
        String catParams2 = ", '" + String.valueOf(this.getHeightInCm()) + "', '" + String.valueOf(this.getLengthInCm()) + "', '" + String.valueOf(this.getWeightInGrams())
                + "', '" + this.getPattern().name() + "', '" + this.getBreed().name() + "'";
    String allParams = " (nameCat, genderCat, healthCat, primColorCat, secColorCat, terColorCat, " +
        "bodytypeCat, sterilCat, collarCat, nametagCat, tailCat, heightCat, lengthCat, weightCat, patternCat, breedCat, idShelter) VALUES ("
            + catParams1 + steril + ", "+ collar + ", "+ nameTag + ", "+ tail + catParams2 + ", " + id +")";

        return allParams;
    }

    @Override
    public String createUploadStatement(Uploadable uploadableObject){
        Cat cat = (Cat) uploadableObject;
        String catParams1 = "'" + cat.getName() + "', '" + cat.getGender().name() + "', '" + cat.getHealthStatus().name() + "', '" + cat.getPrimaryColor().name() + "', '" + cat.getSecondaryColor().name() + "', '"
                + cat.getTertiaryColor().name() + "', '" + cat.getBodytype().name() + "', ";
        boolean steril =  cat.getSterilisation(); boolean collar = cat.getHadCollar(); boolean nameTag = cat.getHadNameTag(); boolean tail = cat.getHasTail();
        int id = cat.getSubmittedBy().getSubmitterID(); // getUploaderID();
        String catParams2 = ", '" + String.valueOf(cat.getHeightInCm()) + "', '" + String.valueOf(cat.getLengthInCm()) + "', '" + String.valueOf(cat.getWeightInGrams())
                + "', '" + cat.getPattern().name() + "', '" + cat.getBreed().name() + "'";
        String sqlStatement = null;
        if (cat.getSubmittedBy() instanceof Owner) { //USED FOR LOST ANIMALS BY OWNERS, I.E. LOSTANIMALS TABLE IN DATABASE
            sqlStatement = "INSERT INTO lostcats (nameCat, genderCat, healthCat, primColorCat, secColorCat, terColorCat, " +
                    "bodytypeCat, sterilCat, collarCat, nametagCat, tailCat, heightCat, lengthCat, weightCat, patternCat, breedCat, idOwner) VALUES (" + catParams1 + steril +", "+ collar + ", "+ nameTag + ", "+ tail + catParams2 + ", "+ id +")";
        }
        else if (cat.getSubmittedBy() instanceof Shelter){ //USED FOR FOUND ANIMALS, I.E. ENSHELTEREDCATS TABLE IN DATABASE
            sqlStatement = "INSERT INTO enshelteredcats (nameCat, genderCat, healthCat, primColorCat, secColorCat, terColorCat, " +
                    "bodytypeCat, sterilCat, collarCat, nametagCat, tailCat, heightCat, lengthCat, weightCat, patternCat, breedCat, idShelter) VALUES (" + catParams1 + steril + ", "+ collar + ", "+ nameTag + ", "+ tail + catParams2 + ", " + id +")";
        }
        return sqlStatement;
    }

    @Override
    public String createGetIDStatement(Uploadable uploadableObject){

        System.out.println("TA FUNKCJA ZWRACA NULLA I JEST W CAT !!!!");

        return null;
}

    ///////////////////////// findable interface methods ////////////////////////////////////////////////

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
    public String getAnimalDetailsForDisplay() { // do poprawy ale sprawdzmy czy w ogole dziala wpierw. musze te funckje wklepac do animalcomparer i potem wyrzucic result panel z catpaneli i tworzyc nowy panel z animalcomparerera
        String text = super.getAnimalDetailsForDisplay();
        text += "cat, breed: " + this.getBreed() + ", pattern: " + getPattern();
        text += "</p>";
        System.out.println("TO JEST TEKST Z FUNKCJI GETANIMALDETAILSTODISPLAY: " + text);
        return text;
    }

    /////////////////////////////////// hashcode & equals overwrite /////////////////////////////////////////////////

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Cat)) return false;
        if (!super.equals(o)) return false;
        Cat cat = (Cat) o;
        return getPattern() == cat.getPattern() &&
                getBreed() == cat.getBreed();
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), getPattern(), getBreed());
    }

    //////////////////////// cat-specific enums /////////////////////////////////////

    public enum CatBreed{
        none,
        Abyssinian,
        Australian,
        Mist,
        Balinese,
        Bengal,
        Birman,
        Bombay,
        British_Shorthair,
        Burmese,
        Burmilla,
        Cornish_Rex,
        Devon_Rex,
        Egyptian_Mau,
        Exotic_Shorthair,
        Japanese_Bobtail,
        Korat,
        La_Perms,
        Maine_Coon,
        Manx,
        Norwegian_Forest,
        Ocicat,
        Oriental_Shorthair,
        Persian_Longhair,
        Ragdoll,
        Russian_Blue,
        Scottish_Fold,
        Siamese,
        Siberian_Forest,
        Singapura,
        Snowshoe,
        Somali,
        Sphynx,
        Tiffanie,
        Tonkinese,
        Turkish_Van;

        CatBreed() {}
    }

    public enum Pattern{
        solid,
        bicolor,
        tabby,
        tortoiseshell,
        tricolor,
        colorpoint;

        Pattern(){}
    }

///////////////////////// getters and setters ///////////////////////////////////


    public Pattern getPattern() {
        return pattern;
    }

    public void setPattern(Pattern pattern) {
        this.pattern = pattern;
    }

    public CatBreed getBreed() {
        return breed;
    }

    public void setBreed(CatBreed breed) {
        this.breed = breed;
    }

}
