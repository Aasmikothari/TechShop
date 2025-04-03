package com.techshop.client;

import com.techshop.entity.*;
import com.techshop.exception.*;
import com.techshop.manager.*;

import java.util.*;

public class Main {
    public static void main(String[] args) {

        InventoryManager inventoryManager = new InventoryManager();

        Product product1 = new Product(101, "Laptop", 1200.00);
        Product product2 = new Product(102, "Smartphone", 800.00);

        inventoryManager.addInventory(product1, 10);
        inventoryManager.addInventory(product2, 15);
        
        Customer customer1 = new Customer(201, "John", "Doe", "john@example.com", "1234567890", "123 Main St");
        Customer customer2 = new Customer(202, "Jane", "Smith", "jane@example.com", "0987654321", "456 Elm St");

        Order order1 = new Order(301, customer1, new Date(), 0);
        Order order2 = new Order(302, customer2, new Date(), 0);

        OrderDetail detail1 = new OrderDetail(401, order1, product1, 2);
        OrderDetail detail2 = new OrderDetail(402, order1, product2, 1);
        OrderDetail detail3 = new OrderDetail(403, order2, product1, 1);

        order1.setTotalAmount(order1.calculateTotalAmount());
        order2.setTotalAmount(order2.calculateTotalAmount());

        List<Inventory> inventoryList = new ArrayList<>(inventoryManager.getInventoryList());
        OrderManager orderManager = new OrderManager(inventoryList);

        orderManager.addOrder(order1);
        orderManager.addOrder(order2);

        orderManager.sortOrdersByDate(true);

        PaymentManager paymentManager = new PaymentManager();
        paymentManager.processPayment(501, order1.getOrderID(), order1.getTotalAmount(), "Credit Card");
        paymentManager.processPayment(502, order2.getOrderID(), order2.getTotalAmount(), "PayPal");

        inventoryManager.displayInventory();
    }
}