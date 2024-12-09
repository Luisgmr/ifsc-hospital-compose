package com.luisgmr.ifsc.hospital.controller;

import com.luisgmr.ifsc.hospital.dao.ExameDAO;
import com.luisgmr.ifsc.hospital.model.Exame;
import com.luisgmr.ifsc.hospital.model.ClasseDados;

import java.util.List;

public class ExameController {

    private final ExameDAO exameDAO;

    public ExameController() {
        this.exameDAO = new ExameDAO();
    }

    /**
     * Salva um exame no banco de dados.
     *
     * @param exame Objeto contendo os dados do exame.
     */
    public void saveExame(Exame exame) {
        try {
            exameDAO.saveExame(exame);
            System.out.println(exame.getTituloExame() + " foi salvo com sucesso!");
        } catch (Exception e) {
            System.err.println("Erro ao salvar o exame: " + e.getMessage());
        }
    }

    /**
     * Carrega todos os exames do banco de dados e atualiza o cache local.
     */
    public void loadExames() {
        try {
            List<Exame> exames = exameDAO.getAllExames();
            ClasseDados dados = ClasseDados.getInstance();
            dados.exames.clear();
            dados.exames.addAll(exames);
            System.out.println(exames.size() + " exames carregados.");
        } catch (Exception e) {
            System.err.println("Erro ao carregar exames: " + e.getMessage());
        }
    }

    /**
     * Obtém a lista de exames do cache local.
     *
     * @return Lista de exames.
     */
    public List<Exame> getExames() {
        return ClasseDados.getInstance().exames;
    }
}
