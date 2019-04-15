package com.iti.controller.servlet;

import com.iti.model.entity.ShoppingCart;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class CartModificationServlet extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String operation = request.getParameter("operation");
        String productId = request.getParameter("productId");
        String productName = request.getParameter("productName");
        String amount = request.getParameter("amount");
        ShoppingCart shoppingCart = (ShoppingCart) request.getSession().getAttribute("cart");
        if(operation.equals("increaseQuantityOperation")){

        }
        else if(operation.equals("decreaseQuantityOperation")){

        }
        else if(operation.equals("removeProductOperation")){

        }
    }
}
