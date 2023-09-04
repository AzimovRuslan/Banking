package org.clevertec.service;

import org.clevertec.entity.Bank;
import org.clevertec.utility.Constants;
import org.clevertec.utility.DbConnectorSingleton;

import java.sql.*;

public class BankService implements IService<Bank> {

    @Override
    public void save(Bank bank) {
        try {
            try (Connection connection = DbConnectorSingleton.getConnection(); PreparedStatement preparedStatement = connection.
                    prepareStatement(Constants.INSERT_INTO_BANKS)) {
                preparedStatement.setString(1, bank.getName());
                preparedStatement.executeUpdate();
            }
        } catch (SQLException e) {
            System.err.format("SQL State: %s\n%s", e.getSQLState(), e.getMessage());
        }
    }

}
