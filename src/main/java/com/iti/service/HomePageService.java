package com.iti.service;

import com.iti.model.response.HomePageProductsResponse;

import java.util.ArrayList;

public interface HomePageService {

    ArrayList<HomePageProductsResponse> getNewProducts();
    ArrayList<HomePageProductsResponse> getGuestFeaturedProducts();
    ArrayList<HomePageProductsResponse> getUserFeaturedProducts(String userEamil);

}
