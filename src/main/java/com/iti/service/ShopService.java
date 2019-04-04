package com.iti.service;

import com.iti.model.request.ShopRequest;
import com.iti.model.response.ShopResponse;

import java.util.ArrayList;

public interface ShopService {
    public ArrayList<ShopResponse> shopData(ShopRequest shopRequest);
}
