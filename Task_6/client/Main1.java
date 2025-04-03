package com.techshop.client;

import com.techshop.entity.Product;
import com.techshop.manager.InventoryManager;

public class Main1 {
    public static void main(String[] args) {
        InventoryManager inventoryManager = new InventoryManager();

        Product laptop = new Product(101, "Laptop", 750.99);
        Product phone = new Product(102, "Smartphone", 499.49);
        Product tablet = new Product(103, "Tablet", 299.99);

        inventoryManager.addInventory(laptop, 10);
        inventoryManager.addInventory(phone, 15);
        inventoryManager.addInventory(tablet, 5);

        inventoryManager.displayInventory();

        System.out.println("\nSearching for Product ID 102:");
        System.out.println(inventoryManager.searchProductByID(102));

        System.out.println("\nSearching for 'Tablet':");
        System.out.println(inventoryManager.searchProductByName("Tablet"));

        inventoryManager.updateInventory(101, 20);
        System.out.println("\nAfter updating stock of Laptop:");
        inventoryManager.displayInventory();

        inventoryManager.removeInventory(103);
        System.out.println("\nAfter removing Tablet from inventory:");
        inventoryManager.displayInventory();
    }
}
