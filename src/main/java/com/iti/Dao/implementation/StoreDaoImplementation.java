package com.iti.Dao.implementation;

import com.iti.Dao.StoreDao;
import com.iti.model.entity.Product;
import com.iti.model.entity.Store;
import org.hibernate.Session;

import java.util.Set;

public class StoreDaoImplementation implements StoreDao {

    @Override
    public Set<Store> retriveStoreByProduct(Product product, Session session) {
        session.get(Product.class,product.getProductID());
        return product.getStores();
    }

    @Override
    public boolean persistStore(Store store, Session session) {
        return false;
    }
}
