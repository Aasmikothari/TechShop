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
    }

    public double calculateSubtotal() {
        return product.getPrice() * quantity;
    }

    public void getOrderDetailInfo() {
    	
    }
       
    public void updateQuantity(int newQuantity) {
        this.quantity = newQuantity;
    }

    public void addDiscount(double discountPercentage) {
        double discountAmount = (product.getPrice() * discountPercentage) / 100;
        double newPrice = product.getPrice() - discountAmount;
        System.out.println("New price after discount: " + newPrice);
    }
}