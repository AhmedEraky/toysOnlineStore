package com.iti.service;

import com.iti.model.entity.User;
import com.iti.model.response.ValidationResponse;

public interface CreditHandlingService
{
    ValidationResponse hasEnoughCredit(String userEmail, double totalCost);
}
