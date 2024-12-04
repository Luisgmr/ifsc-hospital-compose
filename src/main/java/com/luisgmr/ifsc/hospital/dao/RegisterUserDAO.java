package com.luisgmr.ifsc.hospital.dao;

import com.luisgmr.ifsc.hospital.database.ConnectionFactory;
import com.luisgmr.ifsc.hospital.model.*;

public class RegisterUserDAO {

    private final ConnectionFactory connectionFactory;

    public RegisterUserDAO() {
        this.connectionFactory = new ConnectionFactory();
    }

    public boolean registerUser(Object user) {
        if (user instanceof Usuario) {
            return registerUsuario((Usuario) user);
        } else if (user instanceof Medico) {
            return registerMedico((Medico) user);
        } else if (user instanceof Enfermeiro) {
            return registerEnfermeiro((Enfermeiro) user);
        } else if (user instanceof Farmaceutico) {
            return registerFarmaceutico((Farmaceutico) user);
        }
        return false;
    }

    private boolean registerUsuario(Usuario usuario) {
        String query = "INSERT INTO usuario (nome, fone1, fone2, email, cpf_cnpj, rg_inscricao_estadual, data_cadastro, endereco, cep, cidade, bairro, logradouro, complemento, login, senha, nome_social) " +
                "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
        return connectionFactory.executeUpdate(
                query,
                usuario.getNome(),
                usuario.getFone1(),
                usuario.getFone2(),
                usuario.getEmail(),
                usuario.getCpfCnpj(),
                usuario.getRgInscricaoEstadual(),
                usuario.getDataCadastro(),
                usuario.getEndereco(),
                usuario.getCep(),
                usuario.getCidade(),
                usuario.getBairro(),
                usuario.getLogradouro(),
                usuario.getComplemento(),
                usuario.getLogin(),
                usuario.getSenha(),
                usuario.getNomeSocial()
        );
    }

    private boolean registerMedico(Medico medico) {
        String query = "INSERT INTO medico (login, senha, crm, nome_social) VALUES (?, ?, ?, ?)";
        return connectionFactory.executeUpdate(
                query,
                medico.getLogin(),
                medico.getSenha(),
                medico.getCrm(),
                medico.getNomeSocial()
        );
    }

    private boolean registerEnfermeiro(Enfermeiro enfermeiro) {
        String query = "INSERT INTO enfermeiro (login, senha, cre, nome_social) VALUES (?, ?, ?, ?)";
        return connectionFactory.executeUpdate(
                query,
                enfermeiro.getLogin(),
                enfermeiro.getSenha(),
                enfermeiro.getCre(),
                enfermeiro.getNomeSocial()
        );
    }

    private boolean registerFarmaceutico(Farmaceutico farmaceutico) {
        String query = "INSERT INTO farmaceutico (login, senha, cfr, nome_social) VALUES (?, ?, ?, ?)";
        return connectionFactory.executeUpdate(
                query,
                farmaceutico.getLogin(),
                farmaceutico.getSenha(),
                farmaceutico.getCfr(),
                farmaceutico.getNomeSocial()
        );
    }
}
