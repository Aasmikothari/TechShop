package com.techshop.dao;

import com.techshop.entity.Product;
import java.util.List;

public interface ProductDAO {
    void addProduct(Product product);
    Product getProductById(int productID);
    List<Product> getAllProducts();
    void updateProduct(Product product);
    void deleteProduct(int productID);
}