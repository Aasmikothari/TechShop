package com.techshop.entity;
import java.util.Date;
import java.util.ArrayList;
class OrderDetail {
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
        System.out.println("Order Detail ID: " + orderDetailID);
        System.out.println("Product: " + product.getProductName());
        System.out.println("Quantity: " + quantity);
        System.out.println("Subtotal: $" + calculateSubtotal());
    }
}
