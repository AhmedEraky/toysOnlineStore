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
        Criteria criteria=session.createCriteria(User.class).add(Restrictions.eq("email",email));
        User user= (User) criteria.uniqueResult();
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
        Criteria criteria=session.createCriteria(User.class).add(Restrictions.like("name","%"+name+"%"));
        ArrayList<User> users= (ArrayList<User>) criteria.list();
        return users;
    }

    @Override
    public User retiveUserEmailNew(String email, Session session) {
        Criteria criteria=session.createCriteria(User.class).add(Restrictions.eq("email",email));
        User user= (User) criteria.uniqueResult();
        return user;
    }
    @Override
    public ArrayList<User> retriveUsers(Session session) {
        Criteria criteria=session.createCriteria(User.class);
        ArrayList<User> users=(ArrayList<User>) criteria.list();
        return users;
    }


    @Override
    public boolean isUser(User user, Session session) {
        Criteria criteria=session.createCriteria(User.class)
                .add(Restrictions.eq("email",user.getEmail()))
                .add(Restrictions.eq("password",user.getPassword()));
        User tempUser= (User) criteria.uniqueResult();
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
        session.save(user);
        return true;
    }

    @Override
    public boolean updateUser(User oldUser, User newUser, Session session) {
        oldUser=session.get(User.class,oldUser.getEmail());
        UserUtil userUtil=new UserUtil();
        oldUser=session.get(User.class,oldUser.getEmail());
        userUtil.compareUser(oldUser,newUser);
        session.update(oldUser);
        return true;
    }


    @Override
    public boolean updateUser(User user, Session session) {
<<<<<<< HEAD
        session.beginTransaction();
        try {
            session.update(user);
            session.getTransaction().commit();
            return true;
        }catch (PersistenceException e) {
            session.getTransaction().rollback();
            return false;
        }

=======
        session.saveOrUpdate(user);
        return true;
>>>>>>> f67d15b51c18eb969ac6cfa89ff953ca1d00c061
    }
}
