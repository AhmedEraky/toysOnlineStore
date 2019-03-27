package com.iti.servlet;

import com.iti.controller.UserController;
import com.iti.model.entity.User;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;

@WebServlet("/retrieveUser")
public class RetriveUser extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        UserController controller=new UserController();
        String name=req.getParameter("name");
        if(name==null){
            name="";
        }
        ArrayList<User> users=controller.retriveUsersByName(name);
        req.setAttribute("users",users);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        UserController controller=new UserController();
        ArrayList<User> users=controller.retriveUsersByName("");
        req.setAttribute("users",users);

    }

}
