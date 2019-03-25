package com.iti.Dao;

import com.iti.model.entity.CartItem;
import com.iti.model.entity.ShoppingCart;
import com.iti.model.entity.User;

import java.lang.reflect.Array;
import java.util.ArrayList;

public interface CartItemDao {
    public CartItem retriveCartItemByID(CartItem item);
    public ArrayList<CartItem> retriveCartItemByShoppingCart(ShoppingCart shoppingCart);

}
