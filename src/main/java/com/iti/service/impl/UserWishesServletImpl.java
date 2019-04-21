package com.iti.service.impl;

import com.iti.model.Dao.ProductDao;
import com.iti.model.Dao.UserDao;
import com.iti.model.Dao.implementation.ProductDaoImplementation;
import com.iti.model.Dao.implementation.UserDaoImplementation;
import com.iti.model.cfg.HibernateUtils;
import com.iti.model.cfg.transaction.TransactionManager;
import com.iti.model.entity.Product;
import com.iti.model.entity.User;
import com.iti.service.UserWishesServlet;
import org.hibernate.Session;

import java.util.Set;

public class UserWishesServletImpl implements UserWishesServlet {
    @Override
    public Boolean insertNewUserWishes(Integer productId, String userEmail) {
        TransactionManager transactionManager = new TransactionManager(HibernateUtils.getSessionFactory());

        try {
            return transactionManager.runInTransaction(session -> {
                ProductDao productDao = new ProductDaoImplementation();
                Product product = productDao.retriveProductByID(productId, session);
                UserDao userDao = new UserDaoImplementation();
                User user = userDao.retiveUserEmail(userEmail, session);
                Set<Product> userProducts = user.getUserWishes();
                
                userProducts.add(product);

                Boolean updated = false;
                User currentUser = userDao.retiveUserEmail(user.getEmail(),session);
                if(currentUser != null) {
                    currentUser.setBirthDate(user.getBirthDate());
                    currentUser.setJob(user.getJob());
                    currentUser.setAddress(user.getAddress());
                    currentUser.setCreditLimit(user.getCreditLimit());
                    /*==Aya==*/
                    if (user.getUserWishes().size() != 0) {
                        currentUser.setUserWishes(user.getUserWishes());
                    }

                    updated = userDao.updateUser(currentUser, session);


                }
                return updated;
            });
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }


    }
}
