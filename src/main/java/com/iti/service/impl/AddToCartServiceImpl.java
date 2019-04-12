package com.iti.service.impl;

import com.iti.model.Dao.ProductDao;
import com.iti.model.Dao.implementation.ProductDaoImplementation;
import com.iti.model.cfg.HibernateUtils;
import com.iti.model.cfg.transaction.TransactionManager;
import com.iti.model.entity.CartItem;
import com.iti.model.entity.Product;
import com.iti.model.entity.ShoppingCart;
import com.iti.model.response.AddingResponse;
import com.iti.service.AddToCartService;
import org.hibernate.Session;

import java.util.Set;

public class AddToCartServiceImpl implements AddToCartService {
    @Override
    public AddingResponse addToCart(Integer productID, Integer quantity, ShoppingCart cart) {
        TransactionManager transactionManager=new TransactionManager(HibernateUtils.getSessionFactory());

        Boolean result=false;
        AddingResponse addingResponse=new AddingResponse();
        try {
            result=transactionManager.runInTransaction( session -> {

                ProductDao productDao = new ProductDaoImplementation();
                Product product = productDao.retriveProductnew(productID, session);

                CartItem cartItem=generateNewCartItem(product,quantity);
                Set<CartItem> cartItems=cart.getShoppingCartItems();
                Set<CartItem> updatedCartItems=addNewCartItem(cartItem,cartItems);

                cart.setShoppingCartItems(updatedCartItems);
                updateCartTotalCost(product,quantity,cart);
                return true;
            });
        } catch (Exception e) {
            addingResponse.setMessage("Sorry Fail to added to Shopping cart");
            addingResponse.setStatus("Fail");
            e.printStackTrace();
        }
        if (result==true){
            addingResponse.setMessage("Successfully added to Shopping cart");
            addingResponse.setStatus("Success");
        }
        return addingResponse;
    }

    private void updateCartTotalCost(Product product, Integer quantity,ShoppingCart cart) {
        if(cart.getTotalCost()==null)
            cart.setTotalCost(0.0);
        cart.setTotalCost(cart.getTotalCost()+(product.getPrice()*quantity));
    }


    CartItem generateNewCartItem(Product product, Integer quantity){

        int discount = product.getDiscountPercentage();
        double price = product.getPrice();
        double totalcost;
        if ((product.getDiscountPercentage()) != 0) {/////////////////////
            totalcost = (price - (price * (discount / 100.0))) * quantity;
        } else {
            totalcost = (product.getPrice()) * quantity;

        }

        //cartitem data
        CartItem cartItem = new CartItem();
        cartItem.setQuantity(quantity);
        cartItem.setTotalCost(totalcost);
        cartItem.setProducts(product);
        return cartItem;
    }

    Set<CartItem>  addNewCartItem(CartItem cartItem,Set<CartItem> cartItems){
        boolean check=false;
        for(CartItem item:cartItems){
            if((item.getProducts().getProductID()).equals(cartItem.getProducts().getProductID())){
                check=true;
                item.setQuantity(cartItem.getQuantity());
            }
        }

        if(!check){
            cartItems.add(cartItem);
        }


        return cartItems;
    }
}
