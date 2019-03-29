package com.iti;

import com.iti.model.Dao.implementation.CategoryDaoImplementation;
import com.iti.controller.UserController;
import com.iti.model.cfg.HibernateUtils;
import com.iti.model.entity.Category;
import com.iti.model.entity.ShoppingCart;
import com.iti.model.entity.User;

import java.util.Date;
import org.hibernate.Session;


public class Main {
    public static void main(String arg[]){

        UserController userController=new UserController();

        User user=new User();
        ShoppingCart shoppingCart=new ShoppingCart();

        user.setEmail("ahmed@yahoo.com");
        user.setAddress("Cairo");
        user.setBirthDate(new Date());
        user.setCreditLimit(10d);
        user.setName("Ahmed Eraky");
        user.setPassword("111111");
        shoppingCart.setTotalCost(0d);

        user.setShoppingCart(shoppingCart);
        userController.insertUser(user);
        User currentUser=userController.retriveUserByEmail("ahmed@yahoo.com");
        System.out.println("current Job is "+currentUser.getJob());
        User newu=new User();
        newu.setJob("new Job");
        newu.setName("fdut");
        userController.updateUser(currentUser,newu);
        user=userController.retriveUserByEmail("ahmed@yahoo.com");
        System.out.println("new Data = "+user.getJob());

        Session session = HibernateUtils.getSession();
        Category category = new Category("Dolls");
        CategoryDaoImplementation categoryDaoImplementation = new CategoryDaoImplementation();
        categoryDaoImplementation.persistCategory(category, session);
        System.out.println("Done");
    }
}
