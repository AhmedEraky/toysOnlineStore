package com.iti.controller.listner;

import com.iti.model.Dao.AdminDao;
import com.iti.model.Dao.CategoryDao;
import com.iti.model.Dao.implementation.AdminDaoImplementation;
import com.iti.model.Dao.implementation.CategoryDaoImplementation;
import com.iti.model.cfg.HibernateUtils;
import com.iti.model.cfg.transaction.TransactionManager;
import com.iti.model.entity.Admin;
import com.iti.model.entity.Category;
import org.hibernate.Session;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

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

                return null;
            });
        } catch (Exception e) {
            e.printStackTrace();
        }
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
