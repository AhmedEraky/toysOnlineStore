package com.iti.service.impl;

import com.iti.model.Dao.ProductDao;
import com.iti.model.Dao.ReviewDao;
import com.iti.model.Dao.UserDao;
import com.iti.model.Dao.implementation.ProductDaoImplementation;
import com.iti.model.Dao.implementation.ReviewDaoImplementation;
import com.iti.model.Dao.implementation.UserDaoImplementation;
import com.iti.model.cfg.HibernateUtils;
import com.iti.model.entity.Product;
import com.iti.model.entity.Review;
import com.iti.model.entity.ReviewId;
import com.iti.model.entity.User;
import com.iti.model.response.ReviewResponse;
import com.iti.service.ReviewService;
import org.hibernate.Session;

import java.util.ArrayList;

public class ReviewServiceImpl implements ReviewService {

    @Override
    public ArrayList<ReviewResponse> fetch(int productID) {
        ArrayList<ReviewResponse> responses=new  ArrayList<ReviewResponse>();

        Session session= HibernateUtils.getSession();
        ReviewDao reviewDao = new ReviewDaoImplementation();
        ArrayList<Review> reviews=reviewDao.retrieveReviewsByProductID(productID,session);

        //response
        for (Review review:reviews) {
            //image
            ReviewResponse response=new ReviewResponse();
            response.setReviewDescription(review.getReviewDescription());
            response.setUserEmail(review.getUser().getEmail());
            response.setUserName(review.getUser().getName());
            response.setRate(review.getRate());
            responses.add(response);
        }
        return responses;
    }

    @Override
    public boolean insert(ReviewResponse reviewResponse) {
        //call persist method from review

        ReviewDao reviewDao=new ReviewDaoImplementation();
        ProductDao productDao=new ProductDaoImplementation();
        UserDao userDao=new UserDaoImplementation();
        //get user
        Session session= HibernateUtils.getSession();
        User user=userDao.retiveUserEmail(reviewResponse.getUserEmail(),session);
        //get product
        Session sessionProduct= HibernateUtils.getSession();
        Product product=productDao.retriveProductByID(reviewResponse.getProductid(),sessionProduct);

        //fill review object
        Review review=new Review();
        review.setUser(user);
        review.setReviewDescription(reviewResponse.getReviewDescription());
        review.setRate(reviewResponse.getRate());
        review.setProducts(product);
        //reviewid
        ReviewId reviewId=new ReviewId();
        reviewId.setUserId(user.getEmail());
        reviewId.setProductsId(product.getProductID());
        review.setId(reviewId);
        //insert review
        Session sessionPeview= HibernateUtils.getSession();
        return (reviewDao.persistReview(review,sessionPeview));
    }
}
