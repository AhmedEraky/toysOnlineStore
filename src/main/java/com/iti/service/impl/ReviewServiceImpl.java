package com.iti.service.impl;

import com.iti.model.Dao.ProductDao;
import com.iti.model.Dao.ReviewDao;
import com.iti.model.Dao.UserDao;
import com.iti.model.Dao.implementation.ProductDaoImplementation;
import com.iti.model.Dao.implementation.ReviewDaoImplementation;
import com.iti.model.Dao.implementation.UserDaoImplementation;
import com.iti.model.cfg.HibernateUtils;
import com.iti.model.cfg.transaction.TransactionManager;
import com.iti.model.entity.Product;
import com.iti.model.entity.Review;
import com.iti.model.entity.ReviewId;
import com.iti.model.entity.User;
import com.iti.model.response.ReviewResponse;
import com.iti.service.ReviewService;
import org.hibernate.Session;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

public class ReviewServiceImpl implements ReviewService {

    @Override
    public ArrayList<ReviewResponse> fetch(int productID) {
        TransactionManager transactionManager=new TransactionManager(HibernateUtils.getSessionFactory());
        try {
            return transactionManager.runInTransaction(session -> {
                ArrayList<ReviewResponse> responses=new  ArrayList<ReviewResponse>();
                ProductDao productDao=new ProductDaoImplementation();
                Product product=productDao.retriveProductByID(productID,session);
                ReviewDao reviewDao = new ReviewDaoImplementation();
                ArrayList<Review> reviews=reviewDao.retrieveReviewsByProduct(product,session);
                if(reviews.size()!=0) {
                    //response
                    for (Review review : reviews) {
                        //image////////////////////////////////////////////////////////////////////////////////////

                        ReviewResponse response = new ReviewResponse();
                        response.setReviewDescription(review.getReviewDescription());
                        response.setUserEmail(review.getUser().getEmail());
                        response.setUserName(review.getUser().getName());
                        response.setRate(review.getRate());

                        responses.add(response);
                    }
                }
                return responses;
            });
        } catch (Exception e) {
            e.printStackTrace();
            return new ArrayList<>();
        }
    }

    @Override
    public boolean insert(ReviewResponse reviewResponse) {
        TransactionManager transactionManager=new TransactionManager(HibernateUtils.getSessionFactory());
        try {
            return transactionManager.runInTransaction(session -> {
                ReviewDao reviewDao=new ReviewDaoImplementation();
                ProductDao productDao=new ProductDaoImplementation();
                UserDao userDao=new UserDaoImplementation();
                //get user
                User user=userDao.retiveUserEmail(reviewResponse.getUserEmail(),session);
                //get product
                Product product=productDao.retriveProductByID(reviewResponse.getProductid(),session);

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
                //if user update the review
                Set<Review> userReviews=user.getReviews();
                Review oldReview=new Review();
                boolean revFalg=false;
                for(Review userReview:userReviews){
                   if(userReview.getId().equals(reviewId)){
                       revFalg=true;
                        oldReview=userReview;
                   }
                }
                if(revFalg){
                    return (reviewDao.updateReviewData(oldReview,review,session));
                }
                else {
                    return (reviewDao.persistReview(review, session));
                }
            });
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
        //call persist method from review


    }
}
