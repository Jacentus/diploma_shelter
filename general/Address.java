package pl.com.jmotyka.general;

public class Address {

    private String street;
    private String streetNo;
    private String flatNo;
    private String postCode;
    private String city;
    private String country;

    ///////////////////////////////// CONSTRUCTORS ///////////////////////////////

    public Address(String street, String streetNo, String flatNo, String postCode, String city, String country) {
        this.street = street;
        this.streetNo = streetNo;
        this.flatNo = flatNo;
        this.postCode = postCode;
        this.city = city;
        this.country = country;
    }
    //////////////////////////// AUTO GENERATED GETTERS & SETTERS ///////////////////////////////////

    public String getStreet() {
        return street;
    }

    public void setStreet(String street) {
        this.street = street;
    }

    public String getStreetNo() {
        return streetNo;
    }

    public void setStreetNo(String streetNo) {
        this.streetNo = streetNo;
    }

    public String getPostCode() {
        return postCode;
    }

    public void setPostCode(String postCode) {
        this.postCode = postCode;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getFlatNo() {
        return flatNo;
    }

    public void setFlatNo(String flatNo) {
        this.flatNo = flatNo;
    }

}
