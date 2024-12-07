package com.luisgmr.ifsc.hospital.controller;

import com.luisgmr.ifsc.hospital.dao.PessoasCategoryDAO;
import com.luisgmr.ifsc.hospital.enums.PessoaType;
import com.luisgmr.ifsc.hospital.model.*;

import java.util.Collection;
import java.util.List;

public class PessoasCategoryController {
    private final PessoasCategoryDAO dao;

    public PessoasCategoryController() {
        this.dao = new PessoasCategoryDAO();
    }

    public void loadPessoas(PessoaType type) {
        List<? extends Pessoa> pessoasDoBanco = dao.getAllPessoas(type);

        ClasseDados dados = ClasseDados.getInstance();
        switch (type) {
            case PACIENTE -> {
                dados.pacientes.clear();
                dados.pacientes.addAll((Collection<? extends Paciente>) pessoasDoBanco);
            }
            case MEDICO -> {
                dados.medicos.clear();
                dados.medicos.addAll((Collection<? extends Medico>) pessoasDoBanco);
            }
            case ENFERMEIRO -> {
                dados.enfermeiros.clear();
                dados.enfermeiros.addAll((Collection<? extends Enfermeiro>) pessoasDoBanco);
            }
            case FARMACEUTICO -> {
                dados.farmaceuticos.clear();
                dados.farmaceuticos.addAll((Collection<? extends Farmaceutico>) pessoasDoBanco);
            }
            case USUARIO -> {
                dados.usuarios.clear();
                dados.usuarios.addAll((Collection<? extends Usuario>) pessoasDoBanco);
            }
        }
    }
}