package com.iti.controller.servlet;

import com.iti.model.entity.Product;
import com.iti.service.WishListRetrievalService;
import com.iti.service.impl.WishListRetrievalServiceImpl;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@WebServlet("/WishList")
public class WishListServlet extends HttpServlet
{
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
    {
        String userEmail=(String)request.getSession().getAttribute("mail");
        WishListRetrievalService retrievalService = new WishListRetrievalServiceImpl();
        List<Product> userWishList = retrievalService.retrieveUserWishList(userEmail);
        request.setAttribute("wishList", userWishList);
        RequestDispatcher dispatcher = getServletContext().getRequestDispatcher("/ViewYourWishes");
        dispatcher.forward(request, response);
    }
}
