package com.iti.Dao;

import com.iti.model.entity.Admin;

import java.util.ArrayList;

public interface AdminDao {

    public void  persistAdmin(Admin admin);
    public Admin retiveAdminByID(String email);
}
