package com.iti.service.impl;

import com.iti.model.Dao.ShoppingCartDao;
import com.iti.model.Dao.UserDao;
import com.iti.model.Dao.implementation.ShoppingCartDaoImplementation;
import com.iti.model.Dao.implementation.UserDaoImplementation;
import com.iti.model.cfg.HibernateUtils;
import com.iti.model.cfg.transaction.TransactionManager;
import com.iti.model.entity.ShoppingCart;
import com.iti.model.entity.User;
import com.iti.service.UpdateShoppingCartService;

public class UpdateShoppingCartServiceImpl implements UpdateShoppingCartService {
    @Override
    public void updateCart(ShoppingCart cart,String email) {
        TransactionManager transactionManager=new TransactionManager(HibernateUtils.getSessionFactory());
        Boolean updateResult=null;
        try {
            updateResult=transactionManager.runInTransaction((session) -> {
                UserDao userDao=new UserDaoImplementation();
                User user=userDao.retiveUserEmailNew(email,session);
                user.setShoppingCart(cart);
                userDao.updateUser(user,session);
                return true;
            });
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
