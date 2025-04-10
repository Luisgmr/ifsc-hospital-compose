package com.luisgmr.ifsc.hospital.dao;

import com.luisgmr.ifsc.hospital.config.HibernateConfig;
import com.luisgmr.ifsc.hospital.enums.PessoaType;
import com.luisgmr.ifsc.hospital.model.Pessoa;
import org.hibernate.Session;
import org.hibernate.Transaction;

import java.util.List;
import java.util.stream.Collectors;

public class PessoaDAO {

    public void save(Pessoa pessoa) {
        try (Session session = HibernateConfig.getSessionFactory().openSession()) {
            Transaction transaction = session.beginTransaction();
            session.persist(pessoa);
            transaction.commit();
        } catch (Exception e) {
            System.err.println("Erro ao salvar pessoa: " + e.getMessage());
        }
    }

    public Pessoa findById(long id) {
        try (Session session = HibernateConfig.getSessionFactory().openSession()) {
            return session.get(Pessoa.class, id);
        }
    }

    public List<Pessoa> findAll(PessoaType type) {
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
}
