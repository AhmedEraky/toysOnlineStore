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
        Product product=getProductById(Integer.parseInt(productid));

        if (request.getParameter("submitButton").equals("cart")) {
            int quantity = Integer.parseInt((String) request.getParameter("quantity"));
            System.out.println("===================="+quantity);
            CartItem cartItem=generateNewCartItem(product,quantity);

            //get shoopingcart of user

            ShoppingCart shoppingCart  =(ShoppingCart) request.getSession().getAttribute("cart");
            Set<CartItem> cartItems=shoppingCart.getShoppingCartItems();
            cartItems.add(cartItem);
            shoppingCart.setShoppingCartItems(cartItems);


                //add shoppingcart to cartitem
            Set<ShoppingCart> shoppingCarts=cartItem.getShoppingCarts();
            shoppingCarts.add(shoppingCart);
           cartItem.setShoppingCarts(shoppingCarts);



            //insert cartitem
            CartItemDao cartItemDao = new CartItemDaoImplementation();
            Session sessionItem = HibernateUtils.getSession();
            boolean flag = cartItemDao.persistCartItem(cartItem, sessionItem);

            response.sendRedirect("productPage?ProductID="+productid);
        }
    }

    Product getProductById(int productid){
        ProductDao productDao = new ProductDaoImplementation();
        Session session = HibernateUtils.getSession();
        Product product = productDao.retriveProductByID(productid, session);
        return product;
    }
    CartItem generateNewCartItem(Product product,Integer quantity){

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
        return cartItem;
    }
}
