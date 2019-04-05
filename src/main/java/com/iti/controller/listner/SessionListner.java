package com.iti.controller.listner;

import com.iti.model.entity.ShoppingCart;

import javax.servlet.ServletContext;
import javax.servlet.annotation.WebListener;
import javax.servlet.http.HttpSession;
import javax.servlet.http.HttpSessionEvent;
import javax.servlet.http.HttpSessionListener;
@WebListener
public class SessionListner implements HttpSessionListener {
    @Override
    public void sessionCreated(HttpSessionEvent se) {
        //Adding Cart to session
        HttpSession httpSession = se.getSession();
        ShoppingCart sessionCart = new ShoppingCart();
        httpSession.setAttribute("cart", sessionCart);
    }

    @Override
    public void sessionDestroyed(HttpSessionEvent se) {
        
    }
}
