package com.iti.Dao.implementation;

import com.iti.Dao.CategoryDao;
import com.iti.model.entity.Category;
import com.iti.model.entity.Product;
import org.hibernate.Session;

import java.util.ArrayList;

public class CategoryDaoImplementation implements CategoryDao {
    @Override
    public Category retriveCartItemByID(Integer ID, Session session) {
        return null;
    }

    @Override
    public Category retriveCategoryByExample(Category category, Session session) {
        return null;
    }

    @Override
    public ArrayList<Category> retriveCategoriesByExample(Category category, Session session) {
        return null;
    }

    @Override
    public ArrayList<Category> retriveCategories(Session session) {
        return null;
    }

    @Override
    public Category retriveCategoryByProduct(Product product, Session session) {
        return null;
    }

    @Override
    public boolean persistCategory(Category category, Session session) {
        return false;
    }
}
