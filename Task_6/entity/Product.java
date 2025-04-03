package com.techshop.entity;
import java.util.*;

public class Product {
    private int productID;
    private String productName;
    private String description;
    private double price;

    public Product(int productID, String productName, double price) {
        this.productID = productID;
        this.productName = productName;
        this.price = price;
    }
    
    public int getProductID() { return productID; }
    public String getProductName() { return productName; }
    public String getDescription() { return description; }
    public double getPrice() { return price; }

    public void setPrice(double price) {
        if (price >= 0) {
            this.price = price;
        } else {
            throw new IllegalArgumentException("Price cannot be negative");
        }
    }

    public void getProductDetails() {
        System.out.println("Product ID: " + productID + ", Name: " + productName);
    }

    public void updateProductInfo(String description, double price) {
        this.description = description;
        this.price = price;
    }


    public boolean isProductInStock(int stock) {
        return stock > 0;
    }
}