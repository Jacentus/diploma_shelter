package pl.com.jmotyka.dbConnectvity;

import pl.com.jmotyka.animals.Animal;
import pl.com.jmotyka.animals.Cat;
import pl.com.jmotyka.animals.Dog;
import pl.com.jmotyka.general.*;

import java.sql.*;
import java.util.ArrayList;

public class MySQLCon {

    private String myDriver = "com.mysql.jdbc.Driver";
    private String myURL = "jdbc:mysql://localhost:3306/kitqfinderdb";

    public MySQLCon(){}

    ///////////////////////////////////////////////////////////////////////////////////////////////////////////////////

    public Boolean checkDBforDuplicates(Submitter submitter){
        Boolean isDuplicated = null;
        try {
            Class.forName(myDriver);
            Connection con = DriverManager.getConnection(this.myURL, "root", "haslo");
            Statement st = con.createStatement();
            String sqlStatement = submitter.createCheckForDuplicateStatement(submitter);
            ResultSet resultSet = st.executeQuery(sqlStatement);
            if (resultSet.next() == false){
                isDuplicated = false;
                System.out.println("NOT A DUPLICATE");
            } //is not a duplicate;
            else isDuplicated = true;
        } catch (ClassNotFoundException | SQLException d){
            System.out.println("Sth went wrong in connectToDB method");
            d.printStackTrace();
        }
        System.out.println("FUNKCJA CHECK FOR DUPLICATE Z MYSQLCON ZWRACA WARTOSC: " + isDuplicated );
        return isDuplicated;
    }

    public void sendToDB(Uploadable uploadableObject) {
        try {
        Class.forName("com.mysql.jdbc.Driver");
        Connection con = DriverManager.getConnection(this.myURL, "root", "haslo");
        Statement st = con.createStatement();
        String sqlStatement = uploadableObject.createUploadStatement(uploadableObject);
        st.executeUpdate(sqlStatement);
        } catch (ClassNotFoundException e){
            e.printStackTrace();
            String message = "class not found exception ";
            System.out.println(message);
        } catch (SQLException e){
            e.printStackTrace();
        }
        }

    public int retrieveID(Uploadable uploadableObject){  // DZIALA DLA LOSTCAT I OWNERA, SPRAWDZIC CZY W OGÓLE MUSZE ODZYSKIWAC ID ZWIERZAKA
        int id = 0;
        try {
            Class.forName("com.mysql.jdbc.Driver");
            Connection con = DriverManager.getConnection(this.myURL, "root", "haslo");
            Statement st = con.createStatement();
            String sqlStatement = uploadableObject.createGetIDStatement(uploadableObject);
            ResultSet resultSet = st.executeQuery(sqlStatement);
            while(resultSet.next()){
                id = resultSet.getInt(1);
            }

        } catch (SQLException | ClassNotFoundException e){
            String message = "NO CONNECTION WITH DATABASE ";
            System.out.println(message);
            e.printStackTrace();
        }
        return id;
    }

    public <T extends Animal> ArrayList<T> searchInDB(Animal animal){ //WYSZUKIWANIE SKOMPLIKOWANE JAKO OSOBNA KLASA?
        ArrayList<T> animalist = new ArrayList<>();
        System.out.println("Zaczynam wykonywac funkcje searchInDB");
        try {
            Class.forName("com.mysql.jdbc.Driver");
            Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/kitqfinderdb", "root", "haslo");
            Statement st = con.createStatement();
            String sqlStatement;
            String tablenameKeptAt = null;
            String tablenameSubmitter = null;
            String joiningOn = null;

            if (animal instanceof Cat){ //te funkcje moge przeniesc chyba do animali, przeciazyc je dajac dwie metody z ownerem/shelterem i wyciagajac je w funkcji searchINDB...
                if (animal.getSubmittedBy() instanceof Owner) {
                    tablenameKeptAt = "enshelteredcats";
                    tablenameSubmitter = "shelter";
                    joiningOn = "idShelter";
                } else if (animal.getSubmittedBy() instanceof Shelter){
                    tablenameKeptAt = "lostcats";
                    tablenameSubmitter = "owner";
                    joiningOn = "idOwner";
                }
                Cat catSearchedFor = (Cat)animal;
                String gender = String.valueOf(catSearchedFor.getGender());
                String breed = String.valueOf(catSearchedFor.getBreed());
                String primaryColor = String.valueOf(catSearchedFor.getPrimaryColor());
                int sterilization;
                if (catSearchedFor.getSterilisation() == true){
                    sterilization = 1;
                } else sterilization = 0;

                sqlStatement = "SELECT * FROM " + tablenameKeptAt + " INNER JOIN " + tablenameSubmitter + " ON " + tablenameKeptAt + "." + joiningOn + " = " + tablenameSubmitter + "." + joiningOn + " WHERE " + tablenameKeptAt + ".genderCat = '" + gender + "' AND  " + tablenameKeptAt + ".breedCat = '" + breed + "' AND " + tablenameKeptAt + ".primColorCat = '" + primaryColor + "' AND " + tablenameKeptAt + ".sterilCat = '" + sterilization + "';";

                System.out.println("taki statement idzie przy wyszukiwaniu podobnych kotów z bazy danych: " + sqlStatement);

                ResultSet rs = st.executeQuery(sqlStatement);
                while (rs.next()) {
                    Cat cat = new Cat(rs.getString("nameCat"), (Animal.GenderEnum) Animal.GenderEnum.valueOf(rs.getString("genderCat")), (Animal.AnimalHealthStatusEnum) Animal.AnimalHealthStatusEnum.valueOf(rs.getString("healthCat")),
                            Animal.Color.valueOf(rs.getString("primColorCat")), (Animal.Color) Animal.Color.valueOf(rs.getString("secColorCat")), (Animal.Color) Animal.Color.valueOf(rs.getString("terColorCat")), (Animal.Bodytype) Animal.Bodytype.valueOf(rs.getString("bodytypeCat")),
                            Boolean.valueOf(rs.getBoolean("sterilCat")), Boolean.valueOf(rs.getBoolean("collarCat")),
                            Boolean.valueOf(rs.getBoolean("nametagCat")), Boolean.valueOf(rs.getBoolean("tailCat")), rs.getLong("heightCat"),
                            rs.getLong("lengthCat"), rs.getLong("weightCat"), (Cat.Pattern) Cat.Pattern.valueOf(rs.getString("patternCat")), (Cat.CatBreed) Cat.CatBreed.valueOf(rs.getString("breedcat")));

                    System.out.println("Dziala funkcja searchInDB. Zrobilem z bazy danych kota o imieniu " + cat.getName());

                    // create Submitter from database and set it as animalsubmitter

                    if (tablenameSubmitter == "shelter"){
                        Shelter shelter = new Shelter(rs.getString("nameShelter"), new Address(rs.getString("streetShelter"), rs.getString("streetNoShelter"), rs.getString("flatNoShelter"),
                                rs.getString("postCodeShelter"), rs.getString("cityShelter"), rs.getString("countryShelter")), rs.getString("phoneNoShelter"), rs.getString("emailShelter"));
                        cat.setSubmittedBy(shelter);
                        System.out.println("nazwa Shelter z bazy to: " + ((Shelter) cat.getSubmittedBy()).getName());
                    }
                    else if (tablenameSubmitter == "owner"){
                        Owner owner = new Owner(rs.getString("nameOwner"), rs.getString("surnameOwner"), Animal.GenderEnum.valueOf(rs.getString("sexOwner")),
                                rs.getInt("ageOwner"), rs.getString("phoneNoOwner"), rs.getString("eMailOwner"));
                        cat.setSubmittedBy(owner);
                        System.out.println("Imie ownera z bazy to: " + ((Owner) cat.getSubmittedBy()).getName());
                    }
                    animalist.add((T) cat);
                }
            }
            else if (animal instanceof Dog){
                if (animal.getSubmittedBy() instanceof Owner) {
                    tablenameKeptAt = "ensheltereddogs";
                    tablenameSubmitter = "shelter";
                    joiningOn = "idShelter";
                } else if (animal.getSubmittedBy() instanceof Shelter){
                    tablenameKeptAt = "lostdogs";
                    tablenameSubmitter = "owner";
                    joiningOn = "idOwner";
                }
            Dog dogSearchedFor = (Dog)animal;
            String gender = String.valueOf(dogSearchedFor.getGender());
            String breed = String.valueOf(dogSearchedFor.getBreed());
            String primaryColor = String.valueOf(dogSearchedFor.getPrimaryColor());
            int sterilization;
                if (dogSearchedFor.getSterilisation() == true){
                    sterilization = 1;
                } else sterilization = 0;

                sqlStatement = "SELECT * FROM " + tablenameKeptAt + " INNER JOIN " + tablenameSubmitter + " ON " + tablenameKeptAt + "." + joiningOn + " = " + tablenameSubmitter + "." + joiningOn + " WHERE " + tablenameKeptAt + ".genderDog = '" + gender + "' AND "  + tablenameKeptAt + ".breedDog = '" + breed + "' AND " + tablenameKeptAt + ".primColorDog = '" + primaryColor + "' AND " + tablenameKeptAt + ".sterilDog = '" + sterilization + "';";
                System.out.println("taki statement idzie przy wyszukiwaniu podobnych psów z bazy danych: " + sqlStatement);

                ResultSet rs = st.executeQuery(sqlStatement);
                while (rs.next()){

                    Dog dog = new Dog(rs.getString("nameDog"), (Animal.GenderEnum)Animal.GenderEnum.valueOf(rs.getString("genderDog")), (Animal.AnimalHealthStatusEnum) Animal.AnimalHealthStatusEnum.valueOf(rs.getString("healthDog")),
                            Animal.Color.valueOf(rs.getString("primColorDog")), (Animal.Color)Animal.Color.valueOf(rs.getString("secColorDog")), (Animal.Color)Animal.Color.valueOf(rs.getString("terColorDog")), (Animal.Bodytype)Animal.Bodytype.valueOf(rs.getString("bodytypeDog")),
                            Boolean.valueOf(rs.getBoolean("sterilDog")), Boolean.valueOf(rs.getBoolean("collarDog")),
                            Boolean.valueOf(rs.getBoolean("nametagDog")), Boolean.valueOf(rs.getBoolean("tailDog")), rs.getLong("heightDog"),
                            rs.getLong("lengthDog"),rs.getLong("weightDog"), (Dog.DogBreed) Dog.DogBreed.valueOf(rs.getString("breedDog")));
                    System.out.println("Dziala funkcja searchInDB. Zrobilem z bazy danych psa o imieniu " + dog.getName());
                    // create Submitter from database and set it as animalsubmitter

                        if (tablenameKeptAt == "lostdogs"){
                            Owner owner = new Owner(rs.getString("nameOwner"), rs.getString("surnameOwner"), (Animal.GenderEnum)Animal.GenderEnum.valueOf(rs.getString("sexOwner"))
                                    , rs.getInt("ageOwner"), rs.getString("phoneNoOwner"), rs.getString("eMailOwner"));
                            dog.setSubmittedBy(owner);
                            System.out.println("Imie ownera z bazy to: " + dog.getSubmittedBy().getName());
                        }
                        else if (tablenameKeptAt == "ensheltereddogs"){
                            Shelter shelter = new Shelter(rs.getString("nameShelter"), new Address(rs.getString("streetShelter"), rs.getString("streetNoShelter"), rs.getString("flatNoShelter"),
                                    rs.getString("postCodeShelter"), rs.getString("cityShelter"), rs.getString("countryShelter")), rs.getString("phoneNoShelter"), rs.getString("emailShelter"));
                            dog.setSubmittedBy(shelter);
                            System.out.println("nazwa Shelter z bazy to: " + dog.getSubmittedBy().getName());
                        }
                    animalist.add((T) dog);
                }
        }
        } catch (SQLException | ClassNotFoundException e){
            String message = "NO CONNECTION WITH DATABASE ";
            e.printStackTrace();
        }

        System.out.println("ANIMAL LIST MA DLUGOSC " + animalist.size());

        return animalist;
    }

    //////////////////////////////////// GETTERS & SETTERS /////////////////////////////////////////////////////

    public String getMyDriver() {
        return myDriver;
    }

    public void setMyDriver(String myDriver) {
        this.myDriver = myDriver;
    }

    public String getMyURL() {
        return myURL;
    }

    public void setMyURL(String myURL) {
        this.myURL = myURL;
    }

}


