package com.luisgmr.ifsc.hospital.dao;

import com.luisgmr.ifsc.hospital.database.ConnectionFactory;
import com.luisgmr.ifsc.hospital.model.Ala;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.luisgmr.ifsc.hospital.model.Quarto;

public class OldQuartoDAO {
    private final ConnectionFactory connectionFactory;

    public OldQuartoDAO() {
        this.connectionFactory = new ConnectionFactory();
    }

    public void saveQuarto(Quarto quarto) {
        String query = "INSERT INTO quarto (descricao, status, ala_id) VALUES (?, ?, ?)";
        connectionFactory.executeUpdate(query,
                quarto.getDescricao(),
                quarto.getStatus(),
                quarto.getAla().getId());
    }

    public List<Quarto> getAllQuartos() {
        String query = "SELECT q.id, q.descricao, q.status, " +
                "a.id AS ala_id, a.descricao AS ala_descricao, a.status AS ala_status " +
                "FROM quarto q JOIN ala a ON q.ala_id = a.id";
        List<Quarto> quartos = new ArrayList<>();
        try (ResultSet resultSet = connectionFactory.executeQuery(query)) {
            while (resultSet != null && resultSet.next()) {
                Ala ala = new Ala();
                ala.setId(resultSet.getLong("ala_id"));
                ala.setDescricao(resultSet.getString("ala_descricao"));
                ala.setStatus(resultSet.getString("ala_status"));

                Quarto quarto = new Quarto();
                quarto.setId(resultSet.getLong("id"));
                quarto.setDescricao(resultSet.getString("descricao"));
                quarto.setStatus(resultSet.getString("status"));
                quarto.setAla(ala);

                quartos.add(quarto);
            }
        } catch (SQLException e) {
            System.err.println("Erro ao buscar quartos: " + e.getMessage());
        }
        return quartos;
    }


}