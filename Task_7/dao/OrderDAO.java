package com.techshop.dao;

import com.techshop.entity.Order;
import java.util.List;

public interface OrderDAO {
    void placeOrder(Order order);
    Order getOrderByID(int orderID);
    List<Order> getOrdersByCustomerID(int customerID);
    void updateOrderStatus(int orderID, String status);
    void updateTotalAmount(int orderID, double totalAmount);
    List<Order> viewOrderHistory(int customerId);
}