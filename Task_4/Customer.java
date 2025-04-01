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
        setEmail(email);
        setPhone(phone);
        this.address = address;
        this.totalOrders = 0;
    }

    public int getCustomerID() { return customerID; }
    public String getFirstName() { return firstName; }
    public String getLastName() { return lastName; }
    public String getEmail() { return email; }
    public String getPhone() { return phone; }
    public String getAddress() { return address; }

    public void setEmail(String email) {
        if (email.contains("@")) {
            this.email = email;
        } else {
            System.out.println("Invalid email format.");
        }
    }

    public void setPhone(String phone) {
        if (phone.matches("\\d{10}")) {
            this.phone = phone;
        } else {
            System.out.println("Invalid phone number.");
        }
    }

    public void setAddress(String address) { this.address = address; }

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
}