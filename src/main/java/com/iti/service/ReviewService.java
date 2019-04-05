package com.iti.service;

import com.iti.model.entity.Product;
import com.iti.model.entity.Review;
import com.iti.model.response.ReviewResponse;

import java.util.ArrayList;

public interface ReviewService {
    ArrayList<ReviewResponse> fetch(int productID);
    boolean insert(ReviewResponse reviewResponse);
}
