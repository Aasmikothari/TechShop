package com.techshop.entity;
import java.util.*;
import com.techshop.exception.InvalidDataException;

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
        setEmail(email);  // Using setter to apply validation
        setPhone(phone);  // Using setter to apply validation
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

    public void addOrder(Order order) {
        orders.add(order);
    }

    public void setEmail(String email) {
        if (email == null || !email.matches("^[A-Za-z0-9+_.-]+@(.+)$")) {
            throw new InvalidDataException("Invalid email format: " + email);
        }
        this.email = email;
    }

    public void setPhone(String phone) {
        if (phone == null || !phone.matches("\\d{10}")) {
            throw new InvalidDataException("Invalid phone number: " + phone);
        }
        this.phone = phone;
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
        setEmail(email);  // Ensuring validation before updating
        setPhone(phone);
        this.address = address;
    }
}