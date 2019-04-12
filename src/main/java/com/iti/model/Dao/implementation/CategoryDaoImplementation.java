package com.iti.model.Dao.implementation;

import com.iti.model.Dao.CategoryDao;
import com.iti.model.entity.Category;
import com.iti.model.entity.Product;
import org.hibernate.Session;

import java.util.ArrayList;
import org.hibernate.Criteria;
import org.hibernate.HibernateException;
import org.hibernate.criterion.Example;
import org.hibernate.criterion.Restrictions;

public class CategoryDaoImplementation implements CategoryDao {
    @Override
    public Category retriveCategoryByID(Integer ID, Session session) {
        session.beginTransaction();
        Criteria criteria = session.createCriteria(Category.class).add(Restrictions.idEq(ID));
        session.getTransaction().commit();
        session.clear();
        return (Category) criteria.uniqueResult();
        
    }

    @Override
    public Category retriveCategoryByExample(Category category, Session session) {
        session.beginTransaction();
        Example categoryExample = Example.create(category);
        Criteria criteria = session.createCriteria(Category.class).add(categoryExample);
        session.getTransaction().commit();
        session.clear();
        return (Category) criteria.uniqueResult();
    }

    @Override
    public ArrayList<Category> retriveCategoriesByExample(Category category, Session session) {
        session.beginTransaction();
        Example categoryExample = Example.create(category);
        Criteria criteria = session.createCriteria(Category.class).add(categoryExample);
        session.getTransaction().commit();
        session.clear();
        return (ArrayList<Category>) criteria.list();
        
    }

    @Override
    public ArrayList<Category> retriveCategories(Session session) {
        session.beginTransaction();
        Criteria criteria = session.createCriteria(Category.class);
        session.getTransaction().commit();
        session.clear();
        return (ArrayList<Category>) criteria.list();
    }

    @Override
    public Category retriveCategoryByProduct(Product product, Session session) {
        ///
        session.beginTransaction();
         Criteria criteria= session.createCriteria(Category.class).createCriteria("products")
                .add(Restrictions.idEq(product.getCategory().getCategoryID()));
         Category category=(Category) criteria.uniqueResult();
        session.getTransaction().commit();
        session.clear();
        return category;
    }

    @Override
    public boolean persistCategory(Category category, Session session) {
         session.beginTransaction();
        try{
            session.persist(category);
            session.getTransaction().commit();
            session.clear();
            return true;
        }catch(HibernateException e){
            return false;
        }
    }

    @Override
    public Category retriveCategoryByName(String name, Session session) {
        session.beginTransaction();
        Criteria criteria = session.createCriteria(Category.class).add(Restrictions.eq("name",name));
        Category category=(Category)criteria.uniqueResult();
        session.getTransaction().commit();
       //session.clear();
        return category;
    }


}
