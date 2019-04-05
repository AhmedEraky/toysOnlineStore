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

        if (request.getParameter("submitButton").equals("cart")){
            int quantity=Integer.parseInt((String)request.getParameter("quantity"));
            int discount=product.getDiscountPercentage();
            double price=product.getPrice();
            double totalcost;
            if((product.getDiscountPercentage())!=0) {/////////////////////
                 totalcost = (price-(price*(discount/100.0)))*quantity;
            }
            else{
                 totalcost = (product.getPrice())* quantity;

            }

                //cartitem data
            CartItem cartItem=new CartItem();
            cartItem.setQuantity(quantity);
            cartItem.setTotalCost(totalcost);
            cartItem.setProducts(product);
            //get shoopingcart of user



           //add cartitem to shoppingcart

            //insert cartitem
            CartItemDao cartItemDao=new CartItemDaoImplementation();
            Session sessionItem=HibernateUtils.getSession();
            boolean flag=cartItemDao.persistCartItem(cartItem,sessionItem);
            User user= userDao.retiveUserEmail(userEmail,sessionUser);
/*
            Set< ShoppingCart> shoppingCarts=new HashSet<>();
            shoppingCarts.add(user.getShoppingCart());
            cartItem.setShoppingCarts(shoppingCarts);
            */

            /*
            Session sessionCartItem=HibernateUtils.getSession();
            ArrayList<CartItem> cartItem1=cartItemDao.retriveCartItemsByExample(cartItem,sessionCartItem);

            Set<CartItem> shoppingCartItems=new HashSet<>();
            cartItem1.get(0).setShoppingCarts(shoppingCarts);
            shoppingCartItems.add(cartItem1.get(0));
            user.getShoppingCart().setShoppingCartItems(shoppingCartItems);
            Session sessionCart= HibernateUtils.getSession();
            boolean userFlag=userDao.updateUser(user,sessionCart);
*/

        }
        else if(request.getParameter("submitButton").equals("wishes")){


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
        response.sendRedirect("/toysOnlineStore_war_exploded/productPage?ProductID="+productid);
    }
}
