package org.clevertec.service;

import org.clevertec.entity.User;
import org.clevertec.utility.Constants;
import org.clevertec.utility.DbConnectorSingleton;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class UserService implements IService<User> {

    @Override
    public void save(User user) {
        try {
            try (Connection connection = DbConnectorSingleton.getConnection(); PreparedStatement preparedStatement = connection.
                    prepareStatement(Constants.INSERT_INTO_USERS)) {
                preparedStatement.setString(1, user.getName());
                preparedStatement.setString(2, user.getSurname());
                preparedStatement.executeUpdate();
            }
        } catch (SQLException e) {
            System.err.format("SQL State: %s\n%s", e.getSQLState(), e.getMessage());
        }
    }

}
