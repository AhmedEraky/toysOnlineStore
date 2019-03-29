package com.iti.model.Dao;

import com.iti.model.entity.ShoppingCart;
import com.iti.model.entity.User;
import org.hibernate.Session;

import java.util.ArrayList;

public interface ShoppingCartDao {

    ArrayList<ShoppingCart> retriveShoppingCartByUser(User user, Session session);
    boolean persistShoppingChart(ShoppingCart cart, Session session);
    boolean updateShoppingCart(ShoppingCart oldShoppingCart, ShoppingCart newShoppingCart, Session session);
}
