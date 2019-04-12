package com.iti.controller.filter.handler;

import com.iti.model.Dao.CategoryDao;
import com.iti.model.Dao.implementation.CategoryDaoImplementation;
import com.iti.model.entity.Category;
import com.iti.model.entity.Product;
import com.iti.model.response.ConfirmationResponse;
import com.iti.model.response.Status;
import com.iti.service.ProductService;
import com.iti.service.impl.ProductServiceImpl;
import org.apache.commons.beanutils.BeanUtils;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;

public class ProductAdditionHandler implements Handler {
    HttpSession session;
    @Override
    public void handle(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain, Boolean login) throws IOException, ServletException {
        session = request.getSession();
        //get data from client side
        Product product = createProduct(request);
        product.setImagePath(request.getParameter("image"));
        String categoryName = request.getParameter("categoryName");
        String storeName = request.getParameter("storeName");
        ProductService productService = new ProductServiceImpl();
        ConfirmationResponse confirmationResponse = new ConfirmationResponse();
        confirmationResponse = productService.insert(product, categoryName, storeName);
        if (confirmationResponse.getStatus().equals(Status.success)) {
            session.setAttribute("successMessage", confirmationResponse.getMessage());
            response.sendRedirect("addProduct?message=" + confirmationResponse.getMessage());
        }
        else{
            session.setAttribute("errorMessage", confirmationResponse.getMessage());
            response.sendRedirect("addProduct?message=" + confirmationResponse.getMessage());
        }
    }
    //get data and create product request

    protected Product createProduct(HttpServletRequest request) {
        Product product = new Product();
        try {
            BeanUtils.populate(product, request.getParameterMap());
        } catch (InvocationTargetException e) { e.printStackTrace(); } catch (IllegalAccessException e) {
            e.printStackTrace();
        }
        return product;
    }
}
