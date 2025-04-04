package com.techshop.dao;

import com.techshop.entity.Customer;
import com.techshop.entity.DatabaseConnection;
import com.techshop.entity.Order;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class CustomerAccountDAOImpl implements CustomerAccountDAO {

    @Override
    public void updateCustomerInfo(Customer customer) {
        String query = "UPDATE customers SET firstName=?, lastName=?, email=?, phone=?, address=? WHERE customerID=?";
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(query)) {

            stmt.setString(1, customer.getFirstName());
            stmt.setString(2, customer.getLastName());
            stmt.setString(3, customer.getEmail());
            stmt.setString(4, customer.getPhone());
            stmt.setString(5, customer.getAddress());
            stmt.setInt(6, customer.getCustomerID());

            int rowsUpdated = stmt.executeUpdate();
            if (rowsUpdated > 0) {
                System.out.println("Customer information updated successfully!");
            } else {
                System.out.println("Customer not found.");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void changePassword(int customerID, String newPassword) {
        String query = "UPDATE customers SET password=? WHERE customerID=?";
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(query)) {

            stmt.setString(1, newPassword);
            stmt.setInt(2, customerID);

            int rowsUpdated = stmt.executeUpdate();
            if (rowsUpdated > 0) {
                System.out.println("Password updated successfully!");
            } else {
                System.out.println("Customer not found.");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public List<Order> getOrderHistory(int customerID) {
        List<Order> orders = new ArrayList<>();
        String query = "SELECT * FROM orders WHERE customerID = ?";

        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(query)) {

            stmt.setInt(1, customerID);
            ResultSet rs = stmt.executeQuery();

            while (rs.next()) {
                int orderID = rs.getInt("orderID");
                Date orderDate = rs.getDate("orderDate");
                double totalAmount = rs.getDouble("totalAmount");
                String status = rs.getString("status");

                Order order = new Order(orderID, new CustomerDAOImpl().getCustomerByID(customerID), orderDate, totalAmount);
                order.setStatus(status);
                orders.add(order);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return orders;
    }

    @Override
    public void deleteAccount(int customerID) {
        String query = "DELETE FROM customers WHERE customerID=?";
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(query)) {

            stmt.setInt(1, customerID);
            int rowsDeleted = stmt.executeUpdate();
            if (rowsDeleted > 0) {
                System.out.println("Account deleted successfully!");
            } else {
                System.out.println("Customer not found.");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
