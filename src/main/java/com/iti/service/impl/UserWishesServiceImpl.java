package com.iti.service.impl;

import com.iti.model.Dao.ProductDao;
import com.iti.model.Dao.UserDao;
import com.iti.model.Dao.implementation.ProductDaoImplementation;
import com.iti.model.Dao.implementation.UserDaoImplementation;
import com.iti.model.cfg.HibernateUtils;
import com.iti.model.cfg.transaction.TransactionManager;
import com.iti.model.entity.Product;
import com.iti.model.entity.User;
import com.iti.model.response.ConfirmationResponse;
import com.iti.model.response.Status;
import com.iti.service.UserWishesService;

import java.util.List;
import java.util.Set;

public class UserWishesServiceImpl implements UserWishesService {
    @Override
    public ConfirmationResponse insertNewUserWishes(Integer productId, String userEmail) {
        TransactionManager transactionManager=new TransactionManager(HibernateUtils.getSessionFactory());
        ConfirmationResponse response=new ConfirmationResponse();
        try {
            return transactionManager.runInTransaction(session -> {
                //GET PRODUCT
                ProductDao productDao=new ProductDaoImplementation();
                Product product=productDao.retriveProductByID(productId,session);
                Set<User> users=product.getUserWishes();
                //GET USER AND SET PRODUCTS
                UserDao userDao=new UserDaoImplementation();
                Boolean check=false;
                User user= userDao.retiveUserEmail(userEmail,session);
                Set<Product> products=user.getUserWishes();
                Boolean flag=true;
                for(Product prod:products) {
                  if(prod.getProductID()==product.getProductID())
                      flag=false;
                }
                //confirmation
                if(flag) {
                    products.add(product);

                   // users.add(user);
                    //update user
                    check=userDao.updateUser(user,session);
                }
                if(check){
                    response.setStatus(Status.success);
                    response.setMessage("Successfully added to wish list");

                }
                else{
                    response.setStatus(Status.success);
                    response.setMessage("It has been added to wish list");
                }
                return response;

            });
        } catch (Exception e) {
            e.printStackTrace();
            response.setStatus(Status.fail);
            response.setMessage("Error exists!");
            return response;
        }



    }
}
