package com.techshop.entity;
import java.util.*;
import com.techshop.exception.IncompleteOrderException;
import com.techshop.exception.InvalidDataException;

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
    public int getCustomerID() { return customer.getCustomerID(); }
    public Date getOrderDate() { return orderDate; }
    public double getTotalAmount() { return totalAmount; }
    public String getStatus() { return status; }
    public List<OrderDetail> getOrderDetails() { return orderDetails; }

    public void addOrderDetail(OrderDetail orderDetail) {
    	if (orderDetail == null || orderDetail.getProduct() == null) {
            throw new IncompleteOrderException("Order detail is missing a valid product reference.");
        }
        orderDetails.add(orderDetail);
    }
    
    public void setOrderID(int orderID) {
        this.orderID = orderID;
    }
    
    public void setTotalAmount(double totalAmount) {
    	if (totalAmount < 0) {
            throw new InvalidDataException("Total order amount cannot be negative.");
        }
        this.totalAmount = totalAmount;
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