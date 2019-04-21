package com.iti.service.impl;

import com.iti.model.entity.CartItem;
import com.iti.model.entity.ShoppingCart;
import com.iti.service.DecreaseCartItemQuantityService;
import com.iti.model.response.DecreaseProductQuantityInCartResponse;

import java.util.Set;

public class DecreaseCartItemQuantityServiceImpl implements DecreaseCartItemQuantityService {
    @Override
    public DecreaseProductQuantityInCartResponse decreaseQuantity(Integer productID, Integer amount, ShoppingCart cart) {

        DecreaseProductQuantityInCartResponse response = new DecreaseProductQuantityInCartResponse();
        try {
            Set<CartItem> items = cart.getShoppingCartItems();
            items.forEach(item ->{
                if(item.getProducts().getProductID().equals(productID)){
                    item.setQuantity(item.getQuantity()-1);
                    item.setTotalCost(item.getProducts().getPrice()*item.getQuantity());
                }
            });
            cart.setShoppingCartItems(items);
            cart.setTotalCost(updateCartTotalCost(items));
            response.setCart(cart);
            response.setMessage("Successfully decreased product quantity from Shopping cart");
        }
        catch (Exception exception){
            response.setCart(cart);
            response.setMessage("failed");
        }
        return response ;

    }

    private Double updateCartTotalCost(Set<CartItem> items){
        return items.stream().mapToDouble(item->item.getTotalCost()).sum();
    }

}
