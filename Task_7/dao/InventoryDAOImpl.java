package com.techshop.dao;

import com.techshop.entity.Product;
import com.techshop.entity.DatabaseConnection;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class InventoryDAOImpl implements InventoryDAO {
    private Connection conn;

    public InventoryDAOImpl() {
        conn = DatabaseConnection.getConnection();
    }

    @Override
    public void addProduct(Product product) {
        String sql = "INSERT INTO products (productID, productName, price, category, description, stockQuantity) VALUES (?, ?, ?, ?, ?, ?)";
        try (PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, product.getProductID());
            stmt.setString(2, product.getProductName());
            stmt.setDouble(3, product.getPrice());
            stmt.setString(4, product.getCategory());
            stmt.setString(5, product.getDescription());
            stmt.setInt(6, product.getStockQuantity());
            stmt.executeUpdate();
            System.out.println("Product added successfully.");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void updateStock(int productID, int newStock) {
        String sql = "UPDATE products SET stockQuantity = ? WHERE productID = ?";
        try (PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, newStock);
            stmt.setInt(2, productID);
            int rowsUpdated = stmt.executeUpdate();
            if (rowsUpdated > 0) {
                System.out.println("Stock updated successfully.");
            } else {
                System.out.println("Product not found.");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void removeProduct(int productID) {
        String sql = "DELETE FROM products WHERE productID = ?";
        try (PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, productID);
            int rowsDeleted = stmt.executeUpdate();
            if (rowsDeleted > 0) {
                System.out.println("Product removed successfully.");
            } else {
                System.out.println("Product not found.");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public Product getProductByID(int productID) {
        String sql = "SELECT productID, productName, price, category, description, stockQuantity FROM products WHERE productID = ?";
        try (PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, productID);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                return new Product(
                    rs.getInt("productID"),
                    rs.getString("productName"),
                    rs.getDouble("price"),
                    rs.getString("category"),
                    rs.getString("description"),
                    rs.getInt("stockQuantity")
                );
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public List<Product> getAllProducts() {
        List<Product> products = new ArrayList<>();
        String sql = "SELECT productID, productName, price, category, description, stockQuantity FROM products";
        try (Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {
            while (rs.next()) {
                products.add(new Product(
                    rs.getInt("productID"),
                    rs.getString("productName"),
                    rs.getDouble("price"),
                    rs.getString("category"),
                    rs.getString("description"),
                    rs.getInt("stockQuantity")
                ));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return products;
    }
    
    @Override
    public List<Product> getLowStockProducts() {
        List<Product> lowStockProducts = new ArrayList<>();
        String query = "SELECT p.productID, p.productName, p.description, p.price, p.category, i.quantity " +
                       "FROM Products p JOIN Inventory i ON p.productID = i.productID " +
                       "WHERE i.quantity < 10";  // You can set this threshold as needed

        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(query);
             ResultSet rs = stmt.executeQuery()) {

            while (rs.next()) {
                Product product = new Product(
                		rs.getInt("productID"),
                        rs.getString("productName"),
                        rs.getDouble("price"),
                        rs.getString("category"),
                        rs.getString("description"),
                        rs.getInt("stockQuantity")
                );
                lowStockProducts.add(product);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return lowStockProducts;
    }

}