package com.luisgmr.ifsc.hospital.dao;

import com.luisgmr.ifsc.hospital.model.Acompanhante;
import com.luisgmr.ifsc.hospital.utils.HibernateUtil;
import org.hibernate.Session;

import java.util.List;

public class AcompanhanteDAO {

    public void save(Acompanhante acompanhante) {
        HibernateUtil.save(acompanhante);
    }

    public List<Acompanhante> getAll() {
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            return session.createQuery("FROM Acompanhante", Acompanhante.class).getResultList();
        }
    }
}