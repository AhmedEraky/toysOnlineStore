package com.iti.service.impl;

import com.iti.Dao.UserDao;
import com.iti.Dao.implementation.UserDaoImplementation;
import com.iti.model.cfg.HibernateUtils;
import com.iti.model.constants.Status;
import com.iti.model.entity.ShoppingCart;
import com.iti.model.entity.User;
import com.iti.model.response.AuthenticationResponse;
import com.iti.service.RegistrationService;
import org.hibernate.Session;

public class RegistrationServiceImpl implements RegistrationService {
    @Override
    public AuthenticationResponse register(User user){
        AuthenticationResponse response=new AuthenticationResponse();
        ShoppingCart cart=new ShoppingCart();
        cart.setTotalCost(0d);
        user.setShoppingCart(cart);
        Session session= HibernateUtils.getSession();
        UserDao userDao = new UserDaoImplementation();
        if(userDao.persistUser(user,session)){
            response.setStatus(Status.success);
            response.setMessage("Registration Success");
            response.setEmail(user.getEmail());
        }else {
            response.setStatus(Status.fail);
            response.setMessage("Sorry Some Data Are Wrong");
            response.setEmail("");
        }
        return response;
    }
}
