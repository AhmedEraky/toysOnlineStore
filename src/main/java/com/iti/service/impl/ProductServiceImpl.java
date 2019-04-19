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
import com.iti.model.cfg.transaction.TransactionManager;
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
        TransactionManager transactionManager=new TransactionManager(HibernateUtils.getSessionFactory());

        try {
            return transactionManager.runInTransaction(session -> {

                ProductResponse response=new ProductResponse();
                ProductDao productDao = new ProductDaoImplementation();
                Product product=productDao.retriveProductByID(productID,session);
                Category category=product.getCategory();
                fillresponse(response,product);
                return response;
            });
        } catch (Exception e) {
            e.printStackTrace();
            return new ProductResponse();
        }
    }

    private void fillresponse(ProductResponse response, Product product) {
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
    }

    @Override
    public ConfirmationResponse insert(Product product,String categoryName,String storeName) {
        //get storename
        //get store object

        //add to product
        product.setStore(getStore(storeName));

        //create product object from data in clientside
        //image
        product.setImagePath("images/"+product.getImagePath());
        //add to product
        product.setCategory(getCategory(categoryName));
        ConfirmationResponse confirmationResponse=new ConfirmationResponse();
        TransactionManager transactionManager=new TransactionManager(HibernateUtils.getSessionFactory());
        try {
            return transactionManager.runInTransaction(session -> {
                //insert product
                ProductDao productDao=new ProductDaoImplementation();
                boolean flag=productDao.persistProduct(product,session);
                // message in confirmationResponse
                if(flag){
                    confirmationResponse.setStatus(Status.success);
                    confirmationResponse.setMessage(product.getName()+" Successfully added");
                }
                else{
                    confirmationResponse.setStatus(Status.fail);
                    confirmationResponse.setMessage("Error exists! Please Insert again");
                }
                return confirmationResponse;

            });
        } catch (Exception e) {
            e.printStackTrace();
            confirmationResponse.setStatus(Status.fail);
            confirmationResponse.setMessage("Error exists! Please Insert again");
            return confirmationResponse;
        }

    }

    @Override
    public Boolean updateProduct(Product product) {
        TransactionManager transactionManager=new TransactionManager(HibernateUtils.getSessionFactory());
        try {
            return transactionManager.runInTransaction(session -> {
                ProductDao productDao = new ProductDaoImplementation();
                Boolean updated = false;
                Product currentProduct = productDao.retriveProductByID(product.getProductID(),session);
                Product newProduct = productDao.retriveProductByID(product.getProductID(),session);
                if(newProduct != null){
                    newProduct.setDiscountPercentage(product.getDiscountPercentage());
                    newProduct.setPrice(product.getPrice());
                    newProduct.setQuantity(product.getQuantity());
                    Session secondSession =HibernateUtils.getSession();
                    updated = productDao.updateProduct(currentProduct,newProduct,secondSession);
                }
                return updated;

            });
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    @Override
    public Boolean removeProduct(Integer productId) {
        TransactionManager transactionManager=new TransactionManager(HibernateUtils.getSessionFactory());
        try {
            return transactionManager.runInTransaction(session -> {
                ProductDao productDao = new ProductDaoImplementation();
                Boolean deleted = false;
                return productDao.removeProductByID(productId,session);

            });
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }



    public Store getStore(String storeName){
        TransactionManager transactionManager=new TransactionManager(HibernateUtils.getSessionFactory());
        try {
            return transactionManager.runInTransaction(session -> {
                Store store;
                StoreDao storeDao=new StoreDaoImplementation();
                store= storeDao.retrieveStoreByName(storeName,session);
                if(store !=null) {
                    return store;
                }
                else{
                    //add new store
                    Session secondSession =HibernateUtils.getSession();
                    Store newStore=new Store();
                    newStore.setName(storeName);
                    boolean flagStore=storeDao.persistStore(newStore,secondSession);
                    //get the new store
                    if(flagStore){
                        Session thirddSession =HibernateUtils.getSession();
                        store=storeDao.retrieveStoreByName(storeName,thirddSession);
                    }
                    return store;
                }

            });
        } catch (Exception e) {
            e.printStackTrace();
            return new Store();
        }
    }

    public Category getCategory(String categoryName){
        TransactionManager transactionManager=new TransactionManager(HibernateUtils.getSessionFactory());

        try {
            return transactionManager.runInTransaction(session -> {
                CategoryDao categoryDao=new CategoryDaoImplementation();
                Category category= categoryDao.retriveCategoryByName(categoryName,session);
                return category;
            });
        } catch (Exception e) {
            e.printStackTrace();
            return new Category();
        }

    }
    @Override
    public Product getProductByID(Integer productId) {
        TransactionManager transactionManager=new TransactionManager(HibernateUtils.getSessionFactory());

        try {
            return transactionManager.runInTransaction(session -> {
                ProductDao productDao = new ProductDaoImplementation();
                Product product=productDao.retriveProductByID(productId,session);
                return product;
            });
        } catch (Exception e) {
            e.printStackTrace();
            return new Product();
        }
    }

}
