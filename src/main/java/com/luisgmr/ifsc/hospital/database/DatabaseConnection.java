package com.luisgmr.ifsc.hospital.database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DatabaseConnection {

    private static DatabaseConnection instance;
    private Connection connection;

    private static final String DATABASE_URL = "jdbc:mysql://100.104.103.66/hospital_compose"; // Atualize com seu endereço do banco
    private static final String DATABASE_USER = "goat"; // Usuário do banco
    private static final String DATABASE_PASSWORD = "Refacty_db5498!#"; // Senha do banco

    private DatabaseConnection() throws SQLException {
        try {
            connection = DriverManager.getConnection(DATABASE_URL, DATABASE_USER, DATABASE_PASSWORD);
        } catch (SQLException e) {
            throw new SQLException("Erro ao conectar ao banco de dados: " + e.getMessage());
        }
    }

    public static DatabaseConnection getInstance() throws SQLException {
        if (instance == null || instance.connection.isClosed()) {
            instance = new DatabaseConnection();
        }
        return instance;
    }

    public Connection getConnection() {
        return connection;
    }
}
