package am.hitech.jdbc.model;

public class UserAddress {
    private String firstname;
    private String lastname;
    private String country;
    private String city;
    private String street;
    private int home;

    public UserAddress(String firstname, String lastname, String country, String city, String street, int home) {
        this.firstname = firstname;
        this.lastname = lastname;
        this.country = country;
        this.city = city;
        this.street = street;
        this.home = home;
    }
    public UserAddress(){}

    @Override
    public String toString() {
        return "UserAddress{" +
                "firstname='" + firstname + '\'' +
                ", lastname='" + lastname + '\'' +
                ", country='" + country + '\'' +
                ", city='" + city + '\'' +
                ", street='" + street + '\'' +
                ", home=" + home +
                '}';
    }

    public String getFirstname() {
        return firstname;
    }

    public void setFirstname(String firstname) {
        this.firstname = firstname;
    }

    public String getLastname() {
        return lastname;
    }

    public void setLastname(String lastname) {
        this.lastname = lastname;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getStreet() {
        return street;
    }

    public void setStreet(String street) {
        this.street = street;
    }

    public int getHome() {
        return home;
    }

    public void setHome(int home) {
        this.home = home;
    }
}
