package com.iti.service.impl;

import com.iti.model.Dao.UserDao;
import com.iti.model.Dao.implementation.UserDaoImplementation;
import com.iti.model.cfg.HibernateUtils;
import com.iti.model.cfg.transaction.TransactionManager;
import com.iti.model.entity.User;
import com.iti.model.response.UserResponse;
import com.iti.service.GetUsersService;

import java.util.ArrayList;

public class GetUsersServiceImpl implements GetUsersService {
    TransactionManager transactionManager=new TransactionManager(HibernateUtils.getSessionFactory());
    @Override
    public ArrayList<User> getUsers() {

        try {
            return transactionManager.runInTransaction(session -> {
                UserDao userDao=new UserDaoImplementation();
                return userDao.retriveUsers(session);
            });
        } catch (Exception e) {
            return new ArrayList<>();
        }
    }

    @Override
    public ArrayList<UserResponse> searchForUsers(String name) {
        try {
            return transactionManager.runInTransaction(session -> {
                ArrayList<UserResponse> userResponses=new ArrayList<>();
                UserDao userDao=new UserDaoImplementation();
                ArrayList<User> users= userDao.retriveUsersByName(name,session);
                for (User user:users){
                    UserResponse userResponse=new UserResponse();
                    userResponse.setEmail(user.getEmail());
                    userResponse.setCreditLimit(user.getCreditLimit());
                    userResponse.setName(user.getName());
                    userResponses.add(userResponse);
                }
                return userResponses;
            });
        } catch (Exception e) {
            return new ArrayList<>();
        }
    }
}
