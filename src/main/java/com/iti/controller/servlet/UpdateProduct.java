package com.iti.controller.servlet;

import com.iti.model.entity.Product;
import com.iti.service.ProductService;
import com.iti.service.impl.ProductServiceImpl;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class UpdateProduct extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        int productId = Integer.parseInt(req.getParameter("productId"));
        ProductService productService = new ProductServiceImpl();
        productService.removeProduct(productId);
        resp.sendRedirect("shop");
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        int productId = Integer.parseInt(req.getParameter("productId"));
        int quantity = Integer.parseInt(req.getParameter("productQuantity"));
        double price = Double.parseDouble(req.getParameter("productPrice"));
        int discountPercentage = Integer.parseInt(req.getParameter("productDiscount"));

        Product product = new Product();
        product.setQuantity(quantity);
        product.setPrice(price);
        product.setDiscountPercentage(discountPercentage);
        product.setProductID(productId);


        ProductService productService = new ProductServiceImpl();
        productService.updateProduct(product);
        resp.sendRedirect("productPage?ProductID="+product.getProductID());
    }
}
