package com.iti.model.Dao.implementation;

import com.iti.model.Dao.ShoppingCartDao;
import com.iti.model.entity.ShoppingCart;
import com.iti.model.entity.User;
import org.hibernate.Session;

import java.util.ArrayList;

public class ShoppingCartDaoImplementation implements ShoppingCartDao {
    @Override
    public ShoppingCart retriveShoppingCartByUser(User user, Session session) {
        return null;
    }

    @Override
    public boolean persistShoppingChart(ShoppingCart cart, Session session) {
        return false;
    }

    @Override
    public boolean updateShoppingCart(ShoppingCart oldShoppingCart, ShoppingCart newShoppingCart, Session session) {
        return false;
    }

}
