package com.iti.Dao.implementation;

import com.iti.Dao.ReviewDao;
import com.iti.model.entity.Product;
import com.iti.model.entity.Review;
import org.hibernate.Session;

import java.util.ArrayList;

public class ReviewDaoImplementation implements ReviewDao {
    @Override
    public ArrayList<Review> retriveReviewsByProduct(Product product) {
        return null;
    }

    @Override
    public boolean persistReview(Review review, Session session) {
        return false;
    }

    @Override
    public boolean updateReviewData(Review oldReview, Review newReview, Session session) {
        return false;
    }
}
