package com.techshop.client;

import com.techshop.entity.*;
import com.techshop.exception.*;

public class Main2 {
    public static void main(String[] args) {
        Product laptop = new Product(101, "Laptop", "Gaming Laptop", 1200.99);
        Inventory inventory = new Inventory(301, laptop, 10);  // Stock: 10

        System.out.println("Initial stock: " + inventory.getQuantityInStock());

        try {
            // Removing valid stock
            inventory.removeFromInventory(5);
            System.out.println("Stock after removing 5: " + inventory.getQuantityInStock());

            // Attempting to remove more than available
            inventory.removeFromInventory(6);
        } catch (InsufficientStockException e) {
            System.out.println(e.getMessage());
        }

        try {
            // Setting negative stock
            inventory.setQuantityInStock(-5);
        } catch (InvalidDataException e) {
            System.out.println(e.getMessage());
        }

        try {
            // Trying to remove zero or negative quantity
            inventory.removeFromInventory(0);
        } catch (InvalidDataException e) {
            System.out.println(e.getMessage());
        }
    }
}
