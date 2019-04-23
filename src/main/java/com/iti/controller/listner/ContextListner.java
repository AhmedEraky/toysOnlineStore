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