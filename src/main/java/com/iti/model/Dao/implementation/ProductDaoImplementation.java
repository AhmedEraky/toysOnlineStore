package com.iti.model.Dao.implementation;

import com.iti.model.Dao.ProductDao;
import com.iti.model.entity.Product;
import org.hibernate.Session;
import com.iti.model.util.ProductUtil;
import com.iti.model.entity.Category;

import java.util.ArrayList;
import org.hibernate.Criteria;
import org.hibernate.HibernateException;
import org.hibernate.criterion.Example;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;

public class ProductDaoImplementation implements ProductDao {
    @Override
    public Product retriveProductByID(Integer id, Session session) {
        session.beginTransaction();
        Criteria criteria = session.createCriteria(Product.class).add(Restrictions.eq("ProductID", id));
        Product product = (Product) criteria.uniqueResult();
        product.getCategory().getProducts();
        product.getStore();
        session.clear();
        session.getTransaction().commit();
        return product;
    }

    @Override
    public Product retriveProductByExample(Product product, Session session) {
        Example example = Example.create(product);
        Criteria criteria = session.createCriteria(Product.class).add(example);
        return (Product) criteria.uniqueResult();
    }

    @Override
    public ArrayList<Product> retriveProductsByExample(Product product, Session session) {
        Example example = Example.create(product);
        Criteria criteria = session.createCriteria(Product.class).add(example);
        return (ArrayList<Product>) criteria.list();
    }

    @Override
    public ArrayList<Product> retriveProductsByAge(int minAge, int maxAge, Session session) {
        Criteria criteria = session.createCriteria(Product.class).add(Restrictions.between("minAge", minAge, maxAge));
        ArrayList<Product> products = (ArrayList<Product>) criteria.list();
        return products;
    }

    @Override
    public ArrayList<Product> retriveProductsByPrice(long minPrice, long maxPrice, Session session) {
        Criteria criteria = session.createCriteria(Product.class).add(Restrictions.between("price", minPrice, maxPrice));
        ArrayList<Product> products = (ArrayList<Product>) criteria.list();
        return products;
    }

    @Override
    public ArrayList<Product> retriveProductByName(String name, Session session) {
        session.beginTransaction();
        Criteria criteria = session.createCriteria(Product.class).add(Restrictions.like("name", "%" + name + "%"));
        ArrayList<Product> products = (ArrayList<Product>) criteria.list();
        session.clear();
        session.getTransaction().commit();
        return products;
    }

    @Override
    public boolean persistProduct(Product product, Session session) {
        session.beginTransaction();
        try {
            session.save(product);

            session.getTransaction().commit();
            return true;
        } catch (HibernateException e) {
            //e.printStackTrace();
            return false;
        }
    }

    @Override
    public boolean updateProduct(Product oldProduct, Product newProduct, Session session) {
        session.beginTransaction();
        
        ProductUtil productUtil = new ProductUtil();
        oldProduct = session.get(Product.class, oldProduct.getProductID());
        productUtil.compareProduct(oldProduct, newProduct);
        try {
            session.update(oldProduct);
            session.getTransaction().commit();
            return true;
        } catch (HibernateException exception) {
            session.getTransaction().rollback();
            return false;
        }

    }

    @Override
    public ArrayList<Product> retriveProductByCategory(Category category, Session session) {
        Criteria criteria = session.createCriteria(Product.class).add(Restrictions.eq("category", category));
        ArrayList<Product> products = (ArrayList<Product>) criteria.list();
        return products;
    }

    @Override
    public ArrayList<Product> retriveProducts(Session session) {
        Criteria criteria = session.createCriteria(Product.class);
        ArrayList<Product> products = (ArrayList<Product>) criteria.list();
        return products;
    }

    @Override
    public ArrayList<Product> retrieveNewProducts(Session session) {
        Criteria criteria = session.createCriteria(Product.class).addOrder(Order.desc("ProductID"));
        ArrayList<Product> products = (ArrayList<Product>) criteria.setMaxResults(10);
        return products;
    }

    @Override
    public ArrayList<Product> retrievePopularProducts(Session session) {
        Criteria criteria = session.createCriteria(Product.class).addOrder(Order.desc("purchaseCount"));
        ArrayList<Product> products = (ArrayList<Product>) criteria.setMaxResults(6);
        return products;
    }

    @Override
    public boolean updateProductQuantity(Product soldedProduct, Session session) {
        Product product = session.load(Product.class, soldedProduct.getProductID());
      
            int quantity = (product.getQuantity()) - (soldedProduct.getQuantity());
           
                product.setQuantity((quantity));
                //update
                try {
                    session.update(product);
                    session.getTransaction().commit();
                    return true;
                } catch (HibernateException e) {
                    session.getTransaction().rollback();
                    return false;
                }
           
        
    }
}
