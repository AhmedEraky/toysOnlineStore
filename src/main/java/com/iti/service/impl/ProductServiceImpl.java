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
import com.iti.model.entity.Store;
import com.iti.model.response.ProductResponse;
import com.iti.service.ProductService;
import org.hibernate.Session;

public class ProductServiceImpl implements ProductService {
    @Override
    public ProductResponse fetch(Integer productID) {
        ProductResponse response=new ProductResponse();


        Session session= HibernateUtils.getSession();
        ProductDao productDao = new ProductDaoImplementation();
        ReviewDao reviewDao = new ReviewDaoImplementation();
        Product product=productDao.retriveProductByID(productID,session);
        Category category=product.getCategory();
        //fill productresponse
        response.setDescription(product.getDescription());
        response.setName(product.getName());
        response.setPrice(product.getPrice());
        response.setDiscountPercentage(product.getDiscountPercentage());
        response.setImagePath(product.getImagePath());
        response.setMinAge(product.getMinAge());
        response.setQuantity(product.getQuantity());

        //category
/*
        Session session1= HibernateUtils.getSession();
        CategoryDao categoryDao = new CategoryDaoImplementation();
        Category category=categoryDao.retriveCategoryByProduct(product,session1);
        response.setCategoryName(category.getName());
        */
        //stores
/*
        StoreDao storeDao = new StoreDaoImplementation();
        Session session2= HibernateUtils.getSession();
        Store store=storeDao.retrieveStoreByProduct(product,session2);
        response.setStoreName(store.getName());
        //rate
        int rate=reviewDao.retrieveRateByProduct( product,  session);
        response.setRate(rate);
*/
        return response;

    }
}
