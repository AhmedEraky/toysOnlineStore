package com.iti.service.impl;

import com.iti.model.Dao.ProductDao;
import com.iti.model.Dao.ReviewDao;
import com.iti.model.Dao.implementation.ProductDaoImplementation;
import com.iti.model.Dao.implementation.ReviewDaoImplementation;
import com.iti.model.cfg.HibernateUtils;
import com.iti.model.cfg.transaction.TransactionManager;
import com.iti.model.entity.Product;
import com.iti.model.entity.Review;
import com.iti.model.request.ShopRequest;
import com.iti.model.response.ReviewResponse;
import com.iti.model.response.ShopResponse;
import com.iti.service.ReviewService;
import com.iti.service.ShopService;
import org.hibernate.Session;

import java.util.ArrayList;

public class ShopServiceImpl implements ShopService {
    TransactionManager transactionManager=new TransactionManager(HibernateUtils.getSessionFactory());

    @Override
    public ArrayList<ShopResponse> shopData(ShopRequest shopRequest,int pageNumber) {
        try {
            return transactionManager.runInTransaction(session -> {
                ProductDao productDao=new ProductDaoImplementation();
                ArrayList<Product> products=productDao.retrieveProductsByFilters(shopRequest,session,(pageNumber-1)*9,9);
                ArrayList<ShopResponse> shopResponse=new ArrayList<>();
                for (Product product:products){
                    ShopResponse response=new ShopResponse();
                    response.setId(Integer.toString(product.getProductID()));
                    response.setName(product.getName());
                    response.setImagePath(product.getImagePath());
                    response.setPrice("$"+product.getPrice());
                    response.setImagePath(product.getImagePath());
                    //rate
                    ArrayList<ReviewResponse> responses=new  ArrayList<ReviewResponse>();
                    ReviewDao reviewDao = new ReviewDaoImplementation();
                    ArrayList<Review> reviews=reviewDao.retrieveReviewsByProduct(product,session);
                    int averageRate;
                    if(reviews.size()!=0) {
                        averageRate=retrieveRate(reviews);

                    }
                    else{
                        averageRate= 0;

                    }
                    response.setRate(Integer.toString(averageRate));
                    shopResponse.add(response);
                }
                return shopResponse;

            });
        } catch (Exception e) {
            e.printStackTrace();
            return new ArrayList<>();
        }
    }

    @Override
    public int getProductCount(ShopRequest shopRequest) {
        try {
            return transactionManager.runInTransaction(session -> {
                ProductDao productDao=new ProductDaoImplementation();
                return productDao.getCountByFilter(shopRequest,session);
            });
        } catch (Exception e) {
            e.printStackTrace();
            return 0;
        }
    }
    //calculate average rate
    public int retrieveRate(ArrayList<Review> reviews){
        int sum=0;
        for(Review review:reviews){
            sum+=review.getRate();
        }
        return sum/(reviews.size());
    }

}
