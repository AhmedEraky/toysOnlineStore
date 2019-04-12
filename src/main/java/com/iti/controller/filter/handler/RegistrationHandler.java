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

    }
}
