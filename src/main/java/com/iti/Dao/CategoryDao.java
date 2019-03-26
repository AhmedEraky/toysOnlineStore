package com.iti.Dao;

import com.iti.model.entity.Category;
import com.iti.model.entity.Product;
import org.hibernate.Session;

import java.util.ArrayList;

public interface CategoryDao {
    Category retriveCartItemByID(Integer ID, Session session);
    Category retriveCategoryByExample(Category category, Session session);
    ArrayList<Category> retriveCategoriesByExample(Category category, Session session);
    ArrayList<Category> retriveCategories(Session session);
    Category retriveCategoryByProduct(Product product, Session session);
    boolean persistCategory(Category category, Session session);
}
