package com.iti.listner;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

public class ContextListner implements ServletContextListener {
    int userCount=0;

    @Override
    public void contextInitialized(ServletContextEvent sce) {
        sce.getServletContext().setAttribute("userCount",userCount);
        System.out.println("Current count = "+userCount);
    }

    @Override
    public void contextDestroyed(ServletContextEvent sce) {

    }
}
