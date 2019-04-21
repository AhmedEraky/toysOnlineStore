package com.iti.controller.servlet;

import com.iti.model.Dao.CartItemDao;
import com.iti.model.Dao.ProductDao;
import com.iti.model.Dao.UserDao;
import com.iti.model.Dao.implementation.CartItemDaoImplementation;
import com.iti.model.Dao.implementation.ProductDaoImplementation;
import com.iti.model.Dao.implementation.UserDaoImplementation;
import com.iti.model.entity.CartItem;
import com.iti.model.entity.Product;
import com.iti.model.entity.ShoppingCart;
import com.iti.model.entity.User;
import com.iti.model.response.ProductResponse;
import com.iti.service.ProductService;
import com.iti.service.ProfileService;
import com.iti.service.UserWishesServlet;
import com.iti.service.impl.ProductServiceImpl;
import com.iti.service.impl.ProfileServiceImpl;
import com.iti.service.impl.UserWishesServletImpl;
import org.hibernate.Session;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;

public class ProductHandling extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String productid = request.getParameter("productid");
        String userEmail = (String) request.getSession().getAttribute("mail");
        PrintWriter out = response.getWriter();
        if (userEmail == null || userEmail.isEmpty()) {
            out.print("Please login first!");
        } else {
            //get product by id
/*==================================================*/
            /*===========================================*/
            UserWishesServlet userWishesServlet=new UserWishesServletImpl();

            Boolean flagUser = userWishesServlet.insertNewUserWishes(Integer.parseInt(productid),userEmail);

            //response.sendRedirect("productPage?ProductID="+productid);
            if (flagUser) {

                out.print("Successfully added to wishes list");
            } else {

                out.print("It has been already added to your wish list");
            }
        }
    }
}
