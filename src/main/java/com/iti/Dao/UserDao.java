package com.iti.Dao;

import com.iti.model.entity.Admin;
import com.iti.model.entity.User;
import org.hibernate.Session;

import java.util.ArrayList;

public interface UserDao {

    User retiveUserEmail(String email, Session session);
    User retriveUserByExample(User user, Session session);
    ArrayList<User> retriveUsersByName(String name, Session session);
    ArrayList<User> retriveUsers(Session session);
    boolean isUser(User user, Session session);
    boolean persistUser(User user, Session session);
    boolean updateUser(User oldUser, User newUser, Session session);

}
