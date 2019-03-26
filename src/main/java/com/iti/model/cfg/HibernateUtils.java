package com.iti.model.cfg;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import java.util.ArrayList;

public class HibernateUtils {
    private static SessionFactory factory=null;
    ArrayList<Session> sessions;
    private static int numberOfSessions;
    private HibernateUtils(){}

    public static SessionFactory getSessionFactory(){
        if(factory==null){
            numberOfSessions=0;
            factory= new  Configuration().configure().buildSessionFactory();
        }
        return factory;
    }

    public static Session getSession(){
        Session session=getSessionFactory().openSession();
        return session;
    }
}
