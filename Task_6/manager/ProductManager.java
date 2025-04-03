package com.techshop.manager;

import com.techshop.entity.Product;
import com.techshop.exception.InvalidDataException;

import java.util.ArrayList;
import java.util.List;

public class ProductManager {
    private List<Product> products;

    public ProductManager() {
        this.products = new ArrayList<>();
    }

    public List<Product> getProducts() {
        return products;
    }

    public void addProduct(Product product) {
        if (product == null || product.getProductName().isEmpty() || product.getPrice() < 0) {
            throw new InvalidDataException("Invalid product details.");
        }
        
        for (Product p : products) {
            if (p.getProductID() == product.getProductID() || p.getProductName().equalsIgnoreCase(product.getProductName())) {
                throw new InvalidDataException("Duplicate product detected: " + product.getProductName());
            }
        }
        
        products.add(product);
        System.out.println("Product added: " + product.getProductName());
    }

    public void updateProduct(int productID, String newDescription, double newPrice) {
        for (Product p : products) {
            if (p.getProductID() == productID) {
                if (newPrice < 0) {
                    throw new InvalidDataException("Price cannot be negative.");
                }
                p.updateProductInfo(newDescription, newPrice);
                System.out.println("Product updated: " + p.getProductName());
                return;
            }
        }
        throw new InvalidDataException("Product not found.");
    }

    public void removeProduct(int productID) {
        Product toRemove = null;
        for (Product p : products) {
            if (p.getProductID() == productID) {
                toRemove = p;
                break;
            }
        }

        if (toRemove != null) {
            products.remove(toRemove);
            System.out.println("Product removed: " + toRemove.getProductName());
        } else {
            throw new InvalidDataException("Product not found.");
        }
    }

    public Product searchProductByName(String name) {
        for (Product p : products) {
            if (p.getProductName().equalsIgnoreCase(name)) {
                return p;
            }
        }
        throw new InvalidDataException("Product not found: " + name);
    }
}