package com.iti.servlet;

import com.iti.controller.UserController;
import com.iti.model.entity.ShoppingCart;
import com.iti.model.entity.User;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Date;

@WebServlet("/ValidateRegistration")
public class ValidateRegistration extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        UserController userController=new UserController();
        User user= (User) req.getSession().getAttribute("newUser");
        ShoppingCart cart=new ShoppingCart();
        cart.setTotalCost(0d);
        user.setShoppingCart(cart);
        user.setBirthDate(new Date());
        if(userController.insertUser(user)){
            req.setAttribute("signUp","success");
            resp.sendRedirect("listOfUsers.jspx");
        }else {
            RequestDispatcher dispatcher=req.getRequestDispatcher("registration.jspx");
            req.setAttribute("signUp","fail");
            dispatcher.include(req,resp);
        }
    }
}
