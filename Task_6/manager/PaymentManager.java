package com.techshop.manager;
import com.techshop.entity.Payment;
import com.techshop.exception.InvalidDataException;
import java.util.HashMap;

public class PaymentManager {
    private HashMap<Integer, Payment> paymentRecords;

    public PaymentManager() {
        this.paymentRecords = new HashMap<>();
    }

    public void processPayment(int paymentID, int orderID, double amount, String method) {
        if (paymentRecords.containsKey(paymentID)) {
            throw new InvalidDataException("Payment ID already exists.");
        }
        Payment payment = new Payment(paymentID, orderID, amount, method);
        paymentRecords.put(paymentID, payment);
        System.out.println("Payment processed: Order " + orderID + ", Amount: $" + amount + ", Method: " + method);
    }

    public Payment getPaymentDetails(int paymentID) {
        return paymentRecords.get(paymentID);
    }
}