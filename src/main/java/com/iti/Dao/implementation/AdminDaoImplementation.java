package com.iti.Dao.implementation;

import com.iti.Dao.AdminDao;
import com.iti.model.entity.Admin;
import org.hibernate.Session;

public class AdminDaoImplementation implements AdminDao {
    @Override
    public boolean persistAdmin(Admin admin, Session session) {
        return false;
    }

    @Override
    public Admin retiveAdminByEmail(String email, Session session) {
        return null;
    }

    @Override
    public Admin retriveAdminByExample(Admin admin, Session session) {
        return null;
    }

    @Override
    public boolean isAdmin(Admin admin, Session session) {
        return false;
    }
}
