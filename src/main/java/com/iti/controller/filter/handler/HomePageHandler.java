package com.iti.controller.filter.handler;

import com.iti.model.response.HomePageProductsResponse;
import com.iti.service.HomePageService;
import com.iti.service.impl.HomePageServiceImpl;

import javax.servlet.FilterChain;
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
        String loginAttribute = (String)session.getAttribute("login");
        if( (loginAttribute!=null )&& loginAttribute.equals("ture")){
            String userEmail =(String)session.getAttribute("mail");
            HomePageService homePageService = new HomePageServiceImpl();
            ArrayList<HomePageProductsResponse> newProducts = homePageService.getNewProducts();
            ArrayList<HomePageProductsResponse> userFeaturedProducts =homePageService.getUserFeaturedProducts(userEmail);
            session.setAttribute("NewProducts",newProducts);
            session.setAttribute("FeaturedProducts",userFeaturedProducts);
            filterChain.doFilter(request, response);
        }
        else{
            HomePageService homePageService = new HomePageServiceImpl();
            ArrayList<HomePageProductsResponse> newProducts = homePageService.getNewProducts();
            ArrayList<HomePageProductsResponse> guestFeaturedProducts =homePageService.getGuestFeaturedProducts();
            session.setAttribute("NewProducts",newProducts);
            session.setAttribute("FeaturedProducts",guestFeaturedProducts);
            filterChain.doFilter(request, response);
        }
    }
}
