package com.iti.Dao.implementation;

import com.iti.Dao.ShoppingCartDao;
import com.iti.model.entity.ShoppingCart;
import com.iti.model.entity.User;
import org.hibernate.Session;

import java.util.ArrayList;

public class ShoppingCartDaoImplementation implements ShoppingCartDao {
    @Override
    public ArrayList<ShoppingCart> retriveShoppingCartByUser(User user, Session session) {
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
