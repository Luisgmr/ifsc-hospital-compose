package com.luisgmr.ifsc.hospital.dao;

import com.luisgmr.ifsc.hospital.model.Ala;
import com.luisgmr.ifsc.hospital.utils.HibernateUtil;
import org.hibernate.Session;

import java.util.List;

public class AlaDAO {

    public void save(Ala ala) {
        HibernateUtil.save(ala);
    }

    public List<Ala> getAll() {
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            return session.createQuery("FROM Ala", Ala.class).getResultList();
        }
    }
}