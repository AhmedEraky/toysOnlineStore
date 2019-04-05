package com.iti.service.impl;

import com.iti.model.Dao.ProductDao;
import com.iti.model.Dao.implementation.ProductDaoImplementation;
import com.iti.model.cfg.HibernateUtils;
import com.iti.model.entity.Category;
import com.iti.model.entity.Product;
import com.iti.model.entity.Store;
import com.iti.service.UploadProductService;
import org.hibernate.Session;

public class UploadProductServiceImpl implements UploadProductService {

    @Override
    public void uploadProduct(Product product) {
        Session session= HibernateUtils.getSession();
        ProductDao productDao=new ProductDaoImplementation();
        product=new Product();
        product.setName("Eraky Product");
        product.setDescription("a good new Product");
        product.setDiscountPercentage(0);
        product.setImagePath("images\\a1.jpg");
        product.setMinAge(3);
        product.setPrice(10.0);
        product.setPurchaseCount(0);
        product.setQuantity(4);
        Store store=new Store();
        store.setName("Eraky Store");
        Category category=new Category();
        category.setName("Erakys");
        product.setCategory(category);
        product.setStore(store);
        productDao.persistProduct(product,session);
    }
}
