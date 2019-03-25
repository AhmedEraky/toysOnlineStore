package com.iti.Dao.implementation;

import com.iti.model.cfg.HibernateUtils;
import com.iti.model.entity.*;
import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.criterion.Example;

import java.util.List;

public class LoginDao {

    public boolean validateLogin(User user){
        Session session= HibernateUtils.getSessionFactory().openSession();
        session.beginTransaction();

        Example example= Example.create(user);

        Criteria criteria=session.createCriteria(User.class)
                .add(example);
        List<User> details=criteria.list();
        session.getTransaction().commit();
        session.close();

        if(details.size()==0)
        {
            return false;
        }
        else {
            return true;
        }
    }
}
