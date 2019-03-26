package com.iti.model.entity;
// Generated Mar 23, 2019 1:04:18 PM by Hibernate Tools 4.3.1
import java.math.BigDecimal;
import java.util.*;
import javax.persistence.*;

/**
 * User generated by hbm2java
 */
@Entity
@Table(name="user",catalog="toysonlineshoppingdb")
public class User  implements java.io.Serializable {

    @Id
    @Column(name="email", unique=true, nullable=false, length=50)
    private String email;

    @Column(name="name", nullable=false, length=50)
    private String name;

    @Column(name="password", nullable=false, length=40)
    private String password;

    @Column(name="birth_date", nullable=false, length=10)
    private Date birthDate;

    @Column(name="job", nullable=true, length=50)
    private String job;

    @Column(name="address", nullable=false, length=100)
    private String address;

    @Column(name="credit_limit", nullable=false)
    private Double creditLimit;

    @Column(name="image_path", nullable=true, length=50)
    private String imagePath;

    @OneToMany(fetch=FetchType.LAZY, mappedBy="user")
    private Set<Review> reviews = new HashSet(0);

    @OneToMany(fetch=FetchType.LAZY, mappedBy="user")
    private Set<OrderHistory> orderHistories = new HashSet(0);

    @OneToOne(cascade = CascadeType.ALL,optional = false)
    private ShoppingCart shoppingCart;

    @ManyToMany(fetch=FetchType.LAZY)
    @JoinTable(name="user_products", catalog="toysonlineshoppingdb",
            joinColumns = {@JoinColumn(name="user_id", nullable=false, updatable=false) },
            inverseJoinColumns = {@JoinColumn(name="products_id", nullable=false, updatable=false) })
    private Set<Product> UserProducts = new HashSet(0);

    @ManyToMany(fetch=FetchType.LAZY)
    @JoinTable(name="user_wishes", catalog="toysonlineshoppingdb",
            joinColumns = {@JoinColumn(name="user_id", nullable=false, updatable=false) },
            inverseJoinColumns = {@JoinColumn(name="products_id", nullable=false, updatable=false) })
    private Set<Product> UserWishes = new HashSet(0);



    @ManyToMany(fetch=FetchType.LAZY)
    @JoinTable(name="user_interest_category", catalog="toysonlineshoppingdb",
            joinColumns = {@JoinColumn(name="user_id", nullable=false, updatable=false) },
            inverseJoinColumns = {@JoinColumn(name="category_id", nullable=false, updatable=false) })
    private Set<Category> userInterestCategory = new HashSet(0);


    public User() {
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Date getBirthDate() {
        return birthDate;
    }

    public void setBirthDate(Date birthDate) {
        this.birthDate = birthDate;
    }

    public String getJob() {
        return job;
    }

    public void setJob(String job) {
        this.job = job;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public Double getCreditLimit() {
        return creditLimit;
    }

    public void setCreditLimit(Double creditLimit) {
        this.creditLimit = creditLimit;
    }

    public String getImagePath() {
        return imagePath;
    }

    public void setImagePath(String imagePath) {
        this.imagePath = imagePath;
    }

    public Set<Review> getReviews() {
        return reviews;
    }

    public void setReviews(Set<Review> reviews) {
        this.reviews = reviews;
    }

    public Set<OrderHistory> getOrderHistories() {
        return orderHistories;
    }

    public void setOrderHistories(Set<OrderHistory> orderHistories) {
        this.orderHistories = orderHistories;
    }

    public ShoppingCart getShoppingCart() {
        return shoppingCart;
    }

    public void setShoppingCart(ShoppingCart shoppingCart) {
        this.shoppingCart = shoppingCart;
    }

    public Set<Product> getUserProducts() {
        return UserProducts;
    }

    public void setUserProducts(Set<Product> userProducts) {
        UserProducts = userProducts;
    }

    public Set<Product> getUserWishes() {
        return UserWishes;
    }

    public void setUserWishes(Set<Product> userWishes) {
        UserWishes = userWishes;
    }

    public Set<Category> getUserInterestCategory() {
        return userInterestCategory;
    }

    public void setUserInterestCategory(Set<Category> userInterestCategory) {
        this.userInterestCategory = userInterestCategory;
    }
}


