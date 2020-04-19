package dais.entities;

public class Address {
    int id;
    String city;
    String country;
    String address_line;

    public Address(int id, String c, String cou, String a) {
        this.id = id;
        this.city = c;
        this.country = cou;
        this.address_line = a;
    }

    public int getId() {
        return this.id;
    }

    public String getCity() {
        return this.city;
    }

    public String getCountry() {
        return this.country;
    }

    public String getAddressLine() {
        return this.address_line;
    }
}
