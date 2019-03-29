package com.iti.service.impl;

import com.iti.Dao.UserDao;
import com.iti.Dao.implementation.UserDaoImplementation;
import com.iti.model.cfg.HibernateUtils;
import com.iti.model.constants.Status;
import com.iti.model.entity.User;
import com.iti.model.response.AuthenticationResponse;
import com.iti.model.response.ValidationResponse;
import com.iti.service.LoginService;
import org.hibernate.Session;

public class LoginServiceImpl implements LoginService {
    @Override
    public AuthenticationResponse login(User user) {
        AuthenticationResponse response=new AuthenticationResponse();
        UserDao userDao=new UserDaoImplementation();
        Session session= HibernateUtils.getSession();
        if(userDao.isUser(user,session)){
            response.setStatus(Status.success);
            response.setEmail(user.getEmail());
            response.setMessage("Success Login");
        }
        else {
            response.setStatus(Status.fail);
            response.setEmail("");
            response.setMessage("Wrong Email Or Password");
        }
        return response;
    }
}
