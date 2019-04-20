package com.iti.model.Dao;

import com.iti.model.entity.Product;
import com.iti.model.entity.User;
import org.hibernate.Session;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

public interface UserDao {

    User retiveUserEmail(String email, Session session);
    User retriveUserByExample(User user, Session session);
    ArrayList<User> retriveUsersByName(String name, Session session);

    User retiveUserEmailNew(String email, Session session);

    ArrayList<User> retriveUsers(Session session);
    boolean isUser(User user, Session session);
    boolean persistUser(User user, Session session);
    boolean updateUser(User oldUser, User newUser, Session session);

    //Aya Part
    boolean updateUser(User user, Session session);

    //Islam's Part
    List<Product> retrieveUserWishList(String userEmail, Session session);
}
