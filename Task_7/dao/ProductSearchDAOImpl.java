package com.techshop.dao;

import com.techshop.entity.Product;
import com.techshop.entity.DatabaseConnection;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ProductSearchDAOImpl implements ProductSearchDAO {

    @Override
    public List<Product> searchProductsByName(String name) {
        List<Product> products = new ArrayList<>();
        String sql = "SELECT * FROM products WHERE productName LIKE ?";

        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, "%" + name + "%");

            try (ResultSet rs = stmt.executeQuery()) {
                while (rs.next()) {
                    products.add(mapResultSetToProduct(rs));
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return products;
    }

    @Override
    public List<Product> searchProductsByCategory(String category) {
        List<Product> products = new ArrayList<>();
        String sql = "SELECT * FROM products WHERE category = ?";

        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, category);

            try (ResultSet rs = stmt.executeQuery()) {
                while (rs.next()) {
                    products.add(mapResultSetToProduct(rs));
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return products;
    }

    @Override
    public List<Product> getRecommendedProducts(int customerID) {
        List<Product> products = new ArrayList<>();
        String sql = """
            SELECT p.* FROM products p
            JOIN order_details od ON p.productID = od.productID
            JOIN orders o ON od.orderID = o.orderID
            WHERE o.customerID = ?
            GROUP BY p.productID
            ORDER BY COUNT(od.productID) DESC LIMIT 5
        """;

        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, customerID);

            try (ResultSet rs = stmt.executeQuery()) {
                while (rs.next()) {
                    products.add(mapResultSetToProduct(rs));
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return products;
    }


    private Product mapResultSetToProduct(ResultSet rs) throws SQLException {
        return new Product(
            rs.getInt("productID"),
            rs.getString("productName"),
            rs.getDouble("price"),
            rs.getString("category"),
            rs.getString("description"),
            rs.getInt("stockQuantity")
        );
    }
}
