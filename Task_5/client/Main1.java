package com.techshop.client;

import com.techshop.entity.Customer;
import com.techshop.exception.InvalidDataException;

public class Main1 {
	public static void main(String[] args) {
        try {
            // Valid Customer
            Customer validCustomer = new Customer(1, "John", "Doe", "john@example.com", "1234567890", "123 Street, City");
            System.out.println("Valid customer created successfully!");

            // Invalid Email Test
            try {
                Customer invalidEmailCustomer = new Customer(2, "Alice", "Smith", "invalid-email", "9876543210", "456 Avenue, City");
            } catch (InvalidDataException e) {
                System.out.println(e.getMessage());
            }

            // Invalid Phone Test
            try {
                Customer invalidPhoneCustomer = new Customer(3, "Bob", "Brown", "bob@example.com", "12345", "789 Road, City");
            } catch (InvalidDataException e) {
                System.out.println(e.getMessage());
            }

        } catch (Exception e) {
            System.out.println("Unexpected error: " + e.getMessage());
        }
    }
}
