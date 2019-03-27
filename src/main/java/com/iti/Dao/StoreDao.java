package com.iti.Dao;

import com.iti.model.entity.Product;
import com.iti.model.entity.Store;
import org.hibernate.Session;

public interface StoreDao {

    Store retriveStoreByProduct(Product product, Session session);
    boolean persistStore(Store store, Session session);
}
