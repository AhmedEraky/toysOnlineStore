package com.iti.controller.servlet;

import com.iti.model.entity.ShoppingCart;
import com.iti.model.entity.User;
import com.iti.model.response.Usertype;
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

        req.getSession().invalidate();
        resp.sendRedirect("home");
    }
}
