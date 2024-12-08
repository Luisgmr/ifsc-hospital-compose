package com.luisgmr.ifsc.hospital.dao;

import com.luisgmr.ifsc.hospital.model.Paciente;
import com.luisgmr.ifsc.hospital.database.ConnectionFactory;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import com.luisgmr.ifsc.hospital.model.*;
import com.luisgmr.ifsc.hospital.enums.PessoaType;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class PessoasCategoryDAO {
    private final ConnectionFactory connectionFactory;

    public PessoasCategoryDAO() {
        this.connectionFactory = new ConnectionFactory();
    }

    public List<?> getAllPessoas(PessoaType type) {
        String query;
        List<Pessoa> pessoas = new ArrayList<>();

        switch (type) {
            case PACIENTE -> query = "SELECT * FROM paciente";
            case MEDICO -> query = "SELECT * FROM medico";
            case ENFERMEIRO -> query = "SELECT * FROM enfermeiro";
            case FARMACEUTICO -> query = "SELECT * FROM farmaceutico";
            case USUARIO -> query = "SELECT * FROM usuario";
            default -> throw new IllegalArgumentException("Tipo de pessoa desconhecido: " + type);
        }

        try (ResultSet resultSet = connectionFactory.executeQuery(query)) {
            while (resultSet != null && resultSet.next()) {
                // Criando um objeto base Pessoa
                Pessoa pessoa = new Pessoa(
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
                        resultSet.getString("complemento")
                );
                switch (type) {
                    case PACIENTE -> pessoas.add(new Paciente(
//                            pessoa.getId(),
                            pessoa.getNome(),
                            pessoa.getFone1(),
                            pessoa.getFone2(),
                            pessoa.getEmail(),
                            pessoa.getCpfCnpj(),
                            pessoa.getRgInscricaoEstadual(),
                            pessoa.getDataCadastro(),
                            pessoa.getEndereco(),
                            pessoa.getCep(),
                            pessoa.getCidade(),
                            pessoa.getBairro(),
                            pessoa.getLogradouro(),
                            pessoa.getComplemento(),
                            resultSet.getString("tipo_sanguineo"),
                            resultSet.getString("sexo"),
                            resultSet.getString("nome_social"),
                            resultSet.getDate("data_nascimento").toLocalDate()
                    ));
                    case MEDICO -> pessoas.add(new Medico(
//                            pessoa.getId(),
                            pessoa.getNome(),
                            pessoa.getFone1(),
                            pessoa.getFone2(),
                            pessoa.getEmail(),
                            pessoa.getCpfCnpj(),
                            pessoa.getRgInscricaoEstadual(),
                            pessoa.getDataCadastro(),
                            pessoa.getEndereco(),
                            pessoa.getCep(),
                            pessoa.getCidade(),
                            pessoa.getBairro(),
                            pessoa.getLogradouro(),
                            pessoa.getComplemento(),
                            resultSet.getString("crm"),
                            resultSet.getString("senha"),
                            resultSet.getString("login"),
                            resultSet.getString("nome_social")
                    ));
                    case ENFERMEIRO -> pessoas.add(new Enfermeiro(
//                            pessoa.getId(),
                            pessoa.getNome(),
                            pessoa.getFone1(),
                            pessoa.getFone2(),
                            pessoa.getEmail(),
                            pessoa.getCpfCnpj(),
                            pessoa.getRgInscricaoEstadual(),
                            pessoa.getDataCadastro(),
                            pessoa.getEndereco(),
                            pessoa.getCep(),
                            pessoa.getCidade(),
                            pessoa.getBairro(),
                            pessoa.getLogradouro(),
                            pessoa.getComplemento(),
                            resultSet.getString("cre"),
                            resultSet.getString("senha"),
                            resultSet.getString("login"),
                            resultSet.getString("nome_social")
                    ));
                    case FARMACEUTICO -> pessoas.add(new Farmaceutico(
//                            pessoa.getId(),
                            pessoa.getNome(),
                            pessoa.getFone1(),
                            pessoa.getFone2(),
                            pessoa.getEmail(),
                            pessoa.getCpfCnpj(),
                            pessoa.getRgInscricaoEstadual(),
                            pessoa.getDataCadastro(),
                            pessoa.getEndereco(),
                            pessoa.getCep(),
                            pessoa.getCidade(),
                            pessoa.getBairro(),
                            pessoa.getLogradouro(),
                            pessoa.getComplemento(),
                            resultSet.getString("cfr"),
                            resultSet.getString("senha"),
                            resultSet.getString("login"),
                            resultSet.getString("nome_social")
                    ));
                    case USUARIO -> pessoas.add(new Usuario(
//                            pessoa.getId(), // TODO ARRUMAR PRA PODER TER ID
                            pessoa.getNome(),
                            pessoa.getFone1(),
                            pessoa.getFone2(),
                            pessoa.getEmail(),
                            pessoa.getCpfCnpj(),
                            pessoa.getRgInscricaoEstadual(),
                            pessoa.getDataCadastro(),
                            pessoa.getEndereco(),
                            pessoa.getCep(),
                            pessoa.getCidade(),
                            pessoa.getBairro(),
                            pessoa.getLogradouro(),
                            pessoa.getComplemento(),
                            resultSet.getString("login"),
                            resultSet.getString("senha"),
                            resultSet.getString("nome_social")
                    ));
                }
            }
        } catch (SQLException e) {
            System.err.println("Erro ao buscar pessoas: " + e.getMessage());
        }
        return pessoas;
    }

    public void insertPessoa(Pessoa pessoa) {
        PessoaType type;

        if (pessoa instanceof Paciente) {
            type = PessoaType.PACIENTE;
        } else if (pessoa instanceof Medico) {
            type = PessoaType.MEDICO;
        } else if (pessoa instanceof Enfermeiro) {
            type = PessoaType.ENFERMEIRO;
        } else if (pessoa instanceof Farmaceutico) {
            type = PessoaType.FARMACEUTICO;
        } else if (pessoa instanceof Usuario) {
            type = PessoaType.USUARIO;
        } else {
            throw new IllegalArgumentException("Tipo desconhecido: " + pessoa.getClass().getSimpleName());
        }

        String query = switch (type) {
            case PACIENTE -> """
                INSERT INTO paciente (nome, fone1, fone2, email, cpf_cnpj, rg_inscricao_estadual, data_cadastro,
                endereco, cep, cidade, bairro, logradouro, complemento, tipo_sanguineo, sexo)
                VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)
            """;
            case MEDICO -> """
                INSERT INTO medico (nome, fone1, fone2, email, cpf_cnpj, rg_inscricao_estadual, data_cadastro,
                endereco, cep, cidade, bairro, logradouro, complemento, crm, login)
                VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)
            """;
            case ENFERMEIRO -> """
                INSERT INTO enfermeiro (nome, fone1, fone2, email, cpf_cnpj, rg_inscricao_estadual, data_cadastro,
                endereco, cep, cidade, bairro, logradouro, complemento, cre, login)
                VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)
            """;
            case FARMACEUTICO -> """
                INSERT INTO farmaceutico (nome, fone1, fone2, email, cpf_cnpj, rg_inscricao_estadual, data_cadastro,
                endereco, cep, cidade, bairro, logradouro, complemento, cfr, login)
                VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)
            """;
            case USUARIO -> """
                INSERT INTO usuario (nome, fone1, fone2, email, cpf_cnpj, rg_inscricao_estadual, data_cadastro,
                endereco, cep, cidade, bairro, logradouro, complemento, login, senha)
                VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)
            """;
        };

        connectionFactory.executeUpdate(query,
                pessoa.getNome(), pessoa.getFone1(), pessoa.getFone2(), pessoa.getEmail(),
                pessoa.getCpfCnpj(), pessoa.getRgInscricaoEstadual(), pessoa.getDataCadastro(),
                pessoa.getEndereco(), pessoa.getCep(), pessoa.getCidade(), pessoa.getBairro(),
                pessoa.getLogradouro(), pessoa.getComplemento(),
                switch (type) {
                    case PACIENTE -> ((Paciente) pessoa).getTipoSanguineo();
                    case MEDICO -> ((Medico) pessoa).getCrm();
                    case ENFERMEIRO -> ((Enfermeiro) pessoa).getCre();
                    case FARMACEUTICO -> ((Farmaceutico) pessoa).getCfr();
                    case USUARIO -> ((Usuario) pessoa).getLogin();
                    default -> null;
                },
                switch (type) {
                    case PACIENTE -> ((Paciente) pessoa).getSexo();
                    case MEDICO -> ((Medico) pessoa).getLogin();
                    case ENFERMEIRO -> ((Enfermeiro) pessoa).getLogin();
                    case FARMACEUTICO -> ((Farmaceutico) pessoa).getLogin();
                    case USUARIO -> ((Usuario) pessoa).getSenha();
                    default -> null;
                }
        );
    }

}
