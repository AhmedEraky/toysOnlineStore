package com.iti.service.impl;

import com.iti.model.Dao.UserDao;
import com.iti.model.Dao.implementation.UserDaoImplementation;
import com.iti.model.cfg.HibernateUtils;
import com.iti.model.cfg.transaction.TransactionManager;
import com.iti.model.response.Status;
import com.iti.model.entity.ShoppingCart;
import com.iti.model.entity.User;
import com.iti.model.response.AuthenticationResponse;
import com.iti.service.RegistrationService;
import org.hibernate.Session;

public class RegistrationServiceImpl implements RegistrationService {
    @Override
    public AuthenticationResponse register(User user){
        AuthenticationResponse response=new AuthenticationResponse();

        TransactionManager transactionManager=new TransactionManager(HibernateUtils.getSessionFactory());
        try {
            return transactionManager.runInTransaction(session -> {
                ShoppingCart cart=new ShoppingCart();
                cart.setTotalCost(0.0);
                user.setShoppingCart(cart);
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

            });
        } catch (Exception e) {
            response.setStatus(Status.fail);
            response.setMessage("Sorry Some Data Are Wrong");
            response.setEmail("");
            e.printStackTrace();
            return response;

        }
    }
}
