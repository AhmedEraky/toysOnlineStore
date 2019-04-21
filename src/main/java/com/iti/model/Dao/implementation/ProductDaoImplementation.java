package com.iti.model.Dao.implementation;

import com.iti.model.Dao.ProductDao;
import com.iti.model.entity.Product;
import com.iti.model.util.ShopFiltrationUtil;
import org.hibernate.Session;
import com.iti.model.util.ProductUtil;
import com.iti.model.entity.Category;

import java.util.ArrayList;
import org.hibernate.Criteria;
import org.hibernate.HibernateException;
import org.hibernate.criterion.*;


//Eraky Import
import com.iti.model.request.ShopRequest;


//Aya import


//Ashraf Import



public class ProductDaoImplementation implements ProductDao {
    //Eraky Part



    @Override
    public ArrayList<Product> retrieveProductsByFilters(ShopRequest request, Session session,int start,int pageSize){
        ShopFiltrationUtil util=new ShopFiltrationUtil();
        Criteria criteria=session.createCriteria(Product.class,"product").
                createAlias("product.category","category");
        util.ShopFilter(criteria,request);
        criteria.setFirstResult(start);
        criteria.setMaxResults(pageSize);
        ArrayList products= (ArrayList) criteria.list();
        return products;

    }

    @Override
    public int getCountByFilter(ShopRequest request, Session session){
        ShopFiltrationUtil util=new ShopFiltrationUtil();
        Criteria criteria=session.createCriteria(Product.class,"product").
                createAlias("product.category","category");
        util.ShopFilter(criteria,request);
        criteria.setProjection(Projections.rowCount());
        return ((Long) criteria.uniqueResult()).intValue();

    }
    @Override
    public ArrayList<Product> retrieveProductsByPage(Session session,int start,int pageSize) {
        start=start*pageSize;
        Criteria criteria = session.createCriteria(Product.class);
        criteria.setFirstResult(start);
        criteria.setMaxResults(pageSize);
        ArrayList<Product> products = (ArrayList<Product>)criteria.list();
        return products;
    }

    //Aya Part

    @Override
    public Product retriveProductByID(Integer id, Session session) {

        Criteria criteria = session.createCriteria(Product.class).add(Restrictions.eq("ProductID", id));
        Product product = (Product) criteria.uniqueResult();
        product.getCategory().getProducts();
        product.getStore();
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
        Criteria criteria = session.createCriteria(Product.class).add(Restrictions.like("name", "%" + name + "%"));
        ArrayList<Product> products = (ArrayList<Product>) criteria.list();
        return products;
    }

    @Override
    public boolean persistProduct(Product product, Session session) {
            session.save(product);
        System.out.println("======================="+session.isOpen());
            return true;
    }

    @Override
    public boolean updateProduct(Product oldProduct, Product newProduct, Session session) {

        ProductUtil productUtil = new ProductUtil();
        oldProduct = session.get(Product.class, oldProduct.getProductID());
        productUtil.compareProduct(oldProduct, newProduct);
            session.update(oldProduct);
            return true;
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
        ArrayList<Product> products = (ArrayList<Product>) criteria.setMaxResults(6).list();
        return products;
    }

    @Override
    public ArrayList<Product> retrievePopularProducts(Session session) {
        Criteria criteria = session.createCriteria(Product.class).addOrder(Order.desc("purchaseCount"));
        ArrayList<Product> products = (ArrayList<Product>) criteria.setMaxResults(3).list();
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
                    return true;
                } catch (HibernateException e) {
                    session.getTransaction().rollback();
                    return false;
                }
    }
    //Ashraf Part

    //Hadeer's Part
    @Override
    public boolean removeProductByID(Integer productId,Session session){
        Product product = session.load(Product.class,productId);
        try {
            session.delete(product);
            return true;

        }catch (HibernateException e){
            session.getTransaction().rollback();
            return false;
        }

    }

    //Islam's Part

    @Override
    public boolean updateProductPurchaseCount(Product soldProduct, Session session)
    {
        Product product = session.load(Product.class, soldProduct.getProductID());
        int purchaseCount = product.getPurchaseCount() + soldProduct.getPurchaseCount();
        product.setPurchaseCount(purchaseCount);
        try
        {
            session.update(product);
            return true;
        }
        catch (HibernateException e)
        {
            session.getTransaction().rollback();
            return false;
        }
    }
}
