package com.techshop.entity;
import java.util.*;
public class Product {
    private int productID;
    private String productName;
    private String description;
    private double price;

    public Product(int productID, String productName, String description, double price) {
        this.productID = productID;
        this.productName = productName;
        this.description = description;
        this.price = price;
    }

    public void getProductDetails() {
        System.out.println("Product ID: " + productID + ", Name: " + productName);
    }

    public void updateProductInfo(String description, double price) {
        this.description = description;
        this.price = price;
    }
    public double getPrice() {
        return price;
    }
}