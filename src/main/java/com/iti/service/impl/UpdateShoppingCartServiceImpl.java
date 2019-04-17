package com.iti.service.impl;

import com.iti.model.Dao.CartItemDao;
import com.iti.model.Dao.UserDao;
import com.iti.model.Dao.implementation.CartItemDaoImplementation;
import com.iti.model.Dao.implementation.UserDaoImplementation;
import com.iti.model.cfg.HibernateUtils;
import com.iti.model.cfg.transaction.TransactionManager;
import com.iti.model.entity.CartItem;
import com.iti.model.entity.ShoppingCart;
import com.iti.model.entity.User;
import com.iti.service.UpdateShoppingCartService;

import java.util.HashSet;
import java.util.Set;

public class UpdateShoppingCartServiceImpl implements UpdateShoppingCartService {
    @Override
    public boolean updateCart(ShoppingCart cart, String email) {

        TransactionManager transactionManager=new TransactionManager(HibernateUtils.getSessionFactory());
        try {
            return transactionManager.runInTransaction((session) -> {
                UserDao userDao=new UserDaoImplementation();
                User user=userDao.retiveUserEmailNew(email,session);
                Set<CartItem> items=null;

                if(user.getShoppingCart().getShoppingCartItems()!=null){
                    items=user.getShoppingCart().getShoppingCartItems();
                }

                user.getShoppingCart().setTotalCost(cart.getTotalCost());
                user.getShoppingCart().setShoppingCartItems(cart.getShoppingCartItems());
                userDao.updateUser(user,session);

                if(items!=null&&cart.getShoppingCartItems().size()==0) {
                    CartItemDao cartItemDao=new CartItemDaoImplementation();
                    for (CartItem cartItem : items) {
                        cartItemDao.deleteCartItem(cartItem, session);
                    }
                }
                return true;
            });
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }
}
