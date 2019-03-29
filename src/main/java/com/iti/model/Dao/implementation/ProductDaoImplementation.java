package com.iti.model.Dao.implementation;

import com.iti.model.Dao.ProductDao;
import com.iti.model.entity.Product;
import org.hibernate.Session;

import java.util.ArrayList;

public class ProductDaoImplementation implements ProductDao {
    @Override
    public Product retriveProductByID(Integer id, Session session) {
        return null;
    }

    @Override
    public Product retriveProductByExample(Product product, Session session) {
        return null;
    }

    @Override
    public ArrayList<Product> retriveProductsByExample(Product product, Session session) {
        return null;
    }

    @Override
    public ArrayList<Product> retriveProductsByAge(int minAge, int maxAge, Session session) {
        return null;
    }

    @Override
    public ArrayList<Product> retriveProductsByPrice(long minPrice, long maxPrice, Session session) {
        return null;
    }

    @Override
    public ArrayList<Product> retriveProductByName(String name, Session session) {
        return null;
    }

    @Override
    public boolean persistProduct(Product product, Session session) {
        return false;
    }

    @Override
    public boolean updateProduct(Product oldProduct, Product newProduct, Session session) {
        return false;
    }
}
