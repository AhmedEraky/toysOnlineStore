package com.iti.model.response;

import com.iti.model.entity.ShoppingCart;

public class DecreaseProductQuantityInCartResponse {

    private ShoppingCart cart;
    private String message;

    public ShoppingCart getCart() {
        return cart;
    }

    public void setCart(ShoppingCart cart) {
        this.cart = cart;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
