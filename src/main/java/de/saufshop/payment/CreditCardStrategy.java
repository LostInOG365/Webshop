package de.saufshop.payment;

import java.util.UUID;

public class CreditCardStrategy implements PaymentStrategy {
    private String cardNumber;
    private String cardHolder;
    private String transactionId;
    
    public CreditCardStrategy(String cardNumber, String cardHolder) {
        this.cardNumber = maskCardNumber(cardNumber);
        this.cardHolder = cardHolder;
    }
    
    @Override
    public boolean pay(double amount) {
        // Simulate Credit Card payment
        System.out.println("Kreditkarte: Zahlung von " + amount + "€");
        System.out.println("Karte: " + cardNumber);
        System.out.println("Karteninhaber: " + cardHolder);
        this.transactionId = "CC-" + UUID.randomUUID().toString().substring(0, 8);
        System.out.println("✓ Kreditkarten-Transaktion erfolgreich: " + transactionId);
        return true;
    }
    
    @Override
    public String getPaymentMethod() {
        return "Kreditkarte (" + cardNumber + ")";
    }
    
    @Override
    public String getTransactionId() {
        return transactionId;
    }
    
    private String maskCardNumber(String cardNumber) {
        return cardNumber.replaceAll("\\d(?=\\d{4})", "*");
    }
}