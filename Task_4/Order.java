package com.techshop.entity;
import java.util.Date;
import java.util.ArrayList;
class Order {
    private int orderID;
    private Customer customer;
    private Date orderDate;
    private double totalAmount;
    private ArrayList<OrderDetail> orderDetails;

    public Order(int orderID, Customer customer) {
        this.orderID = orderID;
        this.customer = customer;
        this.orderDate = new Date();
        this.totalAmount = 0.0;
        this.orderDetails = new ArrayList<>();
    }

    public int getOrderID() { return orderID; }
    public Customer getCustomer() { return customer; }
    public Date getOrderDate() { return orderDate; }
    public double getTotalAmount() { return totalAmount; }

    public void addOrderDetail(OrderDetail orderDetail) {
        orderDetails.add(orderDetail);
        totalAmount += orderDetail.getProduct().getPrice() * orderDetail.getQuantity();
    }
}
