package com.luisgmr.ifsc.hospital.dao;

import com.luisgmr.ifsc.hospital.model.Exame;
import com.luisgmr.ifsc.hospital.utils.HibernateUtil;
import org.hibernate.Session;

import java.util.List;

public class ExameDAO {

    public void save(Exame exame) {
        HibernateUtil.save(exame);
    }

    public List<Exame> getAll() {
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            return session.createQuery("FROM Exame", Exame.class).getResultList();
        }
    }
}