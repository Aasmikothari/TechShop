package com.techshop.dao;

import com.techshop.entity.Customer;
import com.techshop.entity.Order;
import java.util.List;

public interface CustomerAccountDAO {
    void updateCustomerInfo(Customer customer); 
    void changePassword(int customerID, String newPassword); 
    List<Order> getOrderHistory(int customerID); 
    void deleteAccount(int customerID); 
}
