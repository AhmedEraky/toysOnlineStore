package com.iti.model.cfg;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import java.util.ArrayList;

public class HibernateUtils {
    private static SessionFactory factory=null;

    private HibernateUtils(){}

    public static SessionFactory getSessionFactory(){
        if(factory==null){
            factory= new  Configuration().configure().buildSessionFactory();
        }
        return factory;
    }

    public static Session getSession(){
        Session session=getSessionFactory().getCurrentSession();
        return session;
    }
}
