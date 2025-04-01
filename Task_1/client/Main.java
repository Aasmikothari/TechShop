package com.techshop.client;

import com.techshop.entity.*;
import java.util.*;

public class Main {
    public static void main(String[] args) {
        Customer customer = new Customer(1, "John", "Doe", "john@example.com", "1234567890", "123 Street");
        Product product = new Product(101, "Laptop", "High-end gaming laptop", 1200.99);
        Order order = new Order(1001, customer, new Date(), 1200.99);
        OrderDetail orderDetail = new OrderDetail(5001, order, product, 2);
        Inventory inventory = new Inventory(301, product, 10);

        customer.getCustomerDetails();
        product.getProductDetails();
        order.getOrderDetails();
        System.out.println("Subtotal: " + orderDetail.calculateSubtotal());
        System.out.println("Is product available? " + inventory.isProductAvailable(5));
    }
}