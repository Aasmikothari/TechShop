package com.techshop.dao;

import com.techshop.entity.DatabaseConnection;
import com.techshop.entity.Order;
import com.techshop.entity.Customer;
import com.techshop.entity.Product;

import java.sql.*;
import java.util.*;
import java.util.Date;

public class SalesReportDAOImpl implements SalesReportDAO {

    @Override
    public double getTotalSalesRevenue() {
        String query = "SELECT SUM(totalAmount) AS totalRevenue FROM orders";
        try (Connection conn = DatabaseConnection.getConnection();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(query)) {
            if (rs.next()) {
                return rs.getDouble("totalRevenue");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return 0.0;
    }

    @Override
    public Map<String, Double> getSalesTrends() {
        Map<String, Double> salesTrends = new LinkedHashMap<>();
        String query = "SELECT DATE_FORMAT(orderDate, '%Y-%m') AS month, SUM(totalAmount) AS revenue " +
                       "FROM orders GROUP BY month ORDER BY month ASC";
        
        try (Connection conn = DatabaseConnection.getConnection();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(query)) {
            while (rs.next()) {
                salesTrends.put(rs.getString("month"), rs.getDouble("revenue"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return salesTrends;
    }

    @Override
    public List<Product> getTopSellingProducts(int limit) {
        List<Product> topProducts = new ArrayList<>();
        String query = "SELECT p.productID, p.productName, p.price, p.category, p.description, p.stockQuantity, SUM(od.quantity) AS totalSold " +
                       "FROM order_details od " +
                       "JOIN products p ON od.productID = p.productID " +
                       "GROUP BY p.productID, p.productName, p.price, p.category, p.description, p.stockQuantity " +
                       "ORDER BY totalSold DESC LIMIT ?";
        
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(query)) {
            stmt.setInt(1, limit);
            ResultSet rs = stmt.executeQuery();
            
            while (rs.next()) {
                int productID = rs.getInt("productID");
                String productName = rs.getString("productName");
                double price = rs.getDouble("price");
                String category = rs.getString("category");
                String description = rs.getString("description");
                int stockQuantity = rs.getInt("stockQuantity");

                Product product = new Product(productID, productName, price, category, description, stockQuantity);
                topProducts.add(product);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return topProducts;
    }

    @Override
    public List<Order> getOrdersWithinDateRange(Date startDate, Date endDate) {
        List<Order> orders = new ArrayList<>();
        String query = "SELECT * FROM orders WHERE orderDate BETWEEN ? AND ?";
        
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(query)) {
            stmt.setTimestamp(1, new Timestamp(startDate.getTime()));
            stmt.setTimestamp(2, new Timestamp(endDate.getTime()));
            ResultSet rs = stmt.executeQuery();
            
            while (rs.next()) {
                int orderID = rs.getInt("orderID");
                int customerID = rs.getInt("customerID");
                Date orderDate = new Date(rs.getTimestamp("orderDate").getTime());
                double totalAmount = rs.getDouble("totalAmount");

                // Retrieve the Customer object
                Customer customer = getCustomerByID(customerID);

                Order order = new Order(orderID, customer, orderDate, totalAmount);
                orders.add(order);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return orders;
    }

    private Customer getCustomerByID(int customerID) {
        String query = "SELECT * FROM customers WHERE customerID = ?";
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(query)) {
            stmt.setInt(1, customerID);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                return new Customer(
                        rs.getInt("customerID"),
                        rs.getString("firstName"),
                        rs.getString("lastName"),
                        rs.getString("email"),
                        rs.getString("phone"),
                        rs.getString("address")
                );
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }
}
