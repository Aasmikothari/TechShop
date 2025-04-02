package com.techshop.entity;
import java.util.*;
import com.techshop.exception.InsufficientStockException;
import com.techshop.exception.InvalidDataException;

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
    	if (quantityInStock < 0) {
            throw new InvalidDataException("Stock quantity cannot be negative.");
        }
        this.quantityInStock = quantityInStock;
        this.lastStockUpdate = new Date();
    }

    public void addToInventory(int quantity) {
    	if (quantity < 0) {
            throw new InvalidDataException("Cannot add a negative quantity to inventory.");
        }
        this.quantityInStock += quantity;
        this.lastStockUpdate = new Date();
    }

    public void removeFromInventory(int quantity) {
    	if (quantity <= 0) {
            throw new InvalidDataException("Quantity to remove must be greater than zero.");
        }
        if (quantity > quantityInStock) {
            throw new InsufficientStockException("Not enough stock available for product: " + product.getProductName());
        }
        this.quantityInStock -= quantity;
        this.lastStockUpdate = new Date();
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
