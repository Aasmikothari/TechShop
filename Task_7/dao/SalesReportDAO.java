package com.techshop.dao;

import com.techshop.entity.Order;
import com.techshop.entity.Product;
import java.util.Date;
import java.util.List;
import java.util.Map;

public interface SalesReportDAO {
    double getTotalSalesRevenue();
    Map<String, Double> getSalesTrends(); // Sales revenue by month
    List<Product> getTopSellingProducts(int limit);
    List<Order> getOrdersWithinDateRange(Date startDate, Date endDate);
}
