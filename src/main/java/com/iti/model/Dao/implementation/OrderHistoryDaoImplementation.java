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
        OrderHistory orderHistory = session.get(OrderHistory.class, id);
        return orderHistory;
    }

    @Override
    public OrderHistory retriveOrderHistoryByExample(OrderHistory orderHistory, Session session) {
        Example orderHistoryExample = Example.create(orderHistory);
        Criteria criteria = session.createCriteria(OrderHistory.class).add(orderHistoryExample);
        OrderHistory retrievedOrderHistory = (OrderHistory) criteria.uniqueResult();
        return retrievedOrderHistory;
    }

    @Override
    public OrderHistory retriveOrderHistoryByUser(User user, Session session) {
        Criteria criteria = session.createCriteria(OrderHistory.class).add(Restrictions.eq("user", user));
        OrderHistory orderHistory = (OrderHistory) criteria.uniqueResult();
        return orderHistory;
    }

    @Override
    public boolean persistOrederHistory(OrderHistory orderHistory, Session session) {
            session.saveOrUpdate(orderHistory);
            return true;
    }
}
