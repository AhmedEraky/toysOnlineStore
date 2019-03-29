package com.iti.model.Dao;

import com.iti.model.entity.Product;
import com.iti.model.entity.Review;
import org.hibernate.Session;

import java.util.ArrayList;

public interface ReviewDao {

    ArrayList<Review> retriveReviewsByProduct(Product product);
    boolean persistReview(Review review, Session session);
    boolean updateReviewData(Review oldReview, Review newReview, Session session);

}
