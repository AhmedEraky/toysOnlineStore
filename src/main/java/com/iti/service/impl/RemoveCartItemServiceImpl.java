package com.iti.service.impl;

import com.iti.model.entity.CartItem;
import com.iti.model.entity.ShoppingCart;
import com.iti.service.RemoveCartItemService;

import java.util.Set;

public class RemoveCartItemServiceImpl implements RemoveCartItemService {
    @Override
    public ShoppingCart removeCartItem(Integer productID, ShoppingCart cart) {
        Set<CartItem> items = cart.getShoppingCartItems();
        items.removeIf(item->item.getProducts().getProductID().equals(productID));
        cart.setShoppingCartItems(items);
        cart.setTotalCost(updateCartTotalCost(items));
        return cart;
    }

    private Double updateCartTotalCost(Set<CartItem> items){
        return items.stream().mapToDouble(item->item.getTotalCost()).sum();
    }

}
