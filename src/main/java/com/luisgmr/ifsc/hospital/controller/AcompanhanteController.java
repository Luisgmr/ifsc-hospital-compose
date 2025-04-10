package com.luisgmr.ifsc.hospital.controller;

import com.luisgmr.ifsc.hospital.dao.OldAcompanhanteDAO;
import com.luisgmr.ifsc.hospital.model.Acompanhante;
import com.luisgmr.ifsc.hospital.model.ClasseDados;

import java.util.List;

public class AcompanhanteController {

    private final OldAcompanhanteDAO oldAcompanhanteDAO;

    public AcompanhanteController() {
        this.oldAcompanhanteDAO = new OldAcompanhanteDAO();
    }

    /**
     * Salva um acompanhante no banco de dados.
     *
     * @param acompanhante Objeto contendo os dados do acompanhante.
     */
    public void saveAcompanhante(Acompanhante acompanhante) {
        try {
            oldAcompanhanteDAO.saveAcompanhante(acompanhante);
            System.out.println(acompanhante.getNome() + " foi salvo com sucesso!");
        } catch (Exception e) {
            System.err.println("Erro ao salvar o acompanhante: " + e.getMessage());
        }
    }

    /**
     * Carrega todos os acompanhantes do banco de dados e atualiza o cache local.
     */
    public void loadAcompanhantes() {
        try {
            List<Acompanhante> acompanhantes = oldAcompanhanteDAO.getAllAcompanhantes();
            ClasseDados dados = ClasseDados.getInstance();
            dados.acompanhantes.clear();
            dados.acompanhantes.addAll(acompanhantes);
            System.out.println(acompanhantes.size() + " acompanhantes carregados.");
        } catch (Exception e) {
            System.err.println("Erro ao carregar acompanhantes: " + e.getMessage());
        }
    }
    public List<Acompanhante> getAcompanhantes() {
        return ClasseDados.getInstance().acompanhantes;
    }
}
