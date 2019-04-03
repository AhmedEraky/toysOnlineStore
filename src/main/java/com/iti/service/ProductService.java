package com.iti.service;


import com.iti.model.response.ProductResponse;

public interface ProductService {
    ProductResponse fetch(Integer productId);
}


