package com.iti.controller.servlet;

import com.iti.model.entity.Review;
import com.iti.model.response.ReviewResponse;
import com.iti.service.ReviewService;
import com.iti.service.impl.ReviewServiceImpl;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class ReviewServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String productid=(String)request.getParameter("productid");
            String email=request.getParameter("userEmail");
            String name=request.getParameter("userName");
        String message=request.getParameter("reviewDescription");
        int rate;
        if((request.getParameter("rate"))!=null) {
             rate = Integer.parseInt(request.getParameter("rate"));
        }
        else{
             rate=1;
        }
        //
        ReviewResponse reviewResponse=new ReviewResponse();

        reviewResponse.setRate(rate);
        reviewResponse.setUserName(name);
        reviewResponse.setUserEmail(email);
        reviewResponse.setReviewDescription(message);
        reviewResponse.setProductid(Integer.parseInt(productid));
        //review

        //insert
        ReviewService service=new ReviewServiceImpl();
        boolean flag=service.insert(reviewResponse);

        //message
            if(flag){
                request.getSession().setAttribute("message","Your Review is added");
            }
            else{
                request.getSession().setAttribute("message","Please add your review again");
            }

////go to  llproductpage

        response.sendRedirect("/toysOnlineStore_war_exploded/productPage?ProductID="+productid);

    }
}
