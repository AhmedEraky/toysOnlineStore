package com.iti.controller.listner;

import com.iti.model.entity.ShoppingCart;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpSession;
import javax.servlet.http.HttpSessionEvent;
import javax.servlet.http.HttpSessionListener;

public class SessionListner implements HttpSessionListener {
    @Override
    public void sessionCreated(HttpSessionEvent se) {
        ServletContext context=se.getSession().getServletContext();
        int userCount= (int) context.getAttribute("userCount")+1;
        context.setAttribute("userCount",userCount);
        //Adding Cart to session
        HttpSession httpSession = se.getSession();
        ShoppingCart sessionCart = new ShoppingCart();
        httpSession.setAttribute("cart", sessionCart);
    }

    @Override
    public void sessionDestroyed(HttpSessionEvent se) {
        ServletContext context=se.getSession().getServletContext();
        int userCount= (int) context.getAttribute("userCount")-1;
        context.setAttribute("userCount",userCount);
    }
}
