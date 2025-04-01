package com.techshop.entity;
import java.util.*;

public class Order {
    private int orderID;
    private Customer customer;
    private Date orderDate;
    private double totalAmount;
    private String status;
    private List<OrderDetail> orderDetails;

    public Order(int orderID, Customer customer, Date orderDate, double totalAmount) {
        this.orderID = orderID;
        this.customer = customer;
        this.orderDate = orderDate;
        this.totalAmount = totalAmount;
        this.status = "Processing";
        this.orderDetails = new ArrayList<>();
    }

    public double calculateTotalAmount() {
        totalAmount = 0;
        for (OrderDetail detail : orderDetails) {
            totalAmount += detail.calculateSubtotal();
        }
        return totalAmount;
    }

    public void getOrderDetails() {
        System.out.println("Order ID: " + orderID + ", Total: " + totalAmount);
    }

    public void updateOrderStatus(String status) {
        this.status = status;
    }

    public void cancelOrder() {
        this.status = "Cancelled";
    }
}