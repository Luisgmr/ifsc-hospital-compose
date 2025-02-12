package com.luisgmr.ifsc.hospital.controller;

import com.luisgmr.ifsc.hospital.dao.QuartoDAO;
import com.luisgmr.ifsc.hospital.model.Quarto;
import com.luisgmr.ifsc.hospital.model.ClasseDados;

public class QuartoController {
    private final QuartoDAO quartoDAO;

    public QuartoController() {
        this.quartoDAO = new QuartoDAO();
    }

    public void saveQuarto(Quarto quarto) {
        quartoDAO.saveQuarto(quarto);
        ClasseDados.getInstance().quartos.add(quarto);
    }

    public void loadQuartos() {
        ClasseDados dados = ClasseDados.getInstance();
        dados.quartos.clear();
        dados.quartos.addAll(quartoDAO.getAllQuartos());
    }

    public java.util.List<Quarto> getQuartos() {
        return ClasseDados.getInstance().quartos;
    }

}
