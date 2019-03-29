package com.iti.model.Dao;

import com.iti.model.entity.Admin;
import org.hibernate.Session;

public interface AdminDao {

    boolean persistAdmin(Admin admin, Session session);
    Admin retrieveAdminByEmail(String email, Session session);
    boolean isAdmin(Admin admin, Session session);
}
