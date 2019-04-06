package com.iti.controller.servlet;

import com.iti.model.Dao.CartItemDao;
import com.iti.model.Dao.ProductDao;
import com.iti.model.Dao.implementation.CartItemDaoImplementation;
import com.iti.model.Dao.implementation.ProductDaoImplementation;
import com.iti.model.cfg.HibernateUtils;
import com.iti.model.entity.CartItem;
import com.iti.model.entity.ShoppingCart;
import com.iti.model.entity.Product;
import org.hibernate.Session;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashSet;
import java.util.Set;

public class ShoppingCartServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String productid = (String) request.getParameter("productid");

        //get product by id
        ProductDao productDao = new ProductDaoImplementation();
        Session session = HibernateUtils.getSession();
        Product product = productDao.retriveProductByID(Integer.parseInt(productid), session);
        if (request.getParameter("submitButton").equals("cart")) {
            int quantity = Integer.parseInt((String) request.getParameter("quantity"));
            int discount = product.getDiscountPercentage();
            double price = product.getPrice();
            double totalcost;
            if ((product.getDiscountPercentage()) != 0) {/////////////////////
                totalcost = (price - (price * (discount / 100.0))) * quantity;
            } else {
                totalcost = (product.getPrice()) * quantity;

            }

            //cartitem data
            CartItem cartItem = new CartItem();
            cartItem.setQuantity(quantity);
            cartItem.setTotalCost(totalcost);
            cartItem.setProducts(product);

            //get shoopingcart of user
            Set<CartItem> cartItems=new HashSet<>();
            Set<ShoppingCart> shoppingCarts=new HashSet<>();
            cartItems.add(cartItem);

            ShoppingCart shoppingCart=new ShoppingCart();
            shoppingCart  =(ShoppingCart) request.getSession().getAttribute("cart");
            shoppingCarts.add(shoppingCart);
            //add cartitem to shoppingcart
           shoppingCart.setShoppingCartItems(cartItems);/////////
                //add shoppingcart to cartitem
           cartItem.setShoppingCarts(shoppingCarts);
            //insert cartitem
            CartItemDao cartItemDao = new CartItemDaoImplementation();
            Session sessionItem = HibernateUtils.getSession();
            boolean flag = cartItemDao.persistCartItem(cartItem, sessionItem);

            response.sendRedirect("/toysOnlineStore_war_exploded/productPage?ProductID="+productid);
        }
    }
}
