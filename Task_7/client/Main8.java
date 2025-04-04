package com.techshop.client;

import com.techshop.dao.ProductSearchDAOImpl;
import com.techshop.entity.Product;

import java.util.List;
import java.util.Scanner;

public class Main8 {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        ProductSearchDAOImpl productSearchDAO = new ProductSearchDAOImpl();

        while (true) {
            System.out.println("===== Product Search & Recommendations =====");
            System.out.println("1. Search Product by Name");
            System.out.println("2. Search Product by Category");
            System.out.println("3. Get Recommended Products");
            System.out.println("4. Exit");
            System.out.print("Enter your choice: ");
            int choice = scanner.nextInt();
            scanner.nextLine(); // Consume newline

            switch (choice) {
                case 1:
                    System.out.print("Enter product name: ");
                    String name = scanner.nextLine();
                    List<Product> nameResults = productSearchDAO.searchProductsByName(name);
                    displayProducts(nameResults);
                    break;

                case 2:
                    System.out.print("Enter product category: ");
                    String category = scanner.nextLine();
                    List<Product> categoryResults = productSearchDAO.searchProductsByCategory(category);
                    displayProducts(categoryResults);
                    break;

                case 3:
                    System.out.print("Enter customer ID for recommendations: ");
                    int customerID = scanner.nextInt();
                    List<Product> recommendedProducts = productSearchDAO.getRecommendedProducts(customerID);
                    displayProducts(recommendedProducts);
                    break;

                case 4:
                    System.out.println("Exiting...");
                    scanner.close();
                    return;

                default:
                    System.out.println("Invalid choice! Please try again.");
            }
        }
    }

    private static void displayProducts(List<Product> products) {
        if (products.isEmpty()) {
            System.out.println("No products found.");
        } else {
            System.out.println("===== Product List =====");
            for (Product product : products) {
                System.out.println("ID: " + product.getProductID() +
                        ", Name: " + product.getProductName() +
                        ", Price: $" + product.getPrice() +
                        ", Category: " + product.getCategory() +
                        ", Stock: " + product.getStockQuantity());
            }
        }
    }
}
