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
    
    public int getCustomerID() { return customerID; }
    public String getFirstName() { return firstName; }
    public String getLastName() { return lastName; }
    public String getEmail() { return email; }
    public String getPhone() { return phone; }
    public String getAddress() { return address; }
    public List<Order> getOrders() { return orders; }

    public void setEmail(String email) {
        if (email.contains("@")) {
            this.email = email;
        } else {
            throw new IllegalArgumentException("Invalid email format");
        }
    }

    public void setPhone(String phone) {
        if (phone.matches("\\d{10}")) {
            this.phone = phone;
        } else {
            throw new IllegalArgumentException("Invalid phone number");
        }
    }

    public void setAddress(String address) {
        this.address = address;
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