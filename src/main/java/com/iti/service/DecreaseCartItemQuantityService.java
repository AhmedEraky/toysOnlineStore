package com.iti.service;

import com.iti.model.entity.ShoppingCart;

public interface DecreaseCartItemQuantityService {
    ShoppingCart decreaseQuantity(Integer productID, Integer amount, ShoppingCart cart);
}
