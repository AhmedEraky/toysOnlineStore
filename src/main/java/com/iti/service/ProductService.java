package com.iti.service;


import com.iti.model.entity.Product;
import com.iti.model.response.ConfirmationResponse;
import com.iti.model.response.ProductResponse;

import java.util.List;

public interface ProductService {
    ProductResponse fetch(Integer productId);
    ConfirmationResponse insert(Product product,String categoryName,String storeName);
    Boolean updateProduct(Product product);
    Boolean removeProduct(Integer productId);
    /*==Aya==*/
    Product getProductByID(Integer productId);
    //to get set of products of certain store




}


