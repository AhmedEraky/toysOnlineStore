package com.iti.controller.filter.handler;

import com.iti.model.entity.Review;
import com.iti.model.response.ReviewResponse;
import com.iti.service.ReviewService;
import com.iti.service.impl.ReviewServiceImpl;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.ArrayList;

public class ReviewHandler implements Handler {
    HttpSession session;
    @Override
    public void handle(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain, Boolean login) throws IOException, ServletException {
        session=request.getSession();
        ReviewService service=new ReviewServiceImpl();
        ArrayList<ReviewResponse> reviewResponse=service.fetch(Integer.parseInt(request.getParameter("ProductID")));

        if(reviewResponse.size()!=0) {
            request.setAttribute("averageRate", retrieveRate(reviewResponse));
            request.setAttribute("reviews",reviewResponse);/////////// on request
        }
        else{
            request.setAttribute("averageRate", 0);
            request.setAttribute("reviews",reviewResponse);/////////// on request
        }
        filterChain.doFilter(request,response);
    }
    public int retrieveRate(ArrayList<ReviewResponse> reviews){
        int sum=0;
        for(ReviewResponse review:reviews){
            sum+=review.getRate();
        }
        return sum/(reviews.size());
    }
}
