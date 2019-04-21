package com.iti.service.impl;

import com.iti.model.Dao.UserDao;
import com.iti.model.Dao.implementation.UserDaoImplementation;
import com.iti.model.cfg.HibernateUtils;
import com.iti.model.cfg.transaction.TransactionManager;
import com.iti.model.entity.Product;
import com.iti.service.WishListRetrievalService;


import java.util.List;


public class WishListRetrievalServiceImpl implements WishListRetrievalService
{
    List<Product> userWishes;
    @Override
    public List<Product> retrieveUserWishList(String userEmail)
    {
        TransactionManager transactionManager=new TransactionManager(HibernateUtils.getSessionFactory());
        UserDao userDao = new UserDaoImplementation();
        try {
            transactionManager.runInTransaction(session -> {
                userWishes = userDao.retrieveUserWishList(userEmail, session);
                return true;
            });
        } catch (Exception e) {
            e.printStackTrace();
        }
        return userWishes;
    }
}
