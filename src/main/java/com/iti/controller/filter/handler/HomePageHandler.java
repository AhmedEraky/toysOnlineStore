package com.iti.controller.filter.handler;

import com.iti.model.response.HomePageProductsResponse;
import com.iti.model.response.Usertype;
import com.iti.service.HomePageService;
import com.iti.service.impl.HomePageServiceImpl;

import javax.servlet.FilterChain;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.ArrayList;

public class HomePageHandler implements  Handler{

    @Override
    public void handle(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain, Boolean login) throws IOException, ServletException {
        HttpSession session = request.getSession();
        Boolean loginAttribute = (Boolean)session.getAttribute("login");

        if ((loginAttribute!=null )&& loginAttribute.equals(true) && session.getAttribute("userType").equals(Usertype.admin)){
            RequestDispatcher requestDispatcher=request.getRequestDispatcher("adminHome");
            requestDispatcher.forward(request,response);
        }
        else{
            HomePageService homePageService = new HomePageServiceImpl();
            ArrayList<HomePageProductsResponse> newProducts = homePageService.getNewProducts();
            ArrayList<HomePageProductsResponse> guestFeaturedProducts =homePageService.getGuestFeaturedProducts();
            request.setAttribute("NewProducts",newProducts);
            request.setAttribute("FeaturedProducts",guestFeaturedProducts);
            filterChain.doFilter(request, response);
        }
    }
}
