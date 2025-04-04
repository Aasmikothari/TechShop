package com.techshop.manager;

import com.techshop.entity.Order;
import com.techshop.entity.Inventory;
import com.techshop.entity.OrderDetail;
import com.techshop.entity.PaymentProcessor;
import com.techshop.exception.InsufficientStockException;
import com.techshop.exception.InvalidDataException;
import com.techshop.exception.PaymentFailedException;
import java.util.Comparator;

import java.util.ArrayList;
import java.util.List;

public class OrderManager {
    private List<Order> orders;
    private List<Inventory> inventoryList;

    public OrderManager(List<Inventory> inventoryList) {
        this.orders = new ArrayList<>();
        this.inventoryList = inventoryList;
    }

    public List<Order> getOrders() {
        return orders;
    }

    public void addOrder(Order order) {
        if (order == null || order.getOrderDetails().isEmpty()) {
            throw new InvalidDataException("Invalid order: Order cannot be empty.");
        }

        for (OrderDetail detail : order.getOrderDetails()) {
            Inventory inventory = findInventoryByProduct(detail.getProduct().getProductID());
            if (inventory == null || inventory.getStockQuantity() < detail.getQuantity()) {
                throw new InsufficientStockException("Insufficient stock for product: " + detail.getProduct().getProductName());
            }
        }

        for (OrderDetail detail : order.getOrderDetails()) {
            Inventory inventory = findInventoryByProduct(detail.getProduct().getProductID());
            inventory.removeFromInventory(detail.getQuantity());
        }

        try {
            PaymentProcessor.processPayment(order.getTotalAmount(), "Credit Card", false);
        } catch (PaymentFailedException e) {
            throw new PaymentFailedException("Order cannot be placed: " + e.getMessage());
        }

        orders.add(order);
        System.out.println("Order placed successfully. Order ID: " + order.getOrderID());
    }

    public void updateOrderStatus(int orderID, String newStatus) {
        for (Order order : orders) {
            if (order.getOrderID() == orderID) {
                order.setStatus(newStatus);
                System.out.println("Order status updated to: " + newStatus);
                return;
            }
        }
        throw new InvalidDataException("Order not found.");
    }

    public void removeCanceledOrder(int orderID) {
        Order toRemove = null;
        for (Order order : orders) {
            if (order.getOrderID() == orderID && order.getStatus().equalsIgnoreCase("Cancelled")) {
                toRemove = order;
                break;
            }
        }

        if (toRemove != null) {
            for (OrderDetail detail : toRemove.getOrderDetails()) {
                Inventory inventory = findInventoryByProduct(detail.getProduct().getProductID());
                if (inventory != null) {
                    inventory.addToInventory(detail.getQuantity());
                }
            }

            orders.remove(toRemove);
            System.out.println("Cancelled order removed: " + toRemove.getOrderID());
        } else {
            throw new InvalidDataException("Order not found or not cancelled.");
        }
    }

    private Inventory findInventoryByProduct(int productID) {
        for (Inventory inventory : inventoryList) {
            if (inventory.getProduct().getProductID() == productID) {
                return inventory;
            }
        }
        return null;
    }
    
    public void sortOrdersByDate(boolean ascending) {
        if (ascending) {
            orders.sort(Comparator.comparing(Order::getOrderDate)); // Oldest first
        } else {
            orders.sort(Comparator.comparing(Order::getOrderDate).reversed()); // Newest first
        }
        System.out.println("Orders sorted by date: " + (ascending ? "Oldest first" : "Newest first"));
    }
}