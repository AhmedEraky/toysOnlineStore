package com.iti.model.util;

import com.iti.model.entity.Review;

public class ReviewUtil
{

    public void compareReview(Review oldReview, Review newReview)
    {

        oldReview.setRate(newReview.getRate());
        if (newReview.getReviewDescription() != null)
        {
            oldReview.setReviewDescription(newReview.getReviewDescription());
        }
    }
}
