package com.iti.service.impl;

import com.iti.model.Dao.UserDao;
import com.iti.model.Dao.implementation.UserDaoImplementation;
import com.iti.model.cfg.HibernateUtils;
import com.iti.model.entity.User;
import com.iti.model.response.ProfileResponse;
import com.iti.model.response.Status;
import com.iti.service.ProfileService;
import org.hibernate.Session;

import java.util.Date;

public class ProfileServiceImpl implements ProfileService {
    @Override
    public ProfileResponse getProfileInfo(User user) {
        ProfileResponse response = new ProfileResponse();
        UserDao userDao = new UserDaoImplementation();
        Session session = HibernateUtils.getSession();
        User retrivedUser = userDao.retiveUserEmail(user.getEmail(),session);
        if(retrivedUser != null){
            response.setStatus(Status.success);
            response.setMessage("Success");
            response.setUserName(retrivedUser.getName());
            response.setJob(retrivedUser.getJob());
            response.setAddress(retrivedUser.getAddress());
            response.setCreditLimit(retrivedUser.getCreditLimit());
            response.setEmail(retrivedUser.getEmail());
            Date birthDate  = retrivedUser.getBirthDate();
            String dateToString = birthDate.toString().split(" ")[0];
            response.setBirthDate(dateToString);
            response.setImgPath(retrivedUser.getImagePath());
        }
        else {
            response.setStatus(Status.fail);
            response.setMessage("Failed");
        }

        return response;

    }

    @Override
    public Boolean updateProfile(User user) {
        UserDao userDao = new UserDaoImplementation();
        Session session = HibernateUtils.getSession();
        User currentUser = userDao.retiveUserEmail(user.getEmail(),session);
        currentUser.setBirthDate(user.getBirthDate());
        currentUser.setJob(user.getJob());
        currentUser.setAddress(user.getAddress());
        currentUser.setCreditLimit(user.getCreditLimit());
        Boolean updated = userDao.updateUser(currentUser,session);
        return updated;
    }
}
