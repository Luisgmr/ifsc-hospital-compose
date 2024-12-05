package com.luisgmr.ifsc.hospital.controller;

import com.luisgmr.ifsc.hospital.dao.RegisterUserDAO;
import com.luisgmr.ifsc.hospital.enums.UserType;
import com.luisgmr.ifsc.hospital.model.*;

public class RegisterUserController {

    private final RegisterUserDAO registerUserDAO;

    public RegisterUserController() {
        this.registerUserDAO = new RegisterUserDAO();
    }

    public boolean registerUser(
            UserType userType,
            String nome,
            String login,
            String senha,
            String crm,
            String cre,
            String cfr
    ) {
        // Criação do usuário conforme o tipo selecionado
        Object user;
        switch (userType) {
            case USUARIO:
                user = new Usuario();
                ((Usuario) user).setNome(nome);
                ((Usuario) user).setNomeSocial(nome);
                ((Usuario) user).setLogin(login);
                ((Usuario) user).setSenha(senha);
                break;
            case MEDICO:
                user = new Medico();
                ((Medico) user).setNomeSocial(nome);
                ((Medico) user).setLogin(login);
                ((Medico) user).setSenha(senha);
                ((Medico) user).setCrm(crm);
                break;
            case ENFERMEIRO:
                user = new Enfermeiro();
                ((Enfermeiro) user).setNomeSocial(nome);
                ((Enfermeiro) user).setLogin(login);
                ((Enfermeiro) user).setSenha(senha);
                ((Enfermeiro) user).setCre(cre);
                break;
            case FARMACEUTICO:
                user = new Farmaceutico();
                ((Farmaceutico) user).setNomeSocial(nome);
                ((Farmaceutico) user).setLogin(login);
                ((Farmaceutico) user).setSenha(senha);
                ((Farmaceutico) user).setCfr(cfr);
                break;
            default:
                throw new IllegalArgumentException("Tipo de usuário inválido");
        }

        if (registerUserDAO.registerUser(user)) {
            addUserToClasseDados(user);
            System.out.println("Usuário registrado com sucesso!");
            return true;
        } else {
            System.out.println("Ocorreu um erro ao registrar o usuário.");
            return false;
        }
    }

    private void addUserToClasseDados(Object user) {
        ClasseDados classeDados = ClasseDados.getInstance();

        if (user instanceof Usuario) {
            classeDados.usuarios.add((Usuario) user);
        } else if (user instanceof Medico) {
            classeDados.medicos.add((Medico) user);
        } else if (user instanceof Enfermeiro) {
            classeDados.enfermeiros.add((Enfermeiro) user);
        } else if (user instanceof Farmaceutico) {
            classeDados.farmaceuticos.add((Farmaceutico) user);
        }
    }
}
