package am.hitech.jdbc.repo;

import am.hitech.jdbc.model.User;
import am.hitech.jdbc.model.UserAddress;
import am.hitech.jdbc.util.DataSource;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class UserAddressRepo {

    Connection connection = DataSource.getConnection();

    public List<UserAddress> getAllUserAddress(){
        List<UserAddress> list = new ArrayList<>();
        UserAddress us = null;
        String query = "SELECT first_name, last_name, country, city, street, home " +
                "FROM USER us LEFT JOIN address ad ON (us.id = ad.user_id )";
        try {
            PreparedStatement statement = connection.prepareStatement(query);

            ResultSet resultSet = statement.executeQuery();
                while (resultSet.next()) {
                    us = new UserAddress();
                    us.setFirstname(resultSet.getString(1));
                    us.setLastname(resultSet.getString(2));
                    us.setCountry(resultSet.getString(3));
                    us.setCity(resultSet.getString(4));
                    us.setStreet(resultSet.getString(5));
                    us.setHome(resultSet.getInt(6));

                    list.add(us);

            }

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return list;
    }
}
