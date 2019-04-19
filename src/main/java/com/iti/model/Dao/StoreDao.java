package com.iti.model.Dao;

import com.iti.model.entity.Product;
import com.iti.model.entity.Store;
import org.hibernate.Session;

import java.util.List;
import java.util.Set;

public interface StoreDao {

    Store retrieveStoreByProduct(Product product, Session session);
    boolean persistStore(Store store, Session session);

    //Aya
    Store retrieveStoreByName(String name,Session session);
    List<Store> retrieveAllStores(Session session);
}
