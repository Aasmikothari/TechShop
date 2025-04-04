package com.techshop.client;

import com.techshop.dao.*;
import com.techshop.entity.*;
import com.techshop.exception.*;

import java.util.*;

public class Main4 {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        CustomerDAO customerDAO = new CustomerDAOImpl();
        ProductDAO productDAO = new ProductDAOImpl();
        OrderDAO orderDAO = new OrderDAOImpl();
        OrderDetailDAO orderDetailDAO = new OrderDetailDAOImpl();

        while (true) {
            System.out.println("\n--- TechShop Main Menu ---");
            System.out.println("1. Register Customer");
            System.out.println("2. Add Product");
            System.out.println("3. Place Order");
            System.out.println("4. View Orders");
            System.out.println("5. Update Order Status");
            System.out.println("6. Exit");
            System.out.print("Enter your choice: ");
            
            int choice = scanner.nextInt();
            scanner.nextLine();

            switch (choice) {
                case 1:
                    System.out.print("Enter Customer ID: ");
                    int customerID = scanner.nextInt();
                    scanner.nextLine();
                    System.out.print("Enter First Name: ");
                    String firstName = scanner.nextLine();
                    System.out.print("Enter Last Name: ");
                    String lastName = scanner.nextLine();
                    System.out.print("Enter Email: ");
                    String email = scanner.nextLine();
                    System.out.print("Enter Phone: ");
                    String phone = scanner.nextLine();
                    System.out.print("Enter Address: ");
                    String address = scanner.nextLine();

                    // Use the updated constructor
                    Customer customer = new Customer(customerID, firstName, lastName, email, phone, address);
                    customerDAO.registerCustomer(customer);
                    System.out.println("Customer registered successfully!");
                    break;

                case 2:
                    System.out.print("Enter Product ID: ");
                    int productID = scanner.nextInt();
                    scanner.nextLine();
                    System.out.print("Enter Product Name: ");
                    String productName = scanner.nextLine();
                    System.out.print("Enter Price: ");
                    double price = scanner.nextDouble();
                    scanner.nextLine();
                    System.out.print("Enter Category: ");
                    String category = scanner.nextLine();
                    System.out.print("Enter Description: ");
                    String description = scanner.nextLine();
                    System.out.print("Enter Stock Quantity: ");
                    int stockQuantity = scanner.nextInt();

                    Product product = new Product(productID, productName, price, category, description, stockQuantity);
                    productDAO.addProduct(product);
                    System.out.println("Product added successfully!");
                    break;

                case 3:
                    System.out.print("Enter Customer ID: ");
                    int custID = scanner.nextInt();

                    Customer orderCustomer = customerDAO.getCustomerByID(custID);
                    if (orderCustomer == null) {
                        System.out.println("Customer not found!");
                        break;
                    }

                    Date orderDate = new Date();
                    Order order = new Order(0, orderCustomer, orderDate, 0); // Order ID is auto-generated
                    orderDAO.placeOrder(order); // Order is inserted and orderID is generated

                    double totalAmount = 0;
                    while (true) {
                        System.out.print("Enter Product ID (or 0 to finish): ");
                        int prodID = scanner.nextInt();
                        if (prodID == 0) break;

                        System.out.print("Enter Quantity: ");
                        int quantity = scanner.nextInt();

                        Product orderProduct = productDAO.getProductById(prodID);
                        if (orderProduct == null) {
                            System.out.println("Product not found!");
                            continue;
                        }

                        OrderDetail orderDetail = new OrderDetail(0, order, orderProduct, quantity);
                        orderDetailDAO.addOrderDetail(orderDetail);
                        totalAmount += orderDetail.getTotalPrice();
                    }

                    order.setTotalAmount(totalAmount);
                    orderDAO.updateTotalAmount(order.getOrderID(), totalAmount);

                    System.out.println("Order placed successfully!");
                    break;

                case 4:
                    System.out.println("\n--- View Orders ---");
                    System.out.println("1. View Order by Order ID");
                    System.out.println("2. View Orders by Customer ID");
                    System.out.print("Enter your choice: ");
                    
                    int viewChoice = scanner.nextInt();
                    scanner.nextLine();

                    if (viewChoice == 1) {
                        System.out.print("Enter Order ID: ");
                        int searchOrderID = scanner.nextInt();
                        order = orderDAO.getOrderByID(searchOrderID);
                        
                        if (order != null) {
                            System.out.println("Order ID: " + order.getOrderID() + 
                                               "\nCustomer: " + order.getCustomer().getFirstName() + " " + order.getCustomer().getLastName() + 
                                               "\nTotal Amount: $" + order.getTotalAmount() + 
                                               "\nStatus: " + order.getStatus());
                        } else {
                            System.out.println("Order not found.");
                        }
                    } 
                    else if (viewChoice == 2) {
                        System.out.print("Enter Customer ID: ");
                        int searchCustomerID = scanner.nextInt();
                        List<Order> customerOrders = orderDAO.getOrdersByCustomerID(searchCustomerID);
                        
                        if (!customerOrders.isEmpty()) {
                            for (Order o : customerOrders) {
                                System.out.println("Order ID: " + o.getOrderID() + 
                                                   ", Total Amount: $" + o.getTotalAmount() + 
                                                   ", Status: " + o.getStatus());
                            }
                        } else {
                            System.out.println("No orders found for this customer.");
                        }
                    } 
                    else {
                        System.out.println("Invalid choice.");
                    }
                    break;

                case 5:
                    System.out.print("Enter Order ID: ");
                    int updateOrderID = scanner.nextInt();
                    scanner.nextLine();
                    System.out.print("Enter New Status: ");
                    String newStatus = scanner.nextLine();
                    
                    orderDAO.updateOrderStatus(updateOrderID, newStatus);
                    System.out.println("Order status updated successfully!");
                    break;

                case 6:
                    System.out.println("Exiting program...");
                    scanner.close();
                    return;

                default:
                    System.out.println("Invalid choice. Try again.");
            }
        }
    }
}
