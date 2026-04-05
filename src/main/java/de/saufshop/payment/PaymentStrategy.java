package de.saufshop.payment;

public interface PaymentStrategy {
    boolean pay(double amount);
    String getPaymentMethod();
    String getTransactionId();
}