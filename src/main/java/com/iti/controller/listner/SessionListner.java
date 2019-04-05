package com.iti.controller.listner;

import com.iti.model.entity.CartItem;
import com.iti.model.entity.ShoppingCart;

import javax.servlet.ServletContext;
import javax.servlet.annotation.WebListener;
import javax.servlet.http.HttpSession;
import javax.servlet.http.HttpSessionEvent;
import javax.servlet.http.HttpSessionListener;
import java.util.HashSet;

@WebListener
public class SessionListner implements HttpSessionListener {
    @Override
    public void sessionCreated(HttpSessionEvent se) {
        //Adding Cart to session
        HttpSession httpSession = se.getSession();
        ShoppingCart sessionCart = new ShoppingCart();
        sessionCart.setShoppingCartItems(new HashSet<CartItem>());
        httpSession.setAttribute("cart", sessionCart);
    }

    @Override
    public void sessionDestroyed(HttpSessionEvent se) {

    }
}
