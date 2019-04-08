package com.iti.service.impl;

import com.iti.model.Dao.CategoryDao;
import com.iti.model.Dao.ProductDao;
import com.iti.model.Dao.ReviewDao;
import com.iti.model.Dao.StoreDao;
import com.iti.model.Dao.implementation.CategoryDaoImplementation;
import com.iti.model.Dao.implementation.ProductDaoImplementation;
import com.iti.model.Dao.implementation.ReviewDaoImplementation;
import com.iti.model.Dao.implementation.StoreDaoImplementation;
import com.iti.model.cfg.HibernateUtils;
import com.iti.model.entity.Category;
import com.iti.model.entity.Product;
import com.iti.model.entity.Review;
import com.iti.model.entity.Store;
import com.iti.model.response.ConfirmationResponse;
import com.iti.model.response.ProductResponse;
import com.iti.model.response.Status;
import com.iti.service.ProductService;
import org.hibernate.Session;

import java.util.ArrayList;
import java.util.Set;

public class ProductServiceImpl implements ProductService {
    @Override
    public ProductResponse fetch(Integer productID) {
        ProductResponse response=new ProductResponse();


        Session session= HibernateUtils.getSession();
        ProductDao productDao = new ProductDaoImplementation();

        Product product=productDao.retriveProductByID(productID,session);
        Category category=product.getCategory();
        //fill productresponse
        response.setDescription(product.getDescription());
        response.setName(product.getName());
        response.setPrice(product.getPrice());
        response.setDiscountPercentage((product.getDiscountPercentage()));

        response.setImagePath(product.getImagePath());
        response.setMinAge(product.getMinAge());
        response.setQuantity(product.getQuantity());
        response.setStoreName(product.getStore().getName());
        response.setCategoryName(product.getCategory().getName());
        response.setId(product.getProductID());

        return response;

    }

    @Override
    public ConfirmationResponse insert(Product product,String categoryName,String storeName) {
        //create product object from data in clientside
        Category category;
        //get category by name
        Session sessionC= HibernateUtils.getSession();
        CategoryDao categoryDao=new CategoryDaoImplementation();
        category= categoryDao.retriveCategoryByName(categoryName,sessionC);
        //add to product
        product.setCategory(category);
        //get store object
        Store store;
        //get category by name
        Session sessionStore= HibernateUtils.getSession();
        StoreDao storeDao=new StoreDaoImplementation();
        store= storeDao.retrieveStoreByName(storeName,sessionStore);
        //add to product
        product.setStore(store);

        //insert product
        Session session= HibernateUtils.getSession();
        ProductDao productDao=new ProductDaoImplementation();
        boolean flag=productDao.persistProduct(product,session);
        // message in confirmationResponse
        ConfirmationResponse confirmationResponse=new ConfirmationResponse();
            if(flag){
                confirmationResponse.setStatus(Status.success);
                confirmationResponse.setMessage(product.getName()+" Successfully added");
            }
            else{
                confirmationResponse.setStatus(Status.fail);
                confirmationResponse.setMessage("Error exists! Please Insert again");
            }
            return confirmationResponse;
    }

}
