package com.techshop.client;

//Import necessary classes
import com.techshop.dao.CustomerDAOImpl;
import com.techshop.entity.Customer;
import com.techshop.dao.OrderDAOImpl;
import com.techshop.entity.Order;
import java.util.List;
import java.util.Scanner;
import java.util.Date;

public class Main2 {
 public static void main(String[] args) {
     Scanner scanner = new Scanner(System.in);
     OrderDAOImpl orderDAO = new OrderDAOImpl();
     CustomerDAOImpl customerDAO = new CustomerDAOImpl();  // Declare once to avoid redundant calls

     while (true) {
         System.out.println("\nTechShop - Order Management");
         System.out.println("1. Place Order");
         System.out.println("2. View All Orders");
         System.out.println("3. Search Order by ID");
         System.out.println("4. View Orders by Customer ID");
         System.out.println("5. Update Order Status");
         System.out.println("6. Exit");
         System.out.print("Choose an option: ");
         int choice = scanner.nextInt();
         scanner.nextLine(); 

         switch (choice) {
             case 1:
                 System.out.println("\nEnter Order Details:");
                 System.out.print("Order ID: ");
                 int orderID = scanner.nextInt();
                 System.out.print("Customer ID: ");
                 int customerID = scanner.nextInt();
                 System.out.print("Total Amount: ");
                 double totalAmount = scanner.nextDouble();
                 scanner.nextLine();
                 System.out.print("Order Status: ");
                 String status = scanner.nextLine();

                 // Fetch the customer object using CustomerDAOImpl
                 Customer customer = customerDAO.getCustomerByID(customerID);
                 if (customer == null) {
                     System.out.println("Customer not found! Please enter a valid Customer ID.");
                     break;
                 }

                 // Create and place order
                 Order newOrder = new Order(orderID, customer, new Date(), totalAmount);
                 orderDAO.placeOrder(newOrder);
                 System.out.println("Order placed successfully!");
                 break;

             case 2:
                 System.out.println("\nOrder List:");
                 for (Order order : orderDAO.getAllOrders()) {
                     System.out.println(order);
                 }
                 break;

             case 3:
                 System.out.print("\nEnter Order ID to Search: ");
                 int searchID = scanner.nextInt();
                 Order foundOrder = orderDAO.getOrderByID(searchID);
                 System.out.println(foundOrder != null ? foundOrder : "Order not found.");
                 break;

             case 4:
                 System.out.print("\nEnter Customer ID to View Orders: ");
                 int searchCustomerID = scanner.nextInt();
                 List<Order> customerOrders = orderDAO.getOrdersByCustomerID(searchCustomerID);
                 
                 if (customerOrders.isEmpty()) {
                     System.out.println("No orders found for this customer.");
                 } else {
                     for (Order order : customerOrders) {
                         System.out.println(order);
                     }
                 }
                 break;

             case 5:
                 System.out.print("\nEnter Order ID to Update Status: ");
                 int updateID = scanner.nextInt();
                 scanner.nextLine();
                 System.out.print("New Status: ");
                 String newStatus = scanner.nextLine();

                 orderDAO.updateOrderStatus(updateID, newStatus);
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
