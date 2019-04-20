package com.iti.service;

import com.iti.model.entity.ShoppingCart;
import com.iti.model.response.RemoveProductFromCartResponse;

public interface RemoveCartItemService {
    RemoveProductFromCartResponse removeCartItem(Integer productID,ShoppingCart cart);
}
