package com.iti.model.Dao.implementation;

import com.iti.model.Dao.UserDao;
import com.iti.model.entity.User;
import com.iti.model.util.UserUtil;
import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.criterion.Example;
import org.hibernate.criterion.Restrictions;

import javax.persistence.PersistenceException;
import java.util.ArrayList;

public class UserDaoImplementation implements UserDao {
    @Override
    public User retiveUserEmail(String email, Session session) {
        session.beginTransaction();
        Criteria criteria=session.createCriteria(User.class).add(Restrictions.eq("email",email));
        User user= (User) criteria.uniqueResult();
        session.clear();
        session.getTransaction().commit();
        return user;
    }

    @Override
    public User retriveUserByExample(User user, Session session) {
        Example example=Example.create(user);
        Criteria criteria=session.createCriteria(User.class)
                .add(example);
        return (User) criteria.uniqueResult();

    }

    @Override
    public ArrayList<User> retriveUsersByName(String name, Session session) {
        session.beginTransaction();
        Criteria criteria=session.createCriteria(User.class).add(Restrictions.like("name","%"+name+"%"));
        ArrayList<User> users= (ArrayList<User>) criteria.list();
        session.clear();
        session.getTransaction().commit();
        return users;
    }

    @Override
    public ArrayList<User> retriveUsers(Session session) {
        session.beginTransaction();
        Criteria criteria=session.createCriteria(User.class);
        ArrayList<User> users=(ArrayList<User>) criteria.list();
        session.clear();
        session.getTransaction().commit();
        return users;
    }


    @Override
    public boolean isUser(User user, Session session) {
        session.beginTransaction();
        Criteria criteria=session.createCriteria(User.class)
                .add(Restrictions.eq("email",user.getEmail()))
                .add(Restrictions.eq("password",user.getPassword()));
        User tempUser= (User) criteria.uniqueResult();
        session.clear();
        session.getTransaction().commit();
        if(tempUser==null)
        {
            return false;
        }
        else {
            return true;
        }
    }

    @Override
    public boolean persistUser(User user, Session session) {
        session.beginTransaction();
        try {
            session.save(user);
            session.getTransaction().commit();
            return true;
        }catch (PersistenceException e) {
            session.getTransaction().rollback();
            return false;
        }
    }

    @Override
    public boolean updateUser(User oldUser, User newUser, Session session) {
        session.beginTransaction();
        oldUser=session.get(User.class,oldUser.getEmail());
        UserUtil userUtil=new UserUtil();
        oldUser=session.get(User.class,oldUser.getEmail());
        userUtil.compareUser(oldUser,newUser);
        try {
            session.update(oldUser);
            session.getTransaction().commit();
            return true;
        }catch (Exception exception){
            session.getTransaction().rollback();
            return false;
        }
    }
}
