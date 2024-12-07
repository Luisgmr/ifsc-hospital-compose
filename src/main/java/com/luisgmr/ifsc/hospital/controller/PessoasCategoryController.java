package com.luisgmr.ifsc.hospital.controller;

import com.luisgmr.ifsc.hospital.dao.PessoasCategoryDAO;
import com.luisgmr.ifsc.hospital.enums.PessoaType;
import com.luisgmr.ifsc.hospital.model.*;

import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

public class PessoasCategoryController {
    private final PessoasCategoryDAO dao;

    public PessoasCategoryController() {
        this.dao = new PessoasCategoryDAO();
    }

    public void loadPessoas(PessoaType type) {
        ClasseDados dados = ClasseDados.getInstance();
        List<? extends Pessoa> pessoas = (List<? extends Pessoa>) dao.getAllPessoas(type);

        switch (type) {
            case PACIENTE -> {
                dados.pacientes.clear();
                dados.pacientes.addAll((List<Paciente>) pessoas);
            }
            case MEDICO -> {
                dados.medicos.clear();
                dados.medicos.addAll((List<Medico>) pessoas);
            }
            case ENFERMEIRO -> {
                dados.enfermeiros.clear();
                dados.enfermeiros.addAll((List<Enfermeiro>) pessoas);
            }
            case FARMACEUTICO -> {
                dados.farmaceuticos.clear();
                dados.farmaceuticos.addAll((List<Farmaceutico>) pessoas);
            }
            case USUARIO -> {
                dados.usuarios.clear();
                dados.usuarios.addAll((List<Usuario>) pessoas);
            }
        }
    }
}