package com.iti.model.util;

import com.iti.model.entity.User;

public class UserUtil {
    public void compareUser(User oldUser,User newUser){
        if(newUser.getName()!=null){
            oldUser.setName(newUser.getName());
        }
        if(newUser.getPassword()!=null){
            oldUser.setPassword(newUser.getPassword());
        }
        if(newUser.getBirthDate()!=null){
            oldUser.setBirthDate(newUser.getBirthDate());
        }
        if (newUser.getJob()!=null){
            oldUser.setJob(newUser.getJob());
        }
        if(newUser.getAddress()!=null){
            oldUser.setAddress(newUser.getAddress());
        }
        if (newUser.getCreditLimit()!=null){
            oldUser.setCreditLimit(newUser.getCreditLimit());
        }
        if(newUser.getImagePath()!=null){
            oldUser.setImagePath(newUser.getImagePath());
        }
    }
}
