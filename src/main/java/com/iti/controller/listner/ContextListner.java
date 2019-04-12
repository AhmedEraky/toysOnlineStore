package com.iti.controller.listner;

import com.iti.model.cfg.HibernateUtils;
import org.hibernate.Session;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

public class ContextListner implements ServletContextListener {
    int imageCount;

    @Override
    public void contextInitialized(ServletContextEvent sce) {
        HibernateUtils.getSessionFactory();
        imageCount=0;
        sce.getServletContext().setAttribute("imageCount",imageCount);
    }

    @Override
    public void contextDestroyed(ServletContextEvent sce) {

    }
}
