package org.clevertec.service;

import lombok.extern.slf4j.Slf4j;
import org.clevertec.entity.Account;
import org.clevertec.entity.TransferTransaction;
import org.clevertec.utility.CheckCreator;
import org.clevertec.utility.Constants;
import org.clevertec.utility.DbConnectorSingleton;

import java.io.IOException;
import java.math.BigDecimal;
import java.sql.*;

public class TransferTransactionService implements IService<TransferTransaction> {

    @Override
    public void save(TransferTransaction transferTransaction) throws IOException {
        CheckCreator checkCreator = new CheckCreator();
        try {
            try (Connection connection = DbConnectorSingleton.getConnection();
                 PreparedStatement preparedStatement = connection.prepareStatement(Constants.INSERT_INTO_TRANSFER_TRANSACTIONS);
                 PreparedStatement preparedStatement1 = connection.prepareStatement(Constants.GET_ACCOUNT_BALANCE_BY_NUMBER);
                 PreparedStatement preparedStatement3 = connection.prepareStatement(Constants.GET_ACCOUNT_BALANCE_BY_NUMBER);
                 PreparedStatement preparedStatement2 = connection.prepareStatement(Constants.UPDATE_BALANCE_OF_ACCOUNT);
                 PreparedStatement preparedStatement4 = connection.prepareStatement(Constants.UPDATE_BALANCE_OF_ACCOUNT)
            ) {

                BigDecimal initSenderBalance = BigDecimal.valueOf(0);
                BigDecimal initGetterBalance = BigDecimal.valueOf(0);

                int senderAccountId = 0;
                int getterAccountId = 0;

                preparedStatement1.setInt(1, transferTransaction.getAccountOfSender().getNumber());
                ResultSet result = preparedStatement1.executeQuery();
                while (result.next()) {
                    senderAccountId = result.getInt("id");
                    initSenderBalance = result.getBigDecimal("balance");
                }

                preparedStatement3.setInt(1, transferTransaction.getAccountOfGetter().getNumber());
                ResultSet result1 = preparedStatement3.executeQuery();
                while (result1.next()) {
                    getterAccountId = result1.getInt("id");
                    initGetterBalance = result1.getBigDecimal("balance");
                }

                transferTransaction.getAccountOfSender().setBalance(initSenderBalance);
                transferTransaction.getAccountOfGetter().setBalance(initGetterBalance);

                transferTransaction.getAccountOfSender().setBalance(transferTransaction.getAccountOfSender().getBalance().subtract(transferTransaction.getAmount()));
                transferTransaction.getAccountOfGetter().setBalance(transferTransaction.getAccountOfGetter().getBalance().add(transferTransaction.getAmount()));

                executeUpdate(preparedStatement2, transferTransaction.getAccountOfSender());
                executeUpdate(preparedStatement4, transferTransaction.getAccountOfGetter());

                preparedStatement.setString(1, transferTransaction.getType());
                preparedStatement.setBigDecimal(2, transferTransaction.getAmount());
                preparedStatement.setDate(3, Date.valueOf(transferTransaction.getDate()));
                preparedStatement.setTime(4, Time.valueOf(transferTransaction.getTime()));
                preparedStatement.setInt(5, senderAccountId);
                preparedStatement.setInt(6, getterAccountId);
                preparedStatement.executeUpdate();

                checkCreator.create(transferTransaction);
            }
        } catch (SQLException e) {
            System.err.format("SQL State: %s\n%s", e.getSQLState(), e.getMessage());
        }
    }

    private void executeUpdate(PreparedStatement preparedStatement, Account account) throws SQLException {
        preparedStatement.setBigDecimal(1, account.getBalance());
        preparedStatement.setInt(2, account.getNumber());
        preparedStatement.executeUpdate();
    }
}
