package com.luisgmr.ifsc.hospital.database;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class ConnectionFactory {

    public boolean executeUpdate(String query, Object... parameters) {
        try (Connection connection = DatabaseConnection.getInstance().getConnection();
             PreparedStatement statement = connection.prepareStatement(query)) {

            setParameters(statement, parameters);
            statement.executeUpdate();
            return true;
        } catch (SQLException e) {
            System.err.println("Erro ao executar a operação: " + e.getMessage());
            return false;
        }
    }

    public ResultSet executeQuery(String query, Object... parameters) {
        try {
            Connection connection = DatabaseConnection.getInstance().getConnection();
            PreparedStatement statement = connection.prepareStatement(query);

            setParameters(statement, parameters);
            return statement.executeQuery();
        } catch (SQLException e) {
            System.err.println("Erro ao executar a consulta: " + e.getMessage());
            return null;
        }
    }

    private void setParameters(PreparedStatement statement, Object... parameters) throws SQLException {
        for (int i = 0; i < parameters.length; i++) {
            statement.setObject(i + 1, parameters[i]);
        }
    }
}
