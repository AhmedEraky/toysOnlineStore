package com.iti.service;

import com.iti.model.entity.CartItem;
import com.iti.model.entity.ShoppingCart;
import com.iti.model.entity.User;
import com.iti.model.response.AuthenticationResponse;
import com.iti.model.response.ValidationResponse;

import java.util.Set;

public interface LoginService {
    AuthenticationResponse login(User user);
    ShoppingCart getLoggedInUserCart(User user);
    Set<CartItem> getLoggedInUserCartItems(ShoppingCart userCart);
}
