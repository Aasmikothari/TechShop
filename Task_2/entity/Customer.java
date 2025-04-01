package com.techshop.entity;
import java.util.*;

public class Customer {
    private int customerID;
    private String firstName;
    private String lastName;
    private String email;
    private String phone;
    private String address;
    private List<Order> orders;

    public Customer(int customerID, String firstName, String lastName, String email, String phone, String address) {
        this.customerID = customerID;
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.phone = phone;
        this.address = address;
        this.orders = new ArrayList<>();
    }

    public int calculateTotalOrders() {
        return orders.size();
    }

    public void getCustomerDetails() {
        System.out.println("Customer ID: " + customerID + ", Name: " + firstName + " " + lastName);
    }

    public void updateCustomerInfo(String email, String phone, String address) {
        this.email = email;
        this.phone = phone;
        this.address = address;
    }
}