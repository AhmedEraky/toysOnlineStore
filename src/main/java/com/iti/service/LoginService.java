package com.iti.service;

import com.iti.model.entity.User;
import com.iti.model.response.AuthenticationResponse;
import com.iti.model.response.ValidationResponse;

public interface LoginService {
    AuthenticationResponse login(User user);
}
