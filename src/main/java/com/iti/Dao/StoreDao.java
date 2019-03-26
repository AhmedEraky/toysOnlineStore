package com.iti.Dao;

import com.iti.model.entity.Product;
import com.iti.model.entity.Store;
import org.hibernate.Session;

import java.util.Set;

public interface StoreDao {

    Set<Store> retriveStoreByProduct(Product product, Session session);
    boolean persistStore(Store store, Session session);
}
