package com.iti.service;

import com.iti.model.entity.ShoppingCart;

public interface UpdateShoppingCartService {

    boolean updateCart(ShoppingCart cart, String email);
}
