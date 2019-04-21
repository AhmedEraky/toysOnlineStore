package com.iti.controller.listner;

import com.iti.model.entity.CartItem;
import com.iti.model.entity.ShoppingCart;
import com.iti.model.response.Usertype;
import com.iti.service.UpdateShoppingCartService;
import com.iti.service.impl.UpdateShoppingCartServiceImpl;

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
        if(se.getSession().getAttribute("userType").equals(Usertype.customer)) {
            ShoppingCart cart = ((ShoppingCart) se.getSession().getAttribute("cart"));
            if (cart.getShoppingCartItems().size() != 0) {
                UpdateShoppingCartService updateShoppingCartService = new UpdateShoppingCartServiceImpl();
                String email = (String) se.getSession().getAttribute("mail");
                updateShoppingCartService.updateCart(cart, email);
            }
        }
    }
}
