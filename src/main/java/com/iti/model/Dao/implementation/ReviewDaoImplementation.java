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

        return productReviews;


    }

    @Override
    public boolean persistReview(Review review, Session session)
    {
        session.save(review);
        return true;
    }

    @Override
    public boolean updateReviewData(Review oldReview, Review newReview, Session session)
    {
        oldReview = session.get(Review.class, oldReview.getId());
        ReviewUtil reviewUtil = new ReviewUtil();
        reviewUtil.compareReview(oldReview, newReview);
        session.update(oldReview);
        return true;

    }

    @Override
    public ArrayList<Review> retrieveReviewsByProductID(int productID, Session session) {
        Criteria criteria = session.createCriteria(Product.class).add(Restrictions.eq("ProductID", productID));
        Product product = (Product) criteria.uniqueResult();
        ArrayList<Review> reviews=retrieveReviewsByProduct( product, session);
        return reviews;
    }

    @Override
    public boolean updateReview(Review newReview, Session session)
    {

        session.saveOrUpdate(newReview);
        return true;

    }
}
