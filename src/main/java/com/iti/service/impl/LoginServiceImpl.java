package com.iti.service.impl;

import com.iti.model.Dao.UserDao;
import com.iti.model.Dao.implementation.CartItemDaoImplementation;
import com.iti.model.Dao.implementation.UserDaoImplementation;
import com.iti.model.cfg.HibernateUtils;
import com.iti.model.entity.CartItem;
import com.iti.model.entity.ShoppingCart;
import com.iti.model.response.Status;
import com.iti.model.entity.User;
import com.iti.model.response.AuthenticationResponse;
import com.iti.service.LoginService;
import org.hibernate.Session;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class LoginServiceImpl implements LoginService {
    @Override
    public AuthenticationResponse login(User user) {
        AuthenticationResponse response=new AuthenticationResponse();
        UserDao userDao=new UserDaoImplementation();
        Session session= HibernateUtils.getSession();
        if(userDao.isUser(user,session)){
            response.setStatus(Status.success);
            response.setEmail(user.getEmail());
            response.setMessage("Success Login");
        }
        else {
            response.setStatus(Status.fail);
            response.setEmail("");
            response.setMessage("Wrong Email Or Password");
        }
        return response;
    }

    @Override
    public ShoppingCart getLoggedInUserCart(User user)
    {
        UserDao userDao = new UserDaoImplementation();
        Session session = HibernateUtils.getSession();
        User LoggedInUser = userDao.retiveUserEmail(user.getEmail(), session);
        ShoppingCart LoggedInUserCart = LoggedInUser.getShoppingCart();
        return LoggedInUserCart;
    }

    @Override
    public Set<CartItem> getLoggedInUserCartItems(ShoppingCart userCart)
    {
        Set<CartItem> savedItems = null;
        Session session = HibernateUtils.getSession();
        CartItemDaoImplementation cartItemDaoImplementation = new CartItemDaoImplementation();
        List<CartItem> savedCartItems = cartItemDaoImplementation.retriveCartItemByShoppingCart(userCart, session);
        if(savedCartItems != null)
        {
            savedItems = new HashSet<CartItem>(savedCartItems);
        }
        return savedItems;
    }
}
