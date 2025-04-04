package com.techshop.dao;

import com.techshop.entity.Order;
import com.techshop.entity.Customer;
import com.techshop.entity.DatabaseConnection;
import java.sql.*;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class OrderDAOImpl implements OrderDAO {
	public void placeOrder(Order order) {
	    String orderQuery = "INSERT INTO orders (customerID, orderDate, totalAmount, status) VALUES (?, ?, ?, ?)";
	    
	    try (Connection conn = DatabaseConnection.getConnection();
	         PreparedStatement stmt = conn.prepareStatement(orderQuery, Statement.RETURN_GENERATED_KEYS)) {

	        stmt.setInt(1, order.getCustomer().getCustomerID());
	        stmt.setTimestamp(2, new Timestamp(order.getOrderDate().getTime()));
	        stmt.setDouble(3, 0);  // Initial amount, updated later
	        stmt.setString(4, "Pending");

	        int affectedRows = stmt.executeUpdate();
	        if (affectedRows == 0) {
	            throw new SQLException("Failed to insert order.");
	        }

	        // Retrieve generated order ID
	        ResultSet generatedKeys = stmt.getGeneratedKeys();
	        if (generatedKeys.next()) {
	            int generatedOrderID = generatedKeys.getInt(1);
	            order.setOrderID(generatedOrderID);  
	        } else {
	            throw new SQLException("Failed to get generated Order ID.");
	        }

	        System.out.println("Order placed successfully.");
	    } catch (SQLException e) {
	        e.printStackTrace();
	    }
	}

    public List<Order> getAllOrders() {
        List<Order> orders = new ArrayList<>();
        String query = "SELECT * FROM orders";

        try (Connection conn = DatabaseConnection.getConnection();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(query)) {

            while (rs.next()) {
                int orderID = rs.getInt("orderID");
                int customerID = rs.getInt("customerID");

                // Fetch Customer object based on customerID
                Customer customer = new CustomerDAOImpl().getCustomerByID(customerID);
                if (customer == null) {
                    System.out.println("Customer with ID " + customerID + " not found.");
                    continue;
                }

                Date orderDate = new Date(rs.getTimestamp("orderDate").getTime()); // Convert Timestamp to Date
                double totalAmount = rs.getDouble("totalAmount");
                String status = rs.getString("status");

                orders.add(new Order(orderID, customer, orderDate, totalAmount));
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return orders;
    }

    public Order getOrderByID(int orderID) {
        String query = "SELECT * FROM orders WHERE orderID = ?";
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(query)) {

            stmt.setInt(1, orderID);
            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {
                int customerID = rs.getInt("customerID");

                Customer customer = new CustomerDAOImpl().getCustomerByID(customerID);
                if (customer == null) {
                    System.out.println("Customer with ID " + customerID + " not found.");
                    return null;
                }

                Date orderDate = new Date(rs.getTimestamp("orderDate").getTime());
                double totalAmount = rs.getDouble("totalAmount");
                String status = rs.getString("status");

                return new Order(orderID, customer, orderDate, totalAmount);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    public void updateOrderStatus(int orderID, String newStatus) {
        String query = "UPDATE orders SET status = ? WHERE orderID = ?";

        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(query)) {
            
            stmt.setString(1, newStatus);
            stmt.setInt(2, orderID);

            int updatedRows = stmt.executeUpdate();
            if (updatedRows > 0) {
                System.out.println("Order status updated successfully.");
            } else {
                System.out.println("Order not found.");
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    
    public List<Order> getOrdersByCustomerID(int customerID) {
        List<Order> orders = new ArrayList<>();
        String query = "SELECT * FROM orders WHERE customerID = ?";

        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(query)) {

            stmt.setInt(1, customerID);
            ResultSet rs = stmt.executeQuery();

            while (rs.next()) {
                int orderID = rs.getInt("orderID");
                Customer customer = new CustomerDAOImpl().getCustomerByID(customerID);
                if (customer == null) continue;

                Date orderDate = new Date(rs.getTimestamp("orderDate").getTime());
                double totalAmount = rs.getDouble("totalAmount");
                String status = rs.getString("status");

                orders.add(new Order(orderID, customer, orderDate, totalAmount));
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return orders;
    }
    
    public void updateTotalAmount(int orderID, double totalAmount) {
        String query = "UPDATE orders SET totalAmount = ? WHERE orderID = ?";
        
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(query)) {

            stmt.setDouble(1, totalAmount);
            stmt.setInt(2, orderID);
            stmt.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    
    public List<Order> viewOrderHistory(int customerId) {
        List<Order> orderHistory = new ArrayList<>();
        String query = "SELECT * FROM Orders WHERE customerID = ? ORDER BY orderDate DESC";
        
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(query)) {

            stmt.setInt(1, customerId);
            ResultSet rs = stmt.executeQuery();

            while (rs.next()) {
                Order order = new Order(
                    rs.getInt("orderID"),
                    new Customer(customerId, "TempFirst", "TempLast", "temp@example.com", "1234567890", "Temp Address"),
                    rs.getTimestamp("orderDate"),
                    rs.getDouble("totalAmount")
                );
                orderHistory.add(order);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return orderHistory;
    }

}
