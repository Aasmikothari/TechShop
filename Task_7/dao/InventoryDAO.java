package com.techshop.dao;

import com.techshop.entity.Product;
import java.util.List;

public interface InventoryDAO {
    void addProduct(Product product);
    void updateStock(int productID, int newStock);
    void removeProduct(int productID);
    Product getProductByID(int productID);
    List<Product> getAllProducts();
    List<Product> getLowStockProducts();
}