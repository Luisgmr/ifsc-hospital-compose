package com.luisgmr.ifsc.hospital.dao;

import com.luisgmr.ifsc.hospital.database.ConnectionFactory;
import com.luisgmr.ifsc.hospital.model.Laboratorio;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class LaboratorioDAO {

    private final ConnectionFactory connectionFactory;

    public LaboratorioDAO() {
        this.connectionFactory = new ConnectionFactory();
    }

    /**
     * Método para salvar um laboratório no banco de dados.
     */
    public void saveLaboratorio(Laboratorio laboratorio) {
        String query = """
            INSERT INTO laboratorio (nome_fantasia, contato, status)
            VALUES (?, ?, ?)
        """;
        connectionFactory.executeUpdate(
                query,
                laboratorio.getNomeFantasia(),
                laboratorio.getContato(),
                laboratorio.getStatus()
        );
    }

    /**
     * Método para buscar todos os laboratórios no banco de dados.
     *
     * @return Lista de laboratórios.
     */
    public List<Laboratorio> getAllLaboratorios() {
        String query = "SELECT * FROM laboratorio";
        List<Laboratorio> laboratorios = new ArrayList<>();

        try (ResultSet resultSet = connectionFactory.executeQuery(query)) {
            while (resultSet != null && resultSet.next()) {
                Laboratorio laboratorio = new Laboratorio(
                        resultSet.getString("nome_fantasia"),
                        resultSet.getString("contato"),
                        resultSet.getString("status")
                );
                laboratorios.add(laboratorio);
            }
        } catch (SQLException e) {
            System.err.println("Erro ao buscar laboratórios: " + e.getMessage());
        }

        return laboratorios;
    }
}
