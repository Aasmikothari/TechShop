package com.techshop.entity;
public class Main {
    public static void main(String[] args) {
        Customer customer1 = new Customer(1, "John", "Doe", "john.doe@email.com", "1234567890", "123 Street");
        Product product1 = new Product(101, "Smartphone", "Latest model smartphone", 699.99);
        Inventory inventory1 = new Inventory(1, product1, 50);
        Order order1 = new Order(1001, customer1);
        OrderDetail orderDetail1 = new OrderDetail(1, order1, product1, 2);

        System.out.println("--- Customer Details ---");
        customer1.getCustomerDetails();
        
        System.out.println("\n--- Product Details ---");
        product1.getProductDetails();
        
        System.out.println("\n--- Inventory Stock ---");
        System.out.println("Stock Available: " + inventory1.getQuantityInStock());
    }
}