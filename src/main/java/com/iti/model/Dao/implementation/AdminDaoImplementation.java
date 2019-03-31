package com.iti.model.Dao.implementation;

import com.iti.model.Dao.AdminDao;
import com.iti.model.entity.Admin;
import javax.persistence.PersistenceException;
import org.hibernate.HibernateException;
import org.hibernate.Session;

public class AdminDaoImplementation implements AdminDao
{

    @Override
    public boolean persistAdmin(Admin admin, Session session)
    {
        try
        {
            session.beginTransaction();
            session.persist(admin);
            session.getTransaction().commit();
            return true;
        } 
        catch (PersistenceException ex)
        {
            session.getTransaction().rollback();
            return false;
        }
    }

    @Override
    public Admin retrieveAdminByEmail(String email, Session session)
    {
        Admin admin = session.get(Admin.class, email);
        return admin;

    }

    @Override
    public boolean isAdmin(Admin admin, Session session)
    {
        Admin testAdmin = session.get(Admin.class, admin.getEmail());
        if (testAdmin == null)
        {
            return false;
        } 
        else
        {
            return true;
        }
    }
}
