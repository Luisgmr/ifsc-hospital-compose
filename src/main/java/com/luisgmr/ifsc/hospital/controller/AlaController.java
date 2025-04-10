package com.luisgmr.ifsc.hospital.controller;

import com.luisgmr.ifsc.hospital.dao.AlaDAO;
import com.luisgmr.ifsc.hospital.model.Ala;
import com.luisgmr.ifsc.hospital.model.ClasseDados;
import java.util.List;

public class AlaController {

    private final AlaDAO alaDAO = new AlaDAO();

    public void save(Ala ala) {
        alaDAO.save(ala);
        ClasseDados.getInstance().alas.add(ala);
    }

    public void load() {
        ClasseDados dados = ClasseDados.getInstance();
        dados.alas.clear();
        dados.alas.addAll(alaDAO.getAll());
    }

    public List<Ala> getAll() {
        return ClasseDados.getInstance().alas;
    }
}