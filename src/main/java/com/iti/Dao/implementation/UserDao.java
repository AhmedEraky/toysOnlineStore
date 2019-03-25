package com.iti.Dao.implementation;
import com.iti.model.cfg.HibernateUtils;
import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.criterion.Restrictions;
import com.iti.model.entity.*;

import java.util.ArrayList;

public class UserDao {
    public ArrayList<User> retriveLike(String s){

        ArrayList<User> users;
        Session session= HibernateUtils.getSessionFactory().openSession();
        session.beginTransaction();

        Criteria criteria=session.createCriteria(User.class).add(Restrictions.or(
                Restrictions.like("lastName","%"+s+"%"),
                Restrictions.like("firstName","%"+s+"%")
        ));
        users= (ArrayList<User>) criteria.list();
        session.getTransaction().commit();
        session.close();

        return users;

    }
}
