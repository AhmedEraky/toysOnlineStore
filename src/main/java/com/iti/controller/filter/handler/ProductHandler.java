package com.iti.controller.filter.handler;

import com.iti.model.response.ProductResponse;
import com.iti.service.ProductService;
import com.iti.service.impl.ProductServiceImpl;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

public class ProductHandler implements Handler {
    HttpSession session;

    @Override
    public void handle(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain, Boolean login) throws IOException, ServletException {
        session=request.getSession();
        ProductService service=new ProductServiceImpl();
      ProductResponse productResponse=service.fetch(Integer.parseInt(request.getParameter("ProductID")));
      //  ProductResponse productResponse=service.fetch(1);
        request.setAttribute("product",productResponse);/////////// on request
      //  filterChain.doFilter(request,response);

    }
}
