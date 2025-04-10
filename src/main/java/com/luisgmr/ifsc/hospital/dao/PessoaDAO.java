package com.luisgmr.ifsc.hospital.dao;

import com.luisgmr.ifsc.hospital.config.HibernateConfig;
import com.luisgmr.ifsc.hospital.enums.PessoaType;
import com.luisgmr.ifsc.hospital.model.Pessoa;
import com.luisgmr.ifsc.hospital.utils.HibernateUtil;
import org.hibernate.Session;
import org.hibernate.Transaction;

import java.util.List;
import java.util.stream.Collectors;

public class PessoaDAO {

    public void save(Pessoa pessoa) {
        HibernateUtil.save(pessoa);
    }

    public Pessoa findById(long id) {
        return HibernateUtil.findById(Pessoa.class, id);
    }

    public List<Pessoa> findAllByType(PessoaType type) {
        try (Session session = HibernateConfig.getSessionFactory().openSession()) {
            return session.createQuery(
                    "FROM " + type.getClassObject().getSimpleName(),
                    type.getClassObject()
            )
                    .stream()
                    .map(pessoa -> (Pessoa) pessoa)
                    .collect(Collectors.toList());
        }
    }

    public void deleteById(long id) {
        HibernateUtil.executeInTransaction(session -> session.delete(findById(id)));
    }
}
