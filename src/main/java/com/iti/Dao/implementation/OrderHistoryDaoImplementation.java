package com.iti.Dao.implementation;

import com.iti.Dao.OrderHistoryDao;
import com.iti.model.entity.OrderHistory;
import com.iti.model.entity.User;
import org.hibernate.Session;

public class OrderHistoryDaoImplementation implements OrderHistoryDao {
    @Override
    public OrderHistory retriveOrderHistoryByID(Integer id, Session session) {
        return null;
    }

    @Override
    public OrderHistory retriveOrderHistoryByExample(OrderHistory orderHistory, Session session) {
        return null;
    }

    @Override
    public OrderHistory retriveOrderHistoryByUser(User user, Session session) {
        return null;
    }

    @Override
    public boolean persistOrederHistory(OrderHistory orderHistory, Session session) {
        return false;
    }
}
