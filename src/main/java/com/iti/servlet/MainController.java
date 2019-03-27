package com.iti.servlet;

import com.iti.controller.UserController;
import com.iti.model.entity.User;
import org.apache.commons.beanutils.BeanUtils;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;

@WebServlet("/maincontroller")
public class MainController extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String currentPage= (String) req.getSession().getAttribute("currentPage");
        if(currentPage.equals("listOfUser")){
            RequestDispatcher dispatcher=req.getRequestDispatcher("listOfUsers.jspx");
            dispatcher.forward(req,resp);
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        String currentPage= (String) req.getSession().getAttribute("currentPage");


        if(currentPage.equals("login")){
            if(req.getSession().getAttribute("login")==null){
                req.getSession().setAttribute("login",false);
            }
            boolean login = (boolean) req.getSession().getAttribute("login");
            if(login==true)
            {
                resp.sendRedirect("listOfUsers.jspx");
            }else {
                UserController controller=new UserController();
                User user=new User();

                try {
                    BeanUtils.populate(user,req.getParameterMap());
                } catch (IllegalAccessException e) {
                    e.printStackTrace();
                } catch (InvocationTargetException e) {
                    e.printStackTrace();
                }

                if(controller.validateUser(user)){
                    resp.sendRedirect("listOfUsers.jspx");
                    req.getSession().setAttribute("login",true);

                }else {
                    resp.sendRedirect("login.jspx");
                    req.getSession().setAttribute("login",false);
                }
            }
        }
    }
}
