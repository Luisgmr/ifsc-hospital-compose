package com.luisgmr.ifsc.hospital.dao;

import com.luisgmr.ifsc.hospital.database.ConnectionFactory;
import com.luisgmr.ifsc.hospital.model.Exame;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ExameDAO {

    private final ConnectionFactory connectionFactory;

    public ExameDAO() {
        this.connectionFactory = new ConnectionFactory();
    }

    /**
     * Método para salvar um exame no banco de dados.
     */
    public void saveExame(Exame exame) {
        String query = """
            INSERT INTO exame (titulo_exame, tipo_exame, status)
            VALUES (?, ?, ?)
        """;
        connectionFactory.executeUpdate(
                query,
                exame.getTituloExame(),
                exame.getTipoExame(),
                exame.getStatus()
        );
    }

    /**
     * Método para buscar todos os exames no banco de dados.
     *
     * @return Lista de exames.
     */
    public List<Exame> getAllExames() {
        String query = "SELECT * FROM exame";
        List<Exame> exames = new ArrayList<>();

        try (ResultSet resultSet = connectionFactory.executeQuery(query)) {
            while (resultSet != null && resultSet.next()) {
                Exame exame = new Exame(
                        resultSet.getString("titulo_exame"),
                        resultSet.getString("tipo_exame"),
                        resultSet.getString("status")
                );
                exames.add(exame);
            }
        } catch (SQLException e) {
            System.err.println("Erro ao buscar exames: " + e.getMessage());
        }

        return exames;
    }
}
