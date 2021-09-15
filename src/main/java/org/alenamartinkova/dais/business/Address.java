package org.alenamartinkova.dais.business;

public class Address {
    Integer addressID;
    String city;
    String street;
    Integer streetNumber;

    public Address(Integer id, String c, String st, Integer stNum) {
        this.addressID = id;
        this.city = c;
        this.street = st;
        this.streetNumber = stNum;
    }

    public Integer getId() {
        return this.addressID;
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
