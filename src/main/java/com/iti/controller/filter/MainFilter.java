package com.iti.controller.filter;

import com.iti.controller.filter.handler.GuestHandler;
import com.iti.controller.filter.handler.Handler;
import com.iti.controller.filter.handler.HomeHandler;
import com.iti.controller.filter.handler.ProfileHandler;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

//Eraky Part

//Aya Part
import com.iti.controller.filter.handler.ProductHandler;
//Islam Part

//Ashraf Part

//Hadeer Part



public class MainFilter implements Filter {

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {

    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        //get Main Request Information
        HttpServletRequest request = (HttpServletRequest) servletRequest;
        HttpServletResponse response = (HttpServletResponse) servletResponse;
        String currentPage= request.getServletPath();
        Boolean login = (Boolean) request.getSession().getAttribute("login");
        Handler handler;

        //Navigation Management
        //Page Appear For User who is not Login
        if(currentPage.equals("/login")) {
            handler=new GuestHandler();
            handler.handle(request,response,filterChain,login);
        }
        else if(currentPage.equals("/registration")){
            handler=new GuestHandler();
            handler.handle(request,response,filterChain,login);
        }
        //Page Appear For User Who is Login
        else {
           if(currentPage.equals("/home")) {
                handler=new HomeHandler();
                handler.handle(request,response,filterChain,login);
            }
            //if user is try to go to main Page
            //Eraky Part

            //Aya Part

            else if(currentPage.equals("/productPage")) {
                //if login is true or not
               String productId=request.getParameter("ProductID");
                handler=new ProductHandler();
                handler.handle(request,response,filterChain,login);


            }

            //Islam Part

            //Ashraf Part

            //Hadeer Part
            else if(currentPage.equals("/profile")){
                handler =new ProfileHandler();
                handler.handle(request,response,filterChain,login);
            }

            //if other type of requst
            else {
                filterChain.doFilter(servletRequest,servletResponse);
            }

        }
    }

    @Override
    public void destroy() {

    }
}
