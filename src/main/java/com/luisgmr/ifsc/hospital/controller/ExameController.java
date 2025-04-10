package com.luisgmr.ifsc.hospital.controller;

import com.luisgmr.ifsc.hospital.dao.ExameDAO;
import com.luisgmr.ifsc.hospital.model.Exame;
import com.luisgmr.ifsc.hospital.model.ClasseDados;
import java.util.List;

public class ExameController {

    private final ExameDAO exameDAO = new ExameDAO();

    public void save(Exame exame) {
        try {
            exameDAO.save(exame);
            System.out.println(exame.getTituloExame() + " foi salvo com sucesso!");
        } catch (Exception e) {
            System.err.println("Erro ao salvar o exame: " + e.getMessage());
        }
    }

    public void load() {
        try {
            List<Exame> exames = exameDAO.getAll();
            ClasseDados dados = ClasseDados.getInstance();
            dados.exames.clear();
            dados.exames.addAll(exames);
            System.out.println(exames.size() + " exames carregados.");
        } catch (Exception e) {
            System.err.println("Erro ao carregar exames: " + e.getMessage());
        }
    }

    public List<Exame> getAll() {
        return ClasseDados.getInstance().exames;
    }
}