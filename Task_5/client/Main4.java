package com.techshop.client;

import com.techshop.entity.PaymentProcessor;
import com.techshop.exception.PaymentFailedException;
import com.techshop.entity.LoggerUtil;

public class Main4 {
    public static void main(String[] args) {
        double amount = 150.75;
        String paymentMethod = "Credit Card";

        try {
            PaymentProcessor.processPayment(amount, paymentMethod, false);
        } catch (PaymentFailedException e) {
            LoggerUtil.log("Payment failed: " + e.getMessage());
            System.out.println(e.getMessage());
        }

        try {
            PaymentProcessor.processPayment(amount, paymentMethod, true);
        } catch (PaymentFailedException e) {
            LoggerUtil.log("Payment failed: " + e.getMessage());
            System.out.println(e.getMessage());
        }

        try {
            PaymentProcessor.processPayment(-50, paymentMethod, false);
        } catch (PaymentFailedException e) {
            LoggerUtil.log("Payment failed: " + e.getMessage());
            System.out.println(e.getMessage());
        }

        try {
            PaymentProcessor.processPayment(amount, "", false);
        } catch (PaymentFailedException e) {
            LoggerUtil.log("Payment failed: " + e.getMessage());
            System.out.println(e.getMessage());
        }

        System.out.println("Check techshop.log for error logs.");
    }
}