package com.iti.service.impl;

import com.iti.model.entity.CartItem;
import com.iti.model.response.ValidationResponse;
import com.iti.service.DeleteCartItemService;

import java.util.Set;

public class DeleteCartItemServiceImpl implements DeleteCartItemService {
    @Override
    public ValidationResponse deleteCartItem(Set<CartItem> items) {
        return null;
    }
}
