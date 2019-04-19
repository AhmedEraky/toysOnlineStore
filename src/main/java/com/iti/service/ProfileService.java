package com.iti.service;

import com.iti.model.entity.User;
import com.iti.model.response.ProfileResponse;

public interface ProfileService {

    ProfileResponse getProfileInfo(User user);
    Boolean updateProfile(User user);
    /*==Aya==*/
    User getUserByEmail(String userEmail);
}
