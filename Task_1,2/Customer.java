package com.techshop.entity;
import java.util.Date;
import java.util.ArrayList;
class Customer {
    private int customerID;
    private String firstName;
    private String lastName;
    private String email;
    private String phone;
    private String address;
    private int totalOrders;

    public Customer(int customerID, String firstName, String lastName, String email, String phone, String address) {
        this.customerID = customerID;
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.phone = phone;
        this.address = address;
        this.totalOrders = 0;
    }

    public void calculateTotalOrders() {
        System.out.println("Total Orders: " + totalOrders);
    }

    public void getCustomerDetails() {
        System.out.println("Customer ID: " + customerID);
        System.out.println("Name: " + firstName + " " + lastName);
        System.out.println("Email: " + email);
        System.out.println("Phone: " + phone);
        System.out.println("Address: " + address);
    }

    public void updateCustomerInfo(String email, String phone, String address) {
        this.email = email;
        this.phone = phone;
        this.address = address;
        System.out.println("Customer information updated.");
    }
}