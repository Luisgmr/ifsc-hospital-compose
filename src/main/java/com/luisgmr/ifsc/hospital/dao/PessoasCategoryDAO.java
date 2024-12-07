package com.luisgmr.ifsc.hospital.dao;

import com.luisgmr.ifsc.hospital.model.Paciente;
import com.luisgmr.ifsc.hospital.database.ConnectionFactory;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class PessoasCategoryDAO {
    private final ConnectionFactory connectionFactory;

    public PessoasCategoryDAO() {
        this.connectionFactory = new ConnectionFactory();
    }

    public List<Paciente> getAllPacientes() {
        List<Paciente> pacientes = new ArrayList<>();
        String query = """
SELECT id,
nome, fone1, fone2, email, cpf_cnpj, rg_inscricao_estadual, data_cadastro,
endereco, cep, cidade, bairro, logradouro, complemento, tipo_sanguineo, sexo,
nome_social, data_nascimento
FROM paciente
                """;

        try (ResultSet resultSet = connectionFactory.executeQuery(query)) {
            while (resultSet != null && resultSet.next()) {
                Paciente paciente = new Paciente(
                        resultSet.getLong("id"),
                        resultSet.getString("nome"),
                        resultSet.getString("fone1"),
                        resultSet.getString("fone2"),
                        resultSet.getString("email"),
                        resultSet.getString("cpf_cnpj"),
                        resultSet.getString("rg_inscricao_estadual"),
                        resultSet.getString("data_cadastro"),
                        resultSet.getString("endereco"),
                        resultSet.getString("cep"),
                        resultSet.getString("cidade"),
                        resultSet.getString("bairro"),
                        resultSet.getString("logradouro"),
                        resultSet.getString("complemento"),
                        resultSet.getString("tipo_sanguineo"),
                        resultSet.getString("sexo"),
                        resultSet.getString("nome_social"),
                        resultSet.getDate("data_nascimento").toLocalDate()
                );
                pacientes.add(paciente);
            }
        } catch (SQLException e) {
            System.err.println("Erro ao buscar pacientes: " + e.getMessage());
        }

        return pacientes;
    }
}
