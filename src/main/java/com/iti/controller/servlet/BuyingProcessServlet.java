package com.iti.controller.servlet;

import com.iti.model.entity.CartItem;
import com.iti.model.entity.ShoppingCart;
import com.iti.model.response.Status;
import com.iti.model.response.ValidationResponse;
import com.iti.service.CreditHandlingService;
import com.iti.service.UpdateShoppingCartService;
import com.iti.service.impl.CreditHandlingServiceImpl;
import com.iti.service.impl.UpdateShoppingCartServiceImpl;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashSet;
import java.util.Set;

public class BuyingProcessServlet extends HttpServlet
{
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
    {
        Boolean loggedIn = (Boolean) request.getSession().getAttribute("login");
        if(loggedIn != null && loggedIn)
        {
            CreditHandlingService checkingService = new CreditHandlingServiceImpl();
            String userEmail =(String)request.getSession().getAttribute("mail");
            ShoppingCart cart = (ShoppingCart) request.getSession().getAttribute("cart");
            double totalCost = getTotalCostOfCart(cart);
            ValidationResponse validationResponse = checkingService.hasEnoughCredit(userEmail, totalCost);
            if(validationResponse.getStatus().equals(Status.success))
            {
                cart.setShoppingCartItems(new HashSet<>());
                cart.setTotalCost(0.0);
                UpdateShoppingCartService updateShoppingCartService = new UpdateShoppingCartServiceImpl();
                updateShoppingCartService.updateCart(cart, userEmail);
                response.sendRedirect("ViewYourCart?success");
            }
            else
            {
                response.sendRedirect("ViewYourCart?lowcredit");
            }
        }
        else
        {
            response.sendRedirect("login");
        }
    }

    private double getTotalCostOfCart(ShoppingCart cart)
    {
        double totalCost = 0;
        Set<CartItem> cartItems = cart.getShoppingCartItems();
        for(CartItem cartItem:cartItems)
        {
            totalCost+=(cartItem.getQuantity() * cartItem.getProducts().getPrice());
        }
        return totalCost;
    }
}
