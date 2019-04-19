package com.iti.controller.servlet;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonObject;
import com.iti.model.entity.Review;
import com.iti.model.response.ReviewResponse;
import com.iti.service.ReviewService;
import com.iti.service.impl.ReviewServiceImpl;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;

public class ReviewServlet extends HttpServlet {
    HttpSession session;
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
             rate=0;
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
        session=request.getSession();

        ArrayList<ReviewResponse> reviewResponses=service.fetch(Integer.parseInt(request.getParameter("productid")));
        JsonObject reviewJson = new JsonObject();
        Gson gsonBuilder = new GsonBuilder().create();
        String jsonFromJavaArrayList = gsonBuilder.toJson(reviewResponses);
        if(reviewResponses.size()!=0) {


            reviewJson.addProperty("averageRate", Integer.toString(retrieveRate(reviewResponses)));
            reviewJson.addProperty("reviews",jsonFromJavaArrayList);


        }
        else{
            reviewJson.addProperty("averageRate", "0");
            reviewJson.addProperty("reviews",jsonFromJavaArrayList);

        }



        PrintWriter out=response.getWriter();
            out.print(reviewJson);
      // response.sendRedirect("/toysOnlineStore_war_exploded/productPage?ProductID="+productid);

    }
/*
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        session=request.getSession();
        ReviewService service=new ReviewServiceImpl();
        ArrayList<ReviewResponse> reviewResponse=service.fetch(Integer.parseInt(request.getParameter("productid")));
        JsonObject reviewJson = new JsonObject();
        Gson gsonBuilder = new GsonBuilder().create();
        String jsonFromJavaArrayList = gsonBuilder.toJson(reviewResponse);
        if(reviewResponse.size()!=0) {


            reviewJson.addProperty("averageRate", Integer.toString(retrieveRate(reviewResponse)));
            reviewJson.addProperty("reviews",jsonFromJavaArrayList);


        }
        else{
            reviewJson.addProperty("averageRate", "0");
            reviewJson.addProperty("reviews",jsonFromJavaArrayList);

        }
       // PrintWriter out=response.getWriter();
       // out.print(reviewJson);
        response.sendRedirect("productPage?ProductID="+request.getParameter("productid"));
    }
    */
    public int retrieveRate(ArrayList<ReviewResponse> reviews){
        int sum=0;
        for(ReviewResponse review:reviews){
            sum+=review.getRate();
        }
        return sum/(reviews.size());
    }
}
