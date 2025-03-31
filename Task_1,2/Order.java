package com.techshop.entity;
import java.util.Date;
import java.util.ArrayList;
class Order {
    private int orderID;
    private Customer customer;
    private Date orderDate;
    private double totalAmount;

    public Order(int orderID, Customer customer) {
        this.orderID = orderID;
        this.customer = customer;
        this.orderDate = new Date();
        this.totalAmount = 0.0;
    }

    public void calculateTotalAmount(double amount) {
        this.totalAmount = amount;
    }

    public void getOrderDetails() {
        System.out.println("Order ID: " + orderID);
        System.out.println("Customer: " + customer);
        System.out.println("Order Date: " + orderDate);
        System.out.println("Total Amount: $" + totalAmount);
    }
}