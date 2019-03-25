package com.iti.model.entity;
// Generated Mar 23, 2019 1:04:18 PM by Hibernate Tools 4.3.1


import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

import static javax.persistence.GenerationType.IDENTITY;

/**
 * CategoryDao generated by hbm2java
 */
@Entity
@Table(name="category",catalog="toysonlineshoppingdb")
public class Category  implements java.io.Serializable {

    @Id
    @GeneratedValue(strategy=IDENTITY)
    @Column(name="category_id", unique=true, nullable=false)
    private Integer categoryID;
    @Column(name="name", nullable=false, length=50)
    private String name;
    @OneToMany(fetch=FetchType.LAZY, mappedBy="category")
    private Set<Product> products = new HashSet(0);
    @ManyToMany(fetch=FetchType.LAZY)
    @JoinTable(name="user_interest_category", catalog="toysonlineshoppingdb",
            joinColumns = {@JoinColumn(name="user_id", nullable=false, updatable=false) },
            inverseJoinColumns = {@JoinColumn(name="category_id", nullable=false, updatable=false) })
    private Set<User> userInterestCategory = new HashSet(0);


    public Category() {
    }
    public Category(String name) {
        this.name = name;
    }
    public Category(String name, Set products) {
        this.name = name;
        this.products = products;
    }

    public Integer getCategoryID() {
        return categoryID;
    }

    public void setCategoryID(Integer categoryID) {
        this.categoryID = categoryID;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Set<Product> getProducts() {
        return products;
    }

    public void setProducts(Set<Product> products) {
        this.products = products;
    }
}


