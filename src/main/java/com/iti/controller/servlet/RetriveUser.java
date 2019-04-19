package com.iti.controller.servlet;

import com.google.gson.Gson;
import com.iti.model.entity.User;
import com.iti.model.response.UserResponse;
import com.iti.service.GetUsersService;
import com.iti.service.impl.GetUsersServiceImpl;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;

@WebServlet("/retrieveUser")
public class RetriveUser extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        GetUsersService service=new GetUsersServiceImpl();
        ArrayList<User> users=service.getUsers();
        req.setAttribute("users",users);

    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        GetUsersService service=new GetUsersServiceImpl();
        String name=req.getParameter("name");
        if(name==null){
            name="";
        }
        ArrayList<UserResponse> users=service.searchForUsers(name);
        req.setAttribute("users",users);
        PrintWriter out=resp.getWriter();
        out.print(new Gson().toJson(users));

    }

}
