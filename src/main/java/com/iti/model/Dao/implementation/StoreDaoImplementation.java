package com.iti.model.Dao.implementation;

import com.iti.model.Dao.StoreDao;
import com.iti.model.entity.Product;
import com.iti.model.entity.Store;
import org.hibernate.Criteria;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.criterion.Restrictions;

import java.util.List;
import java.util.Set;

public class StoreDaoImplementation implements StoreDao
{

    @Override
    public Store retrieveStoreByProduct(Product product, Session session)
    {
        session.get(Product.class, product.getProductID());
        return product.getStore();
    }



    @Override
    public boolean persistStore(Store store, Session session)
    {
        try {


            session.save(store);
            return true;
        }catch (HibernateException ex){
            ex.printStackTrace();
            return false;
        }
    }

    @Override
    public Store retrieveStoreByName(String name, Session session) {
        Criteria criteria = session.createCriteria(Store.class).add(Restrictions.eq("name",name));
        Store store=(Store) criteria.uniqueResult();
        if(store!=null)
            System.out.println("number of products = "+store.getStoreProducts().size());
        return store;

    }

    @Override
    public List<Store> retrieveAllStores(Session session) {
        Criteria criteria = session.createCriteria(Store.class);
        List<Store> stores= criteria.list();
        return stores;
    }

    @Override
    public boolean updateStore(Store store, Session session) {
        try {
            session.update(store);
            return true;
        }catch (HibernateException ex){
            return false;
        }
    }

    @Override
    public Set<Product> retrieveProductByStore(Store store, Session session) {
    return null;
    }


}
