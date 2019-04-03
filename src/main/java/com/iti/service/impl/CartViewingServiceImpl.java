package com.iti.service.impl;

import com.iti.model.Dao.CartItemDao;
import com.iti.model.Dao.implementation.CartItemDaoImplementation;
import com.iti.model.cfg.HibernateUtils;
import com.iti.model.entity.CartItem;
import com.iti.model.entity.ShoppingCart;
import com.iti.service.CartViewingService;
import org.hibernate.Session;

import java.util.List;

public class CartViewingServiceImpl implements CartViewingService
{
    @Override
    public List<CartItem> getPreviousSessionsCartItems(ShoppingCart cart)
    {
        Session session= HibernateUtils.getSession();
        CartItemDao cartItemDao = new CartItemDaoImplementation();
        List<CartItem> savedItems = cartItemDao.retriveCartItemByShoppingCart(cart, session);
        return savedItems;
    }
}
