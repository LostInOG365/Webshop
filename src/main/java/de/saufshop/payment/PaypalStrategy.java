package de.saufshop.payment;

import java.util.UUID;

public class PaypalStrategy implements PaymentStrategy {
    private String email;
    private String transactionId;
    
    public PaypalStrategy(String email) {
        this.email = email;
    }
    
    @Override
    public boolean pay(double amount) {
        // Simulate PayPal payment
        System.out.println("PayPal: Zahlung von " + amount + "€ für " + email);
        this.transactionId = "PP-" + UUID.randomUUID().toString().substring(0, 8);
        System.out.println("✓ PayPal-Transaktion erfolgreich: " + transactionId);
        return true;
    }
    
    @Override
    public String getPaymentMethod() {
        return "PayPal (" + email + ")";
    }
    
    @Override
    public String getTransactionId() {
        return transactionId;
    }
}