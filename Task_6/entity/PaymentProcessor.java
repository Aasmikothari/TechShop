package com.techshop.entity;
import com.techshop.exception.PaymentFailedException;

public class PaymentProcessor {
	public static void processPayment(double amount, String paymentMethod, boolean simulateFailure) {
        if (amount <= 0) {
            throw new PaymentFailedException("Payment amount must be greater than zero.");
        }

        if (paymentMethod == null || paymentMethod.isEmpty()) {
            throw new PaymentFailedException("Invalid payment method.");
        }

        // Simulating payment success or failure
        if (simulateFailure) {
            throw new PaymentFailedException("Payment declined by the bank.");
        }

        System.out.println("Payment of " + amount + " via " + paymentMethod + " processed successfully.");
    }
}