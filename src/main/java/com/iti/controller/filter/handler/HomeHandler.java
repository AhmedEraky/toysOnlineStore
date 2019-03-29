package com.iti.controller.filter.handler;

import com.iti.model.response.Status;
import com.iti.model.entity.User;
import com.iti.model.response.AuthenticationResponse;
import com.iti.service.LoginService;
import com.iti.service.RegistrationService;
import com.iti.service.impl.LoginServiceImpl;
import com.iti.service.impl.RegistrationServiceImpl;
import org.apache.commons.beanutils.BeanUtils;
import javax.servlet.FilterChain;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import javax.servlet.http.HttpSession;


public class HomeHandler implements Handler {

    @Override
    public void handle(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain, Boolean login) throws IOException, ServletException {
        User user;
        HttpSession session=request.getSession(true);
        //if user Try to Login
        if (request.getParameter("loginButton") != null&& request.getParameter("loginButton").equals("true")) {
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
                user.setBirthDate(new SimpleDateFormat("yyyy-MM-dd").parse(date));
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
                response.sendRedirect("registration.jspx?signup="+authenticationResponse.getStatus()+"&error="+authenticationResponse.getMessage());
            }
        }
        else {
            RequestDispatcher dispatcher=request.getRequestDispatcher("index.jspx");
            dispatcher.forward(request,response);
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
