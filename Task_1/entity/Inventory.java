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

    public boolean isProductAvailable(int quantityToCheck) {
        return quantityInStock >= quantityToCheck;
    }
}