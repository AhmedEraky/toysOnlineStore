package com.iti.service;

import com.iti.model.entity.ShoppingCart;
import com.iti.model.entity.User;
import com.iti.model.response.ValidationResponse;

public interface BuyingProcessHandlingService
{
    ValidationResponse hasEnoughCredit(String userEmail, double totalCost);
    void updateProductsData(ShoppingCart cart);
}
