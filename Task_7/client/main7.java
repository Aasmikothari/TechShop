package com.techshop.client;

import com.techshop.dao.CustomerDAO;
import com.techshop.dao.CustomerDAOImpl;
import com.techshop.entity.Customer;
import java.util.Scanner;

public class main7 {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        CustomerDAO customerDAO = new CustomerDAOImpl();

        while (true) {
            System.out.println("\nTechShop - Customer Registration");
            System.out.println("1. Register Customer");
            System.out.println("2. View All Customers");
            System.out.println("3. Search Customer by Email");
            System.out.println("4. Update Customer");
            System.out.println("5. Delete Customer");
            System.out.println("6. Exit");
            System.out.print("Choose an option: ");
            int choice = scanner.nextInt();
            scanner.nextLine();  

            switch (choice) {
                case 1:
                    System.out.println("\nEnter Customer Details:");
                    System.out.print("Customer ID: ");
                    int customerID = scanner.nextInt();
                    scanner.nextLine();  

                    System.out.print("First Name: ");
                    String firstName = scanner.nextLine();

                    System.out.print("Last Name: ");
                    String lastName = scanner.nextLine();

                    System.out.print("Email: ");
                    String email = scanner.nextLine();

                    System.out.print("Phone: ");
                    String phone = scanner.nextLine();

                    System.out.print("Address: ");
                    String address = scanner.nextLine();

                    Customer newCustomer = new Customer(customerID, firstName, lastName, email, phone, address);
                    customerDAO.registerCustomer(newCustomer);
                    break;

                case 2:
                    System.out.println("\nAll Customers:");
                    customerDAO.getAllCustomers().forEach(System.out::println);
                    break;

                case 3:
                    System.out.print("\nEnter Email to Search: ");
                    String searchEmail = scanner.nextLine();
                    Customer foundCustomer = customerDAO.getCustomerByEmail(searchEmail);
                    if (foundCustomer != null) {
                        System.out.println(foundCustomer);
                    } else {
                        System.out.println("Customer not found.");
                    }
                    break;

                case 4:
                    System.out.print("\nEnter Customer ID to Update: ");
                    int updateID = scanner.nextInt();
                    scanner.nextLine();

                    Customer existingCustomer = customerDAO.getCustomerByID(updateID);
                    if (existingCustomer == null) {
                        System.out.println("Customer not found.");
                        break;
                    }

                    System.out.print("New First Name: ");
                    existingCustomer.setFirstName(scanner.nextLine());

                    System.out.print("New Last Name: ");
                    existingCustomer.setLastName(scanner.nextLine());

                    System.out.print("New Email: ");
                    existingCustomer.setEmail(scanner.nextLine());

                    System.out.print("New Phone: ");
                    existingCustomer.setPhone(scanner.nextLine());

                    System.out.print("New Address: ");
                    existingCustomer.setAddress(scanner.nextLine());

                    customerDAO.updateCustomer(existingCustomer);
                    break;

                case 5:
                    System.out.print("\nEnter Customer ID to Delete: ");
                    int deleteID = scanner.nextInt();
                    customerDAO.deleteCustomer(deleteID);
                    break;

                case 6:
                    System.out.println("Exiting...");
                    scanner.close();
                    System.exit(0);
                    break;

                default:
                    System.out.println("Invalid option! Try again.");
            }
        }
    }
}