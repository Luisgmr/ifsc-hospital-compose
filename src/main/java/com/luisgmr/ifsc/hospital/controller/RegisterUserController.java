package com.luisgmr.ifsc.hospital.controller;

import com.luisgmr.ifsc.hospital.dao.RegisterUserDAO;
import com.luisgmr.ifsc.hospital.enums.UserType;
import com.luisgmr.ifsc.hospital.model.*;

public class RegisterUserController {

    private final RegisterUserDAO registerUserDAO = new RegisterUserDAO();

    public boolean register(
            UserType userType,
            String nome,
            String login,
            String senha,
            String crm,
            String cre,
            String cfr
    ) {
        try {
            Object user = createUser(userType, nome, login, senha, crm, cre, cfr);
            registerUserDAO.registerUser(user);
            addToCache(user);
            System.out.println("Usuário registrado com sucesso!");
            return true;
        } catch (Exception e) {
            System.err.println("Erro ao registrar usuário: " + e.getMessage());
            return false;
        }
    }

    private Object createUser(UserType type, String nome, String login, String senha,
                              String crm, String cre, String cfr) {
        return switch (type) {
            case USUARIO -> createUsuario(nome, login, senha);
            case MEDICO -> createMedico(nome, login, senha, crm);
            case ENFERMEIRO -> createEnfermeiro(nome, login, senha, cre);
            case FARMACEUTICO -> createFarmaceutico(nome, login, senha, cfr);
        };
    }

    private Usuario createUsuario(String nome, String login, String senha) {
        Usuario usuario = new Usuario();
        usuario.setNome(nome);
        usuario.setNomeSocial(nome);
        usuario.setLogin(login);
        usuario.setSenha(senha);
        return usuario;
    }

    private Medico createMedico(String nome, String login, String senha, String crm) {
        Medico medico = new Medico();
        medico.setNomeSocial(nome);
        medico.setLogin(login);
        medico.setSenha(senha);
        medico.setCrm(crm);
        return medico;
    }

    private Enfermeiro createEnfermeiro(String nome, String login, String senha, String cre) {
        Enfermeiro enfermeiro = new Enfermeiro();
        enfermeiro.setNomeSocial(nome);
        enfermeiro.setLogin(login);
        enfermeiro.setSenha(senha);
        enfermeiro.setCre(cre);
        return enfermeiro;
    }

    private Farmaceutico createFarmaceutico(String nome, String login, String senha, String cfr) {
        Farmaceutico farmaceutico = new Farmaceutico();
        farmaceutico.setNomeSocial(nome);
        farmaceutico.setLogin(login);
        farmaceutico.setSenha(senha);
        farmaceutico.setCfr(cfr);
        return farmaceutico;
    }

    private void addToCache(Object user) {
        ClasseDados dados = ClasseDados.getInstance();
        if (user instanceof Usuario) dados.usuarios.add((Usuario) user);
        else if (user instanceof Medico) dados.medicos.add((Medico) user);
        else if (user instanceof Enfermeiro) dados.enfermeiros.add((Enfermeiro) user);
        else if (user instanceof Farmaceutico) dados.farmaceuticos.add((Farmaceutico) user);
    }
}