package com.luisgmr.ifsc.hospital.utils;

import com.luisgmr.ifsc.hospital.config.HibernateConfig;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

import java.io.Serializable;
import java.util.List;
import java.util.function.Consumer;
import java.util.function.Function;

public class HibernateUtil {
    public static SessionFactory getSessionFactory() {
        return HibernateConfig.getSessionFactory();
    }
    public static void executeInTransaction(Consumer<Session> action) {
        Transaction transaction = null;
        try (Session session = HibernateConfig.getSessionFactory().openSession()) {
            transaction = session.beginTransaction();
            action.accept(session);
            transaction.commit();
        } catch (Exception e) {
            if (transaction != null) transaction.rollback();
            throw new RuntimeException("Erro na transação: " + e.getMessage(), e);
        }
    }

    public static void save(Object entity) {
        HibernateUtil.executeInTransaction(session -> session.persist(entity));
    }

    public static <T> T executeAndReturn(Function<Session, T> action) {
        Transaction transaction = null;
        try (Session session = HibernateConfig.getSessionFactory().openSession()) {
            transaction = session.beginTransaction();
            T result = action.apply(session);
            transaction.commit();
            return result;
        } catch (Exception e) {
            if (transaction != null) transaction.rollback();
            throw new RuntimeException("Erro na transação: " + e.getMessage(), e);
        }
    }

    public static <T> T findById(Class<T> entityClass, Serializable id) {
        return executeAndReturn(session -> session.get(entityClass, id));
    }



}
