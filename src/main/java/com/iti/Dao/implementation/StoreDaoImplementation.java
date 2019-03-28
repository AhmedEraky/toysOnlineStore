package com.iti.Dao.implementation;

import com.iti.Dao.StoreDao;
import com.iti.model.entity.Product;
import com.iti.model.entity.Store;
import org.hibernate.HibernateException;
import org.hibernate.Session;

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
}
