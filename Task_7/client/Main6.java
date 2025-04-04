package com.techshop.client;

import com.techshop.dao.*;
import com.techshop.entity.*;

import java.util.List;
import java.util.Scanner;

public class Main6 {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        CustomerDAO customerDAO = new CustomerDAOImpl();
        CustomerAccountDAO customerAccountDAO = new CustomerAccountDAOImpl();

        while (true) {
            System.out.println("\n===== Customer Account Management =====");
            System.out.println("1. Update Customer Information");
            System.out.println("2. Change Password");
            System.out.println("3. View Order History");
            System.out.println("4. Delete Account");
            System.out.println("5. Exit");
            System.out.print("Enter your choice: ");
            
            int choice = scanner.nextInt();
            scanner.nextLine(); // Consume newline

            switch (choice) {
                case 1:
                    // Update Customer Info
                    System.out.print("Enter Customer ID: ");
                    int customerID = scanner.nextInt();
                    scanner.nextLine();
                    Customer customer = customerDAO.getCustomerByID(customerID);

                    if (customer == null) {
                        System.out.println("Customer not found.");
                        break;
                    }

                    System.out.print("Enter New First Name: ");
                    String firstName = scanner.nextLine();
                    System.out.print("Enter New Last Name: ");
                    String lastName = scanner.nextLine();
                    System.out.print("Enter New Email: ");
                    String email = scanner.nextLine();
                    System.out.print("Enter New Phone: ");
                    String phone = scanner.nextLine();
                    System.out.print("Enter New Address: ");
                    String address = scanner.nextLine();

                    customer.setFirstName(firstName);
                    customer.setLastName(lastName);
                    customer.setEmail(email);
                    customer.setPhone(phone);
                    customer.setAddress(address);

                    customerAccountDAO.updateCustomerInfo(customer);
                    break;

                case 2:
                    // Change Password
                    System.out.print("Enter Customer ID: ");
                    int custID = scanner.nextInt();
                    scanner.nextLine();
                    System.out.print("Enter New Password: ");
                    String newPassword = scanner.nextLine();
                    customerAccountDAO.changePassword(custID, newPassword);
                    break;

                case 3:
                    // View Order History
                    System.out.print("Enter Customer ID: ");
                    int orderCustomerID = scanner.nextInt();
                    scanner.nextLine();

                    List<Order> orders = customerAccountDAO.getOrderHistory(orderCustomerID);
                    if (orders.isEmpty()) {
                        System.out.println("No orders found.");
                    } else {
                        System.out.println("\n===== Order History =====");
                        for (Order order : orders) {
                            System.out.println("Order ID: " + order.getOrderID() +
                                    ", Date: " + order.getOrderDate() +
                                    ", Total: $" + order.getTotalAmount() +
                                    ", Status: " + order.getStatus());
                        }
                    }
                    break;

                case 4:
                    // Delete Account
                    System.out.print("Enter Customer ID: ");
                    int delCustomerID = scanner.nextInt();
                    scanner.nextLine();

                    System.out.print("Are you sure you want to delete your account? (yes/no): ");
                    String confirm = scanner.nextLine();
                    if (confirm.equalsIgnoreCase("yes")) {
                        customerAccountDAO.deleteAccount(delCustomerID);
                    } else {
                        System.out.println("Account deletion canceled.");
                    }
                    break;

                case 5:
                    System.out.println("Exiting...");
                    scanner.close();
                    return;

                default:
                    System.out.println("Invalid choice. Try again.");
            }
        }
    }
}
