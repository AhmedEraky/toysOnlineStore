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
                Set<User> users=product.getUserWishes();

                UserDao userDao = new UserDaoImplementation();
                User user = userDao.retiveUserEmail(userEmail, session);
                users.add(user);
                Set<Product> userProducts = user.getUserWishes();
                //check is it exists
                Boolean check=true;
                for(Product product1:userProducts) {
                    if(product1.getProductID()==productId)
                        check=false;

                }
                if(check){
                    userProducts.add(product);
                    userDao.updateUser(user,session);
                }

                return check;
            });
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }


    }
}
