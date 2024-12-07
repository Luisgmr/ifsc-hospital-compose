package com.luisgmr.ifsc.hospital.controller;

import com.luisgmr.ifsc.hospital.dao.PessoasCategoryDAO;
import com.luisgmr.ifsc.hospital.model.ClasseDados;
import com.luisgmr.ifsc.hospital.model.Paciente;

import java.util.List;

public class PessoasCategoryController {
    private final PessoasCategoryDAO dao;

    public PessoasCategoryController() {
        this.dao = new PessoasCategoryDAO();
    }

    public void loadPacientes() {
        List<Paciente> pacientesDoBanco = dao.getAllPacientes();

        ClasseDados.getInstance().pacientes.clear();
        ClasseDados.getInstance().pacientes.addAll(pacientesDoBanco);
    }
}