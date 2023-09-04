package org.clevertec.utility;

public class Constants {
    public static final String URL = "jdbc:postgresql://localhost:5432/banking_system";
    public static final String USER = "postgres";
    public static final String PASSWORD = "admin";

    public static final String INSERT_INTO_BANKS = "INSERT INTO banks (name) VALUES (?)";
    public static final String GET_BANK_ID_BY_NAME = "SELECT id FROM banks WHERE name = ?";


    public static final String INSERT_INTO_USERS = "INSERT INTO users (name, surname) VALUES (?, ?)";
    public static final String GET_USER_ID_BY_NAME = "SELECT id FROM users WHERE name = ? AND surname = ?";


    public static final String INSERT_INTO_ACCOUNTS = "INSERT INTO accounts (number, bank_id, user_id, balance) VALUES (?, ?, ?, ?)";
    public static final String GET_ACCOUNT_BALANCE_BY_NUMBER = "SELECT id, balance FROM accounts WHERE number = ?";
    public static final String UPDATE_BALANCE_OF_ACCOUNT = "UPDATE accounts SET balance = ? WHERE number = ?";

    public static final String INSERT_INTO_DEP_WITH_TRANSACTIONS = "INSERT INTO dep_with_transactions (type, amount, date, time, account_id) VALUES (?, ?, ?, ?, ?)";

    public static final String INSERT_INTO_TRANSFER_TRANSACTIONS = "INSERT INTO transfer_transactions (type, amount, date, time, account_of_sender_id, account_of_getter_id) VALUES (?, ?, ?, ?, ?, ?)";

    public static final String[] NAMES = {"Ruslan", "Pavel", "Eugine", "Veronika", "Anna", "Nasty", "Kate", "Arnold"};
    public static final String[] SURNAMES = {"Azimov", "Perunov", "Lobanov", "Askerko", "Ivanov", "Pavlov", "Morozov", "Alekseenko"};
}
