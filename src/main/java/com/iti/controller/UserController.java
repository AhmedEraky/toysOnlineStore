package com.iti.controller;

import com.iti.model.Dao.UserDao;
import com.iti.model.Dao.implementation.UserDaoImplementation;
import com.iti.model.cfg.HibernateUtils;
import com.iti.model.entity.ShoppingCart;
import com.iti.model.entity.User;
import org.hibernate.Session;

import java.util.ArrayList;

public class UserController {

    public boolean updateUser(User oldUser,User newUser){
        UserDao userDao=new UserDaoImplementation();
        Session session= HibernateUtils.getSession();
        return userDao.updateUser(oldUser,newUser,session);
    }
    public User retriveUserByEmail(String email){
        UserDao userDao=new UserDaoImplementation();
        Session session= HibernateUtils.getSession();
        return userDao.retiveUserEmail(email,session);
    }
    public boolean insertUser(User user){
        ShoppingCart cart=new ShoppingCart();
        cart.setTotalCost(0d);
        user.setShoppingCart(cart);
        Session session=HibernateUtils.getSessionFactory().openSession();
        UserDao userDao = new UserDaoImplementation();
        return userDao.persistUser(user,session);

    }
    public ArrayList<User> retriveUsersByName(String name){
        UserDao userDao=new UserDaoImplementation();
        Session session= HibernateUtils.getSession();
        return userDao.retriveUsersByName(name,session);
    }

    public boolean validateUser(User user) {
        UserDao userDao=new UserDaoImplementation();
        Session session= HibernateUtils.getSession();
        return userDao.isUser(user,session);
    }
}
