package com.iti.model.Dao.implementation;

import com.iti.model.Dao.AdminDao;
import com.iti.model.entity.Admin;
import javax.persistence.PersistenceException;

import com.iti.model.entity.User;
import org.hibernate.Criteria;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.criterion.Restrictions;

public class AdminDaoImplementation implements AdminDao
{

    @Override
    public boolean persistAdmin(Admin admin, Session session)
    {
            session.persist(admin);
            return true;
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
        Criteria criteria=session.createCriteria(Admin.class)
                .add(Restrictions.eq("email",admin.getEmail()))
                .add(Restrictions.eq("password",admin.getPassword()));
        Admin tempUser= (Admin) criteria.uniqueResult();
        if(tempUser==null)
        {
            return false;
        }
        else {
            return true;
        }
    }
}
