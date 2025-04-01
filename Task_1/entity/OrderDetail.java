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
}