package com.iti.service.impl;

import com.iti.model.entity.CartItem;
import com.iti.model.entity.ShoppingCart;
import com.iti.service.DecreaseCartItemQuantityService;

import java.util.Set;

public class DecreaseCartItemQuantityServiceImpl implements DecreaseCartItemQuantityService {
    @Override
    public ShoppingCart decreaseQuantity(Integer productID, Integer amount, ShoppingCart cart) {
        Set<CartItem> items = cart.getShoppingCartItems();
        items.forEach(item ->{
            if(item.getProducts().getProductID().equals(productID)){
                item.setQuantity(amount);
                item.setTotalCost(item.getProducts().getPrice()*amount);
            }
        });
        cart.setShoppingCartItems(items);
        cart.setTotalCost(updateCartTotalCost(items));
        return cart;
    }

    private Double updateCartTotalCost(Set<CartItem> items){
        return items.stream().mapToDouble(item->item.getTotalCost()).sum();
    }

}
