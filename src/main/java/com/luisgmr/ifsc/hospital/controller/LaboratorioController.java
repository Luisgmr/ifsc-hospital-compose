package com.luisgmr.ifsc.hospital.controller;

import com.luisgmr.ifsc.hospital.dao.LaboratorioDAO;
import com.luisgmr.ifsc.hospital.model.ClasseDados;
import com.luisgmr.ifsc.hospital.model.Laboratorio;

import java.util.List;

public class LaboratorioController {

    private final LaboratorioDAO laboratorioDAO;

    public LaboratorioController() {
        this.laboratorioDAO = new LaboratorioDAO();
    }

    /**
     * Salva um laboratório no banco de dados.
     *
     * @param laboratorio Objeto contendo os dados do laboratório.
     */
    public void saveLaboratorio(Laboratorio laboratorio) {
        try {
            laboratorioDAO.saveLaboratorio(laboratorio);
            System.out.println("Laboratório " + laboratorio.getNomeFantasia() + " foi salvo com sucesso!");
        } catch (Exception e) {
            System.err.println("Erro ao salvar o laboratório: " + e.getMessage());
        }
    }

    /**
     * Carrega todos os laboratórios do banco de dados e atualiza o cache local.
     */
    public void loadLaboratorios() {
        try {
            List<Laboratorio> laboratorios = laboratorioDAO.getAllLaboratorios();
            ClasseDados dados = ClasseDados.getInstance();
            dados.laboratorios.clear();
            dados.laboratorios.addAll(laboratorios);
            System.out.println(laboratorios.size() + " laboratórios carregados.");
        } catch (Exception e) {
            System.err.println("Erro ao carregar laboratórios: " + e.getMessage());
        }
    }

    /**
     * Retorna a lista de laboratórios do cache local.
     *
     * @return Lista de laboratórios.
     */
    public List<Laboratorio> getLaboratorios() {
        return ClasseDados.getInstance().laboratorios;
    }
}
