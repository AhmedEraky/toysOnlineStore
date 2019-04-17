package com.iti.service.impl;

import com.iti.model.Dao.ProductDao;
import com.iti.model.Dao.UserDao;
import com.iti.model.Dao.implementation.ProductDaoImplementation;
import com.iti.model.Dao.implementation.UserDaoImplementation;
import com.iti.model.cfg.HibernateUtils;
import com.iti.model.cfg.transaction.TransactionManager;
import com.iti.model.entity.CartItem;
import com.iti.model.entity.Product;
import com.iti.model.entity.ShoppingCart;
import com.iti.model.entity.User;
import com.iti.model.response.Status;
import com.iti.model.response.ValidationResponse;
import com.iti.service.BuyingProcessHandlingService;

import java.util.Set;

public class BuyingProcessHandlingServiceImpl implements BuyingProcessHandlingService
{
    @Override
    public ValidationResponse hasEnoughCredit(String userEmail, double totalCost)
    {
        TransactionManager transactionManager=new TransactionManager(HibernateUtils.getSessionFactory());
        ValidationResponse response=new ValidationResponse();
        try {
            return transactionManager.runInTransaction(session -> {
                UserDao userDao=new UserDaoImplementation();
                User user = userDao.retiveUserEmailNew(userEmail, session);
                if(user.getCreditLimit() >= totalCost){
                    user.setCreditLimit(user.getCreditLimit() - totalCost);
                    userDao.updateUser(user, session);
                    response.setStatus(Status.success);
                    response.setMessage("Successful Buying Operation");
                }
                else {
                    response.setStatus(Status.fail);
                    response.setMessage("Buying Operation failed");
                }
                return response;

            });
        } catch (Exception e) {
            e.printStackTrace();
            response.setStatus(Status.fail);
            response.setMessage("Buying Operation failed");
            return response;
        }

    }

    @Override
    public void updateProductsData(ShoppingCart cart)
    {
        TransactionManager transactionManager=new TransactionManager(HibernateUtils.getSessionFactory());
        ProductDao productDao = new ProductDaoImplementation();
        Set<CartItem> cartItems = cart.getShoppingCartItems();
        for(CartItem cartItem:cartItems)
        {
            Product soldProduct = cartItem.getProducts();
            soldProduct.setQuantity(cartItem.getQuantity());
            soldProduct.setPurchaseCount(cartItem.getQuantity());
            try {
                    transactionManager.runInTransaction(session -> {
                        productDao.updateProductQuantity(soldProduct, session);
                        productDao.updateProductPurchaseCount(soldProduct, session);
                        return true;
                });
            } catch (Exception e) {
                e.printStackTrace();
            }

        }
    }
}
