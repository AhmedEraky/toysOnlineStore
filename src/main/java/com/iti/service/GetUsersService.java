package com.iti.service;

import com.iti.model.entity.User;
import com.iti.model.response.UserResponse;

import java.util.ArrayList;

public interface GetUsersService {
    ArrayList<User> getUsers();
    ArrayList<UserResponse> searchForUsers(String name);
}
