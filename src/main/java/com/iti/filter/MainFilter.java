package com.iti.filter;

import com.iti.controller.UserController;
import com.iti.model.constants.Status;
import com.iti.model.entity.User;
import com.iti.model.response.AuthenticationResponse;
import com.iti.model.response.ValidationResponse;
import com.iti.service.LoginService;
import com.iti.service.RegistrationService;
import com.iti.service.impl.LoginServiceImpl;
import com.iti.service.impl.RegistrationServiceImpl;
import org.apache.commons.beanutils.BeanUtils;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.text.ParseException;
import java.text.SimpleDateFormat;

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
            //if user is try to go to main Page
            else if(currentPage.equals("/listOfUsers.jspx")) {
                User user;
                //if user Try to Login
                if (session.getAttribute("loginButton") != null&& session.getAttribute("loginButton").equals("true")) {
                    session.removeAttribute("loginButton");
                    user = createUser(request);
                    LoginService service=new LoginServiceImpl();
                    AuthenticationResponse loginResponse=service.login(user);
                    if (loginResponse.getStatus().equals(Status.success)) {
                        session.setAttribute("login", true);
                        filterChain.doFilter(request, response);
                    } else {
                        session.setAttribute("errorMessage",loginResponse.getMessage());
                        response.sendRedirect("login.jspx");
                    }
                }
                //if user try to register
                else if(request.getParameter("registrationButton")!=null&&request.getParameter("registrationButton").equals("true")){
                    user = createUser(request);
                    String date=request.getParameter("dateOfBirth");
                    try {
                        user.setBirthDate(new SimpleDateFormat("dd-mm-yyyy").parse(date));
                    } catch (ParseException e) {
                        e.printStackTrace();
                    }
                    RegistrationService service=new RegistrationServiceImpl();
                    AuthenticationResponse authenticationResponse=service.register(user);

                    if(authenticationResponse.getStatus().equals(Status.success)){
                        session.setAttribute("login",true);
                        filterChain.doFilter(request,response);
                    }else {
                        session.setAttribute("errorMessage",authenticationResponse.getMessage());
                        response.sendRedirect("registration.jspx?signup=fail");
                    }
                }else {
                    RequestDispatcher dispatcher=request.getRequestDispatcher("index.jspx");
                    dispatcher.forward(request,response);
                }
            }
            else {
                filterChain.doFilter(servletRequest,servletResponse);
            }
        }


    }

    private User createUser(HttpServletRequest request) {
        User user = new User();
        try {
            BeanUtils.populate(user, request.getParameterMap());
        } catch (InvocationTargetException e) { e.printStackTrace(); } catch (IllegalAccessException e) {
            e.printStackTrace();
        }
        return user;
    }
}
