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
                        resultSet.getString("uf"),
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
                            pessoa.getUf(),
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
                            pessoa.getUf(),
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
                            pessoa.getUf(),
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
                            pessoa.getUf(),
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
                            pessoa.getUf(),
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
                endereco, cep, cidade, bairro, logradouro, complemento, tipo_sanguineo, sexo, data_nascimento)
                VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, '$dataNascimento')
            """.replace("$dataNascimento", ((Paciente)pessoa).getDataNascimento().toString());
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

    public Pessoa getPessoaByCPF(String cpf, PessoaType type) {
        String query;

        switch (type) {
            case PACIENTE -> query = "SELECT * FROM paciente WHERE cpf_cnpj = ?";
            case MEDICO -> query = "SELECT * FROM medico WHERE cpf_cnpj = ?";
            case ENFERMEIRO -> query = "SELECT * FROM enfermeiro WHERE cpf_cnpj = ?";
            case FARMACEUTICO -> query = "SELECT * FROM farmaceutico WHERE cpf_cnpj = ?";
            case USUARIO -> query = "SELECT * FROM usuario WHERE cpf_cnpj = ?";
            default -> throw new IllegalArgumentException("Tipo de pessoa desconhecido: " + type);
        }

        try (ResultSet resultSet = connectionFactory.executeQuery(query, cpf)) {
            if (resultSet != null && resultSet.next()) {
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
                        resultSet.getString("uf"),
                        resultSet.getString("cep"),
                        resultSet.getString("cidade"),
                        resultSet.getString("bairro"),
                        resultSet.getString("logradouro"),
                        resultSet.getString("complemento")
                );

                // Cast específico para o tipo de pessoa
                return switch (type) {
                    case PACIENTE -> new Paciente(
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
                            pessoa.getUf(),
                            pessoa.getBairro(),
                            pessoa.getLogradouro(),
                            pessoa.getComplemento(),
                            resultSet.getString("tipo_sanguineo"),
                            resultSet.getString("sexo"),
                            resultSet.getString("nome_social"),
                            resultSet.getDate("data_nascimento").toLocalDate()
                    );
                    case MEDICO -> new Medico(
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
                            pessoa.getUf(),
                            pessoa.getBairro(),
                            pessoa.getLogradouro(),
                            pessoa.getComplemento(),
                            resultSet.getString("crm"),
                            resultSet.getString("senha"),
                            resultSet.getString("login"),
                            resultSet.getString("nome_social")
                    );
                    case ENFERMEIRO -> new Enfermeiro(
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
                            pessoa.getUf(),
                            pessoa.getBairro(),
                            pessoa.getLogradouro(),
                            pessoa.getComplemento(),
                            resultSet.getString("cre"),
                            resultSet.getString("senha"),
                            resultSet.getString("login"),
                            resultSet.getString("nome_social")
                    );
                    case FARMACEUTICO -> new Farmaceutico(
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
                            pessoa.getUf(),
                            pessoa.getBairro(),
                            pessoa.getLogradouro(),
                            pessoa.getComplemento(),
                            resultSet.getString("cfr"),
                            resultSet.getString("senha"),
                            resultSet.getString("login"),
                            resultSet.getString("nome_social")
                    );
                    case USUARIO -> new Usuario(
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
                            pessoa.getUf(),
                            pessoa.getBairro(),
                            pessoa.getLogradouro(),
                            pessoa.getComplemento(),
                            resultSet.getString("login"),
                            resultSet.getString("senha"),
                            resultSet.getString("nome_social")
                    );
                    default -> throw new IllegalArgumentException("Tipo de pessoa não suportado.");
                };
            }
        } catch (SQLException e) {
            System.err.println("Erro ao buscar pessoa por CPF: " + e.getMessage());
        }

        return null; // Retorna null se nenhuma pessoa foi encontrada
    }

    public void updatePessoa(String cpf, Pessoa novaPessoa, PessoaType type) {
        String query;

        switch (type) {
            case PACIENTE -> query = """
            UPDATE paciente SET nome = ?, fone1 = ?, fone2 = ?, email = ?, rg_inscricao_estadual = ?, 
            data_cadastro = ?, endereco = ?, cep = ?, cidade = ?, uf = ?, bairro = ?, logradouro = ?, complemento = ?, 
            tipo_sanguineo = ?, sexo = ?, data_nascimento = ? WHERE cpf_cnpj = ?
        """;
            case MEDICO -> query = """
            UPDATE medico SET nome = ?, fone1 = ?, fone2 = ?, email = ?, rg_inscricao_estadual = ?, 
            data_cadastro = ?, endereco = ?, cep = ?, cidade = ?, uf = ?, bairro = ?, logradouro = ?, complemento = ?, 
            crm = ?, login = ?, senha = ? WHERE cpf_cnpj = ?
        """;
            case ENFERMEIRO -> query = """
            UPDATE enfermeiro SET nome = ?, fone1 = ?, fone2 = ?, email = ?, rg_inscricao_estadual = ?, 
            data_cadastro = ?, endereco = ?, cep = ?, cidade = ?, uf = ?, bairro = ?, logradouro = ?, complemento = ?, 
            cre = ?, login = ?, senha = ? WHERE cpf_cnpj = ?
        """;
            case FARMACEUTICO -> query = """
            UPDATE farmaceutico SET nome = ?, fone1 = ?, fone2 = ?, email = ?, rg_inscricao_estadual = ?, 
            data_cadastro = ?, endereco = ?, cep = ?, cidade = ?, uf = ?, bairro = ?, logradouro = ?, complemento = ?, 
            cfr = ?, login = ?, senha = ? WHERE cpf_cnpj = ?
        """;
            case USUARIO -> query = """
            UPDATE usuario SET nome = ?, fone1 = ?, fone2 = ?, email = ?, rg_inscricao_estadual = ?, 
            data_cadastro = ?, endereco = ?, cep = ?, cidade = ?, uf = ?, bairro = ?, logradouro = ?, complemento = ?, 
            login = ?, senha = ? WHERE cpf_cnpj = ?
        """;
            default -> throw new IllegalArgumentException("Tipo de pessoa desconhecido: " + type);
        }

        connectionFactory.executeUpdate(query,
                novaPessoa.getNome(),
                novaPessoa.getFone1(),
                novaPessoa.getFone2(),
                novaPessoa.getEmail(),
                novaPessoa.getRgInscricaoEstadual(),
                novaPessoa.getDataCadastro(),
                novaPessoa.getEndereco(),
                novaPessoa.getCep(),
                novaPessoa.getCidade(),
                novaPessoa.getUf(),
                novaPessoa.getBairro(),
                novaPessoa.getLogradouro(),
                novaPessoa.getComplemento(),
                switch (type) {
                    case PACIENTE -> ((Paciente) novaPessoa).getTipoSanguineo();
                    case MEDICO -> ((Medico) novaPessoa).getCrm();
                    case ENFERMEIRO -> ((Enfermeiro) novaPessoa).getCre();
                    case FARMACEUTICO -> ((Farmaceutico) novaPessoa).getCfr();
                    default -> null;
                },
                switch (type) {
                    case PACIENTE -> ((Paciente) novaPessoa).getSexo();
                    case MEDICO, ENFERMEIRO, FARMACEUTICO, USUARIO -> ((Usuario) novaPessoa).getLogin();
                    default -> null;
                },
                switch (type) {
                    case PACIENTE -> ((Paciente) novaPessoa).getDataNascimento();
                    case MEDICO -> ((Medico) novaPessoa).getSenha();
                    case ENFERMEIRO -> ((Enfermeiro) novaPessoa).getSenha();
                    case FARMACEUTICO -> ((Farmaceutico) novaPessoa).getSenha();
                    case USUARIO -> ((Usuario) novaPessoa).getSenha();
                    default -> null;
                },
                cpf
        );
        System.out.println("Pessoa atualizada com sucesso!");
    }

    public void deletePessoa(String cpf, PessoaType type) {
        String query;

        switch (type) {
            case PACIENTE -> query = "DELETE FROM paciente WHERE cpf_cnpj = ?";
            case MEDICO -> query = "DELETE FROM medico WHERE cpf_cnpj = ?";
            case ENFERMEIRO -> query = "DELETE FROM enfermeiro WHERE cpf_cnpj = ?";
            case FARMACEUTICO -> query = "DELETE FROM farmaceutico WHERE cpf_cnpj = ?";
            case USUARIO -> query = "DELETE FROM usuario WHERE cpf_cnpj = ?";
            default -> throw new IllegalArgumentException("Tipo de pessoa desconhecido: " + type);
        }

        connectionFactory.executeUpdate(query, cpf);
        System.out.println("Pessoa excluída com sucesso!");
    }


}
