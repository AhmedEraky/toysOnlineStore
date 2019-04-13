package com.iti.service;

import com.iti.model.entity.CartItem;
import com.iti.model.response.ValidationResponse;

import java.util.Set;

public interface DeleteCartItemService {
    ValidationResponse deleteCartItem(Set<CartItem> items);

}
