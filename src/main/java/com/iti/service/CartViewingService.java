package com.iti.service;

import com.iti.model.entity.CartItem;
import com.iti.model.entity.ShoppingCart;

import java.util.List;

public interface CartViewingService
{
    public List<CartItem> getPreviousSessionsCartItems(ShoppingCart cart);

}
