package com.iti.Dao.implementation;

import com.iti.Dao.CartItemDao;
import com.iti.model.entity.CartItem;
import com.iti.model.entity.ShoppingCart;
import org.hibernate.Session;

import java.util.ArrayList;

public class CartItemDaoImplementation implements CartItemDao {
    @Override
    public CartItem retriveCartItemByID(Integer id, Session session) {
        return null;
    }

    @Override
    public CartItem retriveCartItemByExample(CartItem item, Session session) {
        return null;
    }

    @Override
    public ArrayList<CartItem> retriveCartItemsByExample(CartItem item, Session session) {
        return null;
    }

    @Override
    public ArrayList<CartItem> retriveCartItemByShoppingCart(ShoppingCart cart, Session session) {
        return null;
    }

    @Override
    public boolean persistCartItem(CartItem item, Session session) {
        return false;
    }
}
