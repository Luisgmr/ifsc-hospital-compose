package com.luisgmr.ifsc.hospital.controller;

import com.luisgmr.ifsc.hospital.dao.RegisterUserDAO;
import com.luisgmr.ifsc.hospital.model.*;

public class RegisterUserController {

    private final RegisterUserDAO registerUserDAO;

    public RegisterUserController() {
        this.registerUserDAO = new RegisterUserDAO();
    }

    // Método para registrar usuário genérico
    public boolean registerUser(Object user) {
        if (registerUserDAO.registerUser(user)) {
            addUserToClasseDados(user);
            return true;
        }
        return false;
    }

    // Adiciona o usuário à ClasseDados
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
