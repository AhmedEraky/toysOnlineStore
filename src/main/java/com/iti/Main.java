package com.iti;

import com.iti.model.Dao.implementation.CategoryDaoImplementation;
import com.iti.controller.UserController;
import com.iti.model.cfg.HibernateUtils;
import com.iti.model.entity.Category;
import com.iti.model.entity.ShoppingCart;
import com.iti.model.entity.User;

import java.util.Date;
import org.hibernate.Session;


public class Main {
    public static void main(String arg[]){

       Session session=HibernateUtils.getSession();
       session.beginTransaction();
       session.getTransaction().commit();
    }
}
