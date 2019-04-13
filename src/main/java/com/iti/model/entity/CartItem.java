package com.iti.model.entity;
// Generated Mar 23, 2019 1:04:18 PM by Hibernate Tools 4.3.1


import java.math.BigDecimal;
import java.util.HashSet;
import java.util.Set;
import javax.persistence.*;

import static javax.persistence.GenerationType.IDENTITY;

/**
 * CartItemDao generated by hbm2java
 */
@Entity
@Table(name="cart_item",catalog="toysonlineshoppingdb")
public class CartItem implements java.io.Serializable {

    @Id
    @GeneratedValue(strategy=IDENTITY)
    @Column(name="item_id", unique=true, nullable=false)
    private Integer itemID;
    @Column(name="quantity", nullable=false)
    private Integer quantity;
    @Column(name="total_cost", nullable=false, precision=6, scale=3)
    private double totalCost;

    @OneToOne(fetch=FetchType.EAGER,optional = false)
    private Product products;
    @ManyToMany(fetch=FetchType.LAZY, mappedBy="shoppingCartItems")
    private Set<ShoppingCart> shoppingCarts = new HashSet<>(0);

    public CartItem() {
    }
    public CartItem(Product products, int quantity, double totalcost) {
        this.products = products;
        this.quantity = quantity;
        this.totalCost = totalcost;
    }
    public CartItem(Product products, int quantity, double totalcost, Set shoppingcarts) {
        this.products = products;
        this.quantity = quantity;
        this.totalCost = totalcost;
        this.shoppingCarts = shoppingcarts;
    }
    public Integer getItemID() {
        return this.itemID;
    }
    public void setItemID(Integer itemID) {
        this.itemID = itemID;
    }
    public Product getProducts() {
        return this.products;
    }
    public void setProducts(Product products) {
        this.products = products;
    }
    public int getQuantity() {
        return this.quantity;
    }
    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }
    public double getTotalCost() {
        return totalCost;
    }
    public void setTotalCost(double totalCost) {
        this.totalCost = totalCost;
    }
    public Set getShoppingCarts() {
        return this.shoppingCarts;
    }
    public void setShoppingCarts(Set<ShoppingCart> shoppingCarts) {
        this.shoppingCarts = shoppingCarts;
    }
}


