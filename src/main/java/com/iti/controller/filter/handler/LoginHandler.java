package com.iti.controller.filter.handler;

import com.iti.model.entity.CartItem;
import com.iti.model.entity.Product;
import com.iti.model.entity.ShoppingCart;
import com.iti.model.entity.User;
import com.iti.model.response.AuthenticationResponse;
import com.iti.model.response.Status;
import com.iti.model.response.Usertype;
import com.iti.model.util.CartItemUtils;
import com.iti.service.LoginService;
import com.iti.service.ProductService;
import com.iti.service.impl.LoginServiceImpl;
import com.iti.service.impl.ProductServiceImpl;
import org.hibernate.usertype.UserType;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.HashSet;
import java.util.Set;

public class LoginHandler  extends HomeHandler{

    @Override
    public void handle(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain, Boolean login) throws IOException, ServletException {
        session=request.getSession();
        user = createUser(request);
        LoginService service=new LoginServiceImpl();
        AuthenticationResponse loginResponse=service.login(user);

        if (loginResponse.getStatus().equals(Status.success)) {
            session.setAttribute("login",true);
            session.setAttribute("mail",user.getEmail());
            session.setAttribute("userType",loginResponse.getUsertype());
            //Get saved cart and merge its items with session cart items.
            if(loginResponse.getUsertype().equals(Usertype.customer)) {
                mergeCarts(user, session);
                CartItemUtils cartItemUtils = new CartItemUtils();
                cartItemUtils.checkItemsAvailability(session);
                response.sendRedirect("home?justLoggedIn=true");
            }else {
                response.sendRedirect("adminHome");
            }
        } else {
            response.sendRedirect("login?message="+loginResponse.getMessage());
        }
    }

    private void mergeCarts(User user, HttpSession session)
    {
        LoginService service= new LoginServiceImpl();
        //Get Shopping Carts
        ShoppingCart savedCart = service.getLoggedInUserCart(user);
        ShoppingCart sessionCart = (ShoppingCart) session.getAttribute("cart");
        //Get CartItem sets
        Set<CartItem> savedItems = service.getLoggedInUserCartItems(savedCart);
        Set<CartItem> sessionItems = sessionCart.getShoppingCartItems();
        if(savedItems != null && sessionItems != null && savedItems.size() != 0)
        {
            //Create merged set
            Set<CartItem> AllItems = new HashSet<>();
            //The Merging Process
            for (CartItem savedItem : savedItems) {
                boolean added = false;
                for (CartItem sessionItem : sessionItems) {
                    if (sessionItem.getProducts().getProductID().intValue() == savedItem.getProducts().getProductID().intValue()) {
                        if((sessionItem.getQuantity() + savedItem.getQuantity()) <= sessionItem.getProducts().getQuantity())
                        {
                            sessionItem.setQuantity(sessionItem.getQuantity() + savedItem.getQuantity());
                        }
                        else
                        {
                            sessionItem.setQuantity(sessionItem.getProducts().getQuantity());
                        }
                        added = true;
                    }
                    AllItems.add(sessionItem);
                }
                if (added == false) {
                    AllItems.add(savedItem);
                }
            }
            //Add the merged set to the session cart
            sessionCart.setShoppingCartItems(AllItems);
            //Update the Session Cart
            session.setAttribute("cart", sessionCart);
        }
    }


}
