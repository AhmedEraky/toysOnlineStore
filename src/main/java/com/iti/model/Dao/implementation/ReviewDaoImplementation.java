package com.iti.model.Dao.implementation;

import com.iti.model.Dao.ReviewDao;
import com.iti.model.entity.Product;
import com.iti.model.entity.Review;
import com.iti.model.util.ReviewUtil;
import org.hibernate.Session;

import java.util.ArrayList;
import java.util.List;
import javax.persistence.PersistenceException;
import org.hibernate.Criteria;
import org.hibernate.criterion.Restrictions;

public class ReviewDaoImplementation implements ReviewDao
{

    @Override
    public ArrayList<Review> retrieveReviewsByProduct(Product product, Session session)
    {
        Criteria criteria = session.createCriteria(Review.class)
                .createAlias("products", "p").
                add(Restrictions.eq("p.ProductID", product.getProductID()));
        List<Review> reviewsList = criteria.list();
        ArrayList<Review> productReviews = new ArrayList<>(reviewsList);
        return productReviews;
    }

    @Override
    public boolean persistReview(Review review, Session session)
    {
        try
        {
            session.beginTransaction();
            session.persist(review);
            session.getTransaction().commit();
            return true;
        } catch (PersistenceException ex)
        {
            session.getTransaction().rollback();
            return false;
        }
    }

    @Override
    public boolean updateReviewData(Review oldReview, Review newReview, Session session)
    {
        session.beginTransaction();
        oldReview = session.get(Review.class, oldReview.getId());
        ReviewUtil reviewUtil = new ReviewUtil();
        reviewUtil.compareReview(oldReview, newReview);
        try
        {
            session.update(oldReview);
            session.getTransaction().commit();
            return true;
        } 
        catch (Exception exception)
        {
            session.getTransaction().rollback();
            return false;
        }
    }
    // Eraky part
    
    //Aya Part
    @Override
    public int retrieveRateByProduct(Product product, Session session) {
        session.beginTransaction();
        Criteria criteria = session.createCriteria(Review.class)
                .createAlias("products", "p").
                        add(Restrictions.eq("p.ProductID", product.getProductID()));
        List<Review> reviewsList = criteria.list();
        //get rate of each review
        int sum=0;
        for(Review review:reviewsList){
            sum+=review.getRate();
        }
        int overallReview = sum / (reviewsList.size());
        session.clear();
        session.getTransaction().commit();
        return overallReview;
    }


}
