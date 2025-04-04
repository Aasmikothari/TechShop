package com.techshop.client;

import com.techshop.dao.SalesReportDAOImpl;
import com.techshop.entity.Order;
import com.techshop.entity.Product;

import java.util.*;

public class Main5 {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        SalesReportDAOImpl salesReportDAO = new SalesReportDAOImpl();

        while (true) {
            System.out.println("\n===== TechShop Sales Report Menu =====");
            System.out.println("1. View Total Sales Revenue");
            System.out.println("2. View Monthly Sales Trends");
            System.out.println("3. View Top-Selling Products");
            System.out.println("4. View Orders Within a Date Range");
            System.out.println("5. Exit");
            System.out.print("Choose an option: ");

            int choice = scanner.nextInt();
            scanner.nextLine(); // Consume newline

            switch (choice) {
                case 1:
                    displayTotalSalesRevenue(salesReportDAO);
                    break;
                case 2:
                    displaySalesTrends(salesReportDAO);
                    break;
                case 3:
                    displayTopSellingProducts(salesReportDAO, scanner);
                    break;
                case 4:
                    displayOrdersWithinDateRange(salesReportDAO, scanner);
                    break;
                case 5:
                    System.out.println("Exiting Sales Report Module...");
                    return;
                default:
                    System.out.println("Invalid choice. Please try again.");
            }
        }
    }

    private static void displayTotalSalesRevenue(SalesReportDAOImpl salesReportDAO) {
        double totalRevenue = salesReportDAO.getTotalSalesRevenue();
        System.out.printf("Total Sales Revenue: $%.2f\n", totalRevenue);
    }

    private static void displaySalesTrends(SalesReportDAOImpl salesReportDAO) {
        Map<String, Double> salesTrends = salesReportDAO.getSalesTrends();
        System.out.println("\n===== Monthly Sales Trends =====");
        for (Map.Entry<String, Double> entry : salesTrends.entrySet()) {
            System.out.printf("Month: %s | Revenue: $%.2f\n", entry.getKey(), entry.getValue());
        }
    }

    private static void displayTopSellingProducts(SalesReportDAOImpl salesReportDAO, Scanner scanner) {
        System.out.print("Enter the number of top-selling products to display: ");
        int limit = scanner.nextInt();
        scanner.nextLine(); // Consume newline
        
        List<Product> topProducts = salesReportDAO.getTopSellingProducts(limit);
        System.out.println("\n===== Top-Selling Products =====");
        for (Product product : topProducts) {
            System.out.printf("Product ID: %d | Name: %s | Price: $%.2f | Stock: %d\n",
                product.getProductID(), product.getProductName(), product.getPrice(), product.getStockQuantity());
        }
    }

    private static void displayOrdersWithinDateRange(SalesReportDAOImpl salesReportDAO, Scanner scanner) {
        try {
            System.out.print("Enter start date (yyyy-MM-dd): ");
            String startDateStr = scanner.nextLine();
            System.out.print("Enter end date (yyyy-MM-dd): ");
            String endDateStr = scanner.nextLine();

            Date startDate = java.sql.Date.valueOf(startDateStr);
            Date endDate = java.sql.Date.valueOf(endDateStr);

            List<Order> orders = salesReportDAO.getOrdersWithinDateRange(startDate, endDate);
            System.out.println("\n===== Orders Within Date Range =====");
            for (Order order : orders) {
                System.out.printf("Order ID: %d | Customer: %s | Date: %s | Total: $%.2f\n",
                    order.getOrderID(), order.getCustomer(), order.getOrderDate(), order.getTotalAmount());
            }
        } catch (Exception e) {
            System.out.println("Invalid date format. Please enter dates in yyyy-MM-dd format.");
        }
    }
}
