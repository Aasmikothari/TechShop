package com.techshop.entity;
import java.util.*;

public class Inventory {
    private int inventoryID;
    private Product product;
    private int quantityInStock;
    private Date lastStockUpdate;

    public Inventory(int inventoryID, Product product, int quantityInStock) {
        this.inventoryID = inventoryID;
        this.product = product;
        this.quantityInStock = quantityInStock;
        this.lastStockUpdate = new Date();
    }
    
    public int getInventoryID() { return inventoryID; }
    public Product getProduct() { return product; }
    public int getQuantityInStock() { return quantityInStock; }
    public Date getLastStockUpdate() { return lastStockUpdate; }

    public void setQuantityInStock(int quantityInStock) {
        if (quantityInStock >= 0) {
            this.quantityInStock = quantityInStock;
            this.lastStockUpdate = new Date();
        } else {
            throw new IllegalArgumentException("Stock quantity cannot be negative");
        }
    }

    public void addToInventory(int quantity) {
        this.quantityInStock += quantity;
        this.lastStockUpdate = new Date();
    }

    public void removeFromInventory(int quantity) {
        if (quantity <= quantityInStock) {
            this.quantityInStock -= quantity;
            this.lastStockUpdate = new Date();
        } else {
            System.out.println("Not enough stock available!");
        }
    }

    public void updateStockQuantity(int newQuantity) {
        this.quantityInStock = newQuantity;
        this.lastStockUpdate = new Date();
    }

    public boolean isProductAvailable(int quantityToCheck) {
        return quantityInStock >= quantityToCheck;
    }

    public double getInventoryValue() {
        return product.getPrice() * quantityInStock;
    }

    public void listLowStockProducts(int threshold) {
    }

    public void listOutOfStockProducts() {
        
    }

}
