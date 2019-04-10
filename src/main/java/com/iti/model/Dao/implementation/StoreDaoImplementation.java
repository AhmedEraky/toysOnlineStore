package com.iti.model.Dao.implementation;

import com.iti.model.Dao.StoreDao;
import com.iti.model.entity.Product;
import com.iti.model.entity.Store;
import org.hibernate.Criteria;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.criterion.Restrictions;

public class StoreDaoImplementation implements StoreDao
{

    @Override
    public Store retrieveStoreByProduct(Product product, Session session)
    {
        session.beginTransaction();
        session.get(Product.class, product.getProductID());
        session.getTransaction().commit();
        return product.getStore();
    }



    @Override
    public boolean persistStore(Store store, Session session)
    {
        try
        {
            session.beginTransaction();
            session.persist(store);
            session.getTransaction().commit();
            return true;
        } catch (HibernateException ex)
        {
            session.getTransaction().rollback();
            return false;
        }
    }

    @Override
    public Store retrieveStoreByName(String name, Session session) {
        session.beginTransaction();
        Criteria criteria = session.createCriteria(Store.class).add(Restrictions.eq("name",name));
      Store store=(Store) criteria.uniqueResult();
        //session.clear();
        session.getTransaction().commit();
        return store;

    }
}
