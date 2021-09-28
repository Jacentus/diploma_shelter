package pl.com.jmotyka.general;

import java.util.Objects;

public class Shelter extends Submitter {

    private String name;
    private Address shelterAddress;
    private String phoneNumber;
    private String email;



    ///////////////////////////////// CONSTRUCTORS ///////////////////////////////

    public Shelter(String name, Address shelterAddress, String phoneNumber, String email) {
        this.name = name;
        this.shelterAddress = shelterAddress;
        this.phoneNumber = phoneNumber;
        this.email = email;
    }

    @Override
    public String getTableName() {
        return new String("ensheltered");
    }

    ///////////////////////////////////// SUBMITTER INHERITED METHODS //////////////////////////////////////////////////////////////////////////

    @Override
    public String createCheckForDuplicateStatement(Submitter submitter) {
        Shelter shelter = (Shelter)submitter;
        String sqlStatement = "SELECT * FROM shelter WHERE emailShelter = '" + shelter.getEmail() + "' AND nameShelter = '" + shelter.getName() + "' AND phoneNoShelter = '" + shelter.getPhoneNumber() + "';";
        System.out.println("taki statement idzie z funkcji checkforduplicate dla Shelter: " + sqlStatement);
        return sqlStatement;
    }

    @Override
    public String showContactData() {
        String text;
        text = "Animal is in " + this.getName() + " shelter. Phone no: " + this.getPhoneNumber() + " E-mail: " + this.getEmail();
        return text;
    }

    /////////////////////////////// UPLOADABLE INTERFACE METHODS //////////////////////////////////////////
//todo
    @Override
    public String getAllParams() {




        return null;
    }

    @Override
    public String createUploadStatement(Uploadable uploadableObject) {
        Shelter shelter = (Shelter) uploadableObject;
        String shelterParams = "'" + shelter.getEmail() + "', '" + shelter.getName() + "', '" + shelter.getShelterAddress().getStreet() + "', '" + shelter.getShelterAddress().getStreetNo() + "', '" + shelter.getShelterAddress().getFlatNo() + "', '" + shelter.getShelterAddress().getPostCode() +"', '" +
                shelter.getShelterAddress().getCity() + "', '" + shelter.getShelterAddress().getCountry() + "', '" +shelter.getPhoneNumber() + "'";
        String sqlStatement = "INSERT INTO shelter (emailShelter, nameShelter, streetShelter, streetNoShelter, flatNoShelter, postCodeShelter, cityShelter, countryShelter, phoneNoShelter) VALUES (" + shelterParams + ")";

        return sqlStatement;
    }

    @Override
    public String createGetIDStatement(Uploadable uploadableObject){
        Shelter shelter = (Shelter) uploadableObject;
        String getIDStatement = "SELECT idShelter FROM shelter WHERE emailShelter = '" + shelter.getEmail() + "' AND nameShelter = '" + shelter.getName() + "' AND phoneNoShelter = '" + shelter.getPhoneNumber() + "';";
        System.out.println("TAKI STATEMENT IDZIE Z FUNKCJI W SHELTER: " + getIDStatement);
        return getIDStatement;
    }

    /////////////////////////////////// hashcode & equals overwrite /////////////////////////////////////////////////

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Shelter)) return false;
        Shelter shelter = (Shelter) o;
        return Objects.equals(getName(), shelter.getName()) &&
                Objects.equals(getShelterAddress(), shelter.getShelterAddress()) &&
                Objects.equals(getPhoneNumber(), shelter.getPhoneNumber()) &&
                getEmail().equals(shelter.getEmail());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getName(), getShelterAddress(), getPhoneNumber(), getEmail());
    }

    //////////////////////// AUTO GENERATED GETTERS & SETTERS //////////////////////////////////

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Address getShelterAddress() {
        return shelterAddress;
    }

    public void setShelterAddress(Address shelterAddress) {
        this.shelterAddress = shelterAddress;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    @Override
    public int getSubmitterID() {
        return SubmitterID;
    }
    @Override
    public void setSubmitterID(int submitterID)
    {
        SubmitterID = submitterID;
    }

}
