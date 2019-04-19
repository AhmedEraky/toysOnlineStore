package com.iti.controller.servlet;

import com.iti.model.entity.ShoppingCart;
import com.iti.model.response.AddingResponse;
import com.iti.service.AddToCartService;
import com.iti.service.DecreaseCartItemQuantityService;
import com.iti.service.RemoveCartItemService;
import com.iti.service.impl.AddToCartServiceImpl;
import com.iti.service.impl.DecreaseCartItemQuantityServiceImpl;
import com.iti.service.impl.RemoveCartItemServiceImpl;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

public class CartModificationServlet extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        PrintWriter out=response.getWriter();
        String operation = request.getParameter("operation");
        Integer productId = Integer.parseInt(request.getParameter("productId"));
        String productName = request.getParameter("productName");
        Integer amount = Integer.parseInt(request.getParameter("amount"));
        ShoppingCart shoppingCart = (ShoppingCart) request.getSession().getAttribute("cart");
        if(operation.equals("increaseQuantityOperation")){
            AddToCartService addToCartService = new AddToCartServiceImpl();
            AddingResponse addingResponse=addToCartService.addToCart(productId,1,shoppingCart);
            out.print(addingResponse.getMessage());
        }
        else if(operation.equals("decreaseQuantityOperation")){
            DecreaseCartItemQuantityService decreaseCartItemQuantityService =
                    new DecreaseCartItemQuantityServiceImpl();
            shoppingCart = decreaseCartItemQuantityService.decreaseQuantity(productId,amount,shoppingCart);
            request.setAttribute("cart",shoppingCart);
        }
        else if(operation.equals("removeProductOperation")){
            RemoveCartItemService removeCartItemService = new RemoveCartItemServiceImpl();
            shoppingCart = removeCartItemService.removeCartItem(productId,shoppingCart);
            request.setAttribute("cart",shoppingCart);
        }
    }
}
