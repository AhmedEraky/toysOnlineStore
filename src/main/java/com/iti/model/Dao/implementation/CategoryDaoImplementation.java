package com.iti.model.Dao.implementation;

import com.iti.model.Dao.CategoryDao;
import com.iti.model.entity.Category;
import com.iti.model.entity.Product;
import org.hibernate.Session;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.HibernateException;
import org.hibernate.criterion.Example;
import org.hibernate.criterion.Restrictions;

public class CategoryDaoImplementation implements CategoryDao {
    @Override
    public Category retriveCategoryByID(Integer ID, Session session) {
        session.beginTransaction();
        Criteria criteria = session.createCriteria(Category.class).add(Restrictions.idEq(ID));
        Category category = (Category) criteria.uniqueResult();
        session.clear();
        session.getTransaction().commit();
        return category;
        
    }

    @Override
    public Category retriveCategoryByExample(Category category, Session session) {
        session.beginTransaction();
        Example categoryExample = Example.create(category);
        Criteria criteria = session.createCriteria(Category.class).add(categoryExample);
        Category retrievedCategory = (Category) criteria.uniqueResult();
        session.clear();
        session.getTransaction().commit();
        return retrievedCategory;
    }

    @Override
    public ArrayList<Category> retriveCategoriesByExample(Category category, Session session) {
        session.beginTransaction();
        Example categoryExample = Example.create(category);
        Criteria criteria = session.createCriteria(Category.class).add(categoryExample);
        ArrayList categories = (ArrayList<Category>) criteria.list();
        session.clear();
        session.getTransaction().commit();
        return categories;
        
    }

    @Override
    public ArrayList<Category> retriveCategories(Session session) {
        session.beginTransaction();
        Criteria criteria = session.createCriteria(Category.class);
        ArrayList categories = (ArrayList<Category>) criteria.list();
        session.clear();
        session.getTransaction().commit();
        return categories;
    }

    @Override
    public Category retriveCategoryByProduct(Product product, Session session) {
        ///
        session.beginTransaction();
         Criteria criteria= session.createCriteria(Category.class).createCriteria("products")
                .add(Restrictions.idEq(product.getCategory().getCategoryID()));
         Category category=(Category) criteria.uniqueResult();
        session.clear();
        session.getTransaction().commit();
        return category;
    }

    @Override
    public boolean persistCategory(Category category, Session session) {
         session.beginTransaction();
        try{
            session.persist(category);
            session.clear();
            session.getTransaction().commit();
            return true;
        }catch(HibernateException e){
            session.clear();
            session.getTransaction().rollback();
            return false;
        }
    }

    @Override
    public Category retriveCategoryByName(String name, Session session) {
        session.beginTransaction();
        Criteria criteria = session.createCriteria(Category.class).add(Restrictions.eq("name",name));
        Category category=(Category)criteria.uniqueResult();
        session.clear();
        session.getTransaction().commit();
        return category;
    }


}
