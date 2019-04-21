package com.iti.service;

import com.iti.model.response.ConfirmationResponse;

public interface UserWishesService  {
    ConfirmationResponse insertNewUserWishes(Integer productId, String userEmail);
}
