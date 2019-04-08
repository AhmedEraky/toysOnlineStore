package com.iti.controller.servlet;

import com.iti.model.Dao.CartItemDao;
import com.iti.model.Dao.ProductDao;
import com.iti.model.Dao.UserDao;
import com.iti.model.Dao.implementation.CartItemDaoImplementation;
import com.iti.model.Dao.implementation.ProductDaoImplementation;
import com.iti.model.Dao.implementation.UserDaoImplementation;
import com.iti.model.cfg.HibernateUtils;
import com.iti.model.entity.CartItem;
import com.iti.model.entity.Product;
import com.iti.model.entity.ShoppingCart;
import com.iti.model.entity.User;
import org.hibernate.Session;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;

public class ProductHandling extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String productid=(String)request.getParameter("productid");
        String userEmail=(String)request.getSession().getAttribute("mail");
       //get product by id
        ProductDao productDao=new ProductDaoImplementation();
        Session session= HibernateUtils.getSession();
        Product product=productDao.retriveProductByID(Integer.parseInt(productid),session);
        //user
        UserDao userDao=new UserDaoImplementation();

        Session sessionUser= HibernateUtils.getSession();


         if(request.getParameter("submitButton").equals("wishes")){


            //get product from id

            //add wishes for user

            User user=userDao.retiveUserEmail(userEmail,sessionUser);
            Set<Product> userProducts=new HashSet();
            userProducts.add(product);
            user.setUserWishes(userProducts);
            //update user
            Session sessionWishes= HibernateUtils.getSession();
            boolean flag=userDao.updateUser(user,sessionWishes);
        }
        response.sendRedirect("productPage?ProductID="+productid);
    }
}
