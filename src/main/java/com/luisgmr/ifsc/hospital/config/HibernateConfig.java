package com.luisgmr.ifsc.hospital.config;

import lombok.Getter;
import org.hibernate.SessionFactory;
import org.hibernate.boot.Metadata;
import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;

public class HibernateConfig {
    @Getter
    private static SessionFactory sessionFactory;

    static {
        try {
            StandardServiceRegistry registry = new StandardServiceRegistryBuilder()
                    .configure("hibernate.cfg.xml")
                    .build();

            Metadata metadata = new MetadataSources(registry)
                    .getMetadataBuilder()
                    .build();

            sessionFactory = metadata.getSessionFactoryBuilder().build();
        } catch (Exception e) {
            e.printStackTrace();
            throw new ExceptionInInitializerError("Falha na inicialização do Hibernate");
        }
    }

    public static void shutdown() {
        if (sessionFactory != null) {
            sessionFactory.close();
        }
    }
}