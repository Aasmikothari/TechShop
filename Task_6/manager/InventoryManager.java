package com.techshop.manager;

import com.techshop.entity.Inventory;
import com.techshop.entity.Product;
import com.techshop.exception.InsufficientStockException;
import com.techshop.exception.InvalidDataException;
import java.util.*;
import java.util.stream.Collectors;

public class InventoryManager {
    private TreeMap<Integer, Inventory> inventoryMap;

    public InventoryManager() {
        this.inventoryMap = new TreeMap<>();
    }

    public void addInventory(Product product, int quantity) {
        if (product == null || quantity <= 0) {
            throw new InvalidDataException("Invalid product or quantity.");
        }

        if (inventoryMap.containsKey(product.getProductID())) {
            Inventory existingInventory = inventoryMap.get(product.getProductID());
            existingInventory.setStockQuantity(existingInventory.getStockQuantity() + quantity);
            System.out.println("Stock updated: " + product.getProductName() + " now has " + existingInventory.getStockQuantity() + " units.");
        } else {
            Inventory newInventory = new Inventory(product, quantity);
            inventoryMap.put(product.getProductID(), newInventory);
            System.out.println("New product added: " + product.getProductName() + " with stock " + quantity);
        }
    }

    public void updateInventory(int productID, int newQuantity) {
        Inventory inventory = inventoryMap.get(productID);
        if (inventory == null) {
            throw new InvalidDataException("Product not found in inventory.");
        }
        inventory.setStockQuantity(newQuantity);
        System.out.println("Inventory updated: " + inventory.getProduct().getProductName() + " (New Stock: " + newQuantity + ")");
    }

    public void removeInventory(int productID) {
        if (!inventoryMap.containsKey(productID)) {
            throw new InvalidDataException("Product not found in inventory.");
        }
        inventoryMap.remove(productID);
        System.out.println("Inventory removed for product ID: " + productID);
    }

    public Inventory getInventory(int productID) {
        return inventoryMap.get(productID);
    }

    public void displayInventory() {
        System.out.println("\nCurrent Inventory:");
        for (Inventory inventory : inventoryMap.values()) {
            System.out.println("Product: " + inventory.getProduct().getProductName() +
                    ", Stock: " + inventory.getStockQuantity());
        }
    }

    public void reduceStock(int productID, int quantity) {
        Inventory inventory = inventoryMap.get(productID);
        if (inventory == null) {
            throw new InvalidDataException("Product not found in inventory.");
        }
        if (inventory.getStockQuantity() < quantity) {
            throw new InsufficientStockException("Not enough stock for product: " + inventory.getProduct().getProductName());
        }
        inventory.setStockQuantity(inventory.getStockQuantity() - quantity);
        System.out.println("Stock reduced for " + inventory.getProduct().getProductName() + ". Remaining stock: " + inventory.getStockQuantity());
    }

    public void restockProduct(int productID, int quantity) {
        Inventory inventory = inventoryMap.get(productID);
        if (inventory == null) {
            throw new InvalidDataException("Product not found in inventory.");
        }
        inventory.setStockQuantity(inventory.getStockQuantity() + quantity);
        System.out.println("Stock increased for " + inventory.getProduct().getProductName() + ". New stock: " + inventory.getStockQuantity());
    }
    
    public Product searchProductByID(int productID) {
        Inventory inventory = inventoryMap.get(productID);
        if (inventory == null) {
            throw new InvalidDataException("Product not found.");
        }
        return inventory.getProduct();
    }
    
    public Product searchProductByName(String name) {
        for (Inventory inventory : inventoryMap.values()) {
            if (inventory.getProduct().getProductName().equalsIgnoreCase(name)) {
                return inventory.getProduct();
            }
        }
        throw new InvalidDataException("Product not found.");
    }
    
    public List<Product> sortProductsByName() {
        return inventoryMap.values().stream()
                .map(Inventory::getProduct)
                .sorted(Comparator.comparing(Product::getProductName))
                .collect(Collectors.toList());
    }
    
    public List<Product> sortProductsByPrice() {
        return inventoryMap.values().stream()
                .map(Inventory::getProduct)
                .sorted(Comparator.comparing(Product::getPrice))
                .collect(Collectors.toList());
    }

    // ✅ ADDED: Method to return the inventory list for OrderManager
    public List<Inventory> getInventoryList() {
        return new ArrayList<>(inventoryMap.values());
    }
}
