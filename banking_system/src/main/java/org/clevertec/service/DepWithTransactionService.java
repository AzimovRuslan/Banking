package org.clevertec.service;

import org.clevertec.entity.DepWithTransaction;
import org.clevertec.utility.CheckCreator;
import org.clevertec.utility.Constants;
import org.clevertec.utility.DbConnectorSingleton;

import java.io.IOException;
import java.math.BigDecimal;
import java.sql.*;

public class DepWithTransactionService implements IService<DepWithTransaction> {

    @Override
    public void save(DepWithTransaction depWithTransaction) throws IOException {
        CheckCreator checkCreator = new CheckCreator();

        try {
            try (Connection connection = DbConnectorSingleton.getConnection();
                 PreparedStatement preparedStatement = connection.prepareStatement(Constants.INSERT_INTO_DEP_WITH_TRANSACTIONS);
                 PreparedStatement preparedStatement1 = connection.prepareStatement(Constants.GET_ACCOUNT_BALANCE_BY_NUMBER);
                 PreparedStatement preparedStatement2 = connection.prepareStatement(Constants.UPDATE_BALANCE_OF_ACCOUNT)) {

                BigDecimal initBalance = BigDecimal.valueOf(0);
                int accountId = 0;

                preparedStatement1.setInt(1, depWithTransaction.getAccount().getNumber());
                ResultSet result = preparedStatement1.executeQuery();
                while (result.next()) {
                    accountId = result.getInt("id");
                    initBalance = result.getBigDecimal("balance");
                }

                depWithTransaction.getAccount().setBalance(initBalance);

                if (depWithTransaction.getType().equals("deposit")) {
                    depWithTransaction.getAccount().setBalance(depWithTransaction.getAccount().getBalance().add(depWithTransaction.getAmount()));
                } else {
                    depWithTransaction.getAccount().setBalance(depWithTransaction.getAccount().getBalance().subtract(depWithTransaction.getAmount()));
                }

                preparedStatement2.setBigDecimal(1, depWithTransaction.getAccount().getBalance());
                preparedStatement2.setInt(2, depWithTransaction.getAccount().getNumber());
                preparedStatement2.executeUpdate();

                preparedStatement.setString(1, depWithTransaction.getType());
                preparedStatement.setBigDecimal(2, depWithTransaction.getAmount());
                preparedStatement.setDate(3, Date.valueOf(depWithTransaction.getDate()));
                preparedStatement.setTime(4, Time.valueOf(depWithTransaction.getTime()));
                preparedStatement.setInt(5, accountId);
                preparedStatement.executeUpdate();

                checkCreator.create(depWithTransaction);
            }
        } catch (SQLException e) {
            System.err.format("SQL State: %s\n%s", e.getSQLState(), e.getMessage());
        }
    }

}
