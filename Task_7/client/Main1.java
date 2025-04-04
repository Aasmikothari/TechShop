package com.techshop.client;

import com.techshop.dao.ProductDAOImpl;
import com.techshop.entity.Product;
import java.util.List;
import java.util.Scanner;

public class Main1 {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        ProductDAOImpl productDAO = new ProductDAOImpl();

        while (true) {
            System.out.println("\nTechShop - Product Management");
            System.out.println("1. Add Product");
            System.out.println("2. View All Products");
            System.out.println("3. Search Product by ID");
            System.out.println("4. Update Product");
            System.out.println("5. Delete Product");
            System.out.println("6. Exit");
            System.out.print("Choose an option: ");

            int choice = scanner.nextInt();
            scanner.nextLine(); 

            switch (choice) {
                case 1:
                    System.out.print("Enter Product ID: ");
                    int id = scanner.nextInt();
                    scanner.nextLine();
                    System.out.print("Enter Product Name: ");
                    String name = scanner.nextLine();
                    System.out.print("Enter Price: ");
                    double price = scanner.nextDouble();
                    scanner.nextLine();
                    System.out.print("Enter Category: ");
                    String category = scanner.nextLine();
                    System.out.print("Enter Description: ");
                    String description = scanner.nextLine();
                    System.out.print("Enter Stock Quantity: ");
                    int stock = scanner.nextInt();

                    Product newProduct = new Product(id, name, price, category, description, stock);
                    productDAO.addProduct(newProduct);
                    break;

                case 2:
                    List<Product> products = productDAO.getAllProducts();
                    System.out.println("\nAvailable Products:");
                    for (Product p : products) {
                        System.out.println(p);
                    }
                    break;

                case 3:
                    System.out.print("Enter Product ID: ");
                    int searchID = scanner.nextInt();
                    Product product = productDAO.getProductById(searchID);
                    System.out.println(product != null ? product : "Product not found.");
                    break;

                case 4:
                    System.out.print("Enter Product ID to update: ");
                    int updateID = scanner.nextInt();
                    scanner.nextLine();
                    System.out.print("Enter New Product Name: ");
                    String newName = scanner.nextLine();
                    System.out.print("Enter New Price: ");
                    double newPrice = scanner.nextDouble();
                    scanner.nextLine();
                    System.out.print("Enter New Category: ");
                    String newCategory = scanner.nextLine();
                    System.out.print("Enter New Description: ");
                    String newDescription = scanner.nextLine();
                    System.out.print("Enter New Stock Quantity: ");
                    int newStock = scanner.nextInt();

                    Product updatedProduct = new Product(updateID, newName, newPrice, newCategory, newDescription, newStock);
                    productDAO.updateProduct(updatedProduct);
                    break;

                case 5:
                    System.out.print("Enter Product ID to delete: ");
                    int deleteID = scanner.nextInt();
                    productDAO.deleteProduct(deleteID);
                    break;

                case 6:
                    System.out.println("Exiting...");
                    scanner.close();
                    return;

                default:
                    System.out.println("Invalid choice. Try again.");
            }
        }
    }
}