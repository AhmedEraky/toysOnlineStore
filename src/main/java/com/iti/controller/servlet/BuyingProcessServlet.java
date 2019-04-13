package com.iti.controller.servlet;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class BuyingProcessServlet extends HttpServlet
{
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
    {
        Boolean loggedIn = (Boolean) request.getSession().getAttribute("login");
        if(loggedIn != null && loggedIn)
        {

        }
        else
        {
            response.sendRedirect("login");
        }
    }
}
