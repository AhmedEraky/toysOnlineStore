package com.iti.model.util;

import com.iti.model.entity.CartItem;
import com.iti.model.entity.Product;
import com.iti.model.entity.ShoppingCart;
import com.iti.service.ProductService;
import com.iti.service.impl.ProductServiceImpl;

import javax.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

public class CartItemUtils
{
    public void checkItemsAvailability(HttpSession session)
    {
        ShoppingCart sessionCart = (ShoppingCart) session.getAttribute("cart");
        Set<CartItem> sessionItems = sessionCart.getShoppingCartItems();
        List<String> removedProducts = new ArrayList<>();
        session.setAttribute("removedItems", removedProducts);
        for(CartItem cartItem:sessionItems)
        {
            Product product = cartItem.getProducts();
            ProductService service = new ProductServiceImpl();
            Product retrievedProduct = service.getProductByID(product.getProductID());
            int itemQuantity = cartItem.getQuantity();
            int productQuantity = retrievedProduct.getQuantity();
            if(productQuantity == 0)
            {
                sessionItems.remove(cartItem);
                removedProducts.add(retrievedProduct.getName());
            }
            else if(productQuantity < itemQuantity)
            {
                cartItem.setQuantity(productQuantity);
            }
        }
    }
}
