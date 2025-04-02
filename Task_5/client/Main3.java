package com.techshop.client;

import com.techshop.entity.*;
import com.techshop.exception.*;

import java.util.Date;

public class Main3 {
    public static void main(String[] args) {
        Customer customer = new Customer(1, "John", "Doe", "john@example.com", "1234567890", "123 Street, City");
        Product laptop = new Product(101, "Laptop", "Gaming Laptop", 1200.99);
        Product phone = new Product(102, "Smartphone", "Latest Model", 899.49);

        Order order = new Order(1001, customer, new Date(), 0);
        
        System.out.println("Order created successfully!");

        try {
            // Adding valid order details
            OrderDetail validDetail = new OrderDetail(5001, order, laptop, 2);
            order.addOrderDetail(validDetail);
            System.out.println("Order detail added successfully!");

            // Adding invalid order detail (null product)
            OrderDetail invalidDetail = new OrderDetail(5002, order, null, 1);
            order.addOrderDetail(invalidDetail);
        } catch (IncompleteOrderException e) {
            System.out.println(e.getMessage());
        }

        try {
            // Setting a negative total amount
            order.setTotalAmount(-50);
        } catch (InvalidDataException e) {
            System.out.println(e.getMessage());
        }
    }
}