package com.iti.controller.servlet;


import com.iti.model.response.ConfirmationResponse;

import com.iti.service.UserWishesService;
import com.iti.service.impl.UserWishesServiceImpl;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

public class ProductHandling extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String productid = request.getParameter("productid");
        String userEmail = (String) request.getSession().getAttribute("mail");
        PrintWriter out = response.getWriter();
        if (userEmail == null || userEmail.isEmpty()) {
            out.print("Please login first!");
        } else {
            //get product by id
/*==================================================*/
            /*===========================================*/
            UserWishesService userWishesServlet=new UserWishesServiceImpl();

            ConfirmationResponse confirmationResponse = userWishesServlet.insertNewUserWishes(Integer.parseInt(productid),userEmail);




                out.print(confirmationResponse.getMessage());

        }
    }
}
