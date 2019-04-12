package com.iti.model.Dao.implementation;

import com.iti.model.Dao.OrderHistoryDao;
import com.iti.model.entity.OrderHistory;
import com.iti.model.entity.User;
import org.hibernate.Criteria;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.criterion.Example;
import org.hibernate.criterion.Restrictions;

public class OrderHistoryDaoImplementation implements OrderHistoryDao {
    @Override
    public OrderHistory retriveOrderHistoryByID(Integer id, Session session) {
        session.beginTransaction();
        OrderHistory orderHistory = session.get(OrderHistory.class, id);
        session.clear();
        session.getTransaction().commit();
        return orderHistory;
    }

    @Override
    public OrderHistory retriveOrderHistoryByExample(OrderHistory orderHistory, Session session) {
        session.beginTransaction();
        Example orderHistoryExample = Example.create(orderHistory);
        Criteria criteria = session.createCriteria(OrderHistory.class).add(orderHistoryExample);
        OrderHistory retrievedOrderHistory = (OrderHistory) criteria.uniqueResult();
        session.clear();
        session.getTransaction().commit();
        return retrievedOrderHistory;
    }

    @Override
    public OrderHistory retriveOrderHistoryByUser(User user, Session session) {
        session.beginTransaction();
        Criteria criteria = session.createCriteria(OrderHistory.class).add(Restrictions.eq("user", user));
        OrderHistory orderHistory = (OrderHistory) criteria.uniqueResult();
        session.clear();
        session.getTransaction().commit();
        return orderHistory;
    }

    @Override
    public boolean persistOrederHistory(OrderHistory orderHistory, Session session) {
        session.beginTransaction();
        try{
            session.persist(orderHistory);
            session.clear();
            session.getTransaction().commit();
            return true;
        }catch(HibernateException e){
            session.clear();
            session.getTransaction().rollback();
            return false;
        }
    }
}
