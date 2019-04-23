package com.iti.model.cfg;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.ArrayList;

public class HibernateUtils {
    private static SessionFactory factory=null;

    private HibernateUtils(){}

    public static SessionFactory getSessionFactory(){
        if(factory==null){
            try {
                Configuration cfg = new Configuration().configure();
                if (System.getenv("DATABASE_URL") != null) {
                    URI dbUri = new URI(System.getenv("DATABASE_URL"));
                    String username = dbUri.getUserInfo().split(":")[0];
                    String password = dbUri.getUserInfo().split(":")[1];
                    String dbUrl = "jdbc:postgresql://" + dbUri.getHost() + ':' + dbUri.getPort()
                            + dbUri.getPath();
                    cfg = cfg.setProperty("hibernate.connection.url", dbUrl)
                            .setProperty("hibernate.connection.username", username)
                            .setProperty("hibernate.connection.password", password)
                            .setProperty("hibernate.hbm2ddl.auto", "create")
                            .setProperty("hibernate.connection.driver_class",
                                    "org.postgresql.Driver")
                            .setProperty("hibernate.dialect",
                                    "org.hibernate.dialect.PostgreSQLDialect").setProperty("hibernate.hikari.connectionTimeout", "20000").setProperty("hibernate.hikari.minimumIdle", "7").setProperty("hibernate.hikari.idleTimeout", "300000").setProperty("hibernate.hikari.maximumPoolSize", "13");
                }
                return cfg.buildSessionFactory();
            } catch (URISyntaxException ex) {
                return null;
            }
        }
        factory= new  Configuration().configure().buildSessionFactory();
        return factory;
    }

    public static Session getSession(){
        Session session=getSessionFactory().getCurrentSession();
        return session;
    }
}
