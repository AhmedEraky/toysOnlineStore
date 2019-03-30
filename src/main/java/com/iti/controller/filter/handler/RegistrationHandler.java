package com.iti.controller.filter.handler;

import com.iti.model.response.AuthenticationResponse;
import com.iti.model.response.Status;
import com.iti.service.RegistrationService;
import com.iti.service.impl.RegistrationServiceImpl;
import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;

public class RegistrationHandler extends HomeHandler {
    @Override
    public void handle(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain, Boolean login) throws IOException, ServletException {
        session=request.getSession();
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
}
