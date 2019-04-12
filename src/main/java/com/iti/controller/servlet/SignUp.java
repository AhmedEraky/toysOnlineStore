package com.iti.controller.servlet;

import com.iti.model.entity.User;
import com.iti.model.response.AuthenticationResponse;
import com.iti.model.response.Status;
import com.iti.service.RegistrationService;
import com.iti.service.UploadImageService;
import com.iti.service.impl.RegistrationServiceImpl;
import com.iti.service.impl.UploadImageServiceImpl;
import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.FileUploadException;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;
import org.apache.commons.io.FilenameUtils;

import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.File;
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.List;

@WebServlet("/signUp")
@MultipartConfig
public class SignUp extends HttpServlet {
    User user;
    HttpSession session;
    FileItem imageItem=null;
    String filename;

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        int imageCount= (int) request.getSession().getServletContext().getAttribute("imageCount");
        List<FileItem> items=null;
        user=new User();

        try {
            items = new ServletFileUpload(new DiskFileItemFactory()).parseRequest(request);
        } catch (FileUploadException e) {
            e.printStackTrace();
        }

        for (FileItem item : items) {
            if (item.isFormField()) {
                fillUserData(item);
            } else {
                imageItem=item;
                String imageName=item.getName().substring(0,item.getName().length()-4);
                String imageExtantion=item.getName().substring(item.getName().length()-4,item.getName().length());
                filename = FilenameUtils.getName(imageName+imageCount+imageExtantion); // Get filename.
                user.setImagePath("images"+File.separator+"users\\"+filename);
            }
        }
        //Try To Register
        RegistrationService service=new RegistrationServiceImpl();
        AuthenticationResponse authenticationResponse=service.register(user);
        if(authenticationResponse.getStatus().equals(Status.success)){
            String appPath=request.getServletContext().getRealPath("");
            String directory=appPath+ File.separator+"images"+File.separator+"users";
            request.getSession().getServletContext().setAttribute("imageCount",imageCount+1);
            UploadImageService uploadImageService=new UploadImageServiceImpl();
            uploadImageService.uploadImage(imageItem,directory,filename);
            request.setAttribute("registration",authenticationResponse);
            response.sendRedirect("login?signup="+authenticationResponse.getStatus()+"&message="+authenticationResponse.getMessage());

        }else {
            session.setAttribute("errorMessage",authenticationResponse.getMessage());
            response.sendRedirect("registration?signup="+authenticationResponse.getStatus()+"&message="+authenticationResponse.getMessage());
        }
    }

    private void fillUserData(FileItem item) {
        if(item.getFieldName().equals("email")){
            user.setEmail(item.getString());
        }else if(item.getFieldName().equals("name")){
            user.setName(item.getString());
        }else if(item.getFieldName().equals("password")){
            user.setPassword(item.getString());
        }else if(item.getFieldName().equals("job")){
            user.setJob(item.getString());
        }else if(item.getFieldName().equals("address")){
            user.setAddress(item.getString());
        }else if(item.getFieldName().equals("creditLimit")){
            user.setCreditLimit(Double.parseDouble(item.getString()));
        }
        else if (item.getFieldName().equals("dateOfBirth")){
            try {
                user.setBirthDate(new SimpleDateFormat("yyyy-MM-dd").parse(item.getString()));
            } catch (ParseException e) {
                e.printStackTrace();
            }
        }
    }

}
