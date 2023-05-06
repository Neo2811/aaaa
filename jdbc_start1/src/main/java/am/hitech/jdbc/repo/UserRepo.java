package am.hitech.jdbc.repo;

import am.hitech.jdbc.model.User;
import am.hitech.jdbc.service.UserService;
import am.hitech.jdbc.util.DataSource;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class UserRepo {
    Connection connection = DataSource.getConnection();


    public int updateUser(String firstName, String lastName, int id) {
        String query = "update `user` set first_name = ?, last_name = ? where id = " + id;
        try {
            PreparedStatement statement = connection.prepareStatement(query);
            statement.setString(1,firstName);
            statement.setString(2,lastName);

            return statement.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public int updateUserPassword(String email, int password) {
        String querry = "update `user` set password = ? where email = ?";
        try {
            PreparedStatement statement = connection.prepareStatement(querry);
            statement.setInt(1,password);
            statement.setString(2,email);

            return statement.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public int updatePasswordToken(String email) {
        UserService userService = new UserService();
        String query = "update `user` set password_token = ? where email = ?";
        try {
            PreparedStatement statement = connection.prepareStatement(query);
            statement.setString(1, userService.randomString());
            statement.setString(2, email);

            return statement.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public String getPasswordToken(String email) {
        String passwordToken = null;
        String query = "select password_token from user where email = ?";
        try {
            PreparedStatement statement = connection.prepareStatement(query);
            statement.setString(1,email);
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                passwordToken = resultSet.getString("password_token");
            }

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return passwordToken;
    }

    public int createUser(User user) {
        String query = "insert into `user` values(0,'Napoleon','Bonaparte','Nap@gmail.com',33,'2023-03-31')";
        try {
            Statement statement = connection.createStatement();
            return statement.executeUpdate(query);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public int createUser2(User user) {
        String query = "insert into user values(0,'"+user.getFirstName()+"','"+user.getLastName()+
                "','"+user.getEmail()+"',"+user.getAge()+","+"'2023-06-05')";

        try {
            Statement statement = connection.createStatement();
            return statement.executeUpdate(query);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public int createUserV2(User user) {
        String query = "insert into `user` values(0,?,?,?,?,'2023.06.05',?,?,?)";
        try {
            PreparedStatement statement = connection.prepareStatement(query);
            statement.setString(1, user.getFirstName());
            statement.setString(2, user.getLastName());
            statement.setString(3, user.getEmail());
            statement.setInt(4, user.getAge());
            statement.setInt(5, user.getPassword());
            statement.setString(6, user.getPasswordToken());
            statement.setString(7, user.getVerification_code());

            return statement.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public int deleteUser(int id) {
        String query = "delete from `user` where id ="+id;
        try {
            PreparedStatement statement = connection.prepareStatement(query);

            return statement.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public User getByUser(String username) {
        User user = null;
        String query = "select * from `user` where email = '" + username +"'";
        try {
            ResultSet resultSet = connection.createStatement().executeQuery(query);
            user = buildUser(resultSet);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return user;
    }

    public User getById(int id) {
        User user = null;
        String query = "select * from `user` where id = " + id;
        try {
            ResultSet resultSet = connection.createStatement().executeQuery(query);
            user = buildUser(resultSet);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return user;
    }

    public User checkUser(String username, int password) {
        User user = null;
        String query = "select * from `user` where email =?and password =?";
        try {

            PreparedStatement statement = connection.prepareStatement(query);
            statement.setString(1,username);
            statement.setInt(2,password);
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                user = new User();
                user.setEmail(resultSet.getString(4));
                user.setFirstName(resultSet.getString(2));
                user.setLastName(resultSet.getString(3));
                user.setPassword(resultSet.getInt(7));
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return user;
    }

    public List<User> getByUserMoreAgeThenGiven(int age) {
        List<User> list ;
        String query = "select * from user where age > " + age;
        try {
            ResultSet resultSet = connection.createStatement().executeQuery(query);
            list = buildUser2(resultSet);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return list;
    }

    public List<User> getByUser2(String s) {
        List<User> list;
       String query = "select * from user where first_name like '" + s + "%'";
        try {
            ResultSet resultSet = connection.createStatement().executeQuery(query);
            list = buildUser2(resultSet);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return list;
    }

    private User buildUser(ResultSet resultSet) {
        User user = null;

        try {
            while (resultSet.next()) {
                user = new User();
                user.setId(resultSet.getInt("id"));
                user.setFirstName(resultSet.getString("first_name"));
                user.setLastName(resultSet.getString("last_name"));
                user.setEmail(resultSet.getString("email"));
                user.setAge(resultSet.getInt("age"));

            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return user;
    }

    private List<User> buildUser2(ResultSet resultSet) {
        User user;
        List<User> list = new ArrayList<>();

        try {
            while (resultSet.next()) {
                user = new User();
                user.setId(resultSet.getInt("id"));
                user.setFirstName(resultSet.getString("first_name"));
                user.setLastName(resultSet.getString("last_name"));
                user.setAge(resultSet.getInt("age"));
                list.add(user);
            }

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return list;
    }
}
