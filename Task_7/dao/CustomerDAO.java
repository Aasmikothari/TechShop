package com.techshop.dao;

import com.techshop.entity.Customer;
import java.util.List;

public interface CustomerDAO {
    void registerCustomer(Customer customer);
    Customer getCustomerByID(int customerID);
    Customer getCustomerByEmail(String email);
    List<Customer> getAllCustomers();
    void updateCustomer(Customer customer);
    void deleteCustomer(int customerID);
    void changePassword(int customerId, String newPassword);

}