package com.iti.service.impl;

import com.iti.model.entity.CartItem;
import com.iti.model.entity.ShoppingCart;
import com.iti.service.RemoveCartItemService;
import com.iti.model.response.RemoveProductFromCartResponse;

import java.util.Set;

public class RemoveCartItemServiceImpl implements RemoveCartItemService {
    @Override
    public RemoveProductFromCartResponse removeCartItem(Integer productID, ShoppingCart cart) {

        RemoveProductFromCartResponse response = new RemoveProductFromCartResponse();
        try {
            Set<CartItem> items = cart.getShoppingCartItems();
            items.removeIf(item->item.getProducts().getProductID().equals(productID));
            cart.setShoppingCartItems(items);
            cart.setTotalCost(updateCartTotalCost(items));
            response.setCart(cart);
            response.setMessage("Successfully removed product from Shopping cart");
        }
        catch (Exception exception){
            response.setCart(cart);
            response.setMessage("failed");
        }
        return response;

    }

    private Double updateCartTotalCost(Set<CartItem> items){
        return items.stream().mapToDouble(item->item.getTotalCost()).sum();
    }

}
