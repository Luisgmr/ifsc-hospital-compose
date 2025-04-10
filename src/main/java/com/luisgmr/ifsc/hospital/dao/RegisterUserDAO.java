package com.luisgmr.ifsc.hospital.dao;

import com.luisgmr.ifsc.hospital.model.*;
import com.luisgmr.ifsc.hospital.utils.HibernateUtil;

public class RegisterUserDAO {

    public void registerUser(Object user) {
        if (user instanceof Usuario) {
            HibernateUtil.save((Usuario) user);
        } else if (user instanceof Medico) {
            HibernateUtil.save((Medico) user);
        } else if (user instanceof Enfermeiro) {
            HibernateUtil.save((Enfermeiro) user);
        } else if (user instanceof Farmaceutico) {
            HibernateUtil.save((Farmaceutico) user);
        }
    }
}