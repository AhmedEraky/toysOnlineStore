package com.iti.model.Dao;

import com.iti.model.entity.OrderHistory;
import com.iti.model.entity.User;
import org.hibernate.Session;

public interface OrderHistoryDao {

    OrderHistory retriveOrderHistoryByID(Integer id, Session session);
    OrderHistory retriveOrderHistoryByExample(OrderHistory orderHistory, Session session);
    OrderHistory retriveOrderHistoryByUser(User user, Session session);
    boolean persistOrederHistory(OrderHistory orderHistory, Session session);
}

