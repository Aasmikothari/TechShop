package com.techshop.entity;

import java.util.Date;
import com.techshop.exception.InvalidDataException;

public class Payment {
    private int paymentID;
    private int orderID;
    private double amount;
    private String paymentMethod;
    private Date paymentDate;

    public Payment(int paymentID, int orderID, double amount, String paymentMethod) {
        if (amount <= 0) {
            throw new InvalidDataException("Payment amount must be greater than zero.");
        }
        this.paymentID = paymentID;
        this.orderID = orderID;
        this.amount = amount;
        this.paymentMethod = paymentMethod;
        this.paymentDate = new Date();
    }

    public int getPaymentID() { return paymentID; }
    public int getOrderID() { return orderID; }
    public double getAmount() { return amount; }
    public String getPaymentMethod() { return paymentMethod; }
    public Date getPaymentDate() { return paymentDate; }
}