package pl.com.jmotyka.general;

import pl.com.jmotyka.animals.Animal;

import java.util.Objects;

public class Owner extends Submitter  {

        private String name;
        private String surname;
        private Animal.GenderEnum sex;
        private Integer age;
        private String phone_no;
        private String e_mail;

    public Owner(String name, String surname, Animal.GenderEnum sex, int age, String phone_no, String e_mail) {
        this.name = name;
        this.surname = surname;
        this.sex = sex;
        this.age = age;
        this.phone_no = phone_no;
        this.e_mail = e_mail;
}

///////////////////////////////////// SUBMITTER INHERITED METHODS //////////////////////////////////////////////////////////////////////////

    @Override
    public String createCheckForDuplicateStatement(Submitter submitter) {
        Owner owner = (Owner)submitter;
        String sqlStatement = "SELECT * FROM owner WHERE nameOwner = '" + owner.getName() + "' AND surnameOwner = '" + owner.getSurname() + "' AND sexOwner = '" + owner.getSex().name() + "' AND ageOwner = '"
                + String.valueOf(owner.getAge()) + "' AND phoneNoOwner = '" + owner.getPhone_no() + "' AND eMailOwner = '" + owner.getE_mail() + "';";
        System.out.println("taki statement idzie z funkcji checkforduplicate dla Ownera: " + sqlStatement);
        return sqlStatement;
    }

    @Override
    public String showContactData() {
        String text;
        text = "Contact owner: " + this.getName() + " " + this.getSurname() + ". Phone no: " + this.getPhone_no() + " E-mail: " + this.getE_mail();
        return text;
    }

/////////////////////////////////////   Uploadable interface methods /////////////////////////////////////////////////////

    @Override
    public String createUploadStatement(Uploadable uploadableObject){
        Owner owner = (Owner) uploadableObject;
        String ownerParams = "'" + owner.getName() + "', '" + owner.getSurname() + "', '" + owner.getSex().name() + "', " + String.valueOf(owner.getAge()) + ", '" + owner.getPhone_no() + "', '" + owner.getE_mail() +"'";
        String sqlStatement = "INSERT INTO owner (nameOwner, surnameOwner, sexOwner, ageOwner, phoneNoOwner, eMailOwner) VALUES (" + ownerParams + ")";
        return sqlStatement;
    }

    @Override
    public String createGetIDStatement(Uploadable uploadableObject){
        Owner owner = (Owner) uploadableObject;
        String getIDStatement = "SELECT idOwner FROM owner WHERE nameOwner = '" + owner.getName() + "' AND surnameOwner = '" + owner.getSurname() + "' AND sexOwner = '" + owner.getSex().name() + "' AND ageOwner = '"
                + String.valueOf(owner.getAge()) + "' AND phoneNoOwner = '" + owner.getPhone_no() + "' AND eMailOwner = '" + owner.getE_mail() + "';";
        System.out.println("TAKI STATEMENT IDZIE Z FUNKCJI W OWNERZE: " + getIDStatement);
        return getIDStatement;
    }

    /////////////////////////////////// hashcode & equals overwrite /////////////////////////////////////////////////'

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Owner)) return false;
        Owner owner = (Owner) o;
        return getName().equals(owner.getName()) &&
                getSurname().equals(owner.getSurname()) &&
                getSex() == owner.getSex() &&
                Objects.equals(getAge(), owner.getAge()) &&
                Objects.equals(getPhone_no(), owner.getPhone_no()) &&
                getE_mail().equals(owner.getE_mail());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getName(), getSurname(), getSex(), getAge(), getPhone_no(), getE_mail());
    }

////////////////////// auto generated Getters & Setters /////////////////////////////////////////////

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public Animal.GenderEnum getSex() {
        return sex;
    }

    public void setSex(Animal.GenderEnum sex) {
        this.sex = sex;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public String getPhone_no() {
        return phone_no;
    }

    public void setPhone_no(String phone_no) {
        this.phone_no = phone_no;
    }

    public String getE_mail() {
        return e_mail;
    }

    public void setE_mail(String e_mail) {
        this.e_mail = e_mail;
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
