package org.clevertec.utility;

import org.clevertec.aspect.exception.InitRuntimeException;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DbConnectorSingleton {

    private final static Connection CONNECTION = getInstance();

    private DbConnectorSingleton() {
    }

    private static Connection getInstance() {
        try {
            return DriverManager.getConnection(Constants.URL, Constants.USER, Constants.PASSWORD);
        } catch (SQLException e) {
            throw new InitRuntimeException("Connection error");
        }
    }

    public static Connection getConnection() {
        return CONNECTION;
    }
}
