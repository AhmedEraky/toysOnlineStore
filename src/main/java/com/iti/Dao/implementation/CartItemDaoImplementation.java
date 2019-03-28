package com.iti.Dao.implementation;

import com.iti.Dao.CartItemDao;
import com.iti.model.entity.CartItem;
import com.iti.model.entity.ShoppingCart;
import org.hibernate.Session;

import java.util.ArrayList;
import org.hibernate.Criteria;
import org.hibernate.criterion.Example;
import org.hibernate.criterion.Restrictions;

public class CartItemDaoImplementation implements CartItemDao {
    @Override
    public CartItem retriveCartItemByID(Integer id, Session session) {

        Criteria cartItemCriteria = session.createCriteria(CartItem.class,"cartItem");
        cartItemCriteria.add(Restrictions.eq("cartItem.itemID", id));
        CartItem cartItem = (CartItem)cartItemCriteria.uniqueResult();
        return cartItem;
        
    }

    @Override
    public CartItem retriveCartItemByExample(CartItem item, Session session) {
        
        Example example=Example.create(item);
        Criteria cartItemCriteria = session.createCriteria(CartItem.class)
                .add(example);
        return (CartItem) cartItemCriteria.uniqueResult();
    
    }

    @Override
    public ArrayList<CartItem> retriveCartItemsByExample(CartItem item, Session session) {
        
        Example example=Example.create(item);
        Criteria cartItemCriteria = session.createCriteria(CartItem.class)
                .add(example);
        return (ArrayList<CartItem>)cartItemCriteria.list();
    
    }

    @Override
    public ArrayList<CartItem> retriveCartItemByShoppingCart(ShoppingCart cart, Session session) {
        
        Criteria cartItemsCriteria = session.createCriteria(CartItem.class,"c")
                .createAlias("c.shoppingCarts", "shoppingCarts");
        cartItemsCriteria.add(Restrictions.eq("shoppingCarts.cartId", cart.getCartId()));
        return (ArrayList<CartItem>)cartItemsCriteria.list();
    
    }

    @Override
    public boolean persistCartItem(CartItem item, Session session) {
        session.beginTransaction();
        try {
            session.save(item);
            session.getTransaction().commit();
            session.close();
            return true;
        } catch (Exception e) {
            session.close();
            return false;
        }
        
    }
}
