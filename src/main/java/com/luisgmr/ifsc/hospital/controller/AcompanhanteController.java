package com.luisgmr.ifsc.hospital.controller;

import com.luisgmr.ifsc.hospital.dao.AcompanhanteDAO;
import com.luisgmr.ifsc.hospital.model.Acompanhante;
import com.luisgmr.ifsc.hospital.model.ClasseDados;
import java.util.List;

public class AcompanhanteController {

    private final AcompanhanteDAO acompanhanteDAO = new AcompanhanteDAO();

    public void save(Acompanhante acompanhante) {
        try {
            acompanhanteDAO.save(acompanhante);
            System.out.println(acompanhante.getNome() + " foi salvo com sucesso!");
        } catch (Exception e) {
            System.err.println("Erro ao salvar o acompanhante: " + e.getMessage());
        }
    }

    public void load() {
        try {
            List<Acompanhante> acompanhantes = acompanhanteDAO.getAll();
            ClasseDados dados = ClasseDados.getInstance();
            dados.acompanhantes.clear();
            dados.acompanhantes.addAll(acompanhantes);
            System.out.println(acompanhantes.size() + " acompanhantes carregados.");
        } catch (Exception e) {
            System.err.println("Erro ao carregar acompanhantes: " + e.getMessage());
        }
    }

    public List<Acompanhante> getAll() {
        return ClasseDados.getInstance().acompanhantes;
    }
}