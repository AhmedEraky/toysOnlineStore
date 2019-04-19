package com.iti.service.impl;

import com.iti.model.Dao.AdminDao;
import com.iti.model.Dao.UserDao;
import com.iti.model.Dao.implementation.AdminDaoImplementation;
import com.iti.model.Dao.implementation.CartItemDaoImplementation;
import com.iti.model.Dao.implementation.UserDaoImplementation;
import com.iti.model.cfg.HibernateUtils;
import com.iti.model.cfg.transaction.TransactionManager;
import com.iti.model.entity.Admin;
import com.iti.model.entity.CartItem;
import com.iti.model.entity.ShoppingCart;
import com.iti.model.response.Status;
import com.iti.model.entity.User;
import com.iti.model.response.AuthenticationResponse;
import com.iti.model.response.Usertype;
import com.iti.service.LoginService;
import org.hibernate.Session;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class LoginServiceImpl implements LoginService {
    @Override
    public AuthenticationResponse login(User user) {
        TransactionManager transactionManager=new TransactionManager(HibernateUtils.getSessionFactory());
        AuthenticationResponse response=new AuthenticationResponse();
        try {
            return transactionManager.runInTransaction(session -> {
                UserDao userDao=new UserDaoImplementation();
                AdminDao adminDao=new AdminDaoImplementation();
                Admin admin=new Admin();
                admin.setEmail(user.getEmail());
                admin.setPassword(user.getPassword());
                if(adminDao.isAdmin(admin,session)){
                    response.setStatus(Status.success);
                    response.setEmail(user.getEmail());
                    response.setMessage("Success Login");
                    response.setUsertype(Usertype.admin);
                }else if(userDao.isUser(user,session)){
                    response.setStatus(Status.success);
                    response.setEmail(user.getEmail());
                    response.setMessage("Success Login");
                    response.setUsertype(Usertype.customer);
                }
                else {
                    response.setStatus(Status.fail);
                    response.setEmail("");
                    response.setMessage("Wrong Email Or Password");
                }
                return response;

            });
        } catch (Exception e) {
            e.printStackTrace();
            response.setStatus(Status.fail);
            response.setEmail("");
            response.setMessage("Wrong Email Or Password");
            return response;
        }
    }

    @Override
    public ShoppingCart getLoggedInUserCart(User user)
    {
        TransactionManager transactionManager=new TransactionManager(HibernateUtils.getSessionFactory());
        try {
            return transactionManager.runInTransaction(session -> {
                UserDao userDao = new UserDaoImplementation();
                User LoggedInUser = userDao.retiveUserEmail(user.getEmail(), session);
                ShoppingCart LoggedInUserCart = LoggedInUser.getShoppingCart();
                return LoggedInUserCart;

            });
        } catch (Exception e) {
            e.printStackTrace();
            return new ShoppingCart();
        }
    }

    @Override
    public Set<CartItem> getLoggedInUserCartItems(ShoppingCart userCart)
    {
        TransactionManager transactionManager=new TransactionManager(HibernateUtils.getSessionFactory());
        try {
            return transactionManager.runInTransaction(session -> {
                Set<CartItem> savedItems = null;
                CartItemDaoImplementation cartItemDaoImplementation = new CartItemDaoImplementation();
                List<CartItem> savedCartItems = cartItemDaoImplementation.retriveCartItemByShoppingCart(userCart, session);
                if(savedCartItems != null)
                {
                    savedItems = new HashSet<CartItem>(savedCartItems);
                }
                return savedItems;

            });
        } catch (Exception e) {
            e.printStackTrace();
            return new HashSet<>();
        }
    }
}
