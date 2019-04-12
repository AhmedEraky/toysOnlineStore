package com.iti.service;

import com.iti.model.entity.ShoppingCart;

public interface UpdateShoppingCartService {

    void updateCart(ShoppingCart cart,String email);
}
