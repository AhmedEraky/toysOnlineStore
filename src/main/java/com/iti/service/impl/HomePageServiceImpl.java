package com.iti.service.impl;

import com.iti.model.Dao.ProductDao;
import com.iti.model.Dao.implementation.ProductDaoImplementation;
import com.iti.model.cfg.HibernateUtils;
import com.iti.model.cfg.transaction.TransactionManager;
import com.iti.model.entity.Product;
import com.iti.model.response.HomePageProductsResponse;
import com.iti.service.HomePageService;
import org.hibernate.Session;

import java.util.ArrayList;

public class HomePageServiceImpl implements HomePageService {

    ProductDao productDao = new ProductDaoImplementation();

    @Override
    public ArrayList<HomePageProductsResponse> getNewProducts() {
        TransactionManager transactionManager=new TransactionManager(HibernateUtils.getSessionFactory());

        try {
            return transactionManager.runInTransaction(session -> {
                ArrayList<Product> dataBaseProducts = productDao.retrieveNewProducts(session);
                ArrayList<HomePageProductsResponse> products = new ArrayList<>();
                transformProductToResponse(dataBaseProducts,products);
                return products;
            });
        } catch (Exception e) {
            e.printStackTrace();
            return new ArrayList<>();
        }
    }

    @Override
    public ArrayList<HomePageProductsResponse> getGuestFeaturedProducts() {
        TransactionManager transactionManager=new TransactionManager(HibernateUtils.getSessionFactory());
        try {
            return transactionManager.runInTransaction(session -> {
                ArrayList<Product> dataBaseProducts = productDao.retrievePopularProducts(session);
                ArrayList<HomePageProductsResponse> products = new ArrayList<>();
                transformProductToResponse(dataBaseProducts,products);
                return products;
            });
        } catch (Exception e) {
            e.printStackTrace();
            return new ArrayList<>();

        }



    }

    @Override
    public ArrayList<HomePageProductsResponse> getUserFeaturedProducts(String userEamil) {

        TransactionManager transactionManager=new TransactionManager(HibernateUtils.getSessionFactory());
        try {
            return transactionManager.runInTransaction(session -> {
                ArrayList<Product> dataBaseProducts = productDao.retrievePopularProducts(session);
                ArrayList<HomePageProductsResponse> products = new ArrayList<>();
                transformProductToResponse(dataBaseProducts,products);
                return products;
            });
        } catch (Exception e) {
            e.printStackTrace();
            return new ArrayList<>();

        }

    }

    private void transformProductToResponse(ArrayList<Product> dataBaseProducts,ArrayList<HomePageProductsResponse> products){

        dataBaseProducts.forEach(dataBaseProduct -> {
            HomePageProductsResponse product = new HomePageProductsResponse();
            product.setName(dataBaseProduct.getName());
            product.setPrice(dataBaseProduct.getPrice());
            product.setImagePath(dataBaseProduct.getImagePath());
            product.setProductID(dataBaseProduct.getProductID());
            products.add(product);
        });
    }
}
