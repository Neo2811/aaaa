package am.hitech.jdbc.model;

public class Address {
    private int id;
    private String country;
    private String city;
    private String street;
    private int home;
    private int userId;

    public Address(){}
    public Address(int id,String country,String city, String street, int home, int userId) {
        this.id=id;
        this.country=country;
        this.city=city;
        this.street=street;
        this.home=home;
        this.userId=userId;
    }

    @Override
    public String toString() {
        return "Address{" +
                "id=" + id +
                ", country='" + country + '\'' +
                ", city='" + city + '\'' +
                ", street='" + street + '\'' +
                ", home=" + home +
                ", userId=" + userId +
                '}';
    }

    public void setId(int id) {
        this.id=id;
    }
    public int getId() {
        return id;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
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

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }
}
