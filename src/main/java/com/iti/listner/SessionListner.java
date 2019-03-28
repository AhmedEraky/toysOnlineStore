package com.iti.listner;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpSessionEvent;
import javax.servlet.http.HttpSessionListener;

public class SessionListner implements HttpSessionListener {
    @Override
    public void sessionCreated(HttpSessionEvent se) {
        ServletContext context=se.getSession().getServletContext();
        int userCount= (int) context.getAttribute("userCount")+1;
        context.setAttribute("userCount",userCount);
    }

    @Override
    public void sessionDestroyed(HttpSessionEvent se) {
        ServletContext context=se.getSession().getServletContext();
        int userCount= (int) context.getAttribute("userCount")-1;
        context.setAttribute("userCount",userCount);
    }
}
