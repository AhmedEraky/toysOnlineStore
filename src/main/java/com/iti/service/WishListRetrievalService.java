package com.iti.service;

import com.iti.model.entity.Product;


import java.util.List;


public interface WishListRetrievalService
{
    List<Product> retrieveUserWishList(String userEmail);
}
