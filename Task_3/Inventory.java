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
        setQuantityInStock(quantityInStock);
    }

    public int getInventoryID() { return inventoryID; }
    public Product getProduct() { return product; }
    public int getQuantityInStock() { return quantityInStock; }

    public void setQuantityInStock(int quantityInStock) {
        if (quantityInStock >= 0) {
            this.quantityInStock = quantityInStock;
        } else {
            System.out.println("Stock quantity cannot be negative.");
        }
    }
}
