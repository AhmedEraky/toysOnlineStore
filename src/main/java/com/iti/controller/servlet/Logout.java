package com.iti.controller.servlet;

import com.iti.model.entity.ShoppingCart;
import com.iti.model.entity.User;
import com.iti.service.UpdateShoppingCartService;
import com.iti.service.impl.UpdateShoppingCartServiceImpl;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/logout")
public class Logout extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        //todo Save Cart To Database
        ShoppingCart cart= ((ShoppingCart) req.getSession().getAttribute("cart"));
        if(cart.getShoppingCartItems().size()!=0){
            UpdateShoppingCartService updateShoppingCartService=new UpdateShoppingCartServiceImpl();
            String email= (String) req.getSession().getAttribute("mail");
            updateShoppingCartService.updateCart(cart,email);
        }

        req.getSession().invalidate();
        resp.sendRedirect("home");
    }
}
