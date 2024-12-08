package com.luisgmr.ifsc.hospital.dao;

import com.luisgmr.ifsc.hospital.database.ConnectionFactory;
import com.luisgmr.ifsc.hospital.model.Acompanhante;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class AcompanhanteDAO {

    private final ConnectionFactory connectionFactory;

    public AcompanhanteDAO() {
        this.connectionFactory = new ConnectionFactory();
    }

    /**
     * Método para salvar um acompanhante no banco de dados.
     */
    public void saveAcompanhante(Acompanhante acompanhante) {
        String query = """
        INSERT INTO acompanhante (nome, grau_parentesco, cpf, fone, email, status)
        VALUES (?, ?, ?, ?, ?, ?)
    """;
        connectionFactory.executeUpdate(
                query,
                acompanhante.getNome(),
                acompanhante.getGrauParentesco(),
                acompanhante.getCpf(),
                acompanhante.getFone(),
                acompanhante.getEmail(),
                acompanhante.getStatus()
        );
    }

    /**
     * Método para buscar todos os acompanhantes no banco de dados.
     *
     * @return Lista de acompanhantes.
     */
    public List<Acompanhante> getAllAcompanhantes() {
        String query = "SELECT * FROM acompanhante";
        List<Acompanhante> acompanhantes = new ArrayList<>();

        try (ResultSet resultSet = connectionFactory.executeQuery(query)) {
            while (resultSet != null && resultSet.next()) {
                Acompanhante acompanhante = new Acompanhante(
//                        resultSet.getLong("id"),
                        resultSet.getString("nome"),
                        resultSet.getString("grau_parentesco"),
                        resultSet.getString("cpf"),
                        resultSet.getString("fone"),
                        resultSet.getString("email"),
                        resultSet.getString("status")
                );
                acompanhantes.add(acompanhante);
            }
        } catch (SQLException e) {
            System.err.println("Erro ao buscar acompanhantes: " + e.getMessage());
        }

        return acompanhantes;
    }
}
