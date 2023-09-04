package org.clevertec.service;

import org.clevertec.entity.Account;
import org.clevertec.utility.Constants;
import org.clevertec.utility.DbConnectorSingleton;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class AccountService implements IService<Account> {

    @Override
    public void save(Account account) {
        try {
            try (Connection connection = DbConnectorSingleton.getConnection();
                 PreparedStatement preparedStatement = connection.prepareStatement(Constants.INSERT_INTO_ACCOUNTS);
                 PreparedStatement preparedStatement1 = connection.prepareStatement(Constants.GET_BANK_ID_BY_NAME);
                 PreparedStatement preparedStatement2 = connection.prepareStatement(Constants.GET_USER_ID_BY_NAME)) {

                int bankId = 0;
                int userId = 0;

                preparedStatement1.setString(1, account.getBank().getName());
                ResultSet result = preparedStatement1.executeQuery();
                while (result.next()) {
                    bankId = result.getInt("id");
                }

                preparedStatement2.setString(1, account.getUser().getName());
                preparedStatement2.setString(2, account.getUser().getSurname());
                ResultSet result1 = preparedStatement2.executeQuery();
                while (result1.next()) {
                    userId = result1.getInt("id");
                }

                preparedStatement.setInt(1, account.getNumber());
                preparedStatement.setInt(2, bankId);
                preparedStatement.setInt(3, userId);
                preparedStatement.setBigDecimal(4, account.getBalance());

                preparedStatement.executeUpdate();
            }
        } catch (SQLException e) {
            System.err.format("SQL State: %s\n%s", e.getSQLState(), e.getMessage());
        }
    }

}
