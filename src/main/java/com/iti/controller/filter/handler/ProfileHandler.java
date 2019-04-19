package com.iti.controller.filter.handler;

import com.iti.model.entity.User;
import com.iti.model.response.ProfileResponse;
import com.iti.model.response.Status;
import com.iti.model.response.Usertype;
import com.iti.service.ProfileService;
import com.iti.service.impl.ProfileServiceImpl;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

public class ProfileHandler extends HomeHandler {
    @Override
    public void handle(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain, Boolean login) throws IOException, ServletException {
        session = request.getSession();

        if(session.getAttribute("userType")!=null&&session.getAttribute("userType").equals(Usertype.admin)){
            user=new User();
            user.setEmail((String) request.getParameter("mail"));
            ProfileService service = new ProfileServiceImpl();
            ProfileResponse profileResponse = service.getProfileInfo(user);
            if (profileResponse.getStatus().equals(Status.success)) {
                request.setAttribute("user",profileResponse);
                filterChain.doFilter(request, response);
            } else {
                session.setAttribute("errorMessage",profileResponse.getMessage());
                response.sendRedirect("home");
            }
        }
        else if(login!=null&&login==true){
            user=new User();
            user.setEmail((String) session.getAttribute("mail"));
            ProfileService service = new ProfileServiceImpl();
            ProfileResponse profileResponse = service.getProfileInfo(user);
            if (profileResponse.getStatus().equals(Status.success)) {
                request.setAttribute("user",profileResponse);
                filterChain.doFilter(request, response);
            } else {
                session.setAttribute("errorMessage",profileResponse.getMessage());
                response.sendRedirect("login");
            }

        }else {
            response.sendRedirect("login?message=you don,t have profile");
        }
    }
}
