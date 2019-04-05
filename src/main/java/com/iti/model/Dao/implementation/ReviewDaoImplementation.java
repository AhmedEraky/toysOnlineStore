package com.iti.model.Dao.implementation;

import com.iti.model.Dao.ReviewDao;
import com.iti.model.cfg.HibernateUtils;
import com.iti.model.entity.Product;
import com.iti.model.entity.Review;
import com.iti.model.util.ReviewUtil;
import org.hibernate.Session;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import javax.persistence.PersistenceException;
import org.hibernate.Criteria;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;

public class ReviewDaoImplementation implements ReviewDao
{

    //Aya Part

    @Override
    public ArrayList<Review> retrieveReviewsByProduct(Product product, Session session)
    {
        session.beginTransaction();
        Criteria criteria = session.createCriteria(Review.class).createAlias("products","p").add(Restrictions.eq("p.ProductID",product.getProductID()));
        ArrayList<Review> productReviews= (ArrayList<Review>) criteria.list();
       /* ArrayList<Product> productList = (ArrayList<Product>) criteria.list();
        for(Product product1:productList) {
            reviewsList.add((Review) product1.getReviews());
        }
        session.clear();
        session.getTransaction().commit();

        return productReviews;
*/
        session.clear();
        session.getTransaction().commit();

        return productReviews;


    }

    @Override
    public boolean persistReview(Review review, Session session)
    {
        try
        {
            session.beginTransaction();
            session.saveOrUpdate(review);
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

    @Override
    public ArrayList<Review> retrieveReviewsByProductID(int productID, Session session) {
        session.beginTransaction();
        Criteria criteria = session.createCriteria(Product.class).add(Restrictions.eq("ProductID", productID));
        Product product = (Product) criteria.uniqueResult();
        session.clear();
        session.getTransaction().commit();
        Session session1= HibernateUtils.getSession();
        ArrayList<Review> reviews=retrieveReviewsByProduct( product, session1);


        return reviews;

    }


}
