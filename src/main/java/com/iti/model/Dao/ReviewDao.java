package com.iti.model.Dao;

import com.iti.model.entity.Product;
import com.iti.model.entity.Review;
import org.hibernate.Session;

import java.util.ArrayList;

//Aya Part
import com.iti.model.entity.User;
public interface ReviewDao {

    ArrayList<Review> retrieveReviewsByProduct(Product product, Session session);
    boolean persistReview(Review review, Session session);
    boolean updateReviewData(Review oldReview, Review newReview, Session session);

    //Aya Part
    ArrayList<Review>  retrieveReviewsByProductID(int productID,Session session);


}
