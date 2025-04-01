package com.techshop.entity;
import java.util.*;

public class Order {
    private int orderID;
    private Customer customer;
    private Date orderDate;
    private double totalAmount;
    private String status;

    public Order(int orderID, Customer customer, Date orderDate, double totalAmount) {
        this.orderID = orderID;
        this.customer = customer;
        this.orderDate = orderDate;
        this.totalAmount = totalAmount;
        this.status = "Processing";
    }

    public void getOrderDetails() {
        System.out.println("Order ID: " + orderID + ", Total: " + totalAmount);
    }

    public void updateOrderStatus(String status) {
        this.status = status;
    }
}
