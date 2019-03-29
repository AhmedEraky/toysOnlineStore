package com.iti.controller.filter.handler;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public interface Handler {
    public void handle(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain,Boolean login) throws IOException, ServletException;
}
