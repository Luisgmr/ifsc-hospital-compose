package com.luisgmr.ifsc.hospital.controller;

import com.luisgmr.ifsc.hospital.dao.QuartoDAO;
import com.luisgmr.ifsc.hospital.model.Quarto;
import com.luisgmr.ifsc.hospital.model.ClasseDados;
import java.util.List;

public class QuartoController {

    private final QuartoDAO quartoDAO = new QuartoDAO();

    public void save(Quarto quarto) {
        quartoDAO.save(quarto);
        ClasseDados.getInstance().quartos.add(quarto);
    }

    public void load() {
        ClasseDados dados = ClasseDados.getInstance();
        dados.quartos.clear();
        dados.quartos.addAll(quartoDAO.getAll());
    }

    public List<Quarto> getAll() {
        return ClasseDados.getInstance().quartos;
    }
}