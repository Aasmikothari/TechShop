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
    
    public int getProductID() { return productID; }
    public String getProductName() { return productName; }
    public String getDescription() { return description; }
    public double getPrice() { return price; }

    public void setPrice(double price) {
        if (price >= 0) {
            this.price = price;
        } else {
            System.out.println("Price cannot be negative.");
        }
    }

    public void getProductDetails() {
        System.out.println("Product ID: " + productID);
        System.out.println("Name: " + productName);
        System.out.println("Description: " + description);
        System.out.println("Price: $" + price);
    }
}