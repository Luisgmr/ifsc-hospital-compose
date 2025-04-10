package com.luisgmr.ifsc.hospital.dao;

import com.luisgmr.ifsc.hospital.model.Laboratorio;
import com.luisgmr.ifsc.hospital.utils.HibernateUtil;
import org.hibernate.Session;

import java.util.List;

public class LaboratorioDAO {

    public void save(Laboratorio laboratorio) {
        HibernateUtil.save(laboratorio);
    }

    public List<Laboratorio> getAll() {
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            return session.createQuery("FROM Laboratorio", Laboratorio.class).getResultList();
        }
    }
}