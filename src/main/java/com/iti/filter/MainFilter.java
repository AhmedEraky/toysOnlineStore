package com.iti.filter;

import com.iti.controller.UserController;
import com.iti.model.entity.User;
import org.apache.commons.beanutils.BeanUtils;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;

public class MainFilter implements Filter {

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        //get Main Request Information
        HttpServletRequest request = (HttpServletRequest) servletRequest;
        HttpServletResponse response = (HttpServletResponse) servletResponse;
        HttpSession session=request.getSession(true);
        String currentPage= request.getServletPath();

        //Navigation Management
        //Page Appear For User who is not Login
        Boolean login = (Boolean) request.getSession().getAttribute("login");
        if(currentPage.equals("/login.jspx")|| currentPage.equals("/registration.jspx")) {
            if(login != null && login == true) {
                RequestDispatcher dispatcher=request.getRequestDispatcher("index.jspx");
                dispatcher.forward(request,response);
            }else {
                filterChain.doFilter(request,response);
            }
        }
        //Page Appear For User Who is Login
        else {
            //if user is login Move to normal path
            if(login!=null&&login==true) {
                filterChain.doFilter(request,response);
            }
            //if user is try to login
            else if(currentPage.equals("/listOfUsers.jspx")) {
                if (session.getAttribute("loginButton") != null&& session.getAttribute("loginButton").equals("true")) {
                    UserController controller = new UserController();
                    User user = new User();
                    try {
                        BeanUtils.populate(user, request.getParameterMap());
                    } catch (IllegalAccessException e) {
                        e.printStackTrace();
                    } catch (InvocationTargetException e) {
                        e.printStackTrace();
                    }
                    if (controller.validateUser(user)) {
                        filterChain.doFilter(request, response);
                        request.getSession().setAttribute("login", true);

                    } else {
                        response.sendRedirect("login.jspx");
                    }
                    session.removeAttribute("loginButton");
                }
            }
            else {
                RequestDispatcher dispatcher=request.getRequestDispatcher("index.jspx");
                dispatcher.forward(request,response);
            }
        }


    }
}
