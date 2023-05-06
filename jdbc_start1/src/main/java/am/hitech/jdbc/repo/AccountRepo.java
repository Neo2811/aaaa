package am.hitech.jdbc.repo;

import am.hitech.jdbc.model.Account;
import am.hitech.jdbc.util.DataSource;

import java.sql.*;

public class AccountRepo {
    private int loginUserId;
    Connection connection = DataSource.getConnection();

    public void transfer(int from, int to, int amount) {

        String addBalance = "update `account` set balance = balance + ? where user_id = ?";
        String deductBalance = "update `account` set balance = balance - ? where user_id = ?";

        try {
            connection.setAutoCommit(false);

            PreparedStatement statement = connection.prepareStatement(deductBalance);
            statement.setInt(1, amount);
            statement.setInt(2, from);
            statement.executeUpdate();

            statement = connection.prepareStatement(addBalance);
            statement.setInt(1, amount);
            statement.setInt(2, to);
            statement.executeUpdate();

            connection.commit();

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
//        catch (Exception e) {
//            try {
//                connection.rollback();
//            } catch (SQLException ex) {
//                throw new RuntimeException(ex);
//            }
//        }
    }


    public ResultSet checkFrom(int from) {
        String query = "select * from account where user_id = ?";
        try {
            PreparedStatement statement = connection.prepareStatement(query);

            statement.setInt(1, from);

            ResultSet resultSet = statement.executeQuery();

            return resultSet;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public Account buildAccount(ResultSet resultSet) {
        Account account = null;

        try {
            while (resultSet.next()) {
                account = new Account();
                account.setId(resultSet.getInt("id"));
                account.setUserName(resultSet.getString("username"));
                account.setPassword(resultSet.getInt("password"));
                account.setBalance(resultSet.getInt("balance"));
                account.setUserId(resultSet.getInt("user_id"));
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return account;
    }


    public int findUseridWithNumber(int number) {
        int id = -1;
        String query = "SELECT user_id FROM ACCOUNT WHERE user_id = " +
                "(SELECT user_id FROM phone_numbers WHERE NUMBER = " +
                "SUBSTRING(?,3,10) AND phone_code_id = " +
                "(SELECT id FROM phone_codes WHERE CODE = SUBSTRING(?,1,2)))";

        try {
            PreparedStatement statement = connection.prepareStatement(query);

            statement.setInt(1, number);
            statement.setInt(2, number);

            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                id = resultSet.getInt("user_id");
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return id;
    }

    public int login(int number, int password) {
        loginUserId = -1;
        String query = "SELECT user_id FROM ACCOUNT WHERE user_id = " +
                "(SELECT user_id FROM phone_numbers WHERE NUMBER = " +
                "SUBSTRING(?,3,10) AND phone_code_id = " +
                "(SELECT id FROM phone_codes WHERE CODE = SUBSTRING(?,1,2))) AND PASSWORD = ?";

        try {
            PreparedStatement statement = connection.prepareStatement(query);

            statement.setInt(1, number);
            statement.setInt(2, number);
            statement.setInt(3, password);

            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                loginUserId = resultSet.getInt("user_id");
            }

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        return loginUserId;
    }

    public int checkAmount(int amount) {
        int loginAmount = 0;
        String query = "select balance from account where user_id =" + loginUserId;

        try {
            PreparedStatement statement = connection.prepareStatement(query);
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                loginAmount = resultSet.getInt("balance");
            }

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return loginAmount - amount;
    }

    public int checkAmount(int amount, int userId) {
        int loginAmount = 0;
        String query = "select balance from account where user_id =" + userId;

        try {
            PreparedStatement statement = connection.prepareStatement(query);
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                loginAmount = resultSet.getInt("balance");
            }

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return loginAmount - amount;
    }

    public int createAccount(Account account) {
        String query = "insert into `account` values(0,?,?,?,?)";

        try {
            PreparedStatement statement = connection.prepareStatement(query);

            statement.setString(1, account.getUserName());
            statement.setInt(2, account.getPassword());
            statement.setInt(3, account.getBalance());
            statement.setInt(4, account.getUserId());

            return statement.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public int updateAccount(String userName, int balance, int id) {
        String query = "update `account` set username = ?, balance = ? where id =" + id;

        try {
            PreparedStatement statement = connection.prepareStatement(query);

            statement.setString(1, userName);
            statement.setInt(2, balance);

            return statement.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public int deleteAccount(int id) {
        String query = "delete from `account` where id =" + id;

        try {
            PreparedStatement statement = connection.prepareStatement(query);

            return statement.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
