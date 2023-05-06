package am.hitech.jdbc.model;

public class Account {
    private int id;
    private String userName;
    private int password;
    private int balance;
    private int userId;

    public Account(int id, String userName, int password,int balance, int userId) {
        this.id = id;
        this.userName = userName;
        this.password = password;
        this.balance = balance;
        this.userId = userId;
    }

    public Account() {};

    @Override
    public String toString() {
        return "Account{" +
                "id=" + id +
                ", userName='" + userName + '\'' +
                ", password=" + password +
                ", balance=" + balance +
                ", userId=" + userId +
                '}';
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public int getBalance() {
        return balance;
    }

    public void setBalance(int balance) {
        this.balance = balance;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public int getPassword() {
        return password;
    }

    public void setPassword(int password) {
        this.password = password;
    }
}
