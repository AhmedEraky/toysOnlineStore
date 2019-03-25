package com.iti;
import com.iti.Dao.implementation.RegistrationDao;
import java.math.BigDecimal;
import java.util.Date;
import com.iti.model.entity.*;


public class Main {
    public static void main(String arg[]){

        User user=new User();
        user.setName("Ahmed");
        user.setAddress("uducduc");
        user.setEmail("ayhd");
        user.setPassword("cbcyeych");
        user.setImagePath("yhchfhe");
        user.setJob("hahaha");
        user.setBirthDate(new Date());
        user.setCreditLimit(new BigDecimal("12"));

        RegistrationDao registrationDao=new RegistrationDao();
        registrationDao.Registration(user);
    }
}
