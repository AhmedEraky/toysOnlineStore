package com.iti.model.Dao;

import com.iti.model.entity.CartItem;
import com.iti.model.entity.ShoppingCart;
import org.hibernate.Session;

import java.util.ArrayList;

public interface CartItemDao {
    CartItem retriveCartItemByID(Integer id, Session session);
    CartItem retriveCartItemByExample(CartItem item, Session session);
    ArrayList<CartItem> retriveCartItemsByExample(CartItem item, Session session);
    ArrayList<CartItem> retriveCartItemByShoppingCart(ShoppingCart cart, Session session);

    boolean persistCartItem(CartItem item, Session session);
    //Aya taha
    public boolean updateCartItem(CartItem item, Session session);
}
