package com.example.bimov.dagger2.model;

/**
 * Created by BimoV on 1/26/2017.
 */

public class Product {
    private long id;
    private String productName;
    private String description;
    private double salePrice;
    private int quantity;

    public Product() {

    }

    public Product(Product product) {
        this.id = product.getId();
        this.productName = product.getProductName();
        this.description = product.getDescription();
        this.salePrice = product.getSalePrice();
    }

    public Product(Product product,int quantity) {
        this.id = product.getId();
        this.productName = product.getProductName();
        this.description = product.getDescription();
        this.salePrice = product.getSalePrice();
        this.quantity = quantity;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public double getSalePrice() {
        return salePrice;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public void setSalePrice(double salePrice) {
        this.salePrice = salePrice;
    }

    public double getSumPrice() {
        return getSalePrice() * quantity;
    }

}
