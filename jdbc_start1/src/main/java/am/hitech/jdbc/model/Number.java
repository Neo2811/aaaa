package am.hitech.jdbc.model;

public class Number {
    private int number;
    private int userId;
    private String firstname;
    private String lastname;

    public Number(int number, int userId, String firstname, String lastname) {
        this.number = number;
        this.userId = userId;
        this.firstname = firstname;
        this.lastname = lastname;
    }

    public Number() {}

    @Override
    public String toString() {
        return "Number{" +
                "number=" + number +
                ", userId=" + userId +
                ", firstname='" + firstname + '\'' +
                ", lastname='" + lastname + '\'' +
                '}';
    }

    public int getNumber() {
        return number;
    }

    public void setNumber(int number) {
        this.number = number;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
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
}
