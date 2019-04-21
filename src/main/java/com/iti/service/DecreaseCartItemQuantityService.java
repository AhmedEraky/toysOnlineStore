package com.iti.service;

import com.iti.model.entity.ShoppingCart;
import com.iti.model.response.DecreaseProductQuantityInCartResponse;

public interface DecreaseCartItemQuantityService {
    DecreaseProductQuantityInCartResponse decreaseQuantity(Integer productID, Integer amount, ShoppingCart cart);
}
