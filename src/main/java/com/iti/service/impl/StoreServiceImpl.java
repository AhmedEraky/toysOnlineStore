package com.iti.service.impl;

import com.iti.model.Dao.ProductDao;
import com.iti.model.Dao.StoreDao;
import com.iti.model.Dao.implementation.ProductDaoImplementation;
import com.iti.model.Dao.implementation.StoreDaoImplementation;
import com.iti.model.cfg.HibernateUtils;
import com.iti.model.cfg.transaction.TransactionManager;
import com.iti.model.entity.Category;
import com.iti.model.entity.Product;
import com.iti.model.entity.Store;
import com.iti.model.response.ProductResponse;
import com.iti.service.StoreService;

import java.util.*;

public class StoreServiceImpl implements StoreService {
    @Override
    public List<Store> getStores() {

        TransactionManager transactionManager=new TransactionManager(HibernateUtils.getSessionFactory());

        try {
            return transactionManager.runInTransaction(session -> {


                StoreDao storeDao = new StoreDaoImplementation();
                List<Store> stores=storeDao.retrieveAllStores(session);


                return stores;
            });
        } catch (Exception e) {
            e.printStackTrace();
            return new List<Store>() {
                @Override
                public int size() {
                    return 0;
                }

                @Override
                public boolean isEmpty() {
                    return false;
                }

                @Override
                public boolean contains(Object o) {
                    return false;
                }

                @Override
                public Iterator<Store> iterator() {
                    return null;
                }

                @Override
                public Object[] toArray() {
                    return new Object[0];
                }

                @Override
                public <T> T[] toArray(T[] a) {
                    return null;
                }

                @Override
                public boolean add(Store store) {
                    return false;
                }

                @Override
                public boolean remove(Object o) {
                    return false;
                }

                @Override
                public boolean containsAll(Collection<?> c) {
                    return false;
                }

                @Override
                public boolean addAll(Collection<? extends Store> c) {
                    return false;
                }

                @Override
                public boolean addAll(int index, Collection<? extends Store> c) {
                    return false;
                }

                @Override
                public boolean removeAll(Collection<?> c) {
                    return false;
                }

                @Override
                public boolean retainAll(Collection<?> c) {
                    return false;
                }

                @Override
                public void clear() {

                }

                @Override
                public Store get(int index) {
                    return null;
                }

                @Override
                public Store set(int index, Store element) {
                    return null;
                }

                @Override
                public void add(int index, Store element) {

                }

                @Override
                public Store remove(int index) {
                    return null;
                }

                @Override
                public int indexOf(Object o) {
                    return 0;
                }

                @Override
                public int lastIndexOf(Object o) {
                    return 0;
                }

                @Override
                public ListIterator<Store> listIterator() {
                    return null;
                }

                @Override
                public ListIterator<Store> listIterator(int index) {
                    return null;
                }

                @Override
                public List<Store> subList(int fromIndex, int toIndex) {
                    return null;
                }
            };


        }
    }
}
