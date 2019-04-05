/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.iti.model.util;

import com.iti.model.entity.Product;


public class ProductUtil {
    public void compareProduct(Product oldProduct,Product newProduct){
        if(newProduct.getProductID()!=null){
            oldProduct.setProductID(newProduct.getProductID());
        }
         if(newProduct.getDescription()!=null){
            oldProduct.setDescription(newProduct.getDescription());
        }
         /*
         if(newProduct.getDiscountPercentage()!=null){
            oldProduct.setDiscountPercentage(newProduct.getDiscountPercentage());
        }
        */
        if(newProduct.getImagePath()!=null){
            oldProduct.setImagePath(newProduct.getImagePath());
        }
       if(newProduct.getMinAge()!=null){
            oldProduct.setMinAge(newProduct.getMinAge());
        }
        if(newProduct.getName()!=null){
            oldProduct.setName(newProduct.getName());
        }
        if(newProduct.getPrice() !=null){
            oldProduct.setPrice(newProduct.getPrice());
        }
        if(newProduct.getCategory() !=null){
            oldProduct.setCategory(newProduct.getCategory());
        }
        if(newProduct.getCartItems() !=null){
            oldProduct.setCartItems(newProduct.getCartItems());
        }
         
        
    }
}

