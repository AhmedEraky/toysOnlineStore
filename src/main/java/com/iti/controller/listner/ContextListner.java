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
        ProductDao productDao=new ProductDaoImplementation();
        Product product1Dolls=new Product();
        product1Dolls.setName("Accessory Set");
        product1Dolls.setMinAge(3);
        product1Dolls.setPurchaseCount(0);
        product1Dolls.setDiscountPercentage(20);
        product1Dolls.setPrice(100d);
        product1Dolls.setImagePath("images/products/dolls/01/acc.jpg");
        product1Dolls.setQuantity(10);
        product1Dolls.setDescription("Dolls Product");
        product1Dolls.setStore(store3);
        product1Dolls.setCategory(category2);
        productDao.persistProduct(product1Dolls,session);
        /**product**/

        Product product2Dolls=new Product();
        product2Dolls.setName("Baby");
        product2Dolls.setMinAge(1);
        product2Dolls.setPurchaseCount(0);
        product2Dolls.setDiscountPercentage(10);
        product2Dolls.setPrice(100d);
        product2Dolls.setImagePath("images/products/dolls/01/baby.jpg");
        product2Dolls.setQuantity(10);
        product2Dolls.setDescription("Dolls Product");
        product2Dolls.setStore(store3);
        product2Dolls.setCategory(category2);
        productDao.persistProduct(product2Dolls,session);
        /** product**/
        Product product3Dolls=new Product();
        product3Dolls.setName("Bear");
        product3Dolls.setMinAge(2);
        product3Dolls.setPurchaseCount(0);
        product3Dolls.setDiscountPercentage(10);
        product3Dolls.setPrice(100d);
        product3Dolls.setImagePath("images/products/dolls/01/bear.jpg");
        product3Dolls.setQuantity(6);
        product3Dolls.setDescription("Dolls Product");
        product3Dolls.setStore(store3);
        product3Dolls.setCategory(category2);
        productDao.persistProduct(product3Dolls,session);
        /**product**/
        Product product4Dolls=new Product();
        product4Dolls.setName("Dog");
        product4Dolls.setMinAge(2);
        product4Dolls.setPurchaseCount(0);
        product4Dolls.setDiscountPercentage(10);
        product4Dolls.setPrice(100d);
        product4Dolls.setImagePath("images/products/dolls/01/dog.jpg");
        product4Dolls.setQuantity(6);
        product4Dolls.setDescription("Dolls Product");
        product4Dolls.setStore(store3);
        product4Dolls.setCategory(category2);
        productDao.persistProduct(product4Dolls,session);
        /**product**/
        Product product5Dolls=new Product();
        product5Dolls.setName("Home");
        product5Dolls.setMinAge(2);
        product5Dolls.setPurchaseCount(0);
        product5Dolls.setDiscountPercentage(10);
        product5Dolls.setPrice(500d);
        product5Dolls.setImagePath("images/products/dolls/01/home.jpg");
        product5Dolls.setQuantity(5);
        product5Dolls.setDescription("Dolls Product");
        product5Dolls.setStore(store3);
        product5Dolls.setCategory(category2);
        productDao.persistProduct(product5Dolls,session);
    }


    //Islam Part
    private void createpuzzlesProduct(Session session, Store store3, Category category2) {
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