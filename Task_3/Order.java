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
    
    public int getOrderID() { return orderID; }
    public Customer getCustomer() { return customer; }
    public Date getOrderDate() { return orderDate; }
    public double getTotalAmount() { return totalAmount; }
}