package com.luisgmr.ifsc.hospital.dao;

import com.luisgmr.ifsc.hospital.model.Quarto;
import com.luisgmr.ifsc.hospital.utils.HibernateUtil;
import org.hibernate.Session;

import java.util.List;

public class QuartoDAO {

    public void save(Quarto quarto) {
        HibernateUtil.save(quarto);
    }

    public List<Quarto> getAll() {
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            return session.createQuery("FROM Quarto", Quarto.class)
                    .setFetchSize(50)
                    .getResultList();
        }
    }
}