package dais.entities;

public class Address {
    Integer id;
    String city;
    String street;
    Integer streetNumber;

    public Address(Integer id, String c, String st, Integer stNum) {
        this.id = id;
        this.city = c;
        this.street = st;
        this.streetNumber = stNum;
    }

    public Integer getId() {
        return this.id;
    }

    public String getCity() {
        return this.city;
    }

    public String getStreet() {
        return this.street;
    }

    public Integer getStreetNumber() {
        return this.streetNumber;
    }
}
