package com.iti.model.Dao.implementation;

import com.iti.model.cfg.HibernateUtils;
import org.hibernate.Session;
import com.iti.model.entity.*;


public class RegistrationDao {
    public void Registration(User user){
        Session session= HibernateUtils.getSessionFactory().openSession();
        session.beginTransaction();

        session.save(user);

        session.getTransaction().commit();
        session.close();

    }
}
