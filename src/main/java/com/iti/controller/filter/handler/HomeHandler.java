package com.iti.controller.filter.handler;

import com.iti.model.entity.User;
import org.apache.commons.beanutils.BeanUtils;
import javax.servlet.FilterChain;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import javax.servlet.http.HttpSession;


public class HomeHandler implements Handler {
    User user;
    Handler handler;
    HttpSession session;

    @Override
    public void handle(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain, Boolean login) throws IOException, ServletException {
        //if user Try to Login
        if (request.getParameter("loginButton") != null&& request.getParameter("loginButton").equals("true")) {
            handler=new LoginHandler();
            handler.handle(request,response,filterChain,login);
        }
        //if user Try to go to home page
        else {

            handler = new HomePageHandler();
            handler.handle(request,response,filterChain,login);

        }
    }


    //Create New User From Input Parameter
    protected User createUser(HttpServletRequest request) {
        User user = new User();
        try {
            BeanUtils.populate(user, request.getParameterMap());
        } catch (InvocationTargetException e) { e.printStackTrace(); } catch (IllegalAccessException e) {
            e.printStackTrace();
        }
        return user;
    }

}
