package com.luisgmr.ifsc.hospital.controller;

import com.luisgmr.ifsc.hospital.dao.AlaDAO;
import com.luisgmr.ifsc.hospital.model.Ala;
import com.luisgmr.ifsc.hospital.model.ClasseDados;

import java.util.List;

public class AlaController {
    private final AlaDAO alaDAO;

    public AlaController() {
        this.alaDAO = new AlaDAO();
    }

    public void saveAla(Ala ala) {
        alaDAO.saveAla(ala);
        ClasseDados.getInstance().alas.add(ala);
    }

    public void loadAlas() {
        ClasseDados dados = ClasseDados.getInstance();
        List<Ala> alas = alaDAO.getAllAlas();
        dados.alas.clear();
        dados.alas.addAll(alas);
    }

    public List<Ala> getAlas() {
        return ClasseDados.getInstance().alas;
    }
}
