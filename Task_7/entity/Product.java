package com.techshop.entity;
import java.util.*;

public class Product {
    private int productID;
    private String productName;
    private String description;
    private double price;
    private String category;
    private int stockQuantity;

    public Product(int productID, String productName, double price, String category, String description, int stockQuantity) {
        this.productID = productID;
        this.productName = productName;
        this.price = price;
        this.category = category;
        this.description = description;
        this.stockQuantity = stockQuantity;
    }
    
    public int getProductID() { return productID; }
    public String getProductName() { return productName; }
    public double getPrice() { return price; }
    public String getCategory() { return category; }
    public String getDescription() { return description; }
    public int getStockQuantity() { return stockQuantity; }
    
    public void setProductName(String productName) { this.productName = productName; }
    public void setCategory(String category) { this.category = category; }
    public void setDescription(String description) { this.description = description; }
    public void setStockQuantity(int stockQuantity) { this.stockQuantity = stockQuantity; }


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
    
    public String toString() {
        return "Product ID: " + productID +
                ", Name: " + productName +
                ", Price: $" + price +
                ", Category: " + category +
                ", Description: " + description +
                ", Stock: " + stockQuantity;
    }
}