package com.techshop.dao;

import com.techshop.entity.Product;
import java.util.List;

public interface ProductSearchDAO {
    List<Product> searchProductsByName(String name);
    List<Product> searchProductsByCategory(String category);
    List<Product> getRecommendedProducts(int customerID);
}