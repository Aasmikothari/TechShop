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

        Inventory inventoryLaptop = new Inventory(301, laptop, 10);
        Inventory inventoryPhone = new Inventory(302, phone, 5);

        customer.getCustomerDetails();

        laptop.getProductDetails();
        phone.getProductDetails();

        order.getOrderDetails();

        System.out.println("Subtotal for laptop: " + orderDetail1.calculateSubtotal());
        System.out.println("Subtotal for phone: " + orderDetail2.calculateSubtotal());

        orderDetail1.addDiscount(10);

        System.out.println("Is laptop available? " + inventoryLaptop.isProductAvailable(5));
        System.out.println("Is phone available? " + inventoryPhone.isProductAvailable(3));

    }
}
