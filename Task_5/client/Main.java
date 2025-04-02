package com.techshop.client;

import com.techshop.entity.*;
import java.util.*;

public class Main {
    public static void main(String[] args) {

        Customer customer = new Customer(1, "John", "Doe", "john@example.com", "1234567890", "123 Street, City");

        Product laptop = new Product(101, "Laptop", "High-end gaming laptop", 1200.99);
        Product phone = new Product(102, "Smartphone", "Latest model smartphone", 899.49);

        Order order = new Order(1001, customer, new Date(), 0);
        OrderDetail orderDetail1 = new OrderDetail(5001, order, laptop, 2);
        OrderDetail orderDetail2 = new OrderDetail(5002, order, phone, 1);

        order.getOrderDetails().add(orderDetail1);
        order.getOrderDetails().add(orderDetail2);

        order.setTotalAmount(order.calculateTotalAmount());

        Inventory inventoryLaptop = new Inventory(301, laptop, 10);
        Inventory inventoryPhone = new Inventory(302, phone, 5);

        System.out.println("Customer Details:");
        customer.getCustomerDetails();
        
        System.out.println("\nProduct Details:");
        System.out.println("Product: " + laptop.getProductName() + ", Price: $" + laptop.getPrice());
        System.out.println("Product: " + phone.getProductName() + ", Price: $" + phone.getPrice());

        System.out.println("\nOrder Details:");
        System.out.println("Order ID: " + order.getOrderID() + ", Total Amount: $" + order.getTotalAmount());

        System.out.println("\nInventory Status:");
        System.out.println("Laptop stock: " + inventoryLaptop.getQuantityInStock());
        System.out.println("Phone stock: " + inventoryPhone.getQuantityInStock());

        System.out.println("\nApplying discount...");
        orderDetail1.addDiscount(10);
   
        System.out.println("\nChecking inventory availability...");
        System.out.println("Is Laptop available? " + (inventoryLaptop.getQuantityInStock() >= 2));
        System.out.println("Is Phone available? " + (inventoryPhone.getQuantityInStock() >= 1));
    }
}
