package com.iti.service;


import com.iti.model.entity.Product;
import com.iti.model.response.ConfirmationResponse;
import com.iti.model.response.ProductResponse;

public interface ProductService {
    ProductResponse fetch(Integer productId);
    ConfirmationResponse insert(Product product,String categoryName,String storeName);
    Boolean updateProduct(Product product);
    Boolean removeProduct(Integer productId);
    /*==Aya==*/
    Product getProductByID(Integer productId);



}


