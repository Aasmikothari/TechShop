package com.techshop.client;

import com.techshop.dao.InventoryDAOImpl;
import com.techshop.entity.Product;
import java.util.List;
import java.util.Scanner;

public class Main3 {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        InventoryDAOImpl inventoryDAO = new InventoryDAOImpl();

        while (true) {
            System.out.println("\nTechShop - Inventory Management");
            System.out.println("1. Add Product");
            System.out.println("2. Update Stock");
            System.out.println("3. Remove Product");
            System.out.println("4. View All Products");
            System.out.println("5. Search Product by ID");
            System.out.println("6. Exit");
            System.out.print("Choose an option: ");
            int choice = scanner.nextInt();
            scanner.nextLine(); 

            switch (choice) {
                case 1:
                    System.out.println("\nEnter Product Details:");
                    
                    System.out.print("Product ID: ");
                    int productID = scanner.nextInt();
                    scanner.nextLine(); // Consume newline

                    System.out.print("Name: ");
                    String name = scanner.nextLine();

                    System.out.print("Price: ");
                    double price = scanner.nextDouble();
                    scanner.nextLine(); // Consume newline

                    System.out.print("Category: ");
                    String category = scanner.nextLine();

                    System.out.print("Description: ");
                    String description = scanner.nextLine();

                    System.out.print("Stock Quantity: ");
                    int stock = scanner.nextInt();

                    Product newProduct = new Product(productID, name, price, category, description, stock);
                    inventoryDAO.addProduct(newProduct);
                    break;

                case 2:
                    System.out.print("\nEnter Product ID to Update Stock: ");
                    int updateID = scanner.nextInt();
                    System.out.print("New Stock Quantity: ");
                    int newStock = scanner.nextInt();
                    inventoryDAO.updateStock(updateID, newStock);
                    break;

                case 3:
                    System.out.print("\nEnter Product ID to Remove: ");
                    int removeID = scanner.nextInt();
                    inventoryDAO.removeProduct(removeID);
                    break;

                case 4:
                    System.out.println("\nProduct List:");
                    List<Product> products = inventoryDAO.getAllProducts();
                    for (Product product : products) {
                        System.out.println(product);
                    }
                    break;

                case 5:
                    System.out.print("\nEnter Product ID to Search: ");
                    int searchID = scanner.nextInt();
                    Product foundProduct = inventoryDAO.getProductByID(searchID);
                    System.out.println(foundProduct != null ? foundProduct : "Product not found.");
                    break;

                case 6:
                    System.out.println("Exiting...");
                    return;

                default:
                    System.out.println("Invalid choice.");
            }
        }
    }
}
