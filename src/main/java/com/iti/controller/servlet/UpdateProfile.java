package com.iti.controller.servlet;

import com.iti.model.entity.User;
import com.iti.model.response.ProfileResponse;
import com.iti.service.ProfileService;
import com.iti.service.impl.ProfileServiceImpl;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class UpdateProfile extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String birthDate = req.getParameter("birthDate");
        String job = req.getParameter("job");
        String address = req.getParameter("address");
        String email = req.getParameter("email");
        Double credit = Double.valueOf(req.getParameter("credit"));

        User user = new User();
        user.setEmail(email);
        try {
            user.setBirthDate(new SimpleDateFormat("yyyy-MM-dd").parse(birthDate));
        } catch (ParseException e) {
            e.printStackTrace();
        }
        user.setCreditLimit(credit);
        user.setJob(job);
        user.setAddress(address);

        ProfileService profileService = new ProfileServiceImpl();
        profileService.updateProfile(user);
        resp.sendRedirect("profile");
    }
}
