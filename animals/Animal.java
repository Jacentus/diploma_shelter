package pl.com.jmotyka.animals;

import pl.com.jmotyka.general.Submitter;
import pl.com.jmotyka.general.Uploadable;
import java.util.Objects;

public abstract class Animal implements Uploadable, Findable {

    protected String name;
    protected GenderEnum gender;
    protected AnimalHealthStatusEnum healthStatus;
    protected Color primaryColor;
    protected Color secondaryColor;
    protected Color tertiaryColor;
    protected Bodytype bodytype;
    protected Boolean sterilisation;
    protected Boolean hadCollar;
    protected Boolean hadNameTag;
    protected Boolean hasTail;
    protected long heightInCm;
    protected long lengthInCm;
    protected long weightInGrams;
    protected Submitter submittedBy;

    public Animal(String name, GenderEnum gender, AnimalHealthStatusEnum healthStatus, Color primaryColor, Color secondaryColor, Color tertiaryColor, Bodytype bodytype, Boolean sterilisation, Boolean hadCollar, Boolean hadNameTag, Boolean hasTail, Long heightInCm, Long lengthInCm, Long weightInGrams) {
        this.name = name;
        this.gender = gender;
        this.healthStatus = healthStatus;
        this.primaryColor = primaryColor;
        this.secondaryColor = secondaryColor;
        this.tertiaryColor = tertiaryColor;
        this.bodytype = bodytype;
        this.sterilisation = sterilisation;
        this.hadCollar = hadCollar;
        this.hadNameTag = hadNameTag;
        this.hasTail = hasTail;
        this.heightInCm = heightInCm;
        this.lengthInCm = lengthInCm;
        this.weightInGrams = weightInGrams;
    }

    public String getAnimalDetailsForDisplay(){ //DO UZUPEŁNIENIA
        String text = "<p>";
        text += "We've found a " + this.getPrimaryColor() + ", " + this.getBodytype() + " ";
        return text;
    }

    @Override
    public <S extends Submitter, T extends Animal> S retrieveSubmitterFromDB(T animal) {

        System.out.println("TA FUNKCJA ZWRACA NULLA I JEST W ANIMAL!!!");

        return null;
    }

    //////////////////////////////////////////////////////////////////////////////////////////

    public enum GenderEnum {

        male("Samiec", "Male"),
        female("Samica", "Female"),
        unspecified("Nie podano", "Unspecified");

        private String valuePL;
        private String valueENG;

        GenderEnum(String valuePL, String valueENG) {
            this.valuePL = valuePL;
            this.valueENG = valueENG;
        }

    }

    public enum Color{
        amber, ash, asphalt, auburn, avocado, aquamarine, azure, beige, bisque,black,blue,bone, bordeaux,brass,bronze,brown,burgundy,
        camel,caramel,canary,celeste,cerulean,champagne,charcoal,chartreuse,chestnut,chocolate,citron,claret,coal,cobalt,coffee,coral,corn,cream,crimson,cyan,
        denim,desert,ebony,ecru,emerald,feldspar,fuchsia,gold,gray,green,heather,indigo,ivory,jet,khaki,lime,magenta,maroon,mint,navy,olive,orange,
        pink,plum,purple,red,rust,salmon,sienna,silver,snow,steel,tan,teal,tomato,violet,white,yellow, none
    }

    public enum AnimalHealthStatusEnum {
        healthy("Zdrowy", "Healthy"),
        diseased("Chory", "Diseased"),
        chronic("Przewlekle chory", "Chronically ill"),
        disabled("Niepełnosprawny", "Disabled"),
        unspecified("Nieokreślony", "Unspecified");

        private static AnimalHealthStatusEnum health;

        private String valuePL;
        private String valueENG;

        AnimalHealthStatusEnum(String valuePL, String valueENG) {
            this.valuePL = valuePL;
            this.valueENG = valueENG;
        }
    }

        public enum Bodytype {
            obese, overweight, normal, thin, anorexic}

    /////////////////////////////////////////////////////////////////////////////////

    public abstract String createUploadStatement(Uploadable uploadableObject);

    /////////////////////////////////// hashcode & equals overwrite /////////////////////////////////////////////////

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Animal)) return false;
        Animal animal = (Animal) o;
        return getHeightInCm() == animal.getHeightInCm() &&
                getLengthInCm() == animal.getLengthInCm() &&
                getWeightInGrams() == animal.getWeightInGrams() &&
                Objects.equals(getName(), animal.getName()) &&
                getGender() == animal.getGender() &&
                getHealthStatus() == animal.getHealthStatus() &&
                getPrimaryColor() == animal.getPrimaryColor() &&
                getSecondaryColor() == animal.getSecondaryColor() &&
                getTertiaryColor() == animal.getTertiaryColor() &&
                getBodytype() == animal.getBodytype() &&
                Objects.equals(getSterilisation(), animal.getSterilisation()) &&
                Objects.equals(getHadCollar(), animal.getHadCollar()) &&
                Objects.equals(getHadNameTag(), animal.getHadNameTag()) &&
                Objects.equals(getHasTail(), animal.getHasTail())/*&&
                Objects.equals(getSubmittedBy(), animal.getSubmittedBy())*/;
    }

    @Override
    public int hashCode() {
        return Objects.hash(getName(), getGender(), getHealthStatus(), getPrimaryColor(), getSecondaryColor(), getTertiaryColor(), getBodytype(), getSterilisation(), getHadCollar(), getHadNameTag(), getHasTail(), getHeightInCm(), getLengthInCm(), getWeightInGrams(), getSubmittedBy());
    }

    ////////////////////////////////// Getters & Setters /////////////////////////////////////


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public GenderEnum getGender() {
        return gender;
    }

    public void setGender(GenderEnum gender) {
        this.gender = gender;
    }

    public AnimalHealthStatusEnum getHealthStatus() {
        return healthStatus;
    }

    public void setHealthStatus(AnimalHealthStatusEnum healthStatus) {
        this.healthStatus = healthStatus;
    }

    public Color getPrimaryColor() {
        return primaryColor;
    }

    public void setPrimaryColor(Color primaryColor) {
        this.primaryColor = primaryColor;
    }

    public Color getSecondaryColor() {
        return secondaryColor;
    }

    public void setSecondaryColor(Color secondaryColor) {
        this.secondaryColor = secondaryColor;
    }

    public Color getTertiaryColor() {
        return tertiaryColor;
    }

    public void setTertiaryColor(Color tertiaryColor) {
        this.tertiaryColor = tertiaryColor;
    }

    public Bodytype getBodytype() {
        return bodytype;
    }

    public void setBodytype(Bodytype bodytype) {
        this.bodytype = bodytype;
    }

    public Boolean getSterilisation() {
        return sterilisation;
    }

    public void setSterilisation(Boolean sterilisation) {
        this.sterilisation = sterilisation;
    }

    public Boolean getHadCollar() {
        return hadCollar;
    }

    public void setHadCollar(Boolean hadCollar) {
        this.hadCollar = hadCollar;
    }

    public Boolean getHadNameTag() {
        return hadNameTag;
    }

    public void setHadNameTag(Boolean hadNameTag) {
        this.hadNameTag = hadNameTag;
    }

    public Boolean getHasTail() {
        return hasTail;
    }

    public void setHasTail(Boolean hasTail) {
        this.hasTail = hasTail;
    }

    public Long getHeightInCm() {
        return heightInCm;
    }

    public void setHeightInCm(Long heightInCm) {
        this.heightInCm = heightInCm;
    }

    public Long getLengthInCm() {
        return lengthInCm;
    }

    public void setLengthInCm(Long lengthInCm) {
        this.lengthInCm = lengthInCm;
    }

    public Long getWeightInGrams() {
        return weightInGrams;
    }

    public void setWeightInGrams(Long weightInGrams) {
        this.weightInGrams = weightInGrams;
    }

    public Submitter getSubmittedBy() {
        return submittedBy;
    }

    public void setSubmittedBy(Submitter submittedBy) {
        this.submittedBy = submittedBy;
    }

}

