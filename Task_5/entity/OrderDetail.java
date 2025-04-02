package com.techshop.entity;
import java.util.*;

public class OrderDetail {
    private int orderDetailID;
    private Order order;
    private Product product;
    private int quantity;

    public OrderDetail(int orderDetailID, Order order, Product product, int quantity) {
        this.orderDetailID = orderDetailID;
        this.order = order;
        this.product = product;
        this.quantity = quantity;
        order.addOrderDetail(this);
    }
    
    public int getOrderDetailID() { return orderDetailID; }
    public Order getOrder() { return order; }
    public Product getProduct() { return product; }
    public int getQuantity() { return quantity; }

    public double calculateSubtotal() {
        return product.getPrice() * quantity;
    }

    public void getOrderDetailInfo() {
    	
    }
       
    public void updateQuantity(int newQuantity) {
    	if (newQuantity <= 0) {
            throw new IllegalArgumentException("Quantity must be greater than zero");
        }
        this.quantity = newQuantity;
    }

    public void addDiscount(double discountPercentage) {
    	if (discountPercentage < 0 || discountPercentage > 100) {
            throw new IllegalArgumentException("Invalid discount percentage");
        }
        double discountAmount = (product.getPrice() * discountPercentage) / 100;
        double newPrice = product.getPrice() - discountAmount;
        System.out.println("New price after discount: " + newPrice);
    }
}