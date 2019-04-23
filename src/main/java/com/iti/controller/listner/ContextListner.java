package com.iti.controller.listner;

import com.iti.model.Dao.AdminDao;
import com.iti.model.Dao.CategoryDao;
import com.iti.model.Dao.ProductDao;
import com.iti.model.Dao.StoreDao;
import com.iti.model.Dao.implementation.AdminDaoImplementation;
import com.iti.model.Dao.implementation.CategoryDaoImplementation;
import com.iti.model.Dao.implementation.ProductDaoImplementation;
import com.iti.model.Dao.implementation.StoreDaoImplementation;
import com.iti.model.cfg.HibernateUtils;
import com.iti.model.cfg.transaction.TransactionManager;
import com.iti.model.entity.Admin;
import com.iti.model.entity.Category;
import com.iti.model.entity.Product;
import com.iti.model.entity.Store;
import org.hibernate.Session;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import java.util.List;

public class ContextListner implements ServletContextListener {
    int imageCount;

    @Override
    public void contextInitialized(ServletContextEvent sce) {
        HibernateUtils.getSessionFactory();
        imageCount=0;
        sce.getServletContext().setAttribute("imageCount",imageCount);

        TransactionManager transactionManager=new TransactionManager(HibernateUtils.getSessionFactory());
        try {
            transactionManager.runInTransaction(session -> {

                createAdmins(session);
                createCategories(session);
                createStores(session);


                StoreDao storeDao=new StoreDaoImplementation();
                Store store1=storeDao.retrieveStoreByName("Eraky Store",session);
                Store store2=storeDao.retrieveStoreByName("Aya Store",session);
                Store store3=storeDao.retrieveStoreByName("Islam Store",session);

                CategoryDao categoryDao=new CategoryDaoImplementation();
                Category category1=categoryDao.retriveCategoryByName("Action Figures",session);
                Category category2=categoryDao.retriveCategoryByName("Cars And Planes",session);
                Category category3=categoryDao.retriveCategoryByName("Construction",session);
                Category category4=categoryDao.retriveCategoryByName("Dolls",session);
                Category category5=categoryDao.retriveCategoryByName("Puzzles",session);


                createActionFiguresProduct(session,store1,category1);
                createActionFiguresProduct(session,store2,category3);
                createDollsProduct(session,store3,category2);
                createpuzzlesProduct(session,store3,category2);

                return null;
            });
        } catch (Exception e) {
            e.printStackTrace();
        }
    }




    //Eraky Part
    private void createActionFiguresProduct(Session session,Store store,Category category) {
        Product product1=new Product();
        product1.setName("Fly");
        product1.setMinAge(3);
        product1.setPurchaseCount(0);
        product1.setDiscountPercentage(10);
        product1.setPrice(200d);
        product1.setImagePath("images/products/actionfigures/01/bighero.jpg");
        product1.setQuantity(30);
        product1.setDescription("Good Eraky Product");
        product1.setStore(store);
        product1.setCategory(category);


        ProductDao productDao=new ProductDaoImplementation();
        productDao.persistProduct(product1,session);



    }


    //Aya Part

    private void createDollsProduct(Session session, Store store3, Category category2) {
    }


    //Islam Part
    private void createpuzzlesProduct(Session session, Store store3, Category category2)
    {
        ProductDao productDao=new ProductDaoImplementation();
        Product product1=new Product();
        product1.setName("Memory Puzzles");
        product1.setMinAge(3);
        product1.setPurchaseCount(0);
        product1.setDiscountPercentage(10);
        product1.setPrice(200d);
        product1.setImagePath("images/products/puzzles/34/79.jpg");
        product1.setQuantity(30);
        product1.setDescription("Puzzles Product");
        product1.setStore(store3);
        product1.setCategory(category2);
        productDao.persistProduct(product1,session);
        //Another Product
        Product product2=new Product();
        product2.setName("Early Puzzles");
        product2.setMinAge(3);
        product2.setPurchaseCount(0);
        product2.setDiscountPercentage(10);
        product2.setPrice(200d);
        product2.setImagePath("images/products/puzzles/34/49.jpg");
        product2.setQuantity(30);
        product2.setDescription("Puzzles Product");
        product2.setStore(store3);
        product2.setCategory(category2);
        productDao.persistProduct(product2,session);
        //Another Product
        Product product3=new Product();
        product3.setName("Jumping Monkey Puzzles");
        product3.setMinAge(3);
        product3.setPurchaseCount(0);
        product3.setDiscountPercentage(10);
        product3.setPrice(200d);
        product3.setImagePath("images/products/puzzles/34/99.jpg");
        product3.setQuantity(30);
        product3.setDescription("Puzzles Product");
        product3.setStore(store3);
        product3.setCategory(category2);
        productDao.persistProduct(product3,session);
        //Another Product
        Product product4=new Product();
        product4.setName("Cars Puzzles");
        product4.setMinAge(3);
        product4.setPurchaseCount(0);
        product4.setDiscountPercentage(10);
        product4.setPrice(200d);
        product4.setImagePath("images/products/puzzles/34/59.jpg");
        product4.setQuantity(30);
        product4.setDescription("Puzzles Product");
        product4.setStore(store3);
        product4.setCategory(category2);
        productDao.persistProduct(product4,session);
        //Another Product
        Product product5=new Product();
        product5.setName("Disney Puzzles");
        product5.setMinAge(3);
        product5.setPurchaseCount(0);
        product5.setDiscountPercentage(10);
        product5.setPrice(200d);
        product5.setImagePath("images/products/puzzles/34/159.jpg");
        product5.setQuantity(30);
        product5.setDescription("Puzzles Product");
        product5.setStore(store3);
        product5.setCategory(category2);
        productDao.persistProduct(product5,session);
        //Another Product
        Product product6=new Product();
        product6.setName("Jumpling Tower Puzzles");
        product6.setMinAge(3);
        product6.setPurchaseCount(0);
        product6.setDiscountPercentage(10);
        product6.setPrice(200d);
        product6.setImagePath("images/products/puzzles/34/199.jpg");
        product6.setQuantity(30);
        product6.setDescription("Puzzles Product");
        product6.setStore(store3);
        product6.setCategory(category2);
        productDao.persistProduct(product6,session);
        //Another Product
        Product product7=new Product();
        product7.setName("Paw Patrol Puzzles");
        product7.setMinAge(3);
        product7.setPurchaseCount(0);
        product7.setDiscountPercentage(10);
        product7.setPrice(200d);
        product7.setImagePath("images/products/puzzles/34/29.jpg");
        product7.setQuantity(30);
        product7.setDescription("Puzzles Product");
        product7.setStore(store3);
        product7.setCategory(category2);
        productDao.persistProduct(product7,session);
        //Another Product
        Product product8=new Product();
        product8.setName("Jumping Monkey Puzzles");
        product8.setMinAge(3);
        product8.setPurchaseCount(0);
        product8.setDiscountPercentage(10);
        product8.setPrice(200d);
        product8.setImagePath("images/products/puzzles/34/99.jpg");
        product8.setQuantity(30);
        product8.setDescription("Puzzles Product");
        product8.setStore(store3);
        product8.setCategory(category2);
        productDao.persistProduct(product8,session);
        //Another Product
        Product product9=new Product();
        product9.setName("Pig Puzzles");
        product9.setMinAge(3);
        product9.setPurchaseCount(0);
        product9.setDiscountPercentage(10);
        product9.setPrice(200d);
        product9.setImagePath("images/products/puzzles/34/119.jpg");
        product9.setQuantity(30);
        product9.setDescription("Puzzles Product");
        product9.setStore(store3);
        product9.setCategory(category2);
        productDao.persistProduct(product9,session);
        //Another Product
        Product product10=new Product();
        product10.setName("Cat Puzzles");
        product10.setMinAge(3);
        product10.setPurchaseCount(0);
        product10.setDiscountPercentage(10);
        product10.setPrice(200d);
        product10.setImagePath("images/products/puzzles/57/129.jpg");
        product10.setQuantity(30);
        product10.setDescription("Puzzles Product");
        product10.setStore(store3);
        product10.setCategory(category2);
        productDao.persistProduct(product10,session);
    }








    private void createStores(Session session) {
        Store store=new Store();
        store.setName("Eraky Store");
        StoreDao storeDao=new StoreDaoImplementation();

        Store store1=new Store();
        store1.setName("Aya Store");

        Store store2=new Store();
        store2.setName("Islam Store");

        storeDao.persistStore(store,session);
        storeDao.persistStore(store1,session);
        storeDao.persistStore(store2,session);

    }


    private void createCategories(Session session) {
        CategoryDao categoryDao=new CategoryDaoImplementation();
        Category category=new Category();
        category.setName("Action Figures");
        categoryDao.persistCategory(category,session);

        Category category4=new Category();
        category4.setName("Cars And Planes");
        categoryDao.persistCategory(category4,session);

        Category category1=new Category();
        category1.setName("Construction");
        categoryDao.persistCategory(category1,session);


        Category category2=new Category();
        category2.setName("Dolls");
        categoryDao.persistCategory(category2,session);

        Category category3=new Category();
        category3.setName("Puzzles");
        categoryDao.persistCategory(category3,session);
    }

    private void createAdmins(Session session) {
        AdminDao adminDao=new AdminDaoImplementation();

        Admin admin=new Admin();
        admin.setEmail("eraky@gmail.com");
        admin.setPassword("Ah111111");
        adminDao.persistAdmin(admin,session);


        Admin admin1=new Admin();
        admin1.setEmail("aya@gmail.com");
        admin1.setPassword("Ah111111");
        adminDao.persistAdmin(admin1,session);


        Admin admin2=new Admin();
        admin2.setEmail("islam@gmail.com");
        admin2.setPassword("Ah111111");
        adminDao.persistAdmin(admin2,session);



        Admin admin3=new Admin();
        admin3.setEmail("ashraf@gmail.com");
        admin3.setPassword("Ah111111");
        adminDao.persistAdmin(admin3,session);

        Admin admin4=new Admin();
        admin4.setEmail("hadeer@gmail.com");
        admin4.setPassword("Ah111111");
        adminDao.persistAdmin(admin4,session);

    }




    @Override
    public void contextDestroyed(ServletContextEvent sce) {

    }
}