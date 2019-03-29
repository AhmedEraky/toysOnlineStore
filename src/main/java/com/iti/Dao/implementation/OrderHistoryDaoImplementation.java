package com.iti.Dao.implementation;

import com.iti.Dao.OrderHistoryDao;
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
        session.getTransaction().commit();
        session.clear();
        return orderHistory;
    }

    @Override
    public OrderHistory retriveOrderHistoryByExample(OrderHistory orderHistory, Session session) {
        session.beginTransaction();
        Example orderHistoryExample = Example.create(orderHistory);
        Criteria criteria = session.createCriteria(OrderHistory.class).add(orderHistoryExample);
        session.getTransaction().commit();
        session.clear();
        return (OrderHistory) criteria.uniqueResult();
    }

    @Override
    public OrderHistory retriveOrderHistoryByUser(User user, Session session) {
        session.beginTransaction();
        Criteria criteria = session.createCriteria(OrderHistory.class).add(Restrictions.eq("user", user));
        session.getTransaction().commit();
        session.clear();
        return (OrderHistory) criteria.uniqueResult();
    }

    @Override
    public boolean persistOrederHistory(OrderHistory orderHistory, Session session) {
        session.beginTransaction();
        try{
            session.persist(orderHistory);
            session.getTransaction().commit();
            session.clear();
            return true;
        }catch(HibernateException e){
            session.getTransaction().rollback();
            session.clear();
            return false;
        }
    }
}
