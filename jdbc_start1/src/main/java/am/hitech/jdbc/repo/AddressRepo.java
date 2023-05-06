package am.hitech.jdbc.repo;

import am.hitech.jdbc.model.Address;
import am.hitech.jdbc.model.User;
import am.hitech.jdbc.util.DataSource;

import java.sql.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class AddressRepo {
    Connection connection = DataSource.getConnection();

    public Address getById(int id) {
        Address address = null;
        String query = "select * from `Address` where id =" + id;
        try {
            ResultSet resultSet = connection.createStatement().executeQuery(query);

            address = addressBuild(resultSet);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return address;
    }

    public List<Address> getByUserId(int id) {
        List<Address> list;
        String query = "select * from `address` where user_id = " + id;

        try {
            ResultSet resultSet = connection.createStatement().executeQuery(query);

            list = addressBuild2(resultSet);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return list;
    }

    public List<Address> getAll() {
        List<Address> list;
        String query = "select * from `address`";

        try {
            ResultSet resultSet = connection.createStatement().executeQuery(query);

            list = addressBuild2(resultSet);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return list;
    }

    public Address addressBuild(ResultSet resultSet) {
        Address address = null;

        try {
            while (resultSet.next()) {
                address = new Address();
                address.setId(resultSet.getInt("id"));
                address.setCountry(resultSet.getString("country"));
                address.setCity(resultSet.getString("city"));
                address.setStreet(resultSet.getString("street"));
                address.setHome(resultSet.getInt("home"));
                address.setUserId(resultSet.getInt("user_id"));
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return address;
    }

    public List<Address> addressBuild2(ResultSet resultSet) {
        Address address;
        List<Address> list = new ArrayList<>();

        try {
            while (resultSet.next()) {
                address = new Address();
                address.setId(resultSet.getInt("id"));
                address.setCountry(resultSet.getString("country"));
                address.setCity(resultSet.getString("city"));
                address.setStreet(resultSet.getString("street"));
                address.setHome(resultSet.getInt("home"));
                address.setUserId(resultSet.getInt("user_id"));

                list.add(address);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return list;
    }

    public Map<Address, User> addressUserBuildByMap(ResultSet resultSet) {
        Address address;
        User user;
        Map<Address, User> map = new HashMap<>();

        try {
            while (resultSet.next()) {
                address = new Address();
                user = new User();
                address.setId(resultSet.getInt("address.id"));
                address.setCountry(resultSet.getString("country"));
                address.setCity(resultSet.getString("city"));
                address.setStreet(resultSet.getString("street"));
                address.setHome(resultSet.getInt("home"));
                address.setUserId(resultSet.getInt("user_id"));

                user.setId(resultSet.getInt("user_id"));
                user.setFirstName(resultSet.getString("first_name"));
                user.setLastName(resultSet.getString("last_name"));
                user.setEmail(resultSet.getString("email"));
                user.setAge(resultSet.getInt("age"));

                map.put(address, user);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return map;
    }

//    public List addressUserBuildByMapByList(ResultSet resultSet) {
//        List<Map> list = new ArrayList<>();
//        Map map;
//        Address address;
//        User user;
//        try {
//            while (resultSet.next()) {
//                address = new Address();
//                user = new User();
//                address.setId(resultSet.getInt("address.id"));
//
//            }
//
//        } catch (SQLException e) {
//            throw new RuntimeException(e);
//        }
//    }

    public int createAddress(Address address) {
        String query = "insert into `address` values(0,?,?,?,?,?)";
        try {
            PreparedStatement statement = connection.prepareStatement(query);
            statement.setString(1, address.getCountry());
            statement.setString(2, address.getCity());
            statement.setString(3, address.getStreet());
            statement.setInt(4, address.getHome());
            statement.setInt(5, address.getUserId());

            return statement.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public int updateAddress(String country, String city, int id) {
        String query = "update `address`set country = ?, city = ? where id =" + id;
        try {
            PreparedStatement statement = connection.prepareStatement(query);
            statement.setString(1, country);
            statement.setString(2, city);

            return statement.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public int deleteAddress(int id) {
        String query = "delete from `address` where id =" + id;
        try {
            PreparedStatement statement = connection.prepareStatement(query);

            return statement.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public Map<Address, User> getAddressUserByMap(int addressId) {
        Map<Address, User> map;
        String query = "SELECT * FROM `user` JOIN `address` ON user.id = address.user_id where address.id =" + addressId;
        try {
            ResultSet resultSet = connection.createStatement().executeQuery(query);
            map = addressUserBuildByMap(resultSet);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return map;
    }

    public Map<Address, User> getAddressUserByMap1(int addressId) {
        Map<Address, User> map;
        String query = "SELECT * FROM `user`, `address` WHERE user.id = (SELECT user_id FROM `address` WHERE id =" + addressId + ") AND address.id = " + addressId;

        try {
            ResultSet resultSet = connection.createStatement().executeQuery(query);

            map = addressUserBuildByMap(resultSet);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return map;
    }

    public Map<Address, User> getAddressUserByMap2(int addressId) {
        Map<Address, User> map;
        String query = "SELECT * FROM `user`, `address` WHERE user.id = (SELECT user_id FROM `address` WHERE id =" + addressId + ") AND address.id = " + addressId;

        try {
            PreparedStatement statement = connection.prepareStatement(query);
            ResultSet resultSet = statement.executeQuery();

            map = addressUserBuildByMap(resultSet);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return map;
    }
}
