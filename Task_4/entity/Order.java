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
    
    public int getOrderID() { return orderID; }
    public Customer getCustomer() { return customer; }
    public Date getOrderDate() { return orderDate; }
    public double getTotalAmount() { return totalAmount; }
    public String getStatus() { return status; }
    public List<OrderDetail> getOrderDetails() { return orderDetails; }

    public void addOrderDetail(OrderDetail orderDetail) {
        if (orderDetail != null) {
            orderDetails.add(orderDetail);
        }
    }
    
    public void setTotalAmount(double totalAmount) {
        if (totalAmount >= 0) {
            this.totalAmount = totalAmount;
        } else {
            throw new IllegalArgumentException("Total amount cannot be negative");
        }
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public double calculateTotalAmount() {
        totalAmount = 0;
        for (OrderDetail detail : orderDetails) {
            totalAmount += detail.calculateSubtotal();
        }
        return totalAmount;
    }

    public void updateOrderStatus(String status) {
        this.status = status;
    }

    public void cancelOrder() {
        this.status = "Cancelled";
    }
}
