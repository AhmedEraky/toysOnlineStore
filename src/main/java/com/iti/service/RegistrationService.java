package com.iti.service;

import com.iti.model.entity.User;
import com.iti.model.response.AuthenticationResponse;

public interface RegistrationService {
    public AuthenticationResponse register(User user);
}
