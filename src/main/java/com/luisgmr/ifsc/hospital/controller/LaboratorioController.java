package com.luisgmr.ifsc.hospital.controller;

import com.luisgmr.ifsc.hospital.dao.LaboratorioDAO;
import com.luisgmr.ifsc.hospital.model.Laboratorio;
import com.luisgmr.ifsc.hospital.model.ClasseDados;
import java.util.List;

public class LaboratorioController {

    private final LaboratorioDAO laboratorioDAO = new LaboratorioDAO();

    public void save(Laboratorio laboratorio) {
        try {
            laboratorioDAO.save(laboratorio);
            System.out.println("Laboratório " + laboratorio.getNomeFantasia() + " foi salvo com sucesso!");
        } catch (Exception e) {
            System.err.println("Erro ao salvar o laboratório: " + e.getMessage());
        }
    }

    public void load() {
        try {
            List<Laboratorio> laboratorios = laboratorioDAO.getAll();
            ClasseDados dados = ClasseDados.getInstance();
            dados.laboratorios.clear();
            dados.laboratorios.addAll(laboratorios);
            System.out.println(laboratorios.size() + " laboratórios carregados.");
        } catch (Exception e) {
            System.err.println("Erro ao carregar laboratórios: " + e.getMessage());
        }
    }

    public List<Laboratorio> getAll() {
        return ClasseDados.getInstance().laboratorios;
    }
}