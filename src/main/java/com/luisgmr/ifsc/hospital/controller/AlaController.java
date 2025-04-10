package com.luisgmr.ifsc.hospital.controller;

import com.luisgmr.ifsc.hospital.dao.OldAlaDAO;
import com.luisgmr.ifsc.hospital.model.Ala;
import com.luisgmr.ifsc.hospital.model.ClasseDados;

import java.util.List;

public class AlaController {
    private final OldAlaDAO oldAlaDAO;

    public AlaController() {
        this.oldAlaDAO = new OldAlaDAO();
    }

    public void saveAla(Ala ala) {
        oldAlaDAO.saveAla(ala);
        ClasseDados.getInstance().alas.add(ala);
    }

    public void loadAlas() {
        ClasseDados dados = ClasseDados.getInstance();
        List<Ala> alas = oldAlaDAO.getAllAlas();
        dados.alas.clear();
        dados.alas.addAll(alas);
    }

    public List<Ala> getAlas() {
        return ClasseDados.getInstance().alas;
    }
}
