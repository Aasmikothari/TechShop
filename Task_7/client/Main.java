package com.techshop.client;

import com.techshop.dao.*;
import com.techshop.entity.*;
import com.techshop.entity.DatabaseConnection;
import java.util.*;
import java.sql.Connection;
import java.sql.SQLException;

public class Main {
    private static Scanner scanner = new Scanner(System.in);
    private static CustomerDAO customerDAO = new CustomerDAOImpl();
    private static ProductDAO productDAO = new ProductDAOImpl();
    private static OrderDAO orderDAO = new OrderDAOImpl();
    private static InventoryDAO inventoryDAO = new InventoryDAOImpl();
    private static SalesReportDAO salesReportDAO = new SalesReportDAOImpl();
    private static ProductSearchDAO productSearchDAO = new ProductSearchDAOImpl();

    public static void main(String[] args) {
        while (true) {
            System.out.println("===== TechShop Main Menu =====");
            System.out.println("1. Customer Management");
            System.out.println("2. Product Management");
            System.out.println("3. Order Processing");
            System.out.println("4. Inventory Management");
            System.out.println("5. Sales Reporting");
            System.out.println("6. Exit");
            System.out.print("Enter your choice: ");
            int choice = scanner.nextInt();
            scanner.nextLine();

            switch (choice) {
                case 1 -> manageCustomers();
                case 2 -> manageProducts();
                case 3 -> processOrders();
                case 4 -> manageInventory();
                case 5 -> viewSalesReports();
                case 6 -> {
                    System.out.println("Exiting TechShop... Goodbye!");
                    return;
                }
                default -> System.out.println("Invalid choice! Try again.");
            }
        }
    }

    private static void manageCustomers() {
        System.out.println("===== Customer Management =====");
        System.out.println("1. Register");
        System.out.println("2. Update Information");
        System.out.println("3. Change Password");
        System.out.println("4. Delete Account");
        System.out.print("Enter your choice: ");
        int choice = scanner.nextInt();
        scanner.nextLine();

        switch (choice) {
            case 1 -> registerCustomer();
            case 2 -> updateCustomerInfo();
            case 3 -> changePassword();
            case 4 -> deleteCustomerAccount();
            default -> System.out.println("Invalid choice!");
        }
    }

    private static void manageProducts() {
        System.out.println("===== Product Management =====");
        System.out.println("1. Add Product");
        System.out.println("2. View All Products");
        System.out.println("3. Update Product");
        System.out.println("4. Delete Product");
        System.out.println("5. Search Product by Name");
        System.out.println("6. Search Product by Category");
        System.out.println("7. Get Recommendations");
        System.out.print("Enter your choice: ");
        
        int choice = scanner.nextInt();
        scanner.nextLine(); // consume newline

        switch (choice) {
            case 1 -> addProduct();
            case 2 -> viewAllProducts();
            case 3 -> updateProduct();
            case 4 -> deleteProduct();
            case 5 -> searchProductByName();
            case 6 -> searchProductByCategory();
            case 7 -> getRecommendations();
            default -> System.out.println("Invalid choice!");
        }
    }


    private static void processOrders() {
        System.out.println("===== Order Processing =====");
        System.out.println("1. View Order History");
        System.out.print("Enter your choice: ");
        int choice = scanner.nextInt();
        scanner.nextLine();

        switch (choice) {
            case 1 -> viewOrderHistory();
            default -> System.out.println("Invalid choice!");
        }
    }

    private static void manageInventory() {
        System.out.println("===== Inventory Management =====");
        System.out.println("1. Update Stock");
        System.out.println("2. Check Low Stock Products");
        System.out.print("Enter your choice: ");
        int choice = scanner.nextInt();
        scanner.nextLine();

        switch (choice) {
            case 1 -> updateStock();
            case 2 -> checkLowStock();
            default -> System.out.println("Invalid choice!");
        }
    }

    private static void viewSalesReports() {
        System.out.println("===== Sales Reports =====");
        System.out.println("1. View Total Sales Revenue");
        System.out.println("2. View Top-Selling Products");
        System.out.print("Enter your choice: ");
        int choice = scanner.nextInt();
        scanner.nextLine();

        switch (choice) {
            case 1 -> viewTotalSalesRevenue();
            case 2 -> viewTopSellingProducts();
            default -> System.out.println("Invalid choice!");
        }
    }

    private static void registerCustomer() {
    	System.out.print("Enter first name: ");
    	String firstName = scanner.nextLine();
    	System.out.print("Enter last name: ");
    	String lastName = scanner.nextLine();
    	System.out.print("Enter email: ");
    	String email = scanner.nextLine();
    	System.out.print("Enter phone: ");
    	String phone = scanner.nextLine();
    	System.out.print("Enter address: ");
    	String address = scanner.nextLine();
    	Customer customer = new Customer(0, firstName, lastName, email, phone, address);
        customerDAO.registerCustomer(customer);
        System.out.println("Registration successful!");
    }

    private static void updateCustomerInfo() {
        System.out.print("Enter customer ID: ");
        int customerId = scanner.nextInt();
        scanner.nextLine(); // consume newline

        System.out.print("Enter updated first name: ");
        String firstName = scanner.nextLine();
        System.out.print("Enter updated last name: ");
        String lastName = scanner.nextLine();
        System.out.print("Enter updated email: ");
        String email = scanner.nextLine();
        System.out.print("Enter updated phone: ");
        String phone = scanner.nextLine();
        System.out.print("Enter updated address: ");
        String address = scanner.nextLine();

        Customer updatedCustomer = new Customer(customerId, firstName, lastName, email, phone, address);
        customerDAO.updateCustomer(updatedCustomer);
    }

    private static void changePassword() {
        System.out.print("Enter Customer ID: ");
        int customerId = scanner.nextInt();
        scanner.nextLine();
        System.out.print("Enter new password: ");
        String password = scanner.nextLine();
        customerDAO.changePassword(customerId, password);
    }

    private static void deleteCustomerAccount() {
        System.out.print("Enter Customer ID: ");
        int customerId = scanner.nextInt();
        scanner.nextLine();
        customerDAO.deleteCustomer(customerId);
        System.out.println("Account deleted.");
    }

    private static void searchProductByName() {
        System.out.print("Enter product name: ");
        String name = scanner.nextLine();
        List<Product> products = productSearchDAO.searchProductsByName(name);
        for (Product p : products) {
            System.out.println(p);
        }
    }

    private static void searchProductByCategory() {
        System.out.print("Enter product category: ");
        String category = scanner.nextLine();
        List<Product> products = productSearchDAO.searchProductsByCategory(category);
        for (Product p : products) {
            System.out.println(p);
        }
    }

    private static void getRecommendations() {
        System.out.print("Enter Customer ID: ");
        int customerId = scanner.nextInt();
        scanner.nextLine();
        List<Product> recommendations = productSearchDAO.getRecommendedProducts(customerId);
        for (Product p : recommendations) {
            System.out.println(p);
        }
    }

    private static void viewOrderHistory() {
        System.out.print("Enter Customer ID: ");
        int customerId = scanner.nextInt();
        scanner.nextLine();

        List<Order> orders = orderDAO.viewOrderHistory(customerId);
        
        if (orders.isEmpty()) {
            System.out.println("No order history found.");
        } else {
            for (Order order : orders) {
                System.out.println("Order ID: " + order.getOrderID() + ", Date: " + order.getOrderDate() + ", Total: $" + order.getTotalAmount());
            }
        }
    }

    private static void updateStock() {
        System.out.print("Enter Product ID: ");
        int productId = scanner.nextInt();
        scanner.nextLine();
        System.out.print("Enter new stock quantity: ");
        int quantity = scanner.nextInt();
        scanner.nextLine();
        inventoryDAO.updateStock(productId, quantity);
        System.out.println("Stock updated.");
    }

    private static void checkLowStock() {
        List<Product> lowStockProducts = inventoryDAO.getLowStockProducts();
        for (Product p : lowStockProducts) {
            System.out.println(p);
        }
    }

    private static void viewTotalSalesRevenue() {
        double revenue = salesReportDAO.getTotalSalesRevenue();
        System.out.println("Total Sales Revenue: $" + revenue);
    }

    private static void viewTopSellingProducts() {
        System.out.print("Enter number of top products to display: ");
        int count = scanner.nextInt();
        scanner.nextLine();
        List<Product> topProducts = salesReportDAO.getTopSellingProducts(count);
        for (Product p : topProducts) {
            System.out.println(p);
        }
    }
    
    private static void addProduct() {
        System.out.print("Enter product ID: ");
        int productId = scanner.nextInt();
        scanner.nextLine();
        System.out.print("Enter product name: ");
        String name = scanner.nextLine();
        System.out.print("Enter category: ");
        String category = scanner.nextLine();
        System.out.print("Enter price: ");
        double price = scanner.nextDouble();
        scanner.nextLine();
        System.out.print("Enter description: ");
        String description = scanner.nextLine();
        System.out.print("Enter stock quantity: ");
        int stockQuantity = scanner.nextInt();
        scanner.nextLine();

        Product product = new Product(productId, name, price, category, description, stockQuantity);
        productDAO.addProduct(product);
    }

    
    private static void viewAllProducts() {
        List<Product> products = productDAO.getAllProducts();
        if (products.isEmpty()) {
            System.out.println("No products available.");
        } else {
            for (Product product : products) {
                System.out.println(product);
            }
        }
    }
    
    private static void updateProduct() {
        System.out.print("Enter Product ID to update: ");
        int productId = scanner.nextInt();
        scanner.nextLine();

        System.out.print("Enter new product name: ");
        String name = scanner.nextLine();
        System.out.print("Enter new category: ");
        String category = scanner.nextLine();
        System.out.print("Enter new price: ");
        double price = scanner.nextDouble();
        scanner.nextLine(); 

        System.out.print("Enter new description: ");
        String description = scanner.nextLine();
        System.out.print("Enter new stock quantity: ");
        int stockQuantity = scanner.nextInt();
        scanner.nextLine();

        Product updatedProduct = new Product(productId, name, price, category, description, stockQuantity);
        productDAO.updateProduct(updatedProduct);
    }

    
    private static void deleteProduct() {
        System.out.print("Enter Product ID to delete: ");
        int productId = scanner.nextInt();
        scanner.nextLine();
        productDAO.deleteProduct(productId);
        System.out.println("Product deleted successfully.");
    }
}
