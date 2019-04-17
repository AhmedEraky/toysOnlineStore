package com.iti.controller.servlet;

import com.iti.model.entity.Product;
import com.iti.model.response.ConfirmationResponse;
import com.iti.model.response.Status;
import com.iti.service.ProductService;
import com.iti.service.UploadImageService;
import com.iti.service.impl.ProductServiceImpl;
import com.iti.service.impl.UploadImageServiceImpl;
import org.apache.commons.beanutils.BeanUtils;
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
import java.lang.reflect.InvocationTargetException;
import java.util.List;

@WebServlet("/uploadProduct")
@MultipartConfig
public class AddProductServlet extends HttpServlet {

    FileItem imageItem=null;
    String filename;
    Product product;
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        int imageCount= (int) request.getSession().getServletContext().getAttribute("imageCount");
        List<FileItem> items=null;
        product=new Product();
        try {
            items = new ServletFileUpload(new DiskFileItemFactory()).parseRequest(request);
        } catch (FileUploadException e) {
            e.printStackTrace();
        }

        String storeName="";
        String categoryName="";
        for (FileItem item : items) {
            if (item.isFormField()) {
                if(item.getFieldName().equals("categoryName")){
                    categoryName=item.getString();
                }
                else if(item.getFieldName().equals("storeName")){
                    storeName=item.getString();
                }
                else if(item.getFieldName().equals("name")){
                    product.setName(item.getString());
                }
                else if(item.getFieldName().equals("description")){
                    product.setDescription(item.getString());
                }
                else if(item.getFieldName().equals("quantity")){
                    product.setQuantity(Integer.parseInt(item.getString()));
                }
                else if(item.getFieldName().equals("minAge")){
                    product.setMinAge(Integer.parseInt(item.getString()));
                }
                else if(item.getFieldName().equals("price")){
                    product.setPrice(Double.parseDouble(item.getString()));
                }
                else if(item.getFieldName().equals("discountPercentage")){
                    product.setDiscountPercentage(Integer.parseInt(item.getString()));
                }
            } else {
                if(!item.getName().equals("")) {
                    imageItem = item;
                    String imageName = item.getName().substring(0, item.getName().length() - 4);
                    String imageExtantion = item.getName().substring(item.getName().length() - 4, item.getName().length());
                    filename = FilenameUtils.getName(imageName + imageCount + imageExtantion); // Get filename.
                    product.setImagePath("images" + File.separator + "products\\" + filename);
                }
            }
        }
        ProductService productService = new ProductServiceImpl();
        ConfirmationResponse confirmationResponse = new ConfirmationResponse();
        confirmationResponse = productService.insert(product, categoryName, storeName);
        if (confirmationResponse.getStatus().equals(Status.success)) {
            String appPath=request.getServletContext().getRealPath("");
            String directory=appPath+ File.separator+"images"+File.separator+"products";
            request.getSession().getServletContext().setAttribute("imageCount",imageCount+1);

            if(imageItem!=null) {
                UploadImageService uploadImageService = new UploadImageServiceImpl();
                uploadImageService.uploadImage(imageItem, directory, filename);
            }
            response.sendRedirect("addProduct?message=" + confirmationResponse.getMessage());
        }
        else{
            response.sendRedirect("addProduct?message=" + confirmationResponse.getMessage());
        }
    }

    private void fillProductData(FileItem item) {


    }


}
