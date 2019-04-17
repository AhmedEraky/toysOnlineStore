package com.iti.model.Dao;

import com.iti.model.entity.Category;
import com.iti.model.entity.Product;
import org.hibernate.Session;

import java.util.ArrayList;

public interface CategoryDao {
    Category retriveCategoryByID(Integer ID, Session session);
    Category retriveCategoryByExample(Category category, Session session);
    ArrayList<Category> retriveCategoriesByExample(Category category, Session session);
    ArrayList<Category> retriveCategories(Session session);
    Category retriveCategoryByProduct(Product product, Session session);
    boolean persistCategory(Category category, Session session);

    //Aya
    Category retriveCategoryByName(String name, Session session);
}
