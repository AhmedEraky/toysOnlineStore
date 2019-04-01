package com.iti.controller.filter.handler;

import com.iti.model.entity.User;
import com.iti.service.ProfileService;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class ProfileHandler implements Handler {
    @Override
    public void handle(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain, Boolean login) throws IOException, ServletException {
        if(login==true){
            User user=new User();
        }else {
            response.sendRedirect("login");
        }
    }
}
