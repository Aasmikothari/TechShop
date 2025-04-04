package com.techshop.dao;

import com.techshop.entity.DatabaseConnection;
import com.techshop.entity.OrderDetail;
import com.techshop.entity.Order;
import com.techshop.entity.Product;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class OrderDetailDAOImpl implements OrderDetailDAO {

    @Override
    public void addOrderDetail(OrderDetail orderDetail) {
        String query = "INSERT INTO order_details (orderID, productID, quantity, totalPrice) VALUES (?, ?, ?, ?)";

        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(query, Statement.RETURN_GENERATED_KEYS)) {

            stmt.setInt(1, orderDetail.getOrder().getOrderID());
            stmt.setInt(2, orderDetail.getProduct().getProductID());
            stmt.setInt(3, orderDetail.getQuantity());
            stmt.setDouble(4, orderDetail.getTotalPrice());

            int affectedRows = stmt.executeUpdate();
            if (affectedRows == 0) {
                throw new SQLException("Failed to insert order detail.");
            }

            System.out.println("Order detail added successfully.");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public List<OrderDetail> getOrderDetailsByOrderID(int orderID) {
        List<OrderDetail> orderDetails = new ArrayList<>();
        String query = "SELECT * FROM order_details WHERE orderID = ?";

        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(query)) {

            stmt.setInt(1, orderID);
            ResultSet rs = stmt.executeQuery();

            while (rs.next()) {
                int orderDetailID = rs.getInt("orderDetailID");
                int productID = rs.getInt("productID");
                int quantity = rs.getInt("quantity");
                double totalPrice = rs.getDouble("totalPrice");

                // Fetch Order & Product objects
                Order order = new OrderDAOImpl().getOrderByID(orderID);
                Product product = new ProductDAOImpl().getProductById(productID);

                if (order != null && product != null) {
                    orderDetails.add(new OrderDetail(orderDetailID, order, product, quantity));
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return orderDetails;
    }

    @Override
    public void deleteOrderDetail(int orderDetailID) {
        String query = "DELETE FROM order_details WHERE orderDetailID = ?";

        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(query)) {

            stmt.setInt(1, orderDetailID);
            int rowsDeleted = stmt.executeUpdate();

            if (rowsDeleted > 0) {
                System.out.println("Order detail deleted successfully.");
            } else {
                System.out.println("Order detail not found.");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
