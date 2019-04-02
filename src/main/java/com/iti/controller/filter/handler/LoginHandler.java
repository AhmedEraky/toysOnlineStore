package com.iti.controller.filter.handler;

import com.iti.model.response.AuthenticationResponse;
import com.iti.model.response.Status;
import com.iti.service.LoginService;
import com.iti.service.impl.LoginServiceImpl;
import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class LoginHandler  extends HomeHandler{

    @Override
    public void handle(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain, Boolean login) throws IOException, ServletException {
        session=request.getSession();
        user = createUser(request);
        LoginService service=new LoginServiceImpl();
        AuthenticationResponse loginResponse=service.login(user);
        if (loginResponse.getStatus().equals(Status.success)) {
            session.setAttribute("login",true);
            session.setAttribute("mail",user.getEmail());
            filterChain.doFilter(request, response);
        } else {
            session.setAttribute("errorMessage",loginResponse.getMessage());
            response.sendRedirect("login.jspx");
        }
    }
}
