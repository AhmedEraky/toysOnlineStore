package com.iti.controller.servlet;

import com.iti.model.Dao.CartItemDao;
import com.iti.model.Dao.ProductDao;
import com.iti.model.Dao.implementation.CartItemDaoImplementation;
import com.iti.model.Dao.implementation.ProductDaoImplementation;
import com.iti.model.cfg.HibernateUtils;
import com.iti.model.entity.CartItem;
import com.iti.model.entity.ShoppingCart;
import com.iti.model.entity.Product;
import com.iti.model.response.AddingResponse;
import com.iti.service.AddToCartService;
import com.iti.service.impl.AddToCartServiceImpl;
import org.hibernate.Session;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashSet;
import java.util.Set;

public class ShoppingCartServlet extends HttpServlet {

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String productid = (String) request.getParameter("productid");
        int quantity = Integer.parseInt((String) request.getParameter("quantity"));
        ShoppingCart shoppingCart  =(ShoppingCart) request.getSession().getAttribute("cart");
        AddToCartService addToCartService=new AddToCartServiceImpl();
        AddingResponse addingResponse=addToCartService.addToCart(Integer.parseInt(productid),quantity,shoppingCart);
        PrintWriter out=response.getWriter();
        out.print(addingResponse.getMessage());
    }
}
