package com.luisgmr.ifsc.hospital.dao;

import com.luisgmr.ifsc.hospital.database.ConnectionFactory;
import com.luisgmr.ifsc.hospital.model.Ala;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class AlaDAO {
    private final ConnectionFactory connectionFactory;

    public AlaDAO() {
        this.connectionFactory = new ConnectionFactory();
    }

    public void saveAla(Ala ala) {
        String query = """
            INSERT INTO ala (descricao, status)
            VALUES (?, ?)
        """;
        connectionFactory.executeUpdate(query, ala.getDescricao(), ala.getStatus());
    }
    /**
     * Método para buscar todos os acompanhantes no banco de dados.
     *
     * @return Lista de acompanhantes.
     */
    public List<Ala> getAllAlas() {
        String query = "SELECT * FROM ala";
        List<Ala> alas = new ArrayList<>();
        try (ResultSet resultSet = connectionFactory.executeQuery(query)) {
            while (resultSet != null && resultSet.next()) {
                Ala ala = new Ala(
                        resultSet.getString("descricao"),
                        resultSet.getString("status")
                );
                alas.add(ala);
            }
        } catch (SQLException e) {
            System.err.println("Erro ao buscar alas: " + e.getMessage());
        }
        return alas;
    }
}
