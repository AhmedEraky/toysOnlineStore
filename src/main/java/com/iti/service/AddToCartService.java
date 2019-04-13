package com.iti.service;

import com.iti.model.entity.ShoppingCart;
import com.iti.model.response.AddingResponse;

public interface AddToCartService {

    AddingResponse addToCart(Integer productID,Integer quantity, ShoppingCart cart);
}
