package am.hitech.jdbc.repo;

import am.hitech.jdbc.model.Number;
import am.hitech.jdbc.util.DataSource;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class NumberRepo {
    Connection connection = DataSource.getConnection();

    public List<Number> getAllNumber() {
        List<Number> list;
        String query = "SELECT CONCAT(CODE, pn.number),user_id  FROM " +
                "phone_codes pc JOIN phone_numbers pn ON (pc.id = pn.phone_code_id)";
        try {
            PreparedStatement statement = connection.prepareStatement(query);

            ResultSet resultSet = statement.executeQuery();

            list = numberBuild(resultSet);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return list;
    }

    public List<Number> getAllUser() {
        List<Number> list;
        String query = "select id,first_name, last_name from user";

        try {
            PreparedStatement statement = connection.prepareStatement(query);
            ResultSet resultSet = statement.executeQuery();

            list = numberBuild2(resultSet);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return list;
    }

    public List<Number> numberBuild(ResultSet resultSet) {
        List<Number> list = new ArrayList<>();
        Number number;

        try {
            while (resultSet.next()) {
                number = new Number();
                number.setNumber(resultSet.getInt(1));
                number.setUserId(resultSet.getInt(2));
                list.add(number);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return list;
    }

    public List<Number> numberBuild2(ResultSet resultSet) {
        List<Number> list = new ArrayList<>();
        Number number;

        try {
            while (resultSet.next()) {
                number = new Number();
                number.setUserId(resultSet.getInt(1));
                number.setFirstname(resultSet.getString(2));
                number.setLastname(resultSet.getString(3));
                list.add(number);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return list;
    }
}
