package com.iti.model.entity;
// Generated Mar 23, 2019 1:04:18 PM by Hibernate Tools 4.3.1


import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
@Entity
@Table(name="admin",catalog="toysonlineshoppingdb")
public class Admin  implements java.io.Serializable {
    @Id
    @Column(name="email", unique=true, nullable=false, length=50)
    private String email;

    @Column(name="password", nullable=false, length=40)
    private String password;

    public Admin() {
    }
    public Admin(String email, String password) {
        this.email = email;
        this.password = password;
    }
    public String getEmail() {
        return this.email;
    }
    public void setEmail(String email) {
        this.email = email;
    }
    public String getPassword() {
        return this.password;
    }
    public void setPassword(String password) {
        this.password = password;
    }




}


