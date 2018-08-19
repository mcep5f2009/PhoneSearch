package application;

public class Address {

    private final String street;
    private final String city;

    public Address(String street, String city) {
        this.street = street.trim();
        this.city = city.trim();
    }

    @Override
    public String toString() {
        return street + " " + city;
    }

    public String getStreet() {
        return street;
    }

    public String getCity() {
        return city;
    }

}
