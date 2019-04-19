package com.iti.service;

import com.iti.model.entity.ShoppingCart;

public interface RemoveCartItemService {
    ShoppingCart removeCartItem(Integer productID,ShoppingCart cart);
}
