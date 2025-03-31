package com.techshop.entity;
import java.util.Date;
import java.util.ArrayList;
class Inventory {
    private int inventoryID;
    private Product product;
    private int quantityInStock;

    public Inventory(int inventoryID, Product product, int quantityInStock) {
        this.inventoryID = inventoryID;
        this.product = product;
        this.quantityInStock = quantityInStock;
    }

    public Product getProduct() {
        return product;
    }

    public int getQuantityInStock() {
        return quantityInStock;
    }

    public void addToInventory(int quantity) {
        this.quantityInStock += quantity;
    }

    public void removeFromInventory(int quantity) {
        if (quantity <= quantityInStock) {
            this.quantityInStock -= quantity;
        } else {
            System.out.println("Not enough stock available.");
        }
    }
}