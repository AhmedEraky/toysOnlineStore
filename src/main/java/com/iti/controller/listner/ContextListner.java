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
                creatcarsandplanesProduct(session,store1,category1);
                createDollsProduct(session,store3,category4);
                createpuzzlesProduct(session,store3,category5);
                createconstructionProducrts(session,store1,category3);

                return null;
            });
        } catch (Exception e) {
            e.printStackTrace();
        }
    }




    //Eraky Part
    private void createActionFiguresProduct(Session session,Store store,Category category) {
        Product product1=new Product();
        product1.setName("bighero");
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

        Product product2=new Product();
        product2.setName("fly");
        product2.setMinAge(3);
        product2.setPurchaseCount(0);
        product2.setDiscountPercentage(10);
        product2.setPrice(200d);
        product2.setImagePath("images/products/actionfigures/01/fly.jpg");
        product2.setQuantity(30);
        product2.setDescription("Good Eraky Product");
        product2.setStore(store);
        product2.setCategory(category);
        productDao.persistProduct(product2,session);


        Product product3=new Product();
        product3.setName("jerry");
        product3.setMinAge(3);
        product3.setPurchaseCount(0);
        product3.setDiscountPercentage(10);
        product3.setPrice(200d);
        product3.setImagePath("images/products/actionfigures/01/jerry.jpg");
        product3.setQuantity(30);
        product3.setDescription("Good Eraky Product");
        product3.setStore(store);
        product3.setCategory(category);
        productDao.persistProduct(product3,session);

        Product product4=new Product();
        product4.setName("mickey");
        product4.setMinAge(3);
        product4.setPurchaseCount(0);
        product4.setDiscountPercentage(10);
        product4.setPrice(200d);
        product4.setImagePath("images/products/actionfigures/01/mickey.jpg");
        product4.setQuantity(30);
        product4.setDescription("Good Eraky Product");
        product4.setStore(store);
        product4.setCategory(category);
        productDao.persistProduct(product4,session);



        Product product5=new Product();
        product5.setName("minnie");
        product5.setMinAge(3);
        product5.setPurchaseCount(0);
        product5.setDiscountPercentage(10);
        product5.setPrice(200d);
        product5.setImagePath("images/products/actionfigures/01/minnie.jpg");
        product5.setQuantity(30);
        product5.setDescription("Good Eraky Product");
        product5.setStore(store);
        product5.setCategory(category);
        productDao.persistProduct(product5,session);



        Product product6=new Product();
        product6.setName("minnie");
        product6.setMinAge(3);
        product6.setPurchaseCount(0);
        product6.setDiscountPercentage(10);
        product6.setPrice(200d);
        product6.setImagePath("images/products/actionfigures/01/minnie.jpg");
        product6.setQuantity(30);
        product6.setDescription("Good Eraky Product");
        product6.setStore(store);
        product6.setCategory(category);
        productDao.persistProduct(product6,session);


        Product product7=new Product();
        product7.setName("mr");
        product7.setMinAge(3);
        product7.setPurchaseCount(0);
        product7.setDiscountPercentage(10);
        product7.setPrice(200d);
        product7.setImagePath("images/products/actionfigures/01/mr.jpg");
        product7.setQuantity(30);
        product7.setDescription("Good Eraky Product");
        product7.setStore(store);
        product7.setCategory(category);
        productDao.persistProduct(product7,session);



        Product product8=new Product();
        product8.setName("spiderman");
        product8.setMinAge(3);
        product8.setPurchaseCount(0);
        product8.setDiscountPercentage(10);
        product8.setPrice(200d);
        product8.setImagePath("images/products/actionfigures/01/spiderman.jpg");
        product8.setQuantity(30);
        product8.setDescription("Good Eraky Product");
        product8.setStore(store);
        product8.setCategory(category);
        productDao.persistProduct(product8,session);


        Product product9=new Product();
        product9.setName("trans");
        product9.setMinAge(3);
        product9.setPurchaseCount(0);
        product9.setDiscountPercentage(10);
        product9.setPrice(200d);
        product9.setImagePath("images/products/actionfigures/01/trans.jpg");
        product9.setQuantity(30);
        product9.setDescription("Good Eraky Product");
        product9.setStore(store);
        product9.setCategory(category);
        productDao.persistProduct(product9,session);


        Product product10=new Product();
        product10.setName("super");
        product10.setMinAge(3);
        product10.setPurchaseCount(0);
        product10.setDiscountPercentage(10);
        product10.setPrice(200d);
        product10.setImagePath("images/products/actionfigures/24/super.jpg");
        product10.setQuantity(30);
        product10.setDescription("Good Eraky Product");
        product10.setStore(store);
        product10.setCategory(category);
        productDao.persistProduct(product10,session);


        Product product11=new Product();
        product11.setName("bat");
        product11.setMinAge(3);
        product11.setPurchaseCount(0);
        product11.setDiscountPercentage(10);
        product11.setPrice(200d);
        product11.setImagePath("images/products/actionfigures/24/bat.jpg");
        product11.setQuantity(30);
        product11.setDescription("Good Eraky Product");
        product11.setStore(store);
        product11.setCategory(category);
        productDao.persistProduct(product11,session);


        Product product12=new Product();
        product12.setName("batman");
        product12.setMinAge(3);
        product12.setPurchaseCount(0);
        product12.setDiscountPercentage(10);
        product12.setPrice(200d);
        product12.setImagePath("images/products/actionfigures/24/batman.jpg");
        product12.setQuantity(30);
        product12.setDescription("Good Eraky Product");
        product12.setStore(store);
        product12.setCategory(category);
        productDao.persistProduct(product12,session);


        Product product13=new Product();
        product13.setName("harry");
        product13.setMinAge(3);
        product13.setPurchaseCount(0);
        product13.setDiscountPercentage(10);
        product13.setPrice(200d);
        product13.setImagePath("images/products/actionfigures/24/harry.jpg");
        product13.setQuantity(30);
        product13.setDescription("Good Eraky Product");
        product13.setStore(store);
        product13.setCategory(category);
        productDao.persistProduct(product13,session);


        Product product14=new Product();
        product14.setName("dino");
        product14.setMinAge(3);
        product14.setPurchaseCount(0);
        product14.setDiscountPercentage(10);
        product14.setPrice(200d);
        product14.setImagePath("images/products/actionfigures/24/dino.jpg");
        product14.setQuantity(30);
        product14.setDescription("Good Eraky Product");
        product14.setStore(store);
        product14.setCategory(category);
        productDao.persistProduct(product14,session);


        Product product15=new Product();
        product15.setName("toy");
        product15.setMinAge(3);
        product15.setPurchaseCount(0);
        product15.setDiscountPercentage(10);
        product15.setPrice(200d);
        product15.setImagePath("images/products/actionfigures/24/toy.jpg");
        product15.setQuantity(30);
        product15.setDescription("Good Eraky Product");
        product15.setStore(store);
        product15.setCategory(category);
        productDao.persistProduct(product15,session);






    }


    private void creatcarsandplanesProduct(Session session, Store store, Category category) {
        ProductDao productDao=new ProductDaoImplementation();

        Product product3=new Product();
        product3.setName("bus");
        product3.setMinAge(3);
        product3.setPurchaseCount(0);
        product3.setDiscountPercentage(10);
        product3.setPrice(200d);
        product3.setImagePath("images/products/carsandplanes/01/bus.jpg");
        product3.setQuantity(30);
        product3.setDescription("Good Eraky Product");
        product3.setStore(store);
        product3.setCategory(category);
        productDao.persistProduct(product3,session);


        Product product4=new Product();
        product4.setName("Car");
        product4.setMinAge(3);
        product4.setPurchaseCount(0);
        product4.setDiscountPercentage(10);
        product4.setPrice(200d);
        product4.setImagePath("images/products/carsandplanes/01/Car.jpg");
        product4.setQuantity(30);
        product4.setDescription("Good Eraky Product");
        product4.setStore(store);
        product4.setCategory(category);
        productDao.persistProduct(product4,session);


        Product product5=new Product();
        product5.setName("cycle");
        product5.setMinAge(3);
        product5.setPurchaseCount(0);
        product5.setDiscountPercentage(10);
        product5.setPrice(200d);
        product5.setImagePath("images/products/carsandplanes/01/cycle.jpg");
        product5.setQuantity(30);
        product5.setDescription("Good Eraky Product");
        product5.setStore(store);
        product5.setCategory(category);
        productDao.persistProduct(product5,session);

        Product product6=new Product();
        product6.setName("Dragon");
        product6.setMinAge(3);
        product6.setPurchaseCount(0);
        product6.setDiscountPercentage(10);
        product6.setPrice(200d);
        product6.setImagePath("images/products/carsandplanes/01/Dragon.jpg");
        product6.setQuantity(30);
        product6.setDescription("Good Eraky Product");
        product6.setStore(store);
        product6.setCategory(category);
        productDao.persistProduct(product6,session);


        Product product7=new Product();
        product7.setName("FarmCar");
        product7.setMinAge(3);
        product7.setPurchaseCount(0);
        product7.setDiscountPercentage(10);
        product7.setPrice(200d);
        product7.setImagePath("images/products/carsandplanes/01/FarmCar.jpg");
        product7.setQuantity(30);
        product7.setDescription("Good Eraky Product");
        product7.setStore(store);
        product7.setCategory(category);
        productDao.persistProduct(product7,session);


        Product product8=new Product();
        product8.setName("Jeep");
        product8.setMinAge(3);
        product8.setPurchaseCount(0);
        product8.setDiscountPercentage(10);
        product8.setPrice(200d);
        product8.setImagePath("images/products/carsandplanes/01/Jeep.jpg");
        product8.setQuantity(30);
        product8.setDescription("Good Eraky Product");
        product8.setStore(store);
        product8.setCategory(category);
        productDao.persistProduct(product8,session);


        Product product9=new Product();
        product9.setName("motoCar");
        product9.setMinAge(3);
        product9.setPurchaseCount(0);
        product9.setDiscountPercentage(10);
        product9.setPrice(200d);
        product9.setImagePath("images/products/carsandplanes/01/motoCar.jpg");
        product9.setQuantity(30);
        product9.setDescription("Good Eraky Product");
        product9.setStore(store);
        product9.setCategory(category);
        productDao.persistProduct(product9,session);

        Product product10=new Product();
        product10.setName("MotoCycle");
        product10.setMinAge(3);
        product10.setPurchaseCount(0);
        product10.setDiscountPercentage(10);
        product10.setPrice(200d);
        product10.setImagePath("images/products/carsandplanes/01/MotoCycle.jpg");
        product10.setQuantity(30);
        product10.setDescription("Good Eraky Product");
        product10.setStore(store);
        product10.setCategory(category);
        productDao.persistProduct(product10,session);


    }

    private void createconstructionProducrts(Session session, Store store, Category category) {
        ProductDao productDao=new ProductDaoImplementation();

        Product product3=new Product();
        product3.setName("construction1");
        product3.setMinAge(3);
        product3.setPurchaseCount(0);
        product3.setDiscountPercentage(10);
        product3.setPrice(200d);
        product3.setImagePath("images/products/construction/01/construction1.jpg");
        product3.setQuantity(30);
        product3.setDescription("Good Eraky Product");
        product3.setStore(store);
        product3.setCategory(category);
        productDao.persistProduct(product3,session);


        Product product4=new Product();
        product4.setName("construction2");
        product4.setMinAge(3);
        product4.setPurchaseCount(0);
        product4.setDiscountPercentage(10);
        product4.setPrice(200d);
        product4.setImagePath("images/products/construction/01/construction2.jpg");
        product4.setQuantity(30);
        product4.setDescription("Good Eraky Product");
        product4.setStore(store);
        product4.setCategory(category);
        productDao.persistProduct(product4,session);


        Product product5=new Product();
        product5.setName("construction3");
        product5.setMinAge(3);
        product5.setPurchaseCount(0);
        product5.setDiscountPercentage(10);
        product5.setPrice(200d);
        product5.setImagePath("images/products/construction/01/construction3.jpg");
        product5.setQuantity(30);
        product5.setDescription("Good Eraky Product");
        product5.setStore(store);
        product5.setCategory(category);
        productDao.persistProduct(product5,session);

        Product product6=new Product();
        product6.setName("construction4");
        product6.setMinAge(3);
        product6.setPurchaseCount(0);
        product6.setDiscountPercentage(10);
        product6.setPrice(200d);
        product6.setImagePath("images/products/construction/01/construction4.jpg");
        product6.setQuantity(30);
        product6.setDescription("Good Eraky Product");
        product6.setStore(store);
        product6.setCategory(category);
        productDao.persistProduct(product6,session);

        

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