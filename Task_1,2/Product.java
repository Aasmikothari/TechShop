package com.techshop.entity;
import java.util.Date;
import java.util.ArrayList;
class Product {
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
        System.out.println("Product ID: " + productID);
        System.out.println("Name: " + productName);
        System.out.println("Description: " + description);
        System.out.println("Price: $" + price);
    }

    public void updateProductInfo(double price, String description) {
        this.price = price;
        this.description = description;
        System.out.println("Product information updated.");
    }
}