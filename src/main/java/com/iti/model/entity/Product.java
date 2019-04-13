package com.iti.model.entity;
// Generated Mar 23, 2019 1:04:18 PM by Hibernate Tools 4.3.1


import java.util.HashSet;
import java.util.Set;
import javax.persistence.*;

import static javax.persistence.GenerationType.IDENTITY;

/**
 * Product generated by hbm2java
 */
@Entity
@Table(name="product",catalog="toysonlineshoppingdb")
public class Product implements java.io.Serializable {

    @Id
    @GeneratedValue(strategy=IDENTITY)
    @Column(name="product_id", unique=true, nullable=false)
    private Integer ProductID;
    @Column(name="name", nullable=false, length=50)
    private String name;
    @Column(name="description", nullable=false, length=500)
    private String description;
    @Column(name="image_path", nullable=false, length=50)
    private String imagePath;
    @Column(name="price", nullable=false, precision=3, scale=3)
    private Double price;
    @Column(name="min_age", nullable=false)
    private Integer minAge;
    @Column(name = "discount_percentage",nullable = false,columnDefinition = "int default 0")
    private Integer discountPercentage;
    @Column(name = "quantity", nullable = false)
    private int quantity;
    @Column(name = "purchase_count",nullable = false,columnDefinition = "int default 0")
    private int purchaseCount;

    @ManyToOne
    @JoinColumn(name="category_id", nullable=false)
    private Category category;

    @OneToOne(mappedBy = "products")
    private CartItem cartItems;

    @ManyToOne(cascade = {CascadeType.MERGE,CascadeType.PERSIST,CascadeType.DETACH})
    private Store store;

    @ManyToMany(fetch=FetchType.LAZY)
    @JoinTable(name="order_history_products", catalog="toysonlineshoppingdb",
            joinColumns = {@JoinColumn(name="products_id", nullable=false, updatable=false) },
            inverseJoinColumns = {@JoinColumn(name="order_history_id", nullable=false, updatable=false) })
    private Set<OrderHistory> orderHistories = new HashSet(0);

    @ManyToMany(fetch=FetchType.LAZY)
    @JoinTable(name="user_products", catalog="toysonlineshoppingdb", joinColumns = {
            @JoinColumn(name="products_id", nullable=false, updatable=false) }, inverseJoinColumns = {
            @JoinColumn(name="user_id", nullable=false, updatable=false) })
    private Set<User> userProducts = new HashSet(0);


    @ManyToMany(fetch=FetchType.LAZY)
    @JoinTable(name="user_wishes", catalog="toysonlineshoppingdb",
            joinColumns = {@JoinColumn(name="user_id", nullable=false, updatable=false) },
            inverseJoinColumns = {@JoinColumn(name="products_id", nullable=false, updatable=false) })
    private Set<User> userWishes = new HashSet(0);

    @OneToMany(fetch=FetchType.LAZY, mappedBy="products")
    private Set<Review> reviews = new HashSet(0);

    public Product() {
    }

    public Integer getProductID() {
        return ProductID;
    }

    public void setProductID(Integer productID) {
        ProductID = productID;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getImagePath() {
        return imagePath;
    }

    public void setImagePath(String imagePath) {
        this.imagePath = imagePath;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public Integer getMinAge() {
        return minAge;
    }

    public void setMinAge(Integer minAge) {
        this.minAge = minAge;
    }

    public Integer getDiscountPercentage() {
        return discountPercentage;
    }

    public void setDiscountPercentage(Integer discountPercentage) {
        this.discountPercentage = discountPercentage;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public int getPurchaseCount() {
        return purchaseCount;
    }

    public void setPurchaseCount(int purchaseCount) {
        this.purchaseCount = purchaseCount;
    }

    public Category getCategory() {
        return category;
    }

    public void setCategory(Category category) {
        this.category = category;
    }

    public CartItem getCartItems() {
        return cartItems;
    }

    public void setCartItems(CartItem cartItems) {
        this.cartItems = cartItems;
    }

    public Store getStore() {
        return store;
    }

    public void setStore(Store store) {
        this.store = store;
    }

    public Set<OrderHistory> getOrderHistories() {
        return orderHistories;
    }

    public void setOrderHistories(Set<OrderHistory> orderHistories) {
        this.orderHistories = orderHistories;
    }

    public Set<User> getUserProducts() {
        return userProducts;
    }

    public void setUserProducts(Set<User> userProducts) {
        this.userProducts = userProducts;
    }

    public Set<User> getUserWishes() {
        return userWishes;
    }

    public void setUserWishes(Set<User> userWishes) {
        this.userWishes = userWishes;
    }

    public Set<Review> getReviews() {
        return reviews;
    }

    public void setReviews(Set<Review> reviews) {
        this.reviews = reviews;
    }
}


